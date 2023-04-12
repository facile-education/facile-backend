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
 * Provides a wrapper for {@link FileUtilsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FileUtilsLocalService
 * @generated
 */
public class FileUtilsLocalServiceWrapper
	implements FileUtilsLocalService, ServiceWrapper<FileUtilsLocalService> {

	public FileUtilsLocalServiceWrapper(
		FileUtilsLocalService fileUtilsLocalService) {

		_fileUtilsLocalService = fileUtilsLocalService;
	}

	@Override
	public java.io.File convertAudioToMP3(
			String fileName, java.io.File audioFile)
		throws com.liferay.portal.kernel.exception.SystemException,
			   java.io.IOException {

		return _fileUtilsLocalService.convertAudioToMP3(fileName, audioFile);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry copyFileEntry(
			long userId, long fileId, long destFolderId,
			boolean copyFileContent)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException,
			   java.io.IOException {

		return _fileUtilsLocalService.copyFileEntry(
			userId, fileId, destFolderId, copyFileContent);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry copyFileEntry(
			long userId, long fileId, long destFolderId,
			boolean copyFileContent, int mode)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException,
			   java.io.IOException {

		return _fileUtilsLocalService.copyFileEntry(
			userId, fileId, destFolderId, copyFileContent, mode);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry
			createGeogebraFile(
				com.liferay.portal.kernel.model.User user, long folderId,
				String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _fileUtilsLocalService.createGeogebraFile(user, folderId, name);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry createHtmlFile(
			com.liferay.portal.kernel.model.User user, long folderId,
			String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _fileUtilsLocalService.createHtmlFile(user, folderId, name);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry createLoolFile(
			com.liferay.portal.kernel.model.User user, long folderId,
			String name, String type)
		throws Exception {

		return _fileUtilsLocalService.createLoolFile(
			user, folderId, name, type);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry
			createMindMapFile(
				com.liferay.portal.kernel.model.User user, long folderId,
				String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException,
			   java.io.IOException {

		return _fileUtilsLocalService.createMindMapFile(user, folderId, name);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry
			createScratchFile(
				com.liferay.portal.kernel.model.User user, long folderId,
				String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _fileUtilsLocalService.createScratchFile(user, folderId, name);
	}

	@Override
	public void deleteFile(long userId, long fileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		_fileUtilsLocalService.deleteFile(userId, fileId);
	}

	@Override
	public String getDisplayUrl(
			com.liferay.portal.kernel.repository.model.FileEntry file,
			long versionId, String typeOfView, long userId, boolean readOnly)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _fileUtilsLocalService.getDisplayUrl(
			file, versionId, typeOfView, userId, readOnly);
	}

	@Override
	public String getDownloadUrl(
		com.liferay.portal.kernel.repository.model.FileEntry file) {

		return _fileUtilsLocalService.getDownloadUrl(file);
	}

	/**
	 * Get file as Object Value Pair
	 */
	@Override
	public com.liferay.portal.kernel.util.ObjectValuePair
		<String, java.io.InputStream> getFileAsOVPStream(
				long companyId, long fileId, String fileName)
			throws com.liferay.portal.kernel.exception.PortalException,
				   com.liferay.portal.kernel.exception.SystemException,
				   java.io.IOException {

		return _fileUtilsLocalService.getFileAsOVPStream(
			companyId, fileId, fileName);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _fileUtilsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public long getSizeInMegaOctet(long pSize) {
		return _fileUtilsLocalService.getSizeInMegaOctet(pSize);
	}

	@Override
	public boolean isAllowedToAccessFolder(long userId, long folderId) {
		return _fileUtilsLocalService.isAllowedToAccessFolder(userId, folderId);
	}

	@Override
	public boolean isGroupFile(long fileEntryId) {
		return _fileUtilsLocalService.isGroupFile(fileEntryId);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry moveFileEntry(
			long userId, long fileId, long destFolderId, int mode)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _fileUtilsLocalService.moveFileEntry(
			userId, fileId, destFolderId, mode);
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry renameFile(
			com.liferay.portal.kernel.model.User user,
			com.liferay.portal.kernel.repository.model.FileEntry originFile,
			String newName)
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _fileUtilsLocalService.renameFile(user, originFile, newName);
	}

	@Override
	public String sanitizeHTMLContent(String content) {
		return _fileUtilsLocalService.sanitizeHTMLContent(content);
	}

	@Override
	public FileUtilsLocalService getWrappedService() {
		return _fileUtilsLocalService;
	}

	@Override
	public void setWrappedService(FileUtilsLocalService fileUtilsLocalService) {
		_fileUtilsLocalService = fileUtilsLocalService;
	}

	private FileUtilsLocalService _fileUtilsLocalService;

}