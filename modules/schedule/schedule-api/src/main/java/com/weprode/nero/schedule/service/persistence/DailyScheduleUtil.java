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

import com.weprode.nero.schedule.model.DailySchedule;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the daily schedule service. This utility wraps <code>com.weprode.nero.schedule.service.persistence.impl.DailySchedulePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DailySchedulePersistence
 * @generated
 */
public class DailyScheduleUtil {

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
	public static void clearCache(DailySchedule dailySchedule) {
		getPersistence().clearCache(dailySchedule);
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
	public static Map<Serializable, DailySchedule> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DailySchedule> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DailySchedule> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DailySchedule> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DailySchedule> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DailySchedule update(DailySchedule dailySchedule) {
		return getPersistence().update(dailySchedule);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DailySchedule update(
		DailySchedule dailySchedule, ServiceContext serviceContext) {

		return getPersistence().update(dailySchedule, serviceContext);
	}

	/**
	 * Returns all the daily schedules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching daily schedules
	 */
	public static List<DailySchedule> findByschoolId(long schoolId) {
		return getPersistence().findByschoolId(schoolId);
	}

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
	public static List<DailySchedule> findByschoolId(
		long schoolId, int start, int end) {

		return getPersistence().findByschoolId(schoolId, start, end);
	}

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
	public static List<DailySchedule> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<DailySchedule> orderByComparator) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator);
	}

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
	public static List<DailySchedule> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<DailySchedule> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching daily schedule
	 * @throws NoSuchDailyScheduleException if a matching daily schedule could not be found
	 */
	public static DailySchedule findByschoolId_First(
			long schoolId, OrderByComparator<DailySchedule> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchDailyScheduleException {

		return getPersistence().findByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the first daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching daily schedule, or <code>null</code> if a matching daily schedule could not be found
	 */
	public static DailySchedule fetchByschoolId_First(
		long schoolId, OrderByComparator<DailySchedule> orderByComparator) {

		return getPersistence().fetchByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching daily schedule
	 * @throws NoSuchDailyScheduleException if a matching daily schedule could not be found
	 */
	public static DailySchedule findByschoolId_Last(
			long schoolId, OrderByComparator<DailySchedule> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchDailyScheduleException {

		return getPersistence().findByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching daily schedule, or <code>null</code> if a matching daily schedule could not be found
	 */
	public static DailySchedule fetchByschoolId_Last(
		long schoolId, OrderByComparator<DailySchedule> orderByComparator) {

		return getPersistence().fetchByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the daily schedules before and after the current daily schedule in the ordered set where schoolId = &#63;.
	 *
	 * @param dailySchedulePK the primary key of the current daily schedule
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next daily schedule
	 * @throws NoSuchDailyScheduleException if a daily schedule with the primary key could not be found
	 */
	public static DailySchedule[] findByschoolId_PrevAndNext(
			DailySchedulePK dailySchedulePK, long schoolId,
			OrderByComparator<DailySchedule> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchDailyScheduleException {

		return getPersistence().findByschoolId_PrevAndNext(
			dailySchedulePK, schoolId, orderByComparator);
	}

	/**
	 * Removes all the daily schedules where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public static void removeByschoolId(long schoolId) {
		getPersistence().removeByschoolId(schoolId);
	}

	/**
	 * Returns the number of daily schedules where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching daily schedules
	 */
	public static int countByschoolId(long schoolId) {
		return getPersistence().countByschoolId(schoolId);
	}

	/**
	 * Caches the daily schedule in the entity cache if it is enabled.
	 *
	 * @param dailySchedule the daily schedule
	 */
	public static void cacheResult(DailySchedule dailySchedule) {
		getPersistence().cacheResult(dailySchedule);
	}

	/**
	 * Caches the daily schedules in the entity cache if it is enabled.
	 *
	 * @param dailySchedules the daily schedules
	 */
	public static void cacheResult(List<DailySchedule> dailySchedules) {
		getPersistence().cacheResult(dailySchedules);
	}

	/**
	 * Creates a new daily schedule with the primary key. Does not add the daily schedule to the database.
	 *
	 * @param dailySchedulePK the primary key for the new daily schedule
	 * @return the new daily schedule
	 */
	public static DailySchedule create(DailySchedulePK dailySchedulePK) {
		return getPersistence().create(dailySchedulePK);
	}

	/**
	 * Removes the daily schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dailySchedulePK the primary key of the daily schedule
	 * @return the daily schedule that was removed
	 * @throws NoSuchDailyScheduleException if a daily schedule with the primary key could not be found
	 */
	public static DailySchedule remove(DailySchedulePK dailySchedulePK)
		throws com.weprode.nero.schedule.exception.
			NoSuchDailyScheduleException {

		return getPersistence().remove(dailySchedulePK);
	}

	public static DailySchedule updateImpl(DailySchedule dailySchedule) {
		return getPersistence().updateImpl(dailySchedule);
	}

	/**
	 * Returns the daily schedule with the primary key or throws a <code>NoSuchDailyScheduleException</code> if it could not be found.
	 *
	 * @param dailySchedulePK the primary key of the daily schedule
	 * @return the daily schedule
	 * @throws NoSuchDailyScheduleException if a daily schedule with the primary key could not be found
	 */
	public static DailySchedule findByPrimaryKey(
			DailySchedulePK dailySchedulePK)
		throws com.weprode.nero.schedule.exception.
			NoSuchDailyScheduleException {

		return getPersistence().findByPrimaryKey(dailySchedulePK);
	}

	/**
	 * Returns the daily schedule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dailySchedulePK the primary key of the daily schedule
	 * @return the daily schedule, or <code>null</code> if a daily schedule with the primary key could not be found
	 */
	public static DailySchedule fetchByPrimaryKey(
		DailySchedulePK dailySchedulePK) {

		return getPersistence().fetchByPrimaryKey(dailySchedulePK);
	}

	/**
	 * Returns all the daily schedules.
	 *
	 * @return the daily schedules
	 */
	public static List<DailySchedule> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<DailySchedule> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<DailySchedule> findAll(
		int start, int end,
		OrderByComparator<DailySchedule> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<DailySchedule> findAll(
		int start, int end, OrderByComparator<DailySchedule> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the daily schedules from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of daily schedules.
	 *
	 * @return the number of daily schedules
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static DailySchedulePersistence getPersistence() {
		return _persistence;
	}

	private static volatile DailySchedulePersistence _persistence;

}