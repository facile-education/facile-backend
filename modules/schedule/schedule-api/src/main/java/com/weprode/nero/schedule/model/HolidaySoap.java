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
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class HolidaySoap implements Serializable {

	public static HolidaySoap toSoapModel(Holiday model) {
		HolidaySoap soapModel = new HolidaySoap();

		soapModel.setHolidayId(model.getHolidayId());
		soapModel.setName(model.getName());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());

		return soapModel;
	}

	public static HolidaySoap[] toSoapModels(Holiday[] models) {
		HolidaySoap[] soapModels = new HolidaySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HolidaySoap[][] toSoapModels(Holiday[][] models) {
		HolidaySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HolidaySoap[models.length][models[0].length];
		}
		else {
			soapModels = new HolidaySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HolidaySoap[] toSoapModels(List<Holiday> models) {
		List<HolidaySoap> soapModels = new ArrayList<HolidaySoap>(
			models.size());

		for (Holiday model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HolidaySoap[soapModels.size()]);
	}

	public HolidaySoap() {
	}

	public long getPrimaryKey() {
		return _holidayId;
	}

	public void setPrimaryKey(long pk) {
		setHolidayId(pk);
	}

	public long getHolidayId() {
		return _holidayId;
	}

	public void setHolidayId(long holidayId) {
		_holidayId = holidayId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
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

	private long _holidayId;
	private String _name;
	private Date _startDate;
	private Date _endDate;

}