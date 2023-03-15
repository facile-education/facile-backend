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

package com.weprode.nero.eel.synchronization.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.eel.synchronization.model.ParentSynchro;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the parent synchro service. This utility wraps <code>com.weprode.nero.eel.synchronization.service.persistence.impl.ParentSynchroPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ParentSynchroPersistence
 * @generated
 */
public class ParentSynchroUtil {

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
	public static void clearCache(ParentSynchro parentSynchro) {
		getPersistence().clearCache(parentSynchro);
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
	public static Map<Serializable, ParentSynchro> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ParentSynchro> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ParentSynchro> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ParentSynchro> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ParentSynchro> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ParentSynchro update(ParentSynchro parentSynchro) {
		return getPersistence().update(parentSynchro);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ParentSynchro update(
		ParentSynchro parentSynchro, ServiceContext serviceContext) {

		return getPersistence().update(parentSynchro, serviceContext);
	}

	/**
	 * Returns the parent synchro where schoolId = &#63; or throws a <code>NoSuchParentSynchroException</code> if it could not be found.
	 *
	 * @param schoolId the school ID
	 * @return the matching parent synchro
	 * @throws NoSuchParentSynchroException if a matching parent synchro could not be found
	 */
	public static ParentSynchro findByschoolId(long schoolId)
		throws com.weprode.nero.eel.synchronization.exception.
			NoSuchParentSynchroException {

		return getPersistence().findByschoolId(schoolId);
	}

	/**
	 * Returns the parent synchro where schoolId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param schoolId the school ID
	 * @return the matching parent synchro, or <code>null</code> if a matching parent synchro could not be found
	 */
	public static ParentSynchro fetchByschoolId(long schoolId) {
		return getPersistence().fetchByschoolId(schoolId);
	}

	/**
	 * Returns the parent synchro where schoolId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param schoolId the school ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching parent synchro, or <code>null</code> if a matching parent synchro could not be found
	 */
	public static ParentSynchro fetchByschoolId(
		long schoolId, boolean useFinderCache) {

		return getPersistence().fetchByschoolId(schoolId, useFinderCache);
	}

	/**
	 * Removes the parent synchro where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @return the parent synchro that was removed
	 */
	public static ParentSynchro removeByschoolId(long schoolId)
		throws com.weprode.nero.eel.synchronization.exception.
			NoSuchParentSynchroException {

		return getPersistence().removeByschoolId(schoolId);
	}

	/**
	 * Returns the number of parent synchros where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching parent synchros
	 */
	public static int countByschoolId(long schoolId) {
		return getPersistence().countByschoolId(schoolId);
	}

	/**
	 * Caches the parent synchro in the entity cache if it is enabled.
	 *
	 * @param parentSynchro the parent synchro
	 */
	public static void cacheResult(ParentSynchro parentSynchro) {
		getPersistence().cacheResult(parentSynchro);
	}

	/**
	 * Caches the parent synchros in the entity cache if it is enabled.
	 *
	 * @param parentSynchros the parent synchros
	 */
	public static void cacheResult(List<ParentSynchro> parentSynchros) {
		getPersistence().cacheResult(parentSynchros);
	}

	/**
	 * Creates a new parent synchro with the primary key. Does not add the parent synchro to the database.
	 *
	 * @param schoolId the primary key for the new parent synchro
	 * @return the new parent synchro
	 */
	public static ParentSynchro create(long schoolId) {
		return getPersistence().create(schoolId);
	}

	/**
	 * Removes the parent synchro with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param schoolId the primary key of the parent synchro
	 * @return the parent synchro that was removed
	 * @throws NoSuchParentSynchroException if a parent synchro with the primary key could not be found
	 */
	public static ParentSynchro remove(long schoolId)
		throws com.weprode.nero.eel.synchronization.exception.
			NoSuchParentSynchroException {

		return getPersistence().remove(schoolId);
	}

	public static ParentSynchro updateImpl(ParentSynchro parentSynchro) {
		return getPersistence().updateImpl(parentSynchro);
	}

	/**
	 * Returns the parent synchro with the primary key or throws a <code>NoSuchParentSynchroException</code> if it could not be found.
	 *
	 * @param schoolId the primary key of the parent synchro
	 * @return the parent synchro
	 * @throws NoSuchParentSynchroException if a parent synchro with the primary key could not be found
	 */
	public static ParentSynchro findByPrimaryKey(long schoolId)
		throws com.weprode.nero.eel.synchronization.exception.
			NoSuchParentSynchroException {

		return getPersistence().findByPrimaryKey(schoolId);
	}

	/**
	 * Returns the parent synchro with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param schoolId the primary key of the parent synchro
	 * @return the parent synchro, or <code>null</code> if a parent synchro with the primary key could not be found
	 */
	public static ParentSynchro fetchByPrimaryKey(long schoolId) {
		return getPersistence().fetchByPrimaryKey(schoolId);
	}

	/**
	 * Returns all the parent synchros.
	 *
	 * @return the parent synchros
	 */
	public static List<ParentSynchro> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the parent synchros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParentSynchroModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of parent synchros
	 * @param end the upper bound of the range of parent synchros (not inclusive)
	 * @return the range of parent synchros
	 */
	public static List<ParentSynchro> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the parent synchros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParentSynchroModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of parent synchros
	 * @param end the upper bound of the range of parent synchros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of parent synchros
	 */
	public static List<ParentSynchro> findAll(
		int start, int end,
		OrderByComparator<ParentSynchro> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the parent synchros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParentSynchroModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of parent synchros
	 * @param end the upper bound of the range of parent synchros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of parent synchros
	 */
	public static List<ParentSynchro> findAll(
		int start, int end, OrderByComparator<ParentSynchro> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the parent synchros from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of parent synchros.
	 *
	 * @return the number of parent synchros
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ParentSynchroPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ParentSynchroPersistence _persistence;

}