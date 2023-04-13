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

package com.weprode.nero.application.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.application.exception.NoSuchBroadcastException;
import com.weprode.nero.application.model.Broadcast;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the broadcast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BroadcastUtil
 * @generated
 */
@ProviderType
public interface BroadcastPersistence extends BasePersistence<Broadcast> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BroadcastUtil} to access the broadcast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the broadcast where applicationId = &#63; and schoolId = &#63; or throws a <code>NoSuchBroadcastException</code> if it could not be found.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching broadcast
	 * @throws NoSuchBroadcastException if a matching broadcast could not be found
	 */
	public Broadcast findByapplicationId_schoolId(
			long applicationId, long schoolId)
		throws NoSuchBroadcastException;

	/**
	 * Returns the broadcast where applicationId = &#63; and schoolId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching broadcast, or <code>null</code> if a matching broadcast could not be found
	 */
	public Broadcast fetchByapplicationId_schoolId(
		long applicationId, long schoolId);

	/**
	 * Returns the broadcast where applicationId = &#63; and schoolId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching broadcast, or <code>null</code> if a matching broadcast could not be found
	 */
	public Broadcast fetchByapplicationId_schoolId(
		long applicationId, long schoolId, boolean useFinderCache);

	/**
	 * Removes the broadcast where applicationId = &#63; and schoolId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the broadcast that was removed
	 */
	public Broadcast removeByapplicationId_schoolId(
			long applicationId, long schoolId)
		throws NoSuchBroadcastException;

	/**
	 * Returns the number of broadcasts where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the number of matching broadcasts
	 */
	public int countByapplicationId_schoolId(long applicationId, long schoolId);

	/**
	 * Returns all the broadcasts where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching broadcasts
	 */
	public java.util.List<Broadcast> findByapplicationId(long applicationId);

	/**
	 * Returns a range of all the broadcasts where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @return the range of matching broadcasts
	 */
	public java.util.List<Broadcast> findByapplicationId(
		long applicationId, int start, int end);

	/**
	 * Returns an ordered range of all the broadcasts where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching broadcasts
	 */
	public java.util.List<Broadcast> findByapplicationId(
		long applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Broadcast>
			orderByComparator);

	/**
	 * Returns an ordered range of all the broadcasts where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching broadcasts
	 */
	public java.util.List<Broadcast> findByapplicationId(
		long applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Broadcast>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast
	 * @throws NoSuchBroadcastException if a matching broadcast could not be found
	 */
	public Broadcast findByapplicationId_First(
			long applicationId,
			com.liferay.portal.kernel.util.OrderByComparator<Broadcast>
				orderByComparator)
		throws NoSuchBroadcastException;

	/**
	 * Returns the first broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching broadcast, or <code>null</code> if a matching broadcast could not be found
	 */
	public Broadcast fetchByapplicationId_First(
		long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator<Broadcast>
			orderByComparator);

	/**
	 * Returns the last broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast
	 * @throws NoSuchBroadcastException if a matching broadcast could not be found
	 */
	public Broadcast findByapplicationId_Last(
			long applicationId,
			com.liferay.portal.kernel.util.OrderByComparator<Broadcast>
				orderByComparator)
		throws NoSuchBroadcastException;

	/**
	 * Returns the last broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching broadcast, or <code>null</code> if a matching broadcast could not be found
	 */
	public Broadcast fetchByapplicationId_Last(
		long applicationId,
		com.liferay.portal.kernel.util.OrderByComparator<Broadcast>
			orderByComparator);

	/**
	 * Returns the broadcasts before and after the current broadcast in the ordered set where applicationId = &#63;.
	 *
	 * @param broadcastId the primary key of the current broadcast
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next broadcast
	 * @throws NoSuchBroadcastException if a broadcast with the primary key could not be found
	 */
	public Broadcast[] findByapplicationId_PrevAndNext(
			long broadcastId, long applicationId,
			com.liferay.portal.kernel.util.OrderByComparator<Broadcast>
				orderByComparator)
		throws NoSuchBroadcastException;

	/**
	 * Removes all the broadcasts where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 */
	public void removeByapplicationId(long applicationId);

	/**
	 * Returns the number of broadcasts where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching broadcasts
	 */
	public int countByapplicationId(long applicationId);

	/**
	 * Caches the broadcast in the entity cache if it is enabled.
	 *
	 * @param broadcast the broadcast
	 */
	public void cacheResult(Broadcast broadcast);

	/**
	 * Caches the broadcasts in the entity cache if it is enabled.
	 *
	 * @param broadcasts the broadcasts
	 */
	public void cacheResult(java.util.List<Broadcast> broadcasts);

	/**
	 * Creates a new broadcast with the primary key. Does not add the broadcast to the database.
	 *
	 * @param broadcastId the primary key for the new broadcast
	 * @return the new broadcast
	 */
	public Broadcast create(long broadcastId);

	/**
	 * Removes the broadcast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast that was removed
	 * @throws NoSuchBroadcastException if a broadcast with the primary key could not be found
	 */
	public Broadcast remove(long broadcastId) throws NoSuchBroadcastException;

	public Broadcast updateImpl(Broadcast broadcast);

	/**
	 * Returns the broadcast with the primary key or throws a <code>NoSuchBroadcastException</code> if it could not be found.
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast
	 * @throws NoSuchBroadcastException if a broadcast with the primary key could not be found
	 */
	public Broadcast findByPrimaryKey(long broadcastId)
		throws NoSuchBroadcastException;

	/**
	 * Returns the broadcast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param broadcastId the primary key of the broadcast
	 * @return the broadcast, or <code>null</code> if a broadcast with the primary key could not be found
	 */
	public Broadcast fetchByPrimaryKey(long broadcastId);

	/**
	 * Returns all the broadcasts.
	 *
	 * @return the broadcasts
	 */
	public java.util.List<Broadcast> findAll();

	/**
	 * Returns a range of all the broadcasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @return the range of broadcasts
	 */
	public java.util.List<Broadcast> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the broadcasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of broadcasts
	 */
	public java.util.List<Broadcast> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Broadcast>
			orderByComparator);

	/**
	 * Returns an ordered range of all the broadcasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BroadcastModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of broadcasts
	 * @param end the upper bound of the range of broadcasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of broadcasts
	 */
	public java.util.List<Broadcast> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Broadcast>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the broadcasts from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of broadcasts.
	 *
	 * @return the number of broadcasts
	 */
	public int countAll();

}