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

import com.weprode.nero.schedule.model.ScheduleConfiguration;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ScheduleConfiguration in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ScheduleConfigurationCacheModel
	implements CacheModel<ScheduleConfiguration>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ScheduleConfigurationCacheModel)) {
			return false;
		}

		ScheduleConfigurationCacheModel scheduleConfigurationCacheModel =
			(ScheduleConfigurationCacheModel)object;

		if (schoolId == scheduleConfigurationCacheModel.schoolId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, schoolId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{schoolId=");
		sb.append(schoolId);
		sb.append(", startDayTime=");
		sb.append(startDayTime);
		sb.append(", endDayTime=");
		sb.append(endDayTime);
		sb.append(", startSessionsDate=");
		sb.append(startSessionsDate);
		sb.append(", endSessionsDate=");
		sb.append(endSessionsDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ScheduleConfiguration toEntityModel() {
		ScheduleConfigurationImpl scheduleConfigurationImpl =
			new ScheduleConfigurationImpl();

		scheduleConfigurationImpl.setSchoolId(schoolId);

		if (startDayTime == null) {
			scheduleConfigurationImpl.setStartDayTime("");
		}
		else {
			scheduleConfigurationImpl.setStartDayTime(startDayTime);
		}

		if (endDayTime == null) {
			scheduleConfigurationImpl.setEndDayTime("");
		}
		else {
			scheduleConfigurationImpl.setEndDayTime(endDayTime);
		}

		if (startSessionsDate == Long.MIN_VALUE) {
			scheduleConfigurationImpl.setStartSessionsDate(null);
		}
		else {
			scheduleConfigurationImpl.setStartSessionsDate(
				new Date(startSessionsDate));
		}

		if (endSessionsDate == Long.MIN_VALUE) {
			scheduleConfigurationImpl.setEndSessionsDate(null);
		}
		else {
			scheduleConfigurationImpl.setEndSessionsDate(
				new Date(endSessionsDate));
		}

		scheduleConfigurationImpl.resetOriginalValues();

		return scheduleConfigurationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		schoolId = objectInput.readLong();
		startDayTime = objectInput.readUTF();
		endDayTime = objectInput.readUTF();
		startSessionsDate = objectInput.readLong();
		endSessionsDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(schoolId);

		if (startDayTime == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(startDayTime);
		}

		if (endDayTime == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(endDayTime);
		}

		objectOutput.writeLong(startSessionsDate);
		objectOutput.writeLong(endSessionsDate);
	}

	public long schoolId;
	public String startDayTime;
	public String endDayTime;
	public long startSessionsDate;
	public long endSessionsDate;

}