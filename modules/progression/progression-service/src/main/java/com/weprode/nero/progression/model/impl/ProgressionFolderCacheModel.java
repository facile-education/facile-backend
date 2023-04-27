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

import com.weprode.nero.progression.model.ProgressionFolder;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProgressionFolder in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProgressionFolderCacheModel
	implements CacheModel<ProgressionFolder>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProgressionFolderCacheModel)) {
			return false;
		}

		ProgressionFolderCacheModel progressionFolderCacheModel =
			(ProgressionFolderCacheModel)object;

		if (progressionFolderId ==
				progressionFolderCacheModel.progressionFolderId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, progressionFolderId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{progressionFolderId=");
		sb.append(progressionFolderId);
		sb.append(", progressionId=");
		sb.append(progressionId);
		sb.append(", parentFolderId=");
		sb.append(parentFolderId);
		sb.append(", folderName=");
		sb.append(folderName);
		sb.append(", order=");
		sb.append(order);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProgressionFolder toEntityModel() {
		ProgressionFolderImpl progressionFolderImpl =
			new ProgressionFolderImpl();

		progressionFolderImpl.setProgressionFolderId(progressionFolderId);
		progressionFolderImpl.setProgressionId(progressionId);
		progressionFolderImpl.setParentFolderId(parentFolderId);

		if (folderName == null) {
			progressionFolderImpl.setFolderName("");
		}
		else {
			progressionFolderImpl.setFolderName(folderName);
		}

		progressionFolderImpl.setOrder(order);

		progressionFolderImpl.resetOriginalValues();

		return progressionFolderImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		progressionFolderId = objectInput.readLong();

		progressionId = objectInput.readLong();

		parentFolderId = objectInput.readLong();
		folderName = objectInput.readUTF();

		order = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(progressionFolderId);

		objectOutput.writeLong(progressionId);

		objectOutput.writeLong(parentFolderId);

		if (folderName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(folderName);
		}

		objectOutput.writeInt(order);
	}

	public long progressionFolderId;
	public long progressionId;
	public long parentFolderId;
	public String folderName;
	public int order;

}