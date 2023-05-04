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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the EntVersion service. Represents a row in the &quot;About_EntVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.about.model.impl.EntVersionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.about.model.impl.EntVersionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntVersion
 * @generated
 */
@ProviderType
public interface EntVersionModel extends BaseModel<EntVersion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ent version model instance should use the {@link EntVersion} interface instead.
	 */

	/**
	 * Returns the primary key of this ent version.
	 *
	 * @return the primary key of this ent version
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ent version.
	 *
	 * @param primaryKey the primary key of this ent version
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the ent version ID of this ent version.
	 *
	 * @return the ent version ID of this ent version
	 */
	public long getEntVersionId();

	/**
	 * Sets the ent version ID of this ent version.
	 *
	 * @param entVersionId the ent version ID of this ent version
	 */
	public void setEntVersionId(long entVersionId);

	/**
	 * Returns the version of this ent version.
	 *
	 * @return the version of this ent version
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this ent version.
	 *
	 * @param version the version of this ent version
	 */
	public void setVersion(String version);

	/**
	 * Returns the details of this ent version.
	 *
	 * @return the details of this ent version
	 */
	@AutoEscape
	public String getDetails();

	/**
	 * Sets the details of this ent version.
	 *
	 * @param details the details of this ent version
	 */
	public void setDetails(String details);

	/**
	 * Returns the version date of this ent version.
	 *
	 * @return the version date of this ent version
	 */
	public Date getVersionDate();

	/**
	 * Sets the version date of this ent version.
	 *
	 * @param versionDate the version date of this ent version
	 */
	public void setVersionDate(Date versionDate);

	/**
	 * Returns the is last of this ent version.
	 *
	 * @return the is last of this ent version
	 */
	public boolean getIsLast();

	/**
	 * Returns <code>true</code> if this ent version is is last.
	 *
	 * @return <code>true</code> if this ent version is is last; <code>false</code> otherwise
	 */
	public boolean isIsLast();

	/**
	 * Sets whether this ent version is is last.
	 *
	 * @param isLast the is last of this ent version
	 */
	public void setIsLast(boolean isLast);

	@Override
	public EntVersion cloneWithOriginalValues();

}