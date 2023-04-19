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
public class SessionStudentSoap implements Serializable {

	public static SessionStudentSoap toSoapModel(SessionStudent model) {
		SessionStudentSoap soapModel = new SessionStudentSoap();

		soapModel.setSessionStudentId(model.getSessionStudentId());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setStudentId(model.getStudentId());

		return soapModel;
	}

	public static SessionStudentSoap[] toSoapModels(SessionStudent[] models) {
		SessionStudentSoap[] soapModels = new SessionStudentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SessionStudentSoap[][] toSoapModels(
		SessionStudent[][] models) {

		SessionStudentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new SessionStudentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SessionStudentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SessionStudentSoap[] toSoapModels(
		List<SessionStudent> models) {

		List<SessionStudentSoap> soapModels = new ArrayList<SessionStudentSoap>(
			models.size());

		for (SessionStudent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SessionStudentSoap[soapModels.size()]);
	}

	public SessionStudentSoap() {
	}

	public long getPrimaryKey() {
		return _sessionStudentId;
	}

	public void setPrimaryKey(long pk) {
		setSessionStudentId(pk);
	}

	public long getSessionStudentId() {
		return _sessionStudentId;
	}

	public void setSessionStudentId(long sessionStudentId) {
		_sessionStudentId = sessionStudentId;
	}

	public long getSessionId() {
		return _sessionId;
	}

	public void setSessionId(long sessionId) {
		_sessionId = sessionId;
	}

	public long getStudentId() {
		return _studentId;
	}

	public void setStudentId(long studentId) {
		_studentId = studentId;
	}

	private long _sessionStudentId;
	private long _sessionId;
	private long _studentId;

}