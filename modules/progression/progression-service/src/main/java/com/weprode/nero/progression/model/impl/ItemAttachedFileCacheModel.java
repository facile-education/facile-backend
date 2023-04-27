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

package com.weprode.nero.progression.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.progression.model.ItemAttachedFile;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ItemAttachedFile in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ItemAttachedFileCacheModel
	implements CacheModel<ItemAttachedFile>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ItemAttachedFileCacheModel)) {
			return false;
		}

		ItemAttachedFileCacheModel itemAttachedFileCacheModel =
			(ItemAttachedFileCacheModel)object;

		if (itemAttachedFileId ==
				itemAttachedFileCacheModel.itemAttachedFileId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemAttachedFileId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{itemAttachedFileId=");
		sb.append(itemAttachedFileId);
		sb.append(", progressionItemId=");
		sb.append(progressionItemId);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", isAudioRecording=");
		sb.append(isAudioRecording);
		sb.append(", isToBeCompleted=");
		sb.append(isToBeCompleted);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ItemAttachedFile toEntityModel() {
		ItemAttachedFileImpl itemAttachedFileImpl = new ItemAttachedFileImpl();

		itemAttachedFileImpl.setItemAttachedFileId(itemAttachedFileId);
		itemAttachedFileImpl.setProgressionItemId(progressionItemId);
		itemAttachedFileImpl.setFileEntryId(fileEntryId);
		itemAttachedFileImpl.setIsAudioRecording(isAudioRecording);
		itemAttachedFileImpl.setIsToBeCompleted(isToBeCompleted);

		itemAttachedFileImpl.resetOriginalValues();

		return itemAttachedFileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		itemAttachedFileId = objectInput.readLong();

		progressionItemId = objectInput.readLong();

		fileEntryId = objectInput.readLong();

		isAudioRecording = objectInput.readBoolean();

		isToBeCompleted = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(itemAttachedFileId);

		objectOutput.writeLong(progressionItemId);

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeBoolean(isAudioRecording);

		objectOutput.writeBoolean(isToBeCompleted);
	}

	public long itemAttachedFileId;
	public long progressionItemId;
	public long fileEntryId;
	public boolean isAudioRecording;
	public boolean isToBeCompleted;

}