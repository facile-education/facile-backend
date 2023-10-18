package com.weprode.nero.commons.wrapper;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.mail.kernel.template.MailTemplate;
import com.liferay.mail.kernel.template.MailTemplateContext;
import com.liferay.mail.kernel.template.MailTemplateContextBuilder;
import com.liferay.mail.kernel.template.MailTemplateFactoryUtil;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceWrapper;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.weprode.nero.document.constants.DocumentConstants;
import com.weprode.nero.group.service.GroupMembershipLocalServiceUtil;
import com.weprode.nero.messaging.model.Message;
import com.weprode.nero.messaging.model.MessageFolder;
import com.weprode.nero.messaging.service.MessageFolderLocalServiceUtil;
import com.weprode.nero.messaging.service.MessageLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.preference.service.NotifyConfigLocalServiceUtil;
import com.weprode.nero.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.nero.user.model.UserContact;
import com.weprode.nero.user.service.LDAPMappingLocalServiceUtil;
import com.weprode.nero.user.service.UserContactLocalServiceUtil;
import com.weprode.nero.user.service.UserRelationshipLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Component(
		immediate = true,
		service = ServiceWrapper.class
)
public class UserLocalServiceOverride extends UserLocalServiceWrapper {

	private static final Log logger = LogFactoryUtil.getLog(UserLocalServiceOverride.class);

	public UserLocalServiceOverride() {
		super(null);
	}

	@Override
	public User addUser(
			long creatorUserId, long companyId, boolean autoPassword,
			String password1, String password2, boolean autoScreenName,
			String screenName, String emailAddress, Locale locale,
			String firstName, String middleName, String lastName,
			long prefixId, long suffixId, boolean male,
			int birthdayMonth, int birthdayDay, int birthdayYear,
			String jobTitle, int type, long[] groupIds, long[] organizationIds,
			long[] roleIds, long[] userGroupIds, boolean sendEmail,
			ServiceContext serviceContext)
			throws PortalException {

		if (organizationIds == null) {
			// Add user to root org
			Organization rootOrg = OrgUtilsLocalServiceUtil.getOrCreateRootOrg(companyId);

			logger.debug("Add new user " + emailAddress + " to main organization (id=" + rootOrg.getOrganizationId() + ")");
			organizationIds = new long[] { rootOrg.getOrganizationId() };
		}

		User user = super.addUser(creatorUserId, companyId, autoPassword,
				password1, password2, autoScreenName,
				screenName, emailAddress, locale,
				firstName, middleName, lastName,
				prefixId, suffixId, male,
				birthdayMonth, birthdayDay, birthdayYear,
				jobTitle, type, groupIds, organizationIds, roleIds, userGroupIds, sendEmail,
				serviceContext);

		// Create default user properties
		UserPropertiesLocalServiceUtil.addUserProperties(user.getUserId());

		// Create default user notifications
		NotifyConfigLocalServiceUtil.getOrCreateNotifyConfig(user.getUserId());

		return user;
	}

	@Override
	public boolean sendPassword(
			long companyId, String emailAddress, String fromName,
			String fromAddress, String subject, String body,
			ServiceContext serviceContext)
			throws PortalException {

		logger.info("Send password to email " + emailAddress);
		emailAddress = StringUtil.toLowerCase(StringUtil.trim(emailAddress));

		if (Validator.isNull(emailAddress)) {
			throw new UserEmailAddressException.MustNotBeNull();
		}

		User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, emailAddress);

		PasswordPolicy passwordPolicy = user.getPasswordPolicy();

		Date expirationDate = null;

		if ((passwordPolicy != null) &&	(passwordPolicy.getResetTicketMaxAge() > 0)) {
			expirationDate = new Date(
					System.currentTimeMillis() + (passwordPolicy.getResetTicketMaxAge() * 1000));
		}

		Ticket ticket = TicketLocalServiceUtil.addDistinctTicket(
				companyId, User.class.getName(), user.getUserId(),
				TicketConstants.TYPE_PASSWORD, null, expirationDate,
				serviceContext);

		String passwordResetURL = StringBundler.concat(
				serviceContext.getPortalURL(), "/password-change?p_l_id=", serviceContext.getPlid(),
				"&ticketKey=", ticket.getKey());

		logger.info("Sending password recovery url " + passwordResetURL);

		sendPasswordNotification(
				user, companyId, null, passwordResetURL, fromName, fromAddress,
				subject, body, serviceContext);

		return false;
	}

	protected void sendPasswordNotification(
			User user, long companyId, String newPassword, String passwordResetURL,
			String fromName, String fromAddress, String subject, String body,
			ServiceContext serviceContext) {

		if (Validator.isNull(fromName)) {
			fromName = PrefsPropsUtil.getString(companyId, PropsKeys.ADMIN_EMAIL_FROM_NAME);
		}

		if (Validator.isNull(fromAddress)) {
			fromAddress = PrefsPropsUtil.getString(companyId, PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
		}

		String toName = user.getFullName();
		String toAddress = user.getEmailAddress();

		MailTemplateContextBuilder mailTemplateContextBuilder =
				MailTemplateFactoryUtil.createMailTemplateContextBuilder();

		mailTemplateContextBuilder.put("[$FROM_ADDRESS$]", fromAddress);
		mailTemplateContextBuilder.put("[$FROM_NAME$]", HtmlUtil.escape(fromName));
		mailTemplateContextBuilder.put("[$PASSWORD_RESET_URL$]", passwordResetURL);
		mailTemplateContextBuilder.put("[$PORTAL_URL$]", serviceContext.getPortalURL());
		mailTemplateContextBuilder.put("[$REMOTE_ADDRESS$]", serviceContext.getRemoteAddr());
		mailTemplateContextBuilder.put("[$REMOTE_HOST$]", HtmlUtil.escape(serviceContext.getRemoteHost()));
		mailTemplateContextBuilder.put("[$TO_ADDRESS$]", toAddress);
		mailTemplateContextBuilder.put("[$TO_NAME$]", HtmlUtil.escape(toName));
		mailTemplateContextBuilder.put("[$USER_ID$]", String.valueOf(user.getUserId()));
		mailTemplateContextBuilder.put("[$USER_SCREENNAME$]", HtmlUtil.escape(user.getScreenName()));

		MailTemplateContext mailTemplateContext = mailTemplateContextBuilder.build();

		try {
			sendNotificationEmail(
					fromAddress, fromName, toAddress, user, subject,
					body, mailTemplateContext);
		}
		catch (PortalException portalException) {
			ReflectionUtil.throwException(portalException);
		}
	}

	@BeanReference(type = MailService.class)
	protected MailService mailService;

	private void sendNotificationEmail(
			String fromAddress, String fromName, String toAddress, User toUser,
			String subject, String body,
			MailTemplateContext mailTemplateContext)
			throws PortalException {

		try {
			MailTemplate subjectTemplate =
					MailTemplateFactoryUtil.createMailTemplate(subject, false);

			MailTemplate bodyTemplate =
					MailTemplateFactoryUtil.createMailTemplate(body, true);

			MailMessage mailMessage = new MailMessage(
					new InternetAddress(fromAddress, fromName),
					new InternetAddress(toAddress, toUser.getFullName()),
					subjectTemplate.renderAsString(
							toUser.getLocale(), mailTemplateContext),
					bodyTemplate.renderAsString(
							toUser.getLocale(), mailTemplateContext),
					true);

			MailServiceUtil.sendEmail(mailMessage);

		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}


	@Override
	public User deleteUser(long userId) throws PortalException {

		// Remove user related data
		deleteUserData(userId);

		return super.deleteUser(userId);
	}

	@Override
	public User deleteUser(User user) throws PortalException {

		// Remove user related content
		deleteUserData(user.getUserId());

		return super.deleteUser(user);
	}

	private void deleteUserData (long userId) {
		logger.info("Cleaning up user id "+userId+" data in DB ...");

		initUserCounts();

		// Cleaning messaging
		cleanupMessaging(userId);

		// Cleaning DLFileEntries
		cleanupDLFileEntries(userId);
		//cleanupDLFolders(userId);

		// Cleanup notify config
		try {
			logger.debug("Clean up user notify config for userId " + userId);
			NotifyConfigLocalServiceUtil.deleteNotifyConfigByUser(userId);
		} catch (Exception e) {
			logger.error("Could not delete the notify configuration for user id "+userId);
		}

		try {
			logger.debug("Clean up user properties for userId " + userId);
			UserPropertiesLocalServiceUtil.deleteUserProperties(userId);
		} catch (Exception e) {
			logger.error("Could not delete user properties for userid " + userId);
		}

		// Cleanup user relationships
		try {
			logger.debug("Clean up user parent relationships ...");
			UserRelationshipLocalServiceUtil.deleteParent(userId);
			userNbRelationshipsSuccess++;
		} catch (Exception e) {
			userNbRelationshipsError++;
			logger.error("Could not delete relationship with user id " + userId);
		}
		try {
			logger.debug("Clean up user children relationships ...");
			UserRelationshipLocalServiceUtil.deleteChild(userId);
			userNbRelationshipsSuccess++;
		} catch (Exception e) {
			userNbRelationshipsError++;
			logger.error("Could not delete relationship with user id " + userId);
		}

		try {
			logger.debug("Clean up ldap mapping for userId " + userId);
			LDAPMappingLocalServiceUtil.deleteLDAPMapping(userId);
		} catch (Exception e) {
			logger.error("Could not delete ldap mapping for userId " + userId);
		}

		try {
			logger.debug("Clean up user memberships for userId " + userId);
			GroupMembershipLocalServiceUtil.removeUserMemberships(userId);
		} catch (Exception e) {
			logger.error("Could not delete user memberships for userId " + userId, e);
		}

		// Cleanup user contact
		try {
			logger.info("Clean up user contact infos ...");
			UserContact userContact = UserContactLocalServiceUtil.getUserContactByUserId(userId);
			UserContactLocalServiceUtil.deleteUserContact(userContact);
		} catch (Exception e) {
			logger.error("Failed to delete user contact infos");
		}

		printUserReport(userId);
	}

	private void cleanupMessaging(long userId) {
		logger.info("Cleaning internal messages for user "+userId+" ...");

		User user = null;
		try {
			user = UserLocalServiceUtil.getUser(userId);
		} catch (Exception e1) {
			logger.error("Could not find user with userId = "+userId);
		}

		if (user == null || user.getGroup() == null) {
			logger.error("UserId "+userId+" has no group associated.");
			return;
		}

		try {
			// Loop over user's folders
			for (MessageFolder folder : MessageFolderLocalServiceUtil.getAllUserFolders(userId)) {
				logger.info("Cleaning folder "+folder.getFolderName()+", id="+folder.getFolderId());

				// Delete messages + internal receivers + attached files + attached files folders
				try {
					List<Message> messageList = MessageLocalServiceUtil.getMessagesByFolder(folder.getFolderId(), userId);
					if (messageList != null) {

						for (Message message : messageList) {

							try {
								MessageLocalServiceUtil.deleteMessageAndDependencies(message.getMessageId());
								logger.debug("Deleted message id "+message.getMessageId()+ "(subject="+message.getMessageSubject()+")");
								userNbMessagesSuccess++;
							} catch (Exception e) {
								userNbMessagesError++;
							}
						}
					}
				} catch (Exception e) {
					logger.info("Error : could not get all messages by folderId "+folder.getFolderId());
				}
			}
		} catch (SystemException e) {
			logger.error("Error cleaning internal messages for user " + userId, e);
		}
	}

	private static void cleanupDLFolders(long userId) {
		logger.info("Cleaning up DLFolders for user id "+userId+" ...");

		try {
			User user = UserLocalServiceUtil.getUser(userId);
			List<DLFolder> dlFolderList = DLFolderLocalServiceUtil.getFolders(user.getGroupId(), 0, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			for (DLFolder dlFolder : dlFolderList) {
				if (dlFolder.getName().equals(DocumentConstants.SCHOOL_BAG_FOLDER_NAME)) {
					logger.info("Deleting folder "+dlFolder.getName());
					DLFolderLocalServiceUtil.deleteFolder(dlFolder.getFolderId());
				}
			}
		} catch (Exception e) {
			logger.error("Unable to get user's DLFolders", e);
		}
	}

	private void cleanupDLFileEntries(long userId) {
		logger.info("Cleaning up DLFileEntries for user id "+userId+" ...");

		User user;

		// Get DL entries for the user
		List<DLFileEntry> fileList = new ArrayList<>();
		try {
			user = UserLocalServiceUtil.getUser(userId);
			fileList = DLFileEntryLocalServiceUtil.getGroupFileEntries(user.getGroup().getGroupId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		} catch (Exception e) {
			logger.error("Could not get group file entries for userId " + userId, e);
		}

		if (fileList == null) {
			logger.error("Could not get group file entries for userId " + userId);
			return;
		}

		// Loop over DL files
		for (DLFileEntry dlFile : fileList) {
			logger.info("Deleting DLFileEntry "+dlFile.getTitle()+" (id="+dlFile.getFileEntryId()+", folderId="+dlFile.getFolderId()+")");

			// Delete the file itself in both DB and File System
			try {
				DLFileEntryLocalServiceUtil.deleteDLFileEntry(dlFile);
				logger.info("Deleted file entry "+dlFile.getFileEntryId()+" in DB and FS OK");
			} catch (SystemException e) {
				logger.error("Could not delete dlFileEntry "+dlFile.getFileEntryId(), e);
				userNbDLFilesError++;
			}
		}
	}

	/**
	 * Prints a report for a given user
	 */
	private void printUserReport(long userId) {
		StringBuilder str = new StringBuilder();

		str.append("============================\n");
		str.append("USER CLEANUP REPORT\n\n");
		str.append("Cleanup for user ").append(userId).append("\n");
		str.append(" - ").append(userNbMessagesSuccess).append(" internal messages deleted\n");
		str.append(" - ").append(userNbDLFilesSuccess).append(" DL files marked for deletion\n");
		str.append(" - ").append(userNbEventsSuccess).append(" CDT events deleted\n");
		str.append(" - ").append(userNbRelationshipsSuccess).append(" relationships deleted\n");

		str.append("Errors:");
		if (userNbMessagesError > 0) {
			str.append(" - ").append(userNbMessagesError).append(" internal messages not deleted\n");
		}
		if (userNbDLFilesError > 0) {
			str.append(" - ").append(userNbDLFilesError).append(" DL files not deleted\n");
		}
		if (userNbEventsError > 0) {
			str.append(" - ").append(userNbEventsError).append(" CDT events not deleted\n");
		}
		if (userNbRelationshipsError > 0) {
			str.append(" - ").append(userNbRelationshipsError).append(" relationships not deleted\n");
		}
		str.append("\n");
		str.append("============================\n");

		String userReport = str.toString();
		logger.info("\n"+userReport);
	}

	/**
	 * Initiates all counts related to a given user
	 */
	private void initUserCounts() {
		userNbMessagesSuccess = 0;
		userNbMessagesError = 0;
		userNbDLFilesSuccess = 0;
		userNbDLFilesError = 0;
		userNbEventsSuccess = 0;
		userNbEventsError = 0;
		userNbRelationshipsSuccess = 0;
		userNbRelationshipsError = 0;
	}

	// Messaging
	private int userNbMessagesSuccess;
	private int userNbMessagesError;

	// DL Files
	private int userNbDLFilesSuccess;
	private int userNbDLFilesError;

	// CDT
	private int userNbEventsSuccess;
	private int userNbEventsError;

	// Relationships
	private int userNbRelationshipsSuccess;
	private int userNbRelationshipsError;

	// Add the following method to the bottom of your service wrapper
	// so it can find the appropriate service it's overriding on deployment.
	@Reference(unbind = "-")
	private void serviceSetter(UserLocalService userLocalService) {
		setWrappedService(userLocalService);
	}

}
