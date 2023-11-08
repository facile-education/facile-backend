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

package com.weprode.facile.dashboard.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.facile.dashboard.service.DashboardServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>DashboardServiceUtil</code> service
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
public class DashboardServiceHttp {

	public static org.json.JSONObject initDashboard(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				DashboardServiceUtil.class, "initDashboard",
				_initDashboardParameterTypes0);

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

	public static org.json.JSONObject getUserSchedule(
		HttpPrincipal httpPrincipal, long userId, String date,
		boolean goForward) {

		try {
			MethodKey methodKey = new MethodKey(
				DashboardServiceUtil.class, "getUserSchedule",
				_getUserScheduleParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, date, goForward);

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

	public static org.json.JSONObject getDashboardActivity(
		HttpPrincipal httpPrincipal, long groupId, String maxDate,
		int nbResults, boolean withNews, boolean withDocs,
		boolean withMemberships, boolean withSchoollife, boolean withSessions) {

		try {
			MethodKey methodKey = new MethodKey(
				DashboardServiceUtil.class, "getDashboardActivity",
				_getDashboardActivityParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, maxDate, nbResults, withNews, withDocs,
				withMemberships, withSchoollife, withSessions);

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

	public static org.json.JSONObject checkDashboardParameter(
		HttpPrincipal httpPrincipal, long dashboardId) {

		try {
			MethodKey methodKey = new MethodKey(
				DashboardServiceUtil.class, "checkDashboardParameter",
				_checkDashboardParameterParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, dashboardId);

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

	private static Log _log = LogFactoryUtil.getLog(DashboardServiceHttp.class);

	private static final Class<?>[] _initDashboardParameterTypes0 =
		new Class[] {};
	private static final Class<?>[] _getUserScheduleParameterTypes1 =
		new Class[] {long.class, String.class, boolean.class};
	private static final Class<?>[] _getDashboardActivityParameterTypes2 =
		new Class[] {
			long.class, String.class, int.class, boolean.class, boolean.class,
			boolean.class, boolean.class, boolean.class
		};
	private static final Class<?>[] _checkDashboardParameterParameterTypes3 =
		new Class[] {long.class};

}