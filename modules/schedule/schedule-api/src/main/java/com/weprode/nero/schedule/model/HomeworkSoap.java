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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.schedule.service.http.HomeworkServiceSoap}.
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
		soapModel.setType(model.getType());
		soapModel.setSourceSessionId(model.getSourceSessionId());
		soapModel.setTargetSessionId(model.getTargetSessionId());
		soapModel.setTargetWeekId(model.getTargetWeekId());
		soapModel.setTargetDate(model.getTargetDate());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setTeacherId(model.getTeacherId());
		soapModel.setDescription(model.getDescription());
		soapModel.setEstimatedTime(model.getEstimatedTime());
		soapModel.setFromDate(model.getFromDate());
		soapModel.setIsCustomStudentList(model.isIsCustomStudentList());

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

	public long getType() {
		return _type;
	}

	public void setType(long type) {
		_type = type;
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

	public int getTargetWeekId() {
		return _targetWeekId;
	}

	public void setTargetWeekId(int targetWeekId) {
		_targetWeekId = targetWeekId;
	}

	public Date getTargetDate() {
		return _targetDate;
	}

	public void setTargetDate(Date targetDate) {
		_targetDate = targetDate;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getTeacherId() {
		return _teacherId;
	}

	public void setTeacherId(long teacherId) {
		_teacherId = teacherId;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getEstimatedTime() {
		return _estimatedTime;
	}

	public void setEstimatedTime(long estimatedTime) {
		_estimatedTime = estimatedTime;
	}

	public Date getFromDate() {
		return _fromDate;
	}

	public void setFromDate(Date fromDate) {
		_fromDate = fromDate;
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

	private long _homeworkId;
	private long _type;
	private long _sourceSessionId;
	private long _targetSessionId;
	private int _targetWeekId;
	private Date _targetDate;
	private long _groupId;
	private long _teacherId;
	private String _description;
	private long _estimatedTime;
	private Date _fromDate;
	private boolean _isCustomStudentList;

}