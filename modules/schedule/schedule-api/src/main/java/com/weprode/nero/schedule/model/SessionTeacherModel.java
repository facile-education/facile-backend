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
 * The base model interface for the SessionTeacher service. Represents a row in the &quot;Schedule_SessionTeacher&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.schedule.model.impl.SessionTeacherModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.schedule.model.impl.SessionTeacherImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionTeacher
 * @generated
 */
@ProviderType
public interface SessionTeacherModel extends BaseModel<SessionTeacher> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a session teacher model instance should use the {@link SessionTeacher} interface instead.
	 */

	/**
	 * Returns the primary key of this session teacher.
	 *
	 * @return the primary key of this session teacher
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this session teacher.
	 *
	 * @param primaryKey the primary key of this session teacher
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the session teacher ID of this session teacher.
	 *
	 * @return the session teacher ID of this session teacher
	 */
	public long getSessionTeacherId();

	/**
	 * Sets the session teacher ID of this session teacher.
	 *
	 * @param sessionTeacherId the session teacher ID of this session teacher
	 */
	public void setSessionTeacherId(long sessionTeacherId);

	/**
	 * Returns the session ID of this session teacher.
	 *
	 * @return the session ID of this session teacher
	 */
	public long getSessionId();

	/**
	 * Sets the session ID of this session teacher.
	 *
	 * @param sessionId the session ID of this session teacher
	 */
	public void setSessionId(long sessionId);

	/**
	 * Returns the teacher ID of this session teacher.
	 *
	 * @return the teacher ID of this session teacher
	 */
	public long getTeacherId();

	/**
	 * Sets the teacher ID of this session teacher.
	 *
	 * @param teacherId the teacher ID of this session teacher
	 */
	public void setTeacherId(long teacherId);

	/**
	 * Returns the status of this session teacher.
	 *
	 * @return the status of this session teacher
	 */
	public int getStatus();

	/**
	 * Sets the status of this session teacher.
	 *
	 * @param status the status of this session teacher
	 */
	public void setStatus(int status);

	/**
	 * Returns the substitute ID of this session teacher.
	 *
	 * @return the substitute ID of this session teacher
	 */
	public long getSubstituteId();

	/**
	 * Sets the substitute ID of this session teacher.
	 *
	 * @param substituteId the substitute ID of this session teacher
	 */
	public void setSubstituteId(long substituteId);

	/**
	 * Returns the modification date of this session teacher.
	 *
	 * @return the modification date of this session teacher
	 */
	public Date getModificationDate();

	/**
	 * Sets the modification date of this session teacher.
	 *
	 * @param modificationDate the modification date of this session teacher
	 */
	public void setModificationDate(Date modificationDate);

	/**
	 * Returns the private notes of this session teacher.
	 *
	 * @return the private notes of this session teacher
	 */
	@AutoEscape
	public String getPrivateNotes();

	/**
	 * Sets the private notes of this session teacher.
	 *
	 * @param privateNotes the private notes of this session teacher
	 */
	public void setPrivateNotes(String privateNotes);

	@Override
	public SessionTeacher cloneWithOriginalValues();

}