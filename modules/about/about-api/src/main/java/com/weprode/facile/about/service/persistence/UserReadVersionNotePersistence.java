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

package com.weprode.facile.about.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.about.exception.NoSuchUserReadVersionNoteException;
import com.weprode.facile.about.model.UserReadVersionNote;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the user read version note service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserReadVersionNoteUtil
 * @generated
 */
@ProviderType
public interface UserReadVersionNotePersistence
	extends BasePersistence<UserReadVersionNote> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserReadVersionNoteUtil} to access the user read version note persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the user read version notes where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @return the matching user read version notes
	 */
	public java.util.List<UserReadVersionNote> findByhasReadLastVersionNote(
		boolean hasReadLastVersionNote);

	/**
	 * Returns a range of all the user read version notes where hasReadLastVersionNote = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @return the range of matching user read version notes
	 */
	public java.util.List<UserReadVersionNote> findByhasReadLastVersionNote(
		boolean hasReadLastVersionNote, int start, int end);

	/**
	 * Returns an ordered range of all the user read version notes where hasReadLastVersionNote = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user read version notes
	 */
	public java.util.List<UserReadVersionNote> findByhasReadLastVersionNote(
		boolean hasReadLastVersionNote, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserReadVersionNote>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user read version notes where hasReadLastVersionNote = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user read version notes
	 */
	public java.util.List<UserReadVersionNote> findByhasReadLastVersionNote(
		boolean hasReadLastVersionNote, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserReadVersionNote>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user read version note
	 * @throws NoSuchUserReadVersionNoteException if a matching user read version note could not be found
	 */
	public UserReadVersionNote findByhasReadLastVersionNote_First(
			boolean hasReadLastVersionNote,
			com.liferay.portal.kernel.util.OrderByComparator
				<UserReadVersionNote> orderByComparator)
		throws NoSuchUserReadVersionNoteException;

	/**
	 * Returns the first user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user read version note, or <code>null</code> if a matching user read version note could not be found
	 */
	public UserReadVersionNote fetchByhasReadLastVersionNote_First(
		boolean hasReadLastVersionNote,
		com.liferay.portal.kernel.util.OrderByComparator<UserReadVersionNote>
			orderByComparator);

	/**
	 * Returns the last user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user read version note
	 * @throws NoSuchUserReadVersionNoteException if a matching user read version note could not be found
	 */
	public UserReadVersionNote findByhasReadLastVersionNote_Last(
			boolean hasReadLastVersionNote,
			com.liferay.portal.kernel.util.OrderByComparator
				<UserReadVersionNote> orderByComparator)
		throws NoSuchUserReadVersionNoteException;

	/**
	 * Returns the last user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user read version note, or <code>null</code> if a matching user read version note could not be found
	 */
	public UserReadVersionNote fetchByhasReadLastVersionNote_Last(
		boolean hasReadLastVersionNote,
		com.liferay.portal.kernel.util.OrderByComparator<UserReadVersionNote>
			orderByComparator);

	/**
	 * Returns the user read version notes before and after the current user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param userId the primary key of the current user read version note
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user read version note
	 * @throws NoSuchUserReadVersionNoteException if a user read version note with the primary key could not be found
	 */
	public UserReadVersionNote[] findByhasReadLastVersionNote_PrevAndNext(
			long userId, boolean hasReadLastVersionNote,
			com.liferay.portal.kernel.util.OrderByComparator
				<UserReadVersionNote> orderByComparator)
		throws NoSuchUserReadVersionNoteException;

	/**
	 * Removes all the user read version notes where hasReadLastVersionNote = &#63; from the database.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 */
	public void removeByhasReadLastVersionNote(boolean hasReadLastVersionNote);

	/**
	 * Returns the number of user read version notes where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @return the number of matching user read version notes
	 */
	public int countByhasReadLastVersionNote(boolean hasReadLastVersionNote);

	/**
	 * Caches the user read version note in the entity cache if it is enabled.
	 *
	 * @param userReadVersionNote the user read version note
	 */
	public void cacheResult(UserReadVersionNote userReadVersionNote);

	/**
	 * Caches the user read version notes in the entity cache if it is enabled.
	 *
	 * @param userReadVersionNotes the user read version notes
	 */
	public void cacheResult(
		java.util.List<UserReadVersionNote> userReadVersionNotes);

	/**
	 * Creates a new user read version note with the primary key. Does not add the user read version note to the database.
	 *
	 * @param userId the primary key for the new user read version note
	 * @return the new user read version note
	 */
	public UserReadVersionNote create(long userId);

	/**
	 * Removes the user read version note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the user read version note
	 * @return the user read version note that was removed
	 * @throws NoSuchUserReadVersionNoteException if a user read version note with the primary key could not be found
	 */
	public UserReadVersionNote remove(long userId)
		throws NoSuchUserReadVersionNoteException;

	public UserReadVersionNote updateImpl(
		UserReadVersionNote userReadVersionNote);

	/**
	 * Returns the user read version note with the primary key or throws a <code>NoSuchUserReadVersionNoteException</code> if it could not be found.
	 *
	 * @param userId the primary key of the user read version note
	 * @return the user read version note
	 * @throws NoSuchUserReadVersionNoteException if a user read version note with the primary key could not be found
	 */
	public UserReadVersionNote findByPrimaryKey(long userId)
		throws NoSuchUserReadVersionNoteException;

	/**
	 * Returns the user read version note with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the user read version note
	 * @return the user read version note, or <code>null</code> if a user read version note with the primary key could not be found
	 */
	public UserReadVersionNote fetchByPrimaryKey(long userId);

	/**
	 * Returns all the user read version notes.
	 *
	 * @return the user read version notes
	 */
	public java.util.List<UserReadVersionNote> findAll();

	/**
	 * Returns a range of all the user read version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @return the range of user read version notes
	 */
	public java.util.List<UserReadVersionNote> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the user read version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user read version notes
	 */
	public java.util.List<UserReadVersionNote> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserReadVersionNote>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user read version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserReadVersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user read version notes
	 * @param end the upper bound of the range of user read version notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user read version notes
	 */
	public java.util.List<UserReadVersionNote> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserReadVersionNote>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the user read version notes from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of user read version notes.
	 *
	 * @return the number of user read version notes
	 */
	public int countAll();

}