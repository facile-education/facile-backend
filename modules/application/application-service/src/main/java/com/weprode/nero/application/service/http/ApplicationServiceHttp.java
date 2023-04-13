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

package com.weprode.nero.application.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.application.service.ApplicationServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>ApplicationServiceUtil</code> service
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
 * @see ApplicationServiceSoap
 * @generated
 */
public class ApplicationServiceHttp {

	public static com.liferay.portal.kernel.json.JSONObject getPortlets(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "getPortlets",
				_getPortletsParameterTypes0);

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

	public static com.liferay.portal.kernel.json.JSONObject addApplication(
		HttpPrincipal httpPrincipal, String applicationName,
		String applicationKey, String category, long menuEntryId, String image,
		boolean hasCustomUrl, String globalUrl, boolean exportUser,
		boolean exportStudent, boolean exportParent, boolean exportTeacher,
		boolean exportOther, String defaultRoles, String authorizedSchools) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "addApplication",
				_addApplicationParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, applicationName, applicationKey, category,
				menuEntryId, image, hasCustomUrl, globalUrl, exportUser,
				exportStudent, exportParent, exportTeacher, exportOther,
				defaultRoles, authorizedSchools);

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

	public static com.liferay.portal.kernel.json.JSONObject editApplication(
		HttpPrincipal httpPrincipal, long applicationId, String applicationName,
		String applicationKey, String category, long menuEntryId, String image,
		boolean hasCustomUrl, String globalUrl, boolean exportUser,
		boolean exportStudent, boolean exportParent, boolean exportTeacher,
		boolean exportOther, String defaultRoles, String authorizedSchools) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "editApplication",
				_editApplicationParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, applicationId, applicationName, applicationKey,
				category, menuEntryId, image, hasCustomUrl, globalUrl,
				exportUser, exportStudent, exportParent, exportTeacher,
				exportOther, defaultRoles, authorizedSchools);

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

	public static com.liferay.portal.kernel.json.JSONObject removeApplication(
		HttpPrincipal httpPrincipal, long applicationId) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "removeApplication",
				_removeApplicationParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, applicationId);

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
		getSchoolApplications(HttpPrincipal httpPrincipal, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "getSchoolApplications",
				_getSchoolApplicationsParameterTypes4);

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

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject export(
		HttpPrincipal httpPrincipal, long applicationId, long schoolId,
		String roleName) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "export", _exportParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, applicationId, schoolId, roleName);

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

	public static com.liferay.portal.kernel.json.JSONObject getUserApplications(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "getUserApplications",
				_getUserApplicationsParameterTypes6);

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

	public static com.liferay.portal.kernel.json.JSONObject getAllApplications(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "getAllApplications",
				_getAllApplicationsParameterTypes7);

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

	public static com.liferay.portal.kernel.json.JSONObject getStatApplications(
		HttpPrincipal httpPrincipal, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "getStatApplications",
				_getStatApplicationsParameterTypes8);

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

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ApplicationServiceHttp.class);

	private static final Class<?>[] _getPortletsParameterTypes0 =
		new Class[] {};
	private static final Class<?>[] _addApplicationParameterTypes1 =
		new Class[] {
			String.class, String.class, String.class, long.class, String.class,
			boolean.class, String.class, boolean.class, boolean.class,
			boolean.class, boolean.class, boolean.class, String.class,
			String.class
		};
	private static final Class<?>[] _editApplicationParameterTypes2 =
		new Class[] {
			long.class, String.class, String.class, String.class, long.class,
			String.class, boolean.class, String.class, boolean.class,
			boolean.class, boolean.class, boolean.class, boolean.class,
			String.class, String.class
		};
	private static final Class<?>[] _removeApplicationParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getSchoolApplicationsParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _exportParameterTypes5 = new Class[] {
		long.class, long.class, String.class
	};
	private static final Class<?>[] _getUserApplicationsParameterTypes6 =
		new Class[] {};
	private static final Class<?>[] _getAllApplicationsParameterTypes7 =
		new Class[] {};
	private static final Class<?>[] _getStatApplicationsParameterTypes8 =
		new Class[] {long.class};

}