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

import com.weprode.nero.news.model.NewsAttachedFile;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the news attached file service. This utility wraps <code>com.weprode.nero.news.service.persistence.impl.NewsAttachedFilePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsAttachedFilePersistence
 * @generated
 */
public class NewsAttachedFileUtil {

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
	public static void clearCache(NewsAttachedFile newsAttachedFile) {
		getPersistence().clearCache(newsAttachedFile);
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
	public static Map<Serializable, NewsAttachedFile> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<NewsAttachedFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NewsAttachedFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NewsAttachedFile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NewsAttachedFile> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NewsAttachedFile update(NewsAttachedFile newsAttachedFile) {
		return getPersistence().update(newsAttachedFile);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NewsAttachedFile update(
		NewsAttachedFile newsAttachedFile, ServiceContext serviceContext) {

		return getPersistence().update(newsAttachedFile, serviceContext);
	}

	/**
	 * Returns all the news attached files where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the matching news attached files
	 */
	public static List<NewsAttachedFile> findBynewsId(long newsId) {
		return getPersistence().findBynewsId(newsId);
	}

	/**
	 * Returns a range of all the news attached files where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @return the range of matching news attached files
	 */
	public static List<NewsAttachedFile> findBynewsId(
		long newsId, int start, int end) {

		return getPersistence().findBynewsId(newsId, start, end);
	}

	/**
	 * Returns an ordered range of all the news attached files where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news attached files
	 */
	public static List<NewsAttachedFile> findBynewsId(
		long newsId, int start, int end,
		OrderByComparator<NewsAttachedFile> orderByComparator) {

		return getPersistence().findBynewsId(
			newsId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the news attached files where newsId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param newsId the news ID
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news attached files
	 */
	public static List<NewsAttachedFile> findBynewsId(
		long newsId, int start, int end,
		OrderByComparator<NewsAttachedFile> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBynewsId(
			newsId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news attached file
	 * @throws NoSuchAttachedFileException if a matching news attached file could not be found
	 */
	public static NewsAttachedFile findBynewsId_First(
			long newsId, OrderByComparator<NewsAttachedFile> orderByComparator)
		throws com.weprode.nero.news.exception.NoSuchAttachedFileException {

		return getPersistence().findBynewsId_First(newsId, orderByComparator);
	}

	/**
	 * Returns the first news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news attached file, or <code>null</code> if a matching news attached file could not be found
	 */
	public static NewsAttachedFile fetchBynewsId_First(
		long newsId, OrderByComparator<NewsAttachedFile> orderByComparator) {

		return getPersistence().fetchBynewsId_First(newsId, orderByComparator);
	}

	/**
	 * Returns the last news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news attached file
	 * @throws NoSuchAttachedFileException if a matching news attached file could not be found
	 */
	public static NewsAttachedFile findBynewsId_Last(
			long newsId, OrderByComparator<NewsAttachedFile> orderByComparator)
		throws com.weprode.nero.news.exception.NoSuchAttachedFileException {

		return getPersistence().findBynewsId_Last(newsId, orderByComparator);
	}

	/**
	 * Returns the last news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news attached file, or <code>null</code> if a matching news attached file could not be found
	 */
	public static NewsAttachedFile fetchBynewsId_Last(
		long newsId, OrderByComparator<NewsAttachedFile> orderByComparator) {

		return getPersistence().fetchBynewsId_Last(newsId, orderByComparator);
	}

	/**
	 * Returns the news attached files before and after the current news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsAttachedFilePK the primary key of the current news attached file
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news attached file
	 * @throws NoSuchAttachedFileException if a news attached file with the primary key could not be found
	 */
	public static NewsAttachedFile[] findBynewsId_PrevAndNext(
			NewsAttachedFilePK newsAttachedFilePK, long newsId,
			OrderByComparator<NewsAttachedFile> orderByComparator)
		throws com.weprode.nero.news.exception.NoSuchAttachedFileException {

		return getPersistence().findBynewsId_PrevAndNext(
			newsAttachedFilePK, newsId, orderByComparator);
	}

	/**
	 * Removes all the news attached files where newsId = &#63; from the database.
	 *
	 * @param newsId the news ID
	 */
	public static void removeBynewsId(long newsId) {
		getPersistence().removeBynewsId(newsId);
	}

	/**
	 * Returns the number of news attached files where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the number of matching news attached files
	 */
	public static int countBynewsId(long newsId) {
		return getPersistence().countBynewsId(newsId);
	}

	/**
	 * Caches the news attached file in the entity cache if it is enabled.
	 *
	 * @param newsAttachedFile the news attached file
	 */
	public static void cacheResult(NewsAttachedFile newsAttachedFile) {
		getPersistence().cacheResult(newsAttachedFile);
	}

	/**
	 * Caches the news attached files in the entity cache if it is enabled.
	 *
	 * @param newsAttachedFiles the news attached files
	 */
	public static void cacheResult(List<NewsAttachedFile> newsAttachedFiles) {
		getPersistence().cacheResult(newsAttachedFiles);
	}

	/**
	 * Creates a new news attached file with the primary key. Does not add the news attached file to the database.
	 *
	 * @param newsAttachedFilePK the primary key for the new news attached file
	 * @return the new news attached file
	 */
	public static NewsAttachedFile create(
		NewsAttachedFilePK newsAttachedFilePK) {

		return getPersistence().create(newsAttachedFilePK);
	}

	/**
	 * Removes the news attached file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsAttachedFilePK the primary key of the news attached file
	 * @return the news attached file that was removed
	 * @throws NoSuchAttachedFileException if a news attached file with the primary key could not be found
	 */
	public static NewsAttachedFile remove(NewsAttachedFilePK newsAttachedFilePK)
		throws com.weprode.nero.news.exception.NoSuchAttachedFileException {

		return getPersistence().remove(newsAttachedFilePK);
	}

	public static NewsAttachedFile updateImpl(
		NewsAttachedFile newsAttachedFile) {

		return getPersistence().updateImpl(newsAttachedFile);
	}

	/**
	 * Returns the news attached file with the primary key or throws a <code>NoSuchAttachedFileException</code> if it could not be found.
	 *
	 * @param newsAttachedFilePK the primary key of the news attached file
	 * @return the news attached file
	 * @throws NoSuchAttachedFileException if a news attached file with the primary key could not be found
	 */
	public static NewsAttachedFile findByPrimaryKey(
			NewsAttachedFilePK newsAttachedFilePK)
		throws com.weprode.nero.news.exception.NoSuchAttachedFileException {

		return getPersistence().findByPrimaryKey(newsAttachedFilePK);
	}

	/**
	 * Returns the news attached file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsAttachedFilePK the primary key of the news attached file
	 * @return the news attached file, or <code>null</code> if a news attached file with the primary key could not be found
	 */
	public static NewsAttachedFile fetchByPrimaryKey(
		NewsAttachedFilePK newsAttachedFilePK) {

		return getPersistence().fetchByPrimaryKey(newsAttachedFilePK);
	}

	/**
	 * Returns all the news attached files.
	 *
	 * @return the news attached files
	 */
	public static List<NewsAttachedFile> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the news attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @return the range of news attached files
	 */
	public static List<NewsAttachedFile> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the news attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of news attached files
	 */
	public static List<NewsAttachedFile> findAll(
		int start, int end,
		OrderByComparator<NewsAttachedFile> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the news attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news attached files
	 * @param end the upper bound of the range of news attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of news attached files
	 */
	public static List<NewsAttachedFile> findAll(
		int start, int end,
		OrderByComparator<NewsAttachedFile> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the news attached files from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of news attached files.
	 *
	 * @return the number of news attached files
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static NewsAttachedFilePersistence getPersistence() {
		return _persistence;
	}

	private static volatile NewsAttachedFilePersistence _persistence;

}