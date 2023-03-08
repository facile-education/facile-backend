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

package com.weprode.nero.user.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserRelationshipPK
	implements Comparable<UserRelationshipPK>, Serializable {

	public long childUserId;
	public long parentUserId;

	public UserRelationshipPK() {
	}

	public UserRelationshipPK(long childUserId, long parentUserId) {
		this.childUserId = childUserId;
		this.parentUserId = parentUserId;
	}

	public long getChildUserId() {
		return childUserId;
	}

	public void setChildUserId(long childUserId) {
		this.childUserId = childUserId;
	}

	public long getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(long parentUserId) {
		this.parentUserId = parentUserId;
	}

	@Override
	public int compareTo(UserRelationshipPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (childUserId < pk.childUserId) {
			value = -1;
		}
		else if (childUserId > pk.childUserId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (parentUserId < pk.parentUserId) {
			value = -1;
		}
		else if (parentUserId > pk.parentUserId) {
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

		if (!(object instanceof UserRelationshipPK)) {
			return false;
		}

		UserRelationshipPK pk = (UserRelationshipPK)object;

		if ((childUserId == pk.childUserId) &&
			(parentUserId == pk.parentUserId)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, childUserId);
		hashCode = HashUtil.hash(hashCode, parentUserId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("childUserId=");

		sb.append(childUserId);
		sb.append(", parentUserId=");

		sb.append(parentUserId);

		sb.append("}");

		return sb.toString();
	}

}