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

package com.weprode.nero.school.life.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SchoollifeSessionService}.
 *
 * @author Brian Wing Shun Chan
 * @see SchoollifeSessionService
 * @generated
 */
public class SchoollifeSessionServiceWrapper
	implements SchoollifeSessionService,
			   ServiceWrapper<SchoollifeSessionService> {

	public SchoollifeSessionServiceWrapper(
		SchoollifeSessionService schoollifeSessionService) {

		_schoollifeSessionService = schoollifeSessionService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _schoollifeSessionService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getWeekSessions(
		long schoolId, int type, String currentDateStr) {

		return _schoollifeSessionService.getWeekSessions(
			schoolId, type, currentDateStr);
	}

	@Override
	public SchoollifeSessionService getWrappedService() {
		return _schoollifeSessionService;
	}

	@Override
	public void setWrappedService(
		SchoollifeSessionService schoollifeSessionService) {

		_schoollifeSessionService = schoollifeSessionService;
	}

	private SchoollifeSessionService _schoollifeSessionService;

}