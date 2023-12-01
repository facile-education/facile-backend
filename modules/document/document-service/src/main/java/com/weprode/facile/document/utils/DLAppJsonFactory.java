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

package com.weprode.facile.document.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.document.constants.PermissionConstants;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.group.model.CommunityInfos;
import com.weprode.facile.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.facile.organization.constants.OrgConstants;
import com.weprode.facile.organization.service.OrgDetailsLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.facile.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

public class DLAppJsonFactory {

    private DLAppJsonFactory() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(DLAppJsonFactory.class);

    public static JSONObject format(long userId, Folder folder) throws PortalException, SystemException {
        int space = DocumentUtil.getSpace(folder, folder.getUserId());
        return format(userId, folder, space);
    }

    public static JSONObject format(long userId, Folder folder, int space) throws PortalException, SystemException {
        return format(userId, folder, space, false);
    }

    public static JSONObject format(long userId, Folder folder, int space, boolean withDetails) throws PortalException, SystemException {
        User user = UserLocalServiceUtil.getUser(userId);
        return format(user, folder, space, withDetails);
    }

    public static JSONObject format(User user, Folder folder, int space, boolean withDetails) {

        JSONObject formattedFolder = new JSONObject();
        addCommonsFields(formattedFolder, folder, user, withDetails);

        if (space == DocumentConstants.COLLABORATIVE) {
            addGroupFields(formattedFolder, folder, user);
        }

        return formattedFolder;
    }

    public static JSONObject format(long userId, FileEntry fileEntry) throws PortalException, SystemException {
        int space = DocumentUtil.getSpace(fileEntry, userId);
        return format(userId, fileEntry, space);
    }

    public static JSONObject format(long userId, FileEntry fileEntry, int space) throws PortalException, SystemException {
        return format(userId, fileEntry, space, false);
    }

    public static JSONObject format(long userId, FileEntry fileEntry, int space, boolean withDetails) throws PortalException, SystemException {
        User user = UserLocalServiceUtil.getUser(userId);
        return format(user, fileEntry, space,withDetails);
    }

    /**
     * Formats a given FileEntry into JSONObject
     */
    public static JSONObject format(User user, FileEntry fileEntry, int space, boolean withDetails) {
        JSONObject formattedFile = new JSONObject();

        addCommonsFields(formattedFile, fileEntry, user, withDetails);

        if (space == DocumentConstants.COLLABORATIVE) {
            addGroupFields(formattedFile, fileEntry, user);
        }

        return formattedFile;
    }

    /**
     * Add commons' folders fields to JSONObject, wherever it is (not depending on folder's space)
     */
    private static void addCommonsFields(JSONObject formattedFolder, Folder folder, User user, boolean withDetails) {
        formattedFolder.put(JSONConstants.ID, String.valueOf(folder.getFolderId()));
        formattedFolder.put(JSONConstants.NAME, folder.getName());
        formattedFolder.put(JSONConstants.TYPE, "Folder");
        formattedFolder.put(JSONConstants.LAST_MODIFIED_DATE, new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT)
                .format(folder.getModifiedDate()));
        
        try {
            if (UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId()).getWebdavActivated()) {
                formattedFolder.put(JSONConstants.URL_WEBDAV, ENTWebDAVUtil.getWebDavUrl(folder.getGroupId(), folder));
            }
        } catch (Exception e) {
            logger.error(e);
        }

        // Permissions
        // Directors and school admins have all rights on institutional groups
        boolean hasFullPermissions = false;
        try {
            Group group = GroupLocalServiceUtil.getGroup(folder.getGroupId());
            hasFullPermissions = group.isOrganization() && RoleUtilsLocalServiceUtil.isSchoolAdmin(user);
        } catch (Exception e) {
            logger.debug(e);
        }
        
        final JSONObject permissions = new JSONObject();
        permissions.put(PermissionConstants.ADD_OBJECT, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, PermissionConstants.ADD_OBJECT));
        permissions.put(ActionKeys.DELETE, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.DELETE));
        permissions.put(ActionKeys.PERMISSIONS, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.PERMISSIONS));
        formattedFolder.put(JSONConstants.PERMISSIONS, permissions);

        if (withDetails) {
            try {
                formattedFolder.put(JSONConstants.SIZE, FolderUtilsLocalServiceUtil.getFolderSize(folder));
            } catch (Exception e) {
                logger.error(e.getMessage());
                formattedFolder.put(JSONConstants.SIZE, "error when computing size");
            }
            formattedFolder.put(JSONConstants.CREATION_DATE, new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).format(folder.getCreateDate()));
        }
    }

    /**
     * Add commons' files fields to JSONObject, wherever it is (not depending on file's space)
     */
    private static void addCommonsFields(JSONObject formattedFile, FileEntry fileEntry, User user, boolean withDetails) {
        formattedFile.put(JSONConstants.ID, String.valueOf(fileEntry.getFileEntryId()));
        formattedFile.put(JSONConstants.NAME, fileEntry.getTitle());
        formattedFile.put(JSONConstants.TYPE, "File");
        formattedFile.put(JSONConstants.SIZE, (int) fileEntry.getSize());
        formattedFile.put(JSONConstants.EXTENSION, fileEntry.getExtension().toLowerCase());
        formattedFile.put(JSONConstants.LAST_MODIFIED_DATE, new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).format(fileEntry.getModifiedDate()));
        formattedFile.put(JSONConstants.URL, FileUtilsLocalServiceUtil.getDownloadUrl(fileEntry));


        // Permissions
        if (user != null) { // No specific user for News attachedFiles or what (maybe we should?)
            final JSONObject permissions = new JSONObject();
            permissions.put(ActionKeys.UPDATE, PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.UPDATE));
            permissions.put(ActionKeys.DELETE, PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.DELETE));
            permissions.put(ActionKeys.PERMISSIONS, PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.PERMISSIONS));
            formattedFile.put(JSONConstants.PERMISSIONS, permissions);
        }

        if (withDetails) {
            formattedFile.put(JSONConstants.CREATION_DATE,
                    new SimpleDateFormat(JSONConstants.FULL_ENGLISH_FORMAT).format(fileEntry.getCreateDate()));
            formattedFile.put(JSONConstants.CREATOR, fileEntry.getUserName());
            formattedFile.put(JSONConstants.VERSION, fileEntry.getVersion());
            try {
                if (UserPropertiesLocalServiceUtil.getUserProperties(user.getUserId()).getWebdavActivated()) {
                    formattedFile.put(JSONConstants.URL_WEBDAV, ENTWebDAVUtil.getWebDavUrl(fileEntry.getGroupId(), fileEntry));
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    private static void addGroupFields(JSONObject formattedFolder, Folder folder, User user) {
        try {
            Group folderGroup = GroupLocalServiceUtil.getGroup(folder.getGroupId());
            formattedFolder.put(JSONConstants.GROUP_ID, folderGroup.getGroupId());
            // TODO: make the groupRootFolder directly have the good name
            if (folder.getParentFolderId() == 0) { // The group root folder have a specific name that is the name of the group and not the folder.name field.
                if (folderGroup.isOrganization()) {	// Org groups
                    Organization org = OrganizationLocalServiceUtil.getOrganization(folderGroup.getOrganizationId());
                    boolean withSchoolName = OrgDetailsLocalServiceUtil.hasType(folderGroup.getOrganizationId(), OrgConstants.SCHOOL_TYPE);
                    formattedFolder.put(JSONConstants.NAME, OrgUtilsLocalServiceUtil.formatOrgName(org.getName(), withSchoolName));
                    formattedFolder.put(JSONConstants.NB_MEMBERS, UserOrgsLocalServiceUtil.countOrgMembers(org.getOrganizationId()));
                    formattedFolder.put(JSONConstants.COLOR, OrgUtilsLocalServiceUtil.getOrgColor(user, org));
                } else {	// Personal groups
                    formattedFolder.put(JSONConstants.NAME, folderGroup.getName(user.getLocale()));
                    formattedFolder.put(JSONConstants.NB_MEMBERS, UserLocalServiceUtil.getGroupUsersCount(folderGroup.getGroupId(), WorkflowConstants.STATUS_APPROVED));
                    CommunityInfos communityInfos = CommunityInfosLocalServiceUtil.getCommunityInfosByGroupId(folderGroup.getGroupId());
                    String color = communityInfos.getColor() != null && !communityInfos.getColor().isEmpty() ? communityInfos.getColor() : "#4353B3";
                    formattedFolder.put(JSONConstants.COLOR, color);
                }
                formattedFolder.put(JSONConstants.IS_GROUP_ROOT_FOLDER, true); // those folder is considered as Group on front side (group root folder)
                final JSONObject permissions = new JSONObject();

                // Directors and school admins have all rights on institutional groups
                boolean hasFullPermissions = false;
                try {
                    Group group = GroupLocalServiceUtil.getGroup(folder.getGroupId());
                    hasFullPermissions = group.isOrganization() && RoleUtilsLocalServiceUtil.isSchoolAdmin(user);
                } catch (Exception e) {
                    logger.debug(e);
                }

                permissions.put(PermissionConstants.ADD_OBJECT, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, PermissionConstants.ADD_OBJECT));
                permissions.put(ActionKeys.UPDATE, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.UPDATE));
                permissions.put(ActionKeys.DELETE, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.DELETE));
                permissions.put(ActionKeys.PERMISSIONS, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, ActionKeys.PERMISSIONS));
                formattedFolder.put(JSONConstants.PERMISSIONS, permissions);
            }

        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static void addGroupFields(JSONObject formattedFile, FileEntry fileEntry, User user) {
        formattedFile.put(JSONConstants.IS_GROUP_FILE, true);
        final JSONObject permissions = new JSONObject();
        // Directors and school admins have all rights on institutional groups
        boolean hasFullPermissions = false;
        try {
            Group group = GroupLocalServiceUtil.getGroup(fileEntry.getGroupId());
            hasFullPermissions = group.isOrganization() && RoleUtilsLocalServiceUtil.isSchoolAdmin(user);
        } catch (Exception e) {
            logger.debug(e);
        }
        
        permissions.put(ActionKeys.UPDATE, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.UPDATE));
        permissions.put(ActionKeys.DELETE, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.DELETE));
        permissions.put(ActionKeys.PERMISSIONS, hasFullPermissions || PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileEntry, ActionKeys.PERMISSIONS));
        formattedFile.put(JSONConstants.PERMISSIONS, permissions);
    }
    
    public static JSONArray formatUsers(List<Long> userIds) {
        final JSONArray jsonUsers = new JSONArray();

        for (long userId : userIds) {
            jsonUsers.put(formatUser(userId));
        }

        return jsonUsers;
    }

    public static JSONObject formatUser(long userId) {
        try {
            User user = UserLocalServiceUtil.getUser(userId);

            // Retrieve user picture url
            String urlImg = UserConstants.getPortraitURL(PortalUtil.getPathImage(), user.isMale(),
                    user.getPortraitId(), user.getUserUuid());

            // Put fields
            JSONObject curr = new JSONObject();
            curr.put(JSONConstants.ID, userId);
            curr.put(JSONConstants.NAME, user.getFullName());
            curr.put(JSONConstants.PICTURE, urlImg);

            return curr;
        } catch (Exception e) {
            logger.error("Cant retrieve user with userId " + userId, e);
            return new JSONObject();
        }
    }

}
