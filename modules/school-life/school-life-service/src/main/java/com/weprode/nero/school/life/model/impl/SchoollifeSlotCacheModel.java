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

package com.weprode.nero.school.life.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.school.life.model.SchoollifeSlot;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SchoollifeSlot in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SchoollifeSlotCacheModel
	implements CacheModel<SchoollifeSlot>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SchoollifeSlotCacheModel)) {
			return false;
		}

		SchoollifeSlotCacheModel schoollifeSlotCacheModel =
			(SchoollifeSlotCacheModel)object;

		if (schoollifeSlotId == schoollifeSlotCacheModel.schoollifeSlotId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, schoollifeSlotId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{schoollifeSlotId=");
		sb.append(schoollifeSlotId);
		sb.append(", schoolId=");
		sb.append(schoolId);
		sb.append(", day=");
		sb.append(day);
		sb.append(", startHour=");
		sb.append(startHour);
		sb.append(", endHour=");
		sb.append(endHour);
		sb.append(", teacherId=");
		sb.append(teacherId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", room=");
		sb.append(room);
		sb.append(", capacity=");
		sb.append(capacity);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SchoollifeSlot toEntityModel() {
		SchoollifeSlotImpl schoollifeSlotImpl = new SchoollifeSlotImpl();

		schoollifeSlotImpl.setSchoollifeSlotId(schoollifeSlotId);
		schoollifeSlotImpl.setSchoolId(schoolId);
		schoollifeSlotImpl.setDay(day);

		if (startHour == null) {
			schoollifeSlotImpl.setStartHour("");
		}
		else {
			schoollifeSlotImpl.setStartHour(startHour);
		}

		if (endHour == null) {
			schoollifeSlotImpl.setEndHour("");
		}
		else {
			schoollifeSlotImpl.setEndHour(endHour);
		}

		schoollifeSlotImpl.setTeacherId(teacherId);
		schoollifeSlotImpl.setType(type);

		if (room == null) {
			schoollifeSlotImpl.setRoom("");
		}
		else {
			schoollifeSlotImpl.setRoom(room);
		}

		schoollifeSlotImpl.setCapacity(capacity);

		schoollifeSlotImpl.resetOriginalValues();

		return schoollifeSlotImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		schoollifeSlotId = objectInput.readLong();

		schoolId = objectInput.readLong();

		day = objectInput.readInt();
		startHour = objectInput.readUTF();
		endHour = objectInput.readUTF();

		teacherId = objectInput.readLong();

		type = objectInput.readInt();
		room = objectInput.readUTF();

		capacity = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(schoollifeSlotId);

		objectOutput.writeLong(schoolId);

		objectOutput.writeInt(day);

		if (startHour == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(startHour);
		}

		if (endHour == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(endHour);
		}

		objectOutput.writeLong(teacherId);

		objectOutput.writeInt(type);

		if (room == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(room);
		}

		objectOutput.writeInt(capacity);
	}

	public long schoollifeSlotId;
	public long schoolId;
	public int day;
	public String startHour;
	public String endHour;
	public long teacherId;
	public int type;
	public String room;
	public int capacity;

}