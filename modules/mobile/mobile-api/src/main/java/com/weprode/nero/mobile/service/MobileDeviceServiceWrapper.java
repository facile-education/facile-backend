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

package com.weprode.nero.mobile.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MobileDeviceService}.
 *
 * @author Brian Wing Shun Chan
 * @see MobileDeviceService
 * @generated
 */
public class MobileDeviceServiceWrapper
	implements MobileDeviceService, ServiceWrapper<MobileDeviceService> {

	public MobileDeviceServiceWrapper(MobileDeviceService mobileDeviceService) {
		_mobileDeviceService = mobileDeviceService;
	}

	@Override
	public org.json.JSONObject deleteUserDevice(String deviceId) {
		return _mobileDeviceService.deleteUserDevice(deviceId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _mobileDeviceService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject saveFullUserDevice(
		String deviceId, long userId, String model, String manufacturer,
		String os, String osVersion, String browserUA) {

		return _mobileDeviceService.saveFullUserDevice(
			deviceId, userId, model, manufacturer, os, osVersion, browserUA);
	}

	@Override
	public org.json.JSONObject saveUserDevice(String deviceId, long userId) {
		return _mobileDeviceService.saveUserDevice(deviceId, userId);
	}

	@Override
	public MobileDeviceService getWrappedService() {
		return _mobileDeviceService;
	}

	@Override
	public void setWrappedService(MobileDeviceService mobileDeviceService) {
		_mobileDeviceService = mobileDeviceService;
	}

	private MobileDeviceService _mobileDeviceService;

}