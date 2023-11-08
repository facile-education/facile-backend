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

package com.weprode.facile.course.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CourseService}.
 *
 * @author Brian Wing Shun Chan
 * @see CourseService
 * @generated
 */
public class CourseServiceWrapper
	implements CourseService, ServiceWrapper<CourseService> {

	public CourseServiceWrapper() {
		this(null);
	}

	public CourseServiceWrapper(CourseService courseService) {
		_courseService = courseService;
	}

	@Override
	public org.json.JSONObject getCourse(long sessionId) {
		return _courseService.getCourse(sessionId);
	}

	@Override
	public org.json.JSONObject getCourseContent(
		long courseId, boolean hideDrafts) {

		return _courseService.getCourseContent(courseId, hideDrafts);
	}

	@Override
	public org.json.JSONObject getCourseStudents(long courseId) {
		return _courseService.getCourseStudents(courseId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _courseService.getOSGiServiceIdentifier();
	}

	@Override
	public org.json.JSONObject getSessionDetails(long sessionId) {
		return _courseService.getSessionDetails(sessionId);
	}

	@Override
	public org.json.JSONObject getUserCourses(long userId) {
		return _courseService.getUserCourses(userId);
	}

	@Override
	public org.json.JSONObject savePrivateNotes(long sessionId, String notes) {
		return _courseService.savePrivateNotes(sessionId, notes);
	}

	@Override
	public CourseService getWrappedService() {
		return _courseService;
	}

	@Override
	public void setWrappedService(CourseService courseService) {
		_courseService = courseService;
	}

	private CourseService _courseService;

}