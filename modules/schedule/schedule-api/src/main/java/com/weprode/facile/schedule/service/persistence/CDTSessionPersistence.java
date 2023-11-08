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

package com.weprode.facile.schedule.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.schedule.exception.NoSuchCDTSessionException;
import com.weprode.facile.schedule.model.CDTSession;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the cdt session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CDTSessionUtil
 * @generated
 */
@ProviderType
public interface CDTSessionPersistence extends BasePersistence<CDTSession> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CDTSessionUtil} to access the cdt session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the cdt sessions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching cdt sessions
	 */
	public java.util.List<CDTSession> findBygroupId(long groupId);

	/**
	 * Returns a range of all the cdt sessions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @return the range of matching cdt sessions
	 */
	public java.util.List<CDTSession> findBygroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the cdt sessions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cdt sessions
	 */
	public java.util.List<CDTSession> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CDTSession>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cdt sessions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cdt sessions
	 */
	public java.util.List<CDTSession> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CDTSession>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cdt session
	 * @throws NoSuchCDTSessionException if a matching cdt session could not be found
	 */
	public CDTSession findBygroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<CDTSession>
				orderByComparator)
		throws NoSuchCDTSessionException;

	/**
	 * Returns the first cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cdt session, or <code>null</code> if a matching cdt session could not be found
	 */
	public CDTSession fetchBygroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CDTSession>
			orderByComparator);

	/**
	 * Returns the last cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cdt session
	 * @throws NoSuchCDTSessionException if a matching cdt session could not be found
	 */
	public CDTSession findBygroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<CDTSession>
				orderByComparator)
		throws NoSuchCDTSessionException;

	/**
	 * Returns the last cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cdt session, or <code>null</code> if a matching cdt session could not be found
	 */
	public CDTSession fetchBygroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<CDTSession>
			orderByComparator);

	/**
	 * Returns the cdt sessions before and after the current cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param sessionId the primary key of the current cdt session
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cdt session
	 * @throws NoSuchCDTSessionException if a cdt session with the primary key could not be found
	 */
	public CDTSession[] findBygroupId_PrevAndNext(
			long sessionId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<CDTSession>
				orderByComparator)
		throws NoSuchCDTSessionException;

	/**
	 * Removes all the cdt sessions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeBygroupId(long groupId);

	/**
	 * Returns the number of cdt sessions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching cdt sessions
	 */
	public int countBygroupId(long groupId);

	/**
	 * Caches the cdt session in the entity cache if it is enabled.
	 *
	 * @param cdtSession the cdt session
	 */
	public void cacheResult(CDTSession cdtSession);

	/**
	 * Caches the cdt sessions in the entity cache if it is enabled.
	 *
	 * @param cdtSessions the cdt sessions
	 */
	public void cacheResult(java.util.List<CDTSession> cdtSessions);

	/**
	 * Creates a new cdt session with the primary key. Does not add the cdt session to the database.
	 *
	 * @param sessionId the primary key for the new cdt session
	 * @return the new cdt session
	 */
	public CDTSession create(long sessionId);

	/**
	 * Removes the cdt session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session that was removed
	 * @throws NoSuchCDTSessionException if a cdt session with the primary key could not be found
	 */
	public CDTSession remove(long sessionId) throws NoSuchCDTSessionException;

	public CDTSession updateImpl(CDTSession cdtSession);

	/**
	 * Returns the cdt session with the primary key or throws a <code>NoSuchCDTSessionException</code> if it could not be found.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session
	 * @throws NoSuchCDTSessionException if a cdt session with the primary key could not be found
	 */
	public CDTSession findByPrimaryKey(long sessionId)
		throws NoSuchCDTSessionException;

	/**
	 * Returns the cdt session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session, or <code>null</code> if a cdt session with the primary key could not be found
	 */
	public CDTSession fetchByPrimaryKey(long sessionId);

	/**
	 * Returns all the cdt sessions.
	 *
	 * @return the cdt sessions
	 */
	public java.util.List<CDTSession> findAll();

	/**
	 * Returns a range of all the cdt sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @return the range of cdt sessions
	 */
	public java.util.List<CDTSession> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the cdt sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cdt sessions
	 */
	public java.util.List<CDTSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CDTSession>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cdt sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CDTSessionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdt sessions
	 * @param end the upper bound of the range of cdt sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cdt sessions
	 */
	public java.util.List<CDTSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CDTSession>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the cdt sessions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of cdt sessions.
	 *
	 * @return the number of cdt sessions
	 */
	public int countAll();

}