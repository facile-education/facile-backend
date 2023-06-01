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

package com.weprode.nero.preference.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserPropertiesService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserPropertiesService
 * @generated
 */
public class UserPropertiesServiceWrapper
	implements ServiceWrapper<UserPropertiesService>, UserPropertiesService {

	public UserPropertiesServiceWrapper(
		UserPropertiesService userPropertiesService) {

		_userPropertiesService = userPropertiesService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userPropertiesService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject updateReportFrequency(int frequency) {
		return _userPropertiesService.updateReportFrequency(frequency);
	}

	@Override
	public org.json.JSONObject updateSideMenuState(boolean isExpanded) {
		return _userPropertiesService.updateSideMenuState(isExpanded);
	}

	@Override
	public org.json.JSONObject updateThemeColor(String color) {
		return _userPropertiesService.updateThemeColor(color);
	}

	@Override
	public org.json.JSONObject updateUserPicture(java.io.File picture) {
		return _userPropertiesService.updateUserPicture(picture);
	}

	@Override
	public org.json.JSONObject updateWebdavPassword(
		String password, String confirmPassword) {

		return _userPropertiesService.updateWebdavPassword(
			password, confirmPassword);
	}

	@Override
	public org.json.JSONObject updateWebdavState(boolean isEnabled) {
		return _userPropertiesService.updateWebdavState(isEnabled);
	}

	@Override
	public UserPropertiesService getWrappedService() {
		return _userPropertiesService;
	}

	@Override
	public void setWrappedService(UserPropertiesService userPropertiesService) {
		_userPropertiesService = userPropertiesService;
	}

	private UserPropertiesService _userPropertiesService;

}