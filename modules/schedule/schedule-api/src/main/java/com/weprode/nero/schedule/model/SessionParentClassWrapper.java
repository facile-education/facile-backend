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

package com.weprode.nero.schedule.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SessionParentClass}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionParentClass
 * @generated
 */
public class SessionParentClassWrapper
	extends BaseModelWrapper<SessionParentClass>
	implements ModelWrapper<SessionParentClass>, SessionParentClass {

	public SessionParentClassWrapper(SessionParentClass sessionParentClass) {
		super(sessionParentClass);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("sessionParentClassId", getSessionParentClassId());
		attributes.put("sessionId", getSessionId());
		attributes.put("groupId", getGroupId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long sessionParentClassId = (Long)attributes.get(
			"sessionParentClassId");

		if (sessionParentClassId != null) {
			setSessionParentClassId(sessionParentClassId);
		}

		Long sessionId = (Long)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}
	}

	@Override
	public SessionParentClass cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the group ID of this session parent class.
	 *
	 * @return the group ID of this session parent class
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the primary key of this session parent class.
	 *
	 * @return the primary key of this session parent class
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the session ID of this session parent class.
	 *
	 * @return the session ID of this session parent class
	 */
	@Override
	public long getSessionId() {
		return model.getSessionId();
	}

	/**
	 * Returns the session parent class ID of this session parent class.
	 *
	 * @return the session parent class ID of this session parent class
	 */
	@Override
	public long getSessionParentClassId() {
		return model.getSessionParentClassId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the group ID of this session parent class.
	 *
	 * @param groupId the group ID of this session parent class
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the primary key of this session parent class.
	 *
	 * @param primaryKey the primary key of this session parent class
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the session ID of this session parent class.
	 *
	 * @param sessionId the session ID of this session parent class
	 */
	@Override
	public void setSessionId(long sessionId) {
		model.setSessionId(sessionId);
	}

	/**
	 * Sets the session parent class ID of this session parent class.
	 *
	 * @param sessionParentClassId the session parent class ID of this session parent class
	 */
	@Override
	public void setSessionParentClassId(long sessionParentClassId) {
		model.setSessionParentClassId(sessionParentClassId);
	}

	@Override
	protected SessionParentClassWrapper wrap(
		SessionParentClass sessionParentClass) {

		return new SessionParentClassWrapper(sessionParentClass);
	}

}