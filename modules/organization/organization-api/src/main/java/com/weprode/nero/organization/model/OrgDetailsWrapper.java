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

package com.weprode.nero.organization.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OrgDetails}.
 * </p>
 *
 * @author Marc Salvat
 * @see OrgDetails
 * @generated
 */
public class OrgDetailsWrapper
	extends BaseModelWrapper<OrgDetails>
	implements ModelWrapper<OrgDetails>, OrgDetails {

	public OrgDetailsWrapper(OrgDetails orgDetails) {
		super(orgDetails);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("orgId", getOrgId());
		attributes.put("schoolId", getSchoolId());
		attributes.put("orgName", getOrgName());
		attributes.put("type", getType());
		attributes.put("isArchive", isIsArchive());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long orgId = (Long)attributes.get("orgId");

		if (orgId != null) {
			setOrgId(orgId);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		String orgName = (String)attributes.get("orgName");

		if (orgName != null) {
			setOrgName(orgName);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Boolean isArchive = (Boolean)attributes.get("isArchive");

		if (isArchive != null) {
			setIsArchive(isArchive);
		}
	}

	@Override
	public OrgDetails cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the is archive of this org details.
	 *
	 * @return the is archive of this org details
	 */
	@Override
	public boolean getIsArchive() {
		return model.getIsArchive();
	}

	/**
	 * Returns the org ID of this org details.
	 *
	 * @return the org ID of this org details
	 */
	@Override
	public long getOrgId() {
		return model.getOrgId();
	}

	/**
	 * Returns the org name of this org details.
	 *
	 * @return the org name of this org details
	 */
	@Override
	public String getOrgName() {
		return model.getOrgName();
	}

	/**
	 * Returns the primary key of this org details.
	 *
	 * @return the primary key of this org details
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the school ID of this org details.
	 *
	 * @return the school ID of this org details
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the type of this org details.
	 *
	 * @return the type of this org details
	 */
	@Override
	public int getType() {
		return model.getType();
	}

	/**
	 * Returns <code>true</code> if this org details is is archive.
	 *
	 * @return <code>true</code> if this org details is is archive; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsArchive() {
		return model.isIsArchive();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this org details is is archive.
	 *
	 * @param isArchive the is archive of this org details
	 */
	@Override
	public void setIsArchive(boolean isArchive) {
		model.setIsArchive(isArchive);
	}

	/**
	 * Sets the org ID of this org details.
	 *
	 * @param orgId the org ID of this org details
	 */
	@Override
	public void setOrgId(long orgId) {
		model.setOrgId(orgId);
	}

	/**
	 * Sets the org name of this org details.
	 *
	 * @param orgName the org name of this org details
	 */
	@Override
	public void setOrgName(String orgName) {
		model.setOrgName(orgName);
	}

	/**
	 * Sets the primary key of this org details.
	 *
	 * @param primaryKey the primary key of this org details
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the school ID of this org details.
	 *
	 * @param schoolId the school ID of this org details
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the type of this org details.
	 *
	 * @param type the type of this org details
	 */
	@Override
	public void setType(int type) {
		model.setType(type);
	}

	@Override
	protected OrgDetailsWrapper wrap(OrgDetails orgDetails) {
		return new OrgDetailsWrapper(orgDetails);
	}

}