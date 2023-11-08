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

package com.weprode.facile.schedule.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.schedule.model.SessionTeacher;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SessionTeacher in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SessionTeacherCacheModel
	implements CacheModel<SessionTeacher>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SessionTeacherCacheModel)) {
			return false;
		}

		SessionTeacherCacheModel sessionTeacherCacheModel =
			(SessionTeacherCacheModel)object;

		if (sessionTeacherId == sessionTeacherCacheModel.sessionTeacherId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, sessionTeacherId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{sessionTeacherId=");
		sb.append(sessionTeacherId);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", teacherId=");
		sb.append(teacherId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", substituteId=");
		sb.append(substituteId);
		sb.append(", modificationDate=");
		sb.append(modificationDate);
		sb.append(", privateNotes=");
		sb.append(privateNotes);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SessionTeacher toEntityModel() {
		SessionTeacherImpl sessionTeacherImpl = new SessionTeacherImpl();

		sessionTeacherImpl.setSessionTeacherId(sessionTeacherId);
		sessionTeacherImpl.setSessionId(sessionId);
		sessionTeacherImpl.setTeacherId(teacherId);
		sessionTeacherImpl.setStatus(status);
		sessionTeacherImpl.setSubstituteId(substituteId);

		if (modificationDate == Long.MIN_VALUE) {
			sessionTeacherImpl.setModificationDate(null);
		}
		else {
			sessionTeacherImpl.setModificationDate(new Date(modificationDate));
		}

		if (privateNotes == null) {
			sessionTeacherImpl.setPrivateNotes("");
		}
		else {
			sessionTeacherImpl.setPrivateNotes(privateNotes);
		}

		sessionTeacherImpl.resetOriginalValues();

		return sessionTeacherImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		sessionTeacherId = objectInput.readLong();

		sessionId = objectInput.readLong();

		teacherId = objectInput.readLong();

		status = objectInput.readInt();

		substituteId = objectInput.readLong();
		modificationDate = objectInput.readLong();
		privateNotes = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(sessionTeacherId);

		objectOutput.writeLong(sessionId);

		objectOutput.writeLong(teacherId);

		objectOutput.writeInt(status);

		objectOutput.writeLong(substituteId);
		objectOutput.writeLong(modificationDate);

		if (privateNotes == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(privateNotes);
		}
	}

	public long sessionTeacherId;
	public long sessionId;
	public long teacherId;
	public int status;
	public long substituteId;
	public long modificationDate;
	public String privateNotes;

}