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

package com.weprode.facile.application.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Broadcast}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Broadcast
 * @generated
 */
public class BroadcastWrapper
	extends BaseModelWrapper<Broadcast>
	implements Broadcast, ModelWrapper<Broadcast> {

	public BroadcastWrapper(Broadcast broadcast) {
		super(broadcast);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("broadcastId", getBroadcastId());
		attributes.put("schoolId", getSchoolId());
		attributes.put("applicationId", getApplicationId());
		attributes.put("isBroadcasted", isIsBroadcasted());
		attributes.put("applicationUrl", getApplicationUrl());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long broadcastId = (Long)attributes.get("broadcastId");

		if (broadcastId != null) {
			setBroadcastId(broadcastId);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
		}

		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		Boolean isBroadcasted = (Boolean)attributes.get("isBroadcasted");

		if (isBroadcasted != null) {
			setIsBroadcasted(isBroadcasted);
		}

		String applicationUrl = (String)attributes.get("applicationUrl");

		if (applicationUrl != null) {
			setApplicationUrl(applicationUrl);
		}
	}

	@Override
	public Broadcast cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the application ID of this broadcast.
	 *
	 * @return the application ID of this broadcast
	 */
	@Override
	public long getApplicationId() {
		return model.getApplicationId();
	}

	/**
	 * Returns the application url of this broadcast.
	 *
	 * @return the application url of this broadcast
	 */
	@Override
	public String getApplicationUrl() {
		return model.getApplicationUrl();
	}

	/**
	 * Returns the broadcast ID of this broadcast.
	 *
	 * @return the broadcast ID of this broadcast
	 */
	@Override
	public long getBroadcastId() {
		return model.getBroadcastId();
	}

	/**
	 * Returns the is broadcasted of this broadcast.
	 *
	 * @return the is broadcasted of this broadcast
	 */
	@Override
	public boolean getIsBroadcasted() {
		return model.getIsBroadcasted();
	}

	/**
	 * Returns the primary key of this broadcast.
	 *
	 * @return the primary key of this broadcast
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the school ID of this broadcast.
	 *
	 * @return the school ID of this broadcast
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns <code>true</code> if this broadcast is is broadcasted.
	 *
	 * @return <code>true</code> if this broadcast is is broadcasted; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsBroadcasted() {
		return model.isIsBroadcasted();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the application ID of this broadcast.
	 *
	 * @param applicationId the application ID of this broadcast
	 */
	@Override
	public void setApplicationId(long applicationId) {
		model.setApplicationId(applicationId);
	}

	/**
	 * Sets the application url of this broadcast.
	 *
	 * @param applicationUrl the application url of this broadcast
	 */
	@Override
	public void setApplicationUrl(String applicationUrl) {
		model.setApplicationUrl(applicationUrl);
	}

	/**
	 * Sets the broadcast ID of this broadcast.
	 *
	 * @param broadcastId the broadcast ID of this broadcast
	 */
	@Override
	public void setBroadcastId(long broadcastId) {
		model.setBroadcastId(broadcastId);
	}

	/**
	 * Sets whether this broadcast is is broadcasted.
	 *
	 * @param isBroadcasted the is broadcasted of this broadcast
	 */
	@Override
	public void setIsBroadcasted(boolean isBroadcasted) {
		model.setIsBroadcasted(isBroadcasted);
	}

	/**
	 * Sets the primary key of this broadcast.
	 *
	 * @param primaryKey the primary key of this broadcast
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the school ID of this broadcast.
	 *
	 * @param schoolId the school ID of this broadcast
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	@Override
	protected BroadcastWrapper wrap(Broadcast broadcast) {
		return new BroadcastWrapper(broadcast);
	}

}