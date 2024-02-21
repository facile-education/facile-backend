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
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.io.File;

import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for FileUtils. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see FileUtilsServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface FileUtilsService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.document.service.impl.FileUtilsServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the file utils remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link FileUtilsServiceUtil} if injection and service tracking are not available.
	 */
	@JSONWebService(method = "POST")
	public JSONObject addLock(long fileId);

	@JSONWebService(method = "POST")
	public JSONObject createAudioFile(long folderId, String name, File file);

	@JSONWebService(method = "POST")
	public JSONObject createGeogebraFile(long folderId, String name);

	@JSONWebService(method = "POST")
	public JSONObject createHTMLFile(long folderId, String name);

	@JSONWebService(method = "POST")
	public JSONObject createLoolFile(long folderId, String name, String type);

	@JSONWebService(method = "POST")
	public JSONObject createMindmapFile(long folderId, String name);

	@JSONWebService(method = "POST")
	public JSONObject createScratchFile(long folderId, String name);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "GET")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getResource(
		long fileId, long versionId, boolean readOnly);

	@JSONWebService(method = "DELETE")
	public JSONObject removeLock(long fileId);

	@JSONWebService(method = "DELETE")
	public JSONObject removeLoolToken(String token);

	@JSONWebService(method = "POST")
	public JSONObject renameFile(long fileId, String fileName);

	@JSONWebService(method = "POST")
	public JSONObject uploadFile(
		long folderId, String fileName, File file, int mode);

	@JSONWebService(method = "POST")
	public JSONObject uploadTmpFile(String fileName, File file);

}