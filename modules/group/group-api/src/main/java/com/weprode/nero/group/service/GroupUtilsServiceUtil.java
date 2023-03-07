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

/**
 * Provides the remote service utility for GroupUtils. This utility wraps
 * <code>com.weprode.nero.group.service.impl.GroupUtilsServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see GroupUtilsService
 * @generated
 */
public class GroupUtilsServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.group.service.impl.GroupUtilsServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject getGroupActivity(
		long groupId, java.lang.String maxDate, int nbResults) {

		return getService().getGroupActivity(groupId, maxDate, nbResults);
	}

	public static com.liferay.portal.kernel.json.JSONObject getGroupHistory(
		long groupId, java.lang.String maxDate, int nbResults) {

		return getService().getGroupHistory(groupId, maxDate, nbResults);
	}

	public static com.liferay.portal.kernel.json.JSONObject getGroupMembers(
		long groupId) {

		return getService().getGroupMembers(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.json.JSONObject
		getSpecificGroupActivities(
			long groupId, java.lang.String maxDate, int nbResults,
			boolean allHistory, boolean containNews, boolean containDocs,
			boolean containMembership, boolean containPendingFirings,
			boolean containFirings, boolean containHomework,
			boolean containSessions) {

		return getService().getSpecificGroupActivities(
			groupId, maxDate, nbResults, allHistory, containNews, containDocs,
			containMembership, containPendingFirings, containFirings,
			containHomework, containSessions);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		getUserCollaborativeGroups(
			java.lang.String filter, boolean allCommunities, boolean allClasses,
			boolean allCours) {

		return getService().getUserCollaborativeGroups(
			filter, allCommunities, allClasses, allCours);
	}

	public static com.liferay.portal.kernel.json.JSONObject getUserGroups(
		long schoolId, boolean includeInstitutional, boolean includeCommunities,
		boolean pedagogicalOnly) {

		return getService().getUserGroups(
			schoolId, includeInstitutional, includeCommunities,
			pedagogicalOnly);
	}

	public static com.liferay.portal.kernel.json.JSONObject getUsersCompletion(
		java.lang.String query, long schoolId, long roleId) {

		return getService().getUsersCompletion(query, schoolId, roleId);
	}

	public static GroupUtilsService getService() {
		return _service;
	}

	private static volatile GroupUtilsService _service;

}