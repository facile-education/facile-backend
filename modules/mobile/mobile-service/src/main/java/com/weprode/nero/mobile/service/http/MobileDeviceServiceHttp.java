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

package com.weprode.nero.mobile.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.mobile.service.MobileDeviceServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>MobileDeviceServiceUtil</code> service
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
 * @see MobileDeviceServiceSoap
 * @generated
 */
public class MobileDeviceServiceHttp {

	public static org.json.JSONObject saveUserDevice(
		HttpPrincipal httpPrincipal, String deviceId, long userId) {

		try {
			MethodKey methodKey = new MethodKey(
				MobileDeviceServiceUtil.class, "saveUserDevice",
				_saveUserDeviceParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, deviceId, userId);

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

	public static org.json.JSONObject saveFullUserDevice(
		HttpPrincipal httpPrincipal, String deviceId, long userId, String model,
		String manufacturer, String os, String osVersion, String browserUA) {

		try {
			MethodKey methodKey = new MethodKey(
				MobileDeviceServiceUtil.class, "saveFullUserDevice",
				_saveFullUserDeviceParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, deviceId, userId, model, manufacturer, os, osVersion,
				browserUA);

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

	public static org.json.JSONObject deleteUserDevice(
		HttpPrincipal httpPrincipal, String deviceId) {

		try {
			MethodKey methodKey = new MethodKey(
				MobileDeviceServiceUtil.class, "deleteUserDevice",
				_deleteUserDeviceParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, deviceId);

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
		MobileDeviceServiceHttp.class);

	private static final Class<?>[] _saveUserDeviceParameterTypes0 =
		new Class[] {String.class, long.class};
	private static final Class<?>[] _saveFullUserDeviceParameterTypes1 =
		new Class[] {
			String.class, long.class, String.class, String.class, String.class,
			String.class, String.class
		};
	private static final Class<?>[] _deleteUserDeviceParameterTypes2 =
		new Class[] {String.class};

}