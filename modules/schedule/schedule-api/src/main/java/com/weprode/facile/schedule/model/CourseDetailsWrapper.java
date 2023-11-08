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

package com.weprode.facile.schedule.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CourseDetails}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CourseDetails
 * @generated
 */
public class CourseDetailsWrapper
	extends BaseModelWrapper<CourseDetails>
	implements CourseDetails, ModelWrapper<CourseDetails> {

	public CourseDetailsWrapper(CourseDetails courseDetails) {
		super(courseDetails);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("courseGroupId", getCourseGroupId());
		attributes.put("color", getColor());
		attributes.put("subjectId", getSubjectId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long courseGroupId = (Long)attributes.get("courseGroupId");

		if (courseGroupId != null) {
			setCourseGroupId(courseGroupId);
		}

		String color = (String)attributes.get("color");

		if (color != null) {
			setColor(color);
		}

		Long subjectId = (Long)attributes.get("subjectId");

		if (subjectId != null) {
			setSubjectId(subjectId);
		}
	}

	@Override
	public CourseDetails cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the color of this course details.
	 *
	 * @return the color of this course details
	 */
	@Override
	public String getColor() {
		return model.getColor();
	}

	/**
	 * Returns the course group ID of this course details.
	 *
	 * @return the course group ID of this course details
	 */
	@Override
	public long getCourseGroupId() {
		return model.getCourseGroupId();
	}

	/**
	 * Returns the primary key of this course details.
	 *
	 * @return the primary key of this course details
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the subject ID of this course details.
	 *
	 * @return the subject ID of this course details
	 */
	@Override
	public long getSubjectId() {
		return model.getSubjectId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the color of this course details.
	 *
	 * @param color the color of this course details
	 */
	@Override
	public void setColor(String color) {
		model.setColor(color);
	}

	/**
	 * Sets the course group ID of this course details.
	 *
	 * @param courseGroupId the course group ID of this course details
	 */
	@Override
	public void setCourseGroupId(long courseGroupId) {
		model.setCourseGroupId(courseGroupId);
	}

	/**
	 * Sets the primary key of this course details.
	 *
	 * @param primaryKey the primary key of this course details
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the subject ID of this course details.
	 *
	 * @param subjectId the subject ID of this course details
	 */
	@Override
	public void setSubjectId(long subjectId) {
		model.setSubjectId(subjectId);
	}

	@Override
	protected CourseDetailsWrapper wrap(CourseDetails courseDetails) {
		return new CourseDetailsWrapper(courseDetails);
	}

}