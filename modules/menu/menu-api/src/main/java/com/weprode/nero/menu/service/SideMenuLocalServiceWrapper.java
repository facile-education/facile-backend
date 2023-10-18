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

package com.weprode.nero.menu.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SideMenuLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SideMenuLocalService
 * @generated
 */
public class SideMenuLocalServiceWrapper
	implements ServiceWrapper<SideMenuLocalService>, SideMenuLocalService {

	public SideMenuLocalServiceWrapper() {
		this(null);
	}

	public SideMenuLocalServiceWrapper(
		SideMenuLocalService sideMenuLocalService) {

		_sideMenuLocalService = sideMenuLocalService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _sideMenuLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.weprode.nero.menu.enums.MenuEntry> getSchoolMenu(
		long schoolId) {

		return _sideMenuLocalService.getSchoolMenu(schoolId);
	}

	@Override
	public java.util.List<com.weprode.nero.menu.enums.MenuEntry> getUserMenu(
		com.liferay.portal.kernel.model.User user) {

		return _sideMenuLocalService.getUserMenu(user);
	}

	@Override
	public SideMenuLocalService getWrappedService() {
		return _sideMenuLocalService;
	}

	@Override
	public void setWrappedService(SideMenuLocalService sideMenuLocalService) {
		_sideMenuLocalService = sideMenuLocalService;
	}

	private SideMenuLocalService _sideMenuLocalService;

}