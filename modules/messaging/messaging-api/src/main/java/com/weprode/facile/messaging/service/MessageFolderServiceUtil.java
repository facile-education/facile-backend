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

package com.weprode.facile.messaging.service;

/**
 * Provides the remote service utility for MessageFolder. This utility wraps
 * <code>com.weprode.facile.messaging.service.impl.MessageFolderServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MessageFolderService
 * @generated
 */
public class MessageFolderServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.messaging.service.impl.MessageFolderServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Add personal folder
	 */
	public static org.json.JSONObject addFolder(
		long parentFolderId, java.lang.String folderName) {

		return getService().addFolder(parentFolderId, folderName);
	}

	/**
	 * Remove a folder
	 */
	public static org.json.JSONObject deleteFolder(long folderId) {
		return getService().deleteFolder(folderId);
	}

	/**
	 * Get all user message boxes and root folders
	 */
	public static org.json.JSONObject getAllUserFolders() {
		return getService().getAllUserFolders();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * Add a folder
	 */
	public static org.json.JSONObject renameFolder(
		long folderId, java.lang.String newLabel) {

		return getService().renameFolder(folderId, newLabel);
	}

	public static MessageFolderService getService() {
		return _service;
	}

	private static volatile MessageFolderService _service;

}