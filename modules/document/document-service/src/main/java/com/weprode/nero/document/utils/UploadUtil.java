package com.weprode.nero.document.utils;

import com.liferay.document.library.kernel.exception.DuplicateFileEntryException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.constants.PermissionConstants;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.document.service.PermissionUtilsLocalServiceUtil;

import java.io.File;
import java.util.List;

public class UploadUtil {

    private UploadUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(UploadUtil.class);

    // Add new file from workspace
    public static JSONObject uploadFile(User user, long folderId, String fileName, File file, int mode) {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        
        result.put(JSONConstants.SUCCESS, false);

        try {
            // If folderId is 0, then use the tmp folder
            if (folderId == 0) {
                folderId = FolderUtilsLocalServiceUtil.getTmpFolder(user.getUserId()).getFolderId();
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
                } catch (DuplicateFileEntryException e) {
                    result.put(JSONConstants.ERROR, JSONConstants.DUPLICATE_FILE_EXCEPTION);
                    return result;
                }

                FileEntry uploadedFile = DLAppServiceUtil.getFileEntry(createdPath.get(createdPath.size() - 1));
                result.put(JSONConstants.UPLOADED_FILE, DLAppJsonFactory.format(user.getUserId(), uploadedFile, space));
                if (createdPath.size() > 1) {
                    Folder firstCreatedFolder = DLAppServiceUtil.getFolder(createdPath.get(0));
                    result.put(JSONConstants.FIRST_CREATED_FOLDER, DLAppJsonFactory.format(user.getUserId(), firstCreatedFolder, space));
                }
                result.put(JSONConstants.UPLOADED_FILE, DLAppJsonFactory.format(user.getUserId(), uploadedFile, space));
                result.put(JSONConstants.SUCCESS, true);
            } else {
                logger.error("Error : user " + user.getFullName() + " does not have permission to upload file " + fileName + " into folder " + folderId);
                result.put(JSONConstants.ERROR, "PermissionException");
            }
        } catch (Exception e) {
            result.put(JSONConstants.ERROR, "Error in uploading files");
            logger.error("Error in uploading files", e);
        }

        return result;
    }

}
