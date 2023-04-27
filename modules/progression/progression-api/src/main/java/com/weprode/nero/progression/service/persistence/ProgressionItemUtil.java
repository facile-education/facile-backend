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

import com.weprode.nero.progression.model.ProgressionItem;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the progression item service. This utility wraps <code>com.weprode.nero.progression.service.persistence.impl.ProgressionItemPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionItemPersistence
 * @generated
 */
public class ProgressionItemUtil {

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
	public static void clearCache(ProgressionItem progressionItem) {
		getPersistence().clearCache(progressionItem);
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
	public static Map<Serializable, ProgressionItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProgressionItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProgressionItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProgressionItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProgressionItem update(ProgressionItem progressionItem) {
		return getPersistence().update(progressionItem);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProgressionItem update(
		ProgressionItem progressionItem, ServiceContext serviceContext) {

		return getPersistence().update(progressionItem, serviceContext);
	}

	/**
	 * Returns the progression item where progressionItemId = &#63; or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public static ProgressionItem findByprogressionItemId(
			long progressionItemId)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().findByprogressionItemId(progressionItemId);
	}

	/**
	 * Returns the progression item where progressionItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public static ProgressionItem fetchByprogressionItemId(
		long progressionItemId) {

		return getPersistence().fetchByprogressionItemId(progressionItemId);
	}

	/**
	 * Returns the progression item where progressionItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public static ProgressionItem fetchByprogressionItemId(
		long progressionItemId, boolean useFinderCache) {

		return getPersistence().fetchByprogressionItemId(
			progressionItemId, useFinderCache);
	}

	/**
	 * Removes the progression item where progressionItemId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the progression item that was removed
	 */
	public static ProgressionItem removeByprogressionItemId(
			long progressionItemId)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().removeByprogressionItemId(progressionItemId);
	}

	/**
	 * Returns the number of progression items where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the number of matching progression items
	 */
	public static int countByprogressionItemId(long progressionItemId) {
		return getPersistence().countByprogressionItemId(progressionItemId);
	}

	/**
	 * Returns the progression item where sessionId = &#63; or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param sessionId the session ID
	 * @return the matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public static ProgressionItem findBysessionId(long sessionId)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().findBysessionId(sessionId);
	}

	/**
	 * Returns the progression item where sessionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sessionId the session ID
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public static ProgressionItem fetchBysessionId(long sessionId) {
		return getPersistence().fetchBysessionId(sessionId);
	}

	/**
	 * Returns the progression item where sessionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public static ProgressionItem fetchBysessionId(
		long sessionId, boolean useFinderCache) {

		return getPersistence().fetchBysessionId(sessionId, useFinderCache);
	}

	/**
	 * Removes the progression item where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @return the progression item that was removed
	 */
	public static ProgressionItem removeBysessionId(long sessionId)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().removeBysessionId(sessionId);
	}

	/**
	 * Returns the number of progression items where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching progression items
	 */
	public static int countBysessionId(long sessionId) {
		return getPersistence().countBysessionId(sessionId);
	}

	/**
	 * Returns the progression item where homeworkId = &#63; or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param homeworkId the homework ID
	 * @return the matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public static ProgressionItem findByhomeworkId(long homeworkId)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().findByhomeworkId(homeworkId);
	}

	/**
	 * Returns the progression item where homeworkId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param homeworkId the homework ID
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public static ProgressionItem fetchByhomeworkId(long homeworkId) {
		return getPersistence().fetchByhomeworkId(homeworkId);
	}

	/**
	 * Returns the progression item where homeworkId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param homeworkId the homework ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public static ProgressionItem fetchByhomeworkId(
		long homeworkId, boolean useFinderCache) {

		return getPersistence().fetchByhomeworkId(homeworkId, useFinderCache);
	}

	/**
	 * Removes the progression item where homeworkId = &#63; from the database.
	 *
	 * @param homeworkId the homework ID
	 * @return the progression item that was removed
	 */
	public static ProgressionItem removeByhomeworkId(long homeworkId)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().removeByhomeworkId(homeworkId);
	}

	/**
	 * Returns the number of progression items where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the number of matching progression items
	 */
	public static int countByhomeworkId(long homeworkId) {
		return getPersistence().countByhomeworkId(homeworkId);
	}

	/**
	 * Returns all the progression items where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the matching progression items
	 */
	public static List<ProgressionItem> findByprogressionFolderId(
		long progressionFolderId) {

		return getPersistence().findByprogressionFolderId(progressionFolderId);
	}

	/**
	 * Returns a range of all the progression items where progressionFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @return the range of matching progression items
	 */
	public static List<ProgressionItem> findByprogressionFolderId(
		long progressionFolderId, int start, int end) {

		return getPersistence().findByprogressionFolderId(
			progressionFolderId, start, end);
	}

	/**
	 * Returns an ordered range of all the progression items where progressionFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching progression items
	 */
	public static List<ProgressionItem> findByprogressionFolderId(
		long progressionFolderId, int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator) {

		return getPersistence().findByprogressionFolderId(
			progressionFolderId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the progression items where progressionFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching progression items
	 */
	public static List<ProgressionItem> findByprogressionFolderId(
		long progressionFolderId, int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByprogressionFolderId(
			progressionFolderId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public static ProgressionItem findByprogressionFolderId_First(
			long progressionFolderId,
			OrderByComparator<ProgressionItem> orderByComparator)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().findByprogressionFolderId_First(
			progressionFolderId, orderByComparator);
	}

	/**
	 * Returns the first progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public static ProgressionItem fetchByprogressionFolderId_First(
		long progressionFolderId,
		OrderByComparator<ProgressionItem> orderByComparator) {

		return getPersistence().fetchByprogressionFolderId_First(
			progressionFolderId, orderByComparator);
	}

	/**
	 * Returns the last progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public static ProgressionItem findByprogressionFolderId_Last(
			long progressionFolderId,
			OrderByComparator<ProgressionItem> orderByComparator)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().findByprogressionFolderId_Last(
			progressionFolderId, orderByComparator);
	}

	/**
	 * Returns the last progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public static ProgressionItem fetchByprogressionFolderId_Last(
		long progressionFolderId,
		OrderByComparator<ProgressionItem> orderByComparator) {

		return getPersistence().fetchByprogressionFolderId_Last(
			progressionFolderId, orderByComparator);
	}

	/**
	 * Returns the progression items before and after the current progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionItemId the primary key of the current progression item
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression item
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	public static ProgressionItem[] findByprogressionFolderId_PrevAndNext(
			long progressionItemId, long progressionFolderId,
			OrderByComparator<ProgressionItem> orderByComparator)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().findByprogressionFolderId_PrevAndNext(
			progressionItemId, progressionFolderId, orderByComparator);
	}

	/**
	 * Removes all the progression items where progressionFolderId = &#63; from the database.
	 *
	 * @param progressionFolderId the progression folder ID
	 */
	public static void removeByprogressionFolderId(long progressionFolderId) {
		getPersistence().removeByprogressionFolderId(progressionFolderId);
	}

	/**
	 * Returns the number of progression items where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the number of matching progression items
	 */
	public static int countByprogressionFolderId(long progressionFolderId) {
		return getPersistence().countByprogressionFolderId(progressionFolderId);
	}

	/**
	 * Returns all the progression items where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @return the matching progression items
	 */
	public static List<ProgressionItem> findByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId) {

		return getPersistence().findByprogressionFolderId_progressionId(
			progressionFolderId, progressionId);
	}

	/**
	 * Returns a range of all the progression items where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @return the range of matching progression items
	 */
	public static List<ProgressionItem> findByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId, int start, int end) {

		return getPersistence().findByprogressionFolderId_progressionId(
			progressionFolderId, progressionId, start, end);
	}

	/**
	 * Returns an ordered range of all the progression items where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching progression items
	 */
	public static List<ProgressionItem> findByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId, int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator) {

		return getPersistence().findByprogressionFolderId_progressionId(
			progressionFolderId, progressionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the progression items where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching progression items
	 */
	public static List<ProgressionItem> findByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId, int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByprogressionFolderId_progressionId(
			progressionFolderId, progressionId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public static ProgressionItem findByprogressionFolderId_progressionId_First(
			long progressionFolderId, long progressionId,
			OrderByComparator<ProgressionItem> orderByComparator)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().findByprogressionFolderId_progressionId_First(
			progressionFolderId, progressionId, orderByComparator);
	}

	/**
	 * Returns the first progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public static ProgressionItem
		fetchByprogressionFolderId_progressionId_First(
			long progressionFolderId, long progressionId,
			OrderByComparator<ProgressionItem> orderByComparator) {

		return getPersistence().fetchByprogressionFolderId_progressionId_First(
			progressionFolderId, progressionId, orderByComparator);
	}

	/**
	 * Returns the last progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public static ProgressionItem findByprogressionFolderId_progressionId_Last(
			long progressionFolderId, long progressionId,
			OrderByComparator<ProgressionItem> orderByComparator)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().findByprogressionFolderId_progressionId_Last(
			progressionFolderId, progressionId, orderByComparator);
	}

	/**
	 * Returns the last progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public static ProgressionItem fetchByprogressionFolderId_progressionId_Last(
		long progressionFolderId, long progressionId,
		OrderByComparator<ProgressionItem> orderByComparator) {

		return getPersistence().fetchByprogressionFolderId_progressionId_Last(
			progressionFolderId, progressionId, orderByComparator);
	}

	/**
	 * Returns the progression items before and after the current progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionItemId the primary key of the current progression item
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression item
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	public static ProgressionItem[]
			findByprogressionFolderId_progressionId_PrevAndNext(
				long progressionItemId, long progressionFolderId,
				long progressionId,
				OrderByComparator<ProgressionItem> orderByComparator)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().
			findByprogressionFolderId_progressionId_PrevAndNext(
				progressionItemId, progressionFolderId, progressionId,
				orderByComparator);
	}

	/**
	 * Removes all the progression items where progressionFolderId = &#63; and progressionId = &#63; from the database.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 */
	public static void removeByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId) {

		getPersistence().removeByprogressionFolderId_progressionId(
			progressionFolderId, progressionId);
	}

	/**
	 * Returns the number of progression items where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @return the number of matching progression items
	 */
	public static int countByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId) {

		return getPersistence().countByprogressionFolderId_progressionId(
			progressionFolderId, progressionId);
	}

	/**
	 * Caches the progression item in the entity cache if it is enabled.
	 *
	 * @param progressionItem the progression item
	 */
	public static void cacheResult(ProgressionItem progressionItem) {
		getPersistence().cacheResult(progressionItem);
	}

	/**
	 * Caches the progression items in the entity cache if it is enabled.
	 *
	 * @param progressionItems the progression items
	 */
	public static void cacheResult(List<ProgressionItem> progressionItems) {
		getPersistence().cacheResult(progressionItems);
	}

	/**
	 * Creates a new progression item with the primary key. Does not add the progression item to the database.
	 *
	 * @param progressionItemId the primary key for the new progression item
	 * @return the new progression item
	 */
	public static ProgressionItem create(long progressionItemId) {
		return getPersistence().create(progressionItemId);
	}

	/**
	 * Removes the progression item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param progressionItemId the primary key of the progression item
	 * @return the progression item that was removed
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	public static ProgressionItem remove(long progressionItemId)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().remove(progressionItemId);
	}

	public static ProgressionItem updateImpl(ProgressionItem progressionItem) {
		return getPersistence().updateImpl(progressionItem);
	}

	/**
	 * Returns the progression item with the primary key or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param progressionItemId the primary key of the progression item
	 * @return the progression item
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	public static ProgressionItem findByPrimaryKey(long progressionItemId)
		throws com.weprode.nero.progression.exception.NoSuchItemException {

		return getPersistence().findByPrimaryKey(progressionItemId);
	}

	/**
	 * Returns the progression item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param progressionItemId the primary key of the progression item
	 * @return the progression item, or <code>null</code> if a progression item with the primary key could not be found
	 */
	public static ProgressionItem fetchByPrimaryKey(long progressionItemId) {
		return getPersistence().fetchByPrimaryKey(progressionItemId);
	}

	/**
	 * Returns all the progression items.
	 *
	 * @return the progression items
	 */
	public static List<ProgressionItem> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the progression items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @return the range of progression items
	 */
	public static List<ProgressionItem> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the progression items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of progression items
	 */
	public static List<ProgressionItem> findAll(
		int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the progression items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProgressionItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of progression items
	 * @param end the upper bound of the range of progression items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of progression items
	 */
	public static List<ProgressionItem> findAll(
		int start, int end,
		OrderByComparator<ProgressionItem> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the progression items from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of progression items.
	 *
	 * @return the number of progression items
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ProgressionItemPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ProgressionItemPersistence _persistence;

}