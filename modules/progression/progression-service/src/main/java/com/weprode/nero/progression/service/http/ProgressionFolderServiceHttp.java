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

import com.weprode.nero.progression.service.ProgressionFolderServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>ProgressionFolderServiceUtil</code> service
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
 * @see ProgressionFolderServiceSoap
 * @generated
 */
public class ProgressionFolderServiceHttp {

	public static com.liferay.portal.kernel.json.JSONObject getFolderContent(
		HttpPrincipal httpPrincipal, long folderId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionFolderServiceUtil.class, "getFolderContent",
				_getFolderContentParameterTypes0);

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

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject addFolder(
		HttpPrincipal httpPrincipal, long progressionId, long parentFolderId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionFolderServiceUtil.class, "addFolder",
				_addFolderParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, progressionId, parentFolderId);

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

	public static com.liferay.portal.kernel.json.JSONObject updateFolder(
		HttpPrincipal httpPrincipal, long folderId, long parentFolderId,
		String name, int order) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionFolderServiceUtil.class, "updateFolder",
				_updateFolderParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, parentFolderId, name, order);

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

	public static com.liferay.portal.kernel.json.JSONObject deleteFolder(
		HttpPrincipal httpPrincipal, long folderId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionFolderServiceUtil.class, "deleteFolder",
				_deleteFolderParameterTypes3);

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

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ProgressionFolderServiceHttp.class);

	private static final Class<?>[] _getFolderContentParameterTypes0 =
		new Class[] {long.class};
	private static final Class<?>[] _addFolderParameterTypes1 = new Class[] {
		long.class, long.class
	};
	private static final Class<?>[] _updateFolderParameterTypes2 = new Class[] {
		long.class, long.class, String.class, int.class
	};
	private static final Class<?>[] _deleteFolderParameterTypes3 = new Class[] {
		long.class
	};

}