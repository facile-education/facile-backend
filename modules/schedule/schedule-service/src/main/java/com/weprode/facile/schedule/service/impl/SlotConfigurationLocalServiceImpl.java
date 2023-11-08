/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.schedule.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.facile.commons.constants.JSONConstants;
import com.weprode.facile.schedule.model.SlotConfiguration;
import com.weprode.facile.schedule.service.base.SlotConfigurationLocalServiceBaseImpl;
import com.weprode.facile.schedule.service.persistence.SlotConfigurationPK;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.facile.schedule.model.SlotConfiguration",
	service = AopService.class
)
public class SlotConfigurationLocalServiceImpl
	extends SlotConfigurationLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(SlotConfigurationLocalServiceImpl.class);

	public void saveSchoolSlots(long schoolId, JSONArray jsonSlots) {

		// First delete all existing slots
		slotConfigurationPersistence.removeByschoolId(schoolId);

		// Then re-create them
		for (int i = 0 ; i < jsonSlots.length() ; i++) {
			JSONObject jsonSlot = jsonSlots.getJSONObject(i);
			int slotNumber = jsonSlot.getInt(JSONConstants.SLOT_NUMBER);
			String sessionStartHour = jsonSlot.getString(JSONConstants.SLOT_START_HOUR);
			String sessionEndHour = jsonSlot.getString(JSONConstants.SLOT_END_HOUR);
			addSchoolSlot(schoolId, slotNumber, sessionStartHour, sessionEndHour);
		}
	}

	public void addSchoolSlot(long schoolId, int slotNumber, String sessionStartHour, String sessionEndHour) {
		try {
			SlotConfiguration slotConfiguration = slotConfigurationPersistence.create(new SlotConfigurationPK(schoolId, slotNumber));
			slotConfiguration.setSessionStartHour(sessionStartHour);
			slotConfiguration.setSessionEndHour(sessionEndHour);
			slotConfigurationPersistence.update(slotConfiguration);
		} catch (Exception e) {
			logger.error("Error while adding slot configuration for schoolId "+schoolId + "and slotNumber " + slotNumber, e);
		}
	}

	public List<SlotConfiguration> getSchoolSlots(long schoolId) {

		try {
			return slotConfigurationPersistence.findByschoolId(schoolId);
		} catch (Exception e) {
			logger.error("Error while fetching schedule configuration for schoolId "+schoolId, e);
		}
		return new ArrayList<>();
	}

	public JSONArray getSchoolSlotsAsJson(long schoolId) {
		JSONArray jsonSlots = new JSONArray();
		try {
			List<SlotConfiguration> slots = slotConfigurationPersistence.findByschoolId(schoolId);
			if (slots != null) {
				for (SlotConfiguration slot : slots) {
					JSONObject jsonSlot = new JSONObject();
					jsonSlot.put(JSONConstants.SLOT_NUMBER, slot.getSlotNumber());
					jsonSlot.put(JSONConstants.SLOT_START_HOUR, slot.getSessionStartHour());
					jsonSlot.put(JSONConstants.SLOT_END_HOUR, slot.getSessionEndHour());
					jsonSlots.put(jsonSlot);
				}
			}
		} catch (Exception e) {
			logger.error("Error while fetching schedule configuration for schoolId "+schoolId, e);
		}
		return jsonSlots;
	}

	public void deleteSchoolSlots(long schoolId) {
		try {
			slotConfigurationPersistence.removeByschoolId(schoolId);
		} catch (Exception e) {
			logger.error("Error while deleting the daily schedule configuration for schoolId "+schoolId, e);
		}
	}

}