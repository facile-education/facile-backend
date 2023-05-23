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

package com.weprode.nero.schedule.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.schedule.model.GroupColor;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing GroupColor in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class GroupColorCacheModel
	implements CacheModel<GroupColor>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof GroupColorCacheModel)) {
			return false;
		}

		GroupColorCacheModel groupColorCacheModel =
			(GroupColorCacheModel)object;

		if (groupId == groupColorCacheModel.groupId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, groupId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{groupId=");
		sb.append(groupId);
		sb.append(", color=");
		sb.append(color);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GroupColor toEntityModel() {
		GroupColorImpl groupColorImpl = new GroupColorImpl();

		groupColorImpl.setGroupId(groupId);

		if (color == null) {
			groupColorImpl.setColor("");
		}
		else {
			groupColorImpl.setColor(color);
		}

		groupColorImpl.resetOriginalValues();

		return groupColorImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		groupId = objectInput.readLong();
		color = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(groupId);

		if (color == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(color);
		}
	}

	public long groupId;
	public String color;

}