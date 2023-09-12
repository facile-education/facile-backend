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

import com.weprode.nero.about.model.VersionNote;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the version note service. This utility wraps <code>com.weprode.nero.about.service.persistence.impl.VersionNotePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VersionNotePersistence
 * @generated
 */
public class VersionNoteUtil {

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
	public static void clearCache(VersionNote versionNote) {
		getPersistence().clearCache(versionNote);
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
	public static Map<Serializable, VersionNote> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<VersionNote> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VersionNote> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VersionNote> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VersionNote> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VersionNote update(VersionNote versionNote) {
		return getPersistence().update(versionNote);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VersionNote update(
		VersionNote versionNote, ServiceContext serviceContext) {

		return getPersistence().update(versionNote, serviceContext);
	}

	/**
	 * Caches the version note in the entity cache if it is enabled.
	 *
	 * @param versionNote the version note
	 */
	public static void cacheResult(VersionNote versionNote) {
		getPersistence().cacheResult(versionNote);
	}

	/**
	 * Caches the version notes in the entity cache if it is enabled.
	 *
	 * @param versionNotes the version notes
	 */
	public static void cacheResult(List<VersionNote> versionNotes) {
		getPersistence().cacheResult(versionNotes);
	}

	/**
	 * Creates a new version note with the primary key. Does not add the version note to the database.
	 *
	 * @param versionNoteId the primary key for the new version note
	 * @return the new version note
	 */
	public static VersionNote create(long versionNoteId) {
		return getPersistence().create(versionNoteId);
	}

	/**
	 * Removes the version note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param versionNoteId the primary key of the version note
	 * @return the version note that was removed
	 * @throws NoSuchVersionNoteException if a version note with the primary key could not be found
	 */
	public static VersionNote remove(long versionNoteId)
		throws com.weprode.nero.about.exception.NoSuchVersionNoteException {

		return getPersistence().remove(versionNoteId);
	}

	public static VersionNote updateImpl(VersionNote versionNote) {
		return getPersistence().updateImpl(versionNote);
	}

	/**
	 * Returns the version note with the primary key or throws a <code>NoSuchVersionNoteException</code> if it could not be found.
	 *
	 * @param versionNoteId the primary key of the version note
	 * @return the version note
	 * @throws NoSuchVersionNoteException if a version note with the primary key could not be found
	 */
	public static VersionNote findByPrimaryKey(long versionNoteId)
		throws com.weprode.nero.about.exception.NoSuchVersionNoteException {

		return getPersistence().findByPrimaryKey(versionNoteId);
	}

	/**
	 * Returns the version note with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param versionNoteId the primary key of the version note
	 * @return the version note, or <code>null</code> if a version note with the primary key could not be found
	 */
	public static VersionNote fetchByPrimaryKey(long versionNoteId) {
		return getPersistence().fetchByPrimaryKey(versionNoteId);
	}

	/**
	 * Returns all the version notes.
	 *
	 * @return the version notes
	 */
	public static List<VersionNote> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of version notes
	 * @param end the upper bound of the range of version notes (not inclusive)
	 * @return the range of version notes
	 */
	public static List<VersionNote> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of version notes
	 * @param end the upper bound of the range of version notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of version notes
	 */
	public static List<VersionNote> findAll(
		int start, int end, OrderByComparator<VersionNote> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the version notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>VersionNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of version notes
	 * @param end the upper bound of the range of version notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of version notes
	 */
	public static List<VersionNote> findAll(
		int start, int end, OrderByComparator<VersionNote> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the version notes from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of version notes.
	 *
	 * @return the number of version notes
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static VersionNotePersistence getPersistence() {
		return _persistence;
	}

	private static volatile VersionNotePersistence _persistence;

}