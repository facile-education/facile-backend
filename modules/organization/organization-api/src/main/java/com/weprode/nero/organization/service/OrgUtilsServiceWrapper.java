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

package com.weprode.nero.organization.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OrgUtilsService}.
 *
 * @author Marc Salvat
 * @see OrgUtilsService
 * @generated
 */
public class OrgUtilsServiceWrapper
	implements OrgUtilsService, ServiceWrapper<OrgUtilsService> {

	public OrgUtilsServiceWrapper(OrgUtilsService orgUtilsService) {
		_orgUtilsService = orgUtilsService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getAllSchools() {
		return _orgUtilsService.getAllSchools();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _orgUtilsService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getSchoolClasses(
		long schoolId, boolean includeCours) {

		return _orgUtilsService.getSchoolClasses(schoolId, includeCours);
	}

	/**
	 * Return the volee list for given schoolId or all the volees in user school list
	 * if schoolId is 0
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject getSchoolVolees(
		long schoolId) {

		return _orgUtilsService.getSchoolVolees(schoolId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getVisibilitySchools() {
		return _orgUtilsService.getVisibilitySchools();
	}

	@Override
	public OrgUtilsService getWrappedService() {
		return _orgUtilsService;
	}

	@Override
	public void setWrappedService(OrgUtilsService orgUtilsService) {
		_orgUtilsService = orgUtilsService;
	}

	private OrgUtilsService _orgUtilsService;

}