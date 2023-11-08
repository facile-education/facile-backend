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

package com.weprode.facile.mobile.service;

/**
 * Provides the remote service utility for MobileDevice. This utility wraps
 * <code>com.weprode.facile.mobile.service.impl.MobileDeviceServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MobileDeviceService
 * @generated
 */
public class MobileDeviceServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.mobile.service.impl.MobileDeviceServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject deleteUserDevice(
		java.lang.String deviceId) {

		return getService().deleteUserDevice(deviceId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject saveFullUserDevice(
		java.lang.String deviceId, long userId, java.lang.String model,
		java.lang.String manufacturer, java.lang.String os,
		java.lang.String osVersion, java.lang.String browserUA) {

		return getService().saveFullUserDevice(
			deviceId, userId, model, manufacturer, os, osVersion, browserUA);
	}

	public static org.json.JSONObject saveUserDevice(
		java.lang.String deviceId, long userId) {

		return getService().saveUserDevice(deviceId, userId);
	}

	public static MobileDeviceService getService() {
		return _service;
	}

	private static volatile MobileDeviceService _service;

}