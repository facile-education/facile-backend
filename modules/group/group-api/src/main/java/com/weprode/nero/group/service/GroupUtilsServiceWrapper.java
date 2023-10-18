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
 * Provides a wrapper for {@link GroupUtilsService}.
 *
 * @author Brian Wing Shun Chan
 * @see GroupUtilsService
 * @generated
 */
public class GroupUtilsServiceWrapper
	implements GroupUtilsService, ServiceWrapper<GroupUtilsService> {

	public GroupUtilsServiceWrapper() {
		this(null);
	}

	public GroupUtilsServiceWrapper(GroupUtilsService groupUtilsService) {
		_groupUtilsService = groupUtilsService;
	}

	@Override
	public org.json.JSONObject getGroupActivity(
		long groupId, String maxDate, int nbResults) {

		return _groupUtilsService.getGroupActivity(groupId, maxDate, nbResults);
	}

	@Override
	public org.json.JSONObject getGroupMembers(long groupId) {
		return _groupUtilsService.getGroupMembers(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _groupUtilsService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getUserCollaborativeGroups(
		String filter, boolean allCommunities, boolean allClasses,
		boolean allCours) {

		return _groupUtilsService.getUserCollaborativeGroups(
			filter, allCommunities, allClasses, allCours);
	}

	@Override
	public org.json.JSONObject getUserGroups(
		long schoolId, boolean includeInstitutional, boolean includeCommunities,
		boolean pedagogicalOnly) {

		return _groupUtilsService.getUserGroups(
			schoolId, includeInstitutional, includeCommunities,
			pedagogicalOnly);
	}

	@Override
	public GroupUtilsService getWrappedService() {
		return _groupUtilsService;
	}

	@Override
	public void setWrappedService(GroupUtilsService groupUtilsService) {
		_groupUtilsService = groupUtilsService;
	}

	private GroupUtilsService _groupUtilsService;

}