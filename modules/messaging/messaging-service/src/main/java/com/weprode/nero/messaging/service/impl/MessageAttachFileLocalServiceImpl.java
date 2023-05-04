package com.weprode.nero.messaging.service.impl;

import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.document.constants.DocumentConstants;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.messaging.model.Message;
import com.weprode.nero.messaging.model.MessageAttachFile;
import com.weprode.nero.messaging.service.MessageFolderLocalServiceUtil;
import com.weprode.nero.messaging.service.MessageLocalServiceUtil;
import com.weprode.nero.messaging.service.base.MessageAttachFileLocalServiceBaseImpl;
import com.weprode.nero.messaging.service.persistence.MessageAttachFilePK;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        property = "model.class.name=com.weprode.nero.messaging.model.MessageAttachFile",
        service = AopService.class
)
public class MessageAttachFileLocalServiceImpl extends MessageAttachFileLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(MessageAttachFileLocalServiceImpl.class);

    public MessageAttachFile addAttachFile(long messageId, long fileId) {
        try {
            MessageAttachFilePK messageAttachFilePK = new MessageAttachFilePK(messageId, fileId);
            MessageAttachFile attachFile = this.createMessageAttachFile(messageAttachFilePK);
            attachFile = messageAttachFilePersistence.update(attachFile);

            return attachFile;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Long> getMessageAttachFileIds(long messageId) {
        List<Long> attachFileIds = new ArrayList<>();

        try {
            List<MessageAttachFile> attachFiles = messageAttachFilePersistence.findBymessageId(messageId);
            if (attachFiles != null) {
                for (MessageAttachFile attachFile : attachFiles) {
                    attachFileIds.add(attachFile.getFileId());
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching attach files for messageId " + messageId, e);
        }

        return attachFileIds;
    }

    public int countAttachedFiles(long messageId) {
        try {
            return messageAttachFilePersistence.countBymessageId(messageId);
        } catch (Exception e) {
            logger.error("Error counting attached files for message " + messageId);
        }

        return 0;
    }

    public void deleteAttachFiles(long messageId) throws SystemException, PortalException {
        // Get user
        Message message = MessageLocalServiceUtil.getMessage(messageId);

        long userId = MessageFolderLocalServiceUtil.getMessageFolder(message.getFolderId()).getUserId();
        User user = UserLocalServiceUtil.getUser(userId);
        Folder messagingFolder = FolderUtilsLocalServiceUtil.getIMBox(userId);

        Folder messageFolder = null;
        try {
            messageFolder = DLAppServiceUtil.getFolder(user.getGroupId(), messagingFolder.getFolderId(), "" + messageId);
        } catch (NoSuchFolderException nsfe) {
            // no existing attach file
        }

        // Security check (only delete folders that belongs to a Sending box folder)
        if (messageFolder != null && messageFolder.getParentFolder().getName().equals(DocumentConstants.IM_BOX_FOLDER_NAME)) {
            FolderUtilsLocalServiceUtil.deleteFolder(messageFolder.getUserId(), messageFolder.getFolderId());
        }

        messageAttachFilePersistence.removeBymessageId(messageId);
    }
}
