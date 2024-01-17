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

package com.weprode.facile.contact.service;

/**
 * Provides the remote service utility for Contact. This utility wraps
 * <code>com.weprode.facile.contact.service.impl.ContactServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ContactService
 * @generated
 */
public class ContactServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.contact.service.impl.ContactServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject getContactTree() {
		return getService().getContactTree();
	}

	public static org.json.JSONObject getMyRelatives() {
		return getService().getMyRelatives();
	}

	public static org.json.JSONObject getMyStudents() {
		return getService().getMyStudents();
	}

	public static org.json.JSONObject getOrgMembers(long orgId, long roleId) {
		return getService().getOrgMembers(orgId, roleId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getUserCard(long contactUserId) {
		return getService().getUserCard(contactUserId);
	}

	public static org.json.JSONObject searchDirectory(
		java.lang.String query, long roleId, long schoolId) {

		return getService().searchDirectory(query, roleId, schoolId);
	}

	public static ContactService getService() {
		return _service;
	}

	private static volatile ContactService _service;

}