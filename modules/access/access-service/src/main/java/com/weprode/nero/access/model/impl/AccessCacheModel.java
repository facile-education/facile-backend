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

package com.weprode.nero.access.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.access.model.Access;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Access in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccessCacheModel implements CacheModel<Access>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccessCacheModel)) {
			return false;
		}

		AccessCacheModel accessCacheModel = (AccessCacheModel)object;

		if (accessId == accessCacheModel.accessId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accessId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", accessId=");
		sb.append(accessId);
		sb.append(", categoryId=");
		sb.append(categoryId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", url=");
		sb.append(url);
		sb.append(", thumbnail=");
		sb.append(thumbnail);
		sb.append(", position=");
		sb.append(position);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Access toEntityModel() {
		AccessImpl accessImpl = new AccessImpl();

		if (uuid == null) {
			accessImpl.setUuid("");
		}
		else {
			accessImpl.setUuid(uuid);
		}

		accessImpl.setAccessId(accessId);
		accessImpl.setCategoryId(categoryId);

		if (title == null) {
			accessImpl.setTitle("");
		}
		else {
			accessImpl.setTitle(title);
		}

		if (url == null) {
			accessImpl.setUrl("");
		}
		else {
			accessImpl.setUrl(url);
		}

		if (thumbnail == null) {
			accessImpl.setThumbnail("");
		}
		else {
			accessImpl.setThumbnail(thumbnail);
		}

		accessImpl.setPosition(position);

		accessImpl.resetOriginalValues();

		return accessImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		accessId = objectInput.readLong();

		categoryId = objectInput.readLong();
		title = objectInput.readUTF();
		url = objectInput.readUTF();
		thumbnail = objectInput.readUTF();

		position = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(accessId);

		objectOutput.writeLong(categoryId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (thumbnail == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(thumbnail);
		}

		objectOutput.writeInt(position);
	}

	public String uuid;
	public long accessId;
	public long categoryId;
	public String title;
	public String url;
	public String thumbnail;
	public int position;

}