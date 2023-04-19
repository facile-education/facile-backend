package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.schedule.model.StudentHomework;
import com.weprode.nero.schedule.service.base.StudentHomeworkLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.StudentHomework",
	service = AopService.class
)
public class StudentHomeworkLocalServiceImpl
	extends StudentHomeworkLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(HomeworkLocalServiceImpl.class);

	public StudentHomework getOrCreateStudentHomework(long homeworkId, long studentId) {

		try {
			List<StudentHomework> studentHomeworks = studentHomeworkPersistence.findBystudentId_homeworkId(studentId, homeworkId);
			if (studentHomeworks != null && studentHomeworks.size() > 0) {
				return studentHomeworks.get(0);
			} else {
				StudentHomework studentHomework = studentHomeworkPersistence.create(counterLocalService.increment());
				studentHomework.setStudentId(studentId);
				studentHomework.setHomeworkId(homeworkId);
				studentHomeworkPersistence.update(studentHomework);
				return studentHomework;
			}
		} catch (Exception e) {
			logger.debug(e);
		}

		return null;
	}

	public StudentHomework getStudentHomework(long homeworkId, long studentId) {
		try {
			List<StudentHomework> studentHomeworks = studentHomeworkPersistence.findBystudentId_homeworkId(studentId, homeworkId);
			if (studentHomeworks != null && studentHomeworks.size() > 0) {
				return studentHomeworks.get(0);
			}
		} catch (Exception e) {
			logger.debug(e);
		}

		return null;
	}

	public boolean setHomeworkDone(long homeworkId, long studentId, boolean isDone) {
		try {
			StudentHomework studentHomework = getOrCreateStudentHomework(homeworkId, studentId);
			studentHomework.setIsDone(isDone);
			studentHomeworkPersistence.update(studentHomework);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean setHomeworkSent(long homeworkId, long studentId) {

		try {
			StudentHomework studentHomework = getOrCreateStudentHomework(homeworkId, studentId);
			if (!studentHomework.getIsSent()) {
				studentHomework.setIsDone(true);
				studentHomework.setIsSent(true);
			}
			studentHomework.setSentDate(new Date());
			studentHomeworkPersistence.update(studentHomework);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Get all students having given homework Id
	 */
	public List<User> getHomeworkStudents(long homeworkId) {
		List<User> result = new ArrayList<>();

		try {
			List<StudentHomework> studentHomeworks = studentHomeworkPersistence.findByhomeworkId(homeworkId);
			if (studentHomeworks != null) {
				for (StudentHomework studentHomework : studentHomeworks) {
					User student = UserLocalServiceUtil.getUser(studentHomework.getStudentId());
					result.add(student);
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}

		return result;
	}

	/**
	 * Returns true if the given student has given homework
	 */
	public boolean hasStudentHomework(long studentId, long homeworkId) {
		try {
			List<StudentHomework> studentHomeworkList = studentHomeworkPersistence.findBystudentId_homeworkId(studentId, homeworkId);
			if (studentHomeworkList != null && studentHomeworkList.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}

	/**
	 * Returns true if the given student has done the given homework
	 */
	public boolean hasStudentDoneHomework(long studentId, long homeworkId) {
		try {
			List<StudentHomework> studentHomeworkList = studentHomeworkPersistence.findBystudentId_homeworkId(studentId, homeworkId);
			if (studentHomeworkList != null && studentHomeworkList.size() > 0 && studentHomeworkList.get(0).getIsDone()) {
				return true;
			}
		} catch (Exception e) {
			logger.debug(e);
		}

		return false;
	}

	/**
	 * Returns the users having done the given homework
	 */
	public List<User> getStudentsHavingDoneHomework(long homeworkId) {
		List<User> students = new ArrayList<>();

		try {
			List<StudentHomework> studentHomeworkList = studentHomeworkPersistence.findByhomeworkId(homeworkId);
			if (studentHomeworkList != null && studentHomeworkList.size() > 0) {
				for (StudentHomework studentHomework : studentHomeworkList) {
					if (studentHomework.getIsDone()) {
						students.add(UserLocalServiceUtil.getUser(studentHomework.getStudentId()));
					}
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}

		return students;
	}

	public boolean removeStudentHomework(long homeworkId, long studentId) {
		try {
			StudentHomework studentHomework = studentHomeworkPersistence.findBystudentId_homeworkId(studentId, homeworkId).get(0);
			studentHomeworkPersistence.remove(studentHomework);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean removeHomework(long homeworkId) {
		try {
			studentHomeworkPersistence.removeByhomeworkId(homeworkId);
		} catch (Exception e) {
			logger.debug(e);
		}

		return true;
	}
}