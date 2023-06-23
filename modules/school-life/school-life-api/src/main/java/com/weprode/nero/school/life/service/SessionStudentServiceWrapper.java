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
 * Provides a wrapper for {@link SessionStudentService}.
 *
 * @author Brian Wing Shun Chan
 * @see SessionStudentService
 * @generated
 */
public class SessionStudentServiceWrapper
	implements ServiceWrapper<SessionStudentService>, SessionStudentService {

	public SessionStudentServiceWrapper(
		SessionStudentService sessionStudentService) {

		_sessionStudentService = sessionStudentService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _sessionStudentService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSessionMembers(long schoollifeSessionId) {
		return _sessionStudentService.getSessionMembers(schoollifeSessionId);
	}

	@Override
	public org.json.JSONObject markStudentsPresent(
		long schoollifeSessionId, String studentsPresence) {

		return _sessionStudentService.markStudentsPresent(
			schoollifeSessionId, studentsPresence);
	}

	@Override
	public org.json.JSONObject registerClass(
		long classId, long schoollifeSessionId, String comment,
		String replayTestSubject, boolean notifyParents) {

		return _sessionStudentService.registerClass(
			classId, schoollifeSessionId, comment, replayTestSubject,
			notifyParents);
	}

	@Override
	public org.json.JSONObject registerStudent(
		long studentId, long schoollifeSessionId, String comment,
		String replayTestSubject, boolean notifyParents) {

		return _sessionStudentService.registerStudent(
			studentId, schoollifeSessionId, comment, replayTestSubject,
			notifyParents);
	}

	@Override
	public org.json.JSONObject unregisterStudent(
		long studentId, long schoollifeSessionId, boolean allSessions) {

		return _sessionStudentService.unregisterStudent(
			studentId, schoollifeSessionId, allSessions);
	}

	@Override
	public SessionStudentService getWrappedService() {
		return _sessionStudentService;
	}

	@Override
	public void setWrappedService(SessionStudentService sessionStudentService) {
		_sessionStudentService = sessionStudentService;
	}

	private SessionStudentService _sessionStudentService;

}