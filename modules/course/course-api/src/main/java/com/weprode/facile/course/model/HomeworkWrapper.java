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

package com.weprode.facile.course.model;

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
		attributes.put("teacherId", getTeacherId());
		attributes.put("title", getTitle());
		attributes.put("modificationDate", getModificationDate());
		attributes.put("sourceSessionId", getSourceSessionId());
		attributes.put("targetSessionId", getTargetSessionId());
		attributes.put("targetDate", getTargetDate());
		attributes.put("isCustomStudentList", isIsCustomStudentList());
		attributes.put("estimatedTime", getEstimatedTime());
		attributes.put("publicationDate", getPublicationDate());
		attributes.put("isDraft", isIsDraft());
		attributes.put("isCorrectionSent", isIsCorrectionSent());

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

		Long teacherId = (Long)attributes.get("teacherId");

		if (teacherId != null) {
			setTeacherId(teacherId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Date modificationDate = (Date)attributes.get("modificationDate");

		if (modificationDate != null) {
			setModificationDate(modificationDate);
		}

		Long sourceSessionId = (Long)attributes.get("sourceSessionId");

		if (sourceSessionId != null) {
			setSourceSessionId(sourceSessionId);
		}

		Long targetSessionId = (Long)attributes.get("targetSessionId");

		if (targetSessionId != null) {
			setTargetSessionId(targetSessionId);
		}

		Date targetDate = (Date)attributes.get("targetDate");

		if (targetDate != null) {
			setTargetDate(targetDate);
		}

		Boolean isCustomStudentList = (Boolean)attributes.get(
			"isCustomStudentList");

		if (isCustomStudentList != null) {
			setIsCustomStudentList(isCustomStudentList);
		}

		Integer estimatedTime = (Integer)attributes.get("estimatedTime");

		if (estimatedTime != null) {
			setEstimatedTime(estimatedTime);
		}

		Date publicationDate = (Date)attributes.get("publicationDate");

		if (publicationDate != null) {
			setPublicationDate(publicationDate);
		}

		Boolean isDraft = (Boolean)attributes.get("isDraft");

		if (isDraft != null) {
			setIsDraft(isDraft);
		}

		Boolean isCorrectionSent = (Boolean)attributes.get("isCorrectionSent");

		if (isCorrectionSent != null) {
			setIsCorrectionSent(isCorrectionSent);
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

	@Override
	public org.json.JSONObject convertToJSON(
		com.liferay.portal.kernel.model.User user, boolean includeBlocks,
		boolean withDetails) {

		return model.convertToJSON(user, includeBlocks, withDetails);
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
	 * Returns the estimated time of this homework.
	 *
	 * @return the estimated time of this homework
	 */
	@Override
	public int getEstimatedTime() {
		return model.getEstimatedTime();
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
	 * Returns the is correction sent of this homework.
	 *
	 * @return the is correction sent of this homework
	 */
	@Override
	public boolean getIsCorrectionSent() {
		return model.getIsCorrectionSent();
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
	 * Returns the modification date of this homework.
	 *
	 * @return the modification date of this homework
	 */
	@Override
	public Date getModificationDate() {
		return model.getModificationDate();
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
	 * Returns the title of this homework.
	 *
	 * @return the title of this homework
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns <code>true</code> if this homework is is correction sent.
	 *
	 * @return <code>true</code> if this homework is is correction sent; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsCorrectionSent() {
		return model.isIsCorrectionSent();
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
	 * Sets the estimated time of this homework.
	 *
	 * @param estimatedTime the estimated time of this homework
	 */
	@Override
	public void setEstimatedTime(int estimatedTime) {
		model.setEstimatedTime(estimatedTime);
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
	 * Sets whether this homework is is correction sent.
	 *
	 * @param isCorrectionSent the is correction sent of this homework
	 */
	@Override
	public void setIsCorrectionSent(boolean isCorrectionSent) {
		model.setIsCorrectionSent(isCorrectionSent);
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
	 * Sets the modification date of this homework.
	 *
	 * @param modificationDate the modification date of this homework
	 */
	@Override
	public void setModificationDate(Date modificationDate) {
		model.setModificationDate(modificationDate);
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

	/**
	 * Sets the title of this homework.
	 *
	 * @param title the title of this homework
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	@Override
	protected HomeworkWrapper wrap(Homework homework) {
		return new HomeworkWrapper(homework);
	}

}