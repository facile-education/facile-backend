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

package com.weprode.nero.maintenance.service;

/**
 * Provides the remote service utility for Maintenance. This utility wraps
 * <code>com.weprode.nero.maintenance.service.impl.MaintenanceServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MaintenanceService
 * @generated
 */
public class MaintenanceServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.maintenance.service.impl.MaintenanceServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject addPermissions() {
		return getService().addPermissions();
	}

	public static org.json.JSONObject cleanupDropboxes() {
		return getService().cleanupDropboxes();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject runAnonymisation() {
		return getService().runAnonymisation();
	}

	public static org.json.JSONObject startFsAnalysis() {
		return getService().startFsAnalysis();
	}

	public static org.json.JSONObject startFsAnalysisV2() {
		return getService().startFsAnalysisV2();
	}

	public static org.json.JSONObject startParentSynchro() {
		return getService().startParentSynchro();
	}

	public static org.json.JSONObject startSynchro() {
		return getService().startSynchro();
	}

	public static MaintenanceService getService() {
		return _service;
	}

	private static volatile MaintenanceService _service;

}