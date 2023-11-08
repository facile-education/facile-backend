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

package com.weprode.facile.document.service;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * Provides the remote service utility for Mindmap. This utility wraps
 * <code>com.weprode.facile.document.service.impl.MindmapServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MindmapService
 * @generated
 */
public class MindmapServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.facile.document.service.impl.MindmapServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the content of the given mindmap file
	 *
	 * @return JSONObject - the mindmap file name and content
	 */
	public static org.json.JSONObject getMindFile(long fileVersionId) {
		return getService().getMindFile(fileVersionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * This method saves a mindmap file
	 *
	 * @param params - The map containing fileVersionId, fileName and content
	 * @return JSONObject success or not
	 */
	public static org.json.JSONObject saveMindFile(String params)
		throws SystemException {

		return getService().saveMindFile(params);
	}

	public static MindmapService getService() {
		return _service;
	}

	private static volatile MindmapService _service;

}