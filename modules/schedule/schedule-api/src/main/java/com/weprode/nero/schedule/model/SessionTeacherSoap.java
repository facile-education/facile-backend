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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.schedule.service.http.SessionTeacherServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class SessionTeacherSoap implements Serializable {

	public static SessionTeacherSoap toSoapModel(SessionTeacher model) {
		SessionTeacherSoap soapModel = new SessionTeacherSoap();

		soapModel.setSessionTeacherId(model.getSessionTeacherId());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setTeacherId(model.getTeacherId());
		soapModel.setStatus(model.getStatus());
		soapModel.setSubstituteId(model.getSubstituteId());
		soapModel.setModificationDate(model.getModificationDate());
		soapModel.setPrivateNotes(model.getPrivateNotes());

		return soapModel;
	}

	public static SessionTeacherSoap[] toSoapModels(SessionTeacher[] models) {
		SessionTeacherSoap[] soapModels = new SessionTeacherSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SessionTeacherSoap[][] toSoapModels(
		SessionTeacher[][] models) {

		SessionTeacherSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new SessionTeacherSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SessionTeacherSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SessionTeacherSoap[] toSoapModels(
		List<SessionTeacher> models) {

		List<SessionTeacherSoap> soapModels = new ArrayList<SessionTeacherSoap>(
			models.size());

		for (SessionTeacher model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SessionTeacherSoap[soapModels.size()]);
	}

	public SessionTeacherSoap() {
	}

	public long getPrimaryKey() {
		return _sessionTeacherId;
	}

	public void setPrimaryKey(long pk) {
		setSessionTeacherId(pk);
	}

	public long getSessionTeacherId() {
		return _sessionTeacherId;
	}

	public void setSessionTeacherId(long sessionTeacherId) {
		_sessionTeacherId = sessionTeacherId;
	}

	public long getSessionId() {
		return _sessionId;
	}

	public void setSessionId(long sessionId) {
		_sessionId = sessionId;
	}

	public long getTeacherId() {
		return _teacherId;
	}

	public void setTeacherId(long teacherId) {
		_teacherId = teacherId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getSubstituteId() {
		return _substituteId;
	}

	public void setSubstituteId(long substituteId) {
		_substituteId = substituteId;
	}

	public Date getModificationDate() {
		return _modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		_modificationDate = modificationDate;
	}

	public String getPrivateNotes() {
		return _privateNotes;
	}

	public void setPrivateNotes(String privateNotes) {
		_privateNotes = privateNotes;
	}

	private long _sessionTeacherId;
	private long _sessionId;
	private long _teacherId;
	private int _status;
	private long _substituteId;
	private Date _modificationDate;
	private String _privateNotes;

}