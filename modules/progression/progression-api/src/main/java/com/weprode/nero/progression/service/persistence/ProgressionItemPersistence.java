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

import com.weprode.nero.progression.exception.NoSuchItemException;
import com.weprode.nero.progression.model.ProgressionItem;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the progression item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProgressionItemUtil
 * @generated
 */
@ProviderType
public interface ProgressionItemPersistence
	extends BasePersistence<ProgressionItem> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProgressionItemUtil} to access the progression item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the progression item where progressionItemId = &#63; or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public ProgressionItem findByprogressionItemId(long progressionItemId)
		throws NoSuchItemException;

	/**
	 * Returns the progression item where progressionItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public ProgressionItem fetchByprogressionItemId(long progressionItemId);

	/**
	 * Returns the progression item where progressionItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param progressionItemId the progression item ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public ProgressionItem fetchByprogressionItemId(
		long progressionItemId, boolean useFinderCache);

	/**
	 * Removes the progression item where progressionItemId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the progression item that was removed
	 */
	public ProgressionItem removeByprogressionItemId(long progressionItemId)
		throws NoSuchItemException;

	/**
	 * Returns the number of progression items where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the number of matching progression items
	 */
	public int countByprogressionItemId(long progressionItemId);

	/**
	 * Returns the progression item where sessionId = &#63; or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param sessionId the session ID
	 * @return the matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public ProgressionItem findBysessionId(long sessionId)
		throws NoSuchItemException;

	/**
	 * Returns the progression item where sessionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sessionId the session ID
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public ProgressionItem fetchBysessionId(long sessionId);

	/**
	 * Returns the progression item where sessionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sessionId the session ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public ProgressionItem fetchBysessionId(
		long sessionId, boolean useFinderCache);

	/**
	 * Removes the progression item where sessionId = &#63; from the database.
	 *
	 * @param sessionId the session ID
	 * @return the progression item that was removed
	 */
	public ProgressionItem removeBysessionId(long sessionId)
		throws NoSuchItemException;

	/**
	 * Returns the number of progression items where sessionId = &#63;.
	 *
	 * @param sessionId the session ID
	 * @return the number of matching progression items
	 */
	public int countBysessionId(long sessionId);

	/**
	 * Returns the progression item where homeworkId = &#63; or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param homeworkId the homework ID
	 * @return the matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public ProgressionItem findByhomeworkId(long homeworkId)
		throws NoSuchItemException;

	/**
	 * Returns the progression item where homeworkId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param homeworkId the homework ID
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public ProgressionItem fetchByhomeworkId(long homeworkId);

	/**
	 * Returns the progression item where homeworkId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param homeworkId the homework ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public ProgressionItem fetchByhomeworkId(
		long homeworkId, boolean useFinderCache);

	/**
	 * Removes the progression item where homeworkId = &#63; from the database.
	 *
	 * @param homeworkId the homework ID
	 * @return the progression item that was removed
	 */
	public ProgressionItem removeByhomeworkId(long homeworkId)
		throws NoSuchItemException;

	/**
	 * Returns the number of progression items where homeworkId = &#63;.
	 *
	 * @param homeworkId the homework ID
	 * @return the number of matching progression items
	 */
	public int countByhomeworkId(long homeworkId);

	/**
	 * Returns all the progression items where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the matching progression items
	 */
	public java.util.List<ProgressionItem> findByprogressionFolderId(
		long progressionFolderId);

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
	public java.util.List<ProgressionItem> findByprogressionFolderId(
		long progressionFolderId, int start, int end);

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
	public java.util.List<ProgressionItem> findByprogressionFolderId(
		long progressionFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
			orderByComparator);

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
	public java.util.List<ProgressionItem> findByprogressionFolderId(
		long progressionFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public ProgressionItem findByprogressionFolderId_First(
			long progressionFolderId,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Returns the first progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public ProgressionItem fetchByprogressionFolderId_First(
		long progressionFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
			orderByComparator);

	/**
	 * Returns the last progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public ProgressionItem findByprogressionFolderId_Last(
			long progressionFolderId,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Returns the last progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public ProgressionItem fetchByprogressionFolderId_Last(
		long progressionFolderId,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
			orderByComparator);

	/**
	 * Returns the progression items before and after the current progression item in the ordered set where progressionFolderId = &#63;.
	 *
	 * @param progressionItemId the primary key of the current progression item
	 * @param progressionFolderId the progression folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next progression item
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	public ProgressionItem[] findByprogressionFolderId_PrevAndNext(
			long progressionItemId, long progressionFolderId,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Removes all the progression items where progressionFolderId = &#63; from the database.
	 *
	 * @param progressionFolderId the progression folder ID
	 */
	public void removeByprogressionFolderId(long progressionFolderId);

	/**
	 * Returns the number of progression items where progressionFolderId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @return the number of matching progression items
	 */
	public int countByprogressionFolderId(long progressionFolderId);

	/**
	 * Returns all the progression items where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @return the matching progression items
	 */
	public java.util.List<ProgressionItem>
		findByprogressionFolderId_progressionId(
			long progressionFolderId, long progressionId);

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
	public java.util.List<ProgressionItem>
		findByprogressionFolderId_progressionId(
			long progressionFolderId, long progressionId, int start, int end);

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
	public java.util.List<ProgressionItem>
		findByprogressionFolderId_progressionId(
			long progressionFolderId, long progressionId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
				orderByComparator);

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
	public java.util.List<ProgressionItem>
		findByprogressionFolderId_progressionId(
			long progressionFolderId, long progressionId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
				orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public ProgressionItem findByprogressionFolderId_progressionId_First(
			long progressionFolderId, long progressionId,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Returns the first progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public ProgressionItem fetchByprogressionFolderId_progressionId_First(
		long progressionFolderId, long progressionId,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
			orderByComparator);

	/**
	 * Returns the last progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression item
	 * @throws NoSuchItemException if a matching progression item could not be found
	 */
	public ProgressionItem findByprogressionFolderId_progressionId_Last(
			long progressionFolderId, long progressionId,
			com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
				orderByComparator)
		throws NoSuchItemException;

	/**
	 * Returns the last progression item in the ordered set where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching progression item, or <code>null</code> if a matching progression item could not be found
	 */
	public ProgressionItem fetchByprogressionFolderId_progressionId_Last(
		long progressionFolderId, long progressionId,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
			orderByComparator);

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
	public ProgressionItem[]
			findByprogressionFolderId_progressionId_PrevAndNext(
				long progressionItemId, long progressionFolderId,
				long progressionId,
				com.liferay.portal.kernel.util.OrderByComparator
					<ProgressionItem> orderByComparator)
		throws NoSuchItemException;

	/**
	 * Removes all the progression items where progressionFolderId = &#63; and progressionId = &#63; from the database.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 */
	public void removeByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId);

	/**
	 * Returns the number of progression items where progressionFolderId = &#63; and progressionId = &#63;.
	 *
	 * @param progressionFolderId the progression folder ID
	 * @param progressionId the progression ID
	 * @return the number of matching progression items
	 */
	public int countByprogressionFolderId_progressionId(
		long progressionFolderId, long progressionId);

	/**
	 * Caches the progression item in the entity cache if it is enabled.
	 *
	 * @param progressionItem the progression item
	 */
	public void cacheResult(ProgressionItem progressionItem);

	/**
	 * Caches the progression items in the entity cache if it is enabled.
	 *
	 * @param progressionItems the progression items
	 */
	public void cacheResult(java.util.List<ProgressionItem> progressionItems);

	/**
	 * Creates a new progression item with the primary key. Does not add the progression item to the database.
	 *
	 * @param progressionItemId the primary key for the new progression item
	 * @return the new progression item
	 */
	public ProgressionItem create(long progressionItemId);

	/**
	 * Removes the progression item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param progressionItemId the primary key of the progression item
	 * @return the progression item that was removed
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	public ProgressionItem remove(long progressionItemId)
		throws NoSuchItemException;

	public ProgressionItem updateImpl(ProgressionItem progressionItem);

	/**
	 * Returns the progression item with the primary key or throws a <code>NoSuchItemException</code> if it could not be found.
	 *
	 * @param progressionItemId the primary key of the progression item
	 * @return the progression item
	 * @throws NoSuchItemException if a progression item with the primary key could not be found
	 */
	public ProgressionItem findByPrimaryKey(long progressionItemId)
		throws NoSuchItemException;

	/**
	 * Returns the progression item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param progressionItemId the primary key of the progression item
	 * @return the progression item, or <code>null</code> if a progression item with the primary key could not be found
	 */
	public ProgressionItem fetchByPrimaryKey(long progressionItemId);

	/**
	 * Returns all the progression items.
	 *
	 * @return the progression items
	 */
	public java.util.List<ProgressionItem> findAll();

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
	public java.util.List<ProgressionItem> findAll(int start, int end);

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
	public java.util.List<ProgressionItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
			orderByComparator);

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
	public java.util.List<ProgressionItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProgressionItem>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the progression items from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of progression items.
	 *
	 * @return the number of progression items
	 */
	public int countAll();

}