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

import com.weprode.nero.school.life.service.SchoollifeSlotServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>SchoollifeSlotServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.weprode.nero.school.life.model.SchoollifeSlotSoap</code>. If the method in the
 * service utility returns a
 * <code>com.weprode.nero.school.life.model.SchoollifeSlot</code>, that is translated to a
 * <code>com.weprode.nero.school.life.model.SchoollifeSlotSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSlotServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class SchoollifeSlotServiceSoap {

	public static org.json.JSONObject createSlot(
			long schoolId, String startDateStr, String endDateStr, int day,
			String startHour, String endHour, long teacherId, int type,
			String room, int capacity)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				SchoollifeSlotServiceUtil.createSlot(
					schoolId, startDateStr, endDateStr, day, startHour, endHour,
					teacherId, type, room, capacity);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject editSlot(
			long schoollifeSessionId, String startDateStr, String endDateStr,
			int newDay, String newStartHour, String newEndHour,
			long newTeacherId, String newRoom, int newCapacity)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				SchoollifeSlotServiceUtil.editSlot(
					schoollifeSessionId, startDateStr, endDateStr, newDay,
					newStartHour, newEndHour, newTeacherId, newRoom,
					newCapacity);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject deleteSlot(
			long schoollifeSessionId, String startDateStr, String endDateStr)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				SchoollifeSlotServiceUtil.deleteSlot(
					schoollifeSessionId, startDateStr, endDateStr);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getSessionLimitSlotDate(
			long schoollifeSessionId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue =
				SchoollifeSlotServiceUtil.getSessionLimitSlotDate(
					schoollifeSessionId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SchoollifeSlotServiceSoap.class);

}