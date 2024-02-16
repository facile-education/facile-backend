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
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.document.service.base.ScratchServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=Scratch"
	},
	service = AopService.class
)
public class ScratchServiceImpl extends ScratchServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(ScratchServiceImpl.class);

	/**
	 * Returns the scratch files in the user's schoolbag
	 * @return JSONObject with all user's scratch files
	 */
	@JSONWebService(value = "get-scratch-files", method = "GET")
	public JSONObject getScratchFiles(long userId) {
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
			// Get all .scratch documents in schoolbag's root directory
			JSONArray scratchFiles = new JSONArray();
			Folder rootFolder = FolderUtilsLocalServiceUtil.getUserRootFolder(user.getUserId());
			for (FileEntry fileEntry : DLAppServiceUtil.getFileEntries(rootFolder.getGroupId(), rootFolder.getFolderId())) {
				if (fileEntry.getTitle().endsWith(".scratch")){
					JSONObject scratchFile = new JSONObject();
					scratchFile.put(JSONConstants.FILE_ENTRY_ID, fileEntry.getFileEntryId());
					scratchFile.put(JSONConstants.NAME, fileEntry.getTitle());
					scratchFiles.put(scratchFile);
				}
			}
			result.put(JSONConstants.SCRATCH_FILES, scratchFiles);
			result.put(JSONConstants.SUCCESS, true);

		} catch(Exception e){
			logger.error("Error while getting scratch files for userId " + userId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	/**
	 * Returns the content of the given scratch file
	 * @return JSONObject - the scratch file name and content
	 */
	@JSONWebService(value = "get-scratch-file", method = "GET")
	public JSONObject getScratchFile(long fileVersionId) {
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
			DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(dlFileVersion.getFileEntryId());
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " gets scratch file " + fileVersionId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " gets scratch file " + fileVersionId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			try (InputStream is = DLFileEntryLocalServiceUtil.getFileAsStream(fileEntry.getFileEntryId(), dlFileVersion.getVersion());
				 ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
				int nRead;
				byte[] data = new byte[16384];
				while ((nRead = is.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, nRead);
				}
				buffer.flush();
				byte[] byteArray = buffer.toByteArray();
				String content = Base64.encode(byteArray);

				result.put(JSONConstants.CONTENT, content);
			}
			result.put(JSONConstants.NAME, fileEntry.getTitle());
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error while getting file with fileVersionId " + fileVersionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	/**
	 * This method saves a scratch file in the user's schoolbag
	 * @param params - The map containing userId, fileEntryId, fileName and content
	 * @return JSONObject success or not
	 */
	@JSONWebService(value = "save-scratch-file", method = "POST")
	public JSONObject saveScratchFile(String params) {
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

			// Param is called 'fileEntryId' but it is a fileVersionId
			long fileVersionId = JSONConstants.getLongValue(paramMap, JSONConstants.FILE_ENTRY_ID, -1);
			String fileName = paramMap.getString(JSONConstants.FILE_NAME);
			String content = paramMap.getString(JSONConstants.CONTENT);

			if(!fileName.isEmpty() && !fileName.endsWith(".sb3")) {
				fileName = fileName.concat(".sb3");
			}

			logger.info("User " + user.getFullName() + " saves scratch fileVersionId " + fileVersionId);

			// Handle the file - If provided, this is an update, else this is a file creation
			FileEntry fileEntry = null;
			if (fileVersionId > -1) {
				try {
					DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getDLFileVersion(fileVersionId);
					fileEntry = DLAppServiceUtil.getFileEntry(dlFileVersion.getFileEntryId());
				} catch (Exception e) {
					logger.info("Was not able to convert fileVersionId '" + fileVersionId + "' to long : might be a file creation");
				}
			}

			// Set default permissions
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setAddGroupPermissions(true);

			// Convert scratch file to byte array
			byte[] byteArrayContent = Base64.decode(content);

			FileEntry newFileEntry;

			if (fileEntry == null) {
				// Create new file
				logger.info("Creating new scratch file '" + fileName + "' for user " + user.getFullName());
				Folder rootFolder = FolderUtilsLocalServiceUtil.getUserRootFolder(user.getUserId());
				newFileEntry = DLAppServiceUtil.addFileEntry(
						StringPool.BLANK, // externalReferenceCode
						rootFolder.getGroupId(),
						rootFolder.getFolderId(),
						fileName,
						MimeTypesUtil.getContentType(fileName),
						fileName,
						StringPool.BLANK, // urlTitle
						StringPool.BLANK, // description
						StringPool.BLANK, // changeLog
						byteArrayContent,
						null,
						null,
						serviceContext);

			} else {
				fileName = fileName.isEmpty() ?  fileEntry.getTitle() : fileName;
				if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
					logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " saves scratch file " + fileVersionId);
					return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
				}
				if (!PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.VIEW) || !PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.UPDATE)) {
					logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " saves scratch file " + fileVersionId);
					return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
				}
				// Update file content
				logger.info("Updating scratch file " + fileName);
				newFileEntry = DLAppServiceUtil.updateFileEntry(
						fileEntry.getFileEntryId(),
						fileName,
						fileEntry.getMimeType(),
						fileName,
						StringPool.BLANK, // urlTitle
						fileEntry.getDescription(),
						StringPool.BLANK, // changeLog
						DLVersionNumberIncrease.MINOR,
						byteArrayContent,
						null,
						null,
						serviceContext);

			}
			result.put(JSONConstants.SUCCESS, true);
			logger.info("Created/updated file with Id " + newFileEntry.getFileEntryId());
		} catch (Exception e) {
			logger.error("Error saving scratch file", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}