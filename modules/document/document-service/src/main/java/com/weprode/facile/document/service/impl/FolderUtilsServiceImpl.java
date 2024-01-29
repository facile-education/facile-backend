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

import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.persistence.DLFileVersionUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.base.FolderUtilsServiceBaseImpl;
import com.weprode.facile.document.utils.DocumentUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=FolderUtils"
	},
	service = AopService.class
)
public class FolderUtilsServiceImpl extends FolderUtilsServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(FolderUtilsServiceImpl.class);

	@JSONWebService(value = "get-breadcrumb", method = "GET")
	public JSONObject getBreadcrumb(long folderId) {

		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}

			if (DLAppServiceUtil.getFolder(folderId).getUserId() != user.getUserId()) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " gets breadcrumb for folder " + folderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " gets breadcrumb for folder " + folderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			List<Folder> folderList = FolderUtilsLocalServiceUtil.getFolderPath(folderId);

			// Format breadCrumb
			final JSONArray folderBreadcrumb = new JSONArray();
			int space = DocumentUtil.getDocumentSpace(folderList.get(0));
			for (int i = 0 ; i < folderList.size() ; i++) {
				Folder breadcrumbFolder = folderList.get(i);
				JSONObject jsonFolder = FolderUtilsLocalServiceUtil.format(user.getUserId(), breadcrumbFolder, space, false);
				// Replace root personal folder name
				if (i == 0) {
					jsonFolder.put(JSONConstants.NAME, "Personnels");
				}
				folderBreadcrumb.put(jsonFolder);
			}
			result.put(JSONConstants.BREADCRUMB, folderBreadcrumb);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	@JSONWebService(value = "create-folder", method = "POST")
	public JSONObject createFolder(long targetFolderId, String folderName) throws SystemException {
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
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), targetFolderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " creates folder in folder " + targetFolderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			Folder createdFolder = FolderUtilsLocalServiceUtil.createFolder(user, targetFolderId, folderName);

			result.put(JSONConstants.CREATED_FOLDER, FolderUtilsLocalServiceUtil.format(user.getUserId(), createdFolder, DocumentConstants.PRIVATE));
			result.put(JSONConstants.SUCCESS, true);

		} catch (FileNameException e) {
			result.put(JSONConstants.ERROR, JSONConstants.DUPLICATE_FILE_EXCEPTION);
		} catch (Exception e) {
			result.put(JSONConstants.ERROR, e.getMessage());
			logger.error("Error creating folder " + folderName + " in folder " + targetFolderId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject renameFolder(long folderId, String folderName) {
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
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " renames folder " + folderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			Folder folderToRename = DLAppServiceUtil.getFolder(folderId);
			Folder renamedFolder = FolderUtilsLocalServiceUtil.renameFolder(user.getUserId(), folderToRename, folderName);

			result.put(JSONConstants.FOLDER, FolderUtilsLocalServiceUtil.format(user.getUserId(), renamedFolder));
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error renaming folder " + folderId + " into " + folderName, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}


	@JSONWebService(value = "get-all-entities", method = "GET")
	public JSONObject getAllEntities(long folderId, boolean withDetails) {
		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}

			if (DLAppServiceUtil.getFolder(folderId).getUserId() != user.getUserId()) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " gets all entities of folder " + folderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		String[] mimeTypes = new String[0];
		return this.getAllEntities(folderId, withDetails, mimeTypes);
	}

	@JSONWebService(value = "get-images-entities", method = "GET")
	public JSONObject getImagesEntities(long folderId, boolean withDetails) {
		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}

			if (DLAppServiceUtil.getFolder(folderId).getUserId() != user.getUserId()) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " gets image entities of folder " + folderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		String[] mimeTypes = DocumentConstants.IMAGE_MIME_TYPES;
		return this.getAllEntities(folderId, withDetails, mimeTypes);
	}

	private JSONObject getAllEntities (long folderId, boolean withDetails, String[] mimeTypes) {
		final JSONObject result = new JSONObject();

		try {
			final JSONArray folderItems = new JSONArray();
			final JSONArray fileItems = new JSONArray();

			User user = getGuestOrUser();
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + user.getFullName() + " gets all entities of folder " + folderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			// Refresh file version to fix shared cache
			DLFileVersionUtil.clearCache();

			// Get root node
			int space = DocumentUtil.getSpace(DLAppServiceUtil.getFolder(folderId), user.getUserId());

			// SubFolders
			List<Folder> folderList = DLAppServiceUtil.getFolders(user.getGroupId(), folderId);
			for (Folder subFolder : folderList) {
				if (!subFolder.getName().startsWith(".")) {
					JSONObject curr = FolderUtilsLocalServiceUtil.format(user.getUserId(), subFolder, space, withDetails);
					folderItems.put(curr);
				}
			}
			result.put(JSONConstants.SUB_FOLDERS, folderItems);

			// Files
			List<FileEntry> fileList;
			if (mimeTypes.length > 0) {
				fileList = DLAppServiceUtil.getFileEntries(user.getGroupId(), folderId, mimeTypes);
			} else {
				fileList = DLAppServiceUtil.getFileEntries(user.getGroupId(), folderId);
			}
			for (FileEntry fileEntry : fileList) {
				// If user is allowed do view the document (in group documents only)
				if (!fileEntry.getTitle().startsWith(".")) {
					JSONObject curr = FileUtilsLocalServiceUtil.format(user.getUserId(), fileEntry, space, withDetails);
					fileItems.put(curr);
				}
			}
			result.put(JSONConstants.FILES, fileItems);
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error getting all entities of folder " + folderId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}