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

package com.weprode.nero.agenda.model;

import com.liferay.portal.kernel.model.BaseModel;

import com.weprode.nero.agenda.service.persistence.EventPopulationPK;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the EventPopulation service. Represents a row in the &quot;Agenda_EventPopulation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.agenda.model.impl.EventPopulationModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.agenda.model.impl.EventPopulationImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventPopulation
 * @generated
 */
@ProviderType
public interface EventPopulationModel extends BaseModel<EventPopulation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a event population model instance should use the {@link EventPopulation} interface instead.
	 */

	/**
	 * Returns the primary key of this event population.
	 *
	 * @return the primary key of this event population
	 */
	public EventPopulationPK getPrimaryKey();

	/**
	 * Sets the primary key of this event population.
	 *
	 * @param primaryKey the primary key of this event population
	 */
	public void setPrimaryKey(EventPopulationPK primaryKey);

	/**
	 * Returns the event ID of this event population.
	 *
	 * @return the event ID of this event population
	 */
	public long getEventId();

	/**
	 * Sets the event ID of this event population.
	 *
	 * @param eventId the event ID of this event population
	 */
	public void setEventId(long eventId);

	/**
	 * Returns the group ID of this event population.
	 *
	 * @return the group ID of this event population
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this event population.
	 *
	 * @param groupId the group ID of this event population
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the role ID of this event population.
	 *
	 * @return the role ID of this event population
	 */
	public long getRoleId();

	/**
	 * Sets the role ID of this event population.
	 *
	 * @param roleId the role ID of this event population
	 */
	public void setRoleId(long roleId);

	@Override
	public EventPopulation cloneWithOriginalValues();

}