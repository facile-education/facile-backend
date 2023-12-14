/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.document.service.impl;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.document.service.base.ClipboardServiceBaseImpl;
import com.weprode.facile.document.utils.ClipboardUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=Clipboard"
	},
	service = AopService.class
)
public class ClipboardServiceImpl extends ClipboardServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(ClipboardServiceImpl.class);

	@JSONWebService(method = "GET")
	public JSONObject copy(String folderIds, String fileIds, long targetFolderId, int mode) {
		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		try {
			long userId = getGuestOrUserId();
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), targetFolderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " copies files and/or folder to directory " + targetFolderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			Folder targetFolder = DLAppServiceUtil.getFolder(targetFolderId);
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), targetFolder, ActionKeys.ADD_DOCUMENT)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " copies files and/or folder to directory " + targetFolderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			// Permissions on source items are checked deeper
			logger.info("User " + userId + " copies files " + fileIds + " and folders " + folderIds + " to targetFolderId " + targetFolderId);
			return ClipboardUtil.copy(userId, targetFolderId, folderIds, fileIds, mode);
		} catch (Exception e) {
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(method = "GET")
	public JSONObject move (String folderIds, String fileIds, long targetFolderId, int mode) {
		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			long userId = getGuestOrUserId();
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), targetFolderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " moves files and/or folder to directory " + targetFolderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			Folder targetFolder = DLAppServiceUtil.getFolder(targetFolderId);
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), targetFolder, ActionKeys.ADD_DOCUMENT)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " moves files and/or folder to directory " + targetFolderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			// Permissions on source items are checked deeper
			logger.info("User " + userId + " moves files " + fileIds + " and folders " + folderIds + " to targetFolderId " + targetFolderId);
			return ClipboardUtil.move(userId, targetFolderId, folderIds, fileIds, mode);
		} catch (Exception e) {
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

}