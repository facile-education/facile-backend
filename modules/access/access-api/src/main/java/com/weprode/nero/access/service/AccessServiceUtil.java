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

package com.weprode.nero.access.service;

/**
 * Provides the remote service utility for Access. This utility wraps
 * <code>com.weprode.nero.access.service.impl.AccessServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccessService
 * @generated
 */
public class AccessServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.access.service.impl.AccessServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getRoleAccesses(
		long schoolId, long roleId) {

		return getService().getRoleAccesses(schoolId, roleId);
	}

	public static org.json.JSONObject getSchoolAccesses(long schoolId) {
		return getService().getSchoolAccesses(schoolId);
	}

	public static org.json.JSONObject getUserAccesses() {
		return getService().getUserAccesses();
	}

	public static org.json.JSONObject saveSchoolAccesses(
		long schoolId, java.lang.String accesses) {

		return getService().saveSchoolAccesses(schoolId, accesses);
	}

	public static AccessService getService() {
		return _service;
	}

	private static volatile AccessService _service;

}