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

import com.weprode.facile.user.model.UserContact;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UserContact in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserContactCacheModel
	implements CacheModel<UserContact>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserContactCacheModel)) {
			return false;
		}

		UserContactCacheModel userContactCacheModel =
			(UserContactCacheModel)object;

		if (userId == userContactCacheModel.userId) {
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
		StringBundler sb = new StringBundler(11);

		sb.append("{userId=");
		sb.append(userId);
		sb.append(", address=");
		sb.append(address);
		sb.append(", mobilePhone=");
		sb.append(mobilePhone);
		sb.append(", homePhone=");
		sb.append(homePhone);
		sb.append(", proPhone=");
		sb.append(proPhone);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserContact toEntityModel() {
		UserContactImpl userContactImpl = new UserContactImpl();

		userContactImpl.setUserId(userId);

		if (address == null) {
			userContactImpl.setAddress("");
		}
		else {
			userContactImpl.setAddress(address);
		}

		if (mobilePhone == null) {
			userContactImpl.setMobilePhone("");
		}
		else {
			userContactImpl.setMobilePhone(mobilePhone);
		}

		if (homePhone == null) {
			userContactImpl.setHomePhone("");
		}
		else {
			userContactImpl.setHomePhone(homePhone);
		}

		if (proPhone == null) {
			userContactImpl.setProPhone("");
		}
		else {
			userContactImpl.setProPhone(proPhone);
		}

		userContactImpl.resetOriginalValues();

		return userContactImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userId = objectInput.readLong();
		address = objectInput.readUTF();
		mobilePhone = objectInput.readUTF();
		homePhone = objectInput.readUTF();
		proPhone = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(userId);

		if (address == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(address);
		}

		if (mobilePhone == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mobilePhone);
		}

		if (homePhone == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(homePhone);
		}

		if (proPhone == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(proPhone);
		}
	}

	public long userId;
	public String address;
	public String mobilePhone;
	public String homePhone;
	public String proPhone;

}