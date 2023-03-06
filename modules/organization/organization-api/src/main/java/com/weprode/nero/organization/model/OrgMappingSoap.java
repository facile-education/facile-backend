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
public class OrgMappingSoap implements Serializable {

	public static OrgMappingSoap toSoapModel(OrgMapping model) {
		OrgMappingSoap soapModel = new OrgMappingSoap();

		soapModel.setOrganizationId(model.getOrganizationId());
		soapModel.setEntStructureUAI(model.getEntStructureUAI());

		return soapModel;
	}

	public static OrgMappingSoap[] toSoapModels(OrgMapping[] models) {
		OrgMappingSoap[] soapModels = new OrgMappingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OrgMappingSoap[][] toSoapModels(OrgMapping[][] models) {
		OrgMappingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OrgMappingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OrgMappingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OrgMappingSoap[] toSoapModels(List<OrgMapping> models) {
		List<OrgMappingSoap> soapModels = new ArrayList<OrgMappingSoap>(
			models.size());

		for (OrgMapping model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OrgMappingSoap[soapModels.size()]);
	}

	public OrgMappingSoap() {
	}

	public String getPrimaryKey() {
		return _entStructureUAI;
	}

	public void setPrimaryKey(String pk) {
		setEntStructureUAI(pk);
	}

	public long getOrganizationId() {
		return _organizationId;
	}

	public void setOrganizationId(long organizationId) {
		_organizationId = organizationId;
	}

	public String getEntStructureUAI() {
		return _entStructureUAI;
	}

	public void setEntStructureUAI(String entStructureUAI) {
		_entStructureUAI = entStructureUAI;
	}

	private long _organizationId;
	private String _entStructureUAI;

}