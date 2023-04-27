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

/**
 * Provides the remote service utility for SessionStudent. This utility wraps
 * <code>com.weprode.nero.school.life.service.impl.SessionStudentServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SessionStudentService
 * @generated
 */
public class SessionStudentServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.school.life.service.impl.SessionStudentServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.json.JSONObject getSessionMembers(
		long schoollifeSessionId) {

		return getService().getSessionMembers(schoollifeSessionId);
	}

	public static com.liferay.portal.kernel.json.JSONObject getSessions(
		long studentId, long classId, java.lang.String minDateStr,
		java.lang.String maxDateStr) {

		return getService().getSessions(
			studentId, classId, minDateStr, maxDateStr);
	}

	public static com.liferay.portal.kernel.json.JSONObject markStudentsPresent(
		long schoollifeSessionId, java.lang.String studentsPresence) {

		return getService().markStudentsPresent(
			schoollifeSessionId, studentsPresence);
	}

	public static com.liferay.portal.kernel.json.JSONObject registerClass(
		long classId, long schoollifeSessionId, java.lang.String comment,
		java.lang.String replayTestSubject, boolean notifyParents) {

		return getService().registerClass(
			classId, schoollifeSessionId, comment, replayTestSubject,
			notifyParents);
	}

	public static com.liferay.portal.kernel.json.JSONObject registerStudent(
		long studentId, long schoollifeSessionId, java.lang.String comment,
		java.lang.String replayTestSubject, boolean notifyParents) {

		return getService().registerStudent(
			studentId, schoollifeSessionId, comment, replayTestSubject,
			notifyParents);
	}

	public static com.liferay.portal.kernel.json.JSONObject unregisterStudent(
		long studentId, long schoollifeSessionId, boolean allSessions) {

		return getService().unregisterStudent(
			studentId, schoollifeSessionId, allSessions);
	}

	public static SessionStudentService getService() {
		return _service;
	}

	private static volatile SessionStudentService _service;

}