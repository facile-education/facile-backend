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

package com.weprode.nero.progression.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ItemContent}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ItemContent
 * @generated
 */
public class ItemContentWrapper
	extends BaseModelWrapper<ItemContent>
	implements ItemContent, ModelWrapper<ItemContent> {

	public ItemContentWrapper(ItemContent itemContent) {
		super(itemContent);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contentId", getContentId());
		attributes.put("progressionItemId", getProgressionItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("contentName", getContentName());
		attributes.put("contentValue", getContentValue());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("contentType", getContentType());
		attributes.put("order", getOrder());
		attributes.put("isToBeCompleted", isIsToBeCompleted());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long contentId = (Long)attributes.get("contentId");

		if (contentId != null) {
			setContentId(contentId);
		}

		Long progressionItemId = (Long)attributes.get("progressionItemId");

		if (progressionItemId != null) {
			setProgressionItemId(progressionItemId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String contentName = (String)attributes.get("contentName");

		if (contentName != null) {
			setContentName(contentName);
		}

		String contentValue = (String)attributes.get("contentValue");

		if (contentValue != null) {
			setContentValue(contentValue);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Integer contentType = (Integer)attributes.get("contentType");

		if (contentType != null) {
			setContentType(contentType);
		}

		Integer order = (Integer)attributes.get("order");

		if (order != null) {
			setOrder(order);
		}

		Boolean isToBeCompleted = (Boolean)attributes.get("isToBeCompleted");

		if (isToBeCompleted != null) {
			setIsToBeCompleted(isToBeCompleted);
		}
	}

	@Override
	public ItemContent cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public org.json.JSONObject convertToJSON(boolean isContentIncluded) {
		return model.convertToJSON(isContentIncluded);
	}

	/**
	 * Returns the content ID of this item content.
	 *
	 * @return the content ID of this item content
	 */
	@Override
	public long getContentId() {
		return model.getContentId();
	}

	/**
	 * Returns the content name of this item content.
	 *
	 * @return the content name of this item content
	 */
	@Override
	public String getContentName() {
		return model.getContentName();
	}

	/**
	 * Returns the content type of this item content.
	 *
	 * @return the content type of this item content
	 */
	@Override
	public int getContentType() {
		return model.getContentType();
	}

	/**
	 * Returns the content value of this item content.
	 *
	 * @return the content value of this item content
	 */
	@Override
	public String getContentValue() {
		return model.getContentValue();
	}

	/**
	 * Returns the file entry ID of this item content.
	 *
	 * @return the file entry ID of this item content
	 */
	@Override
	public long getFileEntryId() {
		return model.getFileEntryId();
	}

	/**
	 * Returns the is to be completed of this item content.
	 *
	 * @return the is to be completed of this item content
	 */
	@Override
	public boolean getIsToBeCompleted() {
		return model.getIsToBeCompleted();
	}

	/**
	 * Returns the modified date of this item content.
	 *
	 * @return the modified date of this item content
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the order of this item content.
	 *
	 * @return the order of this item content
	 */
	@Override
	public int getOrder() {
		return model.getOrder();
	}

	/**
	 * Returns the primary key of this item content.
	 *
	 * @return the primary key of this item content
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the progression item ID of this item content.
	 *
	 * @return the progression item ID of this item content
	 */
	@Override
	public long getProgressionItemId() {
		return model.getProgressionItemId();
	}

	/**
	 * Returns <code>true</code> if this item content is is to be completed.
	 *
	 * @return <code>true</code> if this item content is is to be completed; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsToBeCompleted() {
		return model.isIsToBeCompleted();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the content ID of this item content.
	 *
	 * @param contentId the content ID of this item content
	 */
	@Override
	public void setContentId(long contentId) {
		model.setContentId(contentId);
	}

	/**
	 * Sets the content name of this item content.
	 *
	 * @param contentName the content name of this item content
	 */
	@Override
	public void setContentName(String contentName) {
		model.setContentName(contentName);
	}

	/**
	 * Sets the content type of this item content.
	 *
	 * @param contentType the content type of this item content
	 */
	@Override
	public void setContentType(int contentType) {
		model.setContentType(contentType);
	}

	/**
	 * Sets the content value of this item content.
	 *
	 * @param contentValue the content value of this item content
	 */
	@Override
	public void setContentValue(String contentValue) {
		model.setContentValue(contentValue);
	}

	/**
	 * Sets the file entry ID of this item content.
	 *
	 * @param fileEntryId the file entry ID of this item content
	 */
	@Override
	public void setFileEntryId(long fileEntryId) {
		model.setFileEntryId(fileEntryId);
	}

	/**
	 * Sets whether this item content is is to be completed.
	 *
	 * @param isToBeCompleted the is to be completed of this item content
	 */
	@Override
	public void setIsToBeCompleted(boolean isToBeCompleted) {
		model.setIsToBeCompleted(isToBeCompleted);
	}

	/**
	 * Sets the modified date of this item content.
	 *
	 * @param modifiedDate the modified date of this item content
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the order of this item content.
	 *
	 * @param order the order of this item content
	 */
	@Override
	public void setOrder(int order) {
		model.setOrder(order);
	}

	/**
	 * Sets the primary key of this item content.
	 *
	 * @param primaryKey the primary key of this item content
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the progression item ID of this item content.
	 *
	 * @param progressionItemId the progression item ID of this item content
	 */
	@Override
	public void setProgressionItemId(long progressionItemId) {
		model.setProgressionItemId(progressionItemId);
	}

	@Override
	protected ItemContentWrapper wrap(ItemContent itemContent) {
		return new ItemContentWrapper(itemContent);
	}

}