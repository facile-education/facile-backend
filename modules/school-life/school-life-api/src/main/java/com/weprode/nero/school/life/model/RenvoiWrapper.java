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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Renvoi}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Renvoi
 * @generated
 */
public class RenvoiWrapper
	extends BaseModelWrapper<Renvoi> implements ModelWrapper<Renvoi>, Renvoi {

	public RenvoiWrapper(Renvoi renvoi) {
		super(renvoi);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("schoollifeSessionId", getSchoollifeSessionId());
		attributes.put("studentId", getStudentId());
		attributes.put("orgId", getOrgId());
		attributes.put("schoolId", getSchoolId());
		attributes.put("renvoiDate", getRenvoiDate());
		attributes.put("teacherId", getTeacherId());
		attributes.put("sourceSessionId", getSourceSessionId());
		attributes.put(
			"sourceSchoollifeSessionId", getSourceSchoollifeSessionId());
		attributes.put("sourceTeacherId", getSourceTeacherId());
		attributes.put("reason", getReason());
		attributes.put("status", getStatus());

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

		Long orgId = (Long)attributes.get("orgId");

		if (orgId != null) {
			setOrgId(orgId);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		Date renvoiDate = (Date)attributes.get("renvoiDate");

		if (renvoiDate != null) {
			setRenvoiDate(renvoiDate);
		}

		Long teacherId = (Long)attributes.get("teacherId");

		if (teacherId != null) {
			setTeacherId(teacherId);
		}

		Long sourceSessionId = (Long)attributes.get("sourceSessionId");

		if (sourceSessionId != null) {
			setSourceSessionId(sourceSessionId);
		}

		Long sourceSchoollifeSessionId = (Long)attributes.get(
			"sourceSchoollifeSessionId");

		if (sourceSchoollifeSessionId != null) {
			setSourceSchoollifeSessionId(sourceSchoollifeSessionId);
		}

		Long sourceTeacherId = (Long)attributes.get("sourceTeacherId");

		if (sourceTeacherId != null) {
			setSourceTeacherId(sourceTeacherId);
		}

		String reason = (String)attributes.get("reason");

		if (reason != null) {
			setReason(reason);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Renvoi cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the org ID of this renvoi.
	 *
	 * @return the org ID of this renvoi
	 */
	@Override
	public long getOrgId() {
		return model.getOrgId();
	}

	/**
	 * Returns the primary key of this renvoi.
	 *
	 * @return the primary key of this renvoi
	 */
	@Override
	public com.weprode.nero.school.life.service.persistence.RenvoiPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the reason of this renvoi.
	 *
	 * @return the reason of this renvoi
	 */
	@Override
	public String getReason() {
		return model.getReason();
	}

	/**
	 * Returns the renvoi date of this renvoi.
	 *
	 * @return the renvoi date of this renvoi
	 */
	@Override
	public Date getRenvoiDate() {
		return model.getRenvoiDate();
	}

	/**
	 * Returns the school ID of this renvoi.
	 *
	 * @return the school ID of this renvoi
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the schoollife session ID of this renvoi.
	 *
	 * @return the schoollife session ID of this renvoi
	 */
	@Override
	public long getSchoollifeSessionId() {
		return model.getSchoollifeSessionId();
	}

	/**
	 * Returns the source schoollife session ID of this renvoi.
	 *
	 * @return the source schoollife session ID of this renvoi
	 */
	@Override
	public long getSourceSchoollifeSessionId() {
		return model.getSourceSchoollifeSessionId();
	}

	/**
	 * Returns the source session ID of this renvoi.
	 *
	 * @return the source session ID of this renvoi
	 */
	@Override
	public long getSourceSessionId() {
		return model.getSourceSessionId();
	}

	/**
	 * Returns the source teacher ID of this renvoi.
	 *
	 * @return the source teacher ID of this renvoi
	 */
	@Override
	public long getSourceTeacherId() {
		return model.getSourceTeacherId();
	}

	/**
	 * Returns the status of this renvoi.
	 *
	 * @return the status of this renvoi
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the student ID of this renvoi.
	 *
	 * @return the student ID of this renvoi
	 */
	@Override
	public long getStudentId() {
		return model.getStudentId();
	}

	/**
	 * Returns the teacher ID of this renvoi.
	 *
	 * @return the teacher ID of this renvoi
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
	 * Sets the org ID of this renvoi.
	 *
	 * @param orgId the org ID of this renvoi
	 */
	@Override
	public void setOrgId(long orgId) {
		model.setOrgId(orgId);
	}

	/**
	 * Sets the primary key of this renvoi.
	 *
	 * @param primaryKey the primary key of this renvoi
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.nero.school.life.service.persistence.RenvoiPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the reason of this renvoi.
	 *
	 * @param reason the reason of this renvoi
	 */
	@Override
	public void setReason(String reason) {
		model.setReason(reason);
	}

	/**
	 * Sets the renvoi date of this renvoi.
	 *
	 * @param renvoiDate the renvoi date of this renvoi
	 */
	@Override
	public void setRenvoiDate(Date renvoiDate) {
		model.setRenvoiDate(renvoiDate);
	}

	/**
	 * Sets the school ID of this renvoi.
	 *
	 * @param schoolId the school ID of this renvoi
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the schoollife session ID of this renvoi.
	 *
	 * @param schoollifeSessionId the schoollife session ID of this renvoi
	 */
	@Override
	public void setSchoollifeSessionId(long schoollifeSessionId) {
		model.setSchoollifeSessionId(schoollifeSessionId);
	}

	/**
	 * Sets the source schoollife session ID of this renvoi.
	 *
	 * @param sourceSchoollifeSessionId the source schoollife session ID of this renvoi
	 */
	@Override
	public void setSourceSchoollifeSessionId(long sourceSchoollifeSessionId) {
		model.setSourceSchoollifeSessionId(sourceSchoollifeSessionId);
	}

	/**
	 * Sets the source session ID of this renvoi.
	 *
	 * @param sourceSessionId the source session ID of this renvoi
	 */
	@Override
	public void setSourceSessionId(long sourceSessionId) {
		model.setSourceSessionId(sourceSessionId);
	}

	/**
	 * Sets the source teacher ID of this renvoi.
	 *
	 * @param sourceTeacherId the source teacher ID of this renvoi
	 */
	@Override
	public void setSourceTeacherId(long sourceTeacherId) {
		model.setSourceTeacherId(sourceTeacherId);
	}

	/**
	 * Sets the status of this renvoi.
	 *
	 * @param status the status of this renvoi
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the student ID of this renvoi.
	 *
	 * @param studentId the student ID of this renvoi
	 */
	@Override
	public void setStudentId(long studentId) {
		model.setStudentId(studentId);
	}

	/**
	 * Sets the teacher ID of this renvoi.
	 *
	 * @param teacherId the teacher ID of this renvoi
	 */
	@Override
	public void setTeacherId(long teacherId) {
		model.setTeacherId(teacherId);
	}

	@Override
	protected RenvoiWrapper wrap(Renvoi renvoi) {
		return new RenvoiWrapper(renvoi);
	}

}