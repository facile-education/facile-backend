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

package com.weprode.nero.progression.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.progression.service.ProgressionServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>ProgressionServiceUtil</code> service
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
 * @see ProgressionServiceSoap
 * @generated
 */
public class ProgressionServiceHttp {

	public static com.liferay.portal.kernel.json.JSONObject getProgressionList(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionServiceUtil.class, "getProgressionList",
				_getProgressionListParameterTypes0);

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

	public static com.liferay.portal.kernel.json.JSONObject getProgressionTree(
		HttpPrincipal httpPrincipal, long progressionId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionServiceUtil.class, "getProgressionTree",
				_getProgressionTreeParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, progressionId);

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

	public static com.liferay.portal.kernel.json.JSONObject addProgression(
		HttpPrincipal httpPrincipal, String name, String description,
		long subjectId, String volee, String color) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionServiceUtil.class, "addProgression",
				_addProgressionParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, name, description, subjectId, volee, color);

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

	public static com.liferay.portal.kernel.json.JSONObject updateProgression(
		HttpPrincipal httpPrincipal, long progressionId, String name,
		String description, long subjectId, String volee, String color) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionServiceUtil.class, "updateProgression",
				_updateProgressionParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, progressionId, name, description, subjectId, volee,
				color);

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

	public static com.liferay.portal.kernel.json.JSONObject deleteProgression(
		HttpPrincipal httpPrincipal, long progressionId) {

		try {
			MethodKey methodKey = new MethodKey(
				ProgressionServiceUtil.class, "deleteProgression",
				_deleteProgressionParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, progressionId);

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
		ProgressionServiceHttp.class);

	private static final Class<?>[] _getProgressionListParameterTypes0 =
		new Class[] {};
	private static final Class<?>[] _getProgressionTreeParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _addProgressionParameterTypes2 =
		new Class[] {
			String.class, String.class, long.class, String.class, String.class
		};
	private static final Class<?>[] _updateProgressionParameterTypes3 =
		new Class[] {
			long.class, String.class, String.class, long.class, String.class,
			String.class
		};
	private static final Class<?>[] _deleteProgressionParameterTypes4 =
		new Class[] {long.class};

}