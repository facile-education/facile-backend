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

package com.weprode.facile.application.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.facile.application.service.ApplicationServiceUtil;

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
 * @generated
 */
public class ApplicationServiceHttp {

	public static org.json.JSONObject addApplication(
		HttpPrincipal httpPrincipal, String applicationName,
		String applicationKey, String category, long menuEntryId, String image,
		boolean hasCustomUrl, String globalUrl, boolean exportUser,
		boolean exportStudent, boolean exportParent, boolean exportTeacher,
		boolean exportOther, String defaultRoles, String authorizedSchools) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "addApplication",
				_addApplicationParameterTypes0);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject editApplication(
		HttpPrincipal httpPrincipal, long applicationId, String applicationName,
		String applicationKey, String category, long menuEntryId, String image,
		boolean hasCustomUrl, String globalUrl, boolean exportUser,
		boolean exportStudent, boolean exportParent, boolean exportTeacher,
		boolean exportOther, String defaultRoles, String authorizedSchools) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "editApplication",
				_editApplicationParameterTypes1);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject removeApplication(
		HttpPrincipal httpPrincipal, long applicationId) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "removeApplication",
				_removeApplicationParameterTypes2);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject getSchoolApplications(
		HttpPrincipal httpPrincipal, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "getSchoolApplications",
				_getSchoolApplicationsParameterTypes3);

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

	public static org.json.JSONObject export(
		HttpPrincipal httpPrincipal, long applicationId, long schoolId,
		String roleName) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "export", _exportParameterTypes4);

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

			return (org.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static org.json.JSONObject getUserApplications(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "getUserApplications",
				_getUserApplicationsParameterTypes5);

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

	public static org.json.JSONObject getAllApplications(
		HttpPrincipal httpPrincipal) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "getAllApplications",
				_getAllApplicationsParameterTypes6);

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

	public static org.json.JSONObject getStatApplications(
		HttpPrincipal httpPrincipal, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "getStatApplications",
				_getStatApplicationsParameterTypes7);

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

	public static org.json.JSONObject getResourceUrls(
		HttpPrincipal httpPrincipal, long menuEntryId) {

		try {
			MethodKey methodKey = new MethodKey(
				ApplicationServiceUtil.class, "getResourceUrls",
				_getResourceUrlsParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, menuEntryId);

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
		ApplicationServiceHttp.class);

	private static final Class<?>[] _addApplicationParameterTypes0 =
		new Class[] {
			String.class, String.class, String.class, long.class, String.class,
			boolean.class, String.class, boolean.class, boolean.class,
			boolean.class, boolean.class, boolean.class, String.class,
			String.class
		};
	private static final Class<?>[] _editApplicationParameterTypes1 =
		new Class[] {
			long.class, String.class, String.class, String.class, long.class,
			String.class, boolean.class, String.class, boolean.class,
			boolean.class, boolean.class, boolean.class, boolean.class,
			String.class, String.class
		};
	private static final Class<?>[] _removeApplicationParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _getSchoolApplicationsParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _exportParameterTypes4 = new Class[] {
		long.class, long.class, String.class
	};
	private static final Class<?>[] _getUserApplicationsParameterTypes5 =
		new Class[] {};
	private static final Class<?>[] _getAllApplicationsParameterTypes6 =
		new Class[] {};
	private static final Class<?>[] _getStatApplicationsParameterTypes7 =
		new Class[] {long.class};
	private static final Class<?>[] _getResourceUrlsParameterTypes8 =
		new Class[] {long.class};

}