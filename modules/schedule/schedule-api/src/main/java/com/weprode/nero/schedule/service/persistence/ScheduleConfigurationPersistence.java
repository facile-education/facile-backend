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

import com.weprode.nero.schedule.exception.NoSuchConfigurationException;
import com.weprode.nero.schedule.model.ScheduleConfiguration;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the schedule configuration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduleConfigurationUtil
 * @generated
 */
@ProviderType
public interface ScheduleConfigurationPersistence
	extends BasePersistence<ScheduleConfiguration> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ScheduleConfigurationUtil} to access the schedule configuration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the schedule configuration in the entity cache if it is enabled.
	 *
	 * @param scheduleConfiguration the schedule configuration
	 */
	public void cacheResult(ScheduleConfiguration scheduleConfiguration);

	/**
	 * Caches the schedule configurations in the entity cache if it is enabled.
	 *
	 * @param scheduleConfigurations the schedule configurations
	 */
	public void cacheResult(
		java.util.List<ScheduleConfiguration> scheduleConfigurations);

	/**
	 * Creates a new schedule configuration with the primary key. Does not add the schedule configuration to the database.
	 *
	 * @param schoolId the primary key for the new schedule configuration
	 * @return the new schedule configuration
	 */
	public ScheduleConfiguration create(long schoolId);

	/**
	 * Removes the schedule configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoolId the primary key of the schedule configuration
	 * @return the schedule configuration that was removed
	 * @throws NoSuchConfigurationException if a schedule configuration with the primary key could not be found
	 */
	public ScheduleConfiguration remove(long schoolId)
		throws NoSuchConfigurationException;

	public ScheduleConfiguration updateImpl(
		ScheduleConfiguration scheduleConfiguration);

	/**
	 * Returns the schedule configuration with the primary key or throws a <code>NoSuchConfigurationException</code> if it could not be found.
	 *
	 * @param schoolId the primary key of the schedule configuration
	 * @return the schedule configuration
	 * @throws NoSuchConfigurationException if a schedule configuration with the primary key could not be found
	 */
	public ScheduleConfiguration findByPrimaryKey(long schoolId)
		throws NoSuchConfigurationException;

	/**
	 * Returns the schedule configuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoolId the primary key of the schedule configuration
	 * @return the schedule configuration, or <code>null</code> if a schedule configuration with the primary key could not be found
	 */
	public ScheduleConfiguration fetchByPrimaryKey(long schoolId);

	/**
	 * Returns all the schedule configurations.
	 *
	 * @return the schedule configurations
	 */
	public java.util.List<ScheduleConfiguration> findAll();

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
	public java.util.List<ScheduleConfiguration> findAll(int start, int end);

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
	public java.util.List<ScheduleConfiguration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleConfiguration>
			orderByComparator);

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
	public java.util.List<ScheduleConfiguration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleConfiguration>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the schedule configurations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of schedule configurations.
	 *
	 * @return the number of schedule configurations
	 */
	public int countAll();

}