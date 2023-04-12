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

package com.weprode.nero.document.model;

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
public class LoolTokenSoap implements Serializable {

	public static LoolTokenSoap toSoapModel(LoolToken model) {
		LoolTokenSoap soapModel = new LoolTokenSoap();

		soapModel.setLoolTokenId(model.getLoolTokenId());
		soapModel.setUserId(model.getUserId());
		soapModel.setToken(model.getToken());

		return soapModel;
	}

	public static LoolTokenSoap[] toSoapModels(LoolToken[] models) {
		LoolTokenSoap[] soapModels = new LoolTokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoolTokenSoap[][] toSoapModels(LoolToken[][] models) {
		LoolTokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoolTokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoolTokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoolTokenSoap[] toSoapModels(List<LoolToken> models) {
		List<LoolTokenSoap> soapModels = new ArrayList<LoolTokenSoap>(
			models.size());

		for (LoolToken model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoolTokenSoap[soapModels.size()]);
	}

	public LoolTokenSoap() {
	}

	public long getPrimaryKey() {
		return _loolTokenId;
	}

	public void setPrimaryKey(long pk) {
		setLoolTokenId(pk);
	}

	public long getLoolTokenId() {
		return _loolTokenId;
	}

	public void setLoolTokenId(long loolTokenId) {
		_loolTokenId = loolTokenId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getToken() {
		return _token;
	}

	public void setToken(String token) {
		_token = token;
	}

	private long _loolTokenId;
	private long _userId;
	private String _token;

}