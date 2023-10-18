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

package com.weprode.nero.messaging.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessageFolderService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessageFolderService
 * @generated
 */
public class MessageFolderServiceWrapper
	implements MessageFolderService, ServiceWrapper<MessageFolderService> {

	public MessageFolderServiceWrapper() {
		this(null);
	}

	public MessageFolderServiceWrapper(
		MessageFolderService messageFolderService) {

		_messageFolderService = messageFolderService;
	}

	/**
	 * Add personal folder
	 */
	@Override
	public org.json.JSONObject addFolder(
		long parentFolderId, String folderName) {

		return _messageFolderService.addFolder(parentFolderId, folderName);
	}

	/**
	 * Remove a folder
	 */
	@Override
	public org.json.JSONObject deleteFolder(long folderId) {
		return _messageFolderService.deleteFolder(folderId);
	}

	/**
	 * Get all user message boxes and root folders
	 */
	@Override
	public org.json.JSONObject getAllUserFolders() {
		return _messageFolderService.getAllUserFolders();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _messageFolderService.getOSGiServiceIdentifier();
	}

	/**
	 * Add a folder
	 */
	@Override
	public org.json.JSONObject renameFolder(long folderId, String newLabel) {
		return _messageFolderService.renameFolder(folderId, newLabel);
	}

	@Override
	public MessageFolderService getWrappedService() {
		return _messageFolderService;
	}

	@Override
	public void setWrappedService(MessageFolderService messageFolderService) {
		_messageFolderService = messageFolderService;
	}

	private MessageFolderService _messageFolderService;

}