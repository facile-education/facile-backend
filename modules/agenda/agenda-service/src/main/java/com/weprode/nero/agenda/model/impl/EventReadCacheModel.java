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

import com.weprode.nero.agenda.model.EventRead;
import com.weprode.nero.agenda.service.persistence.EventReadPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing EventRead in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EventReadCacheModel
	implements CacheModel<EventRead>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EventReadCacheModel)) {
			return false;
		}

		EventReadCacheModel eventReadCacheModel = (EventReadCacheModel)object;

		if (eventReadPK.equals(eventReadCacheModel.eventReadPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, eventReadPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{eventId=");
		sb.append(eventId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", readDate=");
		sb.append(readDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EventRead toEntityModel() {
		EventReadImpl eventReadImpl = new EventReadImpl();

		eventReadImpl.setEventId(eventId);
		eventReadImpl.setUserId(userId);

		if (readDate == Long.MIN_VALUE) {
			eventReadImpl.setReadDate(null);
		}
		else {
			eventReadImpl.setReadDate(new Date(readDate));
		}

		eventReadImpl.resetOriginalValues();

		return eventReadImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		eventId = objectInput.readLong();

		userId = objectInput.readLong();
		readDate = objectInput.readLong();

		eventReadPK = new EventReadPK(eventId, userId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(eventId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(readDate);
	}

	public long eventId;
	public long userId;
	public long readDate;
	public transient EventReadPK eventReadPK;

}