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

package com.weprode.facile.user.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SchoolAdminService}.
 *
 * @author Brian Wing Shun Chan
 * @see SchoolAdminService
 * @generated
 */
public class SchoolAdminServiceWrapper
	implements SchoolAdminService, ServiceWrapper<SchoolAdminService> {

	public SchoolAdminServiceWrapper() {
		this(null);
	}

	public SchoolAdminServiceWrapper(SchoolAdminService schoolAdminService) {
		_schoolAdminService = schoolAdminService;
	}

	@Override
	public org.json.JSONObject addSchoolAdmin(long userId, long schoolId) {
		return _schoolAdminService.addSchoolAdmin(userId, schoolId);
	}

	@Override
	public org.json.JSONObject getDelegationCandidates(
		long schoolId, String filter) {

		return _schoolAdminService.getDelegationCandidates(schoolId, filter);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _schoolAdminService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSchoolDelegates(long schoolId) {
		return _schoolAdminService.getSchoolDelegates(schoolId);
	}

	@Override
	public org.json.JSONObject removeSchoolAdmin(long userId, long schoolId) {
		return _schoolAdminService.removeSchoolAdmin(userId, schoolId);
	}

	@Override
	public SchoolAdminService getWrappedService() {
		return _schoolAdminService;
	}

	@Override
	public void setWrappedService(SchoolAdminService schoolAdminService) {
		_schoolAdminService = schoolAdminService;
	}

	private SchoolAdminService _schoolAdminService;

}