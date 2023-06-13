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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.weprode.nero.course.CourseConstants;
import com.weprode.nero.course.model.Homework;
import com.weprode.nero.course.model.StudentHomework;
import com.weprode.nero.course.service.StudentHomeworkLocalServiceUtil;
import com.weprode.nero.course.service.base.HomeworkLocalServiceBaseImpl;
import com.weprode.nero.document.constants.DocumentConstants;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.nero.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.nero.course.model.Homework",
	service = AopService.class
)
public class HomeworkLocalServiceImpl extends HomeworkLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(HomeworkLocalServiceImpl.class);
	private static final int NB_HOMEWORKS = 10;

	public Homework createHomework(User teacher, long sourceSessionId, long targetSessionId, long courseId, Date toDate, int homeworkType, List<Long> studentIds) {
		try {
			logger.info("Creating homework by teacher " + teacher.getFullName() + ", targetSessionId=" + targetSessionId + ", type=" + homeworkType + " and " + studentIds.size() + " students");

			Homework homework = homeworkPersistence.create(CounterLocalServiceUtil.increment());

			homework.setSourceSessionId(sourceSessionId);
			homework.setTargetSessionId(targetSessionId);

			// Target date is the targetSession date, else the toDate
			if (targetSessionId != 0) {
				CDTSession targetSession = CDTSessionLocalServiceUtil.getCDTSession(targetSessionId);
				homework.setTargetDate(targetSession.getStart());
			} else {
				homework.setTargetDate(toDate);
			}
			homework.setFromDate(new Date());
			homework.setCourseId(courseId);
			homework.setTeacherId(teacher.getUserId());
			homework.setHomeworkType(homeworkType);

			// Students
			if (!studentIds.isEmpty()) {
				homework.setIsCustomStudentList(true);
				for (long studentId : studentIds) {
					StudentHomeworkLocalServiceUtil.getOrCreateStudentHomework(homework.getHomeworkId(), studentId);
				}
			} else {
				homework.setIsCustomStudentList(false);
			}

			// Create deposit folder
			if (homeworkType != CourseConstants.HOMEWORK_TYPE_SIMPLE) {
				Folder courseFolder = FolderUtilsLocalServiceUtil.getGroupCourseFolder(courseId);

				Folder homeworkFolder = DLAppServiceUtil.addFolder(
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
	public Homework updateHomeworkTargets(long homeworkId, long targetSessionId, Date toDate, List<Long> studentIds) {
		logger.info("Updating homework " + homeworkId + ", targetSessionId=" + targetSessionId + " and " + studentIds.size() + " students");

		try {
			Homework homework = getHomework(homeworkId);

			homework.setTargetDate(toDate);
			homework.setTargetSessionId(targetSessionId);

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

	public List<Homework> getCourseHomeworks(User user, long courseId, Date minDate, Date maxDate) {
		List<Homework> courseHomeworks = new ArrayList<>();

		try {
			List<Homework> homeworks = homeworkPersistence.findBycourseId(courseId);
			// Current user is student or parent -> filter on homeworks he really has
			if (homeworks != null && !homeworks.isEmpty()) {
				for (Homework homework : homeworks) {
					// Manage:
					// - Custom homework broadcast
					// - Date range
					// - publication policy
					if ((!homework.getIsCustomStudentList() || hasUserCustomHomework(user, homework.getHomeworkId())) &&
							homework.getTargetDate().after(minDate) &&
							homework.getTargetDate().before(maxDate) &&
							(RoleUtilsLocalServiceUtil.isTeacher(user) || (!homework.getIsDraft() && homework.getPublicationDate().before(new Date())))) {
						courseHomeworks.add(homework);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching homeworks given in courseId " + courseId, e);
		}

		return courseHomeworks;
	}


	public boolean hasUserCustomHomework(User user, long homeworkId) {
		try {
			if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
				return true;
			} else if (RoleUtilsLocalServiceUtil.isStudent(user)) {
				return StudentHomeworkLocalServiceUtil.hasStudentDoneHomework(user.getUserId(), homeworkId);
			} else if (RoleUtilsLocalServiceUtil.isParent(user)) {
				for (User child : UserRelationshipLocalServiceUtil.getChildren(user.getUserId())) {
					if (StudentHomeworkLocalServiceUtil.hasStudentDoneHomework(child.getUserId(), homeworkId)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			// Nothing
		}
		return false;
	}


	public List<Homework> getSessionToDoHomeworks (User user, long sessionId) {
		List<Homework> toDoHomeworks = new ArrayList<>();

		try {
			List<Homework> homeworks = homeworkPersistence.findBytargetSessionId(sessionId);
			if (homeworks != null && !homeworks.isEmpty()) {
				for (Homework homework : homeworks) {
					// Manage:
					// - Custom homework broadcast
					// - publication policy
					if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
						toDoHomeworks.add(homework);
					} else if (RoleUtilsLocalServiceUtil.isStudent(user) &&
						(!homework.getIsCustomStudentList() || hasUserCustomHomework(user, homework.getHomeworkId())) &&
						(!homework.getIsDraft() && homework.getPublicationDate().before(new Date()))) {
						toDoHomeworks.add(homework);
					} else if (RoleUtilsLocalServiceUtil.isParent(user)) {
						for (User child : UserRelationshipLocalServiceUtil.getChildren(user.getUserId())) {
							if ((!homework.getIsCustomStudentList() || hasUserCustomHomework(child, homework.getHomeworkId())) &&
									(!homework.getIsDraft() && homework.getPublicationDate().before(new Date()))) {
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

	public List<Homework> getSessionGivenHomeworks (User user, long sessionId) {
		List<Homework> givenHomeworks = new ArrayList<>();

		try {
			List<Homework> homeworks = homeworkPersistence.findBysourceSessionId(sessionId);
			if (homeworks != null && !homeworks.isEmpty()) {
				for (Homework homework : homeworks) {
					// Manage:
					// - Custom homework broadcast
					// - publication policy
					if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
						givenHomeworks.add(homework);
					} else if (RoleUtilsLocalServiceUtil.isStudent(user) &&
							(!homework.getIsCustomStudentList() || hasUserCustomHomework(user, homework.getHomeworkId())) &&
							(!homework.getIsDraft() && homework.getPublicationDate().before(new Date()))) {
						givenHomeworks.add(homework);
					} else if (RoleUtilsLocalServiceUtil.isParent(user)) {
						for (User child : UserRelationshipLocalServiceUtil.getChildren(user.getUserId())) {
							if ((!homework.getIsCustomStudentList() || hasUserCustomHomework(child, homework.getHomeworkId())) &&
									(!homework.getIsDraft() && homework.getPublicationDate().before(new Date()))) {
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


	public List<Homework> getFutureStudentHomeworks(User student, boolean undoneOnly) {

		List<Homework> homeworkList = new ArrayList<>();

		// Get the groupId list for the student
		List<Long> studentGroupIdList = UserUtilsLocalServiceUtil.getUserGroupIds(student.getUserId());

		// If no group => return empty homework list
		if (studentGroupIdList.isEmpty()) {
			return homeworkList;
		}

		try {
			// Set maxDate to far away in the future
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			Date minDate = cal.getTime();
			cal.add(Calendar.YEAR, 100);
			Date maxDate = cal.getTime();

			homeworkList.addAll(homeworkFinder.getStudentHomeworksFromGroupIds(studentGroupIdList, minDate, maxDate));
		} catch (Exception e) {
			logger.error("Error when running dynamic query to get all homeworks for student "+student.getUserId()+" at given date range", e);
		}

		// Skip the homeworks given to a student list and the current user does not belong to it
		List<Homework> finalHomeworkList = new ArrayList<>();
		for (Homework homework : homeworkList) {

			// Filter undone only if needed
			// Filter on publication policy
			if ((!homework.getIsCustomStudentList() || hasUserCustomHomework(student, homework.getHomeworkId())) &&
					(!undoneOnly || StudentHomeworkLocalServiceUtil.hasStudentDoneHomework(student.getUserId(), homework.getHomeworkId()) &&
							!homework.getIsDraft() &&
							homework.getPublicationDate().before(new Date()))) {
				finalHomeworkList.add(homework);
			}
		}

		return finalHomeworkList;
	}

	public List<Homework> getPreviousStudentHomeworks(User student, Date maxDate, boolean undoneOnly) {

		List<Homework> studentHomeworkList = new ArrayList<>();

		// Get the groupId list for the student
		List<Long> studentGroupIdList = UserUtilsLocalServiceUtil.getUserGroupIds(student.getUserId());

		// If no group => return empty homework list
		if (studentGroupIdList.isEmpty()) {
			return studentHomeworkList;
		}

		// Loop back in time until 10 elements are fetched
		Calendar cal = Calendar.getInstance();
		cal.setTime(maxDate);
		cal.add(Calendar.DATE, -10);
		Date minDate = cal.getTime();
		while (minDate.after(ScheduleConfigurationLocalServiceUtil.getSchoolYearStartDate()) && studentHomeworkList.size() < NB_HOMEWORKS) {

			try {

				List<Homework> homeworkList = homeworkFinder.getStudentHomeworksFromGroupIds(studentGroupIdList, minDate, maxDate);
				// Skip the homeworks given to a student list and the current user does not belong to it
				for (Homework homework : homeworkList) {

					// Filter undone only if needed
					// Filter on publication policy
					if ((!homework.getIsCustomStudentList() || hasUserCustomHomework(student, homework.getHomeworkId())) &&
							(!undoneOnly || StudentHomeworkLocalServiceUtil.hasStudentDoneHomework(student.getUserId(), homework.getHomeworkId()) &&
									!homework.getIsDraft() &&
									homework.getPublicationDate().before(new Date()))) {
						studentHomeworkList.add(homework);
					}
				}

				// Go back 10 days
				cal.setTime(minDate);
				cal.add(Calendar.DATE, -10);
				minDate = cal.getTime();

			} catch (Exception e) {
				logger.error("Error when fetching homeworks back in time for student " + student.getUserId(), e);
			}
		}

		// No sorting, we can return more than NB_HOMEWORKS homeworks, just the maxDate is important
		return studentHomeworkList;
	}

	public List<Homework> getStudentHomeworkActivity(User student, Date minDate, Date maxDate) {

		List<Homework> studentHomeworkList = new ArrayList<>();

		// Get the groupId list for the student
		List<Long> studentGroupIdList = UserUtilsLocalServiceUtil.getUserGroupIds(student.getUserId());

		// If no group => return empty homework list
		if (studentGroupIdList.isEmpty()) {
			return studentHomeworkList;
		}

		try {

			List<Homework> homeworkList = homeworkFinder.getStudentHomeworksFromGroupIds(studentGroupIdList, minDate, maxDate);
			for (Homework homework : homeworkList) {

				// Skip the homeworks given to a student list and the current user does not belong to it
				// Filter on publication policy
				if ((!homework.getIsCustomStudentList() || hasUserCustomHomework(student, homework.getHomeworkId())) &&
						!homework.getIsDraft() &&
						homework.getPublicationDate().before(new Date())) {
					studentHomeworkList.add(homework);
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching homework activities for student " + student.getUserId(), e);
		}

		return studentHomeworkList;
	}


	public List<Homework> getTeacherHomeworksToCorrect(User teacher) {

		try {
			return homeworkFinder.getTeacherHomeworksToCorrect(teacher.getUserId());
		} catch (Exception e) {
			logger.error("Error when running dynamic query to get all homeworks for student "+teacher.getUserId()+" at given date range", e);
		}
		return new ArrayList<>();
	}

	public Folder getHomeworkFolder(long homeworkId) throws PortalException, SystemException {

		Homework homework = getHomework(homeworkId);
		Folder courseFolder = FolderUtilsLocalServiceUtil.getGroupCourseFolder(homework.getCourseId());

		Folder homeworkFolder = null;
		try {
			homeworkFolder = FolderUtilsLocalServiceUtil.getFolderByName(courseFolder, String.valueOf(homeworkId));
		} catch (NoSuchFolderException e) {
			homeworkFolder = DLAppServiceUtil.addFolder(
					courseFolder.getGroupId(),
					courseFolder.getFolderId(),
					String.valueOf(homeworkId),
					"Dossier du devoir " + homeworkId,
					new ServiceContext()
			);
		} catch (Exception e) {
			logger.error("Error when fetching folder for homeworkId " + homeworkId, e);
		}

		return homeworkFolder;
	}

	public Folder getHomeworkDropFolder(long homeworkId) throws PortalException, SystemException {

		Folder homeworkFolder = getHomeworkFolder(homeworkId);

		Folder homeworkDropFolder = null;
		try {
			homeworkDropFolder = FolderUtilsLocalServiceUtil.getFolderByName(homeworkFolder, CourseConstants.DROP_FOLDER);
		} catch (NoSuchFolderException e) {
			homeworkDropFolder = DLAppServiceUtil.addFolder(
					homeworkFolder.getGroupId(),
					homeworkFolder.getFolderId(),
					CourseConstants.DROP_FOLDER,
					"",
					new ServiceContext()
			);
		} catch (Exception e) {
			logger.error("Error when fetching folder for homeworkId " + homeworkId, e);
		}

		return homeworkDropFolder;
	}

	public void dropHomeworkFile(long studentId, long homeworkId, long fileEntryId) throws PortalException {

		Folder homeworkDropFolder = getHomeworkDropFolder(homeworkId);
		FileEntry copiedFile = FileUtilsLocalServiceUtil.moveFileEntry(studentId, fileEntryId, homeworkDropFolder.getFolderId(), DocumentConstants.MODE_NORMAL);
		StudentHomeworkLocalServiceUtil.setHomeworkSent(studentId, homeworkId, copiedFile.getFileEntryId());
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
	public boolean deleteHomeworkAndDependencies(Homework homeworkToRemove) {
		try {
			// Remove student homework
			StudentHomeworkLocalServiceUtil.removeHomework(homeworkToRemove.getHomeworkId());

			// remove the homework
			deleteHomework(homeworkToRemove);
			return true;

		} catch (Exception e) {
			logger.error("Error during homework removal : " + homeworkToRemove.getHomeworkId() , e);
		}

		return false;
	}


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

	public void correctFile(long homeworkId, long studentId, String comment) {
		StudentHomework studentHomework = StudentHomeworkLocalServiceUtil.getStudentHomework(homeworkId, studentId);
		if (studentHomework != null) {
			studentHomework.setIsCorrected(true);
			studentHomework.setCorrectionDate(new Date());
			studentHomework.setComment(comment);
			studentHomeworkPersistence.update(studentHomework);
		}
	}
}