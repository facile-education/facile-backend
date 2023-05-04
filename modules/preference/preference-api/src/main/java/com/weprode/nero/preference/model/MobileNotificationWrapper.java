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
 * This class is a wrapper for {@link MobileNotification}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MobileNotification
 * @generated
 */
public class MobileNotificationWrapper
	extends BaseModelWrapper<MobileNotification>
	implements MobileNotification, ModelWrapper<MobileNotification> {

	public MobileNotificationWrapper(MobileNotification mobileNotification) {
		super(mobileNotification);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mobileNotificationId", getMobileNotificationId());
		attributes.put("userId", getUserId());
		attributes.put("etabId", getEtabId());
		attributes.put("enable", isEnable());
		attributes.put("token", getToken());
		attributes.put("device", getDevice());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mobileNotificationId = (Long)attributes.get(
			"mobileNotificationId");

		if (mobileNotificationId != null) {
			setMobileNotificationId(mobileNotificationId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long etabId = (Long)attributes.get("etabId");

		if (etabId != null) {
			setEtabId(etabId);
		}

		Boolean enable = (Boolean)attributes.get("enable");

		if (enable != null) {
			setEnable(enable);
		}

		String token = (String)attributes.get("token");

		if (token != null) {
			setToken(token);
		}

		String device = (String)attributes.get("device");

		if (device != null) {
			setDevice(device);
		}
	}

	@Override
	public MobileNotification cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the device of this mobile notification.
	 *
	 * @return the device of this mobile notification
	 */
	@Override
	public String getDevice() {
		return model.getDevice();
	}

	/**
	 * Returns the enable of this mobile notification.
	 *
	 * @return the enable of this mobile notification
	 */
	@Override
	public boolean getEnable() {
		return model.getEnable();
	}

	/**
	 * Returns the etab ID of this mobile notification.
	 *
	 * @return the etab ID of this mobile notification
	 */
	@Override
	public long getEtabId() {
		return model.getEtabId();
	}

	/**
	 * Returns the mobile notification ID of this mobile notification.
	 *
	 * @return the mobile notification ID of this mobile notification
	 */
	@Override
	public long getMobileNotificationId() {
		return model.getMobileNotificationId();
	}

	/**
	 * Returns the primary key of this mobile notification.
	 *
	 * @return the primary key of this mobile notification
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the token of this mobile notification.
	 *
	 * @return the token of this mobile notification
	 */
	@Override
	public String getToken() {
		return model.getToken();
	}

	/**
	 * Returns the user ID of this mobile notification.
	 *
	 * @return the user ID of this mobile notification
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this mobile notification.
	 *
	 * @return the user uuid of this mobile notification
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this mobile notification is enable.
	 *
	 * @return <code>true</code> if this mobile notification is enable; <code>false</code> otherwise
	 */
	@Override
	public boolean isEnable() {
		return model.isEnable();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the device of this mobile notification.
	 *
	 * @param device the device of this mobile notification
	 */
	@Override
	public void setDevice(String device) {
		model.setDevice(device);
	}

	/**
	 * Sets whether this mobile notification is enable.
	 *
	 * @param enable the enable of this mobile notification
	 */
	@Override
	public void setEnable(boolean enable) {
		model.setEnable(enable);
	}

	/**
	 * Sets the etab ID of this mobile notification.
	 *
	 * @param etabId the etab ID of this mobile notification
	 */
	@Override
	public void setEtabId(long etabId) {
		model.setEtabId(etabId);
	}

	/**
	 * Sets the mobile notification ID of this mobile notification.
	 *
	 * @param mobileNotificationId the mobile notification ID of this mobile notification
	 */
	@Override
	public void setMobileNotificationId(long mobileNotificationId) {
		model.setMobileNotificationId(mobileNotificationId);
	}

	/**
	 * Sets the primary key of this mobile notification.
	 *
	 * @param primaryKey the primary key of this mobile notification
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the token of this mobile notification.
	 *
	 * @param token the token of this mobile notification
	 */
	@Override
	public void setToken(String token) {
		model.setToken(token);
	}

	/**
	 * Sets the user ID of this mobile notification.
	 *
	 * @param userId the user ID of this mobile notification
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this mobile notification.
	 *
	 * @param userUuid the user uuid of this mobile notification
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected MobileNotificationWrapper wrap(
		MobileNotification mobileNotification) {

		return new MobileNotificationWrapper(mobileNotification);
	}

}