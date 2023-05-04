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

package com.weprode.nero.about.model;

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
public class UpdateInformationSoap implements Serializable {

	public static UpdateInformationSoap toSoapModel(UpdateInformation model) {
		UpdateInformationSoap soapModel = new UpdateInformationSoap();

		soapModel.setUpdateInfoId(model.getUpdateInfoId());
		soapModel.setDescription(model.getDescription());
		soapModel.setModifyDate(model.getModifyDate());

		return soapModel;
	}

	public static UpdateInformationSoap[] toSoapModels(
		UpdateInformation[] models) {

		UpdateInformationSoap[] soapModels =
			new UpdateInformationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UpdateInformationSoap[][] toSoapModels(
		UpdateInformation[][] models) {

		UpdateInformationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new UpdateInformationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UpdateInformationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UpdateInformationSoap[] toSoapModels(
		List<UpdateInformation> models) {

		List<UpdateInformationSoap> soapModels =
			new ArrayList<UpdateInformationSoap>(models.size());

		for (UpdateInformation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UpdateInformationSoap[soapModels.size()]);
	}

	public UpdateInformationSoap() {
	}

	public long getPrimaryKey() {
		return _updateInfoId;
	}

	public void setPrimaryKey(long pk) {
		setUpdateInfoId(pk);
	}

	public long getUpdateInfoId() {
		return _updateInfoId;
	}

	public void setUpdateInfoId(long updateInfoId) {
		_updateInfoId = updateInfoId;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getModifyDate() {
		return _modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		_modifyDate = modifyDate;
	}

	private long _updateInfoId;
	private String _description;
	private Date _modifyDate;

}