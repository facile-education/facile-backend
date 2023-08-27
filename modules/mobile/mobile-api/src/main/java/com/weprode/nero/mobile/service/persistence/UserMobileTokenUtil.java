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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.mobile.model.UserMobileToken;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the user mobile token service. This utility wraps <code>com.weprode.nero.mobile.service.persistence.impl.UserMobileTokenPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserMobileTokenPersistence
 * @generated
 */
public class UserMobileTokenUtil {

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
	public static void clearCache(UserMobileToken userMobileToken) {
		getPersistence().clearCache(userMobileToken);
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
	public static Map<Serializable, UserMobileToken> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserMobileToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserMobileToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserMobileToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserMobileToken> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserMobileToken update(UserMobileToken userMobileToken) {
		return getPersistence().update(userMobileToken);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserMobileToken update(
		UserMobileToken userMobileToken, ServiceContext serviceContext) {

		return getPersistence().update(userMobileToken, serviceContext);
	}

	/**
	 * Returns the user mobile token where mobileToken = &#63; or throws a <code>NoSuchUserMobileTokenException</code> if it could not be found.
	 *
	 * @param mobileToken the mobile token
	 * @return the matching user mobile token
	 * @throws NoSuchUserMobileTokenException if a matching user mobile token could not be found
	 */
	public static UserMobileToken findBymobileToken(String mobileToken)
		throws com.weprode.nero.mobile.exception.
			NoSuchUserMobileTokenException {

		return getPersistence().findBymobileToken(mobileToken);
	}

	/**
	 * Returns the user mobile token where mobileToken = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mobileToken the mobile token
	 * @return the matching user mobile token, or <code>null</code> if a matching user mobile token could not be found
	 */
	public static UserMobileToken fetchBymobileToken(String mobileToken) {
		return getPersistence().fetchBymobileToken(mobileToken);
	}

	/**
	 * Returns the user mobile token where mobileToken = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mobileToken the mobile token
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user mobile token, or <code>null</code> if a matching user mobile token could not be found
	 */
	public static UserMobileToken fetchBymobileToken(
		String mobileToken, boolean useFinderCache) {

		return getPersistence().fetchBymobileToken(mobileToken, useFinderCache);
	}

	/**
	 * Removes the user mobile token where mobileToken = &#63; from the database.
	 *
	 * @param mobileToken the mobile token
	 * @return the user mobile token that was removed
	 */
	public static UserMobileToken removeBymobileToken(String mobileToken)
		throws com.weprode.nero.mobile.exception.
			NoSuchUserMobileTokenException {

		return getPersistence().removeBymobileToken(mobileToken);
	}

	/**
	 * Returns the number of user mobile tokens where mobileToken = &#63;.
	 *
	 * @param mobileToken the mobile token
	 * @return the number of matching user mobile tokens
	 */
	public static int countBymobileToken(String mobileToken) {
		return getPersistence().countBymobileToken(mobileToken);
	}

	/**
	 * Caches the user mobile token in the entity cache if it is enabled.
	 *
	 * @param userMobileToken the user mobile token
	 */
	public static void cacheResult(UserMobileToken userMobileToken) {
		getPersistence().cacheResult(userMobileToken);
	}

	/**
	 * Caches the user mobile tokens in the entity cache if it is enabled.
	 *
	 * @param userMobileTokens the user mobile tokens
	 */
	public static void cacheResult(List<UserMobileToken> userMobileTokens) {
		getPersistence().cacheResult(userMobileTokens);
	}

	/**
	 * Creates a new user mobile token with the primary key. Does not add the user mobile token to the database.
	 *
	 * @param userId the primary key for the new user mobile token
	 * @return the new user mobile token
	 */
	public static UserMobileToken create(long userId) {
		return getPersistence().create(userId);
	}

	/**
	 * Removes the user mobile token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the user mobile token
	 * @return the user mobile token that was removed
	 * @throws NoSuchUserMobileTokenException if a user mobile token with the primary key could not be found
	 */
	public static UserMobileToken remove(long userId)
		throws com.weprode.nero.mobile.exception.
			NoSuchUserMobileTokenException {

		return getPersistence().remove(userId);
	}

	public static UserMobileToken updateImpl(UserMobileToken userMobileToken) {
		return getPersistence().updateImpl(userMobileToken);
	}

	/**
	 * Returns the user mobile token with the primary key or throws a <code>NoSuchUserMobileTokenException</code> if it could not be found.
	 *
	 * @param userId the primary key of the user mobile token
	 * @return the user mobile token
	 * @throws NoSuchUserMobileTokenException if a user mobile token with the primary key could not be found
	 */
	public static UserMobileToken findByPrimaryKey(long userId)
		throws com.weprode.nero.mobile.exception.
			NoSuchUserMobileTokenException {

		return getPersistence().findByPrimaryKey(userId);
	}

	/**
	 * Returns the user mobile token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the user mobile token
	 * @return the user mobile token, or <code>null</code> if a user mobile token with the primary key could not be found
	 */
	public static UserMobileToken fetchByPrimaryKey(long userId) {
		return getPersistence().fetchByPrimaryKey(userId);
	}

	/**
	 * Returns all the user mobile tokens.
	 *
	 * @return the user mobile tokens
	 */
	public static List<UserMobileToken> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<UserMobileToken> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<UserMobileToken> findAll(
		int start, int end,
		OrderByComparator<UserMobileToken> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<UserMobileToken> findAll(
		int start, int end,
		OrderByComparator<UserMobileToken> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the user mobile tokens from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of user mobile tokens.
	 *
	 * @return the number of user mobile tokens
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserMobileTokenPersistence getPersistence() {
		return _persistence;
	}

	private static volatile UserMobileTokenPersistence _persistence;

}