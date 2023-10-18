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
 * Provides a wrapper for {@link WYSIWYGService}.
 *
 * @author Brian Wing Shun Chan
 * @see WYSIWYGService
 * @generated
 */
public class WYSIWYGServiceWrapper
	implements ServiceWrapper<WYSIWYGService>, WYSIWYGService {

	public WYSIWYGServiceWrapper() {
		this(null);
	}

	public WYSIWYGServiceWrapper(WYSIWYGService wysiwygService) {
		_wysiwygService = wysiwygService;
	}

	@Override
	public org.json.JSONObject getHTMLContent(long fileVersionId) {
		return _wysiwygService.getHTMLContent(fileVersionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _wysiwygService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject saveHTMLContent(
		Long fileVersionId, String content, boolean majorVersion) {

		return _wysiwygService.saveHTMLContent(
			fileVersionId, content, majorVersion);
	}

	@Override
	public WYSIWYGService getWrappedService() {
		return _wysiwygService;
	}

	@Override
	public void setWrappedService(WYSIWYGService wysiwygService) {
		_wysiwygService = wysiwygService;
	}

	private WYSIWYGService _wysiwygService;

}