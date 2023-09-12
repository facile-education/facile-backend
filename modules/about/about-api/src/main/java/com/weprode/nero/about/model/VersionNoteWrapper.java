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

package com.weprode.nero.about.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VersionNote}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VersionNote
 * @generated
 */
public class VersionNoteWrapper
	extends BaseModelWrapper<VersionNote>
	implements ModelWrapper<VersionNote>, VersionNote {

	public VersionNoteWrapper(VersionNote versionNote) {
		super(versionNote);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("versionNoteId", getVersionNoteId());
		attributes.put("title", getTitle());
		attributes.put("content", getContent());
		attributes.put("versionNoteDate", getVersionNoteDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long versionNoteId = (Long)attributes.get("versionNoteId");

		if (versionNoteId != null) {
			setVersionNoteId(versionNoteId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Date versionNoteDate = (Date)attributes.get("versionNoteDate");

		if (versionNoteDate != null) {
			setVersionNoteDate(versionNoteDate);
		}
	}

	@Override
	public VersionNote cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the content of this version note.
	 *
	 * @return the content of this version note
	 */
	@Override
	public String getContent() {
		return model.getContent();
	}

	/**
	 * Returns the primary key of this version note.
	 *
	 * @return the primary key of this version note
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the title of this version note.
	 *
	 * @return the title of this version note
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the version note date of this version note.
	 *
	 * @return the version note date of this version note
	 */
	@Override
	public Date getVersionNoteDate() {
		return model.getVersionNoteDate();
	}

	/**
	 * Returns the version note ID of this version note.
	 *
	 * @return the version note ID of this version note
	 */
	@Override
	public long getVersionNoteId() {
		return model.getVersionNoteId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the content of this version note.
	 *
	 * @param content the content of this version note
	 */
	@Override
	public void setContent(String content) {
		model.setContent(content);
	}

	/**
	 * Sets the primary key of this version note.
	 *
	 * @param primaryKey the primary key of this version note
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the title of this version note.
	 *
	 * @param title the title of this version note
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the version note date of this version note.
	 *
	 * @param versionNoteDate the version note date of this version note
	 */
	@Override
	public void setVersionNoteDate(Date versionNoteDate) {
		model.setVersionNoteDate(versionNoteDate);
	}

	/**
	 * Sets the version note ID of this version note.
	 *
	 * @param versionNoteId the version note ID of this version note
	 */
	@Override
	public void setVersionNoteId(long versionNoteId) {
		model.setVersionNoteId(versionNoteId);
	}

	@Override
	protected VersionNoteWrapper wrap(VersionNote versionNote) {
		return new VersionNoteWrapper(versionNote);
	}

}