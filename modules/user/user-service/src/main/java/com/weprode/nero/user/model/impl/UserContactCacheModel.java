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

package com.weprode.nero.user.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.user.model.UserContact;

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

		if (contactId == userContactCacheModel.contactId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, contactId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{contactId=");
		sb.append(contactId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", middleNames=");
		sb.append(middleNames);
		sb.append(", birthName=");
		sb.append(birthName);
		sb.append(", address=");
		sb.append(address);
		sb.append(", isAddressAuthorized=");
		sb.append(isAddressAuthorized);
		sb.append(", mail=");
		sb.append(mail);
		sb.append(", isMailAuthorized=");
		sb.append(isMailAuthorized);
		sb.append(", mobilePhone=");
		sb.append(mobilePhone);
		sb.append(", mobilePhoneSMS=");
		sb.append(mobilePhoneSMS);
		sb.append(", homePhone=");
		sb.append(homePhone);
		sb.append(", proPhone=");
		sb.append(proPhone);
		sb.append(", familyLink=");
		sb.append(familyLink);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserContact toEntityModel() {
		UserContactImpl userContactImpl = new UserContactImpl();

		userContactImpl.setContactId(contactId);
		userContactImpl.setUserId(userId);

		if (middleNames == null) {
			userContactImpl.setMiddleNames("");
		}
		else {
			userContactImpl.setMiddleNames(middleNames);
		}

		if (birthName == null) {
			userContactImpl.setBirthName("");
		}
		else {
			userContactImpl.setBirthName(birthName);
		}

		if (address == null) {
			userContactImpl.setAddress("");
		}
		else {
			userContactImpl.setAddress(address);
		}

		userContactImpl.setIsAddressAuthorized(isAddressAuthorized);

		if (mail == null) {
			userContactImpl.setMail("");
		}
		else {
			userContactImpl.setMail(mail);
		}

		userContactImpl.setIsMailAuthorized(isMailAuthorized);

		if (mobilePhone == null) {
			userContactImpl.setMobilePhone("");
		}
		else {
			userContactImpl.setMobilePhone(mobilePhone);
		}

		if (mobilePhoneSMS == null) {
			userContactImpl.setMobilePhoneSMS("");
		}
		else {
			userContactImpl.setMobilePhoneSMS(mobilePhoneSMS);
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

		if (familyLink == null) {
			userContactImpl.setFamilyLink("");
		}
		else {
			userContactImpl.setFamilyLink(familyLink);
		}

		userContactImpl.resetOriginalValues();

		return userContactImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		contactId = objectInput.readLong();

		userId = objectInput.readLong();
		middleNames = objectInput.readUTF();
		birthName = objectInput.readUTF();
		address = objectInput.readUTF();

		isAddressAuthorized = objectInput.readBoolean();
		mail = objectInput.readUTF();

		isMailAuthorized = objectInput.readBoolean();
		mobilePhone = objectInput.readUTF();
		mobilePhoneSMS = objectInput.readUTF();
		homePhone = objectInput.readUTF();
		proPhone = objectInput.readUTF();
		familyLink = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(contactId);

		objectOutput.writeLong(userId);

		if (middleNames == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(middleNames);
		}

		if (birthName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(birthName);
		}

		if (address == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(address);
		}

		objectOutput.writeBoolean(isAddressAuthorized);

		if (mail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mail);
		}

		objectOutput.writeBoolean(isMailAuthorized);

		if (mobilePhone == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mobilePhone);
		}

		if (mobilePhoneSMS == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mobilePhoneSMS);
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

		if (familyLink == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(familyLink);
		}
	}

	public long contactId;
	public long userId;
	public String middleNames;
	public String birthName;
	public String address;
	public boolean isAddressAuthorized;
	public String mail;
	public boolean isMailAuthorized;
	public String mobilePhone;
	public String mobilePhoneSMS;
	public String homePhone;
	public String proPhone;
	public String familyLink;

}