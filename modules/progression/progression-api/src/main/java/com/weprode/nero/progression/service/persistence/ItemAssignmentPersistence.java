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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.weprode.nero.progression.exception.NoSuchItemAssignmentException;
import com.weprode.nero.progression.model.ItemAssignment;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the item assignment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ItemAssignmentUtil
 * @generated
 */
@ProviderType
public interface ItemAssignmentPersistence
	extends BasePersistence<ItemAssignment> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItemAssignmentUtil} to access the item assignment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the item assignment where progressionItemId = &#63; and sessionId = &#63; or throws a <code>NoSuchItemAssignmentException</code> if it could not be found.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @return the matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public ItemAssignment findByprogressionItemId_sessionId(
			long progressionItemId, long sessionId)
		throws NoSuchItemAssignmentException;

	/**
	 * Returns the item assignment where progressionItemId = &#63; and sessionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @return the matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public ItemAssignment fetchByprogressionItemId_sessionId(
		long progressionItemId, long sessionId);

	/**
	 * Returns the item assignment where progressionItemId = &#63; and sessionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public ItemAssignment fetchByprogressionItemId_sessionId(
		long progressionItemId, long sessionId, boolean useFinderCache);

	/**
	 * Removes the item assignment where progressionItemId = &#63; and sessionId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @return the item assignment that was removed
	 */
	public ItemAssignment removeByprogressionItemId_sessionId(
			long progressionItemId, long sessionId)
		throws NoSuchItemAssignmentException;

	/**
	 * Returns the number of item assignments where progressionItemId = &#63; and sessionId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param sessionId the session ID
	 * @return the number of matching item assignments
	 */
	public int countByprogressionItemId_sessionId(
		long progressionItemId, long sessionId);

	/**
	 * Returns the item assignment where progressionItemId = &#63; and homeworkId = &#63; or throws a <code>NoSuchItemAssignmentException</code> if it could not be found.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @return the matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public ItemAssignment findByprogressionItemId_homeworkId(
			long progressionItemId, long homeworkId)
		throws NoSuchItemAssignmentException;

	/**
	 * Returns the item assignment where progressionItemId = &#63; and homeworkId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @return the matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public ItemAssignment fetchByprogressionItemId_homeworkId(
		long progressionItemId, long homeworkId);

	/**
	 * Returns the item assignment where progressionItemId = &#63; and homeworkId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public ItemAssignment fetchByprogressionItemId_homeworkId(
		long progressionItemId, long homeworkId, boolean useFinderCache);

	/**
	 * Removes the item assignment where progressionItemId = &#63; and homeworkId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @return the item assignment that was removed
	 */
	public ItemAssignment removeByprogressionItemId_homeworkId(
			long progressionItemId, long homeworkId)
		throws NoSuchItemAssignmentException;

	/**
	 * Returns the number of item assignments where progressionItemId = &#63; and homeworkId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param homeworkId the homework ID
	 * @return the number of matching item assignments
	 */
	public int countByprogressionItemId_homeworkId(
		long progressionItemId, long homeworkId);

	/**
	 * Returns all the item assignments where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching item assignments
	 */
	public java.util.List<ItemAssignment> findByprogressionItemId(
		long progressionItemId);

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
	public java.util.List<ItemAssignment> findByprogressionItemId(
		long progressionItemId, int start, int end);

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
	public java.util.List<ItemAssignment> findByprogressionItemId(
		long progressionItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator);

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
	public java.util.List<ItemAssignment> findByprogressionItemId(
		long progressionItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public ItemAssignment findByprogressionItemId_First(
			long progressionItemId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
				orderByComparator)
		throws NoSuchItemAssignmentException;

	/**
	 * Returns the first item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public ItemAssignment fetchByprogressionItemId_First(
		long progressionItemId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator);

	/**
	 * Returns the last item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public ItemAssignment findByprogressionItemId_Last(
			long progressionItemId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
				orderByComparator)
		throws NoSuchItemAssignmentException;

	/**
	 * Returns the last item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public ItemAssignment fetchByprogressionItemId_Last(
		long progressionItemId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator);

	/**
	 * Returns the item assignments before and after the current item assignment in the ordered set where progressionItemId = &#63;.
	 *
	 * @param itemAssignmentPK the primary key of the current item assignment
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	public ItemAssignment[] findByprogressionItemId_PrevAndNext(
			ItemAssignmentPK itemAssignmentPK, long progressionItemId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
				orderByComparator)
		throws NoSuchItemAssignmentException;

	/**
	 * Removes all the item assignments where progressionItemId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 */
	public void removeByprogressionItemId(long progressionItemId);

	/**
	 * Returns the number of item assignments where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the number of matching item assignments
	 */
	public int countByprogressionItemId(long progressionItemId);

	/**
	 * Returns all the item assignments where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the matching item assignments
	 */
	public java.util.List<ItemAssignment> findBysessionId(long sessionId);

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
	public java.util.List<ItemAssignment> findBysessionId(
		long sessionId, int start, int end);

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
	public java.util.List<ItemAssignment> findBysessionId(
		long sessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator);

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
	public java.util.List<ItemAssignment> findBysessionId(
		long sessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public ItemAssignment findBysessionId_First(
			long sessionId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
				orderByComparator)
		throws NoSuchItemAssignmentException;

	/**
	 * Returns the first item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public ItemAssignment fetchBysessionId_First(
		long sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator);

	/**
	 * Returns the last item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public ItemAssignment findBysessionId_Last(
			long sessionId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
				orderByComparator)
		throws NoSuchItemAssignmentException;

	/**
	 * Returns the last item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public ItemAssignment fetchBysessionId_Last(
		long sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator);

	/**
	 * Returns the item assignments before and after the current item assignment in the ordered set where sessionId = &#63;.
	 *
	 * @param itemAssignmentPK the primary key of the current item assignment
	 * @param sessionId the session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	public ItemAssignment[] findBysessionId_PrevAndNext(
			ItemAssignmentPK itemAssignmentPK, long sessionId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
				orderByComparator)
		throws NoSuchItemAssignmentException;

	/**
	 * Removes all the item assignments where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 */
	public void removeBysessionId(long sessionId);

	/**
	 * Returns the number of item assignments where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching item assignments
	 */
	public int countBysessionId(long sessionId);

	/**
	 * Returns all the item assignments where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the matching item assignments
	 */
	public java.util.List<ItemAssignment> findByhomeworkId(long homeworkId);

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
	public java.util.List<ItemAssignment> findByhomeworkId(
		long homeworkId, int start, int end);

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
	public java.util.List<ItemAssignment> findByhomeworkId(
		long homeworkId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator);

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
	public java.util.List<ItemAssignment> findByhomeworkId(
		long homeworkId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public ItemAssignment findByhomeworkId_First(
			long homeworkId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
				orderByComparator)
		throws NoSuchItemAssignmentException;

	/**
	 * Returns the first item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public ItemAssignment fetchByhomeworkId_First(
		long homeworkId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator);

	/**
	 * Returns the last item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment
	 * @throws NoSuchItemAssignmentException if a matching item assignment could not be found
	 */
	public ItemAssignment findByhomeworkId_Last(
			long homeworkId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
				orderByComparator)
		throws NoSuchItemAssignmentException;

	/**
	 * Returns the last item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item assignment, or <code>null</code> if a matching item assignment could not be found
	 */
	public ItemAssignment fetchByhomeworkId_Last(
		long homeworkId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator);

	/**
	 * Returns the item assignments before and after the current item assignment in the ordered set where homeworkId = &#63;.
	 *
	 * @param itemAssignmentPK the primary key of the current item assignment
	 * @param homeworkId the homework ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	public ItemAssignment[] findByhomeworkId_PrevAndNext(
			ItemAssignmentPK itemAssignmentPK, long homeworkId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
				orderByComparator)
		throws NoSuchItemAssignmentException;

	/**
	 * Removes all the item assignments where homeworkId = &#63; from the database.
	 *
	 * @param homeworkId the homework ID
	 */
	public void removeByhomeworkId(long homeworkId);

	/**
	 * Returns the number of item assignments where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the number of matching item assignments
	 */
	public int countByhomeworkId(long homeworkId);

	/**
	 * Caches the item assignment in the entity cache if it is enabled.
	 *
	 * @param itemAssignment the item assignment
	 */
	public void cacheResult(ItemAssignment itemAssignment);

	/**
	 * Caches the item assignments in the entity cache if it is enabled.
	 *
	 * @param itemAssignments the item assignments
	 */
	public void cacheResult(java.util.List<ItemAssignment> itemAssignments);

	/**
	 * Creates a new item assignment with the primary key. Does not add the item assignment to the database.
	 *
	 * @param itemAssignmentPK the primary key for the new item assignment
	 * @return the new item assignment
	 */
	public ItemAssignment create(ItemAssignmentPK itemAssignmentPK);

	/**
	 * Removes the item assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment that was removed
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	public ItemAssignment remove(ItemAssignmentPK itemAssignmentPK)
		throws NoSuchItemAssignmentException;

	public ItemAssignment updateImpl(ItemAssignment itemAssignment);

	/**
	 * Returns the item assignment with the primary key or throws a <code>NoSuchItemAssignmentException</code> if it could not be found.
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment
	 * @throws NoSuchItemAssignmentException if a item assignment with the primary key could not be found
	 */
	public ItemAssignment findByPrimaryKey(ItemAssignmentPK itemAssignmentPK)
		throws NoSuchItemAssignmentException;

	/**
	 * Returns the item assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemAssignmentPK the primary key of the item assignment
	 * @return the item assignment, or <code>null</code> if a item assignment with the primary key could not be found
	 */
	public ItemAssignment fetchByPrimaryKey(ItemAssignmentPK itemAssignmentPK);

	/**
	 * Returns all the item assignments.
	 *
	 * @return the item assignments
	 */
	public java.util.List<ItemAssignment> findAll();

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
	public java.util.List<ItemAssignment> findAll(int start, int end);

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
	public java.util.List<ItemAssignment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator);

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
	public java.util.List<ItemAssignment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAssignment>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the item assignments from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of item assignments.
	 *
	 * @return the number of item assignments
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}