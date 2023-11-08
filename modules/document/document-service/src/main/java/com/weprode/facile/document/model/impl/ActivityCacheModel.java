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

package com.weprode.facile.document.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.document.model.Activity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Activity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ActivityCacheModel
	implements CacheModel<Activity>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ActivityCacheModel)) {
			return false;
		}

		ActivityCacheModel activityCacheModel = (ActivityCacheModel)object;

		if (activityId == activityCacheModel.activityId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, activityId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{activityId=");
		sb.append(activityId);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append(", folderId=");
		sb.append(folderId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", folderName=");
		sb.append(folderName);
		sb.append(", type=");
		sb.append(type);
		sb.append(", modificationDate=");
		sb.append(modificationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Activity toEntityModel() {
		ActivityImpl activityImpl = new ActivityImpl();

		activityImpl.setActivityId(activityId);
		activityImpl.setFileEntryId(fileEntryId);
		activityImpl.setFolderId(folderId);
		activityImpl.setUserId(userId);
		activityImpl.setGroupId(groupId);

		if (fileName == null) {
			activityImpl.setFileName("");
		}
		else {
			activityImpl.setFileName(fileName);
		}

		if (folderName == null) {
			activityImpl.setFolderName("");
		}
		else {
			activityImpl.setFolderName(folderName);
		}

		activityImpl.setType(type);

		if (modificationDate == Long.MIN_VALUE) {
			activityImpl.setModificationDate(null);
		}
		else {
			activityImpl.setModificationDate(new Date(modificationDate));
		}

		activityImpl.resetOriginalValues();

		return activityImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		activityId = objectInput.readLong();

		fileEntryId = objectInput.readLong();

		folderId = objectInput.readLong();

		userId = objectInput.readLong();

		groupId = objectInput.readLong();
		fileName = objectInput.readUTF();
		folderName = objectInput.readUTF();

		type = objectInput.readInt();
		modificationDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(activityId);

		objectOutput.writeLong(fileEntryId);

		objectOutput.writeLong(folderId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(groupId);

		if (fileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		if (folderName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(folderName);
		}

		objectOutput.writeInt(type);
		objectOutput.writeLong(modificationDate);
	}

	public long activityId;
	public long fileEntryId;
	public long folderId;
	public long userId;
	public long groupId;
	public String fileName;
	public String folderName;
	public int type;
	public long modificationDate;

}