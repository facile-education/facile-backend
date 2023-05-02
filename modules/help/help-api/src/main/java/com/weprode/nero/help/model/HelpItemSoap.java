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
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.help.service.http.HelpItemServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class HelpItemSoap implements Serializable {

	public static HelpItemSoap toSoapModel(HelpItem model) {
		HelpItemSoap soapModel = new HelpItemSoap();

		soapModel.setItemId(model.getItemId());
		soapModel.setCategoryId(model.getCategoryId());
		soapModel.setItemName(model.getItemName());
		soapModel.setVideoURL(model.getVideoURL());
		soapModel.setVideoDescription(model.getVideoDescription());
		soapModel.setManual(model.getManual());
		soapModel.setPosition(model.getPosition());
		soapModel.setLanguage(model.getLanguage());
		soapModel.setIsManagement(model.isIsManagement());

		return soapModel;
	}

	public static HelpItemSoap[] toSoapModels(HelpItem[] models) {
		HelpItemSoap[] soapModels = new HelpItemSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HelpItemSoap[][] toSoapModels(HelpItem[][] models) {
		HelpItemSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HelpItemSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HelpItemSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HelpItemSoap[] toSoapModels(List<HelpItem> models) {
		List<HelpItemSoap> soapModels = new ArrayList<HelpItemSoap>(
			models.size());

		for (HelpItem model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HelpItemSoap[soapModels.size()]);
	}

	public HelpItemSoap() {
	}

	public long getPrimaryKey() {
		return _itemId;
	}

	public void setPrimaryKey(long pk) {
		setItemId(pk);
	}

	public long getItemId() {
		return _itemId;
	}

	public void setItemId(long itemId) {
		_itemId = itemId;
	}

	public long getCategoryId() {
		return _categoryId;
	}

	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getVideoURL() {
		return _videoURL;
	}

	public void setVideoURL(String videoURL) {
		_videoURL = videoURL;
	}

	public String getVideoDescription() {
		return _videoDescription;
	}

	public void setVideoDescription(String videoDescription) {
		_videoDescription = videoDescription;
	}

	public String getManual() {
		return _manual;
	}

	public void setManual(String manual) {
		_manual = manual;
	}

	public int getPosition() {
		return _position;
	}

	public void setPosition(int position) {
		_position = position;
	}

	public String getLanguage() {
		return _language;
	}

	public void setLanguage(String language) {
		_language = language;
	}

	public boolean getIsManagement() {
		return _isManagement;
	}

	public boolean isIsManagement() {
		return _isManagement;
	}

	public void setIsManagement(boolean isManagement) {
		_isManagement = isManagement;
	}

	private long _itemId;
	private long _categoryId;
	private String _itemName;
	private String _videoURL;
	private String _videoDescription;
	private String _manual;
	private int _position;
	private String _language;
	private boolean _isManagement;

}