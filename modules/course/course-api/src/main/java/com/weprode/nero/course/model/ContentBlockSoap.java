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

package com.weprode.nero.course.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.weprode.nero.course.service.http.ContentBlockServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ContentBlockSoap implements Serializable {

	public static ContentBlockSoap toSoapModel(ContentBlock model) {
		ContentBlockSoap soapModel = new ContentBlockSoap();

		soapModel.setBlockId(model.getBlockId());
		soapModel.setCourseItemId(model.getCourseItemId());
		soapModel.setModificationDate(model.getModificationDate());
		soapModel.setBlockName(model.getBlockName());
		soapModel.setBlockValue(model.getBlockValue());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setBlockType(model.getBlockType());
		soapModel.setOrder(model.getOrder());

		return soapModel;
	}

	public static ContentBlockSoap[] toSoapModels(ContentBlock[] models) {
		ContentBlockSoap[] soapModels = new ContentBlockSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContentBlockSoap[][] toSoapModels(ContentBlock[][] models) {
		ContentBlockSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContentBlockSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContentBlockSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContentBlockSoap[] toSoapModels(List<ContentBlock> models) {
		List<ContentBlockSoap> soapModels = new ArrayList<ContentBlockSoap>(
			models.size());

		for (ContentBlock model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContentBlockSoap[soapModels.size()]);
	}

	public ContentBlockSoap() {
	}

	public long getPrimaryKey() {
		return _blockId;
	}

	public void setPrimaryKey(long pk) {
		setBlockId(pk);
	}

	public long getBlockId() {
		return _blockId;
	}

	public void setBlockId(long blockId) {
		_blockId = blockId;
	}

	public long getCourseItemId() {
		return _courseItemId;
	}

	public void setCourseItemId(long courseItemId) {
		_courseItemId = courseItemId;
	}

	public Date getModificationDate() {
		return _modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		_modificationDate = modificationDate;
	}

	public String getBlockName() {
		return _blockName;
	}

	public void setBlockName(String blockName) {
		_blockName = blockName;
	}

	public String getBlockValue() {
		return _blockValue;
	}

	public void setBlockValue(String blockValue) {
		_blockValue = blockValue;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public int getBlockType() {
		return _blockType;
	}

	public void setBlockType(int blockType) {
		_blockType = blockType;
	}

	public int getOrder() {
		return _order;
	}

	public void setOrder(int order) {
		_order = order;
	}

	private long _blockId;
	private long _courseItemId;
	private Date _modificationDate;
	private String _blockName;
	private String _blockValue;
	private long _fileEntryId;
	private int _blockType;
	private int _order;

}