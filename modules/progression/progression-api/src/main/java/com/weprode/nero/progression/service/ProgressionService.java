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

package com.weprode.nero.progression.service;

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
 * Provides the remote service interface for Progression. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ProgressionService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.nero.progression.service.impl.ProgressionServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the progression remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link ProgressionServiceUtil} if injection and service tracking are not available.
	 */
	@JSONWebService(method = "POST", value = "add-progression")
	public JSONObject addProgression(
		String name, String description, long subjectId, String volee,
		String color);

	@JSONWebService(method = "DELETE", value = "delete-progression")
	public JSONObject deleteProgression(long progressionId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "GET", value = "get-progression-list")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getProgressionList();

	@JSONWebService(method = "GET", value = "get-progression-content")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getProgressionTree(long progressionId);

	@JSONWebService(method = "POST", value = "update-progression")
	public JSONObject updateProgression(
		long progressionId, String name, String description, long subjectId,
		String volee, String color);

}