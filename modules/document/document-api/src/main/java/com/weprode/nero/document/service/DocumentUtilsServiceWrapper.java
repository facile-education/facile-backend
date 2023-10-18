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
 * Provides a wrapper for {@link DocumentUtilsService}.
 *
 * @author Brian Wing Shun Chan
 * @see DocumentUtilsService
 * @generated
 */
public class DocumentUtilsServiceWrapper
	implements DocumentUtilsService, ServiceWrapper<DocumentUtilsService> {

	public DocumentUtilsServiceWrapper() {
		this(null);
	}

	public DocumentUtilsServiceWrapper(
		DocumentUtilsService documentUtilsService) {

		_documentUtilsService = documentUtilsService;
	}

	@Override
	public org.json.JSONObject getDocumentGroupActivity(
		long groupId, String maxDate, int nbResults) {

		return _documentUtilsService.getDocumentGroupActivity(
			groupId, maxDate, nbResults);
	}

	@Override
	public org.json.JSONObject getGlobalDocumentsProperties()
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.exception.SystemException {

		return _documentUtilsService.getGlobalDocumentsProperties();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _documentUtilsService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject isEmbedUrlWhitelisted(String url)
		throws com.liferay.portal.kernel.exception.SystemException {

		return _documentUtilsService.isEmbedUrlWhitelisted(url);
	}

	@Override
	public DocumentUtilsService getWrappedService() {
		return _documentUtilsService;
	}

	@Override
	public void setWrappedService(DocumentUtilsService documentUtilsService) {
		_documentUtilsService = documentUtilsService;
	}

	private DocumentUtilsService _documentUtilsService;

}