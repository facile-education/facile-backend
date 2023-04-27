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

import com.weprode.nero.progression.model.ItemContent;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ItemContent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ItemContentCacheModel
	implements CacheModel<ItemContent>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ItemContentCacheModel)) {
			return false;
		}

		ItemContentCacheModel itemContentCacheModel =
			(ItemContentCacheModel)object;

		if (contentId == itemContentCacheModel.contentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, contentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{contentId=");
		sb.append(contentId);
		sb.append(", progressionItemId=");
		sb.append(progressionItemId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", contentName=");
		sb.append(contentName);
		sb.append(", contentValue=");
		sb.append(contentValue);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", contentType=");
		sb.append(contentType);
		sb.append(", order=");
		sb.append(order);
		sb.append(", isToBeCompleted=");
		sb.append(isToBeCompleted);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ItemContent toEntityModel() {
		ItemContentImpl itemContentImpl = new ItemContentImpl();

		itemContentImpl.setContentId(contentId);
		itemContentImpl.setProgressionItemId(progressionItemId);

		if (modifiedDate == Long.MIN_VALUE) {
			itemContentImpl.setModifiedDate(null);
		}
		else {
			itemContentImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (contentName == null) {
			itemContentImpl.setContentName("");
		}
		else {
			itemContentImpl.setContentName(contentName);
		}

		if (contentValue == null) {
			itemContentImpl.setContentValue("");
		}
		else {
			itemContentImpl.setContentValue(contentValue);
		}

		itemContentImpl.setFileEntryId(fileEntryId);
		itemContentImpl.setContentType(contentType);
		itemContentImpl.setOrder(order);
		itemContentImpl.setIsToBeCompleted(isToBeCompleted);

		itemContentImpl.resetOriginalValues();

		return itemContentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		contentId = objectInput.readLong();

		progressionItemId = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		contentName = objectInput.readUTF();
		contentValue = objectInput.readUTF();

		fileEntryId = objectInput.readLong();

		contentType = objectInput.readInt();

		order = objectInput.readInt();

		isToBeCompleted = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(contentId);

		objectOutput.writeLong(progressionItemId);
		objectOutput.writeLong(modifiedDate);

		if (contentName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contentName);
		}

		if (contentValue == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(contentValue);
		}

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeInt(contentType);

		objectOutput.writeInt(order);

		objectOutput.writeBoolean(isToBeCompleted);
	}

	public long contentId;
	public long progressionItemId;
	public long modifiedDate;
	public String contentName;
	public String contentValue;
	public long fileEntryId;
	public int contentType;
	public int order;
	public boolean isToBeCompleted;

}