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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HelpRelation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpRelation
 * @generated
 */
public class HelpRelationWrapper
	extends BaseModelWrapper<HelpRelation>
	implements HelpRelation, ModelWrapper<HelpRelation> {

	public HelpRelationWrapper(HelpRelation helpRelation) {
		super(helpRelation);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("relationId", getRelationId());
		attributes.put("itemId", getItemId());
		attributes.put("relatedItemId", getRelatedItemId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long relationId = (Long)attributes.get("relationId");

		if (relationId != null) {
			setRelationId(relationId);
		}

		Long itemId = (Long)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Long relatedItemId = (Long)attributes.get("relatedItemId");

		if (relatedItemId != null) {
			setRelatedItemId(relatedItemId);
		}
	}

	@Override
	public HelpRelation cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the item ID of this help relation.
	 *
	 * @return the item ID of this help relation
	 */
	@Override
	public long getItemId() {
		return model.getItemId();
	}

	/**
	 * Returns the primary key of this help relation.
	 *
	 * @return the primary key of this help relation
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the related item ID of this help relation.
	 *
	 * @return the related item ID of this help relation
	 */
	@Override
	public long getRelatedItemId() {
		return model.getRelatedItemId();
	}

	/**
	 * Returns the relation ID of this help relation.
	 *
	 * @return the relation ID of this help relation
	 */
	@Override
	public long getRelationId() {
		return model.getRelationId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the item ID of this help relation.
	 *
	 * @param itemId the item ID of this help relation
	 */
	@Override
	public void setItemId(long itemId) {
		model.setItemId(itemId);
	}

	/**
	 * Sets the primary key of this help relation.
	 *
	 * @param primaryKey the primary key of this help relation
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the related item ID of this help relation.
	 *
	 * @param relatedItemId the related item ID of this help relation
	 */
	@Override
	public void setRelatedItemId(long relatedItemId) {
		model.setRelatedItemId(relatedItemId);
	}

	/**
	 * Sets the relation ID of this help relation.
	 *
	 * @param relationId the relation ID of this help relation
	 */
	@Override
	public void setRelationId(long relationId) {
		model.setRelationId(relationId);
	}

	@Override
	protected HelpRelationWrapper wrap(HelpRelation helpRelation) {
		return new HelpRelationWrapper(helpRelation);
	}

}