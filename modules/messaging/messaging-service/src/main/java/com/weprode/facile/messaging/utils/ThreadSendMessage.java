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

package com.weprode.facile.messaging.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.List;

public class ThreadSendMessage extends Thread{
	
	private static final Log logger = LogFactoryUtil.getLog(ThreadSendMessage.class);

	private final User user;
	private final List<Long> recipientList;
	private final String subject;
	private final String content;
	private final List<Long> attachFileIds;
	private final long draftMessageId;	// Draft
	private final long originMessageId;	// Response
	private final int type;

	public ThreadSendMessage(User user, List<Long> recipientList, String subject, String content, int type, List<Long> attachFileIds, long draftMessageId, long originMessageId) {
		
		this.user= user;
		this.recipientList = recipientList;
		this.subject = subject;
		this.content = content;
		this.type = type;
		this.attachFileIds = attachFileIds;
		this.draftMessageId = draftMessageId;
		this.originMessageId = originMessageId;
	}

	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		try {
			
			if (recipientList != null && !recipientList.isEmpty()) {

				PrincipalThreadLocal.setName(this.user.getUserId());
				PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(this.user);
				PermissionThreadLocal.setPermissionChecker(permissionChecker);

				StringBuilder formattedRecipients = new StringBuilder("");
				for (Long recipientId : recipientList) {
					User recipient = UserLocalServiceUtil.getUser(recipientId);
					formattedRecipients.append(recipient.getFullName()).append(", ");
				}
				logger.info("Start ThreadSendMessage " + this.getId() + " - "
						+ "User " + user.getFullName() + " (userId = "+ user.getUserId() + ") " 
						+ "sends IM with subject '" + subject + "' "
						+ "to " + this.recipientList.size() + " recipients :"
						+ formattedRecipients
						+ "with " + (this.attachFileIds != null ? this.attachFileIds.size() : 0) + " attachments");
				MessageUtil.sendMessage(user, recipientList, subject, content, attachFileIds, draftMessageId, originMessageId, type);

			}
		} catch (Exception e) {
			logger.error("Error in ThreadSendMessage : subject = " + this.subject, e);
		}
		logger.info("End ThreadSendMessage " + this.getId() + " in " + (System.currentTimeMillis()-timer)+ " ms.");
	}
	
}
