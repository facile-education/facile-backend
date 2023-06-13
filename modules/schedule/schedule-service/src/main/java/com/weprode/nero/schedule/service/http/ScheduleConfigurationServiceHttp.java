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

package com.weprode.nero.schedule.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.schedule.service.ScheduleConfigurationServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>ScheduleConfigurationServiceUtil</code> service
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
 * @see ScheduleConfigurationServiceSoap
 * @generated
 */
public class ScheduleConfigurationServiceHttp {

	public static org.json.JSONObject getConfiguration(
		HttpPrincipal httpPrincipal, long schoolId, long childId) {

		try {
			MethodKey methodKey = new MethodKey(
				ScheduleConfigurationServiceUtil.class, "getConfiguration",
				_getConfigurationParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoolId, childId);

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

	public static org.json.JSONObject getGlobalConfiguration(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				ScheduleConfigurationServiceUtil.class,
				"getGlobalConfiguration",
				_getGlobalConfigurationParameterTypes1);

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

	public static org.json.JSONObject saveGlobalConfiguration(
		HttpPrincipal httpPrincipal, String startDateStr,
		String semesterDateStr, String endDateStr, String holidays,
		String h1Weeks, String h2Weeks) {

		try {
			MethodKey methodKey = new MethodKey(
				ScheduleConfigurationServiceUtil.class,
				"saveGlobalConfiguration",
				_saveGlobalConfigurationParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, startDateStr, semesterDateStr, endDateStr, holidays,
				h1Weeks, h2Weeks);

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
		ScheduleConfigurationServiceHttp.class);

	private static final Class<?>[] _getConfigurationParameterTypes0 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _getGlobalConfigurationParameterTypes1 =
		new Class[] {};
	private static final Class<?>[] _saveGlobalConfigurationParameterTypes2 =
		new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, String.class
		};

}