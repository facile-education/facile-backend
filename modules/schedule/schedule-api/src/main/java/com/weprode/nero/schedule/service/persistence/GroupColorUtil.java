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

package com.weprode.nero.schedule.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.schedule.model.GroupColor;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the group color service. This utility wraps <code>com.weprode.nero.schedule.service.persistence.impl.GroupColorPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GroupColorPersistence
 * @generated
 */
public class GroupColorUtil {

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
	public static void clearCache(GroupColor groupColor) {
		getPersistence().clearCache(groupColor);
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
	public static Map<Serializable, GroupColor> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<GroupColor> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GroupColor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GroupColor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<GroupColor> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GroupColor update(GroupColor groupColor) {
		return getPersistence().update(groupColor);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GroupColor update(
		GroupColor groupColor, ServiceContext serviceContext) {

		return getPersistence().update(groupColor, serviceContext);
	}

	/**
	 * Caches the group color in the entity cache if it is enabled.
	 *
	 * @param groupColor the group color
	 */
	public static void cacheResult(GroupColor groupColor) {
		getPersistence().cacheResult(groupColor);
	}

	/**
	 * Caches the group colors in the entity cache if it is enabled.
	 *
	 * @param groupColors the group colors
	 */
	public static void cacheResult(List<GroupColor> groupColors) {
		getPersistence().cacheResult(groupColors);
	}

	/**
	 * Creates a new group color with the primary key. Does not add the group color to the database.
	 *
	 * @param groupId the primary key for the new group color
	 * @return the new group color
	 */
	public static GroupColor create(long groupId) {
		return getPersistence().create(groupId);
	}

	/**
	 * Removes the group color with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param groupId the primary key of the group color
	 * @return the group color that was removed
	 * @throws NoSuchGroupColorException if a group color with the primary key could not be found
	 */
	public static GroupColor remove(long groupId)
		throws com.weprode.nero.schedule.exception.NoSuchGroupColorException {

		return getPersistence().remove(groupId);
	}

	public static GroupColor updateImpl(GroupColor groupColor) {
		return getPersistence().updateImpl(groupColor);
	}

	/**
	 * Returns the group color with the primary key or throws a <code>NoSuchGroupColorException</code> if it could not be found.
	 *
	 * @param groupId the primary key of the group color
	 * @return the group color
	 * @throws NoSuchGroupColorException if a group color with the primary key could not be found
	 */
	public static GroupColor findByPrimaryKey(long groupId)
		throws com.weprode.nero.schedule.exception.NoSuchGroupColorException {

		return getPersistence().findByPrimaryKey(groupId);
	}

	/**
	 * Returns the group color with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param groupId the primary key of the group color
	 * @return the group color, or <code>null</code> if a group color with the primary key could not be found
	 */
	public static GroupColor fetchByPrimaryKey(long groupId) {
		return getPersistence().fetchByPrimaryKey(groupId);
	}

	/**
	 * Returns all the group colors.
	 *
	 * @return the group colors
	 */
	public static List<GroupColor> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group colors
	 * @param end the upper bound of the range of group colors (not inclusive)
	 * @return the range of group colors
	 */
	public static List<GroupColor> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group colors
	 * @param end the upper bound of the range of group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of group colors
	 */
	public static List<GroupColor> findAll(
		int start, int end, OrderByComparator<GroupColor> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the group colors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>GroupColorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of group colors
	 * @param end the upper bound of the range of group colors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of group colors
	 */
	public static List<GroupColor> findAll(
		int start, int end, OrderByComparator<GroupColor> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the group colors from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of group colors.
	 *
	 * @return the number of group colors
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static GroupColorPersistence getPersistence() {
		return _persistence;
	}

	private static volatile GroupColorPersistence _persistence;

}