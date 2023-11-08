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

package com.weprode.facile.schedule.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.schedule.model.TeacherSubject;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing TeacherSubject in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TeacherSubjectCacheModel
	implements CacheModel<TeacherSubject>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TeacherSubjectCacheModel)) {
			return false;
		}

		TeacherSubjectCacheModel teacherSubjectCacheModel =
			(TeacherSubjectCacheModel)object;

		if (teacherSubjectId == teacherSubjectCacheModel.teacherSubjectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, teacherSubjectId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{teacherSubjectId=");
		sb.append(teacherSubjectId);
		sb.append(", teacherId=");
		sb.append(teacherId);
		sb.append(", subjectId=");
		sb.append(subjectId);
		sb.append(", schoolId=");
		sb.append(schoolId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TeacherSubject toEntityModel() {
		TeacherSubjectImpl teacherSubjectImpl = new TeacherSubjectImpl();

		teacherSubjectImpl.setTeacherSubjectId(teacherSubjectId);
		teacherSubjectImpl.setTeacherId(teacherId);
		teacherSubjectImpl.setSubjectId(subjectId);
		teacherSubjectImpl.setSchoolId(schoolId);

		teacherSubjectImpl.resetOriginalValues();

		return teacherSubjectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		teacherSubjectId = objectInput.readLong();

		teacherId = objectInput.readLong();

		subjectId = objectInput.readLong();

		schoolId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(teacherSubjectId);

		objectOutput.writeLong(teacherId);

		objectOutput.writeLong(subjectId);

		objectOutput.writeLong(schoolId);
	}

	public long teacherSubjectId;
	public long teacherId;
	public long subjectId;
	public long schoolId;

}