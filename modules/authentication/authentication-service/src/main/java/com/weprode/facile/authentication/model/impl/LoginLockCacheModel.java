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

package com.weprode.facile.authentication.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.authentication.model.LoginLock;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LoginLock in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LoginLockCacheModel
	implements CacheModel<LoginLock>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LoginLockCacheModel)) {
			return false;
		}

		LoginLockCacheModel loginLockCacheModel = (LoginLockCacheModel)object;

		if (login.equals(loginLockCacheModel.login)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, login);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{login=");
		sb.append(login);
		sb.append(", failedLoginAttempts=");
		sb.append(failedLoginAttempts);
		sb.append(", isLocked=");
		sb.append(isLocked);
		sb.append(", lockEndDate=");
		sb.append(lockEndDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LoginLock toEntityModel() {
		LoginLockImpl loginLockImpl = new LoginLockImpl();

		if (login == null) {
			loginLockImpl.setLogin("");
		}
		else {
			loginLockImpl.setLogin(login);
		}

		loginLockImpl.setFailedLoginAttempts(failedLoginAttempts);
		loginLockImpl.setIsLocked(isLocked);

		if (lockEndDate == Long.MIN_VALUE) {
			loginLockImpl.setLockEndDate(null);
		}
		else {
			loginLockImpl.setLockEndDate(new Date(lockEndDate));
		}

		loginLockImpl.resetOriginalValues();

		return loginLockImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		login = objectInput.readUTF();

		failedLoginAttempts = objectInput.readInt();

		isLocked = objectInput.readBoolean();
		lockEndDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (login == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(login);
		}

		objectOutput.writeInt(failedLoginAttempts);

		objectOutput.writeBoolean(isLocked);
		objectOutput.writeLong(lockEndDate);
	}

	public String login;
	public int failedLoginAttempts;
	public boolean isLocked;
	public long lockEndDate;

}