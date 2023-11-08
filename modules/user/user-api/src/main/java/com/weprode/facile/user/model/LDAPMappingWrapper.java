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

		attributes.put("UserId", getUserId());
		attributes.put("EntPersonJointure", getEntPersonJointure());
		attributes.put("UID", getUID());
		attributes.put("INE", getINE());
		attributes.put("EntEleveStructRattachId", getEntEleveStructRattachId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long UserId = (Long)attributes.get("UserId");

		if (UserId != null) {
			setUserId(UserId);
		}

		String EntPersonJointure = (String)attributes.get("EntPersonJointure");

		if (EntPersonJointure != null) {
			setEntPersonJointure(EntPersonJointure);
		}

		String UID = (String)attributes.get("UID");

		if (UID != null) {
			setUID(UID);
		}

		String INE = (String)attributes.get("INE");

		if (INE != null) {
			setINE(INE);
		}

		String EntEleveStructRattachId = (String)attributes.get(
			"EntEleveStructRattachId");

		if (EntEleveStructRattachId != null) {
			setEntEleveStructRattachId(EntEleveStructRattachId);
		}
	}

	@Override
	public LDAPMapping cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the ent eleve struct rattach ID of this ldap mapping.
	 *
	 * @return the ent eleve struct rattach ID of this ldap mapping
	 */
	@Override
	public String getEntEleveStructRattachId() {
		return model.getEntEleveStructRattachId();
	}

	/**
	 * Returns the ent person jointure of this ldap mapping.
	 *
	 * @return the ent person jointure of this ldap mapping
	 */
	@Override
	public String getEntPersonJointure() {
		return model.getEntPersonJointure();
	}

	/**
	 * Returns the ine of this ldap mapping.
	 *
	 * @return the ine of this ldap mapping
	 */
	@Override
	public String getINE() {
		return model.getINE();
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
	 * Sets the ent eleve struct rattach ID of this ldap mapping.
	 *
	 * @param EntEleveStructRattachId the ent eleve struct rattach ID of this ldap mapping
	 */
	@Override
	public void setEntEleveStructRattachId(String EntEleveStructRattachId) {
		model.setEntEleveStructRattachId(EntEleveStructRattachId);
	}

	/**
	 * Sets the ent person jointure of this ldap mapping.
	 *
	 * @param EntPersonJointure the ent person jointure of this ldap mapping
	 */
	@Override
	public void setEntPersonJointure(String EntPersonJointure) {
		model.setEntPersonJointure(EntPersonJointure);
	}

	/**
	 * Sets the ine of this ldap mapping.
	 *
	 * @param INE the ine of this ldap mapping
	 */
	@Override
	public void setINE(String INE) {
		model.setINE(INE);
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
	 * @param UserId the user ID of this ldap mapping
	 */
	@Override
	public void setUserId(long UserId) {
		model.setUserId(UserId);
	}

	/**
	 * Sets the user uuid of this ldap mapping.
	 *
	 * @param UserUuid the user uuid of this ldap mapping
	 */
	@Override
	public void setUserUuid(String UserUuid) {
		model.setUserUuid(UserUuid);
	}

	@Override
	protected LDAPMappingWrapper wrap(LDAPMapping ldapMapping) {
		return new LDAPMappingWrapper(ldapMapping);
	}

}