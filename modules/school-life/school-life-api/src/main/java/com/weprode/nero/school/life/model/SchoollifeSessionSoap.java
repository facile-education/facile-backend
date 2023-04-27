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

package com.weprode.nero.school.life.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.school.life.service.http.SchoollifeSessionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class SchoollifeSessionSoap implements Serializable {

	public static SchoollifeSessionSoap toSoapModel(SchoollifeSession model) {
		SchoollifeSessionSoap soapModel = new SchoollifeSessionSoap();

		soapModel.setSchoollifeSessionId(model.getSchoollifeSessionId());
		soapModel.setSchoollifeSlotId(model.getSchoollifeSlotId());
		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setType(model.getType());
		soapModel.setWeekNb(model.getWeekNb());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setRollCalled(model.isRollCalled());
		soapModel.setAbsenceNotificationSent(model.isAbsenceNotificationSent());

		return soapModel;
	}

	public static SchoollifeSessionSoap[] toSoapModels(
		SchoollifeSession[] models) {

		SchoollifeSessionSoap[] soapModels =
			new SchoollifeSessionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SchoollifeSessionSoap[][] toSoapModels(
		SchoollifeSession[][] models) {

		SchoollifeSessionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new SchoollifeSessionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SchoollifeSessionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SchoollifeSessionSoap[] toSoapModels(
		List<SchoollifeSession> models) {

		List<SchoollifeSessionSoap> soapModels =
			new ArrayList<SchoollifeSessionSoap>(models.size());

		for (SchoollifeSession model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SchoollifeSessionSoap[soapModels.size()]);
	}

	public SchoollifeSessionSoap() {
	}

	public long getPrimaryKey() {
		return _schoollifeSessionId;
	}

	public void setPrimaryKey(long pk) {
		setSchoollifeSessionId(pk);
	}

	public long getSchoollifeSessionId() {
		return _schoollifeSessionId;
	}

	public void setSchoollifeSessionId(long schoollifeSessionId) {
		_schoollifeSessionId = schoollifeSessionId;
	}

	public long getSchoollifeSlotId() {
		return _schoollifeSlotId;
	}

	public void setSchoollifeSlotId(long schoollifeSlotId) {
		_schoollifeSlotId = schoollifeSlotId;
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getWeekNb() {
		return _weekNb;
	}

	public void setWeekNb(int weekNb) {
		_weekNb = weekNb;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public boolean getRollCalled() {
		return _rollCalled;
	}

	public boolean isRollCalled() {
		return _rollCalled;
	}

	public void setRollCalled(boolean rollCalled) {
		_rollCalled = rollCalled;
	}

	public boolean getAbsenceNotificationSent() {
		return _absenceNotificationSent;
	}

	public boolean isAbsenceNotificationSent() {
		return _absenceNotificationSent;
	}

	public void setAbsenceNotificationSent(boolean absenceNotificationSent) {
		_absenceNotificationSent = absenceNotificationSent;
	}

	private long _schoollifeSessionId;
	private long _schoollifeSlotId;
	private long _schoolId;
	private int _type;
	private int _weekNb;
	private Date _startDate;
	private Date _endDate;
	private boolean _rollCalled;
	private boolean _absenceNotificationSent;

}