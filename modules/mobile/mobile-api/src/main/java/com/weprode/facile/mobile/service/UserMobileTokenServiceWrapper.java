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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserMobileTokenService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserMobileTokenService
 * @generated
 */
public class UserMobileTokenServiceWrapper
	implements ServiceWrapper<UserMobileTokenService>, UserMobileTokenService {

	public UserMobileTokenServiceWrapper() {
		this(null);
	}

	public UserMobileTokenServiceWrapper(
		UserMobileTokenService userMobileTokenService) {

		_userMobileTokenService = userMobileTokenService;
	}

	@Override
	public org.json.JSONObject addMobileToken() {
		return _userMobileTokenService.addMobileToken();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userMobileTokenService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject refreshMobileToken(String token) {
		return _userMobileTokenService.refreshMobileToken(token);
	}

	@Override
	public UserMobileTokenService getWrappedService() {
		return _userMobileTokenService;
	}

	@Override
	public void setWrappedService(
		UserMobileTokenService userMobileTokenService) {

		_userMobileTokenService = userMobileTokenService;
	}

	private UserMobileTokenService _userMobileTokenService;

}