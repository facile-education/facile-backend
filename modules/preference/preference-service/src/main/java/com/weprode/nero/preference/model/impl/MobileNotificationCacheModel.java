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

package com.weprode.nero.preference.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.preference.model.MobileNotification;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MobileNotification in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MobileNotificationCacheModel
	implements CacheModel<MobileNotification>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MobileNotificationCacheModel)) {
			return false;
		}

		MobileNotificationCacheModel mobileNotificationCacheModel =
			(MobileNotificationCacheModel)object;

		if (mobileNotificationId ==
				mobileNotificationCacheModel.mobileNotificationId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mobileNotificationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{mobileNotificationId=");
		sb.append(mobileNotificationId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", etabId=");
		sb.append(etabId);
		sb.append(", enable=");
		sb.append(enable);
		sb.append(", token=");
		sb.append(token);
		sb.append(", device=");
		sb.append(device);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MobileNotification toEntityModel() {
		MobileNotificationImpl mobileNotificationImpl =
			new MobileNotificationImpl();

		mobileNotificationImpl.setMobileNotificationId(mobileNotificationId);
		mobileNotificationImpl.setUserId(userId);
		mobileNotificationImpl.setEtabId(etabId);
		mobileNotificationImpl.setEnable(enable);

		if (token == null) {
			mobileNotificationImpl.setToken("");
		}
		else {
			mobileNotificationImpl.setToken(token);
		}

		if (device == null) {
			mobileNotificationImpl.setDevice("");
		}
		else {
			mobileNotificationImpl.setDevice(device);
		}

		mobileNotificationImpl.resetOriginalValues();

		return mobileNotificationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mobileNotificationId = objectInput.readLong();

		userId = objectInput.readLong();

		etabId = objectInput.readLong();

		enable = objectInput.readBoolean();
		token = objectInput.readUTF();
		device = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mobileNotificationId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(etabId);

		objectOutput.writeBoolean(enable);

		if (token == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(token);
		}

		if (device == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(device);
		}
	}

	public long mobileNotificationId;
	public long userId;
	public long etabId;
	public boolean enable;
	public String token;
	public String device;

}