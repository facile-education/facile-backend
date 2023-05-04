package com.weprode.nero.messaging.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.messaging.constants.MessagingConstants;
import com.weprode.nero.messaging.model.MessageFolder;
import com.weprode.nero.messaging.service.MessageFolderLocalServiceUtil;
import com.weprode.nero.messaging.service.base.MessageFolderLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component(
        property = "model.class.name=com.weprode.nero.messaging.model.MessageFolder",
        service = AopService.class
)
public class MessageFolderLocalServiceImpl extends MessageFolderLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(MessageFolderLocalServiceImpl.class);

    public MessageFolder addFolderMessage(long userId, String folderName, int type, long parentFolderId) {
        try {
            MessageFolder folder = this.createMessageFolder(counterLocalService.increment());
            folder.setUserId(userId);
            folder.setFolderName(folderName);
            folder.setType(type);
            folder.setParentFolderId(parentFolderId);

            return MessageFolderLocalServiceUtil.updateMessageFolder(folder);
        } catch (Exception e) {
            return null;
        }
    }

    public MessageFolder getUserInboxFolder(long userId) {
        try {
            List<MessageFolder> inboxFolders = messageFolderPersistence.findByuserId_type(userId, MessagingConstants.INBOX_FOLDER_TYPE);

            if (inboxFolders == null || inboxFolders.isEmpty()) {
                // Create the inbox
                logger.info("Create messaging inbox for user " + userId);
                User user = UserLocalServiceUtil.getUser(userId);
                ResourceBundle bundle = ResourceBundle.getBundle("content.Language", user.getLocale());

                return MessageFolderLocalServiceUtil.addFolderMessage(userId, bundle.getString("folder-inbox"), MessagingConstants.INBOX_FOLDER_TYPE, 0);
            } else {
                return inboxFolders.get(0);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public MessageFolder getUserSendingBoxFolder(long userId) {
        try {
            List<MessageFolder> sendingBoxFolders = messageFolderPersistence.findByuserId_type(userId, MessagingConstants.SENT_FOLDER_TYPE);

            if (sendingBoxFolders == null || sendingBoxFolders.isEmpty()) {
                // Create the sending box
                User user = UserLocalServiceUtil.getUser(userId);
                ResourceBundle bundle = ResourceBundle.getBundle("content.Language", user.getLocale());

                return MessageFolderLocalServiceUtil.addFolderMessage(userId, bundle.getString("folder-sent"), MessagingConstants.SENT_FOLDER_TYPE, 0);
            } else {
                return sendingBoxFolders.get(0);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public MessageFolder getUserTrashFolder(long userId) {

        try {
            List<MessageFolder> trashFolders = messageFolderPersistence.findByuserId_type(userId, MessagingConstants.TRASH_FOLDER_TYPE);

            if (trashFolders == null || trashFolders.isEmpty()) {
                // Create the trash
                User user = UserLocalServiceUtil.getUser(userId);
                ResourceBundle bundle = ResourceBundle.getBundle("content.Language", user.getLocale());

                return MessageFolderLocalServiceUtil.addFolderMessage(userId, bundle.getString("folder-trash"), MessagingConstants.TRASH_FOLDER_TYPE, 0);
            } else {
                return trashFolders.get(0);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public MessageFolder getUserDraftFolder(long userId) {

        try {
            List<MessageFolder> draftFolders = messageFolderPersistence.findByuserId_type(userId, MessagingConstants.DRAFT_FOLDER_TYPE);

            if (draftFolders == null || draftFolders.isEmpty()) {
                // Create the draft
                User user = UserLocalServiceUtil.getUser(userId);
                ResourceBundle bundle = ResourceBundle.getBundle("content.Language", user.getLocale());

                return MessageFolderLocalServiceUtil.addFolderMessage(userId, bundle.getString("folder-draft"), MessagingConstants.DRAFT_FOLDER_TYPE, 0);
            } else {
                return draftFolders.get(0);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<MessageFolder> getAllUserFolders(long userId) {
        try {
            return messageFolderPersistence.findByuserId(userId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<MessageFolder> getUserSubFolders(long userId, long parentFolderId) {
        try {
            // Create root folders if needed
            if (parentFolderId == 0) {
                getUserInboxFolder(userId);
                getUserSendingBoxFolder(userId);
                getUserTrashFolder(userId);
                getUserDraftFolder(userId);
            }

            return messageFolderPersistence.findByuserId_parentFolderId(userId, parentFolderId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void deletePersonalFolder(MessageFolder personalFolder, long userId) {
        try {
            List<MessageFolder> subFolderList = messageFolderPersistence.findByuserId_parentFolderId(userId, personalFolder.getFolderId());

            for (MessageFolder subFolder : subFolderList) {
                deletePersonalFolder(subFolder, userId);
            }

            MessageFolderLocalServiceUtil.deleteMessageFolder(personalFolder);
        } catch (Exception e) {
            logger.error("Error deleting personal folder " + personalFolder.getFolderId() + " for userId " + userId);
        }
    }

    public User getFolderUser (long folderId) {
        try {
            return UserLocalServiceUtil.getUser(MessageFolderLocalServiceUtil.getMessageFolder(folderId).getUserId());
        } catch (Exception e) {
            // If user does not exist anymore
            return null;
        }
    }
}
