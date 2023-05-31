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

import com.weprode.nero.school.life.service.persistence.RenvoiPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.school.life.service.http.RenvoiServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class RenvoiSoap implements Serializable {

	public static RenvoiSoap toSoapModel(Renvoi model) {
		RenvoiSoap soapModel = new RenvoiSoap();

		soapModel.setSchoollifeSessionId(model.getSchoollifeSessionId());
		soapModel.setStudentId(model.getStudentId());
		soapModel.setOrgId(model.getOrgId());
		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setRenvoiDate(model.getRenvoiDate());
		soapModel.setTeacherId(model.getTeacherId());
		soapModel.setSourceSessionId(model.getSourceSessionId());
		soapModel.setSourceSchoollifeSessionId(
			model.getSourceSchoollifeSessionId());
		soapModel.setSourceTeacherId(model.getSourceTeacherId());
		soapModel.setReason(model.getReason());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static RenvoiSoap[] toSoapModels(Renvoi[] models) {
		RenvoiSoap[] soapModels = new RenvoiSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RenvoiSoap[][] toSoapModels(Renvoi[][] models) {
		RenvoiSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RenvoiSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RenvoiSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RenvoiSoap[] toSoapModels(List<Renvoi> models) {
		List<RenvoiSoap> soapModels = new ArrayList<RenvoiSoap>(models.size());

		for (Renvoi model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RenvoiSoap[soapModels.size()]);
	}

	public RenvoiSoap() {
	}

	public RenvoiPK getPrimaryKey() {
		return new RenvoiPK(_schoollifeSessionId, _studentId);
	}

	public void setPrimaryKey(RenvoiPK pk) {
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

	public long getOrgId() {
		return _orgId;
	}

	public void setOrgId(long orgId) {
		_orgId = orgId;
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	public Date getRenvoiDate() {
		return _renvoiDate;
	}

	public void setRenvoiDate(Date renvoiDate) {
		_renvoiDate = renvoiDate;
	}

	public long getTeacherId() {
		return _teacherId;
	}

	public void setTeacherId(long teacherId) {
		_teacherId = teacherId;
	}

	public long getSourceSessionId() {
		return _sourceSessionId;
	}

	public void setSourceSessionId(long sourceSessionId) {
		_sourceSessionId = sourceSessionId;
	}

	public long getSourceSchoollifeSessionId() {
		return _sourceSchoollifeSessionId;
	}

	public void setSourceSchoollifeSessionId(long sourceSchoollifeSessionId) {
		_sourceSchoollifeSessionId = sourceSchoollifeSessionId;
	}

	public long getSourceTeacherId() {
		return _sourceTeacherId;
	}

	public void setSourceTeacherId(long sourceTeacherId) {
		_sourceTeacherId = sourceTeacherId;
	}

	public String getReason() {
		return _reason;
	}

	public void setReason(String reason) {
		_reason = reason;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _schoollifeSessionId;
	private long _studentId;
	private long _orgId;
	private long _schoolId;
	private Date _renvoiDate;
	private long _teacherId;
	private long _sourceSessionId;
	private long _sourceSchoollifeSessionId;
	private long _sourceTeacherId;
	private String _reason;
	private int _status;

}