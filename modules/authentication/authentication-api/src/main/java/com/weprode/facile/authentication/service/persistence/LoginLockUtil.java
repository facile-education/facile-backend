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

package com.weprode.facile.authentication.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.authentication.model.LoginLock;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the login lock service. This utility wraps <code>com.weprode.facile.authentication.service.persistence.impl.LoginLockPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoginLockPersistence
 * @generated
 */
public class LoginLockUtil {

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
	public static void clearCache(LoginLock loginLock) {
		getPersistence().clearCache(loginLock);
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
	public static Map<Serializable, LoginLock> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoginLock> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoginLock> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoginLock> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoginLock> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoginLock update(LoginLock loginLock) {
		return getPersistence().update(loginLock);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoginLock update(
		LoginLock loginLock, ServiceContext serviceContext) {

		return getPersistence().update(loginLock, serviceContext);
	}

	/**
	 * Caches the login lock in the entity cache if it is enabled.
	 *
	 * @param loginLock the login lock
	 */
	public static void cacheResult(LoginLock loginLock) {
		getPersistence().cacheResult(loginLock);
	}

	/**
	 * Caches the login locks in the entity cache if it is enabled.
	 *
	 * @param loginLocks the login locks
	 */
	public static void cacheResult(List<LoginLock> loginLocks) {
		getPersistence().cacheResult(loginLocks);
	}

	/**
	 * Creates a new login lock with the primary key. Does not add the login lock to the database.
	 *
	 * @param login the primary key for the new login lock
	 * @return the new login lock
	 */
	public static LoginLock create(String login) {
		return getPersistence().create(login);
	}

	/**
	 * Removes the login lock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param login the primary key of the login lock
	 * @return the login lock that was removed
	 * @throws NoSuchLoginLockException if a login lock with the primary key could not be found
	 */
	public static LoginLock remove(String login)
		throws com.weprode.facile.authentication.exception.
			NoSuchLoginLockException {

		return getPersistence().remove(login);
	}

	public static LoginLock updateImpl(LoginLock loginLock) {
		return getPersistence().updateImpl(loginLock);
	}

	/**
	 * Returns the login lock with the primary key or throws a <code>NoSuchLoginLockException</code> if it could not be found.
	 *
	 * @param login the primary key of the login lock
	 * @return the login lock
	 * @throws NoSuchLoginLockException if a login lock with the primary key could not be found
	 */
	public static LoginLock findByPrimaryKey(String login)
		throws com.weprode.facile.authentication.exception.
			NoSuchLoginLockException {

		return getPersistence().findByPrimaryKey(login);
	}

	/**
	 * Returns the login lock with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param login the primary key of the login lock
	 * @return the login lock, or <code>null</code> if a login lock with the primary key could not be found
	 */
	public static LoginLock fetchByPrimaryKey(String login) {
		return getPersistence().fetchByPrimaryKey(login);
	}

	/**
	 * Returns all the login locks.
	 *
	 * @return the login locks
	 */
	public static List<LoginLock> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the login locks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoginLockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of login locks
	 * @param end the upper bound of the range of login locks (not inclusive)
	 * @return the range of login locks
	 */
	public static List<LoginLock> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the login locks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoginLockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of login locks
	 * @param end the upper bound of the range of login locks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of login locks
	 */
	public static List<LoginLock> findAll(
		int start, int end, OrderByComparator<LoginLock> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the login locks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoginLockModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of login locks
	 * @param end the upper bound of the range of login locks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of login locks
	 */
	public static List<LoginLock> findAll(
		int start, int end, OrderByComparator<LoginLock> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the login locks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of login locks.
	 *
	 * @return the number of login locks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoginLockPersistence getPersistence() {
		return _persistence;
	}

	private static volatile LoginLockPersistence _persistence;

}