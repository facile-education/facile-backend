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

package com.weprode.nero.messaging.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessageAttachFilePK
	implements Comparable<MessageAttachFilePK>, Serializable {

	public long messageId;
	public long fileId;

	public MessageAttachFilePK() {
	}

	public MessageAttachFilePK(long messageId, long fileId) {
		this.messageId = messageId;
		this.fileId = fileId;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	@Override
	public int compareTo(MessageAttachFilePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (messageId < pk.messageId) {
			value = -1;
		}
		else if (messageId > pk.messageId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (fileId < pk.fileId) {
			value = -1;
		}
		else if (fileId > pk.fileId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MessageAttachFilePK)) {
			return false;
		}

		MessageAttachFilePK pk = (MessageAttachFilePK)object;

		if ((messageId == pk.messageId) && (fileId == pk.fileId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, messageId);
		hashCode = HashUtil.hash(hashCode, fileId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("messageId=");

		sb.append(messageId);
		sb.append(", fileId=");

		sb.append(fileId);

		sb.append("}");

		return sb.toString();
	}

}