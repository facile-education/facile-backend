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

package com.weprode.nero.role.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RoleUtilsService}.
 *
 * @author Brian Wing Shun Chan
 * @see RoleUtilsService
 * @generated
 */
public class RoleUtilsServiceWrapper
	implements RoleUtilsService, ServiceWrapper<RoleUtilsService> {

	public RoleUtilsServiceWrapper() {
		this(null);
	}

	public RoleUtilsServiceWrapper(RoleUtilsService roleUtilsService) {
		_roleUtilsService = roleUtilsService;
	}

	@Override
	public org.json.JSONObject getBroadcastRoles() {
		return _roleUtilsService.getBroadcastRoles();
	}

	@Override
	public org.json.JSONObject getLocalUserRoles() {
		return _roleUtilsService.getLocalUserRoles();
	}

	@Override
	public org.json.JSONObject getMainRoles() {
		return _roleUtilsService.getMainRoles();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _roleUtilsService.getOSGiServiceIdentifier();
	}

	@Override
	public RoleUtilsService getWrappedService() {
		return _roleUtilsService;
	}

	@Override
	public void setWrappedService(RoleUtilsService roleUtilsService) {
		_roleUtilsService = roleUtilsService;
	}

	private RoleUtilsService _roleUtilsService;

}