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

package com.weprode.nero.agenda.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.weprode.nero.agenda.service.AgendaServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>AgendaServiceUtil</code> service
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
public class AgendaServiceHttp {

	public static org.json.JSONObject getEvents(
		HttpPrincipal httpPrincipal, int startIndex, int nbEvents,
		boolean unreadOnly) {

		try {
			MethodKey methodKey = new MethodKey(
				AgendaServiceUtil.class, "getEvents",
				_getEventsParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, startIndex, nbEvents, unreadOnly);

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

	public static org.json.JSONObject getEventDetails(
		HttpPrincipal httpPrincipal, long eventId) {

		try {
			MethodKey methodKey = new MethodKey(
				AgendaServiceUtil.class, "getEventDetails",
				_getEventDetailsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, eventId);

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

	public static org.json.JSONObject createEvent(
		HttpPrincipal httpPrincipal, String title, String description,
		String location, String startDate, String endDate, String populations) {

		try {
			MethodKey methodKey = new MethodKey(
				AgendaServiceUtil.class, "createEvent",
				_createEventParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, title, description, location, startDate, endDate,
				populations);

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

	public static org.json.JSONObject modifyEvent(
		HttpPrincipal httpPrincipal, long eventId, String title,
		String description, String location, String startDate, String endDate,
		String populations, boolean markAsUnreadForAll) {

		try {
			MethodKey methodKey = new MethodKey(
				AgendaServiceUtil.class, "modifyEvent",
				_modifyEventParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, eventId, title, description, location, startDate,
				endDate, populations, markAsUnreadForAll);

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

	public static org.json.JSONObject deleteEvent(
		HttpPrincipal httpPrincipal, long eventId) {

		try {
			MethodKey methodKey = new MethodKey(
				AgendaServiceUtil.class, "deleteEvent",
				_deleteEventParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, eventId);

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

	public static org.json.JSONObject setEventRead(
		HttpPrincipal httpPrincipal, long eventId, boolean read) {

		try {
			MethodKey methodKey = new MethodKey(
				AgendaServiceUtil.class, "setEventRead",
				_setEventReadParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, eventId, read);

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

	private static Log _log = LogFactoryUtil.getLog(AgendaServiceHttp.class);

	private static final Class<?>[] _getEventsParameterTypes0 = new Class[] {
		int.class, int.class, boolean.class
	};
	private static final Class<?>[] _getEventDetailsParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _createEventParameterTypes2 = new Class[] {
		String.class, String.class, String.class, String.class, String.class,
		String.class
	};
	private static final Class<?>[] _modifyEventParameterTypes3 = new Class[] {
		long.class, String.class, String.class, String.class, String.class,
		String.class, String.class, boolean.class
	};
	private static final Class<?>[] _deleteEventParameterTypes4 = new Class[] {
		long.class
	};
	private static final Class<?>[] _setEventReadParameterTypes5 = new Class[] {
		long.class, boolean.class
	};

}