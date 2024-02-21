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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the local service utility for Contact. This utility wraps
 * <code>com.weprode.facile.contact.service.impl.ContactLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContactLocalService
 * @generated
 */
public class ContactLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.contact.service.impl.ContactLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject convertUserToJson(
		com.liferay.portal.kernel.model.User user) {

		return getService().convertUserToJson(user);
	}

	public static List<com.liferay.portal.kernel.model.User> directorySearch(
		com.liferay.portal.kernel.model.User user, String query,
		List<Long> schoolIds, List<Long> roleIds, int start, int limit,
		OrderByComparator<com.liferay.portal.kernel.model.User> obc) {

		return getService().directorySearch(
			user, query, schoolIds, roleIds, start, limit, obc);
	}

	public static List<com.liferay.portal.kernel.model.User>
		getAllGroupsContacts(
			com.liferay.portal.kernel.model.User user, String search, int start,
			int limit,
			OrderByComparator<com.liferay.portal.kernel.model.User>
				comparator) {

		return getService().getAllGroupsContacts(
			user, search, start, limit, comparator);
	}

	public static org.json.JSONArray getContactTree(
		com.liferay.portal.kernel.model.User user) {

		return getService().getContactTree(user);
	}

	public static List<com.liferay.portal.kernel.model.User> getListMembers(
		com.liferay.portal.kernel.model.User currentUser, long roleId,
		long orgId) {

		return getService().getListMembers(currentUser, roleId, orgId);
	}

	public static List<com.liferay.portal.kernel.model.User> getMyRelatives(
		com.liferay.portal.kernel.model.User user) {

		return getService().getMyRelatives(user);
	}

	public static List<com.liferay.portal.kernel.model.User> getMyStudents(
		com.liferay.portal.kernel.model.User user) {

		return getService().getMyStudents(user);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static String getPopulationName(
		long orgId, long roleId, long userId) {

		return getService().getPopulationName(orgId, roleId, userId);
	}

	public static List<Long> getRecipients(
			org.json.JSONArray recipients,
			com.liferay.portal.kernel.model.User user)
		throws SystemException {

		return getService().getRecipients(recipients, user);
	}

	public static org.json.JSONObject getUserCard(
		com.liferay.portal.kernel.model.User currentUser, long contactUserId) {

		return getService().getUserCard(currentUser, contactUserId);
	}

	public static boolean isAllowedToContact(long userId, long contactId) {
		return getService().isAllowedToContact(userId, contactId);
	}

	public static ContactLocalService getService() {
		return _service;
	}

	private static volatile ContactLocalService _service;

}