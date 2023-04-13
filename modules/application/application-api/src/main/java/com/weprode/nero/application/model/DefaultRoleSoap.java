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

package com.weprode.nero.application.model;

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
public class DefaultRoleSoap implements Serializable {

	public static DefaultRoleSoap toSoapModel(DefaultRole model) {
		DefaultRoleSoap soapModel = new DefaultRoleSoap();

		soapModel.setDefaultRoleId(model.getDefaultRoleId());
		soapModel.setRoleId(model.getRoleId());
		soapModel.setApplicationId(model.getApplicationId());

		return soapModel;
	}

	public static DefaultRoleSoap[] toSoapModels(DefaultRole[] models) {
		DefaultRoleSoap[] soapModels = new DefaultRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DefaultRoleSoap[][] toSoapModels(DefaultRole[][] models) {
		DefaultRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DefaultRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DefaultRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DefaultRoleSoap[] toSoapModels(List<DefaultRole> models) {
		List<DefaultRoleSoap> soapModels = new ArrayList<DefaultRoleSoap>(
			models.size());

		for (DefaultRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DefaultRoleSoap[soapModels.size()]);
	}

	public DefaultRoleSoap() {
	}

	public long getPrimaryKey() {
		return _defaultRoleId;
	}

	public void setPrimaryKey(long pk) {
		setDefaultRoleId(pk);
	}

	public long getDefaultRoleId() {
		return _defaultRoleId;
	}

	public void setDefaultRoleId(long defaultRoleId) {
		_defaultRoleId = defaultRoleId;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
	}

	private long _defaultRoleId;
	private long _roleId;
	private long _applicationId;

}