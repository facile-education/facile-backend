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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContactLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactLocalService
 * @generated
 */
public class ContactLocalServiceWrapper
	implements ContactLocalService, ServiceWrapper<ContactLocalService> {

	public ContactLocalServiceWrapper() {
		this(null);
	}

	public ContactLocalServiceWrapper(ContactLocalService contactLocalService) {
		_contactLocalService = contactLocalService;
	}

	@Override
	public org.json.JSONObject convertUserToJson(
		com.liferay.portal.kernel.model.User user) {

		return _contactLocalService.convertUserToJson(user);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> directorySearch(
		com.liferay.portal.kernel.model.User user, String query,
		java.util.List<Long> schoolIds, java.util.List<Long> roleIds, int start,
		int limit, com.liferay.portal.kernel.util.OrderByComparator obc) {

		return _contactLocalService.directorySearch(
			user, query, schoolIds, roleIds, start, limit, obc);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
		getAllGroupsContacts(
			com.liferay.portal.kernel.model.User user, String search, int start,
			int limit,
			com.liferay.portal.kernel.util.OrderByComparator comparator) {

		return _contactLocalService.getAllGroupsContacts(
			user, search, start, limit, comparator);
	}

	@Override
	public org.json.JSONObject getContactDetails(
		com.liferay.portal.kernel.model.User currentUser, long contactUserId) {

		return _contactLocalService.getContactDetails(
			currentUser, contactUserId);
	}

	@Override
	public org.json.JSONArray getContactTree(
		com.liferay.portal.kernel.model.User user) {

		return _contactLocalService.getContactTree(user);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getListMembers(
		com.liferay.portal.kernel.model.User currentUser, long roleId,
		long orgId) {

		return _contactLocalService.getListMembers(currentUser, roleId, orgId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getMyRelatives(
		com.liferay.portal.kernel.model.User user) {

		return _contactLocalService.getMyRelatives(user);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getMyStudents(
		com.liferay.portal.kernel.model.User user) {

		return _contactLocalService.getMyStudents(user);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public String getPopulationName(long orgId, long roleId, long userId) {
		return _contactLocalService.getPopulationName(orgId, roleId, userId);
	}

	@Override
	public java.util.List<Long> getRecipients(
			org.json.JSONArray recipients,
			com.liferay.portal.kernel.model.User user)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _contactLocalService.getRecipients(recipients, user);
	}

	@Override
	public ContactLocalService getWrappedService() {
		return _contactLocalService;
	}

	@Override
	public void setWrappedService(ContactLocalService contactLocalService) {
		_contactLocalService = contactLocalService;
	}

	private ContactLocalService _contactLocalService;

}