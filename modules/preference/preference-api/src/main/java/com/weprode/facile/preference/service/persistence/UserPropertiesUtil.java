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

package com.weprode.facile.preference.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.preference.model.UserProperties;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the user properties service. This utility wraps <code>com.weprode.facile.preference.service.persistence.impl.UserPropertiesPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserPropertiesPersistence
 * @generated
 */
public class UserPropertiesUtil {

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
	public static void clearCache(UserProperties userProperties) {
		getPersistence().clearCache(userProperties);
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
	public static Map<Serializable, UserProperties> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserProperties> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserProperties> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserProperties> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserProperties> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserProperties update(UserProperties userProperties) {
		return getPersistence().update(userProperties);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserProperties update(
		UserProperties userProperties, ServiceContext serviceContext) {

		return getPersistence().update(userProperties, serviceContext);
	}

	/**
	 * Returns all the user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @return the matching user propertieses
	 */
	public static List<UserProperties> findByetabId_manualAccount(
		long etabId, boolean manualAccount) {

		return getPersistence().findByetabId_manualAccount(
			etabId, manualAccount);
	}

	/**
	 * Returns a range of all the user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @return the range of matching user propertieses
	 */
	public static List<UserProperties> findByetabId_manualAccount(
		long etabId, boolean manualAccount, int start, int end) {

		return getPersistence().findByetabId_manualAccount(
			etabId, manualAccount, start, end);
	}

	/**
	 * Returns an ordered range of all the user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user propertieses
	 */
	public static List<UserProperties> findByetabId_manualAccount(
		long etabId, boolean manualAccount, int start, int end,
		OrderByComparator<UserProperties> orderByComparator) {

		return getPersistence().findByetabId_manualAccount(
			etabId, manualAccount, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user propertieses
	 */
	public static List<UserProperties> findByetabId_manualAccount(
		long etabId, boolean manualAccount, int start, int end,
		OrderByComparator<UserProperties> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByetabId_manualAccount(
			etabId, manualAccount, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user properties
	 * @throws NoSuchUserPropertiesException if a matching user properties could not be found
	 */
	public static UserProperties findByetabId_manualAccount_First(
			long etabId, boolean manualAccount,
			OrderByComparator<UserProperties> orderByComparator)
		throws com.weprode.facile.preference.exception.
			NoSuchUserPropertiesException {

		return getPersistence().findByetabId_manualAccount_First(
			etabId, manualAccount, orderByComparator);
	}

	/**
	 * Returns the first user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user properties, or <code>null</code> if a matching user properties could not be found
	 */
	public static UserProperties fetchByetabId_manualAccount_First(
		long etabId, boolean manualAccount,
		OrderByComparator<UserProperties> orderByComparator) {

		return getPersistence().fetchByetabId_manualAccount_First(
			etabId, manualAccount, orderByComparator);
	}

	/**
	 * Returns the last user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user properties
	 * @throws NoSuchUserPropertiesException if a matching user properties could not be found
	 */
	public static UserProperties findByetabId_manualAccount_Last(
			long etabId, boolean manualAccount,
			OrderByComparator<UserProperties> orderByComparator)
		throws com.weprode.facile.preference.exception.
			NoSuchUserPropertiesException {

		return getPersistence().findByetabId_manualAccount_Last(
			etabId, manualAccount, orderByComparator);
	}

	/**
	 * Returns the last user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user properties, or <code>null</code> if a matching user properties could not be found
	 */
	public static UserProperties fetchByetabId_manualAccount_Last(
		long etabId, boolean manualAccount,
		OrderByComparator<UserProperties> orderByComparator) {

		return getPersistence().fetchByetabId_manualAccount_Last(
			etabId, manualAccount, orderByComparator);
	}

	/**
	 * Returns the user propertieses before and after the current user properties in the ordered set where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param userId the primary key of the current user properties
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user properties
	 * @throws NoSuchUserPropertiesException if a user properties with the primary key could not be found
	 */
	public static UserProperties[] findByetabId_manualAccount_PrevAndNext(
			long userId, long etabId, boolean manualAccount,
			OrderByComparator<UserProperties> orderByComparator)
		throws com.weprode.facile.preference.exception.
			NoSuchUserPropertiesException {

		return getPersistence().findByetabId_manualAccount_PrevAndNext(
			userId, etabId, manualAccount, orderByComparator);
	}

	/**
	 * Removes all the user propertieses where etabId = &#63; and manualAccount = &#63; from the database.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 */
	public static void removeByetabId_manualAccount(
		long etabId, boolean manualAccount) {

		getPersistence().removeByetabId_manualAccount(etabId, manualAccount);
	}

	/**
	 * Returns the number of user propertieses where etabId = &#63; and manualAccount = &#63;.
	 *
	 * @param etabId the etab ID
	 * @param manualAccount the manual account
	 * @return the number of matching user propertieses
	 */
	public static int countByetabId_manualAccount(
		long etabId, boolean manualAccount) {

		return getPersistence().countByetabId_manualAccount(
			etabId, manualAccount);
	}

	/**
	 * Caches the user properties in the entity cache if it is enabled.
	 *
	 * @param userProperties the user properties
	 */
	public static void cacheResult(UserProperties userProperties) {
		getPersistence().cacheResult(userProperties);
	}

	/**
	 * Caches the user propertieses in the entity cache if it is enabled.
	 *
	 * @param userPropertieses the user propertieses
	 */
	public static void cacheResult(List<UserProperties> userPropertieses) {
		getPersistence().cacheResult(userPropertieses);
	}

	/**
	 * Creates a new user properties with the primary key. Does not add the user properties to the database.
	 *
	 * @param userId the primary key for the new user properties
	 * @return the new user properties
	 */
	public static UserProperties create(long userId) {
		return getPersistence().create(userId);
	}

	/**
	 * Removes the user properties with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the user properties
	 * @return the user properties that was removed
	 * @throws NoSuchUserPropertiesException if a user properties with the primary key could not be found
	 */
	public static UserProperties remove(long userId)
		throws com.weprode.facile.preference.exception.
			NoSuchUserPropertiesException {

		return getPersistence().remove(userId);
	}

	public static UserProperties updateImpl(UserProperties userProperties) {
		return getPersistence().updateImpl(userProperties);
	}

	/**
	 * Returns the user properties with the primary key or throws a <code>NoSuchUserPropertiesException</code> if it could not be found.
	 *
	 * @param userId the primary key of the user properties
	 * @return the user properties
	 * @throws NoSuchUserPropertiesException if a user properties with the primary key could not be found
	 */
	public static UserProperties findByPrimaryKey(long userId)
		throws com.weprode.facile.preference.exception.
			NoSuchUserPropertiesException {

		return getPersistence().findByPrimaryKey(userId);
	}

	/**
	 * Returns the user properties with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the user properties
	 * @return the user properties, or <code>null</code> if a user properties with the primary key could not be found
	 */
	public static UserProperties fetchByPrimaryKey(long userId) {
		return getPersistence().fetchByPrimaryKey(userId);
	}

	/**
	 * Returns all the user propertieses.
	 *
	 * @return the user propertieses
	 */
	public static List<UserProperties> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the user propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @return the range of user propertieses
	 */
	public static List<UserProperties> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the user propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user propertieses
	 */
	public static List<UserProperties> findAll(
		int start, int end,
		OrderByComparator<UserProperties> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the user propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserPropertiesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user propertieses
	 * @param end the upper bound of the range of user propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user propertieses
	 */
	public static List<UserProperties> findAll(
		int start, int end, OrderByComparator<UserProperties> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the user propertieses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of user propertieses.
	 *
	 * @return the number of user propertieses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserPropertiesPersistence getPersistence() {
		return _persistence;
	}

	private static volatile UserPropertiesPersistence _persistence;

}