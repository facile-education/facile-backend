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

package com.weprode.nero.preference.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.preference.service.UserPropertiesServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>UserPropertiesServiceUtil</code> service
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
 * @see UserPropertiesServiceSoap
 * @generated
 */
public class UserPropertiesServiceHttp {

	public static org.json.JSONObject updateSideMenuState(
		HttpPrincipal httpPrincipal, boolean isExpanded) {

		try {
			MethodKey methodKey = new MethodKey(
				UserPropertiesServiceUtil.class, "updateSideMenuState",
				_updateSideMenuStateParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, isExpanded);

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

	public static org.json.JSONObject updateUserPicture(
		HttpPrincipal httpPrincipal, java.io.File picture) {

		try {
			MethodKey methodKey = new MethodKey(
				UserPropertiesServiceUtil.class, "updateUserPicture",
				_updateUserPictureParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, picture);

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

	public static org.json.JSONObject updateThemeColor(
		HttpPrincipal httpPrincipal, String color) {

		try {
			MethodKey methodKey = new MethodKey(
				UserPropertiesServiceUtil.class, "updateThemeColor",
				_updateThemeColorParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, color);

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

	public static org.json.JSONObject updateReportFrequency(
		HttpPrincipal httpPrincipal, int frequency) {

		try {
			MethodKey methodKey = new MethodKey(
				UserPropertiesServiceUtil.class, "updateReportFrequency",
				_updateReportFrequencyParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, frequency);

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

	public static org.json.JSONObject updateWebdavState(
		HttpPrincipal httpPrincipal, boolean isEnabled) {

		try {
			MethodKey methodKey = new MethodKey(
				UserPropertiesServiceUtil.class, "updateWebdavState",
				_updateWebdavStateParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, isEnabled);

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
		UserPropertiesServiceHttp.class);

	private static final Class<?>[] _updateSideMenuStateParameterTypes0 =
		new Class[] {boolean.class};
	private static final Class<?>[] _updateUserPictureParameterTypes1 =
		new Class[] {java.io.File.class};
	private static final Class<?>[] _updateThemeColorParameterTypes2 =
		new Class[] {String.class};
	private static final Class<?>[] _updateReportFrequencyParameterTypes3 =
		new Class[] {int.class};
	private static final Class<?>[] _updateWebdavStateParameterTypes4 =
		new Class[] {boolean.class};

}