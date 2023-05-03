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
 * This class is a wrapper for {@link Homework}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Homework
 * @generated
 */
public class HomeworkWrapper
	extends BaseModelWrapper<Homework>
	implements Homework, ModelWrapper<Homework> {

	public HomeworkWrapper(Homework homework) {
		super(homework);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("homeworkId", getHomeworkId());
		attributes.put("type", getType());
		attributes.put("sourceSessionId", getSourceSessionId());
		attributes.put("targetSessionId", getTargetSessionId());
		attributes.put("targetWeekId", getTargetWeekId());
		attributes.put("targetDate", getTargetDate());
		attributes.put("groupId", getGroupId());
		attributes.put("teacherId", getTeacherId());
		attributes.put("description", getDescription());
		attributes.put("estimatedTime", getEstimatedTime());
		attributes.put("fromDate", getFromDate());
		attributes.put("isCustomStudentList", isIsCustomStudentList());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long homeworkId = (Long)attributes.get("homeworkId");

		if (homeworkId != null) {
			setHomeworkId(homeworkId);
		}

		Long type = (Long)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long sourceSessionId = (Long)attributes.get("sourceSessionId");

		if (sourceSessionId != null) {
			setSourceSessionId(sourceSessionId);
		}

		Long targetSessionId = (Long)attributes.get("targetSessionId");

		if (targetSessionId != null) {
			setTargetSessionId(targetSessionId);
		}

		Integer targetWeekId = (Integer)attributes.get("targetWeekId");

		if (targetWeekId != null) {
			setTargetWeekId(targetWeekId);
		}

		Date targetDate = (Date)attributes.get("targetDate");

		if (targetDate != null) {
			setTargetDate(targetDate);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long teacherId = (Long)attributes.get("teacherId");

		if (teacherId != null) {
			setTeacherId(teacherId);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long estimatedTime = (Long)attributes.get("estimatedTime");

		if (estimatedTime != null) {
			setEstimatedTime(estimatedTime);
		}

		Date fromDate = (Date)attributes.get("fromDate");

		if (fromDate != null) {
			setFromDate(fromDate);
		}

		Boolean isCustomStudentList = (Boolean)attributes.get(
			"isCustomStudentList");

		if (isCustomStudentList != null) {
			setIsCustomStudentList(isCustomStudentList);
		}
	}

	@Override
	public Homework cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public org.json.JSONObject convertToJSON(
		com.liferay.portal.kernel.model.User user) {

		return model.convertToJSON(user);
	}

	/**
	 * Returns the description of this homework.
	 *
	 * @return the description of this homework
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the estimated time of this homework.
	 *
	 * @return the estimated time of this homework
	 */
	@Override
	public long getEstimatedTime() {
		return model.getEstimatedTime();
	}

	/**
	 * Returns the from date of this homework.
	 *
	 * @return the from date of this homework
	 */
	@Override
	public Date getFromDate() {
		return model.getFromDate();
	}

	/**
	 * Returns the group ID of this homework.
	 *
	 * @return the group ID of this homework
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the homework ID of this homework.
	 *
	 * @return the homework ID of this homework
	 */
	@Override
	public long getHomeworkId() {
		return model.getHomeworkId();
	}

	/**
	 * Returns the is custom student list of this homework.
	 *
	 * @return the is custom student list of this homework
	 */
	@Override
	public boolean getIsCustomStudentList() {
		return model.getIsCustomStudentList();
	}

	/**
	 * Returns the primary key of this homework.
	 *
	 * @return the primary key of this homework
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the source session ID of this homework.
	 *
	 * @return the source session ID of this homework
	 */
	@Override
	public long getSourceSessionId() {
		return model.getSourceSessionId();
	}

	/**
	 * Returns the target date of this homework.
	 *
	 * @return the target date of this homework
	 */
	@Override
	public Date getTargetDate() {
		return model.getTargetDate();
	}

	/**
	 * Returns the target session ID of this homework.
	 *
	 * @return the target session ID of this homework
	 */
	@Override
	public long getTargetSessionId() {
		return model.getTargetSessionId();
	}

	/**
	 * Returns the target week ID of this homework.
	 *
	 * @return the target week ID of this homework
	 */
	@Override
	public int getTargetWeekId() {
		return model.getTargetWeekId();
	}

	/**
	 * Returns the teacher ID of this homework.
	 *
	 * @return the teacher ID of this homework
	 */
	@Override
	public long getTeacherId() {
		return model.getTeacherId();
	}

	/**
	 * Returns the type of this homework.
	 *
	 * @return the type of this homework
	 */
	@Override
	public long getType() {
		return model.getType();
	}

	/**
	 * Returns <code>true</code> if this homework is is custom student list.
	 *
	 * @return <code>true</code> if this homework is is custom student list; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsCustomStudentList() {
		return model.isIsCustomStudentList();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the description of this homework.
	 *
	 * @param description the description of this homework
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the estimated time of this homework.
	 *
	 * @param estimatedTime the estimated time of this homework
	 */
	@Override
	public void setEstimatedTime(long estimatedTime) {
		model.setEstimatedTime(estimatedTime);
	}

	/**
	 * Sets the from date of this homework.
	 *
	 * @param fromDate the from date of this homework
	 */
	@Override
	public void setFromDate(Date fromDate) {
		model.setFromDate(fromDate);
	}

	/**
	 * Sets the group ID of this homework.
	 *
	 * @param groupId the group ID of this homework
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the homework ID of this homework.
	 *
	 * @param homeworkId the homework ID of this homework
	 */
	@Override
	public void setHomeworkId(long homeworkId) {
		model.setHomeworkId(homeworkId);
	}

	/**
	 * Sets whether this homework is is custom student list.
	 *
	 * @param isCustomStudentList the is custom student list of this homework
	 */
	@Override
	public void setIsCustomStudentList(boolean isCustomStudentList) {
		model.setIsCustomStudentList(isCustomStudentList);
	}

	/**
	 * Sets the primary key of this homework.
	 *
	 * @param primaryKey the primary key of this homework
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the source session ID of this homework.
	 *
	 * @param sourceSessionId the source session ID of this homework
	 */
	@Override
	public void setSourceSessionId(long sourceSessionId) {
		model.setSourceSessionId(sourceSessionId);
	}

	/**
	 * Sets the target date of this homework.
	 *
	 * @param targetDate the target date of this homework
	 */
	@Override
	public void setTargetDate(Date targetDate) {
		model.setTargetDate(targetDate);
	}

	/**
	 * Sets the target session ID of this homework.
	 *
	 * @param targetSessionId the target session ID of this homework
	 */
	@Override
	public void setTargetSessionId(long targetSessionId) {
		model.setTargetSessionId(targetSessionId);
	}

	/**
	 * Sets the target week ID of this homework.
	 *
	 * @param targetWeekId the target week ID of this homework
	 */
	@Override
	public void setTargetWeekId(int targetWeekId) {
		model.setTargetWeekId(targetWeekId);
	}

	/**
	 * Sets the teacher ID of this homework.
	 *
	 * @param teacherId the teacher ID of this homework
	 */
	@Override
	public void setTeacherId(long teacherId) {
		model.setTeacherId(teacherId);
	}

	/**
	 * Sets the type of this homework.
	 *
	 * @param type the type of this homework
	 */
	@Override
	public void setType(long type) {
		model.setType(type);
	}

	@Override
	protected HomeworkWrapper wrap(Homework homework) {
		return new HomeworkWrapper(homework);
	}

}