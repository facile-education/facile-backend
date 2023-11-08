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

package com.weprode.facile.user.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NewsAdminService}.
 *
 * @author Brian Wing Shun Chan
 * @see NewsAdminService
 * @generated
 */
public class NewsAdminServiceWrapper
	implements NewsAdminService, ServiceWrapper<NewsAdminService> {

	public NewsAdminServiceWrapper() {
		this(null);
	}

	public NewsAdminServiceWrapper(NewsAdminService newsAdminService) {
		_newsAdminService = newsAdminService;
	}

	@Override
	public org.json.JSONObject addNewsDelegate(long userId, long schoolId) {
		return _newsAdminService.addNewsDelegate(userId, schoolId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _newsAdminService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject removeNewsDelegate(long userId, long schoolId) {
		return _newsAdminService.removeNewsDelegate(userId, schoolId);
	}

	@Override
	public NewsAdminService getWrappedService() {
		return _newsAdminService;
	}

	@Override
	public void setWrappedService(NewsAdminService newsAdminService) {
		_newsAdminService = newsAdminService;
	}

	private NewsAdminService _newsAdminService;

}