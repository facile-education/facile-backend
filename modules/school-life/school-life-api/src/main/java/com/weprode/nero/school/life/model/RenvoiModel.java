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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import com.weprode.nero.school.life.service.persistence.RenvoiPK;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Renvoi service. Represents a row in the &quot;Schoollife_Renvoi&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.school.life.model.impl.RenvoiModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.school.life.model.impl.RenvoiImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Renvoi
 * @generated
 */
@ProviderType
public interface RenvoiModel extends BaseModel<Renvoi> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a renvoi model instance should use the {@link Renvoi} interface instead.
	 */

	/**
	 * Returns the primary key of this renvoi.
	 *
	 * @return the primary key of this renvoi
	 */
	public RenvoiPK getPrimaryKey();

	/**
	 * Sets the primary key of this renvoi.
	 *
	 * @param primaryKey the primary key of this renvoi
	 */
	public void setPrimaryKey(RenvoiPK primaryKey);

	/**
	 * Returns the schoollife session ID of this renvoi.
	 *
	 * @return the schoollife session ID of this renvoi
	 */
	public long getSchoollifeSessionId();

	/**
	 * Sets the schoollife session ID of this renvoi.
	 *
	 * @param schoollifeSessionId the schoollife session ID of this renvoi
	 */
	public void setSchoollifeSessionId(long schoollifeSessionId);

	/**
	 * Returns the student ID of this renvoi.
	 *
	 * @return the student ID of this renvoi
	 */
	public long getStudentId();

	/**
	 * Sets the student ID of this renvoi.
	 *
	 * @param studentId the student ID of this renvoi
	 */
	public void setStudentId(long studentId);

	/**
	 * Returns the school ID of this renvoi.
	 *
	 * @return the school ID of this renvoi
	 */
	public long getSchoolId();

	/**
	 * Sets the school ID of this renvoi.
	 *
	 * @param schoolId the school ID of this renvoi
	 */
	public void setSchoolId(long schoolId);

	/**
	 * Returns the renvoi date of this renvoi.
	 *
	 * @return the renvoi date of this renvoi
	 */
	public Date getRenvoiDate();

	/**
	 * Sets the renvoi date of this renvoi.
	 *
	 * @param renvoiDate the renvoi date of this renvoi
	 */
	public void setRenvoiDate(Date renvoiDate);

	/**
	 * Returns the teacher ID of this renvoi.
	 *
	 * @return the teacher ID of this renvoi
	 */
	public long getTeacherId();

	/**
	 * Sets the teacher ID of this renvoi.
	 *
	 * @param teacherId the teacher ID of this renvoi
	 */
	public void setTeacherId(long teacherId);

	/**
	 * Returns the source session ID of this renvoi.
	 *
	 * @return the source session ID of this renvoi
	 */
	public long getSourceSessionId();

	/**
	 * Sets the source session ID of this renvoi.
	 *
	 * @param sourceSessionId the source session ID of this renvoi
	 */
	public void setSourceSessionId(long sourceSessionId);

	/**
	 * Returns the source schoollife session ID of this renvoi.
	 *
	 * @return the source schoollife session ID of this renvoi
	 */
	public long getSourceSchoollifeSessionId();

	/**
	 * Sets the source schoollife session ID of this renvoi.
	 *
	 * @param sourceSchoollifeSessionId the source schoollife session ID of this renvoi
	 */
	public void setSourceSchoollifeSessionId(long sourceSchoollifeSessionId);

	/**
	 * Returns the source teacher ID of this renvoi.
	 *
	 * @return the source teacher ID of this renvoi
	 */
	public long getSourceTeacherId();

	/**
	 * Sets the source teacher ID of this renvoi.
	 *
	 * @param sourceTeacherId the source teacher ID of this renvoi
	 */
	public void setSourceTeacherId(long sourceTeacherId);

	/**
	 * Returns the reason of this renvoi.
	 *
	 * @return the reason of this renvoi
	 */
	@AutoEscape
	public String getReason();

	/**
	 * Sets the reason of this renvoi.
	 *
	 * @param reason the reason of this renvoi
	 */
	public void setReason(String reason);

	/**
	 * Returns the status of this renvoi.
	 *
	 * @return the status of this renvoi
	 */
	public int getStatus();

	/**
	 * Sets the status of this renvoi.
	 *
	 * @param status the status of this renvoi
	 */
	public void setStatus(int status);

	@Override
	public Renvoi cloneWithOriginalValues();

}