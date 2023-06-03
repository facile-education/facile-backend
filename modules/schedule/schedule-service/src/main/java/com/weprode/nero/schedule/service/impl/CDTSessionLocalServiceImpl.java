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
import com.weprode.nero.group.model.GroupMembership;
import com.weprode.nero.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.nero.group.service.GroupMembershipLocalServiceUtil;
import com.weprode.nero.organization.service.ClassCoursMappingLocalServiceUtil;
import com.weprode.nero.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.progression.service.ItemContentLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionItemLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.GroupColorLocalServiceUtil;
import com.weprode.nero.schedule.service.HomeworkLocalServiceUtil;
import com.weprode.nero.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionStudentLocalServiceUtil;
import com.weprode.nero.schedule.service.SessionTeacherLocalServiceUtil;
import com.weprode.nero.schedule.service.base.CDTSessionLocalServiceBaseImpl;
import com.weprode.nero.schedule.utils.HomeworkUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.CDTSession",
	service = AopService.class
)
public class CDTSessionLocalServiceImpl extends CDTSessionLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(CDTSessionLocalServiceImpl.class);

	/**
	 * Create a CDTSession from all its properties
	 */
	public CDTSession createCDTSession(long schoolId, long groupId, String subject, Date startDate, Date endDate,
									   List<Long> teacherIdList, String room, String title, String fullCoursName, String description, boolean published, boolean isManual) throws SystemException {
		long cdtSessionId = counterLocalService.increment();
		CDTSession cdtSession = cdtSessionPersistence.create(cdtSessionId);

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int weekId = cal.get(Calendar.WEEK_OF_YEAR);

		cdtSession.setSchoolId(schoolId);
		cdtSession.setGroupId(groupId);
		cdtSession.setSubject(subject);
		cdtSession.setSessionStart(startDate);
		cdtSession.setSessionEnd(endDate);
		cdtSession.setRoom(room);
		cdtSession.setTitle(title);
		cdtSession.setFullCoursName(fullCoursName);
		cdtSession.setDescription(description);
		cdtSession.setPublished(published);
		cdtSession.setIsManual(isManual);
		cdtSession.setWeekId(weekId);
		cdtSessionPersistence.update(cdtSession);

		// Update the teacher list
		SessionTeacherLocalServiceUtil.updateTeacherListForSession(cdtSession.getSessionId(), teacherIdList);

		return cdtSession;
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

			List<CDTSession> cdtSessionList = cdtSessionFinder.getGroupsSessions(groupIdList, minDate, maxDate);

			// Remove the sessions where the student is not member of the org in the specific date range
			List<GroupMembership> orgMemberships = GroupMembershipLocalServiceUtil.getStudentGroupMemberships(studentId);

			for (CDTSession cdtSession : cdtSessionList) {
				boolean hasSpecificDates = false;
				for (GroupMembership orgMembership : orgMemberships) {
					if (cdtSession.getGroupId() == orgMembership.getGroupId()) {
						hasSpecificDates = true;
					}
				}

				// Keep the session if no specific dates or if any, if they match the specific dates
				if (!hasSpecificDates || GroupMembershipLocalServiceUtil.isStudentOrgMember(studentId, cdtSession.getGroupId(), cdtSession.getSessionStart())) {
					studentSessionList.add(cdtSession);
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
	 */
	public List<CDTSession> getStudentSpecificSessions(long studentId, Date minDate, Date maxDate) {
		// Custom query
		List<CDTSession> sessionList = cdtSessionFinder.getStudentSpecificSessions(studentId, minDate, maxDate);

		// Sort unique
		List<CDTSession> uniqueCdtSessions = new ArrayList<>();
		for (CDTSession cdtSession : sessionList) {
			if (!uniqueCdtSessions.contains(cdtSession)) {
				uniqueCdtSessions.add(cdtSession);
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
					if (groupSession.getSessionStart().after(minDate) && groupSession.getSessionStart().before(maxDate)) {
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
						if (groupSession.getSessionStart().after(minDate) && groupSession.getSessionStart().before(maxDate)) {
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
			CDTSession session = CDTSessionLocalServiceUtil.getCDTSession(sessionId);

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

	public boolean assignSessionContent (long sessionId, long progressionItemId) {
		try {
			// Push item text + links + videos + h5p into session's description
			// Force publication
			CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(sessionId);
			String itemContent = ProgressionItemLocalServiceUtil.convertContentAsHtml(progressionItemId);
			cdtSession.setDescription(itemContent);
			cdtSession.setPublished(true);
			CDTSessionLocalServiceUtil.updateCDTSession(cdtSession);

			// Delete existing attach files
			// TODO Attachments
			// AttachFileLocalServiceUtil.removeAllSessionAttachFiles(sessionId);

			// Copy attached files
			List<Long> fileEntryIds = ItemContentLocalServiceUtil.getFileIds(progressionItemId);
			for (Long fileEntryId : fileEntryIds) {
				// TODO Attachments
				// AttachFileLocalServiceUtil.addSessionAttachFile(sessionId, fileEntryId);
			}

			// Delete existing audio files
			// TODO Attachments
			// AttachFileLocalServiceUtil.deleteSessionAudioInstructions(sessionId);

			// Copy audio files
			List<Long> audioFileEntryIds = ItemContentLocalServiceUtil.getAudioFileIds(progressionItemId);
			for (Long audioFileEntryId : audioFileEntryIds) {
				// TODO Attachments
				//AttachFileLocalServiceUtil.addSessionAttachFile(sessionId, audioFileEntryId);
				// //AttachFileLocalServiceUtil.addSessionAudioInstructions(sessionId, audioFileName);
			}

			logger.info("Assigned content of item " + progressionItemId + " to session " + cdtSession.getSessionId());
			return true;
		} catch (Exception e) {
			logger.error("Error assigning item " + progressionItemId + " to session " + sessionId, e);
		}

		return false;
	}

	public boolean resetSessionContent (long sessionId) {
		try {
			CDTSession cdtSession = CDTSessionLocalServiceUtil.getCDTSession(sessionId);
			cdtSession.setDescription("");
			CDTSessionLocalServiceUtil.updateCDTSession(cdtSession);

			// Delete attach files in CDT
			// TODO Attachments
			// AttachFileLocalServiceUtil.removeAllSessionAttachFiles(sessionId);

			// Delete audio instructions in CDT
			// TODO Attachments
			// AttachFileLocalServiceUtil.deleteSessionAudioInstructions(sessionId);

			logger.info("Reset content of session " + cdtSession.getSessionId());
			return true;
		} catch (Exception e) {
			logger.error("Error resetting content of session " + sessionId, e);
		}

		return false;
	}

	public JSONObject copySessionContent(User currentUser, long sourceSessionId, long targetSessionId, JSONArray homeworksAsJSON) {
		try {
			CDTSession sourceSession = CDTSessionLocalServiceUtil.getCDTSession(sourceSessionId);
			CDTSession targetSession = CDTSessionLocalServiceUtil.getCDTSession(targetSessionId);

			// Copy session content
			targetSession.setPublished(sourceSession.getPublished());
			targetSession.setTitle(sourceSession.getTitle());
			targetSession.setDescription(sourceSession.getDescription());

			CDTSessionLocalServiceUtil.updateCDTSession(targetSession);

			// Copy session attach files and audio instructions
			// TODO Attachments
			// AttachFileLocalServiceUtil.copySession(sourceSession.getSessionId(), targetSession.getSessionId());

			// copy homework
			HomeworkUtil.copyHomeworksFromSessionToAnother(homeworksAsJSON, targetSession);

			return CDTSessionLocalServiceUtil.fetchCDTSession(targetSession.getSessionId()).convertToJSON(true, currentUser);
		} catch (Exception e) {
			logger.error("Error in copying session content", e);
		}

		return null;
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

	public boolean createRecurrentSessions(long groupId, String subject, String room, Date startDate, Date endDate, List<Long> teacherIdList) {
		logger.info("Start creating recurrent sessions");

		// Loop from startDate to the schoolyear end's date
		try {
			Group coursGroup = GroupLocalServiceUtil.getGroup(groupId);
			String fullCoursName = "";
			long schoolId = 0;
			Date schoolYearEndDate = null;
			if (coursGroup.isOrganization()) {
				Organization coursOrg = OrganizationLocalServiceUtil.getOrganization(coursGroup.getOrganizationId());
				fullCoursName = OrgUtilsLocalServiceUtil.formatOrgName(coursOrg.getName(), false);
				schoolId = coursOrg.getParentOrganizationId();
				schoolYearEndDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate();
			} else {
				fullCoursName = coursGroup.getName();
				schoolYearEndDate = getSchoolYearEndDate();
			}

			while (startDate.before(schoolYearEndDate)) {
				logger.info("About to create recurrent session from " + startDate + " to " + endDate);
				CDTSessionLocalServiceUtil.createCDTSession(schoolId, groupId, subject, startDate, endDate, teacherIdList, room, "Cours de " + subject, fullCoursName, "", false, true);

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

	private Date getSchoolYearEndDate() {
		// After august the 1st, school year end date is the 8th of july of next year
		// Before august the 1st, school year end date is the 8th of july of current year
		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date());
		if (cal.get(Calendar.MONTH) >= Calendar.AUGUST) {
			cal.add(Calendar.YEAR, 1);
		}
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 8);

		return cal.getTime();
	}

	public String getSessionColor(long sessionId, long userId) {

		try {
			CDTSession session = CDTSessionLocalServiceUtil.getCDTSession(sessionId);
			return GroupColorLocalServiceUtil.getColor(session.getGroupId());
		} catch (Exception e) {
			logger.error("Error getting color for session " + sessionId + " and user " + userId, e);
		}
		// Default
		return "#EA4335";
	}

	/**
	 * Delete a CDTSession with all its dependencies (homeworks, attachments..)
	 */
	public boolean deleteSessionAndDependencies(long sessionId) throws PortalException, SystemException {
		// Remove SessionTeacher
		SessionTeacherLocalServiceUtil.removeBySessionId(sessionId);

		// Remove SessionStudents
		SessionStudentLocalServiceUtil.removeBySessionId(sessionId);

		// Remove homeworks from this session
		HomeworkLocalServiceUtil.deleteSessionHomeworks(sessionId);

		// Remove attached files
		// TODO Attachments
		// AttachFileLocalServiceUtil.removeAllSessionAttachFiles(sessionId);

		// Delete session
		CDTSessionLocalServiceUtil.deleteCDTSession(sessionId);

		return true;
	}

	public JSONArray convertSessionsToJson(List<CDTSession> sessions, User user, long colorsTeacherId) {
		JSONArray sessionsArray = new JSONArray();

		for (CDTSession session : sessions) {
			try {
				sessionsArray.put(session.convertToJSON(colorsTeacherId, user));
			} catch (Exception e) {
				logger.error("Error when fetching CDT session for user "+user.getFullName(), e);
			}
		}

		return sessionsArray;
	}
}