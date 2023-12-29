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

package com.weprode.facile.mobile.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.mobile.model.UserMobileToken;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserMobileToken in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserMobileTokenCacheModel
	implements CacheModel<UserMobileToken>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserMobileTokenCacheModel)) {
			return false;
		}

		UserMobileTokenCacheModel userMobileTokenCacheModel =
			(UserMobileTokenCacheModel)object;

		if (userId == userMobileTokenCacheModel.userId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{userId=");
		sb.append(userId);
		sb.append(", mobileToken=");
		sb.append(mobileToken);
		sb.append(", creationDate=");
		sb.append(creationDate);
		sb.append(", modificationDate=");
		sb.append(modificationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserMobileToken toEntityModel() {
		UserMobileTokenImpl userMobileTokenImpl = new UserMobileTokenImpl();

		userMobileTokenImpl.setUserId(userId);

		if (mobileToken == null) {
			userMobileTokenImpl.setMobileToken("");
		}
		else {
			userMobileTokenImpl.setMobileToken(mobileToken);
		}

		if (creationDate == Long.MIN_VALUE) {
			userMobileTokenImpl.setCreationDate(null);
		}
		else {
			userMobileTokenImpl.setCreationDate(new Date(creationDate));
		}

		if (modificationDate == Long.MIN_VALUE) {
			userMobileTokenImpl.setModificationDate(null);
		}
		else {
			userMobileTokenImpl.setModificationDate(new Date(modificationDate));
		}

		userMobileTokenImpl.resetOriginalValues();

		return userMobileTokenImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userId = objectInput.readLong();
		mobileToken = objectInput.readUTF();
		creationDate = objectInput.readLong();
		modificationDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(userId);

		if (mobileToken == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mobileToken);
		}

		objectOutput.writeLong(creationDate);
		objectOutput.writeLong(modificationDate);
	}

	public long userId;
	public String mobileToken;
	public long creationDate;
	public long modificationDate;

}