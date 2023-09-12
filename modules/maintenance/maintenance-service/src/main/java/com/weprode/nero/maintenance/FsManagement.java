package com.weprode.nero.maintenance;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.document.constants.DocumentConstants;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class FsManagement {

    private static final String ROOT_PATH = "/opt/liferay-7.4.3.40-ga40/data/document_library/10202/";

    private static final Log logger = LogFactoryUtil.getLog(FsManagement.class);

    int nbFilesWithoutAnyDbReference;
    int nbFilesWithDbReference;
    int nbFiles;
    int nbErrors;

    public FsManagement() {
        nbFilesWithoutAnyDbReference = 0;
        nbFilesWithDbReference = 0;
        nbFiles = 0;
        nbErrors = 0;
    }

    public void exploreFileSystem() {

        logger.info("Start exploring file system");
        File rootFile = new File(ROOT_PATH);
        parseDirectory(rootFile);

        logger.info("On a total of " + nbFiles + " files, " + nbFilesWithDbReference + " were found in DB, whereas " + nbFilesWithoutAnyDbReference + " were not found in DB");
        logger.info(nbErrors + "files deletion was in error");
        logger.info("=======================================================================");
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
            //logger.info("   > file is not referenced in DB");
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

        // Remove ROOT_PATH from filePath
        String filePathWithoutRoot = filePath.substring(ROOT_PATH.length());

        String[] fileTab = filePathWithoutRoot.split("/");

        // Loop over path to get folderId and fileName
        // folderId is composed of the 2 first parts of the path
        folderIdStr = fileTab[0] + fileTab[1];

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
            logger.info("Deleting TO DO");

            logger.info("DELETION DONE");
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
        int nbUserDeleted = 0;
        int nbOldFiles = 0;
        int nbDeleted = 0;
        nbErrors = 0;
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Loop over all FileEntries
        try {
            List<DLFileEntry> dlFileEntries = DLFileEntryLocalServiceUtil.getDLFileEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            logger.info("Got " + dlFileEntries.size() + " files to process");
            int nbFilesProcessed = 0;
            for (DLFileEntry fileEntry : dlFileEntries) {
                nbFilesProcessed++;
                if (nbFilesProcessed % 100 == 0) {
                    logger.info("Processed " + nbFilesProcessed + " files");
                }
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
                        nbUserDeleted++;
                    }
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fileEntry.getModifiedDate());
                    if (cal.get(Calendar.YEAR) < 2023) {
                        nbOldFiles++;
                    } else {
                        logger.info(">> new File is " + fileEntry.getTitle() + " of user " + fileEntry.getUserName());
                    }
                    logger.info("NOT FOUND : modDate = " + sdf.format(fileEntry.getModifiedDate()) + ", folderId=" + fileEntry.getFolderId() + ", name=" + fileEntry.getName() + ", path=" + filePath);

                    DLAppServiceUtil.deleteFileEntry(fileEntry.getFileEntryId());

                    logger.info("DELETION DONE");
                    nbDeleted++;
                } catch (Exception e) {
                    logger.error("Error happened when looping over file entry " + fileEntry.getFileEntryId(), e);
                    nbErrors++;
                }

            }
        } catch (Exception e) {
            logger.error("Error happened when looping over all file entries", e);
        }
        logger.info("END : " + nbFilesExisting + " were found on FS, whereas " + nbFilesNotExisting + " were not found on FS");
        logger.info(nbUserDeleted + " files have their user not existing");
        logger.info(nbOldFiles + " files are older than 2022");
        logger.info(nbDeleted + " files were successfully deleted");
        logger.info(nbErrors + " files were in error");
    }

}
