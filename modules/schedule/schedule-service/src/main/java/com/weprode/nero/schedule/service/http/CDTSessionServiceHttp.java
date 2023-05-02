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

import com.weprode.nero.schedule.service.CDTSessionServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>CDTSessionServiceUtil</code> service
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
 * @see CDTSessionServiceSoap
 * @generated
 */
public class CDTSessionServiceHttp {

	public static com.liferay.portal.kernel.json.JSONObject getHorairesSessions(
		HttpPrincipal httpPrincipal, long userId, long groupId, String start,
		String end, String volee) {

		try {
			MethodKey methodKey = new MethodKey(
				CDTSessionServiceUtil.class, "getHorairesSessions",
				_getHorairesSessionsParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, groupId, start, end, volee);

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

	public static com.liferay.portal.kernel.json.JSONObject getTeacherGroups(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				CDTSessionServiceUtil.class, "getTeacherGroups",
				_getTeacherGroupsParameterTypes1);

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

	public static com.liferay.portal.kernel.json.JSONObject getSessionDetails(
		HttpPrincipal httpPrincipal, long sessionId) {

		try {
			MethodKey methodKey = new MethodKey(
				CDTSessionServiceUtil.class, "getSessionDetails",
				_getSessionDetailsParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, sessionId);

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

	public static com.liferay.portal.kernel.json.JSONObject createSession(
		HttpPrincipal httpPrincipal, long groupId, String subject, String room,
		String startDate, String endDate, String teacherIds,
		boolean isRecurrent) {

		try {
			MethodKey methodKey = new MethodKey(
				CDTSessionServiceUtil.class, "createSession",
				_createSessionParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, subject, room, startDate, endDate,
				teacherIds, isRecurrent);

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
		CDTSessionServiceHttp.class);

	private static final Class<?>[] _getHorairesSessionsParameterTypes0 =
		new Class[] {
			long.class, long.class, String.class, String.class, String.class
		};
	private static final Class<?>[] _getTeacherGroupsParameterTypes1 =
		new Class[] {};
	private static final Class<?>[] _getSessionDetailsParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _createSessionParameterTypes3 =
		new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			String.class, boolean.class
		};

}