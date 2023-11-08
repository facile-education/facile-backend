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

package com.weprode.facile.access.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.access.model.AccessCategory;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AccessCategory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccessCategoryCacheModel
	implements CacheModel<AccessCategory>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccessCategoryCacheModel)) {
			return false;
		}

		AccessCategoryCacheModel accessCategoryCacheModel =
			(AccessCategoryCacheModel)object;

		if (categoryId == accessCategoryCacheModel.categoryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, categoryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", categoryId=");
		sb.append(categoryId);
		sb.append(", schoolId=");
		sb.append(schoolId);
		sb.append(", categoryName=");
		sb.append(categoryName);
		sb.append(", position=");
		sb.append(position);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccessCategory toEntityModel() {
		AccessCategoryImpl accessCategoryImpl = new AccessCategoryImpl();

		if (uuid == null) {
			accessCategoryImpl.setUuid("");
		}
		else {
			accessCategoryImpl.setUuid(uuid);
		}

		accessCategoryImpl.setCategoryId(categoryId);
		accessCategoryImpl.setSchoolId(schoolId);

		if (categoryName == null) {
			accessCategoryImpl.setCategoryName("");
		}
		else {
			accessCategoryImpl.setCategoryName(categoryName);
		}

		accessCategoryImpl.setPosition(position);

		accessCategoryImpl.resetOriginalValues();

		return accessCategoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		categoryId = objectInput.readLong();

		schoolId = objectInput.readLong();
		categoryName = objectInput.readUTF();

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

		objectOutput.writeLong(categoryId);

		objectOutput.writeLong(schoolId);

		if (categoryName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(categoryName);
		}

		objectOutput.writeInt(position);
	}

	public String uuid;
	public long categoryId;
	public long schoolId;
	public String categoryName;
	public int position;

}