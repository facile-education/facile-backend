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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WeeklySchedule}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WeeklySchedule
 * @generated
 */
public class WeeklyScheduleWrapper
	extends BaseModelWrapper<WeeklySchedule>
	implements ModelWrapper<WeeklySchedule>, WeeklySchedule {

	public WeeklyScheduleWrapper(WeeklySchedule weeklySchedule) {
		super(weeklySchedule);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("schoolId", getSchoolId());
		attributes.put("dayId", getDayId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		Integer dayId = (Integer)attributes.get("dayId");

		if (dayId != null) {
			setDayId(dayId);
		}
	}

	@Override
	public WeeklySchedule cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the day ID of this weekly schedule.
	 *
	 * @return the day ID of this weekly schedule
	 */
	@Override
	public int getDayId() {
		return model.getDayId();
	}

	/**
	 * Returns the primary key of this weekly schedule.
	 *
	 * @return the primary key of this weekly schedule
	 */
	@Override
	public com.weprode.nero.schedule.service.persistence.WeeklySchedulePK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the school ID of this weekly schedule.
	 *
	 * @return the school ID of this weekly schedule
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the day ID of this weekly schedule.
	 *
	 * @param dayId the day ID of this weekly schedule
	 */
	@Override
	public void setDayId(int dayId) {
		model.setDayId(dayId);
	}

	/**
	 * Sets the primary key of this weekly schedule.
	 *
	 * @param primaryKey the primary key of this weekly schedule
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.nero.schedule.service.persistence.WeeklySchedulePK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the school ID of this weekly schedule.
	 *
	 * @param schoolId the school ID of this weekly schedule
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	@Override
	protected WeeklyScheduleWrapper wrap(WeeklySchedule weeklySchedule) {
		return new WeeklyScheduleWrapper(weeklySchedule);
	}

}