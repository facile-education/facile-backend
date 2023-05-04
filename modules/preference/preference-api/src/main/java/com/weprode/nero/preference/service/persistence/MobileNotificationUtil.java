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

package com.weprode.nero.preference.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.preference.model.MobileNotification;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the mobile notification service. This utility wraps <code>com.weprode.nero.preference.service.persistence.impl.MobileNotificationPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MobileNotificationPersistence
 * @generated
 */
public class MobileNotificationUtil {

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
	public static void clearCache(MobileNotification mobileNotification) {
		getPersistence().clearCache(mobileNotification);
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
	public static Map<Serializable, MobileNotification> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MobileNotification> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MobileNotification> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MobileNotification> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MobileNotification update(
		MobileNotification mobileNotification) {

		return getPersistence().update(mobileNotification);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MobileNotification update(
		MobileNotification mobileNotification, ServiceContext serviceContext) {

		return getPersistence().update(mobileNotification, serviceContext);
	}

	/**
	 * Returns all the mobile notifications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching mobile notifications
	 */
	public static List<MobileNotification> findByuserId(long userId) {
		return getPersistence().findByuserId(userId);
	}

	/**
	 * Returns a range of all the mobile notifications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @return the range of matching mobile notifications
	 */
	public static List<MobileNotification> findByuserId(
		long userId, int start, int end) {

		return getPersistence().findByuserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mobile notifications
	 */
	public static List<MobileNotification> findByuserId(
		long userId, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching mobile notifications
	 */
	public static List<MobileNotification> findByuserId(
		long userId, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first mobile notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	public static MobileNotification findByuserId_First(
			long userId,
			OrderByComparator<MobileNotification> orderByComparator)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first mobile notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	public static MobileNotification fetchByuserId_First(
		long userId, OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().fetchByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last mobile notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	public static MobileNotification findByuserId_Last(
			long userId,
			OrderByComparator<MobileNotification> orderByComparator)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last mobile notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	public static MobileNotification fetchByuserId_Last(
		long userId, OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().fetchByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the mobile notifications before and after the current mobile notification in the ordered set where userId = &#63;.
	 *
	 * @param mobileNotificationId the primary key of the current mobile notification
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mobile notification
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	public static MobileNotification[] findByuserId_PrevAndNext(
			long mobileNotificationId, long userId,
			OrderByComparator<MobileNotification> orderByComparator)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByuserId_PrevAndNext(
			mobileNotificationId, userId, orderByComparator);
	}

	/**
	 * Removes all the mobile notifications where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByuserId(long userId) {
		getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of mobile notifications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching mobile notifications
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Returns all the mobile notifications where etabId = &#63;.
	 *
	 * @param etabId the etab ID
	 * @return the matching mobile notifications
	 */
	public static List<MobileNotification> findByetabId(long etabId) {
		return getPersistence().findByetabId(etabId);
	}

	/**
	 * Returns a range of all the mobile notifications where etabId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @return the range of matching mobile notifications
	 */
	public static List<MobileNotification> findByetabId(
		long etabId, int start, int end) {

		return getPersistence().findByetabId(etabId, start, end);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where etabId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mobile notifications
	 */
	public static List<MobileNotification> findByetabId(
		long etabId, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().findByetabId(
			etabId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where etabId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching mobile notifications
	 */
	public static List<MobileNotification> findByetabId(
		long etabId, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByetabId(
			etabId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first mobile notification in the ordered set where etabId = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	public static MobileNotification findByetabId_First(
			long etabId,
			OrderByComparator<MobileNotification> orderByComparator)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByetabId_First(etabId, orderByComparator);
	}

	/**
	 * Returns the first mobile notification in the ordered set where etabId = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	public static MobileNotification fetchByetabId_First(
		long etabId, OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().fetchByetabId_First(etabId, orderByComparator);
	}

	/**
	 * Returns the last mobile notification in the ordered set where etabId = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	public static MobileNotification findByetabId_Last(
			long etabId,
			OrderByComparator<MobileNotification> orderByComparator)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByetabId_Last(etabId, orderByComparator);
	}

	/**
	 * Returns the last mobile notification in the ordered set where etabId = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	public static MobileNotification fetchByetabId_Last(
		long etabId, OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().fetchByetabId_Last(etabId, orderByComparator);
	}

	/**
	 * Returns the mobile notifications before and after the current mobile notification in the ordered set where etabId = &#63;.
	 *
	 * @param mobileNotificationId the primary key of the current mobile notification
	 * @param etabId the etab ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mobile notification
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	public static MobileNotification[] findByetabId_PrevAndNext(
			long mobileNotificationId, long etabId,
			OrderByComparator<MobileNotification> orderByComparator)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByetabId_PrevAndNext(
			mobileNotificationId, etabId, orderByComparator);
	}

	/**
	 * Removes all the mobile notifications where etabId = &#63; from the database.
	 *
	 * @param etabId the etab ID
	 */
	public static void removeByetabId(long etabId) {
		getPersistence().removeByetabId(etabId);
	}

	/**
	 * Returns the number of mobile notifications where etabId = &#63;.
	 *
	 * @param etabId the etab ID
	 * @return the number of matching mobile notifications
	 */
	public static int countByetabId(long etabId) {
		return getPersistence().countByetabId(etabId);
	}

	/**
	 * Returns all the mobile notifications where userId = &#63; and enable = &#63;.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @return the matching mobile notifications
	 */
	public static List<MobileNotification> findByuserId_enable(
		long userId, boolean enable) {

		return getPersistence().findByuserId_enable(userId, enable);
	}

	/**
	 * Returns a range of all the mobile notifications where userId = &#63; and enable = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @return the range of matching mobile notifications
	 */
	public static List<MobileNotification> findByuserId_enable(
		long userId, boolean enable, int start, int end) {

		return getPersistence().findByuserId_enable(userId, enable, start, end);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where userId = &#63; and enable = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mobile notifications
	 */
	public static List<MobileNotification> findByuserId_enable(
		long userId, boolean enable, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().findByuserId_enable(
			userId, enable, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where userId = &#63; and enable = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching mobile notifications
	 */
	public static List<MobileNotification> findByuserId_enable(
		long userId, boolean enable, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId_enable(
			userId, enable, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first mobile notification in the ordered set where userId = &#63; and enable = &#63;.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	public static MobileNotification findByuserId_enable_First(
			long userId, boolean enable,
			OrderByComparator<MobileNotification> orderByComparator)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByuserId_enable_First(
			userId, enable, orderByComparator);
	}

	/**
	 * Returns the first mobile notification in the ordered set where userId = &#63; and enable = &#63;.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	public static MobileNotification fetchByuserId_enable_First(
		long userId, boolean enable,
		OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().fetchByuserId_enable_First(
			userId, enable, orderByComparator);
	}

	/**
	 * Returns the last mobile notification in the ordered set where userId = &#63; and enable = &#63;.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	public static MobileNotification findByuserId_enable_Last(
			long userId, boolean enable,
			OrderByComparator<MobileNotification> orderByComparator)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByuserId_enable_Last(
			userId, enable, orderByComparator);
	}

	/**
	 * Returns the last mobile notification in the ordered set where userId = &#63; and enable = &#63;.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	public static MobileNotification fetchByuserId_enable_Last(
		long userId, boolean enable,
		OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().fetchByuserId_enable_Last(
			userId, enable, orderByComparator);
	}

	/**
	 * Returns the mobile notifications before and after the current mobile notification in the ordered set where userId = &#63; and enable = &#63;.
	 *
	 * @param mobileNotificationId the primary key of the current mobile notification
	 * @param userId the user ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mobile notification
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	public static MobileNotification[] findByuserId_enable_PrevAndNext(
			long mobileNotificationId, long userId, boolean enable,
			OrderByComparator<MobileNotification> orderByComparator)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByuserId_enable_PrevAndNext(
			mobileNotificationId, userId, enable, orderByComparator);
	}

	/**
	 * Removes all the mobile notifications where userId = &#63; and enable = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 */
	public static void removeByuserId_enable(long userId, boolean enable) {
		getPersistence().removeByuserId_enable(userId, enable);
	}

	/**
	 * Returns the number of mobile notifications where userId = &#63; and enable = &#63;.
	 *
	 * @param userId the user ID
	 * @param enable the enable
	 * @return the number of matching mobile notifications
	 */
	public static int countByuserId_enable(long userId, boolean enable) {
		return getPersistence().countByuserId_enable(userId, enable);
	}

	/**
	 * Returns all the mobile notifications where etabId = &#63; and enable = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @return the matching mobile notifications
	 */
	public static List<MobileNotification> findByetabId_enable(
		long etabId, boolean enable) {

		return getPersistence().findByetabId_enable(etabId, enable);
	}

	/**
	 * Returns a range of all the mobile notifications where etabId = &#63; and enable = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @return the range of matching mobile notifications
	 */
	public static List<MobileNotification> findByetabId_enable(
		long etabId, boolean enable, int start, int end) {

		return getPersistence().findByetabId_enable(etabId, enable, start, end);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where etabId = &#63; and enable = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching mobile notifications
	 */
	public static List<MobileNotification> findByetabId_enable(
		long etabId, boolean enable, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().findByetabId_enable(
			etabId, enable, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mobile notifications where etabId = &#63; and enable = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching mobile notifications
	 */
	public static List<MobileNotification> findByetabId_enable(
		long etabId, boolean enable, int start, int end,
		OrderByComparator<MobileNotification> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByetabId_enable(
			etabId, enable, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first mobile notification in the ordered set where etabId = &#63; and enable = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	public static MobileNotification findByetabId_enable_First(
			long etabId, boolean enable,
			OrderByComparator<MobileNotification> orderByComparator)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByetabId_enable_First(
			etabId, enable, orderByComparator);
	}

	/**
	 * Returns the first mobile notification in the ordered set where etabId = &#63; and enable = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	public static MobileNotification fetchByetabId_enable_First(
		long etabId, boolean enable,
		OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().fetchByetabId_enable_First(
			etabId, enable, orderByComparator);
	}

	/**
	 * Returns the last mobile notification in the ordered set where etabId = &#63; and enable = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification
	 * @throws NoSuchMobileNotificationException if a matching mobile notification could not be found
	 */
	public static MobileNotification findByetabId_enable_Last(
			long etabId, boolean enable,
			OrderByComparator<MobileNotification> orderByComparator)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByetabId_enable_Last(
			etabId, enable, orderByComparator);
	}

	/**
	 * Returns the last mobile notification in the ordered set where etabId = &#63; and enable = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mobile notification, or <code>null</code> if a matching mobile notification could not be found
	 */
	public static MobileNotification fetchByetabId_enable_Last(
		long etabId, boolean enable,
		OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().fetchByetabId_enable_Last(
			etabId, enable, orderByComparator);
	}

	/**
	 * Returns the mobile notifications before and after the current mobile notification in the ordered set where etabId = &#63; and enable = &#63;.
	 *
	 * @param mobileNotificationId the primary key of the current mobile notification
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next mobile notification
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	public static MobileNotification[] findByetabId_enable_PrevAndNext(
			long mobileNotificationId, long etabId, boolean enable,
			OrderByComparator<MobileNotification> orderByComparator)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByetabId_enable_PrevAndNext(
			mobileNotificationId, etabId, enable, orderByComparator);
	}

	/**
	 * Removes all the mobile notifications where etabId = &#63; and enable = &#63; from the database.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 */
	public static void removeByetabId_enable(long etabId, boolean enable) {
		getPersistence().removeByetabId_enable(etabId, enable);
	}

	/**
	 * Returns the number of mobile notifications where etabId = &#63; and enable = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param enable the enable
	 * @return the number of matching mobile notifications
	 */
	public static int countByetabId_enable(long etabId, boolean enable) {
		return getPersistence().countByetabId_enable(etabId, enable);
	}

	/**
	 * Caches the mobile notification in the entity cache if it is enabled.
	 *
	 * @param mobileNotification the mobile notification
	 */
	public static void cacheResult(MobileNotification mobileNotification) {
		getPersistence().cacheResult(mobileNotification);
	}

	/**
	 * Caches the mobile notifications in the entity cache if it is enabled.
	 *
	 * @param mobileNotifications the mobile notifications
	 */
	public static void cacheResult(
		List<MobileNotification> mobileNotifications) {

		getPersistence().cacheResult(mobileNotifications);
	}

	/**
	 * Creates a new mobile notification with the primary key. Does not add the mobile notification to the database.
	 *
	 * @param mobileNotificationId the primary key for the new mobile notification
	 * @return the new mobile notification
	 */
	public static MobileNotification create(long mobileNotificationId) {
		return getPersistence().create(mobileNotificationId);
	}

	/**
	 * Removes the mobile notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mobileNotificationId the primary key of the mobile notification
	 * @return the mobile notification that was removed
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	public static MobileNotification remove(long mobileNotificationId)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().remove(mobileNotificationId);
	}

	public static MobileNotification updateImpl(
		MobileNotification mobileNotification) {

		return getPersistence().updateImpl(mobileNotification);
	}

	/**
	 * Returns the mobile notification with the primary key or throws a <code>NoSuchMobileNotificationException</code> if it could not be found.
	 *
	 * @param mobileNotificationId the primary key of the mobile notification
	 * @return the mobile notification
	 * @throws NoSuchMobileNotificationException if a mobile notification with the primary key could not be found
	 */
	public static MobileNotification findByPrimaryKey(long mobileNotificationId)
		throws com.weprode.nero.preference.exception.
			NoSuchMobileNotificationException {

		return getPersistence().findByPrimaryKey(mobileNotificationId);
	}

	/**
	 * Returns the mobile notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mobileNotificationId the primary key of the mobile notification
	 * @return the mobile notification, or <code>null</code> if a mobile notification with the primary key could not be found
	 */
	public static MobileNotification fetchByPrimaryKey(
		long mobileNotificationId) {

		return getPersistence().fetchByPrimaryKey(mobileNotificationId);
	}

	/**
	 * Returns all the mobile notifications.
	 *
	 * @return the mobile notifications
	 */
	public static List<MobileNotification> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the mobile notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @return the range of mobile notifications
	 */
	public static List<MobileNotification> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the mobile notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of mobile notifications
	 */
	public static List<MobileNotification> findAll(
		int start, int end,
		OrderByComparator<MobileNotification> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the mobile notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MobileNotificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mobile notifications
	 * @param end the upper bound of the range of mobile notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of mobile notifications
	 */
	public static List<MobileNotification> findAll(
		int start, int end,
		OrderByComparator<MobileNotification> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the mobile notifications from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of mobile notifications.
	 *
	 * @return the number of mobile notifications
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MobileNotificationPersistence getPersistence() {
		return _persistence;
	}

	private static volatile MobileNotificationPersistence _persistence;

}