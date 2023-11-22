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
 * Provides a wrapper for {@link UserManagementLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserManagementLocalService
 * @generated
 */
public class UserManagementLocalServiceWrapper
	implements ServiceWrapper<UserManagementLocalService>,
			   UserManagementLocalService {

	public UserManagementLocalServiceWrapper() {
		this(null);
	}

	public UserManagementLocalServiceWrapper(
		UserManagementLocalService userManagementLocalService) {

		_userManagementLocalService = userManagementLocalService;
	}

	@Override
	public com.liferay.portal.kernel.model.User createManualUser(
		String lastName, String firstName, String email,
		java.util.Date birthday, long roleId, long schoolId) {

		return _userManagementLocalService.createManualUser(
			lastName, firstName, email, birthday, roleId, schoolId);
	}

	@Override
	public com.liferay.portal.kernel.model.User createManualUser(
		String lastName, String firstName, String email,
		java.util.Date birthday, long roleId, long schoolId,
		boolean sendUserLogin) {

		return _userManagementLocalService.createManualUser(
			lastName, firstName, email, birthday, roleId, schoolId,
			sendUserLogin);
	}

	@Override
	public com.liferay.portal.kernel.model.User createManualUser(
		String lastName, String firstName, String email,
		java.util.Date birthday, long roleId, long schoolId,
		boolean sendUserLogin, String password) {

		return _userManagementLocalService.createManualUser(
			lastName, firstName, email, birthday, roleId, schoolId,
			sendUserLogin, password);
	}

	@Override
	public com.liferay.portal.kernel.model.User createUser(
		long companyId, String lastName, String firstName, String email,
		boolean isMale, java.util.Date birthday, boolean sendUserLogin,
		String password) {

		return _userManagementLocalService.createUser(
			companyId, lastName, firstName, email, isMale, birthday,
			sendUserLogin, password);
	}

	@Override
	public com.liferay.portal.kernel.model.User createUser(
		long companyId, String lastName, String firstName, String email,
		java.util.Date birthday) {

		return _userManagementLocalService.createUser(
			companyId, lastName, firstName, email, birthday);
	}

	@Override
	public com.liferay.portal.kernel.model.User createUser(
		long companyId, String lastName, String firstName, String email,
		java.util.Date birthday, boolean sendUserLogin) {

		return _userManagementLocalService.createUser(
			companyId, lastName, firstName, email, birthday, sendUserLogin);
	}

	@Override
	public String generatePassword() {
		return _userManagementLocalService.generatePassword();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userManagementLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public void synchronizeUserSchool(long userId, long schoolId) {
		_userManagementLocalService.synchronizeUserSchool(userId, schoolId);
	}

	@Override
	public UserManagementLocalService getWrappedService() {
		return _userManagementLocalService;
	}

	@Override
	public void setWrappedService(
		UserManagementLocalService userManagementLocalService) {

		_userManagementLocalService = userManagementLocalService;
	}

	private UserManagementLocalService _userManagementLocalService;

}