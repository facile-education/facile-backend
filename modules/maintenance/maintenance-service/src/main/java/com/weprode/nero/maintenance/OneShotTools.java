package com.weprode.nero.maintenance;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;

import java.util.List;

public class OneShotTools {

    private static final Log logger = LogFactoryUtil.getLog(OneShotTools.class);

    private int nbSubFolders = 0;
    private int nbFiles = 0;

    // Delete old folders named '._CASIER_' and their content
    public void cleanupDropboxes() {

        int nbFolders = 0;
        List<DLFolder> dlFolders = DLFolderLocalServiceUtil.getDLFolders(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (DLFolder dlFolder : dlFolders) {
            if (dlFolder.getName().equals("._CASIER_")) {
                nbFolders++;
                deleteFolder(dlFolder.getFolderId());
//                // tmp limitation for testing
//                if (nbFolders > 20) {
//                    break;
//                }
            }
        }
        logger.info("END dropbox folder deletion : " + nbFolders + " folders, " + nbSubFolders + " sub-folders and " + nbFiles + " files were deleted");
    }

    private void deleteFolder(long folderId) {

        try {
            Folder folder = DLAppServiceUtil.getFolder(folderId);

            // SubFolders
            List<Folder> subFolders = DLAppServiceUtil.getFolders(folder.getGroupId(), folder.getFolderId());
            for (Folder subFolder : subFolders) {
                nbSubFolders++;
                logger.info(" > subFolder " + subFolder.getName());
                deleteFolder(subFolder.getFolderId());
            }

            // Files
            List<FileEntry> fileList;
            fileList = DLAppServiceUtil.getFileEntries(folder.getGroupId(), folderId);
            for (FileEntry fileEntry : fileList) {
                nbFiles++;
                DLAppServiceUtil.deleteFileEntry(fileEntry.getFileEntryId());
            }
        } catch (Exception e) {
            logger.error("Error deleting folder " + folderId, e);
        }
    }
}
