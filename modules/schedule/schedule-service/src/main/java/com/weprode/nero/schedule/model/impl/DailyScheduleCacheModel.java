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

import com.weprode.nero.schedule.model.DailySchedule;
import com.weprode.nero.schedule.service.persistence.DailySchedulePK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DailySchedule in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DailyScheduleCacheModel
	implements CacheModel<DailySchedule>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DailyScheduleCacheModel)) {
			return false;
		}

		DailyScheduleCacheModel dailyScheduleCacheModel =
			(DailyScheduleCacheModel)object;

		if (dailySchedulePK.equals(dailyScheduleCacheModel.dailySchedulePK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dailySchedulePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{schoolId=");
		sb.append(schoolId);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", sessionStartHour=");
		sb.append(sessionStartHour);
		sb.append(", sessionEndHour=");
		sb.append(sessionEndHour);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DailySchedule toEntityModel() {
		DailyScheduleImpl dailyScheduleImpl = new DailyScheduleImpl();

		dailyScheduleImpl.setSchoolId(schoolId);
		dailyScheduleImpl.setSessionId(sessionId);

		if (sessionStartHour == null) {
			dailyScheduleImpl.setSessionStartHour("");
		}
		else {
			dailyScheduleImpl.setSessionStartHour(sessionStartHour);
		}

		if (sessionEndHour == null) {
			dailyScheduleImpl.setSessionEndHour("");
		}
		else {
			dailyScheduleImpl.setSessionEndHour(sessionEndHour);
		}

		dailyScheduleImpl.resetOriginalValues();

		return dailyScheduleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		schoolId = objectInput.readLong();

		sessionId = objectInput.readInt();
		sessionStartHour = objectInput.readUTF();
		sessionEndHour = objectInput.readUTF();

		dailySchedulePK = new DailySchedulePK(schoolId, sessionId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(schoolId);

		objectOutput.writeInt(sessionId);

		if (sessionStartHour == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sessionStartHour);
		}

		if (sessionEndHour == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(sessionEndHour);
		}
	}

	public long schoolId;
	public int sessionId;
	public String sessionStartHour;
	public String sessionEndHour;
	public transient DailySchedulePK dailySchedulePK;

}