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

package com.weprode.facile.maintenance;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.course.CourseConstants;
import com.weprode.facile.course.model.ContentBlock;
import com.weprode.facile.course.model.Homework;
import com.weprode.facile.course.model.SessionContent;
import com.weprode.facile.course.service.ContentBlockLocalServiceUtil;
import com.weprode.facile.course.service.HomeworkLocalServiceUtil;
import com.weprode.facile.course.service.SessionContentLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.group.service.CommunityInfosLocalServiceUtil;
import com.weprode.facile.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.facile.user.service.UserSearchLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {

    private static final Log logger = LogFactoryUtil.getLog(PermissionUtil.class);

    public static void addPermissions() {

        logger.info("START Permissions");

        // Do as administrator, to get rid of permission issues
        List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(RoleUtilsLocalServiceUtil.getAdministratorRole().getRoleId());
        PrincipalThreadLocal.setName(adminUsers.get(0).getUserId());
        PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(adminUsers.get(0));
        PermissionThreadLocal.setPermissionChecker(permissionChecker);

        // Loop over impacted orgs : school and subject
        // This was run after migration (August 7th 2023)
//        List<Organization> schools = OrgUtilsLocalServiceUtil.getAllSchools();
//        for (Organization school : schools) {
//            List<Integer> types = new ArrayList<>();
//            types.add(OrgConstants.SUBJECT_TYPE);
//
//            List<Organization> orgs = OrgUtilsLocalServiceUtil.getSchoolOrganizations(school.getOrganizationId(), types, false);
//            for (Organization org : orgs) {
//                logger.info("Processing org " + org.getName());
//                try {
//                    Folder rootFolder = FolderUtilsLocalServiceUtil.getOrCreateGroupRootFolder(org.getGroupId());
//                    recursiveFolder(rootFolder);
//                } catch (Exception e) {
//                    logger.error("Error processing permissions for org " + org.getOrganizationId(), e);
//                }
//            }
//
//        }

        // Loop over communities
        List<Organization> schools = OrgUtilsLocalServiceUtil.getAllSchools();
        for (Organization school : schools) {
            // Get school director
            List<Long> orgIds = new ArrayList<>();
            orgIds.add(school.getOrganizationId());
            List<Long> roleIds = new ArrayList<>();
            roleIds.add(RoleUtilsLocalServiceUtil.getDirectionRole().getRoleId());
            List<User> directors = UserSearchLocalServiceUtil.searchUsers("", orgIds, null, roleIds, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
            User director = directors.get(0);

            List<Group> communities = CommunityInfosLocalServiceUtil.getSchoolCommunities(school.getOrganizationId(), false, true);
            for (Group community : communities) {
                logger.info("Processing community " + community.getName());
                try {
                    Folder rootFolder = FolderUtilsLocalServiceUtil.getOrCreateGroupRootFolder(community.getGroupId());
                    recursiveFolder(director, rootFolder);
                } catch (Exception e) {
                    logger.error("Error processing permissions for community " + community.getGroupId(), e);
                }
            }
        }
        logger.info("END Permissions");
    }

    private static void recursiveFolder(User director, Folder folder) throws PortalException {
        logger.info(" > folder " + folder.getName());
        JSONArray permissionMatrix = new JSONArray();

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
            roleActions.put(JSONConstants.ROLE_NAME, role.getName());
            roleActions.put(JSONConstants.EDITABLE, areRoleActionsEditable);

            for (ResourceAction resourceAction : dLFolderResourceActions) {
                roleActions.put(resourceAction.getActionId(), PermissionUtilsLocalServiceUtil.hasRoleFolderPermission(role, folder, resourceAction.getActionId()));
            }

            permissionMatrix.put(roleActions);
        }

        PermissionUtilsLocalServiceUtil.validateFullPermission(director, folder.getFolderId(), "folder", permissionMatrix, false, folder.getGroupId());

    }


    public static void applySessionContentAndHomeworkPermissions() {

        // Loop over all homeworks and session contents
        // Apply default permissions to fix those which were created without these permissions
        int nbHomeworkErrors = 0;
        int count = 0;
        List<Homework> allHomeworks = HomeworkLocalServiceUtil.getHomeworks(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        logger.info("Looping over " + allHomeworks.size() + " homeworks ...");
        for (Homework homework : allHomeworks) {
            try {
                count ++;
                if (count % 100 == 0) {
                    logger.info("Processed " + count + "/" + allHomeworks.size());
                }
                HomeworkLocalServiceUtil.getHomeworkFolder(homework.getHomeworkId(), false);
                logger.info("Applied permissions to homework " + homework.getHomeworkId());
                // Loop over files
                List<ContentBlock> blocks = ContentBlockLocalServiceUtil.getContentsByItemId(homework.getHomeworkId());
                for (ContentBlock block : blocks) {
                    if (block.getBlockType() == CourseConstants.TYPE_FILE) {
                        FileEntry contentFile = DLAppLocalServiceUtil.getFileEntry(block.getFileEntryId());
                        PermissionUtilsLocalServiceUtil.addDefaultPermissionsFile(contentFile);
                        logger.info(" > Applied to file " + contentFile.getTitle());
                    }
                }
            } catch (Exception e) {
                logger.error("Error applying default permissions to homework " + homework.getHomeworkId(), e);
                nbHomeworkErrors++;
            }
        }
        int nbSessionErrors = 0;
        count = 0;
        List<SessionContent> allSessionContents = SessionContentLocalServiceUtil.getSessionContents(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (SessionContent sessionContent : allSessionContents) {
            try {
                count ++;
                if (count % 100 == 0) {
                    logger.info("Processed " + count + "/" + allHomeworks.size());
                }
                SessionContentLocalServiceUtil.getSessionFolder(sessionContent.getSessionId(), false);
                logger.info("Applied permissions to session " + sessionContent.getSessionId());
                // Loop over files
                List<ContentBlock> blocks = ContentBlockLocalServiceUtil.getContentsByItemId(sessionContent.getSessionId());
                for (ContentBlock block : blocks) {
                    if (block.getBlockType() == CourseConstants.TYPE_FILE) {
                        FileEntry contentFile = DLAppLocalServiceUtil.getFileEntry(block.getFileEntryId());
                        PermissionUtilsLocalServiceUtil.addDefaultPermissionsFile(contentFile);
                        logger.info(" > Applied to file " + contentFile.getTitle());
                    }
                }
            } catch (Exception e) {
                logger.error("Error applying default permissions to session " + sessionContent.getSessionId(), e);
                nbSessionErrors++;
            }
        }

        logger.info("Processed homeworks with " + nbHomeworkErrors + " errors");
        logger.info("Processed session contents with " + nbSessionErrors + " errors");
    }

}
