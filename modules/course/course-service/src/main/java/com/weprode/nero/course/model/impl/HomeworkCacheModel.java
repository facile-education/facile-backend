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
		StringBundler sb = new StringBundler(25);

		sb.append("{homeworkId=");
		sb.append(homeworkId);
		sb.append(", homeworkType=");
		sb.append(homeworkType);
		sb.append(", courseId=");
		sb.append(courseId);
		sb.append(", teacherId=");
		sb.append(teacherId);
		sb.append(", modificationDate=");
		sb.append(modificationDate);
		sb.append(", sourceSessionId=");
		sb.append(sourceSessionId);
		sb.append(", targetSessionId=");
		sb.append(targetSessionId);
		sb.append(", targetDate=");
		sb.append(targetDate);
		sb.append(", isCustomStudentList=");
		sb.append(isCustomStudentList);
		sb.append(", estimatedTime=");
		sb.append(estimatedTime);
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
		homeworkImpl.setTeacherId(teacherId);

		if (modificationDate == Long.MIN_VALUE) {
			homeworkImpl.setModificationDate(null);
		}
		else {
			homeworkImpl.setModificationDate(new Date(modificationDate));
		}

		homeworkImpl.setSourceSessionId(sourceSessionId);
		homeworkImpl.setTargetSessionId(targetSessionId);

		if (targetDate == Long.MIN_VALUE) {
			homeworkImpl.setTargetDate(null);
		}
		else {
			homeworkImpl.setTargetDate(new Date(targetDate));
		}

		homeworkImpl.setIsCustomStudentList(isCustomStudentList);
		homeworkImpl.setEstimatedTime(estimatedTime);

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

		teacherId = objectInput.readLong();
		modificationDate = objectInput.readLong();

		sourceSessionId = objectInput.readLong();

		targetSessionId = objectInput.readLong();
		targetDate = objectInput.readLong();

		isCustomStudentList = objectInput.readBoolean();

		estimatedTime = objectInput.readInt();
		publicationDate = objectInput.readLong();

		isDraft = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(homeworkId);

		objectOutput.writeInt(homeworkType);

		objectOutput.writeLong(courseId);

		objectOutput.writeLong(teacherId);
		objectOutput.writeLong(modificationDate);

		objectOutput.writeLong(sourceSessionId);

		objectOutput.writeLong(targetSessionId);
		objectOutput.writeLong(targetDate);

		objectOutput.writeBoolean(isCustomStudentList);

		objectOutput.writeInt(estimatedTime);
		objectOutput.writeLong(publicationDate);

		objectOutput.writeBoolean(isDraft);
	}

	public long homeworkId;
	public int homeworkType;
	public long courseId;
	public long teacherId;
	public long modificationDate;
	public long sourceSessionId;
	public long targetSessionId;
	public long targetDate;
	public boolean isCustomStudentList;
	public int estimatedTime;
	public long publicationDate;
	public boolean isDraft;

}