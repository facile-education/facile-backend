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

import com.weprode.nero.application.service.BroadcastRuleServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>BroadcastRuleServiceUtil</code> service
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
 * @see BroadcastRuleServiceSoap
 * @generated
 */
public class BroadcastRuleServiceHttp {

	public static com.liferay.portal.kernel.json.JSONObject getApplicationRules(
		HttpPrincipal httpPrincipal, long applicationId, long schoolId) {

		try {
			MethodKey methodKey = new MethodKey(
				BroadcastRuleServiceUtil.class, "getApplicationRules",
				_getApplicationRulesParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, applicationId, schoolId);

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
		updateBroadcastRules(
			HttpPrincipal httpPrincipal, long applicationId, long schoolId,
			String rules) {

		try {
			MethodKey methodKey = new MethodKey(
				BroadcastRuleServiceUtil.class, "updateBroadcastRules",
				_updateBroadcastRulesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, applicationId, schoolId, rules);

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
		BroadcastRuleServiceHttp.class);

	private static final Class<?>[] _getApplicationRulesParameterTypes0 =
		new Class[] {long.class, long.class};
	private static final Class<?>[] _updateBroadcastRulesParameterTypes1 =
		new Class[] {long.class, long.class, String.class};

}