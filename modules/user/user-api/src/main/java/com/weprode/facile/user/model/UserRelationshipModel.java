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

import com.liferay.portal.kernel.model.BaseModel;

import com.weprode.facile.user.service.persistence.UserRelationshipPK;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the UserRelationship service. Represents a row in the &quot;User_UserRelationship&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.user.model.impl.UserRelationshipModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.user.model.impl.UserRelationshipImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserRelationship
 * @generated
 */
@ProviderType
public interface UserRelationshipModel extends BaseModel<UserRelationship> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a user relationship model instance should use the {@link UserRelationship} interface instead.
	 */

	/**
	 * Returns the primary key of this user relationship.
	 *
	 * @return the primary key of this user relationship
	 */
	public UserRelationshipPK getPrimaryKey();

	/**
	 * Sets the primary key of this user relationship.
	 *
	 * @param primaryKey the primary key of this user relationship
	 */
	public void setPrimaryKey(UserRelationshipPK primaryKey);

	/**
	 * Returns the child user ID of this user relationship.
	 *
	 * @return the child user ID of this user relationship
	 */
	public long getChildUserId();

	/**
	 * Sets the child user ID of this user relationship.
	 *
	 * @param childUserId the child user ID of this user relationship
	 */
	public void setChildUserId(long childUserId);

	/**
	 * Returns the child user uuid of this user relationship.
	 *
	 * @return the child user uuid of this user relationship
	 */
	public String getChildUserUuid();

	/**
	 * Sets the child user uuid of this user relationship.
	 *
	 * @param childUserUuid the child user uuid of this user relationship
	 */
	public void setChildUserUuid(String childUserUuid);

	/**
	 * Returns the parent user ID of this user relationship.
	 *
	 * @return the parent user ID of this user relationship
	 */
	public long getParentUserId();

	/**
	 * Sets the parent user ID of this user relationship.
	 *
	 * @param parentUserId the parent user ID of this user relationship
	 */
	public void setParentUserId(long parentUserId);

	/**
	 * Returns the parent user uuid of this user relationship.
	 *
	 * @return the parent user uuid of this user relationship
	 */
	public String getParentUserUuid();

	/**
	 * Sets the parent user uuid of this user relationship.
	 *
	 * @param parentUserUuid the parent user uuid of this user relationship
	 */
	public void setParentUserUuid(String parentUserUuid);

	@Override
	public UserRelationship cloneWithOriginalValues();

}