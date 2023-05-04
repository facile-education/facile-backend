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

package com.weprode.nero.messaging.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.messaging.model.Message;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Message in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessageCacheModel implements CacheModel<Message>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MessageCacheModel)) {
			return false;
		}

		MessageCacheModel messageCacheModel = (MessageCacheModel)object;

		if (messageId == messageCacheModel.messageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, messageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{messageId=");
		sb.append(messageId);
		sb.append(", folderId=");
		sb.append(folderId);
		sb.append(", threadId=");
		sb.append(threadId);
		sb.append(", sendMessageId=");
		sb.append(sendMessageId);
		sb.append(", senderId=");
		sb.append(senderId);
		sb.append(", sendDate=");
		sb.append(sendDate);
		sb.append(", senderName=");
		sb.append(senderName);
		sb.append(", messageSubject=");
		sb.append(messageSubject);
		sb.append(", messageContent=");
		sb.append(messageContent);
		sb.append(", isNew=");
		sb.append(isNew);
		sb.append(", readDate=");
		sb.append(readDate);
		sb.append(", isAnswered=");
		sb.append(isAnswered);
		sb.append(", isForwarded=");
		sb.append(isForwarded);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Message toEntityModel() {
		MessageImpl messageImpl = new MessageImpl();

		messageImpl.setMessageId(messageId);
		messageImpl.setFolderId(folderId);
		messageImpl.setThreadId(threadId);
		messageImpl.setSendMessageId(sendMessageId);
		messageImpl.setSenderId(senderId);

		if (sendDate == Long.MIN_VALUE) {
			messageImpl.setSendDate(null);
		}
		else {
			messageImpl.setSendDate(new Date(sendDate));
		}

		if (senderName == null) {
			messageImpl.setSenderName("");
		}
		else {
			messageImpl.setSenderName(senderName);
		}

		if (messageSubject == null) {
			messageImpl.setMessageSubject("");
		}
		else {
			messageImpl.setMessageSubject(messageSubject);
		}

		if (messageContent == null) {
			messageImpl.setMessageContent("");
		}
		else {
			messageImpl.setMessageContent(messageContent);
		}

		messageImpl.setIsNew(isNew);

		if (readDate == Long.MIN_VALUE) {
			messageImpl.setReadDate(null);
		}
		else {
			messageImpl.setReadDate(new Date(readDate));
		}

		messageImpl.setIsAnswered(isAnswered);
		messageImpl.setIsForwarded(isForwarded);
		messageImpl.setType(type);

		messageImpl.resetOriginalValues();

		return messageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		messageId = objectInput.readLong();

		folderId = objectInput.readLong();

		threadId = objectInput.readLong();

		sendMessageId = objectInput.readLong();

		senderId = objectInput.readLong();
		sendDate = objectInput.readLong();
		senderName = objectInput.readUTF();
		messageSubject = objectInput.readUTF();
		messageContent = objectInput.readUTF();

		isNew = objectInput.readBoolean();
		readDate = objectInput.readLong();

		isAnswered = objectInput.readBoolean();

		isForwarded = objectInput.readBoolean();

		type = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(messageId);

		objectOutput.writeLong(folderId);

		objectOutput.writeLong(threadId);

		objectOutput.writeLong(sendMessageId);

		objectOutput.writeLong(senderId);
		objectOutput.writeLong(sendDate);

		if (senderName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(senderName);
		}

		if (messageSubject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(messageSubject);
		}

		if (messageContent == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(messageContent);
		}

		objectOutput.writeBoolean(isNew);
		objectOutput.writeLong(readDate);

		objectOutput.writeBoolean(isAnswered);

		objectOutput.writeBoolean(isForwarded);

		objectOutput.writeInt(type);
	}

	public long messageId;
	public long folderId;
	public long threadId;
	public long sendMessageId;
	public long senderId;
	public long sendDate;
	public String senderName;
	public String messageSubject;
	public String messageContent;
	public boolean isNew;
	public long readDate;
	public boolean isAnswered;
	public boolean isForwarded;
	public int type;

}