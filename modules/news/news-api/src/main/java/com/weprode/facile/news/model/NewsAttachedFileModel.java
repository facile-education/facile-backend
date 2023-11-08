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

package com.weprode.facile.news.model;

import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the NewsAttachedFile service. Represents a row in the &quot;News_NewsAttachedFile&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.news.model.impl.NewsAttachedFileModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.news.model.impl.NewsAttachedFileImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsAttachedFile
 * @generated
 */
@ProviderType
public interface NewsAttachedFileModel extends BaseModel<NewsAttachedFile> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a news attached file model instance should use the {@link NewsAttachedFile} interface instead.
	 */

	/**
	 * Returns the primary key of this news attached file.
	 *
	 * @return the primary key of this news attached file
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this news attached file.
	 *
	 * @param primaryKey the primary key of this news attached file
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the news file ID of this news attached file.
	 *
	 * @return the news file ID of this news attached file
	 */
	public long getNewsFileId();

	/**
	 * Sets the news file ID of this news attached file.
	 *
	 * @param newsFileId the news file ID of this news attached file
	 */
	public void setNewsFileId(long newsFileId);

	/**
	 * Returns the news ID of this news attached file.
	 *
	 * @return the news ID of this news attached file
	 */
	public long getNewsId();

	/**
	 * Sets the news ID of this news attached file.
	 *
	 * @param newsId the news ID of this news attached file
	 */
	public void setNewsId(long newsId);

	/**
	 * Returns the group ID of this news attached file.
	 *
	 * @return the group ID of this news attached file
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this news attached file.
	 *
	 * @param groupId the group ID of this news attached file
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the file ID of this news attached file.
	 *
	 * @return the file ID of this news attached file
	 */
	public long getFileId();

	/**
	 * Sets the file ID of this news attached file.
	 *
	 * @param fileId the file ID of this news attached file
	 */
	public void setFileId(long fileId);

	@Override
	public NewsAttachedFile cloneWithOriginalValues();

}