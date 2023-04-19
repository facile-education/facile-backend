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

import com.weprode.nero.schedule.service.persistence.WeeklySchedulePK;

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
public class WeeklyScheduleSoap implements Serializable {

	public static WeeklyScheduleSoap toSoapModel(WeeklySchedule model) {
		WeeklyScheduleSoap soapModel = new WeeklyScheduleSoap();

		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setDayId(model.getDayId());

		return soapModel;
	}

	public static WeeklyScheduleSoap[] toSoapModels(WeeklySchedule[] models) {
		WeeklyScheduleSoap[] soapModels = new WeeklyScheduleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WeeklyScheduleSoap[][] toSoapModels(
		WeeklySchedule[][] models) {

		WeeklyScheduleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new WeeklyScheduleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WeeklyScheduleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WeeklyScheduleSoap[] toSoapModels(
		List<WeeklySchedule> models) {

		List<WeeklyScheduleSoap> soapModels = new ArrayList<WeeklyScheduleSoap>(
			models.size());

		for (WeeklySchedule model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WeeklyScheduleSoap[soapModels.size()]);
	}

	public WeeklyScheduleSoap() {
	}

	public WeeklySchedulePK getPrimaryKey() {
		return new WeeklySchedulePK(_schoolId, _dayId);
	}

	public void setPrimaryKey(WeeklySchedulePK pk) {
		setSchoolId(pk.schoolId);
		setDayId(pk.dayId);
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	public int getDayId() {
		return _dayId;
	}

	public void setDayId(int dayId) {
		_dayId = dayId;
	}

	private long _schoolId;
	private int _dayId;

}