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

package com.weprode.nero.user.service;

/**
 * Provides the remote service utility for UserSearch. This utility wraps
 * <code>com.weprode.nero.user.service.impl.UserSearchServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see UserSearchService
 * @generated
 */
public class UserSearchServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.user.service.impl.UserSearchServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.json.JSONObject getSchoollifeAgents(
		java.lang.String search, long schoolId) {

		return getService().getSchoollifeAgents(search, schoolId);
	}

	public static com.liferay.portal.kernel.json.JSONObject getSchoolMembers(
		long schoolId, java.lang.String search) {

		return getService().getSchoolMembers(schoolId, search);
	}

	public static com.liferay.portal.kernel.json.JSONObject getSchoolStudents(
		java.lang.String search, long schoolId) {

		return getService().getSchoolStudents(search, schoolId);
	}

	public static com.liferay.portal.kernel.json.JSONObject
		getSchoolStudentTeacherList(long schoolId, java.lang.String search) {

		return getService().getSchoolStudentTeacherList(schoolId, search);
	}

	public static com.liferay.portal.kernel.json.JSONObject getSchoolTeachers(
		long schoolId, java.lang.String search) {

		return getService().getSchoolTeachers(schoolId, search);
	}

	public static UserSearchService getService() {
		return _service;
	}

	private static volatile UserSearchService _service;

}