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

package com.weprode.nero.application.service;

/**
 * Provides the remote service utility for Application. This utility wraps
 * <code>com.weprode.nero.application.service.impl.ApplicationServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationService
 * @generated
 */
public class ApplicationServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.application.service.impl.ApplicationServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject addApplication(
		java.lang.String applicationName, java.lang.String applicationKey,
		java.lang.String category, long menuEntryId, java.lang.String image,
		boolean hasCustomUrl, java.lang.String globalUrl, boolean exportUser,
		boolean exportStudent, boolean exportParent, boolean exportTeacher,
		boolean exportOther, java.lang.String defaultRoles,
		java.lang.String authorizedSchools) {

		return getService().addApplication(
			applicationName, applicationKey, category, menuEntryId, image,
			hasCustomUrl, globalUrl, exportUser, exportStudent, exportParent,
			exportTeacher, exportOther, defaultRoles, authorizedSchools);
	}

	public static org.json.JSONObject editApplication(
		long applicationId, java.lang.String applicationName,
		java.lang.String applicationKey, java.lang.String category,
		long menuEntryId, java.lang.String image, boolean hasCustomUrl,
		java.lang.String globalUrl, boolean exportUser, boolean exportStudent,
		boolean exportParent, boolean exportTeacher, boolean exportOther,
		java.lang.String defaultRoles, java.lang.String authorizedSchools) {

		return getService().editApplication(
			applicationId, applicationName, applicationKey, category,
			menuEntryId, image, hasCustomUrl, globalUrl, exportUser,
			exportStudent, exportParent, exportTeacher, exportOther,
			defaultRoles, authorizedSchools);
	}

	public static org.json.JSONObject export(
		long applicationId, long schoolId, java.lang.String roleName) {

		return getService().export(applicationId, schoolId, roleName);
	}

	public static org.json.JSONObject getAllApplications() {
		return getService().getAllApplications();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getPortlets() {
		return getService().getPortlets();
	}

	public static org.json.JSONObject getSchoolApplications(long schoolId) {
		return getService().getSchoolApplications(schoolId);
	}

	public static org.json.JSONObject getStatApplications(long schoolId) {
		return getService().getStatApplications(schoolId);
	}

	public static org.json.JSONObject getUserApplications() {
		return getService().getUserApplications();
	}

	public static org.json.JSONObject removeApplication(long applicationId) {
		return getService().removeApplication(applicationId);
	}

	public static ApplicationService getService() {
		return _service;
	}

	private static volatile ApplicationService _service;

}