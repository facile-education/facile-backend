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

import com.weprode.nero.news.exception.NoSuchNewsException;
import com.weprode.nero.news.model.News;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the news service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsUtil
 * @generated
 */
@ProviderType
public interface NewsPersistence extends BasePersistence<News> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NewsUtil} to access the news persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the newses where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @return the matching newses
	 */
	public java.util.List<News> findByauthorId(long authorId);

	/**
	 * Returns a range of all the newses where authorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param authorId the author ID
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @return the range of matching newses
	 */
	public java.util.List<News> findByauthorId(
		long authorId, int start, int end);

	/**
	 * Returns an ordered range of all the newses where authorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param authorId the author ID
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching newses
	 */
	public java.util.List<News> findByauthorId(
		long authorId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<News>
			orderByComparator);

	/**
	 * Returns an ordered range of all the newses where authorId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param authorId the author ID
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching newses
	 */
	public java.util.List<News> findByauthorId(
		long authorId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<News>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first news in the ordered set where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news
	 * @throws NoSuchNewsException if a matching news could not be found
	 */
	public News findByauthorId_First(
			long authorId,
			com.liferay.portal.kernel.util.OrderByComparator<News>
				orderByComparator)
		throws NoSuchNewsException;

	/**
	 * Returns the first news in the ordered set where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news, or <code>null</code> if a matching news could not be found
	 */
	public News fetchByauthorId_First(
		long authorId,
		com.liferay.portal.kernel.util.OrderByComparator<News>
			orderByComparator);

	/**
	 * Returns the last news in the ordered set where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news
	 * @throws NoSuchNewsException if a matching news could not be found
	 */
	public News findByauthorId_Last(
			long authorId,
			com.liferay.portal.kernel.util.OrderByComparator<News>
				orderByComparator)
		throws NoSuchNewsException;

	/**
	 * Returns the last news in the ordered set where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news, or <code>null</code> if a matching news could not be found
	 */
	public News fetchByauthorId_Last(
		long authorId,
		com.liferay.portal.kernel.util.OrderByComparator<News>
			orderByComparator);

	/**
	 * Returns the newses before and after the current news in the ordered set where authorId = &#63;.
	 *
	 * @param newsId the primary key of the current news
	 * @param authorId the author ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	public News[] findByauthorId_PrevAndNext(
			long newsId, long authorId,
			com.liferay.portal.kernel.util.OrderByComparator<News>
				orderByComparator)
		throws NoSuchNewsException;

	/**
	 * Removes all the newses where authorId = &#63; from the database.
	 *
	 * @param authorId the author ID
	 */
	public void removeByauthorId(long authorId);

	/**
	 * Returns the number of newses where authorId = &#63;.
	 *
	 * @param authorId the author ID
	 * @return the number of matching newses
	 */
	public int countByauthorId(long authorId);

	/**
	 * Caches the news in the entity cache if it is enabled.
	 *
	 * @param news the news
	 */
	public void cacheResult(News news);

	/**
	 * Caches the newses in the entity cache if it is enabled.
	 *
	 * @param newses the newses
	 */
	public void cacheResult(java.util.List<News> newses);

	/**
	 * Creates a new news with the primary key. Does not add the news to the database.
	 *
	 * @param newsId the primary key for the new news
	 * @return the new news
	 */
	public News create(long newsId);

	/**
	 * Removes the news with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsId the primary key of the news
	 * @return the news that was removed
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	public News remove(long newsId) throws NoSuchNewsException;

	public News updateImpl(News news);

	/**
	 * Returns the news with the primary key or throws a <code>NoSuchNewsException</code> if it could not be found.
	 *
	 * @param newsId the primary key of the news
	 * @return the news
	 * @throws NoSuchNewsException if a news with the primary key could not be found
	 */
	public News findByPrimaryKey(long newsId) throws NoSuchNewsException;

	/**
	 * Returns the news with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsId the primary key of the news
	 * @return the news, or <code>null</code> if a news with the primary key could not be found
	 */
	public News fetchByPrimaryKey(long newsId);

	/**
	 * Returns all the newses.
	 *
	 * @return the newses
	 */
	public java.util.List<News> findAll();

	/**
	 * Returns a range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @return the range of newses
	 */
	public java.util.List<News> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of newses
	 */
	public java.util.List<News> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<News>
			orderByComparator);

	/**
	 * Returns an ordered range of all the newses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of newses
	 * @param end the upper bound of the range of newses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of newses
	 */
	public java.util.List<News> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<News>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the newses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of newses.
	 *
	 * @return the number of newses
	 */
	public int countAll();

}