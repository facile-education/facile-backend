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

package com.weprode.nero.user.model;

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
public class LDAPMappingSoap implements Serializable {

	public static LDAPMappingSoap toSoapModel(LDAPMapping model) {
		LDAPMappingSoap soapModel = new LDAPMappingSoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setEntPersonJointure(model.getEntPersonJointure());
		soapModel.setUID(model.getUID());
		soapModel.setINE(model.getINE());
		soapModel.setEntEleveStructRattachId(
			model.getEntEleveStructRattachId());

		return soapModel;
	}

	public static LDAPMappingSoap[] toSoapModels(LDAPMapping[] models) {
		LDAPMappingSoap[] soapModels = new LDAPMappingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LDAPMappingSoap[][] toSoapModels(LDAPMapping[][] models) {
		LDAPMappingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LDAPMappingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LDAPMappingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LDAPMappingSoap[] toSoapModels(List<LDAPMapping> models) {
		List<LDAPMappingSoap> soapModels = new ArrayList<LDAPMappingSoap>(
			models.size());

		for (LDAPMapping model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LDAPMappingSoap[soapModels.size()]);
	}

	public LDAPMappingSoap() {
	}

	public long getPrimaryKey() {
		return _UserId;
	}

	public void setPrimaryKey(long pk) {
		setUserId(pk);
	}

	public long getUserId() {
		return _UserId;
	}

	public void setUserId(long UserId) {
		_UserId = UserId;
	}

	public String getEntPersonJointure() {
		return _EntPersonJointure;
	}

	public void setEntPersonJointure(String EntPersonJointure) {
		_EntPersonJointure = EntPersonJointure;
	}

	public String getUID() {
		return _UID;
	}

	public void setUID(String UID) {
		_UID = UID;
	}

	public String getINE() {
		return _INE;
	}

	public void setINE(String INE) {
		_INE = INE;
	}

	public String getEntEleveStructRattachId() {
		return _EntEleveStructRattachId;
	}

	public void setEntEleveStructRattachId(String EntEleveStructRattachId) {
		_EntEleveStructRattachId = EntEleveStructRattachId;
	}

	private long _UserId;
	private String _EntPersonJointure;

	private String _UID;

	private String _INE;

	private String _EntEleveStructRattachId;

}