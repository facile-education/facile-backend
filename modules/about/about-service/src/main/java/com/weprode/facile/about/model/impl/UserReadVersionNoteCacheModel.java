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

package com.weprode.facile.about.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.about.model.UserReadVersionNote;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UserReadVersionNote in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserReadVersionNoteCacheModel
	implements CacheModel<UserReadVersionNote>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof UserReadVersionNoteCacheModel)) {
			return false;
		}

		UserReadVersionNoteCacheModel userReadVersionNoteCacheModel =
			(UserReadVersionNoteCacheModel)object;

		if (userId == userReadVersionNoteCacheModel.userId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{userId=");
		sb.append(userId);
		sb.append(", hasReadLastVersionNote=");
		sb.append(hasReadLastVersionNote);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserReadVersionNote toEntityModel() {
		UserReadVersionNoteImpl userReadVersionNoteImpl =
			new UserReadVersionNoteImpl();

		userReadVersionNoteImpl.setUserId(userId);
		userReadVersionNoteImpl.setHasReadLastVersionNote(
			hasReadLastVersionNote);

		userReadVersionNoteImpl.resetOriginalValues();

		return userReadVersionNoteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userId = objectInput.readLong();

		hasReadLastVersionNote = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(userId);

		objectOutput.writeBoolean(hasReadLastVersionNote);
	}

	public long userId;
	public boolean hasReadLastVersionNote;

}