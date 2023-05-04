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

package com.weprode.nero.about.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.about.model.EntVersion;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the ent version service. This utility wraps <code>com.weprode.nero.about.service.persistence.impl.EntVersionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntVersionPersistence
 * @generated
 */
public class EntVersionUtil {

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
	public static void clearCache(EntVersion entVersion) {
		getPersistence().clearCache(entVersion);
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
	public static Map<Serializable, EntVersion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EntVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EntVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EntVersion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EntVersion> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EntVersion update(EntVersion entVersion) {
		return getPersistence().update(entVersion);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EntVersion update(
		EntVersion entVersion, ServiceContext serviceContext) {

		return getPersistence().update(entVersion, serviceContext);
	}

	/**
	 * Returns the ent version where isLast = &#63; or throws a <code>NoSuchEntVersionException</code> if it could not be found.
	 *
	 * @param isLast the is last
	 * @return the matching ent version
	 * @throws NoSuchEntVersionException if a matching ent version could not be found
	 */
	public static EntVersion findByisLast(boolean isLast)
		throws com.weprode.nero.about.exception.NoSuchEntVersionException {

		return getPersistence().findByisLast(isLast);
	}

	/**
	 * Returns the ent version where isLast = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param isLast the is last
	 * @return the matching ent version, or <code>null</code> if a matching ent version could not be found
	 */
	public static EntVersion fetchByisLast(boolean isLast) {
		return getPersistence().fetchByisLast(isLast);
	}

	/**
	 * Returns the ent version where isLast = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param isLast the is last
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ent version, or <code>null</code> if a matching ent version could not be found
	 */
	public static EntVersion fetchByisLast(
		boolean isLast, boolean useFinderCache) {

		return getPersistence().fetchByisLast(isLast, useFinderCache);
	}

	/**
	 * Removes the ent version where isLast = &#63; from the database.
	 *
	 * @param isLast the is last
	 * @return the ent version that was removed
	 */
	public static EntVersion removeByisLast(boolean isLast)
		throws com.weprode.nero.about.exception.NoSuchEntVersionException {

		return getPersistence().removeByisLast(isLast);
	}

	/**
	 * Returns the number of ent versions where isLast = &#63;.
	 *
	 * @param isLast the is last
	 * @return the number of matching ent versions
	 */
	public static int countByisLast(boolean isLast) {
		return getPersistence().countByisLast(isLast);
	}

	/**
	 * Caches the ent version in the entity cache if it is enabled.
	 *
	 * @param entVersion the ent version
	 */
	public static void cacheResult(EntVersion entVersion) {
		getPersistence().cacheResult(entVersion);
	}

	/**
	 * Caches the ent versions in the entity cache if it is enabled.
	 *
	 * @param entVersions the ent versions
	 */
	public static void cacheResult(List<EntVersion> entVersions) {
		getPersistence().cacheResult(entVersions);
	}

	/**
	 * Creates a new ent version with the primary key. Does not add the ent version to the database.
	 *
	 * @param entVersionId the primary key for the new ent version
	 * @return the new ent version
	 */
	public static EntVersion create(long entVersionId) {
		return getPersistence().create(entVersionId);
	}

	/**
	 * Removes the ent version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entVersionId the primary key of the ent version
	 * @return the ent version that was removed
	 * @throws NoSuchEntVersionException if a ent version with the primary key could not be found
	 */
	public static EntVersion remove(long entVersionId)
		throws com.weprode.nero.about.exception.NoSuchEntVersionException {

		return getPersistence().remove(entVersionId);
	}

	public static EntVersion updateImpl(EntVersion entVersion) {
		return getPersistence().updateImpl(entVersion);
	}

	/**
	 * Returns the ent version with the primary key or throws a <code>NoSuchEntVersionException</code> if it could not be found.
	 *
	 * @param entVersionId the primary key of the ent version
	 * @return the ent version
	 * @throws NoSuchEntVersionException if a ent version with the primary key could not be found
	 */
	public static EntVersion findByPrimaryKey(long entVersionId)
		throws com.weprode.nero.about.exception.NoSuchEntVersionException {

		return getPersistence().findByPrimaryKey(entVersionId);
	}

	/**
	 * Returns the ent version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entVersionId the primary key of the ent version
	 * @return the ent version, or <code>null</code> if a ent version with the primary key could not be found
	 */
	public static EntVersion fetchByPrimaryKey(long entVersionId) {
		return getPersistence().fetchByPrimaryKey(entVersionId);
	}

	/**
	 * Returns all the ent versions.
	 *
	 * @return the ent versions
	 */
	public static List<EntVersion> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the ent versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent versions
	 * @param end the upper bound of the range of ent versions (not inclusive)
	 * @return the range of ent versions
	 */
	public static List<EntVersion> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the ent versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent versions
	 * @param end the upper bound of the range of ent versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ent versions
	 */
	public static List<EntVersion> findAll(
		int start, int end, OrderByComparator<EntVersion> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ent versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntVersionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ent versions
	 * @param end the upper bound of the range of ent versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ent versions
	 */
	public static List<EntVersion> findAll(
		int start, int end, OrderByComparator<EntVersion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the ent versions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of ent versions.
	 *
	 * @return the number of ent versions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EntVersionPersistence getPersistence() {
		return _persistence;
	}

	private static volatile EntVersionPersistence _persistence;

}