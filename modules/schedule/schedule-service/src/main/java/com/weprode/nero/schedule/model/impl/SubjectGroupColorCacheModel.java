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

import com.weprode.nero.schedule.model.SubjectGroupColor;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SubjectGroupColor in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SubjectGroupColorCacheModel
	implements CacheModel<SubjectGroupColor>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SubjectGroupColorCacheModel)) {
			return false;
		}

		SubjectGroupColorCacheModel subjectGroupColorCacheModel =
			(SubjectGroupColorCacheModel)object;

		if (subjectGroupColorId ==
				subjectGroupColorCacheModel.subjectGroupColorId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, subjectGroupColorId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{subjectGroupColorId=");
		sb.append(subjectGroupColorId);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", color=");
		sb.append(color);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SubjectGroupColor toEntityModel() {
		SubjectGroupColorImpl subjectGroupColorImpl =
			new SubjectGroupColorImpl();

		subjectGroupColorImpl.setSubjectGroupColorId(subjectGroupColorId);

		if (subject == null) {
			subjectGroupColorImpl.setSubject("");
		}
		else {
			subjectGroupColorImpl.setSubject(subject);
		}

		subjectGroupColorImpl.setGroupId(groupId);

		if (color == null) {
			subjectGroupColorImpl.setColor("");
		}
		else {
			subjectGroupColorImpl.setColor(color);
		}

		subjectGroupColorImpl.resetOriginalValues();

		return subjectGroupColorImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		subjectGroupColorId = objectInput.readLong();
		subject = objectInput.readUTF();

		groupId = objectInput.readLong();
		color = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(subjectGroupColorId);

		if (subject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subject);
		}

		objectOutput.writeLong(groupId);

		if (color == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(color);
		}
	}

	public long subjectGroupColorId;
	public String subject;
	public long groupId;
	public String color;

}