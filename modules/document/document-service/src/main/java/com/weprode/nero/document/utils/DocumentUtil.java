package com.weprode.nero.document.utils;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.weprode.nero.document.constants.DocumentConstants;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

public class DocumentUtil {

    private DocumentUtil() {
        throw new IllegalStateException("Utility class");
    }

    // recursive search if parent folder is sendingBox root folder
    public static boolean belongToSendingBox(Folder folder, long userId) throws SystemException, PortalException {
        Folder sendingBoxFolder = FolderUtilsLocalServiceUtil.getSendingBox(userId);
        return isSubEntityOf(folder, sendingBoxFolder);
    }

    public static boolean belongToSendingBox(FileEntry file, long userId) throws SystemException, PortalException {
        Folder sendingBoxFolder = FolderUtilsLocalServiceUtil.getSendingBox(userId);
        return isSubEntityOf(file, sendingBoxFolder);
    }

    public static boolean belongToTmpFolder(FileEntry file, long userId) throws SystemException, PortalException {
        Folder tmpFolder = FolderUtilsLocalServiceUtil.getTmpFolder(userId);
        return isSubEntityOf(file, tmpFolder);
    }

//    // recursive search if parent folder is trash root folder
//    public static boolean belongToTrash(Folder folder, long userId) throws SystemException, PortalException {
//        Folder trashFolder = FoldersUtil.getTrash(userId);
//        return isSubEntityOf(folder, trashFolder);
//    }
//
//    public static boolean belongToTrash(FileEntry file, long userId) throws SystemException, PortalException {
//        Folder trashFolder = FoldersUtil.getTrash(userId);
//        return isSubEntityOf(file, trashFolder);
//    }

    // return true if the document is in a subFolder
    public static boolean belongToASendingBoxSubFolder(Folder folder, long userId) throws SystemException, PortalException {
        Folder sendingBoxFolder = FolderUtilsLocalServiceUtil.getSendingBox(userId);
        return folder.getParentFolderId() != sendingBoxFolder.getFolderId();
    }

    public static boolean belongToASendingBoxSubFolder(FileEntry file, long userId) throws SystemException, PortalException {
        Folder sendingBoxFolder = FolderUtilsLocalServiceUtil.getSendingBox(userId);
        return file.getFolder().getFolderId() != sendingBoxFolder.getFolderId();
    }

//    // return true if the document is in a subFolder of trash
//    public static boolean belongToATrashSubFolder(Folder folder, long userId) throws SystemException, PortalException {
//        Folder trashFolder = FoldersUtil.getTrash(userId);
//        return folder.getParentFolderId() != trashFolder.getFolderId();
//    }
//
//    public static boolean belongToATrashSubFolder(FileEntry file, long userId) throws SystemException, PortalException {
//        Folder trashFolder = FoldersUtil.getTrash(userId);
//        return file.getFolder().getFolderId() != trashFolder.getFolderId();
//    }

    public static boolean isSubEntityOf(Folder entity, Folder potentialAncestor) throws PortalException, SystemException {
        if (entity.getParentFolderId() == potentialAncestor.getFolderId()){
            return true;
        } else {
            if (entity.getParentFolderId() == 0) {
                return false;
            } else {
                return isSubEntityOf(entity.getParentFolder(), potentialAncestor);
            }
        }
    }

    public static boolean isSubEntityOf(FileEntry entity, Folder potentialAncestor) throws PortalException, SystemException {
        if (entity.getFolderId() == potentialAncestor.getFolderId()) {
            return true;
        } else {
            return isSubEntityOf(DLAppServiceUtil.getFolder(entity.getFolderId()), potentialAncestor);
        }
    }

    public static int getDocumentSpace(Folder folder) throws SystemException {
        if (FolderUtilsLocalServiceUtil.isGroupFolder(folder)){
            return DocumentConstants.COLLABORATIVE;
        } else {
            return DocumentConstants.PRIVATE;
        }
    }

    public static int getSpace(Folder folder, long userId) throws SystemException, PortalException {
        if (belongToSendingBox(folder, userId)){
            return DocumentConstants.SENDING_BOX;
//        } else if (belongToTrash(folder, userId)){
//            return DocumentConstants.TRASH;
        } else if (FolderUtilsLocalServiceUtil.isGroupFolder(folder)){
            return DocumentConstants.COLLABORATIVE;
        } else {
            return DocumentConstants.PRIVATE;
        }
    }

    public static int getSpace(FileEntry fileEntry, long userId) throws SystemException, PortalException {
        return getSpace(fileEntry.getFolder(), userId);
    }

    public static List<Long> extractLongIds (String str) {
        List<Long> idList = new ArrayList<>();

        String[] folderIdsTab = str.split("\"");
        if (folderIdsTab.length > 0) {
            for (String folderIdStr : folderIdsTab) {
                try {
                    long folderId = Long.parseLong(folderIdStr);
                    if (folderId != 0) {
                        idList.add(folderId);
                    }
                } catch (Exception e) {
                    // The character is not a number
                }
            }
        }

        return idList;
    }

}
