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

import com.weprode.nero.about.model.EntVersionUser;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing EntVersionUser in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EntVersionUserCacheModel
	implements CacheModel<EntVersionUser>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EntVersionUserCacheModel)) {
			return false;
		}

		EntVersionUserCacheModel entVersionUserCacheModel =
			(EntVersionUserCacheModel)object;

		if (versionUserId == entVersionUserCacheModel.versionUserId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, versionUserId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{versionUserId=");
		sb.append(versionUserId);
		sb.append(", entVersionId=");
		sb.append(entVersionId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EntVersionUser toEntityModel() {
		EntVersionUserImpl entVersionUserImpl = new EntVersionUserImpl();

		entVersionUserImpl.setVersionUserId(versionUserId);
		entVersionUserImpl.setEntVersionId(entVersionId);
		entVersionUserImpl.setUserId(userId);

		entVersionUserImpl.resetOriginalValues();

		return entVersionUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		versionUserId = objectInput.readLong();

		entVersionId = objectInput.readLong();

		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(versionUserId);

		objectOutput.writeLong(entVersionId);

		objectOutput.writeLong(userId);
	}

	public long versionUserId;
	public long entVersionId;
	public long userId;

}