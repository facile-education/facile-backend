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
 * This class is a wrapper for {@link MessageRecipients}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipients
 * @generated
 */
public class MessageRecipientsWrapper
	extends BaseModelWrapper<MessageRecipients>
	implements MessageRecipients, ModelWrapper<MessageRecipients> {

	public MessageRecipientsWrapper(MessageRecipients messageRecipients) {
		super(messageRecipients);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("messageId", getMessageId());
		attributes.put("recipients", getRecipients());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long messageId = (Long)attributes.get("messageId");

		if (messageId != null) {
			setMessageId(messageId);
		}

		String recipients = (String)attributes.get("recipients");

		if (recipients != null) {
			setRecipients(recipients);
		}
	}

	@Override
	public MessageRecipients cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the message ID of this message recipients.
	 *
	 * @return the message ID of this message recipients
	 */
	@Override
	public long getMessageId() {
		return model.getMessageId();
	}

	/**
	 * Returns the primary key of this message recipients.
	 *
	 * @return the primary key of this message recipients
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the recipients of this message recipients.
	 *
	 * @return the recipients of this message recipients
	 */
	@Override
	public String getRecipients() {
		return model.getRecipients();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the message ID of this message recipients.
	 *
	 * @param messageId the message ID of this message recipients
	 */
	@Override
	public void setMessageId(long messageId) {
		model.setMessageId(messageId);
	}

	/**
	 * Sets the primary key of this message recipients.
	 *
	 * @param primaryKey the primary key of this message recipients
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the recipients of this message recipients.
	 *
	 * @param recipients the recipients of this message recipients
	 */
	@Override
	public void setRecipients(String recipients) {
		model.setRecipients(recipients);
	}

	@Override
	protected MessageRecipientsWrapper wrap(
		MessageRecipients messageRecipients) {

		return new MessageRecipientsWrapper(messageRecipients);
	}

}