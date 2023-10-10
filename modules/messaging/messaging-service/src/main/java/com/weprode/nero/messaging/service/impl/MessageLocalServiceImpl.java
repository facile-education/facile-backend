package com.weprode.nero.messaging.service.impl;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.messaging.constants.MessagingConstants;
import com.weprode.nero.messaging.exception.NoSuchMessageException;
import com.weprode.nero.messaging.model.Message;
import com.weprode.nero.messaging.model.MessageContent;
import com.weprode.nero.messaging.model.MessageFolder;
import com.weprode.nero.messaging.model.MessagingThread;
import com.weprode.nero.messaging.service.MessageAttachFileLocalServiceUtil;
import com.weprode.nero.messaging.service.MessageContentLocalServiceUtil;
import com.weprode.nero.messaging.service.MessageFolderLocalServiceUtil;
import com.weprode.nero.messaging.service.MessageLocalServiceUtil;
import com.weprode.nero.messaging.service.MessageRecipientsLocalServiceUtil;
import com.weprode.nero.messaging.service.MessagingConfigLocalServiceUtil;
import com.weprode.nero.messaging.service.base.MessageLocalServiceBaseImpl;
import com.weprode.nero.messaging.utils.MessagingUtil;
import com.weprode.nero.messaging.utils.ThreadSendMessage;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import javax.mail.internet.InternetAddress;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Component(
        property = "model.class.name=com.weprode.nero.messaging.model.Message",
        service = AopService.class
)
public class MessageLocalServiceImpl extends MessageLocalServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(MessageLocalServiceImpl.class);

    public List<MessagingThread> getThreads (long userId, long folderId, Date maxDate, int nbThreads, boolean unReadOnly) {
        List<MessagingThread> threadList = new ArrayList<>();
        List<Long> addedThreadIds = new ArrayList<>();

        // Fetch threads one by one
        while (threadList.size() < nbThreads) {
            MessagingThread thread = getMostRecentThread(addedThreadIds, folderId, maxDate, unReadOnly);

            if (thread == null) {	// No more threads available
                break;
            }

            addedThreadIds.add(thread.getThreadId());
            threadList.add(thread);

            // Main message is the first message displayed
            Message threadMainMessage = MessagingUtil.getLastMessageFromList(thread.getDisplayableMessages(unReadOnly, folderId));
            if (maxDate.toInstant().isAfter(threadMainMessage.getSendDate().toInstant())) {
                maxDate = threadMainMessage.getSendDate();	// Get next thread after the date of the main-message thread
            }
        }

        return threadList;
    }

    public MessagingThread getMostRecentThread (List<Long> addedThreadIds, long folderId, Date fromDate, boolean unReadOnly) {
        // Get messages from newest to oldest by pack of 60, and compute if the wanted message is inside
        int start = 0;
        int nbMessagesToFetchAtTime = 60;
        OrderByComparator<Message> odc = OrderByComparatorFactoryUtil.create("Messaging_Message", "sendDate", false);

        List<Message> messagePool = new ArrayList<>();
        Message lastMessage = null;

        while (lastMessage == null) {
            List<Message> folderMessages = MessageLocalServiceUtil.getFolderMessages(	// Quick on recall because of cache
                    folderId,
                    start,
                    start + nbMessagesToFetchAtTime,
                    odc
            );
            if (folderMessages.isEmpty()) {
                // No more messages the search the chosen from, return null
                return null;
            }
            messagePool.addAll(folderMessages);

            // Loop over messages
            for (Message message : messagePool) {
                // Must be before the given Date and be New if the unreadOnly boolean is set to true
                if (!addedThreadIds.contains(message.getThreadId()) && message.getSendDate().before(fromDate) && (!unReadOnly || message.getIsNew())) {
                    lastMessage = message;
                    break;
                }
            }
            start = start + nbMessagesToFetchAtTime;
        }

        try {
            return MessageLocalServiceUtil.getMessagingThread(lastMessage.getThreadId());
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public MessagingThread getMessagingThread (long threadId) throws SystemException {
        List<Message> messageList = messagePersistence.findBythreadId(threadId);
        return new MessagingThread(threadId, messageList);
    }

    @Indexable(type = IndexableType.REINDEX)
    public Message addMessage(long folderId, long senderId, Date sendDate, long threadId, String messageSubject, String messageContent, boolean isNew, int type, long sendMessageId) {
        try {
            final long messageId = counterLocalService.increment();
            Message message = this.createMessage(messageId);
            message.setCompanyId(PortalUtil.getDefaultCompanyId());

            message.setFolderId(folderId);

            if (threadId == 0) {
                // Message creation
                threadId = counterLocalService.increment();
            }
            message.setThreadId(threadId);
            message.setSendMessageId(sendMessageId);
            message.setSenderId(senderId);
            message.setSendDate(sendDate);

            try {
                User sender = UserLocalServiceUtil.getUser(senderId);
                message.setSenderName(sender.getFullName());
            } catch (Exception e) {
                // For migration : user was deleted
                // To remove
                logger.debug(e);
            }

            String sanitizedContent = FileUtilsLocalServiceUtil.sanitizeHTMLContent(messageContent);
            String previewContent = HtmlParserUtil.extractText(sanitizedContent).trim();
            if (previewContent.length() > 250) {
                previewContent = previewContent.substring(0, 250);
            }
            message.setMessageContent(previewContent);
            message.setMessageSubject(messageSubject);

            message.setIsNew(isNew);
            message.setType(type);
            message.setIsAnswered(false);
            message.setIsForwarded(false);

            message = messagePersistence.update(message);

            // Create content
            MessageContentLocalServiceUtil.addContent(message.getMessageId(), sanitizedContent);

            // Indexer
            /* TODO Indexer indexer = IndexerRegistryUtil.getIndexer(Message.class);
            try {
                indexer.reindex(message);
            } catch (SearchException e) {
                logger.error("Error indexing new message", e);
            }*/
            return message;

        } catch (Exception e) {
            logger.error("Error creating message", e);
        }

        return null;
    }

    public boolean sendMessage(long senderId, List<Long> recipientList, String subject, String content) {
        return sendMessage(senderId, recipientList, subject, content, MessagingConstants.TYPE_OTHER, null, 0, 0);
    }

    public boolean sendMessage(long senderId, List<Long> recipientList, String subject, String content, int type) {
        return sendMessage(senderId, recipientList, subject, content, type, null, 0, 0);
    }

    public boolean sendMessage(long senderId, List<Long> recipientList, String subject, String content, int type, List<Long> attachFileIds, long draftMessageId, long originMessageId) {
        try {
            if (!recipientList.isEmpty()) {
                User sender = UserLocalServiceUtil.getUser(senderId);
                ThreadSendMessage sendMsgThd = new ThreadSendMessage(sender, recipientList, subject, content, type, attachFileIds, draftMessageId, originMessageId);
                sendMsgThd.start();
            }

            return true;
        } catch (Exception e) {
            logger.error("Error when sending message", e);
        }

        return false;
    }

    public boolean sendSupportMessage(User sender, List<Long> recipientList, String subject, String content, List<Long> attachFileIds, long draftMessageId, long originMessageId) {
        try {
            if (!recipientList.isEmpty()) {
                ThreadSendMessage sendMsgThd = new ThreadSendMessage(sender, recipientList, subject, content, MessagingConstants.TYPE_SUPPORT, attachFileIds, draftMessageId, originMessageId);
                sendMsgThd.start();
            }
            return true;
        } catch (Exception e) {
            logger.error("Error when sending support message", e);
        }

        return false;
    }

    public boolean sendAutoReply(long receiverId, User initialSender, long originMessageId) {
        try {
            if (MessagingConfigLocalServiceUtil.hasAutoReplyActive(receiverId)) {
                String autoReplyContent = MessagingConfigLocalServiceUtil.getAutoReply(receiverId);
                String autoReplySubject = "R\u00e9ponse automatique";

                List<Long> recipientList = new ArrayList<>();
                recipientList.add(initialSender.getUserId());

                sendMessage(receiverId, recipientList, autoReplySubject, autoReplyContent, MessagingConstants.TYPE_AUTO_REPLY, null, 0, originMessageId);

                return true;
            }
        } catch (Exception e) {
            logger.error("Error sending auto-response to initialSender " + initialSender.getUserId(), e);
        }

        return false;
    }

    public boolean setMessageAsRead(long messageId, boolean isRead) {
        try {
            Message message = MessageLocalServiceUtil.getMessage(messageId);
            message.setIsNew(!isRead);
            // Do not erase readDate if it has already been set, because we want to keep the first read date for the sender
            if (isRead && message.getReadDate() == null) {
                message.setReadDate(new Date());
            }
            messagePersistence.update(message);

            return true;
        } catch (Exception e) {
            logger.error("Could not set messageId " + messageId +" as being answered", e);
        }

        return false;
    }

    public boolean setMessageAnswered(long messageId) {
        try {
            Message message = MessageLocalServiceUtil.getMessage(messageId);
            message.setIsAnswered(true);
            messagePersistence.update(message);

            return true;
        } catch (Exception e) {
            logger.error("Could not set messageId " + messageId +" as being answered", e);
        }

        return false;
    }

    public boolean setMessageForwarded(long messageId) {
        try {
            Message message = MessageLocalServiceUtil.getMessage(messageId);
            message.setIsForwarded(true);
            messagePersistence.update(message);

            return true;
        } catch (Exception e) {
            logger.error("Could not set messageId " + messageId +" as being forwarded", e);
        }

        return false;
    }

    @Indexable(type = IndexableType.REINDEX)
    public JSONObject saveDraft(long senderId, long draftMessageId, String subject, String content, List<Long> recipientIds, List<Long> attachFileIds, long threadId, boolean isSupport) {
        JSONObject result = new JSONObject();

        result.put(JSONConstants.SUCCESS, false);
        try {
            Message message;

            if (draftMessageId > 0) {
                // Draft was existing
                message = MessageLocalServiceUtil.getMessage(draftMessageId);

                message.setMessageSubject(subject);
                String sanitizedContent = FileUtilsLocalServiceUtil.sanitizeHTMLContent(content);
                String previewContent = HtmlParserUtil.extractText(sanitizedContent).trim();
                if (previewContent.length() > 250) {
                    previewContent = previewContent.substring(0, 250);
                }
                message.setMessageContent(previewContent);

                try {
                    MessageContent messageContent = MessageContentLocalServiceUtil.getMessageContent(message.getMessageId());
                    messageContent.setMessageContent(sanitizedContent);
                    MessageContentLocalServiceUtil.updateMessageContent(messageContent);
                } catch (Exception e) {
                    logger.error("Error when updating draft content : messageId = " + message.getMessageId(), e);
                }

                // Remove existing receivers
                try {
                    MessageRecipientsLocalServiceUtil.deleteMessageRecipientsByMessageId(message.getMessageId());
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }

            } else {
                // New message
                MessageFolder draftFolder = MessageFolderLocalServiceUtil.getUserDraftFolder(senderId);

                message = MessageLocalServiceUtil.addMessage(draftFolder.getFolderId(), senderId, new Date(), threadId, subject, content, false, isSupport ? MessagingConstants.TYPE_SUPPORT : MessagingConstants.TYPE_MANUAL, 0);
            }

            // Handle recipients
            if (!recipientIds.isEmpty()) {
                MessageRecipientsLocalServiceUtil.addMessageRecipients(message.getMessageId(), recipientIds);
            }

            // Attached files
            try {
                // Remove existing attachments
                MessageAttachFileLocalServiceUtil.deleteAttachFiles(message.getMessageId());

                // Add all new attached files
                if (attachFileIds != null) {
                    Folder imBox = FolderUtilsLocalServiceUtil.getIMBox(senderId);
                    Folder attachedFilesFolder = DLAppServiceUtil.addFolder(
                            imBox.getGroupId(),
                            imBox.getFolderId(),
                            "PJ du message " + message.getMessageId(),
                            "PJ du message " + message.getMessageId(),
                            new ServiceContext());
                    FolderUtilsLocalServiceUtil.hideDLFolder(attachedFilesFolder.getFolderId());

                    for (Long attachFileId : attachFileIds) {
                        logger.info("Copying file " + attachFileId + " to sender's folder " + attachedFilesFolder.getFolderId());
                        FileEntry referenceFile = FileUtilsLocalServiceUtil.copyFileEntry(senderId, attachFileId, attachedFilesFolder.getFolderId(), true);
                        logger.info("Add attached file " + referenceFile.getFileEntryId() + " to message " + message.getMessageId());
                        MessageAttachFileLocalServiceUtil.addAttachFile(message.getMessageId(), referenceFile.getFileEntryId());
                    }
                }

            } catch (Exception e) {
                logger.error("Error while managing attached files in messaging drafts for senderId = " + senderId, e);
            }

            MessageLocalServiceUtil.updateMessage(message);

            result.put(JSONConstants.MESSAGE_ID, message.getMessageId());
            result.put(JSONConstants.SUCCESS, true);
        }
        catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error when saving draft in messaging for user " + senderId, e);
        }

        return result;
    }

    public List<Message> getMessagesByFolder(long folderId, long userId) throws SystemException {
        if (folderId == 0L) {
            folderId = MessageFolderLocalServiceUtil.getUserInboxFolder(userId).getFolderId();
        }

        return messagePersistence.findByfolderId(folderId);
    }

    public List<Message> getFolderThreadMessages(long folderId, long threadId) {
        List<Message> messageList = new ArrayList<>();

        try {
            messageList = messagePersistence.findByfolderId_threadId(folderId, threadId);
        } catch (Exception e) {
            logger.error("Error fetching messages for folderId " + folderId + " and threadId " + threadId, e);
        }

        return messageList;
    }

    public Message getThreadLastMessage(long folderId, long threadId) {
        Message lastMessage = null;

        try {
            List<Message> messageList = messagePersistence.findByfolderId_threadId(folderId, threadId);
            long maxMessageId = 0;

            for (Message message : messageList) {
                // Latest message has the highest messageId
                if (message.getMessageId() > maxMessageId) {
                    maxMessageId = message.getMessageId();
                    lastMessage = message;
                }
            }
        } catch (Exception e) {
            logger.error("Error fetching last message for folderId " + folderId + " and threadId " + threadId, e);
        }
        return lastMessage;
    }

    public int countUnreadMessages(long folderId) {
        try {
            return messagePersistence.countByfolderId_isNew(folderId, true);
        } catch (Exception e) {
            return 0;
        }
    }

    public int countMessages(long folderId) {
        try {
            return messagePersistence.countByfolderId(folderId);
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Message> getMessagesByMessageSendId(long sendMessageId) {
        try {
            return messagePersistence.findBysendMessageId(sendMessageId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Message> getFolderMessages(long folderId, int start, int end, boolean unreadOnly) {
        List<Message> messageList = new ArrayList<>();

        try {
            if (unreadOnly) {
                messageList = messagePersistence.findByfolderId_isNew(folderId, true, start, end);
            } else {
                messageList = messagePersistence.findByfolderId(folderId, start, end);
            }
        } catch (Exception e) {
            logger.error("Error fetching messages for folderId " + folderId + ", start= " + start + " , end=" + end, e);
        }

        return messageList;
    }

    public List<Message> getFolderMessages(long folderId, int start, int end, OrderByComparator odc) {
        List<Message> messageList = new ArrayList<>();

        try {
            messageList = messagePersistence.findByfolderId(folderId, start, end, odc);
        } catch (Exception e) {
            logger.error("Error fetching messages for folderId " + folderId + ", start= " + start + " , end=" + end, e);
        }

        return messageList;
    }

    public List<Message> getAllFolderMessages(long folderId) {
        List<Message> messageList = new ArrayList<>();

        try {
            messageList = messagePersistence.findByfolderId(folderId);
        } catch (Exception e) {
            logger.error("Error fetching messages for folderId " + folderId, e);
        }

        return messageList;
    }

    public List<Message> getUserThreadMessages(long userId, long threadId) {
        List<Message> messageList = new ArrayList<>();

        try {
            return messageFinder.getUserThreadMessages(userId, threadId);
        } catch (Exception e) {
            logger.error("Error fetching user's thread messages for userId " + userId + ", threadId= " + threadId, e);
        }

        return messageList;
    }

    @Indexable(type = IndexableType.DELETE)
    public void deleteMessageAndDependencies(long messageId) throws NoSuchMessageException, SystemException {
        // Message content, recipients and attached files are duplicated among all recipients, so no need to keep old messages anymore
        try {
            MessageRecipientsLocalServiceUtil.deleteMessageRecipientsByMessageId(messageId);
        } catch (Exception e) {
            logger.error("Error deleting recipients for message " + messageId, e);
        }

        // Delete the content
        try {
            MessageContentLocalServiceUtil.deleteMessageContent(messageId);
        } catch (Exception e) {
            logger.error("Error deleting content for message " + messageId, e);
        }

        // Delete the attachments
        try {
            MessageAttachFileLocalServiceUtil.deleteAttachFiles(messageId);
        } catch (Exception e) {
            logger.error("Error deleting attach files for message " + messageId, e);
        }

        // Delete the message itself
        messagePersistence.remove(messageId);
    }

    /**
     * Fetch message recipients
     */
    public List<User> getMessageRecipients(Long[] userIds) {
        return messageFinder.findMessageRecipients(userIds);
    }

    public boolean performMailForwards(User receiver, String subject, String content, String senderName, List<File> attachmentFileList) throws SystemException {
        // Send the redirections if exists
        boolean isMailForwardEnabled = PrefsPropsUtil.getBoolean(NeroSystemProperties.MAIL_FORWARD_ENABLED);
        if (!isMailForwardEnabled) {
            return false;
        }

        ResourceBundle bundle = ResourceBundle.getBundle("content.Language", receiver.getLocale());
        String noReplyAddress = PrefsPropsUtil.getString(NeroSystemProperties.MAIL_NO_REPLY_ADDRESS);
        String forwardedMailSubject = bundle.getString("forward-subject") + HtmlParserUtil.extractText(subject);
        String forwardedMailContent = bundle.getString("forward-content");

        // Exception for administrator
        if (PrefsPropsUtil.getBoolean(NeroSystemProperties.MAIL_FORWARD_ADDCONTENT) || RoleUtilsLocalServiceUtil.isAdministrator(receiver)) {
            forwardedMailContent += "</br></br>" + content;
        }

        List<String> forwardMails = MessagingConfigLocalServiceUtil.getForwardAddresses(receiver.getUserId());

        for (String forwardMail : forwardMails) {

            try {
                MailMessage mailMessage = new MailMessage();
                mailMessage.setFrom(new InternetAddress(noReplyAddress));
                mailMessage.setTo(new InternetAddress(forwardMail));
                mailMessage.setHTMLFormat(true);
                mailMessage.setSubject(forwardedMailSubject);
                mailMessage.setBody(forwardedMailContent);
                if (PrefsPropsUtil.getBoolean(NeroSystemProperties.MAIL_FORWARD_ADDCONTENT)) {
                    for (File file : attachmentFileList) {
                        mailMessage.addFileAttachment(file);
                    }
                }
                MailServiceUtil.sendEmail(mailMessage);
            } catch (Exception e) {
                logger.error("Error forwarding mail to address " + forwardMail, e);
            }
        }

        return true;
    }

    public List<Message> getRecipientsMessages (Message message) {
        try {
            if (message.getSendMessageId() == 0) {
                // This is a sent message
                return messagePersistence.findBysendMessageId(message.getMessageId());
            } else {
                // This is a received message
                return messagePersistence.findBysendMessageId(message.getSendMessageId());
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void deleteUsersMessages (String userIds) throws PortalException, SystemException {
        deleteUsersMessages(MessagingUtil.UserIdsStringToUserList(userIds));
    }

    public void deleteUsersMessages (List<User> userList) throws SystemException {
        for (User user: userList) {
            logger.info("Delete messages for user " + user.getFullName());
            deleteUserMessages(user);
        }
    }

    public void deleteUsersPersonalFolders (String userIds) throws PortalException {
        deleteUsersPersonalFolders(MessagingUtil.UserIdsStringToUserList(userIds));
    }

    public void deleteUsersPersonalFolders (List<User> userList) {
        for (User user: userList) {
            logger.info("Delete personalsFolders for user " + user.getFullName());

            List<MessageFolder> rootFolders = MessageFolderLocalServiceUtil.getUserSubFolders(user.getUserId(), 0);
            for (MessageFolder rootFolder : rootFolders) {
                if (rootFolder.getType() == MessagingConstants.PERSONAL_FOLDER_TYPE) {
                    MessageFolderLocalServiceUtil.deletePersonalFolder(rootFolder, user.getUserId());
                }
            }
        }
    }

    public void deleteUserMessages(User user) throws SystemException {
        List<Message> messagesToDelete = getAllUserMessages(user);

        for (Message messageToDelete : messagesToDelete) {
            try {
                MessageLocalServiceUtil.deleteMessageAndDependencies(messageToDelete.getMessageId());
            } catch (Exception e) {
                logger.error("Error deleting user message " + messageToDelete.getMessageId(), e);
            }
        }
    }

    private List<Message> getAllUserMessages (User user) {
        List<Message> userMessages = new ArrayList<>();

        List<MessageFolder> rootFolders = MessageFolderLocalServiceUtil.getUserSubFolders(user.getUserId(), 0);
        for (MessageFolder rootFolder : rootFolders) {
            userMessages.addAll(getFolderMessages(rootFolder, user));
        }

        return userMessages;
    }

    private List<Message> getFolderMessages (MessageFolder folder, User user) {
        List<Message> personalFoldersMessages = new ArrayList<>(
                MessageLocalServiceUtil.getAllFolderMessages(folder.getFolderId())
        );

        if (folder.getType() == MessagingConstants.PERSONAL_FOLDER_TYPE) {
            List<MessageFolder> subFolders = MessageFolderLocalServiceUtil.getUserSubFolders(user.getUserId(), folder.getFolderId());
            for (MessageFolder subFolder : subFolders) {
                personalFoldersMessages.addAll(getFolderMessages(subFolder, user));
            }
        }

        return personalFoldersMessages;
    }
}
