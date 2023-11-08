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

import com.weprode.facile.agenda.model.EventPopulation;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the event population service. This utility wraps <code>com.weprode.facile.agenda.service.persistence.impl.EventPopulationPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventPopulationPersistence
 * @generated
 */
public class EventPopulationUtil {

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
	public static void clearCache(EventPopulation eventPopulation) {
		getPersistence().clearCache(eventPopulation);
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
	public static Map<Serializable, EventPopulation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EventPopulation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EventPopulation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EventPopulation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EventPopulation> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EventPopulation update(EventPopulation eventPopulation) {
		return getPersistence().update(eventPopulation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EventPopulation update(
		EventPopulation eventPopulation, ServiceContext serviceContext) {

		return getPersistence().update(eventPopulation, serviceContext);
	}

	/**
	 * Returns all the event populations where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching event populations
	 */
	public static List<EventPopulation> findByeventId(long eventId) {
		return getPersistence().findByeventId(eventId);
	}

	/**
	 * Returns a range of all the event populations where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @return the range of matching event populations
	 */
	public static List<EventPopulation> findByeventId(
		long eventId, int start, int end) {

		return getPersistence().findByeventId(eventId, start, end);
	}

	/**
	 * Returns an ordered range of all the event populations where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching event populations
	 */
	public static List<EventPopulation> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<EventPopulation> orderByComparator) {

		return getPersistence().findByeventId(
			eventId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the event populations where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching event populations
	 */
	public static List<EventPopulation> findByeventId(
		long eventId, int start, int end,
		OrderByComparator<EventPopulation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByeventId(
			eventId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event population
	 * @throws NoSuchEventPopulationException if a matching event population could not be found
	 */
	public static EventPopulation findByeventId_First(
			long eventId, OrderByComparator<EventPopulation> orderByComparator)
		throws com.weprode.facile.agenda.exception.
			NoSuchEventPopulationException {

		return getPersistence().findByeventId_First(eventId, orderByComparator);
	}

	/**
	 * Returns the first event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event population, or <code>null</code> if a matching event population could not be found
	 */
	public static EventPopulation fetchByeventId_First(
		long eventId, OrderByComparator<EventPopulation> orderByComparator) {

		return getPersistence().fetchByeventId_First(
			eventId, orderByComparator);
	}

	/**
	 * Returns the last event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event population
	 * @throws NoSuchEventPopulationException if a matching event population could not be found
	 */
	public static EventPopulation findByeventId_Last(
			long eventId, OrderByComparator<EventPopulation> orderByComparator)
		throws com.weprode.facile.agenda.exception.
			NoSuchEventPopulationException {

		return getPersistence().findByeventId_Last(eventId, orderByComparator);
	}

	/**
	 * Returns the last event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event population, or <code>null</code> if a matching event population could not be found
	 */
	public static EventPopulation fetchByeventId_Last(
		long eventId, OrderByComparator<EventPopulation> orderByComparator) {

		return getPersistence().fetchByeventId_Last(eventId, orderByComparator);
	}

	/**
	 * Returns the event populations before and after the current event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventPopulationPK the primary key of the current event population
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event population
	 * @throws NoSuchEventPopulationException if a event population with the primary key could not be found
	 */
	public static EventPopulation[] findByeventId_PrevAndNext(
			EventPopulationPK eventPopulationPK, long eventId,
			OrderByComparator<EventPopulation> orderByComparator)
		throws com.weprode.facile.agenda.exception.
			NoSuchEventPopulationException {

		return getPersistence().findByeventId_PrevAndNext(
			eventPopulationPK, eventId, orderByComparator);
	}

	/**
	 * Removes all the event populations where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	public static void removeByeventId(long eventId) {
		getPersistence().removeByeventId(eventId);
	}

	/**
	 * Returns the number of event populations where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching event populations
	 */
	public static int countByeventId(long eventId) {
		return getPersistence().countByeventId(eventId);
	}

	/**
	 * Caches the event population in the entity cache if it is enabled.
	 *
	 * @param eventPopulation the event population
	 */
	public static void cacheResult(EventPopulation eventPopulation) {
		getPersistence().cacheResult(eventPopulation);
	}

	/**
	 * Caches the event populations in the entity cache if it is enabled.
	 *
	 * @param eventPopulations the event populations
	 */
	public static void cacheResult(List<EventPopulation> eventPopulations) {
		getPersistence().cacheResult(eventPopulations);
	}

	/**
	 * Creates a new event population with the primary key. Does not add the event population to the database.
	 *
	 * @param eventPopulationPK the primary key for the new event population
	 * @return the new event population
	 */
	public static EventPopulation create(EventPopulationPK eventPopulationPK) {
		return getPersistence().create(eventPopulationPK);
	}

	/**
	 * Removes the event population with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventPopulationPK the primary key of the event population
	 * @return the event population that was removed
	 * @throws NoSuchEventPopulationException if a event population with the primary key could not be found
	 */
	public static EventPopulation remove(EventPopulationPK eventPopulationPK)
		throws com.weprode.facile.agenda.exception.
			NoSuchEventPopulationException {

		return getPersistence().remove(eventPopulationPK);
	}

	public static EventPopulation updateImpl(EventPopulation eventPopulation) {
		return getPersistence().updateImpl(eventPopulation);
	}

	/**
	 * Returns the event population with the primary key or throws a <code>NoSuchEventPopulationException</code> if it could not be found.
	 *
	 * @param eventPopulationPK the primary key of the event population
	 * @return the event population
	 * @throws NoSuchEventPopulationException if a event population with the primary key could not be found
	 */
	public static EventPopulation findByPrimaryKey(
			EventPopulationPK eventPopulationPK)
		throws com.weprode.facile.agenda.exception.
			NoSuchEventPopulationException {

		return getPersistence().findByPrimaryKey(eventPopulationPK);
	}

	/**
	 * Returns the event population with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventPopulationPK the primary key of the event population
	 * @return the event population, or <code>null</code> if a event population with the primary key could not be found
	 */
	public static EventPopulation fetchByPrimaryKey(
		EventPopulationPK eventPopulationPK) {

		return getPersistence().fetchByPrimaryKey(eventPopulationPK);
	}

	/**
	 * Returns all the event populations.
	 *
	 * @return the event populations
	 */
	public static List<EventPopulation> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the event populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @return the range of event populations
	 */
	public static List<EventPopulation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the event populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of event populations
	 */
	public static List<EventPopulation> findAll(
		int start, int end,
		OrderByComparator<EventPopulation> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the event populations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EventPopulationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of event populations
	 * @param end the upper bound of the range of event populations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of event populations
	 */
	public static List<EventPopulation> findAll(
		int start, int end,
		OrderByComparator<EventPopulation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the event populations from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of event populations.
	 *
	 * @return the number of event populations
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static EventPopulationPersistence getPersistence() {
		return _persistence;
	}

	private static volatile EventPopulationPersistence _persistence;

}