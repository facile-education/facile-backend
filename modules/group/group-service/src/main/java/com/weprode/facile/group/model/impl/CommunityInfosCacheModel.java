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

package com.weprode.facile.group.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.group.model.CommunityInfos;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommunityInfos in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CommunityInfosCacheModel
	implements CacheModel<CommunityInfos>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommunityInfosCacheModel)) {
			return false;
		}

		CommunityInfosCacheModel communityInfosCacheModel =
			(CommunityInfosCacheModel)object;

		if (communityInfosId == communityInfosCacheModel.communityInfosId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, communityInfosId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{communityInfosId=");
		sb.append(communityInfosId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", creatorId=");
		sb.append(creatorId);
		sb.append(", creationDate=");
		sb.append(creationDate);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", isPedagogical=");
		sb.append(isPedagogical);
		sb.append(", isContactList=");
		sb.append(isContactList);
		sb.append(", color=");
		sb.append(color);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommunityInfos toEntityModel() {
		CommunityInfosImpl communityInfosImpl = new CommunityInfosImpl();

		communityInfosImpl.setCommunityInfosId(communityInfosId);
		communityInfosImpl.setGroupId(groupId);
		communityInfosImpl.setStatus(status);
		communityInfosImpl.setCreatorId(creatorId);

		if (creationDate == Long.MIN_VALUE) {
			communityInfosImpl.setCreationDate(null);
		}
		else {
			communityInfosImpl.setCreationDate(new Date(creationDate));
		}

		if (expirationDate == Long.MIN_VALUE) {
			communityInfosImpl.setExpirationDate(null);
		}
		else {
			communityInfosImpl.setExpirationDate(new Date(expirationDate));
		}

		communityInfosImpl.setIsPedagogical(isPedagogical);
		communityInfosImpl.setIsContactList(isContactList);

		if (color == null) {
			communityInfosImpl.setColor("");
		}
		else {
			communityInfosImpl.setColor(color);
		}

		communityInfosImpl.resetOriginalValues();

		return communityInfosImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		communityInfosId = objectInput.readLong();

		groupId = objectInput.readLong();

		status = objectInput.readInt();

		creatorId = objectInput.readLong();
		creationDate = objectInput.readLong();
		expirationDate = objectInput.readLong();

		isPedagogical = objectInput.readBoolean();

		isContactList = objectInput.readBoolean();
		color = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(communityInfosId);

		objectOutput.writeLong(groupId);

		objectOutput.writeInt(status);

		objectOutput.writeLong(creatorId);
		objectOutput.writeLong(creationDate);
		objectOutput.writeLong(expirationDate);

		objectOutput.writeBoolean(isPedagogical);

		objectOutput.writeBoolean(isContactList);

		if (color == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(color);
		}
	}

	public long communityInfosId;
	public long groupId;
	public int status;
	public long creatorId;
	public long creationDate;
	public long expirationDate;
	public boolean isPedagogical;
	public boolean isContactList;
	public String color;

}