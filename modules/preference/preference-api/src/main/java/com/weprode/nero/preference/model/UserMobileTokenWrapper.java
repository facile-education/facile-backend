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
 * This class is a wrapper for {@link UserMobileToken}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserMobileToken
 * @generated
 */
public class UserMobileTokenWrapper
	extends BaseModelWrapper<UserMobileToken>
	implements ModelWrapper<UserMobileToken>, UserMobileToken {

	public UserMobileTokenWrapper(UserMobileToken userMobileToken) {
		super(userMobileToken);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userMobileTokenId", getUserMobileTokenId());
		attributes.put("userId", getUserId());
		attributes.put("mobileToken", getMobileToken());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userMobileTokenId = (Long)attributes.get("userMobileTokenId");

		if (userMobileTokenId != null) {
			setUserMobileTokenId(userMobileTokenId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String mobileToken = (String)attributes.get("mobileToken");

		if (mobileToken != null) {
			setMobileToken(mobileToken);
		}
	}

	@Override
	public UserMobileToken cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the mobile token of this user mobile token.
	 *
	 * @return the mobile token of this user mobile token
	 */
	@Override
	public String getMobileToken() {
		return model.getMobileToken();
	}

	/**
	 * Returns the primary key of this user mobile token.
	 *
	 * @return the primary key of this user mobile token
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this user mobile token.
	 *
	 * @return the user ID of this user mobile token
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user mobile token ID of this user mobile token.
	 *
	 * @return the user mobile token ID of this user mobile token
	 */
	@Override
	public long getUserMobileTokenId() {
		return model.getUserMobileTokenId();
	}

	/**
	 * Returns the user uuid of this user mobile token.
	 *
	 * @return the user uuid of this user mobile token
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
	 * Sets the mobile token of this user mobile token.
	 *
	 * @param mobileToken the mobile token of this user mobile token
	 */
	@Override
	public void setMobileToken(String mobileToken) {
		model.setMobileToken(mobileToken);
	}

	/**
	 * Sets the primary key of this user mobile token.
	 *
	 * @param primaryKey the primary key of this user mobile token
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this user mobile token.
	 *
	 * @param userId the user ID of this user mobile token
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user mobile token ID of this user mobile token.
	 *
	 * @param userMobileTokenId the user mobile token ID of this user mobile token
	 */
	@Override
	public void setUserMobileTokenId(long userMobileTokenId) {
		model.setUserMobileTokenId(userMobileTokenId);
	}

	/**
	 * Sets the user uuid of this user mobile token.
	 *
	 * @param userUuid the user uuid of this user mobile token
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected UserMobileTokenWrapper wrap(UserMobileToken userMobileToken) {
		return new UserMobileTokenWrapper(userMobileToken);
	}

}