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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.course.service.HomeworkLocalServiceUtil;
import com.weprode.nero.group.model.GroupMembership;
import com.weprode.nero.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.nero.group.service.GroupMembershipLocalServiceUtil;
import com.weprode.nero.organization.service.ClassCoursMappingLocalServiceUtil;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.CDTSessionModel;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionStudentLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.nero.schedule.service.base.CDTSessionLocalServiceBaseImpl;
import com.weprode.nero.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import org.json.JSONArray;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.CDTSession",
	service = AopService.class
)
public class CDTSessionLocalServiceImpl extends CDTSessionLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(CDTSessionLocalServiceImpl.class);

	public CDTSession createSession(long groupId, String subject, Date startDate, Date endDate, int slot,
								 List<Long> teacherIdList, String room, String fullCoursName, boolean isManual) throws SystemException {
		long sessionId = counterLocalService.increment();
		CDTSession session = cdtSessionPersistence.create(sessionId);

		session.setGroupId(groupId);
		session.setSubject(subject);
		session.setStart(startDate);
		session.setEnd(endDate);
		session.setSlot(slot);
		session.setRoom(room);
		session.setFullCoursName(fullCoursName);
		session.setIsManual(isManual);
		cdtSessionPersistence.update(session);

		// Update the teacher list
		SessionTeacherLocalServiceUtil.updateTeacherListForSession(session.getSessionId(), teacherIdList);

		return session;
	}

	public boolean createRecurrentSessions(long groupId, String subject, String room, Date startDate, Date endDate, int slot, List<Long> teacherIdList) {
		logger.info("Start creating recurrent sessions");

		// Loop from startDate to the school year end's date
		try {
			Group coursGroup = GroupLocalServiceUtil.getGroup(groupId);
			String fullCoursName;
			Date schoolYearEndDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate();
			if (coursGroup.isOrganization()) {
				Organization coursOrg = OrganizationLocalServiceUtil.getOrganization(coursGroup.getOrganizationId());
				fullCoursName = OrgUtilsLocalServiceUtil.formatOrgName(coursOrg.getName(), false);
			} else {
				fullCoursName = coursGroup.getName();
			}

			while (startDate.before(schoolYearEndDate)) {
				logger.info("About to create recurrent session from " + startDate + " to " + endDate);
				createSession(groupId, subject, startDate, endDate, slot, teacherIdList, room, fullCoursName, true);

				// Add 7 days to start and end dates
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				cal.add(Calendar.DATE, 7);
				startDate = cal.getTime();
				cal.setTime(endDate);
				cal.add(Calendar.DATE, 7);
				endDate = cal.getTime();
			}

		} catch (Exception e) {
			logger.error("Error while creating recurrent sessions", e);
			return false;
		}

		return true;
	}

	public List<CDTSession> getTeacherSessions(long teacherId, Date minDate, Date maxDate) {
		return cdtSessionFinder.getTeacherSessions(teacherId, minDate, maxDate);
	}

	public List<CDTSession> getStudentSessions(long studentId, Date minDate, Date maxDate) {
		List<CDTSession> studentSessionList = new ArrayList<>();

		try {
			User student = UserLocalServiceUtil.getUser(studentId);

			// Get institutional groups
			List<Organization> orgList = UserOrgsLocalServiceUtil.getUserClassesAndCours(student, false);
			List<Long> groupIdList = new ArrayList<>();
			for (Organization org : orgList) {
				groupIdList.add(org.getGroupId());
			}

			// Add personal groups
			List<Group> studentGroupList = CommunityInfosLocalServiceUtil.getUserCommunities(student.getUserId(), true, true);
			if (studentGroupList != null) {
				for (Group studentGroup : studentGroupList) {
					groupIdList.add(studentGroup.getGroupId());
				}
			}

			List<CDTSession> sessionList = cdtSessionFinder.getGroupsSessions(groupIdList, minDate, maxDate);

			// Remove the sessions where the student is not member of the org in the specific date range
			List<GroupMembership> orgMemberships = GroupMembershipLocalServiceUtil.getStudentGroupMemberships(studentId);

			for (CDTSession session : sessionList) {
				boolean hasSpecificDates = false;
				for (GroupMembership orgMembership : orgMemberships) {
					if (session.getGroupId() == orgMembership.getGroupId()) {
						hasSpecificDates = true;
					}
				}

				// Keep the session if no specific dates or if any, if they match the specific dates
				if (!hasSpecificDates || GroupMembershipLocalServiceUtil.isStudentOrgMember(studentId, session.getGroupId(), session.getStart())) {
					studentSessionList.add(session);
				}
			}

			// Add subClass sessions
			List<CDTSession> subClassSessions = getStudentSpecificSessions(student.getUserId(), minDate, maxDate);
			if (subClassSessions != null) {
				studentSessionList.addAll(subClassSessions);
			}

		} catch (Exception e) {
			logger.error("Error while getting student sessions", e);
		}

		return studentSessionList;
	}

	public List<CDTSession> getNextStudentDaySessions(long studentId, Date targetDate, boolean goForward) {
		List<CDTSession> studentSessionList = new ArrayList<>();

		try {
			// Set min date to 0:00 at date
			Calendar cal = Calendar.getInstance();
			cal.setTime(targetDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			Date minDate = cal.getTime();

			// Set max date at 00:00 the next day
			cal.add(Calendar.DATE, 1);
			Date maxDate = cal.getTime();

			// Go forward in time until next day having sessions
			int idx = 0;
			while (idx < 30) {
				studentSessionList = getStudentSessions(studentId, minDate, maxDate);
				if (!studentSessionList.isEmpty()) {
					break;
				} else {
					// Add or remove 1 day depending on nav direction
					if (goForward) {
						minDate = cal.getTime();
					} else {
						cal.add(Calendar.DATE, -2);
						minDate = cal.getTime();
					}
					cal.add(Calendar.DATE, 1);
					maxDate = cal.getTime();
				}
				idx++;
			}

		} catch (Exception e) {
			logger.error("Error fetching next sessions for student " + studentId, e);
		}

		return studentSessionList;
	}

	public List<CDTSession> getNextTeacherDaySessions(long teacherId, Date targetDate, boolean goForward) {
		List<CDTSession> teacherSessionList = new ArrayList<>();

		try {
			// Set min date to 0:00 at date
			Calendar cal = Calendar.getInstance();
			cal.setTime(targetDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			Date minDate = cal.getTime();

			// Set max date at 00:00 the next day
			cal.add(Calendar.DATE, 1);
			Date maxDate = cal.getTime();

			// Go forward in time until next day having sessions
			int idx = 0;
			while (idx < 30) {
				teacherSessionList = getTeacherSessions(teacherId, minDate, maxDate);
				if (!teacherSessionList.isEmpty()) {
					break;
				} else {
					// Add or remove 1 day depending on nav direction
					if (goForward) {
						minDate = cal.getTime();
					} else {
						cal.add(Calendar.DATE, -2);
						minDate = cal.getTime();
					}
					cal.add(Calendar.DATE, 1);
					maxDate = cal.getTime();
				}
				idx++;
			}

		} catch (Exception e) {
			logger.error("Error fetching next sessions for teacherId " + teacherId, e);
		}

		return teacherSessionList;
	}

	/**
	 * Get all sessions for a given student in a given date range, that are not attached to a group (eg. subClass)
	 * Returns empty for GVA
	 */
	public List<CDTSession> getStudentSpecificSessions(long studentId, Date minDate, Date maxDate) {
		// Custom query
		List<CDTSession> sessionList = cdtSessionFinder.getStudentSpecificSessions(studentId, minDate, maxDate);

		// Sort unique
		List<CDTSession> uniqueCdtSessions = new ArrayList<>();
		for (CDTSession session : sessionList) {
			if (!uniqueCdtSessions.contains(session)) {
				uniqueCdtSessions.add(session);
			}
		}

		return uniqueCdtSessions;
	}

	public List<CDTSession> getGroupSessions(long groupId, Date minDate, Date maxDate, boolean includeSubClasses) {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		logger.info("Fetching sessions for groupId " + groupId + " from " + sdf.format(minDate) + " to " + sdf.format(maxDate) + " with subclasses " + includeSubClasses);

		List<CDTSession> sessions = new ArrayList<>();
		try {
			// If group is a community
			Group group = GroupLocalServiceUtil.getGroup(groupId);
			if (group.isRegularSite()) {
				logger.info("> Group is a community");
				List<CDTSession> groupSessions = cdtSessionPersistence.findBygroupId(groupId);
				for (CDTSession groupSession : groupSessions) {
					if (groupSession.getStart().after(minDate) && groupSession.getStart().before(maxDate)) {
						sessions.add(groupSession);
					}
				}
			} else {
				// Organization
				Organization org = OrganizationLocalServiceUtil.getOrganization(group.getClassPK());
				// If org is a cours, get its sessions
				if (OrgDetailsLocalServiceUtil.isCours(org.getOrganizationId())) {
					logger.info("> Group is a cours");
					List<CDTSession> groupSessions = cdtSessionPersistence.findBygroupId(groupId);
					for (CDTSession groupSession : groupSessions) {
						if (groupSession.getStart().after(minDate) && groupSession.getStart().before(maxDate)) {
							sessions.add(groupSession);
						}
					}
				} else if (OrgDetailsLocalServiceUtil.isClass(org.getOrganizationId())) {
					logger.info("> Group is a class");
					// If group is a class, get the sessions of the class's cours
					List<Long> coursOrgIds = ClassCoursMappingLocalServiceUtil.getClassCours(org.getOrganizationId());
					logger.info("> that has " + coursOrgIds.size() + " cours");
					List<Long> coursGroupIds = new ArrayList<>();
					for (Long coursOrgId : coursOrgIds) {
						Organization coursOrg = OrganizationLocalServiceUtil.getOrganization(coursOrgId);
						coursGroupIds.add(coursOrg.getGroupId());
					}
					sessions = cdtSessionFinder.getGroupsSessions(coursGroupIds, minDate, maxDate);
				}
			}

		} catch (Exception e) {
			logger.error("Error getting sessions for group " + groupId, e);
		}

		return sessions;
	}

	/**
	 * Get session student list
	 */
	public List<User> getSessionStudents(long sessionId) {
		List<User> sessionStudents = new ArrayList<>();

		try {
			CDTSession session = getCDTSession(sessionId);

			if (session.getGroupId() == 0) {
				// SubClass
				sessionStudents = SessionStudentLocalServiceUtil.getStudentsBySession(session.getSessionId());
			} else {

				Group sessionGroup = GroupLocalServiceUtil.getGroup(session.getGroupId());
				Role studentRole = RoleUtilsLocalServiceUtil.getStudentRole();

				// Differentiate personal group and organization
				if (sessionGroup.getClassPK() == sessionGroup.getGroupId()) {
					// This is a personal group
					List<Long> groupIds = new ArrayList<>();
					groupIds.add(sessionGroup.getGroupId());
					List<Long> roleIds = new ArrayList<>();
					roleIds.add(studentRole.getRoleId());
					sessionStudents = UserSearchLocalServiceUtil.searchUsers("", null, groupIds, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

				} else {
					// This is an organization
					List<Long> orgIds = new ArrayList<>();
					orgIds.add(sessionGroup.getClassPK());
					List<Long> roleIds = new ArrayList<>();
					roleIds.add(studentRole.getRoleId());
					sessionStudents = UserSearchLocalServiceUtil.searchUsers("", orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
				}
			}
		} catch (Exception e) {
			logger.error("Error when getting session students", e);
		}

		return sessionStudents;
	}


	/**
	 * Get the session for a school id that begin between 2 dates
	 * Used by synchronization process
	 */
	public List<CDTSession> getSchoolSessions(Long schoolId, Date startDate, Date endDate) throws SystemException {
		return cdtSessionFinder.getSchoolSessions(schoolId, startDate, endDate);
	}

	public List<CDTSession> getGroupsSessionActivity(long userId, List<Long> groupIds, Date minDate, Date maxDate) {
		return cdtSessionFinder.getSessionActivity(userId, groupIds, minDate, maxDate);
	}

	// Used for WS check for session ownership
	public boolean hasUserSession(User user, long sessionId) {

		if (RoleUtilsLocalServiceUtil.isTeacher(user) && SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), sessionId)) {
			return true;
		} else if (RoleUtilsLocalServiceUtil.isStudent(user) && SessionStudentLocalServiceUtil.hasStudentSession(user.getUserId(), sessionId)) {
			return true;
		} else if (RoleUtilsLocalServiceUtil.isParent(user)) {
			for (User child : UserRelationshipLocalServiceUtil.getChildren(user.getUserId())) {
				if (SessionStudentLocalServiceUtil.hasStudentSession(child.getUserId(), sessionId)) {
					return true;
				}
			}
			return true;
		}
		return false;
	}

	public void deleteSessionAndDependencies(long sessionId) throws PortalException, SystemException {

		SessionTeacherLocalServiceUtil.removeBySessionId(sessionId);
		SessionStudentLocalServiceUtil.removeBySessionId(sessionId);
		HomeworkLocalServiceUtil.deleteSessionHomeworks(sessionId);
		deleteCDTSession(sessionId);
	}

	public JSONArray convertSessions(List<CDTSession> sessions, User user) {
		JSONArray jsonSessions = new JSONArray();
		for (CDTSession session : sessions) {
			jsonSessions.put(session.convertToJSON(user));
		}
		return jsonSessions;
	}

	// Used for content association
	public boolean isSession(long itemId) {
		CDTSession session = cdtSessionPersistence.fetchByPrimaryKey(itemId);
		return session != null;
	}

	// Fetch next sessions for same groupId in the next 50 days
	public List<CDTSession> getNextSessions(User user, long sessionId) {

		List<CDTSession> nextSessions = new ArrayList<>();

		try {
			CDTSession sourceSession = CDTSessionLocalServiceUtil.getCDTSession(sessionId);

			Calendar cal = Calendar.getInstance();
			cal.setTime(sourceSession.getEnd());
			cal.add(Calendar.MINUTE, 1);
			Date startDate = cal.getTime();

			cal.add(Calendar.DATE, 50);
			Date endDate = cal.getTime();

			List<CDTSession> futureSessions = CDTSessionLocalServiceUtil.getGroupSessions(sourceSession.getGroupId(), startDate, endDate, false);
			for (CDTSession nextSession : futureSessions) {
				if (nextSession.getSessionId() != sessionId &&
						RoleUtilsLocalServiceUtil.isTeacher(user) &&
						SessionTeacherLocalServiceUtil.hasTeacherSession(user.getUserId(), nextSession.getSessionId())) {
					nextSessions.add(nextSession);
				}
			}

			nextSessions.sort(Comparator.comparing(CDTSessionModel::getStart));

			if (nextSessions.size() > NB_NEXT_SESSIONS) {
				nextSessions = nextSessions.subList(0, NB_NEXT_SESSIONS);
			}
		} catch (Exception e) {
			logger.error("Error fetching next sessions from session " + sessionId, e);
		}
		return nextSessions;
	}

	private static final int NB_NEXT_SESSIONS = 15;

}