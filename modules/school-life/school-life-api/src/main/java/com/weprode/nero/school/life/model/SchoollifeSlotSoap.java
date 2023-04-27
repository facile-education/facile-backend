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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.school.life.service.http.SchoollifeSlotServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class SchoollifeSlotSoap implements Serializable {

	public static SchoollifeSlotSoap toSoapModel(SchoollifeSlot model) {
		SchoollifeSlotSoap soapModel = new SchoollifeSlotSoap();

		soapModel.setSchoollifeSlotId(model.getSchoollifeSlotId());
		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setDay(model.getDay());
		soapModel.setStartHour(model.getStartHour());
		soapModel.setEndHour(model.getEndHour());
		soapModel.setTeacherId(model.getTeacherId());
		soapModel.setType(model.getType());
		soapModel.setRoom(model.getRoom());
		soapModel.setCapacity(model.getCapacity());

		return soapModel;
	}

	public static SchoollifeSlotSoap[] toSoapModels(SchoollifeSlot[] models) {
		SchoollifeSlotSoap[] soapModels = new SchoollifeSlotSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SchoollifeSlotSoap[][] toSoapModels(
		SchoollifeSlot[][] models) {

		SchoollifeSlotSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new SchoollifeSlotSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SchoollifeSlotSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SchoollifeSlotSoap[] toSoapModels(
		List<SchoollifeSlot> models) {

		List<SchoollifeSlotSoap> soapModels = new ArrayList<SchoollifeSlotSoap>(
			models.size());

		for (SchoollifeSlot model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SchoollifeSlotSoap[soapModels.size()]);
	}

	public SchoollifeSlotSoap() {
	}

	public long getPrimaryKey() {
		return _schoollifeSlotId;
	}

	public void setPrimaryKey(long pk) {
		setSchoollifeSlotId(pk);
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

	public int getDay() {
		return _day;
	}

	public void setDay(int day) {
		_day = day;
	}

	public String getStartHour() {
		return _startHour;
	}

	public void setStartHour(String startHour) {
		_startHour = startHour;
	}

	public String getEndHour() {
		return _endHour;
	}

	public void setEndHour(String endHour) {
		_endHour = endHour;
	}

	public long getTeacherId() {
		return _teacherId;
	}

	public void setTeacherId(long teacherId) {
		_teacherId = teacherId;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String getRoom() {
		return _room;
	}

	public void setRoom(String room) {
		_room = room;
	}

	public int getCapacity() {
		return _capacity;
	}

	public void setCapacity(int capacity) {
		_capacity = capacity;
	}

	private long _schoollifeSlotId;
	private long _schoolId;
	private int _day;
	private String _startHour;
	private String _endHour;
	private long _teacherId;
	private int _type;
	private String _room;
	private int _capacity;

}