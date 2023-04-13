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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for Application. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ApplicationService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.nero.application.service.impl.ApplicationServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the application remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link ApplicationServiceUtil} if injection and service tracking are not available.
	 */
	@JSONWebService(method = "POST", value = "add-application")
	public JSONObject addApplication(
		String applicationName, String applicationKey, String category,
		long menuEntryId, String image, boolean hasCustomUrl, String globalUrl,
		boolean exportUser, boolean exportStudent, boolean exportParent,
		boolean exportTeacher, boolean exportOther, String defaultRoles,
		String authorizedSchools);

	@JSONWebService(method = "POST", value = "edit-application")
	public JSONObject editApplication(
		long applicationId, String applicationName, String applicationKey,
		String category, long menuEntryId, String image, boolean hasCustomUrl,
		String globalUrl, boolean exportUser, boolean exportStudent,
		boolean exportParent, boolean exportTeacher, boolean exportOther,
		String defaultRoles, String authorizedSchools);

	@JSONWebService(method = "GET", value = "export")
	public JSONObject export(
		long applicationId, long schoolId, String roleName);

	@JSONWebService(method = "GET", value = "get-all-applications")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getAllApplications();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "GET", value = "get-portlets")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getPortlets();

	@JSONWebService(method = "GET", value = "get-school-applications")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getSchoolApplications(long schoolId);

	@JSONWebService(method = "GET", value = "get-stat-applications")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getStatApplications(long schoolId);

	@JSONWebService(method = "GET", value = "get-user-applications")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getUserApplications();

	@JSONWebService(method = "GET", value = "remove-application")
	public JSONObject removeApplication(long applicationId);

}