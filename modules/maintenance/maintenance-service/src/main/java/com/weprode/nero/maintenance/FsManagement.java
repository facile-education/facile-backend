package com.weprode.nero.maintenance;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.document.constants.DocumentConstants;

import java.io.File;
import java.util.List;

public class FsManagement {

    private static final String ROOT_PATH = "/home/entnero/liferay/data/document_library/10202/";

    private static final Log logger = LogFactoryUtil.getLog(FsManagement.class);

    int nbFilesWithoutAnyDbReference;
    int nbFilesWithDbReference;
    int nbFiles;

    private static final String BLOG_ENTRIES = "blog_entries";
    private static final String MESSAGE_BOARDS = "messageboards";
    private static final String EXPORT_DATA_ENT = "exportDataENT";
    private static final String CDT = "cahierdetexte";

    public FsManagement() {
        nbFilesWithoutAnyDbReference = 0;
        nbFilesWithDbReference = 0;
        nbFiles = 0;
    }

    public void exploreFileSystem() {

        logger.info("Start exploring file system");
        File rootFile = new File(ROOT_PATH);
        parseDirectory(rootFile);

        logger.info("On a total of " + nbFiles + " files, " + nbFilesWithDbReference + " were found in DB, whereas " + nbFilesWithoutAnyDbReference + " were not found in DB");
    }


    private void parseDirectory(File directory) {

        // Limit to 1000 files
//		if (nbTotal > 100000) {
//			//logger.info("Done "+nbTotal+" files => stopping ...");
//			return;
//		}

        File[] subFiles = directory.listFiles();
        if (subFiles != null) {
            for (File subFile : subFiles) {
                if (subFile.isDirectory()) {
                    parseDirectory(subFile);
                } else {
                    analyzeFile(subFile);
                }
            }
        }
    }

    private void analyzeFile(File file) {

        logger.info("Analyzing file "+file.getAbsolutePath()+" ("+nbFiles+")");
        nbFiles++;

        if (!isFileReferencedInDB(file.getAbsolutePath())) {
            logger.info("   > file is not referenced in DB");
            nbFilesWithoutAnyDbReference++;
        } else {
            nbFilesWithDbReference++;
        }

    }

    /**
     * Parses a filepath to determine if it is referenced in the database or not
     */
    private boolean isFileReferencedInDB(String filePath) {

        String folderIdStr = "";
        String fileName = "";
        logger.error("filePath=" + filePath);

        // Remove ROOT_PATH from filePath
        String filePathWithoutRoot = filePath.substring(ROOT_PATH.length());

        String[] fileTab = filePathWithoutRoot.split("/");

        // Loop over path to get folderId and fileName
        // folderId is composed of the 2 first parts of the path
        folderIdStr = fileTab[0] + fileTab[1];

        // Skip special paths
        if (folderIdStr.contains(BLOG_ENTRIES) || folderIdStr.contains(MESSAGE_BOARDS) || folderIdStr.contains(EXPORT_DATA_ENT) || folderIdStr.contains(CDT)) {
            logger.info("   Special path");
            return true;
        }
        // Check if the folder path corresponds to an existing DLFolder in DB
        DLFolder dlFolder = getFolderByStr(folderIdStr);
        if (dlFolder != null) {
            logger.info("   Folder exists");
        } else {
            logger.info("   Folder does not exist in DB -> candidate for deletion");
            return false;
        }

        if (dlFolder.getName().equals(DocumentConstants.TMP_IMG_FOLDER_NAME)) {
            logger.info("   TMP IMG");
        }
        if (dlFolder.getName().equals(DocumentConstants.SENDING_BOX_FOLDER_NAME)) {
            logger.info("   SENDING BOX");
        }

        // Check if the user still exists or not
        try {
            UserLocalServiceUtil.getUser(dlFolder.getUserId());
        } catch (Exception e) {
            logger.info("   User " + dlFolder.getUserId() + " does not exist anymore -> candidate for deletion");
            return false;
        }


        // file name is the 5th ou 6th part of the path
        for (String fileTabSubDir : fileTab) {
            if (fileTabSubDir.contains(".")) {
                fileName = fileTabSubDir.substring(0, fileTabSubDir.length() - 1);
                break;
            }
        }

        // Identify DLFileEntry
        DLFileEntry dlfe = null;
        try {
            dlfe = DLFileEntryLocalServiceUtil.getFileEntryByName(dlFolder.getGroupId(), dlFolder.getFolderId(), fileName);
        } catch (Exception e) {
        }
        if (dlfe == null) {
            logger.info("   File entry could not be found for folderId="+dlFolder.getFolderId()+" and groupId="+dlFolder.getGroupId()+" and fileName="+fileName);
            return false;
        } else {
            logger.info("   FOUND");
        }

        return true;
    }

    /**
     * Fetch a DLFolder by its folderId (PK) in string format
     */
    private DLFolder getFolderByStr(String folderIdStr) {
        long folderId = 0;
        try {
            folderId = Long.parseLong(folderIdStr);
        } catch (NumberFormatException nfe) {
            logger.error("Error : could not convert folder id "+folderIdStr);
            return null;
        }

        DLFolder dlFolder = null;
        try {
            dlFolder = DLFolderLocalServiceUtil.getDLFolder(folderId);
        } catch (Exception e) {
            return null;
        }
        return dlFolder;
    }


    public void exploreDB() {

        logger.error("Explore DB");
        int nbFilesExisting = 0;
        int nbFilesNotExisting = 0;
        // Loop over all FileEntries
        try {
            List<DLFileEntry> dlFileEntries = DLFileEntryLocalServiceUtil.getDLFileEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            for (DLFileEntry fileEntry : dlFileEntries) {
                try {
                    // Check fileEntry existence on FS
                    String filePath = ROOT_PATH;

                    // Split folderId in 4-length blocks
                    String folderIdStr = fileEntry.getFolderId() + "";
                    filePath += folderIdStr.substring(0, 4) + "/";
                    filePath += folderIdStr.substring(4) + "/";

                    // Split file name in 2-length blocks
                    String fileNameStr = fileEntry.getName() + "";
                    filePath += fileNameStr.substring(0, 2) + "/";
                    filePath += fileNameStr.substring(2, 4) + "/";
                    if (fileNameStr.length() >= 7) {
                        filePath += fileNameStr.substring(4, 6) + "/";
                    }
                    filePath += fileEntry.getName() + ".";

                    File file = new File(filePath);
                    if (file == null || !file.exists()) {
                        nbFilesNotExisting++;
                    } else {
                        nbFilesExisting++;
                        continue;
                    }

                    // Check if the user still exists or not
                    try {
                        UserLocalServiceUtil.getUser(fileEntry.getUserId());
                    } catch (Exception e) {
                        logger.info("   User " + fileEntry.getUserId() + " does not exist anymore -> candidate for deletion");
                    }

                } catch (Exception e) {
                    logger.error("Error happened when looping over file entry " + fileEntry.getFileEntryId(), e);
                }

            }
        } catch (Exception e) {
            logger.error("Error happened when looping over all file entries", e);
        }
        logger.info("END : " + nbFilesExisting + " were found on FS, whereas " + nbFilesNotExisting + " were not found on FS");
    }

}
