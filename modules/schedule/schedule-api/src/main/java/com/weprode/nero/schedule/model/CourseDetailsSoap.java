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
public class CourseDetailsSoap implements Serializable {

	public static CourseDetailsSoap toSoapModel(CourseDetails model) {
		CourseDetailsSoap soapModel = new CourseDetailsSoap();

		soapModel.setCourseGroupId(model.getCourseGroupId());
		soapModel.setColor(model.getColor());
		soapModel.setSubjectId(model.getSubjectId());

		return soapModel;
	}

	public static CourseDetailsSoap[] toSoapModels(CourseDetails[] models) {
		CourseDetailsSoap[] soapModels = new CourseDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CourseDetailsSoap[][] toSoapModels(CourseDetails[][] models) {
		CourseDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CourseDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CourseDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CourseDetailsSoap[] toSoapModels(List<CourseDetails> models) {
		List<CourseDetailsSoap> soapModels = new ArrayList<CourseDetailsSoap>(
			models.size());

		for (CourseDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CourseDetailsSoap[soapModels.size()]);
	}

	public CourseDetailsSoap() {
	}

	public long getPrimaryKey() {
		return _courseGroupId;
	}

	public void setPrimaryKey(long pk) {
		setCourseGroupId(pk);
	}

	public long getCourseGroupId() {
		return _courseGroupId;
	}

	public void setCourseGroupId(long courseGroupId) {
		_courseGroupId = courseGroupId;
	}

	public String getColor() {
		return _color;
	}

	public void setColor(String color) {
		_color = color;
	}

	public long getSubjectId() {
		return _subjectId;
	}

	public void setSubjectId(long subjectId) {
		_subjectId = subjectId;
	}

	private long _courseGroupId;
	private String _color;
	private long _subjectId;

}