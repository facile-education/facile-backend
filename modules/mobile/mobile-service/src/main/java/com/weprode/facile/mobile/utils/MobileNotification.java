/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.mobile.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.facile.mobile.constants.MobileConstants;
import com.weprode.facile.mobile.model.MobileDevice;
import com.weprode.facile.mobile.service.MobileDeviceLocalServiceUtil;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MobileNotification {

	private MobileNotification() {
		throw new IllegalStateException("Utility class");
	}

	private static final Log logger = LogFactoryUtil.getLog(MobileNotification.class);

	// TODO use these constants in future menu service
	// These path must map the ones defined in front code
	public static final String MESSAGING_PATH = "messagerie";
	public static final String HOMEWORK_PATH = "cours-et-devoirs";
	public static final String DASHBOARD_PATH = "tableau-de-bord";


	public static void sendMobileNotification(long recipientId, String title, String subtitle, String message, String service, long paramId) throws PortalException, SystemException {

		List<MobileDevice> userDeviceList = MobileDeviceLocalServiceUtil.getUserMobileDevices(recipientId);

		if (userDeviceList != null && !userDeviceList.isEmpty()) {

			// Build service URL
			User recipient = UserLocalServiceUtil.getUser(recipientId);
			String redirect = "";
			switch (service) {
				case MobileConstants.MESSAGING_TYPE:
					redirect += "%2F" + MESSAGING_PATH + "%2F" + paramId;
					break;
				case MobileConstants.HOMEWORK_TYPE:
					// Not used for now
					redirect += "%2F" + HOMEWORK_PATH + "%2F" + paramId;
					break;
				case MobileConstants.EVENT_TYPE:
				case MobileConstants.ANNOUNCEMENT_TYPE:
					redirect += "%2F" + DASHBOARD_PATH + "%2F" + paramId;
					break;
				default:
					redirect += "%2F" + DASHBOARD_PATH;
			}
			logger.info("Send mobile notification with redirect url = "+redirect);

			for (MobileDevice device : userDeviceList) {
				try {
					// Cannot use subtitle on Android devices
					String notificationTitle = title + " : " + subtitle;

					logger.info("Sending push notification to " + recipient.getFullName() + "(" + recipientId + "). DeviceId is " + device.getMobileDeviceId()+", redirect="+redirect);

					String escapedTitle = formatHtml(notificationTitle, 70);
					String escapedMessage = formatHtml(message, 200);

					JSONObject response = FCMNotification.pushFCMNotification(device.getManufacturerDeviceId(), escapedTitle, "", escapedMessage, redirect);
					String results = response.getJSONArray("results").toString();
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

	public static void sendMobileNotificationGroup(List<Long> groupIds, long senderId, String title, String subtitle, String message, String service, long paramId)
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
		List<User> processedUsers = new ArrayList<>();
		logger.info("Starting to send push notification to " + groupMembers.size() + " users.");
		for (User user : groupMembers) {
			try {
				// Do not send to sender
				if (user.getUserId() == senderId || processedUsers.contains(user)) {
					continue;
				}
				MobileNotification.sendMobileNotification(user.getUserId(), title, subtitle, message, service, paramId);
				processedUsers.add(user);
			} catch (Exception e) {
				logger.error("Cannot push group android notification for user "+user.getFullName()+" (id "+user.getUserId()+")", e);
			}
		}
	}

	private static String formatHtml(String source, int maxSize) {
		Document jsoupDoc = Jsoup.parse(source);
		Document.OutputSettings outputSettings = new Document.OutputSettings();
		outputSettings.prettyPrint(false);
		jsoupDoc.outputSettings(outputSettings);

		// Select all <p> tag except the first one
		Elements paragraphs = jsoupDoc.select("p:not(:first-child)");
		for (Element paragraph : paragraphs) {
			paragraph.before("\\n");
		}

		String str = jsoupDoc.html().replaceAll("\\\\n", "\n");
		String formattedString = Jsoup.clean(str, "", Safelist.none(), outputSettings).replaceAll("&nbsp;", "");
		if (formattedString.length() > maxSize) {
			formattedString = formattedString.substring(0, maxSize) + "...";
		}
		return formattedString;
	}

}