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

import com.weprode.nero.news.exception.NoSuchAttachedFileException;
import com.weprode.nero.news.model.NewsAttachedFile;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the news attached file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsAttachedFileUtil
 * @generated
 */
@ProviderType
public interface NewsAttachedFilePersistence
	extends BasePersistence<NewsAttachedFile> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NewsAttachedFileUtil} to access the news attached file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the news attached files where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the matching news attached files
	 */
	public java.util.List<NewsAttachedFile> findBynewsId(long newsId);

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
	public java.util.List<NewsAttachedFile> findBynewsId(
		long newsId, int start, int end);

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
	public java.util.List<NewsAttachedFile> findBynewsId(
		long newsId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAttachedFile>
			orderByComparator);

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
	public java.util.List<NewsAttachedFile> findBynewsId(
		long newsId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAttachedFile>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news attached file
	 * @throws NoSuchAttachedFileException if a matching news attached file could not be found
	 */
	public NewsAttachedFile findBynewsId_First(
			long newsId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsAttachedFile>
				orderByComparator)
		throws NoSuchAttachedFileException;

	/**
	 * Returns the first news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news attached file, or <code>null</code> if a matching news attached file could not be found
	 */
	public NewsAttachedFile fetchBynewsId_First(
		long newsId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAttachedFile>
			orderByComparator);

	/**
	 * Returns the last news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news attached file
	 * @throws NoSuchAttachedFileException if a matching news attached file could not be found
	 */
	public NewsAttachedFile findBynewsId_Last(
			long newsId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsAttachedFile>
				orderByComparator)
		throws NoSuchAttachedFileException;

	/**
	 * Returns the last news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news attached file, or <code>null</code> if a matching news attached file could not be found
	 */
	public NewsAttachedFile fetchBynewsId_Last(
		long newsId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAttachedFile>
			orderByComparator);

	/**
	 * Returns the news attached files before and after the current news attached file in the ordered set where newsId = &#63;.
	 *
	 * @param newsAttachedFilePK the primary key of the current news attached file
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news attached file
	 * @throws NoSuchAttachedFileException if a news attached file with the primary key could not be found
	 */
	public NewsAttachedFile[] findBynewsId_PrevAndNext(
			NewsAttachedFilePK newsAttachedFilePK, long newsId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsAttachedFile>
				orderByComparator)
		throws NoSuchAttachedFileException;

	/**
	 * Removes all the news attached files where newsId = &#63; from the database.
	 *
	 * @param newsId the news ID
	 */
	public void removeBynewsId(long newsId);

	/**
	 * Returns the number of news attached files where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the number of matching news attached files
	 */
	public int countBynewsId(long newsId);

	/**
	 * Caches the news attached file in the entity cache if it is enabled.
	 *
	 * @param newsAttachedFile the news attached file
	 */
	public void cacheResult(NewsAttachedFile newsAttachedFile);

	/**
	 * Caches the news attached files in the entity cache if it is enabled.
	 *
	 * @param newsAttachedFiles the news attached files
	 */
	public void cacheResult(java.util.List<NewsAttachedFile> newsAttachedFiles);

	/**
	 * Creates a new news attached file with the primary key. Does not add the news attached file to the database.
	 *
	 * @param newsAttachedFilePK the primary key for the new news attached file
	 * @return the new news attached file
	 */
	public NewsAttachedFile create(NewsAttachedFilePK newsAttachedFilePK);

	/**
	 * Removes the news attached file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsAttachedFilePK the primary key of the news attached file
	 * @return the news attached file that was removed
	 * @throws NoSuchAttachedFileException if a news attached file with the primary key could not be found
	 */
	public NewsAttachedFile remove(NewsAttachedFilePK newsAttachedFilePK)
		throws NoSuchAttachedFileException;

	public NewsAttachedFile updateImpl(NewsAttachedFile newsAttachedFile);

	/**
	 * Returns the news attached file with the primary key or throws a <code>NoSuchAttachedFileException</code> if it could not be found.
	 *
	 * @param newsAttachedFilePK the primary key of the news attached file
	 * @return the news attached file
	 * @throws NoSuchAttachedFileException if a news attached file with the primary key could not be found
	 */
	public NewsAttachedFile findByPrimaryKey(
			NewsAttachedFilePK newsAttachedFilePK)
		throws NoSuchAttachedFileException;

	/**
	 * Returns the news attached file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsAttachedFilePK the primary key of the news attached file
	 * @return the news attached file, or <code>null</code> if a news attached file with the primary key could not be found
	 */
	public NewsAttachedFile fetchByPrimaryKey(
		NewsAttachedFilePK newsAttachedFilePK);

	/**
	 * Returns all the news attached files.
	 *
	 * @return the news attached files
	 */
	public java.util.List<NewsAttachedFile> findAll();

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
	public java.util.List<NewsAttachedFile> findAll(int start, int end);

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
	public java.util.List<NewsAttachedFile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAttachedFile>
			orderByComparator);

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
	public java.util.List<NewsAttachedFile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAttachedFile>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the news attached files from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of news attached files.
	 *
	 * @return the number of news attached files
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}