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
 * This class is a wrapper for {@link HelpItem}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpItem
 * @generated
 */
public class HelpItemWrapper
	extends BaseModelWrapper<HelpItem>
	implements HelpItem, ModelWrapper<HelpItem> {

	public HelpItemWrapper(HelpItem helpItem) {
		super(helpItem);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemId", getItemId());
		attributes.put("categoryId", getCategoryId());
		attributes.put("itemName", getItemName());
		attributes.put("videoURL", getVideoURL());
		attributes.put("videoDescription", getVideoDescription());
		attributes.put("manual", getManual());
		attributes.put("position", getPosition());
		attributes.put("language", getLanguage());
		attributes.put("isManagement", isIsManagement());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long itemId = (Long)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Long categoryId = (Long)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		String videoURL = (String)attributes.get("videoURL");

		if (videoURL != null) {
			setVideoURL(videoURL);
		}

		String videoDescription = (String)attributes.get("videoDescription");

		if (videoDescription != null) {
			setVideoDescription(videoDescription);
		}

		String manual = (String)attributes.get("manual");

		if (manual != null) {
			setManual(manual);
		}

		Integer position = (Integer)attributes.get("position");

		if (position != null) {
			setPosition(position);
		}

		String language = (String)attributes.get("language");

		if (language != null) {
			setLanguage(language);
		}

		Boolean isManagement = (Boolean)attributes.get("isManagement");

		if (isManagement != null) {
			setIsManagement(isManagement);
		}
	}

	@Override
	public HelpItem cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the category ID of this help item.
	 *
	 * @return the category ID of this help item
	 */
	@Override
	public long getCategoryId() {
		return model.getCategoryId();
	}

	/**
	 * Returns the is management of this help item.
	 *
	 * @return the is management of this help item
	 */
	@Override
	public boolean getIsManagement() {
		return model.getIsManagement();
	}

	/**
	 * Returns the item ID of this help item.
	 *
	 * @return the item ID of this help item
	 */
	@Override
	public long getItemId() {
		return model.getItemId();
	}

	/**
	 * Returns the item name of this help item.
	 *
	 * @return the item name of this help item
	 */
	@Override
	public String getItemName() {
		return model.getItemName();
	}

	/**
	 * Returns the language of this help item.
	 *
	 * @return the language of this help item
	 */
	@Override
	public String getLanguage() {
		return model.getLanguage();
	}

	/**
	 * Returns the manual of this help item.
	 *
	 * @return the manual of this help item
	 */
	@Override
	public String getManual() {
		return model.getManual();
	}

	/**
	 * Returns the position of this help item.
	 *
	 * @return the position of this help item
	 */
	@Override
	public int getPosition() {
		return model.getPosition();
	}

	/**
	 * Returns the primary key of this help item.
	 *
	 * @return the primary key of this help item
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the video description of this help item.
	 *
	 * @return the video description of this help item
	 */
	@Override
	public String getVideoDescription() {
		return model.getVideoDescription();
	}

	/**
	 * Returns the video url of this help item.
	 *
	 * @return the video url of this help item
	 */
	@Override
	public String getVideoURL() {
		return model.getVideoURL();
	}

	/**
	 * Returns <code>true</code> if this help item is is management.
	 *
	 * @return <code>true</code> if this help item is is management; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsManagement() {
		return model.isIsManagement();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the category ID of this help item.
	 *
	 * @param categoryId the category ID of this help item
	 */
	@Override
	public void setCategoryId(long categoryId) {
		model.setCategoryId(categoryId);
	}

	/**
	 * Sets whether this help item is is management.
	 *
	 * @param isManagement the is management of this help item
	 */
	@Override
	public void setIsManagement(boolean isManagement) {
		model.setIsManagement(isManagement);
	}

	/**
	 * Sets the item ID of this help item.
	 *
	 * @param itemId the item ID of this help item
	 */
	@Override
	public void setItemId(long itemId) {
		model.setItemId(itemId);
	}

	/**
	 * Sets the item name of this help item.
	 *
	 * @param itemName the item name of this help item
	 */
	@Override
	public void setItemName(String itemName) {
		model.setItemName(itemName);
	}

	/**
	 * Sets the language of this help item.
	 *
	 * @param language the language of this help item
	 */
	@Override
	public void setLanguage(String language) {
		model.setLanguage(language);
	}

	/**
	 * Sets the manual of this help item.
	 *
	 * @param manual the manual of this help item
	 */
	@Override
	public void setManual(String manual) {
		model.setManual(manual);
	}

	/**
	 * Sets the position of this help item.
	 *
	 * @param position the position of this help item
	 */
	@Override
	public void setPosition(int position) {
		model.setPosition(position);
	}

	/**
	 * Sets the primary key of this help item.
	 *
	 * @param primaryKey the primary key of this help item
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the video description of this help item.
	 *
	 * @param videoDescription the video description of this help item
	 */
	@Override
	public void setVideoDescription(String videoDescription) {
		model.setVideoDescription(videoDescription);
	}

	/**
	 * Sets the video url of this help item.
	 *
	 * @param videoURL the video url of this help item
	 */
	@Override
	public void setVideoURL(String videoURL) {
		model.setVideoURL(videoURL);
	}

	@Override
	protected HelpItemWrapper wrap(HelpItem helpItem) {
		return new HelpItemWrapper(helpItem);
	}

}