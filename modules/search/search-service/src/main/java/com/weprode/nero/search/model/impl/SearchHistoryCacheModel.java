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

package com.weprode.nero.search.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.weprode.nero.search.model.SearchHistory;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SearchHistory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SearchHistoryCacheModel
	implements CacheModel<SearchHistory>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SearchHistoryCacheModel)) {
			return false;
		}

		SearchHistoryCacheModel searchHistoryCacheModel =
			(SearchHistoryCacheModel)object;

		if (searchHistoryId == searchHistoryCacheModel.searchHistoryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, searchHistoryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{searchHistoryId=");
		sb.append(searchHistoryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", query=");
		sb.append(query);
		sb.append(", queryDate=");
		sb.append(queryDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SearchHistory toEntityModel() {
		SearchHistoryImpl searchHistoryImpl = new SearchHistoryImpl();

		searchHistoryImpl.setSearchHistoryId(searchHistoryId);
		searchHistoryImpl.setUserId(userId);

		if (query == null) {
			searchHistoryImpl.setQuery("");
		}
		else {
			searchHistoryImpl.setQuery(query);
		}

		if (queryDate == Long.MIN_VALUE) {
			searchHistoryImpl.setQueryDate(null);
		}
		else {
			searchHistoryImpl.setQueryDate(new Date(queryDate));
		}

		searchHistoryImpl.resetOriginalValues();

		return searchHistoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		searchHistoryId = objectInput.readLong();

		userId = objectInput.readLong();
		query = objectInput.readUTF();
		queryDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(searchHistoryId);

		objectOutput.writeLong(userId);

		if (query == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(query);
		}

		objectOutput.writeLong(queryDate);
	}

	public long searchHistoryId;
	public long userId;
	public String query;
	public long queryDate;

}