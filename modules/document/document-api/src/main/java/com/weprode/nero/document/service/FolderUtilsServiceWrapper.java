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
 * Provides a wrapper for {@link FolderUtilsService}.
 *
 * @author Brian Wing Shun Chan
 * @see FolderUtilsService
 * @generated
 */
public class FolderUtilsServiceWrapper
	implements FolderUtilsService, ServiceWrapper<FolderUtilsService> {

	public FolderUtilsServiceWrapper() {
		this(null);
	}

	public FolderUtilsServiceWrapper(FolderUtilsService folderUtilsService) {
		_folderUtilsService = folderUtilsService;
	}

	@Override
	public org.json.JSONObject createFolder(
			long targetFolderId, String folderName)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsService.createFolder(targetFolderId, folderName);
	}

	@Override
	public org.json.JSONObject downloadFolder(long folderId) {
		return _folderUtilsService.downloadFolder(folderId);
	}

	@Override
	public org.json.JSONObject getAllEntities(
		long folderId, boolean withDetails) {

		return _folderUtilsService.getAllEntities(folderId, withDetails);
	}

	@Override
	public org.json.JSONObject getBreadcrumb(long folderId) {
		return _folderUtilsService.getBreadcrumb(folderId);
	}

	@Override
	public org.json.JSONObject getImagesEntities(
		long folderId, boolean withDetails) {

		return _folderUtilsService.getImagesEntities(folderId, withDetails);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _folderUtilsService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject renameFolder(long folderId, String folderName) {
		return _folderUtilsService.renameFolder(folderId, folderName);
	}

	@Override
	public FolderUtilsService getWrappedService() {
		return _folderUtilsService;
	}

	@Override
	public void setWrappedService(FolderUtilsService folderUtilsService) {
		_folderUtilsService = folderUtilsService;
	}

	private FolderUtilsService _folderUtilsService;

}