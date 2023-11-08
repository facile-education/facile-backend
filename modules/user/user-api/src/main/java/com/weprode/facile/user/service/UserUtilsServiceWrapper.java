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
 * Provides a wrapper for {@link UserUtilsService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserUtilsService
 * @generated
 */
public class UserUtilsServiceWrapper
	implements ServiceWrapper<UserUtilsService>, UserUtilsService {

	public UserUtilsServiceWrapper() {
		this(null);
	}

	public UserUtilsServiceWrapper(UserUtilsService userUtilsService) {
		_userUtilsService = userUtilsService;
	}

	@Override
	public org.json.JSONObject acceptTermsOfUse() {
		return _userUtilsService.acceptTermsOfUse();
	}

	@Override
	public org.json.JSONObject getCasAttributes() {
		return _userUtilsService.getCasAttributes();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userUtilsService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getParentInfos(long parentUserId) {
		return _userUtilsService.getParentInfos(parentUserId);
	}

	@Override
	public org.json.JSONObject getPersonnalDetails() {
		return _userUtilsService.getPersonnalDetails();
	}

	@Override
	public org.json.JSONObject getUserInfos() {
		return _userUtilsService.getUserInfos();
	}

	@Override
	public UserUtilsService getWrappedService() {
		return _userUtilsService;
	}

	@Override
	public void setWrappedService(UserUtilsService userUtilsService) {
		_userUtilsService = userUtilsService;
	}

	private UserUtilsService _userUtilsService;

}