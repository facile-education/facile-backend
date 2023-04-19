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

import com.weprode.nero.schedule.model.Homework;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Homework in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HomeworkCacheModel
	implements CacheModel<Homework>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HomeworkCacheModel)) {
			return false;
		}

		HomeworkCacheModel homeworkCacheModel = (HomeworkCacheModel)object;

		if (homeworkId == homeworkCacheModel.homeworkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, homeworkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{homeworkId=");
		sb.append(homeworkId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", sourceSessionId=");
		sb.append(sourceSessionId);
		sb.append(", targetSessionId=");
		sb.append(targetSessionId);
		sb.append(", targetWeekId=");
		sb.append(targetWeekId);
		sb.append(", targetDate=");
		sb.append(targetDate);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", teacherId=");
		sb.append(teacherId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", estimatedTime=");
		sb.append(estimatedTime);
		sb.append(", fromDate=");
		sb.append(fromDate);
		sb.append(", isCustomStudentList=");
		sb.append(isCustomStudentList);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Homework toEntityModel() {
		HomeworkImpl homeworkImpl = new HomeworkImpl();

		homeworkImpl.setHomeworkId(homeworkId);
		homeworkImpl.setType(type);
		homeworkImpl.setSourceSessionId(sourceSessionId);
		homeworkImpl.setTargetSessionId(targetSessionId);
		homeworkImpl.setTargetWeekId(targetWeekId);

		if (targetDate == Long.MIN_VALUE) {
			homeworkImpl.setTargetDate(null);
		}
		else {
			homeworkImpl.setTargetDate(new Date(targetDate));
		}

		homeworkImpl.setGroupId(groupId);
		homeworkImpl.setTeacherId(teacherId);

		if (description == null) {
			homeworkImpl.setDescription("");
		}
		else {
			homeworkImpl.setDescription(description);
		}

		homeworkImpl.setEstimatedTime(estimatedTime);

		if (fromDate == Long.MIN_VALUE) {
			homeworkImpl.setFromDate(null);
		}
		else {
			homeworkImpl.setFromDate(new Date(fromDate));
		}

		homeworkImpl.setIsCustomStudentList(isCustomStudentList);

		homeworkImpl.resetOriginalValues();

		return homeworkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		homeworkId = objectInput.readLong();

		type = objectInput.readLong();

		sourceSessionId = objectInput.readLong();

		targetSessionId = objectInput.readLong();

		targetWeekId = objectInput.readInt();
		targetDate = objectInput.readLong();

		groupId = objectInput.readLong();

		teacherId = objectInput.readLong();
		description = objectInput.readUTF();

		estimatedTime = objectInput.readLong();
		fromDate = objectInput.readLong();

		isCustomStudentList = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(homeworkId);

		objectOutput.writeLong(type);

		objectOutput.writeLong(sourceSessionId);

		objectOutput.writeLong(targetSessionId);

		objectOutput.writeInt(targetWeekId);
		objectOutput.writeLong(targetDate);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(teacherId);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(estimatedTime);
		objectOutput.writeLong(fromDate);

		objectOutput.writeBoolean(isCustomStudentList);
	}

	public long homeworkId;
	public long type;
	public long sourceSessionId;
	public long targetSessionId;
	public int targetWeekId;
	public long targetDate;
	public long groupId;
	public long teacherId;
	public String description;
	public long estimatedTime;
	public long fromDate;
	public boolean isCustomStudentList;

}