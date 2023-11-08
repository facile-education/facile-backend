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

package com.weprode.facile.schedule.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.schedule.exception.NoSuchSlotConfigurationException;
import com.weprode.facile.schedule.model.SlotConfiguration;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the slot configuration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SlotConfigurationUtil
 * @generated
 */
@ProviderType
public interface SlotConfigurationPersistence
	extends BasePersistence<SlotConfiguration> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SlotConfigurationUtil} to access the slot configuration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the slot configurations where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching slot configurations
	 */
	public java.util.List<SlotConfiguration> findByschoolId(long schoolId);

	/**
	 * Returns a range of all the slot configurations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @return the range of matching slot configurations
	 */
	public java.util.List<SlotConfiguration> findByschoolId(
		long schoolId, int start, int end);

	/**
	 * Returns an ordered range of all the slot configurations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching slot configurations
	 */
	public java.util.List<SlotConfiguration> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SlotConfiguration>
			orderByComparator);

	/**
	 * Returns an ordered range of all the slot configurations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching slot configurations
	 */
	public java.util.List<SlotConfiguration> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SlotConfiguration>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching slot configuration
	 * @throws NoSuchSlotConfigurationException if a matching slot configuration could not be found
	 */
	public SlotConfiguration findByschoolId_First(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<SlotConfiguration>
				orderByComparator)
		throws NoSuchSlotConfigurationException;

	/**
	 * Returns the first slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching slot configuration, or <code>null</code> if a matching slot configuration could not be found
	 */
	public SlotConfiguration fetchByschoolId_First(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<SlotConfiguration>
			orderByComparator);

	/**
	 * Returns the last slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching slot configuration
	 * @throws NoSuchSlotConfigurationException if a matching slot configuration could not be found
	 */
	public SlotConfiguration findByschoolId_Last(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<SlotConfiguration>
				orderByComparator)
		throws NoSuchSlotConfigurationException;

	/**
	 * Returns the last slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching slot configuration, or <code>null</code> if a matching slot configuration could not be found
	 */
	public SlotConfiguration fetchByschoolId_Last(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<SlotConfiguration>
			orderByComparator);

	/**
	 * Returns the slot configurations before and after the current slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param slotConfigurationPK the primary key of the current slot configuration
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next slot configuration
	 * @throws NoSuchSlotConfigurationException if a slot configuration with the primary key could not be found
	 */
	public SlotConfiguration[] findByschoolId_PrevAndNext(
			SlotConfigurationPK slotConfigurationPK, long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<SlotConfiguration>
				orderByComparator)
		throws NoSuchSlotConfigurationException;

	/**
	 * Removes all the slot configurations where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public void removeByschoolId(long schoolId);

	/**
	 * Returns the number of slot configurations where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching slot configurations
	 */
	public int countByschoolId(long schoolId);

	/**
	 * Caches the slot configuration in the entity cache if it is enabled.
	 *
	 * @param slotConfiguration the slot configuration
	 */
	public void cacheResult(SlotConfiguration slotConfiguration);

	/**
	 * Caches the slot configurations in the entity cache if it is enabled.
	 *
	 * @param slotConfigurations the slot configurations
	 */
	public void cacheResult(
		java.util.List<SlotConfiguration> slotConfigurations);

	/**
	 * Creates a new slot configuration with the primary key. Does not add the slot configuration to the database.
	 *
	 * @param slotConfigurationPK the primary key for the new slot configuration
	 * @return the new slot configuration
	 */
	public SlotConfiguration create(SlotConfigurationPK slotConfigurationPK);

	/**
	 * Removes the slot configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration that was removed
	 * @throws NoSuchSlotConfigurationException if a slot configuration with the primary key could not be found
	 */
	public SlotConfiguration remove(SlotConfigurationPK slotConfigurationPK)
		throws NoSuchSlotConfigurationException;

	public SlotConfiguration updateImpl(SlotConfiguration slotConfiguration);

	/**
	 * Returns the slot configuration with the primary key or throws a <code>NoSuchSlotConfigurationException</code> if it could not be found.
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration
	 * @throws NoSuchSlotConfigurationException if a slot configuration with the primary key could not be found
	 */
	public SlotConfiguration findByPrimaryKey(
			SlotConfigurationPK slotConfigurationPK)
		throws NoSuchSlotConfigurationException;

	/**
	 * Returns the slot configuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration, or <code>null</code> if a slot configuration with the primary key could not be found
	 */
	public SlotConfiguration fetchByPrimaryKey(
		SlotConfigurationPK slotConfigurationPK);

	/**
	 * Returns all the slot configurations.
	 *
	 * @return the slot configurations
	 */
	public java.util.List<SlotConfiguration> findAll();

	/**
	 * Returns a range of all the slot configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @return the range of slot configurations
	 */
	public java.util.List<SlotConfiguration> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the slot configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of slot configurations
	 */
	public java.util.List<SlotConfiguration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SlotConfiguration>
			orderByComparator);

	/**
	 * Returns an ordered range of all the slot configurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SlotConfigurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of slot configurations
	 * @param end the upper bound of the range of slot configurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of slot configurations
	 */
	public java.util.List<SlotConfiguration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SlotConfiguration>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the slot configurations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of slot configurations.
	 *
	 * @return the number of slot configurations
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}