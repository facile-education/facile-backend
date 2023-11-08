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

package com.weprode.facile.statistic.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.statistic.exception.NoSuchLoolStatException;
import com.weprode.facile.statistic.model.LoolStat;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the lool stat service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoolStatUtil
 * @generated
 */
@ProviderType
public interface LoolStatPersistence extends BasePersistence<LoolStat> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoolStatUtil} to access the lool stat persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the lool stats where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching lool stats
	 */
	public java.util.List<LoolStat> findByuserId(long userId);

	/**
	 * Returns a range of all the lool stats where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @return the range of matching lool stats
	 */
	public java.util.List<LoolStat> findByuserId(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the lool stats where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lool stats
	 */
	public java.util.List<LoolStat> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoolStat>
			orderByComparator);

	/**
	 * Returns an ordered range of all the lool stats where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching lool stats
	 */
	public java.util.List<LoolStat> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoolStat>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first lool stat in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lool stat
	 * @throws NoSuchLoolStatException if a matching lool stat could not be found
	 */
	public LoolStat findByuserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<LoolStat>
				orderByComparator)
		throws NoSuchLoolStatException;

	/**
	 * Returns the first lool stat in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lool stat, or <code>null</code> if a matching lool stat could not be found
	 */
	public LoolStat fetchByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LoolStat>
			orderByComparator);

	/**
	 * Returns the last lool stat in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lool stat
	 * @throws NoSuchLoolStatException if a matching lool stat could not be found
	 */
	public LoolStat findByuserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<LoolStat>
				orderByComparator)
		throws NoSuchLoolStatException;

	/**
	 * Returns the last lool stat in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lool stat, or <code>null</code> if a matching lool stat could not be found
	 */
	public LoolStat fetchByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<LoolStat>
			orderByComparator);

	/**
	 * Returns the lool stats before and after the current lool stat in the ordered set where userId = &#63;.
	 *
	 * @param statId the primary key of the current lool stat
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lool stat
	 * @throws NoSuchLoolStatException if a lool stat with the primary key could not be found
	 */
	public LoolStat[] findByuserId_PrevAndNext(
			long statId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<LoolStat>
				orderByComparator)
		throws NoSuchLoolStatException;

	/**
	 * Removes all the lool stats where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId);

	/**
	 * Returns the number of lool stats where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching lool stats
	 */
	public int countByuserId(long userId);

	/**
	 * Caches the lool stat in the entity cache if it is enabled.
	 *
	 * @param loolStat the lool stat
	 */
	public void cacheResult(LoolStat loolStat);

	/**
	 * Caches the lool stats in the entity cache if it is enabled.
	 *
	 * @param loolStats the lool stats
	 */
	public void cacheResult(java.util.List<LoolStat> loolStats);

	/**
	 * Creates a new lool stat with the primary key. Does not add the lool stat to the database.
	 *
	 * @param statId the primary key for the new lool stat
	 * @return the new lool stat
	 */
	public LoolStat create(long statId);

	/**
	 * Removes the lool stat with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param statId the primary key of the lool stat
	 * @return the lool stat that was removed
	 * @throws NoSuchLoolStatException if a lool stat with the primary key could not be found
	 */
	public LoolStat remove(long statId) throws NoSuchLoolStatException;

	public LoolStat updateImpl(LoolStat loolStat);

	/**
	 * Returns the lool stat with the primary key or throws a <code>NoSuchLoolStatException</code> if it could not be found.
	 *
	 * @param statId the primary key of the lool stat
	 * @return the lool stat
	 * @throws NoSuchLoolStatException if a lool stat with the primary key could not be found
	 */
	public LoolStat findByPrimaryKey(long statId)
		throws NoSuchLoolStatException;

	/**
	 * Returns the lool stat with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param statId the primary key of the lool stat
	 * @return the lool stat, or <code>null</code> if a lool stat with the primary key could not be found
	 */
	public LoolStat fetchByPrimaryKey(long statId);

	/**
	 * Returns all the lool stats.
	 *
	 * @return the lool stats
	 */
	public java.util.List<LoolStat> findAll();

	/**
	 * Returns a range of all the lool stats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @return the range of lool stats
	 */
	public java.util.List<LoolStat> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the lool stats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of lool stats
	 */
	public java.util.List<LoolStat> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoolStat>
			orderByComparator);

	/**
	 * Returns an ordered range of all the lool stats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoolStatModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lool stats
	 * @param end the upper bound of the range of lool stats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of lool stats
	 */
	public java.util.List<LoolStat> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoolStat>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the lool stats from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of lool stats.
	 *
	 * @return the number of lool stats
	 */
	public int countAll();

}