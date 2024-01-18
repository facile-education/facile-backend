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

import com.liferay.document.library.kernel.antivirus.AntivirusVirusFoundException;
import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.DuplicateFolderNameException;
import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.document.constants.PermissionConstants;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

public class UploadUtil {

    private UploadUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(UploadUtil.class);

    public static JSONObject uploadFile(User user, long folderId, String fileName, File file, int mode) {
        return uploadFile(user, folderId, fileName, file, mode, false);
    }

    // Add new file from workspace
    public static JSONObject uploadFile(User user, long folderId, String fileName, File file, int mode, boolean withDisplayUrl) {
        JSONObject result = new JSONObject();
        
        result.put(JSONConstants.SUCCESS, false);

        try {
            // If folderId is 0, then use the tmp folder
            if (folderId == 0) {
                folderId = FolderUtilsLocalServiceUtil.getUserTmpFolder(user.getUserId()).getFolderId();
            }

            Folder folder = DLAppServiceUtil.getFolder(folderId);
            int space = DocumentUtil.getSpace(folder, user.getUserId());

            // Check for ADD permission in current folder
            if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, PermissionConstants.ADD_OBJECT)) {

                List<Long> createdPath;
                try {
                    createdPath = DLAppUtil.addFileEntry(fileName, file, user, folder, mode);
                } catch (FileSizeException e) {
                    result.put(JSONConstants.ERROR, JSONConstants.FILE_SIZE_EXCEPTION);
                    return result;
                } catch (FileExtensionException e) {
                    result.put(JSONConstants.ERROR, JSONConstants.FILE_EXTENSION_EXCEPTION);
                    return result;
                } catch (DuplicateFileEntryException e) {
                    // Find the file that cause the conflict and check if it can be replaced
                    FileEntry fileThatCauseConflict = DLAppLocalServiceUtil.getFileEntry(folder.getGroupId(), folderId, fileName);
                    result.put(JSONConstants.HAS_UPDATE_PERMISSION, PermissionUtilsLocalServiceUtil.hasUserFilePermission(user.getUserId(), fileThatCauseConflict, ActionKeys.UPDATE));

                    result.put(JSONConstants.ERROR, JSONConstants.DUPLICATE_FILE_EXCEPTION);
                    return result;
                } catch (DuplicateFolderNameException e) {
                    // Get the problematic folder
                    Folder folderThatCauseConflict = DLAppLocalServiceUtil.getFolder(Long.parseLong(e.getMessage()));
                    result.put(JSONConstants.FOLDER_NAME, folderThatCauseConflict.getName());
                    result.put(JSONConstants.HAS_UPDATE_PERMISSION, PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folderThatCauseConflict, PermissionConstants.ADD_OBJECT));

                    result.put(JSONConstants.ERROR, JSONConstants.DUPLICATE_FOLDER_EXCEPTION);
                    return result;
                } catch (AntivirusVirusFoundException e) {
                    result.put(JSONConstants.ERROR, JSONConstants.ANTIVIRUS_EXCEPTION);
                    return result;
                }

                FileEntry uploadedFile = DLAppServiceUtil.getFileEntry(createdPath.get(createdPath.size() - 1));
                if (createdPath.size() > 1) {
                    Folder firstCreatedFolder = DLAppServiceUtil.getFolder(createdPath.get(0));
                    Folder parentFolder = firstCreatedFolder.getParentFolder();

                    if (mode == DocumentConstants.MODE_RENAME) {
                        // Return the old folderName (that one is replaced by the new folder) to change it in the next concerned uploads
                        String[] oldFileNameTab = fileName.split(StringPool.SLASH);

                        List<Folder> siblingsFolders = DLAppServiceUtil.getFolders(parentFolder.getRepositoryId(), parentFolder.getFolderId());
                        for (Folder siblingsFolder : siblingsFolders) {
                            for (int i = 0; i < oldFileNameTab.length - 1; i++) {
                                if (oldFileNameTab[i].equals(siblingsFolder.getName())) {
                                    result.put(JSONConstants.OLD_FOLDER_NAME, oldFileNameTab[i]);
                                    break;
                                }
                            }
                        }
                    }
                    result.put(JSONConstants.FIRST_CREATED_FOLDER, FolderUtilsLocalServiceUtil.format(user.getUserId(), firstCreatedFolder, space));
                }
                JSONObject jsonFormattedFile = FileUtilsLocalServiceUtil.format(user.getUserId(), uploadedFile, space);
                if (withDisplayUrl) {
                    String documentURL = FileUtilsLocalServiceUtil.getDisplayUrl(
                            uploadedFile,
                            uploadedFile.getLatestFileVersion().getFileVersionId(),
                            user.getUserId(),
                            true
                    );
                    jsonFormattedFile.put(JSONConstants.FILE_URL, documentURL);
                }
                result.put(JSONConstants.UPLOADED_FILE, jsonFormattedFile);
                result.put(JSONConstants.SUCCESS, true);
            } else {
                logger.error("Error : user " + user.getFullName() + " does not have permission to upload file " + fileName + " into folder " + folderId);
                result.put(JSONConstants.ERROR, "PermissionException");
            }
        } catch (FileExtensionException e) {
            result.put(JSONConstants.ERROR, JSONConstants.FILE_EXTENSION_EXCEPTION);
            logger.error("Error in uploading files", e);
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, "Error in uploading files");
            logger.error("Error in uploading files", e);
        }

        return result;
    }

}
