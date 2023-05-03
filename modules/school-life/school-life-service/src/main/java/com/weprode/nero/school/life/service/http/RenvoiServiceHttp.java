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

package com.weprode.nero.school.life.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.school.life.service.RenvoiServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>RenvoiServiceUtil</code> service
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
 * @see RenvoiServiceSoap
 * @generated
 */
public class RenvoiServiceHttp {

	public static org.json.JSONObject getPendingRenvois(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				RenvoiServiceUtil.class, "getPendingRenvois",
				_getPendingRenvoisParameterTypes0);

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

	public static org.json.JSONObject registerStudentRenvoi(
		HttpPrincipal httpPrincipal, long schoollifeSessionId,
		long sourceTeacherId, long studentId, long sourceSessionId,
		long sourceSchoollifeSessionId, String registrationDate) {

		try {
			MethodKey methodKey = new MethodKey(
				RenvoiServiceUtil.class, "registerStudentRenvoi",
				_registerStudentRenvoiParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoollifeSessionId, sourceTeacherId, studentId,
				sourceSessionId, sourceSchoollifeSessionId, registrationDate);

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

	public static org.json.JSONObject setRenvoiReason(
		HttpPrincipal httpPrincipal, long schoollifeSessionId, long studentId,
		String reason) {

		try {
			MethodKey methodKey = new MethodKey(
				RenvoiServiceUtil.class, "setRenvoiReason",
				_setRenvoiReasonParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoollifeSessionId, studentId, reason);

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

	public static org.json.JSONObject unregisterStudentRenvoi(
		HttpPrincipal httpPrincipal, long schoollifeSessionId, long studentId) {

		try {
			MethodKey methodKey = new MethodKey(
				RenvoiServiceUtil.class, "unregisterStudentRenvoi",
				_unregisterStudentRenvoiParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoollifeSessionId, studentId);

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

	public static org.json.JSONObject getCandidateSessions(
		HttpPrincipal httpPrincipal, long schoollifeSessionId, long studentId) {

		try {
			MethodKey methodKey = new MethodKey(
				RenvoiServiceUtil.class, "getCandidateSessions",
				_getCandidateSessionsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoollifeSessionId, studentId);

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

	private static Log _log = LogFactoryUtil.getLog(RenvoiServiceHttp.class);

	private static final Class<?>[] _getPendingRenvoisParameterTypes0 =
		new Class[] {};
	private static final Class<?>[] _registerStudentRenvoiParameterTypes1 =
		new Class[] {
			long.class, long.class, long.class, long.class, long.class,
			String.class
		};
	private static final Class<?>[] _setRenvoiReasonParameterTypes2 =
		new Class[] {long.class, long.class, String.class};
	private static final Class<?>[] _unregisterStudentRenvoiParameterTypes3 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _getCandidateSessionsParameterTypes4 =
		new Class[] {long.class, long.class};

}