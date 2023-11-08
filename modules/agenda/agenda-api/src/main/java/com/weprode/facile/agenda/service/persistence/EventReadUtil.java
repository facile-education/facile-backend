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

package com.weprode.facile.agenda.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.agenda.model.EventRead;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the event read service. This utility wraps <code>com.weprode.facile.agenda.service.persistence.impl.EventReadPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventReadPersistence
 * @generated
 */
public class EventReadUtil {

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
	public static void clearCache(EventRead eventRead) {
		getPersistence().clearCache(eventRead);
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
	public static Map<Serializable, EventRead> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EventRead> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EventRead> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EventRead> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EventRead> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EventRead update(EventRead eventRead) {
		return getPersistence().update(eventRead);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EventRead update(
		EventRead eventRead, ServiceContext serviceContext) {

		return getPersistence().update(eventRead, serviceContext);
	}

	/**
	 * Returns all the event reads where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching event reads
	 */
	public static List<EventRead> findByeventId(long eventId) {
		return getPersistence().findByeventId(eventId);
	}

	/**
	 * Returns a range of all the event reads where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @return the range of matching event reads
	 */
	public static List<EventRead> findByeventId(
		long eventId, int start, int end) {

		return getPersistence().findByeventId(eventId, start, end);
	}

	/**
	 * Returns an ordered range of all the event reads where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event reads
	 */
	public static List<EventRead> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<EventRead> orderByComparator) {

		return getPersistence().findByeventId(
			eventId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the event reads where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching event reads
	 */
	public static List<EventRead> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<EventRead> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByeventId(
			eventId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event read
	 * @throws NoSuchEventReadException if a matching event read could not be found
	 */
	public static EventRead findByeventId_First(
			long eventId, OrderByComparator<EventRead> orderByComparator)
		throws com.weprode.facile.agenda.exception.NoSuchEventReadException {

		return getPersistence().findByeventId_First(eventId, orderByComparator);
	}

	/**
	 * Returns the first event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event read, or <code>null</code> if a matching event read could not be found
	 */
	public static EventRead fetchByeventId_First(
		long eventId, OrderByComparator<EventRead> orderByComparator) {

		return getPersistence().fetchByeventId_First(
			eventId, orderByComparator);
	}

	/**
	 * Returns the last event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event read
	 * @throws NoSuchEventReadException if a matching event read could not be found
	 */
	public static EventRead findByeventId_Last(
			long eventId, OrderByComparator<EventRead> orderByComparator)
		throws com.weprode.facile.agenda.exception.NoSuchEventReadException {

		return getPersistence().findByeventId_Last(eventId, orderByComparator);
	}

	/**
	 * Returns the last event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event read, or <code>null</code> if a matching event read could not be found
	 */
	public static EventRead fetchByeventId_Last(
		long eventId, OrderByComparator<EventRead> orderByComparator) {

		return getPersistence().fetchByeventId_Last(eventId, orderByComparator);
	}

	/**
	 * Returns the event reads before and after the current event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventReadPK the primary key of the current event read
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event read
	 * @throws NoSuchEventReadException if a event read with the primary key could not be found
	 */
	public static EventRead[] findByeventId_PrevAndNext(
			EventReadPK eventReadPK, long eventId,
			OrderByComparator<EventRead> orderByComparator)
		throws com.weprode.facile.agenda.exception.NoSuchEventReadException {

		return getPersistence().findByeventId_PrevAndNext(
			eventReadPK, eventId, orderByComparator);
	}

	/**
	 * Removes all the event reads where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	public static void removeByeventId(long eventId) {
		getPersistence().removeByeventId(eventId);
	}

	/**
	 * Returns the number of event reads where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching event reads
	 */
	public static int countByeventId(long eventId) {
		return getPersistence().countByeventId(eventId);
	}

	/**
	 * Caches the event read in the entity cache if it is enabled.
	 *
	 * @param eventRead the event read
	 */
	public static void cacheResult(EventRead eventRead) {
		getPersistence().cacheResult(eventRead);
	}

	/**
	 * Caches the event reads in the entity cache if it is enabled.
	 *
	 * @param eventReads the event reads
	 */
	public static void cacheResult(List<EventRead> eventReads) {
		getPersistence().cacheResult(eventReads);
	}

	/**
	 * Creates a new event read with the primary key. Does not add the event read to the database.
	 *
	 * @param eventReadPK the primary key for the new event read
	 * @return the new event read
	 */
	public static EventRead create(EventReadPK eventReadPK) {
		return getPersistence().create(eventReadPK);
	}

	/**
	 * Removes the event read with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventReadPK the primary key of the event read
	 * @return the event read that was removed
	 * @throws NoSuchEventReadException if a event read with the primary key could not be found
	 */
	public static EventRead remove(EventReadPK eventReadPK)
		throws com.weprode.facile.agenda.exception.NoSuchEventReadException {

		return getPersistence().remove(eventReadPK);
	}

	public static EventRead updateImpl(EventRead eventRead) {
		return getPersistence().updateImpl(eventRead);
	}

	/**
	 * Returns the event read with the primary key or throws a <code>NoSuchEventReadException</code> if it could not be found.
	 *
	 * @param eventReadPK the primary key of the event read
	 * @return the event read
	 * @throws NoSuchEventReadException if a event read with the primary key could not be found
	 */
	public static EventRead findByPrimaryKey(EventReadPK eventReadPK)
		throws com.weprode.facile.agenda.exception.NoSuchEventReadException {

		return getPersistence().findByPrimaryKey(eventReadPK);
	}

	/**
	 * Returns the event read with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventReadPK the primary key of the event read
	 * @return the event read, or <code>null</code> if a event read with the primary key could not be found
	 */
	public static EventRead fetchByPrimaryKey(EventReadPK eventReadPK) {
		return getPersistence().fetchByPrimaryKey(eventReadPK);
	}

	/**
	 * Returns all the event reads.
	 *
	 * @return the event reads
	 */
	public static List<EventRead> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the event reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @return the range of event reads
	 */
	public static List<EventRead> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the event reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of event reads
	 */
	public static List<EventRead> findAll(
		int start, int end, OrderByComparator<EventRead> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the event reads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventReadModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event reads
	 * @param end the upper bound of the range of event reads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of event reads
	 */
	public static List<EventRead> findAll(
		int start, int end, OrderByComparator<EventRead> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the event reads from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of event reads.
	 *
	 * @return the number of event reads
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static EventReadPersistence getPersistence() {
		return _persistence;
	}

	private static volatile EventReadPersistence _persistence;

}