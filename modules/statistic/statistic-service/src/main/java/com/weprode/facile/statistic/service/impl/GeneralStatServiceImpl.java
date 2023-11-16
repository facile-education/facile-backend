/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.statistic.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import org.json.JSONArray;

import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.statistic.constants.MatomoConstants;
import com.weprode.facile.statistic.service.GeneralStatLocalServiceUtil;
import com.weprode.facile.statistic.service.MatomoLocalServiceUtil;
import com.weprode.facile.statistic.service.base.GeneralStatServiceBaseImpl;
import com.weprode.facile.statistic.utils.UserProfile;
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

    private static final String[] COLOR_POOL = {"#FF7F72", "#B9A1FF", "#6190E0", "#1F51A7", "#27ae60"};

    // For files types
    private static final String[] VIOLET_COLOR_POOL = {"#D7AAE6", "#F7DBFF", "#DEC7E5", "#F3CBFF",
            "#21133F", "#E4B9F3", "#332158", "#D09CE1", "#4A3080",
            "#533E7F", "#C28BD5", "#685590", "#AD77C0"};

    // For homework types
    private static final String[] BLUE_COLOR_POOL = {"#436A86", "#B8D3FF", "#25406D", "#C5DAF3", "#314D7C",
            "#A1C2F9", "#45659B", "#597AB1", "#82A7E4", "#7699D3", "#85B7DE"};

    private static final String DATA_SET_DEFAULT = "{label: \"Main\"}";

    @JSONWebService(value = "get-sessions-count", method = "GET")
    public JSONObject getSessionsCount(Date startDate, Date endDate, long schoolId, String comparator) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!checkUserPermissions(user, schoolId)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        String period = MatomoConstants.PERIOD_DAY;

        try {
            // If Matomo is disabled, return
            if (PropsUtil.get(NeroSystemProperties.MATOMO_ENABLED) == null || !PropsUtil.get(NeroSystemProperties.MATOMO_ENABLED).equals("true")) {
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }

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

            JSONObject data = MatomoLocalServiceUtil.fetchStatistics(user, comparator, period, startDate, endDate,
                    profileIds, schoolIds, serviceIds, false);

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

    @JSONWebService(value = "get-actions-count", method = "GET")
    public JSONObject getActionsCount(Date startDate, Date endDate, long schoolId, long serviceId, String comparator) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!checkUserPermissions(user, schoolId)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        String period = MatomoConstants.PERIOD_DAY;

        try {
            // If Matomo is disabled, return
            if (PropsUtil.get(NeroSystemProperties.MATOMO_ENABLED) == null || !PropsUtil.get(NeroSystemProperties.MATOMO_ENABLED).equals("true")) {
                result.put(JSONConstants.SUCCESS, false);
                return result;
            }
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
            if (serviceId >= 0) {
                serviceIds.add(serviceId);
            }

            JSONObject data = MatomoLocalServiceUtil.fetchStatistics(user, comparator, period, startDate, endDate,
                    profileIds, schoolIds, serviceIds, true);

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
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!checkUserPermissions(user, schoolId)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        result.put(JSONConstants.COUNT, GeneralStatLocalServiceUtil.countActiveUsers(startDate, endDate, schoolId));
        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    @JSONWebService(value = "get-files-count", method = "GET")
    public JSONObject getFilesCount(Date startDate, Date endDate, long schoolId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!checkUserPermissions(user, schoolId)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONArray labels = new JSONArray();
            JSONArray datasets = new JSONArray();
            JSONObject dataset = new JSONObject(DATA_SET_DEFAULT);
            JSONArray data = new JSONArray();
            JSONArray backgroundColors = new JSONArray();
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
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!checkUserPermissions(user, schoolId)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONArray labels = new JSONArray();
            JSONArray datasets = new JSONArray();
            JSONObject dataset = new JSONObject(DATA_SET_DEFAULT);
            JSONArray data = new JSONArray();
            JSONArray backgroundColors = new JSONArray();
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
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!checkUserPermissions(user, schoolId)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        result.put(JSONConstants.SCHOOL_NEWS_COUNT, GeneralStatLocalServiceUtil.countNews(startDate, endDate, schoolId, true));
        result.put(JSONConstants.GROUP_NEWS_COUNT, GeneralStatLocalServiceUtil.countNews(startDate, endDate, schoolId, false));
        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    @JSONWebService(value = "get-messages-count", method = "GET")
    public JSONObject getMessagesCount(Date startDate, Date endDate, long schoolId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!checkUserPermissions(user, schoolId)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        result.put(JSONConstants.COUNT, GeneralStatLocalServiceUtil.countMessages(startDate, endDate, schoolId));
        result.put(JSONConstants.SUCCESS, true);

        return result;
    }

    @JSONWebService(value = "get-school-life-students-count", method = "GET")
    public JSONObject getSchoolLifeStudentsCount(Date startDate, Date endDate, long schoolId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!checkUserPermissions(user, schoolId)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            JSONArray labels = new JSONArray();
            JSONArray datasets = new JSONArray();
            JSONObject dataset = new JSONObject(DATA_SET_DEFAULT);
            JSONArray data = new JSONArray();
            JSONArray backgroundColors = new JSONArray();
            int totalCount = 0;

            int index = 0;
            for (Map.Entry<Integer, Integer> entry : GeneralStatLocalServiceUtil.countSchoolLifeStudents(startDate, endDate, schoolId).entrySet()) {
                labels.put("slot-" + entry.getKey());
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

    private boolean checkUserPermissions(User user, long schoolId) {
        if (RoleUtilsLocalServiceUtil.isAdministrator(user) || RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            return true;
        }

        return schoolId != 0 && (RoleUtilsLocalServiceUtil.isSchoolAdmin(user, schoolId) || RoleUtilsLocalServiceUtil.isDirectionMember(user));
    }

    @JSONWebService(value = "get-dashboard-statistics-count", method = "GET")
    public JSONObject getDashboardStatistics() {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        if (!RoleUtilsLocalServiceUtil.isDirectionMember(user) && !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        // If Matomo is not enabled, return
        if (PropsUtil.get(NeroSystemProperties.MATOMO_ENABLED) == null || !PropsUtil.get(NeroSystemProperties.MATOMO_ENABLED).equals("true")) {
            result.put(JSONConstants.SUCCESS, false);
            return result;
        }

        try {
            List<Long> profileIds = UserProfile.getAllStatProfileIds();
            List<Long> schoolIds = getUserSchoolIds(user);
            List<Long> serviceIds = new ArrayList<>();

            // Start and end are last week
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            Date endDate = cal.getTime();
            cal.add(Calendar.DATE, -7);
            Date startDate = cal.getTime();

            logger.debug("Fetch dashboard statistics from " + startDate + " to " + endDate);
            JSONObject data = MatomoLocalServiceUtil.fetchStatistics(user, "", MatomoConstants.PERIOD_DAY, startDate, endDate,
                    profileIds, schoolIds, serviceIds, false);

            logger.debug("matomo data=" + data);
            JSONArray datasets = data.getJSONArray(JSONConstants.DATASETS);
            JSONArray dayData = datasets.getJSONObject(0).getJSONArray(JSONConstants.DATA);
            int nbConnexions = 0;
            for (int i = 0 ; i < dayData.length() ; ++i) {
                nbConnexions += dayData.getLong(i);
            }

            result.put(JSONConstants.NB_CONNEXIONS, nbConnexions);

            // Same call for previous week
            cal.add(Calendar.DATE, -1);
            Date previousWeekEndDate = cal.getTime();
            cal.add(Calendar.DATE, -7);
            Date previousWeekStartDate = cal.getTime();
            JSONObject previousWeekData = MatomoLocalServiceUtil.fetchStatistics(user, "", MatomoConstants.PERIOD_DAY, previousWeekStartDate, previousWeekEndDate,
                    profileIds, schoolIds, serviceIds, false);

            JSONArray previousDayData = previousWeekData.getJSONArray(JSONConstants.DATASETS).getJSONObject(0).getJSONArray(JSONConstants.DATA);
            int previousNbConnexions = 0;
            for (int i = 0 ; i < previousDayData.length() ; ++i) {
                previousNbConnexions += previousDayData.getLong(i);
            }
            result.put(JSONConstants.PREVIOUS_NB_CONNEXIONS, previousNbConnexions);

            // Active users
            if (RoleUtilsLocalServiceUtil.isCollectivityAdmin(user) || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                result.put(JSONConstants.ACTIVE_USERS_COUNT, GeneralStatLocalServiceUtil.countActiveUsers(startDate, endDate, 0));
                result.put(JSONConstants.PREVIOUS_ACTIVE_USERS_COUNT, GeneralStatLocalServiceUtil.countActiveUsers(previousWeekStartDate, previousWeekEndDate, 0));
            } else {
                Organization userSchool = UserOrgsLocalServiceUtil.getUserSchools(user).get(0);
                result.put(JSONConstants.ACTIVE_USERS_COUNT, GeneralStatLocalServiceUtil.countActiveUsers(startDate, endDate, userSchool.getOrganizationId()));
                result.put(JSONConstants.PREVIOUS_ACTIVE_USERS_COUNT, GeneralStatLocalServiceUtil.countActiveUsers(previousWeekStartDate, previousWeekEndDate, userSchool.getOrganizationId()));
            }

            // Number of productions
            // For now, number of created news
            if (RoleUtilsLocalServiceUtil.isCollectivityAdmin(user) || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
                result.put(JSONConstants.GROUP_NEWS_COUNT, GeneralStatLocalServiceUtil.countNews(startDate, endDate, 0, false));
                result.put(JSONConstants.PREVIOUS_GROUP_NEWS_COUNT, GeneralStatLocalServiceUtil.countNews(previousWeekStartDate, previousWeekEndDate, 0, false));
            } else {
                Organization userSchool = UserOrgsLocalServiceUtil.getUserSchools(user).get(0);
                result.put(JSONConstants.GROUP_NEWS_COUNT, GeneralStatLocalServiceUtil.countNews(startDate, endDate, userSchool.getOrganizationId(), false));
                result.put(JSONConstants.PREVIOUS_GROUP_NEWS_COUNT, GeneralStatLocalServiceUtil.countNews(previousWeekStartDate, previousWeekEndDate, userSchool.getOrganizationId(), false));
            }
            result.put(JSONConstants.SUCCESS, true);
        } catch(Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Could not fetch dashboard statistics.", e);
        }

        return result;
    }

    private List<Long> getUserSchoolIds(User user) {
        List<Long> schoolIds = new ArrayList<>();

        // Do not add school filter for admins
        if (!RoleUtilsLocalServiceUtil.isAdministrator(user) && !RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) {
            for (Organization school : UserOrgsLocalServiceUtil.getUserSchools(user)) {
                if (RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isSchoolAdmin(user, school.getOrganizationId())) {
                    schoolIds.add(school.getOrganizationId());
                }
            }
        }
        return schoolIds;
    }
}
