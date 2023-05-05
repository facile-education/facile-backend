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

package com.weprode.nero.access.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccessService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccessService
 * @generated
 */
public class AccessServiceWrapper
	implements AccessService, ServiceWrapper<AccessService> {

	public AccessServiceWrapper(AccessService accessService) {
		_accessService = accessService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accessService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getRoleAccesses(long schoolId, long roleId) {
		return _accessService.getRoleAccesses(schoolId, roleId);
	}

	@Override
	public org.json.JSONObject getSchoolAccesses(long schoolId) {
		return _accessService.getSchoolAccesses(schoolId);
	}

	@Override
	public org.json.JSONObject getUserAccesses() {
		return _accessService.getUserAccesses();
	}

	@Override
	public org.json.JSONObject saveSchoolAccesses(
		long schoolId, String accesses) {

		return _accessService.saveSchoolAccesses(schoolId, accesses);
	}

	@Override
	public AccessService getWrappedService() {
		return _accessService;
	}

	@Override
	public void setWrappedService(AccessService accessService) {
		_accessService = accessService;
	}

	private AccessService _accessService;

}