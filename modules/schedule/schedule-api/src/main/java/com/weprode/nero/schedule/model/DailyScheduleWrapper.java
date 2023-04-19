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
 * This class is a wrapper for {@link DailySchedule}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DailySchedule
 * @generated
 */
public class DailyScheduleWrapper
	extends BaseModelWrapper<DailySchedule>
	implements DailySchedule, ModelWrapper<DailySchedule> {

	public DailyScheduleWrapper(DailySchedule dailySchedule) {
		super(dailySchedule);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("schoolId", getSchoolId());
		attributes.put("sessionId", getSessionId());
		attributes.put("sessionStartHour", getSessionStartHour());
		attributes.put("sessionEndHour", getSessionEndHour());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		Integer sessionId = (Integer)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		String sessionStartHour = (String)attributes.get("sessionStartHour");

		if (sessionStartHour != null) {
			setSessionStartHour(sessionStartHour);
		}

		String sessionEndHour = (String)attributes.get("sessionEndHour");

		if (sessionEndHour != null) {
			setSessionEndHour(sessionEndHour);
		}
	}

	@Override
	public DailySchedule cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the primary key of this daily schedule.
	 *
	 * @return the primary key of this daily schedule
	 */
	@Override
	public com.weprode.nero.schedule.service.persistence.DailySchedulePK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the school ID of this daily schedule.
	 *
	 * @return the school ID of this daily schedule
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the session end hour of this daily schedule.
	 *
	 * @return the session end hour of this daily schedule
	 */
	@Override
	public String getSessionEndHour() {
		return model.getSessionEndHour();
	}

	/**
	 * Returns the session ID of this daily schedule.
	 *
	 * @return the session ID of this daily schedule
	 */
	@Override
	public int getSessionId() {
		return model.getSessionId();
	}

	/**
	 * Returns the session start hour of this daily schedule.
	 *
	 * @return the session start hour of this daily schedule
	 */
	@Override
	public String getSessionStartHour() {
		return model.getSessionStartHour();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the primary key of this daily schedule.
	 *
	 * @param primaryKey the primary key of this daily schedule
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.nero.schedule.service.persistence.DailySchedulePK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the school ID of this daily schedule.
	 *
	 * @param schoolId the school ID of this daily schedule
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the session end hour of this daily schedule.
	 *
	 * @param sessionEndHour the session end hour of this daily schedule
	 */
	@Override
	public void setSessionEndHour(String sessionEndHour) {
		model.setSessionEndHour(sessionEndHour);
	}

	/**
	 * Sets the session ID of this daily schedule.
	 *
	 * @param sessionId the session ID of this daily schedule
	 */
	@Override
	public void setSessionId(int sessionId) {
		model.setSessionId(sessionId);
	}

	/**
	 * Sets the session start hour of this daily schedule.
	 *
	 * @param sessionStartHour the session start hour of this daily schedule
	 */
	@Override
	public void setSessionStartHour(String sessionStartHour) {
		model.setSessionStartHour(sessionStartHour);
	}

	@Override
	protected DailyScheduleWrapper wrap(DailySchedule dailySchedule) {
		return new DailyScheduleWrapper(dailySchedule);
	}

}