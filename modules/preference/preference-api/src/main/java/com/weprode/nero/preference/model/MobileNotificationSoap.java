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

package com.weprode.nero.preference.model;

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
public class MobileNotificationSoap implements Serializable {

	public static MobileNotificationSoap toSoapModel(MobileNotification model) {
		MobileNotificationSoap soapModel = new MobileNotificationSoap();

		soapModel.setMobileNotificationId(model.getMobileNotificationId());
		soapModel.setUserId(model.getUserId());
		soapModel.setEtabId(model.getEtabId());
		soapModel.setEnable(model.isEnable());
		soapModel.setToken(model.getToken());
		soapModel.setDevice(model.getDevice());

		return soapModel;
	}

	public static MobileNotificationSoap[] toSoapModels(
		MobileNotification[] models) {

		MobileNotificationSoap[] soapModels =
			new MobileNotificationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MobileNotificationSoap[][] toSoapModels(
		MobileNotification[][] models) {

		MobileNotificationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new MobileNotificationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MobileNotificationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MobileNotificationSoap[] toSoapModels(
		List<MobileNotification> models) {

		List<MobileNotificationSoap> soapModels =
			new ArrayList<MobileNotificationSoap>(models.size());

		for (MobileNotification model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new MobileNotificationSoap[soapModels.size()]);
	}

	public MobileNotificationSoap() {
	}

	public long getPrimaryKey() {
		return _mobileNotificationId;
	}

	public void setPrimaryKey(long pk) {
		setMobileNotificationId(pk);
	}

	public long getMobileNotificationId() {
		return _mobileNotificationId;
	}

	public void setMobileNotificationId(long mobileNotificationId) {
		_mobileNotificationId = mobileNotificationId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getEtabId() {
		return _etabId;
	}

	public void setEtabId(long etabId) {
		_etabId = etabId;
	}

	public boolean getEnable() {
		return _enable;
	}

	public boolean isEnable() {
		return _enable;
	}

	public void setEnable(boolean enable) {
		_enable = enable;
	}

	public String getToken() {
		return _token;
	}

	public void setToken(String token) {
		_token = token;
	}

	public String getDevice() {
		return _device;
	}

	public void setDevice(String device) {
		_device = device;
	}

	private long _mobileNotificationId;
	private long _userId;
	private long _etabId;
	private boolean _enable;
	private String _token;
	private String _device;

}