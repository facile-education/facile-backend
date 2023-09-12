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

package com.weprode.nero.about.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.about.model.VersionNote;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VersionNote in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class VersionNoteCacheModel
	implements CacheModel<VersionNote>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof VersionNoteCacheModel)) {
			return false;
		}

		VersionNoteCacheModel versionNoteCacheModel =
			(VersionNoteCacheModel)object;

		if (versionNoteId == versionNoteCacheModel.versionNoteId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, versionNoteId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{versionNoteId=");
		sb.append(versionNoteId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", content=");
		sb.append(content);
		sb.append(", versionNoteDate=");
		sb.append(versionNoteDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VersionNote toEntityModel() {
		VersionNoteImpl versionNoteImpl = new VersionNoteImpl();

		versionNoteImpl.setVersionNoteId(versionNoteId);

		if (title == null) {
			versionNoteImpl.setTitle("");
		}
		else {
			versionNoteImpl.setTitle(title);
		}

		if (content == null) {
			versionNoteImpl.setContent("");
		}
		else {
			versionNoteImpl.setContent(content);
		}

		if (versionNoteDate == Long.MIN_VALUE) {
			versionNoteImpl.setVersionNoteDate(null);
		}
		else {
			versionNoteImpl.setVersionNoteDate(new Date(versionNoteDate));
		}

		versionNoteImpl.resetOriginalValues();

		return versionNoteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		versionNoteId = objectInput.readLong();
		title = objectInput.readUTF();
		content = objectInput.readUTF();
		versionNoteDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(versionNoteId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		objectOutput.writeLong(versionNoteDate);
	}

	public long versionNoteId;
	public String title;
	public String content;
	public long versionNoteDate;

}