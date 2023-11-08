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

package com.weprode.facile.access.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.facile.access.exception.NoSuchCategoryException;
import com.weprode.facile.access.model.AccessCategory;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the access category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccessCategoryUtil
 * @generated
 */
@ProviderType
public interface AccessCategoryPersistence
	extends BasePersistence<AccessCategory> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccessCategoryUtil} to access the access category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the access categories where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching access categories
	 */
	public java.util.List<AccessCategory> findByUuid(String uuid);

	/**
	 * Returns a range of all the access categories where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of access categories
	 * @param end the upper bound of the range of access categories (not inclusive)
	 * @return the range of matching access categories
	 */
	public java.util.List<AccessCategory> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the access categories where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of access categories
	 * @param end the upper bound of the range of access categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching access categories
	 */
	public java.util.List<AccessCategory> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
			orderByComparator);

	/**
	 * Returns an ordered range of all the access categories where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of access categories
	 * @param end the upper bound of the range of access categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching access categories
	 */
	public java.util.List<AccessCategory> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first access category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access category
	 * @throws NoSuchCategoryException if a matching access category could not be found
	 */
	public AccessCategory findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
				orderByComparator)
		throws NoSuchCategoryException;

	/**
	 * Returns the first access category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access category, or <code>null</code> if a matching access category could not be found
	 */
	public AccessCategory fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
			orderByComparator);

	/**
	 * Returns the last access category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access category
	 * @throws NoSuchCategoryException if a matching access category could not be found
	 */
	public AccessCategory findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
				orderByComparator)
		throws NoSuchCategoryException;

	/**
	 * Returns the last access category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access category, or <code>null</code> if a matching access category could not be found
	 */
	public AccessCategory fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
			orderByComparator);

	/**
	 * Returns the access categories before and after the current access category in the ordered set where uuid = &#63;.
	 *
	 * @param categoryId the primary key of the current access category
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access category
	 * @throws NoSuchCategoryException if a access category with the primary key could not be found
	 */
	public AccessCategory[] findByUuid_PrevAndNext(
			long categoryId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
				orderByComparator)
		throws NoSuchCategoryException;

	/**
	 * Removes all the access categories where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of access categories where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching access categories
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the access categories where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching access categories
	 */
	public java.util.List<AccessCategory> findByschoolId(long schoolId);

	/**
	 * Returns a range of all the access categories where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of access categories
	 * @param end the upper bound of the range of access categories (not inclusive)
	 * @return the range of matching access categories
	 */
	public java.util.List<AccessCategory> findByschoolId(
		long schoolId, int start, int end);

	/**
	 * Returns an ordered range of all the access categories where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of access categories
	 * @param end the upper bound of the range of access categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching access categories
	 */
	public java.util.List<AccessCategory> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
			orderByComparator);

	/**
	 * Returns an ordered range of all the access categories where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of access categories
	 * @param end the upper bound of the range of access categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching access categories
	 */
	public java.util.List<AccessCategory> findByschoolId(
		long schoolId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first access category in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access category
	 * @throws NoSuchCategoryException if a matching access category could not be found
	 */
	public AccessCategory findByschoolId_First(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
				orderByComparator)
		throws NoSuchCategoryException;

	/**
	 * Returns the first access category in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access category, or <code>null</code> if a matching access category could not be found
	 */
	public AccessCategory fetchByschoolId_First(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
			orderByComparator);

	/**
	 * Returns the last access category in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access category
	 * @throws NoSuchCategoryException if a matching access category could not be found
	 */
	public AccessCategory findByschoolId_Last(
			long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
				orderByComparator)
		throws NoSuchCategoryException;

	/**
	 * Returns the last access category in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access category, or <code>null</code> if a matching access category could not be found
	 */
	public AccessCategory fetchByschoolId_Last(
		long schoolId,
		com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
			orderByComparator);

	/**
	 * Returns the access categories before and after the current access category in the ordered set where schoolId = &#63;.
	 *
	 * @param categoryId the primary key of the current access category
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access category
	 * @throws NoSuchCategoryException if a access category with the primary key could not be found
	 */
	public AccessCategory[] findByschoolId_PrevAndNext(
			long categoryId, long schoolId,
			com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
				orderByComparator)
		throws NoSuchCategoryException;

	/**
	 * Removes all the access categories where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public void removeByschoolId(long schoolId);

	/**
	 * Returns the number of access categories where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching access categories
	 */
	public int countByschoolId(long schoolId);

	/**
	 * Caches the access category in the entity cache if it is enabled.
	 *
	 * @param accessCategory the access category
	 */
	public void cacheResult(AccessCategory accessCategory);

	/**
	 * Caches the access categories in the entity cache if it is enabled.
	 *
	 * @param accessCategories the access categories
	 */
	public void cacheResult(java.util.List<AccessCategory> accessCategories);

	/**
	 * Creates a new access category with the primary key. Does not add the access category to the database.
	 *
	 * @param categoryId the primary key for the new access category
	 * @return the new access category
	 */
	public AccessCategory create(long categoryId);

	/**
	 * Removes the access category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param categoryId the primary key of the access category
	 * @return the access category that was removed
	 * @throws NoSuchCategoryException if a access category with the primary key could not be found
	 */
	public AccessCategory remove(long categoryId)
		throws NoSuchCategoryException;

	public AccessCategory updateImpl(AccessCategory accessCategory);

	/**
	 * Returns the access category with the primary key or throws a <code>NoSuchCategoryException</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the access category
	 * @return the access category
	 * @throws NoSuchCategoryException if a access category with the primary key could not be found
	 */
	public AccessCategory findByPrimaryKey(long categoryId)
		throws NoSuchCategoryException;

	/**
	 * Returns the access category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the access category
	 * @return the access category, or <code>null</code> if a access category with the primary key could not be found
	 */
	public AccessCategory fetchByPrimaryKey(long categoryId);

	/**
	 * Returns all the access categories.
	 *
	 * @return the access categories
	 */
	public java.util.List<AccessCategory> findAll();

	/**
	 * Returns a range of all the access categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access categories
	 * @param end the upper bound of the range of access categories (not inclusive)
	 * @return the range of access categories
	 */
	public java.util.List<AccessCategory> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the access categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access categories
	 * @param end the upper bound of the range of access categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of access categories
	 */
	public java.util.List<AccessCategory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
			orderByComparator);

	/**
	 * Returns an ordered range of all the access categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access categories
	 * @param end the upper bound of the range of access categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of access categories
	 */
	public java.util.List<AccessCategory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccessCategory>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the access categories from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of access categories.
	 *
	 * @return the number of access categories
	 */
	public int countAll();

}