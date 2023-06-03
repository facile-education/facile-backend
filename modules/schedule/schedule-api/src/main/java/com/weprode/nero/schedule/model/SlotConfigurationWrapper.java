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
 * This class is a wrapper for {@link SlotConfiguration}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SlotConfiguration
 * @generated
 */
public class SlotConfigurationWrapper
	extends BaseModelWrapper<SlotConfiguration>
	implements ModelWrapper<SlotConfiguration>, SlotConfiguration {

	public SlotConfigurationWrapper(SlotConfiguration slotConfiguration) {
		super(slotConfiguration);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("schoolId", getSchoolId());
		attributes.put("slotNumber", getSlotNumber());
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

		Integer slotNumber = (Integer)attributes.get("slotNumber");

		if (slotNumber != null) {
			setSlotNumber(slotNumber);
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
	public SlotConfiguration cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the primary key of this slot configuration.
	 *
	 * @return the primary key of this slot configuration
	 */
	@Override
	public com.weprode.nero.schedule.service.persistence.SlotConfigurationPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the school ID of this slot configuration.
	 *
	 * @return the school ID of this slot configuration
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the session end hour of this slot configuration.
	 *
	 * @return the session end hour of this slot configuration
	 */
	@Override
	public String getSessionEndHour() {
		return model.getSessionEndHour();
	}

	/**
	 * Returns the session start hour of this slot configuration.
	 *
	 * @return the session start hour of this slot configuration
	 */
	@Override
	public String getSessionStartHour() {
		return model.getSessionStartHour();
	}

	/**
	 * Returns the slot number of this slot configuration.
	 *
	 * @return the slot number of this slot configuration
	 */
	@Override
	public int getSlotNumber() {
		return model.getSlotNumber();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the primary key of this slot configuration.
	 *
	 * @param primaryKey the primary key of this slot configuration
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.nero.schedule.service.persistence.SlotConfigurationPK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the school ID of this slot configuration.
	 *
	 * @param schoolId the school ID of this slot configuration
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the session end hour of this slot configuration.
	 *
	 * @param sessionEndHour the session end hour of this slot configuration
	 */
	@Override
	public void setSessionEndHour(String sessionEndHour) {
		model.setSessionEndHour(sessionEndHour);
	}

	/**
	 * Sets the session start hour of this slot configuration.
	 *
	 * @param sessionStartHour the session start hour of this slot configuration
	 */
	@Override
	public void setSessionStartHour(String sessionStartHour) {
		model.setSessionStartHour(sessionStartHour);
	}

	/**
	 * Sets the slot number of this slot configuration.
	 *
	 * @param slotNumber the slot number of this slot configuration
	 */
	@Override
	public void setSlotNumber(int slotNumber) {
		model.setSlotNumber(slotNumber);
	}

	@Override
	protected SlotConfigurationWrapper wrap(
		SlotConfiguration slotConfiguration) {

		return new SlotConfigurationWrapper(slotConfiguration);
	}

}