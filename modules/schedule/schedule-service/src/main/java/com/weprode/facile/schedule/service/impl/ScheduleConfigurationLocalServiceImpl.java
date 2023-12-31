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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.schedule.model.ScheduleConfiguration;
import com.weprode.facile.schedule.service.base.ScheduleConfigurationLocalServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.facile.schedule.model.ScheduleConfiguration",
	service = AopService.class
)
public class ScheduleConfigurationLocalServiceImpl
	extends ScheduleConfigurationLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(ScheduleConfigurationLocalServiceImpl.class);

	public ScheduleConfiguration setScheduleConfiguration(Date schoolYearStartDate, Date semesterDate, Date schoolYearEndDate, String h1Weeks, String h2Weeks) {

		ScheduleConfiguration schoolConfig = null;
		try {
			List<ScheduleConfiguration> configs = scheduleConfigurationPersistence.findAll();
			if (configs != null) {
				schoolConfig = configs.get(0);
			}
		} catch (Exception e) {
			logger.error("Error fetching global schedule configuration", e);
		}

		if (schoolConfig == null) {
			try {
				schoolConfig = scheduleConfigurationPersistence.create(counterLocalService.increment());
			} catch (Exception e) {
				return null;
			}
		}

		try {
			schoolConfig.setSchoolYearStartDate(schoolYearStartDate);
			schoolConfig.setSchoolYearSemesterDate(semesterDate);
			schoolConfig.setSchoolYearEndDate(schoolYearEndDate);
			schoolConfig.setH1Weeks(h1Weeks);
			schoolConfig.setH2Weeks(h2Weeks);
			schoolConfig = scheduleConfigurationLocalService.updateScheduleConfiguration(schoolConfig);
		} catch (Exception e) {
			return null;
		}

		return schoolConfig;
	}

	public Date getSchoolYearStartDate() {

		try {
			return scheduleConfigurationPersistence.findAll().get(0).getSchoolYearStartDate();
		} catch (Exception e) {
			logger.error("Error fetching school year start date", e);
		}
		return null;
	}

	public Date getSchoolYearSemesterDate() {

		try {
			return scheduleConfigurationPersistence.findAll().get(0).getSchoolYearSemesterDate();
		} catch (Exception e) {
			logger.error("Error fetching school year semester date", e);
		}
		return null;
	}

	public Date getSchoolYearEndDate() {

		try {
			return scheduleConfigurationPersistence.findAll().get(0).getSchoolYearEndDate();
		} catch (Exception e) {
			logger.error("Error fetching school year end date", e);
		}
		return null;
	}

	public Date getProjectStartDate() {

		try {
			return scheduleConfigurationPersistence.findAll().get(0).getProjectStartDate();
		} catch (Exception e) {
			logger.error("Error fetching project start date", e);
		}
		return null;
	}

	public List<Integer> getH1Weeks() {

		List<Integer> weeksList = new ArrayList<>();
		try {
			String h1Weeks = scheduleConfigurationPersistence.findAll().get(0).getH1Weeks();
			String[] h1WeeksTab = h1Weeks.split(",");
			for (String week : h1WeeksTab) {
				weeksList.add(Integer.parseInt(week));
			}
		} catch (Exception e) {
			logger.error("Error fetching H1 weeks", e);
		}
		return weeksList;
	}

	public List<Integer> getH2Weeks() {

		List<Integer> weeksList = new ArrayList<>();
		try {
			String h1Weeks = scheduleConfigurationPersistence.findAll().get(0).getH2Weeks();
			String[] h1WeeksTab = h1Weeks.split(",");
			for (String week : h1WeeksTab) {
				weeksList.add(Integer.parseInt(week));
			}
		} catch (Exception e) {
			logger.error("Error fetching H1 weeks", e);
		}
		return weeksList;
	}

	public JSONObject getCalendarConfiguration () {
		JSONObject jsonConfig = new JSONObject();

		try {
			ScheduleConfiguration config = scheduleConfigurationPersistence.findAll().get(0);
			SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT);

			jsonConfig.put(JSONConstants.SCHOOL_YEAR_START_DATE, sdf.format(config.getSchoolYearStartDate()));
			jsonConfig.put(JSONConstants.SCHOOL_YEAR_END_DATE, sdf.format(config.getSchoolYearEndDate()));

			int[] schoolDay = {1, 2, 3, 4, 5}; // TODO: To make customizable
			JSONArray jsonSchoolDay = new JSONArray(schoolDay);
			jsonConfig.put(JSONConstants.SCHOOL_DAYS, jsonSchoolDay);

			jsonConfig.put(JSONConstants.START_DAY_TIME, "07:00"); // TODO: To make customizable depending of user min P1 and max P12
			jsonConfig.put(JSONConstants.END_DAY_TIME, "18:00");

		} catch (Exception e) {
			logger.error("Error fetching school year end date", e);
		}

		return jsonConfig;
	}

	public JSONObject getScheduleConfiguration() {
		JSONObject jsonConfig = new JSONObject();

		try {
			ScheduleConfiguration config = scheduleConfigurationPersistence.findAll().get(0);
			SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT);
			jsonConfig.put(JSONConstants.START_DATE_PROJECT, sdf.format(config.getProjectStartDate()));
			jsonConfig.put(JSONConstants.SCHOOL_YEAR_START_DATE, sdf.format(config.getSchoolYearStartDate()));
			jsonConfig.put(JSONConstants.SCHOOL_YEAR_SEMESTER_DATE, sdf.format(config.getSchoolYearSemesterDate()));
			jsonConfig.put(JSONConstants.SCHOOL_YEAR_END_DATE, sdf.format(config.getSchoolYearEndDate()));
			jsonConfig.put(JSONConstants.H1_WEEKS, config.getH1Weeks());
			jsonConfig.put(JSONConstants.H2_WEEKS, config.getH2Weeks());
		} catch (Exception e) {
			logger.error("Error fetching school year end date", e);
		}

		return jsonConfig;
	}

	// Kept for souvenir
	private Date getDefaultSchoolYearEndDate() {
		Calendar cal = Calendar.getInstance();

		Date today = new Date();
		cal.setTime(today);
		if (cal.get(Calendar.MONTH) >= Calendar.AUGUST) {
			cal.set(cal.get(Calendar.YEAR) + 1, Calendar.JULY, 4);
		} else {
			cal.set(cal.get(Calendar.YEAR), Calendar.JULY, 4);
		}
		logger.info("Calculated default school year end date to " + new SimpleDateFormat(JSONConstants.FRENCH_FORMAT).format(cal.getTime()));

		return cal.getTime();
	}
}