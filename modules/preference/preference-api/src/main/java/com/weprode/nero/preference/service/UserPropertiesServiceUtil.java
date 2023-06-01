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

/**
 * Provides the remote service utility for UserProperties. This utility wraps
 * <code>com.weprode.nero.preference.service.impl.UserPropertiesServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see UserPropertiesService
 * @generated
 */
public class UserPropertiesServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.preference.service.impl.UserPropertiesServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject updateReportFrequency(int frequency) {
		return getService().updateReportFrequency(frequency);
	}

	public static org.json.JSONObject updateSideMenuState(boolean isExpanded) {
		return getService().updateSideMenuState(isExpanded);
	}

	public static org.json.JSONObject updateThemeColor(java.lang.String color) {
		return getService().updateThemeColor(color);
	}

	public static org.json.JSONObject updateUserPicture(java.io.File picture) {
		return getService().updateUserPicture(picture);
	}

	public static org.json.JSONObject updateWebdavPassword(
		java.lang.String password, java.lang.String confirmPassword) {

		return getService().updateWebdavPassword(password, confirmPassword);
	}

	public static org.json.JSONObject updateWebdavState(boolean isEnabled) {
		return getService().updateWebdavState(isEnabled);
	}

	public static UserPropertiesService getService() {
		return _service;
	}

	private static volatile UserPropertiesService _service;

}