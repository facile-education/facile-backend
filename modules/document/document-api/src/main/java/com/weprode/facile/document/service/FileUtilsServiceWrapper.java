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
 * Provides a wrapper for {@link FileUtilsService}.
 *
 * @author Brian Wing Shun Chan
 * @see FileUtilsService
 * @generated
 */
public class FileUtilsServiceWrapper
	implements FileUtilsService, ServiceWrapper<FileUtilsService> {

	public FileUtilsServiceWrapper() {
		this(null);
	}

	public FileUtilsServiceWrapper(FileUtilsService fileUtilsService) {
		_fileUtilsService = fileUtilsService;
	}

	@Override
	public org.json.JSONObject addLock(long fileId) {
		return _fileUtilsService.addLock(fileId);
	}

	@Override
	public org.json.JSONObject createAudioFile(
		long folderId, String name, java.io.File file) {

		return _fileUtilsService.createAudioFile(folderId, name, file);
	}

	@Override
	public org.json.JSONObject createGeogebraFile(long folderId, String name) {
		return _fileUtilsService.createGeogebraFile(folderId, name);
	}

	@Override
	public org.json.JSONObject createHTMLFile(long folderId, String name) {
		return _fileUtilsService.createHTMLFile(folderId, name);
	}

	@Override
	public org.json.JSONObject createLoolFile(
		long folderId, String name, String type) {

		return _fileUtilsService.createLoolFile(folderId, name, type);
	}

	@Override
	public org.json.JSONObject createMindmapFile(long folderId, String name) {
		return _fileUtilsService.createMindmapFile(folderId, name);
	}

	@Override
	public org.json.JSONObject createScratchFile(long folderId, String name) {
		return _fileUtilsService.createScratchFile(folderId, name);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _fileUtilsService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getResource(
		long fileId, long versionId, boolean readOnly) {

		return _fileUtilsService.getResource(fileId, versionId, readOnly);
	}

	@Override
	public org.json.JSONObject removeLock(long fileId) {
		return _fileUtilsService.removeLock(fileId);
	}

	@Override
	public org.json.JSONObject removeLoolToken(String token) {
		return _fileUtilsService.removeLoolToken(token);
	}

	@Override
	public org.json.JSONObject renameFile(long fileId, String fileName) {
		return _fileUtilsService.renameFile(fileId, fileName);
	}

	@Override
	public org.json.JSONObject uploadFile(
		long folderId, String fileName, java.io.File file, int mode) {

		return _fileUtilsService.uploadFile(folderId, fileName, file, mode);
	}

	@Override
	public org.json.JSONObject uploadTmpFile(
		String fileName, java.io.File file) {

		return _fileUtilsService.uploadTmpFile(fileName, file);
	}

	@Override
	public FileUtilsService getWrappedService() {
		return _fileUtilsService;
	}

	@Override
	public void setWrappedService(FileUtilsService fileUtilsService) {
		_fileUtilsService = fileUtilsService;
	}

	private FileUtilsService _fileUtilsService;

}