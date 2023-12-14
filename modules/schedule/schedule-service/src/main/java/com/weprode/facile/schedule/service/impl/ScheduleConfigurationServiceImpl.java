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

package com.weprode.facile.schedule.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.schedule.service.HolidayLocalServiceUtil;
import com.weprode.facile.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.facile.schedule.service.base.ScheduleConfigurationServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId()) ) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		JSONObject configuration = ScheduleConfigurationLocalServiceUtil.getCalendarConfiguration();
		configuration.put(JSONConstants.HOLIDAYS, HolidayLocalServiceUtil.getHolidaysAsJson());
		result.put(JSONConstants.CONFIGURATION, configuration);

		result.put(JSONConstants.SUCCESS, true);
		return result;
	}

	@JSONWebService(value = "get-global-configuration", method = "GET")
	public JSONObject getGlobalConfiguration() {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		// Authorized for global admins only
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets global configuration");
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
			if (user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		// Authorized for global admins only
		if (!RoleUtilsLocalServiceUtil.isAdministrator(user)) {
			logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " saves global schedule configuration");
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT);
			Date schoolYearStartDate = sdf.parse(startDateStr);

			Date schoolYearEndDate = sdf.parse(endDateStr);
			// Set schoolYearEndDate to 23:59:59
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(schoolYearEndDate);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			schoolYearEndDate = calendar.getTime();

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