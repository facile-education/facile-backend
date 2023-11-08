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

import com.liferay.portal.kernel.model.BaseModel;

import com.weprode.facile.messaging.service.persistence.MessageAttachFilePK;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the MessageAttachFile service. Represents a row in the &quot;Messaging_MessageAttachFile&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.messaging.model.impl.MessageAttachFileModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.messaging.model.impl.MessageAttachFileImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageAttachFile
 * @generated
 */
@ProviderType
public interface MessageAttachFileModel extends BaseModel<MessageAttachFile> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a message attach file model instance should use the {@link MessageAttachFile} interface instead.
	 */

	/**
	 * Returns the primary key of this message attach file.
	 *
	 * @return the primary key of this message attach file
	 */
	public MessageAttachFilePK getPrimaryKey();

	/**
	 * Sets the primary key of this message attach file.
	 *
	 * @param primaryKey the primary key of this message attach file
	 */
	public void setPrimaryKey(MessageAttachFilePK primaryKey);

	/**
	 * Returns the message ID of this message attach file.
	 *
	 * @return the message ID of this message attach file
	 */
	public long getMessageId();

	/**
	 * Sets the message ID of this message attach file.
	 *
	 * @param messageId the message ID of this message attach file
	 */
	public void setMessageId(long messageId);

	/**
	 * Returns the file ID of this message attach file.
	 *
	 * @return the file ID of this message attach file
	 */
	public long getFileId();

	/**
	 * Sets the file ID of this message attach file.
	 *
	 * @param fileId the file ID of this message attach file
	 */
	public void setFileId(long fileId);

	@Override
	public MessageAttachFile cloneWithOriginalValues();

}