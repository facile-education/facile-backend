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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.messaging.service.http.MessageServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class MessageSoap implements Serializable {

	public static MessageSoap toSoapModel(Message model) {
		MessageSoap soapModel = new MessageSoap();

		soapModel.setMessageId(model.getMessageId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setFolderId(model.getFolderId());
		soapModel.setThreadId(model.getThreadId());
		soapModel.setSendMessageId(model.getSendMessageId());
		soapModel.setSenderId(model.getSenderId());
		soapModel.setSendDate(model.getSendDate());
		soapModel.setSenderName(model.getSenderName());
		soapModel.setMessageSubject(model.getMessageSubject());
		soapModel.setMessageContent(model.getMessageContent());
		soapModel.setIsNew(model.isIsNew());
		soapModel.setReadDate(model.getReadDate());
		soapModel.setIsAnswered(model.isIsAnswered());
		soapModel.setIsForwarded(model.isIsForwarded());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static MessageSoap[] toSoapModels(Message[] models) {
		MessageSoap[] soapModels = new MessageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MessageSoap[][] toSoapModels(Message[][] models) {
		MessageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MessageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MessageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MessageSoap[] toSoapModels(List<Message> models) {
		List<MessageSoap> soapModels = new ArrayList<MessageSoap>(
			models.size());

		for (Message model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MessageSoap[soapModels.size()]);
	}

	public MessageSoap() {
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

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getFolderId() {
		return _folderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	public long getThreadId() {
		return _threadId;
	}

	public void setThreadId(long threadId) {
		_threadId = threadId;
	}

	public long getSendMessageId() {
		return _sendMessageId;
	}

	public void setSendMessageId(long sendMessageId) {
		_sendMessageId = sendMessageId;
	}

	public long getSenderId() {
		return _senderId;
	}

	public void setSenderId(long senderId) {
		_senderId = senderId;
	}

	public Date getSendDate() {
		return _sendDate;
	}

	public void setSendDate(Date sendDate) {
		_sendDate = sendDate;
	}

	public String getSenderName() {
		return _senderName;
	}

	public void setSenderName(String senderName) {
		_senderName = senderName;
	}

	public String getMessageSubject() {
		return _messageSubject;
	}

	public void setMessageSubject(String messageSubject) {
		_messageSubject = messageSubject;
	}

	public String getMessageContent() {
		return _messageContent;
	}

	public void setMessageContent(String messageContent) {
		_messageContent = messageContent;
	}

	public boolean getIsNew() {
		return _isNew;
	}

	public boolean isIsNew() {
		return _isNew;
	}

	public void setIsNew(boolean isNew) {
		_isNew = isNew;
	}

	public Date getReadDate() {
		return _readDate;
	}

	public void setReadDate(Date readDate) {
		_readDate = readDate;
	}

	public boolean getIsAnswered() {
		return _isAnswered;
	}

	public boolean isIsAnswered() {
		return _isAnswered;
	}

	public void setIsAnswered(boolean isAnswered) {
		_isAnswered = isAnswered;
	}

	public boolean getIsForwarded() {
		return _isForwarded;
	}

	public boolean isIsForwarded() {
		return _isForwarded;
	}

	public void setIsForwarded(boolean isForwarded) {
		_isForwarded = isForwarded;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private long _messageId;
	private long _companyId;
	private long _folderId;
	private long _threadId;
	private long _sendMessageId;
	private long _senderId;
	private Date _sendDate;
	private String _senderName;
	private String _messageSubject;
	private String _messageContent;
	private boolean _isNew;
	private Date _readDate;
	private boolean _isAnswered;
	private boolean _isForwarded;
	private int _type;

}