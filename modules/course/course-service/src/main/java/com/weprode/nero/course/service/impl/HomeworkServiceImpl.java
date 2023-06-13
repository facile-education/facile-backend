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
import com.weprode.nero.course.model.Homework;
import com.weprode.nero.course.service.ContentBlockLocalServiceUtil;
import com.weprode.nero.course.service.HomeworkLocalServiceUtil;
import com.weprode.nero.course.service.StudentHomeworkLocalServiceUtil;
import com.weprode.nero.course.service.base.HomeworkServiceBaseImpl;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.UserRelationshipLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

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

	@JSONWebService(value = "get-future-student-homeworks", method = "GET")
	public JSONObject getFutureStudentHomeworks(long studentId, boolean undoneOnly) throws SystemException, PortalException {
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

		List<Homework> homeworkList = HomeworkLocalServiceUtil.getFutureStudentHomeworks(targetUser, undoneOnly);

		// Convert to JSON
		for (Homework homework : homeworkList) {
			JSONObject homeworkJson = homework.convertToJSON(targetUser, true);
			homeworks.put(homeworkJson);
		}

		result.put(JSONConstants.HOMEWORKS, homeworks);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "get-previous-student-homeworks", method = "GET")
	public JSONObject getPreviousStudentHomeworks(long studentId, String maxDateStr, boolean undoneOnly) throws SystemException, PortalException {
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
			logger.info("User " + currentUser + " fetches previous homeworks until " + maxDateStr + ((studentId != 0) ? " for student " + studentId : ""));
			Date maxDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(maxDateStr);
			homeworkList = HomeworkLocalServiceUtil.getPreviousStudentHomeworks(targetUser, maxDate, undoneOnly);
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
	public JSONObject createHomework(long courseId, long sourceSessionId, long targetSessionId, String targetDate, int homeworkType, String students, String blocks) throws SystemException {
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
			Date toDate = targetSessionId == 0 ? new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(targetDate) : null;
			List<Long> studentIds = new ArrayList<>();
			if (!students.equals("")) {
				JSONArray jsonStudents = new JSONArray(students);
				for (int i = 0 ; i < jsonStudents.length() ; i++) {
					JSONObject jsonStudent = jsonStudents.getJSONObject(i);
					studentIds.add(jsonStudent.getLong(JSONConstants.USER_ID));
				}
			}
			Homework homework = HomeworkLocalServiceUtil.createHomework(user, sourceSessionId, targetSessionId, courseId, toDate, homeworkType, studentIds);

			// Create blocks
			JSONArray jsonBlocks = new JSONArray(students);
			for (int i = 0 ; i < jsonBlocks.length() ; i++) {
				JSONObject jsonBlock = jsonBlocks.getJSONObject(i);
				ContentBlockLocalServiceUtil.addBlock(user.getUserId(), homework.getHomeworkId(),
						jsonBlock.getInt(JSONConstants.BLOCK_TYPE),
						jsonBlock.getString(JSONConstants.BLOCK_NAME),
						jsonBlock.getString(JSONConstants.BLOCK_VALUE),
						jsonBlock.getLong(JSONConstants.FILE_ENTRY_ID));
			}

			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error creating homework");
		}
		return result;
	}

	@JSONWebService(value = "update-homework", method = "POST")
	public JSONObject updateHomework(long homeworkId, long targetSessionId, String targetDate, int homeworkType, String students, String blocks) throws SystemException {
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
			Date toDate = targetSessionId == 0 ? new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(targetDate) : null;
			List<Long> studentIds = new ArrayList<>();
			if (!students.equals("")) {
				JSONArray jsonStudents = new JSONArray(students);
				for (int i = 0 ; i < jsonStudents.length() ; i++) {
					JSONObject jsonStudent = jsonStudents.getJSONObject(i);
					studentIds.add(jsonStudent.getLong(JSONConstants.USER_ID));
				}
			}
			Homework homework = HomeworkLocalServiceUtil.updateHomeworkTargets(homeworkId, targetSessionId, toDate, studentIds);

			// Create blocks
			JSONArray jsonBlocks = new JSONArray(students);
			for (int i = 0 ; i < jsonBlocks.length() ; i++) {
				JSONObject jsonBlock = jsonBlocks.getJSONObject(i);
				ContentBlockLocalServiceUtil.addBlock(user.getUserId(), homework.getHomeworkId(),
						jsonBlock.getInt(JSONConstants.BLOCK_TYPE),
						jsonBlock.getString(JSONConstants.BLOCK_NAME),
						jsonBlock.getString(JSONConstants.BLOCK_VALUE),
						jsonBlock.getLong(JSONConstants.FILE_ENTRY_ID));
			}

			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error creating homework");
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