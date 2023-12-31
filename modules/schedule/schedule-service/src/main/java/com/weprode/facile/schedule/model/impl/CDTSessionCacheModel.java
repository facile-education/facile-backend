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

import com.weprode.facile.schedule.model.CDTSession;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CDTSession in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CDTSessionCacheModel
	implements CacheModel<CDTSession>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CDTSessionCacheModel)) {
			return false;
		}

		CDTSessionCacheModel cdtSessionCacheModel =
			(CDTSessionCacheModel)object;

		if (sessionId == cdtSessionCacheModel.sessionId) {
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
		StringBundler sb = new StringBundler(19);

		sb.append("{sessionId=");
		sb.append(sessionId);
		sb.append(", start=");
		sb.append(start);
		sb.append(", end=");
		sb.append(end);
		sb.append(", slot=");
		sb.append(slot);
		sb.append(", fullCoursName=");
		sb.append(fullCoursName);
		sb.append(", room=");
		sb.append(room);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", isManual=");
		sb.append(isManual);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CDTSession toEntityModel() {
		CDTSessionImpl cdtSessionImpl = new CDTSessionImpl();

		cdtSessionImpl.setSessionId(sessionId);

		if (start == Long.MIN_VALUE) {
			cdtSessionImpl.setStart(null);
		}
		else {
			cdtSessionImpl.setStart(new Date(start));
		}

		if (end == Long.MIN_VALUE) {
			cdtSessionImpl.setEnd(null);
		}
		else {
			cdtSessionImpl.setEnd(new Date(end));
		}

		cdtSessionImpl.setSlot(slot);

		if (fullCoursName == null) {
			cdtSessionImpl.setFullCoursName("");
		}
		else {
			cdtSessionImpl.setFullCoursName(fullCoursName);
		}

		if (room == null) {
			cdtSessionImpl.setRoom("");
		}
		else {
			cdtSessionImpl.setRoom(room);
		}

		if (subject == null) {
			cdtSessionImpl.setSubject("");
		}
		else {
			cdtSessionImpl.setSubject(subject);
		}

		cdtSessionImpl.setGroupId(groupId);
		cdtSessionImpl.setIsManual(isManual);

		cdtSessionImpl.resetOriginalValues();

		return cdtSessionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		sessionId = objectInput.readLong();
		start = objectInput.readLong();
		end = objectInput.readLong();

		slot = objectInput.readInt();
		fullCoursName = objectInput.readUTF();
		room = objectInput.readUTF();
		subject = objectInput.readUTF();

		groupId = objectInput.readLong();

		isManual = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(sessionId);
		objectOutput.writeLong(start);
		objectOutput.writeLong(end);

		objectOutput.writeInt(slot);

		if (fullCoursName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fullCoursName);
		}

		if (room == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(room);
		}

		if (subject == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subject);
		}

		objectOutput.writeLong(groupId);

		objectOutput.writeBoolean(isManual);
	}

	public long sessionId;
	public long start;
	public long end;
	public int slot;
	public String fullCoursName;
	public String room;
	public String subject;
	public long groupId;
	public boolean isManual;

}