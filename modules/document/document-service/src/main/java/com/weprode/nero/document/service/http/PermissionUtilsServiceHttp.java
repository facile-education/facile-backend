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

import com.weprode.nero.document.service.PermissionUtilsServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>PermissionUtilsServiceUtil</code> service
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
 * @see PermissionUtilsServiceSoap
 * @generated
 */
public class PermissionUtilsServiceHttp {

	public static org.json.JSONObject getFilePermissionMatrix(
		HttpPrincipal httpPrincipal, long fileEntryId) {

		try {
			MethodKey methodKey = new MethodKey(
				PermissionUtilsServiceUtil.class, "getFilePermissionMatrix",
				_getFilePermissionMatrixParameterTypes0);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject getFolderPermissionMatrix(
		HttpPrincipal httpPrincipal, long folderId) {

		try {
			MethodKey methodKey = new MethodKey(
				PermissionUtilsServiceUtil.class, "getFolderPermissionMatrix",
				_getFolderPermissionMatrixParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId);

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

	public static org.json.JSONObject saveFolderPermissionMatrix(
		HttpPrincipal httpPrincipal, long folderId, String jsonPermissionMatrix,
		boolean isRecursive) {

		try {
			MethodKey methodKey = new MethodKey(
				PermissionUtilsServiceUtil.class, "saveFolderPermissionMatrix",
				_saveFolderPermissionMatrixParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, jsonPermissionMatrix, isRecursive);

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

	public static org.json.JSONObject saveFilePermissionMatrix(
		HttpPrincipal httpPrincipal, long fileEntryId,
		String jsonPermissionMatrix) {

		try {
			MethodKey methodKey = new MethodKey(
				PermissionUtilsServiceUtil.class, "saveFilePermissionMatrix",
				_saveFilePermissionMatrixParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fileEntryId, jsonPermissionMatrix);

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
		PermissionUtilsServiceHttp.class);

	private static final Class<?>[] _getFilePermissionMatrixParameterTypes0 =
		new Class[] {long.class};
	private static final Class<?>[] _getFolderPermissionMatrixParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _saveFolderPermissionMatrixParameterTypes2 =
		new Class[] {long.class, String.class, boolean.class};
	private static final Class<?>[] _saveFilePermissionMatrixParameterTypes3 =
		new Class[] {long.class, String.class};

}