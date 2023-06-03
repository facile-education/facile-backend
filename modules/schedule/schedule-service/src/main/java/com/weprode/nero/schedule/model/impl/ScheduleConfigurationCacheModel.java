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

		if (configId == scheduleConfigurationCacheModel.configId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, configId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{configId=");
		sb.append(configId);
		sb.append(", projectStartDate=");
		sb.append(projectStartDate);
		sb.append(", schoolYearStartDate=");
		sb.append(schoolYearStartDate);
		sb.append(", schoolYearSemesterDate=");
		sb.append(schoolYearSemesterDate);
		sb.append(", schoolYearEndDate=");
		sb.append(schoolYearEndDate);
		sb.append(", h1Weeks=");
		sb.append(h1Weeks);
		sb.append(", h2Weeks=");
		sb.append(h2Weeks);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ScheduleConfiguration toEntityModel() {
		ScheduleConfigurationImpl scheduleConfigurationImpl =
			new ScheduleConfigurationImpl();

		scheduleConfigurationImpl.setConfigId(configId);

		if (projectStartDate == Long.MIN_VALUE) {
			scheduleConfigurationImpl.setProjectStartDate(null);
		}
		else {
			scheduleConfigurationImpl.setProjectStartDate(
				new Date(projectStartDate));
		}

		if (schoolYearStartDate == Long.MIN_VALUE) {
			scheduleConfigurationImpl.setSchoolYearStartDate(null);
		}
		else {
			scheduleConfigurationImpl.setSchoolYearStartDate(
				new Date(schoolYearStartDate));
		}

		if (schoolYearSemesterDate == Long.MIN_VALUE) {
			scheduleConfigurationImpl.setSchoolYearSemesterDate(null);
		}
		else {
			scheduleConfigurationImpl.setSchoolYearSemesterDate(
				new Date(schoolYearSemesterDate));
		}

		if (schoolYearEndDate == Long.MIN_VALUE) {
			scheduleConfigurationImpl.setSchoolYearEndDate(null);
		}
		else {
			scheduleConfigurationImpl.setSchoolYearEndDate(
				new Date(schoolYearEndDate));
		}

		if (h1Weeks == null) {
			scheduleConfigurationImpl.setH1Weeks("");
		}
		else {
			scheduleConfigurationImpl.setH1Weeks(h1Weeks);
		}

		if (h2Weeks == null) {
			scheduleConfigurationImpl.setH2Weeks("");
		}
		else {
			scheduleConfigurationImpl.setH2Weeks(h2Weeks);
		}

		scheduleConfigurationImpl.resetOriginalValues();

		return scheduleConfigurationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		configId = objectInput.readLong();
		projectStartDate = objectInput.readLong();
		schoolYearStartDate = objectInput.readLong();
		schoolYearSemesterDate = objectInput.readLong();
		schoolYearEndDate = objectInput.readLong();
		h1Weeks = objectInput.readUTF();
		h2Weeks = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(configId);
		objectOutput.writeLong(projectStartDate);
		objectOutput.writeLong(schoolYearStartDate);
		objectOutput.writeLong(schoolYearSemesterDate);
		objectOutput.writeLong(schoolYearEndDate);

		if (h1Weeks == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(h1Weeks);
		}

		if (h2Weeks == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(h2Weeks);
		}
	}

	public long configId;
	public long projectStartDate;
	public long schoolYearStartDate;
	public long schoolYearSemesterDate;
	public long schoolYearEndDate;
	public String h1Weeks;
	public String h2Weeks;

}