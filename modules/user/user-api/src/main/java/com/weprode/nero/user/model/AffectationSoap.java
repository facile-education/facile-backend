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

package com.weprode.nero.user.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.user.service.http.AffectationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class AffectationSoap implements Serializable {

	public static AffectationSoap toSoapModel(Affectation model) {
		AffectationSoap soapModel = new AffectationSoap();

		soapModel.setAffectationId(model.getAffectationId());
		soapModel.setUserId(model.getUserId());
		soapModel.setOrgId(model.getOrgId());
		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setType(model.getType());
		soapModel.setAdminUserId(model.getAdminUserId());
		soapModel.setAffectationDate(model.getAffectationDate());
		soapModel.setExpirationDate(model.getExpirationDate());

		return soapModel;
	}

	public static AffectationSoap[] toSoapModels(Affectation[] models) {
		AffectationSoap[] soapModels = new AffectationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AffectationSoap[][] toSoapModels(Affectation[][] models) {
		AffectationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AffectationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AffectationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AffectationSoap[] toSoapModels(List<Affectation> models) {
		List<AffectationSoap> soapModels = new ArrayList<AffectationSoap>(
			models.size());

		for (Affectation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AffectationSoap[soapModels.size()]);
	}

	public AffectationSoap() {
	}

	public long getPrimaryKey() {
		return _affectationId;
	}

	public void setPrimaryKey(long pk) {
		setAffectationId(pk);
	}

	public long getAffectationId() {
		return _affectationId;
	}

	public void setAffectationId(long affectationId) {
		_affectationId = affectationId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public long getAdminUserId() {
		return _adminUserId;
	}

	public void setAdminUserId(long adminUserId) {
		_adminUserId = adminUserId;
	}

	public Date getAffectationDate() {
		return _affectationDate;
	}

	public void setAffectationDate(Date affectationDate) {
		_affectationDate = affectationDate;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	private long _affectationId;
	private long _userId;
	private long _orgId;
	private long _schoolId;
	private int _type;
	private long _adminUserId;
	private Date _affectationDate;
	private Date _expirationDate;

}