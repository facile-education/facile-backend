package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.base.CDTSessionServiceBaseImpl;

import com.weprode.nero.schedule.utils.FilterUtil;
import com.weprode.nero.schedule.utils.JSONProxy;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
	property = {
		"json.web.service.context.name=schedule",
		"json.web.service.context.path=CDTSession"
	},
	service = AopService.class
)
public class CDTSessionServiceImpl extends CDTSessionServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(CDTSessionServiceImpl.class);

	@JSONWebService(value = "get-horaires-sessions", method = "GET")
	public JSONObject getHorairesSessions(long userId, long groupId, String start, String end, String volee) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		User currentUser;
		try {
			currentUser = getGuestOrUser();
			if (currentUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		logger.info("User " + currentUser.getUserId() + " fetches sessions for userId " + userId + " or group " + groupId + " from " + start + " to " + end +" and volee=" + volee);
		Date startDate;
		Date endDate;
		try {
			startDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(start);
			endDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(end);
		} catch (Exception e) {
			logger.error("Error when parsing the start or end date while retrieving the user's sessions", e);
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}

		try {
			User targetUser = null;
			if (userId > 0) {
				targetUser = UserLocalServiceUtil.getUser(userId);
			}

			// 1. CDT sessions
			List<CDTSession> userSessions = new ArrayList<>();
			if (groupId != 0) {
				userSessions = CDTSessionLocalServiceUtil.getGroupSessions(groupId, startDate, endDate, true);
			} else if (RoleUtilsLocalServiceUtil.isStudent(targetUser)) {
				userSessions = CDTSessionLocalServiceUtil.getStudentSessions(userId, startDate, endDate);
			} else if (RoleUtilsLocalServiceUtil.isTeacher(targetUser)) {
				userSessions = CDTSessionLocalServiceUtil.getTeacherSessions(userId, startDate, endDate);
			}

			if (!volee.equals("")) {
				userSessions = FilterUtil.filterSessionsOnVolee(userSessions, volee);
			}

			// Color management
			long colorsTeacherId = 0;
			if (targetUser != null && RoleUtilsLocalServiceUtil.isTeacher(targetUser)) {
				colorsTeacherId = targetUser.getUserId();
			}
			result.put(JSONConstants.SESSIONS, JSONProxy.convertSessionsToJson(userSessions, currentUser, colorsTeacherId));

			// 2. Schoollife sessions
			JSONArray jsonSchoollifeSessions = JSONFactoryUtil.createJSONArray();
			if (targetUser != null && RoleUtilsLocalServiceUtil.isTeacher(targetUser)) {
				// TODO Schoollife
				// jsonSchoollifeSessions = SchoollifeSessionLocalServiceUtil.getTeacherSessions(targetUser.getUserId(), startDate, endDate);
			} else if (targetUser != null && RoleUtilsLocalServiceUtil.isStudent(targetUser)) {
				// TODO Schoollife
				// jsonSchoollifeSessions = SchoollifeSessionStudentLocalServiceUtil.getStudentSessions(targetUser.getUserId(), startDate, endDate);
			}
			// Transform teachers
			for (int i = 0 ; i < jsonSchoollifeSessions.length() ; i++) {
				JSONObject jsonSchoollifeSession = jsonSchoollifeSessions.getJSONObject(i);
				JSONArray jsonTeachers = JSONFactoryUtil.createJSONArray();
				if (jsonSchoollifeSession.getJSONObject(JSONConstants.TEACHER) != null) {
					jsonTeachers.put(jsonSchoollifeSession.getJSONObject(JSONConstants.TEACHER));
				}
				jsonSchoollifeSession.put(JSONConstants.TEACHERS, jsonTeachers);
			}
			result.put(JSONConstants.SCHOOLLIFE_SESSIONS, jsonSchoollifeSessions);

			result.put(JSONConstants.SUCCESS, true);

			return result;
		} catch (Exception e) {
			logger.error(e);
			return JSONProxy.getJSONReturnInErrorCase("Error when get sessions for agent : " + currentUser.getFullName() + " ( id: " + currentUser.getUserId() + ")");
		}
	}

	@JSONWebService(value = "get-teacher-groups", method = "GET")
	public JSONObject getTeacherGroups() {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		User currentUser;
		try {
			currentUser = getGuestOrUser();
			if (currentUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		JSONArray jsonGroups = FilterUtil.getGroupsForFilter(currentUser);
		result.put(JSONConstants.GROUPS, jsonGroups);
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}

	@JSONWebService(value = "get-session-details", method = "GET")
	public JSONObject getSessionDetails(long sessionId) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
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
			sessionDetails = CDTSessionLocalServiceUtil.fetchCDTSession(sessionId).convertToJSON(true, user);
		} catch (SystemException e) {
			logger.error("Could not fetch sessionId = " + sessionId, e);
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}

		// Student list
		List<User> studentList = CDTSessionLocalServiceUtil.getSessionStudents(sessionId);
		JSONArray jsonStudents = JSONProxy.convertUsersToJson(studentList);
		sessionDetails.put(JSONConstants.STUDENTS, jsonStudents);

		result.put(JSONConstants.SESSION_DETAILS, sessionDetails);
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}

	@JSONWebService(value = "create-session", method = "POST")
	public JSONObject createSession(long groupId, String subject, String room, String startDate, String endDate, String teacherIds, boolean isRecurrent) {

		logger.info("Creating manual session(s) for group " + groupId + ", subject=" + subject + " from " + startDate + " to " + endDate + ", teacherIds=" + teacherIds + ", isRecurrent is " + isRecurrent);
		JSONObject result = JSONFactoryUtil.createJSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		try {
			Group group = GroupLocalServiceUtil.getGroup(groupId);
			boolean isCommunity = group.isRegularSite();
			String fullCoursName;
			long schoolId = 0;
			Organization org = null;
			if (isCommunity) {
				fullCoursName = group.getName();
			} else {
				org = OrganizationLocalServiceUtil.getOrganization(group.getOrganizationId());
				fullCoursName = OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), false);
				schoolId = org.getParentOrganizationId();
			}

			Date start = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(startDate);
			Date end = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(endDate);

			List<Long> teacherIdList = new ArrayList<>();
			JSONArray jsonTeachers = JSONFactoryUtil.createJSONArray(teacherIds);
			if (jsonTeachers != null) {
				for (int i = 0 ; i < jsonTeachers.length() ; i++) {
					JSONObject jsonTeacher = jsonTeachers.getJSONObject(i);
					teacherIdList.add(jsonTeacher.getLong(JSONConstants.USER_ID));
				}
			}

			// Manually add teachers to group if needed
			if (!isCommunity) {
				for (Long teacherId : teacherIdList) {
					UserOrgsLocalServiceUtil.affectManuallyUserToOrg(teacherId, org);
				}
			}

			if (isRecurrent) {
				CDTSessionLocalServiceUtil.createRecurrentSessions(groupId, subject, room, start, end, teacherIdList);
			} else {
				logger.info("Creating manual session");
				CDTSessionLocalServiceUtil.createCDTSession(isCommunity ? 0 : schoolId, groupId, subject, start, end, teacherIdList, room, "Cours de " + subject, fullCoursName, "", false, true);
			}

		} catch (Exception e) {
			logger.error("Error creating manual sessions", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}
}