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

package com.weprode.nero.course.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SessionContent}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionContent
 * @generated
 */
public class SessionContentWrapper
	extends BaseModelWrapper<SessionContent>
	implements ModelWrapper<SessionContent>, SessionContent {

	public SessionContentWrapper(SessionContent sessionContent) {
		super(sessionContent);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("sessionId", getSessionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("teacherId", getTeacherId());
		attributes.put("title", getTitle());
		attributes.put("modificationDate", getModificationDate());
		attributes.put("publicationDate", getPublicationDate());
		attributes.put("isDraft", isIsDraft());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long sessionId = (Long)attributes.get("sessionId");

		if (sessionId != null) {
			setSessionId(sessionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long teacherId = (Long)attributes.get("teacherId");

		if (teacherId != null) {
			setTeacherId(teacherId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Date modificationDate = (Date)attributes.get("modificationDate");

		if (modificationDate != null) {
			setModificationDate(modificationDate);
		}

		Date publicationDate = (Date)attributes.get("publicationDate");

		if (publicationDate != null) {
			setPublicationDate(publicationDate);
		}

		Boolean isDraft = (Boolean)attributes.get("isDraft");

		if (isDraft != null) {
			setIsDraft(isDraft);
		}
	}

	@Override
	public SessionContent cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public org.json.JSONObject convertToJSON(
		com.liferay.portal.kernel.model.User user, boolean isContentIncluded) {

		return model.convertToJSON(user, isContentIncluded);
	}

	/**
	 * Returns the company ID of this session content.
	 *
	 * @return the company ID of this session content
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the is draft of this session content.
	 *
	 * @return the is draft of this session content
	 */
	@Override
	public boolean getIsDraft() {
		return model.getIsDraft();
	}

	/**
	 * Returns the modification date of this session content.
	 *
	 * @return the modification date of this session content
	 */
	@Override
	public Date getModificationDate() {
		return model.getModificationDate();
	}

	/**
	 * Returns the primary key of this session content.
	 *
	 * @return the primary key of this session content
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the publication date of this session content.
	 *
	 * @return the publication date of this session content
	 */
	@Override
	public Date getPublicationDate() {
		return model.getPublicationDate();
	}

	/**
	 * Returns the session ID of this session content.
	 *
	 * @return the session ID of this session content
	 */
	@Override
	public long getSessionId() {
		return model.getSessionId();
	}

	/**
	 * Returns the teacher ID of this session content.
	 *
	 * @return the teacher ID of this session content
	 */
	@Override
	public long getTeacherId() {
		return model.getTeacherId();
	}

	/**
	 * Returns the title of this session content.
	 *
	 * @return the title of this session content
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns <code>true</code> if this session content is is draft.
	 *
	 * @return <code>true</code> if this session content is is draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsDraft() {
		return model.isIsDraft();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this session content.
	 *
	 * @param companyId the company ID of this session content
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets whether this session content is is draft.
	 *
	 * @param isDraft the is draft of this session content
	 */
	@Override
	public void setIsDraft(boolean isDraft) {
		model.setIsDraft(isDraft);
	}

	/**
	 * Sets the modification date of this session content.
	 *
	 * @param modificationDate the modification date of this session content
	 */
	@Override
	public void setModificationDate(Date modificationDate) {
		model.setModificationDate(modificationDate);
	}

	/**
	 * Sets the primary key of this session content.
	 *
	 * @param primaryKey the primary key of this session content
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the publication date of this session content.
	 *
	 * @param publicationDate the publication date of this session content
	 */
	@Override
	public void setPublicationDate(Date publicationDate) {
		model.setPublicationDate(publicationDate);
	}

	/**
	 * Sets the session ID of this session content.
	 *
	 * @param sessionId the session ID of this session content
	 */
	@Override
	public void setSessionId(long sessionId) {
		model.setSessionId(sessionId);
	}

	/**
	 * Sets the teacher ID of this session content.
	 *
	 * @param teacherId the teacher ID of this session content
	 */
	@Override
	public void setTeacherId(long teacherId) {
		model.setTeacherId(teacherId);
	}

	/**
	 * Sets the title of this session content.
	 *
	 * @param title the title of this session content
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	@Override
	protected SessionContentWrapper wrap(SessionContent sessionContent) {
		return new SessionContentWrapper(sessionContent);
	}

}