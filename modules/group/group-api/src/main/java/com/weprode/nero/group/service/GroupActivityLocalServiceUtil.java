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

import java.util.List;

/**
 * Provides the local service utility for GroupActivity. This utility wraps
 * <code>com.weprode.nero.group.service.impl.GroupActivityLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see GroupActivityLocalService
 * @generated
 */
public class GroupActivityLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.group.service.impl.GroupActivityLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject convertGroupActivity(
		long userId, com.weprode.nero.group.model.GroupActivity groupActivity) {

		return getService().convertGroupActivity(userId, groupActivity);
	}

	public static List<com.weprode.nero.group.model.GroupActivity>
		getDashboardGroupsActivities(
			long userId, List<Long> groupIds, java.util.Date maxDate,
			int nbResults, boolean withNews, boolean withDocs,
			boolean withMemberships, boolean withSchoollife,
			boolean withSessions) {

		return getService().getDashboardGroupsActivities(
			userId, groupIds, maxDate, nbResults, withNews, withDocs,
			withMemberships, withSchoollife, withSessions);
	}

	public static List<com.weprode.nero.group.model.GroupActivity>
		getDocumentGroupActivities(
			long userId, long groupId, java.util.Date maxDate, int nbResults) {

		return getService().getDocumentGroupActivities(
			userId, groupId, maxDate, nbResults);
	}

	public static List<com.weprode.nero.group.model.GroupActivity>
		getFullGroupActivities(
			long userId, long groupId, java.util.Date maxDate, int nbResults) {

		return getService().getFullGroupActivities(
			userId, groupId, maxDate, nbResults);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static GroupActivityLocalService getService() {
		return _service;
	}

	private static volatile GroupActivityLocalService _service;

}