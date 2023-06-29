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
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.course.service.http.HomeworkServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class HomeworkSoap implements Serializable {

	public static HomeworkSoap toSoapModel(Homework model) {
		HomeworkSoap soapModel = new HomeworkSoap();

		soapModel.setHomeworkId(model.getHomeworkId());
		soapModel.setHomeworkType(model.getHomeworkType());
		soapModel.setCourseId(model.getCourseId());
		soapModel.setTeacherId(model.getTeacherId());
		soapModel.setTitle(model.getTitle());
		soapModel.setModificationDate(model.getModificationDate());
		soapModel.setSourceSessionId(model.getSourceSessionId());
		soapModel.setTargetSessionId(model.getTargetSessionId());
		soapModel.setTargetDate(model.getTargetDate());
		soapModel.setIsCustomStudentList(model.isIsCustomStudentList());
		soapModel.setEstimatedTime(model.getEstimatedTime());
		soapModel.setPublicationDate(model.getPublicationDate());
		soapModel.setIsDraft(model.isIsDraft());

		return soapModel;
	}

	public static HomeworkSoap[] toSoapModels(Homework[] models) {
		HomeworkSoap[] soapModels = new HomeworkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HomeworkSoap[][] toSoapModels(Homework[][] models) {
		HomeworkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HomeworkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HomeworkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HomeworkSoap[] toSoapModels(List<Homework> models) {
		List<HomeworkSoap> soapModels = new ArrayList<HomeworkSoap>(
			models.size());

		for (Homework model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HomeworkSoap[soapModels.size()]);
	}

	public HomeworkSoap() {
	}

	public long getPrimaryKey() {
		return _homeworkId;
	}

	public void setPrimaryKey(long pk) {
		setHomeworkId(pk);
	}

	public long getHomeworkId() {
		return _homeworkId;
	}

	public void setHomeworkId(long homeworkId) {
		_homeworkId = homeworkId;
	}

	public int getHomeworkType() {
		return _homeworkType;
	}

	public void setHomeworkType(int homeworkType) {
		_homeworkType = homeworkType;
	}

	public long getCourseId() {
		return _courseId;
	}

	public void setCourseId(long courseId) {
		_courseId = courseId;
	}

	public long getTeacherId() {
		return _teacherId;
	}

	public void setTeacherId(long teacherId) {
		_teacherId = teacherId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public Date getModificationDate() {
		return _modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		_modificationDate = modificationDate;
	}

	public long getSourceSessionId() {
		return _sourceSessionId;
	}

	public void setSourceSessionId(long sourceSessionId) {
		_sourceSessionId = sourceSessionId;
	}

	public long getTargetSessionId() {
		return _targetSessionId;
	}

	public void setTargetSessionId(long targetSessionId) {
		_targetSessionId = targetSessionId;
	}

	public Date getTargetDate() {
		return _targetDate;
	}

	public void setTargetDate(Date targetDate) {
		_targetDate = targetDate;
	}

	public boolean getIsCustomStudentList() {
		return _isCustomStudentList;
	}

	public boolean isIsCustomStudentList() {
		return _isCustomStudentList;
	}

	public void setIsCustomStudentList(boolean isCustomStudentList) {
		_isCustomStudentList = isCustomStudentList;
	}

	public int getEstimatedTime() {
		return _estimatedTime;
	}

	public void setEstimatedTime(int estimatedTime) {
		_estimatedTime = estimatedTime;
	}

	public Date getPublicationDate() {
		return _publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		_publicationDate = publicationDate;
	}

	public boolean getIsDraft() {
		return _isDraft;
	}

	public boolean isIsDraft() {
		return _isDraft;
	}

	public void setIsDraft(boolean isDraft) {
		_isDraft = isDraft;
	}

	private long _homeworkId;
	private int _homeworkType;
	private long _courseId;
	private long _teacherId;
	private String _title;
	private Date _modificationDate;
	private long _sourceSessionId;
	private long _targetSessionId;
	private Date _targetDate;
	private boolean _isCustomStudentList;
	private int _estimatedTime;
	private Date _publicationDate;
	private boolean _isDraft;

}