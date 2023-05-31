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

package com.weprode.nero.search.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.search.model.SearchHistory;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the search history service. This utility wraps <code>com.weprode.nero.search.service.persistence.impl.SearchHistoryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SearchHistoryPersistence
 * @generated
 */
public class SearchHistoryUtil {

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
	public static void clearCache(SearchHistory searchHistory) {
		getPersistence().clearCache(searchHistory);
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
	public static Map<Serializable, SearchHistory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SearchHistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SearchHistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SearchHistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SearchHistory> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SearchHistory update(SearchHistory searchHistory) {
		return getPersistence().update(searchHistory);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SearchHistory update(
		SearchHistory searchHistory, ServiceContext serviceContext) {

		return getPersistence().update(searchHistory, serviceContext);
	}

	/**
	 * Returns all the search histories where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching search histories
	 */
	public static List<SearchHistory> findByuserId(long userId) {
		return getPersistence().findByuserId(userId);
	}

	/**
	 * Returns a range of all the search histories where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SearchHistoryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of search histories
	 * @param end the upper bound of the range of search histories (not inclusive)
	 * @return the range of matching search histories
	 */
	public static List<SearchHistory> findByuserId(
		long userId, int start, int end) {

		return getPersistence().findByuserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the search histories where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SearchHistoryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of search histories
	 * @param end the upper bound of the range of search histories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching search histories
	 */
	public static List<SearchHistory> findByuserId(
		long userId, int start, int end,
		OrderByComparator<SearchHistory> orderByComparator) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the search histories where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SearchHistoryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of search histories
	 * @param end the upper bound of the range of search histories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching search histories
	 */
	public static List<SearchHistory> findByuserId(
		long userId, int start, int end,
		OrderByComparator<SearchHistory> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first search history in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching search history
	 * @throws NoSuchHistoryException if a matching search history could not be found
	 */
	public static SearchHistory findByuserId_First(
			long userId, OrderByComparator<SearchHistory> orderByComparator)
		throws com.weprode.nero.search.exception.NoSuchHistoryException {

		return getPersistence().findByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first search history in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching search history, or <code>null</code> if a matching search history could not be found
	 */
	public static SearchHistory fetchByuserId_First(
		long userId, OrderByComparator<SearchHistory> orderByComparator) {

		return getPersistence().fetchByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last search history in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching search history
	 * @throws NoSuchHistoryException if a matching search history could not be found
	 */
	public static SearchHistory findByuserId_Last(
			long userId, OrderByComparator<SearchHistory> orderByComparator)
		throws com.weprode.nero.search.exception.NoSuchHistoryException {

		return getPersistence().findByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last search history in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching search history, or <code>null</code> if a matching search history could not be found
	 */
	public static SearchHistory fetchByuserId_Last(
		long userId, OrderByComparator<SearchHistory> orderByComparator) {

		return getPersistence().fetchByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the search histories before and after the current search history in the ordered set where userId = &#63;.
	 *
	 * @param searchHistoryId the primary key of the current search history
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next search history
	 * @throws NoSuchHistoryException if a search history with the primary key could not be found
	 */
	public static SearchHistory[] findByuserId_PrevAndNext(
			long searchHistoryId, long userId,
			OrderByComparator<SearchHistory> orderByComparator)
		throws com.weprode.nero.search.exception.NoSuchHistoryException {

		return getPersistence().findByuserId_PrevAndNext(
			searchHistoryId, userId, orderByComparator);
	}

	/**
	 * Removes all the search histories where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByuserId(long userId) {
		getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of search histories where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching search histories
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Caches the search history in the entity cache if it is enabled.
	 *
	 * @param searchHistory the search history
	 */
	public static void cacheResult(SearchHistory searchHistory) {
		getPersistence().cacheResult(searchHistory);
	}

	/**
	 * Caches the search histories in the entity cache if it is enabled.
	 *
	 * @param searchHistories the search histories
	 */
	public static void cacheResult(List<SearchHistory> searchHistories) {
		getPersistence().cacheResult(searchHistories);
	}

	/**
	 * Creates a new search history with the primary key. Does not add the search history to the database.
	 *
	 * @param searchHistoryId the primary key for the new search history
	 * @return the new search history
	 */
	public static SearchHistory create(long searchHistoryId) {
		return getPersistence().create(searchHistoryId);
	}

	/**
	 * Removes the search history with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param searchHistoryId the primary key of the search history
	 * @return the search history that was removed
	 * @throws NoSuchHistoryException if a search history with the primary key could not be found
	 */
	public static SearchHistory remove(long searchHistoryId)
		throws com.weprode.nero.search.exception.NoSuchHistoryException {

		return getPersistence().remove(searchHistoryId);
	}

	public static SearchHistory updateImpl(SearchHistory searchHistory) {
		return getPersistence().updateImpl(searchHistory);
	}

	/**
	 * Returns the search history with the primary key or throws a <code>NoSuchHistoryException</code> if it could not be found.
	 *
	 * @param searchHistoryId the primary key of the search history
	 * @return the search history
	 * @throws NoSuchHistoryException if a search history with the primary key could not be found
	 */
	public static SearchHistory findByPrimaryKey(long searchHistoryId)
		throws com.weprode.nero.search.exception.NoSuchHistoryException {

		return getPersistence().findByPrimaryKey(searchHistoryId);
	}

	/**
	 * Returns the search history with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param searchHistoryId the primary key of the search history
	 * @return the search history, or <code>null</code> if a search history with the primary key could not be found
	 */
	public static SearchHistory fetchByPrimaryKey(long searchHistoryId) {
		return getPersistence().fetchByPrimaryKey(searchHistoryId);
	}

	/**
	 * Returns all the search histories.
	 *
	 * @return the search histories
	 */
	public static List<SearchHistory> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the search histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SearchHistoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of search histories
	 * @param end the upper bound of the range of search histories (not inclusive)
	 * @return the range of search histories
	 */
	public static List<SearchHistory> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the search histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SearchHistoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of search histories
	 * @param end the upper bound of the range of search histories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of search histories
	 */
	public static List<SearchHistory> findAll(
		int start, int end,
		OrderByComparator<SearchHistory> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the search histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SearchHistoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of search histories
	 * @param end the upper bound of the range of search histories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of search histories
	 */
	public static List<SearchHistory> findAll(
		int start, int end, OrderByComparator<SearchHistory> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the search histories from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of search histories.
	 *
	 * @return the number of search histories
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SearchHistoryPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SearchHistoryPersistence _persistence;

}