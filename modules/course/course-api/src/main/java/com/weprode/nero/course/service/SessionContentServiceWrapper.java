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

package com.weprode.nero.course.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SessionContentService}.
 *
 * @author Brian Wing Shun Chan
 * @see SessionContentService
 * @generated
 */
public class SessionContentServiceWrapper
	implements ServiceWrapper<SessionContentService>, SessionContentService {

	public SessionContentServiceWrapper() {
		this(null);
	}

	public SessionContentServiceWrapper(
		SessionContentService sessionContentService) {

		_sessionContentService = sessionContentService;
	}

	@Override
	public org.json.JSONObject addSessionContent(
			long sessionId, String title, String blocks, String publicationDate,
			boolean isDraft)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionContentService.addSessionContent(
			sessionId, title, blocks, publicationDate, isDraft);
	}

	@Override
	public org.json.JSONObject deleteSessionContent(long sessionId) {
		return _sessionContentService.deleteSessionContent(sessionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _sessionContentService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSessionContents(long sessionId) {
		return _sessionContentService.getSessionContents(sessionId);
	}

	@Override
	public org.json.JSONObject getSessionPreview(long sessionId) {
		return _sessionContentService.getSessionPreview(sessionId);
	}

	@Override
	public org.json.JSONObject updateSessionContent(
			long sessionId, String title, String blocks, String publicationDate,
			boolean isDraft)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _sessionContentService.updateSessionContent(
			sessionId, title, blocks, publicationDate, isDraft);
	}

	@Override
	public SessionContentService getWrappedService() {
		return _sessionContentService;
	}

	@Override
	public void setWrappedService(SessionContentService sessionContentService) {
		_sessionContentService = sessionContentService;
	}

	private SessionContentService _sessionContentService;

}