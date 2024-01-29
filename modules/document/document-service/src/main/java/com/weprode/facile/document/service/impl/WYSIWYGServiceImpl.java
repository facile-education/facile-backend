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

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.document.service.base.WYSIWYGServiceBaseImpl;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=WYSIWYG"
	},
	service = AopService.class
)
public class WYSIWYGServiceImpl extends WYSIWYGServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(WYSIWYGServiceImpl.class);

	@JSONWebService(value = "get-html-content", method = "GET")
	public JSONObject getHTMLContent (long fileVersionId) {
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
			DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getDLFileVersion(fileVersionId);
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(dlFileVersion.getFileEntryId());
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " fetches HTML for " + fileVersionId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.VIEW)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " fetches HTML for " + fileVersionId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			// Get file content
			InputStream is = DLFileEntryLocalServiceUtil.getFileAsStream(fileEntry.getFileEntryId(), dlFileVersion.getVersion());
			String content = StringUtil.read(is);

			try {
				is.close();
			} catch (IOException e) {
				logger.error("Error closing input stream", e);
			}

			result.put(JSONConstants.CONTENT, content);
			result.put(JSONConstants.NAME, fileEntry.getTitle());
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error while getting file with fileVersionId " + fileVersionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(value = "save-html-content", method = "POST")
	public JSONObject saveHTMLContent(Long fileVersionId, String content, boolean majorVersion) {
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
			DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getDLFileVersion(fileVersionId);
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(dlFileVersion.getFileEntryId());
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " saves HTML for " + fileVersionId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.UPDATE)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " saves HTML for " + fileVersionId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			// Check permissions
			if (FileUtilsLocalServiceUtil.isGroupFile(fileEntry.getFileEntryId()) && !PermissionUtilsLocalServiceUtil.hasUserFilePermission(getGuestOrUserId(), fileEntry, ActionKeys.UPDATE)) {
				result.put(JSONConstants.SUCCESS, false);
				return result;
			}

			// Set default permissions
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setAddGroupPermissions(true);

			// html sanitizing
			byte[] bytes = content.getBytes();
			String htmlContent = new String(bytes, StandardCharsets.UTF_8);
			htmlContent = FileUtilsLocalServiceUtil.sanitizeHTMLContent(htmlContent);
			bytes = htmlContent.getBytes(StandardCharsets.UTF_8);

			// Update file content
			FileEntry updatedFileEntry = DLAppServiceUtil.updateFileEntry(
					fileEntry.getFileEntryId(),
					fileEntry.getTitle(),
					fileEntry.getMimeType(),
					fileEntry.getTitle(),
					StringPool.BLANK, // urlTitle
					fileEntry.getDescription(),
					StringPool.BLANK, // changeLog
					majorVersion ? DLVersionNumberIncrease.MAJOR : DLVersionNumberIncrease.MINOR,
					bytes, null, null, serviceContext);

			result.put(JSONConstants.FILE_ENTRY_ID, updatedFileEntry.getFileEntryId());
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error saving HTML file " + fileVersionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}