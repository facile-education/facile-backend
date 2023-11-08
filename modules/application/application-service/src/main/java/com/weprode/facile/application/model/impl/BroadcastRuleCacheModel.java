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

package com.weprode.facile.application.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.application.model.BroadcastRule;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing BroadcastRule in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BroadcastRuleCacheModel
	implements CacheModel<BroadcastRule>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof BroadcastRuleCacheModel)) {
			return false;
		}

		BroadcastRuleCacheModel broadcastRuleCacheModel =
			(BroadcastRuleCacheModel)object;

		if (broadcastRuleId == broadcastRuleCacheModel.broadcastRuleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, broadcastRuleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{broadcastRuleId=");
		sb.append(broadcastRuleId);
		sb.append(", applicationId=");
		sb.append(applicationId);
		sb.append(", schoolId=");
		sb.append(schoolId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append(", orgId=");
		sb.append(orgId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BroadcastRule toEntityModel() {
		BroadcastRuleImpl broadcastRuleImpl = new BroadcastRuleImpl();

		broadcastRuleImpl.setBroadcastRuleId(broadcastRuleId);
		broadcastRuleImpl.setApplicationId(applicationId);
		broadcastRuleImpl.setSchoolId(schoolId);
		broadcastRuleImpl.setRoleId(roleId);
		broadcastRuleImpl.setOrgId(orgId);
		broadcastRuleImpl.setGroupId(groupId);

		broadcastRuleImpl.resetOriginalValues();

		return broadcastRuleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		broadcastRuleId = objectInput.readLong();

		applicationId = objectInput.readLong();

		schoolId = objectInput.readLong();

		roleId = objectInput.readLong();

		orgId = objectInput.readLong();

		groupId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(broadcastRuleId);

		objectOutput.writeLong(applicationId);

		objectOutput.writeLong(schoolId);

		objectOutput.writeLong(roleId);

		objectOutput.writeLong(orgId);

		objectOutput.writeLong(groupId);
	}

	public long broadcastRuleId;
	public long applicationId;
	public long schoolId;
	public long roleId;
	public long orgId;
	public long groupId;

}