package com.weprode.nero.messaging.service.impl;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.contact.constants.ContactConstants;
import com.weprode.nero.contact.service.ContactCompletionLocalServiceUtil;
import com.weprode.nero.contact.service.ContactLocalServiceUtil;
import com.weprode.nero.messaging.constants.MessagingConstants;
import com.weprode.nero.messaging.model.Message;
import com.weprode.nero.messaging.model.MessageFolder;
import com.weprode.nero.messaging.model.MessagingThread;
import com.weprode.nero.messaging.service.*;
import com.weprode.nero.messaging.service.base.MessageServiceBaseImpl;
import com.weprode.nero.messaging.utils.MessageUtil;
import com.weprode.nero.messaging.utils.MessagingUtil;
import com.weprode.nero.messaging.utils.ThreadUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        property = {
                "json.web.service.context.name=messaging",
                "json.web.service.context.path=Message"
        },
        service = AopService.class
)
public class MessageServiceImpl extends MessageServiceBaseImpl {

    private static final Log logger = LogFactoryUtil.getLog(MessageServiceImpl.class);

    /**
     * Get user messages
     */
    @JSONWebService(value = "get-threads", method = "GET")
    public JSONObject getThreads(long folderId, String fromDate, int nbDisplayed, boolean unreadOnly) {
        JSONObject result = new JSONObject();
        
        result.put(JSONConstants.SUCCESS, false);
        try {
            // Check user's permission to fetch those folderId messages
            long userId = getGuestOrUserId();
            logger.info("User " + userId + " fetches messages of folderId " + folderId + ", from " + fromDate + (unreadOnly ? " (unread only)" : ""));
            if (MessageFolderLocalServiceUtil.getMessageFolder(folderId).getUserId() != userId) {
                logger.error("User " + userId + " try to fetch message from folderId " + folderId + ", but it not belong to him!");
                result.put(JSONConstants.ERROR, "PermissionException");
                return result;
            }

            // If no date specified, get threads from now
            Date fromDateDate = fromDate.equals("-1") ? new Date() :
                    new SimpleDateFormat(MessagingUtil.messagingDateFormat).parse(fromDate);
            List<MessagingThread> lastThreads = MessageLocalServiceUtil.getLastThreads(userId, folderId, fromDateDate, nbDisplayed, unreadOnly);
            result.put(JSONConstants.THREADS, ThreadUtil.formatThreadList(lastThreads, false, folderId));

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error in getting profile user's threads", e);
        }
        return result;
    }

    @JSONWebService(value = "get-message-thread", method = "GET")
    public JSONObject getMessageThread(long messageId) throws PrincipalException {
        JSONObject result = new JSONObject();

        long userId = getGuestOrUserId();
        result.put(JSONConstants.SUCCESS, false);
        try {
            Message message = MessageLocalServiceUtil.getMessage(messageId);
            // Check if user have the right to see the message (and it threads)
            if (MessageFolderLocalServiceUtil.getMessageFolder(message.getFolderId()).getUserId() != userId) {
                logger.error("User " + userId + " try to fetch message from folderId " +  message.getFolderId() + ", but it not belong to him!");
                result.put(JSONConstants.ERROR, "PermissionException");
                return result;
            }
            logger.info("User " + userId + " fetches thread from messageId " + messageId);

            result.put(JSONConstants.MESSAGE_FOLDER_ID, message.getFolderId()); // To be able to select the correct folder in front
            MessagingThread thread = MessageLocalServiceUtil.getUserThread(userId, message.getThreadId());
            result.put(JSONConstants.THREAD, ThreadUtil.formatThread(thread, false, message.getFolderId()));
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error getting thread from message " + messageId + " for userId " + userId, e);
        }

        return result;
    }

    @JSONWebService(value = "get-thread-messages", method = "GET")
    public JSONObject getThreadMessages(long threadId, long folderId) throws PrincipalException {
        JSONObject result = new JSONObject();

        long userId = getGuestOrUserId();
        result.put(JSONConstants.SUCCESS, false);
        try {
            logger.info("User " + userId + " fetches all messages of threadId " + threadId + " and from folderId " + folderId);
            MessageFolder trashFolder = MessageFolderLocalServiceUtil.getUserTrashFolder(userId);

            // Get all other messages of this thread, through all user's folders
            List<Message> threadMessages = MessageLocalServiceUtil.getUserThreadMessages(userId, threadId);
            JSONArray jsonMessages = new JSONArray();

            for (Message threadMessage : threadMessages) {
                // Skip messages in trash while current folder is not trash
                if (folderId != trashFolder.getFolderId() && threadMessage.getFolderId() == trashFolder.getFolderId()) {
                    continue;
                }

                JSONObject jsonMessage = MessageUtil.convertMessageToJSON(threadMessage, true);

                // Add folder name if not in the current selected folder AND if not trash
                if (threadMessage.getFolderId() != folderId) {
                    MessageFolder folder = MessageFolderLocalServiceUtil.getMessageFolder(threadMessage.getFolderId());
                    jsonMessage.put(JSONConstants.FOLDER_NAME, folder.getFolderName());
                }
                jsonMessages.put(jsonMessage);
            }
            result.put(JSONConstants.MESSAGES, jsonMessages);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error getting message details for threadId = " + threadId + " for userId " + userId, e);
        }

        return result;
    }

    @JSONWebService(value = "get-nb-messages", method = "GET")
    public JSONObject getNbMessages(long folderId) throws PrincipalException {
        JSONObject result = new JSONObject();

        long userId = getGuestOrUserId();
        result.put(JSONConstants.SUCCESS, false);
        try {
            result.put(JSONConstants.NB_MESSAGES, MessageLocalServiceUtil.countMessages(folderId));
            result.put(JSONConstants.NB_UNREAD, MessageLocalServiceUtil.countUnreadMessages(folderId));
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error getting number of new messages for userId " + userId, e);
        }

        return result;
    }

    // TODO ?
    /* @JSONWebService(value = "search-messages", method = "GET")
    public JSONObject searchMessages(long folderId, String search, int startIndex, int nbResults, boolean unreadOnly) throws PrincipalException {
        JSONObject result = new JSONObject();

        long userId = getGuestOrUserId();
        result.put(JSONConstants.SUCCESS, false);

        search = IndexerUtil.normalizeStringEscaped(search);

        int endIndex = QueryUtil.ALL_POS;
        if (nbResults > -1) {
            endIndex = startIndex + nbResults;
        }
        logger.info("User " + userId + " searches messages with query " + search + ", in folderId " + folderId);

        if (!search.equals("") && folderId != -1) {
            try {
                final String MODIFIED_DATE_FORMATTED = "modifiedDate_sortable";

                User user = UserLocalServiceUtil.getUser(userId);
                Hits resultsSearch = MessageLocalServiceUtil.search(user.getCompanyId(), 0, folderId,
                        user.getUserId(), 0, search, startIndex, endIndex, new Sort[] {new Sort(MODIFIED_DATE_FORMATTED, false)});

                List<Document> docList = resultsSearch.toList();
                JSONArray jsonMessages = new JSONArray();

                for (Document doc : docList) {
                    try{
                        Message message = MessageLocalServiceUtil.getMessage(GetterUtil.getLong(doc.get("entryClassPK")));

                        JSONObject jsonMessage = MessageUtil.convertMessageToJSON(message, false);
                        jsonMessages.put(jsonMessage);

                    } catch (Exception e){
                        logger.warn("Error when converting message to json for " + user.getFullName() + " (id = "+ user.getUserId() + ")");
                    }
                }
                result.put(JSONConstants.MESSAGES, jsonMessages);

            } catch (Exception e) {
                logger.error("Message search failed for userId " + userId + ", folderId " + folderId + ", search " + search, e);
            }
        }

        return result;
    }*/

    /**
     * Get the full recipients list for a message
     */
    @JSONWebService(value = "get-message-recipients", method = "GET")
    public JSONObject getMessageRecipients(long messageId) {
        JSONObject result = new JSONObject();

        JSONArray jsonRecipients = new JSONArray();
        try {
            long userId = getGuestOrUserId();
            Message message = MessageLocalServiceUtil.getMessage(messageId);
            // Check if user have the right to see the message (and it threads)
            if (MessageFolderLocalServiceUtil.getMessageFolder(message.getFolderId()).getUserId() != userId) {
                logger.error("User " + userId + " try to fetch recipients of messageId " +  messageId + ", but it not belong to him!");
                result.put(JSONConstants.ERROR, "PermissionException");
                return result;
            }
            logger.info("User " + userId + " fetches recipients of messageId " + messageId);
            List<User> recipients = MessageRecipientsLocalServiceUtil.getRecipients(messageId);

            for (User recipient : recipients) {
                JSONObject jsonRecipient = new JSONObject();
                jsonRecipient.put(JSONConstants.USER_ID, recipient.getUserId());
                jsonRecipient.put(JSONConstants.TYPE, ContactConstants.RECIPIENT_TYPE_USER);
                jsonRecipient.put(JSONConstants.FIRST_NAME, recipient.getFirstName());
                jsonRecipient.put(JSONConstants.LAST_NAME, recipient.getLastName());
                jsonRecipients.put(jsonRecipient);
            }
            result.put(JSONConstants.RECIPIENTS, jsonRecipients);
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error fetching recipients for message " + messageId, e);
            result.put(JSONConstants.SUCCESS, false);
        }

        result.put(JSONConstants.RECIPIENTS, jsonRecipients);

        return result;
    }

    /**
     * Return useful informations for message creation
     */
    @JSONWebService(value = "get-message-answer-forward-infos", method = "GET")
    public JSONObject getMessageAnswerForwardInfos(long messageId, boolean isReply, boolean isReplyAll, boolean isDraft, boolean isForward) throws PrincipalException {
        JSONObject result = new JSONObject();

        final String PREFIX_REPLY = "Re: ";
        final String PREFIX_FORWARD = "Tr: ";

        long userId = getGuestOrUserId();
        try {
            logger.info("User " + userId + " fetches answer and forward infos of messageId " + messageId);
            Message message = MessageLocalServiceUtil.getMessage(messageId);

            JSONArray jsonRecipients = new JSONArray();
            List<Long> addedRecipientIds = new ArrayList<>();

            // Avoid self answer
            addedRecipientIds.add(userId);

            // Add sender in case of reply or replyAll
            if (isReply || isReplyAll) {
                User userSender = UserLocalServiceUtil.getUser(message.getSenderId());
                jsonRecipients.put(MessageUtil.convertRecipient(userSender));
                addedRecipientIds.add(userSender.getUserId());
            }

            // Add other recipients
            if (isDraft || isReplyAll) {
                List<User> recipients = MessageRecipientsLocalServiceUtil.getRecipients(messageId);

                for (User recipient : recipients) {

                    if (!addedRecipientIds.contains(recipient.getUserId()) || isDraft) {
                        JSONObject jsonRecipient = MessageUtil.convertRecipient(recipient);
                        jsonRecipients.put(jsonRecipient);
                        addedRecipientIds.add(recipient.getUserId());
                    }
                }
            }
            result.put(JSONConstants.RECIPIENTS, jsonRecipients);

            String subject = message.getMessageSubject();
            if ((isReply || isReplyAll) && !message.getMessageSubject().startsWith(PREFIX_REPLY)) {
                subject = PREFIX_REPLY + message.getMessageSubject();
            }
            if (isForward && !message.getMessageSubject().startsWith(PREFIX_FORWARD)) {
                subject = PREFIX_FORWARD + message.getMessageSubject();
            }
            result.put(JSONConstants.SUBJECT, subject);

            // Content
            String content = MessageContentLocalServiceUtil.getContent(messageId);
            result.put(JSONConstants.CONTENT, content);

            // Add attached files if forward
            JSONArray jsonAttachedFiles = new JSONArray();
            if (isForward || isDraft) {
                List<Long> attachedFileIds = MessageAttachFileLocalServiceUtil.getMessageAttachFileIds(messageId);
                for (Long attachedFileId : attachedFileIds) {
                    JSONObject jsonAttachedFile = new JSONObject();
                    jsonAttachedFile.put(JSONConstants.ID, attachedFileId);
                    FileEntry fileEntry = DLAppServiceUtil.getFileEntry(attachedFileId);
                    jsonAttachedFile.put(JSONConstants.NAME, fileEntry.getTitle());
                    jsonAttachedFiles.put(jsonAttachedFile);
                }
            }
            result.put(JSONConstants.ATTACHED_FILES, jsonAttachedFiles);

            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error fetching message attachments", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    /**
     * Change message read status
     */
    @JSONWebService(value = "set-message-read-status", method = "POST")
    public JSONObject setMessageReadStatus(String messageIds, boolean isRead) {
        JSONObject result = new JSONObject();

        result.put(JSONConstants.SUCCESS, false);
        try {
            long userId = getGuestOrUserId();
            logger.info("User " + userId + " sets messageIds " + messageIds + " as " + (isRead ? "read" : "unread"));
            JSONArray jsonMessageIds = new JSONArray(messageIds);
            for (int i = 0 ; i < jsonMessageIds.length() ; i++) {
                try {
                    long messageId = jsonMessageIds.getLong(i);
                    MessageLocalServiceUtil.setMessageAsRead(messageId, isRead);
                } catch (Exception e) {
                    logger.error("Error setting read/unread status to messages " + messageIds, e);
                }
            }
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error parsing messages " + messageIds, e);
        }

        return result;
    }

    @JSONWebService(value = "send-message", method = "POST")
    public JSONObject sendMessage(String recipients, String subject, String content, String attachedFiles, long draftMessageId, long originMessageId, boolean isReply, boolean isForward, boolean isSupport) throws PrincipalException {
        JSONObject result = new JSONObject();

        result.put(JSONConstants.SUCCESS, false);
        try {
            User user = getGuestOrUser();

            logger.info("User " + user.getFullName() + " (" + user.getUserId() + ") sends message with subject " + subject + ", content=" + content + ", attachedFiles=" + attachedFiles + " to recipients " + recipients);

            JSONArray jsonAttachedFiles = new JSONArray(attachedFiles);
            List<Long> attachedFileIds = new ArrayList<>();
            for (int i = 0 ; i < jsonAttachedFiles.length() ; i++) {
                attachedFileIds.add(jsonAttachedFiles.getJSONObject(i).getLong("id"));
            }

            JSONArray jsonRecipients = new JSONArray(recipients);
            List<Long> recipientList = ContactLocalServiceUtil.getRecipients(jsonRecipients, user);

            MessageLocalServiceUtil.sendMessage(user.getUserId(), recipientList, subject, content, MessagingConstants.TYPE_MANUAL, attachedFileIds, draftMessageId, originMessageId);

            // Set original message as answered/forwarded
            if (isReply && originMessageId > 0) {
                MessageLocalServiceUtil.setMessageAnswered(originMessageId);
            }
            if (isForward && originMessageId > 0) {
                MessageLocalServiceUtil.setMessageForwarded(originMessageId);
            }
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error(e);
        }

        return result;
    }

    @JSONWebService(value = "save-draft", method = "POST")
    public JSONObject saveDraft(String recipients, String subject, String content, String attachedFiles, long draftMessageId, boolean isSupport) {
        JSONObject result = new JSONObject();

        result.put(JSONConstants.SUCCESS, false);
        try {
            User user = getGuestOrUser();

            logger.info("User " + user.getFullName() + " (" + user.getUserId() + ") "
                    + "saves draft with subject " + subject);

            JSONArray jsonAttachedFiles = new JSONArray(attachedFiles);
            List<Long> attachFileIds = new ArrayList<>();
            for (int i = 0 ; i < jsonAttachedFiles.length() ; i++) {
                attachFileIds.add(jsonAttachedFiles.getJSONObject(i).getLong("id"));
            }

            JSONArray jsonRecipients = new JSONArray(recipients);
            List<Long> recipientList = ContactLocalServiceUtil.getRecipients(jsonRecipients, user);

            MessageLocalServiceUtil.saveDraft(user.getUserId(), draftMessageId, subject, content, recipientList, attachFileIds, isSupport);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error(e);
        }

        return result;
    }

    /**
     * Move messages to folder
     */
    @JSONWebService(value = "move-messages", method = "POST")
    public JSONObject moveMessages(long folderId, String messageIds) {
        JSONObject result = new JSONObject();

        result.put(JSONConstants.SUCCESS, false);
        long nbMovedMessages = 0;
        try {
            User user = getGuestOrUser();

            logger.info("User " + user.getFullName() + " (" + user.getUserId() + ") " + "moves messages " + messageIds + " to folder " + folderId);

            if (folderId == -1) {
                MessageFolder inboxFolder = MessageFolderLocalServiceUtil.getUserInboxFolder(user.getUserId());
                folderId = inboxFolder.getFolderId();
            }

            JSONArray jsonMessageIds = new JSONArray(messageIds);
            for (int i = 0 ; i < jsonMessageIds.length() ; i++) {
                long messageId = jsonMessageIds.getLong(i);
                try {
                    Message message = MessageLocalServiceUtil.getMessage(messageId);
                    message.setFolderId(folderId);
                    MessageLocalServiceUtil.updateMessage(message);
                    ++ nbMovedMessages;
                } catch (Exception e) {
                    logger.error("Error moving message " + messageId + " to folder " + folderId, e);
                }
            }
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error( e);
        }

        result.put(JSONConstants.NB_MOVED_MESSAGES, nbMovedMessages);

        return result;
    }

    /**
     * Delete messages
     */
    @JSONWebService(value = "delete-messages", method = "POST")
    public JSONObject deleteMessages(String messageIds) {
        JSONObject result = new JSONObject();

        result.put(JSONConstants.SUCCESS, false);
        try {
            User user = getGuestOrUser();

            logger.info("User " + user.getFullName() + " (" + user.getUserId() + ") " + "deletes messages " + messageIds);

            final long trashFolderId = MessageFolderLocalServiceUtil.getUserTrashFolder(user.getUserId()).getFolderId();

            JSONArray jsonMessageIds = new JSONArray(messageIds);
            for (int i = 0 ; i < jsonMessageIds.length() ; i++) {
                long messageId = jsonMessageIds.getLong(i);
                try {
                    Message messageToDelete = MessageLocalServiceUtil.getMessage(messageId);

                    if (messageToDelete.getFolderId() == trashFolderId) {
                        MessageLocalServiceUtil.deleteMessageAndDependencies(messageId);
                    } else {
                        // Move to trashes
                        messageToDelete.setFolderId(trashFolderId);
                        MessageLocalServiceUtil.updateMessage(messageToDelete);
                    }
                } catch (Exception e) {
                    logger.error("Error deleting messageId " + messageId + " among others", e);
                }
            }
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            result.put(JSONConstants.SUCCESS, false);
            logger.error("Error deleting messageIds " + messageIds, e);
        }

        return result;
    }

    @JSONWebService(value = "get-users-completion", method="GET")
    public JSONObject getUsersCompletion (String query) {
        try {
            User user = getGuestOrUser();
            logger.info("User " + user.getFullName() + " uses messaging auto-completion with query " + query);

            return ContactCompletionLocalServiceUtil.getCompletionResultAsJSON(query, user, true);
        } catch (Exception e) {
            logger.error("Error in auto-completion", e);
            JSONObject result = new JSONObject();
            result.put(JSONConstants.SUCCESS, false);

            return result;
        }
    }

    /**
     * Test useful method to clean all the user's messaging objects
     */
    @JSONWebService(method = "POST")
    public JSONObject testCleanUserMessaging(String userIds) {
        JSONObject result = new JSONObject();
        result.put(JSONConstants.SUCCESS, false);

        try {
            MessageLocalServiceUtil.deleteUsersMessages(userIds);
            MessageLocalServiceUtil.deleteUsersPersonalFolders(userIds);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error in cleaning test users messaging", e);
        }

        return result;
    }

    /**
     * Test useful method to clean all the user's messaging objects
     */
    @JSONWebService(method = "POST")
    public JSONObject testSendMessage(long senderId, String recipients, String subject, String content, String attachedFiles, long draftMessageId, long originMessageId, boolean isReply, boolean isForward, boolean isSupport) {
        JSONObject result = new JSONObject();

        result.put(JSONConstants.SUCCESS, false);
        try {
            User user = getGuestOrUser();

            logger.info("User " + user.getFullName() + " (" + user.getUserId() + ") "
                    + "sends message to " + recipients +  " with subject " + subject + ", content=" + content + ", recipients=" + recipients + ", attachedFiles=" + attachedFiles);

            JSONArray jsonAttachedFiles = new JSONArray(attachedFiles);
            List<Long> attachedFileIds = new ArrayList<>();
            for (int i = 0 ; i < jsonAttachedFiles.length() ; i++) {
                attachedFileIds.add(jsonAttachedFiles.getJSONObject(i).getLong(JSONConstants.ID));
            }

            JSONArray jsonRecipients = new JSONArray(recipients);
            List<Long> recipientList = ContactLocalServiceUtil.getRecipients(jsonRecipients, user);

            MessageLocalServiceUtil.sendMessage(senderId, recipientList, subject, content, MessagingConstants.TYPE_MANUAL, attachedFileIds, draftMessageId, originMessageId);

            // Set original message as answered/forwarded
            if (isReply && originMessageId > 0) {
                MessageLocalServiceUtil.setMessageAnswered(originMessageId);
            }
            if (isForward && originMessageId > 0) {
                MessageLocalServiceUtil.setMessageForwarded(originMessageId);
            }
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error(e);
        }

        return result;
    }
}
