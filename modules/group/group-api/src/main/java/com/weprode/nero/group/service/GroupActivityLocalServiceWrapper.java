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
 * Provides a wrapper for {@link GroupActivityLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see GroupActivityLocalService
 * @generated
 */
public class GroupActivityLocalServiceWrapper
	implements GroupActivityLocalService,
			   ServiceWrapper<GroupActivityLocalService> {

	public GroupActivityLocalServiceWrapper(
		GroupActivityLocalService groupActivityLocalService) {

		_groupActivityLocalService = groupActivityLocalService;
	}

	@Override
	public org.json.JSONObject convertGroupActivity(
		long userId, com.weprode.nero.group.model.GroupActivity groupActivity) {

		return _groupActivityLocalService.convertGroupActivity(
			userId, groupActivity);
	}

	@Override
	public java.util.List<com.weprode.nero.group.model.GroupActivity>
		getGroupsActivities(
			long userId, java.util.List<Long> groupIds, java.util.Date maxDate,
			int nbResults) {

		return _groupActivityLocalService.getGroupsActivities(
			userId, groupIds, maxDate, nbResults);
	}

	@Override
	public java.util.List<com.weprode.nero.group.model.GroupActivity>
		getGroupsActivities(
			long userId, java.util.List<Long> groupIds, java.util.Date maxDate,
			int nbResults, boolean allHistory, boolean containNews,
			boolean containDocs, boolean containMembership,
			boolean containPendingFirings, boolean containFirings,
			boolean containHomework, boolean containsSessions) {

		return _groupActivityLocalService.getGroupsActivities(
			userId, groupIds, maxDate, nbResults, allHistory, containNews,
			containDocs, containMembership, containPendingFirings,
			containFirings, containHomework, containsSessions);
	}

	@Override
	public java.util.List<com.weprode.nero.group.model.GroupActivity>
		getGroupsHistory(
			long userId, java.util.List<Long> groupIds, java.util.Date maxDate,
			int nbResults) {

		return _groupActivityLocalService.getGroupsHistory(
			userId, groupIds, maxDate, nbResults);
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