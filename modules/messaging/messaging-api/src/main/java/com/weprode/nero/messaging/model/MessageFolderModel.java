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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the MessageFolder service. Represents a row in the &quot;Messaging_MessageFolder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.messaging.model.impl.MessageFolderModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.messaging.model.impl.MessageFolderImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageFolder
 * @generated
 */
@ProviderType
public interface MessageFolderModel extends BaseModel<MessageFolder> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a message folder model instance should use the {@link MessageFolder} interface instead.
	 */

	/**
	 * Returns the primary key of this message folder.
	 *
	 * @return the primary key of this message folder
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this message folder.
	 *
	 * @param primaryKey the primary key of this message folder
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the folder ID of this message folder.
	 *
	 * @return the folder ID of this message folder
	 */
	public long getFolderId();

	/**
	 * Sets the folder ID of this message folder.
	 *
	 * @param folderId the folder ID of this message folder
	 */
	public void setFolderId(long folderId);

	/**
	 * Returns the user ID of this message folder.
	 *
	 * @return the user ID of this message folder
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this message folder.
	 *
	 * @param userId the user ID of this message folder
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this message folder.
	 *
	 * @return the user uuid of this message folder
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this message folder.
	 *
	 * @param userUuid the user uuid of this message folder
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the folder name of this message folder.
	 *
	 * @return the folder name of this message folder
	 */
	@AutoEscape
	public String getFolderName();

	/**
	 * Sets the folder name of this message folder.
	 *
	 * @param folderName the folder name of this message folder
	 */
	public void setFolderName(String folderName);

	/**
	 * Returns the type of this message folder.
	 *
	 * @return the type of this message folder
	 */
	public int getType();

	/**
	 * Sets the type of this message folder.
	 *
	 * @param type the type of this message folder
	 */
	public void setType(int type);

	/**
	 * Returns the parent folder ID of this message folder.
	 *
	 * @return the parent folder ID of this message folder
	 */
	public long getParentFolderId();

	/**
	 * Sets the parent folder ID of this message folder.
	 *
	 * @param parentFolderId the parent folder ID of this message folder
	 */
	public void setParentFolderId(long parentFolderId);

	@Override
	public MessageFolder cloneWithOriginalValues();

}