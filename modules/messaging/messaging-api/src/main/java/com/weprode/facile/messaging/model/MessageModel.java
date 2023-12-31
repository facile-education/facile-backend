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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Message service. Represents a row in the &quot;Messaging_Message&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.messaging.model.impl.MessageModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.messaging.model.impl.MessageImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Message
 * @generated
 */
@ProviderType
public interface MessageModel extends BaseModel<Message>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a message model instance should use the {@link Message} interface instead.
	 */

	/**
	 * Returns the primary key of this message.
	 *
	 * @return the primary key of this message
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this message.
	 *
	 * @param primaryKey the primary key of this message
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the message ID of this message.
	 *
	 * @return the message ID of this message
	 */
	public long getMessageId();

	/**
	 * Sets the message ID of this message.
	 *
	 * @param messageId the message ID of this message
	 */
	public void setMessageId(long messageId);

	/**
	 * Returns the company ID of this message.
	 *
	 * @return the company ID of this message
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this message.
	 *
	 * @param companyId the company ID of this message
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the folder ID of this message.
	 *
	 * @return the folder ID of this message
	 */
	public long getFolderId();

	/**
	 * Sets the folder ID of this message.
	 *
	 * @param folderId the folder ID of this message
	 */
	public void setFolderId(long folderId);

	/**
	 * Returns the thread ID of this message.
	 *
	 * @return the thread ID of this message
	 */
	public long getThreadId();

	/**
	 * Sets the thread ID of this message.
	 *
	 * @param threadId the thread ID of this message
	 */
	public void setThreadId(long threadId);

	/**
	 * Returns the send message ID of this message.
	 *
	 * @return the send message ID of this message
	 */
	public long getSendMessageId();

	/**
	 * Sets the send message ID of this message.
	 *
	 * @param sendMessageId the send message ID of this message
	 */
	public void setSendMessageId(long sendMessageId);

	/**
	 * Returns the sender ID of this message.
	 *
	 * @return the sender ID of this message
	 */
	public long getSenderId();

	/**
	 * Sets the sender ID of this message.
	 *
	 * @param senderId the sender ID of this message
	 */
	public void setSenderId(long senderId);

	/**
	 * Returns the send date of this message.
	 *
	 * @return the send date of this message
	 */
	public Date getSendDate();

	/**
	 * Sets the send date of this message.
	 *
	 * @param sendDate the send date of this message
	 */
	public void setSendDate(Date sendDate);

	/**
	 * Returns the sender name of this message.
	 *
	 * @return the sender name of this message
	 */
	@AutoEscape
	public String getSenderName();

	/**
	 * Sets the sender name of this message.
	 *
	 * @param senderName the sender name of this message
	 */
	public void setSenderName(String senderName);

	/**
	 * Returns the message subject of this message.
	 *
	 * @return the message subject of this message
	 */
	@AutoEscape
	public String getMessageSubject();

	/**
	 * Sets the message subject of this message.
	 *
	 * @param messageSubject the message subject of this message
	 */
	public void setMessageSubject(String messageSubject);

	/**
	 * Returns the message content of this message.
	 *
	 * @return the message content of this message
	 */
	@AutoEscape
	public String getMessageContent();

	/**
	 * Sets the message content of this message.
	 *
	 * @param messageContent the message content of this message
	 */
	public void setMessageContent(String messageContent);

	/**
	 * Returns the is new of this message.
	 *
	 * @return the is new of this message
	 */
	public boolean getIsNew();

	/**
	 * Returns <code>true</code> if this message is is new.
	 *
	 * @return <code>true</code> if this message is is new; <code>false</code> otherwise
	 */
	public boolean isIsNew();

	/**
	 * Sets whether this message is is new.
	 *
	 * @param isNew the is new of this message
	 */
	public void setIsNew(boolean isNew);

	/**
	 * Returns the read date of this message.
	 *
	 * @return the read date of this message
	 */
	public Date getReadDate();

	/**
	 * Sets the read date of this message.
	 *
	 * @param readDate the read date of this message
	 */
	public void setReadDate(Date readDate);

	/**
	 * Returns the is answered of this message.
	 *
	 * @return the is answered of this message
	 */
	public boolean getIsAnswered();

	/**
	 * Returns <code>true</code> if this message is is answered.
	 *
	 * @return <code>true</code> if this message is is answered; <code>false</code> otherwise
	 */
	public boolean isIsAnswered();

	/**
	 * Sets whether this message is is answered.
	 *
	 * @param isAnswered the is answered of this message
	 */
	public void setIsAnswered(boolean isAnswered);

	/**
	 * Returns the is forwarded of this message.
	 *
	 * @return the is forwarded of this message
	 */
	public boolean getIsForwarded();

	/**
	 * Returns <code>true</code> if this message is is forwarded.
	 *
	 * @return <code>true</code> if this message is is forwarded; <code>false</code> otherwise
	 */
	public boolean isIsForwarded();

	/**
	 * Sets whether this message is is forwarded.
	 *
	 * @param isForwarded the is forwarded of this message
	 */
	public void setIsForwarded(boolean isForwarded);

	/**
	 * Returns the type of this message.
	 *
	 * @return the type of this message
	 */
	public int getType();

	/**
	 * Sets the type of this message.
	 *
	 * @param type the type of this message
	 */
	public void setType(int type);

	@Override
	public Message cloneWithOriginalValues();

}