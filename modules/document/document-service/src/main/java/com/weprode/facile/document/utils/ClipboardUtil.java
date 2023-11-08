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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClipboardUtil {

    private ClipboardUtil() {
        throw new IllegalStateException("Utility class");
    }
    private static final Log logger = LogFactoryUtil.getLog(ClipboardUtil.class);

    public static JSONObject copy(long userId, long destFolderId, String folderIds, String fileIds, int mode) {
        JSONObject result = new JSONObject();
        JSONArray failedEntitiesList = new JSONArray();
        JSONArray foldersInConflict = new JSONArray();
        JSONArray filesInConflict = new JSONArray();

        List<Long> folderIdList = jsonArrayStringToList(folderIds);

        for (long folderId : folderIdList) {
            try {
                if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, folderId)) {
                    logger.info("User " + userId + " tries to copy folderId " + folderId + " but has no permission");
                    continue;
                }
                FolderUtilsLocalServiceUtil.copyFolder(userId, folderId, destFolderId, mode);
            } catch (FileNameException e) {
                try {
                    Folder folderInConflict = DLAppServiceUtil.getFolder(folderId);
                    foldersInConflict.put(DLAppJsonFactory.format(userId, folderInConflict, DocumentConstants.PRIVATE, false));
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
                result.put(JSONConstants.FAILED_ENTITIES_LIST, failedEntitiesList);
            }
        }

        List<Long> fileIdList = jsonArrayStringToList(fileIds);

        for (long fileId : fileIdList) {
            try {
                FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileId);
                if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, fileEntry.getFolderId())) {
                    logger.info("User " + userId + " tries to copy file " + fileId + " but has no permission");
                    continue;
                }
                FileUtilsLocalServiceUtil.copyFileEntry(userId, fileId, destFolderId, true, mode);
            } catch (DuplicateFileEntryException e) {
                try {
                    FileEntry fileEntryInConflict = DLAppServiceUtil.getFileEntry(fileId);
                    filesInConflict.put(DLAppJsonFactory.format(userId, fileEntryInConflict, DocumentConstants.PRIVATE, false));
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
                if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, folderId)) {
                    logger.info("User " + userId + " tries to move folderId " + folderId + " but has no permission");
                    continue;
                }
                FolderUtilsLocalServiceUtil.moveFolder(userId, folder, destFolderId, mode);
            } catch (FileNameException e) {
                try {
                    Folder folderInConflict = DLAppServiceUtil.getFolder(folderId);
                    foldersInConflict.put(DLAppJsonFactory.format(userId, folderInConflict, DocumentConstants.PRIVATE, false));
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
                if (!FolderUtilsLocalServiceUtil.isAllowedToAccessFolder(userId, fileEntry.getFolderId())) {
                    logger.info("User " + userId + " tries to move file " + fileId + " but has no permission");
                    continue;
                }
                FileUtilsLocalServiceUtil.moveFileEntry(userId, fileId, destFolderId, mode);
            } catch (DuplicateFileEntryException e) {
                try {
                    FileEntry fileEntryInConflict = DLAppServiceUtil.getFileEntry(fileId);
                    filesInConflict.put(DLAppJsonFactory.format(userId, fileEntryInConflict, DocumentConstants.PRIVATE, false));
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
    
}
