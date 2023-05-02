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

package com.weprode.nero.progression.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.progression.service.ProgressionItemServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>ProgressionItemServiceUtil</code> service
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
 * @see ProgressionItemServiceSoap
 * @generated
 */
public class ProgressionItemServiceHttp {

	public static org.json.JSONObject addItem(
		HttpPrincipal httpPrincipal, long progressionId, long folderId,
		boolean isHomework) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "addItem",
				_addItemParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, progressionId, folderId, isHomework);

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

	public static org.json.JSONObject updateItem(
		HttpPrincipal httpPrincipal, long itemId, long folderId, String name,
		int type, String duration, int order) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "updateItem",
				_updateItemParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, itemId, folderId, name, type, duration, order);

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

	public static org.json.JSONObject deleteItem(
		HttpPrincipal httpPrincipal, long itemId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "deleteItem",
				_deleteItemParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, itemId);

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

	public static org.json.JSONObject addItemContent(
		HttpPrincipal httpPrincipal, long itemId, int contentType,
		String contentName, String contentValue, long fileEntryId,
		boolean isToBeCompleted) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "addItemContent",
				_addItemContentParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, itemId, contentType, contentName, contentValue,
				fileEntryId, isToBeCompleted);

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

	public static org.json.JSONObject addItemContent(
		HttpPrincipal httpPrincipal, long itemId, int contentType,
		String contentName, String fileName, java.io.File file) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "addItemContent",
				_addItemContentParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, itemId, contentType, contentName, fileName, file);

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

	public static org.json.JSONObject updateItemContent(
		HttpPrincipal httpPrincipal, long contentId, String contentName,
		String contentValue, int order) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "updateItemContent",
				_updateItemContentParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contentId, contentName, contentValue, order);

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

	public static org.json.JSONObject deleteItemContent(
		HttpPrincipal httpPrincipal, long contentId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "deleteItemContent",
				_deleteItemContentParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, contentId);

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

	public static org.json.JSONObject getItemContents(
		HttpPrincipal httpPrincipal, long itemId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "getItemContents",
				_getItemContentsParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, itemId);

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

	public static org.json.JSONObject getItemPreview(
		HttpPrincipal httpPrincipal, long itemId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "getItemPreview",
				_getItemPreviewParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, itemId);

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

	public static org.json.JSONObject getSessionSpecificContents(
		HttpPrincipal httpPrincipal, long sessionId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "getSessionSpecificContents",
				_getSessionSpecificContentsParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, sessionId);

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

	public static org.json.JSONObject getHomeworkSpecificContents(
		HttpPrincipal httpPrincipal, long homeworkId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "getHomeworkSpecificContents",
				_getHomeworkSpecificContentsParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, homeworkId);

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

	public static org.json.JSONObject saveSessionSpecificItem(
		HttpPrincipal httpPrincipal, long sessionId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "saveSessionSpecificItem",
				_saveSessionSpecificItemParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, sessionId);

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

	public static org.json.JSONObject saveHomeworkSpecificItem(
		HttpPrincipal httpPrincipal, long homeworkId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionItemServiceUtil.class, "saveHomeworkSpecificItem",
				_saveHomeworkSpecificItemParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, homeworkId);

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

	private static Log _log = LogFactoryUtil.getLog(
		ProgressionItemServiceHttp.class);

	private static final Class<?>[] _addItemParameterTypes0 = new Class[] {
		long.class, long.class, boolean.class
	};
	private static final Class<?>[] _updateItemParameterTypes1 = new Class[] {
		long.class, long.class, String.class, int.class, String.class, int.class
	};
	private static final Class<?>[] _deleteItemParameterTypes2 = new Class[] {
		long.class
	};
	private static final Class<?>[] _addItemContentParameterTypes3 =
		new Class[] {
			long.class, int.class, String.class, String.class, long.class,
			boolean.class
		};
	private static final Class<?>[] _addItemContentParameterTypes4 =
		new Class[] {
			long.class, int.class, String.class, String.class,
			java.io.File.class
		};
	private static final Class<?>[] _updateItemContentParameterTypes5 =
		new Class[] {long.class, String.class, String.class, int.class};
	private static final Class<?>[] _deleteItemContentParameterTypes6 =
		new Class[] {long.class};
	private static final Class<?>[] _getItemContentsParameterTypes7 =
		new Class[] {long.class};
	private static final Class<?>[] _getItemPreviewParameterTypes8 =
		new Class[] {long.class};
	private static final Class<?>[] _getSessionSpecificContentsParameterTypes9 =
		new Class[] {long.class};
	private static final Class<?>[]
		_getHomeworkSpecificContentsParameterTypes10 = new Class[] {long.class};
	private static final Class<?>[] _saveSessionSpecificItemParameterTypes11 =
		new Class[] {long.class};
	private static final Class<?>[] _saveHomeworkSpecificItemParameterTypes12 =
		new Class[] {long.class};

}