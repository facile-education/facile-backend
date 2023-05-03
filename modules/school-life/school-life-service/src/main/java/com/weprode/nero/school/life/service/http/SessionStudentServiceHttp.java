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

import com.weprode.nero.school.life.service.SessionStudentServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>SessionStudentServiceUtil</code> service
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
 * @see SessionStudentServiceSoap
 * @generated
 */
public class SessionStudentServiceHttp {

	public static org.json.JSONObject getSessionMembers(
		HttpPrincipal httpPrincipal, long schoollifeSessionId) {

		try {
			MethodKey methodKey = new MethodKey(
				SessionStudentServiceUtil.class, "getSessionMembers",
				_getSessionMembersParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoollifeSessionId);

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

	public static org.json.JSONObject getSessions(
		HttpPrincipal httpPrincipal, long studentId, long classId,
		String minDateStr, String maxDateStr) {

		try {
			MethodKey methodKey = new MethodKey(
				SessionStudentServiceUtil.class, "getSessions",
				_getSessionsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, studentId, classId, minDateStr, maxDateStr);

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

	public static org.json.JSONObject registerStudent(
		HttpPrincipal httpPrincipal, long studentId, long schoollifeSessionId,
		String comment, String replayTestSubject, boolean notifyParents) {

		try {
			MethodKey methodKey = new MethodKey(
				SessionStudentServiceUtil.class, "registerStudent",
				_registerStudentParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, studentId, schoollifeSessionId, comment,
				replayTestSubject, notifyParents);

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

	public static org.json.JSONObject registerClass(
		HttpPrincipal httpPrincipal, long classId, long schoollifeSessionId,
		String comment, String replayTestSubject, boolean notifyParents) {

		try {
			MethodKey methodKey = new MethodKey(
				SessionStudentServiceUtil.class, "registerClass",
				_registerClassParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classId, schoollifeSessionId, comment,
				replayTestSubject, notifyParents);

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

	public static org.json.JSONObject unregisterStudent(
		HttpPrincipal httpPrincipal, long studentId, long schoollifeSessionId,
		boolean allSessions) {

		try {
			MethodKey methodKey = new MethodKey(
				SessionStudentServiceUtil.class, "unregisterStudent",
				_unregisterStudentParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, studentId, schoollifeSessionId, allSessions);

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

	public static org.json.JSONObject markStudentsPresent(
		HttpPrincipal httpPrincipal, long schoollifeSessionId,
		String studentsPresence) {

		try {
			MethodKey methodKey = new MethodKey(
				SessionStudentServiceUtil.class, "markStudentsPresent",
				_markStudentsPresentParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoollifeSessionId, studentsPresence);

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
		SessionStudentServiceHttp.class);

	private static final Class<?>[] _getSessionMembersParameterTypes0 =
		new Class[] {long.class};
	private static final Class<?>[] _getSessionsParameterTypes1 = new Class[] {
		long.class, long.class, String.class, String.class
	};
	private static final Class<?>[] _registerStudentParameterTypes2 =
		new Class[] {
			long.class, long.class, String.class, String.class, boolean.class
		};
	private static final Class<?>[] _registerClassParameterTypes3 =
		new Class[] {
			long.class, long.class, String.class, String.class, boolean.class
		};
	private static final Class<?>[] _unregisterStudentParameterTypes4 =
		new Class[] {long.class, long.class, boolean.class};
	private static final Class<?>[] _markStudentsPresentParameterTypes5 =
		new Class[] {long.class, String.class};

}