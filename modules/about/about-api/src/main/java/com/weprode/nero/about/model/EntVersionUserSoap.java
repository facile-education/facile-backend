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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class EntVersionUserSoap implements Serializable {

	public static EntVersionUserSoap toSoapModel(EntVersionUser model) {
		EntVersionUserSoap soapModel = new EntVersionUserSoap();

		soapModel.setVersionUserId(model.getVersionUserId());
		soapModel.setEntVersionId(model.getEntVersionId());
		soapModel.setUserId(model.getUserId());

		return soapModel;
	}

	public static EntVersionUserSoap[] toSoapModels(EntVersionUser[] models) {
		EntVersionUserSoap[] soapModels = new EntVersionUserSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EntVersionUserSoap[][] toSoapModels(
		EntVersionUser[][] models) {

		EntVersionUserSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new EntVersionUserSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EntVersionUserSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EntVersionUserSoap[] toSoapModels(
		List<EntVersionUser> models) {

		List<EntVersionUserSoap> soapModels = new ArrayList<EntVersionUserSoap>(
			models.size());

		for (EntVersionUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EntVersionUserSoap[soapModels.size()]);
	}

	public EntVersionUserSoap() {
	}

	public long getPrimaryKey() {
		return _versionUserId;
	}

	public void setPrimaryKey(long pk) {
		setVersionUserId(pk);
	}

	public long getVersionUserId() {
		return _versionUserId;
	}

	public void setVersionUserId(long versionUserId) {
		_versionUserId = versionUserId;
	}

	public long getEntVersionId() {
		return _entVersionId;
	}

	public void setEntVersionId(long entVersionId) {
		_entVersionId = entVersionId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private long _versionUserId;
	private long _entVersionId;
	private long _userId;

}