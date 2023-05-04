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
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class MessageContentSoap implements Serializable {

	public static MessageContentSoap toSoapModel(MessageContent model) {
		MessageContentSoap soapModel = new MessageContentSoap();

		soapModel.setMessageId(model.getMessageId());
		soapModel.setMessageContent(model.getMessageContent());

		return soapModel;
	}

	public static MessageContentSoap[] toSoapModels(MessageContent[] models) {
		MessageContentSoap[] soapModels = new MessageContentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MessageContentSoap[][] toSoapModels(
		MessageContent[][] models) {

		MessageContentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new MessageContentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MessageContentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MessageContentSoap[] toSoapModels(
		List<MessageContent> models) {

		List<MessageContentSoap> soapModels = new ArrayList<MessageContentSoap>(
			models.size());

		for (MessageContent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MessageContentSoap[soapModels.size()]);
	}

	public MessageContentSoap() {
	}

	public long getPrimaryKey() {
		return _messageId;
	}

	public void setPrimaryKey(long pk) {
		setMessageId(pk);
	}

	public long getMessageId() {
		return _messageId;
	}

	public void setMessageId(long messageId) {
		_messageId = messageId;
	}

	public String getMessageContent() {
		return _messageContent;
	}

	public void setMessageContent(String messageContent) {
		_messageContent = messageContent;
	}

	private long _messageId;
	private String _messageContent;

}