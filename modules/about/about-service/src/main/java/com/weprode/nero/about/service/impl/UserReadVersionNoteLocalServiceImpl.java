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

import com.weprode.nero.about.exception.NoSuchUserReadVersionNoteException;
import com.weprode.nero.about.model.UserReadVersionNote;
import com.weprode.nero.about.service.base.UserReadVersionNoteLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.weprode.nero.about.model.UserReadVersionNote",
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