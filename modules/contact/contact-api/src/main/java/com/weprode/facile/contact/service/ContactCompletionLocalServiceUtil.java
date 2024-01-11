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

import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the local service utility for ContactCompletion. This utility wraps
 * <code>com.weprode.facile.contact.service.impl.ContactCompletionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContactCompletionLocalService
 * @generated
 */
public class ContactCompletionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.contact.service.impl.ContactCompletionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject getCompletionResultAsJSON(
		String query, com.liferay.portal.kernel.model.User user,
		boolean includeLists) {

		return getService().getCompletionResultAsJSON(
			query, user, includeLists);
	}

	public static org.json.JSONArray getContactsForMessaging(
		com.liferay.portal.kernel.model.User user) {

		return getService().getContactsForMessaging(user);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<com.liferay.portal.kernel.model.User> getSchoolContacts(
		String search, com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.model.Organization school, int start,
		int limit, OrderByComparator comparator) {

		return getService().getSchoolContacts(
			search, user, school, start, limit, comparator);
	}

	public static void removeCompletionResultsFromCache(long userId) {
		getService().removeCompletionResultsFromCache(userId);
	}

	public static ContactCompletionLocalService getService() {
		return _service;
	}

	private static volatile ContactCompletionLocalService _service;

}