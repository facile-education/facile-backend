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

package com.weprode.facile.help.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.facile.help.model.HelpItemRole;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the help item role service. This utility wraps <code>com.weprode.facile.help.service.persistence.impl.HelpItemRolePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpItemRolePersistence
 * @generated
 */
public class HelpItemRoleUtil {

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
	public static void clearCache(HelpItemRole helpItemRole) {
		getPersistence().clearCache(helpItemRole);
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
	public static Map<Serializable, HelpItemRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HelpItemRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HelpItemRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HelpItemRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HelpItemRole> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HelpItemRole update(HelpItemRole helpItemRole) {
		return getPersistence().update(helpItemRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HelpItemRole update(
		HelpItemRole helpItemRole, ServiceContext serviceContext) {

		return getPersistence().update(helpItemRole, serviceContext);
	}

	/**
	 * Returns all the help item roles where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching help item roles
	 */
	public static List<HelpItemRole> findByitemId(long itemId) {
		return getPersistence().findByitemId(itemId);
	}

	/**
	 * Returns a range of all the help item roles where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @return the range of matching help item roles
	 */
	public static List<HelpItemRole> findByitemId(
		long itemId, int start, int end) {

		return getPersistence().findByitemId(itemId, start, end);
	}

	/**
	 * Returns an ordered range of all the help item roles where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help item roles
	 */
	public static List<HelpItemRole> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpItemRole> orderByComparator) {

		return getPersistence().findByitemId(
			itemId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help item roles where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help item roles
	 */
	public static List<HelpItemRole> findByitemId(
		long itemId, int start, int end,
		OrderByComparator<HelpItemRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByitemId(
			itemId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first help item role in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help item role
	 * @throws NoSuchItemRoleException if a matching help item role could not be found
	 */
	public static HelpItemRole findByitemId_First(
			long itemId, OrderByComparator<HelpItemRole> orderByComparator)
		throws com.weprode.facile.help.exception.NoSuchItemRoleException {

		return getPersistence().findByitemId_First(itemId, orderByComparator);
	}

	/**
	 * Returns the first help item role in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help item role, or <code>null</code> if a matching help item role could not be found
	 */
	public static HelpItemRole fetchByitemId_First(
		long itemId, OrderByComparator<HelpItemRole> orderByComparator) {

		return getPersistence().fetchByitemId_First(itemId, orderByComparator);
	}

	/**
	 * Returns the last help item role in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help item role
	 * @throws NoSuchItemRoleException if a matching help item role could not be found
	 */
	public static HelpItemRole findByitemId_Last(
			long itemId, OrderByComparator<HelpItemRole> orderByComparator)
		throws com.weprode.facile.help.exception.NoSuchItemRoleException {

		return getPersistence().findByitemId_Last(itemId, orderByComparator);
	}

	/**
	 * Returns the last help item role in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help item role, or <code>null</code> if a matching help item role could not be found
	 */
	public static HelpItemRole fetchByitemId_Last(
		long itemId, OrderByComparator<HelpItemRole> orderByComparator) {

		return getPersistence().fetchByitemId_Last(itemId, orderByComparator);
	}

	/**
	 * Returns the help item roles before and after the current help item role in the ordered set where itemId = &#63;.
	 *
	 * @param helpItemRoleId the primary key of the current help item role
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help item role
	 * @throws NoSuchItemRoleException if a help item role with the primary key could not be found
	 */
	public static HelpItemRole[] findByitemId_PrevAndNext(
			long helpItemRoleId, long itemId,
			OrderByComparator<HelpItemRole> orderByComparator)
		throws com.weprode.facile.help.exception.NoSuchItemRoleException {

		return getPersistence().findByitemId_PrevAndNext(
			helpItemRoleId, itemId, orderByComparator);
	}

	/**
	 * Removes all the help item roles where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	public static void removeByitemId(long itemId) {
		getPersistence().removeByitemId(itemId);
	}

	/**
	 * Returns the number of help item roles where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching help item roles
	 */
	public static int countByitemId(long itemId) {
		return getPersistence().countByitemId(itemId);
	}

	/**
	 * Caches the help item role in the entity cache if it is enabled.
	 *
	 * @param helpItemRole the help item role
	 */
	public static void cacheResult(HelpItemRole helpItemRole) {
		getPersistence().cacheResult(helpItemRole);
	}

	/**
	 * Caches the help item roles in the entity cache if it is enabled.
	 *
	 * @param helpItemRoles the help item roles
	 */
	public static void cacheResult(List<HelpItemRole> helpItemRoles) {
		getPersistence().cacheResult(helpItemRoles);
	}

	/**
	 * Creates a new help item role with the primary key. Does not add the help item role to the database.
	 *
	 * @param helpItemRoleId the primary key for the new help item role
	 * @return the new help item role
	 */
	public static HelpItemRole create(long helpItemRoleId) {
		return getPersistence().create(helpItemRoleId);
	}

	/**
	 * Removes the help item role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpItemRoleId the primary key of the help item role
	 * @return the help item role that was removed
	 * @throws NoSuchItemRoleException if a help item role with the primary key could not be found
	 */
	public static HelpItemRole remove(long helpItemRoleId)
		throws com.weprode.facile.help.exception.NoSuchItemRoleException {

		return getPersistence().remove(helpItemRoleId);
	}

	public static HelpItemRole updateImpl(HelpItemRole helpItemRole) {
		return getPersistence().updateImpl(helpItemRole);
	}

	/**
	 * Returns the help item role with the primary key or throws a <code>NoSuchItemRoleException</code> if it could not be found.
	 *
	 * @param helpItemRoleId the primary key of the help item role
	 * @return the help item role
	 * @throws NoSuchItemRoleException if a help item role with the primary key could not be found
	 */
	public static HelpItemRole findByPrimaryKey(long helpItemRoleId)
		throws com.weprode.facile.help.exception.NoSuchItemRoleException {

		return getPersistence().findByPrimaryKey(helpItemRoleId);
	}

	/**
	 * Returns the help item role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param helpItemRoleId the primary key of the help item role
	 * @return the help item role, or <code>null</code> if a help item role with the primary key could not be found
	 */
	public static HelpItemRole fetchByPrimaryKey(long helpItemRoleId) {
		return getPersistence().fetchByPrimaryKey(helpItemRoleId);
	}

	/**
	 * Returns all the help item roles.
	 *
	 * @return the help item roles
	 */
	public static List<HelpItemRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the help item roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @return the range of help item roles
	 */
	public static List<HelpItemRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the help item roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help item roles
	 */
	public static List<HelpItemRole> findAll(
		int start, int end, OrderByComparator<HelpItemRole> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help item roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help item roles
	 * @param end the upper bound of the range of help item roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help item roles
	 */
	public static List<HelpItemRole> findAll(
		int start, int end, OrderByComparator<HelpItemRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the help item roles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of help item roles.
	 *
	 * @return the number of help item roles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HelpItemRolePersistence getPersistence() {
		return _persistence;
	}

	private static volatile HelpItemRolePersistence _persistence;

}