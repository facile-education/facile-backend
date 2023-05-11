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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link EventRead}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventRead
 * @generated
 */
public class EventReadWrapper
	extends BaseModelWrapper<EventRead>
	implements EventRead, ModelWrapper<EventRead> {

	public EventReadWrapper(EventRead eventRead) {
		super(eventRead);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("eventId", getEventId());
		attributes.put("userId", getUserId());
		attributes.put("readDate", getReadDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long eventId = (Long)attributes.get("eventId");

		if (eventId != null) {
			setEventId(eventId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date readDate = (Date)attributes.get("readDate");

		if (readDate != null) {
			setReadDate(readDate);
		}
	}

	@Override
	public EventRead cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the event ID of this event read.
	 *
	 * @return the event ID of this event read
	 */
	@Override
	public long getEventId() {
		return model.getEventId();
	}

	/**
	 * Returns the primary key of this event read.
	 *
	 * @return the primary key of this event read
	 */
	@Override
	public com.weprode.nero.agenda.service.persistence.EventReadPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the read date of this event read.
	 *
	 * @return the read date of this event read
	 */
	@Override
	public Date getReadDate() {
		return model.getReadDate();
	}

	/**
	 * Returns the user ID of this event read.
	 *
	 * @return the user ID of this event read
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this event read.
	 *
	 * @return the user uuid of this event read
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the event ID of this event read.
	 *
	 * @param eventId the event ID of this event read
	 */
	@Override
	public void setEventId(long eventId) {
		model.setEventId(eventId);
	}

	/**
	 * Sets the primary key of this event read.
	 *
	 * @param primaryKey the primary key of this event read
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.nero.agenda.service.persistence.EventReadPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the read date of this event read.
	 *
	 * @param readDate the read date of this event read
	 */
	@Override
	public void setReadDate(Date readDate) {
		model.setReadDate(readDate);
	}

	/**
	 * Sets the user ID of this event read.
	 *
	 * @param userId the user ID of this event read
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this event read.
	 *
	 * @param userUuid the user uuid of this event read
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected EventReadWrapper wrap(EventRead eventRead) {
		return new EventReadWrapper(eventRead);
	}

}