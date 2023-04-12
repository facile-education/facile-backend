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

package com.weprode.nero.document.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.document.model.EditionLock;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing EditionLock in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EditionLockCacheModel
	implements CacheModel<EditionLock>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EditionLockCacheModel)) {
			return false;
		}

		EditionLockCacheModel editionLockCacheModel =
			(EditionLockCacheModel)object;

		if (fileId == editionLockCacheModel.fileId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, fileId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{fileId=");
		sb.append(fileId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", editionDate=");
		sb.append(editionDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EditionLock toEntityModel() {
		EditionLockImpl editionLockImpl = new EditionLockImpl();

		editionLockImpl.setFileId(fileId);
		editionLockImpl.setUserId(userId);

		if (editionDate == Long.MIN_VALUE) {
			editionLockImpl.setEditionDate(null);
		}
		else {
			editionLockImpl.setEditionDate(new Date(editionDate));
		}

		editionLockImpl.resetOriginalValues();

		return editionLockImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		fileId = objectInput.readLong();

		userId = objectInput.readLong();
		editionDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(fileId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(editionDate);
	}

	public long fileId;
	public long userId;
	public long editionDate;

}