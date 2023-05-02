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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the CDTSession service. Represents a row in the &quot;Schedule_CDTSession&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.schedule.model.impl.CDTSessionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.schedule.model.impl.CDTSessionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CDTSession
 * @generated
 */
@ProviderType
public interface CDTSessionModel extends BaseModel<CDTSession> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cdt session model instance should use the {@link CDTSession} interface instead.
	 */

	/**
	 * Returns the primary key of this cdt session.
	 *
	 * @return the primary key of this cdt session
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cdt session.
	 *
	 * @param primaryKey the primary key of this cdt session
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the session ID of this cdt session.
	 *
	 * @return the session ID of this cdt session
	 */
	public long getSessionId();

	/**
	 * Sets the session ID of this cdt session.
	 *
	 * @param sessionId the session ID of this cdt session
	 */
	public void setSessionId(long sessionId);

	/**
	 * Returns the session start of this cdt session.
	 *
	 * @return the session start of this cdt session
	 */
	public Date getSessionStart();

	/**
	 * Sets the session start of this cdt session.
	 *
	 * @param sessionStart the session start of this cdt session
	 */
	public void setSessionStart(Date sessionStart);

	/**
	 * Returns the session end of this cdt session.
	 *
	 * @return the session end of this cdt session
	 */
	public Date getSessionEnd();

	/**
	 * Sets the session end of this cdt session.
	 *
	 * @param sessionEnd the session end of this cdt session
	 */
	public void setSessionEnd(Date sessionEnd);

	/**
	 * Returns the week ID of this cdt session.
	 *
	 * @return the week ID of this cdt session
	 */
	public long getWeekId();

	/**
	 * Sets the week ID of this cdt session.
	 *
	 * @param weekId the week ID of this cdt session
	 */
	public void setWeekId(long weekId);

	/**
	 * Returns the published of this cdt session.
	 *
	 * @return the published of this cdt session
	 */
	public boolean getPublished();

	/**
	 * Returns <code>true</code> if this cdt session is published.
	 *
	 * @return <code>true</code> if this cdt session is published; <code>false</code> otherwise
	 */
	public boolean isPublished();

	/**
	 * Sets whether this cdt session is published.
	 *
	 * @param published the published of this cdt session
	 */
	public void setPublished(boolean published);

	/**
	 * Returns the title of this cdt session.
	 *
	 * @return the title of this cdt session
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this cdt session.
	 *
	 * @param title the title of this cdt session
	 */
	public void setTitle(String title);

	/**
	 * Returns the full cours name of this cdt session.
	 *
	 * @return the full cours name of this cdt session
	 */
	@AutoEscape
	public String getFullCoursName();

	/**
	 * Sets the full cours name of this cdt session.
	 *
	 * @param fullCoursName the full cours name of this cdt session
	 */
	public void setFullCoursName(String fullCoursName);

	/**
	 * Returns the description of this cdt session.
	 *
	 * @return the description of this cdt session
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this cdt session.
	 *
	 * @param description the description of this cdt session
	 */
	public void setDescription(String description);

	/**
	 * Returns the room of this cdt session.
	 *
	 * @return the room of this cdt session
	 */
	@AutoEscape
	public String getRoom();

	/**
	 * Sets the room of this cdt session.
	 *
	 * @param room the room of this cdt session
	 */
	public void setRoom(String room);

	/**
	 * Returns the subject of this cdt session.
	 *
	 * @return the subject of this cdt session
	 */
	@AutoEscape
	public String getSubject();

	/**
	 * Sets the subject of this cdt session.
	 *
	 * @param subject the subject of this cdt session
	 */
	public void setSubject(String subject);

	/**
	 * Returns the school ID of this cdt session.
	 *
	 * @return the school ID of this cdt session
	 */
	public long getSchoolId();

	/**
	 * Sets the school ID of this cdt session.
	 *
	 * @param schoolId the school ID of this cdt session
	 */
	public void setSchoolId(long schoolId);

	/**
	 * Returns the group ID of this cdt session.
	 *
	 * @return the group ID of this cdt session
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this cdt session.
	 *
	 * @param groupId the group ID of this cdt session
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the is manual of this cdt session.
	 *
	 * @return the is manual of this cdt session
	 */
	public boolean getIsManual();

	/**
	 * Returns <code>true</code> if this cdt session is is manual.
	 *
	 * @return <code>true</code> if this cdt session is is manual; <code>false</code> otherwise
	 */
	public boolean isIsManual();

	/**
	 * Sets whether this cdt session is is manual.
	 *
	 * @param isManual the is manual of this cdt session
	 */
	public void setIsManual(boolean isManual);

	@Override
	public CDTSession cloneWithOriginalValues();

}