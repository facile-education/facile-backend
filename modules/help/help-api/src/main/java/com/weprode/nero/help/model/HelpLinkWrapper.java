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
 * This class is a wrapper for {@link HelpLink}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpLink
 * @generated
 */
public class HelpLinkWrapper
	extends BaseModelWrapper<HelpLink>
	implements HelpLink, ModelWrapper<HelpLink> {

	public HelpLinkWrapper(HelpLink helpLink) {
		super(helpLink);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("linkId", getLinkId());
		attributes.put("itemId", getItemId());
		attributes.put("linkName", getLinkName());
		attributes.put("linkUrl", getLinkUrl());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long linkId = (Long)attributes.get("linkId");

		if (linkId != null) {
			setLinkId(linkId);
		}

		Long itemId = (Long)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		String linkName = (String)attributes.get("linkName");

		if (linkName != null) {
			setLinkName(linkName);
		}

		String linkUrl = (String)attributes.get("linkUrl");

		if (linkUrl != null) {
			setLinkUrl(linkUrl);
		}
	}

	@Override
	public HelpLink cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the item ID of this help link.
	 *
	 * @return the item ID of this help link
	 */
	@Override
	public long getItemId() {
		return model.getItemId();
	}

	/**
	 * Returns the link ID of this help link.
	 *
	 * @return the link ID of this help link
	 */
	@Override
	public long getLinkId() {
		return model.getLinkId();
	}

	/**
	 * Returns the link name of this help link.
	 *
	 * @return the link name of this help link
	 */
	@Override
	public String getLinkName() {
		return model.getLinkName();
	}

	/**
	 * Returns the link url of this help link.
	 *
	 * @return the link url of this help link
	 */
	@Override
	public String getLinkUrl() {
		return model.getLinkUrl();
	}

	/**
	 * Returns the primary key of this help link.
	 *
	 * @return the primary key of this help link
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the item ID of this help link.
	 *
	 * @param itemId the item ID of this help link
	 */
	@Override
	public void setItemId(long itemId) {
		model.setItemId(itemId);
	}

	/**
	 * Sets the link ID of this help link.
	 *
	 * @param linkId the link ID of this help link
	 */
	@Override
	public void setLinkId(long linkId) {
		model.setLinkId(linkId);
	}

	/**
	 * Sets the link name of this help link.
	 *
	 * @param linkName the link name of this help link
	 */
	@Override
	public void setLinkName(String linkName) {
		model.setLinkName(linkName);
	}

	/**
	 * Sets the link url of this help link.
	 *
	 * @param linkUrl the link url of this help link
	 */
	@Override
	public void setLinkUrl(String linkUrl) {
		model.setLinkUrl(linkUrl);
	}

	/**
	 * Sets the primary key of this help link.
	 *
	 * @param primaryKey the primary key of this help link
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected HelpLinkWrapper wrap(HelpLink helpLink) {
		return new HelpLinkWrapper(helpLink);
	}

}