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

import com.weprode.facile.user.model.UserContact;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the user contact service. This utility wraps <code>com.weprode.facile.user.service.persistence.impl.UserContactPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserContactPersistence
 * @generated
 */
public class UserContactUtil {

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
	public static void clearCache(UserContact userContact) {
		getPersistence().clearCache(userContact);
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
	public static Map<Serializable, UserContact> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserContact> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserContact> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserContact> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserContact> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserContact update(UserContact userContact) {
		return getPersistence().update(userContact);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserContact update(
		UserContact userContact, ServiceContext serviceContext) {

		return getPersistence().update(userContact, serviceContext);
	}

	/**
	 * Returns the user contact where userId = &#63; or throws a <code>NoSuchContactException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user contact
	 * @throws NoSuchContactException if a matching user contact could not be found
	 */
	public static UserContact findByuserId(long userId)
		throws com.weprode.facile.user.exception.NoSuchContactException {

		return getPersistence().findByuserId(userId);
	}

	/**
	 * Returns the user contact where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user contact, or <code>null</code> if a matching user contact could not be found
	 */
	public static UserContact fetchByuserId(long userId) {
		return getPersistence().fetchByuserId(userId);
	}

	/**
	 * Returns the user contact where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching user contact, or <code>null</code> if a matching user contact could not be found
	 */
	public static UserContact fetchByuserId(
		long userId, boolean useFinderCache) {

		return getPersistence().fetchByuserId(userId, useFinderCache);
	}

	/**
	 * Removes the user contact where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the user contact that was removed
	 */
	public static UserContact removeByuserId(long userId)
		throws com.weprode.facile.user.exception.NoSuchContactException {

		return getPersistence().removeByuserId(userId);
	}

	/**
	 * Returns the number of user contacts where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user contacts
	 */
	public static int countByuserId(long userId) {
		return getPersistence().countByuserId(userId);
	}

	/**
	 * Caches the user contact in the entity cache if it is enabled.
	 *
	 * @param userContact the user contact
	 */
	public static void cacheResult(UserContact userContact) {
		getPersistence().cacheResult(userContact);
	}

	/**
	 * Caches the user contacts in the entity cache if it is enabled.
	 *
	 * @param userContacts the user contacts
	 */
	public static void cacheResult(List<UserContact> userContacts) {
		getPersistence().cacheResult(userContacts);
	}

	/**
	 * Creates a new user contact with the primary key. Does not add the user contact to the database.
	 *
	 * @param contactId the primary key for the new user contact
	 * @return the new user contact
	 */
	public static UserContact create(long contactId) {
		return getPersistence().create(contactId);
	}

	/**
	 * Removes the user contact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactId the primary key of the user contact
	 * @return the user contact that was removed
	 * @throws NoSuchContactException if a user contact with the primary key could not be found
	 */
	public static UserContact remove(long contactId)
		throws com.weprode.facile.user.exception.NoSuchContactException {

		return getPersistence().remove(contactId);
	}

	public static UserContact updateImpl(UserContact userContact) {
		return getPersistence().updateImpl(userContact);
	}

	/**
	 * Returns the user contact with the primary key or throws a <code>NoSuchContactException</code> if it could not be found.
	 *
	 * @param contactId the primary key of the user contact
	 * @return the user contact
	 * @throws NoSuchContactException if a user contact with the primary key could not be found
	 */
	public static UserContact findByPrimaryKey(long contactId)
		throws com.weprode.facile.user.exception.NoSuchContactException {

		return getPersistence().findByPrimaryKey(contactId);
	}

	/**
	 * Returns the user contact with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactId the primary key of the user contact
	 * @return the user contact, or <code>null</code> if a user contact with the primary key could not be found
	 */
	public static UserContact fetchByPrimaryKey(long contactId) {
		return getPersistence().fetchByPrimaryKey(contactId);
	}

	/**
	 * Returns all the user contacts.
	 *
	 * @return the user contacts
	 */
	public static List<UserContact> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the user contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserContactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user contacts
	 * @param end the upper bound of the range of user contacts (not inclusive)
	 * @return the range of user contacts
	 */
	public static List<UserContact> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the user contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserContactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user contacts
	 * @param end the upper bound of the range of user contacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user contacts
	 */
	public static List<UserContact> findAll(
		int start, int end, OrderByComparator<UserContact> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserContactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user contacts
	 * @param end the upper bound of the range of user contacts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user contacts
	 */
	public static List<UserContact> findAll(
		int start, int end, OrderByComparator<UserContact> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the user contacts from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of user contacts.
	 *
	 * @return the number of user contacts
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserContactPersistence getPersistence() {
		return _persistence;
	}

	private static volatile UserContactPersistence _persistence;

}