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

package com.weprode.facile.user.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserContact}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserContact
 * @generated
 */
public class UserContactWrapper
	extends BaseModelWrapper<UserContact>
	implements ModelWrapper<UserContact>, UserContact {

	public UserContactWrapper(UserContact userContact) {
		super(userContact);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("address", getAddress());
		attributes.put("mobilePhone", getMobilePhone());
		attributes.put("homePhone", getHomePhone());
		attributes.put("proPhone", getProPhone());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		String mobilePhone = (String)attributes.get("mobilePhone");

		if (mobilePhone != null) {
			setMobilePhone(mobilePhone);
		}

		String homePhone = (String)attributes.get("homePhone");

		if (homePhone != null) {
			setHomePhone(homePhone);
		}

		String proPhone = (String)attributes.get("proPhone");

		if (proPhone != null) {
			setProPhone(proPhone);
		}
	}

	@Override
	public UserContact cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the address of this user contact.
	 *
	 * @return the address of this user contact
	 */
	@Override
	public String getAddress() {
		return model.getAddress();
	}

	/**
	 * Returns the home phone of this user contact.
	 *
	 * @return the home phone of this user contact
	 */
	@Override
	public String getHomePhone() {
		return model.getHomePhone();
	}

	/**
	 * Returns the mobile phone of this user contact.
	 *
	 * @return the mobile phone of this user contact
	 */
	@Override
	public String getMobilePhone() {
		return model.getMobilePhone();
	}

	/**
	 * Returns the primary key of this user contact.
	 *
	 * @return the primary key of this user contact
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the pro phone of this user contact.
	 *
	 * @return the pro phone of this user contact
	 */
	@Override
	public String getProPhone() {
		return model.getProPhone();
	}

	/**
	 * Returns the user ID of this user contact.
	 *
	 * @return the user ID of this user contact
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this user contact.
	 *
	 * @return the user uuid of this user contact
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
	 * Sets the address of this user contact.
	 *
	 * @param address the address of this user contact
	 */
	@Override
	public void setAddress(String address) {
		model.setAddress(address);
	}

	/**
	 * Sets the home phone of this user contact.
	 *
	 * @param homePhone the home phone of this user contact
	 */
	@Override
	public void setHomePhone(String homePhone) {
		model.setHomePhone(homePhone);
	}

	/**
	 * Sets the mobile phone of this user contact.
	 *
	 * @param mobilePhone the mobile phone of this user contact
	 */
	@Override
	public void setMobilePhone(String mobilePhone) {
		model.setMobilePhone(mobilePhone);
	}

	/**
	 * Sets the primary key of this user contact.
	 *
	 * @param primaryKey the primary key of this user contact
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the pro phone of this user contact.
	 *
	 * @param proPhone the pro phone of this user contact
	 */
	@Override
	public void setProPhone(String proPhone) {
		model.setProPhone(proPhone);
	}

	/**
	 * Sets the user ID of this user contact.
	 *
	 * @param userId the user ID of this user contact
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this user contact.
	 *
	 * @param userUuid the user uuid of this user contact
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected UserContactWrapper wrap(UserContact userContact) {
		return new UserContactWrapper(userContact);
	}

}