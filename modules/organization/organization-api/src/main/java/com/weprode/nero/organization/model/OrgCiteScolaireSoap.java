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
public class OrgCiteScolaireSoap implements Serializable {

	public static OrgCiteScolaireSoap toSoapModel(OrgCiteScolaire model) {
		OrgCiteScolaireSoap soapModel = new OrgCiteScolaireSoap();

		soapModel.setParentENTStructureUAI(model.getParentENTStructureUAI());
		soapModel.setChildENTStructureUAI(model.getChildENTStructureUAI());

		return soapModel;
	}

	public static OrgCiteScolaireSoap[] toSoapModels(OrgCiteScolaire[] models) {
		OrgCiteScolaireSoap[] soapModels =
			new OrgCiteScolaireSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OrgCiteScolaireSoap[][] toSoapModels(
		OrgCiteScolaire[][] models) {

		OrgCiteScolaireSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new OrgCiteScolaireSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OrgCiteScolaireSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OrgCiteScolaireSoap[] toSoapModels(
		List<OrgCiteScolaire> models) {

		List<OrgCiteScolaireSoap> soapModels =
			new ArrayList<OrgCiteScolaireSoap>(models.size());

		for (OrgCiteScolaire model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OrgCiteScolaireSoap[soapModels.size()]);
	}

	public OrgCiteScolaireSoap() {
	}

	public String getPrimaryKey() {
		return _childENTStructureUAI;
	}

	public void setPrimaryKey(String pk) {
		setChildENTStructureUAI(pk);
	}

	public String getParentENTStructureUAI() {
		return _parentENTStructureUAI;
	}

	public void setParentENTStructureUAI(String parentENTStructureUAI) {
		_parentENTStructureUAI = parentENTStructureUAI;
	}

	public String getChildENTStructureUAI() {
		return _childENTStructureUAI;
	}

	public void setChildENTStructureUAI(String childENTStructureUAI) {
		_childENTStructureUAI = childENTStructureUAI;
	}

	private String _parentENTStructureUAI;
	private String _childENTStructureUAI;

}