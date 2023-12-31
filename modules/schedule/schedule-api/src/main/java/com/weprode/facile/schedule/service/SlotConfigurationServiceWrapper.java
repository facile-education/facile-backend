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

package com.weprode.facile.schedule.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SlotConfigurationService}.
 *
 * @author Brian Wing Shun Chan
 * @see SlotConfigurationService
 * @generated
 */
public class SlotConfigurationServiceWrapper
	implements ServiceWrapper<SlotConfigurationService>,
			   SlotConfigurationService {

	public SlotConfigurationServiceWrapper() {
		this(null);
	}

	public SlotConfigurationServiceWrapper(
		SlotConfigurationService slotConfigurationService) {

		_slotConfigurationService = slotConfigurationService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _slotConfigurationService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSchoolSlotConfiguration(long schoolId) {
		return _slotConfigurationService.getSchoolSlotConfiguration(schoolId);
	}

	@Override
	public org.json.JSONObject saveSchoolSlotConfiguration(
		long schoolId, String jsonConfig) {

		return _slotConfigurationService.saveSchoolSlotConfiguration(
			schoolId, jsonConfig);
	}

	@Override
	public SlotConfigurationService getWrappedService() {
		return _slotConfigurationService;
	}

	@Override
	public void setWrappedService(
		SlotConfigurationService slotConfigurationService) {

		_slotConfigurationService = slotConfigurationService;
	}

	private SlotConfigurationService _slotConfigurationService;

}