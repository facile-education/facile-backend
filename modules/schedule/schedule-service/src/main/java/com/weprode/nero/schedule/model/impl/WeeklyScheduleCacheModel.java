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

import com.weprode.nero.schedule.model.WeeklySchedule;
import com.weprode.nero.schedule.service.persistence.WeeklySchedulePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing WeeklySchedule in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class WeeklyScheduleCacheModel
	implements CacheModel<WeeklySchedule>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof WeeklyScheduleCacheModel)) {
			return false;
		}

		WeeklyScheduleCacheModel weeklyScheduleCacheModel =
			(WeeklyScheduleCacheModel)object;

		if (weeklySchedulePK.equals(
				weeklyScheduleCacheModel.weeklySchedulePK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, weeklySchedulePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{schoolId=");
		sb.append(schoolId);
		sb.append(", dayId=");
		sb.append(dayId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WeeklySchedule toEntityModel() {
		WeeklyScheduleImpl weeklyScheduleImpl = new WeeklyScheduleImpl();

		weeklyScheduleImpl.setSchoolId(schoolId);
		weeklyScheduleImpl.setDayId(dayId);

		weeklyScheduleImpl.resetOriginalValues();

		return weeklyScheduleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		schoolId = objectInput.readLong();

		dayId = objectInput.readInt();

		weeklySchedulePK = new WeeklySchedulePK(schoolId, dayId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(schoolId);

		objectOutput.writeInt(dayId);
	}

	public long schoolId;
	public int dayId;
	public transient WeeklySchedulePK weeklySchedulePK;

}