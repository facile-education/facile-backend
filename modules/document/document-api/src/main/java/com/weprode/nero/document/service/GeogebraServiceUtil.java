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

package com.weprode.nero.document.service;

/**
 * Provides the remote service utility for Geogebra. This utility wraps
 * <code>com.weprode.nero.document.service.impl.GeogebraServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see GeogebraService
 * @generated
 */
public class GeogebraServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.GeogebraServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject getGeogebraFile(long fileVersionId) {
		return getService().getGeogebraFile(fileVersionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject saveGeogebraFile(
		java.lang.String params) {

		return getService().saveGeogebraFile(params);
	}

	public static GeogebraService getService() {
		return _service;
	}

	private static volatile GeogebraService _service;

}