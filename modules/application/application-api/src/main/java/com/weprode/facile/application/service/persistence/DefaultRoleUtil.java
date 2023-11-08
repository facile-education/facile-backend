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

package com.weprode.facile.application.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.application.model.DefaultRole;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the default role service. This utility wraps <code>com.weprode.facile.application.service.persistence.impl.DefaultRolePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DefaultRolePersistence
 * @generated
 */
public class DefaultRoleUtil {

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
	public static void clearCache(DefaultRole defaultRole) {
		getPersistence().clearCache(defaultRole);
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
	public static Map<Serializable, DefaultRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DefaultRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DefaultRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DefaultRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DefaultRole> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DefaultRole update(DefaultRole defaultRole) {
		return getPersistence().update(defaultRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DefaultRole update(
		DefaultRole defaultRole, ServiceContext serviceContext) {

		return getPersistence().update(defaultRole, serviceContext);
	}

	/**
	 * Returns all the default roles where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the matching default roles
	 */
	public static List<DefaultRole> findByapplicationId(long applicationId) {
		return getPersistence().findByapplicationId(applicationId);
	}

	/**
	 * Returns a range of all the default roles where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @return the range of matching default roles
	 */
	public static List<DefaultRole> findByapplicationId(
		long applicationId, int start, int end) {

		return getPersistence().findByapplicationId(applicationId, start, end);
	}

	/**
	 * Returns an ordered range of all the default roles where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching default roles
	 */
	public static List<DefaultRole> findByapplicationId(
		long applicationId, int start, int end,
		OrderByComparator<DefaultRole> orderByComparator) {

		return getPersistence().findByapplicationId(
			applicationId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the default roles where applicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param applicationId the application ID
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching default roles
	 */
	public static List<DefaultRole> findByapplicationId(
		long applicationId, int start, int end,
		OrderByComparator<DefaultRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByapplicationId(
			applicationId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first default role in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching default role
	 * @throws NoSuchDefaultRoleException if a matching default role could not be found
	 */
	public static DefaultRole findByapplicationId_First(
			long applicationId,
			OrderByComparator<DefaultRole> orderByComparator)
		throws com.weprode.facile.application.exception.
			NoSuchDefaultRoleException {

		return getPersistence().findByapplicationId_First(
			applicationId, orderByComparator);
	}

	/**
	 * Returns the first default role in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching default role, or <code>null</code> if a matching default role could not be found
	 */
	public static DefaultRole fetchByapplicationId_First(
		long applicationId, OrderByComparator<DefaultRole> orderByComparator) {

		return getPersistence().fetchByapplicationId_First(
			applicationId, orderByComparator);
	}

	/**
	 * Returns the last default role in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching default role
	 * @throws NoSuchDefaultRoleException if a matching default role could not be found
	 */
	public static DefaultRole findByapplicationId_Last(
			long applicationId,
			OrderByComparator<DefaultRole> orderByComparator)
		throws com.weprode.facile.application.exception.
			NoSuchDefaultRoleException {

		return getPersistence().findByapplicationId_Last(
			applicationId, orderByComparator);
	}

	/**
	 * Returns the last default role in the ordered set where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching default role, or <code>null</code> if a matching default role could not be found
	 */
	public static DefaultRole fetchByapplicationId_Last(
		long applicationId, OrderByComparator<DefaultRole> orderByComparator) {

		return getPersistence().fetchByapplicationId_Last(
			applicationId, orderByComparator);
	}

	/**
	 * Returns the default roles before and after the current default role in the ordered set where applicationId = &#63;.
	 *
	 * @param defaultRoleId the primary key of the current default role
	 * @param applicationId the application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next default role
	 * @throws NoSuchDefaultRoleException if a default role with the primary key could not be found
	 */
	public static DefaultRole[] findByapplicationId_PrevAndNext(
			long defaultRoleId, long applicationId,
			OrderByComparator<DefaultRole> orderByComparator)
		throws com.weprode.facile.application.exception.
			NoSuchDefaultRoleException {

		return getPersistence().findByapplicationId_PrevAndNext(
			defaultRoleId, applicationId, orderByComparator);
	}

	/**
	 * Removes all the default roles where applicationId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 */
	public static void removeByapplicationId(long applicationId) {
		getPersistence().removeByapplicationId(applicationId);
	}

	/**
	 * Returns the number of default roles where applicationId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @return the number of matching default roles
	 */
	public static int countByapplicationId(long applicationId) {
		return getPersistence().countByapplicationId(applicationId);
	}

	/**
	 * Returns the default role where applicationId = &#63; and roleId = &#63; or throws a <code>NoSuchDefaultRoleException</code> if it could not be found.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @return the matching default role
	 * @throws NoSuchDefaultRoleException if a matching default role could not be found
	 */
	public static DefaultRole findByapplicationId_roleId(
			long applicationId, long roleId)
		throws com.weprode.facile.application.exception.
			NoSuchDefaultRoleException {

		return getPersistence().findByapplicationId_roleId(
			applicationId, roleId);
	}

	/**
	 * Returns the default role where applicationId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @return the matching default role, or <code>null</code> if a matching default role could not be found
	 */
	public static DefaultRole fetchByapplicationId_roleId(
		long applicationId, long roleId) {

		return getPersistence().fetchByapplicationId_roleId(
			applicationId, roleId);
	}

	/**
	 * Returns the default role where applicationId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching default role, or <code>null</code> if a matching default role could not be found
	 */
	public static DefaultRole fetchByapplicationId_roleId(
		long applicationId, long roleId, boolean useFinderCache) {

		return getPersistence().fetchByapplicationId_roleId(
			applicationId, roleId, useFinderCache);
	}

	/**
	 * Removes the default role where applicationId = &#63; and roleId = &#63; from the database.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @return the default role that was removed
	 */
	public static DefaultRole removeByapplicationId_roleId(
			long applicationId, long roleId)
		throws com.weprode.facile.application.exception.
			NoSuchDefaultRoleException {

		return getPersistence().removeByapplicationId_roleId(
			applicationId, roleId);
	}

	/**
	 * Returns the number of default roles where applicationId = &#63; and roleId = &#63;.
	 *
	 * @param applicationId the application ID
	 * @param roleId the role ID
	 * @return the number of matching default roles
	 */
	public static int countByapplicationId_roleId(
		long applicationId, long roleId) {

		return getPersistence().countByapplicationId_roleId(
			applicationId, roleId);
	}

	/**
	 * Caches the default role in the entity cache if it is enabled.
	 *
	 * @param defaultRole the default role
	 */
	public static void cacheResult(DefaultRole defaultRole) {
		getPersistence().cacheResult(defaultRole);
	}

	/**
	 * Caches the default roles in the entity cache if it is enabled.
	 *
	 * @param defaultRoles the default roles
	 */
	public static void cacheResult(List<DefaultRole> defaultRoles) {
		getPersistence().cacheResult(defaultRoles);
	}

	/**
	 * Creates a new default role with the primary key. Does not add the default role to the database.
	 *
	 * @param defaultRoleId the primary key for the new default role
	 * @return the new default role
	 */
	public static DefaultRole create(long defaultRoleId) {
		return getPersistence().create(defaultRoleId);
	}

	/**
	 * Removes the default role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param defaultRoleId the primary key of the default role
	 * @return the default role that was removed
	 * @throws NoSuchDefaultRoleException if a default role with the primary key could not be found
	 */
	public static DefaultRole remove(long defaultRoleId)
		throws com.weprode.facile.application.exception.
			NoSuchDefaultRoleException {

		return getPersistence().remove(defaultRoleId);
	}

	public static DefaultRole updateImpl(DefaultRole defaultRole) {
		return getPersistence().updateImpl(defaultRole);
	}

	/**
	 * Returns the default role with the primary key or throws a <code>NoSuchDefaultRoleException</code> if it could not be found.
	 *
	 * @param defaultRoleId the primary key of the default role
	 * @return the default role
	 * @throws NoSuchDefaultRoleException if a default role with the primary key could not be found
	 */
	public static DefaultRole findByPrimaryKey(long defaultRoleId)
		throws com.weprode.facile.application.exception.
			NoSuchDefaultRoleException {

		return getPersistence().findByPrimaryKey(defaultRoleId);
	}

	/**
	 * Returns the default role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param defaultRoleId the primary key of the default role
	 * @return the default role, or <code>null</code> if a default role with the primary key could not be found
	 */
	public static DefaultRole fetchByPrimaryKey(long defaultRoleId) {
		return getPersistence().fetchByPrimaryKey(defaultRoleId);
	}

	/**
	 * Returns all the default roles.
	 *
	 * @return the default roles
	 */
	public static List<DefaultRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the default roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @return the range of default roles
	 */
	public static List<DefaultRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the default roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of default roles
	 */
	public static List<DefaultRole> findAll(
		int start, int end, OrderByComparator<DefaultRole> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the default roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DefaultRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of default roles
	 * @param end the upper bound of the range of default roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of default roles
	 */
	public static List<DefaultRole> findAll(
		int start, int end, OrderByComparator<DefaultRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the default roles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of default roles.
	 *
	 * @return the number of default roles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DefaultRolePersistence getPersistence() {
		return _persistence;
	}

	private static volatile DefaultRolePersistence _persistence;

}