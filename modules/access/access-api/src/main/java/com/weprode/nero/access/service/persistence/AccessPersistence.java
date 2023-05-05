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

package com.weprode.nero.access.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.access.exception.NoSuchAccessException;
import com.weprode.nero.access.model.Access;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the access service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccessUtil
 * @generated
 */
@ProviderType
public interface AccessPersistence extends BasePersistence<Access> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccessUtil} to access the access persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the accesses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching accesses
	 */
	public java.util.List<Access> findByUuid(String uuid);

	/**
	 * Returns a range of all the accesses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of accesses
	 * @param end the upper bound of the range of accesses (not inclusive)
	 * @return the range of matching accesses
	 */
	public java.util.List<Access> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the accesses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of accesses
	 * @param end the upper bound of the range of accesses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching accesses
	 */
	public java.util.List<Access> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Access>
			orderByComparator);

	/**
	 * Returns an ordered range of all the accesses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of accesses
	 * @param end the upper bound of the range of accesses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching accesses
	 */
	public java.util.List<Access> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Access>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first access in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access
	 * @throws NoSuchAccessException if a matching access could not be found
	 */
	public Access findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Access>
				orderByComparator)
		throws NoSuchAccessException;

	/**
	 * Returns the first access in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access, or <code>null</code> if a matching access could not be found
	 */
	public Access fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Access>
			orderByComparator);

	/**
	 * Returns the last access in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access
	 * @throws NoSuchAccessException if a matching access could not be found
	 */
	public Access findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Access>
				orderByComparator)
		throws NoSuchAccessException;

	/**
	 * Returns the last access in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access, or <code>null</code> if a matching access could not be found
	 */
	public Access fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Access>
			orderByComparator);

	/**
	 * Returns the accesses before and after the current access in the ordered set where uuid = &#63;.
	 *
	 * @param accessId the primary key of the current access
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access
	 * @throws NoSuchAccessException if a access with the primary key could not be found
	 */
	public Access[] findByUuid_PrevAndNext(
			long accessId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Access>
				orderByComparator)
		throws NoSuchAccessException;

	/**
	 * Removes all the accesses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of accesses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching accesses
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the accesses where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the matching accesses
	 */
	public java.util.List<Access> findBycategoryId(long categoryId);

	/**
	 * Returns a range of all the accesses where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessModelImpl</code>.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of accesses
	 * @param end the upper bound of the range of accesses (not inclusive)
	 * @return the range of matching accesses
	 */
	public java.util.List<Access> findBycategoryId(
		long categoryId, int start, int end);

	/**
	 * Returns an ordered range of all the accesses where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessModelImpl</code>.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of accesses
	 * @param end the upper bound of the range of accesses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching accesses
	 */
	public java.util.List<Access> findBycategoryId(
		long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Access>
			orderByComparator);

	/**
	 * Returns an ordered range of all the accesses where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessModelImpl</code>.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of accesses
	 * @param end the upper bound of the range of accesses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching accesses
	 */
	public java.util.List<Access> findBycategoryId(
		long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Access>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first access in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access
	 * @throws NoSuchAccessException if a matching access could not be found
	 */
	public Access findBycategoryId_First(
			long categoryId,
			com.liferay.portal.kernel.util.OrderByComparator<Access>
				orderByComparator)
		throws NoSuchAccessException;

	/**
	 * Returns the first access in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access, or <code>null</code> if a matching access could not be found
	 */
	public Access fetchBycategoryId_First(
		long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<Access>
			orderByComparator);

	/**
	 * Returns the last access in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access
	 * @throws NoSuchAccessException if a matching access could not be found
	 */
	public Access findBycategoryId_Last(
			long categoryId,
			com.liferay.portal.kernel.util.OrderByComparator<Access>
				orderByComparator)
		throws NoSuchAccessException;

	/**
	 * Returns the last access in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access, or <code>null</code> if a matching access could not be found
	 */
	public Access fetchBycategoryId_Last(
		long categoryId,
		com.liferay.portal.kernel.util.OrderByComparator<Access>
			orderByComparator);

	/**
	 * Returns the accesses before and after the current access in the ordered set where categoryId = &#63;.
	 *
	 * @param accessId the primary key of the current access
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access
	 * @throws NoSuchAccessException if a access with the primary key could not be found
	 */
	public Access[] findBycategoryId_PrevAndNext(
			long accessId, long categoryId,
			com.liferay.portal.kernel.util.OrderByComparator<Access>
				orderByComparator)
		throws NoSuchAccessException;

	/**
	 * Removes all the accesses where categoryId = &#63; from the database.
	 *
	 * @param categoryId the category ID
	 */
	public void removeBycategoryId(long categoryId);

	/**
	 * Returns the number of accesses where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the number of matching accesses
	 */
	public int countBycategoryId(long categoryId);

	/**
	 * Caches the access in the entity cache if it is enabled.
	 *
	 * @param access the access
	 */
	public void cacheResult(Access access);

	/**
	 * Caches the accesses in the entity cache if it is enabled.
	 *
	 * @param accesses the accesses
	 */
	public void cacheResult(java.util.List<Access> accesses);

	/**
	 * Creates a new access with the primary key. Does not add the access to the database.
	 *
	 * @param accessId the primary key for the new access
	 * @return the new access
	 */
	public Access create(long accessId);

	/**
	 * Removes the access with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accessId the primary key of the access
	 * @return the access that was removed
	 * @throws NoSuchAccessException if a access with the primary key could not be found
	 */
	public Access remove(long accessId) throws NoSuchAccessException;

	public Access updateImpl(Access access);

	/**
	 * Returns the access with the primary key or throws a <code>NoSuchAccessException</code> if it could not be found.
	 *
	 * @param accessId the primary key of the access
	 * @return the access
	 * @throws NoSuchAccessException if a access with the primary key could not be found
	 */
	public Access findByPrimaryKey(long accessId) throws NoSuchAccessException;

	/**
	 * Returns the access with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accessId the primary key of the access
	 * @return the access, or <code>null</code> if a access with the primary key could not be found
	 */
	public Access fetchByPrimaryKey(long accessId);

	/**
	 * Returns all the accesses.
	 *
	 * @return the accesses
	 */
	public java.util.List<Access> findAll();

	/**
	 * Returns a range of all the accesses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of accesses
	 * @param end the upper bound of the range of accesses (not inclusive)
	 * @return the range of accesses
	 */
	public java.util.List<Access> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the accesses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of accesses
	 * @param end the upper bound of the range of accesses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of accesses
	 */
	public java.util.List<Access> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Access>
			orderByComparator);

	/**
	 * Returns an ordered range of all the accesses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of accesses
	 * @param end the upper bound of the range of accesses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of accesses
	 */
	public java.util.List<Access> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Access>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the accesses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of accesses.
	 *
	 * @return the number of accesses
	 */
	public int countAll();

}