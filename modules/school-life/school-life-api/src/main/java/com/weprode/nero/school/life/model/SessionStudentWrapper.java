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

package com.weprode.nero.school.life.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SessionStudent}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionStudent
 * @generated
 */
public class SessionStudentWrapper
	extends BaseModelWrapper<SessionStudent>
	implements ModelWrapper<SessionStudent>, SessionStudent {

	public SessionStudentWrapper(SessionStudent sessionStudent) {
		super(sessionStudent);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("schoollifeSessionId", getSchoollifeSessionId());
		attributes.put("studentId", getStudentId());
		attributes.put("sourceTeacherId", getSourceTeacherId());
		attributes.put("isPresent", isIsPresent());
		attributes.put("notifyParents", isNotifyParents());
		attributes.put("comment", getComment());
		attributes.put("subject", getSubject());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long schoollifeSessionId = (Long)attributes.get("schoollifeSessionId");

		if (schoollifeSessionId != null) {
			setSchoollifeSessionId(schoollifeSessionId);
		}

		Long studentId = (Long)attributes.get("studentId");

		if (studentId != null) {
			setStudentId(studentId);
		}

		Long sourceTeacherId = (Long)attributes.get("sourceTeacherId");

		if (sourceTeacherId != null) {
			setSourceTeacherId(sourceTeacherId);
		}

		Boolean isPresent = (Boolean)attributes.get("isPresent");

		if (isPresent != null) {
			setIsPresent(isPresent);
		}

		Boolean notifyParents = (Boolean)attributes.get("notifyParents");

		if (notifyParents != null) {
			setNotifyParents(notifyParents);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}
	}

	@Override
	public SessionStudent cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the comment of this session student.
	 *
	 * @return the comment of this session student
	 */
	@Override
	public String getComment() {
		return model.getComment();
	}

	/**
	 * Returns the is present of this session student.
	 *
	 * @return the is present of this session student
	 */
	@Override
	public boolean getIsPresent() {
		return model.getIsPresent();
	}

	/**
	 * Returns the notify parents of this session student.
	 *
	 * @return the notify parents of this session student
	 */
	@Override
	public boolean getNotifyParents() {
		return model.getNotifyParents();
	}

	/**
	 * Returns the primary key of this session student.
	 *
	 * @return the primary key of this session student
	 */
	@Override
	public com.weprode.nero.school.life.service.persistence.SessionStudentPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the schoollife session ID of this session student.
	 *
	 * @return the schoollife session ID of this session student
	 */
	@Override
	public long getSchoollifeSessionId() {
		return model.getSchoollifeSessionId();
	}

	/**
	 * Returns the source teacher ID of this session student.
	 *
	 * @return the source teacher ID of this session student
	 */
	@Override
	public long getSourceTeacherId() {
		return model.getSourceTeacherId();
	}

	/**
	 * Returns the student ID of this session student.
	 *
	 * @return the student ID of this session student
	 */
	@Override
	public long getStudentId() {
		return model.getStudentId();
	}

	/**
	 * Returns the subject of this session student.
	 *
	 * @return the subject of this session student
	 */
	@Override
	public String getSubject() {
		return model.getSubject();
	}

	/**
	 * Returns <code>true</code> if this session student is is present.
	 *
	 * @return <code>true</code> if this session student is is present; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsPresent() {
		return model.isIsPresent();
	}

	/**
	 * Returns <code>true</code> if this session student is notify parents.
	 *
	 * @return <code>true</code> if this session student is notify parents; <code>false</code> otherwise
	 */
	@Override
	public boolean isNotifyParents() {
		return model.isNotifyParents();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the comment of this session student.
	 *
	 * @param comment the comment of this session student
	 */
	@Override
	public void setComment(String comment) {
		model.setComment(comment);
	}

	/**
	 * Sets whether this session student is is present.
	 *
	 * @param isPresent the is present of this session student
	 */
	@Override
	public void setIsPresent(boolean isPresent) {
		model.setIsPresent(isPresent);
	}

	/**
	 * Sets whether this session student is notify parents.
	 *
	 * @param notifyParents the notify parents of this session student
	 */
	@Override
	public void setNotifyParents(boolean notifyParents) {
		model.setNotifyParents(notifyParents);
	}

	/**
	 * Sets the primary key of this session student.
	 *
	 * @param primaryKey the primary key of this session student
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.nero.school.life.service.persistence.SessionStudentPK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the schoollife session ID of this session student.
	 *
	 * @param schoollifeSessionId the schoollife session ID of this session student
	 */
	@Override
	public void setSchoollifeSessionId(long schoollifeSessionId) {
		model.setSchoollifeSessionId(schoollifeSessionId);
	}

	/**
	 * Sets the source teacher ID of this session student.
	 *
	 * @param sourceTeacherId the source teacher ID of this session student
	 */
	@Override
	public void setSourceTeacherId(long sourceTeacherId) {
		model.setSourceTeacherId(sourceTeacherId);
	}

	/**
	 * Sets the student ID of this session student.
	 *
	 * @param studentId the student ID of this session student
	 */
	@Override
	public void setStudentId(long studentId) {
		model.setStudentId(studentId);
	}

	/**
	 * Sets the subject of this session student.
	 *
	 * @param subject the subject of this session student
	 */
	@Override
	public void setSubject(String subject) {
		model.setSubject(subject);
	}

	@Override
	protected SessionStudentWrapper wrap(SessionStudent sessionStudent) {
		return new SessionStudentWrapper(sessionStudent);
	}

}