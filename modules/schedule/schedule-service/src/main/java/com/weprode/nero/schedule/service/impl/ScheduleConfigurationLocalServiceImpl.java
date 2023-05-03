package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import org.json.JSONArray;

import org.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.schedule.model.ScheduleConfiguration;
import com.weprode.nero.schedule.model.WeeklySchedule;
import com.weprode.nero.schedule.service.WeeklyScheduleLocalServiceUtil;
import com.weprode.nero.schedule.service.base.ScheduleConfigurationLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.ScheduleConfiguration",
	service = AopService.class
)
public class ScheduleConfigurationLocalServiceImpl
	extends ScheduleConfigurationLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(ScheduleConfigurationLocalServiceImpl.class);

	public ScheduleConfiguration createOrSetSchoolConfiguration(long schoolId, String startDayTime, String endDayTime, Date startDateSchool, Date endDateSchool) {
		ScheduleConfiguration schoolConfig = null;

		try {
			schoolConfig = scheduleConfigurationPersistence.findByPrimaryKey(schoolId);
		} catch (Exception e) {
			logger.debug(e);
		}

		if (schoolConfig == null) {
			try {
				schoolConfig = scheduleConfigurationPersistence.create(counterLocalService.increment());
			} catch (Exception e) {
				return null;
			}
		}

		try {
			schoolConfig.setSchoolId(schoolId);
			schoolConfig.setStartDayTime(startDayTime);
			schoolConfig.setEndDayTime(endDayTime);
			schoolConfig.setStartSessionsDate(startDateSchool);
			schoolConfig.setEndSessionsDate(endDateSchool);
			schoolConfig = scheduleConfigurationLocalService.updateScheduleConfiguration(schoolConfig);
		} catch (Exception e) {
			return null;
		}

		return schoolConfig;
	}

	public ScheduleConfiguration createDefaultSchoolConfiguration(long schoolId) {
		ScheduleConfiguration defaultConfiguration = null;

		try {
			defaultConfiguration = scheduleConfigurationPersistence.create(counterLocalService.increment());
			defaultConfiguration.setSchoolId(schoolId);
			defaultConfiguration.setStartDayTime("08:00");
			defaultConfiguration.setEndDayTime("18:00");

			Calendar startCal = Calendar.getInstance();
			Calendar endCal = Calendar.getInstance();

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int monthNb = cal.get(Calendar.MONTH);
			if (monthNb <= 7) {
				startCal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
				endCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			} else {
				startCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
				endCal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
			}
			startCal.set(Calendar.MONTH, Calendar.SEPTEMBER);
			startCal.set(Calendar.DATE, 1);
			endCal.set(Calendar.MONTH, Calendar.JULY);
			endCal.set(Calendar.DATE, 8);
			defaultConfiguration.setStartSessionsDate(startCal.getTime());
			defaultConfiguration.setEndSessionsDate(endCal.getTime());
			defaultConfiguration = scheduleConfigurationPersistence.update(defaultConfiguration);
		} catch (Exception e) {
			logger.debug(e);
		}

		return defaultConfiguration;
	}

	public ScheduleConfiguration getSchoolConfiguration(long schoolId) {
		ScheduleConfiguration schoolConfig = null;

		try {
			schoolConfig = scheduleConfigurationPersistence.findByPrimaryKey(schoolId);
		} catch (Exception e) {
			logger.error(e);
		}
		if (schoolConfig == null) {
			schoolConfig = createDefaultSchoolConfiguration(schoolId);
		}

		return schoolConfig;
	}

	public JSONObject getSchoolConfigurationAsJson(long schoolId) {
		JSONObject jsonConfig = new JSONObject();

		ScheduleConfiguration schoolConfig = getSchoolConfiguration(schoolId);
		if (schoolConfig != null) {
			List<WeeklySchedule> weeklySchedules = WeeklyScheduleLocalServiceUtil.getWeeklyScheduleBySchoolId(schoolId);

			SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT);
			jsonConfig.put(JSONConstants.START_DAY_TIME, schoolConfig.getStartDayTime());
			jsonConfig.put(JSONConstants.END_DAY_TIME, schoolConfig.getEndDayTime());
			// TODO: handle correctly 'startDateProject' (portal-ext property?)
			jsonConfig.put(JSONConstants.START_DATE_PROJECT, sdf.format(schoolConfig.getStartSessionsDate()));
			jsonConfig.put(JSONConstants.START_DATE_SCHOOL, sdf.format(schoolConfig.getStartSessionsDate()));
			jsonConfig.put(JSONConstants.END_DATE_SCHOOL, sdf.format(schoolConfig.getEndSessionsDate()));

			JSONArray dayArray = new JSONArray();
			for (WeeklySchedule aDay : weeklySchedules) {
				dayArray.put(aDay.getDayId());
			}
			jsonConfig.put(JSONConstants.SCHOOL_DAYS, dayArray);

		}

		return jsonConfig;
	}

	/**
	 * Calculates a default current school year end date (4th of july)
	 * @return the default end date
	 */
	public Date getDefaultSchoolYearEndDate() {
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