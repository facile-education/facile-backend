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

package com.weprode.nero.document.service;

/**
 * Provides the remote service utility for Groups. This utility wraps
 * <code>com.weprode.nero.document.service.impl.GroupsServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see GroupsService
 * @generated
 */
public class GroupsServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.GroupsServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject getGroupBreadcrumb(
		java.lang.String nodePath) {

		return getService().getGroupBreadcrumb(nodePath);
	}

	public static com.liferay.portal.kernel.json.JSONObject getGroupEntities(
		java.lang.String nodePath) {

		return getService().getGroupEntities(nodePath);
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
		recordDownloadActivity(long fileEntryId, long versionId) {

		return getService().recordDownloadActivity(fileEntryId, versionId);
	}

	public static com.liferay.portal.kernel.json.JSONObject recordViewActivity(
		long fileEntryId, long versionId) {

		return getService().recordViewActivity(fileEntryId, versionId);
	}

	public static GroupsService getService() {
		return _service;
	}

	private static volatile GroupsService _service;

}