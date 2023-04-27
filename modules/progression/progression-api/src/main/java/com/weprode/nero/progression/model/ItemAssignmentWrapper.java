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

package com.weprode.nero.progression.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ItemAssignment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ItemAssignment
 * @generated
 */
public class ItemAssignmentWrapper
	extends BaseModelWrapper<ItemAssignment>
	implements ItemAssignment, ModelWrapper<ItemAssignment> {

	public ItemAssignmentWrapper(ItemAssignment itemAssignment) {
		super(itemAssignment);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("progressionItemId", getProgressionItemId());
		attributes.put("sessionId", getSessionId());
		attributes.put("homeworkId", getHomeworkId());
		attributes.put("assignedDate", getAssignedDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long progressionItemId = (Long)attributes.get("progressionItemId");

		if (progressionItemId != null) {
			setProgressionItemId(progressionItemId);
		}

		Long sessionId = (Long)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Long homeworkId = (Long)attributes.get("homeworkId");

		if (homeworkId != null) {
			setHomeworkId(homeworkId);
		}

		Date assignedDate = (Date)attributes.get("assignedDate");

		if (assignedDate != null) {
			setAssignedDate(assignedDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public ItemAssignment cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject convertToJSON(
		long userId) {

		return model.convertToJSON(userId);
	}

	/**
	 * Returns the assigned date of this item assignment.
	 *
	 * @return the assigned date of this item assignment
	 */
	@Override
	public Date getAssignedDate() {
		return model.getAssignedDate();
	}

	/**
	 * Returns the homework ID of this item assignment.
	 *
	 * @return the homework ID of this item assignment
	 */
	@Override
	public long getHomeworkId() {
		return model.getHomeworkId();
	}

	/**
	 * Returns the modified date of this item assignment.
	 *
	 * @return the modified date of this item assignment
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this item assignment.
	 *
	 * @return the primary key of this item assignment
	 */
	@Override
	public com.weprode.nero.progression.service.persistence.ItemAssignmentPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the progression item ID of this item assignment.
	 *
	 * @return the progression item ID of this item assignment
	 */
	@Override
	public long getProgressionItemId() {
		return model.getProgressionItemId();
	}

	/**
	 * Returns the session ID of this item assignment.
	 *
	 * @return the session ID of this item assignment
	 */
	@Override
	public long getSessionId() {
		return model.getSessionId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the assigned date of this item assignment.
	 *
	 * @param assignedDate the assigned date of this item assignment
	 */
	@Override
	public void setAssignedDate(Date assignedDate) {
		model.setAssignedDate(assignedDate);
	}

	/**
	 * Sets the homework ID of this item assignment.
	 *
	 * @param homeworkId the homework ID of this item assignment
	 */
	@Override
	public void setHomeworkId(long homeworkId) {
		model.setHomeworkId(homeworkId);
	}

	/**
	 * Sets the modified date of this item assignment.
	 *
	 * @param modifiedDate the modified date of this item assignment
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this item assignment.
	 *
	 * @param primaryKey the primary key of this item assignment
	 */
	@Override
	public void setPrimaryKey(
		com.weprode.nero.progression.service.persistence.ItemAssignmentPK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the progression item ID of this item assignment.
	 *
	 * @param progressionItemId the progression item ID of this item assignment
	 */
	@Override
	public void setProgressionItemId(long progressionItemId) {
		model.setProgressionItemId(progressionItemId);
	}

	/**
	 * Sets the session ID of this item assignment.
	 *
	 * @param sessionId the session ID of this item assignment
	 */
	@Override
	public void setSessionId(long sessionId) {
		model.setSessionId(sessionId);
	}

	@Override
	protected ItemAssignmentWrapper wrap(ItemAssignment itemAssignment) {
		return new ItemAssignmentWrapper(itemAssignment);
	}

}