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

package com.weprode.nero.user.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.user.service.UserManagementServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>UserManagementServiceUtil</code> service
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
public class UserManagementServiceHttp {

	public static org.json.JSONObject createManualUser(
		HttpPrincipal httpPrincipal, String lastName, String firstName,
		String email, long roleId, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				UserManagementServiceUtil.class, "createManualUser",
				_createManualUserParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, lastName, firstName, email, roleId, schoolId);

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

	public static org.json.JSONObject editManualUser(
		HttpPrincipal httpPrincipal, long userId, String lastName,
		String firstName, String email, long roleId, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				UserManagementServiceUtil.class, "editManualUser",
				_editManualUserParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, lastName, firstName, email, roleId,
				schoolId);

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

	public static org.json.JSONObject deleteManualUser(
		HttpPrincipal httpPrincipal, long userId) {

		try {
			MethodKey methodKey = new MethodKey(
				UserManagementServiceUtil.class, "deleteManualUser",
				_deleteManualUserParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId);

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

	public static org.json.JSONObject getManualUsers(
		HttpPrincipal httpPrincipal, long schoolId, String search, int start,
		int limit) {

		try {
			MethodKey methodKey = new MethodKey(
				UserManagementServiceUtil.class, "getManualUsers",
				_getManualUsersParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoolId, search, start, limit);

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

	public static org.json.JSONObject updatePasswordByManager(
		HttpPrincipal httpPrincipal, long userId, String password) {

		try {
			MethodKey methodKey = new MethodKey(
				UserManagementServiceUtil.class, "updatePasswordByManager",
				_updatePasswordByManagerParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, password);

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

	public static org.json.JSONObject updatePasswordAfterReinitByManager(
		HttpPrincipal httpPrincipal, String password, String confirmPassword) {

		try {
			MethodKey methodKey = new MethodKey(
				UserManagementServiceUtil.class,
				"updatePasswordAfterReinitByManager",
				_updatePasswordAfterReinitByManagerParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, password, confirmPassword);

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

	public static org.json.JSONObject updateForgottenPassword(
		HttpPrincipal httpPrincipal, String password, String confirmPassword,
		String ticketKey) {

		try {
			MethodKey methodKey = new MethodKey(
				UserManagementServiceUtil.class, "updateForgottenPassword",
				_updateForgottenPasswordParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, password, confirmPassword, ticketKey);

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
		UserManagementServiceHttp.class);

	private static final Class<?>[] _createManualUserParameterTypes0 =
		new Class[] {
			String.class, String.class, String.class, long.class, long.class
		};
	private static final Class<?>[] _editManualUserParameterTypes1 =
		new Class[] {
			long.class, String.class, String.class, String.class, long.class,
			long.class
		};
	private static final Class<?>[] _deleteManualUserParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _getManualUsersParameterTypes3 =
		new Class[] {long.class, String.class, int.class, int.class};
	private static final Class<?>[] _updatePasswordByManagerParameterTypes5 =
		new Class[] {long.class, String.class};
	private static final Class<?>[]
		_updatePasswordAfterReinitByManagerParameterTypes6 = new Class[] {
			String.class, String.class
		};
	private static final Class<?>[] _updateForgottenPasswordParameterTypes7 =
		new Class[] {String.class, String.class, String.class};

}