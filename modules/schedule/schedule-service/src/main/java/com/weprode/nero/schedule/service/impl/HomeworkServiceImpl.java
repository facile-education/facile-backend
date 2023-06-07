package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
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
	public JSONObject getHomeworks(long studentId, String minDate, boolean undoneOnly) throws SystemException, PortalException {
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

		Date date;
		try {
			date = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).parse(minDate);
		} catch (Exception e) {
			logger.error("Error when parsing minDate " + minDate + " while retrieving homeworks to do", e);
			date = new Date();
		}

		JSONArray homeworks = new JSONArray();

		User targetUser = currentUser;
		if (RoleUtilsLocalServiceUtil.isParent(currentUser)) {
			targetUser = UserLocalServiceUtil.getUser(studentId);
		}

		List<Homework> homeworkList = HomeworkLocalServiceUtil.getStudentHomeworks(targetUser, date);

		// Convert to JSON
		for (Homework homework : homeworkList) {
			// Filter undone only if needed
			if (!undoneOnly || StudentHomeworkLocalServiceUtil.hasStudentDoneHomework(targetUser.getUserId(), homework.getHomeworkId())) {
				JSONObject homeworkJson = homework.convertToJSON(targetUser);
				homeworks.put(homeworkJson);
			}
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
}