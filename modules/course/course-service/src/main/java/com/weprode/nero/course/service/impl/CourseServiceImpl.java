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

package com.weprode.nero.course.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.course.model.Homework;
import com.weprode.nero.course.model.SessionContent;
import com.weprode.nero.course.service.HomeworkLocalServiceUtil;
import com.weprode.nero.course.service.SessionContentLocalServiceUtil;
import com.weprode.nero.course.service.base.CourseServiceBaseImpl;
import com.weprode.nero.organization.constants.OrgConstants;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = {
		"json.web.service.context.name=course",
		"json.web.service.context.path=Course"
	},
	service = AopService.class
)
public class CourseServiceImpl extends CourseServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(CourseServiceImpl.class);

	@JSONWebService(value = "get-user-courses", method = "GET")
	public JSONObject getUserCourses() {
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

		JSONArray jsonCourses = new JSONArray();
		List<Organization> userCourses = UserOrgsLocalServiceUtil.getUserCours(currentUser, false, OrgConstants.ALL_SCHOOLS_ID);
		for (Organization userCours : userCourses) {
			JSONObject jsonCourse = new JSONObject();
			jsonCourse.put(JSONConstants.COURSE_ID, userCours.getGroupId());
			jsonCourse.put(JSONConstants.GROUP_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userCours.getName(), false));
			jsonCourses.put(jsonCourse);
		}
		result.put(JSONConstants.COURSES, jsonCourses);
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}


	@JSONWebService(value = "get-course-content", method = "GET")
	public JSONObject getCourseContent(long courseId, String minDateStr, String maxDateStr) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				throw new AuthException();
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		result.put(JSONConstants.SUCCESS, true);
		try {
			if (!UserUtilsLocalServiceUtil.getUserGroupIds(user.getGroupId()).contains(courseId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			logger.info("User " + user.getUserId() + " fetches full course content for course " + courseId + ", from " + minDateStr + " to " + maxDateStr);

			DateFormat sdf = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT);
			Date minDate = sdf.parse(minDateStr);
			Date maxDate = sdf.parse(maxDateStr);

			// Loop over sessions
			List<CDTSession> courseSessions = CDTSessionLocalServiceUtil.getGroupSessions(courseId, minDate, maxDate, true);
			for (CDTSession courseSession : courseSessions) {
				JSONObject jsonSession = courseSession.convertToJSON(user);

				// Session content
				SessionContent sessionContent = SessionContentLocalServiceUtil.getSessionContent(courseSession.getSessionId());
				jsonSession.put(JSONConstants.SESSION_CONTENT, sessionContent.convertToJSON(user, false));

				// To perform homeworks
				List<Homework> toDoHomeworks = HomeworkLocalServiceUtil.getSessionToDoHomeworks(user, courseSession.getSessionId());
				JSONArray jsonToDoHomeworks = new JSONArray();
				for (Homework toDoHomework : toDoHomeworks) {
					jsonToDoHomeworks.put(toDoHomework.convertToJSON(user, false));
				}
				jsonSession.put(JSONConstants.TO_DO_HOMEWORKS, jsonToDoHomeworks);

				// Given homeworks
				List<Homework> givenHomeworks = HomeworkLocalServiceUtil.getSessionGivenHomeworks(user, courseSession.getSessionId());
				JSONArray jsonGivenHomeworks = new JSONArray();
				for (Homework givenHomework : givenHomeworks) {
					jsonGivenHomeworks.put(givenHomework.convertToJSON(user, false));
				}
				jsonSession.put(JSONConstants.GIVEN_HOMEWORKS, jsonGivenHomeworks);
			}

		} catch (Exception e) {
			logger.error("Could not get course content for course " + courseId + " for "+user.getFullName()+" (id="+user.getUserId()+")", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	// Called when clicked on a session in the scheduler
	@JSONWebService(value = "get-session-details", method = "GET")
	public JSONObject getSessionDetails(long sessionId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				throw new AuthException();
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		result.put(JSONConstants.SUCCESS, true);
		try {
			if (!CDTSessionLocalServiceUtil.hasUserSession(user, sessionId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			DateFormat sdf = new SimpleDateFormat(JSONConstants.FULL_FRENCH_FORMAT);
			CDTSession session = CDTSessionLocalServiceUtil.getCDTSession(sessionId);
			logger.info("User " + user.getUserId() + " consults details of session " + sessionId + ", from " + sdf.format(session.getStart()) + " to " + sdf.format(session.getEnd()));

			JSONObject jsonSession = new JSONObject();

			// 1. Session content
			try {
				SessionContent sessionContent = SessionContentLocalServiceUtil.getSessionContent(sessionId);
				if (RoleUtilsLocalServiceUtil.isTeacher(user) || (!sessionContent.getIsDraft() && sessionContent.getPublicationDate().before(new Date()))) {
					jsonSession.put(JSONConstants.SESSION_CONTENT, sessionContent.convertToJSON(user, true));
				}
			} catch (Exception e) {
				// No session content
			}

			// 2. Given homeworks
			List<Homework> givenHomeworks = HomeworkLocalServiceUtil.getSessionGivenHomeworks(user, sessionId);
			JSONArray jsonGivenHomeworks = new JSONArray();
			for (Homework givenHomework : givenHomeworks) {
				jsonGivenHomeworks.put(givenHomework.convertToJSON(user, true));
			}
			jsonSession.put(JSONConstants.GIVEN_HOMEWORKS, jsonGivenHomeworks);

			// 3. To do homeworks
			List<Homework> toDoHomeworks = HomeworkLocalServiceUtil.getSessionToDoHomeworks(user, sessionId);
			JSONArray jsonToDoHomeworks = new JSONArray();
			for (Homework toDoHomework : toDoHomeworks) {
				jsonToDoHomeworks.put(toDoHomework.convertToJSON(user, true));
			}
			jsonSession.put(JSONConstants.TO_DO_HOMEWORKS, jsonToDoHomeworks);

			// 4. Private notes
			if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
				jsonSession.put(JSONConstants.PRIVATE_NOTES, SessionTeacherLocalServiceUtil.getPrivateNotes(user.getUserId(), sessionId));
			}

			result.put(JSONConstants.SESSION_DETAILS, jsonSession);

		} catch (Exception e) {
			logger.error("Could not get details for session " + sessionId + " for "+user.getFullName()+" (id="+user.getUserId()+")", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(value = "get-course-students", method = "GET")
	public JSONObject getCourseStudents(long courseId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				throw new AuthException();
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		result.put(JSONConstants.SUCCESS, true);
		try {
			if (!RoleUtilsLocalServiceUtil.isTeacher(user) || !UserUtilsLocalServiceUtil.getUserGroupIds(user.getUserId()).contains(courseId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			logger.info("Teacher " + user.getFullName() + " fetches students for course " + courseId + " (giving homework)");

			List<Long> organizationIds = new ArrayList<>();
			Group courseGroup = GroupLocalServiceUtil.getGroup(courseId);
			organizationIds.add(courseGroup.getClassPK());
			Role studentRole = RoleUtilsLocalServiceUtil.getStudentRole();
			List<Long> roleIds = new ArrayList<>();
			roleIds.add(studentRole.getRoleId());
			List<User> studentList = UserSearchLocalServiceUtil.searchUsers("", organizationIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			JSONArray jsonStudents = UserUtilsLocalServiceUtil.convertUsersToJson(studentList);

			result.put(JSONConstants.STUDENTS, jsonStudents);

		} catch (Exception e) {
			logger.error("Could not get students for course " + courseId + " for "+user.getFullName()+" (id="+user.getUserId()+")", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(value = "save-private-notes", method = "POST")
	public JSONObject savePrivateNotes(long sessionId, String notes) {
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
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			if (RoleUtilsLocalServiceUtil.isTeacher(user) && !SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), sessionId)) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			SessionTeacherLocalServiceUtil.saveNotes(user.getUserId(), sessionId, notes);

			logger.info("Teacher "+user.getFullName()+" (id="+user.getUserId()+") has added private notes to session " + sessionId);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Could not add private notes to session " + sessionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}