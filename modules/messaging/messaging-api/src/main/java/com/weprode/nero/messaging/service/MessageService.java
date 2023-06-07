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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for Message. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MessageServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface MessageService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.nero.messaging.service.impl.MessageServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the message remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link MessageServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Delete messages
	 */
	@JSONWebService(method = "POST", value = "delete-messages")
	public JSONObject deleteMessages(String messageIds);

	/**
	 * Return useful informations for message creation
	 */
	@JSONWebService(method = "GET", value = "get-message-answer-forward-infos")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getMessageAnswerForwardInfos(
		long messageId, boolean isReply, boolean isReplyAll, boolean isDraft,
		boolean isForward);

	/**
	 * Get the full recipients list for a message
	 */
	@JSONWebService(method = "GET", value = "get-message-recipients")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getMessageRecipients(long messageId);

	@JSONWebService(method = "GET", value = "get-message-thread")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getMessageThread(long messageId);

	@JSONWebService(method = "GET", value = "get-nb-messages")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getNbMessages(long folderId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "GET", value = "get-thread-messages")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getThreadMessages(long threadId, long folderId);

	/**
	 * Get user messages
	 */
	@JSONWebService(method = "GET", value = "get-threads")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getThreads(
		long folderId, String fromDate, int nbDisplayed, boolean unreadOnly);

	@JSONWebService(method = "GET", value = "get-users-completion")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getUsersCompletion(String query);

	/**
	 * Move messages to folder
	 */
	@JSONWebService(method = "POST", value = "move-messages")
	public JSONObject moveMessages(long folderId, String messageIds);

	@JSONWebService(method = "POST", value = "save-draft")
	public JSONObject saveDraft(
		String recipients, String subject, String content, String attachedFiles,
		long draftMessageId, boolean isSupport);

	@JSONWebService(method = "POST", value = "send-assistance-message")
	public JSONObject sendAssistanceMessage(
		long applicationId, String content, boolean isSuggestion,
		String attachFiles);

	@JSONWebService(method = "POST", value = "send-message")
	public JSONObject sendMessage(
		String recipients, String subject, String content, String attachedFiles,
		long draftMessageId, long originMessageId, boolean isReply,
		boolean isForward, boolean isSupport);

	/**
	 * Change message read status
	 */
	@JSONWebService(method = "POST", value = "set-message-read-status")
	public JSONObject setMessageReadStatus(String messageIds, boolean isRead);

}