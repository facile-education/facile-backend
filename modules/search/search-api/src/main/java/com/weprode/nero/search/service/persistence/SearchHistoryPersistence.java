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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.search.exception.NoSuchHistoryException;
import com.weprode.nero.search.model.SearchHistory;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the search history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SearchHistoryUtil
 * @generated
 */
@ProviderType
public interface SearchHistoryPersistence
	extends BasePersistence<SearchHistory> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SearchHistoryUtil} to access the search history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the search histories where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching search histories
	 */
	public java.util.List<SearchHistory> findByuserId(long userId);

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
	public java.util.List<SearchHistory> findByuserId(
		long userId, int start, int end);

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
	public java.util.List<SearchHistory> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SearchHistory>
			orderByComparator);

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
	public java.util.List<SearchHistory> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SearchHistory>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first search history in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching search history
	 * @throws NoSuchHistoryException if a matching search history could not be found
	 */
	public SearchHistory findByuserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<SearchHistory>
				orderByComparator)
		throws NoSuchHistoryException;

	/**
	 * Returns the first search history in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching search history, or <code>null</code> if a matching search history could not be found
	 */
	public SearchHistory fetchByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SearchHistory>
			orderByComparator);

	/**
	 * Returns the last search history in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching search history
	 * @throws NoSuchHistoryException if a matching search history could not be found
	 */
	public SearchHistory findByuserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<SearchHistory>
				orderByComparator)
		throws NoSuchHistoryException;

	/**
	 * Returns the last search history in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching search history, or <code>null</code> if a matching search history could not be found
	 */
	public SearchHistory fetchByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<SearchHistory>
			orderByComparator);

	/**
	 * Returns the search histories before and after the current search history in the ordered set where userId = &#63;.
	 *
	 * @param searchHistoryId the primary key of the current search history
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next search history
	 * @throws NoSuchHistoryException if a search history with the primary key could not be found
	 */
	public SearchHistory[] findByuserId_PrevAndNext(
			long searchHistoryId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<SearchHistory>
				orderByComparator)
		throws NoSuchHistoryException;

	/**
	 * Removes all the search histories where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId);

	/**
	 * Returns the number of search histories where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching search histories
	 */
	public int countByuserId(long userId);

	/**
	 * Caches the search history in the entity cache if it is enabled.
	 *
	 * @param searchHistory the search history
	 */
	public void cacheResult(SearchHistory searchHistory);

	/**
	 * Caches the search histories in the entity cache if it is enabled.
	 *
	 * @param searchHistories the search histories
	 */
	public void cacheResult(java.util.List<SearchHistory> searchHistories);

	/**
	 * Creates a new search history with the primary key. Does not add the search history to the database.
	 *
	 * @param searchHistoryId the primary key for the new search history
	 * @return the new search history
	 */
	public SearchHistory create(long searchHistoryId);

	/**
	 * Removes the search history with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param searchHistoryId the primary key of the search history
	 * @return the search history that was removed
	 * @throws NoSuchHistoryException if a search history with the primary key could not be found
	 */
	public SearchHistory remove(long searchHistoryId)
		throws NoSuchHistoryException;

	public SearchHistory updateImpl(SearchHistory searchHistory);

	/**
	 * Returns the search history with the primary key or throws a <code>NoSuchHistoryException</code> if it could not be found.
	 *
	 * @param searchHistoryId the primary key of the search history
	 * @return the search history
	 * @throws NoSuchHistoryException if a search history with the primary key could not be found
	 */
	public SearchHistory findByPrimaryKey(long searchHistoryId)
		throws NoSuchHistoryException;

	/**
	 * Returns the search history with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param searchHistoryId the primary key of the search history
	 * @return the search history, or <code>null</code> if a search history with the primary key could not be found
	 */
	public SearchHistory fetchByPrimaryKey(long searchHistoryId);

	/**
	 * Returns all the search histories.
	 *
	 * @return the search histories
	 */
	public java.util.List<SearchHistory> findAll();

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
	public java.util.List<SearchHistory> findAll(int start, int end);

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
	public java.util.List<SearchHistory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SearchHistory>
			orderByComparator);

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
	public java.util.List<SearchHistory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SearchHistory>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the search histories from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of search histories.
	 *
	 * @return the number of search histories
	 */
	public int countAll();

}