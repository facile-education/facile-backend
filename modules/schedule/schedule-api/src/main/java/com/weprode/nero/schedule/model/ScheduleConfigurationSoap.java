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

		soapModel.setConfigId(model.getConfigId());
		soapModel.setProjectStartDate(model.getProjectStartDate());
		soapModel.setSchoolYearStartDate(model.getSchoolYearStartDate());
		soapModel.setSchoolYearSemesterDate(model.getSchoolYearSemesterDate());
		soapModel.setSchoolYearEndDate(model.getSchoolYearEndDate());
		soapModel.setH1Weeks(model.getH1Weeks());
		soapModel.setH2Weeks(model.getH2Weeks());

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
		return _configId;
	}

	public void setPrimaryKey(long pk) {
		setConfigId(pk);
	}

	public long getConfigId() {
		return _configId;
	}

	public void setConfigId(long configId) {
		_configId = configId;
	}

	public Date getProjectStartDate() {
		return _projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		_projectStartDate = projectStartDate;
	}

	public Date getSchoolYearStartDate() {
		return _schoolYearStartDate;
	}

	public void setSchoolYearStartDate(Date schoolYearStartDate) {
		_schoolYearStartDate = schoolYearStartDate;
	}

	public Date getSchoolYearSemesterDate() {
		return _schoolYearSemesterDate;
	}

	public void setSchoolYearSemesterDate(Date schoolYearSemesterDate) {
		_schoolYearSemesterDate = schoolYearSemesterDate;
	}

	public Date getSchoolYearEndDate() {
		return _schoolYearEndDate;
	}

	public void setSchoolYearEndDate(Date schoolYearEndDate) {
		_schoolYearEndDate = schoolYearEndDate;
	}

	public String getH1Weeks() {
		return _h1Weeks;
	}

	public void setH1Weeks(String h1Weeks) {
		_h1Weeks = h1Weeks;
	}

	public String getH2Weeks() {
		return _h2Weeks;
	}

	public void setH2Weeks(String h2Weeks) {
		_h2Weeks = h2Weeks;
	}

	private long _configId;
	private Date _projectStartDate;
	private Date _schoolYearStartDate;
	private Date _schoolYearSemesterDate;
	private Date _schoolYearEndDate;
	private String _h1Weeks;
	private String _h2Weeks;

}