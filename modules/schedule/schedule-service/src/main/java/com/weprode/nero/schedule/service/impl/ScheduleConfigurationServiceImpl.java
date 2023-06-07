package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.HolidayLocalServiceUtil;
import com.weprode.nero.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.nero.schedule.service.base.ScheduleConfigurationServiceBaseImpl;
import com.weprode.nero.schedule.utils.JSONProxy;
import com.weprode.nero.user.service.UserRelationshipLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component(
	property = {
		"json.web.service.context.name=schedule",
		"json.web.service.context.path=ScheduleConfiguration"
	},
	service = AopService.class
)
public class ScheduleConfigurationServiceImpl extends ScheduleConfigurationServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(ScheduleConfigurationServiceImpl.class);

	// Used to initialize schedule
	@JSONWebService(value = "get-configuration", method = "GET")
	public JSONObject getConfiguration(long schoolId, long childId) {
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

		try {
			User targetUser = user;
			if (RoleUtilsLocalServiceUtil.isParent(user)) {
				List<User> children = UserRelationshipLocalServiceUtil.getChildren(user.getUserId());
				targetUser = children.get(0);
				result.put(JSONConstants.CHILDREN, JSONProxy.convertUsersToJson(children));
			}

			result.put(JSONConstants.CONFIGURATION, ScheduleConfigurationLocalServiceUtil.convertAsJson());

			// Get week start and end date
			// Check if they are sessions between today and the end of the current week
			// If yes, keep current dates
			// If no, check sessions for next week
			// -> if there are sessions, keep the next week ; else loop again in the future (max 3 times)

			Date startDate = new Date();
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(startDate);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			cal.set(Calendar.HOUR_OF_DAY, 7);
			cal.set(Calendar.MINUTE, 0);
			Date endDate = cal.getTime();
			DateFormat sdf = new SimpleDateFormat(JSONConstants.FULL_FRENCH_FORMAT);

			logger.info("CDT Configuration : fetch sessions between " + sdf.format(startDate) + " and " + sdf.format(endDate));
			List<CDTSession> sessions = new ArrayList<>();
			if (RoleUtilsLocalServiceUtil.isStudent(targetUser)) {
				sessions = CDTSessionLocalServiceUtil.getStudentSessions(targetUser.getUserId(), startDate, endDate);
			} else if (RoleUtilsLocalServiceUtil.isTeacher(targetUser)) {
				sessions = CDTSessionLocalServiceUtil.getTeacherSessions(targetUser.getUserId(), startDate, endDate);
			}

			if (sessions != null && !sessions.isEmpty()) {
				// Set start date at current week's monday
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				startDate = cal.getTime();
				SimpleDateFormat englishSdf = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
				result.put(JSONConstants.START_DATE, englishSdf.format(startDate));
				result.put(JSONConstants.END_DATE, englishSdf.format(endDate));
			} else {
				// Loop
				int idx = 0;
				while (idx < 3) {
					cal.add(Calendar.DATE, 1);
					startDate = cal.getTime();
					cal.add(Calendar.DATE, 6);
					endDate = cal.getTime();
					if (RoleUtilsLocalServiceUtil.isStudent(targetUser)) {
						sessions = CDTSessionLocalServiceUtil.getStudentSessions(targetUser.getUserId(), startDate, endDate);
					} else if (RoleUtilsLocalServiceUtil.isTeacher(targetUser)) {
						sessions = CDTSessionLocalServiceUtil.getTeacherSessions(targetUser.getUserId(), startDate, endDate);
					}

					if (sessions != null && !sessions.isEmpty()) {
						result.put(JSONConstants.START_DATE, new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).format(startDate));
						result.put(JSONConstants.END_DATE, new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).format(endDate));
						result.put(JSONConstants.SUCCESS, true);
						return result;
					}
					idx++;
				}
			}
		} catch (Exception e) {
			logger.error("Error when get cdt school config", e);
		}

		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "get-schedule-configuration", method = "GET")
	public JSONObject getScheduleConfiguration() {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		// Authorized for global admins only
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		JSONObject configuration = ScheduleConfigurationLocalServiceUtil.convertAsJson();
		configuration.put(JSONConstants.HOLIDAYS, HolidayLocalServiceUtil.getHolidaysAsJson());

		result.put(JSONConstants.CONFIGURATION, configuration);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "save-schedule-configuration", method = "POST")
	public JSONObject saveScheduleConfiguration(String startDateStr, String semesterDateStr, String endDateStr, String holidays, String h1Weeks, String h2Weeks) {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		// Authorized for global admins only
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT);
			Date schoolYearStartDate = sdf.parse(startDateStr);
			Date schoolYearEndDate = sdf.parse(endDateStr);
			Date semesterDate = sdf.parse(semesterDateStr);
			ScheduleConfigurationLocalServiceUtil.setScheduleConfiguration(schoolYearStartDate, semesterDate, schoolYearEndDate, h1Weeks, h2Weeks);

			JSONArray jsonHolidays = new JSONArray(holidays);
			HolidayLocalServiceUtil.saveHolidays(jsonHolidays);

			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error when saving global schedule configuration", e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

}