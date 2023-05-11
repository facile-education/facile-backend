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

package com.weprode.nero.agenda.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.agenda.model.Event;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Event in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EventCacheModel implements CacheModel<Event>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EventCacheModel)) {
			return false;
		}

		EventCacheModel eventCacheModel = (EventCacheModel)object;

		if (eventId == eventCacheModel.eventId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, eventId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{eventId=");
		sb.append(eventId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", location=");
		sb.append(location);
		sb.append(", authorId=");
		sb.append(authorId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Event toEntityModel() {
		EventImpl eventImpl = new EventImpl();

		eventImpl.setEventId(eventId);

		if (startDate == Long.MIN_VALUE) {
			eventImpl.setStartDate(null);
		}
		else {
			eventImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			eventImpl.setEndDate(null);
		}
		else {
			eventImpl.setEndDate(new Date(endDate));
		}

		if (title == null) {
			eventImpl.setTitle("");
		}
		else {
			eventImpl.setTitle(title);
		}

		if (description == null) {
			eventImpl.setDescription("");
		}
		else {
			eventImpl.setDescription(description);
		}

		if (location == null) {
			eventImpl.setLocation("");
		}
		else {
			eventImpl.setLocation(location);
		}

		eventImpl.setAuthorId(authorId);

		eventImpl.resetOriginalValues();

		return eventImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		eventId = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();
		location = objectInput.readUTF();

		authorId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(eventId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (location == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(location);
		}

		objectOutput.writeLong(authorId);
	}

	public long eventId;
	public long startDate;
	public long endDate;
	public String title;
	public String description;
	public String location;
	public long authorId;

}