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

package com.weprode.nero.access.model;

import com.weprode.nero.access.service.persistence.AccessProfilePK;

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
public class AccessProfileSoap implements Serializable {

	public static AccessProfileSoap toSoapModel(AccessProfile model) {
		AccessProfileSoap soapModel = new AccessProfileSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAccessId(model.getAccessId());
		soapModel.setRoleId(model.getRoleId());

		return soapModel;
	}

	public static AccessProfileSoap[] toSoapModels(AccessProfile[] models) {
		AccessProfileSoap[] soapModels = new AccessProfileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccessProfileSoap[][] toSoapModels(AccessProfile[][] models) {
		AccessProfileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccessProfileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccessProfileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccessProfileSoap[] toSoapModels(List<AccessProfile> models) {
		List<AccessProfileSoap> soapModels = new ArrayList<AccessProfileSoap>(
			models.size());

		for (AccessProfile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccessProfileSoap[soapModels.size()]);
	}

	public AccessProfileSoap() {
	}

	public AccessProfilePK getPrimaryKey() {
		return new AccessProfilePK(_accessId, _roleId);
	}

	public void setPrimaryKey(AccessProfilePK pk) {
		setAccessId(pk.accessId);
		setRoleId(pk.roleId);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAccessId() {
		return _accessId;
	}

	public void setAccessId(long accessId) {
		_accessId = accessId;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	private String _uuid;
	private long _accessId;
	private long _roleId;

}