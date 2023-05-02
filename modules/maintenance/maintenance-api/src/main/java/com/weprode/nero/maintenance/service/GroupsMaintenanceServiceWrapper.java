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

package com.weprode.nero.maintenance.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GroupsMaintenanceService}.
 *
 * @author Brian Wing Shun Chan
 * @see GroupsMaintenanceService
 * @generated
 */
public class GroupsMaintenanceServiceWrapper
	implements GroupsMaintenanceService,
			   ServiceWrapper<GroupsMaintenanceService> {

	public GroupsMaintenanceServiceWrapper(
		GroupsMaintenanceService groupsMaintenanceService) {

		_groupsMaintenanceService = groupsMaintenanceService;
	}

	@Override
	public org.json.JSONObject archiveGroups() {
		return _groupsMaintenanceService.archiveGroups();
	}

	@Override
	public org.json.JSONObject deleteGroup(long groupId) {
		return _groupsMaintenanceService.deleteGroup(groupId);
	}

	@Override
	public org.json.JSONObject deleteGroups(java.io.File file) {
		return _groupsMaintenanceService.deleteGroups(file);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _groupsMaintenanceService.getOSGiServiceIdentifier();
	}

	@Override
	public GroupsMaintenanceService getWrappedService() {
		return _groupsMaintenanceService;
	}

	@Override
	public void setWrappedService(
		GroupsMaintenanceService groupsMaintenanceService) {

		_groupsMaintenanceService = groupsMaintenanceService;
	}

	private GroupsMaintenanceService _groupsMaintenanceService;

}