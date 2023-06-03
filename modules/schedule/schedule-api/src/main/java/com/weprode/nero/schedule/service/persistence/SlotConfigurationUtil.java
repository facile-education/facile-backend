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

import com.weprode.nero.schedule.model.SlotConfiguration;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the slot configuration service. This utility wraps <code>com.weprode.nero.schedule.service.persistence.impl.SlotConfigurationPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SlotConfigurationPersistence
 * @generated
 */
public class SlotConfigurationUtil {

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
	public static void clearCache(SlotConfiguration slotConfiguration) {
		getPersistence().clearCache(slotConfiguration);
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
	public static Map<Serializable, SlotConfiguration> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SlotConfiguration> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SlotConfiguration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SlotConfiguration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SlotConfiguration> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SlotConfiguration update(
		SlotConfiguration slotConfiguration) {

		return getPersistence().update(slotConfiguration);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SlotConfiguration update(
		SlotConfiguration slotConfiguration, ServiceContext serviceContext) {

		return getPersistence().update(slotConfiguration, serviceContext);
	}

	/**
	 * Returns all the slot configurations where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching slot configurations
	 */
	public static List<SlotConfiguration> findByschoolId(long schoolId) {
		return getPersistence().findByschoolId(schoolId);
	}

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
	public static List<SlotConfiguration> findByschoolId(
		long schoolId, int start, int end) {

		return getPersistence().findByschoolId(schoolId, start, end);
	}

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
	public static List<SlotConfiguration> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<SlotConfiguration> orderByComparator) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator);
	}

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
	public static List<SlotConfiguration> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<SlotConfiguration> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching slot configuration
	 * @throws NoSuchSlotConfigurationException if a matching slot configuration could not be found
	 */
	public static SlotConfiguration findByschoolId_First(
			long schoolId,
			OrderByComparator<SlotConfiguration> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSlotConfigurationException {

		return getPersistence().findByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the first slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching slot configuration, or <code>null</code> if a matching slot configuration could not be found
	 */
	public static SlotConfiguration fetchByschoolId_First(
		long schoolId, OrderByComparator<SlotConfiguration> orderByComparator) {

		return getPersistence().fetchByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching slot configuration
	 * @throws NoSuchSlotConfigurationException if a matching slot configuration could not be found
	 */
	public static SlotConfiguration findByschoolId_Last(
			long schoolId,
			OrderByComparator<SlotConfiguration> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSlotConfigurationException {

		return getPersistence().findByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching slot configuration, or <code>null</code> if a matching slot configuration could not be found
	 */
	public static SlotConfiguration fetchByschoolId_Last(
		long schoolId, OrderByComparator<SlotConfiguration> orderByComparator) {

		return getPersistence().fetchByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the slot configurations before and after the current slot configuration in the ordered set where schoolId = &#63;.
	 *
	 * @param slotConfigurationPK the primary key of the current slot configuration
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next slot configuration
	 * @throws NoSuchSlotConfigurationException if a slot configuration with the primary key could not be found
	 */
	public static SlotConfiguration[] findByschoolId_PrevAndNext(
			SlotConfigurationPK slotConfigurationPK, long schoolId,
			OrderByComparator<SlotConfiguration> orderByComparator)
		throws com.weprode.nero.schedule.exception.
			NoSuchSlotConfigurationException {

		return getPersistence().findByschoolId_PrevAndNext(
			slotConfigurationPK, schoolId, orderByComparator);
	}

	/**
	 * Removes all the slot configurations where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public static void removeByschoolId(long schoolId) {
		getPersistence().removeByschoolId(schoolId);
	}

	/**
	 * Returns the number of slot configurations where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching slot configurations
	 */
	public static int countByschoolId(long schoolId) {
		return getPersistence().countByschoolId(schoolId);
	}

	/**
	 * Caches the slot configuration in the entity cache if it is enabled.
	 *
	 * @param slotConfiguration the slot configuration
	 */
	public static void cacheResult(SlotConfiguration slotConfiguration) {
		getPersistence().cacheResult(slotConfiguration);
	}

	/**
	 * Caches the slot configurations in the entity cache if it is enabled.
	 *
	 * @param slotConfigurations the slot configurations
	 */
	public static void cacheResult(List<SlotConfiguration> slotConfigurations) {
		getPersistence().cacheResult(slotConfigurations);
	}

	/**
	 * Creates a new slot configuration with the primary key. Does not add the slot configuration to the database.
	 *
	 * @param slotConfigurationPK the primary key for the new slot configuration
	 * @return the new slot configuration
	 */
	public static SlotConfiguration create(
		SlotConfigurationPK slotConfigurationPK) {

		return getPersistence().create(slotConfigurationPK);
	}

	/**
	 * Removes the slot configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration that was removed
	 * @throws NoSuchSlotConfigurationException if a slot configuration with the primary key could not be found
	 */
	public static SlotConfiguration remove(
			SlotConfigurationPK slotConfigurationPK)
		throws com.weprode.nero.schedule.exception.
			NoSuchSlotConfigurationException {

		return getPersistence().remove(slotConfigurationPK);
	}

	public static SlotConfiguration updateImpl(
		SlotConfiguration slotConfiguration) {

		return getPersistence().updateImpl(slotConfiguration);
	}

	/**
	 * Returns the slot configuration with the primary key or throws a <code>NoSuchSlotConfigurationException</code> if it could not be found.
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration
	 * @throws NoSuchSlotConfigurationException if a slot configuration with the primary key could not be found
	 */
	public static SlotConfiguration findByPrimaryKey(
			SlotConfigurationPK slotConfigurationPK)
		throws com.weprode.nero.schedule.exception.
			NoSuchSlotConfigurationException {

		return getPersistence().findByPrimaryKey(slotConfigurationPK);
	}

	/**
	 * Returns the slot configuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param slotConfigurationPK the primary key of the slot configuration
	 * @return the slot configuration, or <code>null</code> if a slot configuration with the primary key could not be found
	 */
	public static SlotConfiguration fetchByPrimaryKey(
		SlotConfigurationPK slotConfigurationPK) {

		return getPersistence().fetchByPrimaryKey(slotConfigurationPK);
	}

	/**
	 * Returns all the slot configurations.
	 *
	 * @return the slot configurations
	 */
	public static List<SlotConfiguration> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<SlotConfiguration> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<SlotConfiguration> findAll(
		int start, int end,
		OrderByComparator<SlotConfiguration> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<SlotConfiguration> findAll(
		int start, int end,
		OrderByComparator<SlotConfiguration> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the slot configurations from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of slot configurations.
	 *
	 * @return the number of slot configurations
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static SlotConfigurationPersistence getPersistence() {
		return _persistence;
	}

	private static volatile SlotConfigurationPersistence _persistence;

}