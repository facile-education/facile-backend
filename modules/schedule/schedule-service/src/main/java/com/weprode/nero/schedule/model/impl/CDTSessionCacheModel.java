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

import com.weprode.nero.schedule.model.CDTSession;

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
		StringBundler sb = new StringBundler(27);

		sb.append("{sessionId=");
		sb.append(sessionId);
		sb.append(", sessionStart=");
		sb.append(sessionStart);
		sb.append(", sessionEnd=");
		sb.append(sessionEnd);
		sb.append(", weekId=");
		sb.append(weekId);
		sb.append(", published=");
		sb.append(published);
		sb.append(", title=");
		sb.append(title);
		sb.append(", fullCoursName=");
		sb.append(fullCoursName);
		sb.append(", description=");
		sb.append(description);
		sb.append(", room=");
		sb.append(room);
		sb.append(", subject=");
		sb.append(subject);
		sb.append(", schoolId=");
		sb.append(schoolId);
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

		if (sessionStart == Long.MIN_VALUE) {
			cdtSessionImpl.setSessionStart(null);
		}
		else {
			cdtSessionImpl.setSessionStart(new Date(sessionStart));
		}

		if (sessionEnd == Long.MIN_VALUE) {
			cdtSessionImpl.setSessionEnd(null);
		}
		else {
			cdtSessionImpl.setSessionEnd(new Date(sessionEnd));
		}

		cdtSessionImpl.setWeekId(weekId);
		cdtSessionImpl.setPublished(published);

		if (title == null) {
			cdtSessionImpl.setTitle("");
		}
		else {
			cdtSessionImpl.setTitle(title);
		}

		if (fullCoursName == null) {
			cdtSessionImpl.setFullCoursName("");
		}
		else {
			cdtSessionImpl.setFullCoursName(fullCoursName);
		}

		if (description == null) {
			cdtSessionImpl.setDescription("");
		}
		else {
			cdtSessionImpl.setDescription(description);
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

		cdtSessionImpl.setSchoolId(schoolId);
		cdtSessionImpl.setGroupId(groupId);
		cdtSessionImpl.setIsManual(isManual);

		cdtSessionImpl.resetOriginalValues();

		return cdtSessionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		sessionId = objectInput.readLong();
		sessionStart = objectInput.readLong();
		sessionEnd = objectInput.readLong();

		weekId = objectInput.readLong();

		published = objectInput.readBoolean();
		title = objectInput.readUTF();
		fullCoursName = objectInput.readUTF();
		description = objectInput.readUTF();
		room = objectInput.readUTF();
		subject = objectInput.readUTF();

		schoolId = objectInput.readLong();

		groupId = objectInput.readLong();

		isManual = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(sessionId);
		objectOutput.writeLong(sessionStart);
		objectOutput.writeLong(sessionEnd);

		objectOutput.writeLong(weekId);

		objectOutput.writeBoolean(published);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (fullCoursName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(fullCoursName);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
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

		objectOutput.writeLong(schoolId);

		objectOutput.writeLong(groupId);

		objectOutput.writeBoolean(isManual);
	}

	public long sessionId;
	public long sessionStart;
	public long sessionEnd;
	public long weekId;
	public boolean published;
	public String title;
	public String fullCoursName;
	public String description;
	public String room;
	public String subject;
	public long schoolId;
	public long groupId;
	public boolean isManual;

}