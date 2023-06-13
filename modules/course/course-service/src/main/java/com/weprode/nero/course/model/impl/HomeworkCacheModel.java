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

package com.weprode.nero.course.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.course.model.Homework;

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
		StringBundler sb = new StringBundler(23);

		sb.append("{homeworkId=");
		sb.append(homeworkId);
		sb.append(", homeworkType=");
		sb.append(homeworkType);
		sb.append(", courseId=");
		sb.append(courseId);
		sb.append(", sourceSessionId=");
		sb.append(sourceSessionId);
		sb.append(", fromDate=");
		sb.append(fromDate);
		sb.append(", targetSessionId=");
		sb.append(targetSessionId);
		sb.append(", targetDate=");
		sb.append(targetDate);
		sb.append(", teacherId=");
		sb.append(teacherId);
		sb.append(", isCustomStudentList=");
		sb.append(isCustomStudentList);
		sb.append(", publicationDate=");
		sb.append(publicationDate);
		sb.append(", isDraft=");
		sb.append(isDraft);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Homework toEntityModel() {
		HomeworkImpl homeworkImpl = new HomeworkImpl();

		homeworkImpl.setHomeworkId(homeworkId);
		homeworkImpl.setHomeworkType(homeworkType);
		homeworkImpl.setCourseId(courseId);
		homeworkImpl.setSourceSessionId(sourceSessionId);

		if (fromDate == Long.MIN_VALUE) {
			homeworkImpl.setFromDate(null);
		}
		else {
			homeworkImpl.setFromDate(new Date(fromDate));
		}

		homeworkImpl.setTargetSessionId(targetSessionId);

		if (targetDate == Long.MIN_VALUE) {
			homeworkImpl.setTargetDate(null);
		}
		else {
			homeworkImpl.setTargetDate(new Date(targetDate));
		}

		homeworkImpl.setTeacherId(teacherId);
		homeworkImpl.setIsCustomStudentList(isCustomStudentList);

		if (publicationDate == Long.MIN_VALUE) {
			homeworkImpl.setPublicationDate(null);
		}
		else {
			homeworkImpl.setPublicationDate(new Date(publicationDate));
		}

		homeworkImpl.setIsDraft(isDraft);

		homeworkImpl.resetOriginalValues();

		return homeworkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		homeworkId = objectInput.readLong();

		homeworkType = objectInput.readInt();

		courseId = objectInput.readLong();

		sourceSessionId = objectInput.readLong();
		fromDate = objectInput.readLong();

		targetSessionId = objectInput.readLong();
		targetDate = objectInput.readLong();

		teacherId = objectInput.readLong();

		isCustomStudentList = objectInput.readBoolean();
		publicationDate = objectInput.readLong();

		isDraft = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(homeworkId);

		objectOutput.writeInt(homeworkType);

		objectOutput.writeLong(courseId);

		objectOutput.writeLong(sourceSessionId);
		objectOutput.writeLong(fromDate);

		objectOutput.writeLong(targetSessionId);
		objectOutput.writeLong(targetDate);

		objectOutput.writeLong(teacherId);

		objectOutput.writeBoolean(isCustomStudentList);
		objectOutput.writeLong(publicationDate);

		objectOutput.writeBoolean(isDraft);
	}

	public long homeworkId;
	public int homeworkType;
	public long courseId;
	public long sourceSessionId;
	public long fromDate;
	public long targetSessionId;
	public long targetDate;
	public long teacherId;
	public boolean isCustomStudentList;
	public long publicationDate;
	public boolean isDraft;

}