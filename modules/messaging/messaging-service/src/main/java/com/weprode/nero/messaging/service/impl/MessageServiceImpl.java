package com.weprode.nero.messaging.service.impl;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.weprode.nero.application.model.Application;
import com.weprode.nero.application.service.ApplicationLocalServiceUtil;
import com.weprode.nero.commons.JSONProxy;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.contact.constants.ContactConstants;
import com.weprode.nero.contact.service.ContactCompletionLocalServiceUtil;
import com.weprode.nero.contact.service.ContactLocalServiceUtil;
import com.weprode.nero.messaging.constants.MessagingConstants;
import com.weprode.nero.messaging.model.Message;
import com.weprode.nero.messaging.model.MessageFolder;
import com.weprode.nero.messaging.model.MessagingThread;
import com.weprode.nero.messaging.service.MessageAttachFileLocalServiceUtil;
import com.weprode.nero.messaging.service.MessageContentLocalServiceUtil;
import com.weprode.nero.messaging.service.MessageFolderLocalServiceUtil;
import com.weprode.nero.messaging.service.MessageLocalServiceUtil;
import com.weprode.nero.messaging.service.MessageRecipientsLocalServiceUtil;
import com.weprode.nero.messaging.service.base.MessageServiceBaseImpl;
import com.weprode.nero.messaging.utils.MessageUtil;
import com.weprode.nero.messaging.utils.MessagingUtil;
import com.weprode.nero.messaging.utils.ThreadUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.user.service.SchoolAdminLocalServiceUtil;
import com.weprode.nero.user.service.UserSearchLocalServiceUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            // Check user's permission to fetch those folderId messages
            logger.info("User " + user.getUserId() + " fetches messages of folderId " + folderId + ", from " + fromDate + (unreadOnly ? " (unread only)" : ""));
            if (MessageFolderLocalServiceUtil.getMessageFolder(folderId).getUserId() != user.getUserId()) {
                logger.error("User " + user.getUserId() + " try to fetch message from folderId " + folderId + ", but it not belong to him!");
                result.put(JSONConstants.ERROR, "PermissionException");
                return result;
            }

            // If no date specified, get threads from now
            Date fromDateDate = fromDate.equals("-1") ? new Date() :
                    new SimpleDateFormat(MessagingUtil.messagingDateFormat).parse(fromDate);
            List<MessagingThread> lastThreads = MessageLocalServiceUtil.getThreads(user.getUserId(), folderId, fromDateDate, nbDisplayed, unreadOnly);
            result.put(JSONConstants.THREADS, ThreadUtil.formatThreadList(lastThreads, false, folderId));

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error in getting profile user's threads", e);
            result.put(JSONConstants.SUCCESS, false);
        }
        return result;
    }

    @JSONWebService(value = "get-message-thread", method = "GET")
    public JSONObject getMessageThread(long messageId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            Message message = MessageLocalServiceUtil.getMessage(messageId);
            // Check if user have the right to see the message (and it threads)
            if (MessageFolderLocalServiceUtil.getMessageFolder(message.getFolderId()).getUserId() != user.getUserId()) {
                logger.error("User " + user.getUserId() + " try to fetch message from folderId " +  message.getFolderId() + ", but it not belong to him!");
                result.put(JSONConstants.ERROR, "PermissionException");
                return result;
            }
            logger.info("User " + user.getUserId() + " fetches thread from messageId " + messageId);

            result.put(JSONConstants.MESSAGE_FOLDER_ID, message.getFolderId()); // To be able to select the correct folder in front
            MessagingThread thread = MessageLocalServiceUtil.getMessagingThread(message.getThreadId());
            result.put(JSONConstants.THREAD, ThreadUtil.formatThread(thread, false, message.getFolderId()));
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error getting thread from message " + messageId + " for user.getUserId() " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-thread-messages", method = "GET")
    public JSONObject getThreadMessages(long threadId, long folderId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            logger.info("User " + user.getUserId() + " fetches all messages of threadId " + threadId + " and from folderId " + folderId);
            MessageFolder trashFolder = MessageFolderLocalServiceUtil.getUserTrashFolder(user.getUserId());

            // Get all other messages of this thread, through all user's folders
            List<Message> threadMessages = MessageLocalServiceUtil.getUserThreadMessages(user.getUserId(), threadId);
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
            logger.error("Error getting message details for threadId = " + threadId + " for user.getUserId() " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "get-nb-messages", method = "GET")
    public JSONObject getNbMessages(long folderId) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        try {
            result.put(JSONConstants.NB_MESSAGES, MessageLocalServiceUtil.countMessages(folderId));
            result.put(JSONConstants.NB_UNREAD, MessageLocalServiceUtil.countUnreadMessages(folderId));
            result.put(JSONConstants.SUCCESS, true);

        } catch (Exception e) {
            logger.error("Error getting number of new messages for user.getUserId() " + user.getUserId(), e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    // TODO ?
    /* @JSONWebService(value = "search-messages", method = "GET")
    public JSONObject searchMessages(long folderId, String search, int startIndex, int nbResults, boolean unreadOnly) throws PrincipalException {
        JSONObject result = new JSONObject();

        long user.getUserId() = getGuestOrUserId();
        result.put(JSONConstants.SUCCESS, false);

        search = IndexerUtil.normalizeStringEscaped(search);

        int endIndex = QueryUtil.ALL_POS;
        if (nbResults > -1) {
            endIndex = startIndex + nbResults;
        }
        logger.info("User " + user.getUserId() + " searches messages with query " + search + ", in folderId " + folderId);

        if (!search.equals("") && folderId != -1) {
            try {
                final String MODIFIED_DATE_FORMATTED = "modifiedDate_sortable";

                User user = UserLocalServiceUtil.getUser(user.getUserId());
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
                logger.error("Message search failed for user.getUserId() " + user.getUserId() + ", folderId " + folderId + ", search " + search, e);
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

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        JSONArray jsonRecipients = new JSONArray();
        try {
            Message message = MessageLocalServiceUtil.getMessage(messageId);
            // Check if user have the right to see the message (and it threads)
            if (MessageFolderLocalServiceUtil.getMessageFolder(message.getFolderId()).getUserId() != user.getUserId()) {
                logger.error("User " + user.getUserId() + " try to fetch recipients of messageId " +  messageId + ", but it not belong to him!");
                result.put(JSONConstants.ERROR, "PermissionException");
                return result;
            }
            logger.info("User " + user.getUserId() + " fetches recipients of messageId " + messageId);
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
    public JSONObject getMessageAnswerForwardInfos(long messageId, boolean isReply, boolean isReplyAll, boolean isDraft, boolean isForward) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        final String PREFIX_REPLY = "Re: ";
        final String PREFIX_FORWARD = "Tr: ";

        try {
            logger.info("User " + user.getUserId() + " fetches answer and forward infos of messageId " + messageId);
            Message message = MessageLocalServiceUtil.getMessage(messageId);

            JSONArray jsonRecipients = new JSONArray();
            List<Long> addedRecipientIds = new ArrayList<>();

            // Avoid self answer
            addedRecipientIds.add(user.getUserId());

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

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            logger.info("User " + user.getUserId() + " sets messageIds " + messageIds + " as " + (isRead ? "read" : "unread"));
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
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "send-message", method = "POST")
    public JSONObject sendMessage(String recipients, String subject, String content, String attachedFiles, long draftMessageId, long originMessageId, boolean isReply, boolean isForward, boolean isSupport) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            logger.info("User " + user.getFullName() + " (" + user.getUserId() + ") sends message with subject " + subject + ", content=" + content + ", attachedFiles=" + attachedFiles + " to recipients " + recipients);

            JSONArray jsonAttachedFiles = new JSONArray(attachedFiles);
            List<Long> attachedFileIds = new ArrayList<>();
            for (int i = 0 ; i < jsonAttachedFiles.length() ; i++) {
                attachedFileIds.add(jsonAttachedFiles.getJSONObject(i).getLong(JSONConstants.ID));
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
            logger.error("Error when user " + user.getUserId() + " is sending a message", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "save-draft", method = "POST")
    public JSONObject saveDraft(String recipients, String subject, String content, String attachedFiles, long draftMessageId, boolean isSupport) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            logger.info("User " + user.getFullName() + " (" + user.getUserId() + ") "
                    + "saves draft with subject " + subject);

            JSONArray jsonAttachedFiles = new JSONArray(attachedFiles);
            List<Long> attachFileIds = new ArrayList<>();
            for (int i = 0 ; i < jsonAttachedFiles.length() ; i++) {
                attachFileIds.add(jsonAttachedFiles.getJSONObject(i).getLong(JSONConstants.ID));
            }

            JSONArray jsonRecipients = new JSONArray(recipients);
            List<Long> recipientList = ContactLocalServiceUtil.getRecipients(jsonRecipients, user);

            MessageLocalServiceUtil.saveDraft(user.getUserId(), draftMessageId, subject, content, recipientList, attachFileIds, isSupport);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when user " + user.getUserId() + " is saving draft", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    /**
     * Move messages to folder
     */
    @JSONWebService(value = "move-messages", method = "POST")
    public JSONObject moveMessages(long folderId, String messageIds) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        long nbMovedMessages = 0;
        try {
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
            logger.error("Error when user " + user.getUserId() + " moves messages", e);
            result.put(JSONConstants.SUCCESS, false);
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

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
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
        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }
        try {
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
        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
            logger.info("User " + user.getUserId() + " deletes all messages of multiple users " + userIds + " !!!");
            MessageLocalServiceUtil.deleteUsersMessages(userIds);
            MessageLocalServiceUtil.deleteUsersPersonalFolders(userIds);
            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error in cleaning test users messaging", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    /**
     * Test useful method to clean all the user's messaging objects
     */
    @JSONWebService(method = "POST")
    public JSONObject testSendMessage(long senderId, String recipients, String subject, String content, String attachedFiles, long draftMessageId, long originMessageId, boolean isReply, boolean isForward, boolean isSupport) {
        JSONObject result = new JSONObject();
        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        try {
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
            logger.error("Error sending test message", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }

    @JSONWebService(value = "send-assistance-message", method = "POST")
    public JSONObject sendAssistanceMessage(long applicationId, String content, boolean isSuggestion, String attachFiles) {
        JSONObject result = new JSONObject();

        User user;
        try {
            user = getGuestOrUser();
            if (user == null || user.getUserId() == UserLocalServiceUtil.getDefaultUserId(PortalUtil.getDefaultCompanyId())) {
                return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
            }
        } catch (Exception e) {
            return JSONProxy.getJSONReturnInErrorCase(JSONConstants.AUTH_EXCEPTION);
        }

        ResourceBundle messages = ResourceBundle.getBundle("content.Language", user.getLocale());
        String htmlContent = content.replaceAll("(\r\n|\n)", "<br />");

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(JSONConstants.FRENCH_FORMAT);
        SimpleDateFormat hourFormat = new SimpleDateFormat("H:mm");
        String dateDay = dateFormat.format(currentDate);
        String dateTime = hourFormat.format(currentDate);

        String statut = messages.getString("utilisateur-local");
        try {
            statut = RoleUtilsLocalServiceUtil.displayUserRoles(user);
        } catch (Exception e) {
            logger.debug(e);
        }

        String schoolName = OrgUtilsLocalServiceUtil.formatOrgName(UserOrgsLocalServiceUtil.getEtabRatachement(user).getName(), true);

        try {
            // Manage destination people
            List<Long> destFinal = new ArrayList<>();

            // 1. Send to all Administrators
            long roleId = RoleUtilsLocalServiceUtil.getAdministratorRole().getRoleId();

            if (roleId > 0) {
                List<Long> roleIds = new ArrayList<>();
                roleIds.add(roleId);

                final List<User> administrators = UserSearchLocalServiceUtil.searchUsers(
                        StringPool.BLANK, null, null, roleIds,
                        null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

                for (User u : administrators){
                    destFinal.add(u.getUserId());
                }
            }

            // 2. Specific list of destination people in portal-ext.properties
            try {
                String[] notifyUsers = PrefsPropsUtil.getStringArray(NeroSystemProperties.ENT_INCIDENTS_USERS_NOTIFICATION, ",");
                for (String notifyUser : notifyUsers) {
                    destFinal.add(Long.parseLong(notifyUser));
                }
            } catch (Exception e) {
                logger.debug("No additional users to notify");
            }

            boolean sendToLocalAdmins = PrefsPropsUtil.getBoolean(NeroSystemProperties.SUPPORT_LOCAL_ADMINS_ENABLE);
            boolean sendToDirectionMembers = PrefsPropsUtil.getBoolean(NeroSystemProperties.SUPPORT_DIRECTION_MEMBERS_ENABLE);

            // 3. Local admins and/or direction members of the user's attachment school
            if ((!RoleUtilsLocalServiceUtil.isSchoolAdmin(user)) && (sendToLocalAdmins || sendToDirectionMembers)) {
                Organization rattachSchool = UserOrgsLocalServiceUtil.getEtabRatachement(user);

                if (rattachSchool != null) {
                    // Get local admins
                    List<User> localAdminList = SchoolAdminLocalServiceUtil.getSchoolAdmins(rattachSchool.getOrganizationId());

                    if (localAdminList != null) {
                        for (User adminUser : localAdminList) {
                            boolean isDirectionMember = RoleUtilsLocalServiceUtil.isDirectionMember(adminUser);
                            if ((!isDirectionMember && sendToLocalAdmins) ||
                                    (isDirectionMember && sendToDirectionMembers)) {
                                logger.info("Send support message to local admin "+adminUser.getFullName());
                                destFinal.add(adminUser.getUserId());
                            }
                        }
                    } else {
                        logger.error("The retrieved local admin list is null");
                    }
                }
            }

            // Sender
            List<Long> sender = new ArrayList<>();
            sender.add(user.getUserId());

            Application app = ApplicationLocalServiceUtil.getApplication(applicationId);

            // Build message subject and content
            String ownerSubject = (isSuggestion ? "[Suggestion " : "[Assistance ") + app.getApplicationName() + "]";
            String adminSubject = (isSuggestion ? "[Suggestion " : "[Assistance ") + app.getApplicationName() + "]" + "[" + user.getFullName() + "] ";
            String ownerContent = (isSuggestion ? messages.getString("rappel-suggestion-que-vous-avez-declare-le") : messages.getString("rappel-incident-que-vous-avez-declare-le")) + " : " + dateDay + " " + messages.getString("a") + " " + dateTime + "<br /><br />"
                    + htmlContent;

            // Mail body for internal messaging in html format
            String adminContentHtml =
                    messages.getString("etablissement") + " : " + schoolName + "<br />"
                            + messages.getString("statut") + " : " + statut + "<br />"
                            + (isSuggestion ? messages.getString("suggestion-declare-le") : messages.getString("incident-declare-le")) + " : " + dateDay + " " + messages.getString("a") + " " + dateTime + "<br />"
                            + htmlContent;

            // Attached files
            JSONArray attachFilesArray = new JSONArray(attachFiles);
            List<Long> attachedFileIds = new ArrayList<>();

            for (int i = 0 ; i < attachFilesArray.length() ; i++) {
                attachedFileIds.add(attachFilesArray.getJSONObject(i).getLong(JSONConstants.ID));
            }

            // First message : from user to the list of destination (administrators + list in portal-ext.properties + list of local admins)
            MessageLocalServiceUtil.sendSupportMessage(user, destFinal, adminSubject, adminContentHtml, attachedFileIds, 0, 0);

            // Second message : from technical team to user
            long defaultSender = PrefsPropsUtil.getLong(NeroSystemProperties.ENT_CONFIRMATION_SENDER_ID);
            User admin = UserLocalServiceUtil.getUser(defaultSender);
            MessageLocalServiceUtil.sendSupportMessage(admin, sender, ownerSubject, ownerContent, attachedFileIds, 0, 0);

            result.put(JSONConstants.SUCCESS, true);
        } catch (Exception e) {
            logger.error("Error when sending support message", e);
            result.put(JSONConstants.SUCCESS, false);
        }

        return result;
    }
}
