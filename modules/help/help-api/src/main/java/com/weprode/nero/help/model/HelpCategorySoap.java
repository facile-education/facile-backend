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
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.help.service.http.HelpCategoryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class HelpCategorySoap implements Serializable {

	public static HelpCategorySoap toSoapModel(HelpCategory model) {
		HelpCategorySoap soapModel = new HelpCategorySoap();

		soapModel.setCategoryId(model.getCategoryId());
		soapModel.setCategoryName(model.getCategoryName());
		soapModel.setServiceId(model.getServiceId());
		soapModel.setPosition(model.getPosition());

		return soapModel;
	}

	public static HelpCategorySoap[] toSoapModels(HelpCategory[] models) {
		HelpCategorySoap[] soapModels = new HelpCategorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HelpCategorySoap[][] toSoapModels(HelpCategory[][] models) {
		HelpCategorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HelpCategorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new HelpCategorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HelpCategorySoap[] toSoapModels(List<HelpCategory> models) {
		List<HelpCategorySoap> soapModels = new ArrayList<HelpCategorySoap>(
			models.size());

		for (HelpCategory model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HelpCategorySoap[soapModels.size()]);
	}

	public HelpCategorySoap() {
	}

	public long getPrimaryKey() {
		return _categoryId;
	}

	public void setPrimaryKey(long pk) {
		setCategoryId(pk);
	}

	public long getCategoryId() {
		return _categoryId;
	}

	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	public String getCategoryName() {
		return _categoryName;
	}

	public void setCategoryName(String categoryName) {
		_categoryName = categoryName;
	}

	public long getServiceId() {
		return _serviceId;
	}

	public void setServiceId(long serviceId) {
		_serviceId = serviceId;
	}

	public int getPosition() {
		return _position;
	}

	public void setPosition(int position) {
		_position = position;
	}

	private long _categoryId;
	private String _categoryName;
	private long _serviceId;
	private int _position;

}