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

package com.weprode.nero.progression.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.progression.model.ItemAssignment;
import com.weprode.nero.progression.service.persistence.ItemAssignmentPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ItemAssignment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ItemAssignmentCacheModel
	implements CacheModel<ItemAssignment>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ItemAssignmentCacheModel)) {
			return false;
		}

		ItemAssignmentCacheModel itemAssignmentCacheModel =
			(ItemAssignmentCacheModel)object;

		if (itemAssignmentPK.equals(
				itemAssignmentCacheModel.itemAssignmentPK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, itemAssignmentPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{progressionItemId=");
		sb.append(progressionItemId);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", homeworkId=");
		sb.append(homeworkId);
		sb.append(", assignedDate=");
		sb.append(assignedDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ItemAssignment toEntityModel() {
		ItemAssignmentImpl itemAssignmentImpl = new ItemAssignmentImpl();

		itemAssignmentImpl.setProgressionItemId(progressionItemId);
		itemAssignmentImpl.setSessionId(sessionId);
		itemAssignmentImpl.setHomeworkId(homeworkId);

		if (assignedDate == Long.MIN_VALUE) {
			itemAssignmentImpl.setAssignedDate(null);
		}
		else {
			itemAssignmentImpl.setAssignedDate(new Date(assignedDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			itemAssignmentImpl.setModifiedDate(null);
		}
		else {
			itemAssignmentImpl.setModifiedDate(new Date(modifiedDate));
		}

		itemAssignmentImpl.resetOriginalValues();

		return itemAssignmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		progressionItemId = objectInput.readLong();

		sessionId = objectInput.readLong();

		homeworkId = objectInput.readLong();
		assignedDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		itemAssignmentPK = new ItemAssignmentPK(progressionItemId, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(progressionItemId);

		objectOutput.writeLong(sessionId);

		objectOutput.writeLong(homeworkId);
		objectOutput.writeLong(assignedDate);
		objectOutput.writeLong(modifiedDate);
	}

	public long progressionItemId;
	public long sessionId;
	public long homeworkId;
	public long assignedDate;
	public long modifiedDate;
	public transient ItemAssignmentPK itemAssignmentPK;

}