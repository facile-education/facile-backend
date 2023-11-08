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

package com.weprode.facile.group.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GroupActivityLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see GroupActivityLocalService
 * @generated
 */
public class GroupActivityLocalServiceWrapper
	implements GroupActivityLocalService,
			   ServiceWrapper<GroupActivityLocalService> {

	public GroupActivityLocalServiceWrapper() {
		this(null);
	}

	public GroupActivityLocalServiceWrapper(
		GroupActivityLocalService groupActivityLocalService) {

		_groupActivityLocalService = groupActivityLocalService;
	}

	@Override
	public org.json.JSONObject convertGroupActivity(
		long userId,
		com.weprode.facile.group.model.GroupActivity groupActivity) {

		return _groupActivityLocalService.convertGroupActivity(
			userId, groupActivity);
	}

	@Override
	public Long getAuthorId(
		com.weprode.facile.group.model.GroupActivity groupActivity) {

		return _groupActivityLocalService.getAuthorId(groupActivity);
	}

	@Override
	public java.util.List<com.weprode.facile.group.model.GroupActivity>
		getDashboardGroupsActivities(
			long userId, java.util.List<Long> groupIds, java.util.Date maxDate,
			int nbResults, boolean withNews, boolean withDocs,
			boolean withMemberships, boolean withSchoollife,
			boolean withSessions) {

		return _groupActivityLocalService.getDashboardGroupsActivities(
			userId, groupIds, maxDate, nbResults, withNews, withDocs,
			withMemberships, withSchoollife, withSessions);
	}

	@Override
	public java.util.List<com.weprode.facile.group.model.GroupActivity>
		getDocumentGroupActivities(
			long userId, long groupId, java.util.Date maxDate, int nbResults) {

		return _groupActivityLocalService.getDocumentGroupActivities(
			userId, groupId, maxDate, nbResults);
	}

	@Override
	public java.util.List<com.weprode.facile.group.model.GroupActivity>
		getFullGroupActivities(
			long userId, long groupId, java.util.Date maxDate, int nbResults) {

		return _groupActivityLocalService.getFullGroupActivities(
			userId, groupId, maxDate, nbResults);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _groupActivityLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public GroupActivityLocalService getWrappedService() {
		return _groupActivityLocalService;
	}

	@Override
	public void setWrappedService(
		GroupActivityLocalService groupActivityLocalService) {

		_groupActivityLocalService = groupActivityLocalService;
	}

	private GroupActivityLocalService _groupActivityLocalService;

}