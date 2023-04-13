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
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.application.service.http.BroadcastServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class BroadcastSoap implements Serializable {

	public static BroadcastSoap toSoapModel(Broadcast model) {
		BroadcastSoap soapModel = new BroadcastSoap();

		soapModel.setBroadcastId(model.getBroadcastId());
		soapModel.setSchoolId(model.getSchoolId());
		soapModel.setApplicationId(model.getApplicationId());
		soapModel.setIsBroadcasted(model.isIsBroadcasted());
		soapModel.setApplicationUrl(model.getApplicationUrl());

		return soapModel;
	}

	public static BroadcastSoap[] toSoapModels(Broadcast[] models) {
		BroadcastSoap[] soapModels = new BroadcastSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BroadcastSoap[][] toSoapModels(Broadcast[][] models) {
		BroadcastSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BroadcastSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BroadcastSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BroadcastSoap[] toSoapModels(List<Broadcast> models) {
		List<BroadcastSoap> soapModels = new ArrayList<BroadcastSoap>(
			models.size());

		for (Broadcast model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BroadcastSoap[soapModels.size()]);
	}

	public BroadcastSoap() {
	}

	public long getPrimaryKey() {
		return _broadcastId;
	}

	public void setPrimaryKey(long pk) {
		setBroadcastId(pk);
	}

	public long getBroadcastId() {
		return _broadcastId;
	}

	public void setBroadcastId(long broadcastId) {
		_broadcastId = broadcastId;
	}

	public long getSchoolId() {
		return _schoolId;
	}

	public void setSchoolId(long schoolId) {
		_schoolId = schoolId;
	}

	public long getApplicationId() {
		return _applicationId;
	}

	public void setApplicationId(long applicationId) {
		_applicationId = applicationId;
	}

	public boolean getIsBroadcasted() {
		return _isBroadcasted;
	}

	public boolean isIsBroadcasted() {
		return _isBroadcasted;
	}

	public void setIsBroadcasted(boolean isBroadcasted) {
		_isBroadcasted = isBroadcasted;
	}

	public String getApplicationUrl() {
		return _applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		_applicationUrl = applicationUrl;
	}

	private long _broadcastId;
	private long _schoolId;
	private long _applicationId;
	private boolean _isBroadcasted;
	private String _applicationUrl;

}