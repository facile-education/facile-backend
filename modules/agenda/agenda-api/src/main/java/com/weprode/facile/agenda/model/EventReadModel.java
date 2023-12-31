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

import com.liferay.portal.kernel.model.BaseModel;

import com.weprode.facile.agenda.service.persistence.EventReadPK;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the EventRead service. Represents a row in the &quot;Agenda_EventRead&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.agenda.model.impl.EventReadModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.agenda.model.impl.EventReadImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventRead
 * @generated
 */
@ProviderType
public interface EventReadModel extends BaseModel<EventRead> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a event read model instance should use the {@link EventRead} interface instead.
	 */

	/**
	 * Returns the primary key of this event read.
	 *
	 * @return the primary key of this event read
	 */
	public EventReadPK getPrimaryKey();

	/**
	 * Sets the primary key of this event read.
	 *
	 * @param primaryKey the primary key of this event read
	 */
	public void setPrimaryKey(EventReadPK primaryKey);

	/**
	 * Returns the event ID of this event read.
	 *
	 * @return the event ID of this event read
	 */
	public long getEventId();

	/**
	 * Sets the event ID of this event read.
	 *
	 * @param eventId the event ID of this event read
	 */
	public void setEventId(long eventId);

	/**
	 * Returns the user ID of this event read.
	 *
	 * @return the user ID of this event read
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this event read.
	 *
	 * @param userId the user ID of this event read
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this event read.
	 *
	 * @return the user uuid of this event read
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this event read.
	 *
	 * @param userUuid the user uuid of this event read
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the read date of this event read.
	 *
	 * @return the read date of this event read
	 */
	public Date getReadDate();

	/**
	 * Sets the read date of this event read.
	 *
	 * @param readDate the read date of this event read
	 */
	public void setReadDate(Date readDate);

	@Override
	public EventRead cloneWithOriginalValues();

}