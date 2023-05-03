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

/**
 * Provides the remote service utility for SessionTeacher. This utility wraps
 * <code>com.weprode.nero.schedule.service.impl.SessionTeacherServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SessionTeacherService
 * @generated
 */
public class SessionTeacherServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.schedule.service.impl.SessionTeacherServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getSessionTeachersAndSubstitutes(
		long sessionId) {

		return getService().getSessionTeachersAndSubstitutes(sessionId);
	}

	public static org.json.JSONObject saveTeacherSubstitutes(
		long sessionId, java.lang.String teacherArray) {

		return getService().saveTeacherSubstitutes(sessionId, teacherArray);
	}

	public static SessionTeacherService getService() {
		return _service;
	}

	private static volatile SessionTeacherService _service;

}