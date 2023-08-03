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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
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
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
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
					// Push mobile - Commented
					//MobileDeviceLocalServiceUtil.pushNotificationToUser(studentId, teacher.getFullName(), NEW_HOMEWORK, homework.getTitle(),
					//		MobileConstants.HOMEWORK_TYPE, homework.getHomeworkId());
				}
			} else {
				homework.setIsCustomStudentList(false);
				// Create StudentHomeworks for all class students
				List<Long> organizationIds = new ArrayList<>();
				Group courseGroup = GroupLocalServiceUtil.getGroup(courseId);
				organizationIds.add(courseGroup.getClassPK());

				Role studentRole = RoleUtilsLocalServiceUtil.getStudentRole();
				List<Long> roleIds = new ArrayList<>();
				roleIds.add(studentRole.getRoleId());
				List<User> studentList = UserSearchLocalServiceUtil.searchUsers("", organizationIds, null,
						roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
				for (User student : studentList) {
					StudentHomeworkLocalServiceUtil.getOrCreateStudentHomework(homework.getHomeworkId(), student.getUserId());
					// Push mobile - Commented
					// MobileDeviceLocalServiceUtil.pushNotificationToUser(student.getUserId(), teacher.getFullName(), NEW_HOMEWORK, homework.getTitle(),
					//		MobileConstants.HOMEWORK_TYPE, homework.getHomeworkId());
				}

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


	public List<Homework> getSessionToDoHomeworks (User user, long sessionId) {
		List<Homework> toDoHomeworks = new ArrayList<>();

		try {
			List<Homework> homeworks = homeworkPersistence.findBytargetSessionId(sessionId);
			if (homeworks != null && !homeworks.isEmpty()) {
				for (Homework homework : homeworks) {
					if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
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

	public List<Homework> getSessionGivenHomeworks (User user, long sessionId) {
		List<Homework> givenHomeworks = new ArrayList<>();

		try {
			List<Homework> homeworks = homeworkPersistence.findBysourceSessionId(sessionId);
			if (homeworks != null && !homeworks.isEmpty()) {
				for (Homework homework : homeworks) {
					if (RoleUtilsLocalServiceUtil.isTeacher(user)) {
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
			return homeworkFinder.getStudentHomeworks(studentId, minDate, maxDate, undoneOnly);
		} catch (Exception e) {
			logger.error("Error when fetching homeworks for student " + studentId, e);
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


	public List<Homework> getTeacherHomeworksToCorrect(User teacher) {

		try {
			return homeworkFinder.getTeacherHomeworksToCorrect(teacher.getUserId());
		} catch (Exception e) {
			logger.error("Error when running dynamic query to get all homeworks for student "+teacher.getUserId()+" at given date range", e);
		}
		return new ArrayList<>();
	}

	public int countHomeworksToCorrect(long teacherId) {

		try {
			return homeworkFinder.countHomeworksToCorrect(teacherId);

		} catch (Exception e) {
			logger.error("Error when counting homeworks to correct for teacher " + teacherId, e);
		}
		return 0;
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

		// Check if a file was previously dropped by the student -> delete it
		StudentHomework studentHomework = StudentHomeworkLocalServiceUtil.getStudentHomework(homeworkId, studentId);
		if (studentHomework != null && studentHomework.getIsSent()) {
			FileUtilsLocalServiceUtil.deleteFile(studentId, studentHomework.getSentFileId());
			logger.info("Old dropped file deleted");
		}

		Folder homeworkDropFolder = getHomeworkDropFolder(homeworkId);
		FileEntry copiedFile = FileUtilsLocalServiceUtil.moveFileEntry(studentId, fileEntryId, homeworkDropFolder.getFolderId(), DocumentConstants.MODE_NORMAL);
		StudentHomeworkLocalServiceUtil.setHomeworkSent(studentId, homeworkId, copiedFile.getFileEntryId());
	}

	public void cancelDrop(long studentId, long homeworkId) throws PortalException {

		StudentHomework studentHomework = StudentHomeworkLocalServiceUtil.getStudentHomework(homeworkId, studentId);
		if (studentHomework != null && studentHomework.getIsSent()) {
			FileUtilsLocalServiceUtil.deleteFile(studentId, studentHomework.getSentFileId());
			logger.info("Old dropped file deleted");
		}
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
			Folder homeworkDropFolder = getHomeworkDropFolder(homeworkId);
			DLAppServiceUtil.deleteFolder(homeworkDropFolder.getFolderId());

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