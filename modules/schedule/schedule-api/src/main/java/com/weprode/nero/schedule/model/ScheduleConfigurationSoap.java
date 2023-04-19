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
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.schedule.service.http.ScheduleConfigurationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ScheduleConfigurationSoap implements Serializable {

	public static ScheduleConfigurationSoap toSoapModel(
		ScheduleConfiguration model) {

		ScheduleConfigurationSoap soapModel = new ScheduleConfigurationSoap();

		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setStartDayTime(model.getStartDayTime());
		soapModel.setEndDayTime(model.getEndDayTime());
		soapModel.setStartSessionsDate(model.getStartSessionsDate());
		soapModel.setEndSessionsDate(model.getEndSessionsDate());

		return soapModel;
	}

	public static ScheduleConfigurationSoap[] toSoapModels(
		ScheduleConfiguration[] models) {

		ScheduleConfigurationSoap[] soapModels =
			new ScheduleConfigurationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ScheduleConfigurationSoap[][] toSoapModels(
		ScheduleConfiguration[][] models) {

		ScheduleConfigurationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ScheduleConfigurationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ScheduleConfigurationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ScheduleConfigurationSoap[] toSoapModels(
		List<ScheduleConfiguration> models) {

		List<ScheduleConfigurationSoap> soapModels =
			new ArrayList<ScheduleConfigurationSoap>(models.size());

		for (ScheduleConfiguration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new ScheduleConfigurationSoap[soapModels.size()]);
	}

	public ScheduleConfigurationSoap() {
	}

	public long getPrimaryKey() {
		return _schoolId;
	}

	public void setPrimaryKey(long pk) {
		setSchoolId(pk);
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	public String getStartDayTime() {
		return _startDayTime;
	}

	public void setStartDayTime(String startDayTime) {
		_startDayTime = startDayTime;
	}

	public String getEndDayTime() {
		return _endDayTime;
	}

	public void setEndDayTime(String endDayTime) {
		_endDayTime = endDayTime;
	}

	public Date getStartSessionsDate() {
		return _startSessionsDate;
	}

	public void setStartSessionsDate(Date startSessionsDate) {
		_startSessionsDate = startSessionsDate;
	}

	public Date getEndSessionsDate() {
		return _endSessionsDate;
	}

	public void setEndSessionsDate(Date endSessionsDate) {
		_endSessionsDate = endSessionsDate;
	}

	private long _schoolId;
	private String _startDayTime;
	private String _endDayTime;
	private Date _startSessionsDate;
	private Date _endSessionsDate;

}