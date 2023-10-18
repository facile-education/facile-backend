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

import com.weprode.nero.document.service.GroupsServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>GroupsServiceUtil</code> service
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
public class GroupsServiceHttp {

	public static org.json.JSONObject getGroupEntities(
		HttpPrincipal httpPrincipal, String nodePath) {

		try {
			MethodKey methodKey = new MethodKey(
				GroupsServiceUtil.class, "getGroupEntities",
				_getGroupEntitiesParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, nodePath);

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

	public static org.json.JSONObject getGroupImages(
		HttpPrincipal httpPrincipal, String nodePath) {

		try {
			MethodKey methodKey = new MethodKey(
				GroupsServiceUtil.class, "getGroupImages",
				_getGroupImagesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, nodePath);

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

	public static org.json.JSONObject getGroupBreadcrumb(
		HttpPrincipal httpPrincipal, String nodePath) {

		try {
			MethodKey methodKey = new MethodKey(
				GroupsServiceUtil.class, "getGroupBreadcrumb",
				_getGroupBreadcrumbParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, nodePath);

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

	public static org.json.JSONObject recordDownloadActivity(
		HttpPrincipal httpPrincipal, long fileEntryId, long versionId) {

		try {
			MethodKey methodKey = new MethodKey(
				GroupsServiceUtil.class, "recordDownloadActivity",
				_recordDownloadActivityParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fileEntryId, versionId);

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

	public static org.json.JSONObject recordViewActivity(
		HttpPrincipal httpPrincipal, long fileEntryId, long versionId) {

		try {
			MethodKey methodKey = new MethodKey(
				GroupsServiceUtil.class, "recordViewActivity",
				_recordViewActivityParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fileEntryId, versionId);

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

	private static Log _log = LogFactoryUtil.getLog(GroupsServiceHttp.class);

	private static final Class<?>[] _getGroupEntitiesParameterTypes0 =
		new Class[] {String.class};
	private static final Class<?>[] _getGroupImagesParameterTypes1 =
		new Class[] {String.class};
	private static final Class<?>[] _getGroupBreadcrumbParameterTypes3 =
		new Class[] {String.class};
	private static final Class<?>[] _recordDownloadActivityParameterTypes4 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _recordViewActivityParameterTypes5 =
		new Class[] {long.class, long.class};

}