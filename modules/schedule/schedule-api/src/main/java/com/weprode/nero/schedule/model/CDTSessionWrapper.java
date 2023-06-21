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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CDTSession}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CDTSession
 * @generated
 */
public class CDTSessionWrapper
	extends BaseModelWrapper<CDTSession>
	implements CDTSession, ModelWrapper<CDTSession> {

	public CDTSessionWrapper(CDTSession cdtSession) {
		super(cdtSession);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("sessionId", getSessionId());
		attributes.put("start", getStart());
		attributes.put("end", getEnd());
		attributes.put("slot", getSlot());
		attributes.put("fullCoursName", getFullCoursName());
		attributes.put("room", getRoom());
		attributes.put("subject", getSubject());
		attributes.put("groupId", getGroupId());
		attributes.put("isManual", isIsManual());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long sessionId = (Long)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Date start = (Date)attributes.get("start");

		if (start != null) {
			setStart(start);
		}

		Date end = (Date)attributes.get("end");

		if (end != null) {
			setEnd(end);
		}

		Integer slot = (Integer)attributes.get("slot");

		if (slot != null) {
			setSlot(slot);
		}

		String fullCoursName = (String)attributes.get("fullCoursName");

		if (fullCoursName != null) {
			setFullCoursName(fullCoursName);
		}

		String room = (String)attributes.get("room");

		if (room != null) {
			setRoom(room);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Boolean isManual = (Boolean)attributes.get("isManual");

		if (isManual != null) {
			setIsManual(isManual);
		}
	}

	@Override
	public CDTSession cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public org.json.JSONObject convertToJSON(
		com.liferay.portal.kernel.model.User user) {

		return model.convertToJSON(user);
	}

	/**
	 * Returns the end of this cdt session.
	 *
	 * @return the end of this cdt session
	 */
	@Override
	public Date getEnd() {
		return model.getEnd();
	}

	/**
	 * Returns the full cours name of this cdt session.
	 *
	 * @return the full cours name of this cdt session
	 */
	@Override
	public String getFullCoursName() {
		return model.getFullCoursName();
	}

	/**
	 * Returns the group ID of this cdt session.
	 *
	 * @return the group ID of this cdt session
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the is manual of this cdt session.
	 *
	 * @return the is manual of this cdt session
	 */
	@Override
	public boolean getIsManual() {
		return model.getIsManual();
	}

	/**
	 * Returns the primary key of this cdt session.
	 *
	 * @return the primary key of this cdt session
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the room of this cdt session.
	 *
	 * @return the room of this cdt session
	 */
	@Override
	public String getRoom() {
		return model.getRoom();
	}

	/**
	 * Returns the session ID of this cdt session.
	 *
	 * @return the session ID of this cdt session
	 */
	@Override
	public long getSessionId() {
		return model.getSessionId();
	}

	/**
	 * Returns the slot of this cdt session.
	 *
	 * @return the slot of this cdt session
	 */
	@Override
	public int getSlot() {
		return model.getSlot();
	}

	/**
	 * Returns the start of this cdt session.
	 *
	 * @return the start of this cdt session
	 */
	@Override
	public Date getStart() {
		return model.getStart();
	}

	/**
	 * Returns the subject of this cdt session.
	 *
	 * @return the subject of this cdt session
	 */
	@Override
	public String getSubject() {
		return model.getSubject();
	}

	/**
	 * Returns <code>true</code> if this cdt session is is manual.
	 *
	 * @return <code>true</code> if this cdt session is is manual; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsManual() {
		return model.isIsManual();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the end of this cdt session.
	 *
	 * @param end the end of this cdt session
	 */
	@Override
	public void setEnd(Date end) {
		model.setEnd(end);
	}

	/**
	 * Sets the full cours name of this cdt session.
	 *
	 * @param fullCoursName the full cours name of this cdt session
	 */
	@Override
	public void setFullCoursName(String fullCoursName) {
		model.setFullCoursName(fullCoursName);
	}

	/**
	 * Sets the group ID of this cdt session.
	 *
	 * @param groupId the group ID of this cdt session
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets whether this cdt session is is manual.
	 *
	 * @param isManual the is manual of this cdt session
	 */
	@Override
	public void setIsManual(boolean isManual) {
		model.setIsManual(isManual);
	}

	/**
	 * Sets the primary key of this cdt session.
	 *
	 * @param primaryKey the primary key of this cdt session
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the room of this cdt session.
	 *
	 * @param room the room of this cdt session
	 */
	@Override
	public void setRoom(String room) {
		model.setRoom(room);
	}

	/**
	 * Sets the session ID of this cdt session.
	 *
	 * @param sessionId the session ID of this cdt session
	 */
	@Override
	public void setSessionId(long sessionId) {
		model.setSessionId(sessionId);
	}

	/**
	 * Sets the slot of this cdt session.
	 *
	 * @param slot the slot of this cdt session
	 */
	@Override
	public void setSlot(int slot) {
		model.setSlot(slot);
	}

	/**
	 * Sets the start of this cdt session.
	 *
	 * @param start the start of this cdt session
	 */
	@Override
	public void setStart(Date start) {
		model.setStart(start);
	}

	/**
	 * Sets the subject of this cdt session.
	 *
	 * @param subject the subject of this cdt session
	 */
	@Override
	public void setSubject(String subject) {
		model.setSubject(subject);
	}

	@Override
	protected CDTSessionWrapper wrap(CDTSession cdtSession) {
		return new CDTSessionWrapper(cdtSession);
	}

}