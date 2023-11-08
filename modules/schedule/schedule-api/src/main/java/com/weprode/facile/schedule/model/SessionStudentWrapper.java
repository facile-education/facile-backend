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

		attributes.put("sessionStudentId", getSessionStudentId());
		attributes.put("sessionId", getSessionId());
		attributes.put("studentId", getStudentId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long sessionStudentId = (Long)attributes.get("sessionStudentId");

		if (sessionStudentId != null) {
			setSessionStudentId(sessionStudentId);
		}

		Long sessionId = (Long)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Long studentId = (Long)attributes.get("studentId");

		if (studentId != null) {
			setStudentId(studentId);
		}
	}

	@Override
	public SessionStudent cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the primary key of this session student.
	 *
	 * @return the primary key of this session student
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the session ID of this session student.
	 *
	 * @return the session ID of this session student
	 */
	@Override
	public long getSessionId() {
		return model.getSessionId();
	}

	/**
	 * Returns the session student ID of this session student.
	 *
	 * @return the session student ID of this session student
	 */
	@Override
	public long getSessionStudentId() {
		return model.getSessionStudentId();
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

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the primary key of this session student.
	 *
	 * @param primaryKey the primary key of this session student
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the session ID of this session student.
	 *
	 * @param sessionId the session ID of this session student
	 */
	@Override
	public void setSessionId(long sessionId) {
		model.setSessionId(sessionId);
	}

	/**
	 * Sets the session student ID of this session student.
	 *
	 * @param sessionStudentId the session student ID of this session student
	 */
	@Override
	public void setSessionStudentId(long sessionStudentId) {
		model.setSessionStudentId(sessionStudentId);
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

	@Override
	protected SessionStudentWrapper wrap(SessionStudent sessionStudent) {
		return new SessionStudentWrapper(sessionStudent);
	}

}