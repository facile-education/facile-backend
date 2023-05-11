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

package com.weprode.nero.news.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.news.model.NewsRead;
import com.weprode.nero.news.service.persistence.NewsReadPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing NewsRead in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NewsReadCacheModel
	implements CacheModel<NewsRead>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NewsReadCacheModel)) {
			return false;
		}

		NewsReadCacheModel newsReadCacheModel = (NewsReadCacheModel)object;

		if (newsReadPK.equals(newsReadCacheModel.newsReadPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, newsReadPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{newsId=");
		sb.append(newsId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", readDate=");
		sb.append(readDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NewsRead toEntityModel() {
		NewsReadImpl newsReadImpl = new NewsReadImpl();

		newsReadImpl.setNewsId(newsId);
		newsReadImpl.setUserId(userId);

		if (readDate == Long.MIN_VALUE) {
			newsReadImpl.setReadDate(null);
		}
		else {
			newsReadImpl.setReadDate(new Date(readDate));
		}

		newsReadImpl.resetOriginalValues();

		return newsReadImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		newsId = objectInput.readLong();

		userId = objectInput.readLong();
		readDate = objectInput.readLong();

		newsReadPK = new NewsReadPK(newsId, userId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(newsId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(readDate);
	}

	public long newsId;
	public long userId;
	public long readDate;
	public transient NewsReadPK newsReadPK;

}