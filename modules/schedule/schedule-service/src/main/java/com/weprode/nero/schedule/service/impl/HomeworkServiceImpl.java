package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.Homework;
import com.weprode.nero.schedule.service.HomeworkLocalServiceUtil;
import com.weprode.nero.schedule.service.StudentHomeworkLocalServiceUtil;
import com.weprode.nero.schedule.service.base.HomeworkServiceBaseImpl;

import com.weprode.nero.schedule.utils.JSONProxy;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
	property = {
		"json.web.service.context.name=schedule",
		"json.web.service.context.path=Homework"
	},
	service = AopService.class
)
public class HomeworkServiceImpl extends HomeworkServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(HomeworkServiceImpl.class);

	@JSONWebService(value = "get-homeworks", method = "GET")
	public JSONObject getHomeworks(long studentId, String minDateStr) throws SystemException, PortalException {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		User currentUser;
		try {
			currentUser = getGuestOrUser();
			if (currentUser.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		Date minDate;
		try {
			minDate = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(minDateStr);
		} catch (Exception e) {
			logger.error("Error when parsing minDate " + minDateStr + " while retrieving homeworks to do", e);
			minDate = new Date();
		}

		JSONArray homeworks = JSONFactoryUtil.createJSONArray();

		List<Homework> homeworkList = new ArrayList<>();
		if (RoleUtilsLocalServiceUtil.isTeacher(currentUser)) {
			homeworkList = HomeworkLocalServiceUtil.getTeacherHomeworks(currentUser, minDate, 0);
		}
		else if (RoleUtilsLocalServiceUtil.isStudent(currentUser)) {
			homeworkList = HomeworkLocalServiceUtil.getStudentHomeworks(currentUser, minDate);
		}
		else if (RoleUtilsLocalServiceUtil.isParent(currentUser)) {
			User studentUser = UserLocalServiceUtil.getUser(studentId);
			homeworkList = HomeworkLocalServiceUtil.getStudentHomeworks(studentUser, minDate);
		}

		// Convert to JSON
		for (Homework homework : homeworkList) {
			JSONObject homeworkJson = homework.convertToJSON(currentUser);
			homeworks.put(homeworkJson);
		}
		result.put(JSONConstants.GROUPS, homeworks);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "set-homework-done", method = "GET")
	public JSONObject setHomeworkDone(long homeworkId, boolean isDone) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		result.put(JSONConstants.SUCCESS, StudentHomeworkLocalServiceUtil.setHomeworkDone(homeworkId, user.getUserId(), isDone));

		return result;
	}
}