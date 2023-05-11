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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DashboardService}.
 *
 * @author Brian Wing Shun Chan
 * @see DashboardService
 * @generated
 */
public class DashboardServiceWrapper
	implements DashboardService, ServiceWrapper<DashboardService> {

	public DashboardServiceWrapper(DashboardService dashboardService) {
		_dashboardService = dashboardService;
	}

	@Override
	public org.json.JSONObject getDashboardActivity(
		String maxDate, int nbResults, boolean withNews, boolean withDocs,
		boolean withSchoollife, boolean withSessions) {

		return _dashboardService.getDashboardActivity(
			maxDate, nbResults, withNews, withDocs, withSchoollife,
			withSessions);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _dashboardService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getUserSchedule(
		long userId, String date, boolean goForward) {

		return _dashboardService.getUserSchedule(userId, date, goForward);
	}

	@Override
	public org.json.JSONObject initDashboard() {
		return _dashboardService.initDashboard();
	}

	@Override
	public DashboardService getWrappedService() {
		return _dashboardService;
	}

	@Override
	public void setWrappedService(DashboardService dashboardService) {
		_dashboardService = dashboardService;
	}

	private DashboardService _dashboardService;

}