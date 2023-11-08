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

import com.weprode.facile.school.life.model.Renvoi;
import com.weprode.facile.school.life.service.persistence.RenvoiPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Renvoi in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RenvoiCacheModel implements CacheModel<Renvoi>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RenvoiCacheModel)) {
			return false;
		}

		RenvoiCacheModel renvoiCacheModel = (RenvoiCacheModel)object;

		if (renvoiPK.equals(renvoiCacheModel.renvoiPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, renvoiPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{schoollifeSessionId=");
		sb.append(schoollifeSessionId);
		sb.append(", studentId=");
		sb.append(studentId);
		sb.append(", orgId=");
		sb.append(orgId);
		sb.append(", schoolId=");
		sb.append(schoolId);
		sb.append(", renvoiDate=");
		sb.append(renvoiDate);
		sb.append(", teacherId=");
		sb.append(teacherId);
		sb.append(", sourceSessionId=");
		sb.append(sourceSessionId);
		sb.append(", sourceSchoollifeSessionId=");
		sb.append(sourceSchoollifeSessionId);
		sb.append(", sourceTeacherId=");
		sb.append(sourceTeacherId);
		sb.append(", reason=");
		sb.append(reason);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Renvoi toEntityModel() {
		RenvoiImpl renvoiImpl = new RenvoiImpl();

		renvoiImpl.setSchoollifeSessionId(schoollifeSessionId);
		renvoiImpl.setStudentId(studentId);
		renvoiImpl.setOrgId(orgId);
		renvoiImpl.setSchoolId(schoolId);

		if (renvoiDate == Long.MIN_VALUE) {
			renvoiImpl.setRenvoiDate(null);
		}
		else {
			renvoiImpl.setRenvoiDate(new Date(renvoiDate));
		}

		renvoiImpl.setTeacherId(teacherId);
		renvoiImpl.setSourceSessionId(sourceSessionId);
		renvoiImpl.setSourceSchoollifeSessionId(sourceSchoollifeSessionId);
		renvoiImpl.setSourceTeacherId(sourceTeacherId);

		if (reason == null) {
			renvoiImpl.setReason("");
		}
		else {
			renvoiImpl.setReason(reason);
		}

		renvoiImpl.setStatus(status);

		renvoiImpl.resetOriginalValues();

		return renvoiImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		schoollifeSessionId = objectInput.readLong();

		studentId = objectInput.readLong();

		orgId = objectInput.readLong();

		schoolId = objectInput.readLong();
		renvoiDate = objectInput.readLong();

		teacherId = objectInput.readLong();

		sourceSessionId = objectInput.readLong();

		sourceSchoollifeSessionId = objectInput.readLong();

		sourceTeacherId = objectInput.readLong();
		reason = objectInput.readUTF();

		status = objectInput.readInt();

		renvoiPK = new RenvoiPK(schoollifeSessionId, studentId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(schoollifeSessionId);

		objectOutput.writeLong(studentId);

		objectOutput.writeLong(orgId);

		objectOutput.writeLong(schoolId);
		objectOutput.writeLong(renvoiDate);

		objectOutput.writeLong(teacherId);

		objectOutput.writeLong(sourceSessionId);

		objectOutput.writeLong(sourceSchoollifeSessionId);

		objectOutput.writeLong(sourceTeacherId);

		if (reason == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reason);
		}

		objectOutput.writeInt(status);
	}

	public long schoollifeSessionId;
	public long studentId;
	public long orgId;
	public long schoolId;
	public long renvoiDate;
	public long teacherId;
	public long sourceSessionId;
	public long sourceSchoollifeSessionId;
	public long sourceTeacherId;
	public String reason;
	public int status;
	public transient RenvoiPK renvoiPK;

}