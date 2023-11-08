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

package com.weprode.facile.help.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.facile.help.service.HelpItemServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>HelpItemServiceUtil</code> service
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
public class HelpItemServiceHttp {

	public static org.json.JSONObject getHelpItemDetails(
		HttpPrincipal httpPrincipal, long itemId) {

		try {
			MethodKey methodKey = new MethodKey(
				HelpItemServiceUtil.class, "getHelpItemDetails",
				_getHelpItemDetailsParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, itemId);

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

	public static org.json.JSONObject saveHelpItemPosition(
		HttpPrincipal httpPrincipal, long categoryId, String item) {

		try {
			MethodKey methodKey = new MethodKey(
				HelpItemServiceUtil.class, "saveHelpItemPosition",
				_saveHelpItemPositionParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, categoryId, item);

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

	public static org.json.JSONObject saveHelpItem(
		HttpPrincipal httpPrincipal, long categoryId, String item) {

		try {
			MethodKey methodKey = new MethodKey(
				HelpItemServiceUtil.class, "saveHelpItem",
				_saveHelpItemParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, categoryId, item);

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

	public static org.json.JSONObject deleteItem(
		HttpPrincipal httpPrincipal, long itemId) {

		try {
			MethodKey methodKey = new MethodKey(
				HelpItemServiceUtil.class, "deleteItem",
				_deleteItemParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, itemId);

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

	private static Log _log = LogFactoryUtil.getLog(HelpItemServiceHttp.class);

	private static final Class<?>[] _getHelpItemDetailsParameterTypes0 =
		new Class[] {long.class};
	private static final Class<?>[] _saveHelpItemPositionParameterTypes1 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _saveHelpItemParameterTypes2 = new Class[] {
		long.class, String.class
	};
	private static final Class<?>[] _deleteItemParameterTypes3 = new Class[] {
		long.class
	};

}