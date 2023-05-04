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

import com.weprode.nero.about.model.EntVersionUser;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the ent version user service. This utility wraps <code>com.weprode.nero.about.service.persistence.impl.EntVersionUserPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntVersionUserPersistence
 * @generated
 */
public class EntVersionUserUtil {

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
	public static void clearCache(EntVersionUser entVersionUser) {
		getPersistence().clearCache(entVersionUser);
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
	public static Map<Serializable, EntVersionUser> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EntVersionUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EntVersionUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EntVersionUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EntVersionUser> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EntVersionUser update(EntVersionUser entVersionUser) {
		return getPersistence().update(entVersionUser);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EntVersionUser update(
		EntVersionUser entVersionUser, ServiceContext serviceContext) {

		return getPersistence().update(entVersionUser, serviceContext);
	}

	/**
	 * Returns the ent version user where entVersionId = &#63; and userId = &#63; or throws a <code>NoSuchEntVersionUserException</code> if it could not be found.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @return the matching ent version user
	 * @throws NoSuchEntVersionUserException if a matching ent version user could not be found
	 */
	public static EntVersionUser findByentVersionId_userId(
			long entVersionId, long userId)
		throws com.weprode.nero.about.exception.NoSuchEntVersionUserException {

		return getPersistence().findByentVersionId_userId(entVersionId, userId);
	}

	/**
	 * Returns the ent version user where entVersionId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @return the matching ent version user, or <code>null</code> if a matching ent version user could not be found
	 */
	public static EntVersionUser fetchByentVersionId_userId(
		long entVersionId, long userId) {

		return getPersistence().fetchByentVersionId_userId(
			entVersionId, userId);
	}

	/**
	 * Returns the ent version user where entVersionId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ent version user, or <code>null</code> if a matching ent version user could not be found
	 */
	public static EntVersionUser fetchByentVersionId_userId(
		long entVersionId, long userId, boolean useFinderCache) {

		return getPersistence().fetchByentVersionId_userId(
			entVersionId, userId, useFinderCache);
	}

	/**
	 * Removes the ent version user where entVersionId = &#63; and userId = &#63; from the database.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @return the ent version user that was removed
	 */
	public static EntVersionUser removeByentVersionId_userId(
			long entVersionId, long userId)
		throws com.weprode.nero.about.exception.NoSuchEntVersionUserException {

		return getPersistence().removeByentVersionId_userId(
			entVersionId, userId);
	}

	/**
	 * Returns the number of ent version users where entVersionId = &#63; and userId = &#63;.
	 *
	 * @param entVersionId the ent version ID
	 * @param userId the user ID
	 * @return the number of matching ent version users
	 */
	public static int countByentVersionId_userId(
		long entVersionId, long userId) {

		return getPersistence().countByentVersionId_userId(
			entVersionId, userId);
	}

	/**
	 * Caches the ent version user in the entity cache if it is enabled.
	 *
	 * @param entVersionUser the ent version user
	 */
	public static void cacheResult(EntVersionUser entVersionUser) {
		getPersistence().cacheResult(entVersionUser);
	}

	/**
	 * Caches the ent version users in the entity cache if it is enabled.
	 *
	 * @param entVersionUsers the ent version users
	 */
	public static void cacheResult(List<EntVersionUser> entVersionUsers) {
		getPersistence().cacheResult(entVersionUsers);
	}

	/**
	 * Creates a new ent version user with the primary key. Does not add the ent version user to the database.
	 *
	 * @param versionUserId the primary key for the new ent version user
	 * @return the new ent version user
	 */
	public static EntVersionUser create(long versionUserId) {
		return getPersistence().create(versionUserId);
	}

	/**
	 * Removes the ent version user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param versionUserId the primary key of the ent version user
	 * @return the ent version user that was removed
	 * @throws NoSuchEntVersionUserException if a ent version user with the primary key could not be found
	 */
	public static EntVersionUser remove(long versionUserId)
		throws com.weprode.nero.about.exception.NoSuchEntVersionUserException {

		return getPersistence().remove(versionUserId);
	}

	public static EntVersionUser updateImpl(EntVersionUser entVersionUser) {
		return getPersistence().updateImpl(entVersionUser);
	}

	/**
	 * Returns the ent version user with the primary key or throws a <code>NoSuchEntVersionUserException</code> if it could not be found.
	 *
	 * @param versionUserId the primary key of the ent version user
	 * @return the ent version user
	 * @throws NoSuchEntVersionUserException if a ent version user with the primary key could not be found
	 */
	public static EntVersionUser findByPrimaryKey(long versionUserId)
		throws com.weprode.nero.about.exception.NoSuchEntVersionUserException {

		return getPersistence().findByPrimaryKey(versionUserId);
	}

	/**
	 * Returns the ent version user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param versionUserId the primary key of the ent version user
	 * @return the ent version user, or <code>null</code> if a ent version user with the primary key could not be found
	 */
	public static EntVersionUser fetchByPrimaryKey(long versionUserId) {
		return getPersistence().fetchByPrimaryKey(versionUserId);
	}

	/**
	 * Returns all the ent version users.
	 *
	 * @return the ent version users
	 */
	public static List<EntVersionUser> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<EntVersionUser> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<EntVersionUser> findAll(
		int start, int end,
		OrderByComparator<EntVersionUser> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<EntVersionUser> findAll(
		int start, int end, OrderByComparator<EntVersionUser> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the ent version users from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of ent version users.
	 *
	 * @return the number of ent version users
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EntVersionUserPersistence getPersistence() {
		return _persistence;
	}

	private static volatile EntVersionUserPersistence _persistence;

}