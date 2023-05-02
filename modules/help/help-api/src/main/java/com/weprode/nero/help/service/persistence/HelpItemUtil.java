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

package com.weprode.nero.help.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.help.model.HelpItem;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the help item service. This utility wraps <code>com.weprode.nero.help.service.persistence.impl.HelpItemPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpItemPersistence
 * @generated
 */
public class HelpItemUtil {

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
	public static void clearCache(HelpItem helpItem) {
		getPersistence().clearCache(helpItem);
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
	public static Map<Serializable, HelpItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HelpItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HelpItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HelpItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HelpItem> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HelpItem update(HelpItem helpItem) {
		return getPersistence().update(helpItem);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HelpItem update(
		HelpItem helpItem, ServiceContext serviceContext) {

		return getPersistence().update(helpItem, serviceContext);
	}

	/**
	 * Returns all the help items where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the matching help items
	 */
	public static List<HelpItem> findBycategoryId(long categoryId) {
		return getPersistence().findBycategoryId(categoryId);
	}

	/**
	 * Returns a range of all the help items where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @return the range of matching help items
	 */
	public static List<HelpItem> findBycategoryId(
		long categoryId, int start, int end) {

		return getPersistence().findBycategoryId(categoryId, start, end);
	}

	/**
	 * Returns an ordered range of all the help items where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help items
	 */
	public static List<HelpItem> findBycategoryId(
		long categoryId, int start, int end,
		OrderByComparator<HelpItem> orderByComparator) {

		return getPersistence().findBycategoryId(
			categoryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help items where categoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param categoryId the category ID
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help items
	 */
	public static List<HelpItem> findBycategoryId(
		long categoryId, int start, int end,
		OrderByComparator<HelpItem> orderByComparator, boolean useFinderCache) {

		return getPersistence().findBycategoryId(
			categoryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first help item in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help item
	 * @throws NoSuchItemException if a matching help item could not be found
	 */
	public static HelpItem findBycategoryId_First(
			long categoryId, OrderByComparator<HelpItem> orderByComparator)
		throws com.weprode.nero.help.exception.NoSuchItemException {

		return getPersistence().findBycategoryId_First(
			categoryId, orderByComparator);
	}

	/**
	 * Returns the first help item in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help item, or <code>null</code> if a matching help item could not be found
	 */
	public static HelpItem fetchBycategoryId_First(
		long categoryId, OrderByComparator<HelpItem> orderByComparator) {

		return getPersistence().fetchBycategoryId_First(
			categoryId, orderByComparator);
	}

	/**
	 * Returns the last help item in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help item
	 * @throws NoSuchItemException if a matching help item could not be found
	 */
	public static HelpItem findBycategoryId_Last(
			long categoryId, OrderByComparator<HelpItem> orderByComparator)
		throws com.weprode.nero.help.exception.NoSuchItemException {

		return getPersistence().findBycategoryId_Last(
			categoryId, orderByComparator);
	}

	/**
	 * Returns the last help item in the ordered set where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help item, or <code>null</code> if a matching help item could not be found
	 */
	public static HelpItem fetchBycategoryId_Last(
		long categoryId, OrderByComparator<HelpItem> orderByComparator) {

		return getPersistence().fetchBycategoryId_Last(
			categoryId, orderByComparator);
	}

	/**
	 * Returns the help items before and after the current help item in the ordered set where categoryId = &#63;.
	 *
	 * @param itemId the primary key of the current help item
	 * @param categoryId the category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help item
	 * @throws NoSuchItemException if a help item with the primary key could not be found
	 */
	public static HelpItem[] findBycategoryId_PrevAndNext(
			long itemId, long categoryId,
			OrderByComparator<HelpItem> orderByComparator)
		throws com.weprode.nero.help.exception.NoSuchItemException {

		return getPersistence().findBycategoryId_PrevAndNext(
			itemId, categoryId, orderByComparator);
	}

	/**
	 * Removes all the help items where categoryId = &#63; from the database.
	 *
	 * @param categoryId the category ID
	 */
	public static void removeBycategoryId(long categoryId) {
		getPersistence().removeBycategoryId(categoryId);
	}

	/**
	 * Returns the number of help items where categoryId = &#63;.
	 *
	 * @param categoryId the category ID
	 * @return the number of matching help items
	 */
	public static int countBycategoryId(long categoryId) {
		return getPersistence().countBycategoryId(categoryId);
	}

	/**
	 * Caches the help item in the entity cache if it is enabled.
	 *
	 * @param helpItem the help item
	 */
	public static void cacheResult(HelpItem helpItem) {
		getPersistence().cacheResult(helpItem);
	}

	/**
	 * Caches the help items in the entity cache if it is enabled.
	 *
	 * @param helpItems the help items
	 */
	public static void cacheResult(List<HelpItem> helpItems) {
		getPersistence().cacheResult(helpItems);
	}

	/**
	 * Creates a new help item with the primary key. Does not add the help item to the database.
	 *
	 * @param itemId the primary key for the new help item
	 * @return the new help item
	 */
	public static HelpItem create(long itemId) {
		return getPersistence().create(itemId);
	}

	/**
	 * Removes the help item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item that was removed
	 * @throws NoSuchItemException if a help item with the primary key could not be found
	 */
	public static HelpItem remove(long itemId)
		throws com.weprode.nero.help.exception.NoSuchItemException {

		return getPersistence().remove(itemId);
	}

	public static HelpItem updateImpl(HelpItem helpItem) {
		return getPersistence().updateImpl(helpItem);
	}

	/**
	 * Returns the help item with the primary key or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item
	 * @throws NoSuchItemException if a help item with the primary key could not be found
	 */
	public static HelpItem findByPrimaryKey(long itemId)
		throws com.weprode.nero.help.exception.NoSuchItemException {

		return getPersistence().findByPrimaryKey(itemId);
	}

	/**
	 * Returns the help item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemId the primary key of the help item
	 * @return the help item, or <code>null</code> if a help item with the primary key could not be found
	 */
	public static HelpItem fetchByPrimaryKey(long itemId) {
		return getPersistence().fetchByPrimaryKey(itemId);
	}

	/**
	 * Returns all the help items.
	 *
	 * @return the help items
	 */
	public static List<HelpItem> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the help items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @return the range of help items
	 */
	public static List<HelpItem> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the help items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help items
	 */
	public static List<HelpItem> findAll(
		int start, int end, OrderByComparator<HelpItem> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help items
	 * @param end the upper bound of the range of help items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help items
	 */
	public static List<HelpItem> findAll(
		int start, int end, OrderByComparator<HelpItem> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the help items from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of help items.
	 *
	 * @return the number of help items
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HelpItemPersistence getPersistence() {
		return _persistence;
	}

	private static volatile HelpItemPersistence _persistence;

}