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

package com.weprode.facile.course.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Homework service. Represents a row in the &quot;Course_Homework&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.weprode.facile.course.model.impl.HomeworkModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.weprode.facile.course.model.impl.HomeworkImpl</code>.
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
	 * Returns the homework type of this homework.
	 *
	 * @return the homework type of this homework
	 */
	public int getHomeworkType();

	/**
	 * Sets the homework type of this homework.
	 *
	 * @param homeworkType the homework type of this homework
	 */
	public void setHomeworkType(int homeworkType);

	/**
	 * Returns the course ID of this homework.
	 *
	 * @return the course ID of this homework
	 */
	public long getCourseId();

	/**
	 * Sets the course ID of this homework.
	 *
	 * @param courseId the course ID of this homework
	 */
	public void setCourseId(long courseId);

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
	 * Returns the title of this homework.
	 *
	 * @return the title of this homework
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this homework.
	 *
	 * @param title the title of this homework
	 */
	public void setTitle(String title);

	/**
	 * Returns the modification date of this homework.
	 *
	 * @return the modification date of this homework
	 */
	public Date getModificationDate();

	/**
	 * Sets the modification date of this homework.
	 *
	 * @param modificationDate the modification date of this homework
	 */
	public void setModificationDate(Date modificationDate);

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

	/**
	 * Returns the estimated time of this homework.
	 *
	 * @return the estimated time of this homework
	 */
	public int getEstimatedTime();

	/**
	 * Sets the estimated time of this homework.
	 *
	 * @param estimatedTime the estimated time of this homework
	 */
	public void setEstimatedTime(int estimatedTime);

	/**
	 * Returns the publication date of this homework.
	 *
	 * @return the publication date of this homework
	 */
	public Date getPublicationDate();

	/**
	 * Sets the publication date of this homework.
	 *
	 * @param publicationDate the publication date of this homework
	 */
	public void setPublicationDate(Date publicationDate);

	/**
	 * Returns the is draft of this homework.
	 *
	 * @return the is draft of this homework
	 */
	public boolean getIsDraft();

	/**
	 * Returns <code>true</code> if this homework is is draft.
	 *
	 * @return <code>true</code> if this homework is is draft; <code>false</code> otherwise
	 */
	public boolean isIsDraft();

	/**
	 * Sets whether this homework is is draft.
	 *
	 * @param isDraft the is draft of this homework
	 */
	public void setIsDraft(boolean isDraft);

	/**
	 * Returns the is correction sent of this homework.
	 *
	 * @return the is correction sent of this homework
	 */
	public boolean getIsCorrectionSent();

	/**
	 * Returns <code>true</code> if this homework is is correction sent.
	 *
	 * @return <code>true</code> if this homework is is correction sent; <code>false</code> otherwise
	 */
	public boolean isIsCorrectionSent();

	/**
	 * Sets whether this homework is is correction sent.
	 *
	 * @param isCorrectionSent the is correction sent of this homework
	 */
	public void setIsCorrectionSent(boolean isCorrectionSent);

	@Override
	public Homework cloneWithOriginalValues();

}