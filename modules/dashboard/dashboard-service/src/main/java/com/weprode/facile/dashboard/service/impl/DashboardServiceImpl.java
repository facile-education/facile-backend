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

package com.weprode.facile.dashboard.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.facile.agenda.model.Event;
import com.weprode.facile.agenda.service.EventLocalServiceUtil;
import com.weprode.facile.application.service.BroadcastLocalServiceUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.commons.properties.NeroSystemProperties;
import com.weprode.facile.dashboard.service.base.DashboardServiceBaseImpl;
import com.weprode.facile.group.model.GroupActivity;
import com.weprode.facile.group.service.GroupActivityLocalServiceUtil;
import com.weprode.facile.news.model.News;
import com.weprode.facile.news.service.NewsLocalServiceUtil;
import com.weprode.facile.preference.model.UserProperties;
import com.weprode.facile.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.model.CDTSession;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.facile.school.life.service.SessionStudentLocalServiceUtil;
import com.weprode.facile.user.service.NewsAdminLocalServiceUtil;
import com.weprode.facile.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component(
        property = {
                "json.web.service.context.name=dashboard",
                "json.web.service.context.path=Dashboard"
        },
        service = AopService.class
)
public class DashboardServiceImpl extends DashboardServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(DashboardServiceImpl.class);

    @JSONWebService(value = "init-dashboard", method = "GET")
    public JSONObject initDashboard() {
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

        try {
            boolean isDirectionMember = RoleUtilsLocalServiceUtil.isDirectionMember(user);
            boolean isStudentOrParent = RoleUtilsLocalServiceUtil.isStudentOrParent(user);

            result.put(JSONConstants.HAS_ACTIVITY_THREAD_WIDGET, true);
            result.put(JSONConstants.HAS_SCHOOL_NEWS_WIDGET, true);
            result.put(JSONConstants.HAS_DIARY_WIDGET, true);
            result.put(JSONConstants.HAS_HOMEWORK_WIDGET, isStudentOrParent && BroadcastLocalServiceUtil.isApplicationBroadcastedToUser(user.getUserId(), "cdt"));
            result.put(JSONConstants.HAS_EDT_WIDGET, isStudentOrParent || RoleUtilsLocalServiceUtil.isTeacher(user));
            result.put(JSONConstants.HAS_STATISTIC_WIDGET, (isDirectionMember || RoleUtilsLocalServiceUtil.isCollectivityAdmin(user)) && PropsUtil.get(NeroSystemProperties.MATOMO_ENABLED) != null && PropsUtil.get(NeroSystemProperties.MATOMO_ENABLED).equals("true"));

            // If we are a parent, get our children list
            if (RoleUtilsLocalServiceUtil.isParent(user)) {
                List<User> children = UserRelationshipLocalServiceUtil.getChildren(user.getUserId());
                JSONArray jsonChildren = new JSONArray();
                for (User child : children) {
                    JSONObject jsonChild = new JSONObject();
                    jsonChild.put(JSONConstants.USER_ID, child.getUserId());
                    jsonChild.put(JSONConstants.FIRST_NAME, child.getFirstName());
                    jsonChild.put(JSONConstants.LAST_NAME, child.getLastName());
                    jsonChildren.put(jsonChild);
                }
                result.put(JSONConstants.CHILDREN, jsonChildren);
            }

            // Check delegations
            boolean isDelegate = NewsAdminLocalServiceUtil.isUserDelegate(user);
            result.put(JSONConstants.IS_DELEGATE, isDelegate);

            // All personals can add group news because they have communities
            boolean canAddGroupNews = RoleUtilsLocalServiceUtil.isTeacher(user) || RoleUtilsLocalServiceUtil.isPersonal(user) || RoleUtilsLocalServiceUtil.isCollectivityAdmin(user);
            // TODO: Chek if user broadast polupations list is not empty to prevent open an unfillable modal
            result.put(JSONConstants.CAN_ADD_GROUP_NEWS, canAddGroupNews);
            result.put(JSONConstants.CAN_ADD_SCHOOL_NEWS, isDirectionMember || isDelegate  || RoleUtilsLocalServiceUtil.isCollectivityAdmin(user));
            result.put(JSONConstants.CAN_ADD_EVENTS, isDirectionMember || isDelegate || RoleUtilsLocalServiceUtil.isCollectivityAdmin(user));

            // Set last dashboard access date
            UserProperties userProperties = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId());
            userProperties.setLastDashboardAccessDate(new Date());
            UserPropertiesLocalServiceUtil.updateUserProperties(userProperties);
            logger.debug("Set last dashboard access date to " + new SimpleDateFormat(JSONConstants.FULL_FRENCH_FORMAT).format(new Date()));

            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error when initiating dashboard for " + user.getFullName() + "(id=" + user.getUserId() + ")", e);
        }
        return result;
    }

    @JSONWebService(value = "get-user-schedule", method = "GET")
    public JSONObject getUserSchedule(long userId, String date, boolean goForward) {
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
        // Students, parents and teachers only
        if (!RoleUtilsLocalServiceUtil.isStudentOrParent(user) && !RoleUtilsLocalServiceUtil.isTeacher(user)) {
            logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets scheduler for user " + userId);
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
        }

        try {
            DateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            Date scheduleDate = df.parse(date);
            User targetUser = user;


            JSONArray eventList = new JSONArray();
            List<CDTSession> cdtSessionList = new ArrayList<>();
            if (RoleUtilsLocalServiceUtil.isStudent(user)) {
                cdtSessionList = CDTSessionLocalServiceUtil.getNextStudentDaySessions(user.getUserId(), scheduleDate, goForward);

            } else if (RoleUtilsLocalServiceUtil.isParent(user)) {
                // Check that the parents only see the schedule of their own children
                if (UserRelationshipLocalServiceUtil.isChild(user.getUserId(), userId)) {
                    cdtSessionList = CDTSessionLocalServiceUtil.getNextStudentDaySessions(userId, scheduleDate, goForward);
                    targetUser = UserLocalServiceUtil.getUser(userId);
                }

            } else if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
                cdtSessionList = CDTSessionLocalServiceUtil.getNextTeacherDaySessions(user.getUserId(), scheduleDate, goForward);
            }

            for (CDTSession cdtSession : cdtSessionList) {
                JSONObject cdtSessionJson = cdtSession.convertToJSON(targetUser);
                eventList.put(cdtSessionJson);
            }

            // Pick 1 session from the list to get its date
            Date reachedDate;
            if (!cdtSessionList.isEmpty()) {
                reachedDate = cdtSessionList.get(0).getStart();
            } else {
                reachedDate = scheduleDate;
            }
            result.put(JSONConstants.DATE, df.format(reachedDate));

            // Set min date to 0:00 at date
            Calendar cal = Calendar.getInstance();
            cal.setTime(reachedDate);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            Date minDate = cal.getTime();

            // Set max date at 00:00 the next day
            cal.add(Calendar.DATE, 1);
            Date maxDate = cal.getTime();

            JSONArray jsonSchoollifeSessions = SessionStudentLocalServiceUtil.getStudentSessions(user, userId, minDate, maxDate);
            for (int i = 0 ; i < jsonSchoollifeSessions.length() ; i++) {
                JSONObject jsonSchoollifeSession = jsonSchoollifeSessions.getJSONObject(i);
                jsonSchoollifeSession.put(JSONConstants.TEACHERS, jsonSchoollifeSession.getJSONArray(JSONConstants.TEACHERS));
                try {
                    Calendar sessionCal = Calendar.getInstance(Locale.FRANCE);
                    sessionCal.setTime(new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(jsonSchoollifeSession.getString("startDate")));
                    int weekNb = sessionCal.get(Calendar.WEEK_OF_YEAR);
                    jsonSchoollifeSession.put(JSONConstants.SESSION_URL, "#/horaires" + "?weekNb=" + weekNb);
                } catch (Exception e) {
                    logger.error("Error building schoollife session url", e);
                }
                eventList.put(jsonSchoollifeSession);
            }

            result.put(JSONConstants.SUCCESS, true);
            result.put(JSONConstants.EVENT_LIST, eventList);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error when getting userId "+userId+" schedule", e);
        }

        return result;
    }

    @JSONWebService(value = "get-dashboard-activity", method = "GET")
    public JSONObject getDashboardActivity(long groupId, String maxDate, int nbResults, boolean withNews, boolean withDocs, boolean withMemberships, boolean withSchoollife, boolean withSessions) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            JSONArray jsonActivities = new JSONArray();
            int nbNewActivities = 0;
            Date lastDashboardAccessDate = UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId()).getLastDashboardAccessDate();
            List<Long> groupIds = new ArrayList<>();
            if (groupId > 0) {
                groupIds.add(groupId);
            } else {
                groupIds = UserUtilsLocalServiceUtil.getUserGroupIds(user.getUserId());
            }
            Date maximumDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(maxDate);

            List<GroupActivity> groupActivities = GroupActivityLocalServiceUtil.getDashboardGroupsActivities(user.getUserId(), groupIds, maximumDate, nbResults,
                    withNews, withDocs, withMemberships, withSchoollife, withSessions);
            boolean isAuthorOfAllNewActivity = true;
            for (GroupActivity groupActivity : groupActivities) {
                if (lastDashboardAccessDate == null || groupActivity.getActivityDate().after(lastDashboardAccessDate)) {
                    nbNewActivities++;
                    if (GroupActivityLocalServiceUtil.getAuthorId(groupActivity) != user.getUserId()) {
                        isAuthorOfAllNewActivity = false;
                    }
                }
                JSONObject jsonActivity = GroupActivityLocalServiceUtil.convertGroupActivity(user.getUserId(), groupActivity);
                if (jsonActivity != null) {
                    jsonActivities.put(jsonActivity);
                }
            }
            if (isAuthorOfAllNewActivity) {
                nbNewActivities = 0;    // Don't display notifications if we are the author of all news activities
            }

            result.put(JSONConstants.ACTIVITIES, jsonActivities);
            // Last dashboard access date is store in UTC, convert it with user's timezone
            DateFormat sdf = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
            sdf.setTimeZone(user.getTimeZone());
            result.put(JSONConstants.LAST_DASHBOARD_ACCESS_DATE, lastDashboardAccessDate == null ? "" : sdf.format(lastDashboardAccessDate));
            result.put(JSONConstants.NB_NEW_ACTIVITIES, nbNewActivities);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error while fetching dashboard activity", e);
        }

        return result;
    }

    @JSONWebService(value = "check-dashboard-parameter", method = "GET")
    public JSONObject checkDashboardParameter(long dashboardId) {
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

        try {
            // dashboardId can be either an eventId or an announceId
            Event event = null;
            try {
                event = EventLocalServiceUtil.getEvent(dashboardId);
            } catch (Exception e) {
                // Nothing
            }
            if (event != null) {
                // This is an event, check permission
                if (EventLocalServiceUtil.hasUserEvent(user.getUserId(), dashboardId)) {
                    result.put(JSONConstants.IS_AUTHORIZED, true);
                    result.put(JSONConstants.IS_EVENT, true);
                } else {
                    logger.error("User " + user.getFullName() + " tries to access event " + dashboardId + " but does have permission to do it");
                }
            }
            News news = null;
            try {
                news = NewsLocalServiceUtil.getNews(dashboardId);
            } catch (Exception e) {
                // Nothing
            }
            if (news != null) {
                // This is a news, check permission
                if (NewsLocalServiceUtil.hasUserNews(user.getUserId(), dashboardId)) {
                    result.put(JSONConstants.IS_AUTHORIZED, true);
                    result.put(JSONConstants.IS_NEWS, true);
                } else {
                    logger.error("User " + user.getFullName() + " tries to access news " + dashboardId + " but does have permission to do it");
                }
            }
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error when checking dashboard parameter " + dashboardId, e);
        }

        return result;
    }


}
