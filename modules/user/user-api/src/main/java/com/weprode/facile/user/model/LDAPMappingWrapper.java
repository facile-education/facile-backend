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
 * This class is a wrapper for {@link LDAPMapping}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LDAPMapping
 * @generated
 */
public class LDAPMappingWrapper
	extends BaseModelWrapper<LDAPMapping>
	implements LDAPMapping, ModelWrapper<LDAPMapping> {

	public LDAPMappingWrapper(LDAPMapping ldapMapping) {
		super(ldapMapping);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("UID", getUID());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String UID = (String)attributes.get("UID");

		if (UID != null) {
			setUID(UID);
		}
	}

	@Override
	public LDAPMapping cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the primary key of this ldap mapping.
	 *
	 * @return the primary key of this ldap mapping
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the uid of this ldap mapping.
	 *
	 * @return the uid of this ldap mapping
	 */
	@Override
	public String getUID() {
		return model.getUID();
	}

	/**
	 * Returns the user ID of this ldap mapping.
	 *
	 * @return the user ID of this ldap mapping
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this ldap mapping.
	 *
	 * @return the user uuid of this ldap mapping
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
	 * Sets the primary key of this ldap mapping.
	 *
	 * @param primaryKey the primary key of this ldap mapping
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the uid of this ldap mapping.
	 *
	 * @param UID the uid of this ldap mapping
	 */
	@Override
	public void setUID(String UID) {
		model.setUID(UID);
	}

	/**
	 * Sets the user ID of this ldap mapping.
	 *
	 * @param userId the user ID of this ldap mapping
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this ldap mapping.
	 *
	 * @param userUuid the user uuid of this ldap mapping
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected LDAPMappingWrapper wrap(LDAPMapping ldapMapping) {
		return new LDAPMappingWrapper(ldapMapping);
	}

}