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

import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import org.json.JSONArray;

import org.json.JSONObject;
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
		JSONObject result = new JSONObject();

		JSONArray permissionMatrix = new JSONArray();
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
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.info("User " + user.getFullName() + " tries to access permission matrix on file " + fileEntry.getFileEntryId() + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}

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

				JSONObject roleActions = new JSONObject();
				roleActions.put(JSONConstants.ROLE_ID, role.getRoleId());
				roleActions.put(JSONConstants.ROLE_NAME, role.getTitle(user.getLocale()));
				roleActions.put(JSONConstants.EDITABLE, areRoleActionsEditable);

				for (ResourceAction resourceAction : dLFileEntryResourceActions) {
					roleActions.put(resourceAction.getActionId(), PermissionUtilsLocalServiceUtil.hasRoleFilePermission(role, fileEntry, resourceAction.getActionId()));
				}

				permissionMatrix.put(roleActions);
			}

			result.put(JSONConstants.PERMISSION_MATRIX, permissionMatrix);
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			logger.error("Error fetching file permission matrix", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "GET")
	public JSONObject getFolderPermissionMatrix (long folderId) {
		JSONObject result = new JSONObject();

		JSONArray permissionMatrix = new JSONArray();
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
			Folder folder = DLAppServiceUtil.getFolder(folderId);

			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.info("User " + user.getFullName() + " tries to access permissions for folder " + folderId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.PERMISSIONS)) {
				logger.info("User " + user.getFullName() + " tries to access permissions for folder " + folderId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}

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

				JSONObject roleActions = new JSONObject();
				roleActions.put(JSONConstants.ROLE_ID, role.getRoleId());
				roleActions.put(JSONConstants.ROLE_NAME, role.getTitle(user.getLocale()));
				roleActions.put(JSONConstants.EDITABLE, areRoleActionsEditable);

				for (ResourceAction resourceAction : dLFolderResourceActions) {
					roleActions.put(resourceAction.getActionId(), PermissionUtilsLocalServiceUtil.hasRoleFolderPermission(role, folder, resourceAction.getActionId()));
				}

				permissionMatrix.put(roleActions);
			}

			result.put(JSONConstants.PERMISSION_MATRIX, permissionMatrix);
			result.put(JSONConstants.SUCCESS, true);
		} catch (Exception e) {
			result.put(JSONConstants.SUCCESS, false);
			logger.error("Error fetching folder permission matrix", e);
		}

		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject saveFolderPermissionMatrix (long folderId, String jsonPermissionMatrix, boolean isRecursive) {
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
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.info("User " + user.getFullName() + " tries to save permissions for folder " + folderId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			logger.info("User " + user.getFullName() + " saves permission matrix for folderId " + folderId);
			Folder folder = DLAppServiceUtil.getFolder(folderId);

			// Check if the user have the right to update those permissions
			if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.PERMISSIONS)) {
				long scopeGroupId = folder.getGroupId();
				JSONArray permissions = new JSONArray(jsonPermissionMatrix);
				PermissionUtilsLocalServiceUtil.validateFullPermission(user, folderId, "folder", permissions, isRecursive, scopeGroupId);

				result.put(JSONConstants.SUCCESS, true);
			} else {
				logger.error("Permission error");
				result.put(JSONConstants.ERROR, "Permission error");
			}
		} catch (Exception e) {
			logger.error("Error saving folder permission matrix", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

	@JSONWebService(method = "POST")
	public JSONObject saveFilePermissionMatrix (long fileEntryId, String jsonPermissionMatrix) {
		JSONObject result = new JSONObject();

		result.put(JSONConstants.SUCCESS, false);

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
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.info("User " + user.getFullName() + " tries to save matrix permissions for file " + fileEntryId + " but has no permission");
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			logger.info("User " + user.getFullName() + " saves permission matrix for file  " + fileEntryId);

			// Check if the user have the right to update those permissions
			if (PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.PERMISSIONS)) {
				long scopeGroupId = fileEntry.getGroupId();
				JSONArray permissions = new JSONArray(jsonPermissionMatrix);
				PermissionUtilsLocalServiceUtil.validateFullPermission(user, fileEntryId, "file", permissions, false, scopeGroupId);

				result.put(JSONConstants.SUCCESS, true);
			} else {
				logger.error("Permission error");
				result.put(JSONConstants.ERROR, "Permission error");
			}
		} catch (Exception e) {
			logger.error("Error saving file permission matrix", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}