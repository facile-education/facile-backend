/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.base.CDTSessionServiceBaseImpl;
import com.weprode.nero.school.life.service.SchoollifeSessionLocalServiceUtil;
import com.weprode.nero.school.life.service.SessionStudentLocalServiceUtil;
import com.weprode.nero.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=schedule",
		"json.web.service.context.path=CDTSession"
	},
	service = AopService.class
)
public class CDTSessionServiceImpl extends CDTSessionServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(CDTSessionServiceImpl.class);

	@JSONWebService(value = "get-user-sessions", method = "GET")
	public JSONObject getUserSessions(long userId, String minDateStr, String maxDateStr) {
		JSONObject result = new JSONObject();

		User currentUser;
		try {
			currentUser = getGuestOrUser();
			if (currentUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		logger.info("User " + currentUser.getUserId() + " fetches sessions for userId " + userId + " from " + minDateStr + " to " + maxDateStr);

		try {
			SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
			Date minDate = df.parse(minDateStr);
			Date maxDate = df.parse(maxDateStr);
			User targetUser = UserLocalServiceUtil.getUser(userId);

			// Students have only the right to fetch their own schedule
			if (RoleUtilsLocalServiceUtil.isStudent(currentUser) && currentUser.getUserId() != userId) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			// Parents have only the right to fetch their children's schedule
			if (RoleUtilsLocalServiceUtil.isParent(currentUser) && !UserRelationshipLocalServiceUtil.isChild(currentUser.getUserId(), userId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			// 1. CDT sessions
			List<CDTSession> userSessions = new ArrayList<>();
			if (RoleUtilsLocalServiceUtil.isStudent(targetUser)) {
				userSessions = CDTSessionLocalServiceUtil.getStudentSessions(userId, minDate, maxDate);
			} else if (RoleUtilsLocalServiceUtil.isTeacher(targetUser)) {
				userSessions = CDTSessionLocalServiceUtil.getTeacherSessions(userId, minDate, maxDate);
			}

			result.put(JSONConstants.SESSIONS, CDTSessionLocalServiceUtil.convertSessions(userSessions, currentUser));

			// 2. Schoollife sessions
			JSONArray jsonSchoollifeSessions = new JSONArray();
			if (RoleUtilsLocalServiceUtil.isStudent(targetUser)) {
				jsonSchoollifeSessions = SessionStudentLocalServiceUtil.getStudentSessions(currentUser, targetUser.getUserId(), minDate, maxDate);
			} else if (RoleUtilsLocalServiceUtil.isTeacher(targetUser)) {
				jsonSchoollifeSessions = SchoollifeSessionLocalServiceUtil.getTeacherSessions(targetUser.getUserId(), minDate, maxDate);
			}
			result.put(JSONConstants.SCHOOLLIFE_SESSIONS, jsonSchoollifeSessions);

			result.put(JSONConstants.SUCCESS, true);

			return result;
		} catch (Exception e) {
			logger.error(e);
			return JSONProxy.getJSONReturnInErrorCase("Error when get sessions for agent : " + currentUser.getFullName() + " ( id: " + currentUser.getUserId() + ")");
		}
	}

	@JSONWebService(value = "get-group-sessions", method = "GET")
	public JSONObject getGroupSessions(long groupId, String minDateStr, String maxDateStr) {
		JSONObject result = new JSONObject();

		User currentUser;
		try {
			currentUser = getGuestOrUser();
			if (currentUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		logger.info("User " + currentUser.getUserId() + " fetches sessions for group " + groupId + " from " + minDateStr + " to " + maxDateStr);

		try {
			SimpleDateFormat df = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
			Date minDate = df.parse(minDateStr);
			Date maxDate = df.parse(maxDateStr);

			if (groupId != 0 && !RoleUtilsLocalServiceUtil.isStudentOrParent(currentUser)) {
				List<CDTSession> userSessions = CDTSessionLocalServiceUtil.getGroupSessions(groupId, minDate, maxDate, true);
				result.put(JSONConstants.SESSIONS, CDTSessionLocalServiceUtil.convertSessions(userSessions, currentUser));
				result.put(JSONConstants.SCHOOLLIFE_SESSIONS, new JSONArray());
				result.put(JSONConstants.SUCCESS, true);
			} else {
				result.put(JSONConstants.SUCCESS, false);
			}

		} catch (Exception e) {
			logger.error("Error when user " + currentUser.getFullName() + " fetches sessions for group " + groupId, e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "get-session-details", method = "GET")
	public JSONObject getSessionDetails(long sessionId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		JSONObject sessionDetails;
		try {
			sessionDetails = CDTSessionLocalServiceUtil.fetchCDTSession(sessionId).convertToJSON(user);
		} catch (SystemException e) {
			logger.error("Could not fetch sessionId = " + sessionId, e);
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}

		// Student list
		List<User> studentList = CDTSessionLocalServiceUtil.getSessionStudents(sessionId);
		JSONArray jsonStudents = UserUtilsLocalServiceUtil.convertUsersToJson(studentList);
		sessionDetails.put(JSONConstants.STUDENTS, jsonStudents);

		result.put(JSONConstants.SESSION_DETAILS, sessionDetails);
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}

	@JSONWebService(value = "create-session", method = "POST")
	public JSONObject createSession(long groupId, String subject, String room, int dayNumber, int slot, String startHour, String endHour, String teacherIds, boolean isRecurrent) {
		logger.info("Creating manual session(s) for group " + groupId + ", subject=" + subject + ", day= " + dayNumber + ", startHour=" + startHour +", endHour=" + endHour + ", teacherIds=" + teacherIds + ", isRecurrent is " + isRecurrent);
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		if (!RoleUtilsLocalServiceUtil.isTeacher(user) && !RoleUtilsLocalServiceUtil.isPersonal(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}
		try {
			Group group = GroupLocalServiceUtil.getGroup(groupId);
			boolean isCommunity = group.isRegularSite();
			String fullCoursName;
			Organization org = null;
			if (isCommunity) {
				fullCoursName = group.getName(LocaleUtil.getDefault());
			} else {
				org = OrganizationLocalServiceUtil.getOrganization(group.getOrganizationId());
				fullCoursName = OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false);
			}

			// Set start date at current week's day number
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_WEEK, dayNumber + 1);

			// Set hour and minutes
			String[] startHourTab = startHour.split(":");
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHourTab[0]));
			cal.set(Calendar.MINUTE, Integer.parseInt(startHourTab[1]));
			Date startDate = cal.getTime();
			String[] endHourTab = endHour.split(":");
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHourTab[0]));
			cal.set(Calendar.MINUTE, Integer.parseInt(endHourTab[1]));
			Date endDate = cal.getTime();

			List<Long> teacherIdList = new ArrayList<>();
			JSONArray jsonTeachers = new JSONArray(teacherIds);
			for (int i = 0 ; i < jsonTeachers.length() ; i++) {
				JSONObject jsonTeacher = jsonTeachers.getJSONObject(i);
				teacherIdList.add(jsonTeacher.getLong(JSONConstants.USER_ID));
			}

			// Manually add teachers to group if needed
			if (!isCommunity) {
				for (Long teacherId : teacherIdList) {
					UserOrgsLocalServiceUtil.affectManuallyUserToOrg(teacherId, org);
				}
			}

			if (isRecurrent) {
				CDTSessionLocalServiceUtil.createRecurrentSessions(groupId, subject, room, startDate, endDate, slot, teacherIdList);
			} else {
				DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				logger.info("Creating manual session for group " + groupId + ", subject=" + subject + ", startDate= " + sdf.format(startDate) + ", endDate=" + sdf.format(endDate) + ", slot=" + slot + ", teacherIds=" + teacherIds + ", isRecurrent is " + isRecurrent);
				CDTSessionLocalServiceUtil.createSession(groupId, subject, startDate, endDate, slot, teacherIdList, room, fullCoursName, true);
			}

		} catch (Exception e) {
			logger.error("Error creating manual sessions", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}

	@JSONWebService(value = "get-course-next-sessions", method = "GET")
	public JSONObject getCourseNextSessions(long sessionId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		if (!RoleUtilsLocalServiceUtil.isTeacher(user) ||
				!CDTSessionLocalServiceUtil.hasUserSession(user, sessionId)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		JSONArray jsonNextSessions = new JSONArray();
		List<CDTSession> nextSessions = CDTSessionLocalServiceUtil.getNextSessions(user, sessionId);
		for (CDTSession nextSession : nextSessions) {
			jsonNextSessions.put(nextSession.convertToJSON(user));
		}

		result.put(JSONConstants.NEXT_SESSIONS, jsonNextSessions);
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}


}