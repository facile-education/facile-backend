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

package com.weprode.facile.school.life.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SchoollifeSlot}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSlot
 * @generated
 */
public class SchoollifeSlotWrapper
	extends BaseModelWrapper<SchoollifeSlot>
	implements ModelWrapper<SchoollifeSlot>, SchoollifeSlot {

	public SchoollifeSlotWrapper(SchoollifeSlot schoollifeSlot) {
		super(schoollifeSlot);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("schoollifeSlotId", getSchoollifeSlotId());
		attributes.put("schoolId", getSchoolId());
		attributes.put("day", getDay());
		attributes.put("startHour", getStartHour());
		attributes.put("endHour", getEndHour());
		attributes.put("teacherId", getTeacherId());
		attributes.put("type", getType());
		attributes.put("room", getRoom());
		attributes.put("capacity", getCapacity());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long schoollifeSlotId = (Long)attributes.get("schoollifeSlotId");

		if (schoollifeSlotId != null) {
			setSchoollifeSlotId(schoollifeSlotId);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		Integer day = (Integer)attributes.get("day");

		if (day != null) {
			setDay(day);
		}

		String startHour = (String)attributes.get("startHour");

		if (startHour != null) {
			setStartHour(startHour);
		}

		String endHour = (String)attributes.get("endHour");

		if (endHour != null) {
			setEndHour(endHour);
		}

		Long teacherId = (Long)attributes.get("teacherId");

		if (teacherId != null) {
			setTeacherId(teacherId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String room = (String)attributes.get("room");

		if (room != null) {
			setRoom(room);
		}

		Integer capacity = (Integer)attributes.get("capacity");

		if (capacity != null) {
			setCapacity(capacity);
		}
	}

	@Override
	public SchoollifeSlot cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the capacity of this schoollife slot.
	 *
	 * @return the capacity of this schoollife slot
	 */
	@Override
	public int getCapacity() {
		return model.getCapacity();
	}

	/**
	 * Returns the day of this schoollife slot.
	 *
	 * @return the day of this schoollife slot
	 */
	@Override
	public int getDay() {
		return model.getDay();
	}

	/**
	 * Returns the end hour of this schoollife slot.
	 *
	 * @return the end hour of this schoollife slot
	 */
	@Override
	public String getEndHour() {
		return model.getEndHour();
	}

	/**
	 * Returns the primary key of this schoollife slot.
	 *
	 * @return the primary key of this schoollife slot
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the room of this schoollife slot.
	 *
	 * @return the room of this schoollife slot
	 */
	@Override
	public String getRoom() {
		return model.getRoom();
	}

	/**
	 * Returns the school ID of this schoollife slot.
	 *
	 * @return the school ID of this schoollife slot
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the schoollife slot ID of this schoollife slot.
	 *
	 * @return the schoollife slot ID of this schoollife slot
	 */
	@Override
	public long getSchoollifeSlotId() {
		return model.getSchoollifeSlotId();
	}

	/**
	 * Returns the start hour of this schoollife slot.
	 *
	 * @return the start hour of this schoollife slot
	 */
	@Override
	public String getStartHour() {
		return model.getStartHour();
	}

	/**
	 * Returns the teacher ID of this schoollife slot.
	 *
	 * @return the teacher ID of this schoollife slot
	 */
	@Override
	public long getTeacherId() {
		return model.getTeacherId();
	}

	/**
	 * Returns the type of this schoollife slot.
	 *
	 * @return the type of this schoollife slot
	 */
	@Override
	public int getType() {
		return model.getType();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the capacity of this schoollife slot.
	 *
	 * @param capacity the capacity of this schoollife slot
	 */
	@Override
	public void setCapacity(int capacity) {
		model.setCapacity(capacity);
	}

	/**
	 * Sets the day of this schoollife slot.
	 *
	 * @param day the day of this schoollife slot
	 */
	@Override
	public void setDay(int day) {
		model.setDay(day);
	}

	/**
	 * Sets the end hour of this schoollife slot.
	 *
	 * @param endHour the end hour of this schoollife slot
	 */
	@Override
	public void setEndHour(String endHour) {
		model.setEndHour(endHour);
	}

	/**
	 * Sets the primary key of this schoollife slot.
	 *
	 * @param primaryKey the primary key of this schoollife slot
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the room of this schoollife slot.
	 *
	 * @param room the room of this schoollife slot
	 */
	@Override
	public void setRoom(String room) {
		model.setRoom(room);
	}

	/**
	 * Sets the school ID of this schoollife slot.
	 *
	 * @param schoolId the school ID of this schoollife slot
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the schoollife slot ID of this schoollife slot.
	 *
	 * @param schoollifeSlotId the schoollife slot ID of this schoollife slot
	 */
	@Override
	public void setSchoollifeSlotId(long schoollifeSlotId) {
		model.setSchoollifeSlotId(schoollifeSlotId);
	}

	/**
	 * Sets the start hour of this schoollife slot.
	 *
	 * @param startHour the start hour of this schoollife slot
	 */
	@Override
	public void setStartHour(String startHour) {
		model.setStartHour(startHour);
	}

	/**
	 * Sets the teacher ID of this schoollife slot.
	 *
	 * @param teacherId the teacher ID of this schoollife slot
	 */
	@Override
	public void setTeacherId(long teacherId) {
		model.setTeacherId(teacherId);
	}

	/**
	 * Sets the type of this schoollife slot.
	 *
	 * @param type the type of this schoollife slot
	 */
	@Override
	public void setType(int type) {
		model.setType(type);
	}

	@Override
	protected SchoollifeSlotWrapper wrap(SchoollifeSlot schoollifeSlot) {
		return new SchoollifeSlotWrapper(schoollifeSlot);
	}

}