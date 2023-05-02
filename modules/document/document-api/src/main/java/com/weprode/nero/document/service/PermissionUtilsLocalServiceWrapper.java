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
 * Provides a wrapper for {@link PermissionUtilsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PermissionUtilsLocalService
 * @generated
 */
public class PermissionUtilsLocalServiceWrapper
	implements PermissionUtilsLocalService,
			   ServiceWrapper<PermissionUtilsLocalService> {

	public PermissionUtilsLocalServiceWrapper(
		PermissionUtilsLocalService permissionUtilsLocalService) {

		_permissionUtilsLocalService = permissionUtilsLocalService;
	}

	@Override
	public void addDefaultPermissionsFile(
			com.liferay.portal.kernel.repository.model.FileEntry fileEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_permissionUtilsLocalService.addDefaultPermissionsFile(fileEntry);
	}

	@Override
	public void addDefaultPermissionsFolder(
			com.liferay.portal.kernel.repository.model.Folder folder)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_permissionUtilsLocalService.addDefaultPermissionsFolder(folder);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _permissionUtilsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<String> getPermissionRoles(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _permissionUtilsLocalService.getPermissionRoles(groupId);
	}

	@Override
	public boolean hasRoleFilePermission(
		com.liferay.portal.kernel.model.Role role,
		com.liferay.portal.kernel.repository.model.FileEntry fileEntry,
		String actionId) {

		return _permissionUtilsLocalService.hasRoleFilePermission(
			role, fileEntry, actionId);
	}

	@Override
	public boolean hasRoleFolderPermission(
		com.liferay.portal.kernel.model.Role role,
		com.liferay.portal.kernel.repository.model.Folder folder,
		String actionId) {

		return _permissionUtilsLocalService.hasRoleFolderPermission(
			role, folder, actionId);
	}

	@Override
	public boolean hasUserFilePermission(
		long userId,
		com.liferay.portal.kernel.repository.model.FileEntry fileEntry,
		String actionId) {

		return _permissionUtilsLocalService.hasUserFilePermission(
			userId, fileEntry, actionId);
	}

	@Override
	public boolean hasUserFolderPermission(
		long userId, com.liferay.portal.kernel.repository.model.Folder folder,
		String actionId) {

		return _permissionUtilsLocalService.hasUserFolderPermission(
			userId, folder, actionId);
	}

	@Override
	public void setParentPermissionToFile(
			com.liferay.portal.kernel.repository.model.FileEntry fileEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_permissionUtilsLocalService.setParentPermissionToFile(fileEntry);
	}

	@Override
	public void setParentPermissionToFolder(
			com.liferay.portal.kernel.repository.model.Folder folder)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_permissionUtilsLocalService.setParentPermissionToFolder(folder);
	}

	/**
	 * Set the VIEW permission to a FileEntry or a Folder for ALL users
	 */
	@Override
	public void setViewPermissionForRessources(Object ressource)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_permissionUtilsLocalService.setViewPermissionForRessources(ressource);
	}

	@Override
	public void validateFullPermission(
			com.liferay.portal.kernel.model.User user, long objectId,
			String type, org.json.JSONArray permissions, boolean applyToChild,
			long scopeGrpId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_permissionUtilsLocalService.validateFullPermission(
			user, objectId, type, permissions, applyToChild, scopeGrpId);
	}

	@Override
	public PermissionUtilsLocalService getWrappedService() {
		return _permissionUtilsLocalService;
	}

	@Override
	public void setWrappedService(
		PermissionUtilsLocalService permissionUtilsLocalService) {

		_permissionUtilsLocalService = permissionUtilsLocalService;
	}

	private PermissionUtilsLocalService _permissionUtilsLocalService;

}