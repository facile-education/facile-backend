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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.course.CourseConstants;
import com.weprode.facile.course.model.Homework;
import com.weprode.facile.course.model.StudentHomework;
import com.weprode.facile.course.service.StudentHomeworkLocalServiceUtil;
import com.weprode.facile.course.service.base.HomeworkLocalServiceBaseImpl;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.group.service.GroupUtilsLocalServiceUtil;
import com.weprode.facile.mobile.constants.MobileConstants;
import com.weprode.facile.mobile.service.MobileDeviceLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.model.CDTSession;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.facile.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.facile.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.facile.user.service.UserSearchLocalServiceUtil;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.facile.course.model.Homework",
	service = AopService.class
)
public class HomeworkLocalServiceImpl extends HomeworkLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(HomeworkLocalServiceImpl.class);

	public Homework createHomework(User teacher, String title, long sourceSessionId, long targetSessionId, long courseId, Date targetDate, int homeworkType, int estimatedTime, List<Long> studentIds, Date publicationDate, boolean isDraft) {
		try {
			logger.info("Creating homework by teacher " + teacher.getFullName() + ", targetSessionId=" + targetSessionId + ", type=" + homeworkType + " and " + studentIds.size() + " students");

			Homework homework = homeworkPersistence.create(CounterLocalServiceUtil.increment());

			homework.setSourceSessionId(sourceSessionId);
			homework.setTargetSessionId(targetSessionId);
			homework.setTitle(title);

			// Target date is the targetSession date, else the toDate
			if (targetSessionId != 0) {
				CDTSession targetSession = CDTSessionLocalServiceUtil.getCDTSession(targetSessionId);
				homework.setTargetDate(targetSession.getStart());
			} else {
				homework.setTargetDate(targetDate);
			}
			homework.setModificationDate(new Date());
			homework.setCourseId(courseId);
			homework.setTeacherId(teacher.getUserId());
			homework.setHomeworkType(homeworkType);
			homework.setEstimatedTime(estimatedTime);
			homework.setPublicationDate(publicationDate);
			homework.setIsDraft(isDraft);

			// Students
			if (!studentIds.isEmpty()) {
				homework.setIsCustomStudentList(true);
				for (long studentId : studentIds) {
					StudentHomeworkLocalServiceUtil.getOrCreateStudentHomework(homework.getHomeworkId(), studentId);
				}
			} else {
				homework.setIsCustomStudentList(false);
				assignHomeworkToStudents(courseId, homework.getHomeworkId());
			}

			// Create deposit folder
			if (homeworkType != CourseConstants.HOMEWORK_TYPE_SIMPLE) {
				Folder courseFolder = FolderUtilsLocalServiceUtil.getGroupCourseFolder(courseId);

				Folder homeworkFolder = DLAppServiceUtil.addFolder(
					UUID.randomUUID().toString(),
					courseFolder.getGroupId(),
					courseFolder.getFolderId(),
					String.valueOf(homework.getHomeworkId()),
					"",
					new ServiceContext()
				);
				// Add ADD permission to teachers and READ perm to students and parents
				long teacherRoleId = RoleUtilsLocalServiceUtil.getTeacherRole().getRoleId();
				ResourcePermissionLocalServiceUtil.setResourcePermissions(homeworkFolder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, ""+homeworkFolder.getFolderId(), teacherRoleId, new String[]{ActionKeys.VIEW, ActionKeys.ADD_DOCUMENT, ActionKeys.UPDATE, ActionKeys.DELETE});
				long studentRoleId = RoleUtilsLocalServiceUtil.getStudentRole().getRoleId();
				long parentRoleId = RoleUtilsLocalServiceUtil.getParentRole().getRoleId();
				ResourcePermissionLocalServiceUtil.setResourcePermissions(homeworkFolder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, ""+homeworkFolder.getFolderId(), studentRoleId, new String[]{ActionKeys.VIEW});
				ResourcePermissionLocalServiceUtil.setResourcePermissions(homeworkFolder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, ""+homeworkFolder.getFolderId(), parentRoleId, new String[]{ActionKeys.VIEW});
			}

			return updateHomework(homework);
		} catch (Exception e) {
			logger.error("Error when creating an homework", e);
		}

		return null;
	}


	// Used from progression service
	public Homework updateHomework(long homeworkId, String title, long targetSessionId, Date targetDate, int estimatedTime, List<Long> studentIds, Date publicationDate, boolean isDraft) {
		logger.info("Updating homework " + homeworkId + ", targetSessionId=" + targetSessionId + " and " + studentIds.size() + " students");

		try {
			Homework homework = getHomework(homeworkId);

			homework.setTitle(title);
			homework.setModificationDate(new Date());
			homework.setTargetDate(targetDate);
			homework.setTargetSessionId(targetSessionId);
			homework.setPublicationDate(publicationDate);
			homework.setIsDraft(isDraft);
			homework.setEstimatedTime(estimatedTime);

			// They are 4 CASES:
			// 1. group -> student list
			// 2. group -> group
			// 3. student list -> group
			// 4. student list -> student list

			// Case 1 : was assigned to group, now is assigned to a student list
			if (!homework.isIsCustomStudentList() && !studentIds.isEmpty()) {

				logger.info("Homework update (id="+homeworkId+") - Case 1 : group -> student list");
				homework.setIsCustomStudentList(true);
				StudentHomeworkLocalServiceUtil.removeHomework(homeworkId);
				for (Long studentId : studentIds) {
					StudentHomeworkLocalServiceUtil.getOrCreateStudentHomework(homework.getHomeworkId(), studentId);
				}

				// Case 2 : group -> group
			} else if (!homework.isIsCustomStudentList() && studentIds.isEmpty()) {

				logger.info("Homework update (id="+homeworkId+") - Case 2 : group -> group");

				// Case 3 : student list -> whole group
			} else if (homework.isIsCustomStudentList()	&& studentIds.isEmpty()) {

				logger.info("Homework update (id="+homeworkId+") - Case 3 : student list -> group");
				homework.setIsCustomStudentList(false);
				StudentHomeworkLocalServiceUtil.removeHomework(homeworkId);
				assignHomeworkToStudents(homework.getCourseId(), homework.getHomeworkId());

				// Case 4 : student list -> student list
			} else {

				logger.info("Homework update (id="+homeworkId+") - Case 4 : student list -> student list");
				StudentHomeworkLocalServiceUtil.removeHomework(homeworkId);
				for (Long studentId : studentIds) {
					StudentHomeworkLocalServiceUtil.getOrCreateStudentHomework(homework.getHomeworkId(), studentId);
				}
			}

			return updateHomework(homework);

		} catch (Exception e) {
			logger.error("Error while updating homework " + homeworkId + " content", e);
		}

		return null;
	}

	private void assignHomeworkToStudents(long courseId, long homeworkId) {
		try {
			// Course is either an org or a community
			Group courseGroup = GroupLocalServiceUtil.getGroup(courseId);
			Role studentRole = RoleUtilsLocalServiceUtil.getStudentRole();
			List<Long> roleIds = new ArrayList<>();
			roleIds.add(studentRole.getRoleId());
			List<User> studentList;
			if (courseGroup.isRegularSite()) {

				List<Long> groupIds = new ArrayList<>();
				groupIds.add(courseId);
				studentList = UserSearchLocalServiceUtil.searchUsers("", null, groupIds,
						roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			} else {

				// Create StudentHomeworks for all class students
				List<Long> organizationIds = new ArrayList<>();
				organizationIds.add(courseGroup.getClassPK());

				studentList = UserSearchLocalServiceUtil.searchUsers("", organizationIds, null,
						roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			}
			for (User student : studentList) {
				StudentHomeworkLocalServiceUtil.getOrCreateStudentHomework(homeworkId, student.getUserId());
			}
		} catch (Exception e) {
			logger.error("Error while affecting homework " + homeworkId + " to course students", e);
		}

	}

	public List<Homework> getSessionToDoHomeworks (User user, long sessionId, boolean hideDraftsForTeachers) {
		List<Homework> toDoHomeworks = new ArrayList<>();

		try {
			List<Homework> homeworks = homeworkPersistence.findBytargetSessionId(sessionId);
			if (homeworks != null && !homeworks.isEmpty()) {
				for (Homework homework : homeworks) {
					if (RoleUtilsLocalServiceUtil.isTeacher(user) && !(hideDraftsForTeachers && homework.getIsDraft())) {
						// Teachers see all homeworks
						toDoHomeworks.add(homework);
					} else if (RoleUtilsLocalServiceUtil.isStudent(user) &&
							StudentHomeworkLocalServiceUtil.hasStudentHomework(user.getUserId(), homework.getHomeworkId()) &&
							!homework.getIsDraft() &&
							homework.getPublicationDate().before(new Date())) {
						// Students do not see drafts and unpublished homeworks
						toDoHomeworks.add(homework);
					} else if (RoleUtilsLocalServiceUtil.isParent(user)) {
						for (User child : UserRelationshipLocalServiceUtil.getChildren(user.getUserId())) {
							if (StudentHomeworkLocalServiceUtil.hasStudentHomework(child.getUserId(), homework.getHomeworkId()) &&
									!homework.getIsDraft() &&
									homework.getPublicationDate().before(new Date())) {
								toDoHomeworks.add(homework);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching homeworks given in sessionId " + sessionId, e);
		}

		return toDoHomeworks;
	}

	public List<Homework> getSessionGivenHomeworks (User user, long sessionId, boolean hideDraftsForTeachers) {
		List<Homework> givenHomeworks = new ArrayList<>();

		try {
			List<Homework> homeworks = homeworkPersistence.findBysourceSessionId(sessionId);
			if (homeworks != null && !homeworks.isEmpty()) {
				for (Homework homework : homeworks) {
					if (RoleUtilsLocalServiceUtil.isTeacher(user) && !(hideDraftsForTeachers && homework.getIsDraft())) {
						// Teachers see all homeworks
						givenHomeworks.add(homework);
					} else if (RoleUtilsLocalServiceUtil.isStudent(user) &&
							StudentHomeworkLocalServiceUtil.hasStudentHomework(user.getUserId(), homework.getHomeworkId()) &&
							!homework.getIsDraft() &&
							homework.getPublicationDate().before(new Date())) {
						// Students do not see drafts and unpublished homeworks
						givenHomeworks.add(homework);
					} else if (RoleUtilsLocalServiceUtil.isParent(user)) {
						for (User child : UserRelationshipLocalServiceUtil.getChildren(user.getUserId())) {
							if (StudentHomeworkLocalServiceUtil.hasStudentHomework(child.getUserId(), homework.getHomeworkId()) &&
									!homework.getIsDraft() &&
									homework.getPublicationDate().before(new Date())) {
								givenHomeworks.add(homework);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching homeworks given in sessionId " + sessionId, e);
		}

		return givenHomeworks;
	}


	// Used for dashboard homework widget and homework tab in sessionsAndHomework service
	public List<Homework> getStudentHomeworks(long studentId, Date minDate, Date maxDate, boolean undoneOnly) {

		List<Homework> studentHomeworkList = new ArrayList<>();

		// Limit to school year
		Date schoolYearStartDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearStartDate();
		if (minDate.before(schoolYearStartDate)) {
			minDate = schoolYearStartDate;
		}
		try {
			List<Homework> homeworkList = homeworkFinder.getStudentHomeworks(studentId, minDate, maxDate, undoneOnly);
			// Check course belonging for the student, because he might have changed class and/or course
			// We do not want him/her to access old homeworks
			for (Homework homework : homeworkList) {
				logger.info("TMP add permission on homework " + homework.getHomeworkId());
				getHomeworkFolder(homework.getHomeworkId(), false);
				if (UserUtilsLocalServiceUtil.getUserGroupIds(studentId).contains(homework.getCourseId())) {
					studentHomeworkList.add(homework);
				} else {
					logger.error("Student " + studentId + " may be trying to access old classes's homework");
				}
			}
		} catch (Exception e) {
			logger.error("Error when fetching homeworks for student " + studentId, e);
		}


		// No sorting, we can return more than NB_HOMEWORKS homeworks, just the maxDate is important
		return studentHomeworkList;
	}

	// Used for work load
	public List<Homework> getStudentsHomeworks(List<Long> studentIds, Date minDate, Date maxDate) {

		List<Homework> studentHomeworkList = new ArrayList<>();

		// Limit to school year
		Date schoolYearStartDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearStartDate();
		if (minDate.before(schoolYearStartDate)) {
			minDate = schoolYearStartDate;
		}
		try {
			return homeworkFinder.getStudentsHomeworks(studentIds, minDate, maxDate);
		} catch (Exception e) {
			logger.error("Error when fetching homeworks for " + studentIds.size() + " students", e);
		}

		// No sorting, we can return more than NB_HOMEWORKS homeworks, just the maxDate is important
		return studentHomeworkList;
	}

	public int countUndoneHomeworks(long studentId) {
		try {
			Date minDate = new Date();
			Date maxDate = ScheduleConfigurationLocalServiceUtil.getSchoolYearEndDate();
			return countUndoneHomeworks(studentId, minDate, maxDate);
		} catch (Exception e) {
			logger.error("Error when counting undone homeworks for student " + studentId, e);
		}

		return 0;
	}

	public int countUndoneHomeworks(long studentId, Date minDate, Date maxDate) {
		try {
			return homeworkFinder.countUndoneHomeworks(studentId, minDate, maxDate);
		} catch (Exception e) {
			logger.error("Error when counting undone homeworks for student " + studentId, e);
		}

		return 0;
	}

	// For dashboard activity widget
	public List<Homework> getStudentHomeworkActivity(long studentId, Date minDate, Date maxDate) {

		List<Homework> studentHomeworkList = new ArrayList<>();

		// Get the groupId list for the student
		List<Long> studentGroupIdList = UserUtilsLocalServiceUtil.getUserGroupIds(studentId);

		// If no group => return empty homework list
		if (studentGroupIdList.isEmpty()) {
			return studentHomeworkList;
		}

		try {
			return homeworkFinder.getStudentHomeworkActivity(studentId, studentGroupIdList, minDate, maxDate);
		} catch (Exception e) {
			logger.error("Error fetching homework activities for student " + studentId, e);
		}

		return studentHomeworkList;
	}

	// For group activity
	public List<Homework> getCourseHomeworkActivity(long userId, long courseId, Date minDate, Date maxDate) {
		List<Homework> courseHomeworkList = new ArrayList<>();

		try {
			List<Homework> courseHomeworks = homeworkPersistence.findBycourseId(courseId);
			if (courseHomeworks != null && !courseHomeworks.isEmpty()) {
				for (Homework courseHomework : courseHomeworks) {
					if (courseHomework.getModificationDate().after(minDate) &&
						courseHomework.getModificationDate().before(maxDate) &&
						(courseHomework.getTeacherId() == userId || (!courseHomework.getIsDraft() && courseHomework.getPublicationDate().before(new Date())))) {
						courseHomeworkList.add(courseHomework);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching homework activity for groupId " + courseId, e);
		}

		return courseHomeworkList;
	}


	public List<Homework> getTeacherHomeworksToCorrect(long teacherId, long courseId) {

		try {
			return homeworkFinder.getTeacherHomeworksToCorrect(teacherId, courseId);
		} catch (Exception e) {
			logger.error("Error when running dynamic query to get all homeworks to correct for teacher " + teacherId + " at given date range", e);
		}
		return new ArrayList<>();
	}

	public JSONArray countHomeworksToCorrect(long teacherId) {

		JSONArray nbHomeworks = new JSONArray();
		try {
			List<Homework> homeworks = getTeacherHomeworksToCorrect(teacherId, 0);
			// Group them by week number
			Map<Integer, Integer> nbHomeworksToCorrectMap = new HashMap<>();
			for (Homework homework : homeworks) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(homework.getTargetDate());
				int weekNb = cal.get(Calendar.WEEK_OF_YEAR);
				nbHomeworksToCorrectMap.putIfAbsent(weekNb, 0);
				nbHomeworksToCorrectMap.put(weekNb, nbHomeworksToCorrectMap.get(weekNb) + 1);
			}
			// Build JSONArray
			for (Map.Entry<Integer, Integer> entry : nbHomeworksToCorrectMap.entrySet()) {
				JSONObject weekJson = new JSONObject();
				weekJson.put(JSONConstants.WEEK_NB, entry.getKey());
				weekJson.put(JSONConstants.NB_HOMEWORKS_TO_CORRECT, entry.getValue());
				nbHomeworks.put(weekJson);
			}
		} catch (Exception e) {
			logger.error("Error when counting homeworks to correct for teacher " + teacherId, e);
		}
		return nbHomeworks;
	}


	public Folder getHomeworkFolder(long homeworkId, boolean doCreate) throws PortalException, SystemException {

		Homework homework = getHomework(homeworkId);
		Folder courseFolder = FolderUtilsLocalServiceUtil.getGroupCourseFolder(homework.getCourseId());
		PermissionUtilsLocalServiceUtil.addDefaultPermissionsFolder(courseFolder);

		Folder homeworkFolder = null;
		try {
			homeworkFolder = FolderUtilsLocalServiceUtil.getFolderByName(courseFolder, String.valueOf(homeworkId));
		} catch (NoSuchFolderException e) {
			if (doCreate) {
				logger.info("Creating folder for homework " + homeworkId);
				homeworkFolder = DLAppServiceUtil.addFolder(
						UUID.randomUUID().toString(),
						courseFolder.getGroupId(),
						courseFolder.getFolderId(),
						String.valueOf(homeworkId),
						"Dossier du devoir " + homeworkId,
						new ServiceContext()
				);
				logger.info("Created drop folder for homework " + homeworkId);
			}
		} catch (Exception e) {
			logger.error("Error when fetching folder for homeworkId " + homeworkId, e);
		}

		if (homeworkFolder != null) {
			// Apply default permissions so that students can VIEW
			logger.info("Applying default permissions on homework folder " + homeworkFolder.getFolderId());
			PermissionUtilsLocalServiceUtil.addDefaultPermissionsFolder(homeworkFolder);
		}

		return homeworkFolder;
	}

	public Folder getHomeworkDropFolder(long homeworkId, boolean doCreate) throws PortalException, SystemException {

		Folder homeworkDropFolder = null;
		Folder homeworkFolder = getHomeworkFolder(homeworkId, doCreate);

		if (homeworkFolder != null) {
			try {
				homeworkDropFolder = FolderUtilsLocalServiceUtil.getFolderByName(homeworkFolder, CourseConstants.DROP_FOLDER);
			} catch (NoSuchFolderException e) {
				if (doCreate) {
					logger.info("Creating drop folder for homework " + homeworkId);
					homeworkDropFolder = DLAppServiceUtil.addFolder(
							UUID.randomUUID().toString(),
							homeworkFolder.getGroupId(),
							homeworkFolder.getFolderId(),
							CourseConstants.DROP_FOLDER,
							"",
							new ServiceContext()
					);
					logger.info("Created drop folder for homework " + homeworkId);
				}
			}

		}

		return homeworkDropFolder;
	}

	public boolean deleteSessionHomeworks(long sessionId) {
		try {
			// Update homeworks having target sessionId
			List<Homework> homeworks = homeworkPersistence.findBytargetSessionId(sessionId);
			if (homeworks != null) {
				CDTSession sessionToDisable = CDTSessionLocalServiceUtil.getCDTSession(sessionId);
				for (Homework homework : homeworks) {
					homework.setTargetSessionId(0);
					homework.setTargetDate(sessionToDisable.getStart());
					homeworkPersistence.update(homework);
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("Error deleting homeworks linked to sessionId " + sessionId, e);
		}

		return false;
	}

	/**
	 * Remove an homework and its associated objects
	 */
	public void deleteHomeworkAndDependencies(long homeworkId) {
		try {
			// Remove student homework
			StudentHomeworkLocalServiceUtil.removeHomework(homeworkId);

			// Delete drop folder
			Folder homeworkDropFolder = getHomeworkDropFolder(homeworkId, false);
			if (homeworkDropFolder != null) {
				DLAppServiceUtil.deleteFolder(homeworkDropFolder.getFolderId());
			}

			// Delete homework folder
			Folder homeworkFolder = getHomeworkFolder(homeworkId, false);
			if (homeworkFolder != null) {
				DLAppServiceUtil.deleteFolder(homeworkFolder.getFolderId());
			}

			// Remove the homework itself
			deleteHomework(homeworkId);

		} catch (Exception e) {
			logger.error("Error deleting homework " + homeworkId , e);
		}

	}

	// Used by synchro to prevent homework deletion
	public boolean hasHomeworksToDoForSession(long sessionId) {
		try {
			List<Homework> homeworks = homeworkPersistence.findBytargetSessionId(sessionId);
			if (homeworks != null && !homeworks.isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			logger.error("Error fetching homeworks to do for sessionId " + sessionId, e);
		}

		return false;
	}

	// Used by synchro to prevent homework deletion
	public boolean hasHomeworksGivenDuringSession(long sessionId) {
		try {
			List<Homework> homeworks = homeworkPersistence.findBysourceSessionId(sessionId);
			if (homeworks != null && !homeworks.isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			logger.error("Error fetching homeworks given in sessionId " + sessionId, e);
		}

		return false;
	}

	public void sendCorrections(long teacherId, long homeworkId) throws PortalException {

		Homework homework = getHomework(homeworkId);
		homework.setIsCorrectionSent(true);
		updateHomework(homework);

		// Push mobile notification to students having sent a document
		String courseName = GroupUtilsLocalServiceUtil.getGroupName(homework.getCourseId());
		String title = "Cours & Devoirs - " + courseName;
		User teacher = UserLocalServiceUtil.getUser(teacherId);
		List<StudentHomework> studentHomeworks = studentHomeworkPersistence.findByhomeworkId(homeworkId);
		if (studentHomeworks != null) {
			for (StudentHomework studentHomework : studentHomeworks) {
				if (studentHomework.getIsSent() && studentHomework.getSentFileId() != 0 && studentHomework.getIsCorrected()) {
					User student = UserLocalServiceUtil.getUser(studentHomework.getStudentId());
					String content = teacher.getFullName() + " a corrigé le devoir " + homework.getTitle() + " de " + student.getFullName();
					MobileDeviceLocalServiceUtil.pushNotificationToUser(studentHomework.getStudentId(), title, "", content,
							MobileConstants.HOMEWORK_TYPE, homeworkId);
					logger.info("Pushed correction to student " + student.getFullName());
				}
			}
		}

	}
}