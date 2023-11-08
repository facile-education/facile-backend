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

package com.weprode.facile.document.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GeogebraService}.
 *
 * @author Brian Wing Shun Chan
 * @see GeogebraService
 * @generated
 */
public class GeogebraServiceWrapper
	implements GeogebraService, ServiceWrapper<GeogebraService> {

	public GeogebraServiceWrapper() {
		this(null);
	}

	public GeogebraServiceWrapper(GeogebraService geogebraService) {
		_geogebraService = geogebraService;
	}

	@Override
	public org.json.JSONObject getGeogebraFile(long fileVersionId) {
		return _geogebraService.getGeogebraFile(fileVersionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _geogebraService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject saveGeogebraFile(String params) {
		return _geogebraService.saveGeogebraFile(params);
	}

	@Override
	public GeogebraService getWrappedService() {
		return _geogebraService;
	}

	@Override
	public void setWrappedService(GeogebraService geogebraService) {
		_geogebraService = geogebraService;
	}

	private GeogebraService _geogebraService;

}