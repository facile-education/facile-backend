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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * Provides the local service utility for Groups. This utility wraps
 * <code>com.weprode.facile.document.service.impl.GroupsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see GroupsLocalService
 * @generated
 */
public class GroupsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.document.service.impl.GroupsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static List<com.liferay.portal.kernel.repository.model.FileEntry>
			getFolderGroupFiles(
				com.liferay.portal.kernel.model.User user, long folderId,
				String[] mimeTypes)
		throws PortalException, SystemException {

		return getService().getFolderGroupFiles(user, folderId, mimeTypes);
	}

	public static List<com.liferay.portal.kernel.repository.model.Folder>
			getFolderGroupFolders(
				com.liferay.portal.kernel.model.User user, long folderId)
		throws PortalException, SystemException {

		return getService().getFolderGroupFolders(user, folderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONArray getUserGroupsFolders(
			com.liferay.portal.kernel.model.User user)
		throws PortalException, SystemException {

		return getService().getUserGroupsFolders(user);
	}

	public static org.json.JSONArray groupFilesToJSON(
		com.liferay.portal.kernel.model.User user,
		List<com.liferay.portal.kernel.repository.model.FileEntry> groupFiles,
		Boolean withDetails) {

		return getService().groupFilesToJSON(user, groupFiles, withDetails);
	}

	public static org.json.JSONArray groupFoldersToJSON(
		com.liferay.portal.kernel.model.User user,
		List<com.liferay.portal.kernel.repository.model.Folder> groupFolders,
		Boolean withDetails) {

		return getService().groupFoldersToJSON(user, groupFolders, withDetails);
	}

	public static GroupsLocalService getService() {
		return _service;
	}

	private static volatile GroupsLocalService _service;

}