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
 * Provides a wrapper for {@link VersionService}.
 *
 * @author Brian Wing Shun Chan
 * @see VersionService
 * @generated
 */
public class VersionServiceWrapper
	implements ServiceWrapper<VersionService>, VersionService {

	public VersionServiceWrapper() {
		this(null);
	}

	public VersionServiceWrapper(VersionService versionService) {
		_versionService = versionService;
	}

	@Override
	public org.json.JSONObject createMajorVersion(long fileEntryId) {
		return _versionService.createMajorVersion(fileEntryId);
	}

	@Override
	public org.json.JSONObject deleteVersion(long fileEntryId, String version) {
		return _versionService.deleteVersion(fileEntryId, version);
	}

	@Override
	public org.json.JSONObject getFileVersions(long fileId) {
		return _versionService.getFileVersions(fileId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _versionService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject restoreVersion(long fileVersionId) {
		return _versionService.restoreVersion(fileVersionId);
	}

	@Override
	public org.json.JSONObject saveVersionDescription(
		long fileVersionId, String description) {

		return _versionService.saveVersionDescription(
			fileVersionId, description);
	}

	@Override
	public VersionService getWrappedService() {
		return _versionService;
	}

	@Override
	public void setWrappedService(VersionService versionService) {
		_versionService = versionService;
	}

	private VersionService _versionService;

}