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

package com.weprode.facile.news.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.news.model.NewsPopulation;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the news population service. This utility wraps <code>com.weprode.facile.news.service.persistence.impl.NewsPopulationPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsPopulationPersistence
 * @generated
 */
public class NewsPopulationUtil {

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
	public static void clearCache(NewsPopulation newsPopulation) {
		getPersistence().clearCache(newsPopulation);
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
	public static Map<Serializable, NewsPopulation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<NewsPopulation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NewsPopulation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NewsPopulation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NewsPopulation update(NewsPopulation newsPopulation) {
		return getPersistence().update(newsPopulation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NewsPopulation update(
		NewsPopulation newsPopulation, ServiceContext serviceContext) {

		return getPersistence().update(newsPopulation, serviceContext);
	}

	/**
	 * Returns all the news populations where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @return the matching news populations
	 */
	public static List<NewsPopulation> findBygroupId_roleId(
		long groupId, long roleId) {

		return getPersistence().findBygroupId_roleId(groupId, roleId);
	}

	/**
	 * Returns a range of all the news populations where groupId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @return the range of matching news populations
	 */
	public static List<NewsPopulation> findBygroupId_roleId(
		long groupId, long roleId, int start, int end) {

		return getPersistence().findBygroupId_roleId(
			groupId, roleId, start, end);
	}

	/**
	 * Returns an ordered range of all the news populations where groupId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news populations
	 */
	public static List<NewsPopulation> findBygroupId_roleId(
		long groupId, long roleId, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator) {

		return getPersistence().findBygroupId_roleId(
			groupId, roleId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the news populations where groupId = &#63; and roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news populations
	 */
	public static List<NewsPopulation> findBygroupId_roleId(
		long groupId, long roleId, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBygroupId_roleId(
			groupId, roleId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	public static NewsPopulation findBygroupId_roleId_First(
			long groupId, long roleId,
			OrderByComparator<NewsPopulation> orderByComparator)
		throws com.weprode.facile.news.exception.NoSuchPopulationException {

		return getPersistence().findBygroupId_roleId_First(
			groupId, roleId, orderByComparator);
	}

	/**
	 * Returns the first news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population, or <code>null</code> if a matching news population could not be found
	 */
	public static NewsPopulation fetchBygroupId_roleId_First(
		long groupId, long roleId,
		OrderByComparator<NewsPopulation> orderByComparator) {

		return getPersistence().fetchBygroupId_roleId_First(
			groupId, roleId, orderByComparator);
	}

	/**
	 * Returns the last news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	public static NewsPopulation findBygroupId_roleId_Last(
			long groupId, long roleId,
			OrderByComparator<NewsPopulation> orderByComparator)
		throws com.weprode.facile.news.exception.NoSuchPopulationException {

		return getPersistence().findBygroupId_roleId_Last(
			groupId, roleId, orderByComparator);
	}

	/**
	 * Returns the last news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population, or <code>null</code> if a matching news population could not be found
	 */
	public static NewsPopulation fetchBygroupId_roleId_Last(
		long groupId, long roleId,
		OrderByComparator<NewsPopulation> orderByComparator) {

		return getPersistence().fetchBygroupId_roleId_Last(
			groupId, roleId, orderByComparator);
	}

	/**
	 * Returns the news populations before and after the current news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param newsPopulationPK the primary key of the current news population
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news population
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	public static NewsPopulation[] findBygroupId_roleId_PrevAndNext(
			NewsPopulationPK newsPopulationPK, long groupId, long roleId,
			OrderByComparator<NewsPopulation> orderByComparator)
		throws com.weprode.facile.news.exception.NoSuchPopulationException {

		return getPersistence().findBygroupId_roleId_PrevAndNext(
			newsPopulationPK, groupId, roleId, orderByComparator);
	}

	/**
	 * Removes all the news populations where groupId = &#63; and roleId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 */
	public static void removeBygroupId_roleId(long groupId, long roleId) {
		getPersistence().removeBygroupId_roleId(groupId, roleId);
	}

	/**
	 * Returns the number of news populations where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @return the number of matching news populations
	 */
	public static int countBygroupId_roleId(long groupId, long roleId) {
		return getPersistence().countBygroupId_roleId(groupId, roleId);
	}

	/**
	 * Returns all the news populations where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the matching news populations
	 */
	public static List<NewsPopulation> findBynewsId(long newsId) {
		return getPersistence().findBynewsId(newsId);
	}

	/**
	 * Returns a range of all the news populations where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @return the range of matching news populations
	 */
	public static List<NewsPopulation> findBynewsId(
		long newsId, int start, int end) {

		return getPersistence().findBynewsId(newsId, start, end);
	}

	/**
	 * Returns an ordered range of all the news populations where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news populations
	 */
	public static List<NewsPopulation> findBynewsId(
		long newsId, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator) {

		return getPersistence().findBynewsId(
			newsId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the news populations where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news populations
	 */
	public static List<NewsPopulation> findBynewsId(
		long newsId, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBynewsId(
			newsId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	public static NewsPopulation findBynewsId_First(
			long newsId, OrderByComparator<NewsPopulation> orderByComparator)
		throws com.weprode.facile.news.exception.NoSuchPopulationException {

		return getPersistence().findBynewsId_First(newsId, orderByComparator);
	}

	/**
	 * Returns the first news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population, or <code>null</code> if a matching news population could not be found
	 */
	public static NewsPopulation fetchBynewsId_First(
		long newsId, OrderByComparator<NewsPopulation> orderByComparator) {

		return getPersistence().fetchBynewsId_First(newsId, orderByComparator);
	}

	/**
	 * Returns the last news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	public static NewsPopulation findBynewsId_Last(
			long newsId, OrderByComparator<NewsPopulation> orderByComparator)
		throws com.weprode.facile.news.exception.NoSuchPopulationException {

		return getPersistence().findBynewsId_Last(newsId, orderByComparator);
	}

	/**
	 * Returns the last news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population, or <code>null</code> if a matching news population could not be found
	 */
	public static NewsPopulation fetchBynewsId_Last(
		long newsId, OrderByComparator<NewsPopulation> orderByComparator) {

		return getPersistence().fetchBynewsId_Last(newsId, orderByComparator);
	}

	/**
	 * Returns the news populations before and after the current news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsPopulationPK the primary key of the current news population
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news population
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	public static NewsPopulation[] findBynewsId_PrevAndNext(
			NewsPopulationPK newsPopulationPK, long newsId,
			OrderByComparator<NewsPopulation> orderByComparator)
		throws com.weprode.facile.news.exception.NoSuchPopulationException {

		return getPersistence().findBynewsId_PrevAndNext(
			newsPopulationPK, newsId, orderByComparator);
	}

	/**
	 * Removes all the news populations where newsId = &#63; from the database.
	 *
	 * @param newsId the news ID
	 */
	public static void removeBynewsId(long newsId) {
		getPersistence().removeBynewsId(newsId);
	}

	/**
	 * Returns the number of news populations where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the number of matching news populations
	 */
	public static int countBynewsId(long newsId) {
		return getPersistence().countBynewsId(newsId);
	}

	/**
	 * Returns all the news populations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching news populations
	 */
	public static List<NewsPopulation> findBygroupId(long groupId) {
		return getPersistence().findBygroupId(groupId);
	}

	/**
	 * Returns a range of all the news populations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @return the range of matching news populations
	 */
	public static List<NewsPopulation> findBygroupId(
		long groupId, int start, int end) {

		return getPersistence().findBygroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the news populations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news populations
	 */
	public static List<NewsPopulation> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator) {

		return getPersistence().findBygroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the news populations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news populations
	 */
	public static List<NewsPopulation> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBygroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first news population in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	public static NewsPopulation findBygroupId_First(
			long groupId, OrderByComparator<NewsPopulation> orderByComparator)
		throws com.weprode.facile.news.exception.NoSuchPopulationException {

		return getPersistence().findBygroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first news population in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population, or <code>null</code> if a matching news population could not be found
	 */
	public static NewsPopulation fetchBygroupId_First(
		long groupId, OrderByComparator<NewsPopulation> orderByComparator) {

		return getPersistence().fetchBygroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last news population in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	public static NewsPopulation findBygroupId_Last(
			long groupId, OrderByComparator<NewsPopulation> orderByComparator)
		throws com.weprode.facile.news.exception.NoSuchPopulationException {

		return getPersistence().findBygroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last news population in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population, or <code>null</code> if a matching news population could not be found
	 */
	public static NewsPopulation fetchBygroupId_Last(
		long groupId, OrderByComparator<NewsPopulation> orderByComparator) {

		return getPersistence().fetchBygroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the news populations before and after the current news population in the ordered set where groupId = &#63;.
	 *
	 * @param newsPopulationPK the primary key of the current news population
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news population
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	public static NewsPopulation[] findBygroupId_PrevAndNext(
			NewsPopulationPK newsPopulationPK, long groupId,
			OrderByComparator<NewsPopulation> orderByComparator)
		throws com.weprode.facile.news.exception.NoSuchPopulationException {

		return getPersistence().findBygroupId_PrevAndNext(
			newsPopulationPK, groupId, orderByComparator);
	}

	/**
	 * Removes all the news populations where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeBygroupId(long groupId) {
		getPersistence().removeBygroupId(groupId);
	}

	/**
	 * Returns the number of news populations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching news populations
	 */
	public static int countBygroupId(long groupId) {
		return getPersistence().countBygroupId(groupId);
	}

	/**
	 * Caches the news population in the entity cache if it is enabled.
	 *
	 * @param newsPopulation the news population
	 */
	public static void cacheResult(NewsPopulation newsPopulation) {
		getPersistence().cacheResult(newsPopulation);
	}

	/**
	 * Caches the news populations in the entity cache if it is enabled.
	 *
	 * @param newsPopulations the news populations
	 */
	public static void cacheResult(List<NewsPopulation> newsPopulations) {
		getPersistence().cacheResult(newsPopulations);
	}

	/**
	 * Creates a new news population with the primary key. Does not add the news population to the database.
	 *
	 * @param newsPopulationPK the primary key for the new news population
	 * @return the new news population
	 */
	public static NewsPopulation create(NewsPopulationPK newsPopulationPK) {
		return getPersistence().create(newsPopulationPK);
	}

	/**
	 * Removes the news population with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsPopulationPK the primary key of the news population
	 * @return the news population that was removed
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	public static NewsPopulation remove(NewsPopulationPK newsPopulationPK)
		throws com.weprode.facile.news.exception.NoSuchPopulationException {

		return getPersistence().remove(newsPopulationPK);
	}

	public static NewsPopulation updateImpl(NewsPopulation newsPopulation) {
		return getPersistence().updateImpl(newsPopulation);
	}

	/**
	 * Returns the news population with the primary key or throws a <code>NoSuchPopulationException</code> if it could not be found.
	 *
	 * @param newsPopulationPK the primary key of the news population
	 * @return the news population
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	public static NewsPopulation findByPrimaryKey(
			NewsPopulationPK newsPopulationPK)
		throws com.weprode.facile.news.exception.NoSuchPopulationException {

		return getPersistence().findByPrimaryKey(newsPopulationPK);
	}

	/**
	 * Returns the news population with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsPopulationPK the primary key of the news population
	 * @return the news population, or <code>null</code> if a news population with the primary key could not be found
	 */
	public static NewsPopulation fetchByPrimaryKey(
		NewsPopulationPK newsPopulationPK) {

		return getPersistence().fetchByPrimaryKey(newsPopulationPK);
	}

	/**
	 * Returns all the news populations.
	 *
	 * @return the news populations
	 */
	public static List<NewsPopulation> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the news populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @return the range of news populations
	 */
	public static List<NewsPopulation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the news populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of news populations
	 */
	public static List<NewsPopulation> findAll(
		int start, int end,
		OrderByComparator<NewsPopulation> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the news populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news populations
	 * @param end the upper bound of the range of news populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of news populations
	 */
	public static List<NewsPopulation> findAll(
		int start, int end, OrderByComparator<NewsPopulation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the news populations from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of news populations.
	 *
	 * @return the number of news populations
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static NewsPopulationPersistence getPersistence() {
		return _persistence;
	}

	private static volatile NewsPopulationPersistence _persistence;

}