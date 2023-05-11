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

package com.weprode.nero.agenda.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.agenda.exception.NoSuchEventPopulationException;
import com.weprode.nero.agenda.model.EventPopulation;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the event population service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventPopulationUtil
 * @generated
 */
@ProviderType
public interface EventPopulationPersistence
	extends BasePersistence<EventPopulation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventPopulationUtil} to access the event population persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the event populations where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching event populations
	 */
	public java.util.List<EventPopulation> findByeventId(long eventId);

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
	public java.util.List<EventPopulation> findByeventId(
		long eventId, int start, int end);

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
	public java.util.List<EventPopulation> findByeventId(
		long eventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventPopulation>
			orderByComparator);

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
	public java.util.List<EventPopulation> findByeventId(
		long eventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventPopulation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event population
	 * @throws NoSuchEventPopulationException if a matching event population could not be found
	 */
	public EventPopulation findByeventId_First(
			long eventId,
			com.liferay.portal.kernel.util.OrderByComparator<EventPopulation>
				orderByComparator)
		throws NoSuchEventPopulationException;

	/**
	 * Returns the first event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event population, or <code>null</code> if a matching event population could not be found
	 */
	public EventPopulation fetchByeventId_First(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator<EventPopulation>
			orderByComparator);

	/**
	 * Returns the last event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event population
	 * @throws NoSuchEventPopulationException if a matching event population could not be found
	 */
	public EventPopulation findByeventId_Last(
			long eventId,
			com.liferay.portal.kernel.util.OrderByComparator<EventPopulation>
				orderByComparator)
		throws NoSuchEventPopulationException;

	/**
	 * Returns the last event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event population, or <code>null</code> if a matching event population could not be found
	 */
	public EventPopulation fetchByeventId_Last(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator<EventPopulation>
			orderByComparator);

	/**
	 * Returns the event populations before and after the current event population in the ordered set where eventId = &#63;.
	 *
	 * @param eventPopulationPK the primary key of the current event population
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event population
	 * @throws NoSuchEventPopulationException if a event population with the primary key could not be found
	 */
	public EventPopulation[] findByeventId_PrevAndNext(
			EventPopulationPK eventPopulationPK, long eventId,
			com.liferay.portal.kernel.util.OrderByComparator<EventPopulation>
				orderByComparator)
		throws NoSuchEventPopulationException;

	/**
	 * Removes all the event populations where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	public void removeByeventId(long eventId);

	/**
	 * Returns the number of event populations where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching event populations
	 */
	public int countByeventId(long eventId);

	/**
	 * Caches the event population in the entity cache if it is enabled.
	 *
	 * @param eventPopulation the event population
	 */
	public void cacheResult(EventPopulation eventPopulation);

	/**
	 * Caches the event populations in the entity cache if it is enabled.
	 *
	 * @param eventPopulations the event populations
	 */
	public void cacheResult(java.util.List<EventPopulation> eventPopulations);

	/**
	 * Creates a new event population with the primary key. Does not add the event population to the database.
	 *
	 * @param eventPopulationPK the primary key for the new event population
	 * @return the new event population
	 */
	public EventPopulation create(EventPopulationPK eventPopulationPK);

	/**
	 * Removes the event population with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventPopulationPK the primary key of the event population
	 * @return the event population that was removed
	 * @throws NoSuchEventPopulationException if a event population with the primary key could not be found
	 */
	public EventPopulation remove(EventPopulationPK eventPopulationPK)
		throws NoSuchEventPopulationException;

	public EventPopulation updateImpl(EventPopulation eventPopulation);

	/**
	 * Returns the event population with the primary key or throws a <code>NoSuchEventPopulationException</code> if it could not be found.
	 *
	 * @param eventPopulationPK the primary key of the event population
	 * @return the event population
	 * @throws NoSuchEventPopulationException if a event population with the primary key could not be found
	 */
	public EventPopulation findByPrimaryKey(EventPopulationPK eventPopulationPK)
		throws NoSuchEventPopulationException;

	/**
	 * Returns the event population with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventPopulationPK the primary key of the event population
	 * @return the event population, or <code>null</code> if a event population with the primary key could not be found
	 */
	public EventPopulation fetchByPrimaryKey(
		EventPopulationPK eventPopulationPK);

	/**
	 * Returns all the event populations.
	 *
	 * @return the event populations
	 */
	public java.util.List<EventPopulation> findAll();

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
	public java.util.List<EventPopulation> findAll(int start, int end);

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
	public java.util.List<EventPopulation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventPopulation>
			orderByComparator);

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
	public java.util.List<EventPopulation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventPopulation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the event populations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of event populations.
	 *
	 * @return the number of event populations
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}