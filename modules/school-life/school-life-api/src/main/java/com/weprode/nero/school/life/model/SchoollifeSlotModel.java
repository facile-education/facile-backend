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

package com.weprode.nero.school.life.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the SchoollifeSlot service. Represents a row in the &quot;Schoollife_SchoollifeSlot&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.school.life.model.impl.SchoollifeSlotModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.school.life.model.impl.SchoollifeSlotImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSlot
 * @generated
 */
@ProviderType
public interface SchoollifeSlotModel extends BaseModel<SchoollifeSlot> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a schoollife slot model instance should use the {@link SchoollifeSlot} interface instead.
	 */

	/**
	 * Returns the primary key of this schoollife slot.
	 *
	 * @return the primary key of this schoollife slot
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this schoollife slot.
	 *
	 * @param primaryKey the primary key of this schoollife slot
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the schoollife slot ID of this schoollife slot.
	 *
	 * @return the schoollife slot ID of this schoollife slot
	 */
	public long getSchoollifeSlotId();

	/**
	 * Sets the schoollife slot ID of this schoollife slot.
	 *
	 * @param schoollifeSlotId the schoollife slot ID of this schoollife slot
	 */
	public void setSchoollifeSlotId(long schoollifeSlotId);

	/**
	 * Returns the school ID of this schoollife slot.
	 *
	 * @return the school ID of this schoollife slot
	 */
	public long getSchoolId();

	/**
	 * Sets the school ID of this schoollife slot.
	 *
	 * @param schoolId the school ID of this schoollife slot
	 */
	public void setSchoolId(long schoolId);

	/**
	 * Returns the day of this schoollife slot.
	 *
	 * @return the day of this schoollife slot
	 */
	public int getDay();

	/**
	 * Sets the day of this schoollife slot.
	 *
	 * @param day the day of this schoollife slot
	 */
	public void setDay(int day);

	/**
	 * Returns the start hour of this schoollife slot.
	 *
	 * @return the start hour of this schoollife slot
	 */
	@AutoEscape
	public String getStartHour();

	/**
	 * Sets the start hour of this schoollife slot.
	 *
	 * @param startHour the start hour of this schoollife slot
	 */
	public void setStartHour(String startHour);

	/**
	 * Returns the end hour of this schoollife slot.
	 *
	 * @return the end hour of this schoollife slot
	 */
	@AutoEscape
	public String getEndHour();

	/**
	 * Sets the end hour of this schoollife slot.
	 *
	 * @param endHour the end hour of this schoollife slot
	 */
	public void setEndHour(String endHour);

	/**
	 * Returns the teacher ID of this schoollife slot.
	 *
	 * @return the teacher ID of this schoollife slot
	 */
	public long getTeacherId();

	/**
	 * Sets the teacher ID of this schoollife slot.
	 *
	 * @param teacherId the teacher ID of this schoollife slot
	 */
	public void setTeacherId(long teacherId);

	/**
	 * Returns the type of this schoollife slot.
	 *
	 * @return the type of this schoollife slot
	 */
	public int getType();

	/**
	 * Sets the type of this schoollife slot.
	 *
	 * @param type the type of this schoollife slot
	 */
	public void setType(int type);

	/**
	 * Returns the room of this schoollife slot.
	 *
	 * @return the room of this schoollife slot
	 */
	@AutoEscape
	public String getRoom();

	/**
	 * Sets the room of this schoollife slot.
	 *
	 * @param room the room of this schoollife slot
	 */
	public void setRoom(String room);

	/**
	 * Returns the capacity of this schoollife slot.
	 *
	 * @return the capacity of this schoollife slot
	 */
	public int getCapacity();

	/**
	 * Sets the capacity of this schoollife slot.
	 *
	 * @param capacity the capacity of this schoollife slot
	 */
	public void setCapacity(int capacity);

	@Override
	public SchoollifeSlot cloneWithOriginalValues();

}