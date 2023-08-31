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

package com.weprode.nero.school.life.service;

/**
 * Provides the remote service utility for SchoollifeSlot. This utility wraps
 * <code>com.weprode.nero.school.life.service.impl.SchoollifeSlotServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSlotService
 * @generated
 */
public class SchoollifeSlotServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.school.life.service.impl.SchoollifeSlotServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject createSlot(
		long schoolId, java.lang.String startDateStr,
		java.lang.String endDateStr, int day, java.lang.String startHour,
		java.lang.String endHour, long teacherId, int type,
		java.lang.String room, int capacity) {

		return getService().createSlot(
			schoolId, startDateStr, endDateStr, day, startHour, endHour,
			teacherId, type, room, capacity);
	}

	public static org.json.JSONObject deleteSlot(
		long schoollifeSessionId, java.lang.String startDateStr,
		java.lang.String endDateStr) {

		return getService().deleteSlot(
			schoollifeSessionId, startDateStr, endDateStr);
	}

	public static org.json.JSONObject editSlot(
		long schoollifeSessionId, java.lang.String startDateStr,
		java.lang.String endDateStr, int newDay, java.lang.String newStartHour,
		java.lang.String newEndHour, long newTeacherId,
		java.lang.String newRoom, int newCapacity) {

		return getService().editSlot(
			schoollifeSessionId, startDateStr, endDateStr, newDay, newStartHour,
			newEndHour, newTeacherId, newRoom, newCapacity);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getSessionLimitSlotDate(
		long schoollifeSessionId) {

		return getService().getSessionLimitSlotDate(schoollifeSessionId);
	}

	public static SchoollifeSlotService getService() {
		return _service;
	}

	private static volatile SchoollifeSlotService _service;

}