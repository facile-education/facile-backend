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
 * Provides a wrapper for {@link UserUtilsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserUtilsLocalService
 * @generated
 */
public class UserUtilsLocalServiceWrapper
	implements ServiceWrapper<UserUtilsLocalService>, UserUtilsLocalService {

	public UserUtilsLocalServiceWrapper(
		UserUtilsLocalService userUtilsLocalService) {

		_userUtilsLocalService = userUtilsLocalService;
	}

	@Override
	public String generateLogin(String lastName, String firstName) {
		return _userUtilsLocalService.generateLogin(lastName, firstName);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userUtilsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getStudentMainTeachers(com.liferay.portal.kernel.model.User student) {

		return _userUtilsLocalService.getStudentMainTeachers(student);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getUserConseillersSociaux(com.liferay.portal.kernel.model.User user) {

		return _userUtilsLocalService.getUserConseillersSociaux(user);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getUserDoyens(
		com.liferay.portal.kernel.model.User user) {

		return _userUtilsLocalService.getUserDoyens(user);
	}

	/**
	 * This method a list a user based on a list of userId
	 *
	 * @return List<User> the users object , empty array in case of error
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getUserListFromUserIdList(java.util.List<Long> userIds) {

		return _userUtilsLocalService.getUserListFromUserIdList(userIds);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getUserPsychologues(com.liferay.portal.kernel.model.User user) {

		return _userUtilsLocalService.getUserPsychologues(user);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getUserTeachers(
		com.liferay.portal.kernel.model.User user) {

		return _userUtilsLocalService.getUserTeachers(user);
	}

	/**
	 * Purges all expired users
	 *
	 * @return the list of purged userIds
	 */
	@Override
	public boolean purgeExpiredUsers() {
		return _userUtilsLocalService.purgeExpiredUsers();
	}

	@Override
	public String updateUserPassword(
		com.liferay.portal.kernel.model.User user, String newPassword,
		String confirmPassword, boolean resetPassword) {

		return _userUtilsLocalService.updateUserPassword(
			user, newPassword, confirmPassword, resetPassword);
	}

	@Override
	public UserUtilsLocalService getWrappedService() {
		return _userUtilsLocalService;
	}

	@Override
	public void setWrappedService(UserUtilsLocalService userUtilsLocalService) {
		_userUtilsLocalService = userUtilsLocalService;
	}

	private UserUtilsLocalService _userUtilsLocalService;

}