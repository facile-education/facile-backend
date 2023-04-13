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

package com.weprode.nero.application.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.application.model.Broadcast;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the broadcast service. This utility wraps <code>com.weprode.nero.application.service.persistence.impl.BroadcastPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastPersistence
 * @generated
 */
public class BroadcastUtil {

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
	public static void clearCache(Broadcast broadcast) {
		getPersistence().clearCache(broadcast);
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
	public static Map<Serializable, Broadcast> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Broadcast> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Broadcast> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Broadcast> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Broadcast> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Broadcast update(Broadcast broadcast) {
		return getPersistence().update(broadcast);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Broadcast update(
		Broadcast broadcast, ServiceContext serviceContext) {

		return getPersistence().update(broadcast, serviceContext);
	}

	/**
	 * Returns the broadcast where applicationId = &#63; and schoolId = &#63; or throws a <code>NoSuchBroadcastException</code> if it could not be found.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching broadcast
	 * @throws NoSuchBroadcastException if a matching broadcast could not be found
	 */
	public static Broadcast findByapplicationId_schoolId(
			long applicationId, long schoolId)
		throws com.weprode.nero.application.exception.NoSuchBroadcastException {

		return getPersistence().findByapplicationId_schoolId(
			applicationId, schoolId);
	}

	/**
	 * Returns the broadcast where applicationId = &#63; and schoolId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching broadcast, or <code>null</code> if a matching broadcast could not be found
	 */
	public static Broadcast fetchByapplicationId_schoolId(
		long applicationId, long schoolId) {

		return getPersistence().fetchByapplicationId_schoolId(
			applicationId, schoolId);
	}

	/**
	 * Returns the broadcast where applicationId = &#63; and schoolId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching broadcast, or <code>null</code> if a matching broadcast could not be found
	 */
	public static Broadcast fetchByapplicationId_schoolId(
		long applicationId, long schoolId, boolean useFinderCache) {

		return getPersistence().fetchByapplicationId_schoolId(
			applicationId, schoolId, useFinderCache);
	}

	/**
	 * Removes the broadcast where applicationId = &#63; and schoolId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the broadcast that was removed
	 */
	public static Broadcast removeByapplicationId_schoolId(
			long applicationId, long schoolId)
		throws com.weprode.nero.application.exception.NoSuchBroadcastException {

		return getPersistence().removeByapplicationId_schoolId(
			applicationId, schoolId);
	}

	/**
	 * Returns the number of broadcasts where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the number of matching broadcasts
	 */
	public static int countByapplicationId_schoolId(
		long applicationId, long schoolId) {

		return getPersistence().countByapplicationId_schoolId(
			applicationId, schoolId);
	}

	/**
	 * Returns all the broadcasts where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching broadcasts
	 */
	public static List<Broadcast> findByapplicationId(long applicationId) {
		return getPersistence().findByapplicationId(applicationId);
	}

	/**
	 * Returns a range of all the broadcasts where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @return the range of matching broadcasts
	 */
	public static List<Broadcast> findByapplicationId(
		long applicationId, int start, int end) {

		return getPersistence().findByapplicationId(applicationId, start, end);
	}

	/**
	 * Returns an ordered range of all the broadcasts where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching broadcasts
	 */
	public static List<Broadcast> findByapplicationId(
		long applicationId, int start, int end,
		OrderByComparator<Broadcast> orderByComparator) {

		return getPersistence().findByapplicationId(
			applicationId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the broadcasts where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching broadcasts
	 */
	public static List<Broadcast> findByapplicationId(
		long applicationId, int start, int end,
		OrderByComparator<Broadcast> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByapplicationId(
			applicationId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast
	 * @throws NoSuchBroadcastException if a matching broadcast could not be found
	 */
	public static Broadcast findByapplicationId_First(
			long applicationId, OrderByComparator<Broadcast> orderByComparator)
		throws com.weprode.nero.application.exception.NoSuchBroadcastException {

		return getPersistence().findByapplicationId_First(
			applicationId, orderByComparator);
	}

	/**
	 * Returns the first broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast, or <code>null</code> if a matching broadcast could not be found
	 */
	public static Broadcast fetchByapplicationId_First(
		long applicationId, OrderByComparator<Broadcast> orderByComparator) {

		return getPersistence().fetchByapplicationId_First(
			applicationId, orderByComparator);
	}

	/**
	 * Returns the last broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast
	 * @throws NoSuchBroadcastException if a matching broadcast could not be found
	 */
	public static Broadcast findByapplicationId_Last(
			long applicationId, OrderByComparator<Broadcast> orderByComparator)
		throws com.weprode.nero.application.exception.NoSuchBroadcastException {

		return getPersistence().findByapplicationId_Last(
			applicationId, orderByComparator);
	}

	/**
	 * Returns the last broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast, or <code>null</code> if a matching broadcast could not be found
	 */
	public static Broadcast fetchByapplicationId_Last(
		long applicationId, OrderByComparator<Broadcast> orderByComparator) {

		return getPersistence().fetchByapplicationId_Last(
			applicationId, orderByComparator);
	}

	/**
	 * Returns the broadcasts before and after the current broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param broadcastId the primary key of the current broadcast
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next broadcast
	 * @throws NoSuchBroadcastException if a broadcast with the primary key could not be found
	 */
	public static Broadcast[] findByapplicationId_PrevAndNext(
			long broadcastId, long applicationId,
			OrderByComparator<Broadcast> orderByComparator)
		throws com.weprode.nero.application.exception.NoSuchBroadcastException {

		return getPersistence().findByapplicationId_PrevAndNext(
			broadcastId, applicationId, orderByComparator);
	}

	/**
	 * Removes all the broadcasts where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 */
	public static void removeByapplicationId(long applicationId) {
		getPersistence().removeByapplicationId(applicationId);
	}

	/**
	 * Returns the number of broadcasts where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching broadcasts
	 */
	public static int countByapplicationId(long applicationId) {
		return getPersistence().countByapplicationId(applicationId);
	}

	/**
	 * Caches the broadcast in the entity cache if it is enabled.
	 *
	 * @param broadcast the broadcast
	 */
	public static void cacheResult(Broadcast broadcast) {
		getPersistence().cacheResult(broadcast);
	}

	/**
	 * Caches the broadcasts in the entity cache if it is enabled.
	 *
	 * @param broadcasts the broadcasts
	 */
	public static void cacheResult(List<Broadcast> broadcasts) {
		getPersistence().cacheResult(broadcasts);
	}

	/**
	 * Creates a new broadcast with the primary key. Does not add the broadcast to the database.
	 *
	 * @param broadcastId the primary key for the new broadcast
	 * @return the new broadcast
	 */
	public static Broadcast create(long broadcastId) {
		return getPersistence().create(broadcastId);
	}

	/**
	 * Removes the broadcast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast that was removed
	 * @throws NoSuchBroadcastException if a broadcast with the primary key could not be found
	 */
	public static Broadcast remove(long broadcastId)
		throws com.weprode.nero.application.exception.NoSuchBroadcastException {

		return getPersistence().remove(broadcastId);
	}

	public static Broadcast updateImpl(Broadcast broadcast) {
		return getPersistence().updateImpl(broadcast);
	}

	/**
	 * Returns the broadcast with the primary key or throws a <code>NoSuchBroadcastException</code> if it could not be found.
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast
	 * @throws NoSuchBroadcastException if a broadcast with the primary key could not be found
	 */
	public static Broadcast findByPrimaryKey(long broadcastId)
		throws com.weprode.nero.application.exception.NoSuchBroadcastException {

		return getPersistence().findByPrimaryKey(broadcastId);
	}

	/**
	 * Returns the broadcast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast, or <code>null</code> if a broadcast with the primary key could not be found
	 */
	public static Broadcast fetchByPrimaryKey(long broadcastId) {
		return getPersistence().fetchByPrimaryKey(broadcastId);
	}

	/**
	 * Returns all the broadcasts.
	 *
	 * @return the broadcasts
	 */
	public static List<Broadcast> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the broadcasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @return the range of broadcasts
	 */
	public static List<Broadcast> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the broadcasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of broadcasts
	 */
	public static List<Broadcast> findAll(
		int start, int end, OrderByComparator<Broadcast> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the broadcasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of broadcasts
	 */
	public static List<Broadcast> findAll(
		int start, int end, OrderByComparator<Broadcast> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the broadcasts from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of broadcasts.
	 *
	 * @return the number of broadcasts
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static BroadcastPersistence getPersistence() {
		return _persistence;
	}

	private static volatile BroadcastPersistence _persistence;

}