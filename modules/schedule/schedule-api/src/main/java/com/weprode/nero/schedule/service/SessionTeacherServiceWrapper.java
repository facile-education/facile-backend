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

package com.weprode.nero.schedule.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SessionTeacherService}.
 *
 * @author Brian Wing Shun Chan
 * @see SessionTeacherService
 * @generated
 */
public class SessionTeacherServiceWrapper
	implements ServiceWrapper<SessionTeacherService>, SessionTeacherService {

	public SessionTeacherServiceWrapper(
		SessionTeacherService sessionTeacherService) {

		_sessionTeacherService = sessionTeacherService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _sessionTeacherService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSessionTeachersAndSubstitutes(
		long sessionId) {

		return _sessionTeacherService.getSessionTeachersAndSubstitutes(
			sessionId);
	}

	@Override
	public org.json.JSONObject saveTeacherSubstitutes(
		long sessionId, String teacherArray) {

		return _sessionTeacherService.saveTeacherSubstitutes(
			sessionId, teacherArray);
	}

	@Override
	public SessionTeacherService getWrappedService() {
		return _sessionTeacherService;
	}

	@Override
	public void setWrappedService(SessionTeacherService sessionTeacherService) {
		_sessionTeacherService = sessionTeacherService;
	}

	private SessionTeacherService _sessionTeacherService;

}