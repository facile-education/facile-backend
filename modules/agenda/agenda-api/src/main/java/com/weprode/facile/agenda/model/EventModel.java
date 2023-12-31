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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Event service. Represents a row in the &quot;Agenda_Event&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.agenda.model.impl.EventModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.agenda.model.impl.EventImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Event
 * @generated
 */
@ProviderType
public interface EventModel extends BaseModel<Event>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a event model instance should use the {@link Event} interface instead.
	 */

	/**
	 * Returns the primary key of this event.
	 *
	 * @return the primary key of this event
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this event.
	 *
	 * @param primaryKey the primary key of this event
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the event ID of this event.
	 *
	 * @return the event ID of this event
	 */
	public long getEventId();

	/**
	 * Sets the event ID of this event.
	 *
	 * @param eventId the event ID of this event
	 */
	public void setEventId(long eventId);

	/**
	 * Returns the company ID of this event.
	 *
	 * @return the company ID of this event
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this event.
	 *
	 * @param companyId the company ID of this event
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the start date of this event.
	 *
	 * @return the start date of this event
	 */
	public Date getStartDate();

	/**
	 * Sets the start date of this event.
	 *
	 * @param startDate the start date of this event
	 */
	public void setStartDate(Date startDate);

	/**
	 * Returns the end date of this event.
	 *
	 * @return the end date of this event
	 */
	public Date getEndDate();

	/**
	 * Sets the end date of this event.
	 *
	 * @param endDate the end date of this event
	 */
	public void setEndDate(Date endDate);

	/**
	 * Returns the title of this event.
	 *
	 * @return the title of this event
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this event.
	 *
	 * @param title the title of this event
	 */
	public void setTitle(String title);

	/**
	 * Returns the description of this event.
	 *
	 * @return the description of this event
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this event.
	 *
	 * @param description the description of this event
	 */
	public void setDescription(String description);

	/**
	 * Returns the location of this event.
	 *
	 * @return the location of this event
	 */
	@AutoEscape
	public String getLocation();

	/**
	 * Sets the location of this event.
	 *
	 * @param location the location of this event
	 */
	public void setLocation(String location);

	/**
	 * Returns the author ID of this event.
	 *
	 * @return the author ID of this event
	 */
	public long getAuthorId();

	/**
	 * Sets the author ID of this event.
	 *
	 * @param authorId the author ID of this event
	 */
	public void setAuthorId(long authorId);

	@Override
	public Event cloneWithOriginalValues();

}