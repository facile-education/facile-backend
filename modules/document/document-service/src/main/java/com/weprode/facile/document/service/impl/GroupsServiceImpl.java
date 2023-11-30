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
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.document.constants.PermissionConstants;
import com.weprode.facile.document.exception.NoSuchVersionException;
import com.weprode.facile.document.model.Version;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.GroupsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.document.service.VersionLocalServiceUtil;
import com.weprode.facile.document.service.base.GroupsServiceBaseImpl;
import com.weprode.facile.document.utils.DLAppJsonFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.List;

import static java.lang.Long.parseLong;

@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=Groups"
	},
	service = AopService.class
)
public class GroupsServiceImpl extends GroupsServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(GroupsServiceImpl.class);

	private static final String COLLABORATIVE = "collaborative";

	@JSONWebService(value = "get-group-entities", method = "GET")
	public JSONObject getGroupEntities(String nodePath) {
		String[] mimeTypes = new String[0];
		return this.getGroupEntities(nodePath, mimeTypes);
	}

	@JSONWebService(value = "get-group-images", method = "GET")
	public JSONObject getGroupImages(String nodePath) {
		String[] mimeTypes = DocumentConstants.IMAGE_MIME_TYPES;
		return this.getGroupEntities(nodePath, mimeTypes);
	}

	private JSONObject getGroupEntities(String nodePath, String[] mimeTypes) {

		JSONObject result = new JSONObject();
		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}

			if (!nodePath.equals(COLLABORATIVE)) {
				Folder folder = DLAppServiceUtil.getFolder(parseLong(nodePath));
				Group group = GroupLocalServiceUtil.getGroup(folder.getGroupId());
				if (group.isUserGroup()) {
					return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
				}
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			logger.info("User " + user.getFullName() + " fetches group entities for folder " + nodePath);
			JSONArray folderArray;
			JSONArray fileArray = null;

			if (nodePath.equals(COLLABORATIVE)) {
				// This is Collaborative root : return personal and institutional groups

				folderArray = GroupsLocalServiceUtil.getUserGroupsFolders(user);

			} else {
				// Specific group's folderId
				long folderId = parseLong(nodePath);
				Folder folder = DLAppServiceUtil.getFolder(folderId);
				if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
					logger.info("User " + user.getFullName() + " tries to fetch content of folderId " + folderId + " but has no permission");
					return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
				}
				if (FolderUtilsLocalServiceUtil.isGroupFolder(folder) &&
						!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(getGuestOrUserId(), folder, ActionKeys.VIEW)) {
					logger.info("User " + user.getFullName() + " tries to get all group entities of folder " + folderId + " but has no permission");
					return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
				}
				List<Folder> folders = GroupsLocalServiceUtil.getFolderGroupFolders(user, folderId);
				List<FileEntry> files = GroupsLocalServiceUtil.getFolderGroupFiles(user, folderId, mimeTypes);
				folderArray = GroupsLocalServiceUtil.groupFoldersToJSON(user, folders, false);
				fileArray = GroupsLocalServiceUtil.groupFilesToJSON(user, files, true);
			}

			result.put(JSONConstants.SUCCESS, true);
			result.put(JSONConstants.FOLDERS, folderArray);
			result.put(JSONConstants.FILES, fileArray);

		} catch (Exception e) {
			logger.error("Error fetching group entities for folder " + nodePath, e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(method = "GET")
	public JSONObject getGroupBreadcrumb (String nodePath) {
		JSONObject result = new JSONObject();

		User user;
		try {
			user = getGuestOrUser();
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}

			if (!nodePath.equals(COLLABORATIVE)) {
				long folderId = parseLong(nodePath);
				Folder folder = DLAppServiceUtil.getFolder(folderId);
				Group group = GroupLocalServiceUtil.getGroup(folder.getGroupId());
				if (group.isUserGroup()) {
					return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
				}
				if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
					logger.info("User " + user.getFullName() + " tries to fetch content of folderId " + folderId + " but has no permission");
					return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
				}
			}
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			JSONArray breadCrumb;
			final JSONObject groupPermissions = new JSONObject();
			groupPermissions.put(PermissionConstants.ADD_OBJECT, false);
			groupPermissions.put(ActionKeys.DELETE, false);
			groupPermissions.put(ActionKeys.PERMISSIONS, false);

			// Group root
			breadCrumb = new JSONArray();
			JSONObject rootGroup = new JSONObject();
			rootGroup.put(JSONConstants.ID, COLLABORATIVE);
			rootGroup.put(JSONConstants.NAME, "Collaboratifs");
			rootGroup.put(JSONConstants.TYPE, "Group");
			rootGroup.put(JSONConstants.PERMISSIONS, groupPermissions);
			breadCrumb.put(rootGroup);

			if (!nodePath.equals(COLLABORATIVE)) {
				// Check that the user is allowed to access to this path
				Folder folder = DLAppServiceUtil.getFolder(parseLong(nodePath));
				if (FolderUtilsLocalServiceUtil.isGroupFolder(folder) &&
						!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(getGuestOrUserId(), folder, ActionKeys.VIEW)) {
					logger.info("User " + user.getFullName() + " tries to get breadcrumb of folder " + nodePath + " but has no permission");
					return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
				}

				List<Folder> folders = FolderUtilsLocalServiceUtil.getFolderPath(parseLong(nodePath));

				for (Folder groupFolder : folders) {
					breadCrumb.put(DLAppJsonFactory.format(user, groupFolder, DocumentConstants.COLLABORATIVE, false));
				}
			}

			result.put(JSONConstants.SUCCESS, true);
			result.put(JSONConstants.BREAD_CRUMB, breadCrumb);

		} catch (Exception e) {
			logger.error("Error getting group breadcrumb for nodePath " + nodePath, e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject recordDownloadActivity (long fileEntryId, long versionId) {
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
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.info("User " + user.getFullName() + " tries to record download activity of file " + fileEntry.getFileEntryId() + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			Folder folder = DLAppServiceUtil.getFolder(fileEntry.getFolderId());
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.VIEW)) {
				logger.info("User " + user.getFullName() + " tries to record download activity of file " + fileEntry.getFileEntryId() + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			if (versionId == 0) {
				versionId = DLFileVersionLocalServiceUtil.getLatestFileVersion(user.getUserId(), fileEntryId).getFileVersionId();
			}

			Version fileVersion;
			try {
				fileVersion = VersionLocalServiceUtil.getVersionByFileEntryId(fileEntryId, versionId);
			} catch (NoSuchVersionException e) {
				fileVersion = VersionLocalServiceUtil.addVersion(fileEntryId, versionId, "", 0, 0);
			}

			VersionLocalServiceUtil.incrementDownloadCount(fileVersion);
			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error recording download activity for fileEntryId " + fileEntryId + " and versionId " + versionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject recordViewActivity (long fileEntryId, long versionId) {
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
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.info("User " + user.getFullName() + " tries to record view activity of file " + fileEntry.getFileEntryId() + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			Folder folder = DLAppServiceUtil.getFolder(fileEntry.getFolderId());
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.VIEW)) {
				logger.info("User " + user.getFullName() + " tries to record view activity of file " + fileEntry.getFileEntryId() + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			if (versionId == 0) {
				versionId = DLFileVersionLocalServiceUtil.getLatestFileVersion(user.getUserId(), fileEntryId).getFileVersionId();
			}

			Version fileVersion;
			try {
				fileVersion = VersionLocalServiceUtil.getVersionByFileEntryId(fileEntryId, versionId);
			} catch (NoSuchVersionException e) {
				fileVersion = VersionLocalServiceUtil.addVersion(fileEntryId, versionId, "", 0, 0);
			}

			VersionLocalServiceUtil.incrementViewCount(fileVersion);

			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error recording view activity for fileEntryId " + fileEntryId + " and versionId " + versionId, e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}