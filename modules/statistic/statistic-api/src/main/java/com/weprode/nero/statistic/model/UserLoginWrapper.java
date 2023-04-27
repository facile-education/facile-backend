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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserLogin}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLogin
 * @generated
 */
public class UserLoginWrapper
	extends BaseModelWrapper<UserLogin>
	implements ModelWrapper<UserLogin>, UserLogin {

	public UserLoginWrapper(UserLogin userLogin) {
		super(userLogin);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userLoginId", getUserLoginId());
		attributes.put("userId", getUserId());
		attributes.put("loginDate", getLoginDate());
		attributes.put("role", getRole());
		attributes.put("schoolId", getSchoolId());
		attributes.put("isMobileApp", isIsMobileApp());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userLoginId = (Long)attributes.get("userLoginId");

		if (userLoginId != null) {
			setUserLoginId(userLoginId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date loginDate = (Date)attributes.get("loginDate");

		if (loginDate != null) {
			setLoginDate(loginDate);
		}

		Integer role = (Integer)attributes.get("role");

		if (role != null) {
			setRole(role);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		Boolean isMobileApp = (Boolean)attributes.get("isMobileApp");

		if (isMobileApp != null) {
			setIsMobileApp(isMobileApp);
		}
	}

	@Override
	public UserLogin cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the is mobile app of this user login.
	 *
	 * @return the is mobile app of this user login
	 */
	@Override
	public boolean getIsMobileApp() {
		return model.getIsMobileApp();
	}

	/**
	 * Returns the login date of this user login.
	 *
	 * @return the login date of this user login
	 */
	@Override
	public Date getLoginDate() {
		return model.getLoginDate();
	}

	/**
	 * Returns the primary key of this user login.
	 *
	 * @return the primary key of this user login
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the role of this user login.
	 *
	 * @return the role of this user login
	 */
	@Override
	public int getRole() {
		return model.getRole();
	}

	/**
	 * Returns the school ID of this user login.
	 *
	 * @return the school ID of this user login
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the user ID of this user login.
	 *
	 * @return the user ID of this user login
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user login ID of this user login.
	 *
	 * @return the user login ID of this user login
	 */
	@Override
	public long getUserLoginId() {
		return model.getUserLoginId();
	}

	/**
	 * Returns the user uuid of this user login.
	 *
	 * @return the user uuid of this user login
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this user login is is mobile app.
	 *
	 * @return <code>true</code> if this user login is is mobile app; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsMobileApp() {
		return model.isIsMobileApp();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this user login is is mobile app.
	 *
	 * @param isMobileApp the is mobile app of this user login
	 */
	@Override
	public void setIsMobileApp(boolean isMobileApp) {
		model.setIsMobileApp(isMobileApp);
	}

	/**
	 * Sets the login date of this user login.
	 *
	 * @param loginDate the login date of this user login
	 */
	@Override
	public void setLoginDate(Date loginDate) {
		model.setLoginDate(loginDate);
	}

	/**
	 * Sets the primary key of this user login.
	 *
	 * @param primaryKey the primary key of this user login
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role of this user login.
	 *
	 * @param role the role of this user login
	 */
	@Override
	public void setRole(int role) {
		model.setRole(role);
	}

	/**
	 * Sets the school ID of this user login.
	 *
	 * @param schoolId the school ID of this user login
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the user ID of this user login.
	 *
	 * @param userId the user ID of this user login
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user login ID of this user login.
	 *
	 * @param userLoginId the user login ID of this user login
	 */
	@Override
	public void setUserLoginId(long userLoginId) {
		model.setUserLoginId(userLoginId);
	}

	/**
	 * Sets the user uuid of this user login.
	 *
	 * @param userUuid the user uuid of this user login
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected UserLoginWrapper wrap(UserLogin userLogin) {
		return new UserLoginWrapper(userLogin);
	}

}