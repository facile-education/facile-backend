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

package com.weprode.nero.school.life.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.school.life.exception.NoSuchNotificationException;
import com.weprode.nero.school.life.model.Notification;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the notification service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NotificationUtil
 * @generated
 */
@ProviderType
public interface NotificationPersistence extends BasePersistence<Notification> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NotificationUtil} to access the notification persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the notifications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching notifications
	 */
	public java.util.List<Notification> findByuserId(long userId);

	/**
	 * Returns a range of all the notifications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of notifications
	 * @param end the upper bound of the range of notifications (not inclusive)
	 * @return the range of matching notifications
	 */
	public java.util.List<Notification> findByuserId(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the notifications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of notifications
	 * @param end the upper bound of the range of notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching notifications
	 */
	public java.util.List<Notification> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notification>
			orderByComparator);

	/**
	 * Returns an ordered range of all the notifications where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of notifications
	 * @param end the upper bound of the range of notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching notifications
	 */
	public java.util.List<Notification> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notification>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification
	 * @throws NoSuchNotificationException if a matching notification could not be found
	 */
	public Notification findByuserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Notification>
				orderByComparator)
		throws NoSuchNotificationException;

	/**
	 * Returns the first notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching notification, or <code>null</code> if a matching notification could not be found
	 */
	public Notification fetchByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Notification>
			orderByComparator);

	/**
	 * Returns the last notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification
	 * @throws NoSuchNotificationException if a matching notification could not be found
	 */
	public Notification findByuserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Notification>
				orderByComparator)
		throws NoSuchNotificationException;

	/**
	 * Returns the last notification in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching notification, or <code>null</code> if a matching notification could not be found
	 */
	public Notification fetchByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Notification>
			orderByComparator);

	/**
	 * Returns the notifications before and after the current notification in the ordered set where userId = &#63;.
	 *
	 * @param notificationPK the primary key of the current notification
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next notification
	 * @throws NoSuchNotificationException if a notification with the primary key could not be found
	 */
	public Notification[] findByuserId_PrevAndNext(
			NotificationPK notificationPK, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Notification>
				orderByComparator)
		throws NoSuchNotificationException;

	/**
	 * Removes all the notifications where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId);

	/**
	 * Returns the number of notifications where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching notifications
	 */
	public int countByuserId(long userId);

	/**
	 * Caches the notification in the entity cache if it is enabled.
	 *
	 * @param notification the notification
	 */
	public void cacheResult(Notification notification);

	/**
	 * Caches the notifications in the entity cache if it is enabled.
	 *
	 * @param notifications the notifications
	 */
	public void cacheResult(java.util.List<Notification> notifications);

	/**
	 * Creates a new notification with the primary key. Does not add the notification to the database.
	 *
	 * @param notificationPK the primary key for the new notification
	 * @return the new notification
	 */
	public Notification create(NotificationPK notificationPK);

	/**
	 * Removes the notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notificationPK the primary key of the notification
	 * @return the notification that was removed
	 * @throws NoSuchNotificationException if a notification with the primary key could not be found
	 */
	public Notification remove(NotificationPK notificationPK)
		throws NoSuchNotificationException;

	public Notification updateImpl(Notification notification);

	/**
	 * Returns the notification with the primary key or throws a <code>NoSuchNotificationException</code> if it could not be found.
	 *
	 * @param notificationPK the primary key of the notification
	 * @return the notification
	 * @throws NoSuchNotificationException if a notification with the primary key could not be found
	 */
	public Notification findByPrimaryKey(NotificationPK notificationPK)
		throws NoSuchNotificationException;

	/**
	 * Returns the notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notificationPK the primary key of the notification
	 * @return the notification, or <code>null</code> if a notification with the primary key could not be found
	 */
	public Notification fetchByPrimaryKey(NotificationPK notificationPK);

	/**
	 * Returns all the notifications.
	 *
	 * @return the notifications
	 */
	public java.util.List<Notification> findAll();

	/**
	 * Returns a range of all the notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notifications
	 * @param end the upper bound of the range of notifications (not inclusive)
	 * @return the range of notifications
	 */
	public java.util.List<Notification> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notifications
	 * @param end the upper bound of the range of notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of notifications
	 */
	public java.util.List<Notification> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notification>
			orderByComparator);

	/**
	 * Returns an ordered range of all the notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NotificationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of notifications
	 * @param end the upper bound of the range of notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of notifications
	 */
	public java.util.List<Notification> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notification>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the notifications from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of notifications.
	 *
	 * @return the number of notifications
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}