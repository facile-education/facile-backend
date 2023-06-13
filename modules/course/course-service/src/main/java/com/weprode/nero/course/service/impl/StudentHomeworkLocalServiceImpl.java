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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.course.model.StudentHomework;
import com.weprode.nero.course.service.base.StudentHomeworkLocalServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.nero.course.model.StudentHomework",
	service = AopService.class
)
public class StudentHomeworkLocalServiceImpl
	extends StudentHomeworkLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(HomeworkLocalServiceImpl.class);

	public StudentHomework getOrCreateStudentHomework(long homeworkId, long studentId) {

		try {
			List<StudentHomework> studentHomeworks = studentHomeworkPersistence.findBystudentId_homeworkId(studentId, homeworkId);
			if (studentHomeworks != null && !studentHomeworks.isEmpty()) {
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
			if (studentHomeworks != null && !studentHomeworks.isEmpty()) {
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

	public boolean setHomeworkSent(long studentId, long homeworkId, long fileEntryId) {

		try {
			StudentHomework studentHomework = getOrCreateStudentHomework(homeworkId, studentId);
			if (!studentHomework.getIsSent()) {
				studentHomework.setIsDone(true);
				studentHomework.setIsSent(true);
			}
			studentHomework.setSentDate(new Date());
			studentHomework.setSentFileId(fileEntryId);
			studentHomework.setIsCorrected(false);
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
			if (studentHomeworkList != null && !studentHomeworkList.isEmpty()) {
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
			return studentHomeworkList != null && !studentHomeworkList.isEmpty() && studentHomeworkList.get(0).getIsDone();
		} catch (Exception e) {
			logger.debug(e);
		}
		return false;
	}

	public List<User> getStudentsHavingDoneHomework(long homeworkId) {
		List<User> students = new ArrayList<>();

		try {
			List<StudentHomework> studentHomeworkList = studentHomeworkPersistence.findByhomeworkId(homeworkId);
			if (studentHomeworkList != null && !studentHomeworkList.isEmpty()) {
				for (StudentHomework studentHomework : studentHomeworkList) {
					if (studentHomework.getIsDone()) {
						students.add(UserLocalServiceUtil.getUser(studentHomework.getStudentId()));
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching students having done homeworkId " + homeworkId, e);
		}
		return students;
	}

	public JSONArray getSentFiles(long homeworkId) {
		JSONArray sentFiles = new JSONArray();
		try {
			List<StudentHomework> studentHomeworkList = studentHomeworkPersistence.findByhomeworkId(homeworkId);
			if (studentHomeworkList != null && !studentHomeworkList.isEmpty()) {
				for (StudentHomework studentHomework : studentHomeworkList) {
					if (studentHomework.getIsSent()) {
						JSONObject jsonFile = studentHomework.convertToJSON();
						sentFiles.put(jsonFile);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching students having done homeworkId " + homeworkId, e);
		}
		return sentFiles;
	}

	public JSONObject getStudentSentFile(long studentId, long homeworkId) {
		try {
			StudentHomework studentHomework = getStudentHomework(homeworkId, studentId);
			return studentHomework.convertToJSON();
		} catch (Exception e) {
			logger.error("Error fetching sent file for studentId " + studentId + " and homeworkId " + homeworkId, e);
		}
		return null;
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