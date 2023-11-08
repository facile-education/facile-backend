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

package com.weprode.facile.application.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DefaultRole}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DefaultRole
 * @generated
 */
public class DefaultRoleWrapper
	extends BaseModelWrapper<DefaultRole>
	implements DefaultRole, ModelWrapper<DefaultRole> {

	public DefaultRoleWrapper(DefaultRole defaultRole) {
		super(defaultRole);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("defaultRoleId", getDefaultRoleId());
		attributes.put("roleId", getRoleId());
		attributes.put("applicationId", getApplicationId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long defaultRoleId = (Long)attributes.get("defaultRoleId");

		if (defaultRoleId != null) {
			setDefaultRoleId(defaultRoleId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}

		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}
	}

	@Override
	public DefaultRole cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the application ID of this default role.
	 *
	 * @return the application ID of this default role
	 */
	@Override
	public long getApplicationId() {
		return model.getApplicationId();
	}

	/**
	 * Returns the default role ID of this default role.
	 *
	 * @return the default role ID of this default role
	 */
	@Override
	public long getDefaultRoleId() {
		return model.getDefaultRoleId();
	}

	/**
	 * Returns the primary key of this default role.
	 *
	 * @return the primary key of this default role
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this default role.
	 *
	 * @return the role ID of this default role
	 */
	@Override
	public long getRoleId() {
		return model.getRoleId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the application ID of this default role.
	 *
	 * @param applicationId the application ID of this default role
	 */
	@Override
	public void setApplicationId(long applicationId) {
		model.setApplicationId(applicationId);
	}

	/**
	 * Sets the default role ID of this default role.
	 *
	 * @param defaultRoleId the default role ID of this default role
	 */
	@Override
	public void setDefaultRoleId(long defaultRoleId) {
		model.setDefaultRoleId(defaultRoleId);
	}

	/**
	 * Sets the primary key of this default role.
	 *
	 * @param primaryKey the primary key of this default role
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this default role.
	 *
	 * @param roleId the role ID of this default role
	 */
	@Override
	public void setRoleId(long roleId) {
		model.setRoleId(roleId);
	}

	@Override
	protected DefaultRoleWrapper wrap(DefaultRole defaultRole) {
		return new DefaultRoleWrapper(defaultRole);
	}

}