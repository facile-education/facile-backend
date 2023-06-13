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

package com.weprode.nero.course.model;

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
		attributes.put("homeworkType", getHomeworkType());
		attributes.put("courseId", getCourseId());
		attributes.put("sourceSessionId", getSourceSessionId());
		attributes.put("fromDate", getFromDate());
		attributes.put("targetSessionId", getTargetSessionId());
		attributes.put("targetDate", getTargetDate());
		attributes.put("teacherId", getTeacherId());
		attributes.put("isCustomStudentList", isIsCustomStudentList());
		attributes.put("publicationDate", getPublicationDate());
		attributes.put("isDraft", isIsDraft());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long homeworkId = (Long)attributes.get("homeworkId");

		if (homeworkId != null) {
			setHomeworkId(homeworkId);
		}

		Integer homeworkType = (Integer)attributes.get("homeworkType");

		if (homeworkType != null) {
			setHomeworkType(homeworkType);
		}

		Long courseId = (Long)attributes.get("courseId");

		if (courseId != null) {
			setCourseId(courseId);
		}

		Long sourceSessionId = (Long)attributes.get("sourceSessionId");

		if (sourceSessionId != null) {
			setSourceSessionId(sourceSessionId);
		}

		Date fromDate = (Date)attributes.get("fromDate");

		if (fromDate != null) {
			setFromDate(fromDate);
		}

		Long targetSessionId = (Long)attributes.get("targetSessionId");

		if (targetSessionId != null) {
			setTargetSessionId(targetSessionId);
		}

		Date targetDate = (Date)attributes.get("targetDate");

		if (targetDate != null) {
			setTargetDate(targetDate);
		}

		Long teacherId = (Long)attributes.get("teacherId");

		if (teacherId != null) {
			setTeacherId(teacherId);
		}

		Boolean isCustomStudentList = (Boolean)attributes.get(
			"isCustomStudentList");

		if (isCustomStudentList != null) {
			setIsCustomStudentList(isCustomStudentList);
		}

		Date publicationDate = (Date)attributes.get("publicationDate");

		if (publicationDate != null) {
			setPublicationDate(publicationDate);
		}

		Boolean isDraft = (Boolean)attributes.get("isDraft");

		if (isDraft != null) {
			setIsDraft(isDraft);
		}
	}

	@Override
	public Homework cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public org.json.JSONObject convertToJSON(
		com.liferay.portal.kernel.model.User user, boolean includeBlocks) {

		return model.convertToJSON(user, includeBlocks);
	}

	/**
	 * Returns the course ID of this homework.
	 *
	 * @return the course ID of this homework
	 */
	@Override
	public long getCourseId() {
		return model.getCourseId();
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
	 * Returns the homework ID of this homework.
	 *
	 * @return the homework ID of this homework
	 */
	@Override
	public long getHomeworkId() {
		return model.getHomeworkId();
	}

	/**
	 * Returns the homework type of this homework.
	 *
	 * @return the homework type of this homework
	 */
	@Override
	public int getHomeworkType() {
		return model.getHomeworkType();
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
	 * Returns the is draft of this homework.
	 *
	 * @return the is draft of this homework
	 */
	@Override
	public boolean getIsDraft() {
		return model.getIsDraft();
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
	 * Returns the publication date of this homework.
	 *
	 * @return the publication date of this homework
	 */
	@Override
	public Date getPublicationDate() {
		return model.getPublicationDate();
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
	 * Returns the teacher ID of this homework.
	 *
	 * @return the teacher ID of this homework
	 */
	@Override
	public long getTeacherId() {
		return model.getTeacherId();
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

	/**
	 * Returns <code>true</code> if this homework is is draft.
	 *
	 * @return <code>true</code> if this homework is is draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsDraft() {
		return model.isIsDraft();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the course ID of this homework.
	 *
	 * @param courseId the course ID of this homework
	 */
	@Override
	public void setCourseId(long courseId) {
		model.setCourseId(courseId);
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
	 * Sets the homework ID of this homework.
	 *
	 * @param homeworkId the homework ID of this homework
	 */
	@Override
	public void setHomeworkId(long homeworkId) {
		model.setHomeworkId(homeworkId);
	}

	/**
	 * Sets the homework type of this homework.
	 *
	 * @param homeworkType the homework type of this homework
	 */
	@Override
	public void setHomeworkType(int homeworkType) {
		model.setHomeworkType(homeworkType);
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
	 * Sets whether this homework is is draft.
	 *
	 * @param isDraft the is draft of this homework
	 */
	@Override
	public void setIsDraft(boolean isDraft) {
		model.setIsDraft(isDraft);
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
	 * Sets the publication date of this homework.
	 *
	 * @param publicationDate the publication date of this homework
	 */
	@Override
	public void setPublicationDate(Date publicationDate) {
		model.setPublicationDate(publicationDate);
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
	 * Sets the teacher ID of this homework.
	 *
	 * @param teacherId the teacher ID of this homework
	 */
	@Override
	public void setTeacherId(long teacherId) {
		model.setTeacherId(teacherId);
	}

	@Override
	protected HomeworkWrapper wrap(Homework homework) {
		return new HomeworkWrapper(homework);
	}

}