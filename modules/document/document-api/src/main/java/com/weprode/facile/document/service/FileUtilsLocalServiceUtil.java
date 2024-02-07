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

/**
 * Provides the local service utility for FileUtils. This utility wraps
 * <code>com.weprode.facile.document.service.impl.FileUtilsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see FileUtilsLocalService
 * @generated
 */
public class FileUtilsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.document.service.impl.FileUtilsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static java.io.File convertAudioToMP3(
			String fileName, java.io.File audioFile)
		throws java.io.IOException, SystemException {

		return getService().convertAudioToMP3(fileName, audioFile);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry
			copyFileEntry(
				long userId, long fileId, long destFolderId,
				boolean copyFileContent)
		throws java.io.IOException, PortalException, SystemException {

		return getService().copyFileEntry(
			userId, fileId, destFolderId, copyFileContent);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry
			copyFileEntry(
				long userId, long fileId, long destFolderId,
				boolean copyFileContent, int mode)
		throws java.io.IOException, PortalException, SystemException {

		return getService().copyFileEntry(
			userId, fileId, destFolderId, copyFileContent, mode);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry
			createGeogebraFile(
				com.liferay.portal.kernel.model.User user, long folderId,
				String name)
		throws PortalException, SystemException {

		return getService().createGeogebraFile(user, folderId, name);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry
			createHtmlFile(
				com.liferay.portal.kernel.model.User user, long folderId,
				String name)
		throws PortalException, SystemException {

		return getService().createHtmlFile(user, folderId, name);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry
			createLoolFile(
				com.liferay.portal.kernel.model.User user, long folderId,
				String name, String type)
		throws Exception {

		return getService().createLoolFile(user, folderId, name, type);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry
			createMindMapFile(
				com.liferay.portal.kernel.model.User user, long folderId,
				String name)
		throws PortalException, SystemException {

		return getService().createMindMapFile(user, folderId, name);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry
			createScratchFile(
				com.liferay.portal.kernel.model.User user, long folderId,
				String name)
		throws PortalException, SystemException {

		return getService().createScratchFile(user, folderId, name);
	}

	public static void deleteFile(long userId, long fileId)
		throws PortalException, SystemException {

		getService().deleteFile(userId, fileId);
	}

	public static org.json.JSONObject format(
			long userId,
			com.liferay.portal.kernel.repository.model.FileEntry fileEntry)
		throws PortalException, SystemException {

		return getService().format(userId, fileEntry);
	}

	public static org.json.JSONObject format(
			long userId,
			com.liferay.portal.kernel.repository.model.FileEntry fileEntry,
			int space)
		throws PortalException, SystemException {

		return getService().format(userId, fileEntry, space);
	}

	public static org.json.JSONObject format(
			long userId,
			com.liferay.portal.kernel.repository.model.FileEntry fileEntry,
			int space, boolean withDetails)
		throws PortalException, SystemException {

		return getService().format(userId, fileEntry, space, withDetails);
	}

	public static org.json.JSONObject format(
		com.liferay.portal.kernel.model.User user,
		com.liferay.portal.kernel.repository.model.FileEntry fileEntry,
		int space, boolean withDetails) {

		return getService().format(user, fileEntry, space, withDetails);
	}

	public static org.json.JSONObject formatWithOnlyMandatoryFields(
		com.liferay.portal.kernel.repository.model.FileEntry fileEntry) {

		return getService().formatWithOnlyMandatoryFields(fileEntry);
	}

	public static org.json.JSONObject formatWithOnlyMandatoryFields(
		long fileId) {

		return getService().formatWithOnlyMandatoryFields(fileId);
	}

	public static String getDisplayUrl(
			com.liferay.portal.kernel.repository.model.FileEntry file,
			long versionId, long userId, boolean readOnly)
		throws PortalException, SystemException {

		return getService().getDisplayUrl(file, versionId, userId, readOnly);
	}

	public static String getDownloadUrl(
		com.liferay.portal.kernel.repository.model.FileEntry file) {

		return getService().getDownloadUrl(file);
	}

	public static String getFileContent(long fileEntryId, String version) {
		return getService().getFileContent(fileEntryId, version);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static boolean isGroupFile(long fileEntryId) {
		return getService().isGroupFile(fileEntryId);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry
			moveFileEntry(long userId, long fileId, long destFolderId)
		throws PortalException, SystemException {

		return getService().moveFileEntry(userId, fileId, destFolderId);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry
			moveFileEntry(long userId, long fileId, long destFolderId, int mode)
		throws PortalException, SystemException {

		return getService().moveFileEntry(userId, fileId, destFolderId, mode);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry
			renameFile(
				long userId,
				com.liferay.portal.kernel.repository.model.FileEntry originFile,
				String newName)
		throws PortalException, SystemException {

		return getService().renameFile(userId, originFile, newName);
	}

	public static String sanitizeHTMLContent(String content) {
		return getService().sanitizeHTMLContent(content);
	}

	public static FileUtilsLocalService getService() {
		return _service;
	}

	private static volatile FileUtilsLocalService _service;

}