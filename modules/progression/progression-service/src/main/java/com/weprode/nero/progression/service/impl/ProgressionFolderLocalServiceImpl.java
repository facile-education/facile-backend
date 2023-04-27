package com.weprode.nero.progression.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.progression.constants.ProgressionConstants;
import com.weprode.nero.progression.exception.NoSuchFolderException;
import com.weprode.nero.progression.model.ProgressionFolder;
import com.weprode.nero.progression.model.ProgressionItem;
import com.weprode.nero.progression.service.ProgressionFolderLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionItemLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionLocalServiceUtil;
import com.weprode.nero.progression.service.base.ProgressionFolderLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.progression.model.ProgressionFolder",
        service = AopService.class
)
public class ProgressionFolderLocalServiceImpl extends ProgressionFolderLocalServiceBaseImpl {

    private final Log logger = LogFactoryUtil.getLog(ProgressionFolderLocalServiceImpl.class);

    public ProgressionFolder addFolder(long progressionId, long parentFolderId) throws SystemException {
        long progressionFolderId = counterLocalService.increment();

        ProgressionFolder folder = progressionFolderPersistence.create(progressionFolderId);

        folder.setProgressionId(progressionId);
        folder.setParentFolderId(parentFolderId);
        if (parentFolderId == 0) {
            folder.setFolderName(ProgressionConstants.DEFAULT_SECTION_NAME);
        } else {
            folder.setFolderName(ProgressionConstants.DEFAULT_SUBSECTION_NAME);
        }

        // Set order as the highest order + 1
        int nbFolders = progressionFolderPersistence.countByparentFolderId_progressionId(parentFolderId, progressionId);
        folder.setOrder(nbFolders + 1);

        folder = ProgressionFolderLocalServiceUtil.updateProgressionFolder(folder);

        // Update Progression modifiedDate
        ProgressionLocalServiceUtil.updateProgressionModifiedDate(progressionId);

        return folder;
    }

    public ProgressionFolder updateFolder(long progressionFolderId, long parentFolderId, String name, int order) throws SystemException {
        ProgressionFolder folder = progressionFolderPersistence.fetchByPrimaryKey(progressionFolderId);

        folder.setFolderName(name);

        boolean hasFolderChanged = (parentFolderId != folder.getParentFolderId());

        if (order != folder.getOrder() || hasFolderChanged) {
            List<ProgressionFolder> folders;

            if (parentFolderId == 0) {
                folders = getProgressionRootFolders(folder.getProgressionId());
            } else {
                folders = getSubFolders(parentFolderId);
            }

            // Update current or new folder item positions
            for (ProgressionFolder subFolder : folders) {
                if (subFolder.getProgressionFolderId() == progressionFolderId) continue;

                if (hasFolderChanged && subFolder.getOrder() >= order) {
                    // Folder has been move to another folder -> so move down all the following folders
                    subFolder.setOrder(subFolder.getOrder() + 1);
                    ProgressionFolderLocalServiceUtil.updateProgressionFolder(subFolder);
                } else if (!hasFolderChanged && subFolder.getOrder() >= order && subFolder.getOrder() < folder.getOrder()) {
                    // Folder has been move up in the same folder -> so move down the following folders until old position
                    subFolder.setOrder(subFolder.getOrder() + 1);
                    ProgressionFolderLocalServiceUtil.updateProgressionFolder(subFolder);
                } else if (!hasFolderChanged && subFolder.getOrder() <= order && subFolder.getOrder() > folder.getOrder()) {
                    // Folder has been move down in the same folder -> so up down the previous folders until old position
                    subFolder.setOrder(subFolder.getOrder() - 1);
                    ProgressionFolderLocalServiceUtil.updateProgressionFolder(subFolder);
                }
            }

            // Update previous folders siblings position
            if (hasFolderChanged) {
                List<ProgressionFolder> previousSubFolders = getSubFolders(folder.getParentFolderId());

                for (ProgressionFolder subFolder : previousSubFolders) {
                    if (subFolder.getOrder() >= folder.getOrder()) {
                        // Folder has been move up in the same folder -> so move down the following folders until old position
                        subFolder.setOrder(subFolder.getOrder() - 1);
                        ProgressionFolderLocalServiceUtil.updateProgressionFolder(subFolder);
                    }
                }
            }
        }

        folder.setParentFolderId(parentFolderId);
        folder.setOrder(order);

        folder = ProgressionFolderLocalServiceUtil.updateProgressionFolder(folder);

        // Update Progression modifiedDate
        ProgressionLocalServiceUtil.updateProgressionModifiedDate(folder.getProgressionId());

        return folder;
    }

    public void deleteFolder(long userId, long progressionFolderId) throws NoSuchFolderException, SystemException {
        for (ProgressionFolder folder: getSubFolders(progressionFolderId) ) {
            deleteFolder(userId, folder.getProgressionFolderId());
        }

        for (ProgressionItem item: ProgressionItemLocalServiceUtil.getFolderItems(progressionFolderId)) {
            try {
                ProgressionItemLocalServiceUtil.deleteItem(userId, item.getProgressionItemId());
            } catch (Exception e) {
                logger.error("Could not remove itemId="+item.getProgressionItemId()+" from folderId="+progressionFolderId, e);
            }
        }

        long progressionId = progressionFolderPersistence.fetchByprogressionFolderId(progressionFolderId).getProgressionId();

        progressionFolderPersistence.remove(progressionFolderId);

        // Update Progression modifiedDate
        ProgressionLocalServiceUtil.updateProgressionModifiedDate(progressionId);
    }

    public List<ProgressionFolder> getProgressionRootFolders(long progressionId) throws SystemException {
        return progressionFolderPersistence.findByparentFolderId_progressionId(0, progressionId);
    }

    public List<ProgressionFolder> getSubFolders(long parentFolderId) throws SystemException {
        return progressionFolderPersistence.findByparentFolderId(parentFolderId);
    }
}
