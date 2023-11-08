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
 * The base model interface for the AccessCategory service. Represents a row in the &quot;Access_AccessCategory&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.access.model.impl.AccessCategoryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.access.model.impl.AccessCategoryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccessCategory
 * @generated
 */
@ProviderType
public interface AccessCategoryModel extends BaseModel<AccessCategory> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a access category model instance should use the {@link AccessCategory} interface instead.
	 */

	/**
	 * Returns the primary key of this access category.
	 *
	 * @return the primary key of this access category
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this access category.
	 *
	 * @param primaryKey the primary key of this access category
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this access category.
	 *
	 * @return the uuid of this access category
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this access category.
	 *
	 * @param uuid the uuid of this access category
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the category ID of this access category.
	 *
	 * @return the category ID of this access category
	 */
	public long getCategoryId();

	/**
	 * Sets the category ID of this access category.
	 *
	 * @param categoryId the category ID of this access category
	 */
	public void setCategoryId(long categoryId);

	/**
	 * Returns the school ID of this access category.
	 *
	 * @return the school ID of this access category
	 */
	public long getSchoolId();

	/**
	 * Sets the school ID of this access category.
	 *
	 * @param schoolId the school ID of this access category
	 */
	public void setSchoolId(long schoolId);

	/**
	 * Returns the category name of this access category.
	 *
	 * @return the category name of this access category
	 */
	@AutoEscape
	public String getCategoryName();

	/**
	 * Sets the category name of this access category.
	 *
	 * @param categoryName the category name of this access category
	 */
	public void setCategoryName(String categoryName);

	/**
	 * Returns the position of this access category.
	 *
	 * @return the position of this access category
	 */
	public int getPosition();

	/**
	 * Sets the position of this access category.
	 *
	 * @param position the position of this access category
	 */
	public void setPosition(int position);

	@Override
	public AccessCategory cloneWithOriginalValues();

}