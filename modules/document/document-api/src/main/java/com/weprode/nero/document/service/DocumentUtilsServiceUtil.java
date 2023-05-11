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
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Provides the remote service utility for DocumentUtils. This utility wraps
 * <code>com.weprode.nero.document.service.impl.DocumentUtilsServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DocumentUtilsService
 * @generated
 */
public class DocumentUtilsServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.DocumentUtilsServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject getDocumentGroupActivity(
		long groupId, String maxDate, int nbResults) {

		return getService().getDocumentGroupActivity(
			groupId, maxDate, nbResults);
	}

	public static org.json.JSONObject getGlobalDocumentsProperties()
		throws PortalException, SystemException {

		return getService().getGlobalDocumentsProperties();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject isEmbedUrlWhitelisted(String url)
		throws SystemException {

		return getService().isEmbedUrlWhitelisted(url);
	}

	public static DocumentUtilsService getService() {
		return _service;
	}

	private static volatile DocumentUtilsService _service;

}