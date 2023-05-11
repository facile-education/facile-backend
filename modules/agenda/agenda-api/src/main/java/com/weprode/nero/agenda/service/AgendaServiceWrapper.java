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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AgendaService}.
 *
 * @author Brian Wing Shun Chan
 * @see AgendaService
 * @generated
 */
public class AgendaServiceWrapper
	implements AgendaService, ServiceWrapper<AgendaService> {

	public AgendaServiceWrapper(AgendaService agendaService) {
		_agendaService = agendaService;
	}

	@Override
	public org.json.JSONObject createEvent(
		String title, String description, String location, String startDate,
		String endDate, String populations) {

		return _agendaService.createEvent(
			title, description, location, startDate, endDate, populations);
	}

	@Override
	public org.json.JSONObject deleteEvent(long eventId) {
		return _agendaService.deleteEvent(eventId);
	}

	@Override
	public org.json.JSONObject getEventDetails(long eventId) {
		return _agendaService.getEventDetails(eventId);
	}

	@Override
	public org.json.JSONObject getEvents(
		int startIndex, int nbEvents, boolean unreadOnly) {

		return _agendaService.getEvents(startIndex, nbEvents, unreadOnly);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _agendaService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject modifyEvent(
		long eventId, String title, String description, String location,
		String startDate, String endDate, String populations,
		boolean markAsUnreadForAll) {

		return _agendaService.modifyEvent(
			eventId, title, description, location, startDate, endDate,
			populations, markAsUnreadForAll);
	}

	@Override
	public org.json.JSONObject setEventRead(long eventId, boolean read) {
		return _agendaService.setEventRead(eventId, read);
	}

	@Override
	public AgendaService getWrappedService() {
		return _agendaService;
	}

	@Override
	public void setWrappedService(AgendaService agendaService) {
		_agendaService = agendaService;
	}

	private AgendaService _agendaService;

}