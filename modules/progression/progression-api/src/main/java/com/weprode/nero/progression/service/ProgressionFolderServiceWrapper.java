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

package com.weprode.nero.progression.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProgressionFolderService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionFolderService
 * @generated
 */
public class ProgressionFolderServiceWrapper
	implements ProgressionFolderService,
			   ServiceWrapper<ProgressionFolderService> {

	public ProgressionFolderServiceWrapper(
		ProgressionFolderService progressionFolderService) {

		_progressionFolderService = progressionFolderService;
	}

	@Override
	public org.json.JSONObject addFolder(
		long progressionId, long parentFolderId) {

		return _progressionFolderService.addFolder(
			progressionId, parentFolderId);
	}

	@Override
	public org.json.JSONObject deleteFolder(long folderId) {
		return _progressionFolderService.deleteFolder(folderId);
	}

	@Override
	public org.json.JSONObject getFolderContent(long folderId) {
		return _progressionFolderService.getFolderContent(folderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _progressionFolderService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject updateFolder(
		long folderId, long parentFolderId, String name, int order) {

		return _progressionFolderService.updateFolder(
			folderId, parentFolderId, name, order);
	}

	@Override
	public ProgressionFolderService getWrappedService() {
		return _progressionFolderService;
	}

	@Override
	public void setWrappedService(
		ProgressionFolderService progressionFolderService) {

		_progressionFolderService = progressionFolderService;
	}

	private ProgressionFolderService _progressionFolderService;

}