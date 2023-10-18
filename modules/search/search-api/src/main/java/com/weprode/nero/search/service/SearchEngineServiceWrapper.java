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

package com.weprode.nero.search.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SearchEngineService}.
 *
 * @author Brian Wing Shun Chan
 * @see SearchEngineService
 * @generated
 */
public class SearchEngineServiceWrapper
	implements SearchEngineService, ServiceWrapper<SearchEngineService> {

	public SearchEngineServiceWrapper() {
		this(null);
	}

	public SearchEngineServiceWrapper(SearchEngineService searchEngineService) {
		_searchEngineService = searchEngineService;
	}

	@Override
	public org.json.JSONObject addQueryHistory(String query) {
		return _searchEngineService.addQueryHistory(query);
	}

	@Override
	public org.json.JSONObject advancedSearch(
		String query, int service, boolean filesOnly, int start, int end) {

		return _searchEngineService.advancedSearch(
			query, service, filesOnly, start, end);
	}

	@Override
	public org.json.JSONObject getLastSearchQueries() {
		return _searchEngineService.getLastSearchQueries();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _searchEngineService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSearchResultDetails(
		long entityId, int service) {

		return _searchEngineService.getSearchResultDetails(entityId, service);
	}

	@Override
	public org.json.JSONObject quickSearch(String query, int start, int end) {
		return _searchEngineService.quickSearch(query, start, end);
	}

	@Override
	public SearchEngineService getWrappedService() {
		return _searchEngineService;
	}

	@Override
	public void setWrappedService(SearchEngineService searchEngineService) {
		_searchEngineService = searchEngineService;
	}

	private SearchEngineService _searchEngineService;

}