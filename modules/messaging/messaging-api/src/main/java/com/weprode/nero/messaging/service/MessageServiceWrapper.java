/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.weprode.nero.messaging.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessageService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessageService
 * @generated
 */
public class MessageServiceWrapper
	implements MessageService, ServiceWrapper<MessageService> {

	public MessageServiceWrapper(MessageService messageService) {
		_messageService = messageService;
	}

	/**
	 * Delete messages
	 */
	@Override
	public org.json.JSONObject deleteMessages(String messageIds) {
		return _messageService.deleteMessages(messageIds);
	}

	/**
	 * Return useful informations for message creation
	 */
	@Override
	public org.json.JSONObject getMessageAnswerForwardInfos(
			long messageId, boolean isReply, boolean isReplyAll,
			boolean isDraft, boolean isForward)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return _messageService.getMessageAnswerForwardInfos(
			messageId, isReply, isReplyAll, isDraft, isForward);
	}

	/**
	 * Get the full recipients list for a message
	 */
	@Override
	public org.json.JSONObject getMessageRecipients(long messageId) {
		return _messageService.getMessageRecipients(messageId);
	}

	@Override
	public org.json.JSONObject getMessageThread(long messageId)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return _messageService.getMessageThread(messageId);
	}

	@Override
	public org.json.JSONObject getNbMessages(long folderId)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return _messageService.getNbMessages(folderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _messageService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getThreadMessages(long threadId, long folderId)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return _messageService.getThreadMessages(threadId, folderId);
	}

	/**
	 * Get user messages
	 */
	@Override
	public org.json.JSONObject getThreads(
		long folderId, String fromDate, int nbDisplayed, boolean unreadOnly) {

		return _messageService.getThreads(
			folderId, fromDate, nbDisplayed, unreadOnly);
	}

	@Override
	public org.json.JSONObject getUsersCompletion(String query) {
		return _messageService.getUsersCompletion(query);
	}

	/**
	 * Move messages to folder
	 */
	@Override
	public org.json.JSONObject moveMessages(long folderId, String messageIds) {
		return _messageService.moveMessages(folderId, messageIds);
	}

	@Override
	public org.json.JSONObject saveDraft(
		String recipients, String subject, String content, String attachedFiles,
		long draftMessageId, boolean isSupport) {

		return _messageService.saveDraft(
			recipients, subject, content, attachedFiles, draftMessageId,
			isSupport);
	}

	@Override
	public org.json.JSONObject sendAssistanceMessage(
			long applicationId, String content, boolean isSuggestion,
			String attachFiles)
		throws Exception {

		return _messageService.sendAssistanceMessage(
			applicationId, content, isSuggestion, attachFiles);
	}

	@Override
	public org.json.JSONObject sendMessage(
			String recipients, String subject, String content,
			String attachedFiles, long draftMessageId, long originMessageId,
			boolean isReply, boolean isForward, boolean isSupport)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return _messageService.sendMessage(
			recipients, subject, content, attachedFiles, draftMessageId,
			originMessageId, isReply, isForward, isSupport);
	}

	/**
	 * Change message read status
	 */
	@Override
	public org.json.JSONObject setMessageReadStatus(
		String messageIds, boolean isRead) {

		return _messageService.setMessageReadStatus(messageIds, isRead);
	}

	/**
	 * Test useful method to clean all the user's messaging objects
	 */
	@Override
	public org.json.JSONObject testCleanUserMessaging(String userIds) {
		return _messageService.testCleanUserMessaging(userIds);
	}

	/**
	 * Test useful method to clean all the user's messaging objects
	 */
	@Override
	public org.json.JSONObject testSendMessage(
		long senderId, String recipients, String subject, String content,
		String attachedFiles, long draftMessageId, long originMessageId,
		boolean isReply, boolean isForward, boolean isSupport) {

		return _messageService.testSendMessage(
			senderId, recipients, subject, content, attachedFiles,
			draftMessageId, originMessageId, isReply, isForward, isSupport);
	}

	@Override
	public MessageService getWrappedService() {
		return _messageService;
	}

	@Override
	public void setWrappedService(MessageService messageService) {
		_messageService = messageService;
	}

	private MessageService _messageService;

}