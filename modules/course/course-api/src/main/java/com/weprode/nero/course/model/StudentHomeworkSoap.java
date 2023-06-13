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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class StudentHomeworkSoap implements Serializable {

	public static StudentHomeworkSoap toSoapModel(StudentHomework model) {
		StudentHomeworkSoap soapModel = new StudentHomeworkSoap();

		soapModel.setStudentHomeworkId(model.getStudentHomeworkId());
		soapModel.setHomeworkId(model.getHomeworkId());
		soapModel.setStudentId(model.getStudentId());
		soapModel.setIsDone(model.isIsDone());
		soapModel.setIsSent(model.isIsSent());
		soapModel.setSentDate(model.getSentDate());
		soapModel.setSentFileId(model.getSentFileId());
		soapModel.setIsCorrected(model.isIsCorrected());
		soapModel.setComment(model.getComment());
		soapModel.setCorrectionDate(model.getCorrectionDate());

		return soapModel;
	}

	public static StudentHomeworkSoap[] toSoapModels(StudentHomework[] models) {
		StudentHomeworkSoap[] soapModels =
			new StudentHomeworkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StudentHomeworkSoap[][] toSoapModels(
		StudentHomework[][] models) {

		StudentHomeworkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new StudentHomeworkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StudentHomeworkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StudentHomeworkSoap[] toSoapModels(
		List<StudentHomework> models) {

		List<StudentHomeworkSoap> soapModels =
			new ArrayList<StudentHomeworkSoap>(models.size());

		for (StudentHomework model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StudentHomeworkSoap[soapModels.size()]);
	}

	public StudentHomeworkSoap() {
	}

	public long getPrimaryKey() {
		return _studentHomeworkId;
	}

	public void setPrimaryKey(long pk) {
		setStudentHomeworkId(pk);
	}

	public long getStudentHomeworkId() {
		return _studentHomeworkId;
	}

	public void setStudentHomeworkId(long studentHomeworkId) {
		_studentHomeworkId = studentHomeworkId;
	}

	public long getHomeworkId() {
		return _homeworkId;
	}

	public void setHomeworkId(long homeworkId) {
		_homeworkId = homeworkId;
	}

	public long getStudentId() {
		return _studentId;
	}

	public void setStudentId(long studentId) {
		_studentId = studentId;
	}

	public boolean getIsDone() {
		return _isDone;
	}

	public boolean isIsDone() {
		return _isDone;
	}

	public void setIsDone(boolean isDone) {
		_isDone = isDone;
	}

	public boolean getIsSent() {
		return _isSent;
	}

	public boolean isIsSent() {
		return _isSent;
	}

	public void setIsSent(boolean isSent) {
		_isSent = isSent;
	}

	public Date getSentDate() {
		return _sentDate;
	}

	public void setSentDate(Date sentDate) {
		_sentDate = sentDate;
	}

	public long getSentFileId() {
		return _sentFileId;
	}

	public void setSentFileId(long sentFileId) {
		_sentFileId = sentFileId;
	}

	public boolean getIsCorrected() {
		return _isCorrected;
	}

	public boolean isIsCorrected() {
		return _isCorrected;
	}

	public void setIsCorrected(boolean isCorrected) {
		_isCorrected = isCorrected;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public Date getCorrectionDate() {
		return _correctionDate;
	}

	public void setCorrectionDate(Date correctionDate) {
		_correctionDate = correctionDate;
	}

	private long _studentHomeworkId;
	private long _homeworkId;
	private long _studentId;
	private boolean _isDone;
	private boolean _isSent;
	private Date _sentDate;
	private long _sentFileId;
	private boolean _isCorrected;
	private String _comment;
	private Date _correctionDate;

}