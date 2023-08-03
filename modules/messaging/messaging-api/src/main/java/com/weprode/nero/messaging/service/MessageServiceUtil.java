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

/**
 * Provides the remote service utility for Message. This utility wraps
 * <code>com.weprode.nero.messaging.service.impl.MessageServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MessageService
 * @generated
 */
public class MessageServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.messaging.service.impl.MessageServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Delete messages
	 */
	public static org.json.JSONObject deleteMessages(
		java.lang.String messageIds) {

		return getService().deleteMessages(messageIds);
	}

	/**
	 * Return useful informations for message creation
	 */
	public static org.json.JSONObject getMessageAnswerForwardInfos(
		long messageId, boolean isReply, boolean isReplyAll, boolean isDraft,
		boolean isForward) {

		return getService().getMessageAnswerForwardInfos(
			messageId, isReply, isReplyAll, isDraft, isForward);
	}

	/**
	 * Get the full recipients list for a message
	 */
	public static org.json.JSONObject getMessageRecipients(long messageId) {
		return getService().getMessageRecipients(messageId);
	}

	public static org.json.JSONObject getMessageThread(long messageId) {
		return getService().getMessageThread(messageId);
	}

	public static org.json.JSONObject getNbMessages(long folderId) {
		return getService().getNbMessages(folderId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getThreadMessages(
		long threadId, long folderId) {

		return getService().getThreadMessages(threadId, folderId);
	}

	/**
	 * Get user messages
	 */
	public static org.json.JSONObject getThreads(
		long folderId, java.lang.String fromDate, int nbDisplayed,
		boolean unreadOnly) {

		return getService().getThreads(
			folderId, fromDate, nbDisplayed, unreadOnly);
	}

	public static org.json.JSONObject getUsersCompletion(
		java.lang.String query) {

		return getService().getUsersCompletion(query);
	}

	/**
	 * Move messages to folder
	 */
	public static org.json.JSONObject moveMessages(
		long folderId, java.lang.String messageIds) {

		return getService().moveMessages(folderId, messageIds);
	}

	public static org.json.JSONObject saveDraft(
		java.lang.String recipients, java.lang.String subject,
		java.lang.String content, java.lang.String attachedFiles,
		long draftMessageId, long threadId, boolean isSupport) {

		return getService().saveDraft(
			recipients, subject, content, attachedFiles, draftMessageId,
			threadId, isSupport);
	}

	public static org.json.JSONObject sendAssistanceMessage(
		long applicationId, java.lang.String content, boolean isSuggestion,
		java.lang.String attachFiles) {

		return getService().sendAssistanceMessage(
			applicationId, content, isSuggestion, attachFiles);
	}

	public static org.json.JSONObject sendMessage(
		java.lang.String recipients, java.lang.String subject,
		java.lang.String content, java.lang.String attachedFiles,
		long draftMessageId, long originMessageId, boolean isReply,
		boolean isForward, boolean isSupport) {

		return getService().sendMessage(
			recipients, subject, content, attachedFiles, draftMessageId,
			originMessageId, isReply, isForward, isSupport);
	}

	/**
	 * Change message read status
	 */
	public static org.json.JSONObject setMessageReadStatus(
		java.lang.String messageIds, boolean isRead) {

		return getService().setMessageReadStatus(messageIds, isRead);
	}

	public static MessageService getService() {
		return _service;
	}

	private static volatile MessageService _service;

}