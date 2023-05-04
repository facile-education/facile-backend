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

import com.weprode.nero.messaging.model.MessageContent;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MessageContent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessageContentCacheModel
	implements CacheModel<MessageContent>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MessageContentCacheModel)) {
			return false;
		}

		MessageContentCacheModel messageContentCacheModel =
			(MessageContentCacheModel)object;

		if (messageId == messageContentCacheModel.messageId) {
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
		StringBundler sb = new StringBundler(5);

		sb.append("{messageId=");
		sb.append(messageId);
		sb.append(", messageContent=");
		sb.append(messageContent);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MessageContent toEntityModel() {
		MessageContentImpl messageContentImpl = new MessageContentImpl();

		messageContentImpl.setMessageId(messageId);

		if (messageContent == null) {
			messageContentImpl.setMessageContent("");
		}
		else {
			messageContentImpl.setMessageContent(messageContent);
		}

		messageContentImpl.resetOriginalValues();

		return messageContentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		messageId = objectInput.readLong();
		messageContent = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(messageId);

		if (messageContent == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(messageContent);
		}
	}

	public long messageId;
	public String messageContent;

}