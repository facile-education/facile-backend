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

package com.weprode.nero.messaging.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MessageFolder}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageFolder
 * @generated
 */
public class MessageFolderWrapper
	extends BaseModelWrapper<MessageFolder>
	implements MessageFolder, ModelWrapper<MessageFolder> {

	public MessageFolderWrapper(MessageFolder messageFolder) {
		super(messageFolder);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("folderId", getFolderId());
		attributes.put("userId", getUserId());
		attributes.put("folderName", getFolderName());
		attributes.put("type", getType());
		attributes.put("parentFolderId", getParentFolderId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long folderId = (Long)attributes.get("folderId");

		if (folderId != null) {
			setFolderId(folderId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String folderName = (String)attributes.get("folderName");

		if (folderName != null) {
			setFolderName(folderName);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long parentFolderId = (Long)attributes.get("parentFolderId");

		if (parentFolderId != null) {
			setParentFolderId(parentFolderId);
		}
	}

	@Override
	public MessageFolder cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the folder ID of this message folder.
	 *
	 * @return the folder ID of this message folder
	 */
	@Override
	public long getFolderId() {
		return model.getFolderId();
	}

	/**
	 * Returns the folder name of this message folder.
	 *
	 * @return the folder name of this message folder
	 */
	@Override
	public String getFolderName() {
		return model.getFolderName();
	}

	/**
	 * Returns the parent folder ID of this message folder.
	 *
	 * @return the parent folder ID of this message folder
	 */
	@Override
	public long getParentFolderId() {
		return model.getParentFolderId();
	}

	/**
	 * Returns the primary key of this message folder.
	 *
	 * @return the primary key of this message folder
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the type of this message folder.
	 *
	 * @return the type of this message folder
	 */
	@Override
	public int getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this message folder.
	 *
	 * @return the user ID of this message folder
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this message folder.
	 *
	 * @return the user uuid of this message folder
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the folder ID of this message folder.
	 *
	 * @param folderId the folder ID of this message folder
	 */
	@Override
	public void setFolderId(long folderId) {
		model.setFolderId(folderId);
	}

	/**
	 * Sets the folder name of this message folder.
	 *
	 * @param folderName the folder name of this message folder
	 */
	@Override
	public void setFolderName(String folderName) {
		model.setFolderName(folderName);
	}

	/**
	 * Sets the parent folder ID of this message folder.
	 *
	 * @param parentFolderId the parent folder ID of this message folder
	 */
	@Override
	public void setParentFolderId(long parentFolderId) {
		model.setParentFolderId(parentFolderId);
	}

	/**
	 * Sets the primary key of this message folder.
	 *
	 * @param primaryKey the primary key of this message folder
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the type of this message folder.
	 *
	 * @param type the type of this message folder
	 */
	@Override
	public void setType(int type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this message folder.
	 *
	 * @param userId the user ID of this message folder
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this message folder.
	 *
	 * @param userUuid the user uuid of this message folder
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected MessageFolderWrapper wrap(MessageFolder messageFolder) {
		return new MessageFolderWrapper(messageFolder);
	}

}