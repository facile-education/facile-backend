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

package com.weprode.facile.agenda.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link EventPopulation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventPopulation
 * @generated
 */
public class EventPopulationWrapper
	extends BaseModelWrapper<EventPopulation>
	implements EventPopulation, ModelWrapper<EventPopulation> {

	public EventPopulationWrapper(EventPopulation eventPopulation) {
		super(eventPopulation);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("eventId", getEventId());
		attributes.put("groupId", getGroupId());
		attributes.put("roleId", getRoleId());
		attributes.put("schoolId", getSchoolId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long eventId = (Long)attributes.get("eventId");

		if (eventId != null) {
			setEventId(eventId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}
	}

	@Override
	public EventPopulation cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the event ID of this event population.
	 *
	 * @return the event ID of this event population
	 */
	@Override
	public long getEventId() {
		return model.getEventId();
	}

	/**
	 * Returns the group ID of this event population.
	 *
	 * @return the group ID of this event population
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the primary key of this event population.
	 *
	 * @return the primary key of this event population
	 */
	@Override
	public com.weprode.facile.agenda.service.persistence.EventPopulationPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this event population.
	 *
	 * @return the role ID of this event population
	 */
	@Override
	public long getRoleId() {
		return model.getRoleId();
	}

	/**
	 * Returns the school ID of this event population.
	 *
	 * @return the school ID of this event population
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
	 * Sets the event ID of this event population.
	 *
	 * @param eventId the event ID of this event population
	 */
	@Override
	public void setEventId(long eventId) {
		model.setEventId(eventId);
	}

	/**
	 * Sets the group ID of this event population.
	 *
	 * @param groupId the group ID of this event population
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the primary key of this event population.
	 *
	 * @param primaryKey the primary key of this event population
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.facile.agenda.service.persistence.EventPopulationPK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this event population.
	 *
	 * @param roleId the role ID of this event population
	 */
	@Override
	public void setRoleId(long roleId) {
		model.setRoleId(roleId);
	}

	/**
	 * Sets the school ID of this event population.
	 *
	 * @param schoolId the school ID of this event population
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	@Override
	protected EventPopulationWrapper wrap(EventPopulation eventPopulation) {
		return new EventPopulationWrapper(eventPopulation);
	}

}