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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.news.exception.NoSuchPopulationException;
import com.weprode.facile.news.model.NewsPopulation;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the news population service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsPopulationUtil
 * @generated
 */
@ProviderType
public interface NewsPopulationPersistence
	extends BasePersistence<NewsPopulation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NewsPopulationUtil} to access the news population persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the news populations where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @return the matching news populations
	 */
	public java.util.List<NewsPopulation> findBygroupId_roleId(
		long groupId, long roleId);

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
	public java.util.List<NewsPopulation> findBygroupId_roleId(
		long groupId, long roleId, int start, int end);

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
	public java.util.List<NewsPopulation> findBygroupId_roleId(
		long groupId, long roleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator);

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
	public java.util.List<NewsPopulation> findBygroupId_roleId(
		long groupId, long roleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	public NewsPopulation findBygroupId_roleId_First(
			long groupId, long roleId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
				orderByComparator)
		throws NoSuchPopulationException;

	/**
	 * Returns the first news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population, or <code>null</code> if a matching news population could not be found
	 */
	public NewsPopulation fetchBygroupId_roleId_First(
		long groupId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator);

	/**
	 * Returns the last news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	public NewsPopulation findBygroupId_roleId_Last(
			long groupId, long roleId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
				orderByComparator)
		throws NoSuchPopulationException;

	/**
	 * Returns the last news population in the ordered set where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population, or <code>null</code> if a matching news population could not be found
	 */
	public NewsPopulation fetchBygroupId_roleId_Last(
		long groupId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator);

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
	public NewsPopulation[] findBygroupId_roleId_PrevAndNext(
			NewsPopulationPK newsPopulationPK, long groupId, long roleId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
				orderByComparator)
		throws NoSuchPopulationException;

	/**
	 * Removes all the news populations where groupId = &#63; and roleId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 */
	public void removeBygroupId_roleId(long groupId, long roleId);

	/**
	 * Returns the number of news populations where groupId = &#63; and roleId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param roleId the role ID
	 * @return the number of matching news populations
	 */
	public int countBygroupId_roleId(long groupId, long roleId);

	/**
	 * Returns all the news populations where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the matching news populations
	 */
	public java.util.List<NewsPopulation> findBynewsId(long newsId);

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
	public java.util.List<NewsPopulation> findBynewsId(
		long newsId, int start, int end);

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
	public java.util.List<NewsPopulation> findBynewsId(
		long newsId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator);

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
	public java.util.List<NewsPopulation> findBynewsId(
		long newsId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	public NewsPopulation findBynewsId_First(
			long newsId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
				orderByComparator)
		throws NoSuchPopulationException;

	/**
	 * Returns the first news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population, or <code>null</code> if a matching news population could not be found
	 */
	public NewsPopulation fetchBynewsId_First(
		long newsId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator);

	/**
	 * Returns the last news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	public NewsPopulation findBynewsId_Last(
			long newsId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
				orderByComparator)
		throws NoSuchPopulationException;

	/**
	 * Returns the last news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population, or <code>null</code> if a matching news population could not be found
	 */
	public NewsPopulation fetchBynewsId_Last(
		long newsId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator);

	/**
	 * Returns the news populations before and after the current news population in the ordered set where newsId = &#63;.
	 *
	 * @param newsPopulationPK the primary key of the current news population
	 * @param newsId the news ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news population
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	public NewsPopulation[] findBynewsId_PrevAndNext(
			NewsPopulationPK newsPopulationPK, long newsId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
				orderByComparator)
		throws NoSuchPopulationException;

	/**
	 * Removes all the news populations where newsId = &#63; from the database.
	 *
	 * @param newsId the news ID
	 */
	public void removeBynewsId(long newsId);

	/**
	 * Returns the number of news populations where newsId = &#63;.
	 *
	 * @param newsId the news ID
	 * @return the number of matching news populations
	 */
	public int countBynewsId(long newsId);

	/**
	 * Returns all the news populations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching news populations
	 */
	public java.util.List<NewsPopulation> findBygroupId(long groupId);

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
	public java.util.List<NewsPopulation> findBygroupId(
		long groupId, int start, int end);

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
	public java.util.List<NewsPopulation> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator);

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
	public java.util.List<NewsPopulation> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first news population in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	public NewsPopulation findBygroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
				orderByComparator)
		throws NoSuchPopulationException;

	/**
	 * Returns the first news population in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news population, or <code>null</code> if a matching news population could not be found
	 */
	public NewsPopulation fetchBygroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator);

	/**
	 * Returns the last news population in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population
	 * @throws NoSuchPopulationException if a matching news population could not be found
	 */
	public NewsPopulation findBygroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
				orderByComparator)
		throws NoSuchPopulationException;

	/**
	 * Returns the last news population in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news population, or <code>null</code> if a matching news population could not be found
	 */
	public NewsPopulation fetchBygroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator);

	/**
	 * Returns the news populations before and after the current news population in the ordered set where groupId = &#63;.
	 *
	 * @param newsPopulationPK the primary key of the current news population
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news population
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	public NewsPopulation[] findBygroupId_PrevAndNext(
			NewsPopulationPK newsPopulationPK, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
				orderByComparator)
		throws NoSuchPopulationException;

	/**
	 * Removes all the news populations where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeBygroupId(long groupId);

	/**
	 * Returns the number of news populations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching news populations
	 */
	public int countBygroupId(long groupId);

	/**
	 * Caches the news population in the entity cache if it is enabled.
	 *
	 * @param newsPopulation the news population
	 */
	public void cacheResult(NewsPopulation newsPopulation);

	/**
	 * Caches the news populations in the entity cache if it is enabled.
	 *
	 * @param newsPopulations the news populations
	 */
	public void cacheResult(java.util.List<NewsPopulation> newsPopulations);

	/**
	 * Creates a new news population with the primary key. Does not add the news population to the database.
	 *
	 * @param newsPopulationPK the primary key for the new news population
	 * @return the new news population
	 */
	public NewsPopulation create(NewsPopulationPK newsPopulationPK);

	/**
	 * Removes the news population with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsPopulationPK the primary key of the news population
	 * @return the news population that was removed
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	public NewsPopulation remove(NewsPopulationPK newsPopulationPK)
		throws NoSuchPopulationException;

	public NewsPopulation updateImpl(NewsPopulation newsPopulation);

	/**
	 * Returns the news population with the primary key or throws a <code>NoSuchPopulationException</code> if it could not be found.
	 *
	 * @param newsPopulationPK the primary key of the news population
	 * @return the news population
	 * @throws NoSuchPopulationException if a news population with the primary key could not be found
	 */
	public NewsPopulation findByPrimaryKey(NewsPopulationPK newsPopulationPK)
		throws NoSuchPopulationException;

	/**
	 * Returns the news population with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsPopulationPK the primary key of the news population
	 * @return the news population, or <code>null</code> if a news population with the primary key could not be found
	 */
	public NewsPopulation fetchByPrimaryKey(NewsPopulationPK newsPopulationPK);

	/**
	 * Returns all the news populations.
	 *
	 * @return the news populations
	 */
	public java.util.List<NewsPopulation> findAll();

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
	public java.util.List<NewsPopulation> findAll(int start, int end);

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
	public java.util.List<NewsPopulation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator);

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
	public java.util.List<NewsPopulation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsPopulation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the news populations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of news populations.
	 *
	 * @return the number of news populations
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}