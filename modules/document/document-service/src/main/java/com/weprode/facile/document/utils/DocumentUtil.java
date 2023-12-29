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

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

public class DocumentUtil {

    private DocumentUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean belongToTmpFolder(FileEntry file, long userId) throws SystemException, PortalException {
        Folder tmpFolder = FolderUtilsLocalServiceUtil.getUserTmpFolder(userId);
        return isSubEntityOf(file, tmpFolder);
    }

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
        if (FolderUtilsLocalServiceUtil.isGroupFolder(folder)){
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
