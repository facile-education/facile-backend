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

package com.weprode.facile.maintenance.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MaintenanceService}.
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceService
 * @generated
 */
public class MaintenanceServiceWrapper
	implements MaintenanceService, ServiceWrapper<MaintenanceService> {

	public MaintenanceServiceWrapper() {
		this(null);
	}

	public MaintenanceServiceWrapper(MaintenanceService maintenanceService) {
		_maintenanceService = maintenanceService;
	}

	@Override
	public org.json.JSONObject addPermissions() {
		return _maintenanceService.addPermissions();
	}

	@Override
	public org.json.JSONObject cleanupObsoleteFolders() {
		return _maintenanceService.cleanupObsoleteFolders();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _maintenanceService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject runAnonymisation() {
		return _maintenanceService.runAnonymisation();
	}

	@Override
	public org.json.JSONObject runDataFeed() {
		return _maintenanceService.runDataFeed();
	}

	@Override
	public org.json.JSONObject setNewsPermissions() {
		return _maintenanceService.setNewsPermissions();
	}

	@Override
	public org.json.JSONObject startFsAnalysis() {
		return _maintenanceService.startFsAnalysis();
	}

	@Override
	public org.json.JSONObject startFsAnalysisV2() {
		return _maintenanceService.startFsAnalysisV2();
	}

	@Override
	public org.json.JSONObject startParentSynchro() {
		return _maintenanceService.startParentSynchro();
	}

	@Override
	public org.json.JSONObject startSynchro() {
		return _maintenanceService.startSynchro();
	}

	@Override
	public MaintenanceService getWrappedService() {
		return _maintenanceService;
	}

	@Override
	public void setWrappedService(MaintenanceService maintenanceService) {
		_maintenanceService = maintenanceService;
	}

	private MaintenanceService _maintenanceService;

}