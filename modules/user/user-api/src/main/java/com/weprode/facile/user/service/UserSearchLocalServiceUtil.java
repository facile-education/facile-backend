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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the local service utility for UserSearch. This utility wraps
 * <code>com.weprode.facile.user.service.impl.UserSearchLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserSearchLocalService
 * @generated
 */
public class UserSearchLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.user.service.impl.UserSearchLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Long countUsers(
			String query, List<Long> organizationIds, List<Long> groupIds,
			List<Long> roleIds, List<Long> subjectIds)
		throws SystemException {

		return getService().countUsers(
			query, organizationIds, groupIds, roleIds, subjectIds);
	}

	public static Long countUsers(
			String query, List<Long> organizationIds, List<Long> groupIds,
			List<Long> roleIds, List<Long> subjectIds, boolean localUsersOnly)
		throws SystemException {

		return getService().countUsers(
			query, organizationIds, groupIds, roleIds, subjectIds,
			localUsersOnly);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<com.liferay.portal.kernel.model.User> searchUsers(
			String query, List<Long> organizationIds, List<Long> groupIds,
			List<Long> roleIds, List<Long> subjectIds, boolean localUsersOnly,
			int start, int stop, OrderByComparator obc)
		throws SystemException {

		return getService().searchUsers(
			query, organizationIds, groupIds, roleIds, subjectIds,
			localUsersOnly, start, stop, obc);
	}

	public static List<com.liferay.portal.kernel.model.User> searchUsers(
			String query, List<Long> organizationIds, List<Long> groupIds,
			List<Long> roleIds, List<Long> subjectIds, int start, int stop,
			OrderByComparator obc)
		throws SystemException {

		return getService().searchUsers(
			query, organizationIds, groupIds, roleIds, subjectIds, start, stop,
			obc);
	}

	public static UserSearchLocalService getService() {
		return _service;
	}

	private static volatile UserSearchLocalService _service;

}