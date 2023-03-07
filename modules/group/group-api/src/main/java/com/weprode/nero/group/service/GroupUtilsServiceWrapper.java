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

	public GroupUtilsServiceWrapper(GroupUtilsService groupUtilsService) {
		_groupUtilsService = groupUtilsService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getGroupActivity(
		long groupId, String maxDate, int nbResults) {

		return _groupUtilsService.getGroupActivity(groupId, maxDate, nbResults);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getGroupHistory(
		long groupId, String maxDate, int nbResults) {

		return _groupUtilsService.getGroupHistory(groupId, maxDate, nbResults);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getGroupMembers(
		long groupId) {

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
	public com.liferay.portal.kernel.json.JSONObject getSpecificGroupActivities(
		long groupId, String maxDate, int nbResults, boolean allHistory,
		boolean containNews, boolean containDocs, boolean containMembership,
		boolean containPendingFirings, boolean containFirings,
		boolean containHomework, boolean containSessions) {

		return _groupUtilsService.getSpecificGroupActivities(
			groupId, maxDate, nbResults, allHistory, containNews, containDocs,
			containMembership, containPendingFirings, containFirings,
			containHomework, containSessions);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getUserCollaborativeGroups(
		String filter, boolean allCommunities, boolean allClasses,
		boolean allCours) {

		return _groupUtilsService.getUserCollaborativeGroups(
			filter, allCommunities, allClasses, allCours);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getUserGroups(
		long schoolId, boolean includeInstitutional, boolean includeCommunities,
		boolean pedagogicalOnly) {

		return _groupUtilsService.getUserGroups(
			schoolId, includeInstitutional, includeCommunities,
			pedagogicalOnly);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getUsersCompletion(
		String query, long schoolId, long roleId) {

		return _groupUtilsService.getUsersCompletion(query, schoolId, roleId);
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