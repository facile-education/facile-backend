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

package com.weprode.nero.schedule.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ScheduleConfiguration}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleConfiguration
 * @generated
 */
public class ScheduleConfigurationWrapper
	extends BaseModelWrapper<ScheduleConfiguration>
	implements ModelWrapper<ScheduleConfiguration>, ScheduleConfiguration {

	public ScheduleConfigurationWrapper(
		ScheduleConfiguration scheduleConfiguration) {

		super(scheduleConfiguration);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("schoolId", getSchoolId());
		attributes.put("startDayTime", getStartDayTime());
		attributes.put("endDayTime", getEndDayTime());
		attributes.put("startSessionsDate", getStartSessionsDate());
		attributes.put("endSessionsDate", getEndSessionsDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		String startDayTime = (String)attributes.get("startDayTime");

		if (startDayTime != null) {
			setStartDayTime(startDayTime);
		}

		String endDayTime = (String)attributes.get("endDayTime");

		if (endDayTime != null) {
			setEndDayTime(endDayTime);
		}

		Date startSessionsDate = (Date)attributes.get("startSessionsDate");

		if (startSessionsDate != null) {
			setStartSessionsDate(startSessionsDate);
		}

		Date endSessionsDate = (Date)attributes.get("endSessionsDate");

		if (endSessionsDate != null) {
			setEndSessionsDate(endSessionsDate);
		}
	}

	@Override
	public ScheduleConfiguration cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the end day time of this schedule configuration.
	 *
	 * @return the end day time of this schedule configuration
	 */
	@Override
	public String getEndDayTime() {
		return model.getEndDayTime();
	}

	/**
	 * Returns the end sessions date of this schedule configuration.
	 *
	 * @return the end sessions date of this schedule configuration
	 */
	@Override
	public Date getEndSessionsDate() {
		return model.getEndSessionsDate();
	}

	/**
	 * Returns the primary key of this schedule configuration.
	 *
	 * @return the primary key of this schedule configuration
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the school ID of this schedule configuration.
	 *
	 * @return the school ID of this schedule configuration
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the start day time of this schedule configuration.
	 *
	 * @return the start day time of this schedule configuration
	 */
	@Override
	public String getStartDayTime() {
		return model.getStartDayTime();
	}

	/**
	 * Returns the start sessions date of this schedule configuration.
	 *
	 * @return the start sessions date of this schedule configuration
	 */
	@Override
	public Date getStartSessionsDate() {
		return model.getStartSessionsDate();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the end day time of this schedule configuration.
	 *
	 * @param endDayTime the end day time of this schedule configuration
	 */
	@Override
	public void setEndDayTime(String endDayTime) {
		model.setEndDayTime(endDayTime);
	}

	/**
	 * Sets the end sessions date of this schedule configuration.
	 *
	 * @param endSessionsDate the end sessions date of this schedule configuration
	 */
	@Override
	public void setEndSessionsDate(Date endSessionsDate) {
		model.setEndSessionsDate(endSessionsDate);
	}

	/**
	 * Sets the primary key of this schedule configuration.
	 *
	 * @param primaryKey the primary key of this schedule configuration
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the school ID of this schedule configuration.
	 *
	 * @param schoolId the school ID of this schedule configuration
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the start day time of this schedule configuration.
	 *
	 * @param startDayTime the start day time of this schedule configuration
	 */
	@Override
	public void setStartDayTime(String startDayTime) {
		model.setStartDayTime(startDayTime);
	}

	/**
	 * Sets the start sessions date of this schedule configuration.
	 *
	 * @param startSessionsDate the start sessions date of this schedule configuration
	 */
	@Override
	public void setStartSessionsDate(Date startSessionsDate) {
		model.setStartSessionsDate(startSessionsDate);
	}

	@Override
	protected ScheduleConfigurationWrapper wrap(
		ScheduleConfiguration scheduleConfiguration) {

		return new ScheduleConfigurationWrapper(scheduleConfiguration);
	}

}