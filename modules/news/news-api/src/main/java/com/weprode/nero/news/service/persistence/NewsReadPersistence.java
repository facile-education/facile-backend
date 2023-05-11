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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.news.exception.NoSuchReadException;
import com.weprode.nero.news.model.NewsRead;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the news read service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsReadUtil
 * @generated
 */
@ProviderType
public interface NewsReadPersistence extends BasePersistence<NewsRead> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NewsReadUtil} to access the news read persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the news reads where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the matching news reads
	 */
	public java.util.List<NewsRead> findBynewsId(long newsId);

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
	public java.util.List<NewsRead> findBynewsId(
		long newsId, int start, int end);

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
	public java.util.List<NewsRead> findBynewsId(
		long newsId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsRead>
			orderByComparator);

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
	public java.util.List<NewsRead> findBynewsId(
		long newsId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsRead>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news read
	 * @throws NoSuchReadException if a matching news read could not be found
	 */
	public NewsRead findBynewsId_First(
			long newsId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsRead>
				orderByComparator)
		throws NoSuchReadException;

	/**
	 * Returns the first news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news read, or <code>null</code> if a matching news read could not be found
	 */
	public NewsRead fetchBynewsId_First(
		long newsId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsRead>
			orderByComparator);

	/**
	 * Returns the last news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news read
	 * @throws NoSuchReadException if a matching news read could not be found
	 */
	public NewsRead findBynewsId_Last(
			long newsId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsRead>
				orderByComparator)
		throws NoSuchReadException;

	/**
	 * Returns the last news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news read, or <code>null</code> if a matching news read could not be found
	 */
	public NewsRead fetchBynewsId_Last(
		long newsId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsRead>
			orderByComparator);

	/**
	 * Returns the news reads before and after the current news read in the ordered set where newsId = &#63;.
	 *
	 * @param newsReadPK the primary key of the current news read
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news read
	 * @throws NoSuchReadException if a news read with the primary key could not be found
	 */
	public NewsRead[] findBynewsId_PrevAndNext(
			NewsReadPK newsReadPK, long newsId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsRead>
				orderByComparator)
		throws NoSuchReadException;

	/**
	 * Removes all the news reads where newsId = &#63; from the database.
	 *
	 * @param newsId the news ID
	 */
	public void removeBynewsId(long newsId);

	/**
	 * Returns the number of news reads where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the number of matching news reads
	 */
	public int countBynewsId(long newsId);

	/**
	 * Caches the news read in the entity cache if it is enabled.
	 *
	 * @param newsRead the news read
	 */
	public void cacheResult(NewsRead newsRead);

	/**
	 * Caches the news reads in the entity cache if it is enabled.
	 *
	 * @param newsReads the news reads
	 */
	public void cacheResult(java.util.List<NewsRead> newsReads);

	/**
	 * Creates a new news read with the primary key. Does not add the news read to the database.
	 *
	 * @param newsReadPK the primary key for the new news read
	 * @return the new news read
	 */
	public NewsRead create(NewsReadPK newsReadPK);

	/**
	 * Removes the news read with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsReadPK the primary key of the news read
	 * @return the news read that was removed
	 * @throws NoSuchReadException if a news read with the primary key could not be found
	 */
	public NewsRead remove(NewsReadPK newsReadPK) throws NoSuchReadException;

	public NewsRead updateImpl(NewsRead newsRead);

	/**
	 * Returns the news read with the primary key or throws a <code>NoSuchReadException</code> if it could not be found.
	 *
	 * @param newsReadPK the primary key of the news read
	 * @return the news read
	 * @throws NoSuchReadException if a news read with the primary key could not be found
	 */
	public NewsRead findByPrimaryKey(NewsReadPK newsReadPK)
		throws NoSuchReadException;

	/**
	 * Returns the news read with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsReadPK the primary key of the news read
	 * @return the news read, or <code>null</code> if a news read with the primary key could not be found
	 */
	public NewsRead fetchByPrimaryKey(NewsReadPK newsReadPK);

	/**
	 * Returns all the news reads.
	 *
	 * @return the news reads
	 */
	public java.util.List<NewsRead> findAll();

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
	public java.util.List<NewsRead> findAll(int start, int end);

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
	public java.util.List<NewsRead> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsRead>
			orderByComparator);

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
	public java.util.List<NewsRead> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsRead>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the news reads from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of news reads.
	 *
	 * @return the number of news reads
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}