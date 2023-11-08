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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

public class ThreadMobileNotification extends Thread {
	
	private static final Log logger = LogFactoryUtil.getLog(ThreadMobileNotification.class);

	// Simple notification
	private final String title;
	private final String subtitle;
	private final String message;
	private long recipientId = 0;
	private String service = "";
	private long paramId = 0;
	
	// Group notification
	private List<Long> groupIds;
	private long senderId;
	
	public ThreadMobileNotification(long userId, String title, String subtitle, String message, String service, long paramId) {
		this.recipientId = userId;
		this.message = message;
		this.title = title;
		this.subtitle = subtitle;
		this.service = service;
		this.paramId = paramId;
	}

	public ThreadMobileNotification(List<Long> groupIds, long senderId, String title, String subtitle, String message, String service, long paramId) {
		this.groupIds = groupIds;
		this.senderId = senderId;
		this.title = title;
		this.subtitle = subtitle;
		this.message = message;
		this.service = service;
		this.paramId = paramId;
	}

	@Override
	public void run() {

		try {
			if (recipientId == 0) {
				// Group
				MobileNotification.sendMobileNotificationGroup(this.groupIds, this.senderId, this.title, this.subtitle, this.message, this.service, this.paramId);
			}
			else {
				MobileNotification.sendMobileNotification(this.recipientId, this.title, this.subtitle, this.message, this.service, this.paramId);
			}
			
		} catch (Exception e) {
			logger.error("Failed to send send mobile notification", e);
		}
	}
		
}