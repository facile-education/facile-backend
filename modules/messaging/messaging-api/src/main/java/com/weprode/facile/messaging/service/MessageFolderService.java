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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for MessageFolder. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MessageFolderServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface MessageFolderService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.messaging.service.impl.MessageFolderServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the message folder remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link MessageFolderServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Add personal folder
	 */
	@JSONWebService(method = "GET")
	public JSONObject addFolder(long parentFolderId, String folderName);

	/**
	 * Remove a folder
	 */
	@JSONWebService(method = "GET")
	public JSONObject deleteFolder(long folderId);

	/**
	 * Get all user message boxes and root folders
	 */
	@JSONWebService(method = "GET")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getAllUserFolders();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * Add a folder
	 */
	@JSONWebService(method = "GET")
	public JSONObject renameFolder(long folderId, String newLabel);

}