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

package com.weprode.facile.user.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserPasswordService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserPasswordService
 * @generated
 */
public class UserPasswordServiceWrapper
	implements ServiceWrapper<UserPasswordService>, UserPasswordService {

	public UserPasswordServiceWrapper() {
		this(null);
	}

	public UserPasswordServiceWrapper(UserPasswordService userPasswordService) {
		_userPasswordService = userPasswordService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userPasswordService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject sendPasswordResetLink(String email)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userPasswordService.sendPasswordResetLink(email);
	}

	@Override
	public org.json.JSONObject sendScreenname(String email)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userPasswordService.sendScreenname(email);
	}

	@Override
	public UserPasswordService getWrappedService() {
		return _userPasswordService;
	}

	@Override
	public void setWrappedService(UserPasswordService userPasswordService) {
		_userPasswordService = userPasswordService;
	}

	private UserPasswordService _userPasswordService;

}