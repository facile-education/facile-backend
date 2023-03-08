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

package com.weprode.nero.user.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.user.model.UserRelationship;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the user relationship service. This utility wraps <code>com.weprode.nero.user.service.persistence.impl.UserRelationshipPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserRelationshipPersistence
 * @generated
 */
public class UserRelationshipUtil {

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
	public static void clearCache(UserRelationship userRelationship) {
		getPersistence().clearCache(userRelationship);
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
	public static Map<Serializable, UserRelationship> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserRelationship> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserRelationship> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserRelationship> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserRelationship> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserRelationship update(UserRelationship userRelationship) {
		return getPersistence().update(userRelationship);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserRelationship update(
		UserRelationship userRelationship, ServiceContext serviceContext) {

		return getPersistence().update(userRelationship, serviceContext);
	}

	/**
	 * Returns all the user relationships where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @return the matching user relationships
	 */
	public static List<UserRelationship> findBychildUserId(long childUserId) {
		return getPersistence().findBychildUserId(childUserId);
	}

	/**
	 * Returns a range of all the user relationships where childUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param childUserId the child user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @return the range of matching user relationships
	 */
	public static List<UserRelationship> findBychildUserId(
		long childUserId, int start, int end) {

		return getPersistence().findBychildUserId(childUserId, start, end);
	}

	/**
	 * Returns an ordered range of all the user relationships where childUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param childUserId the child user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user relationships
	 */
	public static List<UserRelationship> findBychildUserId(
		long childUserId, int start, int end,
		OrderByComparator<UserRelationship> orderByComparator) {

		return getPersistence().findBychildUserId(
			childUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user relationships where childUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param childUserId the child user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user relationships
	 */
	public static List<UserRelationship> findBychildUserId(
		long childUserId, int start, int end,
		OrderByComparator<UserRelationship> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBychildUserId(
			childUserId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user relationship
	 * @throws NoSuchRelationshipException if a matching user relationship could not be found
	 */
	public static UserRelationship findBychildUserId_First(
			long childUserId,
			OrderByComparator<UserRelationship> orderByComparator)
		throws com.weprode.nero.user.exception.NoSuchRelationshipException {

		return getPersistence().findBychildUserId_First(
			childUserId, orderByComparator);
	}

	/**
	 * Returns the first user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user relationship, or <code>null</code> if a matching user relationship could not be found
	 */
	public static UserRelationship fetchBychildUserId_First(
		long childUserId,
		OrderByComparator<UserRelationship> orderByComparator) {

		return getPersistence().fetchBychildUserId_First(
			childUserId, orderByComparator);
	}

	/**
	 * Returns the last user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user relationship
	 * @throws NoSuchRelationshipException if a matching user relationship could not be found
	 */
	public static UserRelationship findBychildUserId_Last(
			long childUserId,
			OrderByComparator<UserRelationship> orderByComparator)
		throws com.weprode.nero.user.exception.NoSuchRelationshipException {

		return getPersistence().findBychildUserId_Last(
			childUserId, orderByComparator);
	}

	/**
	 * Returns the last user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user relationship, or <code>null</code> if a matching user relationship could not be found
	 */
	public static UserRelationship fetchBychildUserId_Last(
		long childUserId,
		OrderByComparator<UserRelationship> orderByComparator) {

		return getPersistence().fetchBychildUserId_Last(
			childUserId, orderByComparator);
	}

	/**
	 * Returns the user relationships before and after the current user relationship in the ordered set where childUserId = &#63;.
	 *
	 * @param userRelationshipPK the primary key of the current user relationship
	 * @param childUserId the child user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user relationship
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	public static UserRelationship[] findBychildUserId_PrevAndNext(
			UserRelationshipPK userRelationshipPK, long childUserId,
			OrderByComparator<UserRelationship> orderByComparator)
		throws com.weprode.nero.user.exception.NoSuchRelationshipException {

		return getPersistence().findBychildUserId_PrevAndNext(
			userRelationshipPK, childUserId, orderByComparator);
	}

	/**
	 * Removes all the user relationships where childUserId = &#63; from the database.
	 *
	 * @param childUserId the child user ID
	 */
	public static void removeBychildUserId(long childUserId) {
		getPersistence().removeBychildUserId(childUserId);
	}

	/**
	 * Returns the number of user relationships where childUserId = &#63;.
	 *
	 * @param childUserId the child user ID
	 * @return the number of matching user relationships
	 */
	public static int countBychildUserId(long childUserId) {
		return getPersistence().countBychildUserId(childUserId);
	}

	/**
	 * Returns all the user relationships where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @return the matching user relationships
	 */
	public static List<UserRelationship> findByparentUserId(long parentUserId) {
		return getPersistence().findByparentUserId(parentUserId);
	}

	/**
	 * Returns a range of all the user relationships where parentUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param parentUserId the parent user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @return the range of matching user relationships
	 */
	public static List<UserRelationship> findByparentUserId(
		long parentUserId, int start, int end) {

		return getPersistence().findByparentUserId(parentUserId, start, end);
	}

	/**
	 * Returns an ordered range of all the user relationships where parentUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param parentUserId the parent user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user relationships
	 */
	public static List<UserRelationship> findByparentUserId(
		long parentUserId, int start, int end,
		OrderByComparator<UserRelationship> orderByComparator) {

		return getPersistence().findByparentUserId(
			parentUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user relationships where parentUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param parentUserId the parent user ID
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user relationships
	 */
	public static List<UserRelationship> findByparentUserId(
		long parentUserId, int start, int end,
		OrderByComparator<UserRelationship> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByparentUserId(
			parentUserId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user relationship
	 * @throws NoSuchRelationshipException if a matching user relationship could not be found
	 */
	public static UserRelationship findByparentUserId_First(
			long parentUserId,
			OrderByComparator<UserRelationship> orderByComparator)
		throws com.weprode.nero.user.exception.NoSuchRelationshipException {

		return getPersistence().findByparentUserId_First(
			parentUserId, orderByComparator);
	}

	/**
	 * Returns the first user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user relationship, or <code>null</code> if a matching user relationship could not be found
	 */
	public static UserRelationship fetchByparentUserId_First(
		long parentUserId,
		OrderByComparator<UserRelationship> orderByComparator) {

		return getPersistence().fetchByparentUserId_First(
			parentUserId, orderByComparator);
	}

	/**
	 * Returns the last user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user relationship
	 * @throws NoSuchRelationshipException if a matching user relationship could not be found
	 */
	public static UserRelationship findByparentUserId_Last(
			long parentUserId,
			OrderByComparator<UserRelationship> orderByComparator)
		throws com.weprode.nero.user.exception.NoSuchRelationshipException {

		return getPersistence().findByparentUserId_Last(
			parentUserId, orderByComparator);
	}

	/**
	 * Returns the last user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user relationship, or <code>null</code> if a matching user relationship could not be found
	 */
	public static UserRelationship fetchByparentUserId_Last(
		long parentUserId,
		OrderByComparator<UserRelationship> orderByComparator) {

		return getPersistence().fetchByparentUserId_Last(
			parentUserId, orderByComparator);
	}

	/**
	 * Returns the user relationships before and after the current user relationship in the ordered set where parentUserId = &#63;.
	 *
	 * @param userRelationshipPK the primary key of the current user relationship
	 * @param parentUserId the parent user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user relationship
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	public static UserRelationship[] findByparentUserId_PrevAndNext(
			UserRelationshipPK userRelationshipPK, long parentUserId,
			OrderByComparator<UserRelationship> orderByComparator)
		throws com.weprode.nero.user.exception.NoSuchRelationshipException {

		return getPersistence().findByparentUserId_PrevAndNext(
			userRelationshipPK, parentUserId, orderByComparator);
	}

	/**
	 * Removes all the user relationships where parentUserId = &#63; from the database.
	 *
	 * @param parentUserId the parent user ID
	 */
	public static void removeByparentUserId(long parentUserId) {
		getPersistence().removeByparentUserId(parentUserId);
	}

	/**
	 * Returns the number of user relationships where parentUserId = &#63;.
	 *
	 * @param parentUserId the parent user ID
	 * @return the number of matching user relationships
	 */
	public static int countByparentUserId(long parentUserId) {
		return getPersistence().countByparentUserId(parentUserId);
	}

	/**
	 * Caches the user relationship in the entity cache if it is enabled.
	 *
	 * @param userRelationship the user relationship
	 */
	public static void cacheResult(UserRelationship userRelationship) {
		getPersistence().cacheResult(userRelationship);
	}

	/**
	 * Caches the user relationships in the entity cache if it is enabled.
	 *
	 * @param userRelationships the user relationships
	 */
	public static void cacheResult(List<UserRelationship> userRelationships) {
		getPersistence().cacheResult(userRelationships);
	}

	/**
	 * Creates a new user relationship with the primary key. Does not add the user relationship to the database.
	 *
	 * @param userRelationshipPK the primary key for the new user relationship
	 * @return the new user relationship
	 */
	public static UserRelationship create(
		UserRelationshipPK userRelationshipPK) {

		return getPersistence().create(userRelationshipPK);
	}

	/**
	 * Removes the user relationship with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship that was removed
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	public static UserRelationship remove(UserRelationshipPK userRelationshipPK)
		throws com.weprode.nero.user.exception.NoSuchRelationshipException {

		return getPersistence().remove(userRelationshipPK);
	}

	public static UserRelationship updateImpl(
		UserRelationship userRelationship) {

		return getPersistence().updateImpl(userRelationship);
	}

	/**
	 * Returns the user relationship with the primary key or throws a <code>NoSuchRelationshipException</code> if it could not be found.
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship
	 * @throws NoSuchRelationshipException if a user relationship with the primary key could not be found
	 */
	public static UserRelationship findByPrimaryKey(
			UserRelationshipPK userRelationshipPK)
		throws com.weprode.nero.user.exception.NoSuchRelationshipException {

		return getPersistence().findByPrimaryKey(userRelationshipPK);
	}

	/**
	 * Returns the user relationship with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userRelationshipPK the primary key of the user relationship
	 * @return the user relationship, or <code>null</code> if a user relationship with the primary key could not be found
	 */
	public static UserRelationship fetchByPrimaryKey(
		UserRelationshipPK userRelationshipPK) {

		return getPersistence().fetchByPrimaryKey(userRelationshipPK);
	}

	/**
	 * Returns all the user relationships.
	 *
	 * @return the user relationships
	 */
	public static List<UserRelationship> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the user relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @return the range of user relationships
	 */
	public static List<UserRelationship> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the user relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user relationships
	 */
	public static List<UserRelationship> findAll(
		int start, int end,
		OrderByComparator<UserRelationship> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user relationships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserRelationshipModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user relationships
	 * @param end the upper bound of the range of user relationships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user relationships
	 */
	public static List<UserRelationship> findAll(
		int start, int end,
		OrderByComparator<UserRelationship> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the user relationships from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of user relationships.
	 *
	 * @return the number of user relationships
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static UserRelationshipPersistence getPersistence() {
		return _persistence;
	}

	private static volatile UserRelationshipPersistence _persistence;

}