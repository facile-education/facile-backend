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
		attributes.put("sessionStart", getSessionStart());
		attributes.put("sessionEnd", getSessionEnd());
		attributes.put("weekId", getWeekId());
		attributes.put("published", isPublished());
		attributes.put("title", getTitle());
		attributes.put("fullCoursName", getFullCoursName());
		attributes.put("description", getDescription());
		attributes.put("room", getRoom());
		attributes.put("subject", getSubject());
		attributes.put("schoolId", getSchoolId());
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

		Date sessionStart = (Date)attributes.get("sessionStart");

		if (sessionStart != null) {
			setSessionStart(sessionStart);
		}

		Date sessionEnd = (Date)attributes.get("sessionEnd");

		if (sessionEnd != null) {
			setSessionEnd(sessionEnd);
		}

		Long weekId = (Long)attributes.get("weekId");

		if (weekId != null) {
			setWeekId(weekId);
		}

		Boolean published = (Boolean)attributes.get("published");

		if (published != null) {
			setPublished(published);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String fullCoursName = (String)attributes.get("fullCoursName");

		if (fullCoursName != null) {
			setFullCoursName(fullCoursName);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String room = (String)attributes.get("room");

		if (room != null) {
			setRoom(room);
		}

		String subject = (String)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		Long schoolId = (Long)attributes.get("schoolId");

		if (schoolId != null) {
			setSchoolId(schoolId);
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
	public org.json.JSONObject convertToJSON() {
		return model.convertToJSON();
	}

	@Override
	public org.json.JSONObject convertToJSON(
		boolean includeDetails, com.liferay.portal.kernel.model.User user) {

		return model.convertToJSON(includeDetails, user);
	}

	@Override
	public org.json.JSONObject convertToJSON(
		long colorsTeacherId, com.liferay.portal.kernel.model.User user) {

		return model.convertToJSON(colorsTeacherId, user);
	}

	/**
	 * Returns the description of this cdt session.
	 *
	 * @return the description of this cdt session
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
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

	@Override
	public java.util.List<CDTSession> getNextSessions(
		com.liferay.portal.kernel.model.User user) {

		return model.getNextSessions(user);
	}

	@Override
	public java.util.List<CDTSession> getPreviousSessions(
		com.liferay.portal.kernel.model.User user) {

		return model.getPreviousSessions(user);
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
	 * Returns the published of this cdt session.
	 *
	 * @return the published of this cdt session
	 */
	@Override
	public boolean getPublished() {
		return model.getPublished();
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
	 * Returns the school ID of this cdt session.
	 *
	 * @return the school ID of this cdt session
	 */
	@Override
	public long getSchoolId() {
		return model.getSchoolId();
	}

	/**
	 * Returns the session end of this cdt session.
	 *
	 * @return the session end of this cdt session
	 */
	@Override
	public Date getSessionEnd() {
		return model.getSessionEnd();
	}

	@Override
	public String getSessionGroupName(boolean withSchoolName) {
		return model.getSessionGroupName(withSchoolName);
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
	 * Returns the session start of this cdt session.
	 *
	 * @return the session start of this cdt session
	 */
	@Override
	public Date getSessionStart() {
		return model.getSessionStart();
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

	@Override
	public String getTeacherList() {
		return model.getTeacherList();
	}

	/**
	 * Returns the title of this cdt session.
	 *
	 * @return the title of this cdt session
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the week ID of this cdt session.
	 *
	 * @return the week ID of this cdt session
	 */
	@Override
	public long getWeekId() {
		return model.getWeekId();
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

	/**
	 * Returns <code>true</code> if this cdt session is published.
	 *
	 * @return <code>true</code> if this cdt session is published; <code>false</code> otherwise
	 */
	@Override
	public boolean isPublished() {
		return model.isPublished();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the description of this cdt session.
	 *
	 * @param description the description of this cdt session
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
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
	 * Sets whether this cdt session is published.
	 *
	 * @param published the published of this cdt session
	 */
	@Override
	public void setPublished(boolean published) {
		model.setPublished(published);
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
	 * Sets the school ID of this cdt session.
	 *
	 * @param schoolId the school ID of this cdt session
	 */
	@Override
	public void setSchoolId(long schoolId) {
		model.setSchoolId(schoolId);
	}

	/**
	 * Sets the session end of this cdt session.
	 *
	 * @param sessionEnd the session end of this cdt session
	 */
	@Override
	public void setSessionEnd(Date sessionEnd) {
		model.setSessionEnd(sessionEnd);
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
	 * Sets the session start of this cdt session.
	 *
	 * @param sessionStart the session start of this cdt session
	 */
	@Override
	public void setSessionStart(Date sessionStart) {
		model.setSessionStart(sessionStart);
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

	/**
	 * Sets the title of this cdt session.
	 *
	 * @param title the title of this cdt session
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the week ID of this cdt session.
	 *
	 * @param weekId the week ID of this cdt session
	 */
	@Override
	public void setWeekId(long weekId) {
		model.setWeekId(weekId);
	}

	@Override
	protected CDTSessionWrapper wrap(CDTSession cdtSession) {
		return new CDTSessionWrapper(cdtSession);
	}

}