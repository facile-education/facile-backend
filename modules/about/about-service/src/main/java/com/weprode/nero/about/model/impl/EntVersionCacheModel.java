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

package com.weprode.nero.about.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.about.model.EntVersion;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing EntVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EntVersionCacheModel
	implements CacheModel<EntVersion>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EntVersionCacheModel)) {
			return false;
		}

		EntVersionCacheModel entVersionCacheModel =
			(EntVersionCacheModel)object;

		if (entVersionId == entVersionCacheModel.entVersionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, entVersionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{entVersionId=");
		sb.append(entVersionId);
		sb.append(", version=");
		sb.append(version);
		sb.append(", details=");
		sb.append(details);
		sb.append(", versionDate=");
		sb.append(versionDate);
		sb.append(", isLast=");
		sb.append(isLast);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EntVersion toEntityModel() {
		EntVersionImpl entVersionImpl = new EntVersionImpl();

		entVersionImpl.setEntVersionId(entVersionId);

		if (version == null) {
			entVersionImpl.setVersion("");
		}
		else {
			entVersionImpl.setVersion(version);
		}

		if (details == null) {
			entVersionImpl.setDetails("");
		}
		else {
			entVersionImpl.setDetails(details);
		}

		if (versionDate == Long.MIN_VALUE) {
			entVersionImpl.setVersionDate(null);
		}
		else {
			entVersionImpl.setVersionDate(new Date(versionDate));
		}

		entVersionImpl.setIsLast(isLast);

		entVersionImpl.resetOriginalValues();

		return entVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		entVersionId = objectInput.readLong();
		version = objectInput.readUTF();
		details = objectInput.readUTF();
		versionDate = objectInput.readLong();

		isLast = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(entVersionId);

		if (version == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(version);
		}

		if (details == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(details);
		}

		objectOutput.writeLong(versionDate);

		objectOutput.writeBoolean(isLast);
	}

	public long entVersionId;
	public String version;
	public String details;
	public long versionDate;
	public boolean isLast;

}