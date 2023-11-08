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
 * This class is a wrapper for {@link TeacherSubject}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeacherSubject
 * @generated
 */
public class TeacherSubjectWrapper
	extends BaseModelWrapper<TeacherSubject>
	implements ModelWrapper<TeacherSubject>, TeacherSubject {

	public TeacherSubjectWrapper(TeacherSubject teacherSubject) {
		super(teacherSubject);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("teacherSubjectId", getTeacherSubjectId());
		attributes.put("teacherId", getTeacherId());
		attributes.put("subjectId", getSubjectId());
		attributes.put("schoolId", getSchoolId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long teacherSubjectId = (Long)attributes.get("teacherSubjectId");

		if (teacherSubjectId != null) {
			setTeacherSubjectId(teacherSubjectId);
		}

		Long teacherId = (Long)attributes.get("teacherId");

		if (teacherId != null) {
			setTeacherId(teacherId);
		}

		Long subjectId = (Long)attributes.get("subjectId");

		if (subjectId != null) {
			setSubjectId(subjectId);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}
	}

	@Override
	public TeacherSubject cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the primary key of this teacher subject.
	 *
	 * @return the primary key of this teacher subject
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the school ID of this teacher subject.
	 *
	 * @return the school ID of this teacher subject
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the subject ID of this teacher subject.
	 *
	 * @return the subject ID of this teacher subject
	 */
	@Override
	public long getSubjectId() {
		return model.getSubjectId();
	}

	/**
	 * Returns the teacher ID of this teacher subject.
	 *
	 * @return the teacher ID of this teacher subject
	 */
	@Override
	public long getTeacherId() {
		return model.getTeacherId();
	}

	/**
	 * Returns the teacher subject ID of this teacher subject.
	 *
	 * @return the teacher subject ID of this teacher subject
	 */
	@Override
	public long getTeacherSubjectId() {
		return model.getTeacherSubjectId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the primary key of this teacher subject.
	 *
	 * @param primaryKey the primary key of this teacher subject
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the school ID of this teacher subject.
	 *
	 * @param schoolId the school ID of this teacher subject
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the subject ID of this teacher subject.
	 *
	 * @param subjectId the subject ID of this teacher subject
	 */
	@Override
	public void setSubjectId(long subjectId) {
		model.setSubjectId(subjectId);
	}

	/**
	 * Sets the teacher ID of this teacher subject.
	 *
	 * @param teacherId the teacher ID of this teacher subject
	 */
	@Override
	public void setTeacherId(long teacherId) {
		model.setTeacherId(teacherId);
	}

	/**
	 * Sets the teacher subject ID of this teacher subject.
	 *
	 * @param teacherSubjectId the teacher subject ID of this teacher subject
	 */
	@Override
	public void setTeacherSubjectId(long teacherSubjectId) {
		model.setTeacherSubjectId(teacherSubjectId);
	}

	@Override
	protected TeacherSubjectWrapper wrap(TeacherSubject teacherSubject) {
		return new TeacherSubjectWrapper(teacherSubject);
	}

}