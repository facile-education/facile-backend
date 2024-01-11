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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.contact.service.ContactLocalServiceUtil;
import com.weprode.facile.course.exception.UnauthorizedUrlException;
import com.weprode.facile.course.model.Homework;
import com.weprode.facile.course.service.ContentBlockLocalServiceUtil;
import com.weprode.facile.course.service.HomeworkLocalServiceUtil;
import com.weprode.facile.course.service.StudentHomeworkLocalServiceUtil;
import com.weprode.facile.course.service.base.HomeworkServiceBaseImpl;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.facile.user.service.UserRelationshipLocalServiceUtil;
import com.weprode.facile.user.service.UserUtilsLocalServiceUtil;
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
	property = {
		"json.web.service.context.name=course",
		"json.web.service.context.path=Homework"
	},
	service = AopService.class
)
public class HomeworkServiceImpl extends HomeworkServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(HomeworkServiceImpl.class);

	@JSONWebService(value = "get-student-homeworks", method = "GET")
	public JSONObject getStudentHomeworks(long studentId, String minDateStr, String maxDateStr, boolean undoneOnly) throws SystemException, PortalException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets homeworks for student " + studentId);
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}
		if (RoleUtilsLocalServiceUtil.isParent(user) && !UserRelationshipLocalServiceUtil.isChild(user.getUserId(), studentId)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets homeworks for student " + studentId);
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		JSONArray homeworks = new JSONArray();

		User targetUser = user;
		if (RoleUtilsLocalServiceUtil.isParent(user)) {
			targetUser = UserLocalServiceUtil.getUser(studentId);
		}

		List<Homework> homeworkList = new ArrayList<>();
		try {
			//logger.info("User " + user.getUserId() + " fetches homeworks from " + minDateStr + " to " + maxDateStr + ((studentId != 0) ? " for student " + studentId : ""));
			Date minDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(minDateStr);
			Date maxDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(maxDateStr);
			homeworkList = HomeworkLocalServiceUtil.getStudentHomeworks(targetUser.getUserId(), minDate, maxDate, undoneOnly);
		} catch (Exception e) {
			logger.error("Error fetching previous homeworks for student " + studentId);
		}

		// Convert to JSON
		for (Homework homework : homeworkList) {
			JSONObject homeworkJson = homework.convertToJSON(targetUser, true);
			homeworks.put(homeworkJson);
		}

		result.put(JSONConstants.HOMEWORKS, homeworks);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "get-teacher-homeworks-to-correct", method = "GET")
	public JSONObject getTeacherHomeworksToCorrect(long courseId) throws SystemException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets homeworks to correct for course " + courseId);
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		JSONArray homeworks = new JSONArray();
		List<Homework> homeworkList = HomeworkLocalServiceUtil.getTeacherHomeworksToCorrect(user.getUserId(), courseId);
		for (Homework homework : homeworkList) {
			JSONObject homeworkJson = homework.convertToJSON(user, true);
			homeworks.put(homeworkJson);
		}

		result.put(JSONConstants.HOMEWORKS, homeworks);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "count-undone-homeworks", method = "GET")
	public JSONObject countUndoneHomeworks(long studentId, String minDateStr, String maxDateStr) throws SystemException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " counts undone homeworks for student  " + studentId);
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}
		if (RoleUtilsLocalServiceUtil.isParent(user) && !UserRelationshipLocalServiceUtil.isChild(user.getUserId(), studentId)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " counts undone homeworks for student  " + studentId);
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		int nbUndoneHomeworks = 0;
		if (minDateStr.isBlank() || maxDateStr.isBlank()) {
			nbUndoneHomeworks = HomeworkLocalServiceUtil.countUndoneHomeworks(studentId);
		} else {
			try {
				Date minDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(minDateStr);
				Date maxDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(maxDateStr);
				nbUndoneHomeworks = HomeworkLocalServiceUtil.countUndoneHomeworks(studentId, minDate, maxDate);
			} catch (Exception e) {
				logger.error("Error fetching previous homeworks for student " + studentId);
			}
		}
		result.put(JSONConstants.NB_UNDONE_HOMEWORKS, nbUndoneHomeworks);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "count-homeworks-to-correct", method = "GET")
	public JSONObject countHomeworksToCorrect() throws SystemException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " counts homeworks to correct");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		JSONArray nbHomeworksToCorrect = HomeworkLocalServiceUtil.countHomeworksToCorrect(user.getUserId());
		result.put(JSONConstants.NB_HOMEWORKS_TO_CORRECT, nbHomeworksToCorrect);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "set-homework-done", method = "GET")
	public JSONObject setHomeworkDone(long homeworkId, boolean isDone) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isStudent(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " sets homework done for " + homeworkId);
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		result.put(JSONConstants.SUCCESS, StudentHomeworkLocalServiceUtil.setHomeworkDone(homeworkId, user.getUserId(), isDone));

		return result;
	}

	@JSONWebService(value = "get-students-done-status", method = "GET")
	public JSONObject getHomeworkDoneStatus (long homeworkId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets homework done status for " + homeworkId);
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		List<User> homeworkStudents = StudentHomeworkLocalServiceUtil.getHomeworkStudents(homeworkId);

		JSONArray jsonHomeworkStudents = new JSONArray();
		for (User student : homeworkStudents) {
			JSONObject jsonStudent = UserUtilsLocalServiceUtil.convertUserToJson(student);
			if (StudentHomeworkLocalServiceUtil.hasStudentDoneHomework(student.getUserId(), homeworkId)) {
				jsonStudent.put(JSONConstants.IS_DONE, true);
			}
			jsonHomeworkStudents.put(jsonStudent);
		}

		result.put(JSONConstants.SELECTED_STUDENTS, jsonHomeworkStudents);
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}

	@JSONWebService(value = "get-work-load", method = "POST")
	public JSONObject getWorkLoad(long courseId, String students, String startDate, String endDate) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets workload for course " + courseId);
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		logger.info("Teacher " + user.getFullName() + " displays work load for " + (courseId != 0 ? "course " + courseId : "n students"));
		try {
			Date minDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(startDate);
			Date maxDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(endDate);

			List<Long> studentIds = new ArrayList<>();
			if (courseId != 0) {
				// Get all course students
				Role studentRole = RoleUtilsLocalServiceUtil.getStudentRole();
				Group courseGroup = GroupLocalServiceUtil.getGroup(courseId);
				List<User> courseStudents = ContactLocalServiceUtil.getListMembers(user, studentRole.getRoleId(), courseGroup.getClassPK());
				for (User student : courseStudents) {
					studentIds.add(student.getUserId());
				}
			} else {
				// Workload for a subset of students
				JSONArray jsonStudents = new JSONArray(students);
				for (int i = 0 ; i < jsonStudents.length() ; i++) {
					JSONObject jsonStudent = jsonStudents.getJSONObject(i);
					studentIds.add(jsonStudent.getLong(JSONConstants.USER_ID));
				}
			}
			List<Homework> homeworkList = HomeworkLocalServiceUtil.getStudentsHomeworks(studentIds, minDate, maxDate);

			// Convert to JSON
			JSONArray homeworks = new JSONArray();
			for (Homework homework : homeworkList) {
                JSONObject homeworkJson = homework.convertToJSON(user, false, false);
				homeworks.put(homeworkJson);
			}

			result.put(JSONConstants.HOMEWORKS, homeworks);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error fetching workload", e);
		}

		return result;
	}

	@JSONWebService(value = "create-homework", method = "POST")
	public JSONObject createHomework(long courseId, String title, long sourceSessionId, long targetSessionId, String targetDateStr, int homeworkType, int estimatedTime, String students, String blocks, String publicationDateStr, boolean isDraft) throws PortalException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " creates homework for course " + courseId);
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		Date targetDate;
		Date publicationDate;
		try {
			targetDate = targetSessionId == 0 ?
					new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(targetDateStr) :
					CDTSessionLocalServiceUtil.getCDTSession(targetSessionId).getStart();
			publicationDate = publicationDateStr.equals("") ? new Date() : new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(publicationDateStr);
		} catch (Exception e) {
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}
		List<Long> studentIds = new ArrayList<>();
		if (!students.equals("")) {
			JSONArray jsonStudents = new JSONArray(students);
			for (int i = 0 ; i < jsonStudents.length() ; i++) {
				JSONObject jsonStudent = jsonStudents.getJSONObject(i);
				studentIds.add(jsonStudent.getLong(JSONConstants.USER_ID));
			}
		}
		Homework homework = HomeworkLocalServiceUtil.createHomework(user, title, sourceSessionId, targetSessionId, courseId, targetDate, homeworkType, estimatedTime, studentIds, publicationDate, isDraft);

		try {
			// Create blocks
			JSONArray jsonBlocks = new JSONArray(blocks);
			for (int i = 0 ; i < jsonBlocks.length() ; i++) {
				JSONObject jsonBlock = jsonBlocks.getJSONObject(i);
				ContentBlockLocalServiceUtil.addBlock(user.getUserId(), homework.getHomeworkId(),
						jsonBlock.getInt(JSONConstants.CONTENT_TYPE),
						jsonBlock.getString(JSONConstants.CONTENT_NAME),
						JSONConstants.getStringValue(jsonBlock, JSONConstants.CONTENT_VALUE, StringPool.BLANK),
						JSONConstants.getLongValue(jsonBlock,JSONConstants.FILE_ID,0));
			}

			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error creating homework", e);
			throw new PortalException(); // To cancel the previous content creation
		}
		return result;
	}

	@JSONWebService(value = "update-homework", method = "POST")
	public JSONObject updateHomework(long homeworkId, String title, long targetSessionId, String targetDateStr, int estimatedTime, String students, String blocks, String publicationDateStr, boolean isDraft) throws PortalException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " updates a homework");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}


		Date targetDate;
		Date publicationDate;
		try {
			targetDate = targetSessionId == 0 ?
					new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(targetDateStr) :
					CDTSessionLocalServiceUtil.getCDTSession(targetSessionId).getStart();
			publicationDate = publicationDateStr.equals("") ? new Date() : new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(publicationDateStr);
		} catch (Exception e) {
			result.put(JSONConstants.SUCCESS, false);
			return result;
		}

			List<Long> studentIds = new ArrayList<>();
			if (!students.equals("")) {
				JSONArray jsonStudents = new JSONArray(students);
				for (int i = 0 ; i < jsonStudents.length() ; i++) {
					JSONObject jsonStudent = jsonStudents.getJSONObject(i);
					studentIds.add(jsonStudent.getLong(JSONConstants.USER_ID));
				}
			}
			Homework homework = HomeworkLocalServiceUtil.updateHomework(homeworkId, title, targetSessionId, targetDate, estimatedTime, studentIds, publicationDate, isDraft);

			// Delete existing blocks
			ContentBlockLocalServiceUtil.deleteBlocksByItemId(homeworkId);

			try {
			// Re-create blocks
			JSONArray jsonBlocks = new JSONArray(blocks);
			for (int i = 0 ; i < jsonBlocks.length() ; i++) {
				JSONObject jsonBlock = jsonBlocks.getJSONObject(i);
				ContentBlockLocalServiceUtil.addBlock(user.getUserId(), homework.getHomeworkId(),
						jsonBlock.getInt(JSONConstants.CONTENT_TYPE),
						jsonBlock.getString(JSONConstants.CONTENT_NAME),
						JSONConstants.getStringValue(jsonBlock, JSONConstants.CONTENT_VALUE, StringPool.BLANK),
						JSONConstants.getLongValue(jsonBlock,JSONConstants.FILE_ID,0));
			}

			result.put(JSONConstants.SUCCESS, true);
		} catch (UnauthorizedUrlException | IOException e) {
			logger.error("Error creating homework", e);
			throw new PortalException(); // To cancel the previous content creation
		}
		return result;
	}

	@JSONWebService(value = "delete-homework", method = "POST")
	public JSONObject deleteHomework(long homeworkId) throws SystemException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " deletes homework");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			logger.info("User " + user.getFullName() + " deletes homework " + homeworkId);
			HomeworkLocalServiceUtil.deleteHomeworkAndDependencies(homeworkId);
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error creating homework", e);
		}
		return result;
	}

	@JSONWebService(value = "drop-homework-file", method = "GET")
	public JSONObject dropHomeworkFile(long homeworkId, long fileEntryId) throws SystemException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isStudent(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " drops a homework file");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}
		if (!StudentHomeworkLocalServiceUtil.hasStudentHomework(user.getUserId(), homeworkId)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " drops a homework file");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			StudentHomeworkLocalServiceUtil.dropHomeworkFile(user.getUserId(), homeworkId, fileEntryId);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error when student " + user.getUserId() + " drops file for homeworkId " + homeworkId);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "delete-dropped-file", method = "GET")
	public JSONObject deleteDroppedFile(long homeworkId, long fileEntryId) throws SystemException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isStudent(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " deletes a dropped file for homework");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}
		// Check that the student drops one of his files
		if (!StudentHomeworkLocalServiceUtil.hasStudentSentFile(user.getUserId(), homeworkId, fileEntryId)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " deletes a dropped file for homework");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			StudentHomeworkLocalServiceUtil.deleteDroppedFile(user.getUserId(), homeworkId, fileEntryId);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error when student " + user.getUserId() + " cancels his file drop for homeworkId " + homeworkId);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}


	@JSONWebService(value = "get-homework-status", method = "GET")
	public JSONObject getHomeworkStatus(long homeworkId) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets homework status");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			// Check that the teacher owns the homework
			Homework homework = HomeworkLocalServiceUtil.getHomework(homeworkId);
			if (homework.getTeacherId() != user.getUserId()) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " deletes a dropped file for homework");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			result.put(JSONConstants.HOMEWORK_STATUS, StudentHomeworkLocalServiceUtil.getHomeworkStatus(homeworkId));
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error when teacher " + user.getUserId() + " corrects file for homeworkId " + homeworkId);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "correct-file", method = "POST")
	public JSONObject correctFile(long homeworkId, long studentId, String comment) throws SystemException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " corrects a file");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			// Check that the teacher owns the homework
			Homework homework = HomeworkLocalServiceUtil.getHomework(homeworkId);
			if (homework.getTeacherId() != user.getUserId()) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " corrects a file");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			StudentHomeworkLocalServiceUtil.correctFile(homeworkId, studentId, comment);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error when teacher " + user.getUserId() + " corrects file for homeworkId " + homeworkId);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "send-corrections", method = "GET")
	public JSONObject sendCorrections(long homeworkId) throws SystemException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " sends corrections");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			// Check that the teacher owns the homework
			Homework homework = HomeworkLocalServiceUtil.getHomework(homeworkId);
			if (homework.getTeacherId() != user.getUserId()) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " sends corrections");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			HomeworkLocalServiceUtil.sendCorrections(user.getUserId(), homeworkId);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error when student " + user.getUserId() + " drops file for homeworkId " + homeworkId);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

}