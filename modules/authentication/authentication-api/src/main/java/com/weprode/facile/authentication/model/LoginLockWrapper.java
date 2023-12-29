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

package com.weprode.facile.authentication.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LoginLock}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoginLock
 * @generated
 */
public class LoginLockWrapper
	extends BaseModelWrapper<LoginLock>
	implements LoginLock, ModelWrapper<LoginLock> {

	public LoginLockWrapper(LoginLock loginLock) {
		super(loginLock);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("login", getLogin());
		attributes.put("failedLoginAttempts", getFailedLoginAttempts());
		attributes.put("isLocked", isIsLocked());
		attributes.put("lockEndDate", getLockEndDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String login = (String)attributes.get("login");

		if (login != null) {
			setLogin(login);
		}

		Integer failedLoginAttempts = (Integer)attributes.get(
			"failedLoginAttempts");

		if (failedLoginAttempts != null) {
			setFailedLoginAttempts(failedLoginAttempts);
		}

		Boolean isLocked = (Boolean)attributes.get("isLocked");

		if (isLocked != null) {
			setIsLocked(isLocked);
		}

		Date lockEndDate = (Date)attributes.get("lockEndDate");

		if (lockEndDate != null) {
			setLockEndDate(lockEndDate);
		}
	}

	@Override
	public LoginLock cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the failed login attempts of this login lock.
	 *
	 * @return the failed login attempts of this login lock
	 */
	@Override
	public int getFailedLoginAttempts() {
		return model.getFailedLoginAttempts();
	}

	/**
	 * Returns the is locked of this login lock.
	 *
	 * @return the is locked of this login lock
	 */
	@Override
	public boolean getIsLocked() {
		return model.getIsLocked();
	}

	/**
	 * Returns the lock end date of this login lock.
	 *
	 * @return the lock end date of this login lock
	 */
	@Override
	public Date getLockEndDate() {
		return model.getLockEndDate();
	}

	/**
	 * Returns the login of this login lock.
	 *
	 * @return the login of this login lock
	 */
	@Override
	public String getLogin() {
		return model.getLogin();
	}

	/**
	 * Returns the primary key of this login lock.
	 *
	 * @return the primary key of this login lock
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns <code>true</code> if this login lock is is locked.
	 *
	 * @return <code>true</code> if this login lock is is locked; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsLocked() {
		return model.isIsLocked();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the failed login attempts of this login lock.
	 *
	 * @param failedLoginAttempts the failed login attempts of this login lock
	 */
	@Override
	public void setFailedLoginAttempts(int failedLoginAttempts) {
		model.setFailedLoginAttempts(failedLoginAttempts);
	}

	/**
	 * Sets whether this login lock is is locked.
	 *
	 * @param isLocked the is locked of this login lock
	 */
	@Override
	public void setIsLocked(boolean isLocked) {
		model.setIsLocked(isLocked);
	}

	/**
	 * Sets the lock end date of this login lock.
	 *
	 * @param lockEndDate the lock end date of this login lock
	 */
	@Override
	public void setLockEndDate(Date lockEndDate) {
		model.setLockEndDate(lockEndDate);
	}

	/**
	 * Sets the login of this login lock.
	 *
	 * @param login the login of this login lock
	 */
	@Override
	public void setLogin(String login) {
		model.setLogin(login);
	}

	/**
	 * Sets the primary key of this login lock.
	 *
	 * @param primaryKey the primary key of this login lock
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected LoginLockWrapper wrap(LoginLock loginLock) {
		return new LoginLockWrapper(loginLock);
	}

}