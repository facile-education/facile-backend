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

package com.weprode.nero.document.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class EditionLockSoap implements Serializable {

	public static EditionLockSoap toSoapModel(EditionLock model) {
		EditionLockSoap soapModel = new EditionLockSoap();

		soapModel.setFileId(model.getFileId());
		soapModel.setUserId(model.getUserId());
		soapModel.setEditionDate(model.getEditionDate());

		return soapModel;
	}

	public static EditionLockSoap[] toSoapModels(EditionLock[] models) {
		EditionLockSoap[] soapModels = new EditionLockSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EditionLockSoap[][] toSoapModels(EditionLock[][] models) {
		EditionLockSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EditionLockSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EditionLockSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EditionLockSoap[] toSoapModels(List<EditionLock> models) {
		List<EditionLockSoap> soapModels = new ArrayList<EditionLockSoap>(
			models.size());

		for (EditionLock model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EditionLockSoap[soapModels.size()]);
	}

	public EditionLockSoap() {
	}

	public long getPrimaryKey() {
		return _fileId;
	}

	public void setPrimaryKey(long pk) {
		setFileId(pk);
	}

	public long getFileId() {
		return _fileId;
	}

	public void setFileId(long fileId) {
		_fileId = fileId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getEditionDate() {
		return _editionDate;
	}

	public void setEditionDate(Date editionDate) {
		_editionDate = editionDate;
	}

	private long _fileId;
	private long _userId;
	private Date _editionDate;

}