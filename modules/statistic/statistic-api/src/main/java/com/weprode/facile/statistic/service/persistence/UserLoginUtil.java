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

package com.weprode.facile.statistic.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.statistic.model.UserLogin;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the user login service. This utility wraps <code>com.weprode.facile.statistic.service.persistence.impl.UserLoginPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLoginPersistence
 * @generated
 */
public class UserLoginUtil {

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
	public static void clearCache(UserLogin userLogin) {
		getPersistence().clearCache(userLogin);
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
	public static Map<Serializable, UserLogin> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserLogin> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserLogin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserLogin> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserLogin> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserLogin update(UserLogin userLogin) {
		return getPersistence().update(userLogin);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserLogin update(
		UserLogin userLogin, ServiceContext serviceContext) {

		return getPersistence().update(userLogin, serviceContext);
	}

	/**
	 * Returns all the user logins where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching user logins
	 */
	public static List<UserLogin> findByuserId(long userId) {
		return getPersistence().findByuserId(userId);
	}

	/**
	 * Returns a range of all the user logins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @return the range of matching user logins
	 */
	public static List<UserLogin> findByuserId(
		long userId, int start, int end) {

		return getPersistence().findByuserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the user logins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user logins
	 */
	public static List<UserLogin> findByuserId(
		long userId, int start, int end,
		OrderByComparator<UserLogin> orderByComparator) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user logins where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user logins
	 */
	public static List<UserLogin> findByuserId(
		long userId, int start, int end,
		OrderByComparator<UserLogin> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByuserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first user login in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user login
	 * @throws NoSuchUserLoginException if a matching user login could not be found
	 */
	public static UserLogin findByuserId_First(
			long userId, OrderByComparator<UserLogin> orderByComparator)
		throws com.weprode.facile.statistic.exception.NoSuchUserLoginException {

		return getPersistence().findByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first user login in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user login, or <code>null</code> if a matching user login could not be found
	 */
	public static UserLogin fetchByuserId_First(
		long userId, OrderByComparator<UserLogin> orderByComparator) {

		return getPersistence().fetchByuserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last user login in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user login
	 * @throws NoSuchUserLoginException if a matching user login could not be found
	 */
	public static UserLogin findByuserId_Last(
			long userId, OrderByComparator<UserLogin> orderByComparator)
		throws com.weprode.facile.statistic.exception.NoSuchUserLoginException {

		return getPersistence().findByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last user login in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user login, or <code>null</code> if a matching user login could not be found
	 */
	public static UserLogin fetchByuserId_Last(
		long userId, OrderByComparator<UserLogin> orderByComparator) {

		return getPersistence().fetchByuserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the user logins before and after the current user login in the ordered set where userId = &#63;.
	 *
	 * @param userLoginId the primary key of the current user login
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user login
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	public static UserLogin[] findByuserId_PrevAndNext(
			long userLoginId, long userId,
			OrderByComparator<UserLogin> orderByComparator)
		throws com.weprode.facile.statistic.exception.NoSuchUserLoginException {

		return getPersistence().findByuserId_PrevAndNext(
			userLoginId, userId, orderByComparator);
	}

	/**
	 * Removes all the user logins where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByuserId(long userId) {
		getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of user logins where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user logins
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Returns all the user logins where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @return the matching user logins
	 */
	public static List<UserLogin> findByschoolId_role(long schoolId, int role) {
		return getPersistence().findByschoolId_role(schoolId, role);
	}

	/**
	 * Returns a range of all the user logins where schoolId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @return the range of matching user logins
	 */
	public static List<UserLogin> findByschoolId_role(
		long schoolId, int role, int start, int end) {

		return getPersistence().findByschoolId_role(schoolId, role, start, end);
	}

	/**
	 * Returns an ordered range of all the user logins where schoolId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user logins
	 */
	public static List<UserLogin> findByschoolId_role(
		long schoolId, int role, int start, int end,
		OrderByComparator<UserLogin> orderByComparator) {

		return getPersistence().findByschoolId_role(
			schoolId, role, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user logins where schoolId = &#63; and role = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user logins
	 */
	public static List<UserLogin> findByschoolId_role(
		long schoolId, int role, int start, int end,
		OrderByComparator<UserLogin> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByschoolId_role(
			schoolId, role, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user login
	 * @throws NoSuchUserLoginException if a matching user login could not be found
	 */
	public static UserLogin findByschoolId_role_First(
			long schoolId, int role,
			OrderByComparator<UserLogin> orderByComparator)
		throws com.weprode.facile.statistic.exception.NoSuchUserLoginException {

		return getPersistence().findByschoolId_role_First(
			schoolId, role, orderByComparator);
	}

	/**
	 * Returns the first user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user login, or <code>null</code> if a matching user login could not be found
	 */
	public static UserLogin fetchByschoolId_role_First(
		long schoolId, int role,
		OrderByComparator<UserLogin> orderByComparator) {

		return getPersistence().fetchByschoolId_role_First(
			schoolId, role, orderByComparator);
	}

	/**
	 * Returns the last user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user login
	 * @throws NoSuchUserLoginException if a matching user login could not be found
	 */
	public static UserLogin findByschoolId_role_Last(
			long schoolId, int role,
			OrderByComparator<UserLogin> orderByComparator)
		throws com.weprode.facile.statistic.exception.NoSuchUserLoginException {

		return getPersistence().findByschoolId_role_Last(
			schoolId, role, orderByComparator);
	}

	/**
	 * Returns the last user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user login, or <code>null</code> if a matching user login could not be found
	 */
	public static UserLogin fetchByschoolId_role_Last(
		long schoolId, int role,
		OrderByComparator<UserLogin> orderByComparator) {

		return getPersistence().fetchByschoolId_role_Last(
			schoolId, role, orderByComparator);
	}

	/**
	 * Returns the user logins before and after the current user login in the ordered set where schoolId = &#63; and role = &#63;.
	 *
	 * @param userLoginId the primary key of the current user login
	 * @param schoolId the school ID
	 * @param role the role
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user login
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	public static UserLogin[] findByschoolId_role_PrevAndNext(
			long userLoginId, long schoolId, int role,
			OrderByComparator<UserLogin> orderByComparator)
		throws com.weprode.facile.statistic.exception.NoSuchUserLoginException {

		return getPersistence().findByschoolId_role_PrevAndNext(
			userLoginId, schoolId, role, orderByComparator);
	}

	/**
	 * Removes all the user logins where schoolId = &#63; and role = &#63; from the database.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 */
	public static void removeByschoolId_role(long schoolId, int role) {
		getPersistence().removeByschoolId_role(schoolId, role);
	}

	/**
	 * Returns the number of user logins where schoolId = &#63; and role = &#63;.
	 *
	 * @param schoolId the school ID
	 * @param role the role
	 * @return the number of matching user logins
	 */
	public static int countByschoolId_role(long schoolId, int role) {
		return getPersistence().countByschoolId_role(schoolId, role);
	}

	/**
	 * Caches the user login in the entity cache if it is enabled.
	 *
	 * @param userLogin the user login
	 */
	public static void cacheResult(UserLogin userLogin) {
		getPersistence().cacheResult(userLogin);
	}

	/**
	 * Caches the user logins in the entity cache if it is enabled.
	 *
	 * @param userLogins the user logins
	 */
	public static void cacheResult(List<UserLogin> userLogins) {
		getPersistence().cacheResult(userLogins);
	}

	/**
	 * Creates a new user login with the primary key. Does not add the user login to the database.
	 *
	 * @param userLoginId the primary key for the new user login
	 * @return the new user login
	 */
	public static UserLogin create(long userLoginId) {
		return getPersistence().create(userLoginId);
	}

	/**
	 * Removes the user login with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userLoginId the primary key of the user login
	 * @return the user login that was removed
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	public static UserLogin remove(long userLoginId)
		throws com.weprode.facile.statistic.exception.NoSuchUserLoginException {

		return getPersistence().remove(userLoginId);
	}

	public static UserLogin updateImpl(UserLogin userLogin) {
		return getPersistence().updateImpl(userLogin);
	}

	/**
	 * Returns the user login with the primary key or throws a <code>NoSuchUserLoginException</code> if it could not be found.
	 *
	 * @param userLoginId the primary key of the user login
	 * @return the user login
	 * @throws NoSuchUserLoginException if a user login with the primary key could not be found
	 */
	public static UserLogin findByPrimaryKey(long userLoginId)
		throws com.weprode.facile.statistic.exception.NoSuchUserLoginException {

		return getPersistence().findByPrimaryKey(userLoginId);
	}

	/**
	 * Returns the user login with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userLoginId the primary key of the user login
	 * @return the user login, or <code>null</code> if a user login with the primary key could not be found
	 */
	public static UserLogin fetchByPrimaryKey(long userLoginId) {
		return getPersistence().fetchByPrimaryKey(userLoginId);
	}

	/**
	 * Returns all the user logins.
	 *
	 * @return the user logins
	 */
	public static List<UserLogin> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the user logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @return the range of user logins
	 */
	public static List<UserLogin> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the user logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user logins
	 */
	public static List<UserLogin> findAll(
		int start, int end, OrderByComparator<UserLogin> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user logins.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserLoginModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user logins
	 * @param end the upper bound of the range of user logins (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user logins
	 */
	public static List<UserLogin> findAll(
		int start, int end, OrderByComparator<UserLogin> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the user logins from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of user logins.
	 *
	 * @return the number of user logins
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserLoginPersistence getPersistence() {
		return _persistence;
	}

	private static volatile UserLoginPersistence _persistence;

}