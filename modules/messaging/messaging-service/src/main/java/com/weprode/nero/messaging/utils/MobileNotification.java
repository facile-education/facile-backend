package com.weprode.nero.messaging.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

public class MobileNotification {

	private MobileNotification() {
		throw new IllegalStateException("Utility class");
	}

	private static final Log _log = LogFactoryUtil.getLog(MobileNotification.class);
	
	private static final String IOS_OPERATING_SYSTEM = "iOS";
	public static final String DROPBOX_FILE_TYPE = "dropbox_file";
	public static final String DROPBOX_FOLDER_TYPE = "dropbox_folder";
	public static final String MESSAGING_TYPE = "messaging";

	public static void sendMobileNotification(long recipientId, String title, String subtitle, String message, String service, String type, long paramId) throws PortalException, SystemException {
		
//		List<MobileDevice> userDeviceList = MobileDeviceLocalServiceUtil.getUserMobileDevices(recipientId);
//		
//		if (userDeviceList != null && userDeviceList.size() > 0) {
//
//			// Build service URL
//			User recipient = UserLocalServiceUtil.getUser(recipientId);
//			String landingPage = ENTMainUtilsLocalServiceUtil.getLoginDefaultPageLanding(recipient.getCompanyId());
//			String redirect = "%2Fuser%2F"  + recipient.getScreenName() + "%2F" + landingPage.replace("/", "") + "%23%2F" + service.replace("/", "");
//			if (type.equals(DROPBOX_FILE_TYPE) || type.equals(DROPBOX_FOLDER_TYPE)) {
//				redirect += "%3FentryId%3D" + paramId;
//			} else if (type.equals(MESSAGING_TYPE)) {
//				redirect += "%3FmessageId%3D" + paramId+"%26type=messaging";
//			}
//			_log.info("Send mobile notification with redirect url = "+redirect);
//
//			for (MobileDevice device : userDeviceList) {
//				try {
//					// Cannot use subtitle on Android devices
//					String notificationTitle = title;
//					String notificationSubtitle = subtitle;
//					if (!device.getOperatingSystem().equals(IOS_OPERATING_SYSTEM) && !subtitle.isEmpty()) {
//						notificationTitle += " : " + subtitle;
//						notificationSubtitle = new String();
//					}
//					
//					_log.info("Sending push notification to " + recipient.getFullName() + "(" + recipientId + "). DeviceId is " + device.getMobileDeviceId()+", redirect="+redirect);
//					JSONObject response = FCMNotification.pushFCMNotification(recipient.getCompanyId(), device.getManufaturerDeviceId(), notificationTitle, notificationSubtitle, message, redirect);
//					String results = response.getString("results");
//					if (results.contains("NotRegistered") || results.contains("InvalidRegistration")) {
//						_log.info("Received '" + results + "' failure message so remove device " + device.getMobileDeviceId() + " for user id " + device.getUserId());
//						MobileDeviceLocalServiceUtil.deleteMobileDevice(device.getMobileDeviceId());
//					}
//				} catch (Exception e) {
//					_log.error("Cannot push android notification for user " + recipientId, e);
//				}
//			}
//		}
	}
		
	public static void sendMobileNotificationGroup(List<Long> groupIds, long senderId, String title, String subtitle, String message, String service, String type, long paramId) 
			throws PortalException, SystemException {
		
//		// Build group members
//		User sender = UserLocalServiceUtil.getUser(senderId);
//		HashSet<User> users = new HashSet<User>();
//		OrderByComparator odb = new UserLastNameComparator(true);
//		for (Long groupId: groupIds) {
//			Group grp = GroupLocalServiceUtil.getGroup(groupId);
//			if (grp.isOrganization()) {
//				
//				final Organization org = OrganizationLocalServiceUtil.getOrganization(grp.getOrganizationId());
//				users.addAll(UserLocalServiceUtil.getOrganizationUsers(org.getOrganizationId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, odb));
//			}
//			else {
//				final java.util.LinkedHashMap<String, Object> userParams = new java.util.LinkedHashMap<String, Object>();
//				userParams.put("usersGroups", groupId);
//				users.addAll(UserLocalServiceUtil.search(sender.getCompanyId(), "", WorkflowConstants.STATUS_APPROVED, userParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS, odb));
//				
//			}
//		}
//		
//		_log.info("Starting to send push notification to " + users.size() + " users.");
//		for (User user : users) {
//			try {
//				// Do not send to sender
//				if (user.getUserId() == senderId) {
//					continue;
//				}
//				MobileNotification.sendMobileNotification(user.getUserId(), title, subtitle, message, service, type, paramId);
//			} catch (Exception e) {
//				_log.error("Cannot push group android notification for user "+user.getFullName()+" (id "+user.getUserId()+")", e);
//			}
//		}
	}
}