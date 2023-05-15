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

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DSLQueryEntry service. Represents a row in the &quot;DSLQueryEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.tools.service.builder.test.model.impl.DSLQueryEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.tools.service.builder.test.model.impl.DSLQueryEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DSLQueryEntry
 * @generated
 */
@ProviderType
public interface DSLQueryEntryModel extends BaseModel<DSLQueryEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a dsl query entry model instance should use the {@link DSLQueryEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this dsl query entry.
	 *
	 * @return the primary key of this dsl query entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this dsl query entry.
	 *
	 * @param primaryKey the primary key of this dsl query entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the dsl query entry ID of this dsl query entry.
	 *
	 * @return the dsl query entry ID of this dsl query entry
	 */
	public long getDslQueryEntryId();

	/**
	 * Sets the dsl query entry ID of this dsl query entry.
	 *
	 * @param dslQueryEntryId the dsl query entry ID of this dsl query entry
	 */
	public void setDslQueryEntryId(long dslQueryEntryId);

	/**
	 * Returns the name of this dsl query entry.
	 *
	 * @return the name of this dsl query entry
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this dsl query entry.
	 *
	 * @param name the name of this dsl query entry
	 */
	public void setName(String name);

	@Override
	public DSLQueryEntry cloneWithOriginalValues();

}