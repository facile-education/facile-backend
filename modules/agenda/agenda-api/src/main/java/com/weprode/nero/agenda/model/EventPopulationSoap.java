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

import com.weprode.nero.agenda.service.persistence.EventPopulationPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class EventPopulationSoap implements Serializable {

	public static EventPopulationSoap toSoapModel(EventPopulation model) {
		EventPopulationSoap soapModel = new EventPopulationSoap();

		soapModel.setEventId(model.getEventId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setRoleId(model.getRoleId());

		return soapModel;
	}

	public static EventPopulationSoap[] toSoapModels(EventPopulation[] models) {
		EventPopulationSoap[] soapModels =
			new EventPopulationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EventPopulationSoap[][] toSoapModels(
		EventPopulation[][] models) {

		EventPopulationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new EventPopulationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EventPopulationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EventPopulationSoap[] toSoapModels(
		List<EventPopulation> models) {

		List<EventPopulationSoap> soapModels =
			new ArrayList<EventPopulationSoap>(models.size());

		for (EventPopulation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EventPopulationSoap[soapModels.size()]);
	}

	public EventPopulationSoap() {
	}

	public EventPopulationPK getPrimaryKey() {
		return new EventPopulationPK(_eventId, _groupId, _roleId);
	}

	public void setPrimaryKey(EventPopulationPK pk) {
		setEventId(pk.eventId);
		setGroupId(pk.groupId);
		setRoleId(pk.roleId);
	}

	public long getEventId() {
		return _eventId;
	}

	public void setEventId(long eventId) {
		_eventId = eventId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	private long _eventId;
	private long _groupId;
	private long _roleId;

}