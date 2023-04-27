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

package com.weprode.nero.statistic.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class UserLoginSoap implements Serializable {

	public static UserLoginSoap toSoapModel(UserLogin model) {
		UserLoginSoap soapModel = new UserLoginSoap();

		soapModel.setUserLoginId(model.getUserLoginId());
		soapModel.setUserId(model.getUserId());
		soapModel.setLoginDate(model.getLoginDate());
		soapModel.setRole(model.getRole());
		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setIsMobileApp(model.isIsMobileApp());

		return soapModel;
	}

	public static UserLoginSoap[] toSoapModels(UserLogin[] models) {
		UserLoginSoap[] soapModels = new UserLoginSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserLoginSoap[][] toSoapModels(UserLogin[][] models) {
		UserLoginSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserLoginSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserLoginSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserLoginSoap[] toSoapModels(List<UserLogin> models) {
		List<UserLoginSoap> soapModels = new ArrayList<UserLoginSoap>(
			models.size());

		for (UserLogin model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserLoginSoap[soapModels.size()]);
	}

	public UserLoginSoap() {
	}

	public long getPrimaryKey() {
		return _userLoginId;
	}

	public void setPrimaryKey(long pk) {
		setUserLoginId(pk);
	}

	public long getUserLoginId() {
		return _userLoginId;
	}

	public void setUserLoginId(long userLoginId) {
		_userLoginId = userLoginId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getLoginDate() {
		return _loginDate;
	}

	public void setLoginDate(Date loginDate) {
		_loginDate = loginDate;
	}

	public int getRole() {
		return _role;
	}

	public void setRole(int role) {
		_role = role;
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	public boolean getIsMobileApp() {
		return _isMobileApp;
	}

	public boolean isIsMobileApp() {
		return _isMobileApp;
	}

	public void setIsMobileApp(boolean isMobileApp) {
		_isMobileApp = isMobileApp;
	}

	private long _userLoginId;
	private long _userId;
	private Date _loginDate;
	private int _role;
	private long _schoolId;
	private boolean _isMobileApp;

}