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

import com.weprode.nero.messaging.model.MessageAttachFile;
import com.weprode.nero.messaging.service.persistence.MessageAttachFilePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MessageAttachFile in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessageAttachFileCacheModel
	implements CacheModel<MessageAttachFile>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MessageAttachFileCacheModel)) {
			return false;
		}

		MessageAttachFileCacheModel messageAttachFileCacheModel =
			(MessageAttachFileCacheModel)object;

		if (messageAttachFilePK.equals(
				messageAttachFileCacheModel.messageAttachFilePK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, messageAttachFilePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{messageId=");
		sb.append(messageId);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MessageAttachFile toEntityModel() {
		MessageAttachFileImpl messageAttachFileImpl =
			new MessageAttachFileImpl();

		messageAttachFileImpl.setMessageId(messageId);
		messageAttachFileImpl.setFileId(fileId);

		messageAttachFileImpl.resetOriginalValues();

		return messageAttachFileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		messageId = objectInput.readLong();

		fileId = objectInput.readLong();

		messageAttachFilePK = new MessageAttachFilePK(messageId, fileId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(messageId);

		objectOutput.writeLong(fileId);
	}

	public long messageId;
	public long fileId;
	public transient MessageAttachFilePK messageAttachFilePK;

}