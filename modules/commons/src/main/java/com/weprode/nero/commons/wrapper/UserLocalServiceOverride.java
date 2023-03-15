package com.weprode.nero.commons.wrapper;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;

import com.liferay.portal.kernel.service.*;
import com.weprode.nero.group.service.GroupMembershipLocalServiceUtil;
import com.weprode.nero.organization.service.OrgUtilsLocalServiceUtil;
import com.weprode.nero.preference.service.UserPropertiesLocalServiceUtil;
import com.weprode.nero.user.model.UserContact;
import com.weprode.nero.user.service.LDAPMappingLocalServiceUtil;
import com.weprode.nero.user.service.UserContactLocalServiceUtil;
import com.weprode.nero.user.service.UserRelationshipLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
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
			String jobTitle, long[] groupIds, long[] organizationIds,
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
				jobTitle, groupIds, organizationIds, roleIds, userGroupIds, sendEmail,
				serviceContext);

		// Create default user properties
		UserPropertiesLocalServiceUtil.createUserProperties(user.getUserId());

		// Create default user notifications
		// TODO Preferences
		// NotifyConfigLocalServiceUtil.getOrCreateNotifyConfig(user.getUserId());
		return user;
	}

//	@Override
//	public User addUserWithWorkflow(
//			long creatorUserId, long companyId, boolean autoPassword,
//			String password1, String password2, boolean autoScreenName,
//			String screenName, String emailAddress, Locale locale,
//			String firstName, String middleName, String lastName,
//			long prefixId, long suffixId, boolean male,
//			int birthdayMonth, int birthdayDay, int birthdayYear,
//			String jobTitle, long[] groupIds, long[] organizationIds,
//			long[] roleIds, long[] userGroupIds, boolean sendEmail,
//			ServiceContext serviceContext)
//			throws PortalException {
//
//		if (organizationIds == null) {
//			long cadycoOrgId = OrganizationLocalServiceUtil.getOrganization(companyId, "Cadyco").getOrganizationId();
//
//			_log.info("Add new user " + emailAddress + " to main organization (id=" + cadycoOrgId + ")");
//			organizationIds = new long[] { cadycoOrgId };
//		}
//
//		return super.addUserWithWorkflow(creatorUserId, companyId, autoPassword,
//				password1, password2, autoScreenName,
//				screenName, emailAddress, facebookId,
//				openId, locale, firstName, middleName,
//				lastName, prefixId, suffixId, male,
//				birthdayMonth, birthdayDay, birthdayYear,
//				jobTitle, groupIds, organizationIds, roleIds, userGroupIds, sendEmail,
//				serviceContext);
//	}

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
		// TODO Preferences
		/*try {
			logger.debug("Clean up user notify config for userId " + userId);
			NotifyConfigLocalServiceUtil.deleteNotifyConfigByUser(userId);
		} catch (Exception e) {
			logger.error("Could not delete the notify configuration for user id "+userId);
		}*/

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

	private static void cleanupMessaging(long userId) {
		logger.info("Cleaning internal messages for user "+userId+" ...");

		User user = null;
		try {
			user = UserLocalServiceUtil.getUser(userId);
		} catch (Exception e1) {
			logger.error("Could not find user with userId = "+userId);
		}

		try {
			if (user == null || user.getGroup() == null) {
				logger.error("UserId "+userId+" has no group associated.");
				return;
			}
		} catch (Exception e) {
			logger.error(e);
		}

		// Get SENDING BOX dlfolder
		DLFolder sendingBox = null;
		try {
			// TODO Documents
			// sendingBox = DLFolderLocalServiceUtil.getFolder(user.getGroup().getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, DocumentConstants.SENDING_BOX_FOLDER_NAME);
		} catch (Exception exc) {
			logger.error("Could not get sending box for user "+userId);
			return;
		}
		if (sendingBox == null) {
			logger.error("Could not get sending box for user "+userId);
			return;
		}
		logger.info("Sending box folderid is "+sendingBox.getFolderId());

		// TODO Messaging
		/*List<InternalFolderMessage> folderMessageList;
		try {
			folderMessageList = InternalFolderMessageLocalServiceUtil.getUserFolderMessages(userId);
			if (folderMessageList != null) {

				// Loop over user's folders
				for (InternalFolderMessage folder : folderMessageList) {
					logger.debug("Folder name = "+folder.getFolderName()+", id="+folder.getFolderMessageId());



					// Delete messages + internal receivers + attached files + attached files folders
					try {
						List<InternalMessage> messageList = InternalMessageLocalServiceUtil.getMessagesByFolder(folder.getFolderMessageId(), userId);
						if (messageList != null) {
							for (InternalMessage message : messageList) {

								if (message.getFolderMessageId() == sendingBox.getFolderId()) {
									logger.info("Do not delete message "+message.getMessageId()+" because in user's sending box");
									continue;
								}
								try {
									InternalMessageLocalServiceUtil.deleteMessageAndDependencies(message);
									logger.debug("Deleted message id "+message.getMessageId()+ "(subject="+message.getMessageSubject()+")");
									userNbMessagesSuccess++;
								} catch (Exception e) {
									userNbMessagesError++;
									continue;
								}
							}
						}
					} catch (Exception e) {
						logger.info("Error : could not get all messages by folderId "+folder.getFolderMessageId());
					}
				}
			}
		} catch (SystemException e) {
			logger.error(e);
		}*/
	}

	private static void cleanupDLFolders(long userId) {
		logger.info("Cleaning up DLFolders for user id "+userId+" ...");

		try {
			User user = UserLocalServiceUtil.getUser(userId);
			List<DLFolder> dlFolderList = DLFolderLocalServiceUtil.getFolders(user.getGroupId(), 0, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			for (DLFolder dlFolder : dlFolderList) {
				// TODO Documents
				// if (dlFolder.getName().equals(DocumentConstants.DROP_BOX_FOLDER_NAME) || dlFolder.getName().equals(DocumentConstants.SCHOOL_BAG_FOLDER_NAME)) {
					logger.info("Deleting folder "+dlFolder.getName());
					DLFolderLocalServiceUtil.deleteFolder(dlFolder.getFolderId());
				// }
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
