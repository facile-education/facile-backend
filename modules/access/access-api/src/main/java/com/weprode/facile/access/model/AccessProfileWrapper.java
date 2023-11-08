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

package com.weprode.facile.access.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AccessProfile}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccessProfile
 * @generated
 */
public class AccessProfileWrapper
	extends BaseModelWrapper<AccessProfile>
	implements AccessProfile, ModelWrapper<AccessProfile> {

	public AccessProfileWrapper(AccessProfile accessProfile) {
		super(accessProfile);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("accessId", getAccessId());
		attributes.put("roleId", getRoleId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long accessId = (Long)attributes.get("accessId");

		if (accessId != null) {
			setAccessId(accessId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}
	}

	@Override
	public AccessProfile cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the access ID of this access profile.
	 *
	 * @return the access ID of this access profile
	 */
	@Override
	public long getAccessId() {
		return model.getAccessId();
	}

	/**
	 * Returns the primary key of this access profile.
	 *
	 * @return the primary key of this access profile
	 */
	@Override
	public com.weprode.facile.access.service.persistence.AccessProfilePK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the role ID of this access profile.
	 *
	 * @return the role ID of this access profile
	 */
	@Override
	public long getRoleId() {
		return model.getRoleId();
	}

	/**
	 * Returns the uuid of this access profile.
	 *
	 * @return the uuid of this access profile
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the access ID of this access profile.
	 *
	 * @param accessId the access ID of this access profile
	 */
	@Override
	public void setAccessId(long accessId) {
		model.setAccessId(accessId);
	}

	/**
	 * Sets the primary key of this access profile.
	 *
	 * @param primaryKey the primary key of this access profile
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.facile.access.service.persistence.AccessProfilePK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the role ID of this access profile.
	 *
	 * @param roleId the role ID of this access profile
	 */
	@Override
	public void setRoleId(long roleId) {
		model.setRoleId(roleId);
	}

	/**
	 * Sets the uuid of this access profile.
	 *
	 * @param uuid the uuid of this access profile
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected AccessProfileWrapper wrap(AccessProfile accessProfile) {
		return new AccessProfileWrapper(accessProfile);
	}

}