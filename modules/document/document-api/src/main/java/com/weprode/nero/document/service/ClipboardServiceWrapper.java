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
 * Provides a wrapper for {@link ClipboardService}.
 *
 * @author Brian Wing Shun Chan
 * @see ClipboardService
 * @generated
 */
public class ClipboardServiceWrapper
	implements ClipboardService, ServiceWrapper<ClipboardService> {

	public ClipboardServiceWrapper(ClipboardService clipboardService) {
		_clipboardService = clipboardService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject copy(
			String folderIds, String fileIds, long targetFolderId, int mode)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return _clipboardService.copy(folderIds, fileIds, targetFolderId, mode);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _clipboardService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject move(
			String folderIds, String fileIds, long targetFolderId, int mode)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return _clipboardService.move(folderIds, fileIds, targetFolderId, mode);
	}

	@Override
	public ClipboardService getWrappedService() {
		return _clipboardService;
	}

	@Override
	public void setWrappedService(ClipboardService clipboardService) {
		_clipboardService = clipboardService;
	}

	private ClipboardService _clipboardService;

}