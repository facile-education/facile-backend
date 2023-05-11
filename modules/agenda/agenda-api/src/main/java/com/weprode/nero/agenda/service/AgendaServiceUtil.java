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

package com.weprode.nero.agenda.service;

/**
 * Provides the remote service utility for Agenda. This utility wraps
 * <code>com.weprode.nero.agenda.service.impl.AgendaServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AgendaService
 * @generated
 */
public class AgendaServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.agenda.service.impl.AgendaServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject createEvent(
		java.lang.String title, java.lang.String description,
		java.lang.String location, java.lang.String startDate,
		java.lang.String endDate, java.lang.String populations) {

		return getService().createEvent(
			title, description, location, startDate, endDate, populations);
	}

	public static org.json.JSONObject deleteEvent(long eventId) {
		return getService().deleteEvent(eventId);
	}

	public static org.json.JSONObject getEventDetails(long eventId) {
		return getService().getEventDetails(eventId);
	}

	public static org.json.JSONObject getEvents(
		int startIndex, int nbEvents, boolean unreadOnly) {

		return getService().getEvents(startIndex, nbEvents, unreadOnly);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject modifyEvent(
		long eventId, java.lang.String title, java.lang.String description,
		java.lang.String location, java.lang.String startDate,
		java.lang.String endDate, java.lang.String populations,
		boolean markAsUnreadForAll) {

		return getService().modifyEvent(
			eventId, title, description, location, startDate, endDate,
			populations, markAsUnreadForAll);
	}

	public static org.json.JSONObject setEventRead(long eventId, boolean read) {
		return getService().setEventRead(eventId, read);
	}

	public static AgendaService getService() {
		return _service;
	}

	private static volatile AgendaService _service;

}