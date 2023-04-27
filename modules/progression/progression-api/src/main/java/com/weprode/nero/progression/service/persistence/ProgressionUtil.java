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

package com.weprode.nero.progression.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.progression.model.Progression;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the progression service. This utility wraps <code>com.weprode.nero.progression.service.persistence.impl.ProgressionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionPersistence
 * @generated
 */
public class ProgressionUtil {

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
	public static void clearCache(Progression progression) {
		getPersistence().clearCache(progression);
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
	public static Map<Serializable, Progression> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Progression> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Progression> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Progression> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Progression> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Progression update(Progression progression) {
		return getPersistence().update(progression);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Progression update(
		Progression progression, ServiceContext serviceContext) {

		return getPersistence().update(progression, serviceContext);
	}

	/**
	 * Returns the progression where progressionId = &#63; or throws a <code>NoSuchProgressionException</code> if it could not be found.
	 *
	 * @param progressionId the progression ID
	 * @return the matching progression
	 * @throws NoSuchProgressionException if a matching progression could not be found
	 */
	public static Progression findByprogressionId(long progressionId)
		throws com.weprode.nero.progression.exception.
			NoSuchProgressionException {

		return getPersistence().findByprogressionId(progressionId);
	}

	/**
	 * Returns the progression where progressionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionId the progression ID
	 * @return the matching progression, or <code>null</code> if a matching progression could not be found
	 */
	public static Progression fetchByprogressionId(long progressionId) {
		return getPersistence().fetchByprogressionId(progressionId);
	}

	/**
	 * Returns the progression where progressionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionId the progression ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression, or <code>null</code> if a matching progression could not be found
	 */
	public static Progression fetchByprogressionId(
		long progressionId, boolean useFinderCache) {

		return getPersistence().fetchByprogressionId(
			progressionId, useFinderCache);
	}

	/**
	 * Removes the progression where progressionId = &#63; from the database.
	 *
	 * @param progressionId the progression ID
	 * @return the progression that was removed
	 */
	public static Progression removeByprogressionId(long progressionId)
		throws com.weprode.nero.progression.exception.
			NoSuchProgressionException {

		return getPersistence().removeByprogressionId(progressionId);
	}

	/**
	 * Returns the number of progressions where progressionId = &#63;.
	 *
	 * @param progressionId the progression ID
	 * @return the number of matching progressions
	 */
	public static int countByprogressionId(long progressionId) {
		return getPersistence().countByprogressionId(progressionId);
	}

	/**
	 * Returns all the progressions where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the matching progressions
	 */
	public static List<Progression> findByteacherId(long teacherId) {
		return getPersistence().findByteacherId(teacherId);
	}

	/**
	 * Returns a range of all the progressions where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @return the range of matching progressions
	 */
	public static List<Progression> findByteacherId(
		long teacherId, int start, int end) {

		return getPersistence().findByteacherId(teacherId, start, end);
	}

	/**
	 * Returns an ordered range of all the progressions where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching progressions
	 */
	public static List<Progression> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<Progression> orderByComparator) {

		return getPersistence().findByteacherId(
			teacherId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the progressions where teacherId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param teacherId the teacher ID
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching progressions
	 */
	public static List<Progression> findByteacherId(
		long teacherId, int start, int end,
		OrderByComparator<Progression> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByteacherId(
			teacherId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first progression in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression
	 * @throws NoSuchProgressionException if a matching progression could not be found
	 */
	public static Progression findByteacherId_First(
			long teacherId, OrderByComparator<Progression> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchProgressionException {

		return getPersistence().findByteacherId_First(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the first progression in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression, or <code>null</code> if a matching progression could not be found
	 */
	public static Progression fetchByteacherId_First(
		long teacherId, OrderByComparator<Progression> orderByComparator) {

		return getPersistence().fetchByteacherId_First(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the last progression in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression
	 * @throws NoSuchProgressionException if a matching progression could not be found
	 */
	public static Progression findByteacherId_Last(
			long teacherId, OrderByComparator<Progression> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchProgressionException {

		return getPersistence().findByteacherId_Last(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the last progression in the ordered set where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression, or <code>null</code> if a matching progression could not be found
	 */
	public static Progression fetchByteacherId_Last(
		long teacherId, OrderByComparator<Progression> orderByComparator) {

		return getPersistence().fetchByteacherId_Last(
			teacherId, orderByComparator);
	}

	/**
	 * Returns the progressions before and after the current progression in the ordered set where teacherId = &#63;.
	 *
	 * @param progressionId the primary key of the current progression
	 * @param teacherId the teacher ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression
	 * @throws NoSuchProgressionException if a progression with the primary key could not be found
	 */
	public static Progression[] findByteacherId_PrevAndNext(
			long progressionId, long teacherId,
			OrderByComparator<Progression> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchProgressionException {

		return getPersistence().findByteacherId_PrevAndNext(
			progressionId, teacherId, orderByComparator);
	}

	/**
	 * Removes all the progressions where teacherId = &#63; from the database.
	 *
	 * @param teacherId the teacher ID
	 */
	public static void removeByteacherId(long teacherId) {
		getPersistence().removeByteacherId(teacherId);
	}

	/**
	 * Returns the number of progressions where teacherId = &#63;.
	 *
	 * @param teacherId the teacher ID
	 * @return the number of matching progressions
	 */
	public static int countByteacherId(long teacherId) {
		return getPersistence().countByteacherId(teacherId);
	}

	/**
	 * Caches the progression in the entity cache if it is enabled.
	 *
	 * @param progression the progression
	 */
	public static void cacheResult(Progression progression) {
		getPersistence().cacheResult(progression);
	}

	/**
	 * Caches the progressions in the entity cache if it is enabled.
	 *
	 * @param progressions the progressions
	 */
	public static void cacheResult(List<Progression> progressions) {
		getPersistence().cacheResult(progressions);
	}

	/**
	 * Creates a new progression with the primary key. Does not add the progression to the database.
	 *
	 * @param progressionId the primary key for the new progression
	 * @return the new progression
	 */
	public static Progression create(long progressionId) {
		return getPersistence().create(progressionId);
	}

	/**
	 * Removes the progression with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param progressionId the primary key of the progression
	 * @return the progression that was removed
	 * @throws NoSuchProgressionException if a progression with the primary key could not be found
	 */
	public static Progression remove(long progressionId)
		throws com.weprode.nero.progression.exception.
			NoSuchProgressionException {

		return getPersistence().remove(progressionId);
	}

	public static Progression updateImpl(Progression progression) {
		return getPersistence().updateImpl(progression);
	}

	/**
	 * Returns the progression with the primary key or throws a <code>NoSuchProgressionException</code> if it could not be found.
	 *
	 * @param progressionId the primary key of the progression
	 * @return the progression
	 * @throws NoSuchProgressionException if a progression with the primary key could not be found
	 */
	public static Progression findByPrimaryKey(long progressionId)
		throws com.weprode.nero.progression.exception.
			NoSuchProgressionException {

		return getPersistence().findByPrimaryKey(progressionId);
	}

	/**
	 * Returns the progression with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param progressionId the primary key of the progression
	 * @return the progression, or <code>null</code> if a progression with the primary key could not be found
	 */
	public static Progression fetchByPrimaryKey(long progressionId) {
		return getPersistence().fetchByPrimaryKey(progressionId);
	}

	/**
	 * Returns all the progressions.
	 *
	 * @return the progressions
	 */
	public static List<Progression> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the progressions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @return the range of progressions
	 */
	public static List<Progression> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the progressions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of progressions
	 */
	public static List<Progression> findAll(
		int start, int end, OrderByComparator<Progression> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the progressions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progressions
	 * @param end the upper bound of the range of progressions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of progressions
	 */
	public static List<Progression> findAll(
		int start, int end, OrderByComparator<Progression> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the progressions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of progressions.
	 *
	 * @return the number of progressions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ProgressionPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ProgressionPersistence _persistence;

}