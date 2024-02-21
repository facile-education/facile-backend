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

package com.weprode.facile.user.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for UserManagement. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see UserManagementServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface UserManagementService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.facile.user.service.impl.UserManagementServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the user management remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link UserManagementServiceUtil} if injection and service tracking are not available.
	 */
	@JSONWebService(method = "POST", value = "create-manual-user")
	public JSONObject createManualUser(
		String lastName, String firstName, String email, long roleId,
		long schoolId);

	@JSONWebService(method = "DELETE", value = "delete-manual-user")
	public JSONObject deleteManualUser(long userId);

	@JSONWebService(method = "POST", value = "edit-manual-user")
	public JSONObject editManualUser(
		long userId, String lastName, String firstName, String email,
		long roleId, long schoolId);

	@JSONWebService(method = "GET", value = "get-manual-users")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getManualUsers(
		long schoolId, String search, int start, int limit);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "POST", value = "update-forgotten-password")
	public JSONObject updateForgottenPassword(
		String password, String confirmPassword, String ticketKey);

	@JSONWebService(
		method = "POST", value = "update-password-after-reinit-by-manager"
	)
	public JSONObject updatePasswordAfterReinitByManager(
		String password, String confirmPassword);

	@JSONWebService(method = "POST", value = "update-password-by-manager")
	public JSONObject updatePasswordByManager(long userId, String password);

}