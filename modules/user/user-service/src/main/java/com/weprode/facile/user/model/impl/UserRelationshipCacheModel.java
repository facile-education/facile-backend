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

package com.weprode.facile.user.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.user.model.UserRelationship;
import com.weprode.facile.user.service.persistence.UserRelationshipPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UserRelationship in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserRelationshipCacheModel
	implements CacheModel<UserRelationship>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserRelationshipCacheModel)) {
			return false;
		}

		UserRelationshipCacheModel userRelationshipCacheModel =
			(UserRelationshipCacheModel)object;

		if (userRelationshipPK.equals(
				userRelationshipCacheModel.userRelationshipPK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userRelationshipPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{childUserId=");
		sb.append(childUserId);
		sb.append(", parentUserId=");
		sb.append(parentUserId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserRelationship toEntityModel() {
		UserRelationshipImpl userRelationshipImpl = new UserRelationshipImpl();

		userRelationshipImpl.setChildUserId(childUserId);
		userRelationshipImpl.setParentUserId(parentUserId);

		userRelationshipImpl.resetOriginalValues();

		return userRelationshipImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		childUserId = objectInput.readLong();

		parentUserId = objectInput.readLong();

		userRelationshipPK = new UserRelationshipPK(childUserId, parentUserId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(childUserId);

		objectOutput.writeLong(parentUserId);
	}

	public long childUserId;
	public long parentUserId;
	public transient UserRelationshipPK userRelationshipPK;

}