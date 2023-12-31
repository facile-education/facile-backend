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

package com.weprode.facile.document.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Version service. Represents a row in the &quot;Document_Version&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.document.model.impl.VersionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.document.model.impl.VersionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Version
 * @generated
 */
@ProviderType
public interface VersionModel extends BaseModel<Version> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a version model instance should use the {@link Version} interface instead.
	 */

	/**
	 * Returns the primary key of this version.
	 *
	 * @return the primary key of this version
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this version.
	 *
	 * @param primaryKey the primary key of this version
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the file version ID of this version.
	 *
	 * @return the file version ID of this version
	 */
	public long getFileVersionId();

	/**
	 * Sets the file version ID of this version.
	 *
	 * @param fileVersionId the file version ID of this version
	 */
	public void setFileVersionId(long fileVersionId);

	/**
	 * Returns the dl file entry ID of this version.
	 *
	 * @return the dl file entry ID of this version
	 */
	public long getDlFileEntryId();

	/**
	 * Sets the dl file entry ID of this version.
	 *
	 * @param dlFileEntryId the dl file entry ID of this version
	 */
	public void setDlFileEntryId(long dlFileEntryId);

	/**
	 * Returns the version number of this version.
	 *
	 * @return the version number of this version
	 */
	public double getVersionNumber();

	/**
	 * Sets the version number of this version.
	 *
	 * @param versionNumber the version number of this version
	 */
	public void setVersionNumber(double versionNumber);

	/**
	 * Returns the comment of this version.
	 *
	 * @return the comment of this version
	 */
	@AutoEscape
	public String getComment();

	/**
	 * Sets the comment of this version.
	 *
	 * @param comment the comment of this version
	 */
	public void setComment(String comment);

	/**
	 * Returns the download count of this version.
	 *
	 * @return the download count of this version
	 */
	public long getDownloadCount();

	/**
	 * Sets the download count of this version.
	 *
	 * @param downloadCount the download count of this version
	 */
	public void setDownloadCount(long downloadCount);

	/**
	 * Returns the view count of this version.
	 *
	 * @return the view count of this version
	 */
	public long getViewCount();

	/**
	 * Sets the view count of this version.
	 *
	 * @param viewCount the view count of this version
	 */
	public void setViewCount(long viewCount);

	@Override
	public Version cloneWithOriginalValues();

}