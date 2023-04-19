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
public class SubjectGroupColorSoap implements Serializable {

	public static SubjectGroupColorSoap toSoapModel(SubjectGroupColor model) {
		SubjectGroupColorSoap soapModel = new SubjectGroupColorSoap();

		soapModel.setSubjectGroupColorId(model.getSubjectGroupColorId());
		soapModel.setSubject(model.getSubject());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setColor(model.getColor());

		return soapModel;
	}

	public static SubjectGroupColorSoap[] toSoapModels(
		SubjectGroupColor[] models) {

		SubjectGroupColorSoap[] soapModels =
			new SubjectGroupColorSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SubjectGroupColorSoap[][] toSoapModels(
		SubjectGroupColor[][] models) {

		SubjectGroupColorSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new SubjectGroupColorSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SubjectGroupColorSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SubjectGroupColorSoap[] toSoapModels(
		List<SubjectGroupColor> models) {

		List<SubjectGroupColorSoap> soapModels =
			new ArrayList<SubjectGroupColorSoap>(models.size());

		for (SubjectGroupColor model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SubjectGroupColorSoap[soapModels.size()]);
	}

	public SubjectGroupColorSoap() {
	}

	public long getPrimaryKey() {
		return _subjectGroupColorId;
	}

	public void setPrimaryKey(long pk) {
		setSubjectGroupColorId(pk);
	}

	public long getSubjectGroupColorId() {
		return _subjectGroupColorId;
	}

	public void setSubjectGroupColorId(long subjectGroupColorId) {
		_subjectGroupColorId = subjectGroupColorId;
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
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

	private long _subjectGroupColorId;
	private String _subject;
	private long _groupId;
	private String _color;

}