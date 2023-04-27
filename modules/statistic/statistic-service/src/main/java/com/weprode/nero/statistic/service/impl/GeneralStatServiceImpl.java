package com.weprode.nero.statistic.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.statistic.constants.MatomoConstants;
import com.weprode.nero.statistic.service.GeneralStatLocalServiceUtil;
import com.weprode.nero.statistic.service.MatomoLocalServiceUtil;
import com.weprode.nero.statistic.service.base.GeneralStatServiceBaseImpl;
import com.weprode.nero.statistic.utils.UserProfile;
import org.osgi.service.component.annotations.Component;

import java.util.*;

@Component(
        property = {
                "json.web.service.context.name=statistic",
                "json.web.service.context.path=GeneralStat"
        },
        service = AopService.class
)
public class GeneralStatServiceImpl extends GeneralStatServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(GeneralStatServiceImpl.class);

    private static final String[] COLOR_POOL = {"#FF7F72", "#B9A1FF", "#6190E0", "#1F51A7"};

    // For files types
    private static final String[] VIOLET_COLOR_POOL = {"#D7AAE6", "#F7DBFF", "#DEC7E5", "#F3CBFF",
            "#21133F", "#E4B9F3", "#332158", "#D09CE1", "#4A3080",
            "#533E7F", "#C28BD5", "#685590", "#AD77C0"};

    // For homework types
    private static final String[] BLUE_COLOR_POOL = {"#436A86", "#B8D3FF", "#25406D", "#C5DAF3", "#314D7C",
            "#A1C2F9", "#45659B", "#597AB1", "#82A7E4", "#7699D3", "#85B7DE"};

    @JSONWebService(value = "get-sessions-count", method = "GET")
    public JSONObject getSessionsCount(Date startDate, Date endDate, long schoolId, long serviceId, String comparator) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            return result;
        }

        if (!checkUserPermissions(user, schoolId)) {
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            return result;
        }

        String period = MatomoConstants.PERIOD_DAY;

        try {
            List<Long> profileIds = UserProfile.getAllStatProfileIds();

            List<Long> schoolIds = new ArrayList<>();
            if (schoolId == 0) {
                for (Organization school: OrgUtilsLocalServiceUtil.getAllSchools()) {
                    schoolIds.add(school.getOrganizationId());
                }
            } else {
                schoolIds.add(schoolId);
            }

            List<Long> serviceIds = new ArrayList<>();
            if (serviceId > 0) {
                serviceIds.add(serviceId);
            }

            JSONObject data = MatomoLocalServiceUtil.fetchStatistics(user, comparator, period, startDate, endDate,
                    profileIds, schoolIds, serviceIds);

            // Get colors from pool
            JSONArray datasets = data.getJSONArray(JSONConstants.DATASETS);
            for (int i = 0 ; i < datasets.length() ; ++i) {
                JSONObject dataset = datasets.getJSONObject(i);
                dataset.put(JSONConstants.POINT_BORDER_COLOR, "white");
                dataset.put(JSONConstants.POINT_BACKGROUND_COLOR, COLOR_POOL[i]);
                dataset.put(JSONConstants.BORDER_COLOR, COLOR_POOL[i]);
                dataset.put(JSONConstants.BACKGROUND_COLOR, COLOR_POOL[i] + "75");
            }

            result.put(JSONConstants.DATASETS, datasets);
            result.put(JSONConstants.LABELS, data.getJSONArray(JSONConstants.LABELS));
            result.put(JSONConstants.SUCCESS, true);
        } catch(Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Could not fetch sessions statistics.", e);
        }

        return result;
    }

    @JSONWebService(value = "get-active-users-count", method = "GET")
    public JSONObject getActiveUsersCount(Date startDate, Date endDate, long schoolId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            return result;
        }

        if (!checkUserPermissions(user, schoolId)) {
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            return result;
        }

        result.put(JSONConstants.COUNT, GeneralStatLocalServiceUtil.countActiveUsers(startDate, endDate, schoolId));
        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    @JSONWebService(value = "get-files-count", method = "GET")
    public JSONObject getFilesCount(Date startDate, Date endDate, long schoolId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            return result;
        }

        if (!checkUserPermissions(user, schoolId)) {
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            return result;
        }

        try {
            JSONArray labels = JSONFactoryUtil.createJSONArray();
            JSONArray datasets = JSONFactoryUtil.createJSONArray();
            JSONObject dataset = JSONFactoryUtil.createJSONObject("{label: \"Main\"}");
            JSONArray data = JSONFactoryUtil.createJSONArray();
            JSONArray backgroundColors = JSONFactoryUtil.createJSONArray();
            int totalCount = 0;

            int index = 0;
            for (Map.Entry<String, Integer> entry : GeneralStatLocalServiceUtil.countFiles(startDate, endDate, schoolId).entrySet()) {
                labels.put(entry.getKey());
                data.put(entry.getValue());
                backgroundColors.put(VIOLET_COLOR_POOL[index++]);
                totalCount += entry.getValue();
            }

            dataset.put(JSONConstants.DATA, data);
            dataset.put(JSONConstants.BACKGROUND_COLOR, backgroundColors);
            datasets.put(dataset);

            result.put(JSONConstants.LABELS, labels);
            result.put(JSONConstants.DATASETS, datasets);
            result.put(JSONConstants.TOTAL_COUNT, totalCount);
            result.put(JSONConstants.SUCCESS, true);
        } catch(Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Could not fetch files statistics.", e);
        }

        return result;
    }

    @JSONWebService(value = "get-homeworks-count", method = "GET")
    public JSONObject getHomeworksCount(Date startDate, Date endDate, long schoolId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            return result;
        }

        if (!checkUserPermissions(user, schoolId)) {
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            return result;
        }

        try {
            JSONArray labels = JSONFactoryUtil.createJSONArray();
            JSONArray datasets = JSONFactoryUtil.createJSONArray();
            JSONObject dataset = JSONFactoryUtil.createJSONObject("{label: \"Main\"}");
            JSONArray data = JSONFactoryUtil.createJSONArray();
            JSONArray backgroundColors = JSONFactoryUtil.createJSONArray();
            int totalCount = 0;

            int index = 0;
            for (Map.Entry<Integer, Integer> entry : GeneralStatLocalServiceUtil.countHomeworks(startDate, endDate, schoolId).entrySet()) {
                labels.put(entry.getKey());
                data.put(entry.getValue());
                backgroundColors.put(BLUE_COLOR_POOL[index++]);
                totalCount += entry.getValue();
            }

            dataset.put(JSONConstants.DATA, data);
            dataset.put(JSONConstants.BACKGROUND_COLOR, backgroundColors);
            datasets.put(dataset);

            result.put(JSONConstants.LABELS, labels);
            result.put(JSONConstants.DATASETS, datasets);
            result.put(JSONConstants.TOTAL_COUNT, totalCount);
            result.put(JSONConstants.SUCCESS, true);
        } catch(Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Could not fetch homeworks statistics.", e);
        }

        return result;
    }

    @JSONWebService(value = "get-news-count", method = "GET")
    public JSONObject getNewsCount(Date startDate, Date endDate, long schoolId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            return result;
        }

        if (!checkUserPermissions(user, schoolId)) {
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            return result;
        }

        result.put(JSONConstants.SCHOOL_NEWS_COUNT, GeneralStatLocalServiceUtil.countNews(startDate, endDate, schoolId, true));
        result.put(JSONConstants.GROUP_NEWS_COUNT, GeneralStatLocalServiceUtil.countNews(startDate, endDate, schoolId, false));
        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    @JSONWebService(value = "get-messages-count", method = "GET")
    public JSONObject getMessagesCount(Date startDate, Date endDate, long schoolId) {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            return result;
        }

        if (!checkUserPermissions(user, schoolId)) {
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.NOT_ALLOWED_EXCEPTION);
            return result;
        }

        result.put(JSONConstants.COUNT, GeneralStatLocalServiceUtil.countMessages(startDate, endDate, schoolId));
        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    private boolean checkUserPermissions(User user, long schoolId) {
        if (RoleUtilsLocalServiceUtil.isAdministrator(user) || RoleUtilsLocalServiceUtil.isENTAdmin(user)) {
            return true;
        }

        return schoolId != 0 && (RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId) || RoleUtilsLocalServiceUtil.isDirectionMember(user));
    }

    @JSONWebService(value = "get-dashboard-statistics-count", method = "GET")
    public JSONObject getDashboardStatistics() {
        JSONObject result = JSONFactoryUtil.createJSONObject();

        User user;
        try {
            user = getGuestOrUser();
        } catch (Exception e) {
            logger.error(e);
            result.put(JSONConstants.SUCCESS, false);
            result.put(JSONConstants.ERROR, JSONConstants.AUTH_EXCEPTION);
            return result;
        }

        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user)) {
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        String period = MatomoConstants.PERIOD_WEEK;

        try {
            List<Long> profileIds = UserProfile.getAllStatProfileIds();
            List<Long> serviceIds = new ArrayList<>();

            List<Long> schoolIds = new ArrayList<>();
            long schoolId = UserOrgsLocalServiceUtil.getUserSchools(user).get(0).getOrganizationId();
            schoolIds.add(schoolId);

            // Start and end are last week
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            Date endDate = cal.getTime();
            cal.add(Calendar.DATE, -7);
            Date startDate = cal.getTime();

            JSONObject data = MatomoLocalServiceUtil.fetchStatistics(user, "", period, startDate, endDate,
                    profileIds, schoolIds, serviceIds);

            // Get colors from pool
            JSONArray datasets = data.getJSONArray(JSONConstants.DATASETS);
            for (int i = 0 ; i < datasets.length() ; ++i) {
                JSONObject dataset = datasets.getJSONObject(i);
                dataset.put(JSONConstants.POINT_BORDER_COLOR, "white");
                dataset.put(JSONConstants.POINT_BACKGROUND_COLOR, COLOR_POOL[i].replace("0.55", "1"));
                dataset.put(JSONConstants.BORDER_COLOR, COLOR_POOL[i].replace("0.55", "1"));
                // dataset.put("backgroundColor", COLOR_POOL[i].replace("0.55", "0.25"));
            }

            result.put(JSONConstants.DATASETS, datasets);
            result.put(JSONConstants.LABELS, data.getJSONArray(JSONConstants.LABELS));
            result.put(JSONConstants.SUCCESS, true);
        } catch(Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Could not fetch dashboard statistics.", e);
        }

        return result;
    }
}
