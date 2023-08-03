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

package com.weprode.nero.mobile.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.mobile.service.http.UserMobileTokenServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class UserMobileTokenSoap implements Serializable {

	public static UserMobileTokenSoap toSoapModel(UserMobileToken model) {
		UserMobileTokenSoap soapModel = new UserMobileTokenSoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setMobileToken(model.getMobileToken());

		return soapModel;
	}

	public static UserMobileTokenSoap[] toSoapModels(UserMobileToken[] models) {
		UserMobileTokenSoap[] soapModels =
			new UserMobileTokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserMobileTokenSoap[][] toSoapModels(
		UserMobileToken[][] models) {

		UserMobileTokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new UserMobileTokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserMobileTokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserMobileTokenSoap[] toSoapModels(
		List<UserMobileToken> models) {

		List<UserMobileTokenSoap> soapModels =
			new ArrayList<UserMobileTokenSoap>(models.size());

		for (UserMobileToken model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserMobileTokenSoap[soapModels.size()]);
	}

	public UserMobileTokenSoap() {
	}

	public long getPrimaryKey() {
		return _userId;
	}

	public void setPrimaryKey(long pk) {
		setUserId(pk);
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getMobileToken() {
		return _mobileToken;
	}

	public void setMobileToken(String mobileToken) {
		_mobileToken = mobileToken;
	}

	private long _userId;
	private String _mobileToken;

}