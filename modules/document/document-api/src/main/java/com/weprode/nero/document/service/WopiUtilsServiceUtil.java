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

package com.weprode.nero.document.service;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for WopiUtils. This utility wraps
 * <code>com.weprode.nero.document.service.impl.WopiUtilsServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see WopiUtilsService
 * @generated
 */
public class WopiUtilsServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.WopiUtilsServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject getFileAction(
			java.io.File file, javax.servlet.http.HttpServletResponse response,
			String accessToken, String wopiParam)
		throws PortalException {

		return getService().getFileAction(
			file, response, accessToken, wopiParam);
	}

	public static com.liferay.portal.kernel.json.JSONObject getFileAction(
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, String accessToken,
			String wopiParam)
		throws PortalException {

		return getService().getFileAction(
			request, response, accessToken, wopiParam);
	}

	public static com.liferay.portal.kernel.json.JSONObject getFileInfo(
			javax.servlet.http.HttpServletResponse response, String accessToken,
			String wopiParam)
		throws java.io.IOException, PortalException {

		return getService().getFileInfo(response, accessToken, wopiParam);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static WopiUtilsService getService() {
		return _service;
	}

	private static volatile WopiUtilsService _service;

}