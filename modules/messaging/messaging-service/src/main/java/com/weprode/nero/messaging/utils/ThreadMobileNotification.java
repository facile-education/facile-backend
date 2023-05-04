package com.weprode.nero.messaging.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

public class ThreadMobileNotification extends Thread {
	
	private static Log logger = LogFactoryUtil.getLog(ThreadMobileNotification.class);

	// simple notification
	private String title;
	private String subtitle;
	private String message;
	private String serviceUrl;
	private long recipentId = 0;
	private String type = ""; // "dropbox_file" or "dropbox_folder" or "messaging"
	private long paramId = 0; // fileEntryId or folderId or messageId
	
	// group notification
	private List<Long> groupIds;
	private long senderId;
	
	// Simple notifications

	public ThreadMobileNotification(long userId, String title, String subtitle, String message, String serviceUrl, String type, long paramId) {
		this(userId, title, subtitle, message, serviceUrl);
		this.type = type;
		this.paramId = paramId;
	}
	
	public ThreadMobileNotification(long userId, String title, String subtitle, String message, String serviceUrl) {
		this.recipentId = userId;
		this.message = message;
		this.title = title;
		this.subtitle = subtitle;
		this.serviceUrl = serviceUrl;
	}
	
	public ThreadMobileNotification(long userId, String title, String message, String serviceUrl, String type, long paramId) {
		this(userId, title, "", message, serviceUrl);
		this.type = type;
		this.paramId = paramId;
	}
	
	public ThreadMobileNotification(long userId, String title, String message, String serviceUrl) {
		this(userId, title, "", message, serviceUrl);
	}
	
	public ThreadMobileNotification(long userId, String title, String message) {
		this(userId, title, message, "");
	}
	
	// Group notifications
	
	public ThreadMobileNotification(List<Long> groupIds, long senderId, String title, String subtitle, String message, String serviceUrl, String type, long paramId) {
		this(groupIds, senderId, title, subtitle, message, serviceUrl);
		this.type = type;
		this.paramId = paramId;
	}
	
	public ThreadMobileNotification(List<Long> groupIds, long senderId, String title, String subtitle, String message, String serviceUrl) {
		this.groupIds = groupIds;
		this.senderId = senderId;
		this.title = title;
		this.subtitle = subtitle;
		this.message = message;
		this.serviceUrl = serviceUrl;
	}
	
	public ThreadMobileNotification(List<Long> groupIds, long senderId, String title, String message, String serviceUrl) {
		this(groupIds, senderId, title, "", message, "");
	}
	
	public ThreadMobileNotification(List<Long> groupIds, long senderId, String title, String message) {
		this(groupIds, senderId, title, message, "");
	}
	
	/**************************
	 * MIGRATION MODIFICATION *
	 **************************/
	public void run() {

		try {
			if (recipentId == 0) {
				// Group
				MobileNotification.sendMobileNotificationGroup(this.groupIds, this.senderId, this.title, this.subtitle, this.message, this.serviceUrl, this.type, this.paramId);
			}
			else {
				MobileNotification.sendMobileNotification(this.recipentId, this.title, this.subtitle, this.message, this.serviceUrl, this.type, this.paramId);
			}
			
		} catch (Exception e) {
			logger.error("Failed to send send mobile notification", e);
		}
	}
		
}