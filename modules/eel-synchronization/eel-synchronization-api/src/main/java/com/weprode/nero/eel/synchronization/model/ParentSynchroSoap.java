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

package com.weprode.nero.eel.synchronization.model;

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
public class ParentSynchroSoap implements Serializable {

	public static ParentSynchroSoap toSoapModel(ParentSynchro model) {
		ParentSynchroSoap soapModel = new ParentSynchroSoap();

		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setFileName(model.getFileName());
		soapModel.setLineCount(model.getLineCount());
		soapModel.setErrorCount(model.getErrorCount());

		return soapModel;
	}

	public static ParentSynchroSoap[] toSoapModels(ParentSynchro[] models) {
		ParentSynchroSoap[] soapModels = new ParentSynchroSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ParentSynchroSoap[][] toSoapModels(ParentSynchro[][] models) {
		ParentSynchroSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ParentSynchroSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ParentSynchroSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ParentSynchroSoap[] toSoapModels(List<ParentSynchro> models) {
		List<ParentSynchroSoap> soapModels = new ArrayList<ParentSynchroSoap>(
			models.size());

		for (ParentSynchro model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ParentSynchroSoap[soapModels.size()]);
	}

	public ParentSynchroSoap() {
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

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public long getLineCount() {
		return _lineCount;
	}

	public void setLineCount(long lineCount) {
		_lineCount = lineCount;
	}

	public long getErrorCount() {
		return _errorCount;
	}

	public void setErrorCount(long errorCount) {
		_errorCount = errorCount;
	}

	private long _schoolId;
	private Date _startDate;
	private Date _endDate;
	private String _fileName;
	private long _lineCount;
	private long _errorCount;

}