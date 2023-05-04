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

import com.weprode.nero.messaging.service.persistence.MessageAttachFilePK;

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
public class MessageAttachFileSoap implements Serializable {

	public static MessageAttachFileSoap toSoapModel(MessageAttachFile model) {
		MessageAttachFileSoap soapModel = new MessageAttachFileSoap();

		soapModel.setMessageId(model.getMessageId());
		soapModel.setFileId(model.getFileId());

		return soapModel;
	}

	public static MessageAttachFileSoap[] toSoapModels(
		MessageAttachFile[] models) {

		MessageAttachFileSoap[] soapModels =
			new MessageAttachFileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MessageAttachFileSoap[][] toSoapModels(
		MessageAttachFile[][] models) {

		MessageAttachFileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new MessageAttachFileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MessageAttachFileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MessageAttachFileSoap[] toSoapModels(
		List<MessageAttachFile> models) {

		List<MessageAttachFileSoap> soapModels =
			new ArrayList<MessageAttachFileSoap>(models.size());

		for (MessageAttachFile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MessageAttachFileSoap[soapModels.size()]);
	}

	public MessageAttachFileSoap() {
	}

	public MessageAttachFilePK getPrimaryKey() {
		return new MessageAttachFilePK(_messageId, _fileId);
	}

	public void setPrimaryKey(MessageAttachFilePK pk) {
		setMessageId(pk.messageId);
		setFileId(pk.fileId);
	}

	public long getMessageId() {
		return _messageId;
	}

	public void setMessageId(long messageId) {
		_messageId = messageId;
	}

	public long getFileId() {
		return _fileId;
	}

	public void setFileId(long fileId) {
		_fileId = fileId;
	}

	private long _messageId;
	private long _fileId;

}