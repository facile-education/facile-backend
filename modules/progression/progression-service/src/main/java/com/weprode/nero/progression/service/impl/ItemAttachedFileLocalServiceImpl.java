package com.weprode.nero.progression.service.impl;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.progression.model.ItemAttachedFile;
import com.weprode.nero.progression.service.ProgressionItemLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionLocalServiceUtil;
import com.weprode.nero.progression.service.base.ItemAttachedFileLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.progression.model.ItemAttachedFile",
        service = AopService.class
)
public class ItemAttachedFileLocalServiceImpl extends ItemAttachedFileLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ItemAttachedFileLocalServiceImpl.class);

    public ItemAttachedFile addAttachedFile(long itemId, File file,
                                            boolean isToBeCompleted, boolean isAudioRecording) throws SystemException {
        //TODO Add fileEntry in correct folder ?
        long fileEntryId = 0;

        long itemAttachedFileId = counterLocalService.increment();
        ItemAttachedFile attachedFile = itemAttachedFilePersistence.create(itemAttachedFileId);

        attachedFile.setFileEntryId(fileEntryId);
        attachedFile.setProgressionItemId(itemId);
        attachedFile.setIsToBeCompleted(isToBeCompleted);
        attachedFile.setIsAudioRecording(isAudioRecording);

        attachedFile = itemAttachedFilePersistence.update(attachedFile);

        try {
            // Update Progression modifiedDate
            long progressionId = ProgressionItemLocalServiceUtil.getProgressionItem(itemId).getProgressionId();
            ProgressionLocalServiceUtil.updateProgressionModifiedDate(progressionId);
        } catch (PortalException e) {
            logger.error("Could not fetch progression id for itemId="+itemId, e);
        }

        return attachedFile;
    }

    public void deleteAttachedFile(long itemAttachedFileId) throws PortalException, SystemException {
        // Remove file
        long fileEntryId = itemAttachedFilePersistence.fetchByitemAttachedFileId(itemAttachedFileId).getFileEntryId();
        DLAppServiceUtil.deleteFileEntry(fileEntryId);

        // Remove attachment
        itemAttachedFilePersistence.remove(itemAttachedFileId);
    }

    public List<ItemAttachedFile> getItemAttachedFiles(long itemId) throws SystemException {
        return itemAttachedFilePersistence.findByprogressionItemId(itemId);
    }
}
