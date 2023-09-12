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

package com.weprode.nero.about.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.about.model.UserReadVersionNote;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the user read version note service. This utility wraps <code>com.weprode.nero.about.service.persistence.impl.UserReadVersionNotePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserReadVersionNotePersistence
 * @generated
 */
public class UserReadVersionNoteUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(UserReadVersionNote userReadVersionNote) {
		getPersistence().clearCache(userReadVersionNote);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, UserReadVersionNote> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserReadVersionNote> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserReadVersionNote> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserReadVersionNote> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserReadVersionNote> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserReadVersionNote update(
		UserReadVersionNote userReadVersionNote) {

		return getPersistence().update(userReadVersionNote);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserReadVersionNote update(
		UserReadVersionNote userReadVersionNote,
		ServiceContext serviceContext) {

		return getPersistence().update(userReadVersionNote, serviceContext);
	}

	/**
	 * Returns all the user read version notes where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @return the matching user read version notes
	 */
	public static List<UserReadVersionNote> findByhasReadLastVersionNote(
		boolean hasReadLastVersionNote) {

		return getPersistence().findByhasReadLastVersionNote(
			hasReadLastVersionNote);
	}

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
	public static List<UserReadVersionNote> findByhasReadLastVersionNote(
		boolean hasReadLastVersionNote, int start, int end) {

		return getPersistence().findByhasReadLastVersionNote(
			hasReadLastVersionNote, start, end);
	}

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
	public static List<UserReadVersionNote> findByhasReadLastVersionNote(
		boolean hasReadLastVersionNote, int start, int end,
		OrderByComparator<UserReadVersionNote> orderByComparator) {

		return getPersistence().findByhasReadLastVersionNote(
			hasReadLastVersionNote, start, end, orderByComparator);
	}

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
	public static List<UserReadVersionNote> findByhasReadLastVersionNote(
		boolean hasReadLastVersionNote, int start, int end,
		OrderByComparator<UserReadVersionNote> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByhasReadLastVersionNote(
			hasReadLastVersionNote, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user read version note
	 * @throws NoSuchUserReadVersionNoteException if a matching user read version note could not be found
	 */
	public static UserReadVersionNote findByhasReadLastVersionNote_First(
			boolean hasReadLastVersionNote,
			OrderByComparator<UserReadVersionNote> orderByComparator)
		throws com.weprode.nero.about.exception.
			NoSuchUserReadVersionNoteException {

		return getPersistence().findByhasReadLastVersionNote_First(
			hasReadLastVersionNote, orderByComparator);
	}

	/**
	 * Returns the first user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user read version note, or <code>null</code> if a matching user read version note could not be found
	 */
	public static UserReadVersionNote fetchByhasReadLastVersionNote_First(
		boolean hasReadLastVersionNote,
		OrderByComparator<UserReadVersionNote> orderByComparator) {

		return getPersistence().fetchByhasReadLastVersionNote_First(
			hasReadLastVersionNote, orderByComparator);
	}

	/**
	 * Returns the last user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user read version note
	 * @throws NoSuchUserReadVersionNoteException if a matching user read version note could not be found
	 */
	public static UserReadVersionNote findByhasReadLastVersionNote_Last(
			boolean hasReadLastVersionNote,
			OrderByComparator<UserReadVersionNote> orderByComparator)
		throws com.weprode.nero.about.exception.
			NoSuchUserReadVersionNoteException {

		return getPersistence().findByhasReadLastVersionNote_Last(
			hasReadLastVersionNote, orderByComparator);
	}

	/**
	 * Returns the last user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user read version note, or <code>null</code> if a matching user read version note could not be found
	 */
	public static UserReadVersionNote fetchByhasReadLastVersionNote_Last(
		boolean hasReadLastVersionNote,
		OrderByComparator<UserReadVersionNote> orderByComparator) {

		return getPersistence().fetchByhasReadLastVersionNote_Last(
			hasReadLastVersionNote, orderByComparator);
	}

	/**
	 * Returns the user read version notes before and after the current user read version note in the ordered set where hasReadLastVersionNote = &#63;.
	 *
	 * @param userId the primary key of the current user read version note
	 * @param hasReadLastVersionNote the has read last version note
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user read version note
	 * @throws NoSuchUserReadVersionNoteException if a user read version note with the primary key could not be found
	 */
	public static UserReadVersionNote[]
			findByhasReadLastVersionNote_PrevAndNext(
				long userId, boolean hasReadLastVersionNote,
				OrderByComparator<UserReadVersionNote> orderByComparator)
		throws com.weprode.nero.about.exception.
			NoSuchUserReadVersionNoteException {

		return getPersistence().findByhasReadLastVersionNote_PrevAndNext(
			userId, hasReadLastVersionNote, orderByComparator);
	}

	/**
	 * Removes all the user read version notes where hasReadLastVersionNote = &#63; from the database.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 */
	public static void removeByhasReadLastVersionNote(
		boolean hasReadLastVersionNote) {

		getPersistence().removeByhasReadLastVersionNote(hasReadLastVersionNote);
	}

	/**
	 * Returns the number of user read version notes where hasReadLastVersionNote = &#63;.
	 *
	 * @param hasReadLastVersionNote the has read last version note
	 * @return the number of matching user read version notes
	 */
	public static int countByhasReadLastVersionNote(
		boolean hasReadLastVersionNote) {

		return getPersistence().countByhasReadLastVersionNote(
			hasReadLastVersionNote);
	}

	/**
	 * Caches the user read version note in the entity cache if it is enabled.
	 *
	 * @param userReadVersionNote the user read version note
	 */
	public static void cacheResult(UserReadVersionNote userReadVersionNote) {
		getPersistence().cacheResult(userReadVersionNote);
	}

	/**
	 * Caches the user read version notes in the entity cache if it is enabled.
	 *
	 * @param userReadVersionNotes the user read version notes
	 */
	public static void cacheResult(
		List<UserReadVersionNote> userReadVersionNotes) {

		getPersistence().cacheResult(userReadVersionNotes);
	}

	/**
	 * Creates a new user read version note with the primary key. Does not add the user read version note to the database.
	 *
	 * @param userId the primary key for the new user read version note
	 * @return the new user read version note
	 */
	public static UserReadVersionNote create(long userId) {
		return getPersistence().create(userId);
	}

	/**
	 * Removes the user read version note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the user read version note
	 * @return the user read version note that was removed
	 * @throws NoSuchUserReadVersionNoteException if a user read version note with the primary key could not be found
	 */
	public static UserReadVersionNote remove(long userId)
		throws com.weprode.nero.about.exception.
			NoSuchUserReadVersionNoteException {

		return getPersistence().remove(userId);
	}

	public static UserReadVersionNote updateImpl(
		UserReadVersionNote userReadVersionNote) {

		return getPersistence().updateImpl(userReadVersionNote);
	}

	/**
	 * Returns the user read version note with the primary key or throws a <code>NoSuchUserReadVersionNoteException</code> if it could not be found.
	 *
	 * @param userId the primary key of the user read version note
	 * @return the user read version note
	 * @throws NoSuchUserReadVersionNoteException if a user read version note with the primary key could not be found
	 */
	public static UserReadVersionNote findByPrimaryKey(long userId)
		throws com.weprode.nero.about.exception.
			NoSuchUserReadVersionNoteException {

		return getPersistence().findByPrimaryKey(userId);
	}

	/**
	 * Returns the user read version note with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the user read version note
	 * @return the user read version note, or <code>null</code> if a user read version note with the primary key could not be found
	 */
	public static UserReadVersionNote fetchByPrimaryKey(long userId) {
		return getPersistence().fetchByPrimaryKey(userId);
	}

	/**
	 * Returns all the user read version notes.
	 *
	 * @return the user read version notes
	 */
	public static List<UserReadVersionNote> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<UserReadVersionNote> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<UserReadVersionNote> findAll(
		int start, int end,
		OrderByComparator<UserReadVersionNote> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<UserReadVersionNote> findAll(
		int start, int end,
		OrderByComparator<UserReadVersionNote> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the user read version notes from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of user read version notes.
	 *
	 * @return the number of user read version notes
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserReadVersionNotePersistence getPersistence() {
		return _persistence;
	}

	private static volatile UserReadVersionNotePersistence _persistence;

}