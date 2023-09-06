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

package com.weprode.nero.course.service;

/**
 * Provides the remote service utility for Course. This utility wraps
 * <code>com.weprode.nero.course.service.impl.CourseServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see CourseService
 * @generated
 */
public class CourseServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.weprode.nero.course.service.impl.CourseServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.json.JSONObject getCourse(long sessionId) {
		return getService().getCourse(sessionId);
	}

	public static org.json.JSONObject getCourseContent(
		long courseId, boolean hideDrafts) {

		return getService().getCourseContent(courseId, hideDrafts);
	}

	public static org.json.JSONObject getCourseStudents(long courseId) {
		return getService().getCourseStudents(courseId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static org.json.JSONObject getSessionDetails(long sessionId) {
		return getService().getSessionDetails(sessionId);
	}

	public static org.json.JSONObject getUserCourses(long userId) {
		return getService().getUserCourses(userId);
	}

	public static org.json.JSONObject savePrivateNotes(
		long sessionId, java.lang.String notes) {

		return getService().savePrivateNotes(sessionId, notes);
	}

	public static CourseService getService() {
		return _service;
	}

	private static volatile CourseService _service;

}