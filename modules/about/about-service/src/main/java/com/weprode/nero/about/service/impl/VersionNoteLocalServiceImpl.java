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

package com.weprode.nero.about.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.weprode.nero.about.exception.NoSuchEntVersionException;
import com.weprode.nero.about.exception.NoSuchVersionNoteException;
import com.weprode.nero.about.model.EntVersion;
import com.weprode.nero.about.model.VersionNote;
import com.weprode.nero.about.service.EntVersionLocalServiceUtil;
import com.weprode.nero.about.service.VersionNoteLocalServiceUtil;
import com.weprode.nero.about.service.base.VersionNoteLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.nero.about.model.VersionNote",
	service = AopService.class
)
public class VersionNoteLocalServiceImpl extends VersionNoteLocalServiceBaseImpl {

	private static final Log logger = LogFactoryUtil.getLog(VersionNoteLocalServiceImpl.class);

	/**
	 * Get all ENT versions sorted by date (the most recent un first position)
	 */
	public List<VersionNote> getSortedVersionNotes() throws SystemException {
		List<VersionNote> versionNotesList = new ArrayList<>(VersionNoteLocalServiceUtil.getVersionNotes(QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		versionNotesList.sort((version1, version2) -> Long.compare(version2.getVersionNoteDate().getTime(), version1.getVersionNoteDate().getTime()));

		return versionNotesList;
	}

	/**
	 * Get last ENT version
	 */
	public VersionNote getLastVersionNote() {
		List<VersionNote> versionNotesList = getSortedVersionNotes();
		if (versionNotesList.size() > 0) {
			return versionNotesList.get(0);
		} else {
			logger.debug("No versionNote to fetch");
			return null;
		}
	}

	public boolean isLastVersionNote (VersionNote versionNote) {
		VersionNote lastVersionNote = getLastVersionNote();
		if (lastVersionNote != null) {
			return lastVersionNote.getVersionNoteId() == versionNote.getVersionNoteId();
		} else {
			return false;
		}
	}

	/**
	 * Add new ENT version with today date
	 */
	public VersionNote addVersionNote(String title, String content) throws SystemException {
		return addVersionNote(title, content, new Date());
	}

	/**
	 * Add new ENT version
	 */
	public VersionNote addVersionNote(String title, String content, Date versionDate) throws SystemException {

		long versionNoteId = counterLocalService.increment();
		VersionNote versionNote = versionNotePersistence.create(versionNoteId);

		versionNote.setTitle(title);
		versionNote.setContent(content);
		versionNote.setVersionNoteDate(versionDate);

		return versionNotePersistence.update(versionNote);
	}

	public VersionNote updateVersionNote(long versionNoteId, String title, String content) throws SystemException, NoSuchVersionNoteException {

		VersionNote entVersion = versionNotePersistence.findByPrimaryKey(versionNoteId);

		entVersion.setTitle(title);
		entVersion.setContent(content);

		return versionNotePersistence.update(entVersion);
	}
}