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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.schedule.exception.NoSuchWeeklyScheduleException;
import com.weprode.nero.schedule.model.WeeklySchedule;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the weekly schedule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WeeklyScheduleUtil
 * @generated
 */
@ProviderType
public interface WeeklySchedulePersistence
	extends BasePersistence<WeeklySchedule> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WeeklyScheduleUtil} to access the weekly schedule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the weekly schedules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching weekly schedules
	 */
	public java.util.List<WeeklySchedule> findByschoolId(long schoolId);

	/**
	 * Returns a range of all the weekly schedules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WeeklyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of weekly schedules
	 * @param end the upper bound of the range of weekly schedules (not inclusive)
	 * @return the range of matching weekly schedules
	 */
	public java.util.List<WeeklySchedule> findByschoolId(
		long schoolId, int start, int end);

	/**
	 * Returns an ordered range of all the weekly schedules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WeeklyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of weekly schedules
	 * @param end the upper bound of the range of weekly schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching weekly schedules
	 */
	public java.util.List<WeeklySchedule> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WeeklySchedule>
			orderByComparator);

	/**
	 * Returns an ordered range of all the weekly schedules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WeeklyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of weekly schedules
	 * @param end the upper bound of the range of weekly schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching weekly schedules
	 */
	public java.util.List<WeeklySchedule> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WeeklySchedule>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first weekly schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching weekly schedule
	 * @throws NoSuchWeeklyScheduleException if a matching weekly schedule could not be found
	 */
	public WeeklySchedule findByschoolId_First(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<WeeklySchedule>
				orderByComparator)
		throws NoSuchWeeklyScheduleException;

	/**
	 * Returns the first weekly schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching weekly schedule, or <code>null</code> if a matching weekly schedule could not be found
	 */
	public WeeklySchedule fetchByschoolId_First(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<WeeklySchedule>
			orderByComparator);

	/**
	 * Returns the last weekly schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching weekly schedule
	 * @throws NoSuchWeeklyScheduleException if a matching weekly schedule could not be found
	 */
	public WeeklySchedule findByschoolId_Last(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<WeeklySchedule>
				orderByComparator)
		throws NoSuchWeeklyScheduleException;

	/**
	 * Returns the last weekly schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching weekly schedule, or <code>null</code> if a matching weekly schedule could not be found
	 */
	public WeeklySchedule fetchByschoolId_Last(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<WeeklySchedule>
			orderByComparator);

	/**
	 * Returns the weekly schedules before and after the current weekly schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param weeklySchedulePK the primary key of the current weekly schedule
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next weekly schedule
	 * @throws NoSuchWeeklyScheduleException if a weekly schedule with the primary key could not be found
	 */
	public WeeklySchedule[] findByschoolId_PrevAndNext(
			WeeklySchedulePK weeklySchedulePK, long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<WeeklySchedule>
				orderByComparator)
		throws NoSuchWeeklyScheduleException;

	/**
	 * Removes all the weekly schedules where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public void removeByschoolId(long schoolId);

	/**
	 * Returns the number of weekly schedules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching weekly schedules
	 */
	public int countByschoolId(long schoolId);

	/**
	 * Caches the weekly schedule in the entity cache if it is enabled.
	 *
	 * @param weeklySchedule the weekly schedule
	 */
	public void cacheResult(WeeklySchedule weeklySchedule);

	/**
	 * Caches the weekly schedules in the entity cache if it is enabled.
	 *
	 * @param weeklySchedules the weekly schedules
	 */
	public void cacheResult(java.util.List<WeeklySchedule> weeklySchedules);

	/**
	 * Creates a new weekly schedule with the primary key. Does not add the weekly schedule to the database.
	 *
	 * @param weeklySchedulePK the primary key for the new weekly schedule
	 * @return the new weekly schedule
	 */
	public WeeklySchedule create(WeeklySchedulePK weeklySchedulePK);

	/**
	 * Removes the weekly schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param weeklySchedulePK the primary key of the weekly schedule
	 * @return the weekly schedule that was removed
	 * @throws NoSuchWeeklyScheduleException if a weekly schedule with the primary key could not be found
	 */
	public WeeklySchedule remove(WeeklySchedulePK weeklySchedulePK)
		throws NoSuchWeeklyScheduleException;

	public WeeklySchedule updateImpl(WeeklySchedule weeklySchedule);

	/**
	 * Returns the weekly schedule with the primary key or throws a <code>NoSuchWeeklyScheduleException</code> if it could not be found.
	 *
	 * @param weeklySchedulePK the primary key of the weekly schedule
	 * @return the weekly schedule
	 * @throws NoSuchWeeklyScheduleException if a weekly schedule with the primary key could not be found
	 */
	public WeeklySchedule findByPrimaryKey(WeeklySchedulePK weeklySchedulePK)
		throws NoSuchWeeklyScheduleException;

	/**
	 * Returns the weekly schedule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param weeklySchedulePK the primary key of the weekly schedule
	 * @return the weekly schedule, or <code>null</code> if a weekly schedule with the primary key could not be found
	 */
	public WeeklySchedule fetchByPrimaryKey(WeeklySchedulePK weeklySchedulePK);

	/**
	 * Returns all the weekly schedules.
	 *
	 * @return the weekly schedules
	 */
	public java.util.List<WeeklySchedule> findAll();

	/**
	 * Returns a range of all the weekly schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WeeklyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of weekly schedules
	 * @param end the upper bound of the range of weekly schedules (not inclusive)
	 * @return the range of weekly schedules
	 */
	public java.util.List<WeeklySchedule> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the weekly schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WeeklyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of weekly schedules
	 * @param end the upper bound of the range of weekly schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of weekly schedules
	 */
	public java.util.List<WeeklySchedule> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WeeklySchedule>
			orderByComparator);

	/**
	 * Returns an ordered range of all the weekly schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>WeeklyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of weekly schedules
	 * @param end the upper bound of the range of weekly schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of weekly schedules
	 */
	public java.util.List<WeeklySchedule> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WeeklySchedule>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the weekly schedules from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of weekly schedules.
	 *
	 * @return the number of weekly schedules
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}