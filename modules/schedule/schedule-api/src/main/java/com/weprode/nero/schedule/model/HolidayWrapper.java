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
 * This class is a wrapper for {@link Holiday}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Holiday
 * @generated
 */
public class HolidayWrapper
	extends BaseModelWrapper<Holiday>
	implements Holiday, ModelWrapper<Holiday> {

	public HolidayWrapper(Holiday holiday) {
		super(holiday);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("holidayId", getHolidayId());
		attributes.put("name", getName());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long holidayId = (Long)attributes.get("holidayId");

		if (holidayId != null) {
			setHolidayId(holidayId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}
	}

	@Override
	public Holiday cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the end date of this holiday.
	 *
	 * @return the end date of this holiday
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the holiday ID of this holiday.
	 *
	 * @return the holiday ID of this holiday
	 */
	@Override
	public long getHolidayId() {
		return model.getHolidayId();
	}

	/**
	 * Returns the name of this holiday.
	 *
	 * @return the name of this holiday
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this holiday.
	 *
	 * @return the primary key of this holiday
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the start date of this holiday.
	 *
	 * @return the start date of this holiday
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the end date of this holiday.
	 *
	 * @param endDate the end date of this holiday
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the holiday ID of this holiday.
	 *
	 * @param holidayId the holiday ID of this holiday
	 */
	@Override
	public void setHolidayId(long holidayId) {
		model.setHolidayId(holidayId);
	}

	/**
	 * Sets the name of this holiday.
	 *
	 * @param name the name of this holiday
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this holiday.
	 *
	 * @param primaryKey the primary key of this holiday
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the start date of this holiday.
	 *
	 * @param startDate the start date of this holiday
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	@Override
	protected HolidayWrapper wrap(Holiday holiday) {
		return new HolidayWrapper(holiday);
	}

}