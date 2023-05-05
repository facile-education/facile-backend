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

package com.weprode.nero.access.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.access.model.AccessProfile;
import com.weprode.nero.access.service.persistence.AccessProfilePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AccessProfile in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccessProfileCacheModel
	implements CacheModel<AccessProfile>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccessProfileCacheModel)) {
			return false;
		}

		AccessProfileCacheModel accessProfileCacheModel =
			(AccessProfileCacheModel)object;

		if (accessProfilePK.equals(accessProfileCacheModel.accessProfilePK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accessProfilePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", accessId=");
		sb.append(accessId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccessProfile toEntityModel() {
		AccessProfileImpl accessProfileImpl = new AccessProfileImpl();

		if (uuid == null) {
			accessProfileImpl.setUuid("");
		}
		else {
			accessProfileImpl.setUuid(uuid);
		}

		accessProfileImpl.setAccessId(accessId);
		accessProfileImpl.setRoleId(roleId);

		accessProfileImpl.resetOriginalValues();

		return accessProfileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		accessId = objectInput.readLong();

		roleId = objectInput.readLong();

		accessProfilePK = new AccessProfilePK(accessId, roleId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(accessId);

		objectOutput.writeLong(roleId);
	}

	public String uuid;
	public long accessId;
	public long roleId;
	public transient AccessProfilePK accessProfilePK;

}