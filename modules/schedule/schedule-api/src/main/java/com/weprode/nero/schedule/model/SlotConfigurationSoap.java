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

package com.weprode.nero.schedule.model;

import com.weprode.nero.schedule.service.persistence.SlotConfigurationPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.schedule.service.http.SlotConfigurationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class SlotConfigurationSoap implements Serializable {

	public static SlotConfigurationSoap toSoapModel(SlotConfiguration model) {
		SlotConfigurationSoap soapModel = new SlotConfigurationSoap();

		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setSlotNumber(model.getSlotNumber());
		soapModel.setSessionStartHour(model.getSessionStartHour());
		soapModel.setSessionEndHour(model.getSessionEndHour());

		return soapModel;
	}

	public static SlotConfigurationSoap[] toSoapModels(
		SlotConfiguration[] models) {

		SlotConfigurationSoap[] soapModels =
			new SlotConfigurationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SlotConfigurationSoap[][] toSoapModels(
		SlotConfiguration[][] models) {

		SlotConfigurationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new SlotConfigurationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SlotConfigurationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SlotConfigurationSoap[] toSoapModels(
		List<SlotConfiguration> models) {

		List<SlotConfigurationSoap> soapModels =
			new ArrayList<SlotConfigurationSoap>(models.size());

		for (SlotConfiguration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SlotConfigurationSoap[soapModels.size()]);
	}

	public SlotConfigurationSoap() {
	}

	public SlotConfigurationPK getPrimaryKey() {
		return new SlotConfigurationPK(_schoolId, _slotNumber);
	}

	public void setPrimaryKey(SlotConfigurationPK pk) {
		setSchoolId(pk.schoolId);
		setSlotNumber(pk.slotNumber);
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	public int getSlotNumber() {
		return _slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		_slotNumber = slotNumber;
	}

	public String getSessionStartHour() {
		return _sessionStartHour;
	}

	public void setSessionStartHour(String sessionStartHour) {
		_sessionStartHour = sessionStartHour;
	}

	public String getSessionEndHour() {
		return _sessionEndHour;
	}

	public void setSessionEndHour(String sessionEndHour) {
		_sessionEndHour = sessionEndHour;
	}

	private long _schoolId;
	private int _slotNumber;
	private String _sessionStartHour;
	private String _sessionEndHour;

}