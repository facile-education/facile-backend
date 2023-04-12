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

import java.io.InputStream;

/**
 * Provides the local service utility for FileUtils. This utility wraps
 * <code>com.weprode.nero.document.service.impl.FileUtilsLocalServiceImpl</code> and
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
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.FileUtilsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
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
		throws java.io.IOException, PortalException, SystemException {

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

	public static String getDisplayUrl(
			com.liferay.portal.kernel.repository.model.FileEntry file,
			long versionId, String typeOfView, long userId, boolean readOnly)
		throws PortalException, SystemException {

		return getService().getDisplayUrl(
			file, versionId, typeOfView, userId, readOnly);
	}

	public static String getDownloadUrl(
		com.liferay.portal.kernel.repository.model.FileEntry file) {

		return getService().getDownloadUrl(file);
	}

	/**
	 * Get file as Object Value Pair
	 */
	public static com.liferay.portal.kernel.util.ObjectValuePair
		<String, InputStream> getFileAsOVPStream(
				long companyId, long fileId, String fileName)
			throws java.io.IOException, PortalException, SystemException {

		return getService().getFileAsOVPStream(companyId, fileId, fileName);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static long getSizeInMegaOctet(long pSize) {
		return getService().getSizeInMegaOctet(pSize);
	}

	public static boolean isAllowedToAccessFolder(long userId, long folderId) {
		return getService().isAllowedToAccessFolder(userId, folderId);
	}

	public static boolean isGroupFile(long fileEntryId) {
		return getService().isGroupFile(fileEntryId);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry
			moveFileEntry(long userId, long fileId, long destFolderId, int mode)
		throws PortalException, SystemException {

		return getService().moveFileEntry(userId, fileId, destFolderId, mode);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry
			renameFile(
				com.liferay.portal.kernel.model.User user,
				com.liferay.portal.kernel.repository.model.FileEntry originFile,
				String newName)
		throws PortalException, SystemException {

		return getService().renameFile(user, originFile, newName);
	}

	public static String sanitizeHTMLContent(String content) {
		return getService().sanitizeHTMLContent(content);
	}

	public static FileUtilsLocalService getService() {
		return _service;
	}

	private static volatile FileUtilsLocalService _service;

}