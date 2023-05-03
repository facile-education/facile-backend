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

import com.weprode.nero.school.life.service.SchoollifeSlotServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>SchoollifeSlotServiceUtil</code> service
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
 * @see SchoollifeSlotServiceSoap
 * @generated
 */
public class SchoollifeSlotServiceHttp {

	public static org.json.JSONObject createSlot(
		HttpPrincipal httpPrincipal, long schoolId, String startDateStr,
		int day, String startHour, String endHour, long teacherId, int type,
		String room, int capacity) {

		try {
			MethodKey methodKey = new MethodKey(
				SchoollifeSlotServiceUtil.class, "createSlot",
				_createSlotParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoolId, startDateStr, day, startHour, endHour,
				teacherId, type, room, capacity);

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

	public static org.json.JSONObject editSlot(
		HttpPrincipal httpPrincipal, long schoollifeSessionId,
		String startDateStr, int newDay, String newStartHour, String newEndHour,
		long newTeacherId, String newRoom, int newCapacity) {

		try {
			MethodKey methodKey = new MethodKey(
				SchoollifeSlotServiceUtil.class, "editSlot",
				_editSlotParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoollifeSessionId, startDateStr, newDay,
				newStartHour, newEndHour, newTeacherId, newRoom, newCapacity);

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

	public static org.json.JSONObject deleteSlot(
		HttpPrincipal httpPrincipal, long schoollifeSessionId,
		String startDateStr) {

		try {
			MethodKey methodKey = new MethodKey(
				SchoollifeSlotServiceUtil.class, "deleteSlot",
				_deleteSlotParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, schoollifeSessionId, startDateStr);

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
		SchoollifeSlotServiceHttp.class);

	private static final Class<?>[] _createSlotParameterTypes0 = new Class[] {
		long.class, String.class, int.class, String.class, String.class,
		long.class, int.class, String.class, int.class
	};
	private static final Class<?>[] _editSlotParameterTypes1 = new Class[] {
		long.class, String.class, int.class, String.class, String.class,
		long.class, String.class, int.class
	};
	private static final Class<?>[] _deleteSlotParameterTypes2 = new Class[] {
		long.class, String.class
	};

}