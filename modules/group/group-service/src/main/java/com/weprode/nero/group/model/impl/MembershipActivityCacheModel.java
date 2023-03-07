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

package com.weprode.nero.group.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.group.model.MembershipActivity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MembershipActivity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MembershipActivityCacheModel
	implements CacheModel<MembershipActivity>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MembershipActivityCacheModel)) {
			return false;
		}

		MembershipActivityCacheModel membershipActivityCacheModel =
			(MembershipActivityCacheModel)object;

		if (membershipActivityId ==
				membershipActivityCacheModel.membershipActivityId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, membershipActivityId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{membershipActivityId=");
		sb.append(membershipActivityId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", actionUserId=");
		sb.append(actionUserId);
		sb.append(", targetUserIds=");
		sb.append(targetUserIds);
		sb.append(", incoming=");
		sb.append(incoming);
		sb.append(", movementDate=");
		sb.append(movementDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MembershipActivity toEntityModel() {
		MembershipActivityImpl membershipActivityImpl =
			new MembershipActivityImpl();

		membershipActivityImpl.setMembershipActivityId(membershipActivityId);
		membershipActivityImpl.setGroupId(groupId);
		membershipActivityImpl.setActionUserId(actionUserId);

		if (targetUserIds == null) {
			membershipActivityImpl.setTargetUserIds("");
		}
		else {
			membershipActivityImpl.setTargetUserIds(targetUserIds);
		}

		membershipActivityImpl.setIncoming(incoming);

		if (movementDate == Long.MIN_VALUE) {
			membershipActivityImpl.setMovementDate(null);
		}
		else {
			membershipActivityImpl.setMovementDate(new Date(movementDate));
		}

		membershipActivityImpl.resetOriginalValues();

		return membershipActivityImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		membershipActivityId = objectInput.readLong();

		groupId = objectInput.readLong();

		actionUserId = objectInput.readLong();
		targetUserIds = objectInput.readUTF();

		incoming = objectInput.readBoolean();
		movementDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(membershipActivityId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(actionUserId);

		if (targetUserIds == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(targetUserIds);
		}

		objectOutput.writeBoolean(incoming);
		objectOutput.writeLong(movementDate);
	}

	public long membershipActivityId;
	public long groupId;
	public long actionUserId;
	public String targetUserIds;
	public boolean incoming;
	public long movementDate;

}