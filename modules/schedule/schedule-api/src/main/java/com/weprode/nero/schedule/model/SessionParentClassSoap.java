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

package com.weprode.nero.schedule.model;

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
public class SessionParentClassSoap implements Serializable {

	public static SessionParentClassSoap toSoapModel(SessionParentClass model) {
		SessionParentClassSoap soapModel = new SessionParentClassSoap();

		soapModel.setSessionParentClassId(model.getSessionParentClassId());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setGroupId(model.getGroupId());

		return soapModel;
	}

	public static SessionParentClassSoap[] toSoapModels(
		SessionParentClass[] models) {

		SessionParentClassSoap[] soapModels =
			new SessionParentClassSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SessionParentClassSoap[][] toSoapModels(
		SessionParentClass[][] models) {

		SessionParentClassSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new SessionParentClassSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SessionParentClassSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SessionParentClassSoap[] toSoapModels(
		List<SessionParentClass> models) {

		List<SessionParentClassSoap> soapModels =
			new ArrayList<SessionParentClassSoap>(models.size());

		for (SessionParentClass model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new SessionParentClassSoap[soapModels.size()]);
	}

	public SessionParentClassSoap() {
	}

	public long getPrimaryKey() {
		return _sessionParentClassId;
	}

	public void setPrimaryKey(long pk) {
		setSessionParentClassId(pk);
	}

	public long getSessionParentClassId() {
		return _sessionParentClassId;
	}

	public void setSessionParentClassId(long sessionParentClassId) {
		_sessionParentClassId = sessionParentClassId;
	}

	public long getSessionId() {
		return _sessionId;
	}

	public void setSessionId(long sessionId) {
		_sessionId = sessionId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	private long _sessionParentClassId;
	private long _sessionId;
	private long _groupId;

}