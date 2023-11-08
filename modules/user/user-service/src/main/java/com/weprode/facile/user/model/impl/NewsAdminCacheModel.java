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

package com.weprode.facile.user.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.user.model.NewsAdmin;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NewsAdmin in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NewsAdminCacheModel
	implements CacheModel<NewsAdmin>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NewsAdminCacheModel)) {
			return false;
		}

		NewsAdminCacheModel newsAdminCacheModel = (NewsAdminCacheModel)object;

		if (newsAdminId == newsAdminCacheModel.newsAdminId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, newsAdminId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{newsAdminId=");
		sb.append(newsAdminId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", schoolId=");
		sb.append(schoolId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NewsAdmin toEntityModel() {
		NewsAdminImpl newsAdminImpl = new NewsAdminImpl();

		newsAdminImpl.setNewsAdminId(newsAdminId);
		newsAdminImpl.setUserId(userId);
		newsAdminImpl.setSchoolId(schoolId);

		newsAdminImpl.resetOriginalValues();

		return newsAdminImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		newsAdminId = objectInput.readLong();

		userId = objectInput.readLong();

		schoolId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(newsAdminId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(schoolId);
	}

	public long newsAdminId;
	public long userId;
	public long schoolId;

}