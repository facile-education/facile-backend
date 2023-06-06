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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ThumbnailsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ThumbnailsLocalService
 * @generated
 */
public class ThumbnailsLocalServiceWrapper
	implements ServiceWrapper<ThumbnailsLocalService>, ThumbnailsLocalService {

	public ThumbnailsLocalServiceWrapper(
		ThumbnailsLocalService thumbnailsLocalService) {

		_thumbnailsLocalService = thumbnailsLocalService;
	}

	@Override
	public com.liferay.portal.kernel.repository.model.FileEntry
			createThumbnailFile(
				long userId, long sourceFileId, String thumbnailName)
		throws com.liferay.portal.kernel.exception.PortalException,
			   java.io.IOException {

		return _thumbnailsLocalService.createThumbnailFile(
			userId, sourceFileId, thumbnailName);
	}

	@Override
	public void deleteThumbnailFile(long thumbnailId) {
		_thumbnailsLocalService.deleteThumbnailFile(thumbnailId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _thumbnailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public ThumbnailsLocalService getWrappedService() {
		return _thumbnailsLocalService;
	}

	@Override
	public void setWrappedService(
		ThumbnailsLocalService thumbnailsLocalService) {

		_thumbnailsLocalService = thumbnailsLocalService;
	}

	private ThumbnailsLocalService _thumbnailsLocalService;

}