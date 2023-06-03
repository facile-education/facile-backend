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
 * Provides the remote service utility for ScheduleConfiguration. This utility wraps
 * <code>com.weprode.nero.schedule.service.impl.ScheduleConfigurationServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleConfigurationService
 * @generated
 */
public class ScheduleConfigurationServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.schedule.service.impl.ScheduleConfigurationServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject getConfiguration(
		long schoolId, long childId) {

		return getService().getConfiguration(schoolId, childId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getScheduleConfiguration() {
		return getService().getScheduleConfiguration();
	}

	public static org.json.JSONObject saveScheduleConfiguration(
		java.lang.String startDateStr, java.lang.String semesterDateStr,
		java.lang.String endDateStr, java.lang.String holidays,
		java.lang.String h1Weeks, java.lang.String h2Weeks) {

		return getService().saveScheduleConfiguration(
			startDateStr, semesterDateStr, endDateStr, holidays, h1Weeks,
			h2Weeks);
	}

	public static ScheduleConfigurationService getService() {
		return _service;
	}

	private static volatile ScheduleConfigurationService _service;

}