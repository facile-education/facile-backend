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

package com.weprode.facile.document.service;

/**
 * Provides the remote service utility for PermissionUtils. This utility wraps
 * <code>com.weprode.facile.document.service.impl.PermissionUtilsServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see PermissionUtilsService
 * @generated
 */
public class PermissionUtilsServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.document.service.impl.PermissionUtilsServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject getFilePermissionMatrix(
		long fileEntryId) {

		return getService().getFilePermissionMatrix(fileEntryId);
	}

	public static org.json.JSONObject getFolderPermissionMatrix(long folderId) {
		return getService().getFolderPermissionMatrix(folderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject saveFilePermissionMatrix(
		long fileEntryId, java.lang.String jsonPermissionMatrix) {

		return getService().saveFilePermissionMatrix(
			fileEntryId, jsonPermissionMatrix);
	}

	public static org.json.JSONObject saveFolderPermissionMatrix(
		long folderId, java.lang.String jsonPermissionMatrix,
		boolean isRecursive) {

		return getService().saveFolderPermissionMatrix(
			folderId, jsonPermissionMatrix, isRecursive);
	}

	public static PermissionUtilsService getService() {
		return _service;
	}

	private static volatile PermissionUtilsService _service;

}