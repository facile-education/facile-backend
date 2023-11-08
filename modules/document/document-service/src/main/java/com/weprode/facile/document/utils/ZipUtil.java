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
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.weprode.facile.document.constants.DocumentConstants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    private ZipUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(ZipUtil.class);

    /**
     * Loop over files of folder to add them into zip archive
     */
    public static void addFolderToZip(List<Folder> folderEntries, String directory, ZipOutputStream out) throws SystemException, PortalException {

        for (Folder dlfo : folderEntries) {

            logger.info("Adding folder "+dlfo.getName()+" to zip ...");
            List<FileEntry> ldlfe = DLAppServiceUtil.getFileEntries(dlfo.getGroupId(), dlfo.getFolderId());

            String folderName;
            if (dlfo.getName().equals(DocumentConstants.SCHOOL_BAG_FOLDER_NAME)) {
                folderName = "Personnel";
            } else {
                folderName = dlfo.getName();
            }

            addFileEntriesToZip(ldlfe, directory + folderName + StringPool.SLASH, out);

            // Recursive call
            List<Folder> ldlfo = DLAppServiceUtil.getFolders(dlfo.getGroupId(), dlfo.getFolderId());
            addFolderToZip(ldlfo, directory + folderName + StringPool.SLASH, out);
        }
    }

    /**
     * Add a list of file entries into a zip archive
     */
    public static void addFileEntriesToZip(List<FileEntry> fileEntries, String folderPath, ZipOutputStream out) {

        for (FileEntry dlfe : fileEntries) {
            logger.info("Adding file " + dlfe.getTitle() + " (id "+dlfe.getFileEntryId()+") into zip with folderPath=" + folderPath);
            try {

                InputStream inputStream = DLFileEntryLocalServiceUtil.getFileAsStream(dlfe.getFileEntryId(), dlfe.getVersion());
                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(folderPath + dlfe.getTitle()));

                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                inputStream.close();

            } catch (Exception e) {
                logger.error("Error while adding file entry into zip archive", e);
            }
        }
    }

    /**
     * Create a zip stream
     */
    public static ByteArrayOutputStream createZipStream(long[] folderIdArray, long[] fileIdArray) throws PortalException, SystemException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ZipOutputStream zipOut = new ZipOutputStream(baos);
        zipOut.setLevel(Deflater.BEST_COMPRESSION);

        // Loop over files
        List<FileEntry> fileEntries = new ArrayList<>();
        for (long fileEntryId : fileIdArray) {
            if (fileEntryId != 0) {
                FileEntry dlfe = DLAppServiceUtil.getFileEntry(fileEntryId);
                fileEntries.add(dlfe);
            }
        }
        addFileEntriesToZip(fileEntries, StringPool.BLANK, zipOut);

        // Loop over folders
        List<Folder> folderEntries = new ArrayList<>();
        for (long folderId : folderIdArray) {
            if (folderId != 0) {
                Folder dlfo = DLAppServiceUtil.getFolder(folderId);
                folderEntries.add(dlfo);
            }
        }
        addFolderToZip(folderEntries, StringPool.BLANK, zipOut);

        zipOut.close();
        baos.close();

        return baos;
    }
}
