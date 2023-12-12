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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.user.model.Affectation;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the affectation service. This utility wraps <code>com.weprode.facile.user.service.persistence.impl.AffectationPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AffectationPersistence
 * @generated
 */
public class AffectationUtil {

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
	public static void clearCache(Affectation affectation) {
		getPersistence().clearCache(affectation);
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
	public static Map<Serializable, Affectation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Affectation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Affectation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Affectation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Affectation> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Affectation update(Affectation affectation) {
		return getPersistence().update(affectation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Affectation update(
		Affectation affectation, ServiceContext serviceContext) {

		return getPersistence().update(affectation, serviceContext);
	}

	/**
	 * Returns all the affectations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching affectations
	 */
	public static List<Affectation> findByuserId(long userId) {
		return getPersistence().findByuserId(userId);
	}

	/**
	 * Returns a range of all the affectations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @return the range of matching affectations
	 */
	public static List<Affectation> findByuserId(
		long userId, int start, int end) {

		return getPersistence().findByuserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the affectations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching affectations
	 */
	public static List<Affectation> findByuserId(
		long userId, int start, int end,
		OrderByComparator<Affectation> orderByComparator) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the affectations where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching affectations
	 */
	public static List<Affectation> findByuserId(
		long userId, int start, int end,
		OrderByComparator<Affectation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first affectation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	public static Affectation findByuserId_First(
			long userId, OrderByComparator<Affectation> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchAffectationException {

		return getPersistence().findByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first affectation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	public static Affectation fetchByuserId_First(
		long userId, OrderByComparator<Affectation> orderByComparator) {

		return getPersistence().fetchByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last affectation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	public static Affectation findByuserId_Last(
			long userId, OrderByComparator<Affectation> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchAffectationException {

		return getPersistence().findByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last affectation in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	public static Affectation fetchByuserId_Last(
		long userId, OrderByComparator<Affectation> orderByComparator) {

		return getPersistence().fetchByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the affectations before and after the current affectation in the ordered set where userId = &#63;.
	 *
	 * @param affectationId the primary key of the current affectation
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next affectation
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	public static Affectation[] findByuserId_PrevAndNext(
			long affectationId, long userId,
			OrderByComparator<Affectation> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchAffectationException {

		return getPersistence().findByuserId_PrevAndNext(
			affectationId, userId, orderByComparator);
	}

	/**
	 * Removes all the affectations where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByuserId(long userId) {
		getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of affectations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching affectations
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Returns all the affectations where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the matching affectations
	 */
	public static List<Affectation> findByschoolId(long schoolId) {
		return getPersistence().findByschoolId(schoolId);
	}

	/**
	 * Returns a range of all the affectations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @return the range of matching affectations
	 */
	public static List<Affectation> findByschoolId(
		long schoolId, int start, int end) {

		return getPersistence().findByschoolId(schoolId, start, end);
	}

	/**
	 * Returns an ordered range of all the affectations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching affectations
	 */
	public static List<Affectation> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<Affectation> orderByComparator) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the affectations where schoolId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching affectations
	 */
	public static List<Affectation> findByschoolId(
		long schoolId, int start, int end,
		OrderByComparator<Affectation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByschoolId(
			schoolId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	public static Affectation findByschoolId_First(
			long schoolId, OrderByComparator<Affectation> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchAffectationException {

		return getPersistence().findByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the first affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	public static Affectation fetchByschoolId_First(
		long schoolId, OrderByComparator<Affectation> orderByComparator) {

		return getPersistence().fetchByschoolId_First(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	public static Affectation findByschoolId_Last(
			long schoolId, OrderByComparator<Affectation> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchAffectationException {

		return getPersistence().findByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the last affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	public static Affectation fetchByschoolId_Last(
		long schoolId, OrderByComparator<Affectation> orderByComparator) {

		return getPersistence().fetchByschoolId_Last(
			schoolId, orderByComparator);
	}

	/**
	 * Returns the affectations before and after the current affectation in the ordered set where schoolId = &#63;.
	 *
	 * @param affectationId the primary key of the current affectation
	 * @param schoolId the school ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next affectation
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	public static Affectation[] findByschoolId_PrevAndNext(
			long affectationId, long schoolId,
			OrderByComparator<Affectation> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchAffectationException {

		return getPersistence().findByschoolId_PrevAndNext(
			affectationId, schoolId, orderByComparator);
	}

	/**
	 * Removes all the affectations where schoolId = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 */
	public static void removeByschoolId(long schoolId) {
		getPersistence().removeByschoolId(schoolId);
	}

	/**
	 * Returns the number of affectations where schoolId = &#63;.
	 *
	 * @param schoolId the school ID
	 * @return the number of matching affectations
	 */
	public static int countByschoolId(long schoolId) {
		return getPersistence().countByschoolId(schoolId);
	}

	/**
	 * Returns all the affectations where userId = &#63; and orgId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orgId the org ID
	 * @return the matching affectations
	 */
	public static List<Affectation> findByuserIdOrgId(long userId, long orgId) {
		return getPersistence().findByuserIdOrgId(userId, orgId);
	}

	/**
	 * Returns a range of all the affectations where userId = &#63; and orgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orgId the org ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @return the range of matching affectations
	 */
	public static List<Affectation> findByuserIdOrgId(
		long userId, long orgId, int start, int end) {

		return getPersistence().findByuserIdOrgId(userId, orgId, start, end);
	}

	/**
	 * Returns an ordered range of all the affectations where userId = &#63; and orgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orgId the org ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching affectations
	 */
	public static List<Affectation> findByuserIdOrgId(
		long userId, long orgId, int start, int end,
		OrderByComparator<Affectation> orderByComparator) {

		return getPersistence().findByuserIdOrgId(
			userId, orgId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the affectations where userId = &#63; and orgId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orgId the org ID
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching affectations
	 */
	public static List<Affectation> findByuserIdOrgId(
		long userId, long orgId, int start, int end,
		OrderByComparator<Affectation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserIdOrgId(
			userId, orgId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first affectation in the ordered set where userId = &#63; and orgId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orgId the org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	public static Affectation findByuserIdOrgId_First(
			long userId, long orgId,
			OrderByComparator<Affectation> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchAffectationException {

		return getPersistence().findByuserIdOrgId_First(
			userId, orgId, orderByComparator);
	}

	/**
	 * Returns the first affectation in the ordered set where userId = &#63; and orgId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orgId the org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	public static Affectation fetchByuserIdOrgId_First(
		long userId, long orgId,
		OrderByComparator<Affectation> orderByComparator) {

		return getPersistence().fetchByuserIdOrgId_First(
			userId, orgId, orderByComparator);
	}

	/**
	 * Returns the last affectation in the ordered set where userId = &#63; and orgId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orgId the org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation
	 * @throws NoSuchAffectationException if a matching affectation could not be found
	 */
	public static Affectation findByuserIdOrgId_Last(
			long userId, long orgId,
			OrderByComparator<Affectation> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchAffectationException {

		return getPersistence().findByuserIdOrgId_Last(
			userId, orgId, orderByComparator);
	}

	/**
	 * Returns the last affectation in the ordered set where userId = &#63; and orgId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orgId the org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching affectation, or <code>null</code> if a matching affectation could not be found
	 */
	public static Affectation fetchByuserIdOrgId_Last(
		long userId, long orgId,
		OrderByComparator<Affectation> orderByComparator) {

		return getPersistence().fetchByuserIdOrgId_Last(
			userId, orgId, orderByComparator);
	}

	/**
	 * Returns the affectations before and after the current affectation in the ordered set where userId = &#63; and orgId = &#63;.
	 *
	 * @param affectationId the primary key of the current affectation
	 * @param userId the user ID
	 * @param orgId the org ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next affectation
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	public static Affectation[] findByuserIdOrgId_PrevAndNext(
			long affectationId, long userId, long orgId,
			OrderByComparator<Affectation> orderByComparator)
		throws com.weprode.facile.user.exception.NoSuchAffectationException {

		return getPersistence().findByuserIdOrgId_PrevAndNext(
			affectationId, userId, orgId, orderByComparator);
	}

	/**
	 * Removes all the affectations where userId = &#63; and orgId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param orgId the org ID
	 */
	public static void removeByuserIdOrgId(long userId, long orgId) {
		getPersistence().removeByuserIdOrgId(userId, orgId);
	}

	/**
	 * Returns the number of affectations where userId = &#63; and orgId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orgId the org ID
	 * @return the number of matching affectations
	 */
	public static int countByuserIdOrgId(long userId, long orgId) {
		return getPersistence().countByuserIdOrgId(userId, orgId);
	}

	/**
	 * Caches the affectation in the entity cache if it is enabled.
	 *
	 * @param affectation the affectation
	 */
	public static void cacheResult(Affectation affectation) {
		getPersistence().cacheResult(affectation);
	}

	/**
	 * Caches the affectations in the entity cache if it is enabled.
	 *
	 * @param affectations the affectations
	 */
	public static void cacheResult(List<Affectation> affectations) {
		getPersistence().cacheResult(affectations);
	}

	/**
	 * Creates a new affectation with the primary key. Does not add the affectation to the database.
	 *
	 * @param affectationId the primary key for the new affectation
	 * @return the new affectation
	 */
	public static Affectation create(long affectationId) {
		return getPersistence().create(affectationId);
	}

	/**
	 * Removes the affectation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param affectationId the primary key of the affectation
	 * @return the affectation that was removed
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	public static Affectation remove(long affectationId)
		throws com.weprode.facile.user.exception.NoSuchAffectationException {

		return getPersistence().remove(affectationId);
	}

	public static Affectation updateImpl(Affectation affectation) {
		return getPersistence().updateImpl(affectation);
	}

	/**
	 * Returns the affectation with the primary key or throws a <code>NoSuchAffectationException</code> if it could not be found.
	 *
	 * @param affectationId the primary key of the affectation
	 * @return the affectation
	 * @throws NoSuchAffectationException if a affectation with the primary key could not be found
	 */
	public static Affectation findByPrimaryKey(long affectationId)
		throws com.weprode.facile.user.exception.NoSuchAffectationException {

		return getPersistence().findByPrimaryKey(affectationId);
	}

	/**
	 * Returns the affectation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param affectationId the primary key of the affectation
	 * @return the affectation, or <code>null</code> if a affectation with the primary key could not be found
	 */
	public static Affectation fetchByPrimaryKey(long affectationId) {
		return getPersistence().fetchByPrimaryKey(affectationId);
	}

	/**
	 * Returns all the affectations.
	 *
	 * @return the affectations
	 */
	public static List<Affectation> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the affectations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @return the range of affectations
	 */
	public static List<Affectation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the affectations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of affectations
	 */
	public static List<Affectation> findAll(
		int start, int end, OrderByComparator<Affectation> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the affectations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AffectationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of affectations
	 * @param end the upper bound of the range of affectations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of affectations
	 */
	public static List<Affectation> findAll(
		int start, int end, OrderByComparator<Affectation> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the affectations from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of affectations.
	 *
	 * @return the number of affectations
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AffectationPersistence getPersistence() {
		return _persistence;
	}

	private static volatile AffectationPersistence _persistence;

}