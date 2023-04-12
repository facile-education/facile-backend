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

import com.weprode.nero.document.model.LoolToken;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the lool token service. This utility wraps <code>com.weprode.nero.document.service.persistence.impl.LoolTokenPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoolTokenPersistence
 * @generated
 */
public class LoolTokenUtil {

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
	public static void clearCache(LoolToken loolToken) {
		getPersistence().clearCache(loolToken);
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
	public static Map<Serializable, LoolToken> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoolToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoolToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoolToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoolToken> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoolToken update(LoolToken loolToken) {
		return getPersistence().update(loolToken);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoolToken update(
		LoolToken loolToken, ServiceContext serviceContext) {

		return getPersistence().update(loolToken, serviceContext);
	}

	/**
	 * Returns the lool token where token = &#63; or throws a <code>NoSuchLoolTokenException</code> if it could not be found.
	 *
	 * @param token the token
	 * @return the matching lool token
	 * @throws NoSuchLoolTokenException if a matching lool token could not be found
	 */
	public static LoolToken findBytoken(String token)
		throws com.weprode.nero.document.exception.NoSuchLoolTokenException {

		return getPersistence().findBytoken(token);
	}

	/**
	 * Returns the lool token where token = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param token the token
	 * @return the matching lool token, or <code>null</code> if a matching lool token could not be found
	 */
	public static LoolToken fetchBytoken(String token) {
		return getPersistence().fetchBytoken(token);
	}

	/**
	 * Returns the lool token where token = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param token the token
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching lool token, or <code>null</code> if a matching lool token could not be found
	 */
	public static LoolToken fetchBytoken(String token, boolean useFinderCache) {
		return getPersistence().fetchBytoken(token, useFinderCache);
	}

	/**
	 * Removes the lool token where token = &#63; from the database.
	 *
	 * @param token the token
	 * @return the lool token that was removed
	 */
	public static LoolToken removeBytoken(String token)
		throws com.weprode.nero.document.exception.NoSuchLoolTokenException {

		return getPersistence().removeBytoken(token);
	}

	/**
	 * Returns the number of lool tokens where token = &#63;.
	 *
	 * @param token the token
	 * @return the number of matching lool tokens
	 */
	public static int countBytoken(String token) {
		return getPersistence().countBytoken(token);
	}

	/**
	 * Caches the lool token in the entity cache if it is enabled.
	 *
	 * @param loolToken the lool token
	 */
	public static void cacheResult(LoolToken loolToken) {
		getPersistence().cacheResult(loolToken);
	}

	/**
	 * Caches the lool tokens in the entity cache if it is enabled.
	 *
	 * @param loolTokens the lool tokens
	 */
	public static void cacheResult(List<LoolToken> loolTokens) {
		getPersistence().cacheResult(loolTokens);
	}

	/**
	 * Creates a new lool token with the primary key. Does not add the lool token to the database.
	 *
	 * @param loolTokenId the primary key for the new lool token
	 * @return the new lool token
	 */
	public static LoolToken create(long loolTokenId) {
		return getPersistence().create(loolTokenId);
	}

	/**
	 * Removes the lool token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loolTokenId the primary key of the lool token
	 * @return the lool token that was removed
	 * @throws NoSuchLoolTokenException if a lool token with the primary key could not be found
	 */
	public static LoolToken remove(long loolTokenId)
		throws com.weprode.nero.document.exception.NoSuchLoolTokenException {

		return getPersistence().remove(loolTokenId);
	}

	public static LoolToken updateImpl(LoolToken loolToken) {
		return getPersistence().updateImpl(loolToken);
	}

	/**
	 * Returns the lool token with the primary key or throws a <code>NoSuchLoolTokenException</code> if it could not be found.
	 *
	 * @param loolTokenId the primary key of the lool token
	 * @return the lool token
	 * @throws NoSuchLoolTokenException if a lool token with the primary key could not be found
	 */
	public static LoolToken findByPrimaryKey(long loolTokenId)
		throws com.weprode.nero.document.exception.NoSuchLoolTokenException {

		return getPersistence().findByPrimaryKey(loolTokenId);
	}

	/**
	 * Returns the lool token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loolTokenId the primary key of the lool token
	 * @return the lool token, or <code>null</code> if a lool token with the primary key could not be found
	 */
	public static LoolToken fetchByPrimaryKey(long loolTokenId) {
		return getPersistence().fetchByPrimaryKey(loolTokenId);
	}

	/**
	 * Returns all the lool tokens.
	 *
	 * @return the lool tokens
	 */
	public static List<LoolToken> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<LoolToken> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<LoolToken> findAll(
		int start, int end, OrderByComparator<LoolToken> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<LoolToken> findAll(
		int start, int end, OrderByComparator<LoolToken> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the lool tokens from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of lool tokens.
	 *
	 * @return the number of lool tokens
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoolTokenPersistence getPersistence() {
		return _persistence;
	}

	private static volatile LoolTokenPersistence _persistence;

}