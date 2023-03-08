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

package com.weprode.nero.user.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserManagementService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserManagementService
 * @generated
 */
public class UserManagementServiceWrapper
	implements ServiceWrapper<UserManagementService>, UserManagementService {

	public UserManagementServiceWrapper(
		UserManagementService userManagementService) {

		_userManagementService = userManagementService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject createManualUser(
		String lastName, String firstName, String email, long roleId,
		long schoolId) {

		return _userManagementService.createManualUser(
			lastName, firstName, email, roleId, schoolId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject deleteManualUser(
		long userId) {

		return _userManagementService.deleteManualUser(userId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject editManualUser(
		long userId, String lastName, String firstName, String email,
		long roleId, long schoolId) {

		return _userManagementService.editManualUser(
			userId, lastName, firstName, email, roleId, schoolId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getManualUsers(
		long schoolId, String search, int start, int limit) {

		return _userManagementService.getManualUsers(
			schoolId, search, start, limit);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userManagementService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updatePassword(
		long userId, String password) {

		return _userManagementService.updatePassword(userId, password);
	}

	@Override
	public UserManagementService getWrappedService() {
		return _userManagementService;
	}

	@Override
	public void setWrappedService(UserManagementService userManagementService) {
		_userManagementService = userManagementService;
	}

	private UserManagementService _userManagementService;

}