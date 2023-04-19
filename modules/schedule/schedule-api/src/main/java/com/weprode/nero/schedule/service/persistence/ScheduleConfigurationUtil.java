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

import com.weprode.nero.schedule.model.ScheduleConfiguration;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the schedule configuration service. This utility wraps <code>com.weprode.nero.schedule.service.persistence.impl.ScheduleConfigurationPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleConfigurationPersistence
 * @generated
 */
public class ScheduleConfigurationUtil {

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
	public static void clearCache(ScheduleConfiguration scheduleConfiguration) {
		getPersistence().clearCache(scheduleConfiguration);
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
	public static Map<Serializable, ScheduleConfiguration> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ScheduleConfiguration> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ScheduleConfiguration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ScheduleConfiguration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ScheduleConfiguration> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ScheduleConfiguration update(
		ScheduleConfiguration scheduleConfiguration) {

		return getPersistence().update(scheduleConfiguration);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ScheduleConfiguration update(
		ScheduleConfiguration scheduleConfiguration,
		ServiceContext serviceContext) {

		return getPersistence().update(scheduleConfiguration, serviceContext);
	}

	/**
	 * Caches the schedule configuration in the entity cache if it is enabled.
	 *
	 * @param scheduleConfiguration the schedule configuration
	 */
	public static void cacheResult(
		ScheduleConfiguration scheduleConfiguration) {

		getPersistence().cacheResult(scheduleConfiguration);
	}

	/**
	 * Caches the schedule configurations in the entity cache if it is enabled.
	 *
	 * @param scheduleConfigurations the schedule configurations
	 */
	public static void cacheResult(
		List<ScheduleConfiguration> scheduleConfigurations) {

		getPersistence().cacheResult(scheduleConfigurations);
	}

	/**
	 * Creates a new schedule configuration with the primary key. Does not add the schedule configuration to the database.
	 *
	 * @param schoolId the primary key for the new schedule configuration
	 * @return the new schedule configuration
	 */
	public static ScheduleConfiguration create(long schoolId) {
		return getPersistence().create(schoolId);
	}

	/**
	 * Removes the schedule configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoolId the primary key of the schedule configuration
	 * @return the schedule configuration that was removed
	 * @throws NoSuchConfigurationException if a schedule configuration with the primary key could not be found
	 */
	public static ScheduleConfiguration remove(long schoolId)
		throws com.weprode.nero.schedule.exception.
			NoSuchConfigurationException {

		return getPersistence().remove(schoolId);
	}

	public static ScheduleConfiguration updateImpl(
		ScheduleConfiguration scheduleConfiguration) {

		return getPersistence().updateImpl(scheduleConfiguration);
	}

	/**
	 * Returns the schedule configuration with the primary key or throws a <code>NoSuchConfigurationException</code> if it could not be found.
	 *
	 * @param schoolId the primary key of the schedule configuration
	 * @return the schedule configuration
	 * @throws NoSuchConfigurationException if a schedule configuration with the primary key could not be found
	 */
	public static ScheduleConfiguration findByPrimaryKey(long schoolId)
		throws com.weprode.nero.schedule.exception.
			NoSuchConfigurationException {

		return getPersistence().findByPrimaryKey(schoolId);
	}

	/**
	 * Returns the schedule configuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoolId the primary key of the schedule configuration
	 * @return the schedule configuration, or <code>null</code> if a schedule configuration with the primary key could not be found
	 */
	public static ScheduleConfiguration fetchByPrimaryKey(long schoolId) {
		return getPersistence().fetchByPrimaryKey(schoolId);
	}

	/**
	 * Returns all the schedule configurations.
	 *
	 * @return the schedule configurations
	 */
	public static List<ScheduleConfiguration> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the schedule configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ScheduleConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedule configurations
	 * @param end the upper bound of the range of schedule configurations (not inclusive)
	 * @return the range of schedule configurations
	 */
	public static List<ScheduleConfiguration> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the schedule configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ScheduleConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedule configurations
	 * @param end the upper bound of the range of schedule configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of schedule configurations
	 */
	public static List<ScheduleConfiguration> findAll(
		int start, int end,
		OrderByComparator<ScheduleConfiguration> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the schedule configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ScheduleConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of schedule configurations
	 * @param end the upper bound of the range of schedule configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of schedule configurations
	 */
	public static List<ScheduleConfiguration> findAll(
		int start, int end,
		OrderByComparator<ScheduleConfiguration> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the schedule configurations from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of schedule configurations.
	 *
	 * @return the number of schedule configurations
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ScheduleConfigurationPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ScheduleConfigurationPersistence _persistence;

}