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

package com.weprode.facile.preference.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.preference.model.UserProperties;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserProperties in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserPropertiesCacheModel
	implements CacheModel<UserProperties>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserPropertiesCacheModel)) {
			return false;
		}

		UserPropertiesCacheModel userPropertiesCacheModel =
			(UserPropertiesCacheModel)object;

		if (userId == userPropertiesCacheModel.userId) {
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
		StringBundler sb = new StringBundler(17);

		sb.append("{userId=");
		sb.append(userId);
		sb.append(", manualAccount=");
		sb.append(manualAccount);
		sb.append(", hideMenu=");
		sb.append(hideMenu);
		sb.append(", themeColor=");
		sb.append(themeColor);
		sb.append(", etabId=");
		sb.append(etabId);
		sb.append(", termsOfUseAgreedDate=");
		sb.append(termsOfUseAgreedDate);
		sb.append(", lastSynchroDate=");
		sb.append(lastSynchroDate);
		sb.append(", lastDashboardAccessDate=");
		sb.append(lastDashboardAccessDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserProperties toEntityModel() {
		UserPropertiesImpl userPropertiesImpl = new UserPropertiesImpl();

		userPropertiesImpl.setUserId(userId);
		userPropertiesImpl.setManualAccount(manualAccount);
		userPropertiesImpl.setHideMenu(hideMenu);

		if (themeColor == null) {
			userPropertiesImpl.setThemeColor("");
		}
		else {
			userPropertiesImpl.setThemeColor(themeColor);
		}

		userPropertiesImpl.setEtabId(etabId);

		if (termsOfUseAgreedDate == Long.MIN_VALUE) {
			userPropertiesImpl.setTermsOfUseAgreedDate(null);
		}
		else {
			userPropertiesImpl.setTermsOfUseAgreedDate(
				new Date(termsOfUseAgreedDate));
		}

		if (lastSynchroDate == Long.MIN_VALUE) {
			userPropertiesImpl.setLastSynchroDate(null);
		}
		else {
			userPropertiesImpl.setLastSynchroDate(new Date(lastSynchroDate));
		}

		if (lastDashboardAccessDate == Long.MIN_VALUE) {
			userPropertiesImpl.setLastDashboardAccessDate(null);
		}
		else {
			userPropertiesImpl.setLastDashboardAccessDate(
				new Date(lastDashboardAccessDate));
		}

		userPropertiesImpl.resetOriginalValues();

		return userPropertiesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userId = objectInput.readLong();

		manualAccount = objectInput.readBoolean();

		hideMenu = objectInput.readBoolean();
		themeColor = objectInput.readUTF();

		etabId = objectInput.readLong();
		termsOfUseAgreedDate = objectInput.readLong();
		lastSynchroDate = objectInput.readLong();
		lastDashboardAccessDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(userId);

		objectOutput.writeBoolean(manualAccount);

		objectOutput.writeBoolean(hideMenu);

		if (themeColor == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(themeColor);
		}

		objectOutput.writeLong(etabId);
		objectOutput.writeLong(termsOfUseAgreedDate);
		objectOutput.writeLong(lastSynchroDate);
		objectOutput.writeLong(lastDashboardAccessDate);
	}

	public long userId;
	public boolean manualAccount;
	public boolean hideMenu;
	public String themeColor;
	public long etabId;
	public long termsOfUseAgreedDate;
	public long lastSynchroDate;
	public long lastDashboardAccessDate;

}