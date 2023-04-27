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

package com.weprode.nero.school.life.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Notification}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Notification
 * @generated
 */
public class NotificationWrapper
	extends BaseModelWrapper<Notification>
	implements ModelWrapper<Notification>, Notification {

	public NotificationWrapper(Notification notification) {
		super(notification);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("schoollifeSessionId", getSchoollifeSessionId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long schoollifeSessionId = (Long)attributes.get("schoollifeSessionId");

		if (schoollifeSessionId != null) {
			setSchoollifeSessionId(schoollifeSessionId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	@Override
	public Notification cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the primary key of this notification.
	 *
	 * @return the primary key of this notification
	 */
	@Override
	public com.weprode.nero.school.life.service.persistence.NotificationPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the schoollife session ID of this notification.
	 *
	 * @return the schoollife session ID of this notification
	 */
	@Override
	public long getSchoollifeSessionId() {
		return model.getSchoollifeSessionId();
	}

	/**
	 * Returns the user ID of this notification.
	 *
	 * @return the user ID of this notification
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this notification.
	 *
	 * @return the user uuid of this notification
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
	 * Sets the primary key of this notification.
	 *
	 * @param primaryKey the primary key of this notification
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.nero.school.life.service.persistence.NotificationPK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the schoollife session ID of this notification.
	 *
	 * @param schoollifeSessionId the schoollife session ID of this notification
	 */
	@Override
	public void setSchoollifeSessionId(long schoollifeSessionId) {
		model.setSchoollifeSessionId(schoollifeSessionId);
	}

	/**
	 * Sets the user ID of this notification.
	 *
	 * @param userId the user ID of this notification
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this notification.
	 *
	 * @param userUuid the user uuid of this notification
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected NotificationWrapper wrap(Notification notification) {
		return new NotificationWrapper(notification);
	}

}