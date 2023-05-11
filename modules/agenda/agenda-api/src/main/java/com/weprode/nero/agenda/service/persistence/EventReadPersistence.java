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

import com.weprode.nero.agenda.exception.NoSuchEventReadException;
import com.weprode.nero.agenda.model.EventRead;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the event read service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventReadUtil
 * @generated
 */
@ProviderType
public interface EventReadPersistence extends BasePersistence<EventRead> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventReadUtil} to access the event read persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the event reads where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching event reads
	 */
	public java.util.List<EventRead> findByeventId(long eventId);

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
	public java.util.List<EventRead> findByeventId(
		long eventId, int start, int end);

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
	public java.util.List<EventRead> findByeventId(
		long eventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventRead>
			orderByComparator);

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
	public java.util.List<EventRead> findByeventId(
		long eventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventRead>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event read
	 * @throws NoSuchEventReadException if a matching event read could not be found
	 */
	public EventRead findByeventId_First(
			long eventId,
			com.liferay.portal.kernel.util.OrderByComparator<EventRead>
				orderByComparator)
		throws NoSuchEventReadException;

	/**
	 * Returns the first event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching event read, or <code>null</code> if a matching event read could not be found
	 */
	public EventRead fetchByeventId_First(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator<EventRead>
			orderByComparator);

	/**
	 * Returns the last event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event read
	 * @throws NoSuchEventReadException if a matching event read could not be found
	 */
	public EventRead findByeventId_Last(
			long eventId,
			com.liferay.portal.kernel.util.OrderByComparator<EventRead>
				orderByComparator)
		throws NoSuchEventReadException;

	/**
	 * Returns the last event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching event read, or <code>null</code> if a matching event read could not be found
	 */
	public EventRead fetchByeventId_Last(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator<EventRead>
			orderByComparator);

	/**
	 * Returns the event reads before and after the current event read in the ordered set where eventId = &#63;.
	 *
	 * @param eventReadPK the primary key of the current event read
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next event read
	 * @throws NoSuchEventReadException if a event read with the primary key could not be found
	 */
	public EventRead[] findByeventId_PrevAndNext(
			EventReadPK eventReadPK, long eventId,
			com.liferay.portal.kernel.util.OrderByComparator<EventRead>
				orderByComparator)
		throws NoSuchEventReadException;

	/**
	 * Removes all the event reads where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	public void removeByeventId(long eventId);

	/**
	 * Returns the number of event reads where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching event reads
	 */
	public int countByeventId(long eventId);

	/**
	 * Caches the event read in the entity cache if it is enabled.
	 *
	 * @param eventRead the event read
	 */
	public void cacheResult(EventRead eventRead);

	/**
	 * Caches the event reads in the entity cache if it is enabled.
	 *
	 * @param eventReads the event reads
	 */
	public void cacheResult(java.util.List<EventRead> eventReads);

	/**
	 * Creates a new event read with the primary key. Does not add the event read to the database.
	 *
	 * @param eventReadPK the primary key for the new event read
	 * @return the new event read
	 */
	public EventRead create(EventReadPK eventReadPK);

	/**
	 * Removes the event read with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventReadPK the primary key of the event read
	 * @return the event read that was removed
	 * @throws NoSuchEventReadException if a event read with the primary key could not be found
	 */
	public EventRead remove(EventReadPK eventReadPK)
		throws NoSuchEventReadException;

	public EventRead updateImpl(EventRead eventRead);

	/**
	 * Returns the event read with the primary key or throws a <code>NoSuchEventReadException</code> if it could not be found.
	 *
	 * @param eventReadPK the primary key of the event read
	 * @return the event read
	 * @throws NoSuchEventReadException if a event read with the primary key could not be found
	 */
	public EventRead findByPrimaryKey(EventReadPK eventReadPK)
		throws NoSuchEventReadException;

	/**
	 * Returns the event read with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventReadPK the primary key of the event read
	 * @return the event read, or <code>null</code> if a event read with the primary key could not be found
	 */
	public EventRead fetchByPrimaryKey(EventReadPK eventReadPK);

	/**
	 * Returns all the event reads.
	 *
	 * @return the event reads
	 */
	public java.util.List<EventRead> findAll();

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
	public java.util.List<EventRead> findAll(int start, int end);

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
	public java.util.List<EventRead> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventRead>
			orderByComparator);

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
	public java.util.List<EventRead> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventRead>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the event reads from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of event reads.
	 *
	 * @return the number of event reads
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}