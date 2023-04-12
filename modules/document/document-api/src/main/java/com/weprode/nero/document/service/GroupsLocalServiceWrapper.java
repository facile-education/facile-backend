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
 * Provides a wrapper for {@link GroupsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see GroupsLocalService
 * @generated
 */
public class GroupsLocalServiceWrapper
	implements GroupsLocalService, ServiceWrapper<GroupsLocalService> {

	public GroupsLocalServiceWrapper(GroupsLocalService groupsLocalService) {
		_groupsLocalService = groupsLocalService;
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.repository.model.FileEntry>
			getFolderGroupFiles(
				com.liferay.portal.kernel.model.User user, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _groupsLocalService.getFolderGroupFiles(user, folderId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.repository.model.Folder>
			getFolderGroupFolders(
				com.liferay.portal.kernel.model.User user, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _groupsLocalService.getFolderGroupFolders(user, folderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _groupsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getUserGroupsFolders(
			com.liferay.portal.kernel.model.User user)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _groupsLocalService.getUserGroupsFolders(user);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray groupFilesToJSON(
		com.liferay.portal.kernel.model.User user,
		java.util.List<com.liferay.portal.kernel.repository.model.FileEntry>
			groupFiles,
		Boolean withDetails) {

		return _groupsLocalService.groupFilesToJSON(
			user, groupFiles, withDetails);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray groupFoldersToJSON(
		com.liferay.portal.kernel.model.User user,
		java.util.List<com.liferay.portal.kernel.repository.model.Folder>
			groupFolders,
		Boolean withDetails) {

		return _groupsLocalService.groupFoldersToJSON(
			user, groupFolders, withDetails);
	}

	@Override
	public GroupsLocalService getWrappedService() {
		return _groupsLocalService;
	}

	@Override
	public void setWrappedService(GroupsLocalService groupsLocalService) {
		_groupsLocalService = groupsLocalService;
	}

	private GroupsLocalService _groupsLocalService;

}