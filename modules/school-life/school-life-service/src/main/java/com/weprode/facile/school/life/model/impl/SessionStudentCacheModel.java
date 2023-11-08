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

import com.weprode.facile.school.life.model.SessionStudent;
import com.weprode.facile.school.life.service.persistence.SessionStudentPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SessionStudent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SessionStudentCacheModel
	implements CacheModel<SessionStudent>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SessionStudentCacheModel)) {
			return false;
		}

		SessionStudentCacheModel sessionStudentCacheModel =
			(SessionStudentCacheModel)object;

		if (sessionStudentPK.equals(
				sessionStudentCacheModel.sessionStudentPK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, sessionStudentPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{schoollifeSessionId=");
		sb.append(schoollifeSessionId);
		sb.append(", studentId=");
		sb.append(studentId);
		sb.append(", sourceTeacherId=");
		sb.append(sourceTeacherId);
		sb.append(", isPresent=");
		sb.append(isPresent);
		sb.append(", notifyParents=");
		sb.append(notifyParents);
		sb.append(", comment=");
		sb.append(comment);
		sb.append(", subject=");
		sb.append(subject);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SessionStudent toEntityModel() {
		SessionStudentImpl sessionStudentImpl = new SessionStudentImpl();

		sessionStudentImpl.setSchoollifeSessionId(schoollifeSessionId);
		sessionStudentImpl.setStudentId(studentId);
		sessionStudentImpl.setSourceTeacherId(sourceTeacherId);
		sessionStudentImpl.setIsPresent(isPresent);
		sessionStudentImpl.setNotifyParents(notifyParents);

		if (comment == null) {
			sessionStudentImpl.setComment("");
		}
		else {
			sessionStudentImpl.setComment(comment);
		}

		if (subject == null) {
			sessionStudentImpl.setSubject("");
		}
		else {
			sessionStudentImpl.setSubject(subject);
		}

		sessionStudentImpl.resetOriginalValues();

		return sessionStudentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		schoollifeSessionId = objectInput.readLong();

		studentId = objectInput.readLong();

		sourceTeacherId = objectInput.readLong();

		isPresent = objectInput.readBoolean();

		notifyParents = objectInput.readBoolean();
		comment = objectInput.readUTF();
		subject = objectInput.readUTF();

		sessionStudentPK = new SessionStudentPK(schoollifeSessionId, studentId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(schoollifeSessionId);

		objectOutput.writeLong(studentId);

		objectOutput.writeLong(sourceTeacherId);

		objectOutput.writeBoolean(isPresent);

		objectOutput.writeBoolean(notifyParents);

		if (comment == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(comment);
		}

		if (subject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subject);
		}
	}

	public long schoollifeSessionId;
	public long studentId;
	public long sourceTeacherId;
	public boolean isPresent;
	public boolean notifyParents;
	public String comment;
	public String subject;
	public transient SessionStudentPK sessionStudentPK;

}