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

package com.weprode.nero.schedule.service.impl;

import com.liferay.portal.aop.AopService;
import com.weprode.nero.schedule.model.CourseDetails;
import com.weprode.nero.schedule.service.base.CourseDetailsLocalServiceBaseImpl;
import com.weprode.nero.schedule.utils.CourseColorUtil;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.CourseDetails",
	service = AopService.class
)
public class CourseDetailsLocalServiceImpl extends CourseDetailsLocalServiceBaseImpl {

	public String getCourseColor(long groupId) {

		CourseDetails courseDetails = null;
		try {
			courseDetails = courseDetailsPersistence.fetchByPrimaryKey(groupId);
			return courseDetails.getColor();
		} catch (Exception e) {
			// Nothing
		}
		if (courseDetails == null) {
			courseDetails = courseDetailsPersistence.create(groupId);
			String color = CourseColorUtil.getRandomColor();
			// int nbExistingGroupColors = courseDetailsPersistence.countAll();
			// String color = CourseColorUtil.getNewColor(nbExistingGroupColors);
			courseDetails.setColor(color);
			courseDetails = courseDetailsPersistence.update(courseDetails);
		}
		return courseDetails.getColor();
	}

	public void setCourseSubject(long groupId, long subjectId) {
		try {
			CourseDetails courseDetails = courseDetailsPersistence.findByPrimaryKey(groupId);
			if (courseDetails != null) {
				courseDetails.setSubjectId(subjectId);
				courseDetailsPersistence.update(courseDetails);
			}
		} catch (Exception e) {
			// Nothing
		}
	}

	public String getCourseSubject(long groupId) {
		try {
			CourseDetails courseDetails = courseDetailsPersistence.findByPrimaryKey(groupId);
			if (courseDetails != null) {
				return courseDetails.getColor();
			}
		} catch (Exception e) {
			// Nothing
		}
		return "Discipline";
	}
}