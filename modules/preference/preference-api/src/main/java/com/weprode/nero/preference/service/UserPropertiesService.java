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

package com.weprode.nero.preference.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.io.File;

import org.json.JSONObject;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the remote service interface for UserProperties. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see UserPropertiesServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface UserPropertiesService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.weprode.nero.preference.service.impl.UserPropertiesServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the user properties remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link UserPropertiesServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@JSONWebService(method = "POST", value = "update-report-frequency")
	public JSONObject updateReportFrequency(int frequency);

	@JSONWebService(method = "POST", value = "update-side-menu-state")
	public JSONObject updateSideMenuState(boolean isExpanded);

	@JSONWebService(method = "POST", value = "update-theme-color")
	public JSONObject updateThemeColor(String color);

	@JSONWebService(method = "POST", value = "update-user-picture")
	public JSONObject updateUserPicture(File picture);

	@JSONWebService(method = "POST", value = "update-webdav-password")
	public JSONObject updateWebdavPassword(
		String password, String confirmPassword);

	@JSONWebService(method = "POST", value = "update-webdav-state")
	public JSONObject updateWebdavState(boolean isEnabled);

}