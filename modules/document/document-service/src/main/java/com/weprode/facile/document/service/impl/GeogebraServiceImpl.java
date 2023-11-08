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

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;


import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Base64;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.service.base.GeogebraServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=Geogebra"
	},
	service = AopService.class
)
public class GeogebraServiceImpl extends GeogebraServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(GeogebraServiceImpl.class);

	@JSONWebService(value = "get-geogebra-file", method = "GET")
	public JSONObject getGeogebraFile(long fileVersionId) {

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

		// Get file content
		try {
			DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getDLFileVersion(fileVersionId);
			DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(dlFileVersion.getFileEntryId());
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.info("User " + user.getFullName() + " tries to get geogebra file for fileVersion " + fileVersionId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}

			InputStream is = DLStoreUtil.getFileAsStream(fileEntry.getCompanyId(), fileEntry.getDataRepositoryId(), fileEntry.getName(), dlFileVersion.getStoreFileName());

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[16384];
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
				buffer.flush();
				byte[] byteArray = buffer.toByteArray();
				String content = Base64.encode(byteArray);

				result.put(JSONConstants.CONTENT, content);
				result.put(JSONConstants.NAME, fileEntry.getTitle());
			}
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error while getting geogebra file with fileVersionId " + fileVersionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(value = "save-geogebra-file", method = "POST")
	public JSONObject saveGeogebraFile(String params) {
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
			JSONObject paramMap = new JSONObject(params);

			String fileVersionId = paramMap.getString(JSONConstants.FILE_VERSION_ID);
			String content = paramMap.getString(JSONConstants.CONTENT);

			long fileVersionIdLong = 0;
			if (fileVersionId != null && !fileVersionId.isEmpty()) {
				try {
					fileVersionIdLong = Long.parseLong(fileVersionId);
				} catch (Exception e) {
					logger.info("Error : was not able to convert fileVersionId '" + fileVersionId + "' to long");
				}
			}

			DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getDLFileVersion(fileVersionIdLong);
			DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(dlFileVersion.getFileEntryId());
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.info("User " + user.getFullName() + " tries to save geogebra file for fileVersion " + fileVersionId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}

			// Set default permissions
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setAddGroupPermissions(true);

			// Convert geogebra file to byte array
			byte[] byteArrayContent = Base64.decode(content);

			FileEntry newFileEntry;

			// Update file content
			logger.info("Updating geogebra file '" + fileVersionId + "' for user " + fileEntry.getUserId());
			newFileEntry = DLAppServiceUtil.updateFileEntry(
					fileEntry.getFileEntryId(),
					fileEntry.getTitle(),
					fileEntry.getMimeType(),
					fileEntry.getTitle(),
					StringPool.BLANK, // urlTitle
					fileEntry.getDescription(),
					StringPool.BLANK, // changeLog
					DLVersionNumberIncrease.MINOR, byteArrayContent, null, null, serviceContext);

			logger.info("Created/updated file with Id " + newFileEntry.getFileEntryId());
			result.put(JSONConstants.FILE_ENTRY_ID, newFileEntry.getFileEntryId());
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error saving geogebra file ", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}