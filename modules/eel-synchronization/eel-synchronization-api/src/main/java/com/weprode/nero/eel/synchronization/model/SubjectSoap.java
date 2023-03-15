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

package com.weprode.nero.eel.synchronization.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.eel.synchronization.service.http.SubjectServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class SubjectSoap implements Serializable {

	public static SubjectSoap toSoapModel(Subject model) {
		SubjectSoap soapModel = new SubjectSoap();

		soapModel.setSubjectId(model.getSubjectId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static SubjectSoap[] toSoapModels(Subject[] models) {
		SubjectSoap[] soapModels = new SubjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SubjectSoap[][] toSoapModels(Subject[][] models) {
		SubjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SubjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SubjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SubjectSoap[] toSoapModels(List<Subject> models) {
		List<SubjectSoap> soapModels = new ArrayList<SubjectSoap>(
			models.size());

		for (Subject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SubjectSoap[soapModels.size()]);
	}

	public SubjectSoap() {
	}

	public long getPrimaryKey() {
		return _subjectId;
	}

	public void setPrimaryKey(long pk) {
		setSubjectId(pk);
	}

	public long getSubjectId() {
		return _subjectId;
	}

	public void setSubjectId(long subjectId) {
		_subjectId = subjectId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _subjectId;
	private String _name;

}