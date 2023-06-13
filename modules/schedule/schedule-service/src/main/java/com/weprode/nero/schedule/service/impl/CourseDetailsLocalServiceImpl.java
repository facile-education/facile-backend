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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.schedule.model.CourseDetails;
import com.weprode.nero.schedule.service.base.CourseDetailsLocalServiceBaseImpl;
import com.weprode.nero.schedule.utils.CDTColorUtil;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.nero.schedule.model.CourseDetails",
	service = AopService.class
)
public class CourseDetailsLocalServiceImpl extends CourseDetailsLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(CourseDetailsLocalServiceImpl.class);

	public String getCourseColor(long groupId) {

		CourseDetails courseDetails;
		try {
			courseDetails = courseDetailsPersistence.findByPrimaryKey(groupId);
			if (courseDetails != null) {
				if (courseDetails.getColor() == null || courseDetails.getColor().equals("")) {
					int nbExistingGroupColors = courseDetailsPersistence.countAll();
					String color = CDTColorUtil.getNewColor(nbExistingGroupColors);
					courseDetails.setColor(color);
					courseDetails = courseDetailsPersistence.update(courseDetails);
				}
				return courseDetails.getColor();
			}
		} catch (Exception e) {
			// Nothing
		}
		return CDTColorUtil.getRandomColor();
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