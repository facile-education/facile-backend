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

package com.weprode.nero.about.model;

import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the EntVersionUser service. Represents a row in the &quot;About_EntVersionUser&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.about.model.impl.EntVersionUserModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.about.model.impl.EntVersionUserImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntVersionUser
 * @generated
 */
@ProviderType
public interface EntVersionUserModel extends BaseModel<EntVersionUser> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ent version user model instance should use the {@link EntVersionUser} interface instead.
	 */

	/**
	 * Returns the primary key of this ent version user.
	 *
	 * @return the primary key of this ent version user
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ent version user.
	 *
	 * @param primaryKey the primary key of this ent version user
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the version user ID of this ent version user.
	 *
	 * @return the version user ID of this ent version user
	 */
	public long getVersionUserId();

	/**
	 * Sets the version user ID of this ent version user.
	 *
	 * @param versionUserId the version user ID of this ent version user
	 */
	public void setVersionUserId(long versionUserId);

	/**
	 * Returns the version user uuid of this ent version user.
	 *
	 * @return the version user uuid of this ent version user
	 */
	public String getVersionUserUuid();

	/**
	 * Sets the version user uuid of this ent version user.
	 *
	 * @param versionUserUuid the version user uuid of this ent version user
	 */
	public void setVersionUserUuid(String versionUserUuid);

	/**
	 * Returns the ent version ID of this ent version user.
	 *
	 * @return the ent version ID of this ent version user
	 */
	public long getEntVersionId();

	/**
	 * Sets the ent version ID of this ent version user.
	 *
	 * @param entVersionId the ent version ID of this ent version user
	 */
	public void setEntVersionId(long entVersionId);

	/**
	 * Returns the user ID of this ent version user.
	 *
	 * @return the user ID of this ent version user
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this ent version user.
	 *
	 * @param userId the user ID of this ent version user
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this ent version user.
	 *
	 * @return the user uuid of this ent version user
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this ent version user.
	 *
	 * @param userUuid the user uuid of this ent version user
	 */
	public void setUserUuid(String userUuid);

	@Override
	public EntVersionUser cloneWithOriginalValues();

}