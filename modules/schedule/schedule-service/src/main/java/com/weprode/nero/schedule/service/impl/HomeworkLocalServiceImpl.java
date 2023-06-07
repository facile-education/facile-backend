package com.weprode.nero.schedule.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.weprode.nero.progression.service.ItemContentLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionItemLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.Homework;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.HomeworkLocalServiceUtil;
import com.weprode.nero.schedule.service.StudentHomeworkLocalServiceUtil;
import com.weprode.nero.schedule.service.base.HomeworkLocalServiceBaseImpl;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.Homework",
	service = AopService.class
)
public class HomeworkLocalServiceImpl extends HomeworkLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(HomeworkLocalServiceImpl.class);

	// Used from progression service
	public Homework createHomework(User teacher, String description, long sourceSessionId, long targetSessionId, long groupId, Date toDate, int type, long estimatedTime, List<User> students) {
		try {
			logger.info("Creating homework by teacher " + teacher.getFullName() + ", description '" + description + "', sourceSessionId=" + sourceSessionId + ", targetSessionId=" + targetSessionId + ", type=" + type + " and " + students.size() + " students");
			// Target week
			Calendar cal = Calendar.getInstance();
			cal.setTime(toDate);
			int weekId = cal.get(Calendar.WEEK_OF_YEAR);

			long homeworkId = CounterLocalServiceUtil.increment();
			Homework homework = homeworkPersistence.create(homeworkId);

			homework.setDescription(description);
			homework.setSourceSessionId(sourceSessionId);
			homework.setTargetSessionId(targetSessionId);
			homework.setTargetWeekId(weekId);

			// Target date is the targetSession date, else the toDate
			if (targetSessionId != 0) {
				CDTSession targetSession = CDTSessionLocalServiceUtil.getCDTSession(targetSessionId);
				homework.setTargetDate(targetSession.getSessionStart());
			} else {
				homework.setTargetDate(toDate);
			}
			homework.setFromDate(new Date());
			homework.setGroupId(groupId);
			homework.setTeacherId(teacher.getUserId());
			homework.setEstimatedTime(estimatedTime);
			homework.setType(type);

			// Students
			if (!students.isEmpty()) {
				homework.setIsCustomStudentList(true);
				for (User student : students) {
					StudentHomeworkLocalServiceUtil.getOrCreateStudentHomework(homework.getHomeworkId(), student.getUserId());
					logger.info("Give homework to student " + student.getFullName());
				}
			} else {
				homework.setIsCustomStudentList(false);
			}

			return HomeworkLocalServiceUtil.updateHomework(homework);
		} catch (Exception e) {
			logger.error("Error when creating an homework", e);
		}

		return null;
	}

	public boolean assignHomeworkContent (long homeworkId, long progressionItemId) {
		try {
			// Push item text + links + videos + h5p into session's description
			Homework homework = HomeworkLocalServiceUtil.getHomework(homeworkId);
			String itemContent = ProgressionItemLocalServiceUtil.convertContentAsHtml(progressionItemId);
			homework.setDescription(itemContent);
			HomeworkLocalServiceUtil.updateHomework(homework);

			// Delete existing attach files
			// TODO Attachments
			// AttachFileLocalServiceUtil.removeAllHomeworkAttachFiles(homeworkId);

			// Copy attached files
			List<Long> fileEntryIds = ItemContentLocalServiceUtil.getFileIds(progressionItemId);
			for (Long fileEntryId : fileEntryIds) {
				logger.info("Assigning file " + fileEntryId + " to homework " + homeworkId);
				// TODO Attachments
				// AttachFileLocalServiceUtil.addHomeworkAttachFile(homeworkId, fileEntryId, false);
			}

			List<Long> editableFileEntryIds = ItemContentLocalServiceUtil.getEditableFileIds(progressionItemId);
			for (Long fileEntryId : editableFileEntryIds) {
				logger.info("Assigning editable file " + fileEntryId + " to homework " + homeworkId);
				// TODO Attachments
				// AttachFileLocalServiceUtil.addHomeworkAttachFile(homeworkId, fileEntryId, true);
			}

			// Delete existing audio files
			// TODO Attachments
			// AttachFileLocalServiceUtil.deleteHomeworkAudioInstructions(homeworkId);

			// Audio files are already included in the homework's description

			logger.info("Assigned content of item " + progressionItemId + " to homework " + homeworkId);
			return true;
		} catch (Exception e) {
			logger.error("Error assigning item " + progressionItemId + " to homework " + homeworkId, e);
		}

		return false;
	}

	// Used from progression service
	public Homework updateHomeworkTargets(long homeworkId, long targetSessionId, Date toDate, List<User> students) {
		logger.info("Updating homework " + homeworkId + ", targetSessionId=" + targetSessionId + " and " + students.size() + " students");

		try {
			Homework homework = HomeworkLocalServiceUtil.getHomework(homeworkId);

			// Rename the folder if the homework is still to be sent but the target date changes
			boolean renamedNeededForDropboxFolder = homework.getType() == 3 && !homework.getTargetDate().equals(toDate);

			Calendar cal = Calendar.getInstance();
			cal.setTime(toDate);
			int weekId = cal.get(Calendar.WEEK_OF_YEAR);

			homework.setTargetDate(toDate);
			homework.setTargetSessionId(targetSessionId);
			homework.setTargetWeekId(weekId);

			// They are 4 CASES:
			// 1. group -> student list
			// 2. group -> group
			// 3. student list -> group
			// 4. student list -> student list

			// Case 1 : was assigned to group, now is assigned to a student list
			if (!homework.isIsCustomStudentList() && !students.isEmpty()) {

				logger.info("Homework update (id="+homeworkId+") - Case 1 : group -> student list");

				homework.setIsCustomStudentList(true);

				// Remove homework to obsolete students (that may remain)
				StudentHomeworkLocalServiceUtil.removeHomework(homeworkId);

				// Add selected students
				for (User student : students) {
					StudentHomeworkLocalServiceUtil.getOrCreateStudentHomework(homework.getHomeworkId(), student.getUserId());
				}

				// Case 2 : group -> group
			} else if (!homework.isIsCustomStudentList() && students.isEmpty()) {

				logger.info("Homework update (id="+homeworkId+") - Case 2 : group -> group");

				// Case 3 : student list -> whole group
			} else if (homework.isIsCustomStudentList()	&& students.isEmpty()) {

				logger.info("Homework update (id="+homeworkId+") - Case 3 : student list -> group");

				// Assign homework to the whole group
				homework.setIsCustomStudentList(false);

				// Remove homework to obsolete students
				StudentHomeworkLocalServiceUtil.removeHomework(homeworkId);

				// Case 4 : student list -> student list
			} else {

				logger.info("Homework update (id="+homeworkId+") - Case 4 : student list -> student list");

				// Remove homework to all students
				StudentHomeworkLocalServiceUtil.removeHomework(homeworkId);

				// Add selected students
				for (User student : students) {
					StudentHomeworkLocalServiceUtil.getOrCreateStudentHomework(homework.getHomeworkId(), student.getUserId());
				}
			}

			// TODO Homework Deposit
			/* if (renamedNeededForDropboxFolder) {
				HomeworkDropboxFolderLocalServiceUtil.renameDropboxFolders(homework);
			}*/
			return HomeworkLocalServiceUtil.updateHomework(homework);

		} catch (Exception e) {
			logger.error("Error while updating homework " + homeworkId + " content", e);
		}

		return null;
	}

	public List<Homework> getToDoHomeworks (long sessionId, long studentId) {
		List<Homework> toDoHomeworks = new ArrayList<>();

		try {
			List<Homework> homeworks = homeworkPersistence.findBytargetSessionId(sessionId);
			if (studentId == 0) {
				// Current user is not a student or parent -> return all homeworks
				return homeworks;
			}

			// Current user is student or parent -> filter on homeworks he really has
			if (homeworks != null && homeworks.size() > 0) {
				for (Homework homework : homeworks) {
					if (homework.getIsCustomStudentList()) {
						boolean hasStudentHomework = StudentHomeworkLocalServiceUtil.hasStudentHomework(studentId, homework.getHomeworkId());
						if (hasStudentHomework) {
							toDoHomeworks.add(homework);
						}
					} else {
						toDoHomeworks.add(homework);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching homeworks given in sessionId " + sessionId, e);
		}

		return toDoHomeworks;
	}

	public List<Homework> getGivenHomeworks (long sessionId, long studentId) {
		List<Homework> givenHomeworks = new ArrayList<>();

		try {
			List<Homework> homeworks = homeworkPersistence.findBysourceSessionId(sessionId);
			if (studentId == 0) {
				// Current user is not a student or parent -> return all homeworks
				return homeworks;
			}

			// Current user is student or parent -> filter on homeworks he really has
			if (homeworks != null && homeworks.size() > 0) {
				for (Homework homework : homeworks) {
					if (homework.getIsCustomStudentList()) {
						boolean hasStudentHomework = StudentHomeworkLocalServiceUtil.hasStudentHomework(studentId, homework.getHomeworkId());
						if (hasStudentHomework) {
							givenHomeworks.add(homework);
						}
					} else {
						// If homework target next session then check the group which might be different from current session
						if (homework.getTargetSessionId() > 0) {
							List<Long> userGroupIds = UserUtilsLocalServiceUtil.getUserGroupIds(studentId);
							if (userGroupIds.contains(homework.getGroupId())) {
								givenHomeworks.add(homework);
							}
						} else {
							givenHomeworks.add(homework);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching homeworks given in sessionId " + sessionId, e);
		}

		return givenHomeworks;
	}

	/**
	 * Get the list of homeworks for given student starting at given minDate
	 */
	public List<Homework> getStudentHomeworks(User student, Date minDate) {
		// Result
		List<Homework> homeworkList = new ArrayList<>();

		// Get the groupId list for the student
		List<Long> studentGroupIdList = UserUtilsLocalServiceUtil.getUserGroupIds(student.getUserId());

		// Set maxDate to far away in the future
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, 100);
		Date maxDate = cal.getTime();

		// Add homeworks attached to subClass sessions
		List<CDTSession> subClassSessions = CDTSessionLocalServiceUtil.getStudentSpecificSessions(student.getUserId(), minDate, maxDate);
		if (subClassSessions != null) {
			for (CDTSession cdtSession : subClassSessions) {
				if (cdtSession.getGroupId() == 0) {
					List<Homework> subClassHomeworkList = new ArrayList<>();
					try {
						subClassHomeworkList = getToDoHomeworks(cdtSession.getSessionId(), student.getUserId());
					} catch (Exception e) {
						logger.error("Error while getting homeworks related to subClass sessions", e);
					}
					homeworkList.addAll(subClassHomeworkList);
				}
			}

		}

		// If no group => return empty homework list
		if (studentGroupIdList.isEmpty()) {
			return homeworkList;
		}

		// Run the dynamic query
		try {
			homeworkList.addAll(homeworkFinder.getStudentHomeworksFromGroupIds(studentGroupIdList, minDate, maxDate));
		} catch (Exception e) {
			logger.error("Error when running dynamic query to get all homeworks for student "+student.getUserId()+" at given date range", e);
		}

		// Skip the homeworks destinated to a student list and the current user does not belong to it
		List<Homework> finalHomeworkList = new ArrayList<>();
		for (Homework homework : homeworkList) {

			if (homework.getIsCustomStudentList()) {
				boolean hasStudentHomework = StudentHomeworkLocalServiceUtil.hasStudentHomework(student.getUserId(), homework.getHomeworkId());
				if (hasStudentHomework) {
					finalHomeworkList.add(homework);
				}
			} else {
				// Whole class homework
				finalHomeworkList.add(homework);
			}
		}

		return finalHomeworkList;
	}

	/**
	 * Get the list of homeworks for given teacher at given date range
	 */
	public List<Homework> getTeacherHomeworks(User teacher, Date minDate, long groupId) {
		List<Homework> homeworkList = new ArrayList<>();

		// Run the dynamic query
		try {
			homeworkFinder.getTeacherHomeworks(teacher, minDate, groupId);
		} catch (Exception e) {
			logger.error("Error when running dynamic query to get all homeworks for student "+teacher.getUserId()+" at given date range", e);
		}

		// logger.info("Teacher "+teacher.getFullName()+" has given "+homeworkList.size()+" homeworks to do for year starting on "+new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT).format(minDate));

		return homeworkList;
	}

	public boolean deleteSessionHomeworks(long sessionId) {
		try {
			// Delete homeworks having source sessionId
			homeworkPersistence.removeBysourceSessionId(sessionId);

			// Update homeworks having target sessionId
			List<Homework> homeworks = homeworkPersistence.findBytargetSessionId(sessionId);
			if (homeworks != null) {
				CDTSession sessionToDisable = CDTSessionLocalServiceUtil.getCDTSession(sessionId);
				for (Homework homework : homeworks) {
					homework.setTargetSessionId(0);
					homework.setTargetDate(sessionToDisable.getSessionStart());
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

			// TODO attachments
			// Remove attach file
			/*AttachFileLocalServiceUtil.removeAllHomeworkAttachFiles(homeworkToRemove.getHomeworkId());

			// Rename dropbox folder
			if (homeworkToRemove.getType() == 3) {
				HomeworkDropboxFolderLocalServiceUtil.tagDropboxsFoldersAsDeleted(homeworkToRemove.getHomeworkId());
			}*/

			// remove the homework
			HomeworkLocalServiceUtil.deleteHomework(homeworkToRemove);
			return true;

		} catch (Exception e) {
			logger.error("Error during homework removal : " + homeworkToRemove.getHomeworkId() , e);
		}

		return false;
	}

	public boolean hasHomeworksGivenInSession(long sessionId) {
		try {
			List<Homework> homeworks = homeworkPersistence.findBysourceSessionId(sessionId);
			if (homeworks != null && homeworks.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			logger.error("Error fetching homeworks given in sessionId " + sessionId, e);
		}

		return false;
	}

	public boolean hasHomeworksToDoForSession(long sessionId) {
		try {
			List<Homework> homeworks = homeworkPersistence.findBytargetSessionId(sessionId);
			if (homeworks != null && homeworks.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			logger.error("Error fetching homeworks given in sessionId " + sessionId, e);
		}

		return false;
	}
}