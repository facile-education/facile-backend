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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.access.model.Access;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the access service. This utility wraps <code>com.weprode.nero.access.service.persistence.impl.AccessPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccessPersistence
 * @generated
 */
public class AccessUtil {

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
	public static void clearCache(Access access) {
		getPersistence().clearCache(access);
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
	public static Map<Serializable, Access> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Access> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Access> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Access> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Access> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Access update(Access access) {
		return getPersistence().update(access);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Access update(Access access, ServiceContext serviceContext) {
		return getPersistence().update(access, serviceContext);
	}

	/**
	 * Returns all the accesses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching accesses
	 */
	public static List<Access> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<Access> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<Access> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Access> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<Access> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Access> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first access in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access
	 * @throws NoSuchAccessException if a matching access could not be found
	 */
	public static Access findByUuid_First(
			String uuid, OrderByComparator<Access> orderByComparator)
		throws com.weprode.nero.access.exception.NoSuchAccessException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first access in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access, or <code>null</code> if a matching access could not be found
	 */
	public static Access fetchByUuid_First(
		String uuid, OrderByComparator<Access> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last access in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access
	 * @throws NoSuchAccessException if a matching access could not be found
	 */
	public static Access findByUuid_Last(
			String uuid, OrderByComparator<Access> orderByComparator)
		throws com.weprode.nero.access.exception.NoSuchAccessException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last access in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access, or <code>null</code> if a matching access could not be found
	 */
	public static Access fetchByUuid_Last(
		String uuid, OrderByComparator<Access> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the accesses before and after the current access in the ordered set where uuid = &#63;.
	 *
	 * @param accessId the primary key of the current access
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access
	 * @throws NoSuchAccessException if a access with the primary key could not be found
	 */
	public static Access[] findByUuid_PrevAndNext(
			long accessId, String uuid,
			OrderByComparator<Access> orderByComparator)
		throws com.weprode.nero.access.exception.NoSuchAccessException {

		return getPersistence().findByUuid_PrevAndNext(
			accessId, uuid, orderByComparator);
	}

	/**
	 * Removes all the accesses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of accesses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching accesses
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the accesses where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the matching accesses
	 */
	public static List<Access> findBycategoryId(long categoryId) {
		return getPersistence().findBycategoryId(categoryId);
	}

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
	public static List<Access> findBycategoryId(
		long categoryId, int start, int end) {

		return getPersistence().findBycategoryId(categoryId, start, end);
	}

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
	public static List<Access> findBycategoryId(
		long categoryId, int start, int end,
		OrderByComparator<Access> orderByComparator) {

		return getPersistence().findBycategoryId(
			categoryId, start, end, orderByComparator);
	}

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
	public static List<Access> findBycategoryId(
		long categoryId, int start, int end,
		OrderByComparator<Access> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBycategoryId(
			categoryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first access in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access
	 * @throws NoSuchAccessException if a matching access could not be found
	 */
	public static Access findBycategoryId_First(
			long categoryId, OrderByComparator<Access> orderByComparator)
		throws com.weprode.nero.access.exception.NoSuchAccessException {

		return getPersistence().findBycategoryId_First(
			categoryId, orderByComparator);
	}

	/**
	 * Returns the first access in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access, or <code>null</code> if a matching access could not be found
	 */
	public static Access fetchBycategoryId_First(
		long categoryId, OrderByComparator<Access> orderByComparator) {

		return getPersistence().fetchBycategoryId_First(
			categoryId, orderByComparator);
	}

	/**
	 * Returns the last access in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access
	 * @throws NoSuchAccessException if a matching access could not be found
	 */
	public static Access findBycategoryId_Last(
			long categoryId, OrderByComparator<Access> orderByComparator)
		throws com.weprode.nero.access.exception.NoSuchAccessException {

		return getPersistence().findBycategoryId_Last(
			categoryId, orderByComparator);
	}

	/**
	 * Returns the last access in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access, or <code>null</code> if a matching access could not be found
	 */
	public static Access fetchBycategoryId_Last(
		long categoryId, OrderByComparator<Access> orderByComparator) {

		return getPersistence().fetchBycategoryId_Last(
			categoryId, orderByComparator);
	}

	/**
	 * Returns the accesses before and after the current access in the ordered set where categoryId = &#63;.
	 *
	 * @param accessId the primary key of the current access
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access
	 * @throws NoSuchAccessException if a access with the primary key could not be found
	 */
	public static Access[] findBycategoryId_PrevAndNext(
			long accessId, long categoryId,
			OrderByComparator<Access> orderByComparator)
		throws com.weprode.nero.access.exception.NoSuchAccessException {

		return getPersistence().findBycategoryId_PrevAndNext(
			accessId, categoryId, orderByComparator);
	}

	/**
	 * Removes all the accesses where categoryId = &#63; from the database.
	 *
	 * @param categoryId the category ID
	 */
	public static void removeBycategoryId(long categoryId) {
		getPersistence().removeBycategoryId(categoryId);
	}

	/**
	 * Returns the number of accesses where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the number of matching accesses
	 */
	public static int countBycategoryId(long categoryId) {
		return getPersistence().countBycategoryId(categoryId);
	}

	/**
	 * Caches the access in the entity cache if it is enabled.
	 *
	 * @param access the access
	 */
	public static void cacheResult(Access access) {
		getPersistence().cacheResult(access);
	}

	/**
	 * Caches the accesses in the entity cache if it is enabled.
	 *
	 * @param accesses the accesses
	 */
	public static void cacheResult(List<Access> accesses) {
		getPersistence().cacheResult(accesses);
	}

	/**
	 * Creates a new access with the primary key. Does not add the access to the database.
	 *
	 * @param accessId the primary key for the new access
	 * @return the new access
	 */
	public static Access create(long accessId) {
		return getPersistence().create(accessId);
	}

	/**
	 * Removes the access with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accessId the primary key of the access
	 * @return the access that was removed
	 * @throws NoSuchAccessException if a access with the primary key could not be found
	 */
	public static Access remove(long accessId)
		throws com.weprode.nero.access.exception.NoSuchAccessException {

		return getPersistence().remove(accessId);
	}

	public static Access updateImpl(Access access) {
		return getPersistence().updateImpl(access);
	}

	/**
	 * Returns the access with the primary key or throws a <code>NoSuchAccessException</code> if it could not be found.
	 *
	 * @param accessId the primary key of the access
	 * @return the access
	 * @throws NoSuchAccessException if a access with the primary key could not be found
	 */
	public static Access findByPrimaryKey(long accessId)
		throws com.weprode.nero.access.exception.NoSuchAccessException {

		return getPersistence().findByPrimaryKey(accessId);
	}

	/**
	 * Returns the access with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accessId the primary key of the access
	 * @return the access, or <code>null</code> if a access with the primary key could not be found
	 */
	public static Access fetchByPrimaryKey(long accessId) {
		return getPersistence().fetchByPrimaryKey(accessId);
	}

	/**
	 * Returns all the accesses.
	 *
	 * @return the accesses
	 */
	public static List<Access> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Access> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Access> findAll(
		int start, int end, OrderByComparator<Access> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Access> findAll(
		int start, int end, OrderByComparator<Access> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the accesses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of accesses.
	 *
	 * @return the number of accesses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AccessPersistence getPersistence() {
		return _persistence;
	}

	private static volatile AccessPersistence _persistence;

}