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

/**
 * Provides the remote service utility for Clipboard. This utility wraps
 * <code>com.weprode.nero.document.service.impl.ClipboardServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ClipboardService
 * @generated
 */
public class ClipboardServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.ClipboardServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject copy(
			java.lang.String folderIds, java.lang.String fileIds,
			long targetFolderId, int mode)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().copy(folderIds, fileIds, targetFolderId, mode);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject move(
			java.lang.String folderIds, java.lang.String fileIds,
			long targetFolderId, int mode)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return getService().move(folderIds, fileIds, targetFolderId, mode);
	}

	public static ClipboardService getService() {
		return _service;
	}

	private static volatile ClipboardService _service;

}