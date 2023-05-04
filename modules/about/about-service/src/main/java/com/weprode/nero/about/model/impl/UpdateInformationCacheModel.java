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

package com.weprode.nero.about.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.about.model.UpdateInformation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UpdateInformation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UpdateInformationCacheModel
	implements CacheModel<UpdateInformation>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UpdateInformationCacheModel)) {
			return false;
		}

		UpdateInformationCacheModel updateInformationCacheModel =
			(UpdateInformationCacheModel)object;

		if (updateInfoId == updateInformationCacheModel.updateInfoId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, updateInfoId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{updateInfoId=");
		sb.append(updateInfoId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", modifyDate=");
		sb.append(modifyDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UpdateInformation toEntityModel() {
		UpdateInformationImpl updateInformationImpl =
			new UpdateInformationImpl();

		updateInformationImpl.setUpdateInfoId(updateInfoId);

		if (description == null) {
			updateInformationImpl.setDescription("");
		}
		else {
			updateInformationImpl.setDescription(description);
		}

		if (modifyDate == Long.MIN_VALUE) {
			updateInformationImpl.setModifyDate(null);
		}
		else {
			updateInformationImpl.setModifyDate(new Date(modifyDate));
		}

		updateInformationImpl.resetOriginalValues();

		return updateInformationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		updateInfoId = objectInput.readLong();
		description = objectInput.readUTF();
		modifyDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(updateInfoId);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(modifyDate);
	}

	public long updateInfoId;
	public String description;
	public long modifyDate;

}