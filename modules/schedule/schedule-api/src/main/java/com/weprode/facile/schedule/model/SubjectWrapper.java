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
 * This class is a wrapper for {@link Subject}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Subject
 * @generated
 */
public class SubjectWrapper
	extends BaseModelWrapper<Subject>
	implements ModelWrapper<Subject>, Subject {

	public SubjectWrapper(Subject subject) {
		super(subject);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("subjectId", getSubjectId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long subjectId = (Long)attributes.get("subjectId");

		if (subjectId != null) {
			setSubjectId(subjectId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public Subject cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the name of this subject.
	 *
	 * @return the name of this subject
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this subject.
	 *
	 * @return the primary key of this subject
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the subject ID of this subject.
	 *
	 * @return the subject ID of this subject
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
	 * Sets the name of this subject.
	 *
	 * @param name the name of this subject
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this subject.
	 *
	 * @param primaryKey the primary key of this subject
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the subject ID of this subject.
	 *
	 * @param subjectId the subject ID of this subject
	 */
	@Override
	public void setSubjectId(long subjectId) {
		model.setSubjectId(subjectId);
	}

	@Override
	protected SubjectWrapper wrap(Subject subject) {
		return new SubjectWrapper(subject);
	}

}