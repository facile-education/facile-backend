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

package com.weprode.nero.help.model;

import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the HelpRelation service. Represents a row in the &quot;Help_HelpRelation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.help.model.impl.HelpRelationModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.help.model.impl.HelpRelationImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpRelation
 * @generated
 */
@ProviderType
public interface HelpRelationModel extends BaseModel<HelpRelation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a help relation model instance should use the {@link HelpRelation} interface instead.
	 */

	/**
	 * Returns the primary key of this help relation.
	 *
	 * @return the primary key of this help relation
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this help relation.
	 *
	 * @param primaryKey the primary key of this help relation
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the relation ID of this help relation.
	 *
	 * @return the relation ID of this help relation
	 */
	public long getRelationId();

	/**
	 * Sets the relation ID of this help relation.
	 *
	 * @param relationId the relation ID of this help relation
	 */
	public void setRelationId(long relationId);

	/**
	 * Returns the item ID of this help relation.
	 *
	 * @return the item ID of this help relation
	 */
	public long getItemId();

	/**
	 * Sets the item ID of this help relation.
	 *
	 * @param itemId the item ID of this help relation
	 */
	public void setItemId(long itemId);

	/**
	 * Returns the related item ID of this help relation.
	 *
	 * @return the related item ID of this help relation
	 */
	public long getRelatedItemId();

	/**
	 * Sets the related item ID of this help relation.
	 *
	 * @param relatedItemId the related item ID of this help relation
	 */
	public void setRelatedItemId(long relatedItemId);

	@Override
	public HelpRelation cloneWithOriginalValues();

}