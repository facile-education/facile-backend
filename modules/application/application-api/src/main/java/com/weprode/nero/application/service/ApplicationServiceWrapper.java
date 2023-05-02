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

package com.weprode.nero.application.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ApplicationService}.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationService
 * @generated
 */
public class ApplicationServiceWrapper
	implements ApplicationService, ServiceWrapper<ApplicationService> {

	public ApplicationServiceWrapper(ApplicationService applicationService) {
		_applicationService = applicationService;
	}

	@Override
	public org.json.JSONObject addApplication(
		String applicationName, String applicationKey, String category,
		long menuEntryId, String image, boolean hasCustomUrl, String globalUrl,
		boolean exportUser, boolean exportStudent, boolean exportParent,
		boolean exportTeacher, boolean exportOther, String defaultRoles,
		String authorizedSchools) {

		return _applicationService.addApplication(
			applicationName, applicationKey, category, menuEntryId, image,
			hasCustomUrl, globalUrl, exportUser, exportStudent, exportParent,
			exportTeacher, exportOther, defaultRoles, authorizedSchools);
	}

	@Override
	public org.json.JSONObject editApplication(
		long applicationId, String applicationName, String applicationKey,
		String category, long menuEntryId, String image, boolean hasCustomUrl,
		String globalUrl, boolean exportUser, boolean exportStudent,
		boolean exportParent, boolean exportTeacher, boolean exportOther,
		String defaultRoles, String authorizedSchools) {

		return _applicationService.editApplication(
			applicationId, applicationName, applicationKey, category,
			menuEntryId, image, hasCustomUrl, globalUrl, exportUser,
			exportStudent, exportParent, exportTeacher, exportOther,
			defaultRoles, authorizedSchools);
	}

	@Override
	public org.json.JSONObject export(
		long applicationId, long schoolId, String roleName) {

		return _applicationService.export(applicationId, schoolId, roleName);
	}

	@Override
	public org.json.JSONObject getAllApplications() {
		return _applicationService.getAllApplications();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicationService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getPortlets() {
		return _applicationService.getPortlets();
	}

	@Override
	public org.json.JSONObject getSchoolApplications(long schoolId) {
		return _applicationService.getSchoolApplications(schoolId);
	}

	@Override
	public org.json.JSONObject getStatApplications(long schoolId) {
		return _applicationService.getStatApplications(schoolId);
	}

	@Override
	public org.json.JSONObject getUserApplications() {
		return _applicationService.getUserApplications();
	}

	@Override
	public org.json.JSONObject removeApplication(long applicationId) {
		return _applicationService.removeApplication(applicationId);
	}

	@Override
	public ApplicationService getWrappedService() {
		return _applicationService;
	}

	@Override
	public void setWrappedService(ApplicationService applicationService) {
		_applicationService = applicationService;
	}

	private ApplicationService _applicationService;

}