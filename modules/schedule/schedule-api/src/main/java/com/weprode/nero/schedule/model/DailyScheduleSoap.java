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

import com.weprode.nero.schedule.service.persistence.DailySchedulePK;

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
public class DailyScheduleSoap implements Serializable {

	public static DailyScheduleSoap toSoapModel(DailySchedule model) {
		DailyScheduleSoap soapModel = new DailyScheduleSoap();

		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setSessionStartHour(model.getSessionStartHour());
		soapModel.setSessionEndHour(model.getSessionEndHour());

		return soapModel;
	}

	public static DailyScheduleSoap[] toSoapModels(DailySchedule[] models) {
		DailyScheduleSoap[] soapModels = new DailyScheduleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DailyScheduleSoap[][] toSoapModels(DailySchedule[][] models) {
		DailyScheduleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DailyScheduleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DailyScheduleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DailyScheduleSoap[] toSoapModels(List<DailySchedule> models) {
		List<DailyScheduleSoap> soapModels = new ArrayList<DailyScheduleSoap>(
			models.size());

		for (DailySchedule model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DailyScheduleSoap[soapModels.size()]);
	}

	public DailyScheduleSoap() {
	}

	public DailySchedulePK getPrimaryKey() {
		return new DailySchedulePK(_schoolId, _sessionId);
	}

	public void setPrimaryKey(DailySchedulePK pk) {
		setSchoolId(pk.schoolId);
		setSessionId(pk.sessionId);
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	public int getSessionId() {
		return _sessionId;
	}

	public void setSessionId(int sessionId) {
		_sessionId = sessionId;
	}

	public String getSessionStartHour() {
		return _sessionStartHour;
	}

	public void setSessionStartHour(String sessionStartHour) {
		_sessionStartHour = sessionStartHour;
	}

	public String getSessionEndHour() {
		return _sessionEndHour;
	}

	public void setSessionEndHour(String sessionEndHour) {
		_sessionEndHour = sessionEndHour;
	}

	private long _schoolId;
	private int _sessionId;
	private String _sessionStartHour;
	private String _sessionEndHour;

}