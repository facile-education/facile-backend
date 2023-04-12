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

import com.liferay.portal.kernel.exception.SystemException;

/**
 * Provides the remote service utility for FolderUtils. This utility wraps
 * <code>com.weprode.nero.document.service.impl.FolderUtilsServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see FolderUtilsService
 * @generated
 */
public class FolderUtilsServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.FolderUtilsServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject createFolder(
			long targetFolderId, String folderName)
		throws SystemException {

		return getService().createFolder(targetFolderId, folderName);
	}

	public static com.liferay.portal.kernel.json.JSONObject downloadFolder(
		long folderId) {

		return getService().downloadFolder(folderId);
	}

	public static com.liferay.portal.kernel.json.JSONObject getAllEntities(
		long folderId, boolean withDetails) {

		return getService().getAllEntities(folderId, withDetails);
	}

	public static com.liferay.portal.kernel.json.JSONObject getBreadcrumb(
		long folderId) {

		return getService().getBreadcrumb(folderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.json.JSONObject renameFolder(
		long folderId, String folderName) {

		return getService().renameFolder(folderId, folderName);
	}

	public static FolderUtilsService getService() {
		return _service;
	}

	private static volatile FolderUtilsService _service;

}