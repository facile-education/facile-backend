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

package com.weprode.nero.schedule.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SlotConfigurationPK
	implements Comparable<SlotConfigurationPK>, Serializable {

	public long schoolId;
	public int slotNumber;

	public SlotConfigurationPK() {
	}

	public SlotConfigurationPK(long schoolId, int slotNumber) {
		this.schoolId = schoolId;
		this.slotNumber = slotNumber;
	}

	public long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(long schoolId) {
		this.schoolId = schoolId;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	@Override
	public int compareTo(SlotConfigurationPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (schoolId < pk.schoolId) {
			value = -1;
		}
		else if (schoolId > pk.schoolId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (slotNumber < pk.slotNumber) {
			value = -1;
		}
		else if (slotNumber > pk.slotNumber) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SlotConfigurationPK)) {
			return false;
		}

		SlotConfigurationPK pk = (SlotConfigurationPK)object;

		if ((schoolId == pk.schoolId) && (slotNumber == pk.slotNumber)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, schoolId);
		hashCode = HashUtil.hash(hashCode, slotNumber);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("schoolId=");

		sb.append(schoolId);
		sb.append(", slotNumber=");

		sb.append(slotNumber);

		sb.append("}");

		return sb.toString();
	}

}