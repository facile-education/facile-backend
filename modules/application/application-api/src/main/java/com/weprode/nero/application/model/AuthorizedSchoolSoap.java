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

package com.weprode.nero.application.model;

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
public class AuthorizedSchoolSoap implements Serializable {

	public static AuthorizedSchoolSoap toSoapModel(AuthorizedSchool model) {
		AuthorizedSchoolSoap soapModel = new AuthorizedSchoolSoap();

		soapModel.setAuthorizedSchoolId(model.getAuthorizedSchoolId());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setSchoolId(model.getSchoolId());

		return soapModel;
	}

	public static AuthorizedSchoolSoap[] toSoapModels(
		AuthorizedSchool[] models) {

		AuthorizedSchoolSoap[] soapModels =
			new AuthorizedSchoolSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AuthorizedSchoolSoap[][] toSoapModels(
		AuthorizedSchool[][] models) {

		AuthorizedSchoolSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new AuthorizedSchoolSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AuthorizedSchoolSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AuthorizedSchoolSoap[] toSoapModels(
		List<AuthorizedSchool> models) {

		List<AuthorizedSchoolSoap> soapModels =
			new ArrayList<AuthorizedSchoolSoap>(models.size());

		for (AuthorizedSchool model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AuthorizedSchoolSoap[soapModels.size()]);
	}

	public AuthorizedSchoolSoap() {
	}

	public long getPrimaryKey() {
		return _authorizedSchoolId;
	}

	public void setPrimaryKey(long pk) {
		setAuthorizedSchoolId(pk);
	}

	public long getAuthorizedSchoolId() {
		return _authorizedSchoolId;
	}

	public void setAuthorizedSchoolId(long authorizedSchoolId) {
		_authorizedSchoolId = authorizedSchoolId;
	}

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	private long _authorizedSchoolId;
	private long _applicationId;
	private long _schoolId;

}