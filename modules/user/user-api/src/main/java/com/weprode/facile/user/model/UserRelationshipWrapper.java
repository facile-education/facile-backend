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
 * This class is a wrapper for {@link UserRelationship}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserRelationship
 * @generated
 */
public class UserRelationshipWrapper
	extends BaseModelWrapper<UserRelationship>
	implements ModelWrapper<UserRelationship>, UserRelationship {

	public UserRelationshipWrapper(UserRelationship userRelationship) {
		super(userRelationship);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("childUserId", getChildUserId());
		attributes.put("parentUserId", getParentUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long childUserId = (Long)attributes.get("childUserId");

		if (childUserId != null) {
			setChildUserId(childUserId);
		}

		Long parentUserId = (Long)attributes.get("parentUserId");

		if (parentUserId != null) {
			setParentUserId(parentUserId);
		}
	}

	@Override
	public UserRelationship cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the child user ID of this user relationship.
	 *
	 * @return the child user ID of this user relationship
	 */
	@Override
	public long getChildUserId() {
		return model.getChildUserId();
	}

	/**
	 * Returns the child user uuid of this user relationship.
	 *
	 * @return the child user uuid of this user relationship
	 */
	@Override
	public String getChildUserUuid() {
		return model.getChildUserUuid();
	}

	/**
	 * Returns the parent user ID of this user relationship.
	 *
	 * @return the parent user ID of this user relationship
	 */
	@Override
	public long getParentUserId() {
		return model.getParentUserId();
	}

	/**
	 * Returns the parent user uuid of this user relationship.
	 *
	 * @return the parent user uuid of this user relationship
	 */
	@Override
	public String getParentUserUuid() {
		return model.getParentUserUuid();
	}

	/**
	 * Returns the primary key of this user relationship.
	 *
	 * @return the primary key of this user relationship
	 */
	@Override
	public com.weprode.facile.user.service.persistence.UserRelationshipPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the child user ID of this user relationship.
	 *
	 * @param childUserId the child user ID of this user relationship
	 */
	@Override
	public void setChildUserId(long childUserId) {
		model.setChildUserId(childUserId);
	}

	/**
	 * Sets the child user uuid of this user relationship.
	 *
	 * @param childUserUuid the child user uuid of this user relationship
	 */
	@Override
	public void setChildUserUuid(String childUserUuid) {
		model.setChildUserUuid(childUserUuid);
	}

	/**
	 * Sets the parent user ID of this user relationship.
	 *
	 * @param parentUserId the parent user ID of this user relationship
	 */
	@Override
	public void setParentUserId(long parentUserId) {
		model.setParentUserId(parentUserId);
	}

	/**
	 * Sets the parent user uuid of this user relationship.
	 *
	 * @param parentUserUuid the parent user uuid of this user relationship
	 */
	@Override
	public void setParentUserUuid(String parentUserUuid) {
		model.setParentUserUuid(parentUserUuid);
	}

	/**
	 * Sets the primary key of this user relationship.
	 *
	 * @param primaryKey the primary key of this user relationship
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.facile.user.service.persistence.UserRelationshipPK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected UserRelationshipWrapper wrap(UserRelationship userRelationship) {
		return new UserRelationshipWrapper(userRelationship);
	}

}