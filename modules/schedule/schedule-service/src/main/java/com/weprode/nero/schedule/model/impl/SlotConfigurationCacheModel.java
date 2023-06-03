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

import com.weprode.nero.schedule.model.SlotConfiguration;
import com.weprode.nero.schedule.service.persistence.SlotConfigurationPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SlotConfiguration in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SlotConfigurationCacheModel
	implements CacheModel<SlotConfiguration>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SlotConfigurationCacheModel)) {
			return false;
		}

		SlotConfigurationCacheModel slotConfigurationCacheModel =
			(SlotConfigurationCacheModel)object;

		if (slotConfigurationPK.equals(
				slotConfigurationCacheModel.slotConfigurationPK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, slotConfigurationPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{schoolId=");
		sb.append(schoolId);
		sb.append(", slotNumber=");
		sb.append(slotNumber);
		sb.append(", sessionStartHour=");
		sb.append(sessionStartHour);
		sb.append(", sessionEndHour=");
		sb.append(sessionEndHour);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SlotConfiguration toEntityModel() {
		SlotConfigurationImpl slotConfigurationImpl =
			new SlotConfigurationImpl();

		slotConfigurationImpl.setSchoolId(schoolId);
		slotConfigurationImpl.setSlotNumber(slotNumber);

		if (sessionStartHour == null) {
			slotConfigurationImpl.setSessionStartHour("");
		}
		else {
			slotConfigurationImpl.setSessionStartHour(sessionStartHour);
		}

		if (sessionEndHour == null) {
			slotConfigurationImpl.setSessionEndHour("");
		}
		else {
			slotConfigurationImpl.setSessionEndHour(sessionEndHour);
		}

		slotConfigurationImpl.resetOriginalValues();

		return slotConfigurationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		schoolId = objectInput.readLong();

		slotNumber = objectInput.readInt();
		sessionStartHour = objectInput.readUTF();
		sessionEndHour = objectInput.readUTF();

		slotConfigurationPK = new SlotConfigurationPK(schoolId, slotNumber);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(schoolId);

		objectOutput.writeInt(slotNumber);

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
	public int slotNumber;
	public String sessionStartHour;
	public String sessionEndHour;
	public transient SlotConfigurationPK slotConfigurationPK;

}