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

import com.weprode.nero.schedule.model.StudentHomework;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StudentHomework in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class StudentHomeworkCacheModel
	implements CacheModel<StudentHomework>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof StudentHomeworkCacheModel)) {
			return false;
		}

		StudentHomeworkCacheModel studentHomeworkCacheModel =
			(StudentHomeworkCacheModel)object;

		if (studentHomeworkId == studentHomeworkCacheModel.studentHomeworkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, studentHomeworkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{studentHomeworkId=");
		sb.append(studentHomeworkId);
		sb.append(", homeworkId=");
		sb.append(homeworkId);
		sb.append(", studentId=");
		sb.append(studentId);
		sb.append(", isDone=");
		sb.append(isDone);
		sb.append(", isSent=");
		sb.append(isSent);
		sb.append(", sentDate=");
		sb.append(sentDate);
		sb.append(", sentFileId=");
		sb.append(sentFileId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StudentHomework toEntityModel() {
		StudentHomeworkImpl studentHomeworkImpl = new StudentHomeworkImpl();

		studentHomeworkImpl.setStudentHomeworkId(studentHomeworkId);
		studentHomeworkImpl.setHomeworkId(homeworkId);
		studentHomeworkImpl.setStudentId(studentId);
		studentHomeworkImpl.setIsDone(isDone);
		studentHomeworkImpl.setIsSent(isSent);

		if (sentDate == Long.MIN_VALUE) {
			studentHomeworkImpl.setSentDate(null);
		}
		else {
			studentHomeworkImpl.setSentDate(new Date(sentDate));
		}

		studentHomeworkImpl.setSentFileId(sentFileId);

		studentHomeworkImpl.resetOriginalValues();

		return studentHomeworkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		studentHomeworkId = objectInput.readLong();

		homeworkId = objectInput.readLong();

		studentId = objectInput.readLong();

		isDone = objectInput.readBoolean();

		isSent = objectInput.readBoolean();
		sentDate = objectInput.readLong();

		sentFileId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(studentHomeworkId);

		objectOutput.writeLong(homeworkId);

		objectOutput.writeLong(studentId);

		objectOutput.writeBoolean(isDone);

		objectOutput.writeBoolean(isSent);
		objectOutput.writeLong(sentDate);

		objectOutput.writeLong(sentFileId);
	}

	public long studentHomeworkId;
	public long homeworkId;
	public long studentId;
	public boolean isDone;
	public boolean isSent;
	public long sentDate;
	public long sentFileId;

}