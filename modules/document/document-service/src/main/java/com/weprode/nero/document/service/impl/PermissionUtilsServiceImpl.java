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
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.nero.document.service.base.PermissionUtilsServiceBaseImpl;

import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=document",
		"json.web.service.context.path=PermissionUtils"
	},
	service = AopService.class
)
public class PermissionUtilsServiceImpl extends PermissionUtilsServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(PermissionUtilsServiceImpl.class);

	@JSONWebService(method = "GET")
	public JSONObject getFilePermissionMatrix (long fileEntryId) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray permissionMatrix = JSONFactoryUtil.createJSONArray();
		result.put(JSONConstants.SUCCESS, false);

		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);

			// Build the role list
			List<String> roleNames = PermissionUtilsLocalServiceUtil.getPermissionRoles(fileEntry.getGroupId());
			// Available actions for DLFileEntry
			List<ResourceAction> dLFileEntryResourceActions = ResourceActionLocalServiceUtil.getResourceActions(DLFileEntry.class.getName());


			for (String roleName : roleNames) {
				Role role = RoleLocalServiceUtil.getRole(fileEntry.getCompanyId(), roleName);

				boolean areRoleActionsEditable = !(role.getRoleId() == RoleUtilsLocalServiceUtil.getCommunityAdministratorRole().getRoleId() ||
						role.getRoleId() == RoleUtilsLocalServiceUtil.getCommunityOwnerRole().getRoleId() ||
						role.getRoleId() == RoleUtilsLocalServiceUtil.getSchoolAdminRole().getRoleId()) ||
						role.getRoleId() == RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId();

				JSONObject roleActions = JSONFactoryUtil.createJSONObject();
				roleActions.put(JSONConstants.ROLE_ID, role.getRoleId());
				roleActions.put(JSONConstants.ROLE_NAME, role.getName());
				roleActions.put(JSONConstants.EDITABLE, areRoleActionsEditable);

				for (ResourceAction resourceAction : dLFileEntryResourceActions) {
					roleActions.put(resourceAction.getActionId(), PermissionUtilsLocalServiceUtil.hasRoleFilePermission(role, fileEntry, resourceAction.getActionId()));
				}

				permissionMatrix.put(roleActions);
			}

			result.put(JSONConstants.PERMISSION_MATRIX, permissionMatrix);
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	@JSONWebService(method = "GET")
	public JSONObject getFolderPermissionMatrix (long folderId) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray permissionMatrix = JSONFactoryUtil.createJSONArray();
		result.put(JSONConstants.SUCCESS, false);

		try {
			Folder folder = DLAppServiceUtil.getFolder(folderId);

			// Build the role list
			List<String> roleNames = PermissionUtilsLocalServiceUtil.getPermissionRoles(folder.getGroupId());
			// Available actions for DLFileEntry
			List<ResourceAction> dLFolderResourceActions = ResourceActionLocalServiceUtil.getResourceActions(DLFolder.class.getName());

			for (String roleName : roleNames) {
				Role role = RoleLocalServiceUtil.getRole(folder.getCompanyId(), roleName);

				boolean areRoleActionsEditable = !(role.getRoleId() == RoleUtilsLocalServiceUtil.getCommunityAdministratorRole().getRoleId() ||
						role.getRoleId() == RoleUtilsLocalServiceUtil.getCommunityOwnerRole().getRoleId() ||
						role.getRoleId() == RoleUtilsLocalServiceUtil.getSchoolAdminRole().getRoleId() ||
						role.getRoleId() == RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId());

				JSONObject roleActions = JSONFactoryUtil.createJSONObject();
				roleActions.put(JSONConstants.ROLE_ID, role.getRoleId());
				roleActions.put(JSONConstants.ROLE_NAME, role.getName());
				roleActions.put(JSONConstants.EDITABLE, areRoleActionsEditable);

				for (ResourceAction resourceAction : dLFolderResourceActions) {
					roleActions.put(resourceAction.getActionId(), PermissionUtilsLocalServiceUtil.hasRoleFolderPermission(role, folder, resourceAction.getActionId()));
				}

				permissionMatrix.put(roleActions);
			}

			result.put(JSONConstants.PERMISSION_MATRIX, permissionMatrix);
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject saveFolderPermissionMatrix (long folderId, String jsonPermissionMatrix, boolean isRecursive) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put(JSONConstants.SUCCESS, false);

		try {
			User user = getGuestOrUser();
			logger.info("User " + user.getFullName() + " saves permission matrix for folderId " + folderId);
			Folder folder = DLAppServiceUtil.getFolder(folderId);

			// Check if the user have the right to update those permissions
			if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.PERMISSIONS)) {
				long scopeGroupId = folder.getGroupId();
				JSONArray permissions = JSONFactoryUtil.createJSONArray(jsonPermissionMatrix);
				PermissionUtilsLocalServiceUtil.validateFullPermission(user, folderId, "folder", permissions, isRecursive, scopeGroupId);

				result.put(JSONConstants.SUCCESS, true);
			} else {
				logger.error("Permission error");
				result.put(JSONConstants.ERROR, "Permission error");
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject saveFilePermissionMatrix (long fileEntryId, String jsonPermissionMatrix) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		result.put(JSONConstants.SUCCESS, false);

		try {
			User user = getGuestOrUser();
			logger.info("User " + user.getFullName() + " saves permission matrix for file  " + fileEntryId);
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);

			// Check if the user have the right to update those permissions
			if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.PERMISSIONS)) {
				long scopeGroupId = fileEntry.getGroupId();
				JSONArray permissions = JSONFactoryUtil.createJSONArray(jsonPermissionMatrix);
				PermissionUtilsLocalServiceUtil.validateFullPermission(user, fileEntryId, "file", permissions, false, scopeGroupId);

				result.put(JSONConstants.SUCCESS, true);
			} else {
				logger.error("Permission error");
				result.put(JSONConstants.ERROR, "Permission error");
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return result;
	}

}