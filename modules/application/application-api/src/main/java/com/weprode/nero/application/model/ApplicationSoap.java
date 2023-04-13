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
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.application.service.http.ApplicationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ApplicationSoap implements Serializable {

	public static ApplicationSoap toSoapModel(Application model) {
		ApplicationSoap soapModel = new ApplicationSoap();

		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setApplicationName(model.getApplicationName());
		soapModel.setApplicationKey(model.getApplicationKey());
		soapModel.setCategoryName(model.getCategoryName());
		soapModel.setImage(model.getImage());
		soapModel.setHasCustomUrl(model.isHasCustomUrl());
		soapModel.setHasGlobalUrl(model.isHasGlobalUrl());
		soapModel.setGlobalUrl(model.getGlobalUrl());
		soapModel.setExportUser(model.isExportUser());
		soapModel.setExportParent(model.isExportParent());
		soapModel.setExportStudent(model.isExportStudent());
		soapModel.setExportTeacher(model.isExportTeacher());
		soapModel.setExportOther(model.isExportOther());
		soapModel.setMenuEntryId(model.getMenuEntryId());

		return soapModel;
	}

	public static ApplicationSoap[] toSoapModels(Application[] models) {
		ApplicationSoap[] soapModels = new ApplicationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ApplicationSoap[][] toSoapModels(Application[][] models) {
		ApplicationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ApplicationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ApplicationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ApplicationSoap[] toSoapModels(List<Application> models) {
		List<ApplicationSoap> soapModels = new ArrayList<ApplicationSoap>(
			models.size());

		for (Application model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ApplicationSoap[soapModels.size()]);
	}

	public ApplicationSoap() {
	}

	public long getPrimaryKey() {
		return _applicationId;
	}

	public void setPrimaryKey(long pk) {
		setApplicationId(pk);
	}

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
	}

	public String getApplicationName() {
		return _applicationName;
	}

	public void setApplicationName(String applicationName) {
		_applicationName = applicationName;
	}

	public String getApplicationKey() {
		return _applicationKey;
	}

	public void setApplicationKey(String applicationKey) {
		_applicationKey = applicationKey;
	}

	public String getCategoryName() {
		return _categoryName;
	}

	public void setCategoryName(String categoryName) {
		_categoryName = categoryName;
	}

	public String getImage() {
		return _image;
	}

	public void setImage(String image) {
		_image = image;
	}

	public boolean getHasCustomUrl() {
		return _hasCustomUrl;
	}

	public boolean isHasCustomUrl() {
		return _hasCustomUrl;
	}

	public void setHasCustomUrl(boolean hasCustomUrl) {
		_hasCustomUrl = hasCustomUrl;
	}

	public boolean getHasGlobalUrl() {
		return _hasGlobalUrl;
	}

	public boolean isHasGlobalUrl() {
		return _hasGlobalUrl;
	}

	public void setHasGlobalUrl(boolean hasGlobalUrl) {
		_hasGlobalUrl = hasGlobalUrl;
	}

	public String getGlobalUrl() {
		return _globalUrl;
	}

	public void setGlobalUrl(String globalUrl) {
		_globalUrl = globalUrl;
	}

	public boolean getExportUser() {
		return _exportUser;
	}

	public boolean isExportUser() {
		return _exportUser;
	}

	public void setExportUser(boolean exportUser) {
		_exportUser = exportUser;
	}

	public boolean getExportParent() {
		return _exportParent;
	}

	public boolean isExportParent() {
		return _exportParent;
	}

	public void setExportParent(boolean exportParent) {
		_exportParent = exportParent;
	}

	public boolean getExportStudent() {
		return _exportStudent;
	}

	public boolean isExportStudent() {
		return _exportStudent;
	}

	public void setExportStudent(boolean exportStudent) {
		_exportStudent = exportStudent;
	}

	public boolean getExportTeacher() {
		return _exportTeacher;
	}

	public boolean isExportTeacher() {
		return _exportTeacher;
	}

	public void setExportTeacher(boolean exportTeacher) {
		_exportTeacher = exportTeacher;
	}

	public boolean getExportOther() {
		return _exportOther;
	}

	public boolean isExportOther() {
		return _exportOther;
	}

	public void setExportOther(boolean exportOther) {
		_exportOther = exportOther;
	}

	public long getMenuEntryId() {
		return _menuEntryId;
	}

	public void setMenuEntryId(long menuEntryId) {
		_menuEntryId = menuEntryId;
	}

	private long _applicationId;
	private String _applicationName;
	private String _applicationKey;
	private String _categoryName;
	private String _image;
	private boolean _hasCustomUrl;
	private boolean _hasGlobalUrl;
	private String _globalUrl;
	private boolean _exportUser;
	private boolean _exportParent;
	private boolean _exportStudent;
	private boolean _exportTeacher;
	private boolean _exportOther;
	private long _menuEntryId;

}