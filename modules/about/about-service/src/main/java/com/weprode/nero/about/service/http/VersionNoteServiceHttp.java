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

package com.weprode.nero.about.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.about.service.VersionNoteServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>VersionNoteServiceUtil</code> service
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
 * @see VersionNoteServiceSoap
 * @generated
 */
public class VersionNoteServiceHttp {

	public static org.json.JSONObject getVersionNotes(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				VersionNoteServiceUtil.class, "getVersionNotes",
				_getVersionNotesParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey);

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

	public static org.json.JSONObject getVersionNoteContent(
		HttpPrincipal httpPrincipal, Long versionNoteId) {

		try {
			MethodKey methodKey = new MethodKey(
				VersionNoteServiceUtil.class, "getVersionNoteContent",
				_getVersionNoteContentParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, versionNoteId);

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

	public static org.json.JSONObject createVersionNote(
		HttpPrincipal httpPrincipal, String title, String htmlContent) {

		try {
			MethodKey methodKey = new MethodKey(
				VersionNoteServiceUtil.class, "createVersionNote",
				_createVersionNoteParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, title, htmlContent);

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

	public static org.json.JSONObject updateVersionNote(
		HttpPrincipal httpPrincipal, long versionNoteId, String title,
		String htmlContent) {

		try {
			MethodKey methodKey = new MethodKey(
				VersionNoteServiceUtil.class, "updateVersionNote",
				_updateVersionNoteParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, versionNoteId, title, htmlContent);

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

	public static org.json.JSONObject deleteVersionNote(
		HttpPrincipal httpPrincipal, long versionNoteId) {

		try {
			MethodKey methodKey = new MethodKey(
				VersionNoteServiceUtil.class, "deleteVersionNote",
				_deleteVersionNoteParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, versionNoteId);

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
		VersionNoteServiceHttp.class);

	private static final Class<?>[] _getVersionNotesParameterTypes0 =
		new Class[] {};
	private static final Class<?>[] _getVersionNoteContentParameterTypes1 =
		new Class[] {Long.class};
	private static final Class<?>[] _createVersionNoteParameterTypes2 =
		new Class[] {String.class, String.class};
	private static final Class<?>[] _updateVersionNoteParameterTypes3 =
		new Class[] {long.class, String.class, String.class};
	private static final Class<?>[] _deleteVersionNoteParameterTypes4 =
		new Class[] {long.class};

}