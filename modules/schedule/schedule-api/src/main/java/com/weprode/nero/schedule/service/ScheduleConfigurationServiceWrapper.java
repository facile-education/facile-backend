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
 * Provides a wrapper for {@link ScheduleConfigurationService}.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleConfigurationService
 * @generated
 */
public class ScheduleConfigurationServiceWrapper
	implements ScheduleConfigurationService,
			   ServiceWrapper<ScheduleConfigurationService> {

	public ScheduleConfigurationServiceWrapper(
		ScheduleConfigurationService scheduleConfigurationService) {

		_scheduleConfigurationService = scheduleConfigurationService;
	}

	@Override
	public org.json.JSONObject getConfiguration(long schoolId, long childId) {
		return _scheduleConfigurationService.getConfiguration(
			schoolId, childId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _scheduleConfigurationService.getOSGiServiceIdentifier();
	}

	@Override
	public ScheduleConfigurationService getWrappedService() {
		return _scheduleConfigurationService;
	}

	@Override
	public void setWrappedService(
		ScheduleConfigurationService scheduleConfigurationService) {

		_scheduleConfigurationService = scheduleConfigurationService;
	}

	private ScheduleConfigurationService _scheduleConfigurationService;

}