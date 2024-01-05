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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FolderUtilsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FolderUtilsLocalService
 * @generated
 */
public class FolderUtilsLocalServiceWrapper
	implements FolderUtilsLocalService,
			   ServiceWrapper<FolderUtilsLocalService> {

	public FolderUtilsLocalServiceWrapper() {
		this(null);
	}

	public FolderUtilsLocalServiceWrapper(
		FolderUtilsLocalService folderUtilsLocalService) {

		_folderUtilsLocalService = folderUtilsLocalService;
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder copyFolder(
			long userId, long folderId, long destFolderId, int mode)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.copyFolder(
			userId, folderId, destFolderId, mode);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder createFolder(
			com.liferay.portal.kernel.model.User user, long targetFolderId,
			String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.createFolder(
			user, targetFolderId, name);
	}

	@Override
	public void deleteFolder(long userId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_folderUtilsLocalService.deleteFolder(userId, folderId);
	}

	@Override
	public org.json.JSONObject format(
			long userId,
			com.liferay.portal.kernel.repository.model.Folder folder)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.format(userId, folder);
	}

	@Override
	public org.json.JSONObject format(
			long userId,
			com.liferay.portal.kernel.repository.model.Folder folder, int space)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.format(userId, folder, space);
	}

	@Override
	public org.json.JSONObject format(
			long userId,
			com.liferay.portal.kernel.repository.model.Folder folder, int space,
			boolean withDetails)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.format(
			userId, folder, space, withDetails);
	}

	@Override
	public org.json.JSONObject format(
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.repository.model.Folder folder, int space,
		boolean withDetails) {

		return _folderUtilsLocalService.format(
			user, folder, space, withDetails);
	}

	@Override
	public org.json.JSONObject formatWithOnlyMandatoryFields(
		com.liferay.portal.kernel.repository.model.Folder folder) {

		return _folderUtilsLocalService.formatWithOnlyMandatoryFields(folder);
	}

	@Override
	public org.json.JSONObject formatWithOnlyMandatoryFields(long folderId) {
		return _folderUtilsLocalService.formatWithOnlyMandatoryFields(folderId);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder getFolderByName(
			com.liferay.portal.kernel.repository.model.Folder parentFolder,
			String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderUtilsLocalService.getFolderByName(parentFolder, name);
	}

	/**
	 * Return the path of a folder until the Root folder (folderId = 0 excluded) containing also the current folder
	 * Example: folder 'test' may have path 'monCartable / folder1 / test'
	 */
	@Override
	public java.util.List<com.liferay.portal.kernel.repository.model.Folder>
			getFolderPath(
				com.liferay.portal.kernel.repository.model.Folder folder)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.getFolderPath(folder);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.repository.model.Folder>
			getFolderPath(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.getFolderPath(folderId);
	}

	@Override
	public int getFolderSize(
			com.liferay.portal.kernel.repository.model.Folder folder)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.getFolderSize(folder);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder
			getGroupCourseFolder(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.getGroupCourseFolder(groupId);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder getGroupNewsFolder(
			long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.getGroupNewsFolder(groupId);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder
			getOrCreateGroupRootFolder(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _folderUtilsLocalService.getOrCreateGroupRootFolder(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _folderUtilsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder
			getThumbnailFolder()
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.getThumbnailFolder();
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder
			getUserMessagingAttachedFilesFolder(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.getUserMessagingAttachedFilesFolder(
			userId);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder getUserRootFolder(
			long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.getUserRootFolder(userId);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder getUserTmpFolder(
			long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.getUserTmpFolder(userId);
	}

	@Override
	public void hideDLFolder(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_folderUtilsLocalService.hideDLFolder(folderId);
	}

	@Override
	public boolean isAllowedToAccessFolder(long userId, long folderId) {
		return _folderUtilsLocalService.isAllowedToAccessFolder(
			userId, folderId);
	}

	@Override
	public boolean isGroupFolder(
		com.liferay.portal.kernel.repository.model.Folder folder) {

		return _folderUtilsLocalService.isGroupFolder(folder);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder moveFolder(
			long userId,
			com.liferay.portal.kernel.repository.model.Folder folder,
			long targetFolderId, int mode)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.moveFolder(
			userId, folder, targetFolderId, mode);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.Folder renameFolder(
			long userId,
			com.liferay.portal.kernel.repository.model.Folder originFolder,
			String newName)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _folderUtilsLocalService.renameFolder(
			userId, originFolder, newName);
	}

	@Override
	public FolderUtilsLocalService getWrappedService() {
		return _folderUtilsLocalService;
	}

	@Override
	public void setWrappedService(
		FolderUtilsLocalService folderUtilsLocalService) {

		_folderUtilsLocalService = folderUtilsLocalService;
	}

	private FolderUtilsLocalService _folderUtilsLocalService;

}