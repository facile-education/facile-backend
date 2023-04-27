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

package com.weprode.nero.statistic.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.statistic.model.LoolStat;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the lool stat service. This utility wraps <code>com.weprode.nero.statistic.service.persistence.impl.LoolStatPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoolStatPersistence
 * @generated
 */
public class LoolStatUtil {

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
	public static void clearCache(LoolStat loolStat) {
		getPersistence().clearCache(loolStat);
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
	public static Map<Serializable, LoolStat> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoolStat> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoolStat> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoolStat> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoolStat> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoolStat update(LoolStat loolStat) {
		return getPersistence().update(loolStat);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoolStat update(
		LoolStat loolStat, ServiceContext serviceContext) {

		return getPersistence().update(loolStat, serviceContext);
	}

	/**
	 * Returns all the lool stats where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching lool stats
	 */
	public static List<LoolStat> findByuserId(long userId) {
		return getPersistence().findByuserId(userId);
	}

	/**
	 * Returns a range of all the lool stats where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @return the range of matching lool stats
	 */
	public static List<LoolStat> findByuserId(long userId, int start, int end) {
		return getPersistence().findByuserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the lool stats where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lool stats
	 */
	public static List<LoolStat> findByuserId(
		long userId, int start, int end,
		OrderByComparator<LoolStat> orderByComparator) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the lool stats where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching lool stats
	 */
	public static List<LoolStat> findByuserId(
		long userId, int start, int end,
		OrderByComparator<LoolStat> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first lool stat in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lool stat
	 * @throws NoSuchLoolStatException if a matching lool stat could not be found
	 */
	public static LoolStat findByuserId_First(
			long userId, OrderByComparator<LoolStat> orderByComparator)
		throws com.weprode.nero.statistic.exception.NoSuchLoolStatException {

		return getPersistence().findByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first lool stat in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lool stat, or <code>null</code> if a matching lool stat could not be found
	 */
	public static LoolStat fetchByuserId_First(
		long userId, OrderByComparator<LoolStat> orderByComparator) {

		return getPersistence().fetchByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last lool stat in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lool stat
	 * @throws NoSuchLoolStatException if a matching lool stat could not be found
	 */
	public static LoolStat findByuserId_Last(
			long userId, OrderByComparator<LoolStat> orderByComparator)
		throws com.weprode.nero.statistic.exception.NoSuchLoolStatException {

		return getPersistence().findByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last lool stat in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lool stat, or <code>null</code> if a matching lool stat could not be found
	 */
	public static LoolStat fetchByuserId_Last(
		long userId, OrderByComparator<LoolStat> orderByComparator) {

		return getPersistence().fetchByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the lool stats before and after the current lool stat in the ordered set where userId = &#63;.
	 *
	 * @param statId the primary key of the current lool stat
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lool stat
	 * @throws NoSuchLoolStatException if a lool stat with the primary key could not be found
	 */
	public static LoolStat[] findByuserId_PrevAndNext(
			long statId, long userId,
			OrderByComparator<LoolStat> orderByComparator)
		throws com.weprode.nero.statistic.exception.NoSuchLoolStatException {

		return getPersistence().findByuserId_PrevAndNext(
			statId, userId, orderByComparator);
	}

	/**
	 * Removes all the lool stats where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByuserId(long userId) {
		getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of lool stats where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching lool stats
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Caches the lool stat in the entity cache if it is enabled.
	 *
	 * @param loolStat the lool stat
	 */
	public static void cacheResult(LoolStat loolStat) {
		getPersistence().cacheResult(loolStat);
	}

	/**
	 * Caches the lool stats in the entity cache if it is enabled.
	 *
	 * @param loolStats the lool stats
	 */
	public static void cacheResult(List<LoolStat> loolStats) {
		getPersistence().cacheResult(loolStats);
	}

	/**
	 * Creates a new lool stat with the primary key. Does not add the lool stat to the database.
	 *
	 * @param statId the primary key for the new lool stat
	 * @return the new lool stat
	 */
	public static LoolStat create(long statId) {
		return getPersistence().create(statId);
	}

	/**
	 * Removes the lool stat with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param statId the primary key of the lool stat
	 * @return the lool stat that was removed
	 * @throws NoSuchLoolStatException if a lool stat with the primary key could not be found
	 */
	public static LoolStat remove(long statId)
		throws com.weprode.nero.statistic.exception.NoSuchLoolStatException {

		return getPersistence().remove(statId);
	}

	public static LoolStat updateImpl(LoolStat loolStat) {
		return getPersistence().updateImpl(loolStat);
	}

	/**
	 * Returns the lool stat with the primary key or throws a <code>NoSuchLoolStatException</code> if it could not be found.
	 *
	 * @param statId the primary key of the lool stat
	 * @return the lool stat
	 * @throws NoSuchLoolStatException if a lool stat with the primary key could not be found
	 */
	public static LoolStat findByPrimaryKey(long statId)
		throws com.weprode.nero.statistic.exception.NoSuchLoolStatException {

		return getPersistence().findByPrimaryKey(statId);
	}

	/**
	 * Returns the lool stat with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param statId the primary key of the lool stat
	 * @return the lool stat, or <code>null</code> if a lool stat with the primary key could not be found
	 */
	public static LoolStat fetchByPrimaryKey(long statId) {
		return getPersistence().fetchByPrimaryKey(statId);
	}

	/**
	 * Returns all the lool stats.
	 *
	 * @return the lool stats
	 */
	public static List<LoolStat> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the lool stats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @return the range of lool stats
	 */
	public static List<LoolStat> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the lool stats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of lool stats
	 */
	public static List<LoolStat> findAll(
		int start, int end, OrderByComparator<LoolStat> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the lool stats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of lool stats
	 */
	public static List<LoolStat> findAll(
		int start, int end, OrderByComparator<LoolStat> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the lool stats from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of lool stats.
	 *
	 * @return the number of lool stats
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoolStatPersistence getPersistence() {
		return _persistence;
	}

	private static volatile LoolStatPersistence _persistence;

}