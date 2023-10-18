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

package com.weprode.nero.about.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VersionNoteService}.
 *
 * @author Brian Wing Shun Chan
 * @see VersionNoteService
 * @generated
 */
public class VersionNoteServiceWrapper
	implements ServiceWrapper<VersionNoteService>, VersionNoteService {

	public VersionNoteServiceWrapper() {
		this(null);
	}

	public VersionNoteServiceWrapper(VersionNoteService versionNoteService) {
		_versionNoteService = versionNoteService;
	}

	@Override
	public org.json.JSONObject createVersionNote(
		String title, String htmlContent) {

		return _versionNoteService.createVersionNote(title, htmlContent);
	}

	@Override
	public org.json.JSONObject deleteVersionNote(long versionNoteId) {
		return _versionNoteService.deleteVersionNote(versionNoteId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _versionNoteService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getVersionNoteContent(Long versionNoteId) {
		return _versionNoteService.getVersionNoteContent(versionNoteId);
	}

	@Override
	public org.json.JSONObject getVersionNotes() {
		return _versionNoteService.getVersionNotes();
	}

	@Override
	public org.json.JSONObject updateVersionNote(
		long versionNoteId, String title, String htmlContent) {

		return _versionNoteService.updateVersionNote(
			versionNoteId, title, htmlContent);
	}

	@Override
	public VersionNoteService getWrappedService() {
		return _versionNoteService;
	}

	@Override
	public void setWrappedService(VersionNoteService versionNoteService) {
		_versionNoteService = versionNoteService;
	}

	private VersionNoteService _versionNoteService;

}