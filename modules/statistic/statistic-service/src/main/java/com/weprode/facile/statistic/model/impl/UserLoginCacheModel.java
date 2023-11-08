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

package com.weprode.facile.statistic.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.statistic.model.UserLogin;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserLogin in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserLoginCacheModel
	implements CacheModel<UserLogin>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserLoginCacheModel)) {
			return false;
		}

		UserLoginCacheModel userLoginCacheModel = (UserLoginCacheModel)object;

		if (userLoginId == userLoginCacheModel.userLoginId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userLoginId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{userLoginId=");
		sb.append(userLoginId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", loginDate=");
		sb.append(loginDate);
		sb.append(", role=");
		sb.append(role);
		sb.append(", schoolId=");
		sb.append(schoolId);
		sb.append(", isMobileApp=");
		sb.append(isMobileApp);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserLogin toEntityModel() {
		UserLoginImpl userLoginImpl = new UserLoginImpl();

		userLoginImpl.setUserLoginId(userLoginId);
		userLoginImpl.setUserId(userId);

		if (loginDate == Long.MIN_VALUE) {
			userLoginImpl.setLoginDate(null);
		}
		else {
			userLoginImpl.setLoginDate(new Date(loginDate));
		}

		userLoginImpl.setRole(role);
		userLoginImpl.setSchoolId(schoolId);
		userLoginImpl.setIsMobileApp(isMobileApp);

		userLoginImpl.resetOriginalValues();

		return userLoginImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userLoginId = objectInput.readLong();

		userId = objectInput.readLong();
		loginDate = objectInput.readLong();

		role = objectInput.readInt();

		schoolId = objectInput.readLong();

		isMobileApp = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(userLoginId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(loginDate);

		objectOutput.writeInt(role);

		objectOutput.writeLong(schoolId);

		objectOutput.writeBoolean(isMobileApp);
	}

	public long userLoginId;
	public long userId;
	public long loginDate;
	public int role;
	public long schoolId;
	public boolean isMobileApp;

}