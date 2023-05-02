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

package com.weprode.nero.statistic.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.statistic.service.GeneralStatServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>GeneralStatServiceUtil</code> service
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
 * @see GeneralStatServiceSoap
 * @generated
 */
public class GeneralStatServiceHttp {

	public static com.liferay.portal.kernel.json.JSONObject getSessionsCount(
		HttpPrincipal httpPrincipal, java.util.Date startDate,
		java.util.Date endDate, long schoolId, long serviceId,
		String comparator) {

		try {
			MethodKey methodKey = new MethodKey(
				GeneralStatServiceUtil.class, "getSessionsCount",
				_getSessionsCountParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, startDate, endDate, schoolId, serviceId, comparator);

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

	public static com.liferay.portal.kernel.json.JSONObject getActiveUsersCount(
		HttpPrincipal httpPrincipal, java.util.Date startDate,
		java.util.Date endDate, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				GeneralStatServiceUtil.class, "getActiveUsersCount",
				_getActiveUsersCountParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, startDate, endDate, schoolId);

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

	public static com.liferay.portal.kernel.json.JSONObject getFilesCount(
		HttpPrincipal httpPrincipal, java.util.Date startDate,
		java.util.Date endDate, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				GeneralStatServiceUtil.class, "getFilesCount",
				_getFilesCountParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, startDate, endDate, schoolId);

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

	public static com.liferay.portal.kernel.json.JSONObject getHomeworksCount(
		HttpPrincipal httpPrincipal, java.util.Date startDate,
		java.util.Date endDate, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				GeneralStatServiceUtil.class, "getHomeworksCount",
				_getHomeworksCountParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, startDate, endDate, schoolId);

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

	public static com.liferay.portal.kernel.json.JSONObject getNewsCount(
		HttpPrincipal httpPrincipal, java.util.Date startDate,
		java.util.Date endDate, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				GeneralStatServiceUtil.class, "getNewsCount",
				_getNewsCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, startDate, endDate, schoolId);

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

	public static com.liferay.portal.kernel.json.JSONObject getMessagesCount(
		HttpPrincipal httpPrincipal, java.util.Date startDate,
		java.util.Date endDate, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				GeneralStatServiceUtil.class, "getMessagesCount",
				_getMessagesCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, startDate, endDate, schoolId);

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
		getDashboardStatistics(HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				GeneralStatServiceUtil.class, "getDashboardStatistics",
				_getDashboardStatisticsParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey);

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
		GeneralStatServiceHttp.class);

	private static final Class<?>[] _getSessionsCountParameterTypes0 =
		new Class[] {
			java.util.Date.class, java.util.Date.class, long.class, long.class,
			String.class
		};
	private static final Class<?>[] _getActiveUsersCountParameterTypes1 =
		new Class[] {java.util.Date.class, java.util.Date.class, long.class};
	private static final Class<?>[] _getFilesCountParameterTypes2 =
		new Class[] {java.util.Date.class, java.util.Date.class, long.class};
	private static final Class<?>[] _getHomeworksCountParameterTypes3 =
		new Class[] {java.util.Date.class, java.util.Date.class, long.class};
	private static final Class<?>[] _getNewsCountParameterTypes4 = new Class[] {
		java.util.Date.class, java.util.Date.class, long.class
	};
	private static final Class<?>[] _getMessagesCountParameterTypes5 =
		new Class[] {java.util.Date.class, java.util.Date.class, long.class};
	private static final Class<?>[] _getDashboardStatisticsParameterTypes7 =
		new Class[] {};

}