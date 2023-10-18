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

package com.weprode.nero.user.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SchoolAdminLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SchoolAdminLocalService
 * @generated
 */
public class SchoolAdminLocalServiceWrapper
	implements SchoolAdminLocalService,
			   ServiceWrapper<SchoolAdminLocalService> {

	public SchoolAdminLocalServiceWrapper() {
		this(null);
	}

	public SchoolAdminLocalServiceWrapper(
		SchoolAdminLocalService schoolAdminLocalService) {

		_schoolAdminLocalService = schoolAdminLocalService;
	}

	@Override
	public void addSchoolAdmin(long schoolId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_schoolAdminLocalService.addSchoolAdmin(schoolId, userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _schoolAdminLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> getSchoolAdmins(
		long schoolId) {

		return _schoolAdminLocalService.getSchoolAdmins(schoolId);
	}

	@Override
	public void removeSchoolAdmin(long schoolId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_schoolAdminLocalService.removeSchoolAdmin(schoolId, userId);
	}

	@Override
	public SchoolAdminLocalService getWrappedService() {
		return _schoolAdminLocalService;
	}

	@Override
	public void setWrappedService(
		SchoolAdminLocalService schoolAdminLocalService) {

		_schoolAdminLocalService = schoolAdminLocalService;
	}

	private SchoolAdminLocalService _schoolAdminLocalService;

}