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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.about.exception.NoSuchEntVersionUserException;
import com.weprode.nero.about.model.EntVersionUser;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the ent version user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntVersionUserUtil
 * @generated
 */
@ProviderType
public interface EntVersionUserPersistence
	extends BasePersistence<EntVersionUser> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntVersionUserUtil} to access the ent version user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the ent version user where entVersionId = &#63; and userId = &#63; or throws a <code>NoSuchEntVersionUserException</code> if it could not be found.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @return the matching ent version user
	 * @throws NoSuchEntVersionUserException if a matching ent version user could not be found
	 */
	public EntVersionUser findByentVersionId_userId(
			long entVersionId, long userId)
		throws NoSuchEntVersionUserException;

	/**
	 * Returns the ent version user where entVersionId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @return the matching ent version user, or <code>null</code> if a matching ent version user could not be found
	 */
	public EntVersionUser fetchByentVersionId_userId(
		long entVersionId, long userId);

	/**
	 * Returns the ent version user where entVersionId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ent version user, or <code>null</code> if a matching ent version user could not be found
	 */
	public EntVersionUser fetchByentVersionId_userId(
		long entVersionId, long userId, boolean useFinderCache);

	/**
	 * Removes the ent version user where entVersionId = &#63; and userId = &#63; from the database.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @return the ent version user that was removed
	 */
	public EntVersionUser removeByentVersionId_userId(
			long entVersionId, long userId)
		throws NoSuchEntVersionUserException;

	/**
	 * Returns the number of ent version users where entVersionId = &#63; and userId = &#63;.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @return the number of matching ent version users
	 */
	public int countByentVersionId_userId(long entVersionId, long userId);

	/**
	 * Caches the ent version user in the entity cache if it is enabled.
	 *
	 * @param entVersionUser the ent version user
	 */
	public void cacheResult(EntVersionUser entVersionUser);

	/**
	 * Caches the ent version users in the entity cache if it is enabled.
	 *
	 * @param entVersionUsers the ent version users
	 */
	public void cacheResult(java.util.List<EntVersionUser> entVersionUsers);

	/**
	 * Creates a new ent version user with the primary key. Does not add the ent version user to the database.
	 *
	 * @param versionUserId the primary key for the new ent version user
	 * @return the new ent version user
	 */
	public EntVersionUser create(long versionUserId);

	/**
	 * Removes the ent version user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param versionUserId the primary key of the ent version user
	 * @return the ent version user that was removed
	 * @throws NoSuchEntVersionUserException if a ent version user with the primary key could not be found
	 */
	public EntVersionUser remove(long versionUserId)
		throws NoSuchEntVersionUserException;

	public EntVersionUser updateImpl(EntVersionUser entVersionUser);

	/**
	 * Returns the ent version user with the primary key or throws a <code>NoSuchEntVersionUserException</code> if it could not be found.
	 *
	 * @param versionUserId the primary key of the ent version user
	 * @return the ent version user
	 * @throws NoSuchEntVersionUserException if a ent version user with the primary key could not be found
	 */
	public EntVersionUser findByPrimaryKey(long versionUserId)
		throws NoSuchEntVersionUserException;

	/**
	 * Returns the ent version user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param versionUserId the primary key of the ent version user
	 * @return the ent version user, or <code>null</code> if a ent version user with the primary key could not be found
	 */
	public EntVersionUser fetchByPrimaryKey(long versionUserId);

	/**
	 * Returns all the ent version users.
	 *
	 * @return the ent version users
	 */
	public java.util.List<EntVersionUser> findAll();

	/**
	 * Returns a range of all the ent version users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent version users
	 * @param end the upper bound of the range of ent version users (not inclusive)
	 * @return the range of ent version users
	 */
	public java.util.List<EntVersionUser> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the ent version users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent version users
	 * @param end the upper bound of the range of ent version users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ent version users
	 */
	public java.util.List<EntVersionUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntVersionUser>
			orderByComparator);

	/**
	 * Returns an ordered range of all the ent version users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent version users
	 * @param end the upper bound of the range of ent version users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ent version users
	 */
	public java.util.List<EntVersionUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntVersionUser>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the ent version users from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of ent version users.
	 *
	 * @return the number of ent version users
	 */
	public int countAll();

}