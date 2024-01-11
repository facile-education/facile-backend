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

package com.weprode.facile.course.service.impl;

import com.liferay.petra.string.StringPool;
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
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.course.exception.NoSuchSessionContentException;
import com.weprode.facile.course.model.Homework;
import com.weprode.facile.course.model.SessionContent;
import com.weprode.facile.course.service.HomeworkLocalServiceUtil;
import com.weprode.facile.course.service.SessionContentLocalServiceUtil;
import com.weprode.facile.course.service.base.CourseServiceBaseImpl;
import com.weprode.facile.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.facile.organization.constants.OrgConstants;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.model.CDTSession;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.facile.schedule.service.CourseDetailsLocalServiceUtil;
import com.weprode.facile.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.facile.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.facile.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.facile.user.service.UserSearchLocalServiceUtil;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	public JSONObject getUserCourses(long userId) {
		JSONObject result = new JSONObject();

		User user;
		User userToFetchCourses;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (userId != user.getUserId() && !UserRelationshipLocalServiceUtil.isChild(user.getUserId(), userId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets courses for user " + userId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			userToFetchCourses = UserLocalServiceUtil.getUser(userId);
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		JSONArray jsonCourses = new JSONArray();
		List<Organization> userCourses = UserOrgsLocalServiceUtil.getUserCours(userToFetchCourses, false, OrgConstants.ALL_SCHOOLS_ID);
		for (Organization userCours : userCourses) {
			JSONObject jsonCourse = new JSONObject();
			jsonCourse.put(JSONConstants.COURSE_ID, userCours.getGroupId());
			jsonCourse.put(JSONConstants.SUBJECT, CourseDetailsLocalServiceUtil.getCourseSubject(userCours.getGroupId()));

			try {
				JSONArray teachers = new JSONArray();

				List<Long> orgIds = new ArrayList<>();
				orgIds.add(userCours.getOrganizationId());

				List<Long> roleIds = new ArrayList<>();
				roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());

				for (User teacher : UserSearchLocalServiceUtil.searchUsers(StringPool.BLANK, orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
					JSONObject teacherJson = new JSONObject();
					teacherJson.put(JSONConstants.USER_ID, teacher.getUserId());
					teacherJson.put(JSONConstants.FIRST_NAME, teacher.getFirstName());
					teacherJson.put(JSONConstants.LAST_NAME, teacher.getLastName());
					teachers.put(teacherJson);
				}

				jsonCourse.put(JSONConstants.TEACHERS, teachers);

			} catch (Exception e) {
				logger.error(e);
			}

			jsonCourse.put(JSONConstants.COLOR, CourseDetailsLocalServiceUtil.getCourseColor(userCours.getGroupId()));
			jsonCourse.put(JSONConstants.GROUP_NAME, OrgUtilsLocalServiceUtil.formatOrgName(userCours.getName(), false));
			jsonCourses.put(jsonCourse);
		}
		// Add student pedagogical communities
		List<Group> userGroups = CommunityInfosLocalServiceUtil.getUserCommunities(userToFetchCourses.getUserId(), true, true);
		if (userGroups != null) {
			for (Group userGroup : userGroups) {
				JSONObject jsonCourse = new JSONObject();
				jsonCourse.put(JSONConstants.COURSE_ID, userGroup.getGroupId());
				jsonCourse.put(JSONConstants.COLOR, CourseDetailsLocalServiceUtil.getCourseColor(userGroup.getGroupId()));
				jsonCourse.put(JSONConstants.GROUP_NAME, userGroup.getName(Locale.getDefault()));
				jsonCourse.put(JSONConstants.SUBJECT, userGroup.getName(Locale.getDefault()));
				try {
					JSONArray teachers = new JSONArray();

					List<Long> groupIds = new ArrayList<>();
					groupIds.add(userGroup.getGroupId());

					List<Long> roleIds = new ArrayList<>();
					roleIds.add(RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId());

					for (User teacher : UserSearchLocalServiceUtil.searchUsers(StringPool.BLANK, null, groupIds, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
						JSONObject teacherJson = new JSONObject();
						teacherJson.put(JSONConstants.FIRST_NAME, teacher.getFirstName());
						teacherJson.put(JSONConstants.LAST_NAME, teacher.getLastName());
						teachers.put(teacherJson);
					}

					jsonCourse.put(JSONConstants.TEACHERS, teachers);

				} catch (Exception e) {
					logger.error(e);
				}
				jsonCourses.put(jsonCourse);
			}
		}
		result.put(JSONConstants.COURSES, jsonCourses);
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}

	@JSONWebService(value = "get-course", method = "GET")
	public JSONObject getCourse(long sessionId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				throw new AuthException();
			}
			if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets courses for session " + sessionId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		try {
			CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(sessionId);
			result.put(JSONConstants.SESSION, cdtSession.convertToJSON(user));
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error(e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(value = "get-course-content", method = "GET")
	public JSONObject getCourseContent(long courseId, boolean hideDrafts) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				throw new AuthException();
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		result.put(JSONConstants.SUCCESS, true);
		try {
			if (!UserUtilsLocalServiceUtil.getUserGroupIds(user.getUserId()).contains(courseId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets course content for course " + courseId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			// Loop over sessions
			List<CDTSession> courseSessions = CDTSessionLocalServiceUtil.getGroupSessions(courseId, ScheduleConfigurationLocalServiceUtil.getSchoolYearStartDate(), ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate(), true);
			JSONArray jsonSessions = new JSONArray();
			for (CDTSession courseSession : courseSessions) {
				JSONObject jsonSession = courseSession.convertToJSON(user);

				// Session content
				SessionContent sessionContent;
				try {
					sessionContent = SessionContentLocalServiceUtil.getSessionContent(courseSession.getSessionId());
					if ((!RoleUtilsLocalServiceUtil.isTeacher(user) || hideDrafts) && (sessionContent.getIsDraft() || sessionContent.getPublicationDate().after(new Date()))) {
						sessionContent = null;
					}
				} catch (NoSuchSessionContentException e) {
					sessionContent = null;
				}

				// To perform homeworks
				List<Homework> toDoHomeworks = HomeworkLocalServiceUtil.getSessionToDoHomeworks(user, courseSession.getSessionId(), hideDrafts);
				// Given homeworks
				List<Homework> givenHomeworks = HomeworkLocalServiceUtil.getSessionGivenHomeworks(user, courseSession.getSessionId(), hideDrafts);
				// Homework to do in session
				List<Homework> sessionHomeworks = new ArrayList<>();
				for (Homework sessionHomework : givenHomeworks) {
					if (sessionHomework.getTargetSessionId() == courseSession.getSessionId()) {
						sessionHomeworks.add(sessionHomework);
					}
				}

				if (sessionContent != null  || !toDoHomeworks.isEmpty() || !sessionHomeworks.isEmpty()) {
					if (sessionContent != null) {
						// Tmp to add VIEW permissions on session content's files
						//SessionContentLocalServiceUtil.getSessionFolder(courseSession.getSessionId(), false);
						jsonSession.put(JSONConstants.SESSION_CONTENT, sessionContent.convertToJSON(user, true));
					}

					// Do not return given homeworks for list display
					jsonSession.put(JSONConstants.GIVEN_HOMEWORKS, new JSONArray());

					JSONArray jsonSessionHomeworks = new JSONArray();
					for (Homework sessionHomework : sessionHomeworks) {
						// TMP for permission issue on old homework folders
						//HomeworkLocalServiceUtil.getHomeworkFolder(sessionHomework.getHomeworkId(), false);
						jsonSessionHomeworks.put(sessionHomework.convertToJSON(user, true));
					}
					jsonSession.put(JSONConstants.SESSION_HOMEWORKS, jsonSessionHomeworks);


					JSONArray jsonToDoHomeworks = new JSONArray();
					for (Homework toDoHomework : toDoHomeworks) {
						if (toDoHomework.getSourceSessionId() != courseSession.getSessionId()) {
							// TMP for permission issue on old homework folders
							//HomeworkLocalServiceUtil.getHomeworkFolder(toDoHomework.getHomeworkId(), false);
							jsonToDoHomeworks.put(toDoHomework.convertToJSON(user, true));
						}
					}
					jsonSession.put(JSONConstants.TO_DO_HOMEWORKS, jsonToDoHomeworks);

					jsonSessions.put(jsonSession);
				}
			}

			result.put(JSONConstants.SESSIONS, jsonSessions);

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
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				throw new AuthException();
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		result.put(JSONConstants.SUCCESS, true);
		try {
			if (!CDTSessionLocalServiceUtil.hasUserSession(user, sessionId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets details of session " + sessionId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

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
			List<Homework> givenHomeworks = HomeworkLocalServiceUtil.getSessionGivenHomeworks(user, sessionId, false);
			JSONArray jsonGivenHomeworks = new JSONArray();
			for (Homework givenHomework : givenHomeworks) {
				if (givenHomework.getTargetSessionId() != sessionId) {
					jsonGivenHomeworks.put(givenHomework.convertToJSON(user, true));
				}
			}
			jsonSession.put(JSONConstants.GIVEN_HOMEWORKS, jsonGivenHomeworks);

			// 3. Session homeworks
			JSONArray jsonSessionHomeworks = new JSONArray();
			for (Homework sessionHomework : givenHomeworks) {
				if (sessionHomework.getTargetSessionId() == sessionId) {
					jsonSessionHomeworks.put(sessionHomework.convertToJSON(user, true));
				}
			}
			jsonSession.put(JSONConstants.SESSION_HOMEWORKS, jsonSessionHomeworks);

			// 4. To do homeworks
			List<Homework> toDoHomeworks = HomeworkLocalServiceUtil.getSessionToDoHomeworks(user, sessionId, false);
			JSONArray jsonToDoHomeworks = new JSONArray();
			for (Homework toDoHomework : toDoHomeworks) {
				if (toDoHomework.getSourceSessionId() != sessionId) {
					jsonToDoHomeworks.put(toDoHomework.convertToJSON(user, true));
				}
			}
			jsonSession.put(JSONConstants.TO_DO_HOMEWORKS, jsonToDoHomeworks);

			// 5. Private notes
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
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				throw new AuthException();
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		result.put(JSONConstants.SUCCESS, true);
		try {
			if (!RoleUtilsLocalServiceUtil.isTeacher(user) || !UserUtilsLocalServiceUtil.getUserGroupIds(user.getUserId()).contains(courseId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets students of course " + courseId);
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
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " saves private notes for session " + sessionId);
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			if (RoleUtilsLocalServiceUtil.isTeacher(user) && !SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), sessionId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " saves private notes for session " + sessionId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			SessionTeacherLocalServiceUtil.saveNotes(user.getUserId(), sessionId, notes);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Could not add private notes to session " + sessionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}