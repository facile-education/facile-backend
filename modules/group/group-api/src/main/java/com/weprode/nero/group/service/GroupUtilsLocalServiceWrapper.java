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

package com.weprode.nero.group.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GroupUtilsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see GroupUtilsLocalService
 * @generated
 */
public class GroupUtilsLocalServiceWrapper
	implements GroupUtilsLocalService, ServiceWrapper<GroupUtilsLocalService> {

	public GroupUtilsLocalServiceWrapper(
		GroupUtilsLocalService groupUtilsLocalService) {

		_groupUtilsLocalService = groupUtilsLocalService;
	}

	@Override
	public String getGroupName(long groupId) {
		return _groupUtilsLocalService.getGroupName(groupId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group>
		getGroupsFromOrganizations(
			java.util.List<com.liferay.portal.kernel.model.Organization>
				orgsToSearchFor) {

		return _groupUtilsLocalService.getGroupsFromOrganizations(
			orgsToSearchFor);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _groupUtilsLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * Delete a whole group and its associated objects
	 */
	@Override
	public void groupCleanup(Long groupId) {
		_groupUtilsLocalService.groupCleanup(groupId);
	}

	@Override
	public GroupUtilsLocalService getWrappedService() {
		return _groupUtilsLocalService;
	}

	@Override
	public void setWrappedService(
		GroupUtilsLocalService groupUtilsLocalService) {

		_groupUtilsLocalService = groupUtilsLocalService;
	}

	private GroupUtilsLocalService _groupUtilsLocalService;

}