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

package com.weprode.facile.document.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.facile.document.service.FolderUtilsServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>FolderUtilsServiceUtil</code> service
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
public class FolderUtilsServiceHttp {

	public static org.json.JSONObject getBreadcrumb(
		HttpPrincipal httpPrincipal, long folderId) {

		try {
			MethodKey methodKey = new MethodKey(
				FolderUtilsServiceUtil.class, "getBreadcrumb",
				_getBreadcrumbParameterTypes0);

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

	public static org.json.JSONObject createFolder(
			HttpPrincipal httpPrincipal, long targetFolderId, String folderName)
		throws com.liferay.portal.kernel.exception.SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				FolderUtilsServiceUtil.class, "createFolder",
				_createFolderParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, targetFolderId, folderName);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.SystemException) {

					throw (com.liferay.portal.kernel.exception.SystemException)
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

	public static org.json.JSONObject renameFolder(
		HttpPrincipal httpPrincipal, long folderId, String folderName) {

		try {
			MethodKey methodKey = new MethodKey(
				FolderUtilsServiceUtil.class, "renameFolder",
				_renameFolderParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, folderName);

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

	public static org.json.JSONObject downloadFolder(
		HttpPrincipal httpPrincipal, long folderId) {

		try {
			MethodKey methodKey = new MethodKey(
				FolderUtilsServiceUtil.class, "downloadFolder",
				_downloadFolderParameterTypes3);

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

	public static org.json.JSONObject getAllEntities(
		HttpPrincipal httpPrincipal, long folderId, boolean withDetails) {

		try {
			MethodKey methodKey = new MethodKey(
				FolderUtilsServiceUtil.class, "getAllEntities",
				_getAllEntitiesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, withDetails);

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

	public static org.json.JSONObject getImagesEntities(
		HttpPrincipal httpPrincipal, long folderId, boolean withDetails) {

		try {
			MethodKey methodKey = new MethodKey(
				FolderUtilsServiceUtil.class, "getImagesEntities",
				_getImagesEntitiesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, folderId, withDetails);

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
		FolderUtilsServiceHttp.class);

	private static final Class<?>[] _getBreadcrumbParameterTypes0 =
		new Class[] {long.class};
	private static final Class<?>[] _createFolderParameterTypes1 = new Class[] {
		long.class, String.class
	};
	private static final Class<?>[] _renameFolderParameterTypes2 = new Class[] {
		long.class, String.class
	};
	private static final Class<?>[] _downloadFolderParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getAllEntitiesParameterTypes4 =
		new Class[] {long.class, boolean.class};
	private static final Class<?>[] _getImagesEntitiesParameterTypes5 =
		new Class[] {long.class, boolean.class};

}