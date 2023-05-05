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
public class AccessCategorySoap implements Serializable {

	public static AccessCategorySoap toSoapModel(AccessCategory model) {
		AccessCategorySoap soapModel = new AccessCategorySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCategoryId(model.getCategoryId());
		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setCategoryName(model.getCategoryName());
		soapModel.setPosition(model.getPosition());

		return soapModel;
	}

	public static AccessCategorySoap[] toSoapModels(AccessCategory[] models) {
		AccessCategorySoap[] soapModels = new AccessCategorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccessCategorySoap[][] toSoapModels(
		AccessCategory[][] models) {

		AccessCategorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new AccessCategorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccessCategorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccessCategorySoap[] toSoapModels(
		List<AccessCategory> models) {

		List<AccessCategorySoap> soapModels = new ArrayList<AccessCategorySoap>(
			models.size());

		for (AccessCategory model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccessCategorySoap[soapModels.size()]);
	}

	public AccessCategorySoap() {
	}

	public long getPrimaryKey() {
		return _categoryId;
	}

	public void setPrimaryKey(long pk) {
		setCategoryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCategoryId() {
		return _categoryId;
	}

	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	public String getCategoryName() {
		return _categoryName;
	}

	public void setCategoryName(String categoryName) {
		_categoryName = categoryName;
	}

	public int getPosition() {
		return _position;
	}

	public void setPosition(int position) {
		_position = position;
	}

	private String _uuid;
	private long _categoryId;
	private long _schoolId;
	private String _categoryName;
	private int _position;

}