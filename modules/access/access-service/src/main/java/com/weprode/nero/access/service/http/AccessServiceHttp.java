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

package com.weprode.nero.access.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.access.service.AccessServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>AccessServiceUtil</code> service
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
 * @see AccessServiceSoap
 * @generated
 */
public class AccessServiceHttp {

	public static org.json.JSONObject getSchoolAccesses(
		HttpPrincipal httpPrincipal, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				AccessServiceUtil.class, "getSchoolAccesses",
				_getSchoolAccessesParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoolId);

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

	public static org.json.JSONObject saveSchoolAccesses(
		HttpPrincipal httpPrincipal, long schoolId, String accesses) {

		try {
			MethodKey methodKey = new MethodKey(
				AccessServiceUtil.class, "saveSchoolAccesses",
				_saveSchoolAccessesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoolId, accesses);

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

	public static org.json.JSONObject getUserAccesses(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				AccessServiceUtil.class, "getUserAccesses",
				_getUserAccessesParameterTypes2);

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

	public static org.json.JSONObject getRoleAccesses(
		HttpPrincipal httpPrincipal, long schoolId, long roleId) {

		try {
			MethodKey methodKey = new MethodKey(
				AccessServiceUtil.class, "getRoleAccesses",
				_getRoleAccessesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoolId, roleId);

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

	private static Log _log = LogFactoryUtil.getLog(AccessServiceHttp.class);

	private static final Class<?>[] _getSchoolAccessesParameterTypes0 =
		new Class[] {long.class};
	private static final Class<?>[] _saveSchoolAccessesParameterTypes1 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _getUserAccessesParameterTypes2 =
		new Class[] {};
	private static final Class<?>[] _getRoleAccessesParameterTypes3 =
		new Class[] {long.class, long.class};

}