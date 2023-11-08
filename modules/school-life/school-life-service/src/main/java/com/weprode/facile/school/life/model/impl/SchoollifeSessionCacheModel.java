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

package com.weprode.facile.school.life.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.school.life.model.SchoollifeSession;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SchoollifeSession in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SchoollifeSessionCacheModel
	implements CacheModel<SchoollifeSession>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SchoollifeSessionCacheModel)) {
			return false;
		}

		SchoollifeSessionCacheModel schoollifeSessionCacheModel =
			(SchoollifeSessionCacheModel)object;

		if (schoollifeSessionId ==
				schoollifeSessionCacheModel.schoollifeSessionId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, schoollifeSessionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{schoollifeSessionId=");
		sb.append(schoollifeSessionId);
		sb.append(", schoollifeSlotId=");
		sb.append(schoollifeSlotId);
		sb.append(", schoolId=");
		sb.append(schoolId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", weekNb=");
		sb.append(weekNb);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", rollCalled=");
		sb.append(rollCalled);
		sb.append(", absenceNotificationSent=");
		sb.append(absenceNotificationSent);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SchoollifeSession toEntityModel() {
		SchoollifeSessionImpl schoollifeSessionImpl =
			new SchoollifeSessionImpl();

		schoollifeSessionImpl.setSchoollifeSessionId(schoollifeSessionId);
		schoollifeSessionImpl.setSchoollifeSlotId(schoollifeSlotId);
		schoollifeSessionImpl.setSchoolId(schoolId);
		schoollifeSessionImpl.setType(type);
		schoollifeSessionImpl.setWeekNb(weekNb);

		if (startDate == Long.MIN_VALUE) {
			schoollifeSessionImpl.setStartDate(null);
		}
		else {
			schoollifeSessionImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			schoollifeSessionImpl.setEndDate(null);
		}
		else {
			schoollifeSessionImpl.setEndDate(new Date(endDate));
		}

		schoollifeSessionImpl.setRollCalled(rollCalled);
		schoollifeSessionImpl.setAbsenceNotificationSent(
			absenceNotificationSent);

		schoollifeSessionImpl.resetOriginalValues();

		return schoollifeSessionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		schoollifeSessionId = objectInput.readLong();

		schoollifeSlotId = objectInput.readLong();

		schoolId = objectInput.readLong();

		type = objectInput.readInt();

		weekNb = objectInput.readInt();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();

		rollCalled = objectInput.readBoolean();

		absenceNotificationSent = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(schoollifeSessionId);

		objectOutput.writeLong(schoollifeSlotId);

		objectOutput.writeLong(schoolId);

		objectOutput.writeInt(type);

		objectOutput.writeInt(weekNb);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		objectOutput.writeBoolean(rollCalled);

		objectOutput.writeBoolean(absenceNotificationSent);
	}

	public long schoollifeSessionId;
	public long schoollifeSlotId;
	public long schoolId;
	public int type;
	public int weekNb;
	public long startDate;
	public long endDate;
	public boolean rollCalled;
	public boolean absenceNotificationSent;

}