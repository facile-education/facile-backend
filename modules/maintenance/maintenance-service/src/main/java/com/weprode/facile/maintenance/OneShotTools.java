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

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.weprode.facile.news.model.NewsAttachedFile;
import com.weprode.facile.news.service.NewsAttachedFileLocalServiceUtil;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

public class OneShotTools {

    private static final Log logger = LogFactoryUtil.getLog(OneShotTools.class);

    private int nbSubFolders = 0;
    private int nbFiles = 0;

    public void setNewsPermissions() {

        List<Long> allRoleIds = new ArrayList<>(RoleUtilsLocalServiceUtil.getAgentsRoleIds());
        allRoleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
        allRoleIds.add(RoleUtilsLocalServiceUtil.getParentRole().getRoleId());

        List<NewsAttachedFile> newsAttachedFiles = NewsAttachedFileLocalServiceUtil.getNewsAttachedFiles(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (NewsAttachedFile newsAttachedFile : newsAttachedFiles) {
            logger.info("Processing newsId " + newsAttachedFile.getNewsId() + " and file " + newsAttachedFile.getFileId());
            try {
                DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(newsAttachedFile.getFileId());
                // Set VIEW permissions to all
                for (long roleId : allRoleIds) {
                    ResourcePermissionLocalServiceUtil.setResourcePermissions(dlFileEntry.getCompanyId(), DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, "" + dlFileEntry.getFileEntryId(), roleId, new String[]{ActionKeys.VIEW});
                }
                // Parent folder permission
                setNewsFolderPermission(dlFileEntry.getFolderId());
            } catch (Exception e) {
                logger.error("Error setting permission to file " + newsAttachedFile.getFileId());
            }

        }
    }

    private void setNewsFolderPermission(long folderId) {

        try {
            DLFolder folder = DLFolderLocalServiceUtil.getFolder(folderId);
            List<Long> agentRoleIds = RoleUtilsLocalServiceUtil.getAgentsRoleIds();
            List<Long> allRoleIds = new ArrayList<>(RoleUtilsLocalServiceUtil.getAgentsRoleIds());
            allRoleIds.add(RoleUtilsLocalServiceUtil.getStudentRole().getRoleId());
            allRoleIds.add(RoleUtilsLocalServiceUtil.getParentRole().getRoleId());

            // Delete all permissions on newsId folder
            ResourcePermissionLocalServiceUtil.deleteResourcePermissions(folder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, folderId);

            // Set VIEW and DELETE permissions for the owner (for search and removal)
            Role ownerRole = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), RoleConstants.OWNER);
            ResourcePermissionLocalServiceUtil.setOwnerResourcePermissions(folder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, "" + folder.getFolderId(), ownerRole.getRoleId(), folder.getUserId(), new String[]{ActionKeys.VIEW, ActionKeys.DELETE});

            // Set VIEW permissions for all
            for (long roleId : allRoleIds) {
                ResourcePermissionLocalServiceUtil.setResourcePermissions(folder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, "" + folder.getFolderId(), roleId, new String[]{ActionKeys.VIEW});
            }
            // Set EDIT permissions to agents
            for (long agentRoleId : agentRoleIds) {
                ResourcePermissionLocalServiceUtil.setResourcePermissions(folder.getCompanyId(), DLFolder.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, "" + folder.getFolderId(), agentRoleId, new String[]{ActionKeys.VIEW, ActionKeys.ADD_DOCUMENT, ActionKeys.UPDATE, ActionKeys.DELETE});
            }
        } catch (Exception e) {
            logger.error("Error setting permission to parent folder " + folderId);
        }
    }

    // Delete old folders named '._CASIER_' and their content
    public void cleanupDropboxes() {

        int nbFolders = 0;
        List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getDLFolders(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (DLFolder dlFolder : dlFolders) {
            if (dlFolder.getName().equals("._CASIER_")) {
                nbFolders++;
                deleteFolder(dlFolder.getFolderId());
            }
        }
        logger.info("END dropbox folder deletion : " + nbFolders + " folders, " + nbSubFolders + " sub-folders and " + nbFiles + " files were deleted");
    }

    private void deleteFolder(long folderId) {

        try {
            Folder folder = DLAppServiceUtil.getFolder(folderId);

            // SubFolders
            List<Folder> subFolders = DLAppServiceUtil.getFolders(folder.getGroupId(), folder.getFolderId());
            for (Folder subFolder : subFolders) {
                nbSubFolders++;
                logger.info(" > subFolder " + subFolder.getName());
                deleteFolder(subFolder.getFolderId());
            }

            // Files
            List<FileEntry> fileList;
            fileList = DLAppServiceUtil.getFileEntries(folder.getGroupId(), folderId);
            for (FileEntry fileEntry : fileList) {
                nbFiles++;
                logger.info("Deleting file " + fileEntry.getTitle() + " (id " + fileEntry.getFileEntryId() + ")");
                DLAppLocalServiceUtil.deleteFileEntry(fileEntry.getFileEntryId());
            }

            logger.info("Deleting folder " + folder.getName() + " (id " + folder.getFolderId() + ")");
            DLAppLocalServiceUtil.deleteFolder(folderId);

        } catch (Exception e) {
            logger.error("Error deleting folder " + folderId, e);
        }
    }
}
