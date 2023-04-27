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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.school.life.model.Renvoi;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the renvoi service. This utility wraps <code>com.weprode.nero.school.life.service.persistence.impl.RenvoiPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RenvoiPersistence
 * @generated
 */
public class RenvoiUtil {

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
	public static void clearCache(Renvoi renvoi) {
		getPersistence().clearCache(renvoi);
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
	public static Map<Serializable, Renvoi> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Renvoi> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Renvoi> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Renvoi> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Renvoi> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Renvoi update(Renvoi renvoi) {
		return getPersistence().update(renvoi);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Renvoi update(Renvoi renvoi, ServiceContext serviceContext) {
		return getPersistence().update(renvoi, serviceContext);
	}

	/**
	 * Returns all the renvois where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching renvois
	 */
	public static List<Renvoi> findByschoolId(long schoolId) {
		return getPersistence().findByschoolId(schoolId);
	}

	/**
	 * Returns a range of all the renvois where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @return the range of matching renvois
	 */
	public static List<Renvoi> findByschoolId(
		long schoolId, int start, int end) {

		return getPersistence().findByschoolId(schoolId, start, end);
	}

	/**
	 * Returns an ordered range of all the renvois where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching renvois
	 */
	public static List<Renvoi> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<Renvoi> orderByComparator) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the renvois where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching renvois
	 */
	public static List<Renvoi> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<Renvoi> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching renvoi
	 * @throws NoSuchRenvoiException if a matching renvoi could not be found
	 */
	public static Renvoi findByschoolId_First(
			long schoolId, OrderByComparator<Renvoi> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchRenvoiException {

		return getPersistence().findByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the first renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching renvoi, or <code>null</code> if a matching renvoi could not be found
	 */
	public static Renvoi fetchByschoolId_First(
		long schoolId, OrderByComparator<Renvoi> orderByComparator) {

		return getPersistence().fetchByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching renvoi
	 * @throws NoSuchRenvoiException if a matching renvoi could not be found
	 */
	public static Renvoi findByschoolId_Last(
			long schoolId, OrderByComparator<Renvoi> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchRenvoiException {

		return getPersistence().findByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching renvoi, or <code>null</code> if a matching renvoi could not be found
	 */
	public static Renvoi fetchByschoolId_Last(
		long schoolId, OrderByComparator<Renvoi> orderByComparator) {

		return getPersistence().fetchByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the renvois before and after the current renvoi in the ordered set where schoolId = &#63;.
	 *
	 * @param renvoiPK the primary key of the current renvoi
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next renvoi
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	public static Renvoi[] findByschoolId_PrevAndNext(
			RenvoiPK renvoiPK, long schoolId,
			OrderByComparator<Renvoi> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchRenvoiException {

		return getPersistence().findByschoolId_PrevAndNext(
			renvoiPK, schoolId, orderByComparator);
	}

	/**
	 * Removes all the renvois where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public static void removeByschoolId(long schoolId) {
		getPersistence().removeByschoolId(schoolId);
	}

	/**
	 * Returns the number of renvois where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching renvois
	 */
	public static int countByschoolId(long schoolId) {
		return getPersistence().countByschoolId(schoolId);
	}

	/**
	 * Returns all the renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @return the matching renvois
	 */
	public static List<Renvoi> findBysourceTeacherId_status(
		long sourceTeacherId, int status) {

		return getPersistence().findBysourceTeacherId_status(
			sourceTeacherId, status);
	}

	/**
	 * Returns a range of all the renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @return the range of matching renvois
	 */
	public static List<Renvoi> findBysourceTeacherId_status(
		long sourceTeacherId, int status, int start, int end) {

		return getPersistence().findBysourceTeacherId_status(
			sourceTeacherId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching renvois
	 */
	public static List<Renvoi> findBysourceTeacherId_status(
		long sourceTeacherId, int status, int start, int end,
		OrderByComparator<Renvoi> orderByComparator) {

		return getPersistence().findBysourceTeacherId_status(
			sourceTeacherId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching renvois
	 */
	public static List<Renvoi> findBysourceTeacherId_status(
		long sourceTeacherId, int status, int start, int end,
		OrderByComparator<Renvoi> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBysourceTeacherId_status(
			sourceTeacherId, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching renvoi
	 * @throws NoSuchRenvoiException if a matching renvoi could not be found
	 */
	public static Renvoi findBysourceTeacherId_status_First(
			long sourceTeacherId, int status,
			OrderByComparator<Renvoi> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchRenvoiException {

		return getPersistence().findBysourceTeacherId_status_First(
			sourceTeacherId, status, orderByComparator);
	}

	/**
	 * Returns the first renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching renvoi, or <code>null</code> if a matching renvoi could not be found
	 */
	public static Renvoi fetchBysourceTeacherId_status_First(
		long sourceTeacherId, int status,
		OrderByComparator<Renvoi> orderByComparator) {

		return getPersistence().fetchBysourceTeacherId_status_First(
			sourceTeacherId, status, orderByComparator);
	}

	/**
	 * Returns the last renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching renvoi
	 * @throws NoSuchRenvoiException if a matching renvoi could not be found
	 */
	public static Renvoi findBysourceTeacherId_status_Last(
			long sourceTeacherId, int status,
			OrderByComparator<Renvoi> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchRenvoiException {

		return getPersistence().findBysourceTeacherId_status_Last(
			sourceTeacherId, status, orderByComparator);
	}

	/**
	 * Returns the last renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching renvoi, or <code>null</code> if a matching renvoi could not be found
	 */
	public static Renvoi fetchBysourceTeacherId_status_Last(
		long sourceTeacherId, int status,
		OrderByComparator<Renvoi> orderByComparator) {

		return getPersistence().fetchBysourceTeacherId_status_Last(
			sourceTeacherId, status, orderByComparator);
	}

	/**
	 * Returns the renvois before and after the current renvoi in the ordered set where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param renvoiPK the primary key of the current renvoi
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next renvoi
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	public static Renvoi[] findBysourceTeacherId_status_PrevAndNext(
			RenvoiPK renvoiPK, long sourceTeacherId, int status,
			OrderByComparator<Renvoi> orderByComparator)
		throws com.weprode.nero.school.life.exception.NoSuchRenvoiException {

		return getPersistence().findBysourceTeacherId_status_PrevAndNext(
			renvoiPK, sourceTeacherId, status, orderByComparator);
	}

	/**
	 * Removes all the renvois where sourceTeacherId = &#63; and status = &#63; from the database.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 */
	public static void removeBysourceTeacherId_status(
		long sourceTeacherId, int status) {

		getPersistence().removeBysourceTeacherId_status(
			sourceTeacherId, status);
	}

	/**
	 * Returns the number of renvois where sourceTeacherId = &#63; and status = &#63;.
	 *
	 * @param sourceTeacherId the source teacher ID
	 * @param status the status
	 * @return the number of matching renvois
	 */
	public static int countBysourceTeacherId_status(
		long sourceTeacherId, int status) {

		return getPersistence().countBysourceTeacherId_status(
			sourceTeacherId, status);
	}

	/**
	 * Caches the renvoi in the entity cache if it is enabled.
	 *
	 * @param renvoi the renvoi
	 */
	public static void cacheResult(Renvoi renvoi) {
		getPersistence().cacheResult(renvoi);
	}

	/**
	 * Caches the renvois in the entity cache if it is enabled.
	 *
	 * @param renvois the renvois
	 */
	public static void cacheResult(List<Renvoi> renvois) {
		getPersistence().cacheResult(renvois);
	}

	/**
	 * Creates a new renvoi with the primary key. Does not add the renvoi to the database.
	 *
	 * @param renvoiPK the primary key for the new renvoi
	 * @return the new renvoi
	 */
	public static Renvoi create(RenvoiPK renvoiPK) {
		return getPersistence().create(renvoiPK);
	}

	/**
	 * Removes the renvoi with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param renvoiPK the primary key of the renvoi
	 * @return the renvoi that was removed
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	public static Renvoi remove(RenvoiPK renvoiPK)
		throws com.weprode.nero.school.life.exception.NoSuchRenvoiException {

		return getPersistence().remove(renvoiPK);
	}

	public static Renvoi updateImpl(Renvoi renvoi) {
		return getPersistence().updateImpl(renvoi);
	}

	/**
	 * Returns the renvoi with the primary key or throws a <code>NoSuchRenvoiException</code> if it could not be found.
	 *
	 * @param renvoiPK the primary key of the renvoi
	 * @return the renvoi
	 * @throws NoSuchRenvoiException if a renvoi with the primary key could not be found
	 */
	public static Renvoi findByPrimaryKey(RenvoiPK renvoiPK)
		throws com.weprode.nero.school.life.exception.NoSuchRenvoiException {

		return getPersistence().findByPrimaryKey(renvoiPK);
	}

	/**
	 * Returns the renvoi with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param renvoiPK the primary key of the renvoi
	 * @return the renvoi, or <code>null</code> if a renvoi with the primary key could not be found
	 */
	public static Renvoi fetchByPrimaryKey(RenvoiPK renvoiPK) {
		return getPersistence().fetchByPrimaryKey(renvoiPK);
	}

	/**
	 * Returns all the renvois.
	 *
	 * @return the renvois
	 */
	public static List<Renvoi> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the renvois.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @return the range of renvois
	 */
	public static List<Renvoi> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the renvois.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of renvois
	 */
	public static List<Renvoi> findAll(
		int start, int end, OrderByComparator<Renvoi> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the renvois.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RenvoiModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of renvois
	 * @param end the upper bound of the range of renvois (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of renvois
	 */
	public static List<Renvoi> findAll(
		int start, int end, OrderByComparator<Renvoi> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the renvois from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of renvois.
	 *
	 * @return the number of renvois
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static RenvoiPersistence getPersistence() {
		return _persistence;
	}

	private static volatile RenvoiPersistence _persistence;

}