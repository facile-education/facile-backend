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

package com.weprode.nero.messaging.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.weprode.nero.messaging.service.MessageServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>MessageServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.weprode.nero.messaging.model.MessageSoap</code>. If the method in the
 * service utility returns a
 * <code>com.weprode.nero.messaging.model.Message</code>, that is translated to a
 * <code>com.weprode.nero.messaging.model.MessageSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class MessageServiceSoap {

	/**
	 * Get user messages
	 */
	public static org.json.JSONObject getThreads(
			long folderId, String fromDate, int nbDisplayed, boolean unreadOnly)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = MessageServiceUtil.getThreads(
				folderId, fromDate, nbDisplayed, unreadOnly);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getMessageThread(long messageId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				MessageServiceUtil.getMessageThread(messageId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getThreadMessages(
			long threadId, long folderId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				MessageServiceUtil.getThreadMessages(threadId, folderId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getNbMessages(long folderId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = MessageServiceUtil.getNbMessages(
				folderId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	/**
	 * Get the full recipients list for a message
	 */
	public static org.json.JSONObject getMessageRecipients(long messageId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				MessageServiceUtil.getMessageRecipients(messageId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	/**
	 * Return useful informations for message creation
	 */
	public static org.json.JSONObject getMessageAnswerForwardInfos(
			long messageId, boolean isReply, boolean isReplyAll,
			boolean isDraft, boolean isForward)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				MessageServiceUtil.getMessageAnswerForwardInfos(
					messageId, isReply, isReplyAll, isDraft, isForward);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	/**
	 * Change message read status
	 */
	public static org.json.JSONObject setMessageReadStatus(
			String messageIds, boolean isRead)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				MessageServiceUtil.setMessageReadStatus(messageIds, isRead);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject sendMessage(
			String recipients, String subject, String content,
			String attachedFiles, long draftMessageId, long originMessageId,
			boolean isReply, boolean isForward, boolean isSupport)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = MessageServiceUtil.sendMessage(
				recipients, subject, content, attachedFiles, draftMessageId,
				originMessageId, isReply, isForward, isSupport);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject saveDraft(
			String recipients, String subject, String content,
			String attachedFiles, long draftMessageId, boolean isSupport)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = MessageServiceUtil.saveDraft(
				recipients, subject, content, attachedFiles, draftMessageId,
				isSupport);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	/**
	 * Move messages to folder
	 */
	public static org.json.JSONObject moveMessages(
			long folderId, String messageIds)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = MessageServiceUtil.moveMessages(
				folderId, messageIds);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	/**
	 * Delete messages
	 */
	public static org.json.JSONObject deleteMessages(String messageIds)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = MessageServiceUtil.deleteMessages(
				messageIds);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getUsersCompletion(String query)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				MessageServiceUtil.getUsersCompletion(query);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject sendAssistanceMessage(
			long applicationId, String content, boolean isSuggestion,
			String attachFiles)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				MessageServiceUtil.sendAssistanceMessage(
					applicationId, content, isSuggestion, attachFiles);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MessageServiceSoap.class);

}