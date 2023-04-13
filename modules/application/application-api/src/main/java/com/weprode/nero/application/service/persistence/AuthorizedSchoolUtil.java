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

package com.weprode.nero.application.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.application.model.AuthorizedSchool;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the authorized school service. This utility wraps <code>com.weprode.nero.application.service.persistence.impl.AuthorizedSchoolPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuthorizedSchoolPersistence
 * @generated
 */
public class AuthorizedSchoolUtil {

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
	public static void clearCache(AuthorizedSchool authorizedSchool) {
		getPersistence().clearCache(authorizedSchool);
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
	public static Map<Serializable, AuthorizedSchool> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AuthorizedSchool> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuthorizedSchool> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuthorizedSchool> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AuthorizedSchool> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AuthorizedSchool update(AuthorizedSchool authorizedSchool) {
		return getPersistence().update(authorizedSchool);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AuthorizedSchool update(
		AuthorizedSchool authorizedSchool, ServiceContext serviceContext) {

		return getPersistence().update(authorizedSchool, serviceContext);
	}

	/**
	 * Returns all the authorized schools where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching authorized schools
	 */
	public static List<AuthorizedSchool> findByapplicationId(
		long applicationId) {

		return getPersistence().findByapplicationId(applicationId);
	}

	/**
	 * Returns a range of all the authorized schools where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @return the range of matching authorized schools
	 */
	public static List<AuthorizedSchool> findByapplicationId(
		long applicationId, int start, int end) {

		return getPersistence().findByapplicationId(applicationId, start, end);
	}

	/**
	 * Returns an ordered range of all the authorized schools where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching authorized schools
	 */
	public static List<AuthorizedSchool> findByapplicationId(
		long applicationId, int start, int end,
		OrderByComparator<AuthorizedSchool> orderByComparator) {

		return getPersistence().findByapplicationId(
			applicationId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the authorized schools where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching authorized schools
	 */
	public static List<AuthorizedSchool> findByapplicationId(
		long applicationId, int start, int end,
		OrderByComparator<AuthorizedSchool> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByapplicationId(
			applicationId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching authorized school
	 * @throws NoSuchAuthorizedSchoolException if a matching authorized school could not be found
	 */
	public static AuthorizedSchool findByapplicationId_First(
			long applicationId,
			OrderByComparator<AuthorizedSchool> orderByComparator)
		throws com.weprode.nero.application.exception.
			NoSuchAuthorizedSchoolException {

		return getPersistence().findByapplicationId_First(
			applicationId, orderByComparator);
	}

	/**
	 * Returns the first authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching authorized school, or <code>null</code> if a matching authorized school could not be found
	 */
	public static AuthorizedSchool fetchByapplicationId_First(
		long applicationId,
		OrderByComparator<AuthorizedSchool> orderByComparator) {

		return getPersistence().fetchByapplicationId_First(
			applicationId, orderByComparator);
	}

	/**
	 * Returns the last authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching authorized school
	 * @throws NoSuchAuthorizedSchoolException if a matching authorized school could not be found
	 */
	public static AuthorizedSchool findByapplicationId_Last(
			long applicationId,
			OrderByComparator<AuthorizedSchool> orderByComparator)
		throws com.weprode.nero.application.exception.
			NoSuchAuthorizedSchoolException {

		return getPersistence().findByapplicationId_Last(
			applicationId, orderByComparator);
	}

	/**
	 * Returns the last authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching authorized school, or <code>null</code> if a matching authorized school could not be found
	 */
	public static AuthorizedSchool fetchByapplicationId_Last(
		long applicationId,
		OrderByComparator<AuthorizedSchool> orderByComparator) {

		return getPersistence().fetchByapplicationId_Last(
			applicationId, orderByComparator);
	}

	/**
	 * Returns the authorized schools before and after the current authorized school in the ordered set where applicationId = &#63;.
	 *
	 * @param authorizedSchoolId the primary key of the current authorized school
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next authorized school
	 * @throws NoSuchAuthorizedSchoolException if a authorized school with the primary key could not be found
	 */
	public static AuthorizedSchool[] findByapplicationId_PrevAndNext(
			long authorizedSchoolId, long applicationId,
			OrderByComparator<AuthorizedSchool> orderByComparator)
		throws com.weprode.nero.application.exception.
			NoSuchAuthorizedSchoolException {

		return getPersistence().findByapplicationId_PrevAndNext(
			authorizedSchoolId, applicationId, orderByComparator);
	}

	/**
	 * Removes all the authorized schools where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 */
	public static void removeByapplicationId(long applicationId) {
		getPersistence().removeByapplicationId(applicationId);
	}

	/**
	 * Returns the number of authorized schools where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching authorized schools
	 */
	public static int countByapplicationId(long applicationId) {
		return getPersistence().countByapplicationId(applicationId);
	}

	/**
	 * Returns the authorized school where applicationId = &#63; and schoolId = &#63; or throws a <code>NoSuchAuthorizedSchoolException</code> if it could not be found.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching authorized school
	 * @throws NoSuchAuthorizedSchoolException if a matching authorized school could not be found
	 */
	public static AuthorizedSchool findByapplicationId_schoolId(
			long applicationId, long schoolId)
		throws com.weprode.nero.application.exception.
			NoSuchAuthorizedSchoolException {

		return getPersistence().findByapplicationId_schoolId(
			applicationId, schoolId);
	}

	/**
	 * Returns the authorized school where applicationId = &#63; and schoolId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the matching authorized school, or <code>null</code> if a matching authorized school could not be found
	 */
	public static AuthorizedSchool fetchByapplicationId_schoolId(
		long applicationId, long schoolId) {

		return getPersistence().fetchByapplicationId_schoolId(
			applicationId, schoolId);
	}

	/**
	 * Returns the authorized school where applicationId = &#63; and schoolId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching authorized school, or <code>null</code> if a matching authorized school could not be found
	 */
	public static AuthorizedSchool fetchByapplicationId_schoolId(
		long applicationId, long schoolId, boolean useFinderCache) {

		return getPersistence().fetchByapplicationId_schoolId(
			applicationId, schoolId, useFinderCache);
	}

	/**
	 * Removes the authorized school where applicationId = &#63; and schoolId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the authorized school that was removed
	 */
	public static AuthorizedSchool removeByapplicationId_schoolId(
			long applicationId, long schoolId)
		throws com.weprode.nero.application.exception.
			NoSuchAuthorizedSchoolException {

		return getPersistence().removeByapplicationId_schoolId(
			applicationId, schoolId);
	}

	/**
	 * Returns the number of authorized schools where applicationId = &#63; and schoolId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param schoolId the school ID
	 * @return the number of matching authorized schools
	 */
	public static int countByapplicationId_schoolId(
		long applicationId, long schoolId) {

		return getPersistence().countByapplicationId_schoolId(
			applicationId, schoolId);
	}

	/**
	 * Caches the authorized school in the entity cache if it is enabled.
	 *
	 * @param authorizedSchool the authorized school
	 */
	public static void cacheResult(AuthorizedSchool authorizedSchool) {
		getPersistence().cacheResult(authorizedSchool);
	}

	/**
	 * Caches the authorized schools in the entity cache if it is enabled.
	 *
	 * @param authorizedSchools the authorized schools
	 */
	public static void cacheResult(List<AuthorizedSchool> authorizedSchools) {
		getPersistence().cacheResult(authorizedSchools);
	}

	/**
	 * Creates a new authorized school with the primary key. Does not add the authorized school to the database.
	 *
	 * @param authorizedSchoolId the primary key for the new authorized school
	 * @return the new authorized school
	 */
	public static AuthorizedSchool create(long authorizedSchoolId) {
		return getPersistence().create(authorizedSchoolId);
	}

	/**
	 * Removes the authorized school with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param authorizedSchoolId the primary key of the authorized school
	 * @return the authorized school that was removed
	 * @throws NoSuchAuthorizedSchoolException if a authorized school with the primary key could not be found
	 */
	public static AuthorizedSchool remove(long authorizedSchoolId)
		throws com.weprode.nero.application.exception.
			NoSuchAuthorizedSchoolException {

		return getPersistence().remove(authorizedSchoolId);
	}

	public static AuthorizedSchool updateImpl(
		AuthorizedSchool authorizedSchool) {

		return getPersistence().updateImpl(authorizedSchool);
	}

	/**
	 * Returns the authorized school with the primary key or throws a <code>NoSuchAuthorizedSchoolException</code> if it could not be found.
	 *
	 * @param authorizedSchoolId the primary key of the authorized school
	 * @return the authorized school
	 * @throws NoSuchAuthorizedSchoolException if a authorized school with the primary key could not be found
	 */
	public static AuthorizedSchool findByPrimaryKey(long authorizedSchoolId)
		throws com.weprode.nero.application.exception.
			NoSuchAuthorizedSchoolException {

		return getPersistence().findByPrimaryKey(authorizedSchoolId);
	}

	/**
	 * Returns the authorized school with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param authorizedSchoolId the primary key of the authorized school
	 * @return the authorized school, or <code>null</code> if a authorized school with the primary key could not be found
	 */
	public static AuthorizedSchool fetchByPrimaryKey(long authorizedSchoolId) {
		return getPersistence().fetchByPrimaryKey(authorizedSchoolId);
	}

	/**
	 * Returns all the authorized schools.
	 *
	 * @return the authorized schools
	 */
	public static List<AuthorizedSchool> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the authorized schools.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @return the range of authorized schools
	 */
	public static List<AuthorizedSchool> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the authorized schools.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of authorized schools
	 */
	public static List<AuthorizedSchool> findAll(
		int start, int end,
		OrderByComparator<AuthorizedSchool> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the authorized schools.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthorizedSchoolModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authorized schools
	 * @param end the upper bound of the range of authorized schools (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of authorized schools
	 */
	public static List<AuthorizedSchool> findAll(
		int start, int end,
		OrderByComparator<AuthorizedSchool> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the authorized schools from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of authorized schools.
	 *
	 * @return the number of authorized schools
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AuthorizedSchoolPersistence getPersistence() {
		return _persistence;
	}

	private static volatile AuthorizedSchoolPersistence _persistence;

}