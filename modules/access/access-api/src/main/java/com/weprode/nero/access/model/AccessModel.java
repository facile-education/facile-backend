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

package com.weprode.nero.access.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Access service. Represents a row in the &quot;Access_Access&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.access.model.impl.AccessModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.access.model.impl.AccessImpl</code>.
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
	 * Returns the url of this access.
	 *
	 * @return the url of this access
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this access.
	 *
	 * @param url the url of this access
	 */
	public void setUrl(String url);

	/**
	 * Returns the thumbnail of this access.
	 *
	 * @return the thumbnail of this access
	 */
	@AutoEscape
	public String getThumbnail();

	/**
	 * Sets the thumbnail of this access.
	 *
	 * @param thumbnail the thumbnail of this access
	 */
	public void setThumbnail(String thumbnail);

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