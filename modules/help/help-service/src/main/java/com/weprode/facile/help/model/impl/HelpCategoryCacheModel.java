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

package com.weprode.facile.help.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.help.model.HelpCategory;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing HelpCategory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HelpCategoryCacheModel
	implements CacheModel<HelpCategory>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof HelpCategoryCacheModel)) {
			return false;
		}

		HelpCategoryCacheModel helpCategoryCacheModel =
			(HelpCategoryCacheModel)object;

		if (categoryId == helpCategoryCacheModel.categoryId) {
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
		StringBundler sb = new StringBundler(9);

		sb.append("{categoryId=");
		sb.append(categoryId);
		sb.append(", categoryName=");
		sb.append(categoryName);
		sb.append(", serviceId=");
		sb.append(serviceId);
		sb.append(", position=");
		sb.append(position);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public HelpCategory toEntityModel() {
		HelpCategoryImpl helpCategoryImpl = new HelpCategoryImpl();

		helpCategoryImpl.setCategoryId(categoryId);

		if (categoryName == null) {
			helpCategoryImpl.setCategoryName("");
		}
		else {
			helpCategoryImpl.setCategoryName(categoryName);
		}

		helpCategoryImpl.setServiceId(serviceId);
		helpCategoryImpl.setPosition(position);

		helpCategoryImpl.resetOriginalValues();

		return helpCategoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		categoryId = objectInput.readLong();
		categoryName = objectInput.readUTF();

		serviceId = objectInput.readLong();

		position = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(categoryId);

		if (categoryName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(categoryName);
		}

		objectOutput.writeLong(serviceId);

		objectOutput.writeInt(position);
	}

	public long categoryId;
	public String categoryName;
	public long serviceId;
	public int position;

}