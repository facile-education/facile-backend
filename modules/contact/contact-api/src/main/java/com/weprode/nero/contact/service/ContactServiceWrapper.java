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
 * Provides a wrapper for {@link ContactService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactService
 * @generated
 */
public class ContactServiceWrapper
	implements ContactService, ServiceWrapper<ContactService> {

	public ContactServiceWrapper(ContactService contactService) {
		_contactService = contactService;
	}

	@Override
	public org.json.JSONObject getContactDetails(long contactUserId) {
		return _contactService.getContactDetails(contactUserId);
	}

	@Override
	public org.json.JSONObject getContactTree() {
		return _contactService.getContactTree();
	}

	@Override
	public org.json.JSONObject getMyRelatives() {
		return _contactService.getMyRelatives();
	}

	@Override
	public org.json.JSONObject getMyStudents() {
		return _contactService.getMyStudents();
	}

	@Override
	public org.json.JSONObject getOrgMembers(long orgId, long roleId) {
		return _contactService.getOrgMembers(orgId, roleId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject searchDirectory(
		String query, long roleId, long schoolId) {

		return _contactService.searchDirectory(query, roleId, schoolId);
	}

	@Override
	public ContactService getWrappedService() {
		return _contactService;
	}

	@Override
	public void setWrappedService(ContactService contactService) {
		_contactService = contactService;
	}

	private ContactService _contactService;

}