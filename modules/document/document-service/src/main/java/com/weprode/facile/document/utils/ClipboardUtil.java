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
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchResourcePermissionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.constants.DocumentConstants;
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
                FolderUtilsLocalServiceUtil.copyFolder(userId, folderId, targetFolderId, mode);
            } catch (NoSuchResourcePermissionException e) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + userId + " copies folder " + folderId);
                addFailedEntityToList(failedEntitiesList, folderId, JSONConstants.NOT_ALLOWED_EXCEPTION);
            } catch (FileNameException e) {
                foldersInConflict.put(FolderUtilsLocalServiceUtil.formatWithOnlyMandatoryFields(folderId));
            } catch (Exception e) {
                logger.error(e);
                addFailedEntityToList(failedEntitiesList, folderId, JSONConstants.UNKNOWN);
            }
        }

        List<Long> fileIdList = jsonArrayStringToList(fileIds);

        for (long fileId : fileIdList) {
            try {
                FileUtilsLocalServiceUtil.copyFileEntry(userId, fileId, targetFolderId, true, mode);
            } catch (NoSuchResourcePermissionException e) {
                logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + userId + " copies file " + fileId);
                addFailedEntityToList(failedEntitiesList, fileId, JSONConstants.NOT_ALLOWED_EXCEPTION);
            } catch (DuplicateFileEntryException e) {
                filesInConflict.put(FileUtilsLocalServiceUtil.formatWithOnlyMandatoryFields(fileId));
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
                if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, folderId)
                        || !PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, folder, ActionKeys.VIEW)) {
                    logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + userId + " moves folder " + folderId);
                    continue;
                }
                FolderUtilsLocalServiceUtil.moveFolder(userId, folder, destFolderId, mode);
            } catch (FileNameException e) {
                try {
                    Folder folderInConflict = DLAppServiceUtil.getFolder(folderId);
                    foldersInConflict.put(FolderUtilsLocalServiceUtil.format(userId, folderInConflict, DocumentConstants.PRIVATE, false));
                } catch (Exception ex) {
                    logger.error(ex);
                    JSONObject failedEntity = new JSONObject();
                    failedEntity.put(JSONConstants.ID, folderId);
                    failedEntity.put(JSONConstants.ERROR, JSONConstants.UNKNOWN);
                    failedEntitiesList.put(failedEntity);
                    result.put(JSONConstants.FAILED_ENTITIES_LIST, failedEntitiesList);
                }
            } catch (Exception e) {
                logger.error(e);
                JSONObject failedEntity = new JSONObject();
                failedEntity.put(JSONConstants.ID, folderId);
                failedEntity.put(JSONConstants.ERROR, JSONConstants.UNKNOWN);
                failedEntitiesList.put(failedEntity);
                result.put(JSONConstants.FAILED_ENTITY_LIST, failedEntitiesList);
            }
        }

        List<Long> fileIdList = jsonArrayStringToList(fileIds);

        for (long fileId : fileIdList) {
            try {
                FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileId);
                if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, fileEntry.getFolderId())
                        || !PermissionUtilsLocalServiceUtil.hasUserFilePermission(userId, fileEntry, ActionKeys.VIEW)) {
                    logger.error(JSONConstants.UNAUTHORIZED_ACCESS_LOG + "User " + userId + " moves file " + fileId);
                    continue;
                }
                FileUtilsLocalServiceUtil.moveFileEntry(userId, fileId, destFolderId, mode);
            } catch (DuplicateFileEntryException e) {
                try {
                    FileEntry fileEntryInConflict = DLAppServiceUtil.getFileEntry(fileId);
                    filesInConflict.put(FileUtilsLocalServiceUtil.format(userId, fileEntryInConflict, DocumentConstants.PRIVATE, false));
                } catch (Exception ex) {
                    logger.error(ex);
                    JSONObject failedEntity = new JSONObject();
                    failedEntity.put(JSONConstants.ID, fileId);
                    failedEntity.put(JSONConstants.ERROR, JSONConstants.UNKNOWN);
                    failedEntitiesList.put(failedEntity);
                    result.put(JSONConstants.FAILED_ENTITIES_LIST, failedEntitiesList);
                }
            } catch (Exception e) {
                logger.error(e);
                JSONObject failedEntity = new JSONObject();
                failedEntity.put(JSONConstants.ID, fileId);
                failedEntity.put(JSONConstants.ERROR, JSONConstants.UNKNOWN);
                failedEntitiesList.put(failedEntity);
                result.put(JSONConstants.FAILED_ENTITIES_LIST, failedEntitiesList);
            }
        }

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
