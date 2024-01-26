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

import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchResourcePermissionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.constants.PermissionConstants;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClipboardUtil {

    private ClipboardUtil() {
        throw new IllegalStateException("Utility class");
    }
    private static final Log logger = LogFactoryUtil.getLog(ClipboardUtil.class);

    public static JSONObject copy(long userId, long targetFolderId, String folderIds, String fileIds, int mode) {
        JSONObject result = new JSONObject();
        JSONArray failedEntitiesList = new JSONArray();
        JSONArray foldersInConflict = new JSONArray();
        JSONArray filesInConflict = new JSONArray();

        List<Long> folderIdList = jsonArrayStringToList(folderIds);

        for (long folderId : folderIdList) {
            try {
                Folder folder = DLAppServiceUtil.getFolder(folderId);
                try {
                    if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, folderId)
                            || !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, folder, ActionKeys.VIEW)) {
                        logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + userId + " copies folder " + folderId);
                        addFailedEntityToList(failedEntitiesList, folderId, JSONConstants.NOT_ALLOWED_EXCEPTION);
                        continue;
                    }
                    FolderUtilsLocalServiceUtil.copyFolder(userId, folderId, targetFolderId, mode);
                } catch (NoSuchResourcePermissionException e) {
                    logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + userId + " copies folder " + folderId);
                    addFailedEntityToList(failedEntitiesList, folderId, JSONConstants.NOT_ALLOWED_EXCEPTION);
                } catch (FileNameException e) {
                    JSONObject conflict = new JSONObject();
                    conflict.put(JSONConstants.ID, folderId);
                    conflict.put(JSONConstants.NAME, folder.getName());
                    // Find the folder that cause the conflict and check if it can be replaced
                    Folder targetFolder = DLAppServiceUtil.getFolder(targetFolderId);
                    Folder folderThatCauseConflict = DLAppLocalServiceUtil.getFolder(targetFolder.getGroupId(), targetFolderId, folder.getName());
                    conflict.put(JSONConstants.HAS_UPDATE_PERMISSION,
                            PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, folderThatCauseConflict, PermissionConstants.ADD_OBJECT) &&
                            !FolderUtilsLocalServiceUtil.isParentFolder(folderThatCauseConflict, folder) &&
                            folderThatCauseConflict.getFolderId() != folderId);
                    foldersInConflict.put(conflict);
                }
            } catch (Exception e) {
                logger.error(e);
                addFailedEntityToList(failedEntitiesList, folderId, JSONConstants.UNKNOWN);
            }
        }

        List<Long> fileIdList = jsonArrayStringToList(fileIds);

        for (long fileId : fileIdList) {
            try {
                FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileId);
                try {
                    if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, fileEntry.getFolderId())
                            || !PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, fileEntry, ActionKeys.VIEW)) {
                        logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + userId + " copies file " + fileId);
                        addFailedEntityToList(failedEntitiesList, fileId, JSONConstants.NOT_ALLOWED_EXCEPTION);
                        continue;
                    }
                    FileUtilsLocalServiceUtil.copyFileEntry(userId, fileId, targetFolderId, true, mode);
                } catch (NoSuchResourcePermissionException e) {
                    logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + userId + " copies file " + fileId);
                    addFailedEntityToList(failedEntitiesList, fileId, JSONConstants.NOT_ALLOWED_EXCEPTION);
                } catch (DuplicateFileEntryException e) {
                    JSONObject conflict = new JSONObject();
                    conflict.put(JSONConstants.ID, fileId);
                    conflict.put(JSONConstants.NAME, fileEntry.getFileName());
                    // Find the file that cause the conflict and check if it can be replaced
                    Folder targetFolder = DLAppServiceUtil.getFolder(targetFolderId);
                    FileEntry fileThatCauseConflict = DLAppLocalServiceUtil.getFileEntry(targetFolder.getGroupId(), targetFolderId, fileEntry.getFileName());
                    conflict.put(JSONConstants.HAS_UPDATE_PERMISSION, PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, fileThatCauseConflict, ActionKeys.UPDATE));
                    filesInConflict.put(conflict);
                }
            } catch (Exception e) {
                logger.error(e);
                addFailedEntityToList(failedEntitiesList, fileId, JSONConstants.UNKNOWN);
            }
        }

        result.put(JSONConstants.FAILED_ENTITIES_LIST, failedEntitiesList);
        result.put(JSONConstants.FOLDERS_IN_CONFLICT, foldersInConflict);
        result.put(JSONConstants.FILES_IN_CONFLICT, filesInConflict);
        result.put(JSONConstants.SUCCESS, failedEntitiesList.length() + filesInConflict.length() + foldersInConflict.length() == 0);

        return result;
    }

    // Move entities or paste from cut
    public static JSONObject move(long userId, long destFolderId, String folderIds, String fileIds, int mode) {
        JSONObject result = new JSONObject();

        JSONArray failedEntitiesList = new JSONArray();
        JSONArray foldersInConflict = new JSONArray();
        JSONArray filesInConflict = new JSONArray();

        List<Long> folderIdList = jsonArrayStringToList(folderIds);

        for (long folderId : folderIdList) {
            try {
                Folder folder = DLAppServiceUtil.getFolder(folderId);
                try {
                    if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, folderId)
                            || !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, folder, ActionKeys.VIEW)) {
                        logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + userId + " moves folder " + folderId);
                        addFailedEntityToList(failedEntitiesList, folderId, JSONConstants.NOT_ALLOWED_EXCEPTION);
                        continue;
                    }
                    FolderUtilsLocalServiceUtil.moveFolder(userId, folder, destFolderId, mode);
                } catch (NoSuchResourcePermissionException e) {
                    logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + userId + " moves folder " + folderId);
                    addFailedEntityToList(failedEntitiesList, folderId, JSONConstants.NOT_ALLOWED_EXCEPTION);
                } catch (FileNameException e) {
                    JSONObject conflict = new JSONObject();
                    conflict.put(JSONConstants.ID, folderId);
                    conflict.put(JSONConstants.NAME, folder.getName());
                    // Find the folder that cause the conflict and check if it can be replaced
                    Folder targetFolder = DLAppServiceUtil.getFolder(destFolderId);
                    Folder folderThatCauseConflict = DLAppLocalServiceUtil.getFolder(targetFolder.getGroupId(), destFolderId, folder.getName());
                    conflict.put(JSONConstants.HAS_UPDATE_PERMISSION,
                            PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, folderThatCauseConflict, PermissionConstants.ADD_OBJECT) &&
                                    !FolderUtilsLocalServiceUtil.isParentFolder(folderThatCauseConflict, folder) &&
                                    folderThatCauseConflict.getFolderId() != folderId);
                    foldersInConflict.put(conflict);
                }
            } catch (Exception e) {
                logger.error(e);
                addFailedEntityToList(failedEntitiesList, folderId, JSONConstants.UNKNOWN);
            }
        }

        List<Long> fileIdList = jsonArrayStringToList(fileIds);

        for (long fileId : fileIdList) {
            try {
                FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileId);
                try {
                    if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, fileEntry.getFolderId())
                            || !PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, fileEntry, ActionKeys.VIEW)) {
                        logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + userId + " moves file " + fileId);
                        addFailedEntityToList(failedEntitiesList, fileId, JSONConstants.NOT_ALLOWED_EXCEPTION);
                        continue;
                    }
                    FileUtilsLocalServiceUtil.moveFileEntry(userId, fileId, destFolderId, mode);
                } catch (NoSuchResourcePermissionException e) {
                    logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + userId + " moves file " + fileId);
                    addFailedEntityToList(failedEntitiesList, fileId, JSONConstants.NOT_ALLOWED_EXCEPTION);
                } catch (DuplicateFileEntryException e) {
                    JSONObject conflict = new JSONObject();
                    conflict.put(JSONConstants.ID, fileId);
                    conflict.put(JSONConstants.NAME, fileEntry.getFileName());
                    // Find the file that cause the conflict and check if it can be replaced
                    Folder targetFolder = DLAppServiceUtil.getFolder(destFolderId);
                    FileEntry fileThatCauseConflict = DLAppLocalServiceUtil.getFileEntry(targetFolder.getGroupId(), destFolderId, fileEntry.getFileName());
                    conflict.put(JSONConstants.HAS_UPDATE_PERMISSION, PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, fileThatCauseConflict, ActionKeys.UPDATE));
                    filesInConflict.put(conflict);
                }
            } catch (Exception e) {
                logger.error(e);
                addFailedEntityToList(failedEntitiesList, fileId, JSONConstants.UNKNOWN);
            }
        }

        result.put(JSONConstants.FAILED_ENTITIES_LIST, failedEntitiesList);
        result.put(JSONConstants.FOLDERS_IN_CONFLICT, foldersInConflict);
        result.put(JSONConstants.FILES_IN_CONFLICT, filesInConflict);
        result.put(JSONConstants.SUCCESS, failedEntitiesList.length() + filesInConflict.length() + foldersInConflict.length() == 0);

        return result;
    }

    private static List<Long> jsonArrayStringToList(String idArray) {
        List<Long> idList = new ArrayList<>();
        
        try {
            JSONArray fileIdArray = new JSONArray(idArray);

            for (int i=0 ; i<fileIdArray.length() ; ++i) {
                idList.add(fileIdArray.getLong(i));
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return idList;
    }

    private static void addFailedEntityToList (JSONArray failedEntityList, long entityId, String errorMessage) {
        JSONObject failedEntity = new JSONObject();
        failedEntity.put(JSONConstants.ID, entityId);
        failedEntity.put(JSONConstants.ERROR, errorMessage);
        failedEntityList.put(failedEntity);
    }
    
}
