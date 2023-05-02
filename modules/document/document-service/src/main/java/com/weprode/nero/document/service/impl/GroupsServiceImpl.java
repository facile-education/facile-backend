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

import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.portal.aop.AopService;

import org.json.JSONArray;

import org.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.constants.DocumentConstants;
import com.weprode.nero.document.constants.PermissionConstants;
import com.weprode.nero.document.exception.NoSuchVersionException;
import com.weprode.nero.document.model.Version;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.document.service.GroupsLocalServiceUtil;
import com.weprode.nero.document.service.VersionLocalServiceUtil;
import com.weprode.nero.document.service.base.GroupsServiceBaseImpl;

import com.weprode.nero.document.utils.DLAppJsonFactory;
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

	@JSONWebService(method = "GET")
	public JSONObject getGroupEntities(String nodePath) {

		JSONObject result = new JSONObject();
		result.put(JSONConstants.SUCCESS, false);

		try {
			User user = getGuestOrUser();
			logger.info("User " + user.getFullName() + " fetches group entities for folder " + nodePath);
			JSONArray folderArray;
			JSONArray fileArray = null;

			if (nodePath.equals(COLLABORATIVE)) {
				// This is Collaborative root : return personal and institutional groups

				folderArray = GroupsLocalServiceUtil.getUserGroupsFolders(user);

			} else {
				// Specific group's folderId
				List<Folder> folders = GroupsLocalServiceUtil.getFolderGroupFolders(user, parseLong(nodePath));
				List<FileEntry> files = GroupsLocalServiceUtil.getFolderGroupFiles(user, parseLong(nodePath));
				folderArray = GroupsLocalServiceUtil.groupFoldersToJSON(user, folders, false);
				fileArray = GroupsLocalServiceUtil.groupFilesToJSON(user, files, true);
			}

			result.put(JSONConstants.SUCCESS, true);
			result.put(JSONConstants.FOLDERS, folderArray);
			result.put(JSONConstants.FILES, fileArray);

		} catch (Exception e) {
			logger.error("Error fetching group entities for folder " + nodePath, e);
		}
		return result;
	}

	@JSONWebService(method = "GET")
	public JSONObject getGroupBreadcrumb (String nodePath) {
		JSONObject result = new JSONObject();
		result.put(JSONConstants.SUCCESS, false);

		try {
			User user = getGuestOrUser();
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
				List<Folder> folders = FolderUtilsLocalServiceUtil.getFolderPath(parseLong(nodePath));

				for (Folder groupFolder : folders) {
					breadCrumb.put(DLAppJsonFactory.format(user, groupFolder, DocumentConstants.COLLABORATIVE, false));
				}
			}

			result.put(JSONConstants.SUCCESS, true);
			result.put(JSONConstants.BREAD_CRUMB, breadCrumb);

		} catch (Exception e) {
			logger.error("Error getting group breadcrump for nodePath " + nodePath, e);
			result.put(JSONConstants.SUCCESS, false);
		}
		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject recordDownloadActivity (long fileEntryId, long versionId) {
		JSONObject result = new JSONObject();
		result.put(JSONConstants.SUCCESS, false);

		try {
			User user = getGuestOrUser();

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
		result.put(JSONConstants.SUCCESS, false);

		try {
			User user = getGuestOrUser();

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