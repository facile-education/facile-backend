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

package com.weprode.facile.access.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccessProfilePK
	implements Comparable<AccessProfilePK>, Serializable {

	public long accessId;
	public long roleId;

	public AccessProfilePK() {
	}

	public AccessProfilePK(long accessId, long roleId) {
		this.accessId = accessId;
		this.roleId = roleId;
	}

	public long getAccessId() {
		return accessId;
	}

	public void setAccessId(long accessId) {
		this.accessId = accessId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Override
	public int compareTo(AccessProfilePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (accessId < pk.accessId) {
			value = -1;
		}
		else if (accessId > pk.accessId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (roleId < pk.roleId) {
			value = -1;
		}
		else if (roleId > pk.roleId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccessProfilePK)) {
			return false;
		}

		AccessProfilePK pk = (AccessProfilePK)object;

		if ((accessId == pk.accessId) && (roleId == pk.roleId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, accessId);
		hashCode = HashUtil.hash(hashCode, roleId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("accessId=");

		sb.append(accessId);
		sb.append(", roleId=");

		sb.append(roleId);

		sb.append("}");

		return sb.toString();
	}

}