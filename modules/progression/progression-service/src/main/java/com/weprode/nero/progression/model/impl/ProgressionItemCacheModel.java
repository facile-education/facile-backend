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

import com.weprode.nero.progression.model.ProgressionItem;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProgressionItem in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProgressionItemCacheModel
	implements CacheModel<ProgressionItem>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProgressionItemCacheModel)) {
			return false;
		}

		ProgressionItemCacheModel progressionItemCacheModel =
			(ProgressionItemCacheModel)object;

		if (progressionItemId == progressionItemCacheModel.progressionItemId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, progressionItemId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{progressionItemId=");
		sb.append(progressionItemId);
		sb.append(", progressionId=");
		sb.append(progressionId);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", homeworkId=");
		sb.append(homeworkId);
		sb.append(", progressionFolderId=");
		sb.append(progressionFolderId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", itemName=");
		sb.append(itemName);
		sb.append(", isHomework=");
		sb.append(isHomework);
		sb.append(", duration=");
		sb.append(duration);
		sb.append(", type=");
		sb.append(type);
		sb.append(", order=");
		sb.append(order);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProgressionItem toEntityModel() {
		ProgressionItemImpl progressionItemImpl = new ProgressionItemImpl();

		progressionItemImpl.setProgressionItemId(progressionItemId);
		progressionItemImpl.setProgressionId(progressionId);
		progressionItemImpl.setSessionId(sessionId);
		progressionItemImpl.setHomeworkId(homeworkId);
		progressionItemImpl.setProgressionFolderId(progressionFolderId);

		if (modifiedDate == Long.MIN_VALUE) {
			progressionItemImpl.setModifiedDate(null);
		}
		else {
			progressionItemImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (itemName == null) {
			progressionItemImpl.setItemName("");
		}
		else {
			progressionItemImpl.setItemName(itemName);
		}

		progressionItemImpl.setIsHomework(isHomework);

		if (duration == null) {
			progressionItemImpl.setDuration("");
		}
		else {
			progressionItemImpl.setDuration(duration);
		}

		progressionItemImpl.setType(type);
		progressionItemImpl.setOrder(order);

		progressionItemImpl.resetOriginalValues();

		return progressionItemImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		progressionItemId = objectInput.readLong();

		progressionId = objectInput.readLong();

		sessionId = objectInput.readLong();

		homeworkId = objectInput.readLong();

		progressionFolderId = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		itemName = objectInput.readUTF();

		isHomework = objectInput.readBoolean();
		duration = objectInput.readUTF();

		type = objectInput.readInt();

		order = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(progressionItemId);

		objectOutput.writeLong(progressionId);

		objectOutput.writeLong(sessionId);

		objectOutput.writeLong(homeworkId);

		objectOutput.writeLong(progressionFolderId);
		objectOutput.writeLong(modifiedDate);

		if (itemName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(itemName);
		}

		objectOutput.writeBoolean(isHomework);

		if (duration == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(duration);
		}

		objectOutput.writeInt(type);

		objectOutput.writeInt(order);
	}

	public long progressionItemId;
	public long progressionId;
	public long sessionId;
	public long homeworkId;
	public long progressionFolderId;
	public long modifiedDate;
	public String itemName;
	public boolean isHomework;
	public String duration;
	public int type;
	public int order;

}