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
 * Provides a wrapper for {@link SearchEngineLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SearchEngineLocalService
 * @generated
 */
public class SearchEngineLocalServiceWrapper
	implements SearchEngineLocalService,
			   ServiceWrapper<SearchEngineLocalService> {

	public SearchEngineLocalServiceWrapper(
		SearchEngineLocalService searchEngineLocalService) {

		_searchEngineLocalService = searchEngineLocalService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _searchEngineLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.weprode.nero.search.model.SearchResults search(
		com.liferay.portal.kernel.model.User user, String keywords, int page,
		int pageSize) {

		return _searchEngineLocalService.search(user, keywords, page, pageSize);
	}

	@Override
	public SearchEngineLocalService getWrappedService() {
		return _searchEngineLocalService;
	}

	@Override
	public void setWrappedService(
		SearchEngineLocalService searchEngineLocalService) {

		_searchEngineLocalService = searchEngineLocalService;
	}

	private SearchEngineLocalService _searchEngineLocalService;

}