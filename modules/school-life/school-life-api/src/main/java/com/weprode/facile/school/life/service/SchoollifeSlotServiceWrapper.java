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

package com.weprode.facile.school.life.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SchoollifeSlotService}.
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSlotService
 * @generated
 */
public class SchoollifeSlotServiceWrapper
	implements SchoollifeSlotService, ServiceWrapper<SchoollifeSlotService> {

	public SchoollifeSlotServiceWrapper() {
		this(null);
	}

	public SchoollifeSlotServiceWrapper(
		SchoollifeSlotService schoollifeSlotService) {

		_schoollifeSlotService = schoollifeSlotService;
	}

	@Override
	public org.json.JSONObject createSlot(
		long schoolId, String startDateStr, String endDateStr, int day,
		String startHour, String endHour, long teacherId, int type, String room,
		int capacity) {

		return _schoollifeSlotService.createSlot(
			schoolId, startDateStr, endDateStr, day, startHour, endHour,
			teacherId, type, room, capacity);
	}

	@Override
	public org.json.JSONObject deleteSlot(
		long schoollifeSessionId, String startDateStr, String endDateStr) {

		return _schoollifeSlotService.deleteSlot(
			schoollifeSessionId, startDateStr, endDateStr);
	}

	@Override
	public org.json.JSONObject editSlot(
		long schoollifeSessionId, String startDateStr, String endDateStr,
		int newDay, String newStartHour, String newEndHour, long newTeacherId,
		String newRoom, int newCapacity) {

		return _schoollifeSlotService.editSlot(
			schoollifeSessionId, startDateStr, endDateStr, newDay, newStartHour,
			newEndHour, newTeacherId, newRoom, newCapacity);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _schoollifeSlotService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSessionLimitSlotDate(
		long schoollifeSessionId) {

		return _schoollifeSlotService.getSessionLimitSlotDate(
			schoollifeSessionId);
	}

	@Override
	public SchoollifeSlotService getWrappedService() {
		return _schoollifeSlotService;
	}

	@Override
	public void setWrappedService(SchoollifeSlotService schoollifeSlotService) {
		_schoollifeSlotService = schoollifeSlotService;
	}

	private SchoollifeSlotService _schoollifeSlotService;

}