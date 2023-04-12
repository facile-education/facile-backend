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

package com.weprode.nero.document.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.document.service.VersionServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>VersionServiceUtil</code> service
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
 * @see VersionServiceSoap
 * @generated
 */
public class VersionServiceHttp {

	public static com.liferay.portal.kernel.json.JSONObject getFileVersions(
		HttpPrincipal httpPrincipal, long fileId) {

		try {
			MethodKey methodKey = new MethodKey(
				VersionServiceUtil.class, "getFileVersions",
				_getFileVersionsParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, fileId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject deleteVersion(
		HttpPrincipal httpPrincipal, long fileEntryId, String version) {

		try {
			MethodKey methodKey = new MethodKey(
				VersionServiceUtil.class, "deleteVersion",
				_deleteVersionParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fileEntryId, version);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject restoreVersion(
		HttpPrincipal httpPrincipal, long fileVersionId) {

		try {
			MethodKey methodKey = new MethodKey(
				VersionServiceUtil.class, "restoreVersion",
				_restoreVersionParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fileVersionId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject
		saveVersionDescription(
			HttpPrincipal httpPrincipal, long fileVersionId,
			String description) {

		try {
			MethodKey methodKey = new MethodKey(
				VersionServiceUtil.class, "saveVersionDescription",
				_saveVersionDescriptionParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fileVersionId, description);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject createMajorVersion(
		HttpPrincipal httpPrincipal, long fileEntryId) {

		try {
			MethodKey methodKey = new MethodKey(
				VersionServiceUtil.class, "createMajorVersion",
				_createMajorVersionParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fileEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(VersionServiceHttp.class);

	private static final Class<?>[] _getFileVersionsParameterTypes0 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteVersionParameterTypes1 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _restoreVersionParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _saveVersionDescriptionParameterTypes3 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _createMajorVersionParameterTypes4 =
		new Class[] {long.class};

}