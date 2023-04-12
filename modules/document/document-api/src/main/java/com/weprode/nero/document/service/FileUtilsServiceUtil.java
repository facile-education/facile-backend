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

/**
 * Provides the remote service utility for FileUtils. This utility wraps
 * <code>com.weprode.nero.document.service.impl.FileUtilsServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see FileUtilsService
 * @generated
 */
public class FileUtilsServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.FileUtilsServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject addLock(
		long fileId) {

		return getService().addLock(fileId);
	}

	public static com.liferay.portal.kernel.json.JSONObject createAudioFile(
		long folderId, java.lang.String name, java.io.File file) {

		return getService().createAudioFile(folderId, name, file);
	}

	public static com.liferay.portal.kernel.json.JSONObject createGeogebraFile(
		long folderId, java.lang.String name) {

		return getService().createGeogebraFile(folderId, name);
	}

	public static com.liferay.portal.kernel.json.JSONObject createHTMLFile(
		long folderId, java.lang.String name) {

		return getService().createHTMLFile(folderId, name);
	}

	public static com.liferay.portal.kernel.json.JSONObject createLoolFile(
		long folderId, java.lang.String name, java.lang.String type) {

		return getService().createLoolFile(folderId, name, type);
	}

	public static com.liferay.portal.kernel.json.JSONObject createMindmapFile(
		long folderId, java.lang.String name) {

		return getService().createMindmapFile(folderId, name);
	}

	public static com.liferay.portal.kernel.json.JSONObject createScratchFile(
		long folderId, java.lang.String name) {

		return getService().createScratchFile(folderId, name);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.json.JSONObject getResource(
		long fileId, long versionId, boolean readOnly) {

		return getService().getResource(fileId, versionId, readOnly);
	}

	public static com.liferay.portal.kernel.json.JSONObject removeLock(
		long fileId) {

		return getService().removeLock(fileId);
	}

	public static com.liferay.portal.kernel.json.JSONObject removeLoolToken(
		java.lang.String token) {

		return getService().removeLoolToken(token);
	}

	public static com.liferay.portal.kernel.json.JSONObject renameFile(
		long fileId, java.lang.String fileName) {

		return getService().renameFile(fileId, fileName);
	}

	public static com.liferay.portal.kernel.json.JSONObject uploadFile(
		long folderId, java.lang.String fileName, java.io.File file, int mode) {

		return getService().uploadFile(folderId, fileName, file, mode);
	}

	public static FileUtilsService getService() {
		return _service;
	}

	private static volatile FileUtilsService _service;

}