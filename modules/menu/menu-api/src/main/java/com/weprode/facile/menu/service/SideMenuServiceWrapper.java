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

package com.weprode.facile.menu.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SideMenuService}.
 *
 * @author Brian Wing Shun Chan
 * @see SideMenuService
 * @generated
 */
public class SideMenuServiceWrapper
	implements ServiceWrapper<SideMenuService>, SideMenuService {

	public SideMenuServiceWrapper() {
		this(null);
	}

	public SideMenuServiceWrapper(SideMenuService sideMenuService) {
		_sideMenuService = sideMenuService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _sideMenuService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSideMenu() {
		return _sideMenuService.getSideMenu();
	}

	@Override
	public SideMenuService getWrappedService() {
		return _sideMenuService;
	}

	@Override
	public void setWrappedService(SideMenuService sideMenuService) {
		_sideMenuService = sideMenuService;
	}

	private SideMenuService _sideMenuService;

}