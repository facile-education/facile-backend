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
 * This class is a wrapper for {@link SubjectGroupColor}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SubjectGroupColor
 * @generated
 */
public class SubjectGroupColorWrapper
	extends BaseModelWrapper<SubjectGroupColor>
	implements ModelWrapper<SubjectGroupColor>, SubjectGroupColor {

	public SubjectGroupColorWrapper(SubjectGroupColor subjectGroupColor) {
		super(subjectGroupColor);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("subjectGroupColorId", getSubjectGroupColorId());
		attributes.put("subject", getSubject());
		attributes.put("groupId", getGroupId());
		attributes.put("color", getColor());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long subjectGroupColorId = (Long)attributes.get("subjectGroupColorId");

		if (subjectGroupColorId != null) {
			setSubjectGroupColorId(subjectGroupColorId);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
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
	public SubjectGroupColor cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the color of this subject group color.
	 *
	 * @return the color of this subject group color
	 */
	@Override
	public String getColor() {
		return model.getColor();
	}

	/**
	 * Returns the group ID of this subject group color.
	 *
	 * @return the group ID of this subject group color
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the primary key of this subject group color.
	 *
	 * @return the primary key of this subject group color
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the subject of this subject group color.
	 *
	 * @return the subject of this subject group color
	 */
	@Override
	public String getSubject() {
		return model.getSubject();
	}

	/**
	 * Returns the subject group color ID of this subject group color.
	 *
	 * @return the subject group color ID of this subject group color
	 */
	@Override
	public long getSubjectGroupColorId() {
		return model.getSubjectGroupColorId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the color of this subject group color.
	 *
	 * @param color the color of this subject group color
	 */
	@Override
	public void setColor(String color) {
		model.setColor(color);
	}

	/**
	 * Sets the group ID of this subject group color.
	 *
	 * @param groupId the group ID of this subject group color
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the primary key of this subject group color.
	 *
	 * @param primaryKey the primary key of this subject group color
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the subject of this subject group color.
	 *
	 * @param subject the subject of this subject group color
	 */
	@Override
	public void setSubject(String subject) {
		model.setSubject(subject);
	}

	/**
	 * Sets the subject group color ID of this subject group color.
	 *
	 * @param subjectGroupColorId the subject group color ID of this subject group color
	 */
	@Override
	public void setSubjectGroupColorId(long subjectGroupColorId) {
		model.setSubjectGroupColorId(subjectGroupColorId);
	}

	@Override
	protected SubjectGroupColorWrapper wrap(
		SubjectGroupColor subjectGroupColor) {

		return new SubjectGroupColorWrapper(subjectGroupColor);
	}

}