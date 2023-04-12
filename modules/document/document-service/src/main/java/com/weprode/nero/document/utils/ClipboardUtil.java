package com.weprode.nero.document.utils;

import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.constants.DocumentConstants;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

public class ClipboardUtil {

    private ClipboardUtil() {
        throw new IllegalStateException("Utility class");
    }
    private static final Log logger = LogFactoryUtil.getLog(ClipboardUtil.class);

    public static JSONObject copy(long userId, long destFolderId, String folderIds, String fileIds, int mode) {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        JSONArray failedEntitiesList = JSONFactoryUtil.createJSONArray();
        JSONArray foldersInConflict = JSONFactoryUtil.createJSONArray();
        JSONArray filesInConflict = JSONFactoryUtil.createJSONArray();

        List<Long> folderIdList = jsonArrayStringToList(folderIds);

        for (long folderId : folderIdList) {
            try {
                FolderUtilsLocalServiceUtil.copyFolder(userId, folderId, destFolderId, mode);
            } catch (FileNameException e) {
                try {
                    Folder folderInConflict = DLAppServiceUtil.getFolder(folderId);
                    foldersInConflict.put(DLAppJsonFactory.format(userId, folderInConflict, DocumentConstants.PRIVATE, false));
                } catch (Exception ex) {
                    logger.error(ex);
                    JSONObject failedEntity = JSONFactoryUtil.createJSONObject();
                    failedEntity.put(JSONConstants.ID, folderId);
                    failedEntity.put(JSONConstants.ERROR, JSONConstants.UNKNOWN);
                    failedEntitiesList.put(failedEntity);
                    result.put(JSONConstants.FAILED_ENTITIES_LIST, failedEntitiesList);
                }
            } catch (Exception e) {
                logger.error(e);
                JSONObject failedEntity = JSONFactoryUtil.createJSONObject();
                failedEntity.put(JSONConstants.ID, folderId);
                failedEntity.put(JSONConstants.ERROR, JSONConstants.UNKNOWN);
                failedEntitiesList.put(failedEntity);
                result.put(JSONConstants.FAILED_ENTITIES_LIST, failedEntitiesList);
            }
        }

        List<Long> fileIdList = jsonArrayStringToList(fileIds);

        for (long fileId : fileIdList) {
            try {
                FileUtilsLocalServiceUtil.copyFileEntry(userId, fileId, destFolderId, true, mode);
            } catch (DuplicateFileEntryException e) {
                try {
                    FileEntry fileEntryInConflict = DLAppServiceUtil.getFileEntry(fileId);
                    filesInConflict.put(DLAppJsonFactory.format(userId, fileEntryInConflict, DocumentConstants.PRIVATE, false));
                } catch (Exception ex) {
                    logger.error(ex);
                    JSONObject failedEntity = JSONFactoryUtil.createJSONObject();
                    failedEntity.put(JSONConstants.ID, fileId);
                    failedEntity.put(JSONConstants.ERROR, JSONConstants.UNKNOWN);
                    failedEntitiesList.put(failedEntity);
                    result.put(JSONConstants.FAILED_ENTITIES_LIST, failedEntitiesList);
                }
            } catch (Exception e) {
                logger.error(e);
                JSONObject failedEntity = JSONFactoryUtil.createJSONObject();
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
        JSONObject result = JSONFactoryUtil.createJSONObject();

        JSONArray failedEntitiesList = JSONFactoryUtil.createJSONArray();
        JSONArray foldersInConflict = JSONFactoryUtil.createJSONArray();
        JSONArray filesInConflict = JSONFactoryUtil.createJSONArray();

        List<Long> folderIdList = jsonArrayStringToList(folderIds);

        for (long folderId : folderIdList) {
            try {
                Folder folder = DLAppServiceUtil.getFolder(folderId);
                FolderUtilsLocalServiceUtil.moveFolder(userId, folder, destFolderId, mode);
            } catch (FileNameException e) {
                try {
                    Folder folderInConflict = DLAppServiceUtil.getFolder(folderId);
                    foldersInConflict.put(DLAppJsonFactory.format(userId, folderInConflict, DocumentConstants.PRIVATE, false));
                } catch (Exception ex) {
                    logger.error(ex);
                    JSONObject failedEntity = JSONFactoryUtil.createJSONObject();
                    failedEntity.put(JSONConstants.ID, folderId);
                    failedEntity.put(JSONConstants.ERROR, JSONConstants.UNKNOWN);
                    failedEntitiesList.put(failedEntity);
                    result.put(JSONConstants.FAILED_ENTITIES_LIST, failedEntitiesList);
                }
            } catch (Exception e) {
                logger.error(e);
                JSONObject failedEntity = JSONFactoryUtil.createJSONObject();
                failedEntity.put(JSONConstants.ID, folderId);
                failedEntity.put(JSONConstants.ERROR, JSONConstants.UNKNOWN);
                failedEntitiesList.put(failedEntity);
                result.put(JSONConstants.FAILED_ENTITY_LIST, failedEntitiesList);
            }
        }

        List<Long> fileIdList = jsonArrayStringToList(fileIds);

        for (long fileId : fileIdList) {
            try {
                FileUtilsLocalServiceUtil.moveFileEntry(userId, fileId, destFolderId, mode);
            } catch (DuplicateFileEntryException e) {
                try {
                    FileEntry fileEntryInConflict = DLAppServiceUtil.getFileEntry(fileId);
                    filesInConflict.put(DLAppJsonFactory.format(userId, fileEntryInConflict, DocumentConstants.PRIVATE, false));
                } catch (Exception ex) {
                    logger.error(ex);
                    JSONObject failedEntity = JSONFactoryUtil.createJSONObject();
                    failedEntity.put(JSONConstants.ID, fileId);
                    failedEntity.put(JSONConstants.ERROR, JSONConstants.UNKNOWN);
                    failedEntitiesList.put(failedEntity);
                    result.put(JSONConstants.FAILED_ENTITIES_LIST, failedEntitiesList);
                }
            } catch (Exception e) {
                logger.error(e);
                JSONObject failedEntity = JSONFactoryUtil.createJSONObject();
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
            JSONArray fileIdArray = JSONFactoryUtil.createJSONArray(idArray);

            for (int i=0 ; i<fileIdArray.length() ; ++i) {
                idList.add(fileIdArray.getLong(i));
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return idList;
    }
    
}
