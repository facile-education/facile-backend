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

package com.weprode.nero.group.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.group.service.CommunityInfosServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>CommunityInfosServiceUtil</code> service
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
 * @see CommunityInfosServiceSoap
 * @generated
 */
public class CommunityInfosServiceHttp {

	public static com.liferay.portal.kernel.json.JSONObject createCommunity(
		HttpPrincipal httpPrincipal, String groupName, String description,
		boolean isPedagogical, String members, String color) {

		try {
			MethodKey methodKey = new MethodKey(
				CommunityInfosServiceUtil.class, "createCommunity",
				_createCommunityParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupName, description, isPedagogical, members,
				color);

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

	public static com.liferay.portal.kernel.json.JSONObject checkCommunityName(
		HttpPrincipal httpPrincipal, String communityName) {

		try {
			MethodKey methodKey = new MethodKey(
				CommunityInfosServiceUtil.class, "checkCommunityName",
				_checkCommunityNameParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, communityName);

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

	public static com.liferay.portal.kernel.json.JSONObject editCommunity(
		HttpPrincipal httpPrincipal, long groupId, String groupName,
		String description, boolean isPedagogical, String members,
		String color) {

		try {
			MethodKey methodKey = new MethodKey(
				CommunityInfosServiceUtil.class, "editCommunity",
				_editCommunityParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, groupName, description, isPedagogical,
				members, color);

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

	public static com.liferay.portal.kernel.json.JSONObject removeCommunity(
		HttpPrincipal httpPrincipal, long groupId) {

		try {
			MethodKey methodKey = new MethodKey(
				CommunityInfosServiceUtil.class, "removeCommunity",
				_removeCommunityParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

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

	public static com.liferay.portal.kernel.json.JSONObject extendCommunity(
		HttpPrincipal httpPrincipal, long groupId) {

		try {
			MethodKey methodKey = new MethodKey(
				CommunityInfosServiceUtil.class, "extendCommunity",
				_extendCommunityParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

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
		CommunityInfosServiceHttp.class);

	private static final Class<?>[] _createCommunityParameterTypes0 =
		new Class[] {
			String.class, String.class, boolean.class, String.class,
			String.class
		};
	private static final Class<?>[] _checkCommunityNameParameterTypes1 =
		new Class[] {String.class};
	private static final Class<?>[] _editCommunityParameterTypes2 =
		new Class[] {
			long.class, String.class, String.class, boolean.class, String.class,
			String.class
		};
	private static final Class<?>[] _removeCommunityParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _extendCommunityParameterTypes4 =
		new Class[] {long.class};

}