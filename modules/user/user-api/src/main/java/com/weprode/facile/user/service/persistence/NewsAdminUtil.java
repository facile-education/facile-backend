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

package com.weprode.facile.user.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.user.model.NewsAdmin;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the news admin service. This utility wraps <code>com.weprode.facile.user.service.persistence.impl.NewsAdminPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsAdminPersistence
 * @generated
 */
public class NewsAdminUtil {

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
	public static void clearCache(NewsAdmin newsAdmin) {
		getPersistence().clearCache(newsAdmin);
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
	public static Map<Serializable, NewsAdmin> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<NewsAdmin> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NewsAdmin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NewsAdmin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NewsAdmin> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NewsAdmin update(NewsAdmin newsAdmin) {
		return getPersistence().update(newsAdmin);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NewsAdmin update(
		NewsAdmin newsAdmin, ServiceContext serviceContext) {

		return getPersistence().update(newsAdmin, serviceContext);
	}

	/**
	 * Returns all the news admins where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching news admins
	 */
	public static List<NewsAdmin> findByuserId(long userId) {
		return getPersistence().findByuserId(userId);
	}

	/**
	 * Returns a range of all the news admins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @return the range of matching news admins
	 */
	public static List<NewsAdmin> findByuserId(
		long userId, int start, int end) {

		return getPersistence().findByuserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the news admins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news admins
	 */
	public static List<NewsAdmin> findByuserId(
		long userId, int start, int end,
		OrderByComparator<NewsAdmin> orderByComparator) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the news admins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news admins
	 */
	public static List<NewsAdmin> findByuserId(
		long userId, int start, int end,
		OrderByComparator<NewsAdmin> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first news admin in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news admin
	 * @throws NoSuchNewsAdminException if a matching news admin could not be found
	 */
	public static NewsAdmin findByuserId_First(
			long userId, OrderByComparator<NewsAdmin> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchNewsAdminException {

		return getPersistence().findByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first news admin in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news admin, or <code>null</code> if a matching news admin could not be found
	 */
	public static NewsAdmin fetchByuserId_First(
		long userId, OrderByComparator<NewsAdmin> orderByComparator) {

		return getPersistence().fetchByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last news admin in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news admin
	 * @throws NoSuchNewsAdminException if a matching news admin could not be found
	 */
	public static NewsAdmin findByuserId_Last(
			long userId, OrderByComparator<NewsAdmin> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchNewsAdminException {

		return getPersistence().findByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last news admin in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news admin, or <code>null</code> if a matching news admin could not be found
	 */
	public static NewsAdmin fetchByuserId_Last(
		long userId, OrderByComparator<NewsAdmin> orderByComparator) {

		return getPersistence().fetchByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the news admins before and after the current news admin in the ordered set where userId = &#63;.
	 *
	 * @param newsAdminId the primary key of the current news admin
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news admin
	 * @throws NoSuchNewsAdminException if a news admin with the primary key could not be found
	 */
	public static NewsAdmin[] findByuserId_PrevAndNext(
			long newsAdminId, long userId,
			OrderByComparator<NewsAdmin> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchNewsAdminException {

		return getPersistence().findByuserId_PrevAndNext(
			newsAdminId, userId, orderByComparator);
	}

	/**
	 * Removes all the news admins where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByuserId(long userId) {
		getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of news admins where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching news admins
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Returns all the news admins where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching news admins
	 */
	public static List<NewsAdmin> findByschoolId(long schoolId) {
		return getPersistence().findByschoolId(schoolId);
	}

	/**
	 * Returns a range of all the news admins where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @return the range of matching news admins
	 */
	public static List<NewsAdmin> findByschoolId(
		long schoolId, int start, int end) {

		return getPersistence().findByschoolId(schoolId, start, end);
	}

	/**
	 * Returns an ordered range of all the news admins where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news admins
	 */
	public static List<NewsAdmin> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<NewsAdmin> orderByComparator) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the news admins where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news admins
	 */
	public static List<NewsAdmin> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<NewsAdmin> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first news admin in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news admin
	 * @throws NoSuchNewsAdminException if a matching news admin could not be found
	 */
	public static NewsAdmin findByschoolId_First(
			long schoolId, OrderByComparator<NewsAdmin> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchNewsAdminException {

		return getPersistence().findByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the first news admin in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news admin, or <code>null</code> if a matching news admin could not be found
	 */
	public static NewsAdmin fetchByschoolId_First(
		long schoolId, OrderByComparator<NewsAdmin> orderByComparator) {

		return getPersistence().fetchByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last news admin in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news admin
	 * @throws NoSuchNewsAdminException if a matching news admin could not be found
	 */
	public static NewsAdmin findByschoolId_Last(
			long schoolId, OrderByComparator<NewsAdmin> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchNewsAdminException {

		return getPersistence().findByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last news admin in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news admin, or <code>null</code> if a matching news admin could not be found
	 */
	public static NewsAdmin fetchByschoolId_Last(
		long schoolId, OrderByComparator<NewsAdmin> orderByComparator) {

		return getPersistence().fetchByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the news admins before and after the current news admin in the ordered set where schoolId = &#63;.
	 *
	 * @param newsAdminId the primary key of the current news admin
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news admin
	 * @throws NoSuchNewsAdminException if a news admin with the primary key could not be found
	 */
	public static NewsAdmin[] findByschoolId_PrevAndNext(
			long newsAdminId, long schoolId,
			OrderByComparator<NewsAdmin> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchNewsAdminException {

		return getPersistence().findByschoolId_PrevAndNext(
			newsAdminId, schoolId, orderByComparator);
	}

	/**
	 * Removes all the news admins where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public static void removeByschoolId(long schoolId) {
		getPersistence().removeByschoolId(schoolId);
	}

	/**
	 * Returns the number of news admins where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching news admins
	 */
	public static int countByschoolId(long schoolId) {
		return getPersistence().countByschoolId(schoolId);
	}

	/**
	 * Caches the news admin in the entity cache if it is enabled.
	 *
	 * @param newsAdmin the news admin
	 */
	public static void cacheResult(NewsAdmin newsAdmin) {
		getPersistence().cacheResult(newsAdmin);
	}

	/**
	 * Caches the news admins in the entity cache if it is enabled.
	 *
	 * @param newsAdmins the news admins
	 */
	public static void cacheResult(List<NewsAdmin> newsAdmins) {
		getPersistence().cacheResult(newsAdmins);
	}

	/**
	 * Creates a new news admin with the primary key. Does not add the news admin to the database.
	 *
	 * @param newsAdminId the primary key for the new news admin
	 * @return the new news admin
	 */
	public static NewsAdmin create(long newsAdminId) {
		return getPersistence().create(newsAdminId);
	}

	/**
	 * Removes the news admin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsAdminId the primary key of the news admin
	 * @return the news admin that was removed
	 * @throws NoSuchNewsAdminException if a news admin with the primary key could not be found
	 */
	public static NewsAdmin remove(long newsAdminId)
		throws com.weprode.facile.user.exception.NoSuchNewsAdminException {

		return getPersistence().remove(newsAdminId);
	}

	public static NewsAdmin updateImpl(NewsAdmin newsAdmin) {
		return getPersistence().updateImpl(newsAdmin);
	}

	/**
	 * Returns the news admin with the primary key or throws a <code>NoSuchNewsAdminException</code> if it could not be found.
	 *
	 * @param newsAdminId the primary key of the news admin
	 * @return the news admin
	 * @throws NoSuchNewsAdminException if a news admin with the primary key could not be found
	 */
	public static NewsAdmin findByPrimaryKey(long newsAdminId)
		throws com.weprode.facile.user.exception.NoSuchNewsAdminException {

		return getPersistence().findByPrimaryKey(newsAdminId);
	}

	/**
	 * Returns the news admin with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsAdminId the primary key of the news admin
	 * @return the news admin, or <code>null</code> if a news admin with the primary key could not be found
	 */
	public static NewsAdmin fetchByPrimaryKey(long newsAdminId) {
		return getPersistence().fetchByPrimaryKey(newsAdminId);
	}

	/**
	 * Returns all the news admins.
	 *
	 * @return the news admins
	 */
	public static List<NewsAdmin> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the news admins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @return the range of news admins
	 */
	public static List<NewsAdmin> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the news admins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of news admins
	 */
	public static List<NewsAdmin> findAll(
		int start, int end, OrderByComparator<NewsAdmin> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the news admins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of news admins
	 */
	public static List<NewsAdmin> findAll(
		int start, int end, OrderByComparator<NewsAdmin> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the news admins from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of news admins.
	 *
	 * @return the number of news admins
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static NewsAdminPersistence getPersistence() {
		return _persistence;
	}

	private static volatile NewsAdminPersistence _persistence;

}