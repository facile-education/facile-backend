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

import com.weprode.nero.progression.exception.NoSuchItemAttachedFileException;
import com.weprode.nero.progression.model.ItemAttachedFile;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the item attached file service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ItemAttachedFileUtil
 * @generated
 */
@ProviderType
public interface ItemAttachedFilePersistence
	extends BasePersistence<ItemAttachedFile> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ItemAttachedFileUtil} to access the item attached file persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the item attached file where itemAttachedFileId = &#63; or throws a <code>NoSuchItemAttachedFileException</code> if it could not be found.
	 *
	 * @param itemAttachedFileId the item attached file ID
	 * @return the matching item attached file
	 * @throws NoSuchItemAttachedFileException if a matching item attached file could not be found
	 */
	public ItemAttachedFile findByitemAttachedFileId(long itemAttachedFileId)
		throws NoSuchItemAttachedFileException;

	/**
	 * Returns the item attached file where itemAttachedFileId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itemAttachedFileId the item attached file ID
	 * @return the matching item attached file, or <code>null</code> if a matching item attached file could not be found
	 */
	public ItemAttachedFile fetchByitemAttachedFileId(long itemAttachedFileId);

	/**
	 * Returns the item attached file where itemAttachedFileId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itemAttachedFileId the item attached file ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching item attached file, or <code>null</code> if a matching item attached file could not be found
	 */
	public ItemAttachedFile fetchByitemAttachedFileId(
		long itemAttachedFileId, boolean useFinderCache);

	/**
	 * Removes the item attached file where itemAttachedFileId = &#63; from the database.
	 *
	 * @param itemAttachedFileId the item attached file ID
	 * @return the item attached file that was removed
	 */
	public ItemAttachedFile removeByitemAttachedFileId(long itemAttachedFileId)
		throws NoSuchItemAttachedFileException;

	/**
	 * Returns the number of item attached files where itemAttachedFileId = &#63;.
	 *
	 * @param itemAttachedFileId the item attached file ID
	 * @return the number of matching item attached files
	 */
	public int countByitemAttachedFileId(long itemAttachedFileId);

	/**
	 * Returns all the item attached files where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the matching item attached files
	 */
	public java.util.List<ItemAttachedFile> findByprogressionItemId(
		long progressionItemId);

	/**
	 * Returns a range of all the item attached files where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @return the range of matching item attached files
	 */
	public java.util.List<ItemAttachedFile> findByprogressionItemId(
		long progressionItemId, int start, int end);

	/**
	 * Returns an ordered range of all the item attached files where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item attached files
	 */
	public java.util.List<ItemAttachedFile> findByprogressionItemId(
		long progressionItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAttachedFile>
			orderByComparator);

	/**
	 * Returns an ordered range of all the item attached files where progressionItemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param progressionItemId the progression item ID
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching item attached files
	 */
	public java.util.List<ItemAttachedFile> findByprogressionItemId(
		long progressionItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAttachedFile>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first item attached file in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item attached file
	 * @throws NoSuchItemAttachedFileException if a matching item attached file could not be found
	 */
	public ItemAttachedFile findByprogressionItemId_First(
			long progressionItemId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemAttachedFile>
				orderByComparator)
		throws NoSuchItemAttachedFileException;

	/**
	 * Returns the first item attached file in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item attached file, or <code>null</code> if a matching item attached file could not be found
	 */
	public ItemAttachedFile fetchByprogressionItemId_First(
		long progressionItemId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAttachedFile>
			orderByComparator);

	/**
	 * Returns the last item attached file in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item attached file
	 * @throws NoSuchItemAttachedFileException if a matching item attached file could not be found
	 */
	public ItemAttachedFile findByprogressionItemId_Last(
			long progressionItemId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemAttachedFile>
				orderByComparator)
		throws NoSuchItemAttachedFileException;

	/**
	 * Returns the last item attached file in the ordered set where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item attached file, or <code>null</code> if a matching item attached file could not be found
	 */
	public ItemAttachedFile fetchByprogressionItemId_Last(
		long progressionItemId,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAttachedFile>
			orderByComparator);

	/**
	 * Returns the item attached files before and after the current item attached file in the ordered set where progressionItemId = &#63;.
	 *
	 * @param itemAttachedFileId the primary key of the current item attached file
	 * @param progressionItemId the progression item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item attached file
	 * @throws NoSuchItemAttachedFileException if a item attached file with the primary key could not be found
	 */
	public ItemAttachedFile[] findByprogressionItemId_PrevAndNext(
			long itemAttachedFileId, long progressionItemId,
			com.liferay.portal.kernel.util.OrderByComparator<ItemAttachedFile>
				orderByComparator)
		throws NoSuchItemAttachedFileException;

	/**
	 * Removes all the item attached files where progressionItemId = &#63; from the database.
	 *
	 * @param progressionItemId the progression item ID
	 */
	public void removeByprogressionItemId(long progressionItemId);

	/**
	 * Returns the number of item attached files where progressionItemId = &#63;.
	 *
	 * @param progressionItemId the progression item ID
	 * @return the number of matching item attached files
	 */
	public int countByprogressionItemId(long progressionItemId);

	/**
	 * Caches the item attached file in the entity cache if it is enabled.
	 *
	 * @param itemAttachedFile the item attached file
	 */
	public void cacheResult(ItemAttachedFile itemAttachedFile);

	/**
	 * Caches the item attached files in the entity cache if it is enabled.
	 *
	 * @param itemAttachedFiles the item attached files
	 */
	public void cacheResult(java.util.List<ItemAttachedFile> itemAttachedFiles);

	/**
	 * Creates a new item attached file with the primary key. Does not add the item attached file to the database.
	 *
	 * @param itemAttachedFileId the primary key for the new item attached file
	 * @return the new item attached file
	 */
	public ItemAttachedFile create(long itemAttachedFileId);

	/**
	 * Removes the item attached file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemAttachedFileId the primary key of the item attached file
	 * @return the item attached file that was removed
	 * @throws NoSuchItemAttachedFileException if a item attached file with the primary key could not be found
	 */
	public ItemAttachedFile remove(long itemAttachedFileId)
		throws NoSuchItemAttachedFileException;

	public ItemAttachedFile updateImpl(ItemAttachedFile itemAttachedFile);

	/**
	 * Returns the item attached file with the primary key or throws a <code>NoSuchItemAttachedFileException</code> if it could not be found.
	 *
	 * @param itemAttachedFileId the primary key of the item attached file
	 * @return the item attached file
	 * @throws NoSuchItemAttachedFileException if a item attached file with the primary key could not be found
	 */
	public ItemAttachedFile findByPrimaryKey(long itemAttachedFileId)
		throws NoSuchItemAttachedFileException;

	/**
	 * Returns the item attached file with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemAttachedFileId the primary key of the item attached file
	 * @return the item attached file, or <code>null</code> if a item attached file with the primary key could not be found
	 */
	public ItemAttachedFile fetchByPrimaryKey(long itemAttachedFileId);

	/**
	 * Returns all the item attached files.
	 *
	 * @return the item attached files
	 */
	public java.util.List<ItemAttachedFile> findAll();

	/**
	 * Returns a range of all the item attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @return the range of item attached files
	 */
	public java.util.List<ItemAttachedFile> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the item attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item attached files
	 */
	public java.util.List<ItemAttachedFile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAttachedFile>
			orderByComparator);

	/**
	 * Returns an ordered range of all the item attached files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ItemAttachedFileModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of item attached files
	 * @param end the upper bound of the range of item attached files (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of item attached files
	 */
	public java.util.List<ItemAttachedFile> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ItemAttachedFile>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the item attached files from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of item attached files.
	 *
	 * @return the number of item attached files
	 */
	public int countAll();

}