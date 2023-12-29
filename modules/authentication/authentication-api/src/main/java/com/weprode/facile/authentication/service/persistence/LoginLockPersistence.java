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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.authentication.exception.NoSuchLoginLockException;
import com.weprode.facile.authentication.model.LoginLock;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the login lock service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoginLockUtil
 * @generated
 */
@ProviderType
public interface LoginLockPersistence extends BasePersistence<LoginLock> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoginLockUtil} to access the login lock persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the login lock in the entity cache if it is enabled.
	 *
	 * @param loginLock the login lock
	 */
	public void cacheResult(LoginLock loginLock);

	/**
	 * Caches the login locks in the entity cache if it is enabled.
	 *
	 * @param loginLocks the login locks
	 */
	public void cacheResult(java.util.List<LoginLock> loginLocks);

	/**
	 * Creates a new login lock with the primary key. Does not add the login lock to the database.
	 *
	 * @param login the primary key for the new login lock
	 * @return the new login lock
	 */
	public LoginLock create(String login);

	/**
	 * Removes the login lock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param login the primary key of the login lock
	 * @return the login lock that was removed
	 * @throws NoSuchLoginLockException if a login lock with the primary key could not be found
	 */
	public LoginLock remove(String login) throws NoSuchLoginLockException;

	public LoginLock updateImpl(LoginLock loginLock);

	/**
	 * Returns the login lock with the primary key or throws a <code>NoSuchLoginLockException</code> if it could not be found.
	 *
	 * @param login the primary key of the login lock
	 * @return the login lock
	 * @throws NoSuchLoginLockException if a login lock with the primary key could not be found
	 */
	public LoginLock findByPrimaryKey(String login)
		throws NoSuchLoginLockException;

	/**
	 * Returns the login lock with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param login the primary key of the login lock
	 * @return the login lock, or <code>null</code> if a login lock with the primary key could not be found
	 */
	public LoginLock fetchByPrimaryKey(String login);

	/**
	 * Returns all the login locks.
	 *
	 * @return the login locks
	 */
	public java.util.List<LoginLock> findAll();

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
	public java.util.List<LoginLock> findAll(int start, int end);

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
	public java.util.List<LoginLock> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoginLock>
			orderByComparator);

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
	public java.util.List<LoginLock> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoginLock>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the login locks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of login locks.
	 *
	 * @return the number of login locks
	 */
	public int countAll();

}