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

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.course.model.StudentHomework;
import com.weprode.facile.course.service.HomeworkLocalServiceUtil;
import com.weprode.facile.course.service.StudentHomeworkLocalServiceUtil;
import com.weprode.facile.course.service.base.StudentHomeworkLocalServiceBaseImpl;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Cédric Lecarpentier
 */
@Component(
	property = "model.class.name=com.weprode.facile.course.model.StudentHomework",
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
				studentHomework.setIsDone(false);
				studentHomework.setIsSent(false);
				studentHomework.setIsCorrected(false);
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

	public void setHomeworkSent(long studentId, long homeworkId, long fileEntryId) {

		try {
			StudentHomework studentHomework = getOrCreateStudentHomework(homeworkId, studentId);
			studentHomework.setIsDone(true);
			studentHomework.setIsSent(true);
			studentHomework.setSentDate(new Date());
			studentHomework.setSentFileId(fileEntryId);
			studentHomework.setIsCorrected(false);
			studentHomeworkPersistence.update(studentHomework);
		} catch (Exception e) {
			logger.error("Error setting homework " + homeworkId + " sent with fileEntryId " + fileEntryId);
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

	public boolean hasStudentSentFile(long studentId, long homeworkId, long fileEntryId) {
		try {
			List<StudentHomework> studentHomeworkList = studentHomeworkPersistence.findByhomeworkId(homeworkId);
			if (studentHomeworkList != null && !studentHomeworkList.isEmpty()) {
				for (StudentHomework studentHomework : studentHomeworkList) {
					if (studentHomework.getIsSent() && studentHomework.getStudentId() == studentId && studentHomework.getSentFileId() == fileEntryId) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error fetching if student " + studentId + " has sent file " + fileEntryId + " for homeworkId " + homeworkId, e);
		}
		return false;
	}

	public JSONObject getStudentSentFile(long studentId, long homeworkId) {
		try {
			StudentHomework studentHomework = getStudentHomework(homeworkId, studentId) ;
			return studentHomework.convertToJSON();
		} catch (Exception e) {
			logger.error("Error fetching sent file for studentId " + studentId + " and homeworkId " + homeworkId, e);
		}
		return null;
	}

	public void dropHomeworkFile(long studentId, long homeworkId, long fileEntryId) throws PortalException, IOException {

		// Check if a file was previously dropped by the student -> delete it
		StudentHomework studentHomework = StudentHomeworkLocalServiceUtil.getStudentHomework(homeworkId, studentId);
		if (studentHomework != null && studentHomework.getIsSent() && studentHomework.getSentFileId() != 0) {
			FileUtilsLocalServiceUtil.deleteFile(studentId, studentHomework.getSentFileId());
			logger.info("Old dropped file deleted");
		}

		Folder homeworkDropFolder = HomeworkLocalServiceUtil.getHomeworkDropFolder(homeworkId, false);
		FileEntry copiedFile = FileUtilsLocalServiceUtil.copyFileEntry(studentId, fileEntryId, homeworkDropFolder.getFolderId(), true);
		logger.info("File is dropped");
		StudentHomeworkLocalServiceUtil.setHomeworkSent(studentId, homeworkId, copiedFile.getFileEntryId());
	}


	public void deleteDroppedFile(long studentId, long homeworkId, long fileEntryId) throws PortalException {

		StudentHomework studentHomework = StudentHomeworkLocalServiceUtil.getStudentHomework(homeworkId, studentId);
		if (studentHomework != null && studentHomework.getIsSent() && studentHomework.getSentFileId() == fileEntryId) {
			FileUtilsLocalServiceUtil.deleteFile(studentId, studentHomework.getSentFileId());
			logger.info("Dropped file deleted");
		}
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

	public int countCorrectedWorks(long homeworkId) {

		int count = 0;
		List<StudentHomework> studentHomeworks = studentHomeworkPersistence.findByhomeworkId(homeworkId);
		if (studentHomeworks != null) {
			for (StudentHomework studentHomework : studentHomeworks) {
				if (studentHomework.getIsCorrected()) {
					count++;
				}
			}
		}
		return count;
	}

	public JSONArray getHomeworkStatus(long homeworkId) {

		JSONArray jsonArray = new JSONArray();
		SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.DATE_EXCHANGE_FORMAT);

		List<StudentHomework> studentHomeworks = studentHomeworkPersistence.findByhomeworkId(homeworkId);
		if (studentHomeworks != null) {
			for (StudentHomework studentHomework : studentHomeworks) {
				try {
					JSONObject jsonStudent = new JSONObject();
					jsonStudent.put(JSONConstants.STUDENT_ID, studentHomework.getStudentId());
					User student = UserLocalServiceUtil.getUser(studentHomework.getStudentId());
					jsonStudent.put(JSONConstants.STUDENT_NAME, student.getFullName());
					jsonStudent.put(JSONConstants.IS_DONE, studentHomework.getIsDone());
					jsonStudent.put(JSONConstants.IS_SENT, studentHomework.getIsSent());
					if (studentHomework.getIsSent() && studentHomework.getSentDate() != null) {
						jsonStudent.put(JSONConstants.SENT_DATE, sdf.format(studentHomework.getSentDate()));
						jsonStudent.put(JSONConstants.SENT_FILE_ID, studentHomework.getSentFileId());
					}
					jsonStudent.put(JSONConstants.IS_CORRECTED, studentHomework.getIsCorrected());
					if (studentHomework.getIsCorrected()) {
						jsonStudent.put(JSONConstants.CORRECTION_DATE, sdf.format(studentHomework.getCorrectionDate()));
						jsonStudent.put(JSONConstants.CORRECTION, studentHomework.getComment());
					}
					jsonArray.put(jsonStudent);
				} catch (Exception e) {
					logger.error("Error processing homework correction matrix", e);
				}
			}
		}
		return jsonArray;
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