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

package com.weprode.nero.news.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.news.model.NewsRead;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the news read service. This utility wraps <code>com.weprode.nero.news.service.persistence.impl.NewsReadPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsReadPersistence
 * @generated
 */
public class NewsReadUtil {

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
	public static void clearCache(NewsRead newsRead) {
		getPersistence().clearCache(newsRead);
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
	public static Map<Serializable, NewsRead> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<NewsRead> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NewsRead> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NewsRead> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NewsRead> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NewsRead update(NewsRead newsRead) {
		return getPersistence().update(newsRead);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NewsRead update(
		NewsRead newsRead, ServiceContext serviceContext) {

		return getPersistence().update(newsRead, serviceContext);
	}

	/**
	 * Returns all the news reads where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the matching news reads
	 */
	public static List<NewsRead> findBynewsId(long newsId) {
		return getPersistence().findBynewsId(newsId);
	}

	/**
	 * Returns a range of all the news reads where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @return the range of matching news reads
	 */
	public static List<NewsRead> findBynewsId(long newsId, int start, int end) {
		return getPersistence().findBynewsId(newsId, start, end);
	}

	/**
	 * Returns an ordered range of all the news reads where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news reads
	 */
	public static List<NewsRead> findBynewsId(
		long newsId, int start, int end,
		OrderByComparator<NewsRead> orderByComparator) {

		return getPersistence().findBynewsId(
			newsId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the news reads where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news reads
	 */
	public static List<NewsRead> findBynewsId(
		long newsId, int start, int end,
		OrderByComparator<NewsRead> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBynewsId(
			newsId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news read
	 * @throws NoSuchReadException if a matching news read could not be found
	 */
	public static NewsRead findBynewsId_First(
			long newsId, OrderByComparator<NewsRead> orderByComparator)
		throws com.weprode.nero.news.exception.NoSuchReadException {

		return getPersistence().findBynewsId_First(newsId, orderByComparator);
	}

	/**
	 * Returns the first news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news read, or <code>null</code> if a matching news read could not be found
	 */
	public static NewsRead fetchBynewsId_First(
		long newsId, OrderByComparator<NewsRead> orderByComparator) {

		return getPersistence().fetchBynewsId_First(newsId, orderByComparator);
	}

	/**
	 * Returns the last news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news read
	 * @throws NoSuchReadException if a matching news read could not be found
	 */
	public static NewsRead findBynewsId_Last(
			long newsId, OrderByComparator<NewsRead> orderByComparator)
		throws com.weprode.nero.news.exception.NoSuchReadException {

		return getPersistence().findBynewsId_Last(newsId, orderByComparator);
	}

	/**
	 * Returns the last news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news read, or <code>null</code> if a matching news read could not be found
	 */
	public static NewsRead fetchBynewsId_Last(
		long newsId, OrderByComparator<NewsRead> orderByComparator) {

		return getPersistence().fetchBynewsId_Last(newsId, orderByComparator);
	}

	/**
	 * Returns the news reads before and after the current news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsReadPK the primary key of the current news read
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news read
	 * @throws NoSuchReadException if a news read with the primary key could not be found
	 */
	public static NewsRead[] findBynewsId_PrevAndNext(
			NewsReadPK newsReadPK, long newsId,
			OrderByComparator<NewsRead> orderByComparator)
		throws com.weprode.nero.news.exception.NoSuchReadException {

		return getPersistence().findBynewsId_PrevAndNext(
			newsReadPK, newsId, orderByComparator);
	}

	/**
	 * Removes all the news reads where newsId = &#63; from the database.
	 *
	 * @param newsId the news ID
	 */
	public static void removeBynewsId(long newsId) {
		getPersistence().removeBynewsId(newsId);
	}

	/**
	 * Returns the number of news reads where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the number of matching news reads
	 */
	public static int countBynewsId(long newsId) {
		return getPersistence().countBynewsId(newsId);
	}

	/**
	 * Caches the news read in the entity cache if it is enabled.
	 *
	 * @param newsRead the news read
	 */
	public static void cacheResult(NewsRead newsRead) {
		getPersistence().cacheResult(newsRead);
	}

	/**
	 * Caches the news reads in the entity cache if it is enabled.
	 *
	 * @param newsReads the news reads
	 */
	public static void cacheResult(List<NewsRead> newsReads) {
		getPersistence().cacheResult(newsReads);
	}

	/**
	 * Creates a new news read with the primary key. Does not add the news read to the database.
	 *
	 * @param newsReadPK the primary key for the new news read
	 * @return the new news read
	 */
	public static NewsRead create(NewsReadPK newsReadPK) {
		return getPersistence().create(newsReadPK);
	}

	/**
	 * Removes the news read with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsReadPK the primary key of the news read
	 * @return the news read that was removed
	 * @throws NoSuchReadException if a news read with the primary key could not be found
	 */
	public static NewsRead remove(NewsReadPK newsReadPK)
		throws com.weprode.nero.news.exception.NoSuchReadException {

		return getPersistence().remove(newsReadPK);
	}

	public static NewsRead updateImpl(NewsRead newsRead) {
		return getPersistence().updateImpl(newsRead);
	}

	/**
	 * Returns the news read with the primary key or throws a <code>NoSuchReadException</code> if it could not be found.
	 *
	 * @param newsReadPK the primary key of the news read
	 * @return the news read
	 * @throws NoSuchReadException if a news read with the primary key could not be found
	 */
	public static NewsRead findByPrimaryKey(NewsReadPK newsReadPK)
		throws com.weprode.nero.news.exception.NoSuchReadException {

		return getPersistence().findByPrimaryKey(newsReadPK);
	}

	/**
	 * Returns the news read with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsReadPK the primary key of the news read
	 * @return the news read, or <code>null</code> if a news read with the primary key could not be found
	 */
	public static NewsRead fetchByPrimaryKey(NewsReadPK newsReadPK) {
		return getPersistence().fetchByPrimaryKey(newsReadPK);
	}

	/**
	 * Returns all the news reads.
	 *
	 * @return the news reads
	 */
	public static List<NewsRead> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the news reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @return the range of news reads
	 */
	public static List<NewsRead> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the news reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of news reads
	 */
	public static List<NewsRead> findAll(
		int start, int end, OrderByComparator<NewsRead> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the news reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news reads
	 * @param end the upper bound of the range of news reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of news reads
	 */
	public static List<NewsRead> findAll(
		int start, int end, OrderByComparator<NewsRead> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the news reads from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of news reads.
	 *
	 * @return the number of news reads
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static NewsReadPersistence getPersistence() {
		return _persistence;
	}

	private static volatile NewsReadPersistence _persistence;

}