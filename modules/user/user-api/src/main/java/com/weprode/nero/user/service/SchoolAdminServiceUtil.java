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
 * Provides the remote service utility for SchoolAdmin. This utility wraps
 * <code>com.weprode.nero.user.service.impl.SchoolAdminServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SchoolAdminService
 * @generated
 */
public class SchoolAdminServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.user.service.impl.SchoolAdminServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject addSchoolAdmin(
		long userId, long schoolId) {

		return getService().addSchoolAdmin(userId, schoolId);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		getDelegationCandidates(long schoolId, java.lang.String filter) {

		return getService().getDelegationCandidates(schoolId, filter);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.json.JSONObject getSchoolDelegates(
		long schoolId) {

		return getService().getSchoolDelegates(schoolId);
	}

	public static com.liferay.portal.kernel.json.JSONObject removeSchoolAdmin(
		long userId, long schoolId) {

		return getService().removeSchoolAdmin(userId, schoolId);
	}

	public static SchoolAdminService getService() {
		return _service;
	}

	private static volatile SchoolAdminService _service;

}