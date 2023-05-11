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

import com.weprode.nero.agenda.service.AgendaServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>AgendaServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
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
 * @see AgendaServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class AgendaServiceSoap {

	public static org.json.JSONObject getEvents(
			int startIndex, int nbEvents, boolean unreadOnly)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = AgendaServiceUtil.getEvents(
				startIndex, nbEvents, unreadOnly);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject getEventDetails(long eventId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = AgendaServiceUtil.getEventDetails(
				eventId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject createEvent(
			String title, String description, String location, String startDate,
			String endDate, String populations)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = AgendaServiceUtil.createEvent(
				title, description, location, startDate, endDate, populations);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject modifyEvent(
			long eventId, String title, String description, String location,
			String startDate, String endDate, String populations,
			boolean markAsUnreadForAll)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = AgendaServiceUtil.modifyEvent(
				eventId, title, description, location, startDate, endDate,
				populations, markAsUnreadForAll);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject deleteEvent(long eventId)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = AgendaServiceUtil.deleteEvent(
				eventId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static org.json.JSONObject setEventRead(long eventId, boolean read)
		throws RemoteException {

		try {
			org.json.JSONObject returnValue = AgendaServiceUtil.setEventRead(
				eventId, read);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AgendaServiceSoap.class);

}