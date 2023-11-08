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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Access service. Represents a row in the &quot;Access_Access&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.access.model.impl.AccessModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.access.model.impl.AccessImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Access
 * @generated
 */
@ProviderType
public interface AccessModel extends BaseModel<Access> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a access model instance should use the {@link Access} interface instead.
	 */

	/**
	 * Returns the primary key of this access.
	 *
	 * @return the primary key of this access
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this access.
	 *
	 * @param primaryKey the primary key of this access
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this access.
	 *
	 * @return the uuid of this access
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this access.
	 *
	 * @param uuid the uuid of this access
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the access ID of this access.
	 *
	 * @return the access ID of this access
	 */
	public long getAccessId();

	/**
	 * Sets the access ID of this access.
	 *
	 * @param accessId the access ID of this access
	 */
	public void setAccessId(long accessId);

	/**
	 * Returns the category ID of this access.
	 *
	 * @return the category ID of this access
	 */
	public long getCategoryId();

	/**
	 * Sets the category ID of this access.
	 *
	 * @param categoryId the category ID of this access
	 */
	public void setCategoryId(long categoryId);

	/**
	 * Returns the title of this access.
	 *
	 * @return the title of this access
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this access.
	 *
	 * @param title the title of this access
	 */
	public void setTitle(String title);

	/**
	 * Returns the type of this access.
	 *
	 * @return the type of this access
	 */
	public int getType();

	/**
	 * Sets the type of this access.
	 *
	 * @param type the type of this access
	 */
	public void setType(int type);

	/**
	 * Returns the external url of this access.
	 *
	 * @return the external url of this access
	 */
	@AutoEscape
	public String getExternalUrl();

	/**
	 * Sets the external url of this access.
	 *
	 * @param externalUrl the external url of this access
	 */
	public void setExternalUrl(String externalUrl);

	/**
	 * Returns the folder ID of this access.
	 *
	 * @return the folder ID of this access
	 */
	public long getFolderId();

	/**
	 * Sets the folder ID of this access.
	 *
	 * @param folderId the folder ID of this access
	 */
	public void setFolderId(long folderId);

	/**
	 * Returns the file ID of this access.
	 *
	 * @return the file ID of this access
	 */
	public long getFileId();

	/**
	 * Sets the file ID of this access.
	 *
	 * @param fileId the file ID of this access
	 */
	public void setFileId(long fileId);

	/**
	 * Returns the thumbnail ID of this access.
	 *
	 * @return the thumbnail ID of this access
	 */
	public long getThumbnailId();

	/**
	 * Sets the thumbnail ID of this access.
	 *
	 * @param thumbnailId the thumbnail ID of this access
	 */
	public void setThumbnailId(long thumbnailId);

	/**
	 * Returns the position of this access.
	 *
	 * @return the position of this access
	 */
	public int getPosition();

	/**
	 * Sets the position of this access.
	 *
	 * @param position the position of this access
	 */
	public void setPosition(int position);

	@Override
	public Access cloneWithOriginalValues();

}