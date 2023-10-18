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
 * Provides a wrapper for {@link UserSearchLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserSearchLocalService
 * @generated
 */
public class UserSearchLocalServiceWrapper
	implements ServiceWrapper<UserSearchLocalService>, UserSearchLocalService {

	public UserSearchLocalServiceWrapper() {
		this(null);
	}

	public UserSearchLocalServiceWrapper(
		UserSearchLocalService userSearchLocalService) {

		_userSearchLocalService = userSearchLocalService;
	}

	@Override
	public Long countUsers(
			String query, java.util.List<Long> organizationIds,
			java.util.List<Long> groupIds, java.util.List<Long> roleIds,
			java.util.List<Long> subjectIds)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userSearchLocalService.countUsers(
			query, organizationIds, groupIds, roleIds, subjectIds);
	}

	@Override
	public Long countUsers(
			String query, java.util.List<Long> organizationIds,
			java.util.List<Long> groupIds, java.util.List<Long> roleIds,
			java.util.List<Long> subjectIds, boolean localUsersOnly)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userSearchLocalService.countUsers(
			query, organizationIds, groupIds, roleIds, subjectIds,
			localUsersOnly);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userSearchLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> searchUsers(
			String query, java.util.List<Long> organizationIds,
			java.util.List<Long> groupIds, java.util.List<Long> roleIds,
			java.util.List<Long> subjectIds, boolean localUsersOnly, int start,
			int stop, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userSearchLocalService.searchUsers(
			query, organizationIds, groupIds, roleIds, subjectIds,
			localUsersOnly, start, stop, obc);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User> searchUsers(
			String query, java.util.List<Long> organizationIds,
			java.util.List<Long> groupIds, java.util.List<Long> roleIds,
			java.util.List<Long> subjectIds, int start, int stop,
			com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _userSearchLocalService.searchUsers(
			query, organizationIds, groupIds, roleIds, subjectIds, start, stop,
			obc);
	}

	@Override
	public UserSearchLocalService getWrappedService() {
		return _userSearchLocalService;
	}

	@Override
	public void setWrappedService(
		UserSearchLocalService userSearchLocalService) {

		_userSearchLocalService = userSearchLocalService;
	}

	private UserSearchLocalService _userSearchLocalService;

}