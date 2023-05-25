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

package com.weprode.nero.progression.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.progression.service.http.ProgressionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ProgressionSoap implements Serializable {

	public static ProgressionSoap toSoapModel(Progression model) {
		ProgressionSoap soapModel = new ProgressionSoap();

		soapModel.setProgressionId(model.getProgressionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setTeacherId(model.getTeacherId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setVolee(model.getVolee());
		soapModel.setSubjectId(model.getSubjectId());
		soapModel.setColor(model.getColor());

		return soapModel;
	}

	public static ProgressionSoap[] toSoapModels(Progression[] models) {
		ProgressionSoap[] soapModels = new ProgressionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProgressionSoap[][] toSoapModels(Progression[][] models) {
		ProgressionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProgressionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProgressionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProgressionSoap[] toSoapModels(List<Progression> models) {
		List<ProgressionSoap> soapModels = new ArrayList<ProgressionSoap>(
			models.size());

		for (Progression model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProgressionSoap[soapModels.size()]);
	}

	public ProgressionSoap() {
	}

	public long getPrimaryKey() {
		return _progressionId;
	}

	public void setPrimaryKey(long pk) {
		setProgressionId(pk);
	}

	public long getProgressionId() {
		return _progressionId;
	}

	public void setProgressionId(long progressionId) {
		_progressionId = progressionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getTeacherId() {
		return _teacherId;
	}

	public void setTeacherId(long teacherId) {
		_teacherId = teacherId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getVolee() {
		return _volee;
	}

	public void setVolee(String volee) {
		_volee = volee;
	}

	public long getSubjectId() {
		return _subjectId;
	}

	public void setSubjectId(long subjectId) {
		_subjectId = subjectId;
	}

	public String getColor() {
		return _color;
	}

	public void setColor(String color) {
		_color = color;
	}

	private long _progressionId;
	private long _companyId;
	private long _teacherId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private String _volee;
	private long _subjectId;
	private String _color;

}