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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Event}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Event
 * @generated
 */
public class EventWrapper
	extends BaseModelWrapper<Event> implements Event, ModelWrapper<Event> {

	public EventWrapper(Event event) {
		super(event);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("eventId", getEventId());
		attributes.put("companyId", getCompanyId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("location", getLocation());
		attributes.put("authorId", getAuthorId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long eventId = (Long)attributes.get("eventId");

		if (eventId != null) {
			setEventId(eventId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String location = (String)attributes.get("location");

		if (location != null) {
			setLocation(location);
		}

		Long authorId = (Long)attributes.get("authorId");

		if (authorId != null) {
			setAuthorId(authorId);
		}
	}

	@Override
	public Event cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the author ID of this event.
	 *
	 * @return the author ID of this event
	 */
	@Override
	public long getAuthorId() {
		return model.getAuthorId();
	}

	/**
	 * Returns the company ID of this event.
	 *
	 * @return the company ID of this event
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the description of this event.
	 *
	 * @return the description of this event
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the end date of this event.
	 *
	 * @return the end date of this event
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the event ID of this event.
	 *
	 * @return the event ID of this event
	 */
	@Override
	public long getEventId() {
		return model.getEventId();
	}

	/**
	 * Returns the location of this event.
	 *
	 * @return the location of this event
	 */
	@Override
	public String getLocation() {
		return model.getLocation();
	}

	/**
	 * Returns the primary key of this event.
	 *
	 * @return the primary key of this event
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the start date of this event.
	 *
	 * @return the start date of this event
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the title of this event.
	 *
	 * @return the title of this event
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the author ID of this event.
	 *
	 * @param authorId the author ID of this event
	 */
	@Override
	public void setAuthorId(long authorId) {
		model.setAuthorId(authorId);
	}

	/**
	 * Sets the company ID of this event.
	 *
	 * @param companyId the company ID of this event
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the description of this event.
	 *
	 * @param description the description of this event
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the end date of this event.
	 *
	 * @param endDate the end date of this event
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the event ID of this event.
	 *
	 * @param eventId the event ID of this event
	 */
	@Override
	public void setEventId(long eventId) {
		model.setEventId(eventId);
	}

	/**
	 * Sets the location of this event.
	 *
	 * @param location the location of this event
	 */
	@Override
	public void setLocation(String location) {
		model.setLocation(location);
	}

	/**
	 * Sets the primary key of this event.
	 *
	 * @param primaryKey the primary key of this event
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the start date of this event.
	 *
	 * @param startDate the start date of this event
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the title of this event.
	 *
	 * @param title the title of this event
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	@Override
	protected EventWrapper wrap(Event event) {
		return new EventWrapper(event);
	}

}