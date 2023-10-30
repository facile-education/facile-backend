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
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.service.base.MindmapServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=Mindmap"
	},
	service = AopService.class
)
public class MindmapServiceImpl extends MindmapServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(MindmapServiceImpl.class);

	/**
	 * Returns the content of the given mindmap file
	 * @return JSONObject - the mindmap file name and content
	 */
	@JSONWebService(value = "get-mind-file", method = "GET")
	public JSONObject getMindFile(long fileVersionId) {
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

		logger.info("getMindFile with fileVersionId="+fileVersionId);

		// Get file content
		try {
			DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getDLFileVersion(fileVersionId);
			DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(dlFileVersion.getFileEntryId());
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.info("User " + user.getFullName() + " tries to get mindmap file for fileVersion " + fileVersionId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}

			InputStream is = DLStoreUtil.getFileAsStream(fileEntry.getCompanyId(), fileEntry.getDataRepositoryId(), fileEntry.getName(), dlFileVersion.getStoreFileName());

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[16384];
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			String content = buffer.toString();

			result.put(JSONConstants.CONTENT, content);
			result.put(JSONConstants.NAME, fileEntry.getTitle());
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error while getting file with fileVersionId "+fileVersionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	/**
	 * This method saves a mindmap file
	 * @param params - The map containing fileVersionId, fileName and content
	 * @return JSONObject success or not
	 */
	@JSONWebService(value = "save-mind-file", method = "POST")
	public JSONObject saveMindFile(String params) throws SystemException {
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

		try {
			JSONObject paramMap = new JSONObject(params);

			long fileVersionId = JSONConstants.getLongValue(paramMap, JSONConstants.ID, -1);
			String content = paramMap.getString("xml");

			if (fileVersionId == -1) {
				logger.error("fileEntryId was not provided");
			}
			if (content == null || content.equals("")) {
				logger.error("Empty content");
			}
			logger.info("saveMindFile with fileVersionId=" + fileVersionId);

			DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getDLFileVersion(fileVersionId);
			DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(dlFileVersion.getFileEntryId());

			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.info("User " + user.getFullName() + " tries to save mindmap file for fileVersion " + fileVersionId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}

			// Set default permissions
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setAddGroupPermissions(true);

			// Convert content to byte array
			byte[] byteArrayContent;
			assert content != null;
			byteArrayContent = content.getBytes(StandardCharsets.UTF_8);

			logger.info("Updating mindmap file '" + fileEntry.getName() + "' for userId " + fileEntry.getUserId());

			// Increment minor version
			DLAppServiceUtil.updateFileEntry(
					fileEntry.getFileEntryId(),
					fileEntry.getTitle(),
					fileEntry.getMimeType(),
					fileEntry.getTitle(),
					StringPool.BLANK, // urlTitle
					fileEntry.getDescription(),
					StringPool.BLANK, // changeLog
					DLVersionNumberIncrease.MINOR,
					byteArrayContent,
					null,
					null,
					serviceContext);

			logger.info("Saved mindmap file");
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error saving mindmap file", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}