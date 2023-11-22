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

/**
 * Provides the local service utility for UserManagement. This utility wraps
 * <code>com.weprode.nero.user.service.impl.UserManagementLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserManagementLocalService
 * @generated
 */
public class UserManagementLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.user.service.impl.UserManagementLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.model.User createManualUser(
		java.lang.String lastName, java.lang.String firstName,
		java.lang.String email, java.util.Date birthday, long roleId,
		long schoolId) {

		return getService().createManualUser(
			lastName, firstName, email, birthday, roleId, schoolId);
	}

	public static com.liferay.portal.kernel.model.User createManualUser(
		java.lang.String lastName, java.lang.String firstName,
		java.lang.String email, java.util.Date birthday, long roleId,
		long schoolId, boolean sendUserLogin) {

		return getService().createManualUser(
			lastName, firstName, email, birthday, roleId, schoolId,
			sendUserLogin);
	}

	public static com.liferay.portal.kernel.model.User createManualUser(
		java.lang.String lastName, java.lang.String firstName,
		java.lang.String email, java.util.Date birthday, long roleId,
		long schoolId, boolean sendUserLogin, java.lang.String password) {

		return getService().createManualUser(
			lastName, firstName, email, birthday, roleId, schoolId,
			sendUserLogin, password);
	}

	public static com.liferay.portal.kernel.model.User createUser(
		long companyId, java.lang.String lastName, java.lang.String firstName,
		java.lang.String email, boolean isMale, java.util.Date birthday,
		boolean sendUserLogin, java.lang.String password) {

		return getService().createUser(
			companyId, lastName, firstName, email, isMale, birthday,
			sendUserLogin, password);
	}

	public static com.liferay.portal.kernel.model.User createUser(
		long companyId, java.lang.String lastName, java.lang.String firstName,
		java.lang.String email, java.util.Date birthday) {

		return getService().createUser(
			companyId, lastName, firstName, email, birthday);
	}

	public static com.liferay.portal.kernel.model.User createUser(
		long companyId, java.lang.String lastName, java.lang.String firstName,
		java.lang.String email, java.util.Date birthday,
		boolean sendUserLogin) {

		return getService().createUser(
			companyId, lastName, firstName, email, birthday, sendUserLogin);
	}

	public static java.lang.String generatePassword() {
		return getService().generatePassword();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void synchronizeUserSchool(long userId, long schoolId) {
		getService().synchronizeUserSchool(userId, schoolId);
	}

	public static UserManagementLocalService getService() {
		return _service;
	}

	private static volatile UserManagementLocalService _service;

}