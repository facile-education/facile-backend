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

package com.weprode.nero.schedule.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TeacherGroupColor}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeacherGroupColor
 * @generated
 */
public class TeacherGroupColorWrapper
	extends BaseModelWrapper<TeacherGroupColor>
	implements ModelWrapper<TeacherGroupColor>, TeacherGroupColor {

	public TeacherGroupColorWrapper(TeacherGroupColor teacherGroupColor) {
		super(teacherGroupColor);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("teacherGroupColorId", getTeacherGroupColorId());
		attributes.put("teacherId", getTeacherId());
		attributes.put("groupId", getGroupId());
		attributes.put("color", getColor());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long teacherGroupColorId = (Long)attributes.get("teacherGroupColorId");

		if (teacherGroupColorId != null) {
			setTeacherGroupColorId(teacherGroupColorId);
		}

		Long teacherId = (Long)attributes.get("teacherId");

		if (teacherId != null) {
			setTeacherId(teacherId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String color = (String)attributes.get("color");

		if (color != null) {
			setColor(color);
		}
	}

	@Override
	public TeacherGroupColor cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the color of this teacher group color.
	 *
	 * @return the color of this teacher group color
	 */
	@Override
	public String getColor() {
		return model.getColor();
	}

	/**
	 * Returns the group ID of this teacher group color.
	 *
	 * @return the group ID of this teacher group color
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the primary key of this teacher group color.
	 *
	 * @return the primary key of this teacher group color
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the teacher group color ID of this teacher group color.
	 *
	 * @return the teacher group color ID of this teacher group color
	 */
	@Override
	public long getTeacherGroupColorId() {
		return model.getTeacherGroupColorId();
	}

	/**
	 * Returns the teacher ID of this teacher group color.
	 *
	 * @return the teacher ID of this teacher group color
	 */
	@Override
	public long getTeacherId() {
		return model.getTeacherId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the color of this teacher group color.
	 *
	 * @param color the color of this teacher group color
	 */
	@Override
	public void setColor(String color) {
		model.setColor(color);
	}

	/**
	 * Sets the group ID of this teacher group color.
	 *
	 * @param groupId the group ID of this teacher group color
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the primary key of this teacher group color.
	 *
	 * @param primaryKey the primary key of this teacher group color
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the teacher group color ID of this teacher group color.
	 *
	 * @param teacherGroupColorId the teacher group color ID of this teacher group color
	 */
	@Override
	public void setTeacherGroupColorId(long teacherGroupColorId) {
		model.setTeacherGroupColorId(teacherGroupColorId);
	}

	/**
	 * Sets the teacher ID of this teacher group color.
	 *
	 * @param teacherId the teacher ID of this teacher group color
	 */
	@Override
	public void setTeacherId(long teacherId) {
		model.setTeacherId(teacherId);
	}

	@Override
	protected TeacherGroupColorWrapper wrap(
		TeacherGroupColor teacherGroupColor) {

		return new TeacherGroupColorWrapper(teacherGroupColor);
	}

}