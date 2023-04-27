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

package com.weprode.nero.progression.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.weprode.nero.progression.model.ItemContent;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the item content service. This utility wraps <code>com.weprode.nero.progression.service.persistence.impl.ItemContentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ItemContentPersistence
 * @generated
 */
public class ItemContentUtil {

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
	public static void clearCache(ItemContent itemContent) {
		getPersistence().clearCache(itemContent);
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
	public static Map<Serializable, ItemContent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ItemContent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ItemContent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ItemContent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ItemContent> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ItemContent update(ItemContent itemContent) {
		return getPersistence().update(itemContent);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ItemContent update(
		ItemContent itemContent, ServiceContext serviceContext) {

		return getPersistence().update(itemContent, serviceContext);
	}

	/**
	 * Returns all the item contents where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching item contents
	 */
	public static List<ItemContent> findByprogressionItemId(
		long progressionItemId) {

		return getPersistence().findByprogressionItemId(progressionItemId);
	}

	/**
	 * Returns a range of all the item contents where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @return the range of matching item contents
	 */
	public static List<ItemContent> findByprogressionItemId(
		long progressionItemId, int start, int end) {

		return getPersistence().findByprogressionItemId(
			progressionItemId, start, end);
	}

	/**
	 * Returns an ordered range of all the item contents where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item contents
	 */
	public static List<ItemContent> findByprogressionItemId(
		long progressionItemId, int start, int end,
		OrderByComparator<ItemContent> orderByComparator) {

		return getPersistence().findByprogressionItemId(
			progressionItemId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the item contents where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching item contents
	 */
	public static List<ItemContent> findByprogressionItemId(
		long progressionItemId, int start, int end,
		OrderByComparator<ItemContent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByprogressionItemId(
			progressionItemId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item content
	 * @throws NoSuchItemContentException if a matching item content could not be found
	 */
	public static ItemContent findByprogressionItemId_First(
			long progressionItemId,
			OrderByComparator<ItemContent> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchItemContentException {

		return getPersistence().findByprogressionItemId_First(
			progressionItemId, orderByComparator);
	}

	/**
	 * Returns the first item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item content, or <code>null</code> if a matching item content could not be found
	 */
	public static ItemContent fetchByprogressionItemId_First(
		long progressionItemId,
		OrderByComparator<ItemContent> orderByComparator) {

		return getPersistence().fetchByprogressionItemId_First(
			progressionItemId, orderByComparator);
	}

	/**
	 * Returns the last item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item content
	 * @throws NoSuchItemContentException if a matching item content could not be found
	 */
	public static ItemContent findByprogressionItemId_Last(
			long progressionItemId,
			OrderByComparator<ItemContent> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchItemContentException {

		return getPersistence().findByprogressionItemId_Last(
			progressionItemId, orderByComparator);
	}

	/**
	 * Returns the last item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item content, or <code>null</code> if a matching item content could not be found
	 */
	public static ItemContent fetchByprogressionItemId_Last(
		long progressionItemId,
		OrderByComparator<ItemContent> orderByComparator) {

		return getPersistence().fetchByprogressionItemId_Last(
			progressionItemId, orderByComparator);
	}

	/**
	 * Returns the item contents before and after the current item content in the ordered set where progressionItemId = &#63;.
	 *
	 * @param contentId the primary key of the current item content
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item content
	 * @throws NoSuchItemContentException if a item content with the primary key could not be found
	 */
	public static ItemContent[] findByprogressionItemId_PrevAndNext(
			long contentId, long progressionItemId,
			OrderByComparator<ItemContent> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchItemContentException {

		return getPersistence().findByprogressionItemId_PrevAndNext(
			contentId, progressionItemId, orderByComparator);
	}

	/**
	 * Removes all the item contents where progressionItemId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 */
	public static void removeByprogressionItemId(long progressionItemId) {
		getPersistence().removeByprogressionItemId(progressionItemId);
	}

	/**
	 * Returns the number of item contents where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the number of matching item contents
	 */
	public static int countByprogressionItemId(long progressionItemId) {
		return getPersistence().countByprogressionItemId(progressionItemId);
	}

	/**
	 * Caches the item content in the entity cache if it is enabled.
	 *
	 * @param itemContent the item content
	 */
	public static void cacheResult(ItemContent itemContent) {
		getPersistence().cacheResult(itemContent);
	}

	/**
	 * Caches the item contents in the entity cache if it is enabled.
	 *
	 * @param itemContents the item contents
	 */
	public static void cacheResult(List<ItemContent> itemContents) {
		getPersistence().cacheResult(itemContents);
	}

	/**
	 * Creates a new item content with the primary key. Does not add the item content to the database.
	 *
	 * @param contentId the primary key for the new item content
	 * @return the new item content
	 */
	public static ItemContent create(long contentId) {
		return getPersistence().create(contentId);
	}

	/**
	 * Removes the item content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contentId the primary key of the item content
	 * @return the item content that was removed
	 * @throws NoSuchItemContentException if a item content with the primary key could not be found
	 */
	public static ItemContent remove(long contentId)
		throws com.weprode.nero.progression.exception.
			NoSuchItemContentException {

		return getPersistence().remove(contentId);
	}

	public static ItemContent updateImpl(ItemContent itemContent) {
		return getPersistence().updateImpl(itemContent);
	}

	/**
	 * Returns the item content with the primary key or throws a <code>NoSuchItemContentException</code> if it could not be found.
	 *
	 * @param contentId the primary key of the item content
	 * @return the item content
	 * @throws NoSuchItemContentException if a item content with the primary key could not be found
	 */
	public static ItemContent findByPrimaryKey(long contentId)
		throws com.weprode.nero.progression.exception.
			NoSuchItemContentException {

		return getPersistence().findByPrimaryKey(contentId);
	}

	/**
	 * Returns the item content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contentId the primary key of the item content
	 * @return the item content, or <code>null</code> if a item content with the primary key could not be found
	 */
	public static ItemContent fetchByPrimaryKey(long contentId) {
		return getPersistence().fetchByPrimaryKey(contentId);
	}

	/**
	 * Returns all the item contents.
	 *
	 * @return the item contents
	 */
	public static List<ItemContent> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the item contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @return the range of item contents
	 */
	public static List<ItemContent> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the item contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item contents
	 */
	public static List<ItemContent> findAll(
		int start, int end, OrderByComparator<ItemContent> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the item contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemContentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item contents
	 * @param end the upper bound of the range of item contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of item contents
	 */
	public static List<ItemContent> findAll(
		int start, int end, OrderByComparator<ItemContent> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the item contents from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of item contents.
	 *
	 * @return the number of item contents
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ItemContentPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ItemContentPersistence _persistence;

}