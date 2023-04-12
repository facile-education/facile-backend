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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.document.exception.NoSuchEditionLockException;
import com.weprode.nero.document.model.EditionLock;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the edition lock service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EditionLockUtil
 * @generated
 */
@ProviderType
public interface EditionLockPersistence extends BasePersistence<EditionLock> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EditionLockUtil} to access the edition lock persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the edition lock where fileId = &#63; and userId = &#63; or throws a <code>NoSuchEditionLockException</code> if it could not be found.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @return the matching edition lock
	 * @throws NoSuchEditionLockException if a matching edition lock could not be found
	 */
	public EditionLock findByfileId_userId(long fileId, long userId)
		throws NoSuchEditionLockException;

	/**
	 * Returns the edition lock where fileId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @return the matching edition lock, or <code>null</code> if a matching edition lock could not be found
	 */
	public EditionLock fetchByfileId_userId(long fileId, long userId);

	/**
	 * Returns the edition lock where fileId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching edition lock, or <code>null</code> if a matching edition lock could not be found
	 */
	public EditionLock fetchByfileId_userId(
		long fileId, long userId, boolean useFinderCache);

	/**
	 * Removes the edition lock where fileId = &#63; and userId = &#63; from the database.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @return the edition lock that was removed
	 */
	public EditionLock removeByfileId_userId(long fileId, long userId)
		throws NoSuchEditionLockException;

	/**
	 * Returns the number of edition locks where fileId = &#63; and userId = &#63;.
	 *
	 * @param fileId the file ID
	 * @param userId the user ID
	 * @return the number of matching edition locks
	 */
	public int countByfileId_userId(long fileId, long userId);

	/**
	 * Caches the edition lock in the entity cache if it is enabled.
	 *
	 * @param editionLock the edition lock
	 */
	public void cacheResult(EditionLock editionLock);

	/**
	 * Caches the edition locks in the entity cache if it is enabled.
	 *
	 * @param editionLocks the edition locks
	 */
	public void cacheResult(java.util.List<EditionLock> editionLocks);

	/**
	 * Creates a new edition lock with the primary key. Does not add the edition lock to the database.
	 *
	 * @param fileId the primary key for the new edition lock
	 * @return the new edition lock
	 */
	public EditionLock create(long fileId);

	/**
	 * Removes the edition lock with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileId the primary key of the edition lock
	 * @return the edition lock that was removed
	 * @throws NoSuchEditionLockException if a edition lock with the primary key could not be found
	 */
	public EditionLock remove(long fileId) throws NoSuchEditionLockException;

	public EditionLock updateImpl(EditionLock editionLock);

	/**
	 * Returns the edition lock with the primary key or throws a <code>NoSuchEditionLockException</code> if it could not be found.
	 *
	 * @param fileId the primary key of the edition lock
	 * @return the edition lock
	 * @throws NoSuchEditionLockException if a edition lock with the primary key could not be found
	 */
	public EditionLock findByPrimaryKey(long fileId)
		throws NoSuchEditionLockException;

	/**
	 * Returns the edition lock with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileId the primary key of the edition lock
	 * @return the edition lock, or <code>null</code> if a edition lock with the primary key could not be found
	 */
	public EditionLock fetchByPrimaryKey(long fileId);

	/**
	 * Returns all the edition locks.
	 *
	 * @return the edition locks
	 */
	public java.util.List<EditionLock> findAll();

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
	public java.util.List<EditionLock> findAll(int start, int end);

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
	public java.util.List<EditionLock> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EditionLock>
			orderByComparator);

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
	public java.util.List<EditionLock> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EditionLock>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the edition locks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of edition locks.
	 *
	 * @return the number of edition locks
	 */
	public int countAll();

}