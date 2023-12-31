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
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PortalUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionUtil;
import com.weprode.facile.document.constants.PermissionConstants;
import com.weprode.facile.document.service.base.PermissionUtilsLocalServiceBaseImpl;

import com.weprode.facile.role.constants.NeroRoleConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.*;

@Component(
	property = "model.class.name=com.weprode.facile.document.model.PermissionUtils",
	service = AopService.class
)
public class PermissionUtilsLocalServiceImpl
	extends PermissionUtilsLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(PermissionUtilsLocalServiceImpl.class);

	public boolean hasUserFilePermission(long userId, FileEntry fileEntry, String actionId) {

		// Check owner
		if (userId == fileEntry.getUserId()) {
			return true;
		}
		try {
			User user = UserLocalServiceUtil.getUser(userId);
			if (user.getGroupId() == fileEntry.getGroupId()) {
				return true;
			}
		} catch (Exception e) {
			// Nothing
		}

		try {
			long[] userRoleIds = getRolesIdsToCheckForUserPermissions(userId, fileEntry.getGroupId(), fileEntry.getUserId());

			return ResourcePermissionLocalServiceUtil.hasResourcePermission(fileEntry.getCompanyId(), DLFileEntry.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(fileEntry.getFileEntryId()), userRoleIds , actionId);
		} catch (Exception e) {
			logger.error("Error fetching user " + userId + " permission on file " + fileEntry.getFileEntryId() + " for action " + actionId, e);
			return false;
		}
	}

	public boolean hasUserFolderPermission(long userId, Folder folder, String actionId) {
		// Check owner
		if (userId == folder.getUserId()) {
			return true;
		}
		try {
			User user = UserLocalServiceUtil.getUser(userId);
			if (user.getGroupId() == folder.getGroupId()) {
				return true;
			}
		} catch (Exception e) {
			// Nothing
		}

		try {
			long[] userRoleIds = getRolesIdsToCheckForUserPermissions(userId, folder.getGroupId(), folder.getUserId());

			return ResourcePermissionLocalServiceUtil.hasResourcePermission(folder.getCompanyId(), DLFolder.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(folder.getFolderId()), userRoleIds , actionId);
		} catch (Exception e) {
			logger.error("Error fetching user " + userId + " permission on folder " + folder.getFolderId() + " for action " + actionId, e);
			return false;
		}
	}

	public boolean hasRoleFilePermission(Role role, FileEntry fileEntry, String actionId) {
		try {
			long[] userRoleIds = {role.getRoleId()};

			return ResourcePermissionLocalServiceUtil.hasResourcePermission(
					fileEntry.getCompanyId(),
					DLFileEntry.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(fileEntry.getFileEntryId()),
					userRoleIds,
					actionId);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public boolean hasRoleFolderPermission(Role role, Folder folder, String actionId) {
		try {
			long[] userRoleIds = {role.getRoleId()};

			return ResourcePermissionLocalServiceUtil.hasResourcePermission(
					folder.getCompanyId(),
					DLFolder.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(folder.getFolderId()),
					userRoleIds,
					actionId);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public List<String> getPermissionRoles(long groupId) throws SystemException, PortalException {

		List<String> roleList = new ArrayList<>(Arrays.asList(PERMISSION_ROLES));

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		// Personal groups roles
		if (group.isRegularSite()) {
			roleList.add(RoleConstants.SITE_ADMINISTRATOR);
			roleList.add(RoleConstants.SITE_OWNER);
		}
		return roleList;
	}

	/** Set the VIEW permission to a FileEntry or a Folder for ALL users */
	public void setViewPermissionOnResource(Object resource) throws PortalException, SystemException {

		int scope = ResourceConstants.SCOPE_INDIVIDUAL;
		String[] actionsId = {ActionKeys.VIEW};

		// Use the 'USER' role to match all users
		if (resource instanceof FileEntry) {
			String name = DLFileEntry.class.getName();
			FileEntry file = (FileEntry) resource;
			long roleId = RoleLocalServiceUtil.getRole(file.getCompanyId(), RoleConstants.USER).getRoleId();
			ResourcePermissionLocalServiceUtil.setResourcePermissions(file.getCompanyId(), name, scope, String.valueOf(file.getPrimaryKey()),roleId, actionsId );
		}
		else if (resource instanceof Folder) {
			String name = DLFolder.class.getName();
			Folder folder = (Folder) resource;
			long roleId = RoleLocalServiceUtil.getRole(folder.getCompanyId(), RoleConstants.USER).getRoleId();
			ResourcePermissionLocalServiceUtil.setResourcePermissions(folder.getCompanyId(), name, scope, String.valueOf(folder.getPrimaryKey()),roleId, actionsId );
		}
		else {
			throw new IllegalArgumentException("Resource must be an FileEntry or a Folder object");
		}
	}


	public void addDefaultPermissionsFolder(Folder folder) throws PortalException, SystemException {
		Group group = GroupLocalServiceUtil.getGroup(folder.getGroupId());

		// Organization
		if (group.isOrganization()) {
			setDefaultRolePermissionsForResource(true, folder.getCompanyId(), folder.getFolderId(), true);
		}
		// Personal group
		else if (group.isRegularSite()) {
			setDefaultRolePermissionsForResource(false, folder.getCompanyId(), folder.getFolderId(), true);
		}
	}

	public void addDefaultPermissionsFile(FileEntry fileEntry) throws PortalException, SystemException {
		Group group = GroupLocalServiceUtil.getGroup(fileEntry.getGroupId());

		// Organization
		if (group.isOrganization()) {
			setDefaultRolePermissionsForResource(true, fileEntry.getCompanyId(), fileEntry.getFileEntryId(), false);
		}
		// Personal group
		else if (group.isRegularSite()) {
			setDefaultRolePermissionsForResource(false, fileEntry.getCompanyId(), fileEntry.getFileEntryId(), false);
		}
	}

	private void setDefaultRolePermissionsForResource(boolean isOrg, long companyId, long objectId, boolean isFolder) throws PortalException, SystemException {
		logger.debug("Setting default permission to resource " + objectId);
		int scope = ResourceConstants.SCOPE_INDIVIDUAL;

		String name = DLFileEntry.class.getName();
		if (isFolder) {
			name = DLFolder.class.getName();
		}

		// Read permissions
		List<String> viewPermissions = new ArrayList<>();
		viewPermissions.add(ActionKeys.VIEW);

		// Update / add content permissions
		List<String> viewUpdatePermissions = new ArrayList<>();
		viewUpdatePermissions.add(ActionKeys.VIEW);
		viewUpdatePermissions.add(ActionKeys.UPDATE);
		if (isFolder) {
			viewUpdatePermissions.add(PermissionConstants.ADD_OBJECT);
			viewUpdatePermissions.add(ActionKeys.ADD_DOCUMENT);
			viewUpdatePermissions.add(ActionKeys.ADD_SUBFOLDER);
		}

		// All permissions list
		List<String> allPermissions = new ArrayList<>();
		allPermissions.add(ActionKeys.VIEW);
		allPermissions.add(ActionKeys.UPDATE);
		if (isFolder) {
			allPermissions.add(PermissionConstants.ADD_OBJECT);
			allPermissions.add(ActionKeys.ADD_DOCUMENT);
			allPermissions.add(ActionKeys.ADD_SUBFOLDER);
		}
		allPermissions.add(ActionKeys.DELETE);
		allPermissions.add(ActionKeys.PERMISSIONS);

		List<Role> allPermissionsRoleList = new ArrayList<>();

		// No permission roles
		List<Role> noPermissionRoleList = new ArrayList<>();
		noPermissionRoleList.add(RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_MEMBER));

		// Read-only roles
		List<Role> viewerRoleList = new ArrayList<>();
		viewerRoleList.add(RoleUtilsLocalServiceUtil.getStudentRole());
		viewerRoleList.add(RoleUtilsLocalServiceUtil.getParentRole());

		// Update / add content roles
		List<Role> viewUpdateRoleList = new ArrayList<>();
		// Students have update right in personal groups only
		if (!isOrg) {
			viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getStudentRole());
		}
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getTeacherRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getAssistantTechniqueRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getCaissierComptableRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getConseillerOrientationRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getConseillerSocialRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getDoyenRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getInfirmiereRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getBibliothecaireRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getPsychologueRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getSecretariatRole());

		// All permissions roles
		allPermissionsRoleList.add(RoleLocalServiceUtil.getRole(companyId, RoleConstants.OWNER));
		allPermissionsRoleList.add(RoleLocalServiceUtil.getRole(companyId, NeroRoleConstants.SCHOOL_ADMIN));
		allPermissionsRoleList.add(RoleLocalServiceUtil.getRole(companyId, NeroRoleConstants.DIRECTION));
		if (!isOrg) {
			allPermissionsRoleList.add(RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_ADMINISTRATOR));
			allPermissionsRoleList.add(RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_OWNER));
		}

		// Set no permissions
		logger.debug("No perm. : " + noPermissionRoleList);
		setPermissionsToRoles(noPermissionRoleList, new ArrayList<>(), companyId, name, scope, String.valueOf(objectId));

		// Set read permissions
		logger.debug("Read perm. : " + viewerRoleList + viewPermissions);
		setPermissionsToRoles(viewerRoleList, viewPermissions, companyId, name, scope, String.valueOf(objectId));

		// Set read and update / add content permissions
		logger.debug("Update perm. : " + viewUpdateRoleList + viewUpdatePermissions);
		setPermissionsToRoles(viewUpdateRoleList, viewUpdatePermissions, companyId, name, scope, String.valueOf(objectId));

		// Set admin permissions
		logger.debug("All perm. : " + allPermissionsRoleList + allPermissions);
		setPermissionsToRoles(allPermissionsRoleList, allPermissions, companyId, name, scope, String.valueOf(objectId));
	}

	public void setThumbnailFolderPermissions(long thumbnailFolderId) throws PortalException, SystemException {

		int scope = ResourceConstants.SCOPE_INDIVIDUAL;
		String name = DLFolder.class.getName();
		long companyId = PortalUtil.getDefaultCompanyId();

		// Read permissions
		List<String> viewPermissions = new ArrayList<>();
		viewPermissions.add(ActionKeys.VIEW);

		// Add content permissions
		List<String> viewUpdatePermissions = new ArrayList<>();
		viewUpdatePermissions.add(ActionKeys.VIEW);
		viewUpdatePermissions.add(ActionKeys.UPDATE);
		viewUpdatePermissions.add(PermissionConstants.ADD_OBJECT);
		viewUpdatePermissions.add(ActionKeys.ADD_DOCUMENT);
		viewUpdatePermissions.add(ActionKeys.ADD_SUBFOLDER);

		// Read-only roles
		List<Role> viewerRoleList = new ArrayList<>();
		viewerRoleList.add(RoleUtilsLocalServiceUtil.getStudentRole());
		viewerRoleList.add(RoleUtilsLocalServiceUtil.getParentRole());

		// Add content roles
		List<Role> viewUpdateRoleList = new ArrayList<>();
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getTeacherRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getAssistantTechniqueRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getCaissierComptableRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getConseillerOrientationRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getConseillerSocialRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getDoyenRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getInfirmiereRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getBibliothecaireRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getPsychologueRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getSecretariatRole());
		viewUpdateRoleList.add(RoleUtilsLocalServiceUtil.getDirectionRole());

		// Set read permissions
		logger.debug("Read perm. : " + viewerRoleList + viewPermissions);
		setPermissionsToRoles(viewerRoleList, viewPermissions, companyId, name, scope, String.valueOf(thumbnailFolderId));

		// Set read and update / add content permissions
		logger.debug("Update perm. : " + viewUpdateRoleList + viewUpdatePermissions);
		setPermissionsToRoles(viewUpdateRoleList, viewUpdatePermissions, companyId, name, scope, String.valueOf(thumbnailFolderId));
	}


	private void setPermissionsToRoles(List<Role> roles, List<String> permissions, long companyId, String name, int scope, String primKey)
			throws PortalException, SystemException {

		for (Role role : roles) {
			ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId, name, scope, primKey, role.getRoleId(), permissions.toArray(new String[0]));
		}
	}

	public void validateFullPermission(User user, long objectId, String type, JSONArray permissions, boolean applyToChild, long scopeGrpId) throws SystemException, PortalException {
		int scope = ResourceConstants.SCOPE_INDIVIDUAL;

		long roleId = 0;
		String name = "";

		boolean isFolder = false;

		Map<Long, String[]> mapPermissionFolder = new HashMap<>();
		Map<Long, String[]> mapPermissionFile = new HashMap<>();

		for (int i = 0; i < permissions.length(); i++) {
			JSONObject jsonObject = permissions.getJSONObject(i);

			roleId = jsonObject.getLong("roleId");

			name = type.equals("folder") ? DLFolder.class.getName() : DLFileEntry.class.getName();

			String[] permissionList;
			if (type.equals("folder")) {
				permissionList = extractFolderPermissionFromJson(jsonObject);
				mapPermissionFolder.put(roleId, permissionList);
				isFolder = true;


			} else if (type.equals("file")) {
				permissionList = extractFilePermissionFromJson(jsonObject);
				mapPermissionFile.put(roleId, permissionList);
			}
		}

		// Set permissions
		if (isFolder) {
			ResourcePermissionLocalServiceUtil.setResourcePermissions(user.getCompanyId(), name, scope, String.valueOf(objectId), mapPermissionFolder);
		} else {
			ResourcePermissionLocalServiceUtil.setResourcePermissions(user.getCompanyId(), name, scope, String.valueOf(objectId), mapPermissionFile);
		}

		// mandatory on folder
		if (applyToChild) {
			mapPermissionFile = convertFolderPermissionToFilePermission(mapPermissionFolder);
			this.applyPermissionsToChild(user, objectId, roleId, scopeGrpId, mapPermissionFolder, mapPermissionFile);
		}
	}

	private void applyPermissionsToChild(User user, long parentFolderId, long roleId, long scopeGrpId,
										 Map<Long, String[]> mapRoleActionsFolder, Map<Long, String[]> mapRoleActionsFile) throws SystemException, PortalException {
		long companyId = user.getCompanyId();
		long userId = user.getUserId();
		int scope = ResourceConstants.SCOPE_INDIVIDUAL;

		// Parent folder
		DLFolder parentFolder = DLFolderLocalServiceUtil.getFolder(parentFolderId);

		// Loop over child files
		List<FileEntry> filesChild = DLAppServiceUtil.getFileEntries(scopeGrpId, parentFolderId);
		for (FileEntry file : filesChild) {
//			boolean isLocked = JSPPortlet.isLocked(file, userId);
			if ((parentFolder.getUserId() == userId || hasUserFilePermission(userId, file, ActionKeys.PERMISSIONS))) {
				ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId, DLFileEntry.class.getName(), scope, String.valueOf(file.getFileEntryId()), mapRoleActionsFile);
				ResourcePermission resourcePermission = ResourcePermissionLocalServiceUtil.getResourcePermission(companyId, DLFileEntry.class.getName(), scope, String.valueOf(file.getFileEntryId()), roleId);
				ResourcePermissionUtil.clearCache(resourcePermission);
			}
		}

		// Loop over child folders
		List<Folder> foldersChild = DLAppServiceUtil.getFolders(scopeGrpId, parentFolderId);
		for (Folder folder : foldersChild) {
			if (parentFolder.getUserId() == userId || hasUserFolderPermission(userId, folder, ActionKeys.PERMISSIONS)) {
				ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId, DLFolder.class.getName(), scope, String.valueOf(folder.getFolderId()), mapRoleActionsFolder);
				ResourcePermission resourcePermission = ResourcePermissionLocalServiceUtil.getResourcePermission(companyId, DLFolder.class.getName(), scope, String.valueOf(folder.getFolderId()), roleId);
				ResourcePermissionUtil.clearCache(resourcePermission);
			}
			// Recursive call on sub-folders
			this.applyPermissionsToChild(user, folder.getFolderId(), roleId, scopeGrpId, mapRoleActionsFolder, mapRoleActionsFile);
		}
	}

	private String[] extractFolderPermissionFromJson(JSONObject permissionObject) {
		List<String> folderPermissionList = new ArrayList<>();

		if (permissionObject.getBoolean(ActionKeys.VIEW)) {
			folderPermissionList.add(ActionKeys.VIEW);
		}
		if (permissionObject.getBoolean(PermissionConstants.ADD_OBJECT)) {
			folderPermissionList.add(PermissionConstants.ADD_OBJECT);
			folderPermissionList.add(ActionKeys.ADD_DOCUMENT);
		}
		if (permissionObject.getBoolean(ActionKeys.DELETE)) {
			folderPermissionList.add(ActionKeys.DELETE);
		}
		if (permissionObject.getBoolean(ActionKeys.PERMISSIONS)) {
			folderPermissionList.add(ActionKeys.PERMISSIONS);
		}
		return folderPermissionList.toArray(new String[0]);
	}

	/** Check and get permission value all Permission for file */
	private String[] extractFilePermissionFromJson(JSONObject permissionObject) {
		List<String> filePermissionList = new ArrayList<>();

		if (permissionObject.getBoolean(ActionKeys.VIEW)) {
			filePermissionList.add(ActionKeys.VIEW);
		}
		if (permissionObject.getBoolean(ActionKeys.UPDATE)) {
			filePermissionList.add(ActionKeys.UPDATE);
		}
		if (permissionObject.getBoolean(ActionKeys.DELETE)) {
			filePermissionList.add(ActionKeys.DELETE);
		}
		if (permissionObject.getBoolean(ActionKeys.PERMISSIONS)) {
			filePermissionList.add(ActionKeys.PERMISSIONS);
		}

		return filePermissionList.toArray(new String[0]);
	}

	private Map<Long, String[]> convertFolderPermissionToFilePermission(Map<Long, String[]> folderPermissionsMap) {
		Map<Long, String[]> filePermissionMap = new HashMap<>();

		for (Map.Entry<Long, String[]> permissionForARole : folderPermissionsMap.entrySet()) {

			List<String> permissionForCurrentRole = new ArrayList<>(Arrays.asList(permissionForARole.getValue()));

			if (permissionForCurrentRole.contains(PermissionConstants.ADD_OBJECT)) {
				permissionForCurrentRole.remove(PermissionConstants.ADD_OBJECT);
				permissionForCurrentRole.add(ActionKeys.UPDATE);
			}
			filePermissionMap.put(permissionForARole.getKey(), permissionForCurrentRole.toArray(new String[0]));
		}

		return filePermissionMap;
	}

	private long[] getRolesIdsToCheckForUserPermissions(long userId, long groupId, long ressourceCreatorId) throws PortalException, SystemException {
		User user = UserLocalServiceUtil.getUser(userId);
		Group group = GroupLocalServiceUtil.getGroup(groupId);

		List<Role> roles = new ArrayList<>();

		// Skip User and Power User roles
		for (Role userRole : user.getRoles()) {
			if (!userRole.getName().equals("User") && !userRole.getName().equals("Power User")) {
				roles.add(userRole);
			}
		}

		if (ressourceCreatorId == userId) {
			roles.add(RoleLocalServiceUtil.getRole(user.getCompanyId(), RoleConstants.OWNER));
		}

		if (RoleUtilsLocalServiceUtil.isSchoolAdmin(user)){
			Role adminGrpRole = RoleUtilsLocalServiceUtil.getSchoolAdminRole();
			roles.add(adminGrpRole);
		}

		if (group.isRegularSite()) {

			Role siteAdministratorRole = RoleUtilsLocalServiceUtil.getCommunityAdministratorRole();
			Role siteOwnerRole = RoleUtilsLocalServiceUtil.getCommunityOwnerRole();

			if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), groupId, siteAdministratorRole.getRoleId())) {
				roles.add(siteAdministratorRole);
			}
			if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), groupId, siteOwnerRole.getRoleId())) {
				roles.add(siteOwnerRole);
			}
		}

		long[] roleIds = new long[roles.size()];
		for (int i = 0 ; i < roles.size() ; i++){
			roleIds[i] = roles.get(i).getRoleId();
		}

		return roleIds;
	}

	// Apply parent folder's permissions to given file
	public void setParentPermissionToFile(FileEntry fileEntry) throws PortalException, SystemException {
		Map<Long,List<String>> permissionMap = getPermissionsMapForFolder(fileEntry.getFolder());

		Map<Long,String[]> permissionMapConvert = convertPermissionFromGetterToSetter(permissionMap);
		Map<Long,String[]> filePermissionMap = convertFolderPermissionToFilePermission(permissionMapConvert);

		ResourcePermissionLocalServiceUtil.setResourcePermissions(fileEntry.getCompanyId(), DLFileEntry.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(fileEntry.getFileEntryId()),
				filePermissionMap);
	}

	// Apply parent folder's permissions to given folder
	public void setParentPermissionToFolder(Folder folder) throws PortalException, SystemException{

		Map<Long,List<String>> permissionMap = getPermissionsMapForFolder(folder.getParentFolder());
		Map<Long,String[]> permissionMapConvert = convertPermissionFromGetterToSetter(permissionMap);

		ResourcePermissionLocalServiceUtil.setResourcePermissions(folder.getCompanyId(), DLFolder.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(folder.getFolderId()),
				permissionMapConvert);
	}

	/**
	 * Converts the permission map from  Map<Long,Set<String>>  to  Map<Long,String[]>
	 */
	private Map<Long,String[]> convertPermissionFromGetterToSetter(Map<Long,List<String>> permissionMap){
		Map<Long,String[]> setterPermissionMap = new HashMap<>();

		for (Map.Entry<Long,List<String>> permissionEntry : permissionMap.entrySet()) {
			setterPermissionMap.put(permissionEntry.getKey(),
					permissionEntry.getValue().toArray(new String[0]));
		}

		return setterPermissionMap;
	}

	private Map<Long,List<String>> getPermissionsMapForFolder(Folder folder) throws PortalException, SystemException {
		// Key is roleId, value is list of permissions as string
		Map<Long,List<String>> permissionsMap = new HashMap<>();

		List<String> listRole = getPermissionRoles(folder.getGroupId());

		int scope = ResourceConstants.SCOPE_INDIVIDUAL;
		String name = DLFolder.class.getName();

		long[] roleIdList = new long[listRole.size()];

		for (int i = 0; i< listRole.size() ; i++) {
			roleIdList[i] = RoleLocalServiceUtil.getRole(folder.getCompanyId(), listRole.get(i)).getRoleId();
		}

		for (long l : roleIdList) {
			permissionsMap.put(l,
					ResourcePermissionLocalServiceUtil.getAvailableResourcePermissionActionIds(
							folder.getCompanyId(), name, scope,
							String.valueOf(folder.getPrimaryKey()), l, PermissionConstants.FOLDER_PERMISSIONS));
		}

		return permissionsMap;
	}

	protected static final String[] PERMISSION_ROLES = {
			NeroRoleConstants.STUDENT, NeroRoleConstants.TEACHER, NeroRoleConstants.RELATIVE, NeroRoleConstants.DIRECTION,
			NeroRoleConstants.SCHOOL_ADMIN,
			NeroRoleConstants.ASSISTANT_TECHNIQUE, NeroRoleConstants.CAISSIER_COMPTABLE, NeroRoleConstants.CONSEILLER_ORIENTATION,
			NeroRoleConstants.CONSEILLER_SOCIAL, NeroRoleConstants.DOYEN, NeroRoleConstants.INFIRMIERE, NeroRoleConstants.BIBLIOTHECAIRE,
			NeroRoleConstants.PSYCHOLOGUE, NeroRoleConstants.SECRETAIRE
	};

}