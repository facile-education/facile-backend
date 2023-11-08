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
 * This class is a wrapper for {@link StudentHomework}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StudentHomework
 * @generated
 */
public class StudentHomeworkWrapper
	extends BaseModelWrapper<StudentHomework>
	implements ModelWrapper<StudentHomework>, StudentHomework {

	public StudentHomeworkWrapper(StudentHomework studentHomework) {
		super(studentHomework);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("studentHomeworkId", getStudentHomeworkId());
		attributes.put("homeworkId", getHomeworkId());
		attributes.put("studentId", getStudentId());
		attributes.put("isDone", isIsDone());
		attributes.put("isSent", isIsSent());
		attributes.put("sentDate", getSentDate());
		attributes.put("sentFileId", getSentFileId());
		attributes.put("isCorrected", isIsCorrected());
		attributes.put("comment", getComment());
		attributes.put("correctionDate", getCorrectionDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long studentHomeworkId = (Long)attributes.get("studentHomeworkId");

		if (studentHomeworkId != null) {
			setStudentHomeworkId(studentHomeworkId);
		}

		Long homeworkId = (Long)attributes.get("homeworkId");

		if (homeworkId != null) {
			setHomeworkId(homeworkId);
		}

		Long studentId = (Long)attributes.get("studentId");

		if (studentId != null) {
			setStudentId(studentId);
		}

		Boolean isDone = (Boolean)attributes.get("isDone");

		if (isDone != null) {
			setIsDone(isDone);
		}

		Boolean isSent = (Boolean)attributes.get("isSent");

		if (isSent != null) {
			setIsSent(isSent);
		}

		Date sentDate = (Date)attributes.get("sentDate");

		if (sentDate != null) {
			setSentDate(sentDate);
		}

		Long sentFileId = (Long)attributes.get("sentFileId");

		if (sentFileId != null) {
			setSentFileId(sentFileId);
		}

		Boolean isCorrected = (Boolean)attributes.get("isCorrected");

		if (isCorrected != null) {
			setIsCorrected(isCorrected);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}

		Date correctionDate = (Date)attributes.get("correctionDate");

		if (correctionDate != null) {
			setCorrectionDate(correctionDate);
		}
	}

	@Override
	public StudentHomework cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public org.json.JSONObject convertToJSON() {
		return model.convertToJSON();
	}

	/**
	 * Returns the comment of this student homework.
	 *
	 * @return the comment of this student homework
	 */
	@Override
	public String getComment() {
		return model.getComment();
	}

	/**
	 * Returns the correction date of this student homework.
	 *
	 * @return the correction date of this student homework
	 */
	@Override
	public Date getCorrectionDate() {
		return model.getCorrectionDate();
	}

	/**
	 * Returns the homework ID of this student homework.
	 *
	 * @return the homework ID of this student homework
	 */
	@Override
	public long getHomeworkId() {
		return model.getHomeworkId();
	}

	/**
	 * Returns the is corrected of this student homework.
	 *
	 * @return the is corrected of this student homework
	 */
	@Override
	public boolean getIsCorrected() {
		return model.getIsCorrected();
	}

	/**
	 * Returns the is done of this student homework.
	 *
	 * @return the is done of this student homework
	 */
	@Override
	public boolean getIsDone() {
		return model.getIsDone();
	}

	/**
	 * Returns the is sent of this student homework.
	 *
	 * @return the is sent of this student homework
	 */
	@Override
	public boolean getIsSent() {
		return model.getIsSent();
	}

	/**
	 * Returns the primary key of this student homework.
	 *
	 * @return the primary key of this student homework
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the sent date of this student homework.
	 *
	 * @return the sent date of this student homework
	 */
	@Override
	public Date getSentDate() {
		return model.getSentDate();
	}

	/**
	 * Returns the sent file ID of this student homework.
	 *
	 * @return the sent file ID of this student homework
	 */
	@Override
	public long getSentFileId() {
		return model.getSentFileId();
	}

	/**
	 * Returns the student homework ID of this student homework.
	 *
	 * @return the student homework ID of this student homework
	 */
	@Override
	public long getStudentHomeworkId() {
		return model.getStudentHomeworkId();
	}

	/**
	 * Returns the student ID of this student homework.
	 *
	 * @return the student ID of this student homework
	 */
	@Override
	public long getStudentId() {
		return model.getStudentId();
	}

	/**
	 * Returns <code>true</code> if this student homework is is corrected.
	 *
	 * @return <code>true</code> if this student homework is is corrected; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsCorrected() {
		return model.isIsCorrected();
	}

	/**
	 * Returns <code>true</code> if this student homework is is done.
	 *
	 * @return <code>true</code> if this student homework is is done; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsDone() {
		return model.isIsDone();
	}

	/**
	 * Returns <code>true</code> if this student homework is is sent.
	 *
	 * @return <code>true</code> if this student homework is is sent; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsSent() {
		return model.isIsSent();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the comment of this student homework.
	 *
	 * @param comment the comment of this student homework
	 */
	@Override
	public void setComment(String comment) {
		model.setComment(comment);
	}

	/**
	 * Sets the correction date of this student homework.
	 *
	 * @param correctionDate the correction date of this student homework
	 */
	@Override
	public void setCorrectionDate(Date correctionDate) {
		model.setCorrectionDate(correctionDate);
	}

	/**
	 * Sets the homework ID of this student homework.
	 *
	 * @param homeworkId the homework ID of this student homework
	 */
	@Override
	public void setHomeworkId(long homeworkId) {
		model.setHomeworkId(homeworkId);
	}

	/**
	 * Sets whether this student homework is is corrected.
	 *
	 * @param isCorrected the is corrected of this student homework
	 */
	@Override
	public void setIsCorrected(boolean isCorrected) {
		model.setIsCorrected(isCorrected);
	}

	/**
	 * Sets whether this student homework is is done.
	 *
	 * @param isDone the is done of this student homework
	 */
	@Override
	public void setIsDone(boolean isDone) {
		model.setIsDone(isDone);
	}

	/**
	 * Sets whether this student homework is is sent.
	 *
	 * @param isSent the is sent of this student homework
	 */
	@Override
	public void setIsSent(boolean isSent) {
		model.setIsSent(isSent);
	}

	/**
	 * Sets the primary key of this student homework.
	 *
	 * @param primaryKey the primary key of this student homework
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the sent date of this student homework.
	 *
	 * @param sentDate the sent date of this student homework
	 */
	@Override
	public void setSentDate(Date sentDate) {
		model.setSentDate(sentDate);
	}

	/**
	 * Sets the sent file ID of this student homework.
	 *
	 * @param sentFileId the sent file ID of this student homework
	 */
	@Override
	public void setSentFileId(long sentFileId) {
		model.setSentFileId(sentFileId);
	}

	/**
	 * Sets the student homework ID of this student homework.
	 *
	 * @param studentHomeworkId the student homework ID of this student homework
	 */
	@Override
	public void setStudentHomeworkId(long studentHomeworkId) {
		model.setStudentHomeworkId(studentHomeworkId);
	}

	/**
	 * Sets the student ID of this student homework.
	 *
	 * @param studentId the student ID of this student homework
	 */
	@Override
	public void setStudentId(long studentId) {
		model.setStudentId(studentId);
	}

	@Override
	protected StudentHomeworkWrapper wrap(StudentHomework studentHomework) {
		return new StudentHomeworkWrapper(studentHomework);
	}

}