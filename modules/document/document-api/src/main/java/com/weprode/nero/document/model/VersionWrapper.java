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

package com.weprode.nero.document.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Version}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Version
 * @generated
 */
public class VersionWrapper
	extends BaseModelWrapper<Version>
	implements ModelWrapper<Version>, Version {

	public VersionWrapper(Version version) {
		super(version);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fileVersionId", getFileVersionId());
		attributes.put("dlFileEntryId", getDlFileEntryId());
		attributes.put("versionNumber", getVersionNumber());
		attributes.put("comment", getComment());
		attributes.put("downloadCount", getDownloadCount());
		attributes.put("viewCount", getViewCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long fileVersionId = (Long)attributes.get("fileVersionId");

		if (fileVersionId != null) {
			setFileVersionId(fileVersionId);
		}

		Long dlFileEntryId = (Long)attributes.get("dlFileEntryId");

		if (dlFileEntryId != null) {
			setDlFileEntryId(dlFileEntryId);
		}

		Double versionNumber = (Double)attributes.get("versionNumber");

		if (versionNumber != null) {
			setVersionNumber(versionNumber);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}

		Long downloadCount = (Long)attributes.get("downloadCount");

		if (downloadCount != null) {
			setDownloadCount(downloadCount);
		}

		Long viewCount = (Long)attributes.get("viewCount");

		if (viewCount != null) {
			setViewCount(viewCount);
		}
	}

	@Override
	public Version cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the comment of this version.
	 *
	 * @return the comment of this version
	 */
	@Override
	public String getComment() {
		return model.getComment();
	}

	/**
	 * Returns the dl file entry ID of this version.
	 *
	 * @return the dl file entry ID of this version
	 */
	@Override
	public long getDlFileEntryId() {
		return model.getDlFileEntryId();
	}

	/**
	 * Returns the download count of this version.
	 *
	 * @return the download count of this version
	 */
	@Override
	public long getDownloadCount() {
		return model.getDownloadCount();
	}

	/**
	 * Returns the file version ID of this version.
	 *
	 * @return the file version ID of this version
	 */
	@Override
	public long getFileVersionId() {
		return model.getFileVersionId();
	}

	/**
	 * Returns the primary key of this version.
	 *
	 * @return the primary key of this version
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the version number of this version.
	 *
	 * @return the version number of this version
	 */
	@Override
	public double getVersionNumber() {
		return model.getVersionNumber();
	}

	/**
	 * Returns the view count of this version.
	 *
	 * @return the view count of this version
	 */
	@Override
	public long getViewCount() {
		return model.getViewCount();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the comment of this version.
	 *
	 * @param comment the comment of this version
	 */
	@Override
	public void setComment(String comment) {
		model.setComment(comment);
	}

	/**
	 * Sets the dl file entry ID of this version.
	 *
	 * @param dlFileEntryId the dl file entry ID of this version
	 */
	@Override
	public void setDlFileEntryId(long dlFileEntryId) {
		model.setDlFileEntryId(dlFileEntryId);
	}

	/**
	 * Sets the download count of this version.
	 *
	 * @param downloadCount the download count of this version
	 */
	@Override
	public void setDownloadCount(long downloadCount) {
		model.setDownloadCount(downloadCount);
	}

	/**
	 * Sets the file version ID of this version.
	 *
	 * @param fileVersionId the file version ID of this version
	 */
	@Override
	public void setFileVersionId(long fileVersionId) {
		model.setFileVersionId(fileVersionId);
	}

	/**
	 * Sets the primary key of this version.
	 *
	 * @param primaryKey the primary key of this version
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the version number of this version.
	 *
	 * @param versionNumber the version number of this version
	 */
	@Override
	public void setVersionNumber(double versionNumber) {
		model.setVersionNumber(versionNumber);
	}

	/**
	 * Sets the view count of this version.
	 *
	 * @param viewCount the view count of this version
	 */
	@Override
	public void setViewCount(long viewCount) {
		model.setViewCount(viewCount);
	}

	@Override
	protected VersionWrapper wrap(Version version) {
		return new VersionWrapper(version);
	}

}