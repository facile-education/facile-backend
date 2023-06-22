package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.service.HolidayLocalServiceUtil;
import com.weprode.nero.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.nero.schedule.service.base.ScheduleConfigurationServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	@JSONWebService(value = "get-schedule-configuration", method = "GET")
	public JSONObject getScheduleConfiguration() {	// Used to calendar config
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

		result.put(JSONConstants.CONFIGURATION, ScheduleConfigurationLocalServiceUtil.getCalendarConfiguration());

		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "get-global-configuration", method = "GET")
	public JSONObject getGlobalConfiguration() {

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

		JSONObject configuration = ScheduleConfigurationLocalServiceUtil.getScheduleConfiguration();
		configuration.put(JSONConstants.HOLIDAYS, HolidayLocalServiceUtil.getHolidaysAsJson());

		result.put(JSONConstants.CONFIGURATION, configuration);
		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "save-global-configuration", method = "POST")
	public JSONObject saveGlobalConfiguration(String startDateStr, String semesterDateStr, String endDateStr, String holidays, String h1Weeks, String h2Weeks) {

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