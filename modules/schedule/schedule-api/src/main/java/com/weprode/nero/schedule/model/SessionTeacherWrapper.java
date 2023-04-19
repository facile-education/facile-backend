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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SessionTeacher}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionTeacher
 * @generated
 */
public class SessionTeacherWrapper
	extends BaseModelWrapper<SessionTeacher>
	implements ModelWrapper<SessionTeacher>, SessionTeacher {

	public SessionTeacherWrapper(SessionTeacher sessionTeacher) {
		super(sessionTeacher);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("sessionTeacherId", getSessionTeacherId());
		attributes.put("sessionId", getSessionId());
		attributes.put("teacherId", getTeacherId());
		attributes.put("status", getStatus());
		attributes.put("substituteId", getSubstituteId());
		attributes.put("modificationDate", getModificationDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long sessionTeacherId = (Long)attributes.get("sessionTeacherId");

		if (sessionTeacherId != null) {
			setSessionTeacherId(sessionTeacherId);
		}

		Long sessionId = (Long)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Long teacherId = (Long)attributes.get("teacherId");

		if (teacherId != null) {
			setTeacherId(teacherId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long substituteId = (Long)attributes.get("substituteId");

		if (substituteId != null) {
			setSubstituteId(substituteId);
		}

		Date modificationDate = (Date)attributes.get("modificationDate");

		if (modificationDate != null) {
			setModificationDate(modificationDate);
		}
	}

	@Override
	public SessionTeacher cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the modification date of this session teacher.
	 *
	 * @return the modification date of this session teacher
	 */
	@Override
	public Date getModificationDate() {
		return model.getModificationDate();
	}

	/**
	 * Returns the primary key of this session teacher.
	 *
	 * @return the primary key of this session teacher
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the session ID of this session teacher.
	 *
	 * @return the session ID of this session teacher
	 */
	@Override
	public long getSessionId() {
		return model.getSessionId();
	}

	/**
	 * Returns the session teacher ID of this session teacher.
	 *
	 * @return the session teacher ID of this session teacher
	 */
	@Override
	public long getSessionTeacherId() {
		return model.getSessionTeacherId();
	}

	/**
	 * Returns the status of this session teacher.
	 *
	 * @return the status of this session teacher
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the substitute ID of this session teacher.
	 *
	 * @return the substitute ID of this session teacher
	 */
	@Override
	public long getSubstituteId() {
		return model.getSubstituteId();
	}

	/**
	 * Returns the teacher ID of this session teacher.
	 *
	 * @return the teacher ID of this session teacher
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
	 * Sets the modification date of this session teacher.
	 *
	 * @param modificationDate the modification date of this session teacher
	 */
	@Override
	public void setModificationDate(Date modificationDate) {
		model.setModificationDate(modificationDate);
	}

	/**
	 * Sets the primary key of this session teacher.
	 *
	 * @param primaryKey the primary key of this session teacher
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the session ID of this session teacher.
	 *
	 * @param sessionId the session ID of this session teacher
	 */
	@Override
	public void setSessionId(long sessionId) {
		model.setSessionId(sessionId);
	}

	/**
	 * Sets the session teacher ID of this session teacher.
	 *
	 * @param sessionTeacherId the session teacher ID of this session teacher
	 */
	@Override
	public void setSessionTeacherId(long sessionTeacherId) {
		model.setSessionTeacherId(sessionTeacherId);
	}

	/**
	 * Sets the status of this session teacher.
	 *
	 * @param status the status of this session teacher
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the substitute ID of this session teacher.
	 *
	 * @param substituteId the substitute ID of this session teacher
	 */
	@Override
	public void setSubstituteId(long substituteId) {
		model.setSubstituteId(substituteId);
	}

	/**
	 * Sets the teacher ID of this session teacher.
	 *
	 * @param teacherId the teacher ID of this session teacher
	 */
	@Override
	public void setTeacherId(long teacherId) {
		model.setTeacherId(teacherId);
	}

	@Override
	protected SessionTeacherWrapper wrap(SessionTeacher sessionTeacher) {
		return new SessionTeacherWrapper(sessionTeacher);
	}

}