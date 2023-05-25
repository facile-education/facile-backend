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

import com.weprode.nero.progression.model.Progression;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Progression in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProgressionCacheModel
	implements CacheModel<Progression>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProgressionCacheModel)) {
			return false;
		}

		ProgressionCacheModel progressionCacheModel =
			(ProgressionCacheModel)object;

		if (progressionId == progressionCacheModel.progressionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, progressionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{progressionId=");
		sb.append(progressionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", teacherId=");
		sb.append(teacherId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", volee=");
		sb.append(volee);
		sb.append(", subjectId=");
		sb.append(subjectId);
		sb.append(", color=");
		sb.append(color);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Progression toEntityModel() {
		ProgressionImpl progressionImpl = new ProgressionImpl();

		progressionImpl.setProgressionId(progressionId);
		progressionImpl.setCompanyId(companyId);
		progressionImpl.setTeacherId(teacherId);

		if (createDate == Long.MIN_VALUE) {
			progressionImpl.setCreateDate(null);
		}
		else {
			progressionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			progressionImpl.setModifiedDate(null);
		}
		else {
			progressionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			progressionImpl.setName("");
		}
		else {
			progressionImpl.setName(name);
		}

		if (description == null) {
			progressionImpl.setDescription("");
		}
		else {
			progressionImpl.setDescription(description);
		}

		if (volee == null) {
			progressionImpl.setVolee("");
		}
		else {
			progressionImpl.setVolee(volee);
		}

		progressionImpl.setSubjectId(subjectId);

		if (color == null) {
			progressionImpl.setColor("");
		}
		else {
			progressionImpl.setColor(color);
		}

		progressionImpl.resetOriginalValues();

		return progressionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		progressionId = objectInput.readLong();

		companyId = objectInput.readLong();

		teacherId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		volee = objectInput.readUTF();

		subjectId = objectInput.readLong();
		color = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(progressionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(teacherId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (volee == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(volee);
		}

		objectOutput.writeLong(subjectId);

		if (color == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(color);
		}
	}

	public long progressionId;
	public long companyId;
	public long teacherId;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public String volee;
	public long subjectId;
	public String color;

}