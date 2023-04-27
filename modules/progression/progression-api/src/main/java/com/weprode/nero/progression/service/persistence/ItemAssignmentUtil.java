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

import com.weprode.nero.progression.model.ItemAssignment;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the item assignment service. This utility wraps <code>com.weprode.nero.progression.service.persistence.impl.ItemAssignmentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ItemAssignmentPersistence
 * @generated
 */
public class ItemAssignmentUtil {

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
	public static void clearCache(ItemAssignment itemAssignment) {
		getPersistence().clearCache(itemAssignment);
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
	public static Map<Serializable, ItemAssignment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ItemAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ItemAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ItemAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ItemAssignment update(ItemAssignment itemAssignment) {
		return getPersistence().update(itemAssignment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ItemAssignment update(
		ItemAssignment itemAssignment, ServiceContext serviceContext) {

		return getPersistence().update(itemAssignment, serviceContext);
	}

	/**
	 * Returns the item assignment where progressionItemId = &#63; and sessionId = &#63; or throws a <code>NoSuchItemAssignmentException</code> if it could not be found.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @return the matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public static ItemAssignment findByprogressionItemId_sessionId(
			long progressionItemId, long sessionId)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().findByprogressionItemId_sessionId(
			progressionItemId, sessionId);
	}

	/**
	 * Returns the item assignment where progressionItemId = &#63; and sessionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @return the matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public static ItemAssignment fetchByprogressionItemId_sessionId(
		long progressionItemId, long sessionId) {

		return getPersistence().fetchByprogressionItemId_sessionId(
			progressionItemId, sessionId);
	}

	/**
	 * Returns the item assignment where progressionItemId = &#63; and sessionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public static ItemAssignment fetchByprogressionItemId_sessionId(
		long progressionItemId, long sessionId, boolean useFinderCache) {

		return getPersistence().fetchByprogressionItemId_sessionId(
			progressionItemId, sessionId, useFinderCache);
	}

	/**
	 * Removes the item assignment where progressionItemId = &#63; and sessionId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @return the item assignment that was removed
	 */
	public static ItemAssignment removeByprogressionItemId_sessionId(
			long progressionItemId, long sessionId)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().removeByprogressionItemId_sessionId(
			progressionItemId, sessionId);
	}

	/**
	 * Returns the number of item assignments where progressionItemId = &#63; and sessionId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @return the number of matching item assignments
	 */
	public static int countByprogressionItemId_sessionId(
		long progressionItemId, long sessionId) {

		return getPersistence().countByprogressionItemId_sessionId(
			progressionItemId, sessionId);
	}

	/**
	 * Returns the item assignment where progressionItemId = &#63; and homeworkId = &#63; or throws a <code>NoSuchItemAssignmentException</code> if it could not be found.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @return the matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public static ItemAssignment findByprogressionItemId_homeworkId(
			long progressionItemId, long homeworkId)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().findByprogressionItemId_homeworkId(
			progressionItemId, homeworkId);
	}

	/**
	 * Returns the item assignment where progressionItemId = &#63; and homeworkId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @return the matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public static ItemAssignment fetchByprogressionItemId_homeworkId(
		long progressionItemId, long homeworkId) {

		return getPersistence().fetchByprogressionItemId_homeworkId(
			progressionItemId, homeworkId);
	}

	/**
	 * Returns the item assignment where progressionItemId = &#63; and homeworkId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public static ItemAssignment fetchByprogressionItemId_homeworkId(
		long progressionItemId, long homeworkId, boolean useFinderCache) {

		return getPersistence().fetchByprogressionItemId_homeworkId(
			progressionItemId, homeworkId, useFinderCache);
	}

	/**
	 * Removes the item assignment where progressionItemId = &#63; and homeworkId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @return the item assignment that was removed
	 */
	public static ItemAssignment removeByprogressionItemId_homeworkId(
			long progressionItemId, long homeworkId)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().removeByprogressionItemId_homeworkId(
			progressionItemId, homeworkId);
	}

	/**
	 * Returns the number of item assignments where progressionItemId = &#63; and homeworkId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @return the number of matching item assignments
	 */
	public static int countByprogressionItemId_homeworkId(
		long progressionItemId, long homeworkId) {

		return getPersistence().countByprogressionItemId_homeworkId(
			progressionItemId, homeworkId);
	}

	/**
	 * Returns all the item assignments where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching item assignments
	 */
	public static List<ItemAssignment> findByprogressionItemId(
		long progressionItemId) {

		return getPersistence().findByprogressionItemId(progressionItemId);
	}

	/**
	 * Returns a range of all the item assignments where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @return the range of matching item assignments
	 */
	public static List<ItemAssignment> findByprogressionItemId(
		long progressionItemId, int start, int end) {

		return getPersistence().findByprogressionItemId(
			progressionItemId, start, end);
	}

	/**
	 * Returns an ordered range of all the item assignments where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item assignments
	 */
	public static List<ItemAssignment> findByprogressionItemId(
		long progressionItemId, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator) {

		return getPersistence().findByprogressionItemId(
			progressionItemId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the item assignments where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching item assignments
	 */
	public static List<ItemAssignment> findByprogressionItemId(
		long progressionItemId, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByprogressionItemId(
			progressionItemId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public static ItemAssignment findByprogressionItemId_First(
			long progressionItemId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().findByprogressionItemId_First(
			progressionItemId, orderByComparator);
	}

	/**
	 * Returns the first item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public static ItemAssignment fetchByprogressionItemId_First(
		long progressionItemId,
		OrderByComparator<ItemAssignment> orderByComparator) {

		return getPersistence().fetchByprogressionItemId_First(
			progressionItemId, orderByComparator);
	}

	/**
	 * Returns the last item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public static ItemAssignment findByprogressionItemId_Last(
			long progressionItemId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().findByprogressionItemId_Last(
			progressionItemId, orderByComparator);
	}

	/**
	 * Returns the last item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public static ItemAssignment fetchByprogressionItemId_Last(
		long progressionItemId,
		OrderByComparator<ItemAssignment> orderByComparator) {

		return getPersistence().fetchByprogressionItemId_Last(
			progressionItemId, orderByComparator);
	}

	/**
	 * Returns the item assignments before and after the current item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param itemAssignmentPK the primary key of the current item assignment
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	public static ItemAssignment[] findByprogressionItemId_PrevAndNext(
			ItemAssignmentPK itemAssignmentPK, long progressionItemId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().findByprogressionItemId_PrevAndNext(
			itemAssignmentPK, progressionItemId, orderByComparator);
	}

	/**
	 * Removes all the item assignments where progressionItemId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 */
	public static void removeByprogressionItemId(long progressionItemId) {
		getPersistence().removeByprogressionItemId(progressionItemId);
	}

	/**
	 * Returns the number of item assignments where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the number of matching item assignments
	 */
	public static int countByprogressionItemId(long progressionItemId) {
		return getPersistence().countByprogressionItemId(progressionItemId);
	}

	/**
	 * Returns all the item assignments where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the matching item assignments
	 */
	public static List<ItemAssignment> findBysessionId(long sessionId) {
		return getPersistence().findBysessionId(sessionId);
	}

	/**
	 * Returns a range of all the item assignments where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @return the range of matching item assignments
	 */
	public static List<ItemAssignment> findBysessionId(
		long sessionId, int start, int end) {

		return getPersistence().findBysessionId(sessionId, start, end);
	}

	/**
	 * Returns an ordered range of all the item assignments where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item assignments
	 */
	public static List<ItemAssignment> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator) {

		return getPersistence().findBysessionId(
			sessionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the item assignments where sessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param sessionId the session ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching item assignments
	 */
	public static List<ItemAssignment> findBysessionId(
		long sessionId, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findBysessionId(
			sessionId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public static ItemAssignment findBysessionId_First(
			long sessionId, OrderByComparator<ItemAssignment> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().findBysessionId_First(
			sessionId, orderByComparator);
	}

	/**
	 * Returns the first item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public static ItemAssignment fetchBysessionId_First(
		long sessionId, OrderByComparator<ItemAssignment> orderByComparator) {

		return getPersistence().fetchBysessionId_First(
			sessionId, orderByComparator);
	}

	/**
	 * Returns the last item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public static ItemAssignment findBysessionId_Last(
			long sessionId, OrderByComparator<ItemAssignment> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().findBysessionId_Last(
			sessionId, orderByComparator);
	}

	/**
	 * Returns the last item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public static ItemAssignment fetchBysessionId_Last(
		long sessionId, OrderByComparator<ItemAssignment> orderByComparator) {

		return getPersistence().fetchBysessionId_Last(
			sessionId, orderByComparator);
	}

	/**
	 * Returns the item assignments before and after the current item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param itemAssignmentPK the primary key of the current item assignment
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	public static ItemAssignment[] findBysessionId_PrevAndNext(
			ItemAssignmentPK itemAssignmentPK, long sessionId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().findBysessionId_PrevAndNext(
			itemAssignmentPK, sessionId, orderByComparator);
	}

	/**
	 * Removes all the item assignments where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 */
	public static void removeBysessionId(long sessionId) {
		getPersistence().removeBysessionId(sessionId);
	}

	/**
	 * Returns the number of item assignments where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching item assignments
	 */
	public static int countBysessionId(long sessionId) {
		return getPersistence().countBysessionId(sessionId);
	}

	/**
	 * Returns all the item assignments where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the matching item assignments
	 */
	public static List<ItemAssignment> findByhomeworkId(long homeworkId) {
		return getPersistence().findByhomeworkId(homeworkId);
	}

	/**
	 * Returns a range of all the item assignments where homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @return the range of matching item assignments
	 */
	public static List<ItemAssignment> findByhomeworkId(
		long homeworkId, int start, int end) {

		return getPersistence().findByhomeworkId(homeworkId, start, end);
	}

	/**
	 * Returns an ordered range of all the item assignments where homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item assignments
	 */
	public static List<ItemAssignment> findByhomeworkId(
		long homeworkId, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator) {

		return getPersistence().findByhomeworkId(
			homeworkId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the item assignments where homeworkId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param homeworkId the homework ID
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching item assignments
	 */
	public static List<ItemAssignment> findByhomeworkId(
		long homeworkId, int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByhomeworkId(
			homeworkId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public static ItemAssignment findByhomeworkId_First(
			long homeworkId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().findByhomeworkId_First(
			homeworkId, orderByComparator);
	}

	/**
	 * Returns the first item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public static ItemAssignment fetchByhomeworkId_First(
		long homeworkId, OrderByComparator<ItemAssignment> orderByComparator) {

		return getPersistence().fetchByhomeworkId_First(
			homeworkId, orderByComparator);
	}

	/**
	 * Returns the last item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public static ItemAssignment findByhomeworkId_Last(
			long homeworkId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().findByhomeworkId_Last(
			homeworkId, orderByComparator);
	}

	/**
	 * Returns the last item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public static ItemAssignment fetchByhomeworkId_Last(
		long homeworkId, OrderByComparator<ItemAssignment> orderByComparator) {

		return getPersistence().fetchByhomeworkId_Last(
			homeworkId, orderByComparator);
	}

	/**
	 * Returns the item assignments before and after the current item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param itemAssignmentPK the primary key of the current item assignment
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	public static ItemAssignment[] findByhomeworkId_PrevAndNext(
			ItemAssignmentPK itemAssignmentPK, long homeworkId,
			OrderByComparator<ItemAssignment> orderByComparator)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().findByhomeworkId_PrevAndNext(
			itemAssignmentPK, homeworkId, orderByComparator);
	}

	/**
	 * Removes all the item assignments where homeworkId = &#63; from the database.
	 *
	 * @param homeworkId the homework ID
	 */
	public static void removeByhomeworkId(long homeworkId) {
		getPersistence().removeByhomeworkId(homeworkId);
	}

	/**
	 * Returns the number of item assignments where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the number of matching item assignments
	 */
	public static int countByhomeworkId(long homeworkId) {
		return getPersistence().countByhomeworkId(homeworkId);
	}

	/**
	 * Caches the item assignment in the entity cache if it is enabled.
	 *
	 * @param itemAssignment the item assignment
	 */
	public static void cacheResult(ItemAssignment itemAssignment) {
		getPersistence().cacheResult(itemAssignment);
	}

	/**
	 * Caches the item assignments in the entity cache if it is enabled.
	 *
	 * @param itemAssignments the item assignments
	 */
	public static void cacheResult(List<ItemAssignment> itemAssignments) {
		getPersistence().cacheResult(itemAssignments);
	}

	/**
	 * Creates a new item assignment with the primary key. Does not add the item assignment to the database.
	 *
	 * @param itemAssignmentPK the primary key for the new item assignment
	 * @return the new item assignment
	 */
	public static ItemAssignment create(ItemAssignmentPK itemAssignmentPK) {
		return getPersistence().create(itemAssignmentPK);
	}

	/**
	 * Removes the item assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment that was removed
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	public static ItemAssignment remove(ItemAssignmentPK itemAssignmentPK)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().remove(itemAssignmentPK);
	}

	public static ItemAssignment updateImpl(ItemAssignment itemAssignment) {
		return getPersistence().updateImpl(itemAssignment);
	}

	/**
	 * Returns the item assignment with the primary key or throws a <code>NoSuchItemAssignmentException</code> if it could not be found.
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	public static ItemAssignment findByPrimaryKey(
			ItemAssignmentPK itemAssignmentPK)
		throws com.weprode.nero.progression.exception.
			NoSuchItemAssignmentException {

		return getPersistence().findByPrimaryKey(itemAssignmentPK);
	}

	/**
	 * Returns the item assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment, or <code>null</code> if a item assignment with the primary key could not be found
	 */
	public static ItemAssignment fetchByPrimaryKey(
		ItemAssignmentPK itemAssignmentPK) {

		return getPersistence().fetchByPrimaryKey(itemAssignmentPK);
	}

	/**
	 * Returns all the item assignments.
	 *
	 * @return the item assignments
	 */
	public static List<ItemAssignment> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the item assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @return the range of item assignments
	 */
	public static List<ItemAssignment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the item assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item assignments
	 */
	public static List<ItemAssignment> findAll(
		int start, int end,
		OrderByComparator<ItemAssignment> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the item assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item assignments
	 * @param end the upper bound of the range of item assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of item assignments
	 */
	public static List<ItemAssignment> findAll(
		int start, int end, OrderByComparator<ItemAssignment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the item assignments from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of item assignments.
	 *
	 * @return the number of item assignments
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static ItemAssignmentPersistence getPersistence() {
		return _persistence;
	}

	private static volatile ItemAssignmentPersistence _persistence;

}