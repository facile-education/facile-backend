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

package com.weprode.facile.course.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.facile.course.service.SessionContentServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>SessionContentServiceUtil</code> service
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
 * @generated
 */
public class SessionContentServiceHttp {

	public static org.json.JSONObject addSessionContent(
			HttpPrincipal httpPrincipal, long sessionId, String title,
			String blocks, String publicationDate, boolean isDraft)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SessionContentServiceUtil.class, "addSessionContent",
				_addSessionContentParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, sessionId, title, blocks, publicationDate, isDraft);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
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

	public static org.json.JSONObject updateSessionContent(
			HttpPrincipal httpPrincipal, long sessionId, String title,
			String blocks, String publicationDate, boolean isDraft)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				SessionContentServiceUtil.class, "updateSessionContent",
				_updateSessionContentParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, sessionId, title, blocks, publicationDate, isDraft);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
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

	public static org.json.JSONObject deleteSessionContent(
		HttpPrincipal httpPrincipal, long sessionId) {

		try {
			MethodKey methodKey = new MethodKey(
				SessionContentServiceUtil.class, "deleteSessionContent",
				_deleteSessionContentParameterTypes2);

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

	public static org.json.JSONObject getSessionContents(
		HttpPrincipal httpPrincipal, long sessionId) {

		try {
			MethodKey methodKey = new MethodKey(
				SessionContentServiceUtil.class, "getSessionContents",
				_getSessionContentsParameterTypes3);

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

	public static org.json.JSONObject getSessionPreview(
		HttpPrincipal httpPrincipal, long sessionId) {

		try {
			MethodKey methodKey = new MethodKey(
				SessionContentServiceUtil.class, "getSessionPreview",
				_getSessionPreviewParameterTypes4);

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

	private static Log _log = LogFactoryUtil.getLog(
		SessionContentServiceHttp.class);

	private static final Class<?>[] _addSessionContentParameterTypes0 =
		new Class[] {
			long.class, String.class, String.class, String.class, boolean.class
		};
	private static final Class<?>[] _updateSessionContentParameterTypes1 =
		new Class[] {
			long.class, String.class, String.class, String.class, boolean.class
		};
	private static final Class<?>[] _deleteSessionContentParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _getSessionContentsParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getSessionPreviewParameterTypes4 =
		new Class[] {long.class};

}