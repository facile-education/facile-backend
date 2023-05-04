package com.weprode.nero.messaging.utils;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.commons.properties.NeroSystemProperties;
import com.weprode.nero.contact.constants.ContactConstants;
import com.weprode.nero.document.service.FileUtilsLocalServiceUtil;
import com.weprode.nero.document.service.FolderUtilsLocalServiceUtil;
import com.weprode.nero.messaging.constants.MessagingConstants;
import com.weprode.nero.messaging.model.Message;
import com.weprode.nero.messaging.model.MessageFolder;
import com.weprode.nero.messaging.service.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageUtil {

	private MessageUtil() {
		throw new IllegalStateException("Utility class");
	}

	private static final Log logger = LogFactoryUtil.getLog(MessageUtil.class);

	public static void sendMessage(User sender, List<Long> recipients, String messageSubject, String messageContent, List<Long> attachFileIds, long draftMessageId, long originMessageId, int type) {
		List<Long> recipientsList = getRecipientsList(recipients, originMessageId, type == MessagingConstants.TYPE_SUPPORT);

		long sendMessageId = 0;
		long threadId = 0;
		
		// Add message in sender's sending box
		long senderId = sender.getUserId();
		try {
			MessageFolder sendFolder = MessageFolderLocalServiceUtil.getUserSendingBoxFolder(senderId);

			// Thread management
			if (originMessageId != 0) {
				threadId = MessageLocalServiceUtil.getMessage(originMessageId).getThreadId();
			}
			
			// First create the message with the content, so that the index can index the content
			Message message = MessageLocalServiceUtil.addMessage(sendFolder.getFolderId(), senderId, new Date(), threadId, messageSubject, messageContent, false, type, 0);
			sendMessageId = message.getMessageId();
			threadId = message.getThreadId();
			
			// Recipients
			MessageRecipientsLocalServiceUtil.addMessageRecipients(message.getMessageId(), recipientsList);
			
			// Attached files
			addAttachFiles(sender.getUserId(), message.getMessageId(), attachFileIds);
		}
		catch (Exception e) {
			logger.error("Error : Cannot add the message in the sender's box", e);
		}

		List<Long> errorRecipients = new ArrayList<>();
		
		// Build attachments once for all
		List<File> attachmentFileList = getFileListFromAttachments(attachFileIds);
		
		// Loop over recipients
		for (long recipientId: recipientsList) {
			try {
				User recipient = UserLocalServiceUtil.getUser(recipientId);

				MessageFolder destFolder = MessageFolderLocalServiceUtil.getUserInboxFolder(recipientId);
				
				Message message = MessageLocalServiceUtil.addMessage(destFolder.getFolderId(), senderId, new Date(), threadId, messageSubject, messageContent, true, type, sendMessageId);

				// Recipients
				MessageRecipientsLocalServiceUtil.addMessageRecipients(message.getMessageId(), recipientsList);

				// Copy attached files to recipient's IMBox
				addAttachFiles(recipientId, message.getMessageId(), attachFileIds);

				// Notify on mobile phone
				boolean isMobileNotificationEnabled = Boolean.parseBoolean(
						PropsUtil.get(NeroSystemProperties.MOBILE_NOTIFICATION_ENABLED));
				if (isMobileNotificationEnabled) {
					ThreadMobileNotification notifThread = new ThreadMobileNotification(recipientId, sender.getFullName(), message.getMessageSubject(), messageContent,
							PropsUtil.get(NeroSystemProperties.MESSAGING_URL), MobileNotification.MESSAGING_TYPE, message.getMessageId());
					notifThread.start();
				}

				// Manage mail forwards
				MessageLocalServiceUtil.performMailForwards(recipient, messageSubject, messageContent, sender.getFullName(), attachmentFileList);

				// Auto reply
				if (isAutoResponseAllowed(senderId) && senderId != recipientId) {
					MessageLocalServiceUtil.sendAutoReply(recipientId, sender, message.getMessageId());
				}

			} catch (Exception e) {
				logger.error("Error during sending message \"" + messageSubject + "\" from sender " + senderId + " to user " + recipientId  + " at date : " + new Date(), e );
				errorRecipients.add(recipientId);
			}

		}
		
		// If errors detected, we send a warning message to the original sender
		if (!errorRecipients.isEmpty()) {
			sendErrorMessage(sender, errorRecipients, messageSubject, messageContent);
		}

		// Remove draft message if needed
		if (draftMessageId > 0) {
			try {
				MessageLocalServiceUtil.deleteMessageAndDependencies(draftMessageId);
			} catch (Exception e){
				logger.error("Exception when deleting draft message after it's been sent", e);
			}
		}
	}

	private static List<Long> getRecipientsList(List<Long> recipients, long originMessageId, boolean isSupport) {
		// Is support message ?
		if (originMessageId > 0) {
			try {
				Message originMessage = MessageLocalServiceUtil.getMessage(originMessageId);
				isSupport = isSupport || originMessage.getType() == MessagingConstants.TYPE_SUPPORT;
				
				if (isSupport) {
					// Get original recipients list
					List<User> originalRecipients = MessageRecipientsLocalServiceUtil.getRecipients(originMessageId);
					if (originalRecipients.size() > recipients.size()) {
						for (User originalRecipient : originalRecipients) {
							if (!recipients.contains(originalRecipient.getUserId())) {
								recipients.add(originalRecipient.getUserId());
							}
						}
						// Add original support sender
						recipients.add(originMessage.getSenderId());
						logger.info("Reply to support message : forcing reply all");
					}
				}

			} catch (Exception e) {
				logger.error("Error processing origin messageId "+originMessageId, e);
			}
		}
		
		return recipients;
	}

	/**
	 * Manage attached files
	 */
	private static void addAttachFiles(long userId, long messageId, List<Long> attachFileIds) {
		try {
			if (attachFileIds != null && !attachFileIds.isEmpty()) {

				Folder imBox = FolderUtilsLocalServiceUtil.getIMBox(userId);
				
				// Create the folder in the sender's sending box
				Folder attachedFilesFolder = DLAppServiceUtil.addFolder(
						imBox.getGroupId(),
						imBox.getFolderId(),
						"PJ du message " + messageId,
						"PJ du message " + messageId,
						new ServiceContext());
				
				for (Long attachFileId : attachFileIds) {
					logger.info("Copying file " + attachFileId + " to IM_BOX, folder " + attachedFilesFolder.getFolderId());
					FileEntry referenceFile = FileUtilsLocalServiceUtil.copyFileEntry(userId, attachFileId, attachedFilesFolder.getFolderId(), true);
					logger.info("Add attached file " + referenceFile.getFileEntryId() + " to message " + messageId);
					MessageAttachFileLocalServiceUtil.addAttachFile(messageId, referenceFile.getFileEntryId());
				}
			}
		} catch (Exception e) {
			logger.error("Error processing attached file", e);
		}
	}
	
	/**
	 * Return file list from attachments JSONArray 
	 */
	private static List<File> getFileListFromAttachments(List<Long> attachFileIds) {
		List<File> attachmentFileList = new ArrayList<>();

		if (attachFileIds != null) {
			for (Long attachFileId : attachFileIds) {

				try {
					// File
					FileEntry dlFile = DLAppServiceUtil.getFileEntry(attachFileId);
					InputStream fileStream = DLFileEntryLocalServiceUtil.getFileAsStream(dlFile.getFileEntryId(), dlFile.getVersion());
					File finalFile = FileUtil.createTempFile(dlFile.getTitle());
					FileUtil.write(finalFile, fileStream);
					attachmentFileList.add(finalFile);
					fileStream.close();
				} catch (Exception e) {
					logger.error("Error when creating mail attachment for id= " + attachFileId, e);
				}
			}
		}

		return attachmentFileList;
	}
	
	private static boolean isAutoResponseAllowed(long senderId) {
		long noReplySenderId = 0;

		try {
			noReplySenderId = PrefsPropsUtil.getLong("mail.noreply.userid");
		} catch (Exception e) {
			logger.error("No noReplySenderId is defined");
		}

		return noReplySenderId != senderId;
	}
	
	private static void sendErrorMessage(User sender, List<Long> errorRecipients, String messageSubject, String messageContent) {
		String errorSubject = "Erreur lors de l'envoi du message";
		
		// Prevent from recursive erroring
		if (messageSubject.contains(errorSubject)) {
			return;
		}

		List<Long> errorSender = new ArrayList<>();
		errorSender.add(sender.getUserId());
		try {
			StringBuilder message = new StringBuilder("Une erreur s'est produite durant l'envoi de votre message.<br>Celui-ci n'a pu &ecirc;tre transmis aux personnes suivantes: <br><ul>");
			for (Long errorRecipientId : errorRecipients) {
				User userDest = UserLocalServiceUtil.getUser(errorRecipientId);
				message.append("<li>").append(userDest.getFirstName()).append(" ").append(userDest.getLastName()).append("</li>");
			}
			message.append("</ul>");
			
			message.append("<br />");
			message.append("<br />");
			message.append("Message non transmis aux diff&eacute;rents destinataires : <br />");
			message.append("<b>Sujet : </b>").append(messageSubject).append("<br />");
			message.append("<b>Contenu : </b><br />").append(messageContent).append("<br />");
			
			sendMessage(sender, errorSender, errorSubject + ": " + messageSubject, message.toString(), null, 0, 0, MessagingConstants.TYPE_OTHER);
		}
		catch (Exception e) {
			logger.error("Error sending IM error message", e);
		}
	}

	public static JSONObject convertMessageToJSON(Message message, boolean fetchFullContent) {
		JSONObject result = new JSONObject();
		
		String fullContent = "";
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // For sorting
		
		if (fetchFullContent) {
			fullContent = MessageContentLocalServiceUtil.getContent(message.getMessageId());
		}

		result.put(JSONConstants.MESSAGE_ID, message.getMessageId());
		result.put(JSONConstants.THREAD_ID, message.getThreadId());
		result.put(JSONConstants.SEND_DATE, sdf.format(message.getSendDate()));
		result.put(JSONConstants.IS_NEW, message.getIsNew());
		String senderName = message.getSenderName();
		User sender = null;
		try {
			sender = UserLocalServiceUtil.getUser(message.getSenderId());
		} catch (Exception e) {
			// Deleted user
		}
		if (sender == null) {
			result.put(JSONConstants.IS_SENDER_DELETED, true);
			senderName += " (Utilisateur supprim\u00e9)";
		}
		result.put(JSONConstants.SENDER_NAME, senderName);
		result.put(JSONConstants.SENDER_ID, message.getSenderId());
		String messageSubject = (message.getMessageSubject() == null || message.getMessageSubject().equals("")) ? DEFAULT_SUBJECT : message.getMessageSubject();
		result.put(JSONConstants.SUBJECT, messageSubject);
		
		// Content
		result.put(JSONConstants.PREVIEW_CONTENT, message.getMessageContent().equals("") ? DEFAULT_CONTENT : message.getMessageContent());
		result.put(JSONConstants.FULL_CONTENT, fullContent);
		
		result.put(JSONConstants.IS_ANSWERED, message.getIsAnswered());
		result.put(JSONConstants.IS_FORWARDED, message.getIsForwarded());

		// Attach files
		result.put(JSONConstants.HAS_ATTACH_FILES, MessageAttachFileLocalServiceUtil.countAttachedFiles(message.getMessageId()) > 0);

		if (fetchFullContent) {
			JSONArray jsonAttachments = new JSONArray();
			List<Long> attachedFileIds = MessageAttachFileLocalServiceUtil.getMessageAttachFileIds(message.getMessageId());
			for (Long attachedFileId : attachedFileIds) {
				try {
					JSONObject jsonAttachment = new JSONObject();
					FileEntry fileEntry = DLAppServiceUtil.getFileEntry(attachedFileId);
					jsonAttachment.put(JSONConstants.ID, attachedFileId);
					jsonAttachment.put(JSONConstants.NAME, fileEntry.getTitle());
					jsonAttachment.put(JSONConstants.TYPE, "File");
					jsonAttachment.put(JSONConstants.EXTENSION, fileEntry.getExtension().toLowerCase());
					jsonAttachment.put(JSONConstants.URL, FileUtilsLocalServiceUtil.getDownloadUrl(fileEntry));
					jsonAttachments.put(jsonAttachment);
				} catch (Exception e) {
					logger.error("Error converting attached file ", e);
				}
			}
			result.put(JSONConstants.ATTACHMENTS, jsonAttachments);
		}
		
		// Recipients
		JSONArray jsonRecipients = new JSONArray();
		if (isDraftMessage(message)) {	// Match the wanted recipients for the draft
			List<User> recipients = MessageRecipientsLocalServiceUtil.getRecipients(message.getMessageId());
			for (User recipient : recipients) {
				JSONObject jsonRecipient = convertRecipient(recipient);
				jsonRecipients.put(jsonRecipient);
			}
			result.put(JSONConstants.NB_RECIPIENTS, recipients.size());
		} else { // Match reals recipients and status
			List<Message> recipientsMessages = MessageLocalServiceUtil.getRecipientsMessages(message);
			if (recipientsMessages == null || recipientsMessages.isEmpty()) {
				// This is indeed a draft that was moved to an other folder
				List<User> recipients = MessageRecipientsLocalServiceUtil.getRecipients(message.getMessageId());
				for (User recipient : recipients) {
					JSONObject jsonRecipient = convertRecipient(recipient);
					jsonRecipients.put(jsonRecipient);
				}
				result.put(JSONConstants.NB_RECIPIENTS, recipients.size());
			} else {
				for (Message recipientMessage : recipientsMessages) {
					try {
						User recipient = MessageFolderLocalServiceUtil.getFolderUser(recipientMessage.getFolderId());
						JSONObject jsonRecipient = convertRecipient(recipient);
						if (message.getSendMessageId() == 0 && recipient != null) {    // Only in case of sent message, add recipient's message status
							jsonRecipient.put(JSONConstants.HAS_READ, recipientMessage.getReadDate() != null);
							if (recipientMessage.getReadDate() != null) {
								jsonRecipient.put(JSONConstants.READ_DATE, sdf.format(recipientMessage.getReadDate()));
							}
						}
						jsonRecipients.put(jsonRecipient);
						// Limit to 2 recipients
						if (!fetchFullContent && jsonRecipients.length() >= 3) {
							break;
						}
					} catch (Exception e) {
						logger.error("Error fetching recipients for message " + message.getMessageId() + " and recipient message " + recipientMessage.getMessageId());
					}
				}
				// No recipient means deleted user(s)
				if (jsonRecipients.length() == 0) {
					JSONObject jsonRecipient = convertRecipient(null);
					jsonRecipients.put(jsonRecipient);
				}
				result.put(JSONConstants.NB_RECIPIENTS, recipientsMessages.size());
			}
		}
		result.put(JSONConstants.RECIPIENTS, jsonRecipients);

		return result;
	}

	public static JSONObject convertRecipient(User user) {
		JSONObject jsonUser = new JSONObject();

		jsonUser.put(JSONConstants.TEXT, user != null ? user.getFullName() : "Utilisateur supprim\u00e9");
		jsonUser.put(JSONConstants.USER_ID, user != null ? user.getUserId() : 0);
		jsonUser.put(JSONConstants.TYPE, ContactConstants.RECIPIENT_TYPE_USER);

		return jsonUser;
	}

	private static boolean isDraftMessage (Message message) {
		try {
			MessageFolder sourceFolder = MessageFolderLocalServiceUtil.getMessageFolder(message.getFolderId());

			return sourceFolder.getType() == MessagingConstants.DRAFT_FOLDER_TYPE;
		} catch (Exception e) {
			return false;
		}
	}

	private static final String DEFAULT_SUBJECT = "(Pas d'objet)";

	private static final String DEFAULT_CONTENT = "Ce message est vide";
}