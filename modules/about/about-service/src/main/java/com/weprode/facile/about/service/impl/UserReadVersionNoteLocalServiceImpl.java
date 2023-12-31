/**
 * Copyright (c) 2022-present - Weprode
 * This file is part of FACILE ENT Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License (LGPL) as
 * published by the Free Software Foundation (version 2.1 of the License).
 * See LICENCE.txt file
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 */

package com.weprode.facile.about.service.impl;

import com.liferay.portal.aop.AopService;

import com.weprode.facile.about.exception.NoSuchUserReadVersionNoteException;
import com.weprode.facile.about.model.UserReadVersionNote;
import com.weprode.facile.about.service.base.UserReadVersionNoteLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.facile.about.model.UserReadVersionNote",
	service = AopService.class
)
public class UserReadVersionNoteLocalServiceImpl extends UserReadVersionNoteLocalServiceBaseImpl {

	public void addNewVersionNote () {
		// Set hasReadLastVersionNote to false for all
		List<UserReadVersionNote> userReadVersionNoteList = userReadVersionNotePersistence.findAll();

		for (UserReadVersionNote userReadVersionNote : userReadVersionNoteList) {
			userReadVersionNote.setHasReadLastVersionNote(false);
			userReadVersionNotePersistence.update(userReadVersionNote);
		}
	}

	public boolean hasReadLastVersionNote (long userId) {
		UserReadVersionNote userReadVersionNote;
		try {
			if (versionNotePersistence.countAll() == 0) {
				// No version
				return true;
			}
			userReadVersionNote = userReadVersionNotePersistence.findByPrimaryKey(userId);
		} catch (NoSuchUserReadVersionNoteException e) {
			return false; // if user doesn't exist in this table, he has not read the last version note
		}
		return userReadVersionNote.getHasReadLastVersionNote();
	}

	public void setLastVersionNoteAsReadForUser (long userId) {
		UserReadVersionNote userReadVersionNote;
		try {
			userReadVersionNote = userReadVersionNotePersistence.findByPrimaryKey(userId);
		} catch (NoSuchUserReadVersionNoteException e) {
			userReadVersionNote = userReadVersionNotePersistence.create(userId);
		}

		userReadVersionNote.setHasReadLastVersionNote(true);
		userReadVersionNotePersistence.update(userReadVersionNote);
	}

	// Useful if we delete the last version, and we don't want notification for the previous one
	public void markAsReadForAllRegisteredUsers () {
		List<UserReadVersionNote> unReadUserList = userReadVersionNotePersistence.findByhasReadLastVersionNote(false);

		for (UserReadVersionNote userReadVersionNote : unReadUserList) {
			userReadVersionNote.setHasReadLastVersionNote(true);
			userReadVersionNotePersistence.update(userReadVersionNote);
		}
	}
}