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

import com.weprode.nero.messaging.model.MessageFolder;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MessageFolder in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MessageFolderCacheModel
	implements CacheModel<MessageFolder>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MessageFolderCacheModel)) {
			return false;
		}

		MessageFolderCacheModel messageFolderCacheModel =
			(MessageFolderCacheModel)object;

		if (folderId == messageFolderCacheModel.folderId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, folderId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{folderId=");
		sb.append(folderId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", folderName=");
		sb.append(folderName);
		sb.append(", type=");
		sb.append(type);
		sb.append(", parentFolderId=");
		sb.append(parentFolderId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MessageFolder toEntityModel() {
		MessageFolderImpl messageFolderImpl = new MessageFolderImpl();

		messageFolderImpl.setFolderId(folderId);
		messageFolderImpl.setUserId(userId);

		if (folderName == null) {
			messageFolderImpl.setFolderName("");
		}
		else {
			messageFolderImpl.setFolderName(folderName);
		}

		messageFolderImpl.setType(type);
		messageFolderImpl.setParentFolderId(parentFolderId);

		messageFolderImpl.resetOriginalValues();

		return messageFolderImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		folderId = objectInput.readLong();

		userId = objectInput.readLong();
		folderName = objectInput.readUTF();

		type = objectInput.readInt();

		parentFolderId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(folderId);

		objectOutput.writeLong(userId);

		if (folderName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(folderName);
		}

		objectOutput.writeInt(type);

		objectOutput.writeLong(parentFolderId);
	}

	public long folderId;
	public long userId;
	public String folderName;
	public int type;
	public long parentFolderId;

}