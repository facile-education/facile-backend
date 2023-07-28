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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.course.exception.UnauthorizedUrlException;
import com.weprode.nero.course.model.Homework;
import com.weprode.nero.course.service.ContentBlockLocalServiceUtil;
import com.weprode.nero.course.service.HomeworkLocalServiceUtil;
import com.weprode.nero.course.service.StudentHomeworkLocalServiceUtil;
import com.weprode.nero.course.service.base.HomeworkServiceBaseImpl;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.user.service.UserRelationshipLocalServiceUtil;
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

		User currentUser;
		try {
			currentUser = getGuestOrUser();
			if (currentUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isStudentOrParent(currentUser)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}
		if (RoleUtilsLocalServiceUtil.isParent(currentUser) && !UserRelationshipLocalServiceUtil.isChild(currentUser.getUserId(), studentId)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		JSONArray homeworks = new JSONArray();

		User targetUser = currentUser;
		if (RoleUtilsLocalServiceUtil.isParent(currentUser)) {
			targetUser = UserLocalServiceUtil.getUser(studentId);
		}

		List<Homework> homeworkList = new ArrayList<>();
		try {
			logger.info("User " + currentUser.getUserId() + " fetches homeworks from " + minDateStr + " to " + maxDateStr + ((studentId != 0) ? " for student " + studentId : ""));
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
	public JSONObject getTeacherHomeworksToCorrect() throws SystemException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		logger.info("Teacher " + user.getFullName() + " fetches his homeworks to correct");
		JSONArray homeworks = new JSONArray();
		List<Homework> homeworkList = HomeworkLocalServiceUtil.getTeacherHomeworksToCorrect(user);
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
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isStudentOrParent(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}
		if (RoleUtilsLocalServiceUtil.isParent(user) && !UserRelationshipLocalServiceUtil.isChild(user.getUserId(), studentId)) {
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
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		int nbHomeworksToCorrect = HomeworkLocalServiceUtil.countHomeworksToCorrect(user.getUserId());
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
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isStudent(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		result.put(JSONConstants.SUCCESS, StudentHomeworkLocalServiceUtil.setHomeworkDone(homeworkId, user.getUserId(), isDone));

		return result;
	}

	@JSONWebService(value = "create-homework", method = "POST")
	public JSONObject createHomework(long courseId, String title, long sourceSessionId, long targetSessionId, String targetDateStr, int homeworkType, int estimatedTime, String students, String blocks, String publicationDateStr, boolean isDraft) throws PortalException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
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
		} catch (UnauthorizedUrlException | IOException e) {
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
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
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
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isTeacher(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
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
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isStudent(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			HomeworkLocalServiceUtil.dropHomeworkFile(user.getUserId(), homeworkId, fileEntryId);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error when student " + user.getUserId() + " drops file for homeworkId " + homeworkId);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "cancel-drop", method = "GET")
	public JSONObject cancelDrop(long homeworkId) throws SystemException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isStudent(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			HomeworkLocalServiceUtil.cancelDrop(user.getUserId(), homeworkId);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error when student " + user.getUserId() + " cancels his file drop for homeworkId " + homeworkId);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "correct-file", method = "GET")
	public JSONObject correctFile(long homeworkId, long studentId, String comment) throws SystemException {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		if (!RoleUtilsLocalServiceUtil.isStudent(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			HomeworkLocalServiceUtil.correctFile(homeworkId, studentId, comment);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error when student " + user.getUserId() + " drops file for homeworkId " + homeworkId);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

}