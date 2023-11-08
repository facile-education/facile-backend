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
 * Provides a wrapper for {@link ScratchService}.
 *
 * @author Brian Wing Shun Chan
 * @see ScratchService
 * @generated
 */
public class ScratchServiceWrapper
	implements ScratchService, ServiceWrapper<ScratchService> {

	public ScratchServiceWrapper() {
		this(null);
	}

	public ScratchServiceWrapper(ScratchService scratchService) {
		_scratchService = scratchService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _scratchService.getOSGiServiceIdentifier();
	}

	/**
	 * Returns the content of the given scratch file
	 *
	 * @return JSONObject - the scratch file name and content
	 */
	@Override
	public org.json.JSONObject getScratchFile(long fileVersionId) {
		return _scratchService.getScratchFile(fileVersionId);
	}

	/**
	 * Returns the scratch files in the user's schoolbag
	 *
	 * @return JSONObject with all user's scratch files
	 */
	@Override
	public org.json.JSONObject getScratchFiles(long userId) {
		return _scratchService.getScratchFiles(userId);
	}

	/**
	 * This method saves a scratch file in the user's schoolbag
	 *
	 * @param params - The map containing userId, fileEntryId, fileName and content
	 * @return JSONObject success or not
	 */
	@Override
	public org.json.JSONObject saveScratchFile(String params) {
		return _scratchService.saveScratchFile(params);
	}

	@Override
	public ScratchService getWrappedService() {
		return _scratchService;
	}

	@Override
	public void setWrappedService(ScratchService scratchService) {
		_scratchService = scratchService;
	}

	private ScratchService _scratchService;

}