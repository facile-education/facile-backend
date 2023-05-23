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
public class ClassCoursMappingSoap implements Serializable {

	public static ClassCoursMappingSoap toSoapModel(ClassCoursMapping model) {
		ClassCoursMappingSoap soapModel = new ClassCoursMappingSoap();

		soapModel.setMappingId(model.getMappingId());
		soapModel.setClassOrgId(model.getClassOrgId());
		soapModel.setCoursOrgId(model.getCoursOrgId());

		return soapModel;
	}

	public static ClassCoursMappingSoap[] toSoapModels(
		ClassCoursMapping[] models) {

		ClassCoursMappingSoap[] soapModels =
			new ClassCoursMappingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ClassCoursMappingSoap[][] toSoapModels(
		ClassCoursMapping[][] models) {

		ClassCoursMappingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ClassCoursMappingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ClassCoursMappingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ClassCoursMappingSoap[] toSoapModels(
		List<ClassCoursMapping> models) {

		List<ClassCoursMappingSoap> soapModels =
			new ArrayList<ClassCoursMappingSoap>(models.size());

		for (ClassCoursMapping model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ClassCoursMappingSoap[soapModels.size()]);
	}

	public ClassCoursMappingSoap() {
	}

	public long getPrimaryKey() {
		return _mappingId;
	}

	public void setPrimaryKey(long pk) {
		setMappingId(pk);
	}

	public long getMappingId() {
		return _mappingId;
	}

	public void setMappingId(long mappingId) {
		_mappingId = mappingId;
	}

	public long getClassOrgId() {
		return _classOrgId;
	}

	public void setClassOrgId(long classOrgId) {
		_classOrgId = classOrgId;
	}

	public long getCoursOrgId() {
		return _coursOrgId;
	}

	public void setCoursOrgId(long coursOrgId) {
		_coursOrgId = coursOrgId;
	}

	private long _mappingId;
	private long _classOrgId;
	private long _coursOrgId;

}