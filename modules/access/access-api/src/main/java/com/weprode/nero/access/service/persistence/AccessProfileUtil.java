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

package com.weprode.nero.access.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.access.model.AccessProfile;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the access profile service. This utility wraps <code>com.weprode.nero.access.service.persistence.impl.AccessProfilePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccessProfilePersistence
 * @generated
 */
public class AccessProfileUtil {

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
	public static void clearCache(AccessProfile accessProfile) {
		getPersistence().clearCache(accessProfile);
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
	public static Map<Serializable, AccessProfile> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AccessProfile> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccessProfile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccessProfile> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccessProfile> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccessProfile update(AccessProfile accessProfile) {
		return getPersistence().update(accessProfile);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccessProfile update(
		AccessProfile accessProfile, ServiceContext serviceContext) {

		return getPersistence().update(accessProfile, serviceContext);
	}

	/**
	 * Returns all the access profiles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching access profiles
	 */
	public static List<AccessProfile> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the access profiles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @return the range of matching access profiles
	 */
	public static List<AccessProfile> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the access profiles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching access profiles
	 */
	public static List<AccessProfile> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AccessProfile> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the access profiles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching access profiles
	 */
	public static List<AccessProfile> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AccessProfile> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first access profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access profile
	 * @throws NoSuchProfileException if a matching access profile could not be found
	 */
	public static AccessProfile findByUuid_First(
			String uuid, OrderByComparator<AccessProfile> orderByComparator)
		throws com.weprode.nero.access.exception.NoSuchProfileException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first access profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access profile, or <code>null</code> if a matching access profile could not be found
	 */
	public static AccessProfile fetchByUuid_First(
		String uuid, OrderByComparator<AccessProfile> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last access profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access profile
	 * @throws NoSuchProfileException if a matching access profile could not be found
	 */
	public static AccessProfile findByUuid_Last(
			String uuid, OrderByComparator<AccessProfile> orderByComparator)
		throws com.weprode.nero.access.exception.NoSuchProfileException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last access profile in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access profile, or <code>null</code> if a matching access profile could not be found
	 */
	public static AccessProfile fetchByUuid_Last(
		String uuid, OrderByComparator<AccessProfile> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the access profiles before and after the current access profile in the ordered set where uuid = &#63;.
	 *
	 * @param accessProfilePK the primary key of the current access profile
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access profile
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	public static AccessProfile[] findByUuid_PrevAndNext(
			AccessProfilePK accessProfilePK, String uuid,
			OrderByComparator<AccessProfile> orderByComparator)
		throws com.weprode.nero.access.exception.NoSuchProfileException {

		return getPersistence().findByUuid_PrevAndNext(
			accessProfilePK, uuid, orderByComparator);
	}

	/**
	 * Removes all the access profiles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of access profiles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching access profiles
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the access profiles where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @return the matching access profiles
	 */
	public static List<AccessProfile> findByaccessId(long accessId) {
		return getPersistence().findByaccessId(accessId);
	}

	/**
	 * Returns a range of all the access profiles where accessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param accessId the access ID
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @return the range of matching access profiles
	 */
	public static List<AccessProfile> findByaccessId(
		long accessId, int start, int end) {

		return getPersistence().findByaccessId(accessId, start, end);
	}

	/**
	 * Returns an ordered range of all the access profiles where accessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param accessId the access ID
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching access profiles
	 */
	public static List<AccessProfile> findByaccessId(
		long accessId, int start, int end,
		OrderByComparator<AccessProfile> orderByComparator) {

		return getPersistence().findByaccessId(
			accessId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the access profiles where accessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param accessId the access ID
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching access profiles
	 */
	public static List<AccessProfile> findByaccessId(
		long accessId, int start, int end,
		OrderByComparator<AccessProfile> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByaccessId(
			accessId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access profile
	 * @throws NoSuchProfileException if a matching access profile could not be found
	 */
	public static AccessProfile findByaccessId_First(
			long accessId, OrderByComparator<AccessProfile> orderByComparator)
		throws com.weprode.nero.access.exception.NoSuchProfileException {

		return getPersistence().findByaccessId_First(
			accessId, orderByComparator);
	}

	/**
	 * Returns the first access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching access profile, or <code>null</code> if a matching access profile could not be found
	 */
	public static AccessProfile fetchByaccessId_First(
		long accessId, OrderByComparator<AccessProfile> orderByComparator) {

		return getPersistence().fetchByaccessId_First(
			accessId, orderByComparator);
	}

	/**
	 * Returns the last access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access profile
	 * @throws NoSuchProfileException if a matching access profile could not be found
	 */
	public static AccessProfile findByaccessId_Last(
			long accessId, OrderByComparator<AccessProfile> orderByComparator)
		throws com.weprode.nero.access.exception.NoSuchProfileException {

		return getPersistence().findByaccessId_Last(
			accessId, orderByComparator);
	}

	/**
	 * Returns the last access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching access profile, or <code>null</code> if a matching access profile could not be found
	 */
	public static AccessProfile fetchByaccessId_Last(
		long accessId, OrderByComparator<AccessProfile> orderByComparator) {

		return getPersistence().fetchByaccessId_Last(
			accessId, orderByComparator);
	}

	/**
	 * Returns the access profiles before and after the current access profile in the ordered set where accessId = &#63;.
	 *
	 * @param accessProfilePK the primary key of the current access profile
	 * @param accessId the access ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next access profile
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	public static AccessProfile[] findByaccessId_PrevAndNext(
			AccessProfilePK accessProfilePK, long accessId,
			OrderByComparator<AccessProfile> orderByComparator)
		throws com.weprode.nero.access.exception.NoSuchProfileException {

		return getPersistence().findByaccessId_PrevAndNext(
			accessProfilePK, accessId, orderByComparator);
	}

	/**
	 * Removes all the access profiles where accessId = &#63; from the database.
	 *
	 * @param accessId the access ID
	 */
	public static void removeByaccessId(long accessId) {
		getPersistence().removeByaccessId(accessId);
	}

	/**
	 * Returns the number of access profiles where accessId = &#63;.
	 *
	 * @param accessId the access ID
	 * @return the number of matching access profiles
	 */
	public static int countByaccessId(long accessId) {
		return getPersistence().countByaccessId(accessId);
	}

	/**
	 * Caches the access profile in the entity cache if it is enabled.
	 *
	 * @param accessProfile the access profile
	 */
	public static void cacheResult(AccessProfile accessProfile) {
		getPersistence().cacheResult(accessProfile);
	}

	/**
	 * Caches the access profiles in the entity cache if it is enabled.
	 *
	 * @param accessProfiles the access profiles
	 */
	public static void cacheResult(List<AccessProfile> accessProfiles) {
		getPersistence().cacheResult(accessProfiles);
	}

	/**
	 * Creates a new access profile with the primary key. Does not add the access profile to the database.
	 *
	 * @param accessProfilePK the primary key for the new access profile
	 * @return the new access profile
	 */
	public static AccessProfile create(AccessProfilePK accessProfilePK) {
		return getPersistence().create(accessProfilePK);
	}

	/**
	 * Removes the access profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accessProfilePK the primary key of the access profile
	 * @return the access profile that was removed
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	public static AccessProfile remove(AccessProfilePK accessProfilePK)
		throws com.weprode.nero.access.exception.NoSuchProfileException {

		return getPersistence().remove(accessProfilePK);
	}

	public static AccessProfile updateImpl(AccessProfile accessProfile) {
		return getPersistence().updateImpl(accessProfile);
	}

	/**
	 * Returns the access profile with the primary key or throws a <code>NoSuchProfileException</code> if it could not be found.
	 *
	 * @param accessProfilePK the primary key of the access profile
	 * @return the access profile
	 * @throws NoSuchProfileException if a access profile with the primary key could not be found
	 */
	public static AccessProfile findByPrimaryKey(
			AccessProfilePK accessProfilePK)
		throws com.weprode.nero.access.exception.NoSuchProfileException {

		return getPersistence().findByPrimaryKey(accessProfilePK);
	}

	/**
	 * Returns the access profile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accessProfilePK the primary key of the access profile
	 * @return the access profile, or <code>null</code> if a access profile with the primary key could not be found
	 */
	public static AccessProfile fetchByPrimaryKey(
		AccessProfilePK accessProfilePK) {

		return getPersistence().fetchByPrimaryKey(accessProfilePK);
	}

	/**
	 * Returns all the access profiles.
	 *
	 * @return the access profiles
	 */
	public static List<AccessProfile> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the access profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @return the range of access profiles
	 */
	public static List<AccessProfile> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the access profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of access profiles
	 */
	public static List<AccessProfile> findAll(
		int start, int end,
		OrderByComparator<AccessProfile> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the access profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccessProfileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of access profiles
	 * @param end the upper bound of the range of access profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of access profiles
	 */
	public static List<AccessProfile> findAll(
		int start, int end, OrderByComparator<AccessProfile> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the access profiles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of access profiles.
	 *
	 * @return the number of access profiles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static AccessProfilePersistence getPersistence() {
		return _persistence;
	}

	private static volatile AccessProfilePersistence _persistence;

}