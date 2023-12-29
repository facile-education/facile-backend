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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.document.service.base.GroupsLocalServiceBaseImpl;
import com.weprode.facile.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.facile.organization.constants.OrgConstants;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
	property = "model.class.name=com.weprode.facile.document.model.Groups",
	service = AopService.class
)
public class GroupsLocalServiceImpl extends GroupsLocalServiceBaseImpl {

	private final Log logger = LogFactoryUtil.getLog(GroupsLocalServiceImpl.class);

	public JSONArray getUserGroupsFolders (User user) throws SystemException, PortalException {
		JSONArray userGroupsArray = new JSONArray();

		List<Group> userGroups = CommunityInfosLocalServiceUtil.getUserCommunities(user.getUserId(), false, true);
		for (Group userGroup : userGroups) {
			Folder groupRootFolder = FolderUtilsLocalServiceUtil.getOrCreateGroupRootFolder(userGroup.getGroupId());
			// We may not have VIEW access to group's root folder
			if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), groupRootFolder, ActionKeys.VIEW)) {
				JSONObject jsonFolder = FolderUtilsLocalServiceUtil.format(user, groupRootFolder, DocumentConstants.COLLABORATIVE, false);
				userGroupsArray.put(jsonFolder);
			}
		}

		// Volees are not displayed in Collaborative
		// Classes, groups, cours, subject and school have their names unchanged
		List<Integer> types = new ArrayList<>();
		// Parents see only the school group
		// Other profiles see other groups except volees
		if (RoleUtilsLocalServiceUtil.isParent(user)) {
			types.add(OrgConstants.SCHOOL_TYPE);
		} else {
			types.add(OrgConstants.SCHOOL_TYPE);
			types.add(OrgConstants.CLASS_TYPE);
			types.add(OrgConstants.COURS_TYPE);
			types.add(OrgConstants.SUBJECT_TYPE);
		}

		List<Organization> organizations = UserOrgsLocalServiceUtil.getUserOrganizations(user.getUserId(), types, false, OrgConstants.ALL_SCHOOLS_ID);

		// Doyens, psys and conseillers sociaux see their classes
		organizations.addAll(UserOrgsLocalServiceUtil.getRoleAffectedClasses(user));

		List<Long> uniqueOrgsIds = new ArrayList<>(); // Avoid duplicates
		for (Organization userOrg : organizations) {
			if (!uniqueOrgsIds.contains(userOrg.getOrganizationId())) {
				uniqueOrgsIds.add(userOrg.getOrganizationId());
				Folder groupRootFolder = FolderUtilsLocalServiceUtil.getOrCreateGroupRootFolder(userOrg.getGroupId());
				// We may not have VIEW access to group's root folder
				if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), groupRootFolder, ActionKeys.VIEW)) {
					JSONObject jsonFolder = FolderUtilsLocalServiceUtil.format(user, groupRootFolder, DocumentConstants.COLLABORATIVE, false);
					userGroupsArray.put(jsonFolder);
				}
			}
		}

		return userGroupsArray;
	}

	public List<Folder> getFolderGroupFolders (User user, long folderId) throws SystemException, PortalException {
		final Folder currFolder = DLAppServiceUtil.getFolder(folderId);

		// Sub Folders
		List<Folder> subFolders = new ArrayList<>(DLAppServiceUtil.getFolders(currFolder.getGroupId(), currFolder.getFolderId()));
		List<Folder> groupFoldersToReturn = new ArrayList<>();

		for (Folder subFolder : subFolders) {
			// Check the view permission
			Group folderGroup = GroupLocalServiceUtil.getGroup(subFolder.getGroupId());
			if (!subFolder.getName().startsWith(".") && (folderGroup.isUser()
					|| PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), subFolder, ActionKeys.VIEW))) {
				groupFoldersToReturn.add(subFolder);
			}
		}

		return groupFoldersToReturn;
	}

	public List<FileEntry> getFolderGroupFiles (User user, long folderId, String[] mimeTypes) throws SystemException, PortalException {
		final Folder currFolder = DLAppServiceUtil.getFolder(folderId);

		// Files
		List<FileEntry> fileEntryList;
		if (mimeTypes.length > 0) {
			fileEntryList = new ArrayList<>(DLAppServiceUtil.getFileEntries(currFolder.getGroupId(), folderId, mimeTypes));
		} else {
			fileEntryList = new ArrayList<>(DLAppServiceUtil.getFileEntries(currFolder.getGroupId(), folderId));
		}
		List<FileEntry> groupFilesToReturn = new ArrayList<>();

		for (FileEntry file : fileEntryList) {
			// Check the view permission
			Group fileGroup = GroupLocalServiceUtil.getGroup(file.getGroupId());
			if (!file.getTitle().startsWith(".") && (fileGroup.isUser()
					|| PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), file, ActionKeys.VIEW))){
				groupFilesToReturn.add(file);
			}
		}

		return groupFilesToReturn;
	}

	public JSONArray groupFoldersToJSON (User user, List<Folder> groupFolders, Boolean withDetails) {
		JSONArray foldersArray = new JSONArray();

		for (Folder groupFolder : groupFolders) {
			foldersArray.put(FolderUtilsLocalServiceUtil.format(user, groupFolder, DocumentConstants.COLLABORATIVE, withDetails));
		}

		return foldersArray;
	}

	public JSONArray groupFilesToJSON (User user, List<FileEntry> groupFiles, Boolean withDetails) {
		JSONArray filesArray = new JSONArray();

		for (FileEntry groupFile : groupFiles) {
			filesArray.put(FileUtilsLocalServiceUtil.format(user, groupFile, DocumentConstants.COLLABORATIVE, withDetails));
		}

		return filesArray;
	}

}