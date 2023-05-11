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

import com.weprode.nero.agenda.service.persistence.EventReadPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class EventReadSoap implements Serializable {

	public static EventReadSoap toSoapModel(EventRead model) {
		EventReadSoap soapModel = new EventReadSoap();

		soapModel.setEventId(model.getEventId());
		soapModel.setUserId(model.getUserId());
		soapModel.setReadDate(model.getReadDate());

		return soapModel;
	}

	public static EventReadSoap[] toSoapModels(EventRead[] models) {
		EventReadSoap[] soapModels = new EventReadSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EventReadSoap[][] toSoapModels(EventRead[][] models) {
		EventReadSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EventReadSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EventReadSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EventReadSoap[] toSoapModels(List<EventRead> models) {
		List<EventReadSoap> soapModels = new ArrayList<EventReadSoap>(
			models.size());

		for (EventRead model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EventReadSoap[soapModels.size()]);
	}

	public EventReadSoap() {
	}

	public EventReadPK getPrimaryKey() {
		return new EventReadPK(_eventId, _userId);
	}

	public void setPrimaryKey(EventReadPK pk) {
		setEventId(pk.eventId);
		setUserId(pk.userId);
	}

	public long getEventId() {
		return _eventId;
	}

	public void setEventId(long eventId) {
		_eventId = eventId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getReadDate() {
		return _readDate;
	}

	public void setReadDate(Date readDate) {
		_readDate = readDate;
	}

	private long _eventId;
	private long _userId;
	private Date _readDate;

}