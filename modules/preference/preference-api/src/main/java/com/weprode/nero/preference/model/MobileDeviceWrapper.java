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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MobileDevice}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MobileDevice
 * @generated
 */
public class MobileDeviceWrapper
	extends BaseModelWrapper<MobileDevice>
	implements MobileDevice, ModelWrapper<MobileDevice> {

	public MobileDeviceWrapper(MobileDevice mobileDevice) {
		super(mobileDevice);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mobileDeviceId", getMobileDeviceId());
		attributes.put("manufaturerDeviceId", getManufaturerDeviceId());
		attributes.put("userId", getUserId());
		attributes.put("deviceModel", getDeviceModel());
		attributes.put("manufacturer", getManufacturer());
		attributes.put("operatingSystem", getOperatingSystem());
		attributes.put("operatingSystemVersion", getOperatingSystemVersion());
		attributes.put("browserUA", getBrowserUA());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mobileDeviceId = (Long)attributes.get("mobileDeviceId");

		if (mobileDeviceId != null) {
			setMobileDeviceId(mobileDeviceId);
		}

		String manufaturerDeviceId = (String)attributes.get(
			"manufaturerDeviceId");

		if (manufaturerDeviceId != null) {
			setManufaturerDeviceId(manufaturerDeviceId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String deviceModel = (String)attributes.get("deviceModel");

		if (deviceModel != null) {
			setDeviceModel(deviceModel);
		}

		String manufacturer = (String)attributes.get("manufacturer");

		if (manufacturer != null) {
			setManufacturer(manufacturer);
		}

		String operatingSystem = (String)attributes.get("operatingSystem");

		if (operatingSystem != null) {
			setOperatingSystem(operatingSystem);
		}

		String operatingSystemVersion = (String)attributes.get(
			"operatingSystemVersion");

		if (operatingSystemVersion != null) {
			setOperatingSystemVersion(operatingSystemVersion);
		}

		String browserUA = (String)attributes.get("browserUA");

		if (browserUA != null) {
			setBrowserUA(browserUA);
		}
	}

	@Override
	public MobileDevice cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the browser ua of this mobile device.
	 *
	 * @return the browser ua of this mobile device
	 */
	@Override
	public String getBrowserUA() {
		return model.getBrowserUA();
	}

	/**
	 * Returns the device model of this mobile device.
	 *
	 * @return the device model of this mobile device
	 */
	@Override
	public String getDeviceModel() {
		return model.getDeviceModel();
	}

	/**
	 * Returns the manufacturer of this mobile device.
	 *
	 * @return the manufacturer of this mobile device
	 */
	@Override
	public String getManufacturer() {
		return model.getManufacturer();
	}

	/**
	 * Returns the manufaturer device ID of this mobile device.
	 *
	 * @return the manufaturer device ID of this mobile device
	 */
	@Override
	public String getManufaturerDeviceId() {
		return model.getManufaturerDeviceId();
	}

	/**
	 * Returns the mobile device ID of this mobile device.
	 *
	 * @return the mobile device ID of this mobile device
	 */
	@Override
	public long getMobileDeviceId() {
		return model.getMobileDeviceId();
	}

	/**
	 * Returns the operating system of this mobile device.
	 *
	 * @return the operating system of this mobile device
	 */
	@Override
	public String getOperatingSystem() {
		return model.getOperatingSystem();
	}

	/**
	 * Returns the operating system version of this mobile device.
	 *
	 * @return the operating system version of this mobile device
	 */
	@Override
	public String getOperatingSystemVersion() {
		return model.getOperatingSystemVersion();
	}

	/**
	 * Returns the primary key of this mobile device.
	 *
	 * @return the primary key of this mobile device
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this mobile device.
	 *
	 * @return the user ID of this mobile device
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this mobile device.
	 *
	 * @return the user uuid of this mobile device
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the browser ua of this mobile device.
	 *
	 * @param browserUA the browser ua of this mobile device
	 */
	@Override
	public void setBrowserUA(String browserUA) {
		model.setBrowserUA(browserUA);
	}

	/**
	 * Sets the device model of this mobile device.
	 *
	 * @param deviceModel the device model of this mobile device
	 */
	@Override
	public void setDeviceModel(String deviceModel) {
		model.setDeviceModel(deviceModel);
	}

	/**
	 * Sets the manufacturer of this mobile device.
	 *
	 * @param manufacturer the manufacturer of this mobile device
	 */
	@Override
	public void setManufacturer(String manufacturer) {
		model.setManufacturer(manufacturer);
	}

	/**
	 * Sets the manufaturer device ID of this mobile device.
	 *
	 * @param manufaturerDeviceId the manufaturer device ID of this mobile device
	 */
	@Override
	public void setManufaturerDeviceId(String manufaturerDeviceId) {
		model.setManufaturerDeviceId(manufaturerDeviceId);
	}

	/**
	 * Sets the mobile device ID of this mobile device.
	 *
	 * @param mobileDeviceId the mobile device ID of this mobile device
	 */
	@Override
	public void setMobileDeviceId(long mobileDeviceId) {
		model.setMobileDeviceId(mobileDeviceId);
	}

	/**
	 * Sets the operating system of this mobile device.
	 *
	 * @param operatingSystem the operating system of this mobile device
	 */
	@Override
	public void setOperatingSystem(String operatingSystem) {
		model.setOperatingSystem(operatingSystem);
	}

	/**
	 * Sets the operating system version of this mobile device.
	 *
	 * @param operatingSystemVersion the operating system version of this mobile device
	 */
	@Override
	public void setOperatingSystemVersion(String operatingSystemVersion) {
		model.setOperatingSystemVersion(operatingSystemVersion);
	}

	/**
	 * Sets the primary key of this mobile device.
	 *
	 * @param primaryKey the primary key of this mobile device
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this mobile device.
	 *
	 * @param userId the user ID of this mobile device
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this mobile device.
	 *
	 * @param userUuid the user uuid of this mobile device
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected MobileDeviceWrapper wrap(MobileDevice mobileDevice) {
		return new MobileDeviceWrapper(mobileDevice);
	}

}