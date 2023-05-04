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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MessageContent}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageContent
 * @generated
 */
public class MessageContentWrapper
	extends BaseModelWrapper<MessageContent>
	implements MessageContent, ModelWrapper<MessageContent> {

	public MessageContentWrapper(MessageContent messageContent) {
		super(messageContent);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("messageId", getMessageId());
		attributes.put("messageContent", getMessageContent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long messageId = (Long)attributes.get("messageId");

		if (messageId != null) {
			setMessageId(messageId);
		}

		String messageContent = (String)attributes.get("messageContent");

		if (messageContent != null) {
			setMessageContent(messageContent);
		}
	}

	@Override
	public MessageContent cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the message content of this message content.
	 *
	 * @return the message content of this message content
	 */
	@Override
	public String getMessageContent() {
		return model.getMessageContent();
	}

	/**
	 * Returns the message ID of this message content.
	 *
	 * @return the message ID of this message content
	 */
	@Override
	public long getMessageId() {
		return model.getMessageId();
	}

	/**
	 * Returns the primary key of this message content.
	 *
	 * @return the primary key of this message content
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the message content of this message content.
	 *
	 * @param messageContent the message content of this message content
	 */
	@Override
	public void setMessageContent(String messageContent) {
		model.setMessageContent(messageContent);
	}

	/**
	 * Sets the message ID of this message content.
	 *
	 * @param messageId the message ID of this message content
	 */
	@Override
	public void setMessageId(long messageId) {
		model.setMessageId(messageId);
	}

	/**
	 * Sets the primary key of this message content.
	 *
	 * @param primaryKey the primary key of this message content
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected MessageContentWrapper wrap(MessageContent messageContent) {
		return new MessageContentWrapper(messageContent);
	}

}