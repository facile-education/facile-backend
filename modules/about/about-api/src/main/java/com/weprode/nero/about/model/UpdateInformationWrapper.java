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
 * This class is a wrapper for {@link UpdateInformation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UpdateInformation
 * @generated
 */
public class UpdateInformationWrapper
	extends BaseModelWrapper<UpdateInformation>
	implements ModelWrapper<UpdateInformation>, UpdateInformation {

	public UpdateInformationWrapper(UpdateInformation updateInformation) {
		super(updateInformation);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("updateInfoId", getUpdateInfoId());
		attributes.put("description", getDescription());
		attributes.put("modifyDate", getModifyDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long updateInfoId = (Long)attributes.get("updateInfoId");

		if (updateInfoId != null) {
			setUpdateInfoId(updateInfoId);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date modifyDate = (Date)attributes.get("modifyDate");

		if (modifyDate != null) {
			setModifyDate(modifyDate);
		}
	}

	@Override
	public UpdateInformation cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the description of this update information.
	 *
	 * @return the description of this update information
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the modify date of this update information.
	 *
	 * @return the modify date of this update information
	 */
	@Override
	public Date getModifyDate() {
		return model.getModifyDate();
	}

	/**
	 * Returns the primary key of this update information.
	 *
	 * @return the primary key of this update information
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the update info ID of this update information.
	 *
	 * @return the update info ID of this update information
	 */
	@Override
	public long getUpdateInfoId() {
		return model.getUpdateInfoId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the description of this update information.
	 *
	 * @param description the description of this update information
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the modify date of this update information.
	 *
	 * @param modifyDate the modify date of this update information
	 */
	@Override
	public void setModifyDate(Date modifyDate) {
		model.setModifyDate(modifyDate);
	}

	/**
	 * Sets the primary key of this update information.
	 *
	 * @param primaryKey the primary key of this update information
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the update info ID of this update information.
	 *
	 * @param updateInfoId the update info ID of this update information
	 */
	@Override
	public void setUpdateInfoId(long updateInfoId) {
		model.setUpdateInfoId(updateInfoId);
	}

	@Override
	protected UpdateInformationWrapper wrap(
		UpdateInformation updateInformation) {

		return new UpdateInformationWrapper(updateInformation);
	}

}