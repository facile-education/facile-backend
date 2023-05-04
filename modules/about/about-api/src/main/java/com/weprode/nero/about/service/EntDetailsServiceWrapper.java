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
 * Provides a wrapper for {@link EntDetailsService}.
 *
 * @author Brian Wing Shun Chan
 * @see EntDetailsService
 * @generated
 */
public class EntDetailsServiceWrapper
	implements EntDetailsService, ServiceWrapper<EntDetailsService> {

	public EntDetailsServiceWrapper(EntDetailsService entDetailsService) {
		_entDetailsService = entDetailsService;
	}

	@Override
	public org.json.JSONObject createVersion(
		String versionNumber, String versionDetails) {

		return _entDetailsService.createVersion(versionNumber, versionDetails);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _entDetailsService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getTermsOfUse() {
		return _entDetailsService.getTermsOfUse();
	}

	@Override
	public org.json.JSONObject getVersionDetails(Long versionId) {
		return _entDetailsService.getVersionDetails(versionId);
	}

	@Override
	public org.json.JSONObject getVersionList() {
		return _entDetailsService.getVersionList();
	}

	@Override
	public EntDetailsService getWrappedService() {
		return _entDetailsService;
	}

	@Override
	public void setWrappedService(EntDetailsService entDetailsService) {
		_entDetailsService = entDetailsService;
	}

	private EntDetailsService _entDetailsService;

}