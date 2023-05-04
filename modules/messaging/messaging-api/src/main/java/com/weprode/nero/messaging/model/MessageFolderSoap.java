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

package com.weprode.nero.messaging.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.messaging.service.http.MessageFolderServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class MessageFolderSoap implements Serializable {

	public static MessageFolderSoap toSoapModel(MessageFolder model) {
		MessageFolderSoap soapModel = new MessageFolderSoap();

		soapModel.setFolderId(model.getFolderId());
		soapModel.setUserId(model.getUserId());
		soapModel.setFolderName(model.getFolderName());
		soapModel.setType(model.getType());
		soapModel.setParentFolderId(model.getParentFolderId());

		return soapModel;
	}

	public static MessageFolderSoap[] toSoapModels(MessageFolder[] models) {
		MessageFolderSoap[] soapModels = new MessageFolderSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MessageFolderSoap[][] toSoapModels(MessageFolder[][] models) {
		MessageFolderSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MessageFolderSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MessageFolderSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MessageFolderSoap[] toSoapModels(List<MessageFolder> models) {
		List<MessageFolderSoap> soapModels = new ArrayList<MessageFolderSoap>(
			models.size());

		for (MessageFolder model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MessageFolderSoap[soapModels.size()]);
	}

	public MessageFolderSoap() {
	}

	public long getPrimaryKey() {
		return _folderId;
	}

	public void setPrimaryKey(long pk) {
		setFolderId(pk);
	}

	public long getFolderId() {
		return _folderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getFolderName() {
		return _folderName;
	}

	public void setFolderName(String folderName) {
		_folderName = folderName;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public long getParentFolderId() {
		return _parentFolderId;
	}

	public void setParentFolderId(long parentFolderId) {
		_parentFolderId = parentFolderId;
	}

	private long _folderId;
	private long _userId;
	private String _folderName;
	private int _type;
	private long _parentFolderId;

}