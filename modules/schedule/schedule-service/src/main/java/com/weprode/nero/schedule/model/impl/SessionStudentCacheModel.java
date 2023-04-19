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

import com.weprode.nero.schedule.model.SessionStudent;

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

		if (sessionStudentId == sessionStudentCacheModel.sessionStudentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, sessionStudentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{sessionStudentId=");
		sb.append(sessionStudentId);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", studentId=");
		sb.append(studentId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SessionStudent toEntityModel() {
		SessionStudentImpl sessionStudentImpl = new SessionStudentImpl();

		sessionStudentImpl.setSessionStudentId(sessionStudentId);
		sessionStudentImpl.setSessionId(sessionId);
		sessionStudentImpl.setStudentId(studentId);

		sessionStudentImpl.resetOriginalValues();

		return sessionStudentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		sessionStudentId = objectInput.readLong();

		sessionId = objectInput.readLong();

		studentId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(sessionStudentId);

		objectOutput.writeLong(sessionId);

		objectOutput.writeLong(studentId);
	}

	public long sessionStudentId;
	public long sessionId;
	public long studentId;

}