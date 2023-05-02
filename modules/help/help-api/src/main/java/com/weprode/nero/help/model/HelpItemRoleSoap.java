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

package com.weprode.nero.help.model;

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
public class HelpItemRoleSoap implements Serializable {

	public static HelpItemRoleSoap toSoapModel(HelpItemRole model) {
		HelpItemRoleSoap soapModel = new HelpItemRoleSoap();

		soapModel.setHelpItemRoleId(model.getHelpItemRoleId());
		soapModel.setItemId(model.getItemId());
		soapModel.setRoleId(model.getRoleId());

		return soapModel;
	}

	public static HelpItemRoleSoap[] toSoapModels(HelpItemRole[] models) {
		HelpItemRoleSoap[] soapModels = new HelpItemRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HelpItemRoleSoap[][] toSoapModels(HelpItemRole[][] models) {
		HelpItemRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HelpItemRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HelpItemRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HelpItemRoleSoap[] toSoapModels(List<HelpItemRole> models) {
		List<HelpItemRoleSoap> soapModels = new ArrayList<HelpItemRoleSoap>(
			models.size());

		for (HelpItemRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HelpItemRoleSoap[soapModels.size()]);
	}

	public HelpItemRoleSoap() {
	}

	public long getPrimaryKey() {
		return _helpItemRoleId;
	}

	public void setPrimaryKey(long pk) {
		setHelpItemRoleId(pk);
	}

	public long getHelpItemRoleId() {
		return _helpItemRoleId;
	}

	public void setHelpItemRoleId(long helpItemRoleId) {
		_helpItemRoleId = helpItemRoleId;
	}

	public long getItemId() {
		return _itemId;
	}

	public void setItemId(long itemId) {
		_itemId = itemId;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	private long _helpItemRoleId;
	private long _itemId;
	private long _roleId;

}