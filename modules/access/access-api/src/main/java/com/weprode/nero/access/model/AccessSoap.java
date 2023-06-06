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
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.access.service.http.AccessServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class AccessSoap implements Serializable {

	public static AccessSoap toSoapModel(Access model) {
		AccessSoap soapModel = new AccessSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAccessId(model.getAccessId());
		soapModel.setCategoryId(model.getCategoryId());
		soapModel.setTitle(model.getTitle());
		soapModel.setType(model.getType());
		soapModel.setExternalUrl(model.getExternalUrl());
		soapModel.setFolderId(model.getFolderId());
		soapModel.setFileId(model.getFileId());
		soapModel.setThumbnailId(model.getThumbnailId());
		soapModel.setPosition(model.getPosition());

		return soapModel;
	}

	public static AccessSoap[] toSoapModels(Access[] models) {
		AccessSoap[] soapModels = new AccessSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccessSoap[][] toSoapModels(Access[][] models) {
		AccessSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccessSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccessSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccessSoap[] toSoapModels(List<Access> models) {
		List<AccessSoap> soapModels = new ArrayList<AccessSoap>(models.size());

		for (Access model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccessSoap[soapModels.size()]);
	}

	public AccessSoap() {
	}

	public long getPrimaryKey() {
		return _accessId;
	}

	public void setPrimaryKey(long pk) {
		setAccessId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAccessId() {
		return _accessId;
	}

	public void setAccessId(long accessId) {
		_accessId = accessId;
	}

	public long getCategoryId() {
		return _categoryId;
	}

	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String getExternalUrl() {
		return _externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		_externalUrl = externalUrl;
	}

	public long getFolderId() {
		return _folderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public long getFileId() {
		return _fileId;
	}

	public void setFileId(long fileId) {
		_fileId = fileId;
	}

	public long getThumbnailId() {
		return _thumbnailId;
	}

	public void setThumbnailId(long thumbnailId) {
		_thumbnailId = thumbnailId;
	}

	public int getPosition() {
		return _position;
	}

	public void setPosition(int position) {
		_position = position;
	}

	private String _uuid;
	private long _accessId;
	private long _categoryId;
	private String _title;
	private int _type;
	private String _externalUrl;
	private long _folderId;
	private long _fileId;
	private long _thumbnailId;
	private int _position;

}