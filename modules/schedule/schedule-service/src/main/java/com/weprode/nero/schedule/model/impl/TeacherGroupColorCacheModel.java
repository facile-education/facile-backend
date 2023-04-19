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

import com.weprode.nero.schedule.model.TeacherGroupColor;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing TeacherGroupColor in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TeacherGroupColorCacheModel
	implements CacheModel<TeacherGroupColor>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TeacherGroupColorCacheModel)) {
			return false;
		}

		TeacherGroupColorCacheModel teacherGroupColorCacheModel =
			(TeacherGroupColorCacheModel)object;

		if (teacherGroupColorId ==
				teacherGroupColorCacheModel.teacherGroupColorId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, teacherGroupColorId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{teacherGroupColorId=");
		sb.append(teacherGroupColorId);
		sb.append(", teacherId=");
		sb.append(teacherId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", color=");
		sb.append(color);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TeacherGroupColor toEntityModel() {
		TeacherGroupColorImpl teacherGroupColorImpl =
			new TeacherGroupColorImpl();

		teacherGroupColorImpl.setTeacherGroupColorId(teacherGroupColorId);
		teacherGroupColorImpl.setTeacherId(teacherId);
		teacherGroupColorImpl.setGroupId(groupId);

		if (color == null) {
			teacherGroupColorImpl.setColor("");
		}
		else {
			teacherGroupColorImpl.setColor(color);
		}

		teacherGroupColorImpl.resetOriginalValues();

		return teacherGroupColorImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		teacherGroupColorId = objectInput.readLong();

		teacherId = objectInput.readLong();

		groupId = objectInput.readLong();
		color = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(teacherGroupColorId);

		objectOutput.writeLong(teacherId);

		objectOutput.writeLong(groupId);

		if (color == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(color);
		}
	}

	public long teacherGroupColorId;
	public long teacherId;
	public long groupId;
	public String color;

}