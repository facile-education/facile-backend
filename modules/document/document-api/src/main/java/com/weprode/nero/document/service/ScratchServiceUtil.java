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
 * Provides the remote service utility for Scratch. This utility wraps
 * <code>com.weprode.nero.document.service.impl.ScratchServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ScratchService
 * @generated
 */
public class ScratchServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.ScratchServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * Returns the content of the given scratch file
	 *
	 * @return JSONObject - the scratch file name and content
	 */
	public static org.json.JSONObject getScratchFile(long fileVersionId) {
		return getService().getScratchFile(fileVersionId);
	}

	/**
	 * Returns the scratch files in the user's schoolbag
	 *
	 * @return JSONObject with all user's scratch files
	 */
	public static org.json.JSONObject getScratchFiles(long userId) {
		return getService().getScratchFiles(userId);
	}

	/**
	 * This method saves a scratch file in the user's schoolbag
	 *
	 * @param params - The map containing userId, fileEntryId, fileName and content
	 * @return JSONObject success or not
	 */
	public static org.json.JSONObject saveScratchFile(java.lang.String params) {
		return getService().saveScratchFile(params);
	}

	public static ScratchService getService() {
		return _service;
	}

	private static volatile ScratchService _service;

}