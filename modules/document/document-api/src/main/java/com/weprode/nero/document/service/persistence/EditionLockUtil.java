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

package com.weprode.nero.document.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.document.model.EditionLock;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the edition lock service. This utility wraps <code>com.weprode.nero.document.service.persistence.impl.EditionLockPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EditionLockPersistence
 * @generated
 */
public class EditionLockUtil {

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
	public static void clearCache(EditionLock editionLock) {
		getPersistence().clearCache(editionLock);
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
	public static Map<Serializable, EditionLock> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EditionLock> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EditionLock> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EditionLock> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EditionLock> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EditionLock update(EditionLock editionLock) {
		return getPersistence().update(editionLock);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EditionLock update(
		EditionLock editionLock, ServiceContext serviceContext) {

		return getPersistence().update(editionLock, serviceContext);
	}

	/**
	 * Returns the edition lock where fileId = &#63; and userId = &#63; or throws a <code>NoSuchEditionLockException</code> if it could not be found.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @return the matching edition lock
	 * @throws NoSuchEditionLockException if a matching edition lock could not be found
	 */
	public static EditionLock findByfileId_userId(long fileId, long userId)
		throws com.weprode.nero.document.exception.NoSuchEditionLockException {

		return getPersistence().findByfileId_userId(fileId, userId);
	}

	/**
	 * Returns the edition lock where fileId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @return the matching edition lock, or <code>null</code> if a matching edition lock could not be found
	 */
	public static EditionLock fetchByfileId_userId(long fileId, long userId) {
		return getPersistence().fetchByfileId_userId(fileId, userId);
	}

	/**
	 * Returns the edition lock where fileId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching edition lock, or <code>null</code> if a matching edition lock could not be found
	 */
	public static EditionLock fetchByfileId_userId(
		long fileId, long userId, boolean useFinderCache) {

		return getPersistence().fetchByfileId_userId(
			fileId, userId, useFinderCache);
	}

	/**
	 * Removes the edition lock where fileId = &#63; and userId = &#63; from the database.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @return the edition lock that was removed
	 */
	public static EditionLock removeByfileId_userId(long fileId, long userId)
		throws com.weprode.nero.document.exception.NoSuchEditionLockException {

		return getPersistence().removeByfileId_userId(fileId, userId);
	}

	/**
	 * Returns the number of edition locks where fileId = &#63; and userId = &#63;.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @return the number of matching edition locks
	 */
	public static int countByfileId_userId(long fileId, long userId) {
		return getPersistence().countByfileId_userId(fileId, userId);
	}

	/**
	 * Caches the edition lock in the entity cache if it is enabled.
	 *
	 * @param editionLock the edition lock
	 */
	public static void cacheResult(EditionLock editionLock) {
		getPersistence().cacheResult(editionLock);
	}

	/**
	 * Caches the edition locks in the entity cache if it is enabled.
	 *
	 * @param editionLocks the edition locks
	 */
	public static void cacheResult(List<EditionLock> editionLocks) {
		getPersistence().cacheResult(editionLocks);
	}

	/**
	 * Creates a new edition lock with the primary key. Does not add the edition lock to the database.
	 *
	 * @param fileId the primary key for the new edition lock
	 * @return the new edition lock
	 */
	public static EditionLock create(long fileId) {
		return getPersistence().create(fileId);
	}

	/**
	 * Removes the edition lock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileId the primary key of the edition lock
	 * @return the edition lock that was removed
	 * @throws NoSuchEditionLockException if a edition lock with the primary key could not be found
	 */
	public static EditionLock remove(long fileId)
		throws com.weprode.nero.document.exception.NoSuchEditionLockException {

		return getPersistence().remove(fileId);
	}

	public static EditionLock updateImpl(EditionLock editionLock) {
		return getPersistence().updateImpl(editionLock);
	}

	/**
	 * Returns the edition lock with the primary key or throws a <code>NoSuchEditionLockException</code> if it could not be found.
	 *
	 * @param fileId the primary key of the edition lock
	 * @return the edition lock
	 * @throws NoSuchEditionLockException if a edition lock with the primary key could not be found
	 */
	public static EditionLock findByPrimaryKey(long fileId)
		throws com.weprode.nero.document.exception.NoSuchEditionLockException {

		return getPersistence().findByPrimaryKey(fileId);
	}

	/**
	 * Returns the edition lock with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileId the primary key of the edition lock
	 * @return the edition lock, or <code>null</code> if a edition lock with the primary key could not be found
	 */
	public static EditionLock fetchByPrimaryKey(long fileId) {
		return getPersistence().fetchByPrimaryKey(fileId);
	}

	/**
	 * Returns all the edition locks.
	 *
	 * @return the edition locks
	 */
	public static List<EditionLock> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the edition locks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionLockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of edition locks
	 * @param end the upper bound of the range of edition locks (not inclusive)
	 * @return the range of edition locks
	 */
	public static List<EditionLock> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the edition locks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionLockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of edition locks
	 * @param end the upper bound of the range of edition locks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of edition locks
	 */
	public static List<EditionLock> findAll(
		int start, int end, OrderByComparator<EditionLock> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the edition locks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionLockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of edition locks
	 * @param end the upper bound of the range of edition locks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of edition locks
	 */
	public static List<EditionLock> findAll(
		int start, int end, OrderByComparator<EditionLock> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the edition locks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of edition locks.
	 *
	 * @return the number of edition locks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EditionLockPersistence getPersistence() {
		return _persistence;
	}

	private static volatile EditionLockPersistence _persistence;

}