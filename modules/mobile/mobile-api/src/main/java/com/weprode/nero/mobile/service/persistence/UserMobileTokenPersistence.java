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

package com.weprode.nero.mobile.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.mobile.exception.NoSuchUserMobileTokenException;
import com.weprode.nero.mobile.model.UserMobileToken;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the user mobile token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserMobileTokenUtil
 * @generated
 */
@ProviderType
public interface UserMobileTokenPersistence
	extends BasePersistence<UserMobileToken> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserMobileTokenUtil} to access the user mobile token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the user mobile token where mobileToken = &#63; or throws a <code>NoSuchUserMobileTokenException</code> if it could not be found.
	 *
	 * @param mobileToken the mobile token
	 * @return the matching user mobile token
	 * @throws NoSuchUserMobileTokenException if a matching user mobile token could not be found
	 */
	public UserMobileToken findBymobileToken(String mobileToken)
		throws NoSuchUserMobileTokenException;

	/**
	 * Returns the user mobile token where mobileToken = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mobileToken the mobile token
	 * @return the matching user mobile token, or <code>null</code> if a matching user mobile token could not be found
	 */
	public UserMobileToken fetchBymobileToken(String mobileToken);

	/**
	 * Returns the user mobile token where mobileToken = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mobileToken the mobile token
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user mobile token, or <code>null</code> if a matching user mobile token could not be found
	 */
	public UserMobileToken fetchBymobileToken(
		String mobileToken, boolean useFinderCache);

	/**
	 * Removes the user mobile token where mobileToken = &#63; from the database.
	 *
	 * @param mobileToken the mobile token
	 * @return the user mobile token that was removed
	 */
	public UserMobileToken removeBymobileToken(String mobileToken)
		throws NoSuchUserMobileTokenException;

	/**
	 * Returns the number of user mobile tokens where mobileToken = &#63;.
	 *
	 * @param mobileToken the mobile token
	 * @return the number of matching user mobile tokens
	 */
	public int countBymobileToken(String mobileToken);

	/**
	 * Caches the user mobile token in the entity cache if it is enabled.
	 *
	 * @param userMobileToken the user mobile token
	 */
	public void cacheResult(UserMobileToken userMobileToken);

	/**
	 * Caches the user mobile tokens in the entity cache if it is enabled.
	 *
	 * @param userMobileTokens the user mobile tokens
	 */
	public void cacheResult(java.util.List<UserMobileToken> userMobileTokens);

	/**
	 * Creates a new user mobile token with the primary key. Does not add the user mobile token to the database.
	 *
	 * @param userId the primary key for the new user mobile token
	 * @return the new user mobile token
	 */
	public UserMobileToken create(long userId);

	/**
	 * Removes the user mobile token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the user mobile token
	 * @return the user mobile token that was removed
	 * @throws NoSuchUserMobileTokenException if a user mobile token with the primary key could not be found
	 */
	public UserMobileToken remove(long userId)
		throws NoSuchUserMobileTokenException;

	public UserMobileToken updateImpl(UserMobileToken userMobileToken);

	/**
	 * Returns the user mobile token with the primary key or throws a <code>NoSuchUserMobileTokenException</code> if it could not be found.
	 *
	 * @param userId the primary key of the user mobile token
	 * @return the user mobile token
	 * @throws NoSuchUserMobileTokenException if a user mobile token with the primary key could not be found
	 */
	public UserMobileToken findByPrimaryKey(long userId)
		throws NoSuchUserMobileTokenException;

	/**
	 * Returns the user mobile token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the user mobile token
	 * @return the user mobile token, or <code>null</code> if a user mobile token with the primary key could not be found
	 */
	public UserMobileToken fetchByPrimaryKey(long userId);

	/**
	 * Returns all the user mobile tokens.
	 *
	 * @return the user mobile tokens
	 */
	public java.util.List<UserMobileToken> findAll();

	/**
	 * Returns a range of all the user mobile tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserMobileTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user mobile tokens
	 * @param end the upper bound of the range of user mobile tokens (not inclusive)
	 * @return the range of user mobile tokens
	 */
	public java.util.List<UserMobileToken> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the user mobile tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserMobileTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user mobile tokens
	 * @param end the upper bound of the range of user mobile tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user mobile tokens
	 */
	public java.util.List<UserMobileToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserMobileToken>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user mobile tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserMobileTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user mobile tokens
	 * @param end the upper bound of the range of user mobile tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user mobile tokens
	 */
	public java.util.List<UserMobileToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserMobileToken>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the user mobile tokens from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of user mobile tokens.
	 *
	 * @return the number of user mobile tokens
	 */
	public int countAll();

}