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

package com.weprode.nero.document.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.json.JSONArray;
import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.nero.application.service.BroadcastLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.document.service.DocumentUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.document.service.base.DocumentUtilsServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=DocumentUtils"
	},
	service = AopService.class
)
public class DocumentUtilsServiceImpl extends DocumentUtilsServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(DocumentUtilsServiceImpl.class);

	@JSONWebService(value = "get-global-documents-properties", method = "GET")
	public JSONObject getGlobalDocumentsProperties() throws SystemException, PortalException {
		JSONObject result = new JSONObject();

		User user = getGuestOrUser();
		logger.info("Getting global document properties for user " + user.getUserId());

		// Get or create user Roots folders
		Folder privateFolder = FolderUtilsLocalServiceUtil.getUserRootFolder(user.getUserId());

		// Private
		JSONObject jsonProperties = new JSONObject();
		jsonProperties.put(JSONConstants.ID, privateFolder.getFolderId());
		jsonProperties.put(JSONConstants.NAME, privateFolder.getName());
		jsonProperties.put(JSONConstants.HAS_SUB_FOLDERS, true);
		jsonProperties.put(JSONConstants.SUB_FOLDERS, new JSONArray());
		jsonProperties.put(JSONConstants.FILES, new JSONArray());
		result.put(JSONConstants.PRIVATE, jsonProperties);

		// User Max upload Size
		// So far, user max upload size is the portal max upload size but in the future it could depend on his roles and cota's gesture
		long maxUploadSize = Long.parseLong(PropsUtil.get(NeroSystemProperties.MAX_UPLOAD_SIZE));
		result.put(JSONConstants.MAX_UPLOAD_SIZE, maxUploadSize);

		result.put(JSONConstants.HAS_MINDMAP_BROADCASTED, BroadcastLocalServiceUtil.isApplicationBroadcastedToUser(user.getUserId(), "mindmap"));
		result.put(JSONConstants.HAS_GEOGEBRA_BROADCASTED, BroadcastLocalServiceUtil.isApplicationBroadcastedToUser(user.getUserId(), "geogebra"));
		result.put(JSONConstants.HAS_SCRATCH_BROADCASTED, BroadcastLocalServiceUtil.isApplicationBroadcastedToUser(user.getUserId(), "scratch"));
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}

	@JSONWebService(value = "is-embed-url-whitelisted", method = "GET")
	public JSONObject isEmbedUrlWhitelisted(String url) throws SystemException {
		JSONObject result = new JSONObject();

		result.put(JSONConstants.IS_ALLOWED, DocumentUtilsLocalServiceUtil.isEmbedUrlWhitelisted(url));
		result.put(JSONConstants.SUCCESS, true);

		return result;
	}
}