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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.access.model.AccessCategory;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the access category service. This utility wraps <code>com.weprode.facile.access.service.persistence.impl.AccessCategoryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccessCategoryPersistence
 * @generated
 */
public class AccessCategoryUtil {

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
	public static void clearCache(AccessCategory accessCategory) {
		getPersistence().clearCache(accessCategory);
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
	public static Map<Serializable, AccessCategory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AccessCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccessCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccessCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccessCategory> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccessCategory update(AccessCategory accessCategory) {
		return getPersistence().update(accessCategory);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccessCategory update(
		AccessCategory accessCategory, ServiceContext serviceContext) {

		return getPersistence().update(accessCategory, serviceContext);
	}

	/**
	 * Returns all the access categories where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching access categories
	 */
	public static List<AccessCategory> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<AccessCategory> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<AccessCategory> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AccessCategory> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<AccessCategory> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AccessCategory> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first access category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access category
	 * @throws NoSuchCategoryException if a matching access category could not be found
	 */
	public static AccessCategory findByUuid_First(
			String uuid, OrderByComparator<AccessCategory> orderByComparator)
		throws com.weprode.facile.access.exception.NoSuchCategoryException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first access category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access category, or <code>null</code> if a matching access category could not be found
	 */
	public static AccessCategory fetchByUuid_First(
		String uuid, OrderByComparator<AccessCategory> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last access category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access category
	 * @throws NoSuchCategoryException if a matching access category could not be found
	 */
	public static AccessCategory findByUuid_Last(
			String uuid, OrderByComparator<AccessCategory> orderByComparator)
		throws com.weprode.facile.access.exception.NoSuchCategoryException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last access category in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access category, or <code>null</code> if a matching access category could not be found
	 */
	public static AccessCategory fetchByUuid_Last(
		String uuid, OrderByComparator<AccessCategory> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the access categories before and after the current access category in the ordered set where uuid = &#63;.
	 *
	 * @param categoryId the primary key of the current access category
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access category
	 * @throws NoSuchCategoryException if a access category with the primary key could not be found
	 */
	public static AccessCategory[] findByUuid_PrevAndNext(
			long categoryId, String uuid,
			OrderByComparator<AccessCategory> orderByComparator)
		throws com.weprode.facile.access.exception.NoSuchCategoryException {

		return getPersistence().findByUuid_PrevAndNext(
			categoryId, uuid, orderByComparator);
	}

	/**
	 * Removes all the access categories where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of access categories where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching access categories
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the access categories where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching access categories
	 */
	public static List<AccessCategory> findByschoolId(long schoolId) {
		return getPersistence().findByschoolId(schoolId);
	}

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
	public static List<AccessCategory> findByschoolId(
		long schoolId, int start, int end) {

		return getPersistence().findByschoolId(schoolId, start, end);
	}

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
	public static List<AccessCategory> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<AccessCategory> orderByComparator) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator);
	}

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
	public static List<AccessCategory> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<AccessCategory> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first access category in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access category
	 * @throws NoSuchCategoryException if a matching access category could not be found
	 */
	public static AccessCategory findByschoolId_First(
			long schoolId, OrderByComparator<AccessCategory> orderByComparator)
		throws com.weprode.facile.access.exception.NoSuchCategoryException {

		return getPersistence().findByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the first access category in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access category, or <code>null</code> if a matching access category could not be found
	 */
	public static AccessCategory fetchByschoolId_First(
		long schoolId, OrderByComparator<AccessCategory> orderByComparator) {

		return getPersistence().fetchByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last access category in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access category
	 * @throws NoSuchCategoryException if a matching access category could not be found
	 */
	public static AccessCategory findByschoolId_Last(
			long schoolId, OrderByComparator<AccessCategory> orderByComparator)
		throws com.weprode.facile.access.exception.NoSuchCategoryException {

		return getPersistence().findByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last access category in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access category, or <code>null</code> if a matching access category could not be found
	 */
	public static AccessCategory fetchByschoolId_Last(
		long schoolId, OrderByComparator<AccessCategory> orderByComparator) {

		return getPersistence().fetchByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the access categories before and after the current access category in the ordered set where schoolId = &#63;.
	 *
	 * @param categoryId the primary key of the current access category
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access category
	 * @throws NoSuchCategoryException if a access category with the primary key could not be found
	 */
	public static AccessCategory[] findByschoolId_PrevAndNext(
			long categoryId, long schoolId,
			OrderByComparator<AccessCategory> orderByComparator)
		throws com.weprode.facile.access.exception.NoSuchCategoryException {

		return getPersistence().findByschoolId_PrevAndNext(
			categoryId, schoolId, orderByComparator);
	}

	/**
	 * Removes all the access categories where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public static void removeByschoolId(long schoolId) {
		getPersistence().removeByschoolId(schoolId);
	}

	/**
	 * Returns the number of access categories where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching access categories
	 */
	public static int countByschoolId(long schoolId) {
		return getPersistence().countByschoolId(schoolId);
	}

	/**
	 * Caches the access category in the entity cache if it is enabled.
	 *
	 * @param accessCategory the access category
	 */
	public static void cacheResult(AccessCategory accessCategory) {
		getPersistence().cacheResult(accessCategory);
	}

	/**
	 * Caches the access categories in the entity cache if it is enabled.
	 *
	 * @param accessCategories the access categories
	 */
	public static void cacheResult(List<AccessCategory> accessCategories) {
		getPersistence().cacheResult(accessCategories);
	}

	/**
	 * Creates a new access category with the primary key. Does not add the access category to the database.
	 *
	 * @param categoryId the primary key for the new access category
	 * @return the new access category
	 */
	public static AccessCategory create(long categoryId) {
		return getPersistence().create(categoryId);
	}

	/**
	 * Removes the access category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param categoryId the primary key of the access category
	 * @return the access category that was removed
	 * @throws NoSuchCategoryException if a access category with the primary key could not be found
	 */
	public static AccessCategory remove(long categoryId)
		throws com.weprode.facile.access.exception.NoSuchCategoryException {

		return getPersistence().remove(categoryId);
	}

	public static AccessCategory updateImpl(AccessCategory accessCategory) {
		return getPersistence().updateImpl(accessCategory);
	}

	/**
	 * Returns the access category with the primary key or throws a <code>NoSuchCategoryException</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the access category
	 * @return the access category
	 * @throws NoSuchCategoryException if a access category with the primary key could not be found
	 */
	public static AccessCategory findByPrimaryKey(long categoryId)
		throws com.weprode.facile.access.exception.NoSuchCategoryException {

		return getPersistence().findByPrimaryKey(categoryId);
	}

	/**
	 * Returns the access category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the access category
	 * @return the access category, or <code>null</code> if a access category with the primary key could not be found
	 */
	public static AccessCategory fetchByPrimaryKey(long categoryId) {
		return getPersistence().fetchByPrimaryKey(categoryId);
	}

	/**
	 * Returns all the access categories.
	 *
	 * @return the access categories
	 */
	public static List<AccessCategory> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<AccessCategory> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<AccessCategory> findAll(
		int start, int end,
		OrderByComparator<AccessCategory> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<AccessCategory> findAll(
		int start, int end, OrderByComparator<AccessCategory> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the access categories from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of access categories.
	 *
	 * @return the number of access categories
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AccessCategoryPersistence getPersistence() {
		return _persistence;
	}

	private static volatile AccessCategoryPersistence _persistence;

}