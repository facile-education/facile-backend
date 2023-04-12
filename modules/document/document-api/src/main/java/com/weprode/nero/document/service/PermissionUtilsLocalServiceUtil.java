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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * Provides the local service utility for PermissionUtils. This utility wraps
 * <code>com.weprode.nero.document.service.impl.PermissionUtilsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PermissionUtilsLocalService
 * @generated
 */
public class PermissionUtilsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.PermissionUtilsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addDefaultPermissionsFile(
			com.liferay.portal.kernel.repository.model.FileEntry fileEntry)
		throws PortalException, SystemException {

		getService().addDefaultPermissionsFile(fileEntry);
	}

	public static void addDefaultPermissionsFolder(
			com.liferay.portal.kernel.repository.model.Folder folder)
		throws PortalException, SystemException {

		getService().addDefaultPermissionsFolder(folder);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<String> getPermissionRoles(long groupId)
		throws PortalException, SystemException {

		return getService().getPermissionRoles(groupId);
	}

	public static boolean hasRoleFilePermission(
		com.liferay.portal.kernel.model.Role role,
		com.liferay.portal.kernel.repository.model.FileEntry fileEntry,
		String actionId) {

		return getService().hasRoleFilePermission(role, fileEntry, actionId);
	}

	public static boolean hasRoleFolderPermission(
		com.liferay.portal.kernel.model.Role role,
		com.liferay.portal.kernel.repository.model.Folder folder,
		String actionId) {

		return getService().hasRoleFolderPermission(role, folder, actionId);
	}

	public static boolean hasUserFilePermission(
		long userId,
		com.liferay.portal.kernel.repository.model.FileEntry fileEntry,
		String actionId) {

		return getService().hasUserFilePermission(userId, fileEntry, actionId);
	}

	public static boolean hasUserFolderPermission(
		long userId, com.liferay.portal.kernel.repository.model.Folder folder,
		String actionId) {

		return getService().hasUserFolderPermission(userId, folder, actionId);
	}

	public static void setParentPermissionToFile(
			com.liferay.portal.kernel.repository.model.FileEntry fileEntry)
		throws PortalException, SystemException {

		getService().setParentPermissionToFile(fileEntry);
	}

	public static void setParentPermissionToFolder(
			com.liferay.portal.kernel.repository.model.Folder folder)
		throws PortalException, SystemException {

		getService().setParentPermissionToFolder(folder);
	}

	/**
	 * Set the VIEW permission to a FileEntry or a Folder for ALL users
	 */
	public static void setViewPermissionForRessources(Object ressource)
		throws PortalException, SystemException {

		getService().setViewPermissionForRessources(ressource);
	}

	public static void validateFullPermission(
			com.liferay.portal.kernel.model.User user, long objectId,
			String type, com.liferay.portal.kernel.json.JSONArray permissions,
			boolean applyToChild, long scopeGrpId)
		throws PortalException, SystemException {

		getService().validateFullPermission(
			user, objectId, type, permissions, applyToChild, scopeGrpId);
	}

	public static PermissionUtilsLocalService getService() {
		return _service;
	}

	private static volatile PermissionUtilsLocalService _service;

}