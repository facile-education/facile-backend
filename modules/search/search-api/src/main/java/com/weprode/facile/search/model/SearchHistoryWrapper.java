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

package com.weprode.facile.search.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SearchHistory}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SearchHistory
 * @generated
 */
public class SearchHistoryWrapper
	extends BaseModelWrapper<SearchHistory>
	implements ModelWrapper<SearchHistory>, SearchHistory {

	public SearchHistoryWrapper(SearchHistory searchHistory) {
		super(searchHistory);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("searchHistoryId", getSearchHistoryId());
		attributes.put("userId", getUserId());
		attributes.put("query", getQuery());
		attributes.put("queryDate", getQueryDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long searchHistoryId = (Long)attributes.get("searchHistoryId");

		if (searchHistoryId != null) {
			setSearchHistoryId(searchHistoryId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String query = (String)attributes.get("query");

		if (query != null) {
			setQuery(query);
		}

		Date queryDate = (Date)attributes.get("queryDate");

		if (queryDate != null) {
			setQueryDate(queryDate);
		}
	}

	@Override
	public SearchHistory cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the primary key of this search history.
	 *
	 * @return the primary key of this search history
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the query of this search history.
	 *
	 * @return the query of this search history
	 */
	@Override
	public String getQuery() {
		return model.getQuery();
	}

	/**
	 * Returns the query date of this search history.
	 *
	 * @return the query date of this search history
	 */
	@Override
	public Date getQueryDate() {
		return model.getQueryDate();
	}

	/**
	 * Returns the search history ID of this search history.
	 *
	 * @return the search history ID of this search history
	 */
	@Override
	public long getSearchHistoryId() {
		return model.getSearchHistoryId();
	}

	/**
	 * Returns the user ID of this search history.
	 *
	 * @return the user ID of this search history
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this search history.
	 *
	 * @return the user uuid of this search history
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the primary key of this search history.
	 *
	 * @param primaryKey the primary key of this search history
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the query of this search history.
	 *
	 * @param query the query of this search history
	 */
	@Override
	public void setQuery(String query) {
		model.setQuery(query);
	}

	/**
	 * Sets the query date of this search history.
	 *
	 * @param queryDate the query date of this search history
	 */
	@Override
	public void setQueryDate(Date queryDate) {
		model.setQueryDate(queryDate);
	}

	/**
	 * Sets the search history ID of this search history.
	 *
	 * @param searchHistoryId the search history ID of this search history
	 */
	@Override
	public void setSearchHistoryId(long searchHistoryId) {
		model.setSearchHistoryId(searchHistoryId);
	}

	/**
	 * Sets the user ID of this search history.
	 *
	 * @param userId the user ID of this search history
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this search history.
	 *
	 * @param userUuid the user uuid of this search history
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected SearchHistoryWrapper wrap(SearchHistory searchHistory) {
		return new SearchHistoryWrapper(searchHistory);
	}

}