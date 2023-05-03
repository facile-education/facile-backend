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

package com.weprode.nero.organization.service;

/**
 * Provides the remote service utility for OrgUtils. This utility wraps
 * <code>com.weprode.nero.organization.service.impl.OrgUtilsServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marc Salvat
 * @see OrgUtilsService
 * @generated
 */
public class OrgUtilsServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.organization.service.impl.OrgUtilsServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject getAllSchools() {
		return getService().getAllSchools();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getSchoolClasses(
		long schoolId, boolean includeCours) {

		return getService().getSchoolClasses(schoolId, includeCours);
	}

	/**
	 * Return the volee list for given schoolId or all the volees in user school list
	 * if schoolId is 0
	 */
	public static org.json.JSONObject getSchoolVolees(long schoolId) {
		return getService().getSchoolVolees(schoolId);
	}

	public static org.json.JSONObject getVisibilitySchools() {
		return getService().getVisibilitySchools();
	}

	public static OrgUtilsService getService() {
		return _service;
	}

	private static volatile OrgUtilsService _service;

}