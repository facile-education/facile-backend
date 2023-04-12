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
 * Provides the local service utility for DocumentUtils. This utility wraps
 * <code>com.weprode.nero.document.service.impl.DocumentUtilsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DocumentUtilsLocalService
 * @generated
 */
public class DocumentUtilsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.DocumentUtilsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.lang.String getWebDavUrl(
		com.liferay.portal.kernel.model.User user) {

		return getService().getWebDavUrl(user);
	}

	public static boolean isEmbedUrlWhitelisted(java.lang.String url) {
		return getService().isEmbedUrlWhitelisted(url);
	}

	public static DocumentUtilsLocalService getService() {
		return _service;
	}

	private static volatile DocumentUtilsLocalService _service;

}