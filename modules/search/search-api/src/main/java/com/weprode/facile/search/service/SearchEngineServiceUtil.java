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

package com.weprode.facile.search.service;

/**
 * Provides the remote service utility for SearchEngine. This utility wraps
 * <code>com.weprode.facile.search.service.impl.SearchEngineServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SearchEngineService
 * @generated
 */
public class SearchEngineServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.search.service.impl.SearchEngineServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject addQueryHistory(java.lang.String query) {
		return getService().addQueryHistory(query);
	}

	public static org.json.JSONObject advancedSearch(
		java.lang.String query, int service, boolean filesOnly, int start,
		int end) {

		return getService().advancedSearch(
			query, service, filesOnly, start, end);
	}

	public static org.json.JSONObject getLastSearchQueries() {
		return getService().getLastSearchQueries();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getSearchResultDetails(
		long entityId, int service) {

		return getService().getSearchResultDetails(entityId, service);
	}

	public static org.json.JSONObject quickSearch(
		java.lang.String query, int start, int end) {

		return getService().quickSearch(query, start, end);
	}

	public static SearchEngineService getService() {
		return _service;
	}

	private static volatile SearchEngineService _service;

}