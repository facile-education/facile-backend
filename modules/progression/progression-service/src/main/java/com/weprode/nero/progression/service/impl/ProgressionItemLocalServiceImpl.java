package com.weprode.nero.progression.service.impl;

import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.progression.constants.ProgressionConstants;
import com.weprode.nero.progression.exception.NoSuchItemException;
import com.weprode.nero.progression.model.*;
import com.weprode.nero.progression.service.*;
import com.weprode.nero.progression.service.base.ProgressionItemLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.*;

@Component(
        property = "model.class.name=com.weprode.nero.progression.model.ProgressionItem",
        service = AopService.class
)
public class ProgressionItemLocalServiceImpl extends ProgressionItemLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ItemContentLocalServiceImpl.class);

    @Indexable(type = IndexableType.REINDEX)
    public ProgressionItem addItem(long progressionId, long userId, long folderId, boolean isHomework) throws SystemException {
        long progressionItemId = counterLocalService.increment();

        ProgressionItem item = progressionItemPersistence.create(progressionItemId);
        item.setProgressionId(progressionId);
        item.setProgressionFolderId(folderId);
        item.setModifiedDate(new Date());
        item.setIsHomework(isHomework);
        item.setType(ProgressionConstants.HOMEWORK_TYPE_SIMPLE);
        item.setDuration(StringPool.BLANK);

        // Order is the latest order + 1
        int nbItems = progressionItemPersistence.countByprogressionFolderId_progressionId(folderId, progressionId);
        item.setOrder(nbItems + 1);

        // Item name is based on the number of previous items of the same type
        int count = 1;
        List<ProgressionItem> existingItems = progressionItemPersistence.findByprogressionFolderId_progressionId(folderId, progressionId);
        if (existingItems != null) {
            for (ProgressionItem existingItem : existingItems) {
                if (existingItem.getIsHomework() == isHomework) {
                    count++;
                }
            }
        }

        if (isHomework) {
            item.setItemName(ProgressionConstants.DEFAULT_HOMEWORK_NAME + " " + count);
        } else {
            item.setItemName(ProgressionConstants.DEFAULT_SESSION_NAME + " " + count);
        }

        item = progressionItemPersistence.update(item);

        try {
            // Create folder to avoid error when adding multiple files
            ProgressionItemLocalServiceUtil.getOrCreateDLFolder(progressionItemId, userId);
        } catch (Exception e) {
            logger.error("Could not create folder when initializing progressionItemId " + progressionItemId, e);
        }

        // Update Progression modifiedDate
        if (progressionId != 0) {
            ProgressionLocalServiceUtil.updateProgressionModifiedDate(progressionId);
        }

        return item;
    }

    @Indexable(type = IndexableType.REINDEX)
    public ProgressionItem updateItem (long progressionItemId, long folderId, String name, int type, String duration, int order) throws SystemException, NoSuchItemException {
        ProgressionItem item = progressionItemPersistence.findByPrimaryKey(progressionItemId);

        item.setModifiedDate(new Date());
        item.setItemName(name);
        item.setType(type);
        item.setDuration(duration);
        boolean hasFolderChanged = (folderId != item.getProgressionFolderId());

        if (order != item.getOrder() || hasFolderChanged) {
            List<ProgressionItem> items = getFolderItems(folderId);

            // Update current or new folder item positions
            for (ProgressionItem folderItem : items) {
                if (folderItem.getProgressionItemId() == progressionItemId) continue;

                if (hasFolderChanged && folderItem.getOrder() >= order) {
                    // Item has been move to another folder -> so move down all the following items
                    folderItem.setOrder(folderItem.getOrder() + 1);
                    progressionItemPersistence.update(folderItem);
                } else if (!hasFolderChanged && folderItem.getOrder() >= order && folderItem.getOrder() < item.getOrder()) {
                    // Item has been move up in the same folder -> so move down the following items until old position
                    folderItem.setOrder(folderItem.getOrder() + 1);
                    progressionItemPersistence.update(folderItem);
                } else if (!hasFolderChanged && folderItem.getOrder() <= order && folderItem.getOrder() > item.getOrder()) {
                    // Item has been move down in the same folder -> so up down the previous items until old position
                    folderItem.setOrder(folderItem.getOrder() - 1);
                    progressionItemPersistence.update(folderItem);
                }
            }

            // Update previous folder items position
            if (hasFolderChanged) {
                List<ProgressionItem> previousFolderitems = getFolderItems(item.getProgressionFolderId());

                for (ProgressionItem folderItem : previousFolderitems) {
                    if (folderItem.getOrder() >= item.getOrder()) {
                        // Item has been move up in the same folder -> so move down the following items until old position
                        folderItem.setOrder(folderItem.getOrder() - 1);
                        progressionItemPersistence.update(folderItem);
                    }
                }
            }
        }

        item.setOrder(order);
        item.setProgressionFolderId(folderId);

        item = progressionItemPersistence.update(item);

        // Update Progression modifiedDate
        ProgressionLocalServiceUtil.updateProgressionModifiedDate(item.getProgressionId());

        return item;
    }

    @Indexable(type = IndexableType.DELETE)
    public void deleteItem (long userId, long progressionItemId) throws PortalException, SystemException {
        List<ItemAssignment> itemAssignments = ItemAssignmentLocalServiceUtil.getItemAssignments(progressionItemId);

        for (ItemAssignment assignment : itemAssignments) {
            ItemAssignmentLocalServiceUtil.deleteItemAssigment(assignment);
        }

        List<ItemAttachedFile> itemFiles = ItemAttachedFileLocalServiceUtil.getItemAttachedFiles(progressionItemId);
        for (ItemAttachedFile file : itemFiles) {
            ItemAttachedFileLocalServiceUtil.deleteAttachedFile(file.getItemAttachedFileId());
        }

        // Delete Progression folder corresponding to this item
        Folder progressionFolder = FolderUtilsLocalServiceUtil.getProgressionFolder(userId);
        try {
            Folder progressionItemFolder = FolderUtilsLocalServiceUtil.getFolderByName(progressionFolder, String.valueOf(progressionItemId));
            DLAppServiceUtil.deleteFolder(progressionItemFolder.getFolderId());
        } catch (PortalException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.info("Nothing to delete, " + e.getMessage());
        }

        ProgressionItem progressionItem = progressionItemPersistence.fetchByprogressionItemId(progressionItemId);
        long progressionId = progressionItem.getProgressionId();

        // Decrement the orders of the following items of the parent folder
        int removedOrder = progressionItem.getOrder();
        List<ProgressionItem> items = progressionItemPersistence.findByprogressionFolderId(progressionItem.getProgressionFolderId());
        for (ProgressionItem item : items) {
            if (item.getProgressionItemId() != progressionItemId && item.getOrder() > removedOrder) {
                item.setOrder(item.getOrder() - 1);
                progressionItemPersistence.update(progressionItem);
            }
        }

        // Decrement the orders of the following folders of the parent folder
        List<ProgressionFolder> folders = progressionFolderPersistence.findByparentFolderId(progressionItem.getProgressionFolderId());
        for (ProgressionFolder folder : folders) {
            if (folder.getOrder() > removedOrder) {
                folder.setOrder(folder.getOrder() - 1);
                progressionFolderPersistence.update(folder);
            }
        }

        progressionItemPersistence.remove(progressionItemId);

        // Update Progression modifiedDate
        ProgressionLocalServiceUtil.updateProgressionModifiedDate(progressionId);

        // Delete the associated item contents
        ItemContentLocalServiceUtil.deleteContentsByItemId(progressionItemId);
    }

    public List<ProgressionItem> getFolderItems (long folderId) throws SystemException {
        return progressionItemPersistence.findByprogressionFolderId(folderId);
    }

    public ProgressionItem getSpecificSessionItem(long sessionId) {
        try {
            return progressionItemPersistence.findBysessionId(sessionId);
        } catch (Exception e) {
            logger.debug(e);
        }

        return null;
    }

    public ProgressionItem getSpecificHomeworkItem(long homeworkId) {
        try {
            return progressionItemPersistence.findByhomeworkId(homeworkId);
        } catch (Exception e) {
            logger.debug(e);
        }

        return null;
    }

    public ProgressionItem cloneItemForSpecificSession (long userId, long sourceItemId, long sessionId) {
        try {
            ProgressionItem sourceItem = ProgressionItemLocalServiceUtil.getProgressionItem(sourceItemId);

            ProgressionItem sessionItem = ProgressionItemLocalServiceUtil.addItem(0, userId, 0, false);
            sessionItem.setSessionId(sessionId);
            sessionItem.setItemName(sourceItem.getItemName());
            sessionItem.setType(sourceItem.getType());
            sessionItem = ProgressionItemLocalServiceUtil.updateProgressionItem(sessionItem);

            cloneContents(sourceItemId, sessionItem.getProgressionItemId());

            return sessionItem;
        } catch (Exception e) {
            logger.error("Error cloning item for specific session " + sessionId, e);
        }

        return null;
    }

    public ProgressionItem cloneItemForSpecificHomework (long userId, long sourceItemId, long homeworkId) {
        try {
            ProgressionItem sourceItem = ProgressionItemLocalServiceUtil.getProgressionItem(sourceItemId);

            ProgressionItem sessionItem = ProgressionItemLocalServiceUtil.addItem(0, userId, 0, false);
            sessionItem.setHomeworkId(homeworkId);
            sessionItem.setDuration(sourceItem.getDuration());
            sessionItem.setItemName(sourceItem.getItemName());
            sessionItem.setType(sourceItem.getType());
            sessionItem = ProgressionItemLocalServiceUtil.updateProgressionItem(sessionItem);

            cloneContents(sourceItemId, sessionItem.getProgressionItemId());

            return sessionItem;
        } catch (Exception e) {
            logger.error("Error cloning item for specific homework " + homeworkId, e);
        }

        return null;
    }

    private void cloneContents(long sourceItemId, long targetItemId) {
        try {
            ProgressionItem sourceItem = ProgressionItemLocalServiceUtil.getProgressionItem(sourceItemId);
            List<ItemContent> sourceContents = ItemContentLocalServiceUtil.getContentsByItemId(sourceItemId);
            Progression sourceProgression = ProgressionLocalServiceUtil.getProgression(sourceItem.getProgressionId());

            for (ItemContent sourceContent : sourceContents) {

                try {
                    ItemContentLocalServiceUtil.addContent(
                            targetItemId,
                            sourceContent.getContentType(),
                            sourceContent.getContentName(),
                            sourceContent.getContentValue(),
                            sourceContent.getFileEntryId(),
                            sourceContent.getIsToBeCompleted(),
                            sourceProgression.getTeacherId());

                    logger.info("Cloned content of type " + sourceContent.getContentType()+ " and name " + sourceContent.getContentName() +" and value " + sourceContent.getContentValue());
                } catch (Exception e) {
                    logger.error("Error saving specific contents", e);
                }
            }
        } catch (Exception e) {
            logger.error("Error saving specific contents", e);
        }
    }

    public String convertContentAsHtml (long itemId) {
        StringBuilder itemHtml = new StringBuilder();

        // We use a copy to be able to sort it
        List<ItemContent> contents = new ArrayList<>(ItemContentLocalServiceUtil.getContentsByItemId(itemId));

        // Order contents
        contents.sort(Comparator.comparingInt(ItemContentModel::getOrder));

        boolean isH5pScriptImported = false;
        for (ItemContent content : contents) {
            if (content.getContentType() == ProgressionConstants.TYPE_H5P && !isH5pScriptImported) {
                itemHtml.insert(0, "<script src=\"https://h5p.eduge.ch/modules/contrib/h5p/vendor/h5p/h5p-core/js/h5p-resizer.js\" charset=\"UTF-8\"></script>");
                isH5pScriptImported = true;
            }
            itemHtml.append(ItemContentLocalServiceUtil.convertContentToHtml(content.getContentId())).append("<br/><br/>");
        }

        return itemHtml.toString();
    }

    // TODO rename to indicate we are progression specific
    // Get of create DLFolder to store item related files (audio and attachments)
    public Folder getOrCreateDLFolder(long itemId, long userId) throws PortalException, SystemException {
        Folder progressionFolder = FolderUtilsLocalServiceUtil.getProgressionFolder(userId);

        Folder progressionItemFolder = null;
        try {
            progressionItemFolder = FolderUtilsLocalServiceUtil.getFolderByName(progressionFolder, String.valueOf(itemId));
        } catch (NoSuchFolderException e) {
            logger.info("Couldn't find folder for itemId = " + itemId + ". Creating it.");
            progressionItemFolder = DLAppServiceUtil.addFolder(
                    progressionFolder.getGroupId(),
                    progressionFolder.getFolderId(),
                    String.valueOf(itemId),
                    "",
                    new ServiceContext()
            );
        } catch (Exception e) {
            logger.error("Error when fetching itemId folder", e);
        }

        return progressionItemFolder;
    }
}
