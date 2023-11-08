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

package com.weprode.facile.document.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.document.exception.NoSuchLoolTokenException;
import com.weprode.facile.document.model.LoolToken;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the lool token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoolTokenUtil
 * @generated
 */
@ProviderType
public interface LoolTokenPersistence extends BasePersistence<LoolToken> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoolTokenUtil} to access the lool token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the lool token where token = &#63; or throws a <code>NoSuchLoolTokenException</code> if it could not be found.
	 *
	 * @param token the token
	 * @return the matching lool token
	 * @throws NoSuchLoolTokenException if a matching lool token could not be found
	 */
	public LoolToken findBytoken(String token) throws NoSuchLoolTokenException;

	/**
	 * Returns the lool token where token = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param token the token
	 * @return the matching lool token, or <code>null</code> if a matching lool token could not be found
	 */
	public LoolToken fetchBytoken(String token);

	/**
	 * Returns the lool token where token = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param token the token
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching lool token, or <code>null</code> if a matching lool token could not be found
	 */
	public LoolToken fetchBytoken(String token, boolean useFinderCache);

	/**
	 * Removes the lool token where token = &#63; from the database.
	 *
	 * @param token the token
	 * @return the lool token that was removed
	 */
	public LoolToken removeBytoken(String token)
		throws NoSuchLoolTokenException;

	/**
	 * Returns the number of lool tokens where token = &#63;.
	 *
	 * @param token the token
	 * @return the number of matching lool tokens
	 */
	public int countBytoken(String token);

	/**
	 * Caches the lool token in the entity cache if it is enabled.
	 *
	 * @param loolToken the lool token
	 */
	public void cacheResult(LoolToken loolToken);

	/**
	 * Caches the lool tokens in the entity cache if it is enabled.
	 *
	 * @param loolTokens the lool tokens
	 */
	public void cacheResult(java.util.List<LoolToken> loolTokens);

	/**
	 * Creates a new lool token with the primary key. Does not add the lool token to the database.
	 *
	 * @param loolTokenId the primary key for the new lool token
	 * @return the new lool token
	 */
	public LoolToken create(long loolTokenId);

	/**
	 * Removes the lool token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loolTokenId the primary key of the lool token
	 * @return the lool token that was removed
	 * @throws NoSuchLoolTokenException if a lool token with the primary key could not be found
	 */
	public LoolToken remove(long loolTokenId) throws NoSuchLoolTokenException;

	public LoolToken updateImpl(LoolToken loolToken);

	/**
	 * Returns the lool token with the primary key or throws a <code>NoSuchLoolTokenException</code> if it could not be found.
	 *
	 * @param loolTokenId the primary key of the lool token
	 * @return the lool token
	 * @throws NoSuchLoolTokenException if a lool token with the primary key could not be found
	 */
	public LoolToken findByPrimaryKey(long loolTokenId)
		throws NoSuchLoolTokenException;

	/**
	 * Returns the lool token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loolTokenId the primary key of the lool token
	 * @return the lool token, or <code>null</code> if a lool token with the primary key could not be found
	 */
	public LoolToken fetchByPrimaryKey(long loolTokenId);

	/**
	 * Returns all the lool tokens.
	 *
	 * @return the lool tokens
	 */
	public java.util.List<LoolToken> findAll();

	/**
	 * Returns a range of all the lool tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool tokens
	 * @param end the upper bound of the range of lool tokens (not inclusive)
	 * @return the range of lool tokens
	 */
	public java.util.List<LoolToken> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the lool tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool tokens
	 * @param end the upper bound of the range of lool tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of lool tokens
	 */
	public java.util.List<LoolToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoolToken>
			orderByComparator);

	/**
	 * Returns an ordered range of all the lool tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool tokens
	 * @param end the upper bound of the range of lool tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of lool tokens
	 */
	public java.util.List<LoolToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoolToken>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the lool tokens from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of lool tokens.
	 *
	 * @return the number of lool tokens
	 */
	public int countAll();

}