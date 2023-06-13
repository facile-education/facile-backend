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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class TeacherSubjectSoap implements Serializable {

	public static TeacherSubjectSoap toSoapModel(TeacherSubject model) {
		TeacherSubjectSoap soapModel = new TeacherSubjectSoap();

		soapModel.setTeacherSubjectId(model.getTeacherSubjectId());
		soapModel.setTeacherId(model.getTeacherId());
		soapModel.setSubjectId(model.getSubjectId());
		soapModel.setSchoolId(model.getSchoolId());

		return soapModel;
	}

	public static TeacherSubjectSoap[] toSoapModels(TeacherSubject[] models) {
		TeacherSubjectSoap[] soapModels = new TeacherSubjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TeacherSubjectSoap[][] toSoapModels(
		TeacherSubject[][] models) {

		TeacherSubjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TeacherSubjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TeacherSubjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TeacherSubjectSoap[] toSoapModels(
		List<TeacherSubject> models) {

		List<TeacherSubjectSoap> soapModels = new ArrayList<TeacherSubjectSoap>(
			models.size());

		for (TeacherSubject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TeacherSubjectSoap[soapModels.size()]);
	}

	public TeacherSubjectSoap() {
	}

	public long getPrimaryKey() {
		return _teacherSubjectId;
	}

	public void setPrimaryKey(long pk) {
		setTeacherSubjectId(pk);
	}

	public long getTeacherSubjectId() {
		return _teacherSubjectId;
	}

	public void setTeacherSubjectId(long teacherSubjectId) {
		_teacherSubjectId = teacherSubjectId;
	}

	public long getTeacherId() {
		return _teacherId;
	}

	public void setTeacherId(long teacherId) {
		_teacherId = teacherId;
	}

	public long getSubjectId() {
		return _subjectId;
	}

	public void setSubjectId(long subjectId) {
		_subjectId = subjectId;
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	private long _teacherSubjectId;
	private long _teacherId;
	private long _subjectId;
	private long _schoolId;

}