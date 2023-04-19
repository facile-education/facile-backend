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
 * The base model interface for the Homework service. Represents a row in the &quot;Schedule_Homework&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.nero.schedule.model.impl.HomeworkModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.nero.schedule.model.impl.HomeworkImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Homework
 * @generated
 */
@ProviderType
public interface HomeworkModel extends BaseModel<Homework> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a homework model instance should use the {@link Homework} interface instead.
	 */

	/**
	 * Returns the primary key of this homework.
	 *
	 * @return the primary key of this homework
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this homework.
	 *
	 * @param primaryKey the primary key of this homework
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the homework ID of this homework.
	 *
	 * @return the homework ID of this homework
	 */
	public long getHomeworkId();

	/**
	 * Sets the homework ID of this homework.
	 *
	 * @param homeworkId the homework ID of this homework
	 */
	public void setHomeworkId(long homeworkId);

	/**
	 * Returns the type of this homework.
	 *
	 * @return the type of this homework
	 */
	public long getType();

	/**
	 * Sets the type of this homework.
	 *
	 * @param type the type of this homework
	 */
	public void setType(long type);

	/**
	 * Returns the source session ID of this homework.
	 *
	 * @return the source session ID of this homework
	 */
	public long getSourceSessionId();

	/**
	 * Sets the source session ID of this homework.
	 *
	 * @param sourceSessionId the source session ID of this homework
	 */
	public void setSourceSessionId(long sourceSessionId);

	/**
	 * Returns the target session ID of this homework.
	 *
	 * @return the target session ID of this homework
	 */
	public long getTargetSessionId();

	/**
	 * Sets the target session ID of this homework.
	 *
	 * @param targetSessionId the target session ID of this homework
	 */
	public void setTargetSessionId(long targetSessionId);

	/**
	 * Returns the target week ID of this homework.
	 *
	 * @return the target week ID of this homework
	 */
	public int getTargetWeekId();

	/**
	 * Sets the target week ID of this homework.
	 *
	 * @param targetWeekId the target week ID of this homework
	 */
	public void setTargetWeekId(int targetWeekId);

	/**
	 * Returns the target date of this homework.
	 *
	 * @return the target date of this homework
	 */
	public Date getTargetDate();

	/**
	 * Sets the target date of this homework.
	 *
	 * @param targetDate the target date of this homework
	 */
	public void setTargetDate(Date targetDate);

	/**
	 * Returns the group ID of this homework.
	 *
	 * @return the group ID of this homework
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this homework.
	 *
	 * @param groupId the group ID of this homework
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the teacher ID of this homework.
	 *
	 * @return the teacher ID of this homework
	 */
	public long getTeacherId();

	/**
	 * Sets the teacher ID of this homework.
	 *
	 * @param teacherId the teacher ID of this homework
	 */
	public void setTeacherId(long teacherId);

	/**
	 * Returns the description of this homework.
	 *
	 * @return the description of this homework
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this homework.
	 *
	 * @param description the description of this homework
	 */
	public void setDescription(String description);

	/**
	 * Returns the estimated time of this homework.
	 *
	 * @return the estimated time of this homework
	 */
	public long getEstimatedTime();

	/**
	 * Sets the estimated time of this homework.
	 *
	 * @param estimatedTime the estimated time of this homework
	 */
	public void setEstimatedTime(long estimatedTime);

	/**
	 * Returns the from date of this homework.
	 *
	 * @return the from date of this homework
	 */
	public Date getFromDate();

	/**
	 * Sets the from date of this homework.
	 *
	 * @param fromDate the from date of this homework
	 */
	public void setFromDate(Date fromDate);

	/**
	 * Returns the is custom student list of this homework.
	 *
	 * @return the is custom student list of this homework
	 */
	public boolean getIsCustomStudentList();

	/**
	 * Returns <code>true</code> if this homework is is custom student list.
	 *
	 * @return <code>true</code> if this homework is is custom student list; <code>false</code> otherwise
	 */
	public boolean isIsCustomStudentList();

	/**
	 * Sets whether this homework is is custom student list.
	 *
	 * @param isCustomStudentList the is custom student list of this homework
	 */
	public void setIsCustomStudentList(boolean isCustomStudentList);

	@Override
	public Homework cloneWithOriginalValues();

}