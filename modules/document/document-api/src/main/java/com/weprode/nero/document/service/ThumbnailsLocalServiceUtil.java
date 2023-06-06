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
 * Provides the local service utility for Thumbnails. This utility wraps
 * <code>com.weprode.nero.document.service.impl.ThumbnailsLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ThumbnailsLocalService
 * @generated
 */
public class ThumbnailsLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.document.service.impl.ThumbnailsLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.repository.model.FileEntry
			createThumbnailFile(
				long userId, long sourceFileId, String thumbnailName)
		throws java.io.IOException, PortalException {

		return getService().createThumbnailFile(
			userId, sourceFileId, thumbnailName);
	}

	public static void deleteThumbnailFile(long thumbnailId) {
		getService().deleteThumbnailFile(thumbnailId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ThumbnailsLocalService getService() {
		return _service;
	}

	private static volatile ThumbnailsLocalService _service;

}