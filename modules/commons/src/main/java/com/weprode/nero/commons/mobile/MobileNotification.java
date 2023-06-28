package com.weprode.nero.messaging.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.commons.mobile.FCMNotification;
import com.weprode.nero.preference.model.MobileDevice;
import com.weprode.nero.preference.service.MobileDeviceLocalServiceUtil;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MobileNotification {

	private MobileNotification() {
		throw new IllegalStateException("Utility class");
	}

	private static final Log logger = LogFactoryUtil.getLog(MobileNotification.class);
	
	private static final String IOS_OPERATING_SYSTEM = "iOS";
	public static final String MESSAGING_TYPE = "messaging";

	public static void sendMobileNotification(long recipientId, String title, String subtitle, String message, String service, String type, long paramId) throws PortalException, SystemException {

		List<MobileDevice> userDeviceList = MobileDeviceLocalServiceUtil.getUserMobileDevices(recipientId);

		if (userDeviceList != null && !userDeviceList.isEmpty()) {

			// Build service URL
			User recipient = UserLocalServiceUtil.getUser(recipientId);
			String landingPage = "TODO";
			String redirect = "%2Fuser%2F"  + recipient.getScreenName() + "%2F" + landingPage.replace("/", "") + "%23%2F" + service.replace("/", "");
			if (type.equals(MESSAGING_TYPE)) {
				redirect += "%3FmessageId%3D" + paramId+"%26type=messaging";
			}
			logger.info("Send mobile notification with redirect url = "+redirect);

			for (MobileDevice device : userDeviceList) {
				try {
					// Cannot use subtitle on Android devices
					String notificationTitle = title;
					String notificationSubtitle = subtitle;
					if (!device.getOperatingSystem().equals(IOS_OPERATING_SYSTEM) && !subtitle.isEmpty()) {
						notificationTitle += " : " + subtitle;
						notificationSubtitle = "";
					}

					logger.info("Sending push notification to " + recipient.getFullName() + "(" + recipientId + "). DeviceId is " + device.getMobileDeviceId()+", redirect="+redirect);

					JSONObject response = FCMNotification.pushFCMNotification(device.getManufaturerDeviceId(), notificationTitle, notificationSubtitle, message, redirect);
					String results = response.getString("results");
					if (results.contains("NotRegistered") || results.contains("InvalidRegistration")) {
						logger.info("Received '" + results + "' failure message so remove device " + device.getMobileDeviceId() + " for user id " + device.getUserId());
						MobileDeviceLocalServiceUtil.deleteMobileDevice(device.getMobileDeviceId());
					}
				} catch (Exception e) {
					logger.error("Cannot push android notification for user " + recipientId, e);
				}
			}
		}
	}
		
	public static void sendMobileNotificationGroup(List<Long> groupIds, long senderId, String title, String subtitle, String message, String service, String type, long paramId) 
			throws PortalException, SystemException {

		// Build group members
		List<User> groupMembers = new ArrayList<>();
		for (Long groupId: groupIds) {
			Group group = GroupLocalServiceUtil.getGroup(groupId);
			if (group.isOrganization()) {
				groupMembers.addAll(UserLocalServiceUtil.getOrganizationUsers(group.getClassPK()));
			} else {
				groupMembers.addAll(UserLocalServiceUtil.getGroupUsers(groupId));
			}
		}

		// For unicity
		List<User> processedUsers = new ArrayList<User>();
		logger.info("Starting to send push notification to " + groupMembers.size() + " users.");
		for (User user : groupMembers) {
			try {
				// Do not send to sender
				if (user.getUserId() == senderId || processedUsers.contains(user)) {
					continue;
				}
				MobileNotification.sendMobileNotification(user.getUserId(), title, subtitle, message, service, type, paramId);
				processedUsers.add(user);
			} catch (Exception e) {
				logger.error("Cannot push group android notification for user "+user.getFullName()+" (id "+user.getUserId()+")", e);
			}
		}
	}
}