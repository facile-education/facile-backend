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

import com.weprode.facile.schedule.model.CourseDetails;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CourseDetails in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CourseDetailsCacheModel
	implements CacheModel<CourseDetails>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CourseDetailsCacheModel)) {
			return false;
		}

		CourseDetailsCacheModel courseDetailsCacheModel =
			(CourseDetailsCacheModel)object;

		if (courseGroupId == courseDetailsCacheModel.courseGroupId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, courseGroupId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{courseGroupId=");
		sb.append(courseGroupId);
		sb.append(", color=");
		sb.append(color);
		sb.append(", subjectId=");
		sb.append(subjectId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CourseDetails toEntityModel() {
		CourseDetailsImpl courseDetailsImpl = new CourseDetailsImpl();

		courseDetailsImpl.setCourseGroupId(courseGroupId);

		if (color == null) {
			courseDetailsImpl.setColor("");
		}
		else {
			courseDetailsImpl.setColor(color);
		}

		courseDetailsImpl.setSubjectId(subjectId);

		courseDetailsImpl.resetOriginalValues();

		return courseDetailsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		courseGroupId = objectInput.readLong();
		color = objectInput.readUTF();

		subjectId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(courseGroupId);

		if (color == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(color);
		}

		objectOutput.writeLong(subjectId);
	}

	public long courseGroupId;
	public String color;
	public long subjectId;

}