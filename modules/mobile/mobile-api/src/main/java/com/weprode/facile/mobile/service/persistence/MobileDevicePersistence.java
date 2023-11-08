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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.mobile.exception.NoSuchDeviceException;
import com.weprode.facile.mobile.model.MobileDevice;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the mobile device service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MobileDeviceUtil
 * @generated
 */
@ProviderType
public interface MobileDevicePersistence extends BasePersistence<MobileDevice> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MobileDeviceUtil} to access the mobile device persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the mobile devices where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching mobile devices
	 */
	public java.util.List<MobileDevice> findByuserId(long userId);

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
	public java.util.List<MobileDevice> findByuserId(
		long userId, int start, int end);

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
	public java.util.List<MobileDevice> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MobileDevice>
			orderByComparator);

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
	public java.util.List<MobileDevice> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MobileDevice>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first mobile device in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile device
	 * @throws NoSuchDeviceException if a matching mobile device could not be found
	 */
	public MobileDevice findByuserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MobileDevice>
				orderByComparator)
		throws NoSuchDeviceException;

	/**
	 * Returns the first mobile device in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	public MobileDevice fetchByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MobileDevice>
			orderByComparator);

	/**
	 * Returns the last mobile device in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile device
	 * @throws NoSuchDeviceException if a matching mobile device could not be found
	 */
	public MobileDevice findByuserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MobileDevice>
				orderByComparator)
		throws NoSuchDeviceException;

	/**
	 * Returns the last mobile device in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	public MobileDevice fetchByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<MobileDevice>
			orderByComparator);

	/**
	 * Returns the mobile devices before and after the current mobile device in the ordered set where userId = &#63;.
	 *
	 * @param mobileDeviceId the primary key of the current mobile device
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mobile device
	 * @throws NoSuchDeviceException if a mobile device with the primary key could not be found
	 */
	public MobileDevice[] findByuserId_PrevAndNext(
			long mobileDeviceId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<MobileDevice>
				orderByComparator)
		throws NoSuchDeviceException;

	/**
	 * Removes all the mobile devices where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId);

	/**
	 * Returns the number of mobile devices where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching mobile devices
	 */
	public int countByuserId(long userId);

	/**
	 * Returns the mobile device where manufacturerDeviceId = &#63; or throws a <code>NoSuchDeviceException</code> if it could not be found.
	 *
	 * @param manufacturerDeviceId the manufacturer device ID
	 * @return the matching mobile device
	 * @throws NoSuchDeviceException if a matching mobile device could not be found
	 */
	public MobileDevice findBymanufacturerDeviceId(String manufacturerDeviceId)
		throws NoSuchDeviceException;

	/**
	 * Returns the mobile device where manufacturerDeviceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param manufacturerDeviceId the manufacturer device ID
	 * @return the matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	public MobileDevice fetchBymanufacturerDeviceId(
		String manufacturerDeviceId);

	/**
	 * Returns the mobile device where manufacturerDeviceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param manufacturerDeviceId the manufacturer device ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching mobile device, or <code>null</code> if a matching mobile device could not be found
	 */
	public MobileDevice fetchBymanufacturerDeviceId(
		String manufacturerDeviceId, boolean useFinderCache);

	/**
	 * Removes the mobile device where manufacturerDeviceId = &#63; from the database.
	 *
	 * @param manufacturerDeviceId the manufacturer device ID
	 * @return the mobile device that was removed
	 */
	public MobileDevice removeBymanufacturerDeviceId(
			String manufacturerDeviceId)
		throws NoSuchDeviceException;

	/**
	 * Returns the number of mobile devices where manufacturerDeviceId = &#63;.
	 *
	 * @param manufacturerDeviceId the manufacturer device ID
	 * @return the number of matching mobile devices
	 */
	public int countBymanufacturerDeviceId(String manufacturerDeviceId);

	/**
	 * Caches the mobile device in the entity cache if it is enabled.
	 *
	 * @param mobileDevice the mobile device
	 */
	public void cacheResult(MobileDevice mobileDevice);

	/**
	 * Caches the mobile devices in the entity cache if it is enabled.
	 *
	 * @param mobileDevices the mobile devices
	 */
	public void cacheResult(java.util.List<MobileDevice> mobileDevices);

	/**
	 * Creates a new mobile device with the primary key. Does not add the mobile device to the database.
	 *
	 * @param mobileDeviceId the primary key for the new mobile device
	 * @return the new mobile device
	 */
	public MobileDevice create(long mobileDeviceId);

	/**
	 * Removes the mobile device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mobileDeviceId the primary key of the mobile device
	 * @return the mobile device that was removed
	 * @throws NoSuchDeviceException if a mobile device with the primary key could not be found
	 */
	public MobileDevice remove(long mobileDeviceId)
		throws NoSuchDeviceException;

	public MobileDevice updateImpl(MobileDevice mobileDevice);

	/**
	 * Returns the mobile device with the primary key or throws a <code>NoSuchDeviceException</code> if it could not be found.
	 *
	 * @param mobileDeviceId the primary key of the mobile device
	 * @return the mobile device
	 * @throws NoSuchDeviceException if a mobile device with the primary key could not be found
	 */
	public MobileDevice findByPrimaryKey(long mobileDeviceId)
		throws NoSuchDeviceException;

	/**
	 * Returns the mobile device with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mobileDeviceId the primary key of the mobile device
	 * @return the mobile device, or <code>null</code> if a mobile device with the primary key could not be found
	 */
	public MobileDevice fetchByPrimaryKey(long mobileDeviceId);

	/**
	 * Returns all the mobile devices.
	 *
	 * @return the mobile devices
	 */
	public java.util.List<MobileDevice> findAll();

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
	public java.util.List<MobileDevice> findAll(int start, int end);

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
	public java.util.List<MobileDevice> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MobileDevice>
			orderByComparator);

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
	public java.util.List<MobileDevice> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MobileDevice>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the mobile devices from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of mobile devices.
	 *
	 * @return the number of mobile devices
	 */
	public int countAll();

}