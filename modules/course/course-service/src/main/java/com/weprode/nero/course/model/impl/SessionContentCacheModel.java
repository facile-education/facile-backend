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

package com.weprode.nero.course.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.course.model.SessionContent;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SessionContent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SessionContentCacheModel
	implements CacheModel<SessionContent>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SessionContentCacheModel)) {
			return false;
		}

		SessionContentCacheModel sessionContentCacheModel =
			(SessionContentCacheModel)object;

		if (sessionId == sessionContentCacheModel.sessionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, sessionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{sessionId=");
		sb.append(sessionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", teacherId=");
		sb.append(teacherId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", modificationDate=");
		sb.append(modificationDate);
		sb.append(", publicationDate=");
		sb.append(publicationDate);
		sb.append(", isDraft=");
		sb.append(isDraft);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SessionContent toEntityModel() {
		SessionContentImpl sessionContentImpl = new SessionContentImpl();

		sessionContentImpl.setSessionId(sessionId);
		sessionContentImpl.setCompanyId(companyId);
		sessionContentImpl.setTeacherId(teacherId);

		if (title == null) {
			sessionContentImpl.setTitle("");
		}
		else {
			sessionContentImpl.setTitle(title);
		}

		if (modificationDate == Long.MIN_VALUE) {
			sessionContentImpl.setModificationDate(null);
		}
		else {
			sessionContentImpl.setModificationDate(new Date(modificationDate));
		}

		if (publicationDate == Long.MIN_VALUE) {
			sessionContentImpl.setPublicationDate(null);
		}
		else {
			sessionContentImpl.setPublicationDate(new Date(publicationDate));
		}

		sessionContentImpl.setIsDraft(isDraft);

		sessionContentImpl.resetOriginalValues();

		return sessionContentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		sessionId = objectInput.readLong();

		companyId = objectInput.readLong();

		teacherId = objectInput.readLong();
		title = objectInput.readUTF();
		modificationDate = objectInput.readLong();
		publicationDate = objectInput.readLong();

		isDraft = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(sessionId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(teacherId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeLong(modificationDate);
		objectOutput.writeLong(publicationDate);

		objectOutput.writeBoolean(isDraft);
	}

	public long sessionId;
	public long companyId;
	public long teacherId;
	public String title;
	public long modificationDate;
	public long publicationDate;
	public boolean isDraft;

}