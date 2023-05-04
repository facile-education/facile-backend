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
 * This class is a wrapper for {@link MessageAttachFile}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageAttachFile
 * @generated
 */
public class MessageAttachFileWrapper
	extends BaseModelWrapper<MessageAttachFile>
	implements MessageAttachFile, ModelWrapper<MessageAttachFile> {

	public MessageAttachFileWrapper(MessageAttachFile messageAttachFile) {
		super(messageAttachFile);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("messageId", getMessageId());
		attributes.put("fileId", getFileId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long messageId = (Long)attributes.get("messageId");

		if (messageId != null) {
			setMessageId(messageId);
		}

		Long fileId = (Long)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}
	}

	@Override
	public MessageAttachFile cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the file ID of this message attach file.
	 *
	 * @return the file ID of this message attach file
	 */
	@Override
	public long getFileId() {
		return model.getFileId();
	}

	/**
	 * Returns the message ID of this message attach file.
	 *
	 * @return the message ID of this message attach file
	 */
	@Override
	public long getMessageId() {
		return model.getMessageId();
	}

	/**
	 * Returns the primary key of this message attach file.
	 *
	 * @return the primary key of this message attach file
	 */
	@Override
	public com.weprode.nero.messaging.service.persistence.MessageAttachFilePK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the file ID of this message attach file.
	 *
	 * @param fileId the file ID of this message attach file
	 */
	@Override
	public void setFileId(long fileId) {
		model.setFileId(fileId);
	}

	/**
	 * Sets the message ID of this message attach file.
	 *
	 * @param messageId the message ID of this message attach file
	 */
	@Override
	public void setMessageId(long messageId) {
		model.setMessageId(messageId);
	}

	/**
	 * Sets the primary key of this message attach file.
	 *
	 * @param primaryKey the primary key of this message attach file
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.nero.messaging.service.persistence.MessageAttachFilePK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected MessageAttachFileWrapper wrap(
		MessageAttachFile messageAttachFile) {

		return new MessageAttachFileWrapper(messageAttachFile);
	}

}