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
public class TeacherGroupColorSoap implements Serializable {

	public static TeacherGroupColorSoap toSoapModel(TeacherGroupColor model) {
		TeacherGroupColorSoap soapModel = new TeacherGroupColorSoap();

		soapModel.setTeacherGroupColorId(model.getTeacherGroupColorId());
		soapModel.setTeacherId(model.getTeacherId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setColor(model.getColor());

		return soapModel;
	}

	public static TeacherGroupColorSoap[] toSoapModels(
		TeacherGroupColor[] models) {

		TeacherGroupColorSoap[] soapModels =
			new TeacherGroupColorSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TeacherGroupColorSoap[][] toSoapModels(
		TeacherGroupColor[][] models) {

		TeacherGroupColorSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TeacherGroupColorSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TeacherGroupColorSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TeacherGroupColorSoap[] toSoapModels(
		List<TeacherGroupColor> models) {

		List<TeacherGroupColorSoap> soapModels =
			new ArrayList<TeacherGroupColorSoap>(models.size());

		for (TeacherGroupColor model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TeacherGroupColorSoap[soapModels.size()]);
	}

	public TeacherGroupColorSoap() {
	}

	public long getPrimaryKey() {
		return _teacherGroupColorId;
	}

	public void setPrimaryKey(long pk) {
		setTeacherGroupColorId(pk);
	}

	public long getTeacherGroupColorId() {
		return _teacherGroupColorId;
	}

	public void setTeacherGroupColorId(long teacherGroupColorId) {
		_teacherGroupColorId = teacherGroupColorId;
	}

	public long getTeacherId() {
		return _teacherId;
	}

	public void setTeacherId(long teacherId) {
		_teacherId = teacherId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getColor() {
		return _color;
	}

	public void setColor(String color) {
		_color = color;
	}

	private long _teacherGroupColorId;
	private long _teacherId;
	private long _groupId;
	private String _color;

}