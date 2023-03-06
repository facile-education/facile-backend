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

package com.weprode.nero.organization.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Marc Salvat
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class OrgDetailsSoap implements Serializable {

	public static OrgDetailsSoap toSoapModel(OrgDetails model) {
		OrgDetailsSoap soapModel = new OrgDetailsSoap();

		soapModel.setOrgId(model.getOrgId());
		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setOrgName(model.getOrgName());
		soapModel.setEduLevel(model.getEduLevel());
		soapModel.setRole(model.getRole());
		soapModel.setType(model.getType());
		soapModel.setIsArchive(model.isIsArchive());

		return soapModel;
	}

	public static OrgDetailsSoap[] toSoapModels(OrgDetails[] models) {
		OrgDetailsSoap[] soapModels = new OrgDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OrgDetailsSoap[][] toSoapModels(OrgDetails[][] models) {
		OrgDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OrgDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OrgDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OrgDetailsSoap[] toSoapModels(List<OrgDetails> models) {
		List<OrgDetailsSoap> soapModels = new ArrayList<OrgDetailsSoap>(
			models.size());

		for (OrgDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OrgDetailsSoap[soapModels.size()]);
	}

	public OrgDetailsSoap() {
	}

	public long getPrimaryKey() {
		return _orgId;
	}

	public void setPrimaryKey(long pk) {
		setOrgId(pk);
	}

	public long getOrgId() {
		return _orgId;
	}

	public void setOrgId(long orgId) {
		_orgId = orgId;
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	public String getOrgName() {
		return _orgName;
	}

	public void setOrgName(String orgName) {
		_orgName = orgName;
	}

	public String getEduLevel() {
		return _eduLevel;
	}

	public void setEduLevel(String eduLevel) {
		_eduLevel = eduLevel;
	}

	public int getRole() {
		return _role;
	}

	public void setRole(int role) {
		_role = role;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public boolean getIsArchive() {
		return _isArchive;
	}

	public boolean isIsArchive() {
		return _isArchive;
	}

	public void setIsArchive(boolean isArchive) {
		_isArchive = isArchive;
	}

	private long _orgId;
	private long _schoolId;
	private String _orgName;
	private String _eduLevel;
	private int _role;
	private int _type;
	private boolean _isArchive;

}