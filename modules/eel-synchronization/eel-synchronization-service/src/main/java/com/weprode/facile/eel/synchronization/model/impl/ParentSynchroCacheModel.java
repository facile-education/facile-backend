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

package com.weprode.facile.eel.synchronization.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.eel.synchronization.model.ParentSynchro;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ParentSynchro in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ParentSynchroCacheModel
	implements CacheModel<ParentSynchro>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ParentSynchroCacheModel)) {
			return false;
		}

		ParentSynchroCacheModel parentSynchroCacheModel =
			(ParentSynchroCacheModel)object;

		if (schoolId == parentSynchroCacheModel.schoolId) {
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
		StringBundler sb = new StringBundler(13);

		sb.append("{schoolId=");
		sb.append(schoolId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", lineCount=");
		sb.append(lineCount);
		sb.append(", errorCount=");
		sb.append(errorCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ParentSynchro toEntityModel() {
		ParentSynchroImpl parentSynchroImpl = new ParentSynchroImpl();

		parentSynchroImpl.setSchoolId(schoolId);

		if (startDate == Long.MIN_VALUE) {
			parentSynchroImpl.setStartDate(null);
		}
		else {
			parentSynchroImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			parentSynchroImpl.setEndDate(null);
		}
		else {
			parentSynchroImpl.setEndDate(new Date(endDate));
		}

		if (fileName == null) {
			parentSynchroImpl.setFileName("");
		}
		else {
			parentSynchroImpl.setFileName(fileName);
		}

		parentSynchroImpl.setLineCount(lineCount);
		parentSynchroImpl.setErrorCount(errorCount);

		parentSynchroImpl.resetOriginalValues();

		return parentSynchroImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		schoolId = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		fileName = objectInput.readUTF();

		lineCount = objectInput.readLong();

		errorCount = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(schoolId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		if (fileName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fileName);
		}

		objectOutput.writeLong(lineCount);

		objectOutput.writeLong(errorCount);
	}

	public long schoolId;
	public long startDate;
	public long endDate;
	public String fileName;
	public long lineCount;
	public long errorCount;

}