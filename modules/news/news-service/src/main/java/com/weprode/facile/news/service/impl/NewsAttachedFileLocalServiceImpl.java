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

package com.weprode.facile.news.service.impl;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.document.service.FileUtilsLocalServiceUtil;
import com.weprode.facile.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.facile.news.model.NewsAttachedFile;
import com.weprode.facile.news.model.NewsPopulation;
import com.weprode.facile.news.service.base.NewsAttachedFileLocalServiceBaseImpl;
import com.weprode.facile.role.service.RoleUtilsLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.facile.news.model.NewsAttachedFile",
        service = AopService.class
)
public class NewsAttachedFileLocalServiceImpl extends NewsAttachedFileLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(NewsAttachedFileLocalServiceImpl.class);

    public boolean addFile(long newsId, long groupId, long fileId) {
        try {
            NewsAttachedFile newsAttachedFile = newsAttachedFilePersistence.create(counterLocalService.increment());
            newsAttachedFile.setNewsId(newsId);
            newsAttachedFile.setGroupId(groupId);
            newsAttachedFile.setFileId(fileId);
            newsAttachedFilePersistence.update(newsAttachedFile);

            return true;
        } catch (Exception e) {
            logger.error("Error adding attached file for newsId " + newsId + " and fileId " + fileId);
        }
        return false;
    }

    public List<NewsAttachedFile> getNewsAttachedFiles(long newsId) {
        return newsAttachedFilePersistence.findBynewsId(newsId);
    }

    public JSONArray convertNewsFiles(long newsId, long userId) {
        JSONArray jsonFiles = new JSONArray();

        try {
            User user = UserLocalServiceUtil.getUser(userId);
            List<NewsAttachedFile> newsAttachedFiles = newsAttachedFilePersistence.findBynewsId(newsId);
            if (newsAttachedFiles != null && !newsAttachedFiles.isEmpty()) {

                // First find a group to which belongs the user
                long groupId = 0;
                for (NewsAttachedFile newsAttachedFile : newsAttachedFiles) {
                    Group group = GroupLocalServiceUtil.getGroup(newsAttachedFile.getGroupId());
                    if ((group.isOrganization() &&
                            (OrganizationLocalServiceUtil.hasUserOrganization(user.getUserId(), group.getClassPK())
                            || RoleUtilsLocalServiceUtil.isDirectionMember(user)
                            || RoleUtilsLocalServiceUtil.isDoyen(user, group.getClassPK())
                            || RoleUtilsLocalServiceUtil.isPsychologue(user, group.getClassPK())
                            || RoleUtilsLocalServiceUtil.isConseillerSocial(user, group.getClassPK())))
                        || (group.isRegularSite() &&
                            (GroupLocalServiceUtil.hasUserGroup(user.getUserId(), group.getGroupId())
                                || RoleUtilsLocalServiceUtil.isDirectionMember(user)))) {
                        groupId = newsAttachedFile.getGroupId();
                        break;
                    }
                }
                // Then pick all files for this groupId, to build accessible urls
                if (groupId == 0) {
                    logger.error("Error converting attached file for news " + newsId + " and user " + userId);
                } else {
                    List<NewsAttachedFile> userAttachedFiles = newsAttachedFilePersistence.findBynewsId_groupId(newsId, groupId);
                    for (NewsAttachedFile userAttachedFile : userAttachedFiles) {
                        jsonFiles.put(convertNewsFile(userAttachedFile));
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error while converting files for news " + newsId, e);
        }

        return jsonFiles;
    }

    private JSONObject convertNewsFile(NewsAttachedFile newsAttachedFile) {

        JSONObject jsonAttachment = new JSONObject();
        try {
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(newsAttachedFile.getFileId());
            jsonAttachment.put(JSONConstants.ID, newsAttachedFile.getFileId());
            jsonAttachment.put(JSONConstants.NAME, fileEntry.getTitle());
            jsonAttachment.put(JSONConstants.TYPE, "File");
            jsonAttachment.put(JSONConstants.EXTENSION, fileEntry.getExtension().toLowerCase());
            jsonAttachment.put(JSONConstants.URL, FileUtilsLocalServiceUtil.getDownloadUrl(fileEntry));
        } catch (Exception e) {
            logger.error("Error converting attached file ", e);
        }
        return jsonAttachment;
    }

    public boolean hasAttachedFiles (long newsId) {
        try {
            return !newsAttachedFilePersistence.findBynewsId(newsId).isEmpty();
        } catch (Exception e) {
            logger.error("Error while fetching attached files for news " + newsId, e);
            return false;
        }
    }

    public void deleteAttachedFile(NewsAttachedFile newsAttachedFile) {
        logger.info("Deleting newsAttachFileId " + newsAttachedFile.getNewsFileId() + " for newsId " + newsAttachedFile.getNewsId());

        try {
            // Delete fileEntry
            long folderId = DLAppServiceUtil.getFileEntry(newsAttachedFile.getFileId()).getFolderId();
            DLAppServiceUtil.deleteFileEntry(newsAttachedFile.getFileId());

            // Check if parent folder is empty, if it is then delete folder
            List<FileEntry> remainingFileEntries = DLAppServiceUtil.getFileEntries(newsAttachedFile.getGroupId(), folderId);
            if (remainingFileEntries.isEmpty()) {
                DLAppServiceUtil.deleteFolder(folderId);
            }

            // Delete newsAttachedFile
            newsAttachedFilePersistence.remove(newsAttachedFile);
        } catch (PortalException e) {
            logger.error("Could not remove attachedFileId " + newsAttachedFile.getNewsFileId(), e);
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
                Folder folder = DLAppServiceUtil.getFolder(groupId, groupNewsFolder.getFolderId(), String.valueOf(newsId));
                if (folder != null) {
                    logger.info("About to delete folder news for groupId " + groupId + " and newsId " + newsId);
                    // Delete existing attached files
                    List<FileEntry> fileList = DLAppServiceUtil.getFileEntries(groupId, folder.getFolderId());
                    for (FileEntry fileEntry : fileList) {
                        logger.info("Deleting existing attached file " + fileEntry.getTitle());
                        DLAppLocalServiceUtil.deleteFileEntry(fileEntry.getFileEntryId());
                    }
                    logger.info("Deleting folder " + folder.getFolderId());
                    DLAppServiceUtil.deleteFolder(folder.getFolderId());

                }

            } catch (Exception e) {
                logger.error("Error adding attached files for newsId " + newsId + " and groupId " + groupId, e);
            }
        }

        newsAttachedFilePersistence.removeBynewsId(newsId);
    }
}
