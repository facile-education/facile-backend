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
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.commons.FacileLogger;
import com.weprode.facile.commons.JSONProxy;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
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
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.document.service.base.PermissionUtilsServiceBaseImpl;

import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			FacileLogger.registerUser(user);
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets permission matrix of file " + fileEntry);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			if (!PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.PERMISSIONS)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets permission matrix of file " + fileEntry);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			FacileLogger.registerUser(user);
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}

		try {
			Folder folder = DLAppServiceUtil.getFolder(folderId);

			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets permission matrix of folder " + folderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.PERMISSIONS)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " gets permission matrix of folder " + folderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			FacileLogger.registerUser(user);
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), folderId)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " saves permission matrix of folder " + folderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			Folder folder = DLAppServiceUtil.getFolder(folderId);
			if (!PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.PERMISSIONS)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " saves permission matrix of folder " + folderId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

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
			if (user == null || user.getUserId() == UserLocalServiceUtil.getGuestUserId(PortalUtil.getDefaultCompanyId())) {
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
			}
			FacileLogger.registerUser(user);
		} catch (Exception e) {
			return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
		}
		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
			if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(user.getUserId(), fileEntry.getFolderId())) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " saves permission matrix of file " + fileEntryId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}
			if (!PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.VIEW) && !PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.PERMISSIONS)) {
				logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + user.getFullName() + " saves permission matrix of file " + fileEntryId);
				return JSONProxy.getJSONReturnInErrorCase(JSONConstants.NOT_ALLOWED_EXCEPTION);
			}

			long scopeGroupId = fileEntry.getGroupId();
			JSONArray permissions = new JSONArray(jsonPermissionMatrix);
			PermissionUtilsLocalServiceUtil.validateFullPermission(user, fileEntryId, "file", permissions, false, scopeGroupId);

			result.put(JSONConstants.SUCCESS, true);

		} catch (Exception e) {
			logger.error("Error saving file permission matrix", e);
			result.put(JSONConstants.SUCCESS, false);
		}

		return result;
	}

}