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
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.nero.application.service.BroadcastLocalServiceUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.document.service.DocumentUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.document.service.base.DocumentUtilsServiceBaseImpl;
import com.weprode.nero.group.model.GroupActivity;
import com.weprode.nero.group.service.GroupActivityLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.UserUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
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
		long maxUploadSize = Long.parseLong(PropsUtil.get(NeroSystemProperties.MAX_UPLOAD_SIZE));
		result.put(JSONConstants.MAX_UPLOAD_SIZE, maxUploadSize);

		result.put(JSONConstants.HAS_MINDMAP_BROADCASTED, BroadcastLocalServiceUtil.isApplicationBroadcastedToUser(user.getUserId(), "mindmap"));
		result.put(JSONConstants.HAS_GEOGEBRA_BROADCASTED, BroadcastLocalServiceUtil.isApplicationBroadcastedToUser(user.getUserId(), "geogebra"));
		result.put(JSONConstants.HAS_SCRATCH_BROADCASTED, BroadcastLocalServiceUtil.isApplicationBroadcastedToUser(user.getUserId(), "scratch"));
		result.put(JSONConstants.HAS_LOOL_BROADCASTED, BroadcastLocalServiceUtil.isApplicationBroadcastedToUser(user.getUserId(), "lool"));
		result.put(JSONConstants.HAS_H5P_BROADCASTED, BroadcastLocalServiceUtil.isApplicationBroadcastedToUser(user.getUserId(), "h5p"));
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

	@JSONWebService(value = "get-document-group-activity", method = "GET")
	public JSONObject getDocumentGroupActivity(long groupId, String maxDate, int nbResults) {
		JSONObject result = new JSONObject();
		
		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		// Limited to user's groups and all groups for direction
		if (!UserUtilsLocalServiceUtil.getUserGroupIds(user.getUserId()).contains(groupId) && !RoleUtilsLocalServiceUtil.isDirectionMember(user)) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		logger.info("User " + user.getFullName() +" fetches document activity for groupId " + groupId);

		try {
			JSONArray jsonActivities = new JSONArray();
			Date maximumDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(maxDate);
			List<GroupActivity> groupActivities = GroupActivityLocalServiceUtil.getDocumentGroupActivities(user.getUserId(), groupId, maximumDate, nbResults);
			for (GroupActivity groupActivity : groupActivities) {
				JSONObject jsonActivity = GroupActivityLocalServiceUtil.convertGroupActivity(user.getUserId(), groupActivity);
				if (jsonActivity != null) {
					jsonActivities.put(jsonActivity);
				}
			}
			result.put(JSONConstants.ACTIVITIES, jsonActivities);
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error while fetching document activity for groupId " + groupId, e);
		}

		return result;
	}
}