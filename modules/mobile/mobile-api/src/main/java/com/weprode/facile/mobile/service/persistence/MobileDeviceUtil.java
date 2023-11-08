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

package com.weprode.facile.mobile.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.mobile.model.MobileDevice;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the mobile device service. This utility wraps <code>com.weprode.facile.mobile.service.persistence.impl.MobileDevicePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MobileDevicePersistence
 * @generated
 */
public class MobileDeviceUtil {

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
	public static void clearCache(MobileDevice mobileDevice) {
		getPersistence().clearCache(mobileDevice);
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
	public static Map<Serializable, MobileDevice> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MobileDevice> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MobileDevice> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MobileDevice> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MobileDevice> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MobileDevice update(MobileDevice mobileDevice) {
		return getPersistence().update(mobileDevice);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MobileDevice update(
		MobileDevice mobileDevice, ServiceContext serviceContext) {

		return getPersistence().update(mobileDevice, serviceContext);
	}

	/**
	 * Returns all the mobile devices where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching mobile devices
	 */
	public static List<MobileDevice> findByuserId(long userId) {
		return getPersistence().findByuserId(userId);
	}

	/**
	 * Returns a range of all the mobile devices where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @return the range of matching mobile devices
	 */
	public static List<MobileDevice> findByuserId(
		long userId, int start, int end) {

		return getPersistence().findByuserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the mobile devices where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mobile devices
	 */
	public static List<MobileDevice> findByuserId(
		long userId, int start, int end,
		OrderByComparator<MobileDevice> orderByComparator) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mobile devices where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching mobile devices
	 */
	public static List<MobileDevice> findByuserId(
		long userId, int start, int end,
		OrderByComparator<MobileDevice> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first mobile device in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile device
	 * @throws NoSuchDeviceException if a matching mobile device could not be found
	 */
	public static MobileDevice findByuserId_First(
			long userId, OrderByComparator<MobileDevice> orderByComparator)
		throws com.weprode.facile.mobile.exception.NoSuchDeviceException {

		return getPersistence().findByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first mobile device in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	public static MobileDevice fetchByuserId_First(
		long userId, OrderByComparator<MobileDevice> orderByComparator) {

		return getPersistence().fetchByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last mobile device in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile device
	 * @throws NoSuchDeviceException if a matching mobile device could not be found
	 */
	public static MobileDevice findByuserId_Last(
			long userId, OrderByComparator<MobileDevice> orderByComparator)
		throws com.weprode.facile.mobile.exception.NoSuchDeviceException {

		return getPersistence().findByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last mobile device in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	public static MobileDevice fetchByuserId_Last(
		long userId, OrderByComparator<MobileDevice> orderByComparator) {

		return getPersistence().fetchByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the mobile devices before and after the current mobile device in the ordered set where userId = &#63;.
	 *
	 * @param mobileDeviceId the primary key of the current mobile device
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mobile device
	 * @throws NoSuchDeviceException if a mobile device with the primary key could not be found
	 */
	public static MobileDevice[] findByuserId_PrevAndNext(
			long mobileDeviceId, long userId,
			OrderByComparator<MobileDevice> orderByComparator)
		throws com.weprode.facile.mobile.exception.NoSuchDeviceException {

		return getPersistence().findByuserId_PrevAndNext(
			mobileDeviceId, userId, orderByComparator);
	}

	/**
	 * Removes all the mobile devices where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByuserId(long userId) {
		getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of mobile devices where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching mobile devices
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Returns the mobile device where manufacturerDeviceId = &#63; or throws a <code>NoSuchDeviceException</code> if it could not be found.
	 *
	 * @param manufacturerDeviceId the manufacturer device ID
	 * @return the matching mobile device
	 * @throws NoSuchDeviceException if a matching mobile device could not be found
	 */
	public static MobileDevice findBymanufacturerDeviceId(
			String manufacturerDeviceId)
		throws com.weprode.facile.mobile.exception.NoSuchDeviceException {

		return getPersistence().findBymanufacturerDeviceId(
			manufacturerDeviceId);
	}

	/**
	 * Returns the mobile device where manufacturerDeviceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param manufacturerDeviceId the manufacturer device ID
	 * @return the matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	public static MobileDevice fetchBymanufacturerDeviceId(
		String manufacturerDeviceId) {

		return getPersistence().fetchBymanufacturerDeviceId(
			manufacturerDeviceId);
	}

	/**
	 * Returns the mobile device where manufacturerDeviceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param manufacturerDeviceId the manufacturer device ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	public static MobileDevice fetchBymanufacturerDeviceId(
		String manufacturerDeviceId, boolean useFinderCache) {

		return getPersistence().fetchBymanufacturerDeviceId(
			manufacturerDeviceId, useFinderCache);
	}

	/**
	 * Removes the mobile device where manufacturerDeviceId = &#63; from the database.
	 *
	 * @param manufacturerDeviceId the manufacturer device ID
	 * @return the mobile device that was removed
	 */
	public static MobileDevice removeBymanufacturerDeviceId(
			String manufacturerDeviceId)
		throws com.weprode.facile.mobile.exception.NoSuchDeviceException {

		return getPersistence().removeBymanufacturerDeviceId(
			manufacturerDeviceId);
	}

	/**
	 * Returns the number of mobile devices where manufacturerDeviceId = &#63;.
	 *
	 * @param manufacturerDeviceId the manufacturer device ID
	 * @return the number of matching mobile devices
	 */
	public static int countBymanufacturerDeviceId(String manufacturerDeviceId) {
		return getPersistence().countBymanufacturerDeviceId(
			manufacturerDeviceId);
	}

	/**
	 * Caches the mobile device in the entity cache if it is enabled.
	 *
	 * @param mobileDevice the mobile device
	 */
	public static void cacheResult(MobileDevice mobileDevice) {
		getPersistence().cacheResult(mobileDevice);
	}

	/**
	 * Caches the mobile devices in the entity cache if it is enabled.
	 *
	 * @param mobileDevices the mobile devices
	 */
	public static void cacheResult(List<MobileDevice> mobileDevices) {
		getPersistence().cacheResult(mobileDevices);
	}

	/**
	 * Creates a new mobile device with the primary key. Does not add the mobile device to the database.
	 *
	 * @param mobileDeviceId the primary key for the new mobile device
	 * @return the new mobile device
	 */
	public static MobileDevice create(long mobileDeviceId) {
		return getPersistence().create(mobileDeviceId);
	}

	/**
	 * Removes the mobile device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mobileDeviceId the primary key of the mobile device
	 * @return the mobile device that was removed
	 * @throws NoSuchDeviceException if a mobile device with the primary key could not be found
	 */
	public static MobileDevice remove(long mobileDeviceId)
		throws com.weprode.facile.mobile.exception.NoSuchDeviceException {

		return getPersistence().remove(mobileDeviceId);
	}

	public static MobileDevice updateImpl(MobileDevice mobileDevice) {
		return getPersistence().updateImpl(mobileDevice);
	}

	/**
	 * Returns the mobile device with the primary key or throws a <code>NoSuchDeviceException</code> if it could not be found.
	 *
	 * @param mobileDeviceId the primary key of the mobile device
	 * @return the mobile device
	 * @throws NoSuchDeviceException if a mobile device with the primary key could not be found
	 */
	public static MobileDevice findByPrimaryKey(long mobileDeviceId)
		throws com.weprode.facile.mobile.exception.NoSuchDeviceException {

		return getPersistence().findByPrimaryKey(mobileDeviceId);
	}

	/**
	 * Returns the mobile device with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mobileDeviceId the primary key of the mobile device
	 * @return the mobile device, or <code>null</code> if a mobile device with the primary key could not be found
	 */
	public static MobileDevice fetchByPrimaryKey(long mobileDeviceId) {
		return getPersistence().fetchByPrimaryKey(mobileDeviceId);
	}

	/**
	 * Returns all the mobile devices.
	 *
	 * @return the mobile devices
	 */
	public static List<MobileDevice> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the mobile devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @return the range of mobile devices
	 */
	public static List<MobileDevice> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the mobile devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of mobile devices
	 */
	public static List<MobileDevice> findAll(
		int start, int end, OrderByComparator<MobileDevice> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mobile devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileDeviceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile devices
	 * @param end the upper bound of the range of mobile devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of mobile devices
	 */
	public static List<MobileDevice> findAll(
		int start, int end, OrderByComparator<MobileDevice> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the mobile devices from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of mobile devices.
	 *
	 * @return the number of mobile devices
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MobileDevicePersistence getPersistence() {
		return _persistence;
	}

	private static volatile MobileDevicePersistence _persistence;

}