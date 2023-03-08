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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * Provides the local service utility for SchoolAdmin. This utility wraps
 * <code>com.weprode.nero.user.service.impl.SchoolAdminLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SchoolAdminLocalService
 * @generated
 */
public class SchoolAdminLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.user.service.impl.SchoolAdminLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addSchoolAdmin(long schoolId, long userId)
		throws PortalException, SystemException {

		getService().addSchoolAdmin(schoolId, userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<com.liferay.portal.kernel.model.User> getSchoolAdmins(
		long schoolId) {

		return getService().getSchoolAdmins(schoolId);
	}

	public static void removeSchoolAdmin(long schoolId, long userId)
		throws PortalException, SystemException {

		getService().removeSchoolAdmin(schoolId, userId);
	}

	public static SchoolAdminLocalService getService() {
		return _service;
	}

	private static volatile SchoolAdminLocalService _service;

}