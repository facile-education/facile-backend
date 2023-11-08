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

package com.weprode.facile.news.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.facile.news.model.NewsPopulation;
import com.weprode.facile.news.service.persistence.NewsPopulationPK;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NewsPopulation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NewsPopulationCacheModel
	implements CacheModel<NewsPopulation>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof NewsPopulationCacheModel)) {
			return false;
		}

		NewsPopulationCacheModel newsPopulationCacheModel =
			(NewsPopulationCacheModel)object;

		if (newsPopulationPK.equals(
				newsPopulationCacheModel.newsPopulationPK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, newsPopulationPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{newsId=");
		sb.append(newsId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public NewsPopulation toEntityModel() {
		NewsPopulationImpl newsPopulationImpl = new NewsPopulationImpl();

		newsPopulationImpl.setNewsId(newsId);
		newsPopulationImpl.setGroupId(groupId);
		newsPopulationImpl.setRoleId(roleId);

		newsPopulationImpl.resetOriginalValues();

		return newsPopulationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		newsId = objectInput.readLong();

		groupId = objectInput.readLong();

		roleId = objectInput.readLong();

		newsPopulationPK = new NewsPopulationPK(newsId, groupId, roleId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(newsId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(roleId);
	}

	public long newsId;
	public long groupId;
	public long roleId;
	public transient NewsPopulationPK newsPopulationPK;

}