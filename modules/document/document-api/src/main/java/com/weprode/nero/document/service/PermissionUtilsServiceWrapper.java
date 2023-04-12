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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PermissionUtilsService}.
 *
 * @author Brian Wing Shun Chan
 * @see PermissionUtilsService
 * @generated
 */
public class PermissionUtilsServiceWrapper
	implements PermissionUtilsService, ServiceWrapper<PermissionUtilsService> {

	public PermissionUtilsServiceWrapper(
		PermissionUtilsService permissionUtilsService) {

		_permissionUtilsService = permissionUtilsService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getFilePermissionMatrix(
		long fileEntryId) {

		return _permissionUtilsService.getFilePermissionMatrix(fileEntryId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getFolderPermissionMatrix(
		long folderId) {

		return _permissionUtilsService.getFolderPermissionMatrix(folderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _permissionUtilsService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject saveFilePermissionMatrix(
		long fileEntryId, String jsonPermissionMatrix) {

		return _permissionUtilsService.saveFilePermissionMatrix(
			fileEntryId, jsonPermissionMatrix);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject saveFolderPermissionMatrix(
		long folderId, String jsonPermissionMatrix, boolean isRecursive) {

		return _permissionUtilsService.saveFolderPermissionMatrix(
			folderId, jsonPermissionMatrix, isRecursive);
	}

	@Override
	public PermissionUtilsService getWrappedService() {
		return _permissionUtilsService;
	}

	@Override
	public void setWrappedService(
		PermissionUtilsService permissionUtilsService) {

		_permissionUtilsService = permissionUtilsService;
	}

	private PermissionUtilsService _permissionUtilsService;

}