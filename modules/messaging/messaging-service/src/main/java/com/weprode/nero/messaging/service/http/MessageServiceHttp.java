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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.messaging.service.MessageServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>MessageServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageServiceSoap
 * @generated
 */
public class MessageServiceHttp {

	public static org.json.JSONObject getThreads(
		HttpPrincipal httpPrincipal, long folderId, String fromDate,
		int nbDisplayed, boolean unreadOnly) {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "getThreads",
				_getThreadsParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, fromDate, nbDisplayed, unreadOnly);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject getMessageThread(
			HttpPrincipal httpPrincipal, long messageId)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "getMessageThread",
				_getMessageThreadParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, messageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.security.auth.
							PrincipalException) {

					throw (com.liferay.portal.kernel.security.auth.
						PrincipalException)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject getThreadMessages(
			HttpPrincipal httpPrincipal, long threadId, long folderId)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "getThreadMessages",
				_getThreadMessagesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, threadId, folderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.security.auth.
							PrincipalException) {

					throw (com.liferay.portal.kernel.security.auth.
						PrincipalException)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject getNbMessages(
			HttpPrincipal httpPrincipal, long folderId)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "getNbMessages",
				_getNbMessagesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.security.auth.
							PrincipalException) {

					throw (com.liferay.portal.kernel.security.auth.
						PrincipalException)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject getMessageRecipients(
		HttpPrincipal httpPrincipal, long messageId) {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "getMessageRecipients",
				_getMessageRecipientsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, messageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject getMessageAnswerForwardInfos(
			HttpPrincipal httpPrincipal, long messageId, boolean isReply,
			boolean isReplyAll, boolean isDraft, boolean isForward)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "getMessageAnswerForwardInfos",
				_getMessageAnswerForwardInfosParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, messageId, isReply, isReplyAll, isDraft, isForward);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.security.auth.
							PrincipalException) {

					throw (com.liferay.portal.kernel.security.auth.
						PrincipalException)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject setMessageReadStatus(
		HttpPrincipal httpPrincipal, String messageIds, boolean isRead) {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "setMessageReadStatus",
				_setMessageReadStatusParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, messageIds, isRead);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject sendMessage(
			HttpPrincipal httpPrincipal, String recipients, String subject,
			String content, String attachedFiles, long draftMessageId,
			long originMessageId, boolean isReply, boolean isForward,
			boolean isSupport)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "sendMessage",
				_sendMessageParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, recipients, subject, content, attachedFiles,
				draftMessageId, originMessageId, isReply, isForward, isSupport);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.security.auth.
							PrincipalException) {

					throw (com.liferay.portal.kernel.security.auth.
						PrincipalException)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject saveDraft(
		HttpPrincipal httpPrincipal, String recipients, String subject,
		String content, String attachedFiles, long draftMessageId,
		boolean isSupport) {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "saveDraft",
				_saveDraftParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, recipients, subject, content, attachedFiles,
				draftMessageId, isSupport);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject moveMessages(
		HttpPrincipal httpPrincipal, long folderId, String messageIds) {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "moveMessages",
				_moveMessagesParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, messageIds);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject deleteMessages(
		HttpPrincipal httpPrincipal, String messageIds) {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "deleteMessages",
				_deleteMessagesParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, messageIds);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject getUsersCompletion(
		HttpPrincipal httpPrincipal, String query) {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "getUsersCompletion",
				_getUsersCompletionParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey, query);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject testCleanUserMessaging(
		HttpPrincipal httpPrincipal, String userIds) {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "testCleanUserMessaging",
				_testCleanUserMessagingParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, userIds);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject testSendMessage(
		HttpPrincipal httpPrincipal, long senderId, String recipients,
		String subject, String content, String attachedFiles,
		long draftMessageId, long originMessageId, boolean isReply,
		boolean isForward, boolean isSupport) {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "testSendMessage",
				_testSendMessageParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, senderId, recipients, subject, content,
				attachedFiles, draftMessageId, originMessageId, isReply,
				isForward, isSupport);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject sendAssistanceMessage(
			HttpPrincipal httpPrincipal, long applicationId, String content,
			boolean isSuggestion, String attachFiles)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				MessageServiceUtil.class, "sendAssistanceMessage",
				_sendAssistanceMessageParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, applicationId, content, isSuggestion, attachFiles);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MessageServiceHttp.class);

	private static final Class<?>[] _getThreadsParameterTypes0 = new Class[] {
		long.class, String.class, int.class, boolean.class
	};
	private static final Class<?>[] _getMessageThreadParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _getThreadMessagesParameterTypes2 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _getNbMessagesParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getMessageRecipientsParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[]
		_getMessageAnswerForwardInfosParameterTypes5 = new Class[] {
			long.class, boolean.class, boolean.class, boolean.class,
			boolean.class
		};
	private static final Class<?>[] _setMessageReadStatusParameterTypes6 =
		new Class[] {String.class, boolean.class};
	private static final Class<?>[] _sendMessageParameterTypes7 = new Class[] {
		String.class, String.class, String.class, String.class, long.class,
		long.class, boolean.class, boolean.class, boolean.class
	};
	private static final Class<?>[] _saveDraftParameterTypes8 = new Class[] {
		String.class, String.class, String.class, String.class, long.class,
		boolean.class
	};
	private static final Class<?>[] _moveMessagesParameterTypes9 = new Class[] {
		long.class, String.class
	};
	private static final Class<?>[] _deleteMessagesParameterTypes10 =
		new Class[] {String.class};
	private static final Class<?>[] _getUsersCompletionParameterTypes11 =
		new Class[] {String.class};
	private static final Class<?>[] _testCleanUserMessagingParameterTypes12 =
		new Class[] {String.class};
	private static final Class<?>[] _testSendMessageParameterTypes13 =
		new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			long.class, long.class, boolean.class, boolean.class, boolean.class
		};
	private static final Class<?>[] _sendAssistanceMessageParameterTypes14 =
		new Class[] {long.class, String.class, boolean.class, String.class};

}