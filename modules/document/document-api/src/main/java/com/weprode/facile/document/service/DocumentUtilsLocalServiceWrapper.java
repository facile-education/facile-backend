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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DocumentUtilsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DocumentUtilsLocalService
 * @generated
 */
public class DocumentUtilsLocalServiceWrapper
	implements DocumentUtilsLocalService,
			   ServiceWrapper<DocumentUtilsLocalService> {

	public DocumentUtilsLocalServiceWrapper() {
		this(null);
	}

	public DocumentUtilsLocalServiceWrapper(
		DocumentUtilsLocalService documentUtilsLocalService) {

		_documentUtilsLocalService = documentUtilsLocalService;
	}

	@Override
	public boolean belongToTmpFolder(
			com.liferay.portal.kernel.repository.model.FileEntry fileEntry,
			long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _documentUtilsLocalService.belongToTmpFolder(fileEntry, userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _documentUtilsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public String getWebDavUrl(com.liferay.portal.kernel.model.User user) {
		return _documentUtilsLocalService.getWebDavUrl(user);
	}

	@Override
	public boolean isEmbedUrlWhitelisted(String url) {
		return _documentUtilsLocalService.isEmbedUrlWhitelisted(url);
	}

	@Override
	public DocumentUtilsLocalService getWrappedService() {
		return _documentUtilsLocalService;
	}

	@Override
	public void setWrappedService(
		DocumentUtilsLocalService documentUtilsLocalService) {

		_documentUtilsLocalService = documentUtilsLocalService;
	}

	private DocumentUtilsLocalService _documentUtilsLocalService;

}