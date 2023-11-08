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

package com.weprode.facile.user.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.user.exception.NoSuchNewsAdminException;
import com.weprode.facile.user.model.NewsAdmin;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the news admin service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsAdminUtil
 * @generated
 */
@ProviderType
public interface NewsAdminPersistence extends BasePersistence<NewsAdmin> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NewsAdminUtil} to access the news admin persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the news admins where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching news admins
	 */
	public java.util.List<NewsAdmin> findByuserId(long userId);

	/**
	 * Returns a range of all the news admins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @return the range of matching news admins
	 */
	public java.util.List<NewsAdmin> findByuserId(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the news admins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news admins
	 */
	public java.util.List<NewsAdmin> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
			orderByComparator);

	/**
	 * Returns an ordered range of all the news admins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news admins
	 */
	public java.util.List<NewsAdmin> findByuserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first news admin in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news admin
	 * @throws NoSuchNewsAdminException if a matching news admin could not be found
	 */
	public NewsAdmin findByuserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
				orderByComparator)
		throws NoSuchNewsAdminException;

	/**
	 * Returns the first news admin in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news admin, or <code>null</code> if a matching news admin could not be found
	 */
	public NewsAdmin fetchByuserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
			orderByComparator);

	/**
	 * Returns the last news admin in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news admin
	 * @throws NoSuchNewsAdminException if a matching news admin could not be found
	 */
	public NewsAdmin findByuserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
				orderByComparator)
		throws NoSuchNewsAdminException;

	/**
	 * Returns the last news admin in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news admin, or <code>null</code> if a matching news admin could not be found
	 */
	public NewsAdmin fetchByuserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
			orderByComparator);

	/**
	 * Returns the news admins before and after the current news admin in the ordered set where userId = &#63;.
	 *
	 * @param newsAdminId the primary key of the current news admin
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news admin
	 * @throws NoSuchNewsAdminException if a news admin with the primary key could not be found
	 */
	public NewsAdmin[] findByuserId_PrevAndNext(
			long newsAdminId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
				orderByComparator)
		throws NoSuchNewsAdminException;

	/**
	 * Removes all the news admins where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByuserId(long userId);

	/**
	 * Returns the number of news admins where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching news admins
	 */
	public int countByuserId(long userId);

	/**
	 * Returns all the news admins where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching news admins
	 */
	public java.util.List<NewsAdmin> findByschoolId(long schoolId);

	/**
	 * Returns a range of all the news admins where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @return the range of matching news admins
	 */
	public java.util.List<NewsAdmin> findByschoolId(
		long schoolId, int start, int end);

	/**
	 * Returns an ordered range of all the news admins where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching news admins
	 */
	public java.util.List<NewsAdmin> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
			orderByComparator);

	/**
	 * Returns an ordered range of all the news admins where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching news admins
	 */
	public java.util.List<NewsAdmin> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first news admin in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news admin
	 * @throws NoSuchNewsAdminException if a matching news admin could not be found
	 */
	public NewsAdmin findByschoolId_First(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
				orderByComparator)
		throws NoSuchNewsAdminException;

	/**
	 * Returns the first news admin in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching news admin, or <code>null</code> if a matching news admin could not be found
	 */
	public NewsAdmin fetchByschoolId_First(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
			orderByComparator);

	/**
	 * Returns the last news admin in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news admin
	 * @throws NoSuchNewsAdminException if a matching news admin could not be found
	 */
	public NewsAdmin findByschoolId_Last(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
				orderByComparator)
		throws NoSuchNewsAdminException;

	/**
	 * Returns the last news admin in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching news admin, or <code>null</code> if a matching news admin could not be found
	 */
	public NewsAdmin fetchByschoolId_Last(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
			orderByComparator);

	/**
	 * Returns the news admins before and after the current news admin in the ordered set where schoolId = &#63;.
	 *
	 * @param newsAdminId the primary key of the current news admin
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next news admin
	 * @throws NoSuchNewsAdminException if a news admin with the primary key could not be found
	 */
	public NewsAdmin[] findByschoolId_PrevAndNext(
			long newsAdminId, long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
				orderByComparator)
		throws NoSuchNewsAdminException;

	/**
	 * Removes all the news admins where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public void removeByschoolId(long schoolId);

	/**
	 * Returns the number of news admins where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching news admins
	 */
	public int countByschoolId(long schoolId);

	/**
	 * Caches the news admin in the entity cache if it is enabled.
	 *
	 * @param newsAdmin the news admin
	 */
	public void cacheResult(NewsAdmin newsAdmin);

	/**
	 * Caches the news admins in the entity cache if it is enabled.
	 *
	 * @param newsAdmins the news admins
	 */
	public void cacheResult(java.util.List<NewsAdmin> newsAdmins);

	/**
	 * Creates a new news admin with the primary key. Does not add the news admin to the database.
	 *
	 * @param newsAdminId the primary key for the new news admin
	 * @return the new news admin
	 */
	public NewsAdmin create(long newsAdminId);

	/**
	 * Removes the news admin with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param newsAdminId the primary key of the news admin
	 * @return the news admin that was removed
	 * @throws NoSuchNewsAdminException if a news admin with the primary key could not be found
	 */
	public NewsAdmin remove(long newsAdminId) throws NoSuchNewsAdminException;

	public NewsAdmin updateImpl(NewsAdmin newsAdmin);

	/**
	 * Returns the news admin with the primary key or throws a <code>NoSuchNewsAdminException</code> if it could not be found.
	 *
	 * @param newsAdminId the primary key of the news admin
	 * @return the news admin
	 * @throws NoSuchNewsAdminException if a news admin with the primary key could not be found
	 */
	public NewsAdmin findByPrimaryKey(long newsAdminId)
		throws NoSuchNewsAdminException;

	/**
	 * Returns the news admin with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param newsAdminId the primary key of the news admin
	 * @return the news admin, or <code>null</code> if a news admin with the primary key could not be found
	 */
	public NewsAdmin fetchByPrimaryKey(long newsAdminId);

	/**
	 * Returns all the news admins.
	 *
	 * @return the news admins
	 */
	public java.util.List<NewsAdmin> findAll();

	/**
	 * Returns a range of all the news admins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @return the range of news admins
	 */
	public java.util.List<NewsAdmin> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the news admins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of news admins
	 */
	public java.util.List<NewsAdmin> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
			orderByComparator);

	/**
	 * Returns an ordered range of all the news admins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NewsAdminModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of news admins
	 * @param end the upper bound of the range of news admins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of news admins
	 */
	public java.util.List<NewsAdmin> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsAdmin>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the news admins from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of news admins.
	 *
	 * @return the number of news admins
	 */
	public int countAll();

}