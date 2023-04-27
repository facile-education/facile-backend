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

package com.weprode.nero.school.life.model;

import com.weprode.nero.school.life.service.persistence.SessionStudentPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.school.life.service.http.SessionStudentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class SessionStudentSoap implements Serializable {

	public static SessionStudentSoap toSoapModel(SessionStudent model) {
		SessionStudentSoap soapModel = new SessionStudentSoap();

		soapModel.setSchoollifeSessionId(model.getSchoollifeSessionId());
		soapModel.setStudentId(model.getStudentId());
		soapModel.setSourceTeacherId(model.getSourceTeacherId());
		soapModel.setIsPresent(model.isIsPresent());
		soapModel.setNotifyParents(model.isNotifyParents());
		soapModel.setComment(model.getComment());
		soapModel.setSubject(model.getSubject());

		return soapModel;
	}

	public static SessionStudentSoap[] toSoapModels(SessionStudent[] models) {
		SessionStudentSoap[] soapModels = new SessionStudentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SessionStudentSoap[][] toSoapModels(
		SessionStudent[][] models) {

		SessionStudentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new SessionStudentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SessionStudentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SessionStudentSoap[] toSoapModels(
		List<SessionStudent> models) {

		List<SessionStudentSoap> soapModels = new ArrayList<SessionStudentSoap>(
			models.size());

		for (SessionStudent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SessionStudentSoap[soapModels.size()]);
	}

	public SessionStudentSoap() {
	}

	public SessionStudentPK getPrimaryKey() {
		return new SessionStudentPK(_schoollifeSessionId, _studentId);
	}

	public void setPrimaryKey(SessionStudentPK pk) {
		setSchoollifeSessionId(pk.schoollifeSessionId);
		setStudentId(pk.studentId);
	}

	public long getSchoollifeSessionId() {
		return _schoollifeSessionId;
	}

	public void setSchoollifeSessionId(long schoollifeSessionId) {
		_schoollifeSessionId = schoollifeSessionId;
	}

	public long getStudentId() {
		return _studentId;
	}

	public void setStudentId(long studentId) {
		_studentId = studentId;
	}

	public long getSourceTeacherId() {
		return _sourceTeacherId;
	}

	public void setSourceTeacherId(long sourceTeacherId) {
		_sourceTeacherId = sourceTeacherId;
	}

	public boolean getIsPresent() {
		return _isPresent;
	}

	public boolean isIsPresent() {
		return _isPresent;
	}

	public void setIsPresent(boolean isPresent) {
		_isPresent = isPresent;
	}

	public boolean getNotifyParents() {
		return _notifyParents;
	}

	public boolean isNotifyParents() {
		return _notifyParents;
	}

	public void setNotifyParents(boolean notifyParents) {
		_notifyParents = notifyParents;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public String getSubject() {
		return _subject;
	}

	public void setSubject(String subject) {
		_subject = subject;
	}

	private long _schoollifeSessionId;
	private long _studentId;
	private long _sourceTeacherId;
	private boolean _isPresent;
	private boolean _notifyParents;
	private String _comment;
	private String _subject;

}