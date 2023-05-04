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

package com.weprode.nero.contact.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContactCompletionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactCompletionLocalService
 * @generated
 */
public class ContactCompletionLocalServiceWrapper
	implements ContactCompletionLocalService,
			   ServiceWrapper<ContactCompletionLocalService> {

	public ContactCompletionLocalServiceWrapper(
		ContactCompletionLocalService contactCompletionLocalService) {

		_contactCompletionLocalService = contactCompletionLocalService;
	}

	@Override
	public org.json.JSONObject getCompletionResultAsJSON(
		String query, com.liferay.portal.kernel.model.User user,
		boolean includeLists) {

		return _contactCompletionLocalService.getCompletionResultAsJSON(
			query, user, includeLists);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactCompletionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getSchoolContacts(
			String search, com.liferay.portal.kernel.model.User user,
			com.liferay.portal.kernel.model.Organization school, int start,
			int limit,
			com.liferay.portal.kernel.util.OrderByComparator comparator) {

		return _contactCompletionLocalService.getSchoolContacts(
			search, user, school, start, limit, comparator);
	}

	@Override
	public void removeCompletionResultsFromCache(long userId) {
		_contactCompletionLocalService.removeCompletionResultsFromCache(userId);
	}

	@Override
	public ContactCompletionLocalService getWrappedService() {
		return _contactCompletionLocalService;
	}

	@Override
	public void setWrappedService(
		ContactCompletionLocalService contactCompletionLocalService) {

		_contactCompletionLocalService = contactCompletionLocalService;
	}

	private ContactCompletionLocalService _contactCompletionLocalService;

}