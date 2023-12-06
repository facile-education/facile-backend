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

package com.weprode.facile.course.service;

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
 * Provides the remote service interface for ContentBlock. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ContentBlockServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ContentBlockService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.course.service.impl.ContentBlockServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the content block remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link ContentBlockServiceUtil} if injection and service tracking are not available.
	 */
	@JSONWebService(method = "POST", value = "add-block")
	public JSONObject addBlock(
		long itemId, int blockType, String blockName, String blockValue,
		long fileEntryId);

	@JSONWebService(method = "POST", value = "add-file-block")
	public JSONObject addFileBlock(
		long itemId, int blockType, String blockName, String fileName,
		File file);

	@JSONWebService(method = "DELETE", value = "delete-block")
	public JSONObject deleteBlock(long blockId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "GET", value = "is-embed-url-whitelisted")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject isEmbedUrlWhitelisted(String url);

	@JSONWebService(method = "POST", value = "update-block")
	public JSONObject updateBlock(
		long blockId, String blockName, String blockValue, int order);

}