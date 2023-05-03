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

package com.weprode.nero.schedule.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CDTSessionService}.
 *
 * @author Brian Wing Shun Chan
 * @see CDTSessionService
 * @generated
 */
public class CDTSessionServiceWrapper
	implements CDTSessionService, ServiceWrapper<CDTSessionService> {

	public CDTSessionServiceWrapper(CDTSessionService cdtSessionService) {
		_cdtSessionService = cdtSessionService;
	}

	@Override
	public org.json.JSONObject createSession(
		long groupId, String subject, String room, String startDate,
		String endDate, String teacherIds, boolean isRecurrent) {

		return _cdtSessionService.createSession(
			groupId, subject, room, startDate, endDate, teacherIds,
			isRecurrent);
	}

	@Override
	public org.json.JSONObject getHorairesSessions(
		long userId, long groupId, String start, String end, String volee) {

		return _cdtSessionService.getHorairesSessions(
			userId, groupId, start, end, volee);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cdtSessionService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSessionDetails(long sessionId) {
		return _cdtSessionService.getSessionDetails(sessionId);
	}

	@Override
	public org.json.JSONObject getTeacherGroups() {
		return _cdtSessionService.getTeacherGroups();
	}

	@Override
	public CDTSessionService getWrappedService() {
		return _cdtSessionService;
	}

	@Override
	public void setWrappedService(CDTSessionService cdtSessionService) {
		_cdtSessionService = cdtSessionService;
	}

	private CDTSessionService _cdtSessionService;

}