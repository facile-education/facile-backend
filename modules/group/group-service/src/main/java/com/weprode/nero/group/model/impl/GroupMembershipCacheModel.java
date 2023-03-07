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

import com.weprode.nero.group.model.GroupMembership;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GroupMembership in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class GroupMembershipCacheModel
	implements CacheModel<GroupMembership>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof GroupMembershipCacheModel)) {
			return false;
		}

		GroupMembershipCacheModel groupMembershipCacheModel =
			(GroupMembershipCacheModel)object;

		if (membershipId == groupMembershipCacheModel.membershipId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, membershipId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{membershipId=");
		sb.append(membershipId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", fullYear=");
		sb.append(fullYear);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GroupMembership toEntityModel() {
		GroupMembershipImpl groupMembershipImpl = new GroupMembershipImpl();

		groupMembershipImpl.setMembershipId(membershipId);
		groupMembershipImpl.setGroupId(groupId);
		groupMembershipImpl.setUserId(userId);

		if (startDate == Long.MIN_VALUE) {
			groupMembershipImpl.setStartDate(null);
		}
		else {
			groupMembershipImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			groupMembershipImpl.setEndDate(null);
		}
		else {
			groupMembershipImpl.setEndDate(new Date(endDate));
		}

		groupMembershipImpl.setFullYear(fullYear);

		groupMembershipImpl.resetOriginalValues();

		return groupMembershipImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		membershipId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();

		fullYear = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(membershipId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		objectOutput.writeBoolean(fullYear);
	}

	public long membershipId;
	public long groupId;
	public long userId;
	public long startDate;
	public long endDate;
	public boolean fullYear;

}