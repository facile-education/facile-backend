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

import com.weprode.nero.preference.model.MobileDevice;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MobileDevice in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MobileDeviceCacheModel
	implements CacheModel<MobileDevice>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MobileDeviceCacheModel)) {
			return false;
		}

		MobileDeviceCacheModel mobileDeviceCacheModel =
			(MobileDeviceCacheModel)object;

		if (mobileDeviceId == mobileDeviceCacheModel.mobileDeviceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mobileDeviceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{mobileDeviceId=");
		sb.append(mobileDeviceId);
		sb.append(", manufaturerDeviceId=");
		sb.append(manufaturerDeviceId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", deviceModel=");
		sb.append(deviceModel);
		sb.append(", manufacturer=");
		sb.append(manufacturer);
		sb.append(", operatingSystem=");
		sb.append(operatingSystem);
		sb.append(", operatingSystemVersion=");
		sb.append(operatingSystemVersion);
		sb.append(", browserUA=");
		sb.append(browserUA);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MobileDevice toEntityModel() {
		MobileDeviceImpl mobileDeviceImpl = new MobileDeviceImpl();

		mobileDeviceImpl.setMobileDeviceId(mobileDeviceId);

		if (manufaturerDeviceId == null) {
			mobileDeviceImpl.setManufaturerDeviceId("");
		}
		else {
			mobileDeviceImpl.setManufaturerDeviceId(manufaturerDeviceId);
		}

		mobileDeviceImpl.setUserId(userId);

		if (deviceModel == null) {
			mobileDeviceImpl.setDeviceModel("");
		}
		else {
			mobileDeviceImpl.setDeviceModel(deviceModel);
		}

		if (manufacturer == null) {
			mobileDeviceImpl.setManufacturer("");
		}
		else {
			mobileDeviceImpl.setManufacturer(manufacturer);
		}

		if (operatingSystem == null) {
			mobileDeviceImpl.setOperatingSystem("");
		}
		else {
			mobileDeviceImpl.setOperatingSystem(operatingSystem);
		}

		if (operatingSystemVersion == null) {
			mobileDeviceImpl.setOperatingSystemVersion("");
		}
		else {
			mobileDeviceImpl.setOperatingSystemVersion(operatingSystemVersion);
		}

		if (browserUA == null) {
			mobileDeviceImpl.setBrowserUA("");
		}
		else {
			mobileDeviceImpl.setBrowserUA(browserUA);
		}

		mobileDeviceImpl.resetOriginalValues();

		return mobileDeviceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mobileDeviceId = objectInput.readLong();
		manufaturerDeviceId = objectInput.readUTF();

		userId = objectInput.readLong();
		deviceModel = objectInput.readUTF();
		manufacturer = objectInput.readUTF();
		operatingSystem = objectInput.readUTF();
		operatingSystemVersion = objectInput.readUTF();
		browserUA = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mobileDeviceId);

		if (manufaturerDeviceId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(manufaturerDeviceId);
		}

		objectOutput.writeLong(userId);

		if (deviceModel == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(deviceModel);
		}

		if (manufacturer == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(manufacturer);
		}

		if (operatingSystem == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(operatingSystem);
		}

		if (operatingSystemVersion == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(operatingSystemVersion);
		}

		if (browserUA == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(browserUA);
		}
	}

	public long mobileDeviceId;
	public String manufaturerDeviceId;
	public long userId;
	public String deviceModel;
	public String manufacturer;
	public String operatingSystem;
	public String operatingSystemVersion;
	public String browserUA;

}