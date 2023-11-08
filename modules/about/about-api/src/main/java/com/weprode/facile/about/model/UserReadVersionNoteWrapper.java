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

package com.weprode.facile.about.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserReadVersionNote}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserReadVersionNote
 * @generated
 */
public class UserReadVersionNoteWrapper
	extends BaseModelWrapper<UserReadVersionNote>
	implements ModelWrapper<UserReadVersionNote>, UserReadVersionNote {

	public UserReadVersionNoteWrapper(UserReadVersionNote userReadVersionNote) {
		super(userReadVersionNote);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("hasReadLastVersionNote", isHasReadLastVersionNote());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Boolean hasReadLastVersionNote = (Boolean)attributes.get(
			"hasReadLastVersionNote");

		if (hasReadLastVersionNote != null) {
			setHasReadLastVersionNote(hasReadLastVersionNote);
		}
	}

	@Override
	public UserReadVersionNote cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the has read last version note of this user read version note.
	 *
	 * @return the has read last version note of this user read version note
	 */
	@Override
	public boolean getHasReadLastVersionNote() {
		return model.getHasReadLastVersionNote();
	}

	/**
	 * Returns the primary key of this user read version note.
	 *
	 * @return the primary key of this user read version note
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this user read version note.
	 *
	 * @return the user ID of this user read version note
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this user read version note.
	 *
	 * @return the user uuid of this user read version note
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this user read version note is has read last version note.
	 *
	 * @return <code>true</code> if this user read version note is has read last version note; <code>false</code> otherwise
	 */
	@Override
	public boolean isHasReadLastVersionNote() {
		return model.isHasReadLastVersionNote();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this user read version note is has read last version note.
	 *
	 * @param hasReadLastVersionNote the has read last version note of this user read version note
	 */
	@Override
	public void setHasReadLastVersionNote(boolean hasReadLastVersionNote) {
		model.setHasReadLastVersionNote(hasReadLastVersionNote);
	}

	/**
	 * Sets the primary key of this user read version note.
	 *
	 * @param primaryKey the primary key of this user read version note
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this user read version note.
	 *
	 * @param userId the user ID of this user read version note
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this user read version note.
	 *
	 * @param userUuid the user uuid of this user read version note
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected UserReadVersionNoteWrapper wrap(
		UserReadVersionNote userReadVersionNote) {

		return new UserReadVersionNoteWrapper(userReadVersionNote);
	}

}