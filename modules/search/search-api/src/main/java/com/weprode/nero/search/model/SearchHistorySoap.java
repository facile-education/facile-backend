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

package com.weprode.nero.search.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class SearchHistorySoap implements Serializable {

	public static SearchHistorySoap toSoapModel(SearchHistory model) {
		SearchHistorySoap soapModel = new SearchHistorySoap();

		soapModel.setSearchHistoryId(model.getSearchHistoryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setQuery(model.getQuery());
		soapModel.setQueryDate(model.getQueryDate());

		return soapModel;
	}

	public static SearchHistorySoap[] toSoapModels(SearchHistory[] models) {
		SearchHistorySoap[] soapModels = new SearchHistorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SearchHistorySoap[][] toSoapModels(SearchHistory[][] models) {
		SearchHistorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SearchHistorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new SearchHistorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SearchHistorySoap[] toSoapModels(List<SearchHistory> models) {
		List<SearchHistorySoap> soapModels = new ArrayList<SearchHistorySoap>(
			models.size());

		for (SearchHistory model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SearchHistorySoap[soapModels.size()]);
	}

	public SearchHistorySoap() {
	}

	public long getPrimaryKey() {
		return _searchHistoryId;
	}

	public void setPrimaryKey(long pk) {
		setSearchHistoryId(pk);
	}

	public long getSearchHistoryId() {
		return _searchHistoryId;
	}

	public void setSearchHistoryId(long searchHistoryId) {
		_searchHistoryId = searchHistoryId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getQuery() {
		return _query;
	}

	public void setQuery(String query) {
		_query = query;
	}

	public Date getQueryDate() {
		return _queryDate;
	}

	public void setQueryDate(Date queryDate) {
		_queryDate = queryDate;
	}

	private long _searchHistoryId;
	private long _userId;
	private String _query;
	private Date _queryDate;

}