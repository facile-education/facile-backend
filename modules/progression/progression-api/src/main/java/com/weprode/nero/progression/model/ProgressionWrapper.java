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

package com.weprode.nero.progression.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Progression}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Progression
 * @generated
 */
public class ProgressionWrapper
	extends BaseModelWrapper<Progression>
	implements ModelWrapper<Progression>, Progression {

	public ProgressionWrapper(Progression progression) {
		super(progression);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("progressionId", getProgressionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("teacherId", getTeacherId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("volee", getVolee());
		attributes.put("subjectId", getSubjectId());
		attributes.put("color", getColor());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long progressionId = (Long)attributes.get("progressionId");

		if (progressionId != null) {
			setProgressionId(progressionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long teacherId = (Long)attributes.get("teacherId");

		if (teacherId != null) {
			setTeacherId(teacherId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String volee = (String)attributes.get("volee");

		if (volee != null) {
			setVolee(volee);
		}

		Long subjectId = (Long)attributes.get("subjectId");

		if (subjectId != null) {
			setSubjectId(subjectId);
		}

		String color = (String)attributes.get("color");

		if (color != null) {
			setColor(color);
		}
	}

	@Override
	public Progression cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public org.json.JSONObject convertToJSON() {
		return model.convertToJSON();
	}

	/**
	 * Returns the color of this progression.
	 *
	 * @return the color of this progression
	 */
	@Override
	public String getColor() {
		return model.getColor();
	}

	/**
	 * Returns the company ID of this progression.
	 *
	 * @return the company ID of this progression
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this progression.
	 *
	 * @return the create date of this progression
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this progression.
	 *
	 * @return the description of this progression
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the modified date of this progression.
	 *
	 * @return the modified date of this progression
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this progression.
	 *
	 * @return the name of this progression
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this progression.
	 *
	 * @return the primary key of this progression
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the progression ID of this progression.
	 *
	 * @return the progression ID of this progression
	 */
	@Override
	public long getProgressionId() {
		return model.getProgressionId();
	}

	/**
	 * Returns the subject ID of this progression.
	 *
	 * @return the subject ID of this progression
	 */
	@Override
	public long getSubjectId() {
		return model.getSubjectId();
	}

	/**
	 * Returns the teacher ID of this progression.
	 *
	 * @return the teacher ID of this progression
	 */
	@Override
	public long getTeacherId() {
		return model.getTeacherId();
	}

	/**
	 * Returns the volee of this progression.
	 *
	 * @return the volee of this progression
	 */
	@Override
	public String getVolee() {
		return model.getVolee();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the color of this progression.
	 *
	 * @param color the color of this progression
	 */
	@Override
	public void setColor(String color) {
		model.setColor(color);
	}

	/**
	 * Sets the company ID of this progression.
	 *
	 * @param companyId the company ID of this progression
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this progression.
	 *
	 * @param createDate the create date of this progression
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this progression.
	 *
	 * @param description the description of this progression
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the modified date of this progression.
	 *
	 * @param modifiedDate the modified date of this progression
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this progression.
	 *
	 * @param name the name of this progression
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this progression.
	 *
	 * @param primaryKey the primary key of this progression
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the progression ID of this progression.
	 *
	 * @param progressionId the progression ID of this progression
	 */
	@Override
	public void setProgressionId(long progressionId) {
		model.setProgressionId(progressionId);
	}

	/**
	 * Sets the subject ID of this progression.
	 *
	 * @param subjectId the subject ID of this progression
	 */
	@Override
	public void setSubjectId(long subjectId) {
		model.setSubjectId(subjectId);
	}

	/**
	 * Sets the teacher ID of this progression.
	 *
	 * @param teacherId the teacher ID of this progression
	 */
	@Override
	public void setTeacherId(long teacherId) {
		model.setTeacherId(teacherId);
	}

	/**
	 * Sets the volee of this progression.
	 *
	 * @param volee the volee of this progression
	 */
	@Override
	public void setVolee(String volee) {
		model.setVolee(volee);
	}

	@Override
	protected ProgressionWrapper wrap(Progression progression) {
		return new ProgressionWrapper(progression);
	}

}