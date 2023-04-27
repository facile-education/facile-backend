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
 * This class is a wrapper for {@link ProgressionItem}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionItem
 * @generated
 */
public class ProgressionItemWrapper
	extends BaseModelWrapper<ProgressionItem>
	implements ModelWrapper<ProgressionItem>, ProgressionItem {

	public ProgressionItemWrapper(ProgressionItem progressionItem) {
		super(progressionItem);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("progressionItemId", getProgressionItemId());
		attributes.put("progressionId", getProgressionId());
		attributes.put("sessionId", getSessionId());
		attributes.put("homeworkId", getHomeworkId());
		attributes.put("progressionFolderId", getProgressionFolderId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("itemName", getItemName());
		attributes.put("isHomework", isIsHomework());
		attributes.put("duration", getDuration());
		attributes.put("type", getType());
		attributes.put("order", getOrder());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long progressionItemId = (Long)attributes.get("progressionItemId");

		if (progressionItemId != null) {
			setProgressionItemId(progressionItemId);
		}

		Long progressionId = (Long)attributes.get("progressionId");

		if (progressionId != null) {
			setProgressionId(progressionId);
		}

		Long sessionId = (Long)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Long homeworkId = (Long)attributes.get("homeworkId");

		if (homeworkId != null) {
			setHomeworkId(homeworkId);
		}

		Long progressionFolderId = (Long)attributes.get("progressionFolderId");

		if (progressionFolderId != null) {
			setProgressionFolderId(progressionFolderId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		Boolean isHomework = (Boolean)attributes.get("isHomework");

		if (isHomework != null) {
			setIsHomework(isHomework);
		}

		String duration = (String)attributes.get("duration");

		if (duration != null) {
			setDuration(duration);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer order = (Integer)attributes.get("order");

		if (order != null) {
			setOrder(order);
		}
	}

	@Override
	public ProgressionItem cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject convertToJSON(
		long userId) {

		return model.convertToJSON(userId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject convertToJSON(
		long userId, boolean isContentIncluded) {

		return model.convertToJSON(userId, isContentIncluded);
	}

	/**
	 * Returns the duration of this progression item.
	 *
	 * @return the duration of this progression item
	 */
	@Override
	public String getDuration() {
		return model.getDuration();
	}

	/**
	 * Returns the homework ID of this progression item.
	 *
	 * @return the homework ID of this progression item
	 */
	@Override
	public long getHomeworkId() {
		return model.getHomeworkId();
	}

	/**
	 * Returns the is homework of this progression item.
	 *
	 * @return the is homework of this progression item
	 */
	@Override
	public boolean getIsHomework() {
		return model.getIsHomework();
	}

	/**
	 * Returns the item name of this progression item.
	 *
	 * @return the item name of this progression item
	 */
	@Override
	public String getItemName() {
		return model.getItemName();
	}

	/**
	 * Returns the modified date of this progression item.
	 *
	 * @return the modified date of this progression item
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the order of this progression item.
	 *
	 * @return the order of this progression item
	 */
	@Override
	public int getOrder() {
		return model.getOrder();
	}

	/**
	 * Returns the primary key of this progression item.
	 *
	 * @return the primary key of this progression item
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the progression folder ID of this progression item.
	 *
	 * @return the progression folder ID of this progression item
	 */
	@Override
	public long getProgressionFolderId() {
		return model.getProgressionFolderId();
	}

	/**
	 * Returns the progression ID of this progression item.
	 *
	 * @return the progression ID of this progression item
	 */
	@Override
	public long getProgressionId() {
		return model.getProgressionId();
	}

	/**
	 * Returns the progression item ID of this progression item.
	 *
	 * @return the progression item ID of this progression item
	 */
	@Override
	public long getProgressionItemId() {
		return model.getProgressionItemId();
	}

	/**
	 * Returns the session ID of this progression item.
	 *
	 * @return the session ID of this progression item
	 */
	@Override
	public long getSessionId() {
		return model.getSessionId();
	}

	/**
	 * Returns the type of this progression item.
	 *
	 * @return the type of this progression item
	 */
	@Override
	public int getType() {
		return model.getType();
	}

	/**
	 * Returns <code>true</code> if this progression item is is homework.
	 *
	 * @return <code>true</code> if this progression item is is homework; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsHomework() {
		return model.isIsHomework();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the duration of this progression item.
	 *
	 * @param duration the duration of this progression item
	 */
	@Override
	public void setDuration(String duration) {
		model.setDuration(duration);
	}

	/**
	 * Sets the homework ID of this progression item.
	 *
	 * @param homeworkId the homework ID of this progression item
	 */
	@Override
	public void setHomeworkId(long homeworkId) {
		model.setHomeworkId(homeworkId);
	}

	/**
	 * Sets whether this progression item is is homework.
	 *
	 * @param isHomework the is homework of this progression item
	 */
	@Override
	public void setIsHomework(boolean isHomework) {
		model.setIsHomework(isHomework);
	}

	/**
	 * Sets the item name of this progression item.
	 *
	 * @param itemName the item name of this progression item
	 */
	@Override
	public void setItemName(String itemName) {
		model.setItemName(itemName);
	}

	/**
	 * Sets the modified date of this progression item.
	 *
	 * @param modifiedDate the modified date of this progression item
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the order of this progression item.
	 *
	 * @param order the order of this progression item
	 */
	@Override
	public void setOrder(int order) {
		model.setOrder(order);
	}

	/**
	 * Sets the primary key of this progression item.
	 *
	 * @param primaryKey the primary key of this progression item
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the progression folder ID of this progression item.
	 *
	 * @param progressionFolderId the progression folder ID of this progression item
	 */
	@Override
	public void setProgressionFolderId(long progressionFolderId) {
		model.setProgressionFolderId(progressionFolderId);
	}

	/**
	 * Sets the progression ID of this progression item.
	 *
	 * @param progressionId the progression ID of this progression item
	 */
	@Override
	public void setProgressionId(long progressionId) {
		model.setProgressionId(progressionId);
	}

	/**
	 * Sets the progression item ID of this progression item.
	 *
	 * @param progressionItemId the progression item ID of this progression item
	 */
	@Override
	public void setProgressionItemId(long progressionItemId) {
		model.setProgressionItemId(progressionItemId);
	}

	/**
	 * Sets the session ID of this progression item.
	 *
	 * @param sessionId the session ID of this progression item
	 */
	@Override
	public void setSessionId(long sessionId) {
		model.setSessionId(sessionId);
	}

	/**
	 * Sets the type of this progression item.
	 *
	 * @param type the type of this progression item
	 */
	@Override
	public void setType(int type) {
		model.setType(type);
	}

	@Override
	protected ProgressionItemWrapper wrap(ProgressionItem progressionItem) {
		return new ProgressionItemWrapper(progressionItem);
	}

}