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

package com.weprode.nero.school.life.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.school.life.model.Notification;
import com.weprode.nero.school.life.service.persistence.NotificationPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Notification in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NotificationCacheModel
	implements CacheModel<Notification>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NotificationCacheModel)) {
			return false;
		}

		NotificationCacheModel notificationCacheModel =
			(NotificationCacheModel)object;

		if (notificationPK.equals(notificationCacheModel.notificationPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, notificationPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{schoollifeSessionId=");
		sb.append(schoollifeSessionId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Notification toEntityModel() {
		NotificationImpl notificationImpl = new NotificationImpl();

		notificationImpl.setSchoollifeSessionId(schoollifeSessionId);
		notificationImpl.setUserId(userId);

		notificationImpl.resetOriginalValues();

		return notificationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		schoollifeSessionId = objectInput.readLong();

		userId = objectInput.readLong();

		notificationPK = new NotificationPK(schoollifeSessionId, userId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(schoollifeSessionId);

		objectOutput.writeLong(userId);
	}

	public long schoollifeSessionId;
	public long userId;
	public transient NotificationPK notificationPK;

}