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

import com.weprode.nero.schedule.exception.NoSuchDailyScheduleException;
import com.weprode.nero.schedule.model.DailySchedule;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the daily schedule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DailyScheduleUtil
 * @generated
 */
@ProviderType
public interface DailySchedulePersistence
	extends BasePersistence<DailySchedule> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DailyScheduleUtil} to access the daily schedule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the daily schedules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching daily schedules
	 */
	public java.util.List<DailySchedule> findByschoolId(long schoolId);

	/**
	 * Returns a range of all the daily schedules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @return the range of matching daily schedules
	 */
	public java.util.List<DailySchedule> findByschoolId(
		long schoolId, int start, int end);

	/**
	 * Returns an ordered range of all the daily schedules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching daily schedules
	 */
	public java.util.List<DailySchedule> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DailySchedule>
			orderByComparator);

	/**
	 * Returns an ordered range of all the daily schedules where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching daily schedules
	 */
	public java.util.List<DailySchedule> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DailySchedule>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching daily schedule
	 * @throws NoSuchDailyScheduleException if a matching daily schedule could not be found
	 */
	public DailySchedule findByschoolId_First(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<DailySchedule>
				orderByComparator)
		throws NoSuchDailyScheduleException;

	/**
	 * Returns the first daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching daily schedule, or <code>null</code> if a matching daily schedule could not be found
	 */
	public DailySchedule fetchByschoolId_First(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<DailySchedule>
			orderByComparator);

	/**
	 * Returns the last daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching daily schedule
	 * @throws NoSuchDailyScheduleException if a matching daily schedule could not be found
	 */
	public DailySchedule findByschoolId_Last(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<DailySchedule>
				orderByComparator)
		throws NoSuchDailyScheduleException;

	/**
	 * Returns the last daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching daily schedule, or <code>null</code> if a matching daily schedule could not be found
	 */
	public DailySchedule fetchByschoolId_Last(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<DailySchedule>
			orderByComparator);

	/**
	 * Returns the daily schedules before and after the current daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param dailySchedulePK the primary key of the current daily schedule
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next daily schedule
	 * @throws NoSuchDailyScheduleException if a daily schedule with the primary key could not be found
	 */
	public DailySchedule[] findByschoolId_PrevAndNext(
			DailySchedulePK dailySchedulePK, long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<DailySchedule>
				orderByComparator)
		throws NoSuchDailyScheduleException;

	/**
	 * Removes all the daily schedules where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public void removeByschoolId(long schoolId);

	/**
	 * Returns the number of daily schedules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching daily schedules
	 */
	public int countByschoolId(long schoolId);

	/**
	 * Caches the daily schedule in the entity cache if it is enabled.
	 *
	 * @param dailySchedule the daily schedule
	 */
	public void cacheResult(DailySchedule dailySchedule);

	/**
	 * Caches the daily schedules in the entity cache if it is enabled.
	 *
	 * @param dailySchedules the daily schedules
	 */
	public void cacheResult(java.util.List<DailySchedule> dailySchedules);

	/**
	 * Creates a new daily schedule with the primary key. Does not add the daily schedule to the database.
	 *
	 * @param dailySchedulePK the primary key for the new daily schedule
	 * @return the new daily schedule
	 */
	public DailySchedule create(DailySchedulePK dailySchedulePK);

	/**
	 * Removes the daily schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dailySchedulePK the primary key of the daily schedule
	 * @return the daily schedule that was removed
	 * @throws NoSuchDailyScheduleException if a daily schedule with the primary key could not be found
	 */
	public DailySchedule remove(DailySchedulePK dailySchedulePK)
		throws NoSuchDailyScheduleException;

	public DailySchedule updateImpl(DailySchedule dailySchedule);

	/**
	 * Returns the daily schedule with the primary key or throws a <code>NoSuchDailyScheduleException</code> if it could not be found.
	 *
	 * @param dailySchedulePK the primary key of the daily schedule
	 * @return the daily schedule
	 * @throws NoSuchDailyScheduleException if a daily schedule with the primary key could not be found
	 */
	public DailySchedule findByPrimaryKey(DailySchedulePK dailySchedulePK)
		throws NoSuchDailyScheduleException;

	/**
	 * Returns the daily schedule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dailySchedulePK the primary key of the daily schedule
	 * @return the daily schedule, or <code>null</code> if a daily schedule with the primary key could not be found
	 */
	public DailySchedule fetchByPrimaryKey(DailySchedulePK dailySchedulePK);

	/**
	 * Returns all the daily schedules.
	 *
	 * @return the daily schedules
	 */
	public java.util.List<DailySchedule> findAll();

	/**
	 * Returns a range of all the daily schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @return the range of daily schedules
	 */
	public java.util.List<DailySchedule> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the daily schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of daily schedules
	 */
	public java.util.List<DailySchedule> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DailySchedule>
			orderByComparator);

	/**
	 * Returns an ordered range of all the daily schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DailyScheduleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of daily schedules
	 * @param end the upper bound of the range of daily schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of daily schedules
	 */
	public java.util.List<DailySchedule> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DailySchedule>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the daily schedules from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of daily schedules.
	 *
	 * @return the number of daily schedules
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}