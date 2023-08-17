package com.weprode.nero.statistic.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.nero.application.model.Application;
import com.weprode.nero.application.service.ApplicationLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.constants.NeroRoleConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.statistic.constants.MatomoConstants;
import com.weprode.nero.statistic.service.base.MatomoLocalServiceBaseImpl;
import com.weprode.nero.statistic.utils.UserProfile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(
        service = AopService.class
)
public class MatomoLocalServiceImpl extends MatomoLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(MatomoLocalServiceImpl.class);

    // Custom 'visit' dimension1 : schoolId
    private static final String MATOMO_SCHOOL_DIMENSION = "dimension1";
    // Custom 'visit' dimension2 : profileId
    private static final String MATOMO_PROFILE_DIMENSION = "dimension2";
    // Custom 'visit' dimension3 : old service_id not used anymore
    // Custom 'action' dimension4 : serviceId
    private static final String MATOMO_SERVICE_DIMENSION = "dimension4";


    public JSONObject fetchStatistics(User user, String compareOn, String period, Date startDate, Date endDate,
                                      List<Long> profileIds, List<Long> schoolIds, List<Long> serviceIds, boolean actions) throws PortalException, SystemException, IOException {

        logger.debug("About to fetch statistics from " + startDate + " to " + endDate + ", compare on " + compareOn
                + " with profileIds = " + profileIds + ", schoolIds = " + schoolIds + " and serviceIds = " + serviceIds);
        Map<String, String> args = new HashMap<>();
        String url = PropsUtil.get(NeroSystemProperties.MATOMO_API_URL);

        args.put("token_auth", PropsUtil.get(NeroSystemProperties.MATOMO_AUTH_TOKEN));
        args.put("idSite", PropsUtil.get(NeroSystemProperties.MATOMO_SITE_ID));
        args.put("module","API");
        args.put("format","json");
        args.put("method", actions ? "VisitsSummary.getActions" : "VisitsSummary.getVisits");

        // is it comparison ?
        if (compareOn == null) {
            compareOn = StringPool.BLANK;
        }

        // Get grouping criterium : daily by default
        if (period == null || period.isEmpty()) {
            period = MatomoConstants.PERIOD_DAY;
        }
        args.put("period", period);

        // Get start and end dates
        SimpleDateFormat classicDateFormat = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT);

        if (startDate == null) {
            GregorianCalendar calendar = new java.util.GregorianCalendar();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, -1);
            calendar.add(Calendar.DAY_OF_YEAR, +1);
            startDate = calendar.getTime();
        }

        if (endDate == null) {
            endDate = new Date();
        }
        args.put("date", classicDateFormat.format(startDate) + "," + classicDateFormat.format(endDate));

        List<String> segments = new ArrayList<>();
        List<String> metadata = new ArrayList<>();

        switch (compareOn) {
            case MatomoConstants.PROFILE_COMPARE:
                ArrayList<Long> personalProfileIds = new ArrayList<>();

                for (Long profileId : profileIds) {
                    if (UserProfile.STUDENT.getMatomoId() == profileId ||
                            UserProfile.PARENT.getMatomoId() == profileId ||
                            UserProfile.TEACHER.getMatomoId() == profileId) {
                        logger.debug("Comparison over profileId " + profileId);
                        metadata.add(getProfileMetadata(profileId));
                        segments.add(getProfileSegment(profileId, schoolIds, serviceIds));
                    } else {
                        logger.debug("Add profileId " + profileId + " to personal list");
                        personalProfileIds.add(profileId);
                    }
                }

                if (!personalProfileIds.isEmpty()) {
                    logger.debug("Comparison over profile metadata " + NeroRoleConstants.PERSONAL_INCLUSIVE);
                    metadata.add(NeroRoleConstants.PERSONAL_INCLUSIVE.substring(0, NeroRoleConstants.PERSONAL_INCLUSIVE.length() - 1));
                    segments.add(getSegment(personalProfileIds, schoolIds, serviceIds));
                }
                // Add total segment
                metadata.add("Total");
                segments.add(getSegment(new ArrayList<>(), schoolIds, serviceIds));

                break;
            case MatomoConstants.SCHOOL_COMPARE:
                for (Long schoolId : schoolIds) {
                    logger.debug("Comparison over schoolId " + schoolId);
                    metadata.add(getSchoolMetadata(schoolId));
                    segments.add(getSchoolSegment(schoolId, profileIds, serviceIds));
                }
                break;
            case MatomoConstants.SERVICE_COMPARE:
                for (Long serviceId : serviceIds) {
                    logger.debug("Comparison over serviceId " + serviceId);
                    metadata.add(getServiceMetadata(serviceId));
                    segments.add(getServiceSegment(serviceId, schoolIds, profileIds));
                }
                break;
            default:
                logger.debug("No comparison");
                metadata.add(getNoCompareMetadata(schoolIds, user));
                segments.add(getSegment(profileIds, schoolIds, serviceIds));
                break;
        }

        // Loop over segments to build api urls
        List<String> urls = new ArrayList<>();

        for (String segment : segments) {
            StringBuilder specificUrl = new StringBuilder(url);
            for (Map.Entry<String,String> s : args.entrySet()) {
                specificUrl.append(s.getKey()).append("=").append(s.getValue()).append("&");
            }
            specificUrl.append("segment=").append(segment);
            urls.add(specificUrl.toString());
        }

        return consumeAPI(urls, metadata);
    }

    private String getProfileMetadata(Long profileId) {
        String metadata = UserProfile.OTHER.getName();
        for (UserProfile userProfile : UserProfile.values()) {
            if (userProfile.getMatomoId() == profileId) {
                metadata = userProfile.getName();
                break;
            }
        }

        logger.debug("Comparison over profile metadata " + metadata);

        return metadata;
    }

    private String getProfileSegment(Long profileId, List<Long> schoolIds, List<Long> serviceIds) {
        ArrayList<Long> profileIds = new ArrayList<>();
        profileIds.add(profileId);

        return getSegment(profileIds, schoolIds, serviceIds);
    }

    private String getSegment(List<Long> profileIds, List<Long> schoolIds, List<Long> serviceIds) {
        StringBuilder segment = new StringBuilder();

        // Add school filter
        for (Long schoolId : schoolIds) {
            if (schoolId != 0) {
                segment.append(MATOMO_SCHOOL_DIMENSION).append("==").append(schoolId).append(",");
            }
        }

        // Remove last ','
        if (!segment.toString().isEmpty() && segment.toString().endsWith(",")){
            segment = new StringBuilder(segment.substring(0, segment.length() - 1));
            segment.append(";");
        }

        // Add profile filter
        for (Long profileId : profileIds) {
            if (profileId != 0) {
                segment.append(MATOMO_PROFILE_DIMENSION).append("==").append(profileId).append(",");
            }
        }

        // Remove last ','
        if (!segment.toString().isEmpty() && segment.toString().endsWith(",")){
            segment = new StringBuilder(segment.substring(0, segment.length() - 1));
            segment.append(";");
        }

        // Add service filter
        for (Long serviceId : serviceIds) {
            if (serviceId != -1) { // -1 means all services
                segment.append(MATOMO_SERVICE_DIMENSION).append("==").append(serviceId).append(",");
            }
        }

        // Remove last ','
        if (!segment.toString().isEmpty() && segment.toString().endsWith(",")) {
            segment = new StringBuilder(segment.substring(0, segment.length() - 1));
            segment.append(";");
        }

        // Remove last ';'
        // Service is segment's last argument
        if (!segment.toString().isEmpty() && (segment.toString().endsWith(",") || segment.toString().endsWith(";"))) {
            segment = new StringBuilder(segment.substring(0, segment.length() - 1));
        }

        return segment.toString();
    }

    private String getSchoolMetadata (Long schoolId) throws PortalException, SystemException {
        String metadata;

        if (schoolId != 0) {
            Organization org = OrganizationLocalServiceUtil.getOrganization(schoolId);
            metadata = OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), true);
        }
        else {
            metadata = "Tous";
        }
        logger.debug("Comparison over school metadata " + metadata);

        return metadata;
    }

    private String getSchoolSegment (Long schoolId, List<Long> profileIds, List<Long> serviceIds) {
        ArrayList<Long> schoolIds = new ArrayList<>();
        schoolIds.add(schoolId);

        return getSegment(profileIds, schoolIds, serviceIds);
    }

    private String getServiceMetadata(Long serviceId) {
        String metadata = "";

        try {
            Application application = ApplicationLocalServiceUtil.getApplication(serviceId);
            // Get service label
            metadata = application.getApplicationName();
        } catch (Exception e) {
            logger.error("Could not find application with id = " + serviceId);
        }

        logger.debug("Comparison over service metadata " + metadata);

        return metadata;
    }

    public String getServiceSegment(Long serviceId, List<Long> schoolIds, List<Long> profileIds) {
        ArrayList<Long> serviceIds = new ArrayList<>();
        serviceIds.add(serviceId);

        return getSegment(profileIds, schoolIds, serviceIds);
    }

    private String getNoCompareMetadata (List<Long> schoolIds, User user) throws SystemException {
        String metadata = " ";

        if (RoleUtilsLocalServiceUtil.isENTAdmin(user) || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            List<Organization> schoolList = UserOrgsLocalServiceUtil.getUserSchools(user);

            if (schoolIds.size() == 1) {
                Long schoolId = schoolIds.get(0);

                for (Organization school : schoolList) {
                    if (school.getOrganizationId() == schoolId) {
                        metadata = OrgUtilsLocalServiceUtil.formatOrgName(school.getName(), true);
                        break;
                    }
                }
            }
        } else {
            Organization rattachSchool = UserOrgsLocalServiceUtil.getEtabRatachement(user);

            List<Organization> schoolList = rattachSchool.getSuborganizations();
            if (schoolIds.size() == 1) {
                Long schoolId = schoolIds.get(0);

                for (Organization school : schoolList) {
                    if (school.getOrganizationId() == schoolId) {
                        metadata = OrgUtilsLocalServiceUtil.formatOrgName(school.getName(), true);
                        break;
                    }
                }
            }
        }

        return metadata;
    }

    private JSONObject consumeAPI (List<String> urls, List<String> metadata) throws IOException, JSONException {
        int cpt = 0;

        // For each built url, call the API and store data
        JSONObject dataJson = new JSONObject();
        JSONArray datasets = new JSONArray();
        JSONArray datesArray = new JSONArray();

        // Fetch statistics from urls
        for (String specificUrl : urls) {

            // Remove last '&'
            if (specificUrl.endsWith("&")) {
                specificUrl = specificUrl.substring(0, specificUrl.length()-1);
            }
            logger.debug("Statistics url : " + specificUrl);

            InputStream is = new URL(specificUrl).openStream();
            JSONObject dataset = new JSONObject();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                // Read JSON from matomo
                StringBuilder sb;
                sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String jsonText = sb.toString();
                JSONObject matomoJson = new JSONObject(jsonText);

                String datasetLabel = metadata.get(cpt);
                if (datasetLabel.trim().equals("")) {
                    datasetLabel = "Nombre de connexions";
                }

                dataset.put(JSONConstants.LABEL, datasetLabel);

                // Get json keys (dates) and sort them
                List<String> dates = new ArrayList<>();
                for (java.util.Iterator<String> iterator = matomoJson.keys(); iterator.hasNext();) {
                    dates.add(iterator.next());
                }
                java.util.Collections.sort(dates);

                JSONArray dataArray = new JSONArray();

                for (String dateStr : dates) {

                    Integer value = matomoJson.getInt(dateStr);
                    // Remove error messages
                    if ("message".equals(dateStr) || "result".equals(dateStr)) {
                        continue;
                    }
                    // Get dates only on first url processed
                    if (cpt == 0) {
                        datesArray.put(dateStr);
                    }
                    dataArray.put(value);
                }

                dataset.put(JSONConstants.DATA, dataArray);

            }
            finally {
                is.close();
            }
            cpt++;
            datasets.put(dataset);
        }

        dataJson.put(JSONConstants.LABELS, datesArray);
        dataJson.put(JSONConstants.DATASETS, datasets);

        logger.debug("Fetched statistics are " + dataJson);

        return dataJson;
    }

    public long getUserProfileId(User user) {
        List<Role> roles = RoleUtilsLocalServiceUtil.getUserEntRoles(user);
        for (Role role : roles) {
            for (UserProfile userProfile : UserProfile.values()) {
                if (userProfile.getRoleId() == role.getRoleId()) {
                    return userProfile.getMatomoId();
                }
            }
        }
        return 0;
    }

}
