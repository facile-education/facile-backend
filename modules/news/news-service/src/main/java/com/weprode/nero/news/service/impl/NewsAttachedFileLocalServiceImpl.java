package com.weprode.nero.news.service.impl;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.news.model.NewsAttachedFile;
import com.weprode.nero.news.model.NewsPopulation;
import com.weprode.nero.news.service.base.NewsAttachedFileLocalServiceBaseImpl;
import com.weprode.nero.news.service.persistence.NewsAttachedFilePK;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.news.model.NewsAttachedFile",
        service = AopService.class
)
public class NewsAttachedFileLocalServiceImpl extends NewsAttachedFileLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(NewsAttachedFileLocalServiceImpl.class);

    public boolean addFile(long newsId, long fileId) {
        try {
            NewsAttachedFilePK newsAttachFilePK = new NewsAttachedFilePK(newsId, fileId);
            NewsAttachedFile newsAttachedFile = newsAttachedFilePersistence.create(newsAttachFilePK);
            newsAttachedFilePersistence.update(newsAttachedFile);

            return true;
        } catch (Exception e) {
            logger.error("Error adding attached file for newsId " + newsId + " and fileId " + fileId);
        }
        return false;
    }

    public JSONArray convertNewsFiles(long newsId) {
        JSONArray jsonFiles = new JSONArray();

        try {
            List<NewsAttachedFile> newsAttachedFiles = newsAttachedFilePersistence.findBynewsId(newsId);
            for (NewsAttachedFile newsAttachedFile : newsAttachedFiles) {
                try {
                    JSONObject jsonAttachment = new JSONObject();
                    FileEntry fileEntry = DLAppServiceUtil.getFileEntry(newsAttachedFile.getFileId());
                    jsonAttachment.put(JSONConstants.ID, newsAttachedFile.getFileId());
                    jsonAttachment.put(JSONConstants.NAME, fileEntry.getTitle());
                    jsonAttachment.put(JSONConstants.TYPE, "File");
                    jsonAttachment.put(JSONConstants.EXTENSION, fileEntry.getExtension().toLowerCase());
                    jsonAttachment.put(JSONConstants.URL, FileUtilsLocalServiceUtil.getDownloadUrl(fileEntry));
                    jsonFiles.put(jsonAttachment);
                } catch (Exception e) {
                    logger.error("Error converting attached file ", e);
                }
            }
        } catch (Exception e) {
            logger.error("Error while converting files for news " + newsId, e);
        }

        return jsonFiles;
    }

    public boolean hasAttachedFiles (long newsId) {
        try {
            return newsAttachedFilePersistence.findBynewsId(newsId).size() > 0;
        } catch (Exception e) {
            logger.error("Error while fetching attached files for news " + newsId, e);
            return false;
        }
    }

    public void deleteByNewsId(long newsId) throws SystemException {
        logger.info("Deleting attached files for news " + newsId);

        // Delete files
        List<NewsPopulation> populations = newsPopulationPersistence.findBynewsId(newsId);

        // Loop over groups
        List<Long> groupIds = new ArrayList<>();
        for (NewsPopulation population : populations) {
            if (!groupIds.contains(population.getGroupId())) {
                groupIds.add(population.getGroupId());
            }
        }

        for (Long groupId : groupIds) {
            logger.info("Deleting attached files for newsId " + newsId + " : groupId " + groupId);
            try {
                Folder groupNewsFolder = FolderUtilsLocalServiceUtil.getGroupNewsFolder(groupId);
                // Check subFolder newsId
                List<Folder> folderList = DLAppServiceUtil.getFolders(groupId, groupNewsFolder.getFolderId());
                for (Folder folder : folderList) {
                    if (folder.getName().equals("" + newsId)) {
                        logger.info("About to delete folder news for groupId " + groupId + " and newsId " + newsId);
                        // Delete existing attached files
                        List<FileEntry> fileList = DLAppServiceUtil.getFileEntries(groupId, folder.getFolderId());
                        for (FileEntry fileEntry : fileList) {
                            logger.info("Deleting existing attached file " + fileEntry.getTitle());
                            DLAppServiceUtil.deleteFileEntry(fileEntry.getFileEntryId());
                        }
                        logger.info("Deleting folder " + folder.getFolderId());
                        DLAppServiceUtil.deleteFolder(folder.getFolderId());
                    }
                }

            } catch (Exception e) {
                logger.error("Error adding attached files for newsId " + newsId + " and groupId " + groupId, e);
            }
        }

        newsAttachedFilePersistence.removeBynewsId(newsId);
    }
}
