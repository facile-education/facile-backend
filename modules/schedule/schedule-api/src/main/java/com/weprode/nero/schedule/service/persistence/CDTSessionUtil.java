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

package com.weprode.nero.schedule.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.schedule.model.CDTSession;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the cdt session service. This utility wraps <code>com.weprode.nero.schedule.service.persistence.impl.CDTSessionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CDTSessionPersistence
 * @generated
 */
public class CDTSessionUtil {

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
	public static void clearCache(CDTSession cdtSession) {
		getPersistence().clearCache(cdtSession);
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
	public static Map<Serializable, CDTSession> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CDTSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CDTSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CDTSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CDTSession> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CDTSession update(CDTSession cdtSession) {
		return getPersistence().update(cdtSession);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CDTSession update(
		CDTSession cdtSession, ServiceContext serviceContext) {

		return getPersistence().update(cdtSession, serviceContext);
	}

	/**
	 * Returns all the cdt sessions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching cdt sessions
	 */
	public static List<CDTSession> findBygroupId(long groupId) {
		return getPersistence().findBygroupId(groupId);
	}

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
	public static List<CDTSession> findBygroupId(
		long groupId, int start, int end) {

		return getPersistence().findBygroupId(groupId, start, end);
	}

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
	public static List<CDTSession> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<CDTSession> orderByComparator) {

		return getPersistence().findBygroupId(
			groupId, start, end, orderByComparator);
	}

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
	public static List<CDTSession> findBygroupId(
		long groupId, int start, int end,
		OrderByComparator<CDTSession> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBygroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cdt session
	 * @throws NoSuchCDTSessionException if a matching cdt session could not be found
	 */
	public static CDTSession findBygroupId_First(
			long groupId, OrderByComparator<CDTSession> orderByComparator)
		throws com.weprode.nero.schedule.exception.NoSuchCDTSessionException {

		return getPersistence().findBygroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cdt session, or <code>null</code> if a matching cdt session could not be found
	 */
	public static CDTSession fetchBygroupId_First(
		long groupId, OrderByComparator<CDTSession> orderByComparator) {

		return getPersistence().fetchBygroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cdt session
	 * @throws NoSuchCDTSessionException if a matching cdt session could not be found
	 */
	public static CDTSession findBygroupId_Last(
			long groupId, OrderByComparator<CDTSession> orderByComparator)
		throws com.weprode.nero.schedule.exception.NoSuchCDTSessionException {

		return getPersistence().findBygroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cdt session, or <code>null</code> if a matching cdt session could not be found
	 */
	public static CDTSession fetchBygroupId_Last(
		long groupId, OrderByComparator<CDTSession> orderByComparator) {

		return getPersistence().fetchBygroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the cdt sessions before and after the current cdt session in the ordered set where groupId = &#63;.
	 *
	 * @param sessionId the primary key of the current cdt session
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cdt session
	 * @throws NoSuchCDTSessionException if a cdt session with the primary key could not be found
	 */
	public static CDTSession[] findBygroupId_PrevAndNext(
			long sessionId, long groupId,
			OrderByComparator<CDTSession> orderByComparator)
		throws com.weprode.nero.schedule.exception.NoSuchCDTSessionException {

		return getPersistence().findBygroupId_PrevAndNext(
			sessionId, groupId, orderByComparator);
	}

	/**
	 * Removes all the cdt sessions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeBygroupId(long groupId) {
		getPersistence().removeBygroupId(groupId);
	}

	/**
	 * Returns the number of cdt sessions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching cdt sessions
	 */
	public static int countBygroupId(long groupId) {
		return getPersistence().countBygroupId(groupId);
	}

	/**
	 * Caches the cdt session in the entity cache if it is enabled.
	 *
	 * @param cdtSession the cdt session
	 */
	public static void cacheResult(CDTSession cdtSession) {
		getPersistence().cacheResult(cdtSession);
	}

	/**
	 * Caches the cdt sessions in the entity cache if it is enabled.
	 *
	 * @param cdtSessions the cdt sessions
	 */
	public static void cacheResult(List<CDTSession> cdtSessions) {
		getPersistence().cacheResult(cdtSessions);
	}

	/**
	 * Creates a new cdt session with the primary key. Does not add the cdt session to the database.
	 *
	 * @param sessionId the primary key for the new cdt session
	 * @return the new cdt session
	 */
	public static CDTSession create(long sessionId) {
		return getPersistence().create(sessionId);
	}

	/**
	 * Removes the cdt session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session that was removed
	 * @throws NoSuchCDTSessionException if a cdt session with the primary key could not be found
	 */
	public static CDTSession remove(long sessionId)
		throws com.weprode.nero.schedule.exception.NoSuchCDTSessionException {

		return getPersistence().remove(sessionId);
	}

	public static CDTSession updateImpl(CDTSession cdtSession) {
		return getPersistence().updateImpl(cdtSession);
	}

	/**
	 * Returns the cdt session with the primary key or throws a <code>NoSuchCDTSessionException</code> if it could not be found.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session
	 * @throws NoSuchCDTSessionException if a cdt session with the primary key could not be found
	 */
	public static CDTSession findByPrimaryKey(long sessionId)
		throws com.weprode.nero.schedule.exception.NoSuchCDTSessionException {

		return getPersistence().findByPrimaryKey(sessionId);
	}

	/**
	 * Returns the cdt session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sessionId the primary key of the cdt session
	 * @return the cdt session, or <code>null</code> if a cdt session with the primary key could not be found
	 */
	public static CDTSession fetchByPrimaryKey(long sessionId) {
		return getPersistence().fetchByPrimaryKey(sessionId);
	}

	/**
	 * Returns all the cdt sessions.
	 *
	 * @return the cdt sessions
	 */
	public static List<CDTSession> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CDTSession> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CDTSession> findAll(
		int start, int end, OrderByComparator<CDTSession> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CDTSession> findAll(
		int start, int end, OrderByComparator<CDTSession> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the cdt sessions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of cdt sessions.
	 *
	 * @return the number of cdt sessions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CDTSessionPersistence getPersistence() {
		return _persistence;
	}

	private static volatile CDTSessionPersistence _persistence;

}