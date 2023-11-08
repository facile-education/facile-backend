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

import com.weprode.facile.document.model.Version;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Version in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class VersionCacheModel implements CacheModel<Version>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof VersionCacheModel)) {
			return false;
		}

		VersionCacheModel versionCacheModel = (VersionCacheModel)object;

		if (fileVersionId == versionCacheModel.fileVersionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, fileVersionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{fileVersionId=");
		sb.append(fileVersionId);
		sb.append(", dlFileEntryId=");
		sb.append(dlFileEntryId);
		sb.append(", versionNumber=");
		sb.append(versionNumber);
		sb.append(", comment=");
		sb.append(comment);
		sb.append(", downloadCount=");
		sb.append(downloadCount);
		sb.append(", viewCount=");
		sb.append(viewCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Version toEntityModel() {
		VersionImpl versionImpl = new VersionImpl();

		versionImpl.setFileVersionId(fileVersionId);
		versionImpl.setDlFileEntryId(dlFileEntryId);
		versionImpl.setVersionNumber(versionNumber);

		if (comment == null) {
			versionImpl.setComment("");
		}
		else {
			versionImpl.setComment(comment);
		}

		versionImpl.setDownloadCount(downloadCount);
		versionImpl.setViewCount(viewCount);

		versionImpl.resetOriginalValues();

		return versionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		fileVersionId = objectInput.readLong();

		dlFileEntryId = objectInput.readLong();

		versionNumber = objectInput.readDouble();
		comment = objectInput.readUTF();

		downloadCount = objectInput.readLong();

		viewCount = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(fileVersionId);

		objectOutput.writeLong(dlFileEntryId);

		objectOutput.writeDouble(versionNumber);

		if (comment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comment);
		}

		objectOutput.writeLong(downloadCount);

		objectOutput.writeLong(viewCount);
	}

	public long fileVersionId;
	public long dlFileEntryId;
	public double versionNumber;
	public String comment;
	public long downloadCount;
	public long viewCount;

}