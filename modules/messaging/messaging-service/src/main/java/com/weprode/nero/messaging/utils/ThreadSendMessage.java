package com.weprode.nero.messaging.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;

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

				logger.info("Start ThreadSendMessage " + this.getId() + " - "
						+ "User " + user.getFullName() + " (userId = "+ user.getUserId() + ") " 
						+ "sends IM with subject '" + subject + "' "
						+ "to " + this.recipientList.size() + " recipients "
						+ "with " + (this.attachFileIds != null ? this.attachFileIds.size() : 0) + " attachments");
				MessageUtil.sendMessage(user, recipientList, subject, content, attachFileIds, draftMessageId, originMessageId, type);

			}
		} catch (Exception e) {
			logger.error("Error in ThreadSendMessage : subject = " + this.subject, e);
		}
		logger.info("End ThreadSendMessage " + this.getId() + " in " + (System.currentTimeMillis()-timer)+ " ms.");
	}
	
}
