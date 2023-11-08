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

package com.weprode.facile.course.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContentBlock}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContentBlock
 * @generated
 */
public class ContentBlockWrapper
	extends BaseModelWrapper<ContentBlock>
	implements ContentBlock, ModelWrapper<ContentBlock> {

	public ContentBlockWrapper(ContentBlock contentBlock) {
		super(contentBlock);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("blockId", getBlockId());
		attributes.put("courseItemId", getCourseItemId());
		attributes.put("modificationDate", getModificationDate());
		attributes.put("blockName", getBlockName());
		attributes.put("blockValue", getBlockValue());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("blockType", getBlockType());
		attributes.put("order", getOrder());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long blockId = (Long)attributes.get("blockId");

		if (blockId != null) {
			setBlockId(blockId);
		}

		Long courseItemId = (Long)attributes.get("courseItemId");

		if (courseItemId != null) {
			setCourseItemId(courseItemId);
		}

		Date modificationDate = (Date)attributes.get("modificationDate");

		if (modificationDate != null) {
			setModificationDate(modificationDate);
		}

		String blockName = (String)attributes.get("blockName");

		if (blockName != null) {
			setBlockName(blockName);
		}

		String blockValue = (String)attributes.get("blockValue");

		if (blockValue != null) {
			setBlockValue(blockValue);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Integer blockType = (Integer)attributes.get("blockType");

		if (blockType != null) {
			setBlockType(blockType);
		}

		Integer order = (Integer)attributes.get("order");

		if (order != null) {
			setOrder(order);
		}
	}

	@Override
	public ContentBlock cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public org.json.JSONObject convertToJSON() {
		return model.convertToJSON();
	}

	/**
	 * Returns the block ID of this content block.
	 *
	 * @return the block ID of this content block
	 */
	@Override
	public long getBlockId() {
		return model.getBlockId();
	}

	/**
	 * Returns the block name of this content block.
	 *
	 * @return the block name of this content block
	 */
	@Override
	public String getBlockName() {
		return model.getBlockName();
	}

	/**
	 * Returns the block type of this content block.
	 *
	 * @return the block type of this content block
	 */
	@Override
	public int getBlockType() {
		return model.getBlockType();
	}

	/**
	 * Returns the block value of this content block.
	 *
	 * @return the block value of this content block
	 */
	@Override
	public String getBlockValue() {
		return model.getBlockValue();
	}

	/**
	 * Returns the course item ID of this content block.
	 *
	 * @return the course item ID of this content block
	 */
	@Override
	public long getCourseItemId() {
		return model.getCourseItemId();
	}

	/**
	 * Returns the file entry ID of this content block.
	 *
	 * @return the file entry ID of this content block
	 */
	@Override
	public long getFileEntryId() {
		return model.getFileEntryId();
	}

	/**
	 * Returns the modification date of this content block.
	 *
	 * @return the modification date of this content block
	 */
	@Override
	public Date getModificationDate() {
		return model.getModificationDate();
	}

	/**
	 * Returns the order of this content block.
	 *
	 * @return the order of this content block
	 */
	@Override
	public int getOrder() {
		return model.getOrder();
	}

	/**
	 * Returns the primary key of this content block.
	 *
	 * @return the primary key of this content block
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
	 * Sets the block ID of this content block.
	 *
	 * @param blockId the block ID of this content block
	 */
	@Override
	public void setBlockId(long blockId) {
		model.setBlockId(blockId);
	}

	/**
	 * Sets the block name of this content block.
	 *
	 * @param blockName the block name of this content block
	 */
	@Override
	public void setBlockName(String blockName) {
		model.setBlockName(blockName);
	}

	/**
	 * Sets the block type of this content block.
	 *
	 * @param blockType the block type of this content block
	 */
	@Override
	public void setBlockType(int blockType) {
		model.setBlockType(blockType);
	}

	/**
	 * Sets the block value of this content block.
	 *
	 * @param blockValue the block value of this content block
	 */
	@Override
	public void setBlockValue(String blockValue) {
		model.setBlockValue(blockValue);
	}

	/**
	 * Sets the course item ID of this content block.
	 *
	 * @param courseItemId the course item ID of this content block
	 */
	@Override
	public void setCourseItemId(long courseItemId) {
		model.setCourseItemId(courseItemId);
	}

	/**
	 * Sets the file entry ID of this content block.
	 *
	 * @param fileEntryId the file entry ID of this content block
	 */
	@Override
	public void setFileEntryId(long fileEntryId) {
		model.setFileEntryId(fileEntryId);
	}

	/**
	 * Sets the modification date of this content block.
	 *
	 * @param modificationDate the modification date of this content block
	 */
	@Override
	public void setModificationDate(Date modificationDate) {
		model.setModificationDate(modificationDate);
	}

	/**
	 * Sets the order of this content block.
	 *
	 * @param order the order of this content block
	 */
	@Override
	public void setOrder(int order) {
		model.setOrder(order);
	}

	/**
	 * Sets the primary key of this content block.
	 *
	 * @param primaryKey the primary key of this content block
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected ContentBlockWrapper wrap(ContentBlock contentBlock) {
		return new ContentBlockWrapper(contentBlock);
	}

}