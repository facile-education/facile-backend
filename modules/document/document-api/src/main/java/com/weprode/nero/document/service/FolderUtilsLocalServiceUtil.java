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
 * Provides the local service utility for FolderUtils. This utility wraps
 * <code>com.weprode.nero.document.service.impl.FolderUtilsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see FolderUtilsLocalService
 * @generated
 */
public class FolderUtilsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.FolderUtilsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.repository.model.Folder copyFolder(
			long userId, long folderId, long destFolderId, int mode)
		throws PortalException, SystemException {

		return getService().copyFolder(userId, folderId, destFolderId, mode);
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			createFolder(
				com.liferay.portal.kernel.model.User user, long targetFolderId,
				String name)
		throws PortalException, SystemException {

		return getService().createFolder(user, targetFolderId, name);
	}

	public static void deleteFolder(long userId, long folderId)
		throws PortalException, SystemException {

		getService().deleteFolder(userId, folderId);
	}

	public static String downloadFolder(
			com.liferay.portal.kernel.repository.model.Folder folder,
			com.liferay.portal.kernel.model.User user)
		throws java.io.IOException, PortalException, SystemException {

		return getService().downloadFolder(folder, user);
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			getFolderByName(
				com.liferay.portal.kernel.repository.model.Folder parentFolder,
				String name)
		throws PortalException {

		return getService().getFolderByName(parentFolder, name);
	}

	/**
	 * Return the path of a folder until the Root folder (folderId = 0 excluded) containing also the current folder
	 * Example: folder 'test' may have path 'monCartable / folder1 / test'
	 */
	public static List<com.liferay.portal.kernel.repository.model.Folder>
			getFolderPath(
				com.liferay.portal.kernel.repository.model.Folder folder)
		throws PortalException, SystemException {

		return getService().getFolderPath(folder);
	}

	public static List<com.liferay.portal.kernel.repository.model.Folder>
			getFolderPath(long folderId)
		throws PortalException, SystemException {

		return getService().getFolderPath(folderId);
	}

	public static int getFolderSize(
			com.liferay.portal.kernel.repository.model.Folder folder)
		throws PortalException, SystemException {

		return getService().getFolderSize(folder);
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			getGroupNewsFolder(long groupId)
		throws PortalException, SystemException {

		return getService().getGroupNewsFolder(groupId);
	}

	public static com.liferay.portal.kernel.repository.model.Folder getIMBox(
			long userId)
		throws PortalException, SystemException {

		return getService().getIMBox(userId);
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			getOrCreateGroupRootFolder(long groupId)
		throws PortalException {

		return getService().getOrCreateGroupRootFolder(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			getProgressionFolder(long userId)
		throws PortalException, SystemException {

		return getService().getProgressionFolder(userId);
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			getSendingBox(long userId)
		throws PortalException, SystemException {

		return getService().getSendingBox(userId);
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			getTmpFolder(long userId)
		throws PortalException, SystemException {

		return getService().getTmpFolder(userId);
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			getUserRootFolder(long userId)
		throws PortalException, SystemException {

		return getService().getUserRootFolder(userId);
	}

	public static boolean isGroupFolder(
		com.liferay.portal.kernel.repository.model.Folder folder) {

		return getService().isGroupFolder(folder);
	}

	public static com.liferay.portal.kernel.repository.model.Folder moveFolder(
			long userId,
			com.liferay.portal.kernel.repository.model.Folder folder,
			long targetFolderId, int mode)
		throws PortalException, SystemException {

		return getService().moveFolder(userId, folder, targetFolderId, mode);
	}

	public static com.liferay.portal.kernel.repository.model.Folder
			renameFolder(
				long userId,
				com.liferay.portal.kernel.repository.model.Folder originFolder,
				String newName)
		throws PortalException, SystemException {

		return getService().renameFolder(userId, originFolder, newName);
	}

	public static FolderUtilsLocalService getService() {
		return _service;
	}

	private static volatile FolderUtilsLocalService _service;

}