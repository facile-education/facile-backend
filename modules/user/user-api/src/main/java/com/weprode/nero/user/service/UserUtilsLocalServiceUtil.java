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

import java.util.List;

/**
 * Provides the local service utility for UserUtils. This utility wraps
 * <code>com.weprode.nero.user.service.impl.UserUtilsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserUtilsLocalService
 * @generated
 */
public class UserUtilsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.user.service.impl.UserUtilsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static String generateLogin(String lastName, String firstName) {
		return getService().generateLogin(lastName, firstName);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<com.liferay.portal.kernel.model.User>
		getStudentMainTeachers(com.liferay.portal.kernel.model.User student) {

		return getService().getStudentMainTeachers(student);
	}

	public static List<com.liferay.portal.kernel.model.User>
		getUserConseillersSociaux(com.liferay.portal.kernel.model.User user) {

		return getService().getUserConseillersSociaux(user);
	}

	public static List<com.liferay.portal.kernel.model.User> getUserDoyens(
		com.liferay.portal.kernel.model.User user) {

		return getService().getUserDoyens(user);
	}

	public static List<Long> getUserGroupIds(long userId) {
		return getService().getUserGroupIds(userId);
	}

	/**
	 * This method a list a user based on a list of userId
	 *
	 * @return List<User> the users object , empty array in case of error
	 */
	public static List<com.liferay.portal.kernel.model.User>
		getUserListFromUserIdList(List<Long> userIds) {

		return getService().getUserListFromUserIdList(userIds);
	}

	public static List<com.liferay.portal.kernel.model.User>
		getUserPsychologues(com.liferay.portal.kernel.model.User user) {

		return getService().getUserPsychologues(user);
	}

	public static List<com.liferay.portal.kernel.model.User> getUserTeachers(
		com.liferay.portal.kernel.model.User user) {

		return getService().getUserTeachers(user);
	}

	/**
	 * Purges all expired users
	 */
	public static boolean purgeExpiredUsers() {
		return getService().purgeExpiredUsers();
	}

	public static String updateUserPassword(
		com.liferay.portal.kernel.model.User user, String newPassword,
		String confirmPassword, boolean resetPassword) {

		return getService().updateUserPassword(
			user, newPassword, confirmPassword, resetPassword);
	}

	public static UserUtilsLocalService getService() {
		return _service;
	}

	private static volatile UserUtilsLocalService _service;

}