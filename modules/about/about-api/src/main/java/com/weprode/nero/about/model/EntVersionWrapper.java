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

package com.weprode.nero.about.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link EntVersion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntVersion
 * @generated
 */
public class EntVersionWrapper
	extends BaseModelWrapper<EntVersion>
	implements EntVersion, ModelWrapper<EntVersion> {

	public EntVersionWrapper(EntVersion entVersion) {
		super(entVersion);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entVersionId", getEntVersionId());
		attributes.put("version", getVersion());
		attributes.put("details", getDetails());
		attributes.put("versionDate", getVersionDate());
		attributes.put("isLast", isIsLast());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long entVersionId = (Long)attributes.get("entVersionId");

		if (entVersionId != null) {
			setEntVersionId(entVersionId);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String details = (String)attributes.get("details");

		if (details != null) {
			setDetails(details);
		}

		Date versionDate = (Date)attributes.get("versionDate");

		if (versionDate != null) {
			setVersionDate(versionDate);
		}

		Boolean isLast = (Boolean)attributes.get("isLast");

		if (isLast != null) {
			setIsLast(isLast);
		}
	}

	@Override
	public EntVersion cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the details of this ent version.
	 *
	 * @return the details of this ent version
	 */
	@Override
	public String getDetails() {
		return model.getDetails();
	}

	/**
	 * Returns the ent version ID of this ent version.
	 *
	 * @return the ent version ID of this ent version
	 */
	@Override
	public long getEntVersionId() {
		return model.getEntVersionId();
	}

	/**
	 * Returns the is last of this ent version.
	 *
	 * @return the is last of this ent version
	 */
	@Override
	public boolean getIsLast() {
		return model.getIsLast();
	}

	/**
	 * Returns the primary key of this ent version.
	 *
	 * @return the primary key of this ent version
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the version of this ent version.
	 *
	 * @return the version of this ent version
	 */
	@Override
	public String getVersion() {
		return model.getVersion();
	}

	/**
	 * Returns the version date of this ent version.
	 *
	 * @return the version date of this ent version
	 */
	@Override
	public Date getVersionDate() {
		return model.getVersionDate();
	}

	/**
	 * Returns <code>true</code> if this ent version is is last.
	 *
	 * @return <code>true</code> if this ent version is is last; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsLast() {
		return model.isIsLast();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the details of this ent version.
	 *
	 * @param details the details of this ent version
	 */
	@Override
	public void setDetails(String details) {
		model.setDetails(details);
	}

	/**
	 * Sets the ent version ID of this ent version.
	 *
	 * @param entVersionId the ent version ID of this ent version
	 */
	@Override
	public void setEntVersionId(long entVersionId) {
		model.setEntVersionId(entVersionId);
	}

	/**
	 * Sets whether this ent version is is last.
	 *
	 * @param isLast the is last of this ent version
	 */
	@Override
	public void setIsLast(boolean isLast) {
		model.setIsLast(isLast);
	}

	/**
	 * Sets the primary key of this ent version.
	 *
	 * @param primaryKey the primary key of this ent version
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the version of this ent version.
	 *
	 * @param version the version of this ent version
	 */
	@Override
	public void setVersion(String version) {
		model.setVersion(version);
	}

	/**
	 * Sets the version date of this ent version.
	 *
	 * @param versionDate the version date of this ent version
	 */
	@Override
	public void setVersionDate(Date versionDate) {
		model.setVersionDate(versionDate);
	}

	@Override
	protected EntVersionWrapper wrap(EntVersion entVersion) {
		return new EntVersionWrapper(entVersion);
	}

}