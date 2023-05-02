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

import com.weprode.nero.help.model.HelpCategory;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the help category service. This utility wraps <code>com.weprode.nero.help.service.persistence.impl.HelpCategoryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpCategoryPersistence
 * @generated
 */
public class HelpCategoryUtil {

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
	public static void clearCache(HelpCategory helpCategory) {
		getPersistence().clearCache(helpCategory);
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
	public static Map<Serializable, HelpCategory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HelpCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HelpCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HelpCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HelpCategory> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HelpCategory update(HelpCategory helpCategory) {
		return getPersistence().update(helpCategory);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HelpCategory update(
		HelpCategory helpCategory, ServiceContext serviceContext) {

		return getPersistence().update(helpCategory, serviceContext);
	}

	/**
	 * Caches the help category in the entity cache if it is enabled.
	 *
	 * @param helpCategory the help category
	 */
	public static void cacheResult(HelpCategory helpCategory) {
		getPersistence().cacheResult(helpCategory);
	}

	/**
	 * Caches the help categories in the entity cache if it is enabled.
	 *
	 * @param helpCategories the help categories
	 */
	public static void cacheResult(List<HelpCategory> helpCategories) {
		getPersistence().cacheResult(helpCategories);
	}

	/**
	 * Creates a new help category with the primary key. Does not add the help category to the database.
	 *
	 * @param categoryId the primary key for the new help category
	 * @return the new help category
	 */
	public static HelpCategory create(long categoryId) {
		return getPersistence().create(categoryId);
	}

	/**
	 * Removes the help category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param categoryId the primary key of the help category
	 * @return the help category that was removed
	 * @throws NoSuchCategoryException if a help category with the primary key could not be found
	 */
	public static HelpCategory remove(long categoryId)
		throws com.weprode.nero.help.exception.NoSuchCategoryException {

		return getPersistence().remove(categoryId);
	}

	public static HelpCategory updateImpl(HelpCategory helpCategory) {
		return getPersistence().updateImpl(helpCategory);
	}

	/**
	 * Returns the help category with the primary key or throws a <code>NoSuchCategoryException</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the help category
	 * @return the help category
	 * @throws NoSuchCategoryException if a help category with the primary key could not be found
	 */
	public static HelpCategory findByPrimaryKey(long categoryId)
		throws com.weprode.nero.help.exception.NoSuchCategoryException {

		return getPersistence().findByPrimaryKey(categoryId);
	}

	/**
	 * Returns the help category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param categoryId the primary key of the help category
	 * @return the help category, or <code>null</code> if a help category with the primary key could not be found
	 */
	public static HelpCategory fetchByPrimaryKey(long categoryId) {
		return getPersistence().fetchByPrimaryKey(categoryId);
	}

	/**
	 * Returns all the help categories.
	 *
	 * @return the help categories
	 */
	public static List<HelpCategory> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the help categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help categories
	 * @param end the upper bound of the range of help categories (not inclusive)
	 * @return the range of help categories
	 */
	public static List<HelpCategory> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the help categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help categories
	 * @param end the upper bound of the range of help categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help categories
	 */
	public static List<HelpCategory> findAll(
		int start, int end, OrderByComparator<HelpCategory> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpCategoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help categories
	 * @param end the upper bound of the range of help categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help categories
	 */
	public static List<HelpCategory> findAll(
		int start, int end, OrderByComparator<HelpCategory> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the help categories from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of help categories.
	 *
	 * @return the number of help categories
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HelpCategoryPersistence getPersistence() {
		return _persistence;
	}

	private static volatile HelpCategoryPersistence _persistence;

}