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

package com.weprode.nero.school.life.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RenvoiPK implements Comparable<RenvoiPK>, Serializable {

	public long schoollifeSessionId;
	public long studentId;

	public RenvoiPK() {
	}

	public RenvoiPK(long schoollifeSessionId, long studentId) {
		this.schoollifeSessionId = schoollifeSessionId;
		this.studentId = studentId;
	}

	public long getSchoollifeSessionId() {
		return schoollifeSessionId;
	}

	public void setSchoollifeSessionId(long schoollifeSessionId) {
		this.schoollifeSessionId = schoollifeSessionId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	@Override
	public int compareTo(RenvoiPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (schoollifeSessionId < pk.schoollifeSessionId) {
			value = -1;
		}
		else if (schoollifeSessionId > pk.schoollifeSessionId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (studentId < pk.studentId) {
			value = -1;
		}
		else if (studentId > pk.studentId) {
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

		if (!(object instanceof RenvoiPK)) {
			return false;
		}

		RenvoiPK pk = (RenvoiPK)object;

		if ((schoollifeSessionId == pk.schoollifeSessionId) &&
			(studentId == pk.studentId)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, schoollifeSessionId);
		hashCode = HashUtil.hash(hashCode, studentId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("schoollifeSessionId=");

		sb.append(schoollifeSessionId);
		sb.append(", studentId=");

		sb.append(studentId);

		sb.append("}");

		return sb.toString();
	}

}