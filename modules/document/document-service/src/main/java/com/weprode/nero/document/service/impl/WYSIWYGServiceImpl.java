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

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.nero.document.service.base.WYSIWYGServiceBaseImpl;

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
		JSONObject result = JSONFactoryUtil.createJSONObject();

		result.put(JSONConstants.SUCCESS, true);

		try {
			DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getDLFileVersion(fileVersionId);
			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(dlFileVersion.getFileEntryId());
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(dlFileVersion.getFileEntryId());

			// Check permissions
			if (FileUtilsLocalServiceUtil.isGroupFile(fileEntry.getFileEntryId()) && !PermissionUtilsLocalServiceUtil.hasUserFilePermission(getGuestOrUserId(), fileEntry, ActionKeys.VIEW)) {
				result.put(JSONConstants.SUCCESS, false);
				result.put(JSONConstants.CONTENT, StringPool.BLANK);
				return result;
			}

			// Get file content
			InputStream is = DLStoreUtil.getFileAsStream(dlFileEntry.getCompanyId(), dlFileEntry.getDataRepositoryId(), dlFileEntry.getName(), dlFileVersion.getVersion());
			String content = StringUtil.read(is);

			try {
				is.close();
			} catch (IOException e) {
				logger.error("Error closing inputstream", e);
			}

			result.put(JSONConstants.CONTENT, content);
			result.put(JSONConstants.NAME, fileEntry.getTitle());

		} catch (Exception e) {
			logger.error("Error while getting file with fileVersionId " + fileVersionId, e);
		}

		return result;
	}

	@JSONWebService(value = "save-html-content", method = "POST")
	public JSONObject saveHTMLContent(Long fileVersionId, String content, Boolean majorVersion) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			User user = getGuestOrUser();
			logger.info("User " + user.getFullName() + " saves HTML content for fileVersionId " + fileVersionId);

			DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getDLFileVersion(fileVersionId);
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(dlFileVersion.getFileEntryId());

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