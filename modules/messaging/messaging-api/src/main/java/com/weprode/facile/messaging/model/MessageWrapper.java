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

package com.weprode.facile.messaging.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Message}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Message
 * @generated
 */
public class MessageWrapper
	extends BaseModelWrapper<Message>
	implements Message, ModelWrapper<Message> {

	public MessageWrapper(Message message) {
		super(message);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("messageId", getMessageId());
		attributes.put("companyId", getCompanyId());
		attributes.put("folderId", getFolderId());
		attributes.put("threadId", getThreadId());
		attributes.put("sendMessageId", getSendMessageId());
		attributes.put("senderId", getSenderId());
		attributes.put("sendDate", getSendDate());
		attributes.put("senderName", getSenderName());
		attributes.put("messageSubject", getMessageSubject());
		attributes.put("messageContent", getMessageContent());
		attributes.put("isNew", isIsNew());
		attributes.put("readDate", getReadDate());
		attributes.put("isAnswered", isIsAnswered());
		attributes.put("isForwarded", isIsForwarded());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long messageId = (Long)attributes.get("messageId");

		if (messageId != null) {
			setMessageId(messageId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long folderId = (Long)attributes.get("folderId");

		if (folderId != null) {
			setFolderId(folderId);
		}

		Long threadId = (Long)attributes.get("threadId");

		if (threadId != null) {
			setThreadId(threadId);
		}

		Long sendMessageId = (Long)attributes.get("sendMessageId");

		if (sendMessageId != null) {
			setSendMessageId(sendMessageId);
		}

		Long senderId = (Long)attributes.get("senderId");

		if (senderId != null) {
			setSenderId(senderId);
		}

		Date sendDate = (Date)attributes.get("sendDate");

		if (sendDate != null) {
			setSendDate(sendDate);
		}

		String senderName = (String)attributes.get("senderName");

		if (senderName != null) {
			setSenderName(senderName);
		}

		String messageSubject = (String)attributes.get("messageSubject");

		if (messageSubject != null) {
			setMessageSubject(messageSubject);
		}

		String messageContent = (String)attributes.get("messageContent");

		if (messageContent != null) {
			setMessageContent(messageContent);
		}

		Boolean isNew = (Boolean)attributes.get("isNew");

		if (isNew != null) {
			setIsNew(isNew);
		}

		Date readDate = (Date)attributes.get("readDate");

		if (readDate != null) {
			setReadDate(readDate);
		}

		Boolean isAnswered = (Boolean)attributes.get("isAnswered");

		if (isAnswered != null) {
			setIsAnswered(isAnswered);
		}

		Boolean isForwarded = (Boolean)attributes.get("isForwarded");

		if (isForwarded != null) {
			setIsForwarded(isForwarded);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@Override
	public Message cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this message.
	 *
	 * @return the company ID of this message
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the folder ID of this message.
	 *
	 * @return the folder ID of this message
	 */
	@Override
	public long getFolderId() {
		return model.getFolderId();
	}

	/**
	 * Returns the is answered of this message.
	 *
	 * @return the is answered of this message
	 */
	@Override
	public boolean getIsAnswered() {
		return model.getIsAnswered();
	}

	/**
	 * Returns the is forwarded of this message.
	 *
	 * @return the is forwarded of this message
	 */
	@Override
	public boolean getIsForwarded() {
		return model.getIsForwarded();
	}

	/**
	 * Returns the is new of this message.
	 *
	 * @return the is new of this message
	 */
	@Override
	public boolean getIsNew() {
		return model.getIsNew();
	}

	/**
	 * Returns the message content of this message.
	 *
	 * @return the message content of this message
	 */
	@Override
	public String getMessageContent() {
		return model.getMessageContent();
	}

	/**
	 * Returns the message ID of this message.
	 *
	 * @return the message ID of this message
	 */
	@Override
	public long getMessageId() {
		return model.getMessageId();
	}

	/**
	 * Returns the message subject of this message.
	 *
	 * @return the message subject of this message
	 */
	@Override
	public String getMessageSubject() {
		return model.getMessageSubject();
	}

	/**
	 * Returns the primary key of this message.
	 *
	 * @return the primary key of this message
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the read date of this message.
	 *
	 * @return the read date of this message
	 */
	@Override
	public Date getReadDate() {
		return model.getReadDate();
	}

	/**
	 * Returns the send date of this message.
	 *
	 * @return the send date of this message
	 */
	@Override
	public Date getSendDate() {
		return model.getSendDate();
	}

	/**
	 * Returns the sender ID of this message.
	 *
	 * @return the sender ID of this message
	 */
	@Override
	public long getSenderId() {
		return model.getSenderId();
	}

	/**
	 * Returns the sender name of this message.
	 *
	 * @return the sender name of this message
	 */
	@Override
	public String getSenderName() {
		return model.getSenderName();
	}

	/**
	 * Returns the send message ID of this message.
	 *
	 * @return the send message ID of this message
	 */
	@Override
	public long getSendMessageId() {
		return model.getSendMessageId();
	}

	/**
	 * Returns the thread ID of this message.
	 *
	 * @return the thread ID of this message
	 */
	@Override
	public long getThreadId() {
		return model.getThreadId();
	}

	/**
	 * Returns the type of this message.
	 *
	 * @return the type of this message
	 */
	@Override
	public int getType() {
		return model.getType();
	}

	/**
	 * Returns <code>true</code> if this message is is answered.
	 *
	 * @return <code>true</code> if this message is is answered; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsAnswered() {
		return model.isIsAnswered();
	}

	/**
	 * Returns <code>true</code> if this message is is forwarded.
	 *
	 * @return <code>true</code> if this message is is forwarded; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsForwarded() {
		return model.isIsForwarded();
	}

	/**
	 * Returns <code>true</code> if this message is is new.
	 *
	 * @return <code>true</code> if this message is is new; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsNew() {
		return model.isIsNew();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this message.
	 *
	 * @param companyId the company ID of this message
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the folder ID of this message.
	 *
	 * @param folderId the folder ID of this message
	 */
	@Override
	public void setFolderId(long folderId) {
		model.setFolderId(folderId);
	}

	/**
	 * Sets whether this message is is answered.
	 *
	 * @param isAnswered the is answered of this message
	 */
	@Override
	public void setIsAnswered(boolean isAnswered) {
		model.setIsAnswered(isAnswered);
	}

	/**
	 * Sets whether this message is is forwarded.
	 *
	 * @param isForwarded the is forwarded of this message
	 */
	@Override
	public void setIsForwarded(boolean isForwarded) {
		model.setIsForwarded(isForwarded);
	}

	/**
	 * Sets whether this message is is new.
	 *
	 * @param isNew the is new of this message
	 */
	@Override
	public void setIsNew(boolean isNew) {
		model.setIsNew(isNew);
	}

	/**
	 * Sets the message content of this message.
	 *
	 * @param messageContent the message content of this message
	 */
	@Override
	public void setMessageContent(String messageContent) {
		model.setMessageContent(messageContent);
	}

	/**
	 * Sets the message ID of this message.
	 *
	 * @param messageId the message ID of this message
	 */
	@Override
	public void setMessageId(long messageId) {
		model.setMessageId(messageId);
	}

	/**
	 * Sets the message subject of this message.
	 *
	 * @param messageSubject the message subject of this message
	 */
	@Override
	public void setMessageSubject(String messageSubject) {
		model.setMessageSubject(messageSubject);
	}

	/**
	 * Sets the primary key of this message.
	 *
	 * @param primaryKey the primary key of this message
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the read date of this message.
	 *
	 * @param readDate the read date of this message
	 */
	@Override
	public void setReadDate(Date readDate) {
		model.setReadDate(readDate);
	}

	/**
	 * Sets the send date of this message.
	 *
	 * @param sendDate the send date of this message
	 */
	@Override
	public void setSendDate(Date sendDate) {
		model.setSendDate(sendDate);
	}

	/**
	 * Sets the sender ID of this message.
	 *
	 * @param senderId the sender ID of this message
	 */
	@Override
	public void setSenderId(long senderId) {
		model.setSenderId(senderId);
	}

	/**
	 * Sets the sender name of this message.
	 *
	 * @param senderName the sender name of this message
	 */
	@Override
	public void setSenderName(String senderName) {
		model.setSenderName(senderName);
	}

	/**
	 * Sets the send message ID of this message.
	 *
	 * @param sendMessageId the send message ID of this message
	 */
	@Override
	public void setSendMessageId(long sendMessageId) {
		model.setSendMessageId(sendMessageId);
	}

	/**
	 * Sets the thread ID of this message.
	 *
	 * @param threadId the thread ID of this message
	 */
	@Override
	public void setThreadId(long threadId) {
		model.setThreadId(threadId);
	}

	/**
	 * Sets the type of this message.
	 *
	 * @param type the type of this message
	 */
	@Override
	public void setType(int type) {
		model.setType(type);
	}

	@Override
	protected MessageWrapper wrap(Message message) {
		return new MessageWrapper(message);
	}

}