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

package com.weprode.nero.dashboard.service;

/**
 * Provides the remote service utility for Dashboard. This utility wraps
 * <code>com.weprode.nero.dashboard.service.impl.DashboardServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DashboardService
 * @generated
 */
public class DashboardServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.dashboard.service.impl.DashboardServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject checkDashboardParameter(
		long dashboardId) {

		return getService().checkDashboardParameter(dashboardId);
	}

	public static org.json.JSONObject getDashboardActivity(
		long groupId, java.lang.String maxDate, int nbResults, boolean withNews,
		boolean withDocs, boolean withMemberships, boolean withSchoollife,
		boolean withSessions) {

		return getService().getDashboardActivity(
			groupId, maxDate, nbResults, withNews, withDocs, withMemberships,
			withSchoollife, withSessions);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getUserSchedule(
		long userId, java.lang.String date, boolean goForward) {

		return getService().getUserSchedule(userId, date, goForward);
	}

	public static org.json.JSONObject initDashboard() {
		return getService().initDashboard();
	}

	public static DashboardService getService() {
		return _service;
	}

	private static volatile DashboardService _service;

}