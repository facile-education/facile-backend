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
public class EntVersionSoap implements Serializable {

	public static EntVersionSoap toSoapModel(EntVersion model) {
		EntVersionSoap soapModel = new EntVersionSoap();

		soapModel.setEntVersionId(model.getEntVersionId());
		soapModel.setVersion(model.getVersion());
		soapModel.setDetails(model.getDetails());
		soapModel.setVersionDate(model.getVersionDate());
		soapModel.setIsLast(model.isIsLast());

		return soapModel;
	}

	public static EntVersionSoap[] toSoapModels(EntVersion[] models) {
		EntVersionSoap[] soapModels = new EntVersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EntVersionSoap[][] toSoapModels(EntVersion[][] models) {
		EntVersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EntVersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EntVersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EntVersionSoap[] toSoapModels(List<EntVersion> models) {
		List<EntVersionSoap> soapModels = new ArrayList<EntVersionSoap>(
			models.size());

		for (EntVersion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EntVersionSoap[soapModels.size()]);
	}

	public EntVersionSoap() {
	}

	public long getPrimaryKey() {
		return _entVersionId;
	}

	public void setPrimaryKey(long pk) {
		setEntVersionId(pk);
	}

	public long getEntVersionId() {
		return _entVersionId;
	}

	public void setEntVersionId(long entVersionId) {
		_entVersionId = entVersionId;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	public String getDetails() {
		return _details;
	}

	public void setDetails(String details) {
		_details = details;
	}

	public Date getVersionDate() {
		return _versionDate;
	}

	public void setVersionDate(Date versionDate) {
		_versionDate = versionDate;
	}

	public boolean getIsLast() {
		return _isLast;
	}

	public boolean isIsLast() {
		return _isLast;
	}

	public void setIsLast(boolean isLast) {
		_isLast = isLast;
	}

	private long _entVersionId;
	private String _version;
	private String _details;
	private Date _versionDate;
	private boolean _isLast;

}