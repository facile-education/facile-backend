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
public class MobileDeviceSoap implements Serializable {

	public static MobileDeviceSoap toSoapModel(MobileDevice model) {
		MobileDeviceSoap soapModel = new MobileDeviceSoap();

		soapModel.setMobileDeviceId(model.getMobileDeviceId());
		soapModel.setManufaturerDeviceId(model.getManufaturerDeviceId());
		soapModel.setUserId(model.getUserId());
		soapModel.setDeviceModel(model.getDeviceModel());
		soapModel.setManufacturer(model.getManufacturer());
		soapModel.setOperatingSystem(model.getOperatingSystem());
		soapModel.setOperatingSystemVersion(model.getOperatingSystemVersion());
		soapModel.setBrowserUA(model.getBrowserUA());

		return soapModel;
	}

	public static MobileDeviceSoap[] toSoapModels(MobileDevice[] models) {
		MobileDeviceSoap[] soapModels = new MobileDeviceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MobileDeviceSoap[][] toSoapModels(MobileDevice[][] models) {
		MobileDeviceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MobileDeviceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MobileDeviceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MobileDeviceSoap[] toSoapModels(List<MobileDevice> models) {
		List<MobileDeviceSoap> soapModels = new ArrayList<MobileDeviceSoap>(
			models.size());

		for (MobileDevice model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MobileDeviceSoap[soapModels.size()]);
	}

	public MobileDeviceSoap() {
	}

	public long getPrimaryKey() {
		return _mobileDeviceId;
	}

	public void setPrimaryKey(long pk) {
		setMobileDeviceId(pk);
	}

	public long getMobileDeviceId() {
		return _mobileDeviceId;
	}

	public void setMobileDeviceId(long mobileDeviceId) {
		_mobileDeviceId = mobileDeviceId;
	}

	public String getManufaturerDeviceId() {
		return _manufaturerDeviceId;
	}

	public void setManufaturerDeviceId(String manufaturerDeviceId) {
		_manufaturerDeviceId = manufaturerDeviceId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getDeviceModel() {
		return _deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		_deviceModel = deviceModel;
	}

	public String getManufacturer() {
		return _manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		_manufacturer = manufacturer;
	}

	public String getOperatingSystem() {
		return _operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		_operatingSystem = operatingSystem;
	}

	public String getOperatingSystemVersion() {
		return _operatingSystemVersion;
	}

	public void setOperatingSystemVersion(String operatingSystemVersion) {
		_operatingSystemVersion = operatingSystemVersion;
	}

	public String getBrowserUA() {
		return _browserUA;
	}

	public void setBrowserUA(String browserUA) {
		_browserUA = browserUA;
	}

	private long _mobileDeviceId;
	private String _manufaturerDeviceId;
	private long _userId;
	private String _deviceModel;
	private String _manufacturer;
	private String _operatingSystem;
	private String _operatingSystemVersion;
	private String _browserUA;

}