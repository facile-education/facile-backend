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

/**
 * Provides the remote service utility for CDTSession. This utility wraps
 * <code>com.weprode.nero.schedule.service.impl.CDTSessionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see CDTSessionService
 * @generated
 */
public class CDTSessionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.schedule.service.impl.CDTSessionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject createSession(
		long groupId, java.lang.String subject, java.lang.String room,
		java.lang.String startDate, java.lang.String endDate,
		java.lang.String teacherIds, boolean isRecurrent) {

		return getService().createSession(
			groupId, subject, room, startDate, endDate, teacherIds,
			isRecurrent);
	}

	public static org.json.JSONObject getHorairesSessions(
		long userId, long groupId, java.lang.String start, java.lang.String end,
		java.lang.String volee) {

		return getService().getHorairesSessions(
			userId, groupId, start, end, volee);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getSessionDetails(long sessionId) {
		return getService().getSessionDetails(sessionId);
	}

	public static org.json.JSONObject getTeacherGroups() {
		return getService().getTeacherGroups();
	}

	public static CDTSessionService getService() {
		return _service;
	}

	private static volatile CDTSessionService _service;

}