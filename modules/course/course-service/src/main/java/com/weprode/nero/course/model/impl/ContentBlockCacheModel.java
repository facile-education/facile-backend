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

package com.weprode.nero.course.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.course.model.ContentBlock;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ContentBlock in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContentBlockCacheModel
	implements CacheModel<ContentBlock>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContentBlockCacheModel)) {
			return false;
		}

		ContentBlockCacheModel contentBlockCacheModel =
			(ContentBlockCacheModel)object;

		if (blockId == contentBlockCacheModel.blockId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, blockId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{blockId=");
		sb.append(blockId);
		sb.append(", courseItemId=");
		sb.append(courseItemId);
		sb.append(", modificationDate=");
		sb.append(modificationDate);
		sb.append(", blockName=");
		sb.append(blockName);
		sb.append(", blockValue=");
		sb.append(blockValue);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", blockType=");
		sb.append(blockType);
		sb.append(", order=");
		sb.append(order);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ContentBlock toEntityModel() {
		ContentBlockImpl contentBlockImpl = new ContentBlockImpl();

		contentBlockImpl.setBlockId(blockId);
		contentBlockImpl.setCourseItemId(courseItemId);

		if (modificationDate == Long.MIN_VALUE) {
			contentBlockImpl.setModificationDate(null);
		}
		else {
			contentBlockImpl.setModificationDate(new Date(modificationDate));
		}

		if (blockName == null) {
			contentBlockImpl.setBlockName("");
		}
		else {
			contentBlockImpl.setBlockName(blockName);
		}

		if (blockValue == null) {
			contentBlockImpl.setBlockValue("");
		}
		else {
			contentBlockImpl.setBlockValue(blockValue);
		}

		contentBlockImpl.setFileEntryId(fileEntryId);
		contentBlockImpl.setBlockType(blockType);
		contentBlockImpl.setOrder(order);

		contentBlockImpl.resetOriginalValues();

		return contentBlockImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		blockId = objectInput.readLong();

		courseItemId = objectInput.readLong();
		modificationDate = objectInput.readLong();
		blockName = objectInput.readUTF();
		blockValue = objectInput.readUTF();

		fileEntryId = objectInput.readLong();

		blockType = objectInput.readInt();

		order = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(blockId);

		objectOutput.writeLong(courseItemId);
		objectOutput.writeLong(modificationDate);

		if (blockName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(blockName);
		}

		if (blockValue == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(blockValue);
		}

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeInt(blockType);

		objectOutput.writeInt(order);
	}

	public long blockId;
	public long courseItemId;
	public long modificationDate;
	public String blockName;
	public String blockValue;
	public long fileEntryId;
	public int blockType;
	public int order;

}