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
 * Provides the remote service utility for UserManagement. This utility wraps
 * <code>com.weprode.nero.user.service.impl.UserManagementServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see UserManagementService
 * @generated
 */
public class UserManagementServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.user.service.impl.UserManagementServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject createManualUser(
		java.lang.String lastName, java.lang.String firstName,
		java.lang.String email, long roleId, long schoolId) {

		return getService().createManualUser(
			lastName, firstName, email, roleId, schoolId);
	}

	public static org.json.JSONObject deleteManualUser(long userId) {
		return getService().deleteManualUser(userId);
	}

	public static org.json.JSONObject editManualUser(
		long userId, java.lang.String lastName, java.lang.String firstName,
		java.lang.String email, long roleId, long schoolId) {

		return getService().editManualUser(
			userId, lastName, firstName, email, roleId, schoolId);
	}

	public static org.json.JSONObject getManualUsers(
		long schoolId, java.lang.String search, int start, int limit) {

		return getService().getManualUsers(schoolId, search, start, limit);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject updatePassword(
		long userId, java.lang.String password, boolean resetPassword) {

		return getService().updatePassword(userId, password, resetPassword);
	}

	public static UserManagementService getService() {
		return _service;
	}

	private static volatile UserManagementService _service;

}