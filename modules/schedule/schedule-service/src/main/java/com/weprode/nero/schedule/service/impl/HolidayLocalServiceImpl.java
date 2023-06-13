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

package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.schedule.model.Holiday;
import com.weprode.nero.schedule.service.base.HolidayLocalServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.Holiday",
	service = AopService.class
)
public class HolidayLocalServiceImpl extends HolidayLocalServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(HolidayLocalServiceImpl.class);

	// First day is the first day of vacation period
	// Second day is the first day of work after the vacation period

	public void saveHolidays(JSONArray jsonHolidays) {

		logger.info("About to save holidays");
		// First delete all existing holidays
		holidayPersistence.removeAll();

		// Rec-create all holidays
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT);
			for (int i = 0; i < jsonHolidays.length(); i++) {
				JSONObject jsonHoliday = jsonHolidays.getJSONObject(i);
				Date holidayStartDate = sdf.parse(jsonHoliday.getString(JSONConstants.START_DATE));
				Date holidayEndDate = sdf.parse(jsonHoliday.getString(JSONConstants.END_DATE));
				Holiday holiday = holidayPersistence.create(counterLocalService.increment());
				holiday.setStartDate(holidayStartDate);
				holiday.setEndDate(holidayEndDate);
				holiday.setName(jsonHoliday.getString(JSONConstants.NAME));
				holidayPersistence.update(holiday);
			}
		} catch (Exception e) {
			logger.error("Error saving holidays", e);
		}
		logger.info("Saved holidays");
	}

	public JSONArray getHolidaysAsJson() {

		JSONArray jsonHolidays = new JSONArray();
		List<Holiday> holidays = holidayPersistence.findAll();
		if (holidays != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT);
			for (Holiday holiday : holidays) {
				JSONObject jsonHoliday = new JSONObject();
				jsonHoliday.put(JSONConstants.ID, holiday.getHolidayId());
				jsonHoliday.put(JSONConstants.NAME, holiday.getName());
				jsonHoliday.put(JSONConstants.START_DATE, sdf.format(holiday.getStartDate()));
				jsonHoliday.put(JSONConstants.END_DATE, sdf.format(holiday.getEndDate()));
				jsonHolidays.put(jsonHoliday);
			}
		}
		return jsonHolidays;
	}

	// Used by synchronization
	public Map<Date, Date> getHolidays() {

		Map<Date, Date> map = new HashMap<>();
		List<Holiday> holidays = holidayPersistence.findAll();
		if (holidays != null) {
			for (Holiday holiday : holidays) {
				map.put(holiday.getStartDate(), holiday.getEndDate());
			}
		}
		return map;
	}
}