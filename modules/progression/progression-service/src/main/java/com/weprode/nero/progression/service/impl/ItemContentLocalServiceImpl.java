package com.weprode.nero.progression.service.impl;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.weprode.nero.document.service.DocumentUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.progression.constants.ProgressionConstants;
import com.weprode.nero.progression.exception.UnauthorizedUrlException;
import com.weprode.nero.progression.model.ItemContent;
import com.weprode.nero.progression.service.ProgressionItemLocalServiceUtil;
import com.weprode.nero.progression.service.base.ItemContentLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.progression.model.ItemContent",
        service = AopService.class
)
public class ItemContentLocalServiceImpl extends ItemContentLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ItemContentLocalServiceImpl.class);

    // Add new empty content of given type
    // Order is the last position for the given itemId
    public ItemContent addContent(long itemId, int contentType, String contentName, String contentValue, long fileEntryId, boolean isToBeCompleted, long userId) throws UnauthorizedUrlException, SystemException, PortalException, IOException {
        ItemContent itemContent = itemContentPersistence.create(counterLocalService.increment());

        int nbContents = itemContentPersistence.countByprogressionItemId(itemId);
        itemContent.setProgressionItemId(itemId);
        itemContent.setOrder(nbContents + 1);
        itemContent.setContentType(contentType);
        itemContent.setModifiedDate(new Date());
        itemContent.setContentName(contentName);
        itemContent.setIsToBeCompleted(isToBeCompleted);
        if (fileEntryId != 0 && contentType != ProgressionConstants.TYPE_RECORD) {
            Folder progressionItemFolder= ProgressionItemLocalServiceUtil.getOrCreateDLFolder(itemId, userId);

            FileEntry fileToAdd = DLAppServiceUtil.getFileEntry(fileEntryId);
            Folder tempFolder = FolderUtilsLocalServiceUtil.getTmpFolder(userId);

            FileEntry contentFile;
            if (fileToAdd.getFolderId() == tempFolder.getFolderId()) {	// If file is from upload, move it from temp folder instead of create a copy
                contentFile = FileUtilsLocalServiceUtil.moveFileEntry(userId, fileEntryId, progressionItemFolder.getFolderId(), 0);
            } else {
                contentFile = FileUtilsLocalServiceUtil.copyFileEntry(userId, fileEntryId, progressionItemFolder.getFolderId(), true);
            }
            fileEntryId = contentFile.getFileEntryId();
        }
        itemContent.setFileEntryId(fileEntryId);

        // Content value is either the default one, or the provided one
        switch (contentType) {
            case ProgressionConstants.TYPE_TEXT:
                if (contentValue.equals("")) {
                    itemContent.setContentValue(ProgressionConstants.DEFAULT_TEXT_NAME);
                } else {
                    itemContent.setContentValue(contentValue);
                }
                break;
            case ProgressionConstants.TYPE_RECORD:
                itemContent.setContentValue(ProgressionConstants.DEFAULT_RECORD_NAME);
                break;
            case ProgressionConstants.TYPE_LINK:
                itemContent.setContentValue(contentValue);
                break;
            case ProgressionConstants.TYPE_VIDEO:
            case ProgressionConstants.TYPE_H5P:
                if (DocumentUtilsLocalServiceUtil.isEmbedUrlWhitelisted(contentValue)) {
                    itemContent.setContentValue(contentValue);
                } else {
                    throw new UnauthorizedUrlException("Url " + contentValue + " is not whiteListed for an embed content");
                }
                break;
            case ProgressionConstants.TYPE_FILE:
                itemContent.setContentValue(ProgressionConstants.DEFAULT_FILE_NAME);
                break;
            default:
                break;
        }

        return itemContentPersistence.update(itemContent);
    }

    public ItemContent updateContent (long contentId, String contentName, String contentValue, int order) throws SystemException, UnauthorizedUrlException {
        ItemContent itemContent = itemContentPersistence.fetchByPrimaryKey(contentId);

        // Name
        itemContent.setContentName(contentName);

        // Content Value (url check on embed contents)
        if (itemContent.getContentType() == ProgressionConstants.TYPE_VIDEO || itemContent.getContentType() == ProgressionConstants.TYPE_H5P) {
            if (DocumentUtilsLocalServiceUtil.isEmbedUrlWhitelisted(contentValue)) {
                itemContent.setContentValue(contentValue);
            } else {
                throw new UnauthorizedUrlException("Url " + contentValue + " is not whiteListed as an authorized embed content");
            }
        }
        itemContent.setContentValue(contentValue);

        // Order
        int oldOrder = itemContent.getOrder();
        itemContent.setOrder(order);
        itemContent.setModifiedDate(new Date());
        itemContent = itemContentPersistence.update(itemContent);

        // Update the other content's orders
        if (oldOrder != order) {
            List<ItemContent> itemContents = itemContentPersistence.findByprogressionItemId(itemContent.getProgressionItemId());
            if (itemContents != null) {
                for (ItemContent subContent : itemContents) {
                    // New position is below the old one
                    if (oldOrder < order && subContent.getOrder() > oldOrder && subContent.getOrder() <= order && subContent.getContentId() != contentId) {
                        subContent.setOrder(subContent.getOrder() - 1);
                        logger.info("Decremented order of content " + subContent.getContentName() + " of type " + subContent.getContentType());
                    }
                    // New position is after the old one
                    if (oldOrder > order && subContent.getOrder() >= order && subContent.getOrder() < oldOrder  && subContent.getContentId() != contentId) {
                        subContent.setOrder(subContent.getOrder() + 1);
                        logger.info("Incremented order of content " + subContent.getContentName() + " of type " + subContent.getContentType());
                    }
                    itemContentPersistence.update(subContent);
                }
            }
        }

        return itemContent;
    }

    public List<ItemContent> getContentsByItemId(long itemId) {
        List<ItemContent> itemContents = new ArrayList<>();

        try {
            itemContents = itemContentPersistence.findByprogressionItemId(itemId);
        } catch (Exception e) {
            logger.error("Error getting all contents for itemId " + itemId, e);
        }

        return itemContents;
    }

    public List<Long> getFileIds (long itemId) {
        List<Long> fileIds = new ArrayList<>();

        try {
            List<ItemContent> itemContents = itemContentPersistence.findByprogressionItemId(itemId);

            if (itemContents != null) {
                for (ItemContent itemContent : itemContents) {
                    if (itemContent.getContentType() == ProgressionConstants.TYPE_FILE && !itemContent.getIsToBeCompleted()) {
                        fileIds.add(itemContent.getFileEntryId());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error getting fileIds for itemId " + itemId, e);
        }

        return fileIds;
    }

    public List<Long> getEditableFileIds (long itemId) {
        List<Long> fileIds = new ArrayList<>();

        try {
            List<ItemContent> itemContents = itemContentPersistence.findByprogressionItemId(itemId);

            if (itemContents != null) {
                for (ItemContent itemContent : itemContents) {
                    if (itemContent.getContentType() == ProgressionConstants.TYPE_FILE && itemContent.getIsToBeCompleted()) {
                        fileIds.add(itemContent.getFileEntryId());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error getting fileIds for itemId " + itemId, e);
        }

        return fileIds;
    }

    public List<Long> getAudioFileIds (long itemId) {
        List<Long> audioFileIds = new ArrayList<>();

        try {
            List<ItemContent> itemContents = itemContentPersistence.findByprogressionItemId(itemId);
            if (itemContents != null) {
                for (ItemContent itemContent : itemContents) {
                    if (itemContent.getContentType() == ProgressionConstants.TYPE_RECORD) {
                        audioFileIds.add(itemContent.getFileEntryId());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error getting audioFileIds for itemId " + itemId, e);
        }

        return audioFileIds;
    }

    public boolean deleteContentsByItemId(long itemId) {
        try {
            itemContentPersistence.removeByprogressionItemId(itemId);
            // Assume here that item content's folder is already deleted
            return true;
        } catch (Exception e) {
            logger.error("Error getting all contents for itemId " + itemId, e);
        }

        return false;
    }

    public boolean deleteContent(long contentId) {
        try {
            ItemContent deletedContent = itemContentPersistence.fetchByPrimaryKey(contentId);

            if (deletedContent.getFileEntryId() != 0) {
                try {
                    DLAppServiceUtil.deleteFileEntry(deletedContent.getFileEntryId());
                } catch (Exception e) {
                    logger.error("Error when deleting fileEntry " + deletedContent.getFileEntryId());
                }
            }
            int deletedOrder = deletedContent.getOrder();

            itemContentPersistence.remove(contentId);
            logger.info("Deleted contentId " + contentId);

            // Decrement following orders
            List<ItemContent> itemContents = itemContentPersistence.findByprogressionItemId(deletedContent.getProgressionItemId());
            if (itemContents != null) {
                for (ItemContent itemContent : itemContents) {
                    if (itemContent.getOrder() > deletedOrder) {
                        itemContent.setOrder(itemContent.getOrder() - 1);
                        itemContentPersistence.update(itemContent);
                        logger.info("Decremented order of following content " + itemContent.getContentId());
                    }
                }
            }

            return true;

        } catch (Exception e) {
            logger.error("Error when deleting contentId " + contentId, e);
            return false;
        }
    }

    public String convertContentToHtml (long contentId) {
        String htmlContent = "";

        try {
            ItemContent content = itemContentPersistence.fetchByPrimaryKey(contentId);

            switch (content.getContentType()) {
                case ProgressionConstants.TYPE_TEXT:
                    htmlContent = content.getContentValue();
                    break;
                case ProgressionConstants.TYPE_RECORD:
                    htmlContent = "<audio controls=\"controls\" autobuffer=\"autobuffer\">";
                    htmlContent += "<source src=\"/c/document_library/get_file?fileEntryId=" +  content.getFileEntryId() + "\" />";
                    htmlContent += "</audio>";
                    break;
                case ProgressionConstants.TYPE_LINK:
                    htmlContent = "<a href=\"" + content.getContentValue() + "\">" + content.getContentName() + "</a>";
                    break;
                case ProgressionConstants.TYPE_VIDEO:
                case ProgressionConstants.TYPE_H5P:
                    if (!content.getContentName().equals("")) {
                        htmlContent = content.getContentName() + "<br/>";
                    }
                    htmlContent = "<iframe src=\"" + content.getContentValue() + "\" style=\"border:none;width:100%;\" allow=\"fullscreen\"></iframe>";
                    break;
                case ProgressionConstants.TYPE_FILE:
                    htmlContent = "";
                    break;
                default:
                    htmlContent = "Erreur de récupération du contenu";
                    break;
            }
        } catch (Exception e) {
            logger.error("Error fetching content " + contentId + " when converting it to HTML", e);
        }

        return htmlContent;
    }
}
