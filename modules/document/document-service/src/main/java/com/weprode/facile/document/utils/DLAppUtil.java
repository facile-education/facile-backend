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
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchResourcePermissionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.weprode.facile.document.constants.DocumentConstants;
import com.weprode.facile.document.constants.PermissionConstants;
import com.weprode.facile.document.service.ActivityLocalServiceUtil;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.document.service.PermissionUtilsLocalServiceUtil;
import com.weprode.facile.group.constants.ActivityConstants;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DLAppUtil {

    private DLAppUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(DLAppUtil.class);

    public static FileEntry addFileEntry(User user, Folder folder, String fileName, InputStream is, int mode) throws PortalException, SystemException, IOException {
        return addFileEntry(user, folder, fileName, FileUtil.getBytes(is), mode);
    }

    public static FileEntry addFileEntry(User user, Folder folder, String fileName, File file, int mode) throws PortalException, SystemException, IOException{
        return addFileEntry(user, folder, fileName, FileUtil.getBytes(file), mode);
    }

    public static List<Long> addFileEntry(String sourceFileName, File file, User user, Folder folder, int mode) throws PortalException, SystemException, IOException {

        // Parse sourceFileName to get or create intermediary folders
        String[] sourceFileNameTab = sourceFileName.split(StringPool.SLASH);
        List<Long> createdPath = new ArrayList<>();

        if (sourceFileNameTab.length > 1) {
            // Loop over the intermediate folders
            for (int i = 0; i < sourceFileNameTab.length - 1; i++) {
                String intermediaryFolderName = sourceFileNameTab[i];

                // this code can merge by folders if parent folder already exist
                List<Folder> subFolders = DLAppServiceUtil.getFolders(folder.getRepositoryId(), folder.getFolderId());
                if (subFolders != null) {
                    boolean hasFound = false;
                    for (Folder subFolder : subFolders) {
                        if (subFolder.getName().equals(intermediaryFolderName)) {
                            hasFound = true;
                            if (mode == DocumentConstants.MODE_NORMAL) {
                                // Return in exception the problematic folder
                                throw new DuplicateFolderNameException(String.valueOf(subFolder.getFolderId()));
                            } else if (mode == DocumentConstants.MODE_MERGE) {
                                folder = subFolder;
                            } else if (mode == DocumentConstants.MODE_RENAME) {
                                ServiceContext serviceContext = new ServiceContext();
                                serviceContext.setAddGroupPermissions(true);

                                folder = DLAppUtil.addFolder(
                                        user.getUserId(),
                                        folder.getRepositoryId(),
                                        folder.getFolderId(),
                                        intermediaryFolderName,
                                        DocumentConstants.MODE_RENAME
                                );
                                logger.info("Set parent permission to folder " + folder.getName());
                                PermissionUtilsLocalServiceUtil.setParentPermissionToFolder(folder);
                                createdPath.add(folder.getFolderId());
                            } else if (mode == DocumentConstants.MODE_REPLACE) {
                                FolderUtilsLocalServiceUtil.deleteFolder(user.getUserId(), subFolder.getFolderId());
                                hasFound = false; // neither seen nor known hehehehe
                                break;
                            }
                        }
                    }
                    if (!hasFound) {
                        // Create the intermediary sub-folder
                        ServiceContext serviceContext = new ServiceContext();
                        serviceContext.setAddGroupPermissions(true);

                        folder = DLAppServiceUtil.addFolder(
                                UUID.randomUUID().toString(),
                                folder.getRepositoryId(),
                                folder.getFolderId(),
                                intermediaryFolderName,
                                "",
                                serviceContext
                        );
                        logger.info("Set parent permission to folder " + folder.getName());
                        PermissionUtilsLocalServiceUtil.setParentPermissionToFolder(folder);
                        createdPath.add(folder.getFolderId());
                    }
                }
            }
        }

        // Add file entry
        FileEntry fe = addFileEntry(user, folder, sourceFileNameTab[sourceFileNameTab.length - 1], file, mode);
        logger.info("User " + user.getFullName() + " adds file " + sourceFileName + " in folder " + folder.getName());
        if (fe != null) {
            createdPath.add(fe.getFileEntryId());
        }

        return createdPath;
    }

    /**
     * Add file entry from bytes
     */
    public static FileEntry addFileEntry(User user, Folder folder, String fileName, byte[] bytes, int mode) throws PortalException, SystemException {

        if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(user.getUserId(), folder, PermissionConstants.ADD_OBJECT)
            || RoleUtilsLocalServiceUtil.isAdministrator(user)) {
            // Set default permissions
            ServiceContext serviceContext = new ServiceContext();
            serviceContext.setAddGroupPermissions(true);

            // Title
            String[] splitFN = FileNameUtil.splitFileName(fileName);
            String originalTitle = FileNameUtil.getValidName(splitFN[0]);
            String extension = "." + splitFN[1];
            String name = "";
            String mimeType = MimeTypesUtil.getContentType(name); // MimeTypesUtil doesn't recognise cadyco extension

            // html sanitizing
            if (extension.equals(".html")) {
                String htmlContent = new String(bytes, StandardCharsets.UTF_8);
                htmlContent = FileUtilsLocalServiceUtil.sanitizeHTMLContent(htmlContent);
                bytes = htmlContent.getBytes(StandardCharsets.UTF_8);
            }

            // Start creation
            boolean finished = false;
            int count = 0;
            String suffix = "";
            FileEntry fileEntry = null;
            String externalReferenceCode = UUID.randomUUID().toString();
            while (!finished && count < DocumentConstants.NB_RENAMED_VERSIONS) {
                try {
                    name = originalTitle + suffix + extension;
                    //Normalize the name before adding it to DB. (Solve some issues with accented character with a form "plain char+combining accent" -- e' insteand of é)
                    name = Normalizer.normalize(name, Normalizer.Form.NFC);

                    fileEntry = DLAppLocalServiceUtil.addFileEntry(
                            externalReferenceCode,
                            user.getUserId(),
                            folder.getGroupId(),
                            folder.getFolderId(),
                            name,
                            mimeType,
                            name,
                            StringPool.BLANK, // urlTitle
                            StringPool.BLANK, // description
                            StringPool.BLANK, // changeLog
                            bytes,
                            null,
                            null,
                            serviceContext);

                    // Permissions management
                    PermissionUtilsLocalServiceUtil.setParentPermissionToFile(fileEntry);
                    finished = true;

                } catch (DuplicateFileEntryException exception) {
                    if (mode == DocumentConstants.MODE_NORMAL || mode == DocumentConstants.MODE_MERGE) { // TODO Handle merge for files
                        throw new DuplicateFileEntryException();
                    } else if (mode == DocumentConstants.MODE_RENAME) {
                        count++;
                        suffix = " (" + count + ")";
                    } else if (mode == DocumentConstants.MODE_REPLACE) {
                        boolean hasFound = false;
                        name = originalTitle + suffix + extension;
                        name = Normalizer.normalize(name, Normalizer.Form.NFC);

                        // Find the problematic file and delete it!
                        List<FileEntry> files = DLAppServiceUtil.getFileEntries(folder.getRepositoryId(), folder.getFolderId());
                        for (FileEntry subFile : files) {
                            if (subFile.getTitle().equals(name) || subFile.getFileName().equals(name)) {
                                hasFound = true;
                                // Fetch DLFileVersion to prevent from OptimisticLockException
                                FileVersion fileVersion = DLAppServiceUtil.getFileVersion(subFile.getLatestFileVersion().getFileVersionId());
                                fileEntry = DLAppServiceUtil.updateFileEntry(
                                        subFile.getFileEntryId(),
                                        name,
                                        mimeType,
                                        name,
                                        StringPool.BLANK, // urlTitle
                                        StringPool.BLANK, // description
                                        StringPool.BLANK, // changeLog
                                        DLVersionNumberIncrease.MAJOR,
                                        bytes,
                                        null,
                                        null,
                                        serviceContext
                                ); // Replace file content
                                finished = true;
                            }
                        }

                        if (!hasFound) {
                            logger.error("Normally have to find the file with the duplicate FileName exception " + name);
                            throw new DuplicateFileEntryException();
                        }
                    }
                } catch (FileSizeException e) {
                    logger.error("Max size exceeded while adding file entry to folder " + folder.getFolderId() + " by user " + user.getUserId());
                    deleteOrphanFile(folder.getGroupId(), externalReferenceCode);
                    throw new FileSizeException();
                } catch (FileExtensionException e) {
                    logger.error("Unauthorized extension while adding file entry " + fileName + " to folder " + folder.getFolderId() + " by user " + user.getUserId());
                    deleteOrphanFile(folder.getGroupId(), externalReferenceCode);
                    throw new FileExtensionException();
                } catch (AntivirusVirusFoundException e) {
                    logger.error("Virus found while adding file entry " + fileName + " to folder " + folder.getFolderId() + " by user " + user.getUserId());
                    deleteOrphanFile(folder.getGroupId(), externalReferenceCode);
                    throw new AntivirusVirusFoundException("Virus was found", "VirusName");
                } catch (Exception e) {
                    logger.error("Error while adding file entry to folder " + folder.getFolderId() + " by user " + user.getUserId(), e);
                    return null;
                }
            }

            if (fileEntry != null) {
                ActivityLocalServiceUtil.addActivity(fileEntry.getFileEntryId(), fileEntry.getFolderId(), user.getUserId(), fileEntry.getGroupId(), fileEntry.getTitle(), folder.getName(), ActivityConstants.TYPE_FILE_CREATION);
            }
            return fileEntry;
        } else {
            throw new NoSuchResourcePermissionException();
        }
    }

    private static void deleteOrphanFile(long groupId, String externalReferenceCode) {
        // This method deletes a DLFileEntry that was just created but for which the store layer has failed (file too big, infected, etc)
        // Liferay does not revert the DLFileEntry creation, so we do it here in order to not create an orphan DLFileEntry
        logger.info("About to delete just created file in error, with groupId=" + groupId + " and externalReferenceCode=" + externalReferenceCode);
        try {
            DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getDLFileEntryByExternalReferenceCode(externalReferenceCode, groupId);
            if (dlFileEntry != null) {
                DLFileEntryLocalServiceUtil.deleteDLFileEntry(dlFileEntry);
                logger.info("File deleted -> no orphan created");
            }
        } catch (PortalException e) {
            logger.error("Error deleting file with externalReferenceCode=" + externalReferenceCode + " : this might create an orphan");
        }
    }

    /**
     * Add new folder
     */
    public static Folder addFolder(long userId, long groupId, long parentFolderId, String name, int mode) throws PortalException, SystemException{
        Folder parentFolder = DLAppServiceUtil.getFolder(parentFolderId);
        if (PermissionUtilsLocalServiceUtil.hasUserFolderPermission(userId, parentFolder, PermissionConstants.ADD_OBJECT)) {

            // Set default permissions
            ServiceContext serviceContext = new ServiceContext();
            serviceContext.setScopeGroupId(groupId);
            serviceContext.setAddGroupPermissions(true);

            boolean finished = false;
            int count = 0;
            String folderTitle = FileNameUtil.getValidName(name);
            String suffixe = "";
            String title = "";
            Folder folder = null;

            while (!finished && count < DocumentConstants.NB_RENAMED_VERSIONS) {
                try {
                    title = folderTitle + suffixe;
                    folder = DLAppServiceUtil.addFolder(
                            UUID.randomUUID().toString(),
                            groupId, parentFolderId, title, "", serviceContext);
                    finished = true;
                } catch (DuplicateFolderNameException exception) {
                    if (mode == DocumentConstants.MODE_NORMAL) {
                        throw new FileNameException();
                    } else if (mode == DocumentConstants.MODE_RENAME) {
                        count++;
                        suffixe = " (" + count + ")";
                    } else if (mode == DocumentConstants.MODE_REPLACE) {
                        Folder folderThatCauseConflict = DLAppLocalServiceUtil.getFolder(parentFolder.getGroupId(), parentFolder.getFolderId(), title);
                        FolderUtilsLocalServiceUtil.deleteFolder(userId, folderThatCauseConflict.getFolderId());
                    } else if (mode == DocumentConstants.MODE_MERGE) {
                        // Return the folder which already exist
                        List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getFolders(groupId, parentFolderId); // Search him by name
                        for (DLFolder dlFolder : dlFolders) {
                            Folder subFolder = DLAppServiceUtil.getFolder(dlFolder.getFolderId());
                            if (subFolder.getName().equals(name)) {
                                return subFolder;
                            }
                        }
                    } else {
                        finished = true; // To make to loop end
                        logger.error("No mode existing with value " + mode);
                    }
                }
            }

            assert folder != null;
            DLAppServiceUtil.updateFolder(folder.getFolderId(), title, "", serviceContext);

            // Register activity
            ActivityLocalServiceUtil.addActivity(0, folder.getFolderId(), userId, folder.getGroupId(), "", folder.getName(), ActivityConstants.TYPE_FOLDER_CREATION);

            Indexer indexer = IndexerRegistryUtil.getIndexer(DLFolder.class);
            indexer.reindex(folder);

            // Permissions management
            PermissionUtilsLocalServiceUtil.setParentPermissionToFolder(folder);
            return folder;
        } else {
            throw new NoSuchResourcePermissionException();
        }
    }

}
